package com.hendisantika.springbootauditlog.service;

import com.hendisantika.springbootauditlog.dto.AuditTrailDTO;
import com.hendisantika.springbootauditlog.model.AuditTrail;
import com.hendisantika.springbootauditlog.repository.AuditTrailRepository;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-audit-log
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 06/08/21
 * Time: 06.08
 */
@Service
public class AuditLogService {

    private final AuditTrailRepository auditTrailRepository;

    public AuditLogService(AuditTrailRepository auditTrailRepository) {
        this.auditTrailRepository = auditTrailRepository;
    }

    public void save(AuditTrailDTO auditTrailDTO) {
        auditTrailRepository.save(new AuditTrail(auditTrailDTO));
    }

}
