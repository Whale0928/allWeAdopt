package edu.kh.allWeAdopt.common.main.model.service;

import edu.kh.allWeAdopt.common.main.model.dao.AdminMainDAO;
import edu.kh.allWeAdopt.common.main.model.vo.AdminMain;
import edu.kh.allWeAdopt.common.main.model.vo.Anal;
import edu.kh.allWeAdopt.member.model.service.MemberServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminMainServiceImpl implements AdminMainService{
	
	@Autowired
	private AdminMainDAO dao;
	
	private final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);


	
	// 메인 상단 조회
	@Override
	public AdminMain adminMainCount() {
		
		AdminMain result = dao.adminMainCount();
		
		
		return result;
	}



	// 어답터 그래프 
	@Override
	public List<Anal> adopterGraph() {
		return dao.adopter();
	}


	// 펀딩 그래프
	@Override
	public List<Anal> fundingGraph() {
		return dao.funding();
	}
	
	
	
	
	
}
