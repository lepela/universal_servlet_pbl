package domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Alias("board")
public class Board {
	private Long bno;
	private String title;
	private String content;
	private String id;
	private String regdate;
	private String moddate;
	private Integer cnt;
	private Integer cno;
	private int replyCnt;
	private int attachCnt;
	
	// 답글을 위한 3개의 필드
	private Long grp;
	
	@Builder.Default
	private int seq = 1;
	@Builder.Default
	private int depth = 1;
	
	
	public Board(Long bno, String title, String content, String id, String regdate, String moddate, Integer cnt,
			Integer cno, int replyCnt, int attachCnt) {
		super();
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.id = id;
		this.regdate = regdate;
		this.moddate = moddate;
		this.cnt = cnt;
		this.cno = cno;
		this.replyCnt = replyCnt;
		this.attachCnt = attachCnt;
	}

	@Builder.Default
	private List<Attach> attachs = new ArrayList<>();
}
