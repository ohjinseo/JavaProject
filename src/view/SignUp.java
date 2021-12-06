package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ButtonGroup;
import java.awt.SystemColor;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SignUp extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	// 프레임 변수
	private Main mainFrame;
	private SearchBook searchBookFrame;

	/**
	 * Launch the application.
	 */

	String[] thirtyOne = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17",
			"18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
	String[] twentyNine = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17",
			"18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", };
	String[] thirty = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17",
			"18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30" };
	private JTextField userNameTextField;
	private JTextField userPhoneFirstTextField;
	private JTextField userPhoneSecondTextField;
	private JTextField userPhoneThirdTextField;
	private JTextField userEmailTextField;
	private JTextField userPasswordTextField;
	private JTextField userPassword2TextFiled;
	private JTextField userEmailDupleTextField;

	/**
	 * Create the frame.
	 */
	public SignUp() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});

		setBounds(100, 100, 732, 605);
		contentPane = new JPanel(); // 메인 프레임
		contentPane.setBackground(SystemColor.menu);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ImageIcon icon5 = new ImageIcon("D:\\\uC0C8 \uD3F4\uB354\\pngegg.png");
		Image img5 = icon5.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon changeIcon5 = new ImageIcon(img5);

		String[] years = new String[122];
		int k = 0;
		for (int i = 2021; i >= 1900; i--) {
			years[k++] = Integer.toString(i);
		}

		// 유저 정보 패널
		JPanel panel = new JPanel();
		panel.setBounds(12, 10, 694, 549);
		contentPane.add(panel);
		panel.setLayout(null);

		// 유저 이름 라벨
		JLabel userNameLabel = new JLabel("\uC774\uB984 ");
		userNameLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		userNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		userNameLabel.setBackground(Color.WHITE);
		userNameLabel.setBounds(12, 10, 81, 30);
		panel.add(userNameLabel);

		// 유저 이름 텍스트필드
		userNameTextField = new JTextField();
		userNameTextField.setText("\uC624\uC9C4\uC11C");
		userNameTextField.setHorizontalAlignment(SwingConstants.CENTER);
		userNameTextField.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 14));
		userNameTextField.setColumns(10);
		userNameTextField.setBounds(105, 12, 239, 30);
		panel.add(userNameTextField);

		// 유저 생일 라벨
		JLabel userBirthLabel = new JLabel("\uC0DD\uB144\uC6D4\uC77C");
		userBirthLabel.setHorizontalAlignment(SwingConstants.LEFT);
		userBirthLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
		userBirthLabel.setBackground(Color.WHITE);
		userBirthLabel.setBounds(12, 50, 81, 30);
		panel.add(userBirthLabel);

		// 생일 년도 콤보박스
		JComboBox yearComboBox = new JComboBox();
		yearComboBox.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		yearComboBox.setEditable(true);
		yearComboBox.setBounds(105, 52, 74, 30);
		panel.add(yearComboBox);

		// 생일 달 콤보박스
		JComboBox monthComboBox = new JComboBox();
		monthComboBox.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		monthComboBox.setBounds(188, 52, 74, 30);
		panel.add(monthComboBox);

		// 생일 일 콤보박스
		JComboBox dayComboBox = new JComboBox();
		dayComboBox.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		dayComboBox.setBounds(270, 52, 74, 30);
		panel.add(dayComboBox);

		// 유저 성별 라벨
		JLabel userSexLabel = new JLabel("\uC131\uBCC4");
		userSexLabel.setHorizontalAlignment(SwingConstants.LEFT);
		userSexLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
		userSexLabel.setBackground(Color.WHITE);
		userSexLabel.setBounds(12, 90, 81, 30);
		panel.add(userSexLabel);

		// 남자 라디오버튼
		JRadioButton manRadioButton = new JRadioButton("\uB0A8\uC790");
		manRadioButton.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		manRadioButton.setBounds(105, 95, 113, 23);
		panel.add(manRadioButton);

		// 여자 라디오버튼
		JRadioButton womanRadioButton = new JRadioButton("\uC5EC\uC790");
		womanRadioButton.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		womanRadioButton.setBounds(225, 95, 113, 23);
		panel.add(womanRadioButton);

		// 유저 전화번호 라벨
		JLabel userPhoneLabel = new JLabel("\uC804\uD654\uBC88\uD638");
		userPhoneLabel.setHorizontalAlignment(SwingConstants.LEFT);
		userPhoneLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
		userPhoneLabel.setBackground(Color.WHITE);
		userPhoneLabel.setBounds(12, 130, 81, 30);
		panel.add(userPhoneLabel);

		// 유저 전화번호 첫번째자리 텍스트필드
		userPhoneFirstTextField = new JTextField();
		userPhoneFirstTextField.setText("000");
		userPhoneFirstTextField.setHorizontalAlignment(SwingConstants.CENTER);
		userPhoneFirstTextField.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		userPhoneFirstTextField.setColumns(10);
		userPhoneFirstTextField.setBounds(105, 132, 74, 30);
		panel.add(userPhoneFirstTextField);

		// 유저 전화번호 두번째자리 텍스트필드
		userPhoneSecondTextField = new JTextField();
		userPhoneSecondTextField.setText("0000");
		userPhoneSecondTextField.setHorizontalAlignment(SwingConstants.CENTER);
		userPhoneSecondTextField.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		userPhoneSecondTextField.setColumns(10);
		userPhoneSecondTextField.setBounds(188, 132, 74, 30);
		panel.add(userPhoneSecondTextField);

		// 유저 전화번호 세번째자리 텍스트필드
		userPhoneThirdTextField = new JTextField();
		userPhoneThirdTextField.setText("0000");
		userPhoneThirdTextField.setHorizontalAlignment(SwingConstants.CENTER);
		userPhoneThirdTextField.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		userPhoneThirdTextField.setColumns(10);
		userPhoneThirdTextField.setBounds(270, 132, 74, 30);
		panel.add(userPhoneThirdTextField);

		// 유저 이메일 라벨
		JLabel userEmailLabel = new JLabel("\uC774\uBA54\uC77C");
		userEmailLabel.setHorizontalAlignment(SwingConstants.LEFT);
		userEmailLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
		userEmailLabel.setBackground(Color.WHITE);
		userEmailLabel.setBounds(12, 172, 81, 30);
		panel.add(userEmailLabel);

		// 유저 이메일 텍스트필드
		userEmailTextField = new JTextField();
		userEmailTextField.setText("abj123@gmail.com");
		userEmailTextField.setHorizontalAlignment(SwingConstants.CENTER);
		userEmailTextField.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		userEmailTextField.setColumns(10);
		userEmailTextField.setBounds(105, 174, 239, 30);
		panel.add(userEmailTextField);

		// 유저 비밀번호 라벨
		JLabel userPasswordLabel = new JLabel("\uBE44\uBC00\uBC88\uD638");
		userPasswordLabel.setHorizontalAlignment(SwingConstants.LEFT);
		userPasswordLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
		userPasswordLabel.setBackground(Color.WHITE);
		userPasswordLabel.setBounds(12, 251, 81, 30);
		panel.add(userPasswordLabel);

		// 유저 비밀번호 텍스트필드
		userPasswordTextField = new JTextField();
		userPasswordTextField.setText("*******");
		userPasswordTextField.setHorizontalAlignment(SwingConstants.CENTER);
		userPasswordTextField.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		userPasswordTextField.setColumns(10);
		userPasswordTextField.setBounds(105, 253, 239, 30);
		panel.add(userPasswordTextField);

		// 유저 비밀번호 중복 라벨
		JLabel userPasswordDupleLabel = new JLabel("\uC911\uBCF5\uD655\uC778");
		userPasswordDupleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		userPasswordDupleLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
		userPasswordDupleLabel.setBackground(Color.WHITE);
		userPasswordDupleLabel.setBounds(12, 291, 81, 30);
		panel.add(userPasswordDupleLabel);

		// 유저 비밀번호 중복 텍스트필드
		userPassword2TextFiled = new JTextField();
		userPassword2TextFiled.setText("*******");
		userPassword2TextFiled.setHorizontalAlignment(SwingConstants.CENTER);
		userPassword2TextFiled.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		userPassword2TextFiled.setColumns(10);
		userPassword2TextFiled.setBounds(105, 293, 239, 30);
		panel.add(userPassword2TextFiled);

		// 중복확인 버튼
		JButton dupleButton = new JButton("\uC911\uBCF5 \uD655\uC778");
		dupleButton.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		dupleButton.setBounds(361, 130, 91, 32);
		panel.add(dupleButton);

		//유저 이메일 중복코드 텍스트필드
		userEmailDupleTextField = new JTextField();
		userEmailDupleTextField.setText("(\uC911\uBCF5 \uCF54\uB4DC \uC790\uB9AC)");
		userEmailDupleTextField.setHorizontalAlignment(SwingConstants.CENTER);
		userEmailDupleTextField.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		userEmailDupleTextField.setColumns(10);
		userEmailDupleTextField.setBounds(105, 214, 239, 30);
		panel.add(userEmailDupleTextField);

		//유저 이미지 라벨
		JLabel userPhotoLabel = new JLabel("\uC0AC\uC9C4");
		userPhotoLabel.setHorizontalAlignment(SwingConstants.LEFT);
		userPhotoLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
		userPhotoLabel.setBackground(Color.WHITE);
		userPhotoLabel.setBounds(12, 331, 81, 30);
		panel.add(userPhotoLabel);

		//유저 이미지 라벨(실제 사진 들어가는 라벨)
		JLabel userPhotoShowLabel = new JLabel("\uC720\uC800 \uC0AC\uC9C4");
		userPhotoShowLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userPhotoShowLabel.setBackground(new Color(255, 250, 250));
		userPhotoShowLabel.setBounds(105, 333, 128, 132);
		panel.add(userPhotoShowLabel);

		//사진 불러오기 버튼
		JButton loadingButton = new JButton("\uBD88\uB7EC \uC624\uAE30...");
		loadingButton.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		loadingButton.setBounds(245, 333, 91, 32);
		panel.add(loadingButton);

		//회원가입 버튼
		JButton singUpButton = new JButton("\uD68C\uC6D0\uAC00\uC785");
		singUpButton.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		singUpButton.setBounds(105, 475, 91, 32);
		panel.add(singUpButton);
	}
}
