package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
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

import java.sql.*;
import java.util.Properties;
import javax.swing.JOptionPane;

public class FindId extends JFrame {
	dbConnector dbConn = new dbConnector();
	private JPanel contentPane;
	
	//프레임 변수
	private Main mainFrame;
	private JTextField userNameTextField;
	private JTextField userPhoneFirstTextField;
	private JTextField userPhoneSecondTextField;
	private JTextField userPhoneThirdTextField;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public FindId() {
		setTitle("\uB3C4\uC11C \uAD00\uB9AC \uD504\uB85C\uADF8\uB7A8 - \uC544\uC774\uB514 \uCC3E\uAE30");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		setResizable(false);	//창 크기 고정
		setBounds(100, 100, 510, 301);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//회원 정보 패널
		JPanel panel = new JPanel();
		panel.setBounds(23, 33, 442, 142);
		contentPane.add(panel);
		panel.setLayout(null);
		
		//유저 이름 라벨
		JLabel userNameLabel = new JLabel("\uC774\uB984");
		userNameLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 15));
		userNameLabel.setBounds(12, 10, 50, 15);
		panel.add(userNameLabel);
		
		//유저 이름 텍스트필드
		userNameTextField = new JTextField();
		userNameTextField.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		userNameTextField.setBounds(108, 8, 312, 21);
		panel.add(userNameTextField);
		userNameTextField.setColumns(10);
		
		//유저 전화번호 라벨
		JLabel userPhoneLabel = new JLabel("\uC804\uD654\uBC88\uD638");
		userPhoneLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 15));
		userPhoneLabel.setBounds(12, 56, 73, 15);
		panel.add(userPhoneLabel);
		
		//유저 전화번호 첫번째자리 텍스트필드
		userPhoneFirstTextField = new JTextField();
		userPhoneFirstTextField.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		userPhoneFirstTextField.setColumns(10);
		userPhoneFirstTextField.setBounds(108, 54, 96, 21);
		panel.add(userPhoneFirstTextField);
		
		//유저 전화번호 두번쨰자리 텍스트필드
		userPhoneSecondTextField = new JTextField();
		userPhoneSecondTextField.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		userPhoneSecondTextField.setColumns(10);
		userPhoneSecondTextField.setBounds(216, 54, 96, 21);
		panel.add(userPhoneSecondTextField);
		
		//유저 전화번호 세번쨰자리 텍스트필드
		userPhoneThirdTextField = new JTextField();
		userPhoneThirdTextField.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		userPhoneThirdTextField.setColumns(10);
		userPhoneThirdTextField.setBounds(324, 54, 96, 21);
		panel.add(userPhoneThirdTextField);
		
		//유저 생년월일 라벨
		JLabel userBirthLabel = new JLabel("\uC0DD\uB144\uC6D4\uC77C");
		userBirthLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 15));
		userBirthLabel.setBounds(12, 105, 73, 15);
		panel.add(userBirthLabel);
		
		//유저 년도 콤보박스
		JComboBox yearComboBox = new JComboBox();
		String[] years = new String[122];	//문자열 배열 years
		int k = 0;	
		for(int i = 2021; i >= 1900; i--) {	//2021부터 1900 까지 배열에 저장
			years[k++] = Integer.toString(i);
		}
		yearComboBox.setModel(new DefaultComboBoxModel(years));	//year을 콤보박스의 리스트로 설정
		yearComboBox.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		yearComboBox.setBounds(108, 99, 96, 21);
		panel.add(yearComboBox);
		
		//유저 달 콤보박스
		JComboBox monthComboBox = new JComboBox();
		monthComboBox.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		monthComboBox.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		monthComboBox.setBounds(216, 99, 96, 21);
		panel.add(monthComboBox);
		
		//유저 일 콤보박스
		JComboBox dayComboBox = new JComboBox();
		dayComboBox.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		dayComboBox.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		dayComboBox.setBounds(324, 99, 96, 21);
		panel.add(dayComboBox);
		
		//아이디 찾기 버튼
		JButton findIdButton = new JButton("\uC544\uC774\uB514 \uCC3E\uAE30");
		findIdButton.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 15));
		findIdButton.setBounds(23, 199, 119, 35);
		contentPane.add(findIdButton);
		findIdButton.addMouseListener(new MouseAdapter() {	//아이디 찾기 버튼을 누르면 호출되는 메소드
			@Override
			public void mouseClicked(MouseEvent e) {
				int flag = 0; // 1이면 찾기 성공, 0이면 찾지못함
				String id = "";	//아이디를 찾으면 저장할 변수
				try { // DB 접근
					
					ResultSet rs = dbConn.executeQuery("select USER_NAME, USER_PHONE, USER_BIRTH, USER_MAIL from USER"); // USER 테이블에서 MAIL과 PW 검색
					//입력받은 전화번호를 연결하여 phone_number 생성
					String phone_number = userPhoneFirstTextField.getText().concat(userPhoneSecondTextField.getText()).concat(userPhoneThirdTextField.getText());	
					String year = yearComboBox.getSelectedItem().toString();	//입력한 년도 값
					String month = monthComboBox.getSelectedItem().toString();	//입력한 월 값
					String day = dayComboBox.getSelectedItem().toString();	//입력한 일 값
					String birth = year.concat("-").concat(month).concat("-").concat(day);	//"년도-월-일" 형태로 birth 생성
					//쿼리문 결과
					while (rs.next()) {
						if (userNameTextField.getText().equals(rs.getString("USER_NAME"))) // 이름 창에 입력한 이름과 일치하는 이름이 DB에 있으면
							if (phone_number.equals(rs.getString("USER_PHONE"))) // 전화번호 창에 입력한 전화번호와 이름의 전화번호가 일치하면
								if(birth.equals(rs.getString("USER_BIRTH")))	// 생년월일 창에 입력한 생년월일과 이름, 전화번호가 일치하면
							{
								flag = 1; // 찾기 성공
								id = rs.getString("USER_MAIL");	//찾은 아이디 저장
								break;
							}
					}
				}catch (SQLException e2) {
					System.out.println("SQL 실행 에러");
				}
				if (flag == 1) {	//찾았으면 아이디를 메시지로 출력
					JOptionPane.showMessageDialog(null,"당신의 아이디는 "+id+" 입니다.","아이디 찾기 성공!",JOptionPane.INFORMATION_MESSAGE);
				}
				else //찾지 못했으면 에러 메시지 출력
					JOptionPane.showMessageDialog(null,"아이디가 존재하지 않습니다.","아이디 찾기 실패",JOptionPane.ERROR_MESSAGE);
			}

		});
	}
}
