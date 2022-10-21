<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>펀딩 상세 조회 : ALL WE ADOPT</title>
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
        
        <!-- sweetalert-->
		<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

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
    <body class="d-flex flex-column">
        <main class="flex-shrink-0">

	        <jsp:include page="/WEB-INF/views/common/header.jsp" />

            
            <!-- 펀딩 이미지, 타이틀, 달성률 들어있는 박스 -->
            <div class="fundingTitleBox">
                <div class="fundingTitleImage" style="background: url(${contextPath}${detail.fundingThumbnail}) 50% 0 ;"></div>
                <div class="fundingTitleText">
                    ${detail.fundingTitle}
                </div>
                <div class="bottom-box">
                    <div class="rate-bar"></div>
                    <!-- 달성률 100이하로 제한하기 -->
                    <c:if test="${detail.salesRate>=100}">
                    <div class="rate-bar-pink" style="width:100%;"></div>
                    <div class="rate-text-box" style="left:100%;">
                    </c:if>
                    <c:if test="${detail.salesRate<100}">
                    <div class="rate-bar-pink" style="width:${detail.salesRate}%;"></div>
                    <div class="rate-text-box" style="left:${detail.salesRate}%;">
                    </c:if>
                    	<svg id="Layer_1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 174 138.61">
	                    	<defs>
	                    		<style>.tooltip_icon{fill:rgb(255, 255, 255, 0.3);}</style>
	                    	</defs>
                    		<path class="tooltip_icon" d="M162.62,0H11.38C5.1,0,0,5.1,0,11.38V86.92c0,6.29,5.1,11.38,11.38,11.38H57.89l29.11,40.31,29.11-40.31h46.51c6.29,0,11.38-5.1,11.38-11.38V11.38c0-6.29-5.1-11.38-11.38-11.38Z"/>
                    	</svg>
                    	<div class="rate-text-wrap"><span class="rate-text"></span><span class="per">%</span></div>
                    </div>
                </div>
            </div>

            <section class="py-4">
                <div class="container px-5">
                    <div class="row gx-5">

                        <!-- 버튼들 -->
                        <!-- fundingNo값이 없으면 nowFundingNo -->
                        <div class="middle-btns">
                            <button id="story" onclick="window.location.href='${contextPath}/funding/detail/${detail.fundingNo}?page=1'">펀딩 스토리</button>
                            <button id="policy" onclick="window.location.href='${contextPath}/funding/detail/${detail.fundingNo}?page=2'">반환 / 정책</button>
                            <button id="supporters" onclick="window.location.href='${contextPath}/funding/detail/${detail.fundingNo}?page=3'">참여 서포터</button>
                            <button onclick="window.location.href='${contextPath}/funding/list'">종료된 펀딩</button>
                        </div>

                        <div class="line"></div>

						<!-- ?page=1 story -->
						<c:if test="${param.page==1}">
                        	<jsp:include page="/WEB-INF/views/funding/funding-detail1-story.jsp" />						
						</c:if>

                        <!-- ?page=2 policy -->
						<c:if test="${param.page==2}">
	                        <jsp:include page="/WEB-INF/views/funding/funding-detail2-policy.jsp" />
						</c:if>

                        <!-- ?page=3 supporters-->
						<c:if test="${param.page==3}">
	                        <jsp:include page="/WEB-INF/views/funding/funding-detail3-supporters.jsp" />
						</c:if>

                        <!-- 오른쪽 영역 -->
                        <div class="col-lg-3 sticky">
                            <!-- d-flex : flex 들어있는 class-->
                            <div class="align-items-center mb-4 fundingRight">

                                <div class="this-month"><span class="month">${detail.month}</span>이달의 펀딩<br>${detail.fundingTitle}</div>
        						<c:if test="${detail.fundingState=='Y'}">
                                <div class="period">
                                    <span class="pointText">${detail.leftDate}</span>일 남음
                                </div>
                                </c:if>
                                
                                <div class="donation">
                                    달성금액 <br>
                                    <span class="pointText">
                                    
                                    <c:if test="${detail.supportersNo==0}">
								    0
								    </c:if>
								    <c:if test="${detail.supportersNo!=0}">
								    ${detail.fullPrice}
								    </c:if>
                                     / ${detail.targetDonation}</span> 원
                                </div>
                                <div class="supporters">
                                    <span class="pointText">${detail.supportersNo}</span>명의 서포터
                                </div>
                            </div>

                            <c:if test="${detail.fundingState=='Y'}">
                            <div class="align-items-center mb-4" >
                                <button class="fundingBtn" onclick="window.location.href='${contextPath}/funding/reward/${detail.fundingNo}'">펀딩하기</button>
                                <c:if test="${empty loginMember}">
                                <button class="qnaBtn" onclick="window.location.href='${contextPath}/member/login'">문의</button>
                                </c:if>
                                <c:if test="${!empty loginMember}">
                                <button class="qnaBtn" onclick="window.location.href='${contextPath}/member/myPage/ask/list'">문의</button>
                                </c:if>
                            </div>
                            </c:if>

                            <c:forEach var="i" begin="0" end="${fn:length(detail.rewardList)-1}">
                            <div class="reward-box">
							
                                <div class="reward">
                                    <div class="rew-number">
                                        리워드${i+1}
                                    </div>
                                    <div class="rew-price">
                                        <fmt:formatNumber value="${detail.rewardList[i].rewardPrice}"/><span>원 펀딩</span>
                                    </div>
                                    <div class="rew-title">
                                        ${detail.rewardList[i].rewardTitle}
                                    </div>
                                    <div class="rew-content">
                                        ${detail.rewardList[i].rewardContent}
                                    </div>
                                    <div class="delivery-box">

                                        <span class="deli-title">리워드 발송 시작일</span>
                                        <span class="deli-content">${detail.sendDate} 부터 순차발송</span>
                                    </div>
                                    
                           			<!-- 재고수량 0보다 클때 -->
                           			<c:if test="${detail.rewardList[i].maxRewardNo-detail.rewardListCount[i].rewardOrderAmount>0}">
                                    <div class="stock-box">
                                        <span class="stock">현재 ${detail.rewardList[i].maxRewardNo-detail.rewardListCount[i].rewardOrderAmount}개 남음 / 제한수량 ${detail.rewardList[i].maxRewardNo}개</span>
                                        <c:if test="${empty detail.rewardListCount[i].rewardOrderAmount}">
	                                        <span class="order-count">총 0개 펀딩 완료</span>
                                        </c:if>
                                        <c:if test="${!empty detail.rewardListCount[i].rewardOrderAmount}">
	                                        <span class="order-count">총 ${detail.rewardListCount[i].rewardOrderAmount}개 펀딩 완료</span>
                                        </c:if>
                                    </div>
                           			</c:if>
                           			
                           			<!-- 재고수량 0보다 작을때 품절처리 -->
                           			<c:if test="${detail.rewardList[i].maxRewardNo-detail.rewardListCount[i].rewardOrderAmount<=0}">
                           			<div class="stock-box">
                                        <span class="stock">품절</span>
                                        <span class="order-count">총 ${detail.rewardListCount[i].rewardOrderAmount}개 펀딩 완료</span>
                                    </div>
                           			</c:if>
                           			
                                    <div class="rewardOver"><div class="vertical-center">이 리워드 펀딩하기</div></div>


                                </div>
                                
								<form action="../reward/${detail.fundingNo}" method="get" class="goReward">
									<input type="hidden" name="selected" value="${detail.rewardList[i].rewardNo}">
								</form>
								</c:forEach>

                            </div> <!-- 리워드 전체 박스  -->
                            
                            <div class="fixbox">
	                            <button class="fundingBtn wid-100" onclick="window.location.href='${contextPath}/funding/reward/${detail.fundingNo}'">이 프로젝트 펀딩하기</button>
                        	</div>
                            
                        </div> 
                        
                        

                    </div>
                </div>
                
                <div style="position:relative;">
					<a href="#">
					<i class="bi bi-arrow-up-circle bt_top" style="color: #FB836B; font-size: 40px; color: #FB836B; position: absolute; right: 10px; bottom:-30px; padding:10px;"></i>
					</a>
                </div>
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
        
        	// 달성률 
        	const salesRate = ${detail.salesRate};
        
        	// 키프레임 js에서 제어하기
        	const pinkWidth = document.getElementsByClassName("rate-bar-pink")[0];
        	const keyFrames = document.createElement("style");
        	
        	keyFrames.innerHTML = `
        		@keyframes long1 {
        		   from { 
        		       width: 0;
        		       background-color: rgb(251, 131, 107, 1);
        		   }
        		   to { 
        		        width: salesRate%;
        		        background-color: rgb(251, 131, 107, 0.9);
        		    }
        		}
        		`;
			pinkWidth.appendChild(keyFrames);
        	
			
			const textBoxleft = document.getElementsByClassName("rate-text-box")[0];
        	const keyFrames2 = document.createElement("style");
        	
        	keyFrames2.innerHTML = `
        		@keyframes long2 {
        		   from { 
        			   left: 2px;
   						color: #FB836B;
        		   }
        		   to { 
        			   left: salesRate%;
  				 		color: #FB836B;
        		    }
        		}
        		`;
			textBoxleft.appendChild(keyFrames2);
        	

            // 달성률 숫자 카운트
        	$(function() {
                var cnt0 = 0;
                counterFn();

                function counterFn() {

                    id0 = setInterval(count0Fn, 20);

                    function count0Fn() {
                        cnt0++;
                        if (cnt0 > salesRate) {
                            clearInterval(id0);
                        } else {
                            $(".rate-text").text(cnt0);
                        }
                    }
                }
		    });

            const rewards = document.getElementsByClassName("reward");
            const rewardOvers = document.getElementsByClassName("rewardOver");
            const goReward = document.getElementsByClassName("goReward");
			
            // 펀딩이 현재 진행중일때
            if(${detail.fundingState=='Y'}){
				            	
	            for(var i=0; i<rewards.length; i++){
		                	
	            		
	            		// 리워드 마우스 오버
						rewards[i].addEventListener("mouseover",function(){
		                    this.lastElementChild.classList.add('visable');
		                })
		
		                rewards[i].addEventListener("mouseout",function(){
		                    this.lastElementChild.classList.remove('visable');
		                })		                
		                
		                // 리워드 클릭 시
		                rewardOvers[i].addEventListener("click", function(){
		                	
		                	if(this.previousElementSibling.firstElementChild.innerText=='품절'){
		                		
		                		Swal.fire({
		                		     title: '품절된 리워드 입니다.',
		                		     width: 350,
		                		     padding: '3em',
		                		     color: 'black',
		                		     confirmButtonColor: 'rgb(251, 131, 107)',
		                		     confirmButtonText: '확인'
		                		     });
							}else{
			                	this.parentElement.nextElementSibling.submit();

							}
		                })
		                
	            	
	            } 
			} 
			 
			
            // 스크롤 reward 끝에 닿으면 버튼 보이게?
            // 스크롤Y 값이 header (헤더)
            //				+.fundingTitleBox(펀딩타이틀)
            //				+.middle-btns(중간버튼)
            //				+... 더할값이 많네 .... 담에 계산 해야지..
            		
            var fixBox = document.querySelector('.fixbox');
            
           	// window.scrollY 가 div 합보다 크거나 같을때 반환
           	if(${detail.fundingState=='Y'}){
           		
	            document.addEventListener('scroll', function() {
	            	
	    		    if(window.scrollY > 1800){
	    		    	fixBox.classList.add('visable');
	    		    }else{
	    		    	fixBox.classList.remove('visable');
	    		    }
	            });
           	}

           	
           	
					    
				

			    
		// param.page에 따라 nowSelect표시하기
		const page = ${param.page}
		const story = document.getElementById("story");
		const policy = document.getElementById("policy");
		const supporters = document.getElementById("supporters");
		
		if(page==1){
			story.classList.add('nowSelect')	
		}else{
			story.classList.remove('nowSelect')
		}
		
		if(page==2){
			policy.classList.add('nowSelect')	
		}else{
			policy.classList.remove('nowSelect')
		}
		
		if(page==3){
			supporters.classList.add('nowSelect')	
		}else{
			supporters.classList.remove('nowSelect')
		}
		
		
		
		
		// 재고수량 0일때 품절처리하기
		if(${detail.rewardList[i].maxRewardNo-detail.rewardListCount[i].rewardOrderAmount}<=0){
			//품절 처리
		}
        </script>
    </body>
    
</html>