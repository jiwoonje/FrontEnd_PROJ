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
import qna.QnaDAO;
import qna.QnaDTO;

@WebServlet ("*.qa")
public class Qna_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Qna_Controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("qa 요청을 처리하는 controller 입니다.");
		
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		String path = uri.substring(uri.lastIndexOf("/")); 
		System.out.println(path);
		System.out.println("====================");
		
		if (path.equals("/insertQna.qa")) {
			System.out.println("insertQna.qa 요청");
			
			String qnatitle = request.getParameter("qnatitle");
			String qnacontent = request.getParameter("qnacontent");
			String nickname = request.getParameter("nickname");
			
			QnaDTO dto = new QnaDTO();
			dto.setQnaTitle(qnatitle);
			dto.setQnaContent(qnacontent);
			dto.setNickName(nickname);
			
			QnaDAO dao = new QnaDAO();
			dao.insertQna(dto);
			System.out.println(dao);
			
			response.sendRedirect("getQnaList.qa");
			
		} else if (path.equals("/updateQna.qa")) {
			System.out.println("updateQna.qa 요청");
			
			String qnatitle = request.getParameter("qnatitle");
			String qnacontent = request.getParameter("qnacontent");
			int qnaID = Integer.parseInt(request.getParameter("qnaID"));
			
			QnaDTO dto = new QnaDTO();
			dto.setQnaTitle(qnatitle);
			dto.setQnaContent(qnacontent);
			dto.setQnaID(qnaID);
			
			QnaDAO dao = new QnaDAO();
			dao.updateQna(dto);
			
			response.sendRedirect("getQnaList.qa");
			
		} else if (path.equals("/deleteQna.qa")) {
			System.out.println("deleteQna.qa 요청");
			
			String qnaIDa = request.getParameter("qnaID");
			int qnaID = Integer.parseInt(qnaIDa);
			
			QnaDTO dto = new QnaDTO();
			dto.setQnaID(qnaID);
			
			QnaDAO dao = new QnaDAO();
			dao.deleteQna(dto);
			
			response.sendRedirect("getQnaList.qa");
			
		} else if (path.equals("/getQnaList.qa")) {
			System.out.println("getQnaList.qa 요청");
			
			QnaDTO dto = new QnaDTO();
			QnaDAO dao = new QnaDAO();
			
			List<QnaDTO> qnaList = new ArrayList<>();
			
			qnaList = dao.getQnaList(dto);
			System.out.println(qnaList);
			
			HttpSession session = request.getSession();
			session.setAttribute("qnaList", qnaList);
			
			response.sendRedirect("qna_list.jsp");
			
		} else if (path.equals("/getQna.qa")) {
			System.out.println("getQna.qa 요청");
			
			int qnaID = Integer.parseInt(request.getParameter("qnaID"));
			
			QnaDTO dto = new QnaDTO();
			dto.setQnaID(qnaID);
			
			QnaDAO dao = new QnaDAO();
			QnaDTO qna = new QnaDTO();
			qna = dao.getQna(dto);
			System.out.println(qna);
			
			HttpSession session = request.getSession();
			session.setAttribute("qna", qna);
			
			response.sendRedirect("qna_get.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
