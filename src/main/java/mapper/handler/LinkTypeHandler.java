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

@MappedTypes(LinkType.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class LinkTypeHandler extends BaseTypeHandler<LinkType>{

	@Override
       public void setNonNullParameter(PreparedStatement ps, int i, LinkType parameter, JdbcType jdbcType)
                       throws SQLException {
               ps.setString(i, parameter.name());
       }

       @Override
       public LinkType getNullableResult(ResultSet rs, String columnName) throws SQLException {
               String value = rs.getString(columnName);
               return value != null ? LinkType.valueOf(value) : null;
       }

       @Override
       public LinkType getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
               String value = rs.getString(columnIndex);
               return value != null ? LinkType.valueOf(value) : null;
       }

       @Override
       public LinkType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
               String value = cs.getString(columnIndex);
               return value != null ? LinkType.valueOf(value) : null;
       }
	
}
