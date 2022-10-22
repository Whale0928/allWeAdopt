package edu.kh.allWeAdopt.board.userBoard.controller;

import edu.kh.allWeAdopt.board.userBoard.model.service.MyPageBoardService;
import edu.kh.allWeAdopt.member.model.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;



@Controller
@RequestMapping("/member/myPage")
@SessionAttributes({"loginMember"})
public class MyPageBoardController {
	
	@Autowired
	private MyPageBoardService service;
	
	// 게시글 리스트
	@GetMapping("/boardList")
	public String myPageBoard(Model model,
			@RequestParam(value="cp", required = false, defaultValue = "1") int cp,
			@ModelAttribute("loginMember") Member loginMember) {
		
		int memberNo = loginMember.getMemberNo();
		// 페이지네이션
		Map<String, Object> map = service.MyPageBoardService(cp, memberNo);
		
		model.addAttribute("map", map);
		return "board/myPageList";
	}
	
	// 좋아요 게시글 리스트
	@GetMapping("/likeList")
	public String likeList(Model model,
			@RequestParam(value="cp", required = false, defaultValue = "1") int cp,
			@ModelAttribute("loginMember") Member loginMember) {
		
		int memberNo = loginMember.getMemberNo();
		// 페이지네이션
		Map<String, Object> map = service.likeListBoard(cp, memberNo);
		
		model.addAttribute("map", map);
		return "board/myPageLike";
	}
	
}
