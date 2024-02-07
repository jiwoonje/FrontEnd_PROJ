package test;

import java.util.ArrayList;
import java.util.List;

import notice.NoticeDAO;
import notice.NoticeDTO;

public class Notice_Test 
{

	public static void main(String[] args) 
	{
		//1. DTO 객체를 생성후 값 입력 
				NoticeDTO dto = new NoticeDTO(); 
								
				// dto 에 setter를 사용해서 값을 입력 
				
				dto.setNoticeID(43);
				dto.setNoticeTitle("예약번호5");
				dto.setNoticeContent("번호5");

				//2. DAO 객체에 insertnotice(dto) 
				NoticeDAO dao = new NoticeDAO(); 
				dao.insertNotice(dto);
								
								
								
				//1. DTO 객체를 생성후 값 입력 
				NoticeDTO dto1 = new NoticeDTO(); 
										
				// dto 에 setter를 사용해서 값을 입력 
				dto1.setNoticeID(44);
				dto1.setNoticeTitle("예약번호6");
				dto1.setNoticeContent("번호6");
								
				//2. DAO 객체에 insertnotice(dto) 
				NoticeDAO dao1 = new NoticeDAO();		
				dao1.insertNotice(dto1);

						
				System.out.println("====================================================");
						
						
				// noticeDAO의 getnoticeList(dto) 메소드 테스트 
						
				// 1. noticeDTO 객체 생성
				NoticeDTO dto2 = new NoticeDTO(); 
						
				// 2. noticeDAO 객체 생성 후 메소드 호출 
				NoticeDAO dao2 = new NoticeDAO();
						
				//ArrayList 선언 : <noticeDTO> 객체가 각 방에 들어 있음. 
				List<NoticeDTO> noticeList = new ArrayList<>();
						
				//noticeList DB의 각 레코드를 DTO에 담아서 저장 
				noticeList = dao2.getNoticeList(dto2); 
						
				//ArrayList : noticeList 의 각방의 noticeDTO 객체를 순환하면서 객체를 출력 
				// FOR 문을 사용해서 출력 
				System.out.println("===========FOR 문으로 출력 ===========");
				for (int i = 0 ; i < noticeList.size(); i++) 
				{
					System.out.println(noticeList.get(i));
				}
						
				System.out.println("===========Enhanced For 문으로 출력 =====================");
				for (NoticeDTO b : noticeList) 
				{   // noticeList : ArrayList 의 각방의 저장된것을 b 변수로 끄집어내서 출력
					System.out.println(b);
				}
						
						
				System.out.println("====================================================");
						
				//1.  noticeDTO 에 title, write, content, seq 값을 
				NoticeDTO dto4 = new NoticeDTO();
				dto4.setNoticeID(44);
				dto4.setNoticeTitle("이름9");
				dto4.setNoticeContent("9");
						
				//2. noticeDAO의 updatenotice(dto) 호출 
				NoticeDAO dao4 = new NoticeDAO(); 
						
				dao4.updateNotice(dto4);
						
				System.out.println("====================================================");
						
				//1. dto 에 조회할 id 값만 할당후 dao.getnotice(dto) 
				NoticeDTO dto3 = new NoticeDTO(); 
				dto3.setNoticeID(44);
						
				//2. dao 메소드 호출 getnotice(dto) 
				NoticeDAO dao3 = new NoticeDAO(); 
						
				//리턴으로 돌려 받는 변수 선언 
				NoticeDTO notice = new NoticeDTO(); 
						
				notice = dao3.getNotice(dto3); 
						
						
				System.out.println(notice);
				
				System.out.println("====================================================");
						
			
	}

}
