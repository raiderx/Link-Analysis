package org.linkAnalysis.model.search;

import org.linkAnalysis.model.entity.DomainEntity;

/**
 * @author Pavel Karpukhin
 */
public class AbstractSearchCriteria<T extends DomainEntity> {

    private T example;
    private int page;
    private int resultsPerPage = 20;

    public T getExample() {
        return example;
    }

    public void setExample(T example) {
        this.example = example;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getResultsPerPage() {
        return resultsPerPage;
    }

    public void setResultsPerPage(int resultsPerPage) {
        this.resultsPerPage = resultsPerPage;
    }
}
