package edu.kh.allWeAdopt.board.shelter.model.service;

import edu.kh.allWeAdopt.board.model.vo.Reply;

import java.util.List;

public interface ShelterReplyService {

	// 댓글 목록 조회
	List<Reply> selectReplyList(int boardNo);

	// 댓글 등록
	int insertReply(Reply reply);

	/** 댓글 삭제
	 * @param replyNo
	 * @return
	 */
	int deleteReply(int replyNo);

	/** 댓글 삭제
	 * @param reply
	 * @return
	 */
	int updateReply(Reply reply);


}
