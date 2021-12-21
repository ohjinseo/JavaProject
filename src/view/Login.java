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

	// ������ ����
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
					frame.setLocationRelativeTo(null); // �߾ӿ� ���
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
				loginTextField.requestFocus(); // �α��� â�� �����Ǹ� �ٷ� Id�� ��Ŀ��
			}
		});

		setResizable(false); // â ũ�� ����

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
		idLabel.setFont(new Font("���Ļ�浸��", Font.BOLD, 16));
		idLabel.setBounds(12, 10, 80, 36);

		panel.add(idLabel);

		JLabel passwordLabel = new JLabel("\uBE44\uBC00\uBC88\uD638");
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLabel.setFont(new Font("���Ļ�浸��", Font.BOLD, 16));
		passwordLabel.setBounds(12, 56, 80, 36);
		panel.add(passwordLabel);

		loginTextField = new JTextField();

		loginTextField.addFocusListener(new FocusAdapter() {

			@Override
			public void focusLost(FocusEvent e) {
				passwordTextField.grabFocus(); // Id ��Ŀ���� ��� �� �н������ ��Ŀ��
			}

		});
		loginTextField.setBackground(new Color(245, 245, 245));
		loginTextField.setBounds(104, 17, 267, 28);
		panel.add(loginTextField);
		loginTextField.setColumns(10);

		passwordTextField = new JTextField();
		passwordTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login_event(); // ���� �� �α��� �̺�Ʈ �Լ� ȣ��
			}
		});
		passwordTextField.setBackground(new Color(245, 245, 245));
		passwordTextField.setColumns(10);
		passwordTextField.setBounds(104, 67, 265, 28);
		panel.add(passwordTextField);
		loginTextField.getText();
		// �α��� ��ư
		JButton loginButton = new JButton("Login");
		loginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				login_event(); // �α��� �̺�Ʈ �Լ� ȣ��
			}

		});

		loginButton.setFont(new Font("���Ļ�浸��", Font.BOLD, 16));
		loginButton.setBounds(378, 17, 85, 79);
		panel.add(loginButton);

		JLabel loginLabel = new JLabel("\uB85C\uADF8\uC778");
		loginLabel.setForeground(SystemColor.desktop);
		loginLabel.setBackground(Color.WHITE);
		loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginLabel.setFont(new Font("���Ļ�浸��", Font.BOLD, 24));
		loginLabel.setBounds(154, 30, 172, 49);
		contentPane.add(loginLabel);

		JButton findIdButton = new JButton("\uC544\uC774\uB514 \uCC3E\uAE30");
		findIdButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				findIdFrame = new FindId();
				findIdFrame.setLocationRelativeTo(null); // ȭ���߾ӿ� ���
				findIdFrame.setVisible(true);

			}
		});
		findIdButton.setFont(new Font("���Ļ�浸��", Font.BOLD, 12));
		findIdButton.setBounds(36, 212, 113, 35);
		contentPane.add(findIdButton);

		JButton findPasswordButton = new JButton("\uBE44\uBC00\uBC88\uD638 \uCC3E\uAE30");
		findPasswordButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				findPasswordFrame = new FindPassword();
				findPasswordFrame.setLocationRelativeTo(null); // ȭ���߾ӿ� ���
				findPasswordFrame.setVisible(true);

			}
		});
		findPasswordButton.setFont(new Font("���Ļ�浸��", Font.BOLD, 12));
		findPasswordButton.setBounds(195, 212, 113, 35);
		contentPane.add(findPasswordButton);

		JButton signupButton = new JButton("\uD68C\uC6D0\uAC00\uC785");
		signupButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				signUpFrame = new SignUp();
				signUpFrame.setLocationRelativeTo(null); // ȭ���߾ӿ� ���
				signUpFrame.setVisible(true);

			}
		});
		signupButton.setFont(new Font("���Ļ�浸��", Font.BOLD, 12));
		signupButton.setBounds(357, 212, 113, 35);
		contentPane.add(signupButton);
	}

	// �α��� ���� �̺�Ʈ �Լ�
	public void login_event() {
		int flag = 0; // 0�̸� �α��� ����, 1�̸� �α��� ����
		String user_phone = "";
		Boolean manager = false;
		try { // DB ����
			ResultSet rs = dbConn
					.executeQuery("select USER_PW, USER_MAIL, USER_PHONE, USER_MANAGER, USER_OUT_DATE from USER"); 
			// ������ ���
			while (rs.next()) {
				if (loginTextField.getText().equals(rs.getString("USER_MAIL"))) // ���̵� â�� �Է��� ���ϰ� ��ġ�ϴ� ������ DB��
																				// ������
					if (passwordTextField.getText().equals(rs.getString("USER_PW"))) // �н����� â�� �Է��� �н������ ������
																						// �н����尡 ��ġ�ϸ�
					{
						if (rs.getString("USER_OUT_DATE") == null) {	//Ż��ȸ���� �ƴϸ�
							user_phone = rs.getString("USER_PHONE");
							manager = rs.getBoolean("USER_MANAGER");
							flag = 1; // �α��� ����
							break;
						} else {	//Ż��ȸ���̸�
							JOptionPane.showMessageDialog(null, "Ż��� ȸ���Դϴ�.", "Ż�� ȸ��", JOptionPane.ERROR_MESSAGE);
							break;
						}
					}
			}
		} catch (SQLException e2) {
			System.out.println("�α���ȭ�鿡�� SQL ���� ����");
		}
		if (flag == 1) { // �α����� ���� �Ǿ�����
			System.out.println("�α��� ����");
			mainFrame = new Main(user_phone, manager); // ���� ������ ��ü ����
			mainFrame.setVisible(true); // ���� �������� ȣ��

			setVisible(false); // �α��� ������ ����
		} else {
			System.out.println("�α��� ����");
		}
	}
}
