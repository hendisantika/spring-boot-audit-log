package com.hendisantika.springbootauditlog.repository;

import com.hendisantika.springbootauditlog.model.Student;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-audit-log
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 06/08/21
 * Time: 06.07
 */
public interface StudentRepository extends CrudRepository<Student, Long> {
}
