package com.hendisantika.springbootauditlog.model;

import com.hendisantika.springbootauditlog.dto.AuditTrailDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDate;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-audit-log
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 06/08/21
 * Time: 05.58
 */
@Entity
@Data
@NoArgsConstructor
public class AuditTrail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateCreated;
    private LocalDate lastUpdated;
    private String className;
    private String persistedObjectId;
    private String eventName;
    private String propertyName;
    private String oldValue;
    private String newValue;

    public AuditTrail(AuditTrailDTO auditTrailDTO) {
        this.className = auditTrailDTO.getClassName();
        this.persistedObjectId = auditTrailDTO.getPersistedObjectId();
        this.eventName = auditTrailDTO.getEventName();
        this.propertyName = auditTrailDTO.getPropertyName();
        this.oldValue = auditTrailDTO.getOldValue();
        this.newValue = auditTrailDTO.getNewValue();
    }

    @PrePersist
    void beforeInsert() {
        this.dateCreated = LocalDate.now();
        this.lastUpdated = LocalDate.now();
    }

    @PreUpdate
    void beforeUpdate() {
        this.lastUpdated = LocalDate.now();
    }

}
