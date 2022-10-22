package edu.kh.allWeAdopt.board.shelter.model.service;


import edu.kh.allWeAdopt.board.model.vo.Board;
import edu.kh.allWeAdopt.board.model.vo.BoardDetail;
import edu.kh.allWeAdopt.board.model.vo.Pagination;
import edu.kh.allWeAdopt.board.shelter.model.dao.ShelterDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ShelterServiceImpl implements ShelterService {
	
	@Autowired
	private ShelterDAO dao;
	
	
	private final Logger logger = LoggerFactory.getLogger(ShelterServiceImpl.class);

	// 전단지 목록 조회
	@Override
	public Map<String, Object> selectPamphletList(int cp) {

		// 전단지 수 조회 
		int listCount = dao.getListCount();
		
		
		Pagination pagination = new Pagination(cp, listCount);
		
		// 전단지 목록 조회 
		List<Board> pamphletList = dao.selectPamphletList(pagination);
				
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagination", pagination);
		map.put("pamphletList", pamphletList);

		
		
		return map;
	}

	
	// 전단지 상세 조회
	@Override
	public BoardDetail pamphletDetail(int boardNo) {
		return dao.pamphletDetail(boardNo);
	}


	// 전단지 작성
	@Override
	public int insertPamphlet(BoardDetail detail) {		
		return dao.insertPamphlet(detail);
	}


	// 전단지 수정
	@Override
	public int updatePamphlet(BoardDetail detail) {
		return dao.updatePamphlet(detail);
	}

	
	// 전단지 삭제
	@Override
	public int deleteBoard(int boardNo) {
		return dao.deletePamphlet(boardNo);
	}
	
	
	
}
