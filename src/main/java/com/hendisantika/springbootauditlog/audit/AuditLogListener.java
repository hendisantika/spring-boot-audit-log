package com.hendisantika.springbootauditlog.audit;

import com.hendisantika.springbootauditlog.dto.AuditTrailDTO;
import com.hendisantika.springbootauditlog.service.AuditLogService;
import com.hendisantika.springbootauditlog.util.ApplicationContextProvider;
import com.hendisantika.springbootauditlog.util.Enums;
import lombok.extern.log4j.Log4j2;
import org.hibernate.event.spi.PostDeleteEvent;
import org.hibernate.event.spi.PostDeleteEventListener;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-audit-log
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 06/08/21
 * Time: 06.03
 */
@Log4j2
@Component
public class AuditLogListener implements PostInsertEventListener, PostUpdateEventListener, PostDeleteEventListener {

    @Override
    public void onPostInsert(PostInsertEvent event) {
        Object entity = event.getEntity();
        if (entity instanceof AuditAware) {
            List<AuditTrailDTO> auditTrailDTOList = new ArrayList<>();
            AuditLogService auditLogService =
                    (AuditLogService) ApplicationContextProvider.getApplicationContext().getBean("auditLogService");
            String[] propertyNames = event.getPersister().getPropertyNames();
            Object[] states = event.getState();
            for (int i = 0; i < propertyNames.length; i++) {
                log.info("Inside On Save   ************    ************** ===>>>      " + propertyNames[i]);
                auditTrailDTOList.add(new AuditTrailDTO(entity.getClass().getCanonicalName(),
                        event.getId().toString(), Enums.AuditEvent.INSERT.name(), propertyNames[i], null,
                        states[i].toString()));
            }
            auditTrailDTOList.forEach(auditLogService::save);
        }
    }

    @Override
    public boolean requiresPostCommitHanding(EntityPersister persister) {
        return false;
    }

    @Override
    public void onPostUpdate(PostUpdateEvent event) {
        Object entity = event.getEntity();
        if (entity instanceof AuditAware) {
            String[] propertyNames = event.getPersister().getPropertyNames();
            Object[] currentState = event.getState();
            Object[] previousState = event.getOldState();
            List<AuditTrailDTO> auditTrailDTOList = new ArrayList<>();
            AuditLogService auditLogService =
                    (AuditLogService) ApplicationContextProvider.getApplicationContext().getBean("auditLogService");
            for (int i = 0; i < currentState.length; i++) {
                if (!previousState[i].equals(currentState[i])) {
                    log.info("Inside On Flush Dirty   ************    **************      ==>>    " + propertyNames[i]);
                    auditTrailDTOList.add(new AuditTrailDTO(entity.getClass().getCanonicalName(),
                            event.getId().toString(), Enums.AuditEvent.UPDATE.name(), propertyNames[i],
                            previousState[i].toString(), currentState[i].toString()));
                }
            }
            auditTrailDTOList.forEach(auditLogService::save);
        }
    }

    @Override
    public void onPostDelete(PostDeleteEvent event) {
        Object entity = event.getEntity();
        if (entity instanceof AuditAware) {
            String[] propertyNames = event.getPersister().getPropertyNames();
            Object[] state = event.getDeletedState();
            List<AuditTrailDTO> auditTrailDTOList = new ArrayList<>();
            AuditLogService auditLogService =
                    (AuditLogService) ApplicationContextProvider.getApplicationContext().getBean("auditLogService");
            for (int i = 0; i < propertyNames.length; i++) {
                log.info("Inside On Delete   ************    ************** ===>>>      " + propertyNames[i]);
                auditTrailDTOList.add(new AuditTrailDTO(entity.getClass().getCanonicalName(),
                        event.getId().toString(), Enums.AuditEvent.DELETE.name(), propertyNames[i],
                        state[i].toString(), null));
            }
            auditTrailDTOList.forEach(auditLogService::save);
        }
    }
}
