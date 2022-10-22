package edu.kh.allWeAdopt.board.userBoard.model.dao;

import edu.kh.allWeAdopt.board.model.vo.Board;
import edu.kh.allWeAdopt.board.model.vo.Pagination;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MyPageBoardDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;

	/** 게시글 리스트 수 조회 DAO
	 * 
	 * @param memberNo
	 * @return listCount
	 */
	public int getListCount(int memberNo) {
		return sqlSession.selectOne("boardMapper.getListCount",memberNo);
	}

	/** 게시글 목록 조회 DAO
	 * 
	 * @param pagination
	 * @param memberNo
	 * @return boardList
	 */
	public List<Board> selectBoardList(Pagination pagination, int memberNo) {

		// RowBounds 객체 생성(Mybatis 기능)
		int offset = (pagination.getCurrentPage()-1) * pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset,pagination.getLimit());
		
		return sqlSession.selectList("boardMapper.selectBoardList", memberNo, rowBounds);
	}

	/** 관심목록 게시글 수 조회 DAO
	 *  @param memberNo
	 *  @return listCount
	 */
	public int getLikeCount(int memberNo) {
		return sqlSession.selectOne("boardMapper.getLikeCount",memberNo);
	}

	/** 관심목록 게시글 조회 DAO
	 * 
	 * @param pagination
	 * @param memberNo
	 * @return likeBoardList
	 */
	public List<Board> likeBoardList(Pagination pagination, int memberNo) {
		// RowBounds 객체 생성(Mybatis 기능)
		int offset = (pagination.getCurrentPage()-1) * pagination.getLimitCount();
		RowBounds rowBounds = new RowBounds(offset,pagination.getLimitCount());
		
		return sqlSession.selectList("boardMapper.likeBoardList", memberNo, rowBounds);
	}
}
