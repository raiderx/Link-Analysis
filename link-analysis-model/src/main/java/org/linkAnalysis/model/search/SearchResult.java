package org.linkAnalysis.model.search;

import org.linkAnalysis.model.entity.DomainEntity;

import java.util.List;

/**
 * @author Pavel Karpukhin
 */
public class SearchResult<T extends DomainEntity> {

    private int pageCount;
    private List<T> result;

    /**
     * Constructs the search result with given page count and list of entities
     *
     * @param pageCount page count
     * @param result list of entities
     */
    public SearchResult(int pageCount, List<T> result) {
        this.pageCount = pageCount;
        this.result = result;
    }

    /**
     * Returns page count
     *
     * @return a page count
     */
    public int getPageCount() {
        return pageCount;
    }

    /**
     * Returns a list of entities
     *
     * @return a list of entities
     */
    public List<T> getResult() {
        return result;
    }
}
