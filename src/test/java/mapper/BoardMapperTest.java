package mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.Board;
import domain.dto.Criteria;
import lombok.extern.slf4j.Slf4j;
import util.MybatisUtil;

@Slf4j
public class BoardMapperTest {

	private BoardMapper boardMapper = MybatisUtil.getSqlSession().getMapper(BoardMapper.class);
	
	@Test
	public void addTest() {
		int result = 1 + 1;
		assertEquals(3, result);
	}
	
	@Test
	@DisplayName("단일 게시글 조회용 테스트")
	public void testSelectOne() {
		// given
		Long bno = 1073L;
		
		// when
		Board board = boardMapper.selectOne(bno);

		// then ~ actual, expect
		assertNotNull(board);
		
		log.info("{}", board);
	}
	
	// list 
	@Test
	@DisplayName("목록 조회 3페이지 10개씩 2번 카테고리")
	public void testList() {
		Criteria cri = new Criteria(1, 10, 2);
		List<Board> list = boardMapper.list(cri);
		list.forEach(b -> log.info("{} {} {}", b.getAttachCnt(), b.getReplyCnt(), b.getAttachs()));
	}

	@Test
	@DisplayName("목록 조회 검색어 테스트")
	public void testList2() {
		Criteria cri = new Criteria(1, 10, 2, "TI", "희선");
		log.info(Arrays.toString(cri.getTypes())); 
		List<Board> list = boardMapper.list(cri);
//		list.forEach(b -> log.info("{}", b.getTitle()));
	}
	
	@Test
	@DisplayName("글 수정 테스트")
	public void testUpdate() {
		Long bno = 1016L;
		Board board = boardMapper.selectOne(bno);
		board.setTitle("제목만 수정하기");
		
		boardMapper.update(board);
	}
	
	@Test
	@DisplayName("maxSeq 조회")
	public void testSelectMaxSeq() {
		Board parent = boardMapper.selectOne(1053L);
		int maxSeq = boardMapper.selectMaxSeq(parent);
		log.info("{}", maxSeq);
	}
	
	
}
