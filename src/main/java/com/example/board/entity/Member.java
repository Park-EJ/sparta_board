package com.example.board.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "member") // member 테이블과 매핑. 만약 @Table을 생략하면 클래스명(Member)을 테이블 이름으로 자동 설정함
public class Member extends BaseEntity { // BaseEntity를 상속받은 Entity는 별도의 설정 없이 자동으로 생성/수정 시간을 관리할 수 있습니다.

    @Id // @Entity는 기본키가 필수이다. 자동생성이니 setter, getter는 필요없다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가(AUTO_INCREMENT)를 사용하여 ID를 자동 생성. MySQL을 사용하니 IDENTITY로 설정한다.
    private Long id;

    @Column(nullable = false, unique = true) // JPA Entity의 필드를 데이터베이스의 컬럼과 매핑, 요구사항 -> 로그인에 사용할 아이디 : 필수, 유일
    private String username;

    @Column(nullable = false) // 요구사항 -> 로그인에 사용할 비밀번호 : 필수
    private String password;

    private Integer age;






}
