package edu.kh.allWeAdopt.common.main.controller;

import com.google.gson.Gson;
import edu.kh.allWeAdopt.common.main.model.service.AdminMainService;
import edu.kh.allWeAdopt.common.main.model.vo.AdminMain;
import edu.kh.allWeAdopt.common.main.model.vo.Anal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class MainController {
	
	@Autowired
	private AdminMainService service;
	
	
	
	// main으로 이동
	@RequestMapping("/main")
	public String mainForward(HttpServletRequest request, HttpServletResponse resp, HttpServletRequest req ) {

		// main페이지에 바로 출력하고 싶은 내용이 있으면
		// 여기서 서비스를 호출한 뒤 main으로 forward하면됨~~~
		
		// 쿠키 
		Cookie cookie = new Cookie("intro", "welcome");
		
		cookie.setMaxAge(60 * 60 * 24 ); // 쿠키하루.
		
		// 쿠키가 적용될 범위(경로) 지정
		cookie.setPath(req.getContextPath());
		
		// 쿠키를 응답 시 클라이언트에게 전달
		resp.addCookie(cookie);
		
		return "common/main";
	}
	
	
	
	// admin-main으로 이동
	@RequestMapping("/admin/main")
	public String adminMainForward(Model model) {
		
		
		// 문의글 갯수 조회(오늘 날짜 조회) 
		// 어답터게시글 갯수 조회(오늘 날짜 조회)
		// 오늘 펀딩 결제 목록 갯수 조회(오늘 날짜조회)
		// 어답터 게시글 추이(리스트)
		// 펀딩 주문 추이(리스트) 
		AdminMain result = service.adminMainCount();
		
		
		
		model.addAttribute("result",result);
		
		return "common/admin-main";
	}
	
	
	// adopter 그래프
	@ResponseBody
	@RequestMapping("/admin/mainAdopter")
	public String analGraph(Model model) {
		
		List<Anal> cList = service.adopterGraph();
		 
		return new Gson().toJson(cList);
		
	}
	
	// funding 그래프
	@ResponseBody
	@RequestMapping("/admin/mainFunding")
	public String analGraph2(Model model) {
		
		List<Anal> cList = service.fundingGraph();
		
		return new Gson().toJson(cList);
		
	}
	
	
	
	
	
	// intro으로 이동
	@RequestMapping("/intro")
	public String introForward () {

		// main페이지에 바로 출력하고 싶은 내용이 있으면
		// 여기서 서비스를 호출한 뒤 main으로 forward하면됨~~~
		
		return "common/intro";
	}
	
	// 이용약관, 개인정보취급방침 . . .등
	@RequestMapping("etc")
	public String etcMain(@RequestParam("mode") int mode,
						  Model model) {
		
		model.addAttribute("mode", mode);
		
		return "common/etcMain";
	}
	

}