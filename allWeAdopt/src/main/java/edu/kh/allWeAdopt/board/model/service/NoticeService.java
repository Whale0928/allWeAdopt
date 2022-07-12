package edu.kh.allWeAdopt.board.model.service;

import java.util.Map;

import edu.kh.allWeAdopt.board.model.vo.BoardDetail;


public interface NoticeService {
	
	/** 공지사항 목록 조회 Service
	 * @param cp
	 * @return nList
	 */
	Map<String, Object> selectNoticeList(int cp);

	/** 공지사항 상세 조회 Service
	 * @param boardNo
	 * @return detail
	 */
	BoardDetail selectNoticeDetail(int boardNo);

	/** 게시글 작성 (썸머노트 이용)
	 * @param detail
	 * @return result
	 */
	int insertBoard(BoardDetail detail);

}
