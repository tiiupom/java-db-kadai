// エラーデバッグ

package text.kadai_007;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Posts_Chapter07 {

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
			
			// SQLクエリを準備（データ追加）
			statement = con.createStatement();
			String sql = """
					INSERT INTO posts (user_id,posted_at,post_content,likes) VALUES
					(1003,'2023-02-08','昨日の夜は徹夜でした・・',13),
					(1002,'2023-02-08','お疲れ様です!',12),
					(1003,'2023-02-09','今日も頑張ります!',18),
					(1001,'2023-02-09','無理は禁物ですよ!',17),
					(1002,'2023-02-10','明日から連休ですね!',20);
					""";
			
			// SQLクエリを実行（DBMSに送信）
			System.out.println("レコード追加を実行します");
			int rowCnt = statement.executeUpdate(sql);	// 「rowCot」更新されたレコードが何件あるかを取得
			System.out.println(rowCnt + "件のレコードが追加されました");
			
			
			// SQLクエリを準備（検索）
			sql= "SELECT * FROM posts WHERE user_id = 1002;";
			
			// SQLクエリを実行
			ResultSet result = statement.executeQuery(sql);	// 「Query」検索したレコード情報を取得
			System.out.println("ユーザーIDが1002のレコードを検索しました");
			
			// SQLクエリの実行結果を抽出、表示
			while(result.next()) {
				Date date = result.getDate("posted_at");
				String content = result.getString("post_content");
				int likes = result.getInt("likes");
				System.out.println(result.getRow() + "件目：投稿日時=" + date 
									+ "/投稿内容=" + content + "/いいね数=" + likes);
			}
		} catch(SQLException e) {
			System.out.println("エラー発生:" + e.getMessage());
		} finally {
			if(statement != null ) {
				try { statement.close(); } catch(SQLException igonre) {}
			}
			if(con != null) {
				try { con.close(); } catch(SQLException ignore) {}
			}
		}
		
	}

}
