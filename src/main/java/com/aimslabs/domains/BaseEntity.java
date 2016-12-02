package com.aimslabs.domains;

import com.aimslabs.rest.rest_config.JsonDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by sayemkcn on 11/9/16.
 */
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JsonSerialize(using=JsonDateSerializer.class)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    @PrePersist
    public void setCreated() {
        this.created = new Date();
    }

    public void setCreated(Date date) {
        this.created = new Date();
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    @PreUpdate
    public void setLastUpdated() {
        this.lastUpdated = new Date();
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                ", created=" + created +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}
