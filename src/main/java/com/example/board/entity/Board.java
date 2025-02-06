package com.example.board.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "board")
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // 요구사항 -> 제목 : 필수
    private String title;

    @Column(columnDefinition = "Longtext") // 요구사항 -> 내용 : 긴 텍스트
    private String contents;

    @ManyToOne // 1명의 회원은 여러개의 게시글을 작성할 수 있다. (N:1 단방향)
    @JoinColumn(name = "member_id") // board 테이블에서 "member_id" 컬럼을 생성하여 member 테이블의 id를 참조
    private Member member; // 이 클래스(Board Entity 내부)의 이 member 필드는 Member Entity(클래스)를 참조하는 필드

    // => DB Board 테이블에는 회원 정보를 직접 저장하는 것이 아니라, Member 테이블의 id 값을 외래 키(FK)로 저장
    // 즉, member 필드는 Board 테이블의 member_id(FK)를 통해 Member Entity(테이블)와 연결

}
