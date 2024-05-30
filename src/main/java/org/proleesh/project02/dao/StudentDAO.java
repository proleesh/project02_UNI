package org.proleesh.project02.dao;

import org.apache.ibatis.annotations.Mapper;
import org.proleesh.project02.dto.StudentDTO;

import java.util.List;
@Mapper
public interface StudentDAO {
    // [1] 전채 학생 목록 조회
    public List<StudentDTO> getAllStudents();

    // [2] 한 학생 목록 조회
    public StudentDTO getStudentById(int id);

    // [3] 학생 목록 등록
    public int addStudent(String name,
    String email,
    String address);

    // [4] 학생 정보 수정
    public int updateStudent(int id, String name, String email, String address);

    // [5] 학생 정보 삭제
    public int deleteStudent(int id);
}
