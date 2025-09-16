package com.nhphuong.postservice.model.listener;

import com.nhphuong.postservice.model.AbstractAuditEntity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.AuditingHandler;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Configuration
public class CustomAuditingEntityListener extends AuditingEntityListener {

    public CustomAuditingEntityListener(ObjectFactory<AuditingHandler> handler) {
        super.setAuditingHandler(handler);
    }

    @Override
    @PrePersist
    public void touchForCreate(Object target) {
        if (target instanceof AbstractAuditEntity entity) {
            if (entity.getCreatedBy() == null) {
                super.touchForCreate(entity);
            } else {
                if (entity.getLastModifiedBy() == null) {
                    entity.setLastModifiedBy(entity.getCreatedBy());
                }
            }
        }
    }

    @Override
    @PreUpdate
    public void touchForUpdate(Object target) {
        if (target instanceof AbstractAuditEntity entity) {
            if (entity.getLastModifiedBy() == null) {
                super.touchForUpdate(target);
            }
        }
    }
}
