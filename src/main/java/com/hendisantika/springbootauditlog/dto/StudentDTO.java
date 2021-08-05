package com.hendisantika.springbootauditlog.dto;

import com.hendisantika.springbootauditlog.model.Student;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-audit-log
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 06/08/21
 * Time: 06.01
 */
@Data
@NoArgsConstructor
public class StudentDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;

    public StudentDTO(Student student) {
        this.id = student.getId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.phoneNumber = student.getPhoneNumber();
        this.emailAddress = student.getEmailAddress();
    }
}
