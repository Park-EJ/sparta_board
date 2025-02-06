package com.example.board.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 이 클래스를 직접 테이블로 생성하지 않고, 상속받는 Entity에 속성만 전달
@EntityListeners(AuditingEntityListener.class) // JPA Auditing을 사용하여 자동으로 날짜 필드를 관리
public abstract class BaseEntity { // BaseEntity는 모든 Entity에서 공통적으로 사용할 생성 및 수정시간 필드를 관리하는 추상 클래스

    @CreatedDate // 	엔티티가 처음 저장될 때 createdAt 값을 자동으로 설정
    @Column(updatable = false) // createdAt 값은 수정 불가능
    private LocalDateTime createdAt;

    @LastModifiedDate // 	엔티티가 변경될 때 modifiedAt 값을 자동으로 업데이트
    private LocalDateTime modifiedAt;

}
