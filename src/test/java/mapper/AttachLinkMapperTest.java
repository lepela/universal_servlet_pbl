package mapper;

import org.junit.jupiter.api.Test;

import domain.AttachLink;
import domain.AttachLink.LinkType;
import lombok.extern.slf4j.Slf4j;
import util.MybatisUtil;

@Slf4j
public class AttachLinkMapperTest {
	AttachLinkMapper mapper = MybatisUtil.getSqlSession().getMapper(AttachLinkMapper.class);
	
	@Test
	public void testList() {
		AttachLink link = AttachLink.builder().linkType(LinkType.BOARD).lno(1022L).build();
		mapper.list(link).forEach(al -> log.info("{}", al));
	}
}
