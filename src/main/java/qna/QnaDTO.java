package qna;

import java.sql.Date;

import lombok.Data;

@Data
public class QnaDTO {
	
	private int qnaID;
	private String qnaTitle;
	private String qnaContent;
	private String nickName;
	private Date qnaDate;
	private int qnaCnt;
	
}
