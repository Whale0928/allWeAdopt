package edu.kh.allWeAdopt.board.shelter.model.service;

import edu.kh.allWeAdopt.board.model.vo.Reply;
import edu.kh.allWeAdopt.board.shelter.model.dao.ShelterReplyDAO;
import edu.kh.allWeAdopt.common.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShelterReplyServiceImpl implements ShelterReplyService {

	@Autowired
	private ShelterReplyDAO dao;

	
	// 댓글 목록 조회 
	@Override
	public List<Reply> selectReplyList(int boardNo) {
		return dao.selectReplyList(boardNo);
	}

	// 댓글 등록
	@Override
	public int insertReply(Reply reply) {
		
		reply.setReplyContent(Util.XSSHandling(reply.getReplyContent()));
		reply.setReplyContent(Util.newLineHandling(reply.getReplyContent()));
		
		return dao.insertReply(reply);
	}
	
	
	// 댓글 삭제
	@Override
	public int deleteReply(int replyNo) {
		return dao.deleteReply(replyNo);
	}

	// 댓글 수정
	@Override
	public int updateReply(Reply reply) {
		return dao.updateReply(reply);
	}

	
	
}
