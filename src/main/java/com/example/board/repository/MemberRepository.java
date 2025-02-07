package com.example.board.repository;

import com.example.board.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // MemberRepository는 Member 테이블을 조작하는 기능을 자동으로 제공하는 인터페이스이다.
    // <Member → 이 Repository가 관리할 Entity 클래스
    // Long> → Member Entity의 기본키(PK) 타입

    Optional<Member> findMembersByUsername(String username); // Query Methods 활용

    default Member findMembersByUsernameOrElseThrow(String username) { // Default 메서드 활용
        return findMembersByUsername(username).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Does not exists username : " + username));
    }

    // interface는 메서드를 구현하지 못하지만 default 를 사용하여 인터페이스에 메서드를 구현할 수 있다.
    // findById를 Default 메서드 내부에서 사용하면 이 Repository를 Optional 이 아닌 Member Entity로 반환 가능하다.
    default Member findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Does not exists id : " + id));
    }




}
