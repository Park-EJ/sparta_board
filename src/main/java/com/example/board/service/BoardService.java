package com.example.board.service;

import com.example.board.dto.BoardResponseDto;
import com.example.board.dto.BoardWithAgeResponseDto;
import com.example.board.entity.Board;
import com.example.board.entity.Member;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    // save는 SimpleJpaRepository 구현체 안에서 이미 @Transactional이 선언되어있다.
    public BoardResponseDto save(String title, String contents, String username) {

        Member findMember = memberRepository.findMembersByUsernameOrElseThrow(username);

        Board board = new Board(title, contents);
        board.setMember(findMember);

        Board savedBoard = boardRepository.save(board);

        return new BoardResponseDto(savedBoard.getId(), savedBoard.getTitle(), savedBoard.getContents());
    }

    public List<BoardResponseDto> findAll() {

        List<Board> findAllBoard = boardRepository.findAll();
       
        List<BoardResponseDto> boardResponseDtoList = new ArrayList<>();
        for (Board board : findAllBoard) {
            boardResponseDtoList.add(new BoardResponseDto(board.getId(), board.getTitle(), board.getContents()));
        }
        return boardResponseDtoList;
    }

    //  findAll stream 사용 버전
    //  public List<BoardResponseDto> findAll() {
    //     return boardRepository.findAll()
    //             .stream()
    //             .map(BoardResponseDto::toDto)
    //             .toList();
    //  }

    public BoardWithAgeResponseDto findById(Long id) {

        Board findBoard = boardRepository.findByIdOrElseThrow(id);

        Member writer = findBoard.getMember();

        return new BoardWithAgeResponseDto(findBoard.getTitle(), findBoard.getContents(), writer.getAge());
    }


    public void delete(Long id) {

        Board findBoard = boardRepository.findByIdOrElseThrow(id);

        boardRepository.delete(findBoard);
    }
}
