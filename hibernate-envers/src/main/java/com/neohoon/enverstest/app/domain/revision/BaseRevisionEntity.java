package com.neohoon.enverstest.app.domain.revision;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Objects;

@Entity
@RevisionEntity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Table(name = "revinfo")
public class BaseRevisionEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @RevisionNumber
    private Long rev;
    @RevisionTimestamp
    private long myRevisionDate;
    @CreatedBy
    private Long createdBy;
}
