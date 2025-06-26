package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Member;
import util.HikariCPUtil;

public class MemberDao {
	public Member findById(String id) {
		try {
			Connection conn = HikariCPUtil.getDataSource().getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from tbl_member where id = ?");
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return Member.builder().id(rs.getString("id")).build();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
