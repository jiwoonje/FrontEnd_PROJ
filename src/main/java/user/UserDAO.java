package user;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import util.JDBCUtil;

public class UserDAO {

	Connection conn = null ; 
	PreparedStatement pstmt = null; 
	ResultSet rs = null;
	
	private final String USER_LOGIN = 
			"select * from users where userID = ? and password = ? ";
	
	private final String USER_INSERT = 
			"insert into users (userID, password, nickname, lastname, firstname, email, phonenumber)"
			+ "values (?, ?, ?, ?, ?, ?, ?)" ;
	
	private final String USER_GET =
			"select userID, nickname, lastname, firstname, email, phonenumber from users where userID = ?" ;
	
	private final String USER_UPDATE = 
			"update users set password=?, nickname=?, lastname=?, firstname=? email=?, phonenumber=? where userID = ? " ;
	
	// 본인 혹은 관리자 페이지 기능
	private final String USER_LIST = 
			"select * from users order by userID desc" ;
	
	private final String USER_DELETE =
			"delete users where userID = ?" ;
	// 본인 혹은 관리자 페이지 기능 끝
	
	
	// login
	public UserDTO login(UserDTO dto) {
		System.out.println("login 메소드 호출");
		
		UserDTO user = null; 
		
		try	{
			conn = JDBCUtil.getConnection(); 
			pstmt = conn.prepareStatement(USER_LOGIN); 
			
			pstmt.setString(1, dto.getUserID());
			pstmt.setString(2, dto.getPassword());
			
			rs = pstmt.executeQuery();   //select 

			while (rs.next()) {
				user = new UserDTO(); 
				
				user.setUserID(rs.getString("USERID"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setNickName(rs.getString("NICKNAME"));
				user.setLastName(rs.getString("LASTNAME"));
				user.setFirstName(rs.getString("FIRSTNAME"));
				user.setEmail(rs.getString("EMAIL"));
				user.setPhoneNumber(rs.getString("PHONENUMBER"));
				user.setRole(rs.getString("ROLE"));
				
				System.out.println(" - 인증 성공 : 해당 ID와 Password가 DB에 존재합니다.  ");				
			}
					
		} catch (Exception e) {
			System.out.println("인증시 문제가 발생 했습니다. ");
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, pstmt, conn);
		} return user; 
	}
	
	//메소드 - 로그아웃
		public UserDTO logout(UserDTO dto) {
			System.out.println("logout 메소드 호출");
			UserDTO user = null; 
			try {
				conn = JDBCUtil.getConnection(); 
				pstmt = conn.prepareStatement(USER_LOGIN); 
				pstmt.setString(1, dto.getUserID());
				pstmt.setString(2, dto.getPassword());
				
				rs = pstmt.executeQuery(); 
				
				while ( rs.next()) {
					user = new UserDTO(); 
					
					user.setUserID(rs.getString("USERID"));
					user.setPassword(rs.getString("PASSWORD"));
					user.setNickName(rs.getString("NICKNAME"));
					user.setLastName(rs.getString("LASTNAME"));
					user.setFirstName(rs.getString("FIRSTNAME"));
					user.setEmail(rs.getString("EMAIL"));
					user.setPhoneNumber(rs.getString("PHONENUMBER"));
					user.setRole(rs.getString("ROLE"));
					
					System.out.println(" - 인증 성공 : 해당 ID와 Password가 DB에 존재합니다.  ");				
				}
						
			} catch (Exception e) {
				System.out.println("인증시 문제가 발생 했습니다. ");
				e.printStackTrace();
			} finally {
				JDBCUtil.close(rs, pstmt, conn);
			} return user; 
		}
	
	// insert
	public void insertUser(UserDTO dto) {
		System.out.println("insert User 기능");
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt= conn.prepareStatement(USER_INSERT); 
			
			pstmt.setString(1, dto.getUserID());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getNickName());
			pstmt.setString(4, dto.getLastName());
			pstmt.setString(5, dto.getFirstName());
			pstmt.setString(6, dto.getEmail());
			pstmt.setString(7, dto.getPhoneNumber());
			
			pstmt.executeUpdate(); 
			
			System.out.println("Users 테이블에 값이 잘 insert 되었습니다. ");
			
		} catch(Exception e) {
			
			System.out.println("Users 테이블에 값이 insert에 실패 했습니다. ");
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt, conn);
		}
	}
	
	// get
	public UserDTO getUser(UserDTO dto) {
		System.out.println("getUser 메소드 호출 성공");
				
		UserDTO user = new UserDTO(); 
		
		try 
		{
			conn = JDBCUtil.getConnection();
			//"select id, phone, email, addr, role from member where id = ?" ;
			pstmt = conn.prepareStatement(USER_GET); 
			pstmt.setString(1, dto.getUserID());
			
			// rs : 레코드 1개 
			rs = pstmt.executeQuery(); 
			
			//rs의 값이 존재할때 
			while ( rs.next()) 
			{
				
				user.setUserID(rs.getString("USERID"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setNickName(rs.getString("NICKNAME"));
				user.setLastName(rs.getString("LASTNAME"));
				user.setFirstName(rs.getString("FIRSTNAME"));
				user.setEmail(rs.getString("EMAIL"));
				user.setPhoneNumber(rs.getString("PHONENUMBER"));
				
				System.out.println("RS 의 레코드를 dto 저장 성공 ");
			}
			
		}
		
		catch (Exception e) 
		{
			System.out.println("회원 정보 조회 실패");
			e.printStackTrace();
		}
		
		finally 
		{
			JDBCUtil.close(rs, pstmt, conn);
		}
				
		return user; 
	}
	
	// update
	public void updateUser(UserDTO dto) 
	{
		System.out.println("updateUser 메소드 호출됨");
		
		try 
		{
			conn = JDBCUtil.getConnection(); 
			//BOARD_UPDATE = "update member set title= ?, write= ? , content = ? where id = ?"
			pstmt = conn.prepareStatement(USER_UPDATE); 
			
			// ? 변수에 값을 할당 
			pstmt.setString(1, dto.getPassword());
			pstmt.setString(2, dto.getNickName());
			pstmt.setString(3, dto.getLastName());
			pstmt.setString(4, dto.getFirstName());
			pstmt.setString(5, dto.getEmail());
			pstmt.setString(6, dto.getPhoneNumber());
			pstmt.setString(6, dto.getUserID());
			
			//쿼리를 실행
			pstmt.executeUpdate(); 		//insert, update, delete 구문일때 실행 
			
			System.out.println("DB 업테이트 성공 ");
			
		}
		
		catch (Exception e) 
		{
			System.out.println("DB 업테이트 실패 ");
			e.printStackTrace();
			
		}
		
		finally 
		{
			JDBCUtil.close(pstmt, conn);
		}
		
	}
	
	// list
	public List<UserDTO> getUserList(UserDTO dto) {
		List<UserDTO> UserList = new ArrayList<>();
		
		try { 
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(USER_LIST) ;
			rs = pstmt.executeQuery(); 
			
			while (rs.next()) {
				UserDTO user = new UserDTO();
				
				user.setUserID(rs.getString("USERID"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setNickName(rs.getString("NICKNAME"));
				user.setLastName(rs.getString("LASTNAME"));
				user.setFirstName(rs.getString("FIRSTNAME"));
				user.setEmail(rs.getString("EMAIL"));
				user.setPhoneNumber(rs.getString("PHONENUMBER"));
				user.setRole(rs.getString("ROLE"));
				
				UserList.add(user);
			}
		} catch (Exception e) {
			System.out.println("DB select 실패");
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}return UserList;
	}
	
	// delete
	public void deleteUser(UserDTO dto) {
		try {
			conn = JDBCUtil.getConnection(); 
			// BOARD_DELETE = "delete board where seq = ?"
			pstmt = conn.prepareStatement(USER_DELETE); 
			
			// ? 변수값 할당. 
			pstmt.setString(1, dto.getUserID());
			
			// 쿼리 실행 
			pstmt.executeUpdate();   // insert, update, delete 
			
			System.out.println("DB의 레코드 삭제 성공");
			
		} catch (Exception e) {
			System.out.println("DB의 레코드 삭제 실패");
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt, conn);
		}
	}
}
