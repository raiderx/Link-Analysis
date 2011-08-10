package org.linkAnalysis.service.transactional;

import com.sun.jmx.remote.util.Service;
import org.linkAnalysis.model.dao.ImageDao;
import org.linkAnalysis.model.dao.LinkDao;
import org.linkAnalysis.model.entity.Image;
import org.linkAnalysis.model.entity.Link;
import org.linkAnalysis.model.search.LinkSearchCriteria;
import org.linkAnalysis.model.search.SearchResult;
import org.linkAnalysis.service.ImageService;
import org.linkAnalysis.service.LinkService;
import org.linkAnalysis.service.util.ServiceResult;
import org.linkAnalysis.service.util.ValidationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Pavel Karpukhin
 */
public class TransactionalLinkService
        extends AbstractTransactionalEntityService<Link, LinkDao>
        implements LinkService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private ImageService imageService;
    private ImageDao imageDao;

    //public TransactionalLinkService(LinkDao linkDao) {
    //    this.dao = linkDao;
    //}

    @Override
    public SearchResult<Link> getLinksByCriteria(LinkSearchCriteria searchCriteria) {
        return dao.getLinksByCriteria(searchCriteria);
    }

    private boolean isUrlAvailable(String url)
            throws IOException {
        URL u = new URL(url);
        HttpURLConnection huc = (HttpURLConnection)u.openConnection();
        huc.setRequestMethod("GET");
        huc.connect();
        return huc.getResponseCode() == HttpURLConnection.HTTP_OK;
    }

    @Override
    public ServiceResult<Link> create(Link link) {
        //ValidationResult validationResult = new ValidationResult();
        try {
            if (!isUrlAvailable(link.getUrl())) {
                return ServiceResult.fail("url is unavailable");
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
            return ServiceResult.fail(e.getMessage());
        }

        // TODO Check if there is the same link in database
        dao.create(link);

        return new ServiceResult<Link>(link);
    }

    @Override
    public ServiceResult<Link> analyzeLink(Link link, String method) {
        link = dao.get(link.getId());
        ServiceResult<List<String>> res = imageService.findAllImageUrlsForUrl(link.getUrl());
        if (res.isFailed()) {
            return ServiceResult.fail("some error");
        }

        final List<Image> images = new ArrayList<Image>();

        for (String url : res.getResult()) {
            Image image = new Image();
            image.setLink(link);
            image.setUrl(url);
            image.setDownloaded(false);
            imageDao.create(image);
            images.add(image);
        }
        for (Image image : images) {
            ServiceResult<Image> res2 = imageService.download(image);

            for (ValidationResult.MessageInfo messageInfo : res2.getErrors()) {
                logger.error(messageInfo.getMessage());
            }
        }
        /*Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
            }
        });

        thread.start();*/

        return new ServiceResult<Link>(link);
    }

    @Override
    public String[] getImageUrlsForUrl(String url) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void setLinkDao(LinkDao linkDao) {
        this.dao = linkDao;
    }

    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }

    public void setImageDao(ImageDao imageDao) {
        this.imageDao = imageDao;
    }
}
