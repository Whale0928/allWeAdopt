<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="shelter" items="${list}">
    <c:if test="${desertionNo == shelter.desertionNo}">
        <c:set var="careNm" value="${shelter.careNm}"/>
        <c:set var="careTel" value="${shelter.careTel}"/>
        <c:set var="noticeSdt" value="${shelter.noticeSdt}"/>
        <c:set var="noticeEdt" value="${shelter.noticeEdt}"/>
        <c:set var="popfile" value="${shelter.popfile}"/>
        <c:set var="happenDt" value="${shelter.happenDt}"/>
        <c:set var="happenPlace" value="${shelter.happenPlace}"/>
        <c:set var="colorCd" value="${shelter.colorCd}"/>
        <c:set var="age" value="${shelter.age}"/>
        <c:set var="weight" value="${shelter.weight}"/>
        <c:set var="processState" value="${shelter.processState}"/>
        <c:set var="specialMark" value="${shelter.specialMark}"/>
        <c:set var="careAddr" value="${shelter.careAddr}"/>
        <c:set var="kindCd" value="${shelter.kindCd}"/>
        <c:set var="sexCd" value="${shelter.sexCd}"/>
    </c:if>    
</c:forEach>

<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>ALL WE ADOPT</title>
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


        <%-- 지도 --%>
        <%-- <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=69933a089a5ecd291058167064475d66"></script>
        <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=69933a089a5ecd291058167064475d66&libraries=services"></script> --%>


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
            <!-- Navigation-->

            <!-- Page Content-->
            <section class="py-5">
                <div class="container px-5">
                    <div class="card border-0 shadow rounded-3 overflow-hidden">
                        <div class="card-body p-0">
                            <div class="row gx-0">
                                <!-- 분양소 메인 로고 (클릭 시 최상위 주소) -->
                                <a href="#"><div class="col-lg-6 col-xl-7"><div class="bg-featured-blog" style="background-image: url('https://dummyimage.com/700x350/343a40/6c757d')"><img src="${contextPath}/resources/images/adopt-mainLogo.png" alt="main"></div></div></a>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <section class="py-5">
                <div class="container px-5 my-5">
                    <div class="row gx-5">
                        <div class="col-lg-3">
                            <div class="d-flex align-items-center mt-lg-5 mb-4">
                                <img class="img-fluid rounded-circle" src="https://dummyimage.com/50x50/ced4da/6c757d.jpg" alt="..." />
                                <div class="ms-3">
                                    <div class="fw-bold">${careNm}</div>
                                    <div class="text-muted">${careTel}</div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-9">
                            <!-- Post content-->
                            <article>
                                <!-- Post header-->
                                <header class="mb-4">
                                    <!-- Post title-->
                                    <h1 class="fw-bolder mb-1">${careNm}</h1>
                                    <!-- Post meta content-->
                                    <div class="text-muted fst-italic mb-2">공고 시작일: ${noticeSdt}</div>
                                    <div class="text-muted fst-italic mb-2">공고 종료일: ${noticeEdt}</div>
                                    <!-- Post categories-->
                                    <a class="badge bg-secondary text-decoration-none link-light" href="#!">관심동물</a>
                                </header>
                                <!-- 유기동물 사진 -->
                                <figure class="mb-4"><img class="img-fluid rounded" src="${popfile}" alt="..." /></figure>
                                <!-- Post content-->
                                <!-- 나중에 폰트 적용 해보기! -->
                                <section class="mb-5">
                                    <%-- <ul class="list-group list-group-flush">
                                        <li class="fs-5 mb-4 list-group-item" style="list-style-type:none">성별 : ${sexCd}</li>
                                        <li class="fs-5 mb-4 list-group-item" style="list-style-type:none">색깔 : ${colorCd}</li>
                                        <li class="fs-5 mb-4 list-group-item" style="list-style-type:none">나이 : ${age}</li>
                                        <li class="fs-5 mb-4 list-group-item" style="list-style-type:none">무게 : ${weight}</li>
                                        <li class="fs-5 mb-4 list-group-item" style="list-style-type:none">상태 : ${processState}</li>
                                        <li class="fs-5 mb-4 list-group-item" style="list-style-type:none">특징 : ${specialMark}</li>
                                        <li class="fs-5 mb-4 list-group-item" style="list-style-type:none">보호소 주소 : ${careAddr}</li>                                   
                                    </ul> --%>

                                    <ol class="list-group list-group-numbered">
                                        <li class="list-group-item d-flex justify-content-between align-items-start">
                                          <div class="ms-2 me-auto">
                                            <div class="fw-bold">접수일</div>
                                            ${happenDt}
                                          </div>                                       
                                        </li>
                                        <li class="list-group-item d-flex justify-content-between align-items-start">
                                          <div class="ms-2 me-auto">
                                            <div class="fw-bold">발견 장소</div>
                                            ${happenPlace}
                                          </div>                                        
                                        </li>
                                        <li class="list-group-item d-flex justify-content-between align-items-start">
                                          <div class="ms-2 me-auto">
                                            <div class="fw-bold">종류</div>
                                            ${kindCd}
                                          </div>                                         
                                        </li>
                                        <li class="list-group-item d-flex justify-content-between align-items-start">
                                          <div class="ms-2 me-auto">
                                            <div class="fw-bold">성별</div>
                                            ${sexCd}
                                          </div>                                         
                                        </li>
                                        <li class="list-group-item d-flex justify-content-between align-items-start">
                                          <div class="ms-2 me-auto">
                                            <div class="fw-bold">색깔</div>
                                            ${colorCd}
                                          </div>                                         
                                        </li>
                                        <li class="list-group-item d-flex justify-content-between align-items-start">
                                          <div class="ms-2 me-auto">
                                            <div class="fw-bold">나이</div>
                                            ${age}
                                          </div>                                         
                                        </li>
                                        <li class="list-group-item d-flex justify-content-between align-items-start">
                                          <div class="ms-2 me-auto">
                                            <div class="fw-bold">무게</div>
                                            ${weight}
                                          </div>                                         
                                        </li>
                                        <li class="list-group-item d-flex justify-content-between align-items-start">
                                          <div class="ms-2 me-auto">
                                            <div class="fw-bold">상태</div>
                                            ${processState}
                                          </div>                                         
                                        </li>
                                        <li class="list-group-item d-flex justify-content-between align-items-start">
                                          <div class="ms-2 me-auto">
                                            <div class="fw-bold">특징</div>
                                            ${specialMark}
                                          </div>                                         
                                        </li>
                                        <li class="list-group-item d-flex justify-content-between align-items-start">
                                          <div class="ms-2 me-auto">
                                            <div class="fw-bold">보호소 주소</div>
                                            ${careAddr}
                                          </div>                                         
                                        </li>
                                    </ol>


                                    <%-- 보호소 지도 첨부 --%>
                                    <div id="map" style="width:600px;height:400px;"></div>

                                    <script>
                                        const careAddr = "${careAddr}";
                                    </script>


                                   <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=69933a089a5ecd291058167064475d66&libraries=services"></script>
                                   <script>
                                   var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
                                        mapOption = {
                                            center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
                                            level: 3 // 지도의 확대 레벨
                                        };  

                                    // 지도를 생성합니다    
                                    var map = new kakao.maps.Map(mapContainer, mapOption); 

                                    // 주소-좌표 변환 객체를 생성합니다
                                    var geocoder = new kakao.maps.services.Geocoder();

                                    // 주소로 좌표를 검색합니다
                                    geocoder.addressSearch(careAddr, function(result, status) {

                                        // 정상적으로 검색이 완료됐으면 
                                        if (status === kakao.maps.services.Status.OK) {

                                            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

                                            // 결과값으로 받은 위치를 마커로 표시합니다
                                            var marker = new kakao.maps.Marker({
                                                map: map,
                                                position: coords
                                            });

                                            // 인포윈도우로 장소에 대한 설명을 표시합니다
                                            var infowindow = new kakao.maps.InfoWindow({
                                                content: '<div style="width:150px;text-align:center;padding:6px 0;">'+careAddr+'</div>'
                                            });
                                            infowindow.open(map, marker);

                                            // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
                                            map.setCenter(coords);
                                        } 
                                    });    
                                    </script>
                                    

                                                                                                
                                </section>
                            </article>
                            <!-- 댓글 쓸지 말지 고민.. -->
                            <%-- <section>
                                <div class="card bg-light">
                                    <div class="card-body">
                                        <!-- Comment form-->
                                        <form class="mb-4"><textarea class="form-control" rows="3" placeholder="댓글 입력" style="resize:none"></textarea></form>
                                        <!-- Comment with nested comments-->
                                        <div class="d-flex mb-4">
                                            <!-- Parent comment-->
                                            <div class="flex-shrink-0"><img class="rounded-circle" src="https://dummyimage.com/50x50/ced4da/6c757d.jpg" alt="..." /></div>
                                            <div class="ms-3">
                                                <div class="fw-bold">댓쓴이1</div>
                                                고양이
                                                <!-- Child comment 1-->
                                                <div class="d-flex mt-4">
                                                    <div class="flex-shrink-0"><img class="rounded-circle" src="https://dummyimage.com/50x50/ced4da/6c757d.jpg" alt="..." /></div>
                                                    <div class="ms-3">
                                                        <div class="fw-bold">대댓쓴이1</div>
                                                        고양이고양이
                                                    </div>
                                                </div>
                                                <!-- Child comment 2-->
                                                <div class="d-flex mt-4">
                                                    <div class="flex-shrink-0"><img class="rounded-circle" src="https://dummyimage.com/50x50/ced4da/6c757d.jpg" alt="..." /></div>
                                                    <div class="ms-3">
                                                        <div class="fw-bold">대댓쓴이2</div>
                                                        고양이고양이고양이
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- Single comment-->
                                        <div class="d-flex">
                                            <div class="flex-shrink-0"><img class="rounded-circle" src="https://dummyimage.com/50x50/ced4da/6c757d.jpg" alt="..." /></div>
                                            <div class="ms-3">
                                                <div class="fw-bold">댓쓴이2</div>
                                                강아지
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </section> --%>
                        </div>
                    </div>
                </div>
            </section>
        </main>
        <!-- Footer-->
        <jsp:include page="/WEB-INF/views/common/footer.jsp" />

        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <%-- <script src="js/scripts.js"></script> --%>



        <%-- <script src="${contextPath}/resources/js/shelter.js"></script> --%>



        
    </body>
    
</html>