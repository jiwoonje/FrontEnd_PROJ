<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="notice.NoticeDTO" %>
<%@ page import="user.UserDTO" %>

<%
	UserDTO user = new UserDTO();
	user = (UserDTO)session.getAttribute("user");
%>
<%
	String userID = (String)session.getAttribute("userID");
	String password = (String)session.getAttribute("password");
	String nickname = (String)session.getAttribute("nickname");
	String lastname = (String)session.getAttribute("lastname");
	String firstname = (String)session.getAttribute("firstname");
	String email = (String)session.getAttribute("email");
	String phonenumber = (String)session.getAttribute("phonenumber");
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
            <!-- 메인 상단 배너 부분 -->
            <div class="visual">
        		<div class="swiper-container">
            		<div class="swiper-wrapper">
                		<div class="swiper-slide"><img src="img/banner1.jpg" alt="HTML"></div>
               			<div class="swiper-slide"><img src="img/banner2.jpg" alt="CSS"></div>
                		<div class="swiper-slide"><img src="img/banner3.jpg" alt="Javascript"></div>
                		<div class="swiper-slide"><img src="img/banner4.jpg" alt="jQuery"></div>
            		</div>
            		<div class="swiper-pagination"></div>
            		<div class="next arrow">&gt;</div>
            		<div class="prev arrow">&lt;</div>
        		</div>
    		</div>
            <!-- 메인 전시 부분 -->
            <div class="site_title"><h1>관광지 소개</h1></div>
            <div>
                <ul class="site_content">
                    <li><a class="site_element" href="#"><img src="img/site1.jpg">
                    	<p class="site_hover_text">경복궁</p></a></li>
                    <li><a class="site_element" href="#"><img src="img/site2.jpg">
                    	<p class="site_hover_text">창덕궁</p></a></li>
                    <li><a class="site_element" href="#"><img src="img/site3.jpg">
                    	<p class="site_hover_text">덕수궁</p></a></li>
                </ul>
                <br>
                <ul class="site_content">
                    <li class="site_element"><a href="#"><img src="img/site4.jpg">
                    	<p class="site_hover_text">탑골공원</p></a></li>
                    <li class="site_element"><a href="#"><img src="img/site5.jpg">
                    	<p class="site_hover_text">동대문</p></a></li>
                    <li class="site_element"><a href="#"><img src="img/site6.jpg">
                    	<p class="site_hover_text">창경궁</p></a></li>
                </ul>
            </div>
            <br><br>
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
