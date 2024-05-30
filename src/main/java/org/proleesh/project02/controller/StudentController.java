package org.proleesh.project02.controller;

import lombok.RequiredArgsConstructor;
import org.proleesh.project02.dao.StudentDAO;
import org.proleesh.project02.dto.StudentDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
    // 4번의 장점?
    // DI(Dependency Injection)
    // 1. field injection
    // 2. setter injection
    // 3. constructor injection
    // 4. Lombok Constructor Dependency Injection
    private final StudentDAO studentDAO;

    // [1] 전채 학생 목록 조회
    @GetMapping("/lists")
    public String listStudents(Model model){
        List<StudentDTO> students = studentDAO.getAllStudents();
        model.addAttribute("students", students);
        return "listStudents";
    }
    // [2] 한 학생 목록 조회
    // [3] 학생 목록 등록
    // [4] 학생 정보 수정
    // [5] 학생 정보 삭제
}
