package notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.JDBCUtil;

public class NoticeDAO {
	
	Connection conn = null; 
	PreparedStatement pstmt = null ; 
	ResultSet rs = null ; 
	
	// 관리자만 가능
	private final String NOTICE_INSERT = 
			"insert into notice (noticeID, noticetitle, noticecontent)"
			+ "values((select nvl(max(noticeID),0)+1 from notice), ?, ?)" ;
	
	private final String NOTICE_UPDATE =
			"update notice set noticetitle=?, noticecontent=? where noticeID=?" ;
	
	private final String NOTICE_DELETE =
			"delete notice where noticeID=?" ;
	// 관리자만 가능 끝
	
	private final String NOTICE_LIST =
			"select * from notice order by noticeID desc";
	
	private final String NOTICE_LIST5 =
			"select * from (select * from notice"
			+ "order by noticedate desc) where rownum <= 5";
	
	private final String NOTICE_GET =
			"select * from notice where noticeID=?" ;
	
	private final String ADD_CNT =
			"update notice set noticecnt = noticecnt + 1 where noticeID=?" ;
	
	// insert 
	public void insertNotice(NoticeDTO dto)
	{
		System.out.println("insertNotice 기능");
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(NOTICE_INSERT);
			
			pstmt.setString(1, dto.getNoticeTitle());
			pstmt.setString(2, dto.getNoticeContent());
			
			pstmt.executeUpdate();
			System.out.println("notice 테이블에 입력 성공");
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt, conn);
		}
	}
	
	// update
	public void updateNotice(NoticeDTO dto) {
		System.out.println("updateNotice 메소드 호출됨");
		
		try {
			conn = JDBCUtil.getConnection(); 
			pstmt = conn.prepareStatement(NOTICE_UPDATE); 
			
			pstmt.setString(1, dto.getNoticeTitle());
			pstmt.setString(2, dto.getNoticeContent());			
			pstmt.setInt(3, dto.getNoticeID());			
			
			pstmt.executeUpdate(); 
			
			System.out.println("DB 업테이트 성공 ");
		} catch (Exception e) {
			System.out.println("DB 업테이트 실패 ");
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt, conn);
		}
	}
	
	// delete
	public void deleteNotice (NoticeDTO dto) {
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(NOTICE_DELETE);
			pstmt.setInt(1, dto.getNoticeID());
			pstmt.executeUpdate();
			
			System.out.println("DB의 레코드 삭제 성공");
		} catch (Exception e) {
			System.out.println("DB의 레코드 삭제 실패");
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt, conn);
		}
	}
	
	// list
	public List<NoticeDTO> getNoticeList(NoticeDTO dto) {
		
		List<NoticeDTO> noticeList = new ArrayList<>();
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(NOTICE_LIST);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				NoticeDTO notice = new NoticeDTO();
				
				notice.setNoticeID(rs.getInt("NOTICEID"));
				notice.setNoticeTitle(rs.getString("NOTICETITLE"));
				notice.setNoticeContent(rs.getString("NOTICECONTENT"));
				notice.setNoticeDate(rs.getDate("NOTICEDATE"));
				notice.setNoticeCnt(rs.getInt("NOTICECNT"));
				
				noticeList.add(notice);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, pstmt, conn);
		} return noticeList;
	}
	
	// list5
	public List<NoticeDTO> getNoticeList5(NoticeDTO dto) {
		
		List<NoticeDTO> noticeList5 = new ArrayList<>();
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(NOTICE_LIST5);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				NoticeDTO notice5 = new NoticeDTO();
				
				notice5.setNoticeID(rs.getInt("NOTICEID"));
				notice5.setNoticeTitle(rs.getString("NOTICETITLE"));
				notice5.setNoticeContent(rs.getString("NOTICECONTENT"));
				notice5.setNoticeDate(rs.getDate("NOTICEDATE"));
				notice5.setNoticeCnt(rs.getInt("NOTICECNT"));
				
				noticeList5.add(notice5);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, pstmt, conn);
		} return noticeList5;
	}

	// get
	public NoticeDTO getNotice(NoticeDTO dto) 
	{
		System.out.println("getNotice 메소드 호출 성공");
		
		addCNT(dto);
		NoticeDTO notice = new NoticeDTO();
		
		try 
		{
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(NOTICE_GET);
			pstmt.setInt(1, dto. getNoticeID());
			
			rs = pstmt.executeQuery();
			while (rs.next()) 
			{
				
				notice.setNoticeID(rs.getInt("NOTICEID"));
				notice.setNoticeTitle(rs.getString("NOTICETITLE"));
				notice.setNoticeContent(rs.getString("NOTICECONTENT"));
				notice.setNoticeCnt(rs.getInt("NOTICECNT"));
				notice.setNoticeDate(rs.getDate("NOTICEDATE"));
				
				System.out.println("RS의 레코드를 dto에 저장 성공");
			}
		} catch (Exception e) {
			System.out.println("글 상세 조회 실패");
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}return notice;
	}

	// addcnt
	public void addCNT(NoticeDTO dto) {
		try {
			conn = JDBCUtil.getConnection(); 
			pstmt = conn.prepareStatement(ADD_CNT);
			pstmt.setInt(1, dto.getNoticeID()); 
			pstmt.executeUpdate(); 
			
			System.out.println("조회수 입력 성공");
		} catch (Exception e) {
			System.out.println("조회수 입력 실패");
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt, conn);
		}
	}
}
