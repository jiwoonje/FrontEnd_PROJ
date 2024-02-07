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
import user.UserDAO;
import user.UserDTO;

@WebServlet ("*.us")
public class User_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public User_Controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		request.setCharacterEncoding("UTF-8");
		
		String uri = request.getRequestURI(); 
		String path = uri.substring(uri.lastIndexOf("/")); 
		
		// login
		if (path.equals("/login.us")) {
			System.out.println("login.us 요청 처리");
			
			//1. client에서 넘긴 파라미터 변수값을 받아서 변수에 저장 
			String userID = request.getParameter("userID"); 
			String password = request.getParameter("password"); 
			
			UserDTO dto = new UserDTO(); 
			dto.setUserID(userID); 
			dto.setPassword(password);
			
			System.out.println("test");
			System.out.println(dto);
			
			
			UserDAO dao = new UserDAO(); 
			UserDTO user = new UserDTO(); 
			user = dao.login(dto); 
			
			System.out.println(user);
			
			if (user == null) {
				System.out.println("인증 실패 했습니다.");
				response.sendRedirect("index.jsp"); 
				
			} else { // 인증 성공 
				System.out.println("인증 성공 했습니다. ");
				
				HttpSession session = request.getSession(); 
				session.setAttribute("userID", user.getUserID()); 
				session.setAttribute("password", user.getPassword()); 
				session.setAttribute("nickname", user.getNickName()); 
				session.setAttribute("lastname", user.getLastName());
				session.setAttribute("firstname", user.getFirstName()); 
				session.setAttribute("email", user.getEmail()); 
				session.setAttribute("phonenumber", user.getPhoneNumber()); 
				session.setAttribute("role", user.getRole()); 
//				session.setAttribute("user", user);
				
				response.sendRedirect("index.jsp"); 
			}
			
		// logout
		} else if (path.equals("/logout.us")) {
			System.out.println("/logout.us 요청 처리 ");
			
			HttpSession session = request.getSession(); 
			
			session.invalidate(); 
			
			response.sendRedirect("index.jsp"); 
			
		// join
		} else if (path.equals("/insertUser.us")) {
			System.out.println("insertUser.us 요청 처리");
			
			String userID = request.getParameter("userID"); 
			String password = request.getParameter("password"); 
			String nickname = request.getParameter("nickname");
			String lastname = request.getParameter("lastname");
			String firstname = request.getParameter("firstname");
			String email = request.getParameter("email");
			String phonenumber = request.getParameter("phonenumber");
			
			UserDTO dto = new UserDTO(); 
			
			dto.setUserID(userID); 
			dto.setPassword(password); 
			dto.setNickName(nickname);
			dto.setLastName(lastname);
			dto.setFirstName(firstname);
			dto.setEmail(email);
			dto.setPhoneNumber(phonenumber);
			
			UserDAO dao = new UserDAO(); 
			dao.insertUser(dto); 
			
			System.out.println("DB에 저장 성공");
			response.sendRedirect("index.jsp"); 
		
		// get user list
		} else if (path.equals("/getUserList.us")) {
			System.out.println("/getUser.us 요청");
			
			UserDTO dto = new UserDTO(); 
			UserDAO dao = new UserDAO (); 
			
			List<UserDTO> userList = new ArrayList<>(); 
			
			userList = dao.getUserList(dto); 
			
			HttpSession session = request.getSession(); 
			
			session.setAttribute("UserList", userList); 
			
			response.sendRedirect("getUserList.jsp"); 
			
			// get user
		} else if (path.equals("/getUser.us")) {
			System.out.println("/getUser.us 요청");
				
				
			String userID = request.getParameter("userID"); 
				
			UserDTO dto = new UserDTO(); 
			dto.setUserID(userID); 
				
			UserDAO dao = new UserDAO(); 
				
			UserDTO user = new UserDTO(); 
			user = dao.getUser(dto); 
				
			HttpSession session = request.getSession(); 
			session.setAttribute("user", user); 
				
			response.sendRedirect("mypage.jsp"); 
			
		// update user	
		} else if (path.equals("/updateUser.us")){
			System.out.println("/updateUser.us 요청");

			String password = request.getParameter("password");
			String nickname = request.getParameter("nickname");
			String lastname = request.getParameter("lastname");
			String firstname = request.getParameter("firstname");
			String email = request.getParameter("email"); 
			String phonenumber = request.getParameter("phonenumber"); 
			
			
			UserDTO dto = new UserDTO(); 
			
			dto.setPassword(password);
			dto.setNickName(nickname);
			dto.setLastName(lastname);
			dto.setFirstName(firstname);
			dto.setEmail(email);
			dto.setPhoneNumber(phonenumber);
			
			UserDAO dao = new UserDAO (); 
			dao.updateUser(dto); 
			
			response.sendRedirect("index.jsp");
			
		// delete user			
		}else if (path.equals("/deleteUser.us")) {
			System.out.println("/deleteUser.us 요청");
			
			String userID = request.getParameter("userID"); 
			
			UserDTO dto = new UserDTO(); 
			dto.setUserID(userID); 
			
			UserDAO dao = new UserDAO(); 
			
			dao.deleteUser(dto); 
			
			response.sendRedirect("getUserList.us"); 

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		
	}

}
