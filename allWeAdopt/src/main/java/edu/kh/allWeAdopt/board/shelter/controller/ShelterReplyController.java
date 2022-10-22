package edu.kh.allWeAdopt.board.shelter.controller;

import com.google.gson.Gson;
import edu.kh.allWeAdopt.board.model.vo.Reply;
import edu.kh.allWeAdopt.board.shelter.model.service.ShelterReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pamphlet/reply")
public class ShelterReplyController {

	@Autowired
	private ShelterReplyService service;
	
	
	// 댓글 목록 조회
	@GetMapping("/selectReplyList")
	public String selectReplyList(int boardNo) {
		
		List<Reply> rList = service.selectReplyList(boardNo);
		
		return new Gson().toJson(rList);
		
	}
	
	// 댓글 등록
	@PostMapping("/insert")
	public int insertReply(Reply reply) {
		return service.insertReply(reply);
	}
	
	// 댓글 삭제
	@GetMapping("/delete")
	public int deleteReply(int replyNo) {
		return service.deleteReply(replyNo);
	}
	
	// 댓글 수정
	@PostMapping("/update")
	public int updateReply(Reply reply) {
		return service.updateReply(reply);
	}
	
	
}
