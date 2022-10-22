package edu.kh.allWeAdopt.board.userBoard.model.service;

import edu.kh.allWeAdopt.board.model.vo.Board;
import edu.kh.allWeAdopt.board.userBoard.model.vo.Animal;
import edu.kh.allWeAdopt.board.userBoard.model.vo.Area;

import java.util.List;

public interface UserBoardService {

	int userBoardRegist(Board board);

	int getBoardNo();

	int userBoardArea(Area area);

	int userBoardAnimal(Animal animal);

	List<Area> areaList();

	List<Area> loadAreaList(String area);

	List<Board> boardList();

	Board boardDetail(int boardNo);

	List<Animal> loadAnimalList(String animalType);

	int boardDelete(int boardNo);

	int userBoardModify(Board board);

	int boardAreaModify(Area area);

	int boardAnimalModify(Animal animal);

	int likeRegist(Board board, int likeNo);

	int likeList(Board likeBoard);

	List<Board> searchList(Board board);

}
