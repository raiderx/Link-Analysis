package org.linkAnalysis.service.transactional;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.linkAnalysis.model.dao.ImageDao;
import org.linkAnalysis.model.dao.LinkDao;
import org.linkAnalysis.model.entity.Image;
import org.linkAnalysis.model.entity.Link;
import org.linkAnalysis.model.search.ImageSearchCriteria;
import org.linkAnalysis.model.search.SearchResult;
import org.linkAnalysis.service.ImageService;
import org.linkAnalysis.service.util.ServiceResult;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Pavel Karpukhin
 */
public class TransactionalImageService
        extends AbstractTransactionalEntityService<Image, ImageDao>
        implements ImageService {

    @Override
    public SearchResult<Image> getImagesByCriteria(ImageSearchCriteria searchCriteria) {
        return dao.getImagesByCriteria(searchCriteria);
    }

    @Override
    public ServiceResult<List<String>> findAllImageUrlsForUrl(String url) {
        List<String> result = new ArrayList<String>();
        try {
            Parser parser = new Parser(url);
            NodeFilter filter = new NodeClassFilter(ImageTag.class);
            NodeList list = parser.extractAllNodesThatMatch(filter);
            for (int i = 0; i < list.size (); i++) {
                result.add(((ImageTag)list.elementAt(i)).getImageURL());
            }
        } catch (ParserException e) {
            return ServiceResult.fail(e.getMessage());
        }
        return new ServiceResult<List<String>>(result);
    }

    @Override
    public ServiceResult<Image> download(Image image) {
        try{
            URL url = new URL(image.getUrl());
            InputStream stream = url.openStream();
            image.setSize(stream.available());
            BufferedImage bufferedImage = ImageIO.read(stream);
            image.setWidth(bufferedImage.getWidth());
            image.setHeight(bufferedImage.getHeight());
            dao.update(image);
        } catch (IOException e) {
            return ServiceResult.fail(e.getMessage());
        }
        return new ServiceResult<Image>(image);
    }

    public void setImageDao(ImageDao imageDao) {
        this.dao = imageDao;
    }
}
