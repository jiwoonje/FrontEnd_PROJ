package site;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import qna.QnaDTO;
import util.JDBCUtil;

public class SiteDAO {
	
	Connection conn = null; 
	PreparedStatement pstmt = null ; 
	ResultSet rs = null ; 

	// 관리자만 가능
	private final String SITE_INSERT = 
			"insert into site (siteid, sitename, siteweb, siteaddr, sitetel, sitedesc, siteimage)"
			+ "values ((select nvl(max(siteid),0)+1 from site), ?, ?, ?, ?, ?, ?)" ;
	
	private final String SITE_UPDATE = 
			"update site set sitename=?, siteweb=?, siteaddr=?, sitetel=?, sittedesc=?, siteimage=? where siteid=?" ;
	
	private final String SITE_DELETE = 
			"delete site where siteid=?";
	// 관리자만 가능 끝
	
	private final String SITE_LIST = 
			"select * from site order by siteid desc";
	
	private final String SITE_GET = 
			"select * from site where siteid=?";
	
	// insert
	public void insertSite (SiteDTO dto)
	{
		System.out.println("insert Site 기능");
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(SITE_INSERT);
			
			pstmt.setString(1, dto.getSiteName());
			pstmt.setString(2, dto.getSiteWeb());
			pstmt.setString(3, dto.getSiteAddr());
			pstmt.setString(4, dto.getSiteTel());
			pstmt.setString(5, dto.getSiteDesc());
			pstmt.setLong(6, dto.getSiteImage());
			
			pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt, conn);
		}
	}
	
	// update
	public void updateSite(SiteDTO dto) {
		System.out.println("updateSite 메소드 호출됨");
		
		try {
			conn = JDBCUtil.getConnection(); 
			pstmt = conn.prepareStatement(SITE_UPDATE); 
			
			pstmt.setString(1, dto.getSiteName());
			pstmt.setString(2, dto.getSiteWeb());
			pstmt.setString(3, dto.getSiteAddr());
			pstmt.setString(4, dto.getSiteTel());
			pstmt.setString(5, dto.getSiteDesc());
			pstmt.setLong(6, dto.getSiteImage());
			
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
	public void deleteSite (SiteDTO dto) {
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(SITE_DELETE);
			pstmt.setInt(1, dto.getSiteID());
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
	public List<SiteDTO> getSiteList(QnaDTO dto) {
		
		List<SiteDTO> siteList = new ArrayList<>();
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(SITE_LIST);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				SiteDTO site = new SiteDTO();
				
				site.setSiteID(rs.getInt("SITEID"));
				site.setSiteName(rs.getString("SITENAME"));
				site.setSiteWeb(rs.getString("SITEWEB"));
				site.setSiteAddr(rs.getString("SITEADDR"));
				site.setSiteTel(rs.getString("SITETEL"));
				site.setSiteDesc(rs.getString("SITEDESC"));
				site.setSiteImage(rs.getLong("SITEIMAGE"));
				
				siteList.add(site);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, pstmt, conn);
		} return siteList;
	}
	
	// get
	public SiteDTO getSite(SiteDTO dto) {
		System.out.println("getSite 메소드 호출 성공");
		
		SiteDTO site = new SiteDTO();
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(SITE_GET);
			pstmt.setInt(1, dto. getSiteID());
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				site.setSiteName(rs.getString("SITENAME"));
				site.setSiteWeb(rs.getString("SITEWEB"));
				site.setSiteAddr(rs.getString("SITEADDR"));
				site.setSiteTel(rs.getString("SITETEL"));
				site.setSiteDesc(rs.getString("SITEDESC"));
				site.setSiteImage(rs.getLong("SITEIMAGE"));
				
				System.out.println("RS의 레코드를 dto에 저장 성공");
			}
		} catch (Exception e) {
			System.out.println("글 상세 조회 실패");
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, pstmt, conn);
		} return site;
	}
	
}
