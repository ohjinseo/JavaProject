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

public class UserInfo extends JFrame {

	private JPanel contentPane;
	private JTextField userNameTextField;
	private JTable table;
	private JTextField userPhoneFirstTextField;
	private JTextField userEmailTextField;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField userPhoneSecondTextField;
	private JTextField userPhoneThirdTextField;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInfo frame = new UserInfo();
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
	public UserInfo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 881, 694);
		contentPane = new JPanel();	//메인 프레임
		contentPane.setBackground(new Color(245, 245, 245));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//메뉴바
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 865, 47);
		menuBar.setBorderPainted(false);
		menuBar.setBackground(new Color(51, 51, 51));
		contentPane.add(menuBar);
		
		//홈아이콘 메뉴
		JMenu homeIconMenu = new JMenu(" \uD648 ");
		homeIconMenu.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		homeIconMenu.setForeground(new Color(255, 255, 255));
		ImageIcon icon5 = new ImageIcon("D:\\\uC0C8 \uD3F4\uB354\\pngegg.png");
		Image img5 = icon5.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon changeIcon5 = new ImageIcon(img5);
		homeIconMenu.setIcon(changeIcon5);
		menuBar.add(homeIconMenu);
		
		//도서 찾기 메뉴
		JMenu findBookMenu = new JMenu("\uB3C4\uC11C\uCC3E\uAE30");	//메뉴 - 도서찾기
		findBookMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		findBookMenu.setForeground(new Color(255, 255, 255));
		findBookMenu.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		menuBar.add(findBookMenu);
		
		//회원 정보 메뉴
		JMenu userInfoMenu = new JMenu("\uD68C\uC6D0\uC815\uBCF4");
		userInfoMenu.setForeground(new Color(255, 255, 255));
		userInfoMenu.setBackground(new Color(230, 230, 250));
		userInfoMenu.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		menuBar.add(userInfoMenu);
		
		//회원 사진 라벨
		JLabel userPictureLabel = new JLabel("\uC720\uC800 \uC0AC\uC9C4");
		userPictureLabel.setBackground(new Color(255, 250, 250));
		userPictureLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userPictureLabel.setBounds(22, 71, 186, 225);
		contentPane.add(userPictureLabel);
		
		//유저 정보 패널
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(128, 128, 128), 2));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(233, 57, 606, 343);
		contentPane.add(panel);
		panel.setLayout(null);
		
		//유저 이름 라벨
		JLabel userNameLabel = new JLabel("\uC774\uB984 ");
		userNameLabel.setFont(new Font("\uD55C\uCEF4\uC0B0\uB73B\uB3CB\uC6C0", userNameLabel.getFont().getStyle() | Font.BOLD, userNameLabel.getFont().getSize() + 3));
		userNameLabel.setHorizontalAlignment(SwingConstants.LEFT);		
		userNameLabel.setBackground(new Color(255, 255, 255));
		userNameLabel.setBounds(20, 10, 81, 30);
		panel.add(userNameLabel);
		
		//유저 이름 텍스트필드
		userNameTextField = new JTextField();
		userNameTextField.setEditable(false);
		userNameTextField.setHorizontalAlignment(SwingConstants.CENTER);
		userNameTextField.setFont(new Font("\uD55C\uCEF4\uC0B0\uB73B\uB3CB\uC6C0", userNameTextField.getFont().getStyle() | Font.BOLD, userNameTextField.getFont().getSize() + 3));
		userNameTextField.setText("\uC624\uC9C4\uC11C");
		userNameTextField.setBounds(113, 12, 239, 30);
		panel.add(userNameTextField);
		userNameTextField.setColumns(10);
		
		//유저 생일 라벨
		JLabel userBirthLabel = new JLabel("\uC0DD\uB144\uC6D4\uC77C");
		userBirthLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
		userBirthLabel.setHorizontalAlignment(SwingConstants.LEFT);
		userBirthLabel.setBackground(Color.WHITE);
		userBirthLabel.setBounds(20, 50, 81, 30);
		panel.add(userBirthLabel);
		
		// 유저 년도 콤보박스
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"2021", "2020"}));
		comboBox.setBounds(113, 52, 74, 30);
		panel.add(comboBox);
		
		//유저 월 콤보박스
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		comboBox_1.setBounds(196, 52, 74, 30);
		panel.add(comboBox_1);
		
		//유저 일 콤보박스
		JComboBox comboBox_1_1 = new JComboBox();
		comboBox_1_1.setBounds(278, 52, 74, 30);
		panel.add(comboBox_1_1);
		
		//유저 성별 라벨
		JLabel userSexLabel = new JLabel("\uC131\uBCC4");
		userSexLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
		userSexLabel.setHorizontalAlignment(SwingConstants.LEFT);
		userSexLabel.setBackground(Color.WHITE);
		userSexLabel.setBounds(20, 90, 81, 30);
		panel.add(userSexLabel);
		
		//남자 라디오버튼
		JRadioButton ManRadioButton = new JRadioButton("\uB0A8\uC790");
		buttonGroup.add(ManRadioButton);
		ManRadioButton.setBounds(113, 95, 58, 23);
		panel.add(ManRadioButton);
		
		//여자 라디오버튼
		JRadioButton WomanRadioButton = new JRadioButton("\uC5EC\uC790");
		buttonGroup.add(WomanRadioButton);
		WomanRadioButton.setBounds(233, 95, 58, 23);
		panel.add(WomanRadioButton);
		
		//유저 전화번호 라벨
		JLabel userPhoneLabel = new JLabel("\uC804\uD654\uBC88\uD638");
		userPhoneLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
		userPhoneLabel.setHorizontalAlignment(SwingConstants.LEFT);
		userPhoneLabel.setBackground(Color.WHITE);
		userPhoneLabel.setBounds(20, 130, 81, 30);
		panel.add(userPhoneLabel);
		
		//유저 전화번호 첫번째자리 텍스트필드
		userPhoneFirstTextField = new JTextField();
		userPhoneFirstTextField.setText("000");
		userPhoneFirstTextField.setHorizontalAlignment(SwingConstants.CENTER);
		userPhoneFirstTextField.setEditable(false);
		userPhoneFirstTextField.setColumns(10);
		userPhoneFirstTextField.setBounds(113, 132, 74, 30);
		panel.add(userPhoneFirstTextField);
		
		//유저 전화번호 두번째자리 텍스트필드
		userPhoneSecondTextField = new JTextField();
		userPhoneSecondTextField.setText("0000");
		userPhoneSecondTextField.setHorizontalAlignment(SwingConstants.CENTER);
		userPhoneSecondTextField.setEditable(false);
		userPhoneSecondTextField.setColumns(10);
		userPhoneSecondTextField.setBounds(196, 132, 74, 30);
		panel.add(userPhoneSecondTextField);
		
		//유저 전화번후 세번째자리 텍스트필드
		userPhoneThirdTextField = new JTextField();
		userPhoneThirdTextField.setText("0000");
		userPhoneThirdTextField.setHorizontalAlignment(SwingConstants.CENTER);
		userPhoneThirdTextField.setEditable(false);
		userPhoneThirdTextField.setColumns(10);
		userPhoneThirdTextField.setBounds(278, 132, 74, 30);
		panel.add(userPhoneThirdTextField);
		
		//유저 이메일 라벨
		JLabel userEmailLabel = new JLabel("\uC774\uBA54\uC77C");
		userEmailLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
		userEmailLabel.setHorizontalAlignment(SwingConstants.LEFT);
		userEmailLabel.setBackground(Color.WHITE);
		userEmailLabel.setBounds(20, 172, 81, 30);
		panel.add(userEmailLabel);
		
		//유저 이메일 텍스트필드
		userEmailTextField = new JTextField();
		userEmailTextField.setText("abj123@gmail.com");
		userEmailTextField.setHorizontalAlignment(SwingConstants.CENTER);
		userEmailTextField.setEditable(false);
		userEmailTextField.setColumns(10);
		userEmailTextField.setBounds(113, 174, 239, 30);
		panel.add(userEmailTextField);
		
		//유저 비밀번호 라벨
		JLabel userPasswordLabel = new JLabel("\uBE44\uBC00\uBC88\uD638");
		userPasswordLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
		userPasswordLabel.setHorizontalAlignment(SwingConstants.LEFT);
		userPasswordLabel.setBackground(Color.WHITE);
		userPasswordLabel.setBounds(20, 212, 81, 30);
		panel.add(userPasswordLabel);
		
		//
		textField_5 = new JTextField();
		textField_5.setText("*******");
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBounds(113, 214, 239, 30);
		panel.add(textField_5);
		
		JLabel userNameLabel_1_5 = new JLabel("\uC911\uBCF5\uD655\uC778");
		userNameLabel_1_5.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
		userNameLabel_1_5.setHorizontalAlignment(SwingConstants.LEFT);
		userNameLabel_1_5.setBackground(Color.WHITE);
		userNameLabel_1_5.setBounds(20, 252, 81, 30);
		panel.add(userNameLabel_1_5);
		
		textField_6 = new JTextField();
		textField_6.setText("*******");
		textField_6.setHorizontalAlignment(SwingConstants.CENTER);
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		textField_6.setBounds(113, 254, 239, 30);
		panel.add(textField_6);
		
		JLabel userGradeLabel = new JLabel("\uD68C\uC6D0\uB4F1\uAE09");
		userGradeLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
		userGradeLabel.setHorizontalAlignment(SwingConstants.LEFT);
		userGradeLabel.setBackground(Color.WHITE);
		userGradeLabel.setBounds(20, 291, 81, 30);
		panel.add(userGradeLabel);
		
		textField_7 = new JTextField();
		textField_7.setText("\uC6B0\uC218\uD68C\uC6D0");
		textField_7.setHorizontalAlignment(SwingConstants.CENTER);
		textField_7.setEditable(false);
		textField_7.setColumns(10);
		textField_7.setBounds(113, 293, 239, 30);
		panel.add(textField_7);
		
		JButton btnNewButton_1 = new JButton("\uC911\uBCF5 \uD655\uC778");
		btnNewButton_1.setBounds(369, 130, 91, 32);
		panel.add(btnNewButton_1);

		//대출 패널
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(128, 128, 128), 2));
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(22, 444, 817, 201);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel borrowInformationLabel = new JLabel("\uB300\uCD9C\uC815\uBCF4");
		borrowInformationLabel.setFont(new Font("\uD55C\uCEF4\uC0B0\uB73B\uB3CB\uC6C0", borrowInformationLabel.getFont().getStyle() | Font.BOLD, borrowInformationLabel.getFont().getSize() + 3));
		borrowInformationLabel.setBounds(12, 10, 151, 39);
		panel_1.add(borrowInformationLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 48, 793, 105);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"\uC81C\uBAA9", "\uC800\uC790", "\uCD9C\uD310\uC0AC", "\uCE74\uD14C\uACE0\uB9AC", "\uB300\uCD9C\uC77C", "\uBC18\uB0A9\uC77C", "\uC5F0\uCCB4\uC5EC\uBD80"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("\uC218\uC815\uD558\uAE30");
		btnNewButton.setFont(new Font("\uD55C\uCEF4\uC0B0\uB73B\uB3CB\uC6C0", btnNewButton.getFont().getStyle() | Font.BOLD, btnNewButton.getFont().getSize() + 3));
		btnNewButton.setBounds(340, 163, 117, 28);
		panel_1.add(btnNewButton);
	}
}
