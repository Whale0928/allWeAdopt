package edu.kh.allWeAdopt.funding.model.service;

import edu.kh.allWeAdopt.funding.model.vo.Funding;
import edu.kh.allWeAdopt.funding.model.vo.FundingDetail;
import edu.kh.allWeAdopt.funding.model.vo.OrderDetail;
import edu.kh.allWeAdopt.funding.model.vo.Reward;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author DamE
 *
 */
/**
 * @author DamE
 *
 */
public interface FundingService {

	
	/** 펀딩 상세 조회 : 수진  ( 김현기가 지금 같이 사용중)
	 * @param fundingNo
	 * @return detail
	 */
	FundingDetail selectFundingDetail(int fundingNo);

	/**자신이 참여한 펀딩 목록들을 조회후 페이지네이션과 같이 가져오는 함수 :현기
	 * @param memberNo
	 * @return map
	 */
	Map<String, Object> selectMyFundingList(int memberNo,int cp);

	/** 현재 진행중인 펀딩 번호 : 수진
	 * @return nowFundingNo
	 */
	int selectNowFundingNo();

	/**자신이 참여한 펀딩을 상세히 조회하기 위한 Service
	 * @param paymentNo
	 * @return
	 */
	OrderDetail selectOrderDetail(int paymentNo);

	/**[김현기]리워드 선택후 결제 페이지로 넘어가기 위한
	 * @param paramMap
	 * @return
	 */
	Map<String, Object> selectRewardList(Map<String, Object> paramMap,int memberNo);

	/** 리워드 리스트와 리워드별 구매수,금액 : 수진
	 * @param fundingNo
	 * @return
	 */
	Map<String, Object> selectReward(int fundingNo);

	/** 모든 펀딩 리스트 조회 : 수진
	 * @return map
	 */
	Map<String, Object> selectfundingAllList();

	/**결제 수행후 DB로 저장하는 서비스 : 현기
	 * @param orderDetail
	 * @param rewardList
	 * @return
	 */
	int payProgress(OrderDetail orderDetail, List<Reward> rewardList);

	/**주문 상태를 결제 취소 상태로 변경하는 AJAX ;현기
	 * @param paymentNo
	 * @return result
	 */
	int cancelPayment(int paymentNo);

	/**주문 상태를 환불 신청으로 변형하는 AJAX ; 현기
	 * @param paymentNo
	 * @return
	 */
	int refundPayment(int paymentNo);

	/**반품 사유 작성 :현기
	 * @param paymentNo
	 * @param returnReason
	 * @return
	 */
	int retrunPayment(int paymentNo, String returnReason);

	/**반품 사유 작성 :현기
	 * @param paymentNo
	 * @return
	 */
	String selectReturn(int paymentNo);

	/** 펀딩 등록  : 현기
	 * @param fundingDetail
	 * @param uploadImage
	 * @param webPath
	 * @param folderPath
	 * @return
	 */
	int fundingRegister(FundingDetail fundingDetail, MultipartFile uploadImage, String webPath, String folderPath)throws Exception;

	/** 진행예정중인 펀딩 썸네일 + 카테고리 + 타이틀
	 * @return
	 */
	List<FundingDetail> selectFundingSList();

	/**위와 다르게 해당 펀딩의 리워드 목록들만 가져오는 함수 오버로딩) :현기
	 * @param fundingNo
	 * @return
	 */
	List<Reward> selectRewardList(int fundingNo);


	/** 펀딩 썸네일+제목만 조회 : 수진
	 * @param fundingNo
	 * @return
	 */
	Funding selectfunding(int fundingNo);

	/**펀딩 업데이트 등록 : 현기
	 * @param fundingDetail
	 * @param uploadImage
	 * @param webPath
	 * @param folderPath
	 * @return
	 */
	int fundingUpdate(FundingDetail fundingDetail, MultipartFile uploadImage, String webPath, String folderPath)throws Exception;

	/**배송관리 페이지를 위한 List 조회 : 김현기
	 * @param map
	 * @return
	 */
	List<OrderDetail> selectOrderList(Map<String, Object> map);

	/**배송 관리에서 수행하는 서비스 : 김현기
	 * @param map
	 * @return
	 */
	int deliveryProcession(Map<String, Object> map);

	/**반품 처리 
	 * @param paramMap
	 * @return
	 */
	List<OrderDetail> returnProcessing(Map<String, Object> paramMap);

	/**택배 발송을 위한 서비스
	 * @param sendList
	 * @param map 
	 * @return
	 */
	List<OrderDetail> sendProcessing(List<OrderDetail> sendList, Map<String, Object> map);

	/**이전까지 진행된 펀딩번호 , 진행 월을 가져오는 서비스 
	 * @return
	 */
	List<Funding> selectSeasonList();




	
	
	



}