package edu.kh.allWeAdopt.common.findHospital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/findHospital")
public class FindHospitalController {

	@GetMapping
	public String findHospital(Model model) {
		
		//인증키
		//58717a6e5463686f3130306d58694a4f
		
		StringBuffer sb = new StringBuffer("http://openapi.seoul.go.kr:8088/58717a6e5463686f3130306d58694a4f/xml/LOCALDATA_020301/1/5/");
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		return "findHospital/findHospital";
	}
}
