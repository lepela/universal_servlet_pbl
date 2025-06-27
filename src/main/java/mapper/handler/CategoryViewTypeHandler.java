package mapper.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import domain.en.CategoryViewType;

@MappedTypes(CategoryViewType.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class CategoryViewTypeHandler extends BaseTypeHandler<CategoryViewType>{

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, CategoryViewType parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setString(i, parameter.name());
	}

	@Override
	public CategoryViewType getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return CategoryViewType.valueOf(rs.getString(columnName));
	}

	@Override
	public CategoryViewType getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return CategoryViewType.valueOf(rs.getString(columnIndex));
	}

	@Override
	public CategoryViewType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		return CategoryViewType.valueOf(cs.getString(columnIndex));
	}
	
}
