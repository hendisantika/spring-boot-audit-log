package com.hendisantika.springbootauditlog.controller;

import com.hendisantika.springbootauditlog.dto.StudentDTO;
import com.hendisantika.springbootauditlog.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-audit-log
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 06/08/21
 * Time: 06.15
 */
@Controller
@RequestMapping(value = "/students")
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public String list(Model model) {
        List<StudentDTO> studentDTOList = new ArrayList<>();
        studentRepository.findAll().forEach(student -> {
            studentDTOList.add(new StudentDTO(student));
        });
        model.addAttribute("studentDTOList", studentDTOList);
        return "student/list";
    }
}
