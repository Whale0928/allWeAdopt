package edu.kh.allWeAdopt.member.model.service;

import edu.kh.allWeAdopt.member.model.vo.Member;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface MemberService {

	
	
	/** 로그인 서비스
	 * @param inputMember
	 * @return loginMember
	 */
    Member login(Member inputMember);

	

	 
	/** 이메일 중복검사 Service
	 * @return result
	 */
	int emailDupCheck( String memberEmail );



  
	/** 회원가입 Service
	 * @param map
	 * @return result
	 * @throws IOException
	 */
    int signUp(Map<String, Object> map, MultipartFile uploadImage) throws IOException;




	/** 카카오 아이디(이메일) 일치하는 회원 정보를 조회하는 Service 호출 후 결과 반환 받기 
	 * @param kakaoEmail
	 * @return
	 */
    Member kakaoEmailCheck(String kakaoEmail);




	/** 카카오 회원 넘버 '삽입' 
	 * @param kakaoEmail
	 * @param nickname
	 * @return result
	 */
    int insertNo(Member mem);




	/** 이미 로그인 해봤던 카카오계정로그인....
	 * @param mem
	 * @return
	 */
    Member kakaoEmailLogin(Member mem);



	
	/**채팅 대상 정보 얻어오는 Service 생성자:김현기
	 * @param memberNo
	 * @return
	 */
    Member selectThisMember(int memberNo);




	/** 인증번호를 받은 이메일, 인증번호, 인증번호 발급 시간  -> DB 삽입
	 * @param inputEmail 
	 * @param inputEmail
	 * @param cNumber
	 * @return
	 */
	int insertCertification(Map<String, Object> map);

	
	
	/** 이메일 인증번호 체크
	 * @param map
	 * @return result
	 */
	int CheckNumber(Map<String, Object> map);




	/** 비밀번호 재설
	 * @param map
	 * @return
	 */
    int changePw(Map<String, Object> map);




	/** 관리자 계정 등록 SERVICE
	 * @param member
	 * @return result
	 */
    int registerAdmin(Member member);







	
	
	
	
	
	
	





}
