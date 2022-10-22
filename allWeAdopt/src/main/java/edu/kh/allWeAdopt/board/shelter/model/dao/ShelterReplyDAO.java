package edu.kh.allWeAdopt.board.shelter.model.dao;

import edu.kh.allWeAdopt.board.model.vo.Reply;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShelterReplyDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	/** 댓글 목록 조회 DAO
	 * @param boardNo
	 * @return
	 */
	public List<Reply> selectReplyList(int boardNo) {
		return sqlSession.selectList("shelterReplyMapper.selectReplyList", boardNo);
	}

	/** 댓글 등록
	 * @param reply
	 * @return
	 */
	public int insertReply(Reply reply) {
		return sqlSession.insert("shelterReplyMapper.insertReply", reply);
	}

	/** 댓글 삭제 
	 * @param replyNo
	 * @return
	 */
	public int deleteReply(int replyNo) {
		return sqlSession.update("shelterReplyMapper.deleteReply", replyNo);
	}
 
	
	/** 댓글 수정
	 * @param reply
	 * @return
	 */
	public int updateReply(Reply reply) {
		return sqlSession.update("shelterReplyMapper.updateReply", reply);
	}

	
}
