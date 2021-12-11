package view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class dbConnector {
	Connection conn ;
	Statement stmt ;
	PreparedStatement ps;
	public dbConnector() {	//생성자
		
		try { // DB 접근
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://jdb.deu.monster:60001/j20183026",
					"20183026", /*여기다 비번*/);
			System.out.println("DB 연결 완료");
			stmt = conn.createStatement();
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로드 에러");
		} catch (SQLException e) {
			System.out.println("DB커넥터 생성자에서 SQL 실행 에러");
		}
	}
	
	public ResultSet executeQuery(String sql) {
		ResultSet rs = null;
		try {
			ps = this.conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);	//last()메소드를 쓰기위해 추가
			rs = ps.executeQuery();
		} catch (SQLException e) {
			System.out.println("DB커넥터 executeQuery에서 SQL 실행 에러");
		}
		return rs;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
