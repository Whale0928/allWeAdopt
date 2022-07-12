<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<!-- map에 저장된 값을 각각 변수에 저장 -->

<c:set var="pagination" value="${map.pagination}" />
<c:set var="nList" value="${map.nList}" />

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>관리자 - 공지사항 리스트</title>
        
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="${contextPath}/resources/assets/올위어답터.ico" />
        
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="${contextPath}/resources/css/styles.css" rel="stylesheet" />

        <!-- main css -->
        <link rel="stylesheet" href="${contextPath}/resources/css/main-style.css">
        
    </head>
    <body class="d-flex flex-column">
        <main class="flex-shrink-0">

            <!-- 헤더 -->
            <jsp:include page="/WEB-INF/views/common/admin-header.jsp" />

            <!-- Header-->
            <header class="py-3">
                <div class="container px-3">
                        <div class="col-lg-8 col-xxl-6">
                            <div class="text-left my-5">
                                <h2 class="fw-bolder mb-3">공지사항</h2>
                                <p class="lead fw-normal text-muted mb-2" style="font-size: 14px;">All We Adopt의 공지사항을 확인해주세요!</p>
                            </div>
                        </div>
                </div>
            </header>
           
            <!-- About section two-->
            <section class="py-0">
                <div class="container px-5 my-1">
                    <div class="row gx-5 align-items-center">
                        <c:if test="${empty nList}">

                            <div>게시글이 없습니당. </div>

                        </c:if>

                        <c:if test="${!empty nList}">

                            <table class="table boardList">
                                <thead >
                                <tr>
                                    <th scope="col" style="width: 9%" class="col-sm-1 text-center" >번호</th>
                                    <th scope="col" style="width: 81%" class="col-sm-6">제목</th>
                                    <th scope="col"style="width: 19%" >작성일</th>
                                </tr>
                                </thead>
                                <tbody>     

                                    <c:forEach var="n" items="${nList}">
                                        <tr>
                                            <th scope="row" class="col-sm-1 text-center" >${n.boardNo}</th>
                                            <td><a href="${contextPath}/admin/notice/detail/${n.boardNo}?cp=${pagination.currentPage}">${n.boardTitle}</a></td>
                                            <td>${n.createDate}</td>
                                        </tr>
                                    </c:forEach>

                                </tbody>
                            </table>

                        </c:if>
                    </div>
                </div>
            </section>
           
            <!-- About section two-->
            <section class="py-3">
                <div class="container px-4 my-1">

                    <div class="row gx-5 d-flex">

                        <nav aria-label="Page navigation example">
                            <ul class="pagination">

                                <c:set var="url" value="${contextPath}/admin/notice/list?cp="/>

                                <li class="page-item"><a class="page-link nav-text-color" href="${url}${pagination.prevPage}">Previous</a></li>

                                <!-- 범위가 정해진 일반 for문 사용 -->
                                <c:forEach var="i" begin="${pagination.startPage}" end="${pagination.endPage}" step="1">

                                    <c:choose>
                                        <c:when test="${i == pagination.currentPage}">
                                            <li class="page-item current"><a class="page-link nav-text-color">${i}</a></li>
                                        </c:when>

                                        <c:otherwise>
                                            <li class="page-item"><a class="page-link nav-text-color" href="${url}${i}">${i}</a></li>     
                                        </c:otherwise>
                                    </c:choose>

                                </c:forEach>

                                <li class="page-item"><a class="page-link nav-text-color" href="${url}${pagination.nextPage}">Next</a></li>

                                <li><a class="btn btn-primary mx-3 button-pink" href="${contextPath}/admin/notice/write?cp=${pagination.currentPage}">글작성</a></li>

                            </ul>
                            
                        </nav>

                    </div>

                </div>
            </section>
        </main>

        <!-- 푸터 -->
        <jsp:include page="/WEB-INF/views/common/footer.jsp" />

        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        
        <!-- Core theme JS-->
        <script src="${contextPath}/resources/js/scripts.js"></script>
    </body>
</html>
