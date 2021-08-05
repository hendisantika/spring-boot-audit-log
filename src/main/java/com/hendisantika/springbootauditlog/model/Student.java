package com.hendisantika.springbootauditlog.model;

import com.hendisantika.springbootauditlog.audit.AuditAware;
import com.hendisantika.springbootauditlog.audit.AuditLogListener;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-audit-log
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 06/08/21
 * Time: 06.01
 */
@Entity
@Data
@NoArgsConstructor
@EntityListeners(value = {AuditLogListener.class})
public class Student implements Serializable, AuditAware {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;

}
