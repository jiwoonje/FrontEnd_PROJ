<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String sessionUserID = (String) session.getAttribute("userid");
	String sessionRole = (String) session.getAttribute("role");
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>I SEOUL YOU</title>
    <link rel="stylesheet" href="css/layout.css">
    <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />
    <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
</head>
<body>
    <header>
            <div class="header_inner_box">
                <!-- 헤더 좌측 로고 1 -->
                <div class="header_logo1"><a href="https://www.seoul.go.kr/main/index.jsp"><img src="./img/logo1.png"></a></div>
                <!-- 헤더 좌측 로고 2 -->
                <div class="header_logo2"><a href="https://www.jongno.go.kr/portalMain.do"><img src="./img/logo2.png"></a></div>
                <!-- 헤더 우측 메뉴 부분 -->
                <div class="header_menu">
                    <ul class="header_menu_top">
                        <li>
                        <%
                        String userID = (String)session.getAttribute("userID");
                        if (userID == null) {
                        %>
                        <a href="./LoginForm.jsp"> 로그인 </a>&nbsp;&nbsp;&nbsp;
                        <a href="./user_insert.jsp"> 회원가입 </a>
                        <%
                        } else {
                        %>
                        <span style="color: white;"><% out.print(userID); %>님 로그인 중</span>&nbsp;&nbsp;&nbsp;
                        
                        <a href="logout.us"> 로그아웃 </a>&nbsp;&nbsp;&nbsp;
                        <a href="getUser.us?=<% out.print(userID); %>"> 마이페이지 </a>
                        </li>
						<%
                        }
						%>
                        
                    </ul>
                    
                    <ul class="header_menu_bottom">
                        <li><a href="index.jsp">홈</a></li>
                        <li><a href="notice_list.jsp">공지사항</a></li>
                        <li><a href="site_list.jsp">관광지 소개</a></li>
                        <li><a href="qna_list.jsp">질문게시판</a></li>
                    </ul>
                </div>
            </div>
        </header>
        <main>
            <div class="main_box">
            
            <%
            	if (sessionUserID == null) {
            %>
                <!-- 로그인 박스 -->
                <form class="login_box" method="post" action="login.us">
                    <br>
                    <p class="login_title">로 그 인</p>
                    <br>
                    <div class="login_box_inner">
                        <input type="text" class="id_box" name="userID" placeholder="ID">
                    </div>
                    <div class="login_box_inner">
                        <input type="password" class="id_box" name="password" placeholder="Password">
                    </div>
                    <a href="index.jsp"><button type="submit" class="btn">
                    Login
                    </button></a>
                </form>
            <%
            	} else {                	
            %>
            <%
            }
            %>
            </div>
        </main>
	<footer>
		<div>
        	<ul>
            	<!-- 푸터 좌측 기본 정보 -->
            	<li class="footer_information">
            		서울특별시 종로구 혜화동 9길 청운빌딩 5층 / 대표전화 02-1234-5678 / E-mail : titour@touristintour.com<br>
                	Copyright(c) TouristInTour all right reserved</li>
                    <!-- 푸터 중앙 기타 사이트 -->
                    <li class="footer_related_site">
                        <select class="select" onchange="window.open(value,'_blank');">
                            <option>관련 사이트 바로가기</option>
                            <option value="https://www.seoul.go.kr/main/index.jsp">서울시청</option>
                            <option value="https://www.jongno.go.kr/portalMain.do">종로구청</option>
                            <option value="https://korean.visitseoul.net/editorspicks">Visit 서울</option>
                        </select>
                    </li>
                    <!-- 푸터 우측 SNS 연동 -->
                    <li class="footer_sns">
                        <a href="https://blog.naver.com/jongno0401" target="_blank"><img src="././img/sns.png"></a>
                        <a href="https://www.instagram.com/jongnotong" target="_blank"><img src="././img/sns2.png"></a>
                        <a href="https://www.youtube.com/channel/UCElvTSAbQjunKseoz1u_-0A/featured" target="_blank"><img src="././img/sns3.png"></a>
                    </li>
                </ul>
            </div>
        </footer>
        <script src="swiper.min.js"></script>
    	<script>
        /* 자동실행, left, right버튼 기능 api 추가 */
        var swiper = new Swiper('.swiper-container', {
            autoplay: {
                delay: 2000,
                disableOnInteraction: false
            },
            slidesPerView: 1,
            loop: true,
            pagination: {
                el: '.swiper-pagination',
                clickable: true,
            },
            navigation: {
                nextEl: '.next',
                prevEl: '.prev',
            }
        });
    	</script>
</body>
</html>