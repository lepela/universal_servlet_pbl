package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import domain.Category;

public interface CategoryMapper {
	@Select("select * from tbl_category tc where tc.status = 'ACTIVE' order by odr")
	List<Category> list();
}
