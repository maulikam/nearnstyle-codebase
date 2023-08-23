package com.nearnstyle.apis.common;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public class EntityAuditInfo {

    @CreatedBy
    @Basic(optional = false)
    @Column(name = "created_by", nullable = false)
    private Long createdBy;
    @CreatedDate
    @Basic(optional = false)
    @Column(name = "created_on", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @LastModifiedBy
    @Column(name = "modified_by")
    private Long modifiedBy;
    @LastModifiedDate
    @Column(name = "modified_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedOn;

    public static class Fields {

        private Fields() {

        }

        public static final String CREATED_BY = "createdBy";
        public static final String CREATED_ON = "createdOn";
        public static final String MODIFIED_BY = "modifiedBy";
        public static final String MODIFIED_ON = "modifiedOn";
        public static final String ADMISSION_ID = "admissionId";

    }

}
