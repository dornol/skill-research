package com.neohoon.enverstest.app.domain;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@MappedSuperclass
@Audited
@Getter
public class BaseEntity {
    @CreatedBy
    private Long createdBy;
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedBy
    private Long lastModifiedBy;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
    private boolean deleted;

    public void delete() {
        this.deleted = true;
    }
}
