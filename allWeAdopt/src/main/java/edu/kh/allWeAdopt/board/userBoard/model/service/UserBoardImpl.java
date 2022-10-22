package edu.kh.allWeAdopt.board.userBoard.model.service;

import edu.kh.allWeAdopt.board.model.vo.Board;
import edu.kh.allWeAdopt.board.userBoard.model.dao.UserBoardDAO;
import edu.kh.allWeAdopt.board.userBoard.model.vo.Animal;
import edu.kh.allWeAdopt.board.userBoard.model.vo.Area;
import edu.kh.allWeAdopt.common.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBoardImpl implements UserBoardService {

	@Autowired
	private UserBoardDAO dao;
	
	@Override
	public int userBoardRegist(Board board) {
		board.setBoardTitle(Util.XSSHandling(board.getBoardTitle()));
		board.setBoardTitle(Util.newLineHandling(board.getBoardTitle()));
		int result = dao.userBoardRegist(board);
		
		return result;
	}
	
	@Override
	public int getBoardNo() {
		return dao.getBoardNo();
	}

	@Override
	public int userBoardArea(Area area) {
		return dao.userBoardArea(area);	
	}

	@Override
	public int userBoardAnimal(Animal animal) {
		return dao.userBoardAnimal(animal);
	}

	@Override
	public List<Area> areaList() {
		List<Area> list= dao.areaList();
		return list;
	}

	// 상세지역 리스트 호출
	@Override
	public List<Area> loadAreaList(String area) {
		List<Area> list = dao.loadAreaList(area);
		return list;
	}

	// 사용자 게시글 리스트 호출
	@Override
	public List<Board> boardList() {
		List<Board> list = dao.boardList();
		
		return list;
	}

	// 상세 게시판 출력
	@Override
	public Board boardDetail(int boardNo) {
		Board board = dao.boardDetail(boardNo);
		return board;
	}

	// 품종 출력
	@Override
	public List<Animal> loadAnimalList(String animalType) {
		List<Animal> list = dao.loadAnimalList(animalType);
		return list;
	}

	// 게시글 삭제
	@Override
	public int boardDelete(int boardNo) {
		return dao.boardDelete(boardNo);
	}

	// 게시글 수정
	@Override
	public int userBoardModify(Board board) {
		board.setBoardTitle(Util.XSSHandling(board.getBoardTitle()));
		board.setBoardTitle(Util.newLineHandling(board.getBoardTitle()));
		return dao.userBoardModify(board);
	}

	// 지역 수정
	@Override
	public int boardAreaModify(Area area) {
		return dao.boardAreaModify(area);
	}
	
	// 추가사항 수정
	@Override
	public int boardAnimalModify(Animal animal) {
		return dao.boardAnimalModify(animal);
	}

	// 관심동물 등록
	@Override
	public int likeRegist(Board board, int likeNo) {
		return dao.likeRegist(board,likeNo);
	}

	// 관심동물 호출
	@Override
	public int likeList(Board likeBoard) {
		return dao.likeList(likeBoard);
	}

	// 게시글 검색
	@Override
	public List<Board> searchList(Board board) {
		List<Board> list = dao.searchList(board);
		return list;
	}
	
}
