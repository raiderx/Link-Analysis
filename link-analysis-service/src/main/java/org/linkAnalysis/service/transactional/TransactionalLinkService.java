package org.linkAnalysis.service.transactional;

import org.linkAnalysis.model.dao.LinkDao;
import org.linkAnalysis.model.entity.Link;
import org.linkAnalysis.model.search.LinkSearchCriteria;
import org.linkAnalysis.model.search.SearchResult;
import org.linkAnalysis.service.LinkService;
import org.linkAnalysis.service.ServiceResult;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * @author Pavel Karpukhin
 */
public class TransactionalLinkService
        extends AbstractTransactionalEntityService<Link, LinkDao>
        implements LinkService {

    public TransactionalLinkService(LinkDao linkDao) {
        this.dao = linkDao;
    }

    @Override
    public SearchResult<Link> getLinksByCriteria(LinkSearchCriteria searchCriteria) {
        return dao.getLinksByCriteria(searchCriteria);
    }

    @Override
    public ServiceResult<Boolean> isUrlAvailable(String url) {
        ServiceResult<Boolean> result = null;
        try {
            URL u = new URL(url);
            HttpURLConnection huc = (HttpURLConnection)u.openConnection();
            huc.setRequestMethod("GET");
            huc.connect();
            result = new ServiceResult<Boolean>(huc.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (MalformedURLException e) {
            result = ServiceResult.fail(e.getMessage());
        } catch (IOException e) {
            result = ServiceResult.fail(e.getMessage());
        }
        return result;
    }

    @Override
    public String[] getImageUrlsForUrl(String url) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
