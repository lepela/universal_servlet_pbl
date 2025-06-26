package service;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;

import dao.MemberDao;
import domain.Member;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import mapper.MemberMapper;
import util.MybatisUtil;
import util.PasswordEncoder;

@Slf4j
public class MemberService {
	public int register(Member member) {
		try (SqlSession session = MybatisUtil.getSqlSession()) {
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			member.setPw(PasswordEncoder.encode(member.getPw()));  
			return mapper.insert(member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public Member findById(String id) {
		try (SqlSession session = MybatisUtil.getSqlSession()) {
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			return mapper.findByIdMap(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public boolean login(String id, String pw) {
		Member member = findById(id);
		if(member == null) {
			return false;
		}
		return PasswordEncoder.matches(pw, member.getPw()) ;
	}
	
	
	public static void main(String[] args) {
		MemberService memberService = new MemberService();
//		log.info("{}", memberService.findById("sae"));
//		log.info("{} 핫하 {}", 10, 20);
//		memberService.register(Member.builder().id("sae").pw("1234").name("새똥이").build());
		
//		log.info("{}", memberService.login("sae", "1234"));
//		log.error("{}", memberService.login("sae", "12345"));
		log.info(System.getProperty("user.dir"));
		;
	}
}
