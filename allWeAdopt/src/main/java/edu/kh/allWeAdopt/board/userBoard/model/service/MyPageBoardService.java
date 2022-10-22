package edu.kh.allWeAdopt.board.userBoard.model.service;

import java.util.Map;

public interface MyPageBoardService {

	Map<String, Object> MyPageBoardService(int cp, int memberNo);

	Map<String, Object> likeListBoard(int cp, int memberNo);

}
