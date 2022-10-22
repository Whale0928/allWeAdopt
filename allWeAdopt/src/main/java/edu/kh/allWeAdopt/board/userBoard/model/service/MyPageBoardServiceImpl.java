package edu.kh.allWeAdopt.board.userBoard.model.service;

import edu.kh.allWeAdopt.board.model.vo.Board;
import edu.kh.allWeAdopt.board.model.vo.Pagination;
import edu.kh.allWeAdopt.board.userBoard.model.dao.MyPageBoardDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MyPageBoardServiceImpl implements MyPageBoardService {
	
	@Autowired
	private MyPageBoardDAO dao;

	/** 게시글 리스트 조회
	 * 
	 * @param cp
	 * @param memberNo
	 * @return map
	 */
	@Override
	public Map<String, Object> MyPageBoardService(int cp, int memberNo) {
		
		// 페이지네이션 객체 생성
		int listCount = dao.getListCount(memberNo);
		Pagination pagination = new Pagination(cp, listCount);
		
		// 게시판 목록 조회
		List<Board> boardList = dao.selectBoardList(pagination,memberNo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("pagination", pagination);
		map.put("boardList", boardList);
		return map;
	}

	/** 관심등록 리스트 조회
	 * @param cp
	 * @param memberNo
	 * @return map
	 */
	@Override
	public Map<String, Object> likeListBoard(int cp, int memberNo) {
		
		// 페이지네이션 객체 생성
		int listCount = dao.getLikeCount(memberNo);
		Pagination pagination = new Pagination(cp, listCount,9);
		
		// 게시판 목록 조회
		List<Board> boardList = dao.likeBoardList(pagination,memberNo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("pagination", pagination);
		map.put("boardList", boardList);
		return map;
	}

}
