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

public class UserInfo extends JFrame {

	private JPanel contentPane;
	private JTextField userNameTextField;
	private JTable table;
	private JTextField userPhoneFirstTextField;
	private JTextField userEmailTextField;
	private JTextField secondUserPasswordTextField;
	private JTextField levelTextField;
	private JTextField userPhoneSecondTextField;
	private JTextField userPhoneThirdTextField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	//프레임 변수
	private Main mainFrame;
	private SearchBook searchBookFrame;

	/**
	 * Launch the application.
	 */
	
	String[] thirtyOne = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
	String[] twentyNine = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",};
	String[] thirty = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"};
	

	/**
	 * Create the frame.
	 */
	public UserInfo() {
		setTitle("\uB3C4\uC11C \uAD00\uB9AC \uD504\uB85C\uADF8\uB7A8 - \uC720\uC800\uC815\uBCF4");
		
		setBounds(100, 100, 881, 694);
		contentPane = new JPanel();	//메인 프레임
		contentPane.setBackground(SystemColor.menu);
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
		homeIconMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mainFrame = new Main();
				mainFrame.setVisible(true);
				setVisible(false);
			}
		});
		homeIconMenu.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		homeIconMenu.setForeground(new Color(255, 255, 255));
		ImageIcon icon5 = new ImageIcon("D:\\\uC0C8 \uD3F4\uB354\\pngegg.png");
		Image img5 = icon5.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon changeIcon5 = new ImageIcon(img5);
		homeIconMenu.setIcon(changeIcon5);
		menuBar.add(homeIconMenu);
		
		//도서 찾기 메뉴
		JMenu findBookMenu = new JMenu("\uB3C4\uC11C\uCC3E\uAE30");	//메뉴 - 도서찾기
		findBookMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				searchBookFrame = new SearchBook();
				searchBookFrame.setVisible(true);
				setVisible(false);
			}
		});
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
		userNameTextField.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 14));
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
		JComboBox yearComboBox = new JComboBox();
		yearComboBox.setEnabled(false);
		yearComboBox.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		
		String[] years = new String[122];
		int k = 0;
		for(int i = 2021; i >= 1900; i--) {
			years[k++] = Integer.toString(i);
		}
		yearComboBox.setModel(new DefaultComboBoxModel(years));
		
		yearComboBox.setBounds(113, 52, 74, 30);
		panel.add(yearComboBox);
		
		//유저 일 콤보박스
		JComboBox dayComboBox = new JComboBox();
		dayComboBox.setEnabled(false);
		dayComboBox.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		
		//유저 월 콤보박스
		JComboBox monthComboBox = new JComboBox();
		monthComboBox.setEnabled(false);
		monthComboBox.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		monthComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getItem().toString() == "1" || e.getItem().toString() == "3" || e.getItem().toString() == "5" ||
						e.getItem().toString() == "7" || e.getItem().toString() == "8" ||e.getItem().toString() == "10" || e.getItem().toString() == "12") {
					dayComboBox.setModel(new DefaultComboBoxModel<String>(thirtyOne));
				}
				
				else if(e.getItem().toString() == "2") {
					dayComboBox.setModel(new DefaultComboBoxModel<String>(twentyNine));
				}
				
				else if(e.getItem().toString() == "4" || e.getItem().toString() == "6" || e.getItem().toString() == "9" || e.getItem().toString() == "11") {
					dayComboBox.setModel(new DefaultComboBoxModel<String>(thirty));
				}
			}
		});
		
		monthComboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		monthComboBox.setBounds(196, 52, 74, 30);
		panel.add(monthComboBox);
		

		dayComboBox.setModel(new DefaultComboBoxModel<String>(thirtyOne));
		
		dayComboBox.setBounds(278, 52, 74, 30);
	
		panel.add(dayComboBox);
		
		//유저 성별 라벨
		JLabel userSexLabel = new JLabel("\uC131\uBCC4");
		userSexLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
		userSexLabel.setHorizontalAlignment(SwingConstants.LEFT);
		userSexLabel.setBackground(Color.WHITE);
		userSexLabel.setBounds(20, 90, 81, 30);
		panel.add(userSexLabel);
		
		//남자 라디오버튼
		JRadioButton manRadioButton = new JRadioButton("\uB0A8\uC790");
		manRadioButton.setEnabled(false);
		manRadioButton.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		manRadioButton.setSelected(true);
		manRadioButton.setBackground(Color.WHITE);
		buttonGroup.add(manRadioButton);
		manRadioButton.setBounds(113, 95, 58, 23);
		panel.add(manRadioButton);
		
		//여자 라디오버튼
		JRadioButton womanRadioButton = new JRadioButton("\uC5EC\uC790");
		womanRadioButton.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		womanRadioButton.setEnabled(false);
		womanRadioButton.setBackground(Color.WHITE);
		buttonGroup.add(womanRadioButton);
		womanRadioButton.setBounds(233, 95, 58, 23);
		panel.add(womanRadioButton);
		
		//유저 전화번호 라벨
		JLabel userPhoneLabel = new JLabel("\uC804\uD654\uBC88\uD638");
		userPhoneLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
		userPhoneLabel.setHorizontalAlignment(SwingConstants.LEFT);
		userPhoneLabel.setBackground(Color.WHITE);
		userPhoneLabel.setBounds(20, 130, 81, 30);
		panel.add(userPhoneLabel);
		
		//유저 전화번호 첫번째자리 텍스트필드
		userPhoneFirstTextField = new JTextField();
		userPhoneFirstTextField.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		userPhoneFirstTextField.setText("000");
		userPhoneFirstTextField.setHorizontalAlignment(SwingConstants.CENTER);
		userPhoneFirstTextField.setEditable(false);
		userPhoneFirstTextField.setColumns(10);
		userPhoneFirstTextField.setBounds(113, 132, 74, 30);
		panel.add(userPhoneFirstTextField);
		
		//유저 전화번호 두번째자리 텍스트필드
		userPhoneSecondTextField = new JTextField();
		userPhoneSecondTextField.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		userPhoneSecondTextField.setText("0000");
		userPhoneSecondTextField.setHorizontalAlignment(SwingConstants.CENTER);
		userPhoneSecondTextField.setEditable(false);
		userPhoneSecondTextField.setColumns(10);
		userPhoneSecondTextField.setBounds(196, 132, 74, 30);
		panel.add(userPhoneSecondTextField);
		
		//유저 전화번후 세번째자리 텍스트필드
		userPhoneThirdTextField = new JTextField();
		userPhoneThirdTextField.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
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
		userEmailTextField.setEditable(false);
		userEmailTextField.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		userEmailTextField.setText("abj123@gmail.com");
		userEmailTextField.setHorizontalAlignment(SwingConstants.CENTER);
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
		
		//비밀번호 텍스트 필드
		JTextField userPasswordTextField = new JTextField();
		userPasswordTextField.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		userPasswordTextField.setText("*******");
		userPasswordTextField.setHorizontalAlignment(SwingConstants.CENTER);
		userPasswordTextField.setEditable(false);
		userPasswordTextField.setColumns(10);
		userPasswordTextField.setBounds(113, 214, 239, 30);
		panel.add(userPasswordTextField);
		
		JLabel userNameLabel_1_5 = new JLabel("\uC911\uBCF5\uD655\uC778");
		userNameLabel_1_5.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
		userNameLabel_1_5.setHorizontalAlignment(SwingConstants.LEFT);
		userNameLabel_1_5.setBackground(Color.WHITE);
		userNameLabel_1_5.setBounds(20, 252, 81, 30);
		panel.add(userNameLabel_1_5);
		
		//비밀번호 재확인 텍스트 필드
		secondUserPasswordTextField = new JTextField();
		secondUserPasswordTextField.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		secondUserPasswordTextField.setText("*******");
		secondUserPasswordTextField.setHorizontalAlignment(SwingConstants.CENTER);
		secondUserPasswordTextField.setEditable(false);
		secondUserPasswordTextField.setColumns(10);
		secondUserPasswordTextField.setBounds(113, 254, 239, 30);
		panel.add(secondUserPasswordTextField);
		
		JLabel userGradeLabel = new JLabel("\uD68C\uC6D0\uB4F1\uAE09");
		userGradeLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
		userGradeLabel.setHorizontalAlignment(SwingConstants.LEFT);
		userGradeLabel.setBackground(Color.WHITE);
		userGradeLabel.setBounds(20, 291, 81, 30);
		panel.add(userGradeLabel);
		
		//등급 텍스트 필드
		levelTextField = new JTextField();
		levelTextField.setEditable(false);
		levelTextField.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		levelTextField.setText("\uC6B0\uC218\uD68C\uC6D0");
		levelTextField.setHorizontalAlignment(SwingConstants.CENTER);
		levelTextField.setColumns(10);
		levelTextField.setBounds(113, 293, 239, 30);
		panel.add(levelTextField);
		
		JButton dupleButton = new JButton("\uC911\uBCF5 \uD655\uC778");
		dupleButton.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		dupleButton.setBounds(369, 130, 91, 32);
		panel.add(dupleButton);

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
		
		JButton editButton = new JButton("\uC218\uC815\uD558\uAE30");
		editButton.addMouseListener(new MouseAdapter() {
			//수정하기 버튼을 클릭했을 때
			@Override
			public void mouseClicked(MouseEvent e) {
				if(editButton.getText().equals("수정하기")) {
					userNameTextField.setEditable(true);
					yearComboBox.setEnabled(true);
					monthComboBox.setEnabled(true);
					dayComboBox.setEnabled(true);
					manRadioButton.setEnabled(true);
					womanRadioButton.setEnabled(true);
					userPhoneFirstTextField.setEditable(true);
					userPhoneSecondTextField.setEditable(true);
					userPhoneThirdTextField.setEditable(true);
					userEmailTextField.setEditable(true);
					userPasswordTextField.setEditable(true);
					secondUserPasswordTextField.setEditable(true);
					editButton.setText("수정완료");
				}
				else if(editButton.getText().equals("수정완료")) {
					userNameTextField.setEditable(false);
					yearComboBox.setEnabled(false);
					monthComboBox.setEnabled(false);
					dayComboBox.setEnabled(false);
					manRadioButton.setEnabled(false);
					womanRadioButton.setEnabled(false);
					userPhoneFirstTextField.setEditable(false);
					userPhoneSecondTextField.setEditable(false);
					userPhoneThirdTextField.setEditable(false);
					userEmailTextField.setEditable(false);
					userPasswordTextField.setEditable(false);
					secondUserPasswordTextField.setEditable(false);
					editButton.setText("수정하기");
				}
			}
		});
		editButton.setFont(new Font("\uD55C\uCEF4\uC0B0\uB73B\uB3CB\uC6C0", editButton.getFont().getStyle() | Font.BOLD, editButton.getFont().getSize() + 3));
		editButton.setBounds(340, 163, 117, 28);
		panel_1.add(editButton);
	}
}
