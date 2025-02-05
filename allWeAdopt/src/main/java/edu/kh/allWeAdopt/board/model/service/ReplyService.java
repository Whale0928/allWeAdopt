package edu.kh.allWeAdopt.board.model.service;

import edu.kh.allWeAdopt.board.model.vo.Reply;

import java.util.List;


public interface ReplyService {

	/** 댓글 목록 조회 서비스
	 * @param boardNo
	 * @return rList
	 */
	List<Reply> selectReplyList(int boardNo);

	 
	/** 댓글 등록 서비스
	 * @param reply
	 * @return result
	 */
	int insertReply(Reply reply);


	/** 댓글 삭제 서비스
	 * @param replyNo
	 * @return result
	 */
	int deleteReply(int replyNo);


	/** 댓글 수정 서비스
	 * @param reply
	 * @return result
	 */
	int updateReply(Reply reply);

	
	
	
	
	
	
	
	
}