package edu.kh.allWeAdopt.board.userBoard.model.dao;

import edu.kh.allWeAdopt.board.model.vo.Board;
import edu.kh.allWeAdopt.board.userBoard.model.vo.Animal;
import edu.kh.allWeAdopt.board.userBoard.model.vo.Area;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserBoardDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

//	게시글 등록
	public int userBoardRegist(Board board) {
		return sqlSession.insert("boardMapper.userBoardRegist",board);
	}

	public int getBoardNo() {
		return sqlSession.selectOne("boardMapper.getBoardNo");
	}
	
	public int userBoardArea(Area area) {
		return sqlSession.insert("boardMapper.userBoardArea",area);
	}
	
	public int userBoardAnimal(Animal animal) {
		return sqlSession.insert("boardMapper.userBoardAnimal",animal);
	}

	public List<Area> areaList() {
		List<Area> list =sqlSession.selectList("boardMapper.areaList");
		return list;
	}

//	상세 지역 리스트 호출
	public List<Area> loadAreaList(String area) {
		List<Area> list = sqlSession.selectList("boardMapper.loadAreaList",area);
		return list;
	}

// 사용자 게시글 리스트 호출
	public List<Board> boardList() {
		List<Board> list = sqlSession.selectList("boardMapper.boardList");
		return list;
	}

	// 상세 게시판 호출
	public Board boardDetail(int boardNo) {
		Board board = sqlSession.selectOne("boardMapper.boardDetail",boardNo);
		return board;
	}

	// 품종 리스트 호출
	public List<Animal> loadAnimalList(String animalType) {
		List<Animal> list = sqlSession.selectList("boardMapper.loadAnimalList",animalType);
		return list;
	}

	// 게시판 삭제
	public int boardDelete(int boardNo) {
		return sqlSession.update("boardMapper.boardDelete", boardNo);
	}

	// 게시글 수정
	public int userBoardModify(Board board) {
		return sqlSession.update("boardMapper.userBoardModify",board);
	}

	// 지역 수정
	public int boardAreaModify(Area area) {
		return sqlSession.update("boardMapper.boardAreaModify",area);
	}

	// 추가사항 수정
	public int boardAnimalModify(Animal animal) {
		return sqlSession.update("boardMapper.boardAnimalModify",animal);
	}

	// 관심동물 등록
	public int likeRegist(Board board, int likeNo) {
		int result = 0;
		System.out.println(likeNo);
		if(likeNo==0) {
			result = sqlSession.insert("boardMapper.likeRegist",board);
		}else {
			result = sqlSession.delete("boardMapper.likeDelete",board);
		}
		return result;
	}

	// 관심동물 호출
	public int likeList(Board likeBoard) {
		return sqlSession.selectOne("boardMapper.likeList",likeBoard);
	}

	public List<Board> searchList(Board board) {
		List<Board> list = sqlSession.selectList("boardMapper.searchList",board);
		return list;
	}

}
