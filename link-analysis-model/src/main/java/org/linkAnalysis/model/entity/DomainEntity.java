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

    /**
     * Returns unique identifier of this entity
     *
     * @return unique identifier of this entity
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets unique identifier of this entity
     *
     * @param id  unique identifier of this entity
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns version of this entity
     *
     * @return version value
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * Sets version of this entity
     *
     * @param version  version value
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * Returns the value
     *
     * @return
     */
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * Returns creation date of this entity
     *
     * @return creation date value
     */
    public DateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Sets creation date of this entity
     *
     * @param creationDate  creation date value
     */
    public void setCreationDate(DateTime creationDate) {
        this.creationDate = creationDate;
    }
}
