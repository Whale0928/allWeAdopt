package edu.kh.allWeAdopt.member.point.controller;

import com.google.gson.Gson;
import edu.kh.allWeAdopt.member.model.vo.Member;
import edu.kh.allWeAdopt.member.point.model.dao.PointDAO;
import edu.kh.allWeAdopt.member.point.model.service.PointService;
import edu.kh.allWeAdopt.member.point.vo.Rank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/point")
@SessionAttributes({"loginMember"})
public class Point {
	
	private final Logger logger = LoggerFactory.getLogger(Point.class);
	
	@Autowired 
	private PointService service;
	
	@Autowired
	private PointDAO dao;
	
	
	//워들 페이지로 이동
	@GetMapping("/wordle")
	public String wrdle() {
		//필요할경우 여기서 데이터 꺼내감
		return "point/wordle";
	}
	
	//워들 성공시 포인트를 추가해주는 AJAX
	@ResponseBody
	@GetMapping("/wordle/success")
	public int wordleSuccess(@ModelAttribute("loginMember") Member loginMember) {
		int memberNo=loginMember.getMemberNo();
		int result=service.wordleSuccess(memberNo);
		loginMember.setMemberPoint(service.selectMyPoint(loginMember.getMemberNo()));
		return result;
	}
	
	
	//룰렛 페이지로 이동
	@GetMapping("/roulette")
	public String roulette(){ 
		return "point/roulette";
	}
	
	//룰렛 수행후 결과값의 DB에 반영
	@ResponseBody
	@GetMapping("/roulette/run")
	public int rouletteRun(int total,@ModelAttribute("loginMember") Member loginMember){
		loginMember.setMemberPoint(total);
		int resultNum = service.rouletteRun(total,loginMember.getMemberNo());
		int result=0;
		if(resultNum>0) {
			result=total;
		}else {
			result=-1;
		}
		return result;
	}
	
	//2048 페이지로 이동
	@GetMapping("/game2048")
	public String game2048(Model model) {

		Rank r = service.selectRank();
		
		model.addAttribute("r", r);
		
		return "point/2048";
	}	
	
	//2048 포인트를 추가해주는 AJAX + 랭크에 데이터 삽입
	@ResponseBody
	@GetMapping("/game2048/success")
	public int game2048Success(@ModelAttribute("loginMember") Member loginMember,
							   int score) {
	
		Rank r = new Rank();
		r.setMemberNo(loginMember.getMemberNo());
		r.setScore(score);
		int result=service.game2048Success(r);
		
		return result;
	}
	
	// 실시간으로 최고점수 불러오기...
	@ResponseBody
	@GetMapping("/game2048/selectRank")
	public String game2048SelectScore() {
		
		return new Gson().toJson(service.selectRank());
	}
	

}
