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

import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.Properties;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Login extends JFrame {
	dbConnector dbConn = new dbConnector();

	private JPanel contentPane;
	private JTextField loginTextField;
	private JTextField passwordTextField;

	// 프레임 변수
	private Main mainFrame;
	private FindId findIdFrame;
	private FindPassword findPasswordFrame;
	private SignUp signUpFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setLocationRelativeTo(null); // 중앙에 출력
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				loginTextField.requestFocus(); // 로그인 창이 생성되면 바로 Id로 포커싱
			}
		});

		setResizable(false); // 창 크기 고정

		setTitle("\uB3C4\uC11C \uAD00\uB9AC \uD504\uB85C\uADF8\uB7A8 - \uB85C\uADF8\uC778\uBA54\uC778");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 301);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(SystemColor.menu);
		panel.setBounds(12, 89, 475, 113);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel idLabel = new JLabel("\uC544\uC774\uB514");
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);
		idLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 16));
		idLabel.setBounds(12, 10, 80, 36);

		panel.add(idLabel);

		JLabel passwordLabel = new JLabel("\uBE44\uBC00\uBC88\uD638");
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 16));
		passwordLabel.setBounds(12, 56, 80, 36);
		panel.add(passwordLabel);

		loginTextField = new JTextField();

		loginTextField.addFocusListener(new FocusAdapter() {

			@Override
			public void focusLost(FocusEvent e) {
				passwordTextField.grabFocus(); // Id 포커스를 벗어날 시 패스워드로 포커스
			}

		});
		loginTextField.setBackground(new Color(245, 245, 245));
		loginTextField.setBounds(104, 17, 267, 28);
		panel.add(loginTextField);
		loginTextField.setColumns(10);

		passwordTextField = new JTextField();
		passwordTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login_event(); // 엔터 시 로그인 이벤트 함수 호출
			}
		});
		passwordTextField.setBackground(new Color(245, 245, 245));
		passwordTextField.setColumns(10);
		passwordTextField.setBounds(104, 67, 265, 28);
		panel.add(passwordTextField);
		loginTextField.getText();
		// 로그인 버튼
		JButton loginButton = new JButton("Login");
		loginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				login_event(); // 로그인 이벤트 함수 호출
			}

		});

		loginButton.setFont(new Font("한컴산뜻돋움", Font.BOLD, 16));
		loginButton.setBounds(378, 17, 85, 79);
		panel.add(loginButton);

		JLabel loginLabel = new JLabel("\uB85C\uADF8\uC778");
		loginLabel.setForeground(SystemColor.desktop);
		loginLabel.setBackground(Color.WHITE);
		loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 24));
		loginLabel.setBounds(154, 30, 172, 49);
		contentPane.add(loginLabel);

		JButton findIdButton = new JButton("\uC544\uC774\uB514 \uCC3E\uAE30");
		findIdButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				findIdFrame = new FindId();
				findIdFrame.setLocationRelativeTo(null); // 화면중앙에 출력
				findIdFrame.setVisible(true);

			}
		});
		findIdButton.setFont(new Font("한컴산뜻돋움", Font.BOLD, 12));
		findIdButton.setBounds(36, 212, 113, 35);
		contentPane.add(findIdButton);

		JButton findPasswordButton = new JButton("\uBE44\uBC00\uBC88\uD638 \uCC3E\uAE30");
		findPasswordButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				findPasswordFrame = new FindPassword();
				findPasswordFrame.setLocationRelativeTo(null); // 화면중앙에 출력
				findPasswordFrame.setVisible(true);

			}
		});
		findPasswordButton.setFont(new Font("한컴산뜻돋움", Font.BOLD, 12));
		findPasswordButton.setBounds(195, 212, 113, 35);
		contentPane.add(findPasswordButton);

		JButton signupButton = new JButton("\uD68C\uC6D0\uAC00\uC785");
		signupButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				signUpFrame = new SignUp();
				signUpFrame.setLocationRelativeTo(null); // 화면중앙에 출력
				signUpFrame.setVisible(true);

			}
		});
		signupButton.setFont(new Font("한컴산뜻돋움", Font.BOLD, 12));
		signupButton.setBounds(357, 212, 113, 35);
		contentPane.add(signupButton);
	}

	// 로그인 접근 이벤트 함수
	public void login_event() {
		int flag = 0; // 0이면 로그인 수락, 1이면 로그인 거절
		String user_phone = "";
		Boolean manager = false;
		try { // DB 접근
			ResultSet rs = dbConn
					.executeQuery("select USER_PW, USER_MAIL, USER_PHONE, USER_MANAGER, USER_OUT_DATE from USER"); 
			// 쿼리문 결과
			while (rs.next()) {
				if (loginTextField.getText().equals(rs.getString("USER_MAIL"))) // 아이디 창에 입력한 메일과 일치하는 메일이 DB에
																				// 있으면
					if (passwordTextField.getText().equals(rs.getString("USER_PW"))) // 패스워드 창에 입력한 패스워드와 메일의
																						// 패스워드가 일치하면
					{
						if (rs.getString("USER_OUT_DATE") == null) {	//탈퇴회원이 아니면
							user_phone = rs.getString("USER_PHONE");
							manager = rs.getBoolean("USER_MANAGER");
							flag = 1; // 로그인 수락
							break;
						} else {	//탈퇴회원이면
							JOptionPane.showMessageDialog(null, "탈퇴된 회원입니다.", "탈퇴 회원", JOptionPane.ERROR_MESSAGE);
							break;
						}
					}
			}
		} catch (SQLException e2) {
			System.out.println("로그인화면에서 SQL 실행 에러");
		}
		if (flag == 1) { // 로그인이 수락 되었으면
			System.out.println("로그인 성공");
			mainFrame = new Main(user_phone, manager); // 메인 프레임 객체 생성
			mainFrame.setVisible(true); // 메인 프레임을 호출

			setVisible(false); // 로그인 프레임 닫음
		} else {
			System.out.println("로그인 실패");
		}
	}
}
