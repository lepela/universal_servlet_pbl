package domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AttachLink {
	private String uuid; // FK
	private LinkType linkType;
	private Long lno; // rno
	private String regdate;
	
	// 두개의 컬럼 index
	
	public enum LinkType {
		BOARD, REPLY
	}
	
}
