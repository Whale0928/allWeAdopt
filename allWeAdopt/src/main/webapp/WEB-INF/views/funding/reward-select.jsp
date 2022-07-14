<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>

<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>펀딩목록 : ALL WE ADOPT</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="${contextPath}/resources/assets/올위어답터.ico" />

        <!-- main css -->
        <link rel="stylesheet" href="${contextPath}/resources/css/main-style.css">

        <!-- 폰트어썸 (폰트)-->
        <script src="https://kit.fontawesome.com/e4f51ae88c.js" crossorigin="anonymous"></script>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Dongle&family=Gowun+Batang&family=Noto+Sans+KR:wght@100;300;400;500;700&display=swap" rel="stylesheet">

        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="${contextPath}/resources/css/main-style.css" rel="stylesheet" />
        <link href="${contextPath}/resources/css/styles.css" rel="stylesheet" />
        <link href="${contextPath}/resources/css/funding/funding-detail.css" rel="stylesheet" />

        <style>
            .nav-text-color{
                /* color: rgb(251, 131, 107); */
                color: black;
            }
           
            .secession-button{
                border: 1px solid rgba(255, 255, 255, 0.3);
                border-radius: 5px;
            }
            .nav-text-color:hover {
                color: rgb(251, 131, 107);
            }
            .bg-pink {
            —bs-bg-opacity: 1;
            background-color: rgb(251, 131, 107);
            }


            .st0{fill:#373737;}
            .st1{fill:#FB836B;}
            .st2{fill:none;stroke:#373737;stroke-width:7.3;stroke-miterlimit:10;}

        </style>
    </head>
    <body class="">

  <!-- Modal -->
					<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
					  <div class="modal-dialog">
					    <div class="modal-content">
					      <div class="modal-header">
					        <h5 class="modal-title" id="exampleModalLabel">펀딩 취소 / 반환 방법 미리 체크패보세요</h5>
					        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					      </div>
					      <div class="modal-body py-4">
					      
					      	<div class="d-flex py-3">
						      	<div class="px-3">
						      		<input type="checkbox">
						      	</div>
						      	<div class="px-3">
						        	<p>펀딩이 끝나기 전까지 언제든 결제를 취소할 수 있어요.</p>
						        	<p>펀딩이 끝나고 바로 리워드가 제작되어 다음 날 부터 순차 발송이 시작되기 때문에, 펀딩이 끝나면 결제를 취소 할 수 없습니다.</p>
						        </div>
					      	</div>
					      	
					      	<div class="d-flex py-3">
						      	<div class="px-3">
					        		<input type="checkbox">
					        	</div>
						      	<div class="px-3">
					        		<p>리워드에 문제가 있거나 배공일을 지키지 않으면 펀딩금을 돌려받을 수 있어요.</p>
					        		<p>펀딩금 반환 정책 확인</p>
						        </div>
					      	</div>
					        
					      	<div class="d-flex py-3">
						      	<div class="px-3">
					        		<input type="checkbox">
					        	</div>
						      	<div class="px-3">
					        		<p>단순 변심에 의한 펀딩금 반환은 신청할 수 없어요.</p>
					        		<p>펀딩은 일반 쇼핑과 달리 리워드를 만드는 메이커에서 투자하고, 투자의 보상으로 제품이나 서비스를 받는 구조이기 때문에 전자상거래법상 통신판매에 해당하지 않아요. 그래서 단순 변심을 이유로 한 펀딩금 반환을 신청할 수 없습니다.</p>
						        </div>
					      	</div>
					        
					        
					      </div>
					      <div class="modal-footer">
					        <button type="submit" class="btn btn-primary" id="goPay">계속해서 펀딩하기</button>
					      </div>
					    </div>
					  </div>
					</div>  <!-- 모달끝 --> 



					
					
        <main class="flex-shrink-0">

	        <jsp:include page="/WEB-INF/views/common/header.jsp" />
	  

					
			<!-- 펀딩 이미지, 타이틀, 달성률 들어있는 박스 -->
			<div class="fundingTitleBox" style="height:120px;">
			    <div class="fundingTitleImage" style="background: url(${contextPath}/resources/images/funding_sample/funding_title_img.png) 50% 0 ;"></div>
			    <div class="fundingTitleText" style="top:30px;">
			        우리아이 시원하게 여름 쿨매트
			    </div>
			</div>
			
            <section class="py-4">
                <div class="container px-5">
				
				
					
					
					<div class="text-center py-5">
						<h1 class="fw-bolder">리워드 선택</h1>
						<p class="lead fw-normal text-muted mb-0">펀딩해주시는 금액에 따라 감사의 의미로 리워드를 제공해 드립니다.</p>
					</div>

					<!-- select 값 테스트용 -->
					<input type="hidden" value="${map.selected}" id="selected">
					
					<!-- 폼태그 -->
					<form action="my/pay" method="POST" onsubmit="return submitConfirm()" id="goPayForm">
					
					<div class="row gx-5">
                    <c:forEach var="i" begin="0" end="${fn:length(map.rewardList)-1}">
						<!-- 리워드-->
						<div class="col-lg-6 col-xl-4 mb-5">
							<div class="card mb-5 mb-xl-0">
								<div class="card-body p-5">
								
									<div class="rew-number reward-select">
										리워드${i+1}
									</div>
									<div class="rew-price reward-select">
										${map.rewardList[i].rewardPrice}<span>원 펀딩</span>
									</div>
									<div class="rew-title">
										${map.rewardList[i].rewardTitle}
									</div>
									<div class="rew-content">
										${map.rewardList[i].rewardContent}
									</div>
									<div class="delivery-box">
										<span class="deli-title">리워드 발송 시작일</span>
										<span class="deli-content"> ${map.sendDate} &nbsp;순차발송</span>
									</div>
									<div class="stock-box">
										<span class="stock">현재 ${map.rewardList[i].maxRewardNo-map.rewardListCount[i].rewardOrderAmount}개 남음 / 제한수량 ${map.rewardList[i].maxRewardNo}개</span>
										<c:if test="${empty map.rewardListCount[i].rewardOrderAmount}">
											<span class="order-count">총 0개 펀딩 완료</span>
										</c:if>
										<c:if test="${!empty map.rewardListCount[i].rewardOrderAmount}">
											<span class="order-count">총 ${map.rewardListCount[i].rewardOrderAmount}개 펀딩 완료</span>
										</c:if>
									</div>
									
									<div class="d-grid"><p class="rewardSelectBtn lineBtn-gr rewardSelectBtn">리워드 선택</p></div>
								
									<input type="number" name="${map.rewardList[i].rewardNo}" value="0" style="width:60px;" class="rewardCount">
								
								</div>
							</div>
						</div>
                    </c:forEach>
                        

                        
                    </div>
                        
                    
                    
                    <div class="mx-5 px-5 py-5" >
                    
	                    <table style="margin: 20px auto;">
		                    <tr>
		                    	<th class="align-top" style="width:130px;">
		                    		공개여부(선택)
			                    </th>
			                    <td>
			                    	서포터 목록에 서포터 이름과 펀딩 금액이 공개됩니다. 조용히 펀딩하고 싶으시다면, 비공개로 선택해주세요.
									<p>커뮤니티, 새소식 댓글 작성 시에는 비공개 여부와 상관없이 펀딩 참여자 표시가 노출됩니다.</p>
		                    	</td>
		                    </tr>
	                    </table>
	                    
	                    
						<div style="margin: 20px auto;" class="d-flex justify-content-center">
							<div class="form-check px-5">
								<input class="form-check-input" type="checkbox" value="N" id="name" name="nameAnonymous">
								<label class="form-check-label" for="name">
									이름 비공개
								</label>
							</div>
							<div class="form-check px-5">
								<input class="form-check-input" type="checkbox" value="N" id="price" name="priceAnonymous">
								<label class="form-check-label" for="price">
									펀딩금액 비공개
								</label>
							</div>
						</div>
						
						
						<div class="text-center mt-5 fs-5">우리아이 시원하게 여름 쿨매트에 <span id="payPrice">0</span>원을 펀딩합니다.</div>





						

                    </div>  
                    <input type="hidden" name="fundingNo" value="${map.fundingNo}">
                    </form>
                    
				
				</div> <!-- container끝 -->
				<button data-bs-toggle="modal" data-bs-target="#exampleModal" class="selected d-grid col-6 mx-auto my-3 py-2" style="border-radius:4px; font-size:20px;" >결제 페이지로</button>

            </section>
					
        </main>
            

        <!-- 푸터 -->
        <jsp:include page="/WEB-INF/views/common/footer.jsp" />

        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="${contextPath}/resources/js/scripts.js"></script>
        <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
        <script>
        	const rewardSelectBtn = document.getElementsByClassName("rewardSelectBtn"); // 리워드 선택 버튼
        	const payPrice = document.getElementById("payPrice"); // 아래 리워드 더해진 금액
        	const rewardCount = document.getElementsByClassName("rewardCount");
        	var price=null;
        	
        	// 리워드별 가격
        	var priceList = new Array();
        	<c:forEach items="${map.rewardList}" var="reward">
        		priceList.push("${reward.rewardPrice}");
        	</c:forEach>
        	console.log(${reward.rewardPrice});
        	for(let i=0; i<rewardSelectBtn.length; i++){
        		
        		// 리워드별 가격?
        		price = ${map.rewardList[0].rewardPrice};
        		
        		/* // 마우스오버 했을때
         		rewardSelectBtn[i].addEventListener("mouseover",function(){	
        			
        			if(this.classList.contains("selected")){
        				this.classList.remove("btn-pk"); 
        			}else{
			        	this.classList.add("btn-pk");        		        				
        			}
        			
        		})
        		
        		// 마우스아웃 했을때
        		rewardSelectBtn[i].addEventListener("mouseout",function(){	
		        	this.classList.remove("btn-pk");        		
        		}) */
 
        		// 클릭 했을때
        		rewardSelectBtn[i].addEventListener("click",function(){	
		        	//this.classList.toggle("selected");  
		        	
        			if(this.classList.contains("selected")){
        				
        				//console.log(select);
        				
        				this.classList.remove("selected"); 
        				const beforeCount = this.parentElement.nextElementSibling.value;
        				this.parentElement.nextElementSibling.value="0";
        				payPrice.innerText=parseInt(payPrice.innerText)-parseInt(beforeCount)*parseInt(price);

        				
        			}else{
        				this.classList.add("selected"); 
        				this.parentElement.nextElementSibling.value="1";
        				payPrice.innerText=parseInt(payPrice.innerText)+parseInt(price);
        			}
        		})

        		
        		//const selected = document.getElementsById("selected");
	        	// selected 넘버확인
	        	//if(selected.value!=""){

		        	
		        	//if(selected==rewardNo){
		        		//rewardSelectBtn[i].classList.add("btn-pk");
		        	//}
	        	//}
        	}
        	
        	// 클릭했을때로 하니까 숫자입력했을때는 적용안됨..
        	// value값 바꼈을때로.. 가능? changeEvent?
        	for(let i=0; i<rewardCount.length; i++){
        		
        		// input값이 바뀌었을때??
        		rewardCount[i].addEventListener("change",function(){
        			
		        	// value값(반복문 덧셈으로 바꾸기)
					payPrice.innerText= parseInt(rewardCount[0].value)*parseInt(price);
					//+parseInt(rewardCount[1].value)+parseInt(rewardCount[2].value)
				
					
					if(rewardCount[i].value>0){
						rewardSelectBtn[i].classList.add("selected");  
					}else{
						rewardSelectBtn[i].classList.remove("selected");  
					}
        		})
        
        	
        	}
        	
        	
        	// 리워드 아무것도 선택 안되었으면 모달창 안열리고 alert.... 모달창 제어를 못하겠어 
        	
        	// 결제버튼 submit
        	const goPay = document.getElementById("goPay"); // 결제버튼
        	const goPayForm = document.getElementById("goPayForm"); // 결제폼태그
			
        	goPay.addEventListener("click",function(){ // 결제버튼 클릭 했을때

        		// 결제 전 유효성검사
	        	//submitConfirm(){
	        	
        			// 체크박스 체크 다 안되면 false
        		
	        		// 체크박스 체크 다 되면 true
	        		//console.log("이런거하나 넣을까");
	
	        	//}
        		
        		//폼태그.submit();
        		goPayForm.submit();
        		
        	})
        	
        </script>

    </body>
    
</html>