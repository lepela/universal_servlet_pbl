package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import domain.Review;

public interface ReviewMapper {
	@Select("select * from tbl_review")
	List<Review> select();
	
	@Select("select * from tbl_review where rno = #{rno}")
	Review findBy(Long rno);
	
	@Insert("insert into tbl_review(content, rating, writer, pno) values (#{content}, #{rating}, #{writer}, #{pno})")
	int insert(Review review);
	
	@Delete("delete from tbl_review where rno = #{rno}")
	int delete(Long rno);
	
	@Update("update tbl_review set content = #{content}, rating = #{rating}, writer = #{writer} where rno = #{rno}")
	int update(Review review);
}
