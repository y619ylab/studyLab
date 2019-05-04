package tetris;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RankingDAO {

	//ランキングのスコアを降順で取得
	public List<RankingDTO> findRanking() {

		List<RankingDTO> listRanking = new ArrayList<RankingDTO>();
		String sql = "select * from ranking order by score desc";

		try (
				Connection con = DbConnector.getConnect();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {

			while (rs.next()) {
				//DTOオブジェクトの生成
				RankingDTO rd = new RankingDTO();
				//DTOオブジェクトにランキングのスコアをセット
				rd.setName(rs.getString(1));
				rd.setScore(rs.getInt(2));

				listRanking.add(rd);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listRanking;

	}

	//ゲーム結果のスコアをinsert
	public void insertRanking(String score) {

		try (
				Connection con = DbConnector.getConnect();) {

			PreparedStatement stmt = con.prepareStatement("insert into ranking(name, score) values('yuji', ?)");
			stmt.setString(1, score);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
