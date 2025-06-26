package mapper;

import java.util.List;

import domain.Member;

public interface MemberMapper {
	List<Member> select();
	
	Member findByNo(Long no);
	Member findById(String id);
	Member findByIdMap(String id);
	
	int insert(Member member);
	
	int delete(Long no);
	
	int update(Member member);
}
