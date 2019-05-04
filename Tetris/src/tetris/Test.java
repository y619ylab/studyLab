package tetris;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {

	public static void main(String[] args) {

		String sql = "select * from ranking";

		try (
				Connection con = DbConnector.getConnect();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {

			while (rs.next()) {
				System.out.println(rs.getString(1));
				System.out.println(rs.getInt(2));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
