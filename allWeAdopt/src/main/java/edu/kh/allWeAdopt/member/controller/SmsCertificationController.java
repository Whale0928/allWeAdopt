package edu.kh.allWeAdopt.member.controller;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class SmsCertificationController {
	
	private final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@RequestMapping("/member/sms")
	@ResponseBody
	public String sendSMS(@RequestParam("memberTel") String memberTel) throws CoolsmsException{ // 휴대폰 문자보내기
		
		logger.info("인증번호 발송 수행됨");
		
		int randomNumber = (int)((Math.random()* (9999 - 1000 + 1)) + 1000);//난수 생성
		
		System.out.println("인증번호 : " + randomNumber);

										// API키                  // 시크릿키
		Message coolsms = new Message( "NCS531VDFXLHXNN7", "2DACFRWLAEC50HPBRQKTWDBBRWG5L6AZ" );

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", memberTel); // 문자 수신할 번호
        params.put("from", "01089510126"); // 문자 발송 번호 (coolSMS에서 인증한 번호만 가능해서 현재는 나의 번호 . . ㅠㅠ )
        params.put("type", "SMS"); // 문자 형식. LMS 등등 여러가지 있음
        params.put("text", "[ALL WE ADOPT] 인증번호 : " + randomNumber); // 문자 내용
        params.put("app_version", "test app 1.2"); // application name and version

	    JSONObject obj = coolsms.send(params);
	    System.out.println(obj.toString());
	    
	    // error_count : 성공시 0, 실패시 1이상의 숫자가 카운팅됨! 
	    if(String.valueOf(obj.get("error_count")).equals("0")) {
	    	return randomNumber+"";
	    }else {
	    	return "0";
	    }
		
	}
	
}
