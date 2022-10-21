package edu.kh.allWeAdopt.board.controller;

import edu.kh.allWeAdopt.board.model.service.BoardService;
import edu.kh.allWeAdopt.board.model.service.ReplyService;
import edu.kh.allWeAdopt.board.model.vo.BoardDetail;
import edu.kh.allWeAdopt.board.model.vo.Reply;
import edu.kh.allWeAdopt.member.model.vo.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes({"loginMember"})
@RequestMapping("admin/ask")
public class AdminAskController {
	
	@Autowired
	private ReplyService ReplyService;
	
	@Autowired
	private BoardService service;
	
	private final Logger logger = LoggerFactory.getLogger(AdminNoticeController.class);

	
	
	// 관리자 - 모~든 공지사항 리스트 조회화면 
	@GetMapping("/list")
	public String dminAskList( @RequestParam(value="cp", required=false, defaultValue="1") int cp,
								Model model,
								 @ModelAttribute("loginMember") Member loginMember) {
		
		Map<String, Object> map = service.selectaskList(cp, loginMember);
		
		// request범위에 aList 세팅
		model.addAttribute("map", map);
		
		return "ask/askList";
	}
	
	
	// 문의사항 상세 조회
	@GetMapping("/detail/{boardNo}")
	public String askDetail( @PathVariable("boardNo") int boardNo,
						 	 @RequestParam(value="cp", required=false, defaultValue="1") int cp,
						 	 Model model ) {
		
		BoardDetail detail = service.selectAskDetail(boardNo); 
		
		List<Reply> rList = ReplyService.selectReplyList(boardNo);
		
		
		model.addAttribute("detail",detail);
		model.addAttribute("rList",rList);
		
		
		
		return "ask/askDetail";
	}
	
	
	// 문의사항 삭제
	@GetMapping("/delete/{boardNo}")
	public String deleteBoard( @PathVariable("boardNo") int boardNo,
			RedirectAttributes ra, @RequestHeader("referer") String referer) {
		
		int result = service.deleteAskBoard(boardNo);
		
		
		String path = null;
		String message = null;
		
		if(result > 0) {
			message = "삭제되었습니다.";
			path = "../../ask/list/";
		} else {
			message = "삭제 실패";
			path = referer;
		}
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:" + path;
	}
	
	
	
	
	
	
	
	
	

}
