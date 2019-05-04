package tetris;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {

	public static Connection getConnect() throws SQLException {

		String url = "jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8&serverTimezone=JST&useSSL=false";
		String user = "root";
		String pass = "kennkenn";

		Connection con = DriverManager.getConnection(url, user, pass);

		return con;
	}
}


