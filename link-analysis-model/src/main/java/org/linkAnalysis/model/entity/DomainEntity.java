package org.linkAnalysis.model.entity;

import org.joda.time.DateTime;

/**
 * Base class for all entities
 *
 * @author Pavel Karpukhin
 */
public class DomainEntity {

    private Integer id;
    private Integer version;
    private Boolean active;
    private DateTime creationDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public DateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(DateTime creationDate) {
        this.creationDate = creationDate;
    }
}
