package notice;

import java.sql.Date;

import lombok.Data;

@Data
public class NoticeDTO {
	
	private int noticeID;
	private String noticeTitle;
	private String noticeContent;
	private Date noticeDate;
	private int noticeCnt;

}
