package org.linkAnalysis.service;

import org.linkAnalysis.model.entity.Image;
import org.linkAnalysis.model.search.ImageSearchCriteria;
import org.linkAnalysis.model.search.SearchResult;
import org.linkAnalysis.service.util.ServiceResult;

import java.util.List;

/**
 * This interface has methods which give us more abilities in
 * manipulation {@link Image} persistent entity
 *
 * @author Pavel Karpukhin
 */
public interface ImageService extends EntityService<Image> {

    SearchResult<Image> getImagesByCriteria(ImageSearchCriteria searchCriteria);

    ServiceResult<List<String>> findAllImageUrlsForUrl(String url);

    ServiceResult<Image> download(Image image);

}
