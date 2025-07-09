package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import domain.AttachLink;

public interface AttachLinkMapper {
	
	@Insert("insert into tbl_attach_link (uuid, link_type, lno) values (#{uuid}, #{linkType}, #{lno})")
	void insert(AttachLink link);
	@Select("select * from tbl_attach_link where link_type = #{linkType} and lno = #{lno}")
	List<AttachLink> list(AttachLink link);
	@Delete("delete from tbl_attach_link where link_type = #{linkType} and lno = #{lno}")
	void delete(AttachLink link);
}
