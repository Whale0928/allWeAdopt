package edu.kh.allWeAdopt.common.main.model.service;

import edu.kh.allWeAdopt.common.main.model.vo.AdminMain;
import edu.kh.allWeAdopt.common.main.model.vo.Anal;

import java.util.List;

public interface AdminMainService {

	//	관리자 메인 조회

	//오늘날짜 '문의 글 수 조회' / 오늘날자 어답터 게시글 수 / 오늘날짜 펀딩 결제 건수
	AdminMain adminMainCount();

	// 어답터 '완료' 그래프
	List<Anal> adopterGraph();

	// 펀딩 
	List<Anal> fundingGraph();

}
