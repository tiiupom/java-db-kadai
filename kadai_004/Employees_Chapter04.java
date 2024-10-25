package text.kadai_004;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Employees_Chapter04 {

	public static void main(String[] args) {
	
		Connection con = null;
		Statement statement = null;
		
		try {
			// データベースに接続
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost/challenge_java",
					"root",
					"Muchio5795!"
			);
			
			System.out.println("データベース接続成功");
			
			
			// SQLクエリを準備
			statement = con.createStatement();
			String sql = """
						CREATE TABLE employees (
						id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
						name VARCHAR(60) NOT NULL,
						email VARCHAR(255) NOT NULL,
						age INT(11),
						address VARCHAR(255)
						);
					""";
			
			// SQLクエリを実行
			int rowCnt = statement.executeUpdate(sql);
			System.out.println("社員テーブルを作成しました:更新レコード数=" + rowCnt);
			
		} catch (SQLException e) {
			System.out.println("e.getmessage");
			// 例外が発生しても必ず実行する処理 finally文
		} finally {
			if(con != null) {
				try { statement.close(); } catch(SQLException ignore) {}
			}
			if(statement != null) {
				try { con.close(); } catch(SQLException ignore) {}
			}
			}

	}

}
