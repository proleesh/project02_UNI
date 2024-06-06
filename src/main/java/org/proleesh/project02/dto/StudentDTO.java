package org.proleesh.project02.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor // 파라미터(필드) 없는 즉 기본 생성자에 관한 애너테이션
@AllArgsConstructor // 파라미터(필드) 있는 즉 매개변수 있는 생성자에 관한 애너테이션
@Getter // Getter에 관한 애너테이션
@Setter // Setter에 관한 애너테이션
public class StudentDTO {
    private int id;
    private String name;
    private String email;
    private String address;
}
