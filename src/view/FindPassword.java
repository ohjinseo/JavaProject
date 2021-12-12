package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FindPassword extends JFrame {
	dbConnector dbConn = new dbConnector();
	private JPanel contentPane;
	
	//프레임 변수
	private Main mainFrame;
	private JTextField EmailTextField;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public FindPassword() {
		setTitle("\uB3C4\uC11C \uAD00\uB9AC \uD504\uB85C\uADF8\uB7A8 - \uBE44\uBC00\uBC88\uD638 \uCC3E\uAE30");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		setResizable(false);	//창 크기 고정
		setBounds(100, 100, 510, 194);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//패스워드 찾기 패널
		JPanel panel = new JPanel();
		panel.setBounds(12, 10, 472, 139);
		contentPane.add(panel);
		panel.setLayout(null);
		
		//패스워드 찾기 버튼
		JButton findPasswordButton = new JButton("\uBE44\uBC00\uBC88\uD638 \uCC3E\uAE30");
		findPasswordButton.setBounds(120, 80, 150, 35);
		panel.add(findPasswordButton);
		findPasswordButton.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 15));
		findPasswordButton.addMouseListener(new MouseAdapter() {	//비밀번호 찾기 버튼을 누르면 호출되는 메소드
			@Override
			public void mouseClicked(MouseEvent e) {
				int flag = 0; // 1이면 찾기 성공, 0이면 찾지못함
				String pw = "";	//비밀번호를 찾으면 저장할 변수
				try { // DB 접근

					ResultSet rs = dbConn.executeQuery("select USER_MAIL, USER_PW from USER"); // USER 테이블에서 MAIL과 PW 검색
					
					//쿼리문 결과
					while (rs.next()) {
						if (EmailTextField.getText().equals(rs.getString("USER_MAIL"))) // 이름 창에 입력한 이름과 일치하는 이름이 DB에 있으면
							{
								flag = 1; // 찾기 성공
								pw = rs.getString("USER_PW");	//찾은 비밀번호 저장
								break;
							}
					}
				}catch (SQLException e2) {
					System.out.println("SQL 실행 에러");
				}
				if (flag == 1) {	//찾았으면 아이디를 메시지로 출력
					JOptionPane.showMessageDialog(null,"당신의 비밀번호는 "+pw+" 입니다.","비밀번호 찾기 성공!",JOptionPane.INFORMATION_MESSAGE);
				}
				else //찾지 못했으면 에러 메시지 출력
					JOptionPane.showMessageDialog(null,"이메일이 존재하지 않습니다.","비밀번호 찾기 실패",JOptionPane.ERROR_MESSAGE);
			}
		});
	
		
		//이메일 텍스트필드
		EmailTextField = new JTextField();
		EmailTextField.setBounds(119, 21, 312, 21);
		panel.add(EmailTextField);
		EmailTextField.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		EmailTextField.setColumns(10);
		
		//이메일 라벨
		JLabel userEmailLabel = new JLabel("\uC774\uBA54\uC77C");
		userEmailLabel.setBounds(23, 23, 50, 15);
		panel.add(userEmailLabel);
		userEmailLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 15));
	}
}
