package edu.kh.allWeAdopt.board.model.service;

import edu.kh.allWeAdopt.board.model.dao.BoardDAO;
import edu.kh.allWeAdopt.board.model.vo.Board;
import edu.kh.allWeAdopt.board.model.vo.BoardDetail;
import edu.kh.allWeAdopt.board.model.vo.Pagination;
import edu.kh.allWeAdopt.board.model.vo.Template;
import edu.kh.allWeAdopt.common.Util;
import edu.kh.allWeAdopt.member.model.vo.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardDAO dao;
	
	private final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);

	// DB에 저장된 boardContent 조회
	@Override
	public List<String> selectDBList() {
		
		List<String> dbList = dao.selectDBList();
		
		List<String> fundingList = dao.selectFundingList();
		
		for(String f : fundingList) {
			dbList.add(f);
		}
		
		return dbList;
	}
	
	// ------------------------- 공지사항 ---------------------------
	
	// 공지사항 목록 조회 구현
	@Override
	public Map<String, Object> selectNoticeList(int cp) {
		
		// 1-1. 게시글 수 조회
		int listCount = dao.getListCount();
		
		// 1-2. 페이지네이션 객체 생성
		Pagination pagination = new Pagination(cp, listCount);
		
		// 2. 게시글 목록 조회
		List<Board> nList = dao.selectNoticeList(pagination);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("nList", nList);
		map.put("pagination", pagination);
		
		return map;
	}

	// 공지사항 상세 조회 구현
	@Override
	public BoardDetail selectNoticeDetail(int boardNo) {
		
		BoardDetail detail = dao.selectNoticeDetail(boardNo);
		
		return detail;
	}

	// 게시글 작성 기능 구현
	@Override
	public int insertBoard(BoardDetail detail) {
		
		// 제목만 XSS, 개행처리
		detail.setBoardTitle(Util.XSSHandling(detail.getBoardTitle()));
		detail.setBoardTitle(Util.newLineHandling(detail.getBoardTitle()));
		
		return dao.insertBoard(detail);
	}

	// 게시글 수정 기능 구현
	@Override
	public int updateBoard(BoardDetail detail) {
		
		// 제목만 XSS, 개행처리
		detail.setBoardTitle(Util.XSSHandling(detail.getBoardTitle()));
		detail.setBoardTitle(Util.newLineHandling(detail.getBoardTitle()));
		
		return dao.updateBoard(detail);
	}
	

	// 게시글 삭제 기능 구현
	@Override
	public int deleteBoard(int boardNo) {

		return dao.deleteBoard(boardNo);
	}
	
	
	// 문의사항 조회 리스트 구현
	@Override
	public Map<String, Object> selectaskList(int cp, Member loginMember) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		
		// 1-1. 게시글 수 조회
		int paginationCount = dao.paginationCount(loginMember);
		
		// 1-2. 페이지네이션 객체 생성
		Pagination pagination = new Pagination(cp, paginationCount);
		
		// 2. 게시글 목록 조회
		List<Board> aList = dao.selectAskList(pagination,loginMember);
		
		
		map.put("aList", aList);
		map.put("pagination", pagination);
		
		return map;		
		
	}

	
	
	//문의사항 글 조회 구현
	@Override
	public BoardDetail selectAskDetail(int boardNo) {
		
		BoardDetail detail = dao.selectAskDetail(boardNo);
		
		return detail;
	}

	// 문의사항 글 작성
	@Override
	public int insertAskWrite(BoardDetail detail) {
		// 제목만 XSS, 개행처리
		detail.setBoardTitle(Util.XSSHandling(detail.getBoardTitle()));
		detail.setBoardTitle(Util.newLineHandling(detail.getBoardTitle()));
		
		return dao.insertAskWrite(detail);
	}

	// 템플릿 조회 기능 구현
	@Override
	public List<Template> selectTemplate() {
		
		List<Template> tList = dao.selectTemplate();
		
		for(Template t : tList) {
			t.setTempEnc(Util.newLineClear(t.getTempContent()));
			t.setTempEnc(Util.XSSClear(t.getTempEnc()));
			int end = t.getTempEnc().length()-4;
			t.setTempEnc(t.getTempEnc().substring(3,end));
		}
		
		return tList;
	}

	// 템플릿 작성 기능 구현
	@Override
	public int insertTemplate(Template template) {
				
		return dao.insertTemplate(template);
	}

	// 템플릿 삭제 기능 구현
	@Override
	public int deleteTemplate(String tempNo) {
		
		return dao.deleteTemplate(tempNo);
	}

	
	// 문의글 삭제 기능 구현
	@Override
	public int deleteAskBoard(int boardNo) {
		return dao.deleteAskBoard(boardNo);
	}
	
	
	
	
	
	
	
	
	
	

}
