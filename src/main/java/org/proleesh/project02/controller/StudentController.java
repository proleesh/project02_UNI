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
    public String saveStudent(@RequestParam(required = false) String name, // HTTP 요청 파라미터를 name 라는 값을 매칭한다.
                              @RequestParam(required = false) String email, // HTTP 요청 파라미터를 email 라는 값을 매칭한다.
                              @RequestParam(required = false) String address // HTTP 요청 파라미터를 address 라는 값을 매칭한다.
                              ){
        int result = studentDAO.addStudent(name, email, address); // DAO(Data Access Object) 에서 addStudent 메서드를 통해 값을 전달
        if(result == 1) { // 만약에 result = 1 이면 다음 조건문을 실행
            System.out.println("학생등록 성공!!!"); // 콘솔창에서 확인 메시지 내용은: 학생등록 성공!!
            // 해당 스크립트를 리턴
            return "<script>alert('학생등록에 성공했습니다!!!'); window.location.href='/student/lists'; </script>";
        }

        // 해당 스크립트를 리턴
        return "<script>alert('등록에 실패했습니다!!!'); window.location.href='/student/lists';</script>";
    }

    // [4] 학생 정보 수정
    // [4 - 1] 한 학생 정보 수정 폼(form)
    /*
    @RequestParam("xxx"), xxx에 데이터베이스에 테이블의 colum 이름을 매핑하기위한 쓰는것이다.
     */
    @GetMapping("/updateForm")
    public String updateForm(@RequestParam("studentId")int id, Model model){
        StudentDTO student = studentDAO.getStudentById(id);
        model.addAttribute("student", student);
        return "updateStudentForm";
    }

    /*
    @RequestParam 는 뭐죠?
    Spring MVC 컨트롤러 메서드에서 요청 매개 변수를 메서드 인수에 매핑하는 데 사용되는 어노테이션입니다. 웹 양식이나 URL 쿼리 문자열과 같은
    클라이언트로부터 전송된 HTTP 요청 매개 변수와 일치하는 메서드 매개 변수에 배치됩니다.
    required = false 는 뭐죠?
    @RequestParam 어노테이션의 required 속성은 해당 요청 매개 변수가 필수인지 선택 사항인지 지정합니다. 기본적으로 required 는 true 로
    설정되어 있으며, 요청에서 누락되면 예외가 발생합니다.
    required = false 는 언제 쓰죠?
    요청 매개 변수가 항상 필요하지 않거나 매개 변수가 누락된 경우를 우아하게 처리하려는 경우 required=false 를 사용해야 합니다.
    예를 들어, 선택 필터 또는 기본값을 가진 매개 변수에 required = false 를 사용할 수 있습니다.
     */
    // [4 - 2] 한 학생 정보 수정 액션(action)
    @PostMapping("/updateStudent")
    @ResponseBody
    public String updateStudent(@RequestParam(required = false) int id, // HTTP 요청 파라미터를 id 라는 값을 매칭한다.
                                @RequestParam(required = false) String name, // HTTP 요청 파라미터를 name 라는 값을 매칭한다.
                                @RequestParam(required = false) String email, // HTTP 요청 파라미터를 email 라는 값을 매칭한다.
                                @RequestParam(required = false) String address) // HTTP 요청 파라미터를 address 라는 값을 매칭한다.
    {
        int result = studentDAO.updateStudent(id, name, email, address);
        if(result == 1) {
            System.out.println("수정 성공");
            return "<script>alert('학생수정에 성공했습니다!!!'); window.location.href='/student/lists';</script>";}
        return "<script>alert('학생수정에 실패했습니다!!!'); window.location.href='/student/lists';</script>";
    }
    // [5] 학생 정보 삭제
    @GetMapping("/deleteForm")
    @ResponseBody
    public String delete(@RequestParam("studentId") int id){
        int result = studentDAO.deleteStudent(id);
        if(result == 1) {
            System.out.println("삭제 성공");
            return "<script>alert('학생삭제에 성공했습니다!!!'); window.location.href='/student/lists';</script>";
        }
        return "<script>alert('학생삭제에 실패했습니다!!!'); window.location.href='/student/lists';</script>";
    }
}
