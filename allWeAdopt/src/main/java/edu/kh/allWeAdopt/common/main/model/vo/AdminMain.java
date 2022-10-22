package edu.kh.allWeAdopt.common.main.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AdminMain {
	
	private int askCount;
	
	private int adopterCount;
	
	private int fundingCount;
	
	private List<Anal> adopterGraph;
	
	private List<Anal> fundingGraph;
	

}
