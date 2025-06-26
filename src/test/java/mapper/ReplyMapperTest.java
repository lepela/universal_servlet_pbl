package mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.Board;
import domain.Reply;
import domain.dto.Criteria;
import lombok.extern.slf4j.Slf4j;
import util.MybatisUtil;

@Slf4j
public class ReplyMapperTest {
	// ctrl + shift + 키패드의 / : 메서드 폴딩
	// ctrl + shift + 키패드의 * : 메서드 언폴딩
	private ReplyMapper replyMapper = MybatisUtil.getSqlSession().getMapper(ReplyMapper.class);
	
	@Test
	@DisplayName("단일 게시글 조회용 테스트")
	public void testSelectOne() {
		// given
		Long rno = 4L;
		
		// when
		Reply reply = replyMapper.selectOne(rno);

		// then ~ actual, expect
		log.info("{}", reply);
	}

	@Test
	@DisplayName("목록 조회")
	public void testList() {
		List<Reply> list = replyMapper.list(1017L, 35L);
		list.forEach(b -> log.info("{}", b.getContent()));
	}

	@Test
	@DisplayName("목록 조회")
	public void testListRnoNull() {
		List<Reply> list = replyMapper.list(1017L, null);
		list.forEach(b -> log.info("{}", b.getContent()));
	}
	
	@Test
	@DisplayName("글 수정 테스트")
	public void testUpdate() {
		Long rno = 4L;
		Reply reply = replyMapper.selectOne(rno);
		reply.setContent("수정하기");
		replyMapper.update(reply);
	}

	@Test
	@DisplayName("글 등록 테스트")
	public void testInsert() {
		Reply reply = Reply.builder().content("매퍼 테스트 등록 글").id("sae").bno(1017L).build();
		log.info("{}", reply);
		replyMapper.insert(reply);
		log.info("{}", reply);
	}

	@Test
	@DisplayName("글 삭제 테스트")
	public void testDelete() {
		Long rno = 5L;
		replyMapper.delete(rno);
	}
	
	
	
	
}
