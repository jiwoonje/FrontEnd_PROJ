package qna;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.JDBCUtil;

public class QnaDAO {
	
	Connection conn = null; 
	PreparedStatement pstmt = null ; 
	ResultSet rs = null ; 

	private final String QNA_INSERT = 
			"insert into qna (qnaID, qnatitle, qnacontent, nickName)"
			+ "values ((select nvl(max(qnaID),0)+1 from qna), ?, ?, ?)";
	
	private final String QNA_LIST = 
			"select * from qna order by qnaID desc" ;
	
	private final String QNA_GET = 
			"select * from qna where qnaID=?" ;
	
	private final String QNA_UPDATE =
			"update qna set qnaTitle=?, qnaContent=? where qnaID=?" ;
	
	private final String ADD_CNT = 
			"update qna set qnacnt = qnacnt + 1 where qnaID=?" ;
	
	// 본인 혹은 관리자만 가능
	private final String QNA_DELETE =
			"delete qna where qnaID=?" ;
	
	// insert
	public void insertQna (QnaDTO dto)
	{
		System.out.println("insert Qna 기능");
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(QNA_INSERT);
			
			pstmt.setString(1, dto.getQnaTitle());
			pstmt.setString(2, dto.getQnaContent());
			pstmt.setString(3, dto.getNickName());
			
			pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt, conn);
		}
	}
	
	// list
	public List<QnaDTO> getQnaList(QnaDTO dto) {
		
		List<QnaDTO> qnaList = new ArrayList<>();
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(QNA_LIST);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				QnaDTO qna = new QnaDTO();
				
				qna.setQnaID(rs.getInt("QNAID"));
				qna.setQnaTitle(rs.getString("QNATITLE"));
				qna.setQnaContent(rs.getString("QNACONTENT"));
				qna.setNickName(rs.getString("NICKNAME"));
				qna.setQnaDate(rs.getDate("QNADATE"));
				qna.setQnaCnt(rs.getInt("QNACNT"));
				
				qnaList.add(qna);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, pstmt, conn);
		} return qnaList;
	}
	
	// get
	public QnaDTO getQna(QnaDTO dto) {
		System.out.println("getQna 메소드 호출 성공");
		
		addCNT(dto);
		QnaDTO qna = new QnaDTO();
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(QNA_GET);
			pstmt.setInt(1, dto. getQnaID());
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				qna.setQnaID(rs.getInt("QNAID"));
				qna.setQnaTitle(rs.getString("QNATITLE"));
				qna.setQnaContent(rs.getString("QNACONTENT"));
				qna.setNickName(rs.getString("NICKNAME"));
				qna.setQnaCnt(rs.getInt("QNACNT"));
				qna.setQnaDate(rs.getDate("QNADATE"));
				
				System.out.println("RS의 레코드를 dto에 저장 성공");
			}
		} catch (Exception e) {
			System.out.println("글 상세 조회 실패");
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}return qna;
	}
	
	// update
	public void updateQna(QnaDTO dto) {
		System.out.println("updateQna 메소드 호출됨");
		
		try {
			conn = JDBCUtil.getConnection(); 
			pstmt = conn.prepareStatement(QNA_UPDATE); 
			
			pstmt.setString(1, dto.getQnaTitle());
			pstmt.setString(2, dto.getQnaContent());			
			pstmt.setInt(3, dto.getQnaID());			
			pstmt.executeUpdate(); 
			
			System.out.println("DB 업테이트 성공 ");
		} catch (Exception e) {
			System.out.println("DB 업테이트 실패 ");
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt, conn);
		}
	}
	
	// addcnt
	public void addCNT(QnaDTO dto) {
		try {
			conn = JDBCUtil.getConnection(); 
			pstmt = conn.prepareStatement(ADD_CNT);
			pstmt.setInt(1, dto.getQnaID()); 
			pstmt.executeUpdate(); 
			
			System.out.println("조회수 입력 성공");
		} catch (Exception e) {
			System.out.println("조회수 입력 실패");
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt, conn);
		}
	}
	
	// delete
	public void deleteQna (QnaDTO dto) {
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(QNA_DELETE);
			pstmt.setInt(1, dto.getQnaID());
			pstmt.executeUpdate();
			
			System.out.println("DB의 레코드 삭제 성공");
		} catch (Exception e) {
			System.out.println("DB의 레코드 삭제 실패");
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt, conn);
		}
	}
}
