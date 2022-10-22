package edu.kh.allWeAdopt.member.point.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//불필요한 VO 하지만 가시성을 위해 구현 미사용 예정(?)
@Getter
@Setter
@NoArgsConstructor
public class Rank {
	
	private int rankNo;
	private int memberNo;
	private int score;
	private String memberName;

}
