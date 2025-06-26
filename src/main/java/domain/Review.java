package domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Review {
	private Long rno;
	private String content;
	private String regdate;
	private Integer rating;
	private String writer;
	private Long pno;
}
