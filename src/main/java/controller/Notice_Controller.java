package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import notice.NoticeDAO;
import notice.NoticeDTO;

@WebServlet ("*.no")
public class Notice_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Notice_Controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		request.setCharacterEncoding("UTF-8");
		
		String uri = request.getRequestURI();
		
		String path = uri.substring(uri.lastIndexOf("/")); 
		System.out.println(path);
		System.out.println("====================");
		
		if (path.equals("/insertNotice.no")) {
			System.out.println("insertNotice.no 요청");
			
			int noticeID = Integer.parseInt(request.getParameter("noticeID"));
			String noticetitle = request.getParameter("noticetitle");
			String noticecontent = request.getParameter("noticecontent");
			int noticecnt = Integer.parseInt(request.getParameter("noticecnt"));
			
			NoticeDTO dto = new NoticeDTO();
			dto.setNoticeID(noticeID);
			dto.setNoticeTitle(noticetitle);
			dto.setNoticeContent(noticecontent);
			dto.setNoticeCnt(noticecnt);
			
			NoticeDAO dao = new NoticeDAO();
			dao.insertNotice(dto);
			
			response.sendRedirect("getNoticeList.no");
			
		}
		
		else if (path.equals("/updateNotice.no")) 
		{
			System.out.println("updateNotice.no 요청");
			
			String noticeTitle = request.getParameter("noticetitle");
			String noticeContent = request.getParameter("noticecontent");
			int noticeID = Integer.parseInt(request.getParameter("noticeID"));
			
			NoticeDTO dto = new NoticeDTO();
			dto.setNoticeTitle(noticeTitle);
			dto.setNoticeContent(noticeContent);
			dto.setNoticeID(noticeID);
			
			NoticeDAO dao = new NoticeDAO();
			dao.updateNotice(dto);
			
			response.sendRedirect("getNoticeList.no");
			
		} else if (path.equals("/deleteNotice.no")) {
			System.out.println("deleteNotice.no 요청");
			
			String noticeIDa = request.getParameter("noticeID");
			int noticeID = Integer.parseInt(noticeIDa);
			
			NoticeDTO dto = new NoticeDTO();
			dto.setNoticeID(noticeID);
			
			NoticeDAO dao = new NoticeDAO();
			dao.deleteNotice(dto);
			
			response.sendRedirect("getNoticeList.no");
			
		} else if (path.equals("/getNoticeList.no")) {
			System.out.println("getNoticeList.no 요청");
			
			NoticeDTO dto = new NoticeDTO();
			NoticeDAO dao = new NoticeDAO();
			
			List<NoticeDTO> noticeList = new ArrayList<>();
			
			noticeList = dao.getNoticeList(dto);
			System.out.println(noticeList);
			
			HttpSession session = request.getSession();
			session.setAttribute("noticeList", noticeList);
			
			response.sendRedirect("notice_list.jsp");
		
			
		} else if (path.equals("/getNoticeList5.no")) {
			System.out.println("getNoticeList5.no 요청");
			
			NoticeDTO dto = new NoticeDTO();
			NoticeDAO dao = new NoticeDAO();
			
			List<NoticeDTO> noticeList5 = new ArrayList<>();
			
			noticeList5 = dao.getNoticeList5(dto);
			System.out.println(noticeList5);
			
			HttpSession session = request.getSession();
			session.setAttribute("noticeList5", noticeList5);
			
			response.sendRedirect("index.jsp");
		
			
		}
		
		
		else if (path.equals("/getNotice.no")) {
			System.out.println("/getNotice.no 요청");
			
			int noticeID = Integer.parseInt(request.getParameter("noticeID"));
			
			NoticeDTO dto =new NoticeDTO();
			dto.setNoticeID(noticeID);
			
			NoticeDAO dao = new NoticeDAO();
			NoticeDTO notice = new NoticeDTO();
			notice = dao.getNotice(dto);
			System.out.println(notice);
			
			HttpSession session = request.getSession();
			session.setAttribute("notice", notice);
			
			response.sendRedirect("notice_get.jsp");
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
