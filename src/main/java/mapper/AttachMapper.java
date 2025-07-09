package mapper;

import java.util.List;

import domain.Attach;

public interface AttachMapper {
	void insert(Attach attach);
	Attach selectOne(String uuid);
	void delete(String uuid);
	
	List<Attach> selectYesterdayList();
}
