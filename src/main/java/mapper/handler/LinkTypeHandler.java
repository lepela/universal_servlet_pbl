package mapper.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import domain.AttachLink.LinkType;
import domain.en.CategoryStatus;

@MappedTypes(LinkType.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class LinkTypeHandler extends BaseTypeHandler<CategoryStatus>{

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, CategoryStatus parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setString(i, parameter.name());
	}

	@Override
	public CategoryStatus getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return CategoryStatus.valueOf(rs.getString(columnName));
	}

	@Override
	public CategoryStatus getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return CategoryStatus.valueOf(rs.getString(columnIndex));
	}

	@Override
	public CategoryStatus getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		return CategoryStatus.valueOf(cs.getString(columnIndex));
	}
	
}
