package edu.kh.allWeAdopt.common.main.model.dao;

import edu.kh.allWeAdopt.common.main.model.vo.AdminMain;
import edu.kh.allWeAdopt.common.main.model.vo.Anal;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminMainDAO {
	
	@Autowired  
	private SqlSessionTemplate sqlSession;
	
	private final Logger logger = LoggerFactory.getLogger(AdminMainDAO.class);

	
	
	
	/** 오늘날짜 '문의 글 수 조회' / 오늘날자 어답터 게시글 수 / 오늘날짜 펀딩 결제 건수
	 * @return
	 */
	public AdminMain adminMainCount() {
		
		return sqlSession.selectOne("adminMapper.adminMainCount");
	}




	/** 월별 '완료' 인 어답터 게시글 수
	 * @return
	 */
	public List<Anal> adopter() {
		
		return sqlSession.selectList("adminMapper.adopterGraph");
	}




	/** 월별 펀딩 결제 목록 갯수 조회(오늘 날짜조회)
	 * @return
	 */
	public List<Anal> funding() {
		return sqlSession.selectList("adminMapper.fundingGraph");
	}


}
