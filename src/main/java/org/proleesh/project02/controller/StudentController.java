package org.proleesh.project02.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.proleesh.project02.dao.StudentDAO;
import org.proleesh.project02.dto.StudentDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@Controller // 해당 Controller를 명시하여 Spring 컨테이너에게 맞긴다.
@RequestMapping("/student") // 매핑할 경로
@RequiredArgsConstructor // Lombok 에서 제공한 주입 애너테이션
public class StudentController {
    // 4번의 장점?
    // DI(Dependency Injection)
    // 1. field injection
    // 2. setter injection
    // 3. constructor injection
    // 4. Lombok Constructor Dependency Injection
    /* 4번의 장점은 @RequireArgsConstructor 또는 @AllArgsConstructor 어노테이션을 사용하여,
    자동으로 생성자를 생성해주기 때문에 코드가 간결해집니다.
    - 코드가 줄어들어, 가독성이 좋아집니다.
    - 의존성을 명확하게 하고, 불변성을 유지할 수 있습니다.
    - 필드 주입과 같은 단점을 피하면서, 생성자 주입의 장점을 유지할 수 있습니다.
    단점은 Lombok을 사용하지 않는 프로젝트에서는 사용할 수 없습니다.
    Lombok 라이브러리에 대한 의존성이 생깁니다.
     */
    private final StudentDAO studentDAO;

    // [1] 전채 학생 목록 조회
    // HTTP Get 요청을 처리하는 핸들러 메서드로, "/lists" 경로로 들어오는 요청을 처리합니다.
    @GetMapping("/lists")
    public String listStudents(Model model){
        // 모든 학생 정보를 가져오기 위해 studentDAO의 getAllStudents 메서드를 호출합니다.
        List<StudentDTO> students = studentDAO.getAllStudents();
        // 가져온 학생 목록을 "students"라는 이름으로 모델에 추가합니다.
        model.addAttribute("students", students);
        // "listStudents" 라는 뷰 이름을 반환합니다.
        // 이 이름에 해당하는 뷰가 렌더링되어 클라이언트에게 응답으로 전송됩니다.
        return "listStudents";
    }
    // [2] 한 학생정보 조회


    // [3] 학생 정보 등록
    // [3 - 1] 학생 등록 폼(Form)
    @GetMapping("/showForm")
    public String showFormAdd(Model model){
        StudentDTO student = new StudentDTO();
        model.addAttribute("student", student);
        return "studentForm";
    }

    // [3 - 2] 학생 등록 Action
    @PostMapping("/saveStudent")
    @ResponseBody
    public String saveStudent(@RequestParam(required = false) String name,
                              @RequestParam(required = false) String email,
                              @RequestParam(required = false) String address
                              ){
        int result = studentDAO.addStudent(name, email, address);
        if(result == 1) {
            System.out.println("학생등록 성공!!!");
                return "<script>alert('학생등록에 성공했습니다!!!'); window.location.href='/student/lists'; </script>";}

        return "<script>alert('등록에 실패했습니다!!!'); window.location.href='/student/lists';</script>";
    }

    // [4] 학생 정보 수정
    // [5] 학생 정보 삭제
}
