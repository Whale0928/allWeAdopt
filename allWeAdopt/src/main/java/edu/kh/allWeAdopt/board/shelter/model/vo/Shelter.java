package edu.kh.allWeAdopt.board.shelter.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Shelter {
	
	String desertionNo; // 유기번호
	String filename; // 썸네일
	String happenDt; // 접수일
	String happenPlace; // 발견장소
	String kindCd; // 품종
	String colorCd; 
	String age;
	String weight;
	String popfile; // 이미지
	String noticeSdt; // 공고시작일
	String noticeEdt; // 공고 종료일
	String processState; // 상태
	String sexCd;
	String specialMark; // 특징
	String careNm; // 동물호보센터명
	String careAddr; // 소재지 주소(도로명)
	String careTel; // 전화번호
	String numOfRows; // 한 페이지 결과 수
	String pageNo; // 페이지 번호
	String totalCount; // 전체 결과 수
	
}
