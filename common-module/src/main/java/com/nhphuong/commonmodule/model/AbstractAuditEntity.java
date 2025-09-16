package com.nhphuong.commonmodule.model;

import com.nhphuong.commonmodule.listener.CustomAuditingListener;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.ZonedDateTime;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(CustomAuditingListener.class)
public class AbstractAuditEntity {

    @CreationTimestamp
    public ZonedDateTime createdAt;

    @CreatedBy
    public String createdBy;

    @UpdateTimestamp
    public ZonedDateTime lastModifiedAt;

    @LastModifiedBy
    public String lastModifiedBy;
}
