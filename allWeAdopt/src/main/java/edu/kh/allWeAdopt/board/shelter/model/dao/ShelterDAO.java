package edu.kh.allWeAdopt.board.shelter.model.dao;

import edu.kh.allWeAdopt.board.model.vo.Board;
import edu.kh.allWeAdopt.board.model.vo.BoardDetail;
import edu.kh.allWeAdopt.board.model.vo.Pagination;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShelterDAO {

	@Autowired  
	private SqlSessionTemplate sqlSession;
	
	private final Logger logger = LoggerFactory.getLogger(ShelterDAO.class);

	/** 전단지 수 조회
	 * @return int
	 */
	public int getListCount() {
		return sqlSession.selectOne("pamphletMapper.getListCount");
	}

	
	/** 전단지 목록 조회
	 * @param pagination
	 * @return
	 */
	public List<Board> selectPamphletList(Pagination pagination) {
		
		int offset = ( pagination.getCurrentPage()-1) * (pagination.getLimit() -4);
		
		RowBounds rowBounds = new RowBounds(offset, (pagination.getLimit()-4));
		
		return sqlSession.selectList("pamphletMapper.selectPamphletList", null, rowBounds);
	}


	/** 전단지 상세 조회
	 * @param boardNo
	 * @return result
	 */
	public BoardDetail pamphletDetail(int boardNo) {
		return sqlSession.selectOne("pamphletMapper.pamphletDetail", boardNo);
	}


	/** 게시글 작성 
	 * @param detail
	 * @return
	 */
	public int insertPamphlet(BoardDetail detail) {
		
		int result = sqlSession.insert("pamphletMapper.insertPamphlet", detail);
		
		if(result>0) result = detail.getBoardNo();
		
		return result;
	}


	/** 전단지 수정
	 * @param detail
	 * @return
	 */
	public int updatePamphlet(BoardDetail detail) {
		return sqlSession.update("pamphletMapper.updatePamphlet", detail);
	}


	/** 전단지 삭제
	 * @param boardNo
	 * @return
	 */
	public int deletePamphlet(int boardNo) {
		return sqlSession.update("pamphletMapper.deletePamphlet", boardNo);
	}
	
}
