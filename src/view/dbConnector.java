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
	public dbConnector() {	//������
		
		try { // DB ����
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://jdb.deu.monster:60001/j20183026",
					"20183026", /*����� ���*/);
			System.out.println("DB ���� �Ϸ�");
			stmt = conn.createStatement();
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC ����̹� �ε� ����");
		} catch (SQLException e) {
			System.out.println("DBĿ���� �����ڿ��� SQL ���� ����");
		}
	}
	
	public ResultSet executeQuery(String sql) {
		ResultSet rs = null;
		try {
			ps = this.conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);	//last()�޼ҵ带 �������� �߰�
			rs = ps.executeQuery();
		} catch (SQLException e) {
			System.out.println("DBĿ���� executeQuery���� SQL ���� ����");
		}
		return rs;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
