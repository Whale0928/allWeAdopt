package edu.kh.allWeAdopt.board.userBoard.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Animal {
	private int boardNo;
	private String animalType;
	private String animalDetail;
	private char gender;
	private char neutering;
}
