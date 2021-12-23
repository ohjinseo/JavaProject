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
import javax.swing.JOptionPane;
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
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.ButtonGroup;
import java.awt.SystemColor;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.PseudoColumnUsage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.beans.PropertyChangeEvent;
import javax.swing.JPasswordField;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SignUp extends JFrame {
	dbConnector dbConn = new dbConnector();
	
	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	// 프레임 변수
	private Main mainFrame;
	private SearchBook searchBookFrame;
	
	int verification_code = 0;	//메일로 발송할 인증코드
	Random rand  =new Random();	//인증코드 생성에 사용될 난수
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

	// 에러 발생여부 변수
	private boolean nameError=true;
	private boolean birthError=true;
	private boolean sexError=true;
	private boolean phoneError=true;
	private boolean emailError=true;
	private boolean emailDupleError=true;
	private boolean passwordError=true;
	private boolean password2Error=true;
	
	private boolean firstPhoneError;
	private boolean secondPhoneError=true;
	private boolean thirdPhoneError=true;
	
	// 번호 중복 확인여부 변수
	private boolean isEmailDupleChecked;
	
	//이미지 파일 경로
	private String filePath;
	
	//생일 년,월,일
	private String year="2021";
	private String month="1";
	private String day="1";
	/**
	 * Create the frame.
	 */
	public SignUp() {
		setTitle("\uB3C4\uC11C \uAD00\uB9AC \uD504\uB85C\uADF8\uB7A8 - \uD68C\uC6D0\uAC00\uC785");
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
		userNameLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
		userNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		userNameLabel.setBackground(Color.WHITE);
		userNameLabel.setBounds(12, 10, 81, 30);
		panel.add(userNameLabel);
		
		// 유저 이름 오류 라벨
		JLabel userNameErrorLabel = new JLabel("\uC774\uB984\uC744 \uC785\uB825\uD558\uC138\uC694.");
		userNameErrorLabel.setForeground(Color.RED);
		userNameErrorLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 14));
		userNameErrorLabel.setBounds(356, 10, 249, 30);
		panel.add(userNameErrorLabel);

		// 유저 이름 텍스트필드
		userNameTextField = new JTextField();
		userNameTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {// 키 눌렀다 땠을때
				if (userNameTextField.getText().equals("")) {
					userNameErrorLabel.setText("이름을 입력하세요."); // 오류 있는경우(이름이 빈칸임) 오류출력
					nameError=true; //이름 에러O
				} else {
					userNameErrorLabel.setText(""); // 오류 없는경우 - 오류출력X
					nameError=false; //이름 에러X
				}
			}
		});
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
		
		// 유저 생일 오류 라벨
		JLabel userBirthErrorLabel = new JLabel("\uC0DD\uB144\uC6D4\uC77C\uC744 \uC124\uC815\uD558\uC138\uC694.");
		userBirthErrorLabel.setForeground(Color.RED);
		userBirthErrorLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 14));
		userBirthErrorLabel.setBounds(356, 50, 249, 30);
		panel.add(userBirthErrorLabel);

		// 생일 년도 콤보박스
		JComboBox yearComboBox = new JComboBox();
		yearComboBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				year=yearComboBox.getSelectedItem().toString(); //년 저장
			}
		});
		yearComboBox.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		
		years = new String[122];
		k = 0;
		for (int i = 2021; i >= 1900; i--) {
			years[k++] = Integer.toString(i);
		}
		yearComboBox.setModel(new DefaultComboBoxModel(years));

		yearComboBox.setBounds(105, 52, 74, 30);
		panel.add(yearComboBox);

		// 생일 일 콤보박스
		JComboBox dayComboBox = new JComboBox();
		dayComboBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				day=dayComboBox.getSelectedItem().toString(); //일 저장
			}
		});
		dayComboBox.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		dayComboBox.setBounds(270, 52, 74, 30);
		panel.add(dayComboBox);
		
		// 생일 달 콤보박스
		JComboBox monthComboBox = new JComboBox();
		monthComboBox.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		
		monthComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //클릭했을때
				month=monthComboBox.getSelectedItem().toString(); //월 저장
				userBirthErrorLabel.setText(""); // 오류 없는경우 - 오류출력X
				birthError=false; //생일 에러X
			}
		});
		
		monthComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getItem().toString() == "1" || e.getItem().toString() == "3" || e.getItem().toString() == "5"
						|| e.getItem().toString() == "7" || e.getItem().toString() == "8"
						|| e.getItem().toString() == "10" || e.getItem().toString() == "12") {
					dayComboBox.setModel(new DefaultComboBoxModel<String>(thirtyOne));
				}

				else if (e.getItem().toString() == "2") {
					dayComboBox.setModel(new DefaultComboBoxModel<String>(twentyNine));
				}

				else if (e.getItem().toString() == "4" || e.getItem().toString() == "6" || e.getItem().toString() == "9"
						|| e.getItem().toString() == "11") {
					dayComboBox.setModel(new DefaultComboBoxModel<String>(thirty));
				}
			}
		});

		monthComboBox.setModel(new DefaultComboBoxModel(
				new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
		
		monthComboBox.setBounds(188, 52, 74, 30);
		panel.add(monthComboBox);
		
		// 유저 성별 라벨
		JLabel userSexLabel = new JLabel("\uC131\uBCC4");
		userSexLabel.setHorizontalAlignment(SwingConstants.LEFT);
		userSexLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
		userSexLabel.setBackground(Color.WHITE);
		userSexLabel.setBounds(12, 90, 81, 30);
		panel.add(userSexLabel);

		// 유저 성별 에러 라벨
		JLabel userSexErrorLabel = new JLabel("\uC131\uBCC4\uC744 \uC124\uC815\uD558\uC138\uC694.");
		userSexErrorLabel.setForeground(Color.RED);
		userSexErrorLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 14));
		userSexErrorLabel.setBounds(356, 90, 249, 30);
		panel.add(userSexErrorLabel);

		
		// 남자 라디오버튼
		JRadioButton manRadioButton = new JRadioButton("\uB0A8\uC790");
		buttonGroup.add(manRadioButton);
		manRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { //클릭했을 떄
				userSexErrorLabel.setText(""); //오류(선택안됨) 사라짐 - 오류 없애기
				sexError=false; //성별 에러X
			}
		});
		manRadioButton.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		manRadioButton.setBounds(105, 95, 113, 23);
		panel.add(manRadioButton);

		// 여자 라디오버튼
		JRadioButton womanRadioButton = new JRadioButton("\uC5EC\uC790");
		buttonGroup.add(womanRadioButton);
		womanRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { //클릭했을 떄
				userSexErrorLabel.setText(""); //오류(선택안됨) 사라짐 - 오류 없애기
				sexError=false; //성별 에러O
			}
		});
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

		// 유저 전화번호 에러 라벨
		JLabel userPhoneErrorLabel = new JLabel("\uC62C\uBC14\uB978 \uC804\uD654\uBC88\uD638\uB97C \uC785\uB825\uD574\uC8FC\uC138\uC694.");
		userPhoneErrorLabel.setForeground(Color.RED);
		userPhoneErrorLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 14));
		userPhoneErrorLabel.setBounds(464, 130, 249, 30);
		panel.add(userPhoneErrorLabel);
		
		// 유저 전화번호 첫번째자리 텍스트필드
		userPhoneFirstTextField = new JTextField();
		userPhoneFirstTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				isEmailDupleChecked=false; //값이 변경되었으므로 다시 중복검사 받도록함
				String first=userPhoneFirstTextField.getText();
				if (first.equals("")||!first.matches("[+-]?\\d*(\\.\\d+)?")) { //빈칸이거나 숫자가아님
					firstPhoneError=true; //전화 에러O
				} else { //정상
					firstPhoneError=false; //전화 에러X
				}
				
				//오류출력부분
				if(firstPhoneError||secondPhoneError||thirdPhoneError) // 3개중 하나가 오류 있는경우 
				{
					userPhoneErrorLabel.setText("올바른 전화번호를 입력해주세요."); // 올바른 문자가아님 오류출력
						
					phoneError=true; //전화에러O
					
				}
				else if(!isEmailDupleChecked)//중복체크 안한경우
				{
					userPhoneErrorLabel.setText("중복 확인을 해주세요."); // 중복체크요구 오류출력
					
					phoneError=true; //전화에러O
				}
				else
				{
					userPhoneErrorLabel.setText(""); // 오류 없는경우 - 오류출력X
					phoneError=false; 
				}
					
			}
		});
		userPhoneFirstTextField.setText("010");
		userPhoneFirstTextField.setHorizontalAlignment(SwingConstants.CENTER);
		userPhoneFirstTextField.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		userPhoneFirstTextField.setColumns(10);
		userPhoneFirstTextField.setBounds(105, 132, 74, 30);
		panel.add(userPhoneFirstTextField);

		// 유저 전화번호 두번째자리 텍스트필드
		userPhoneSecondTextField = new JTextField();
		userPhoneSecondTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				isEmailDupleChecked=false; //값이 변경되었으므로 다시 중복검사 받도록함
				String second=userPhoneSecondTextField.getText();
				if (second.equals("")||!second.matches("[+-]?\\d*(\\.\\d+)?")) { //빈칸이거나 숫자가아님
					secondPhoneError=true; //전화 에러O
				} else { //정상
					secondPhoneError=false; //전화 에러X
				}
				
				//오류출력부분
				if(firstPhoneError||secondPhoneError||thirdPhoneError) // 3개중 하나가 오류 있는경우 
				{
					userPhoneErrorLabel.setText("올바른 전화번호를 입력해주세요."); // 올바른 문자가아님 오류출력
					
					phoneError=true; //전화에러O
				}
				else if(!isEmailDupleChecked)//중복체크 안한경우
				{
					userPhoneErrorLabel.setText("중복 확인을 해주세요."); // 중복체크요구 오류출력
					
					phoneError=true; //전화에러O
				}
				else
				{
					userPhoneErrorLabel.setText(""); // 오류 없는경우 - 오류출력X
					phoneError=false; 
				}
			}
		});
		userPhoneSecondTextField.setHorizontalAlignment(SwingConstants.CENTER);
		userPhoneSecondTextField.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		userPhoneSecondTextField.setColumns(10);
		userPhoneSecondTextField.setBounds(188, 132, 74, 30);
		panel.add(userPhoneSecondTextField);

		// 유저 전화번호 세번째자리 텍스트필드
		userPhoneThirdTextField = new JTextField();
		userPhoneThirdTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				isEmailDupleChecked=false; //값이 변경되었으므로 다시 중복검사 받도록함
				String third=userPhoneThirdTextField.getText();
				if (third.equals("")||!third.matches("[+-]?\\d*(\\.\\d+)?")) { //빈칸이거나 숫자가아님
					thirdPhoneError=true; //전화 에러O
				} else { //정상
					thirdPhoneError=false; //전화 에러X
				}
				
				if(firstPhoneError||secondPhoneError||thirdPhoneError)// 3개중 하나가 오류 있는경우 
				{
					userPhoneErrorLabel.setText("올바른 전화번호를 입력해주세요."); // 올바른 문자가아님 오류출력

					phoneError=true; //전화에러O
				}
				else if(!isEmailDupleChecked)//중복체크 안한경우
				{
					userPhoneErrorLabel.setText("중복 확인을 해주세요."); // 중복체크요구 오류출력
					
					phoneError=true; //전화에러O
				}
				else
				{
					userPhoneErrorLabel.setText(""); // 오류 없는경우 - 오류출력X
					phoneError=false; 
				}
			}
		});
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

		// 유저 이메일 오류 라벨
		JLabel userEmailErrorLabel = new JLabel(
				"\uC62C\uBC14\uB978 \uC774\uBA54\uC77C\uC744 \uC785\uB825\uD558\uC138\uC694.");
		userEmailErrorLabel.setForeground(Color.RED);
		userEmailErrorLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 14));
		userEmailErrorLabel.setBounds(464, 172, 187, 30);
		panel.add(userEmailErrorLabel);

		// 유저 이메일 텍스트필드
		userEmailTextField = new JTextField();
		userEmailTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) { // 키 눌렀다 땠을때
				if (userEmailTextField.getText().equals("")||!userEmailTextField.getText().contains("@")) {
					userEmailErrorLabel.setText("올바른 이메일을 입력하세요."); //오류 있는 경우(빈칸이거나 올바른 형식이 아님) 오류출력
					emailError=true; //이메일 에러O
				} else {
					userEmailErrorLabel.setText(""); // 오류 없는경우 - 오류출력X
					emailError=false; //이메일 에러X
				}
			}
		});
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

		// 유저 비밀번호 오류 라벨
		JLabel userPasswordErrorLabel = new JLabel("\uBE44\uBC00\uBC88\uD638\uB97C \uC785\uB825\uD558\uC138\uC694.");
		userPasswordErrorLabel.setForeground(Color.RED);
		userPasswordErrorLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 14));
		userPasswordErrorLabel.setBounds(356, 253, 249, 30);
		panel.add(userPasswordErrorLabel);
		
		// 유저 비밀번호 텍스트필드
		userPasswordTextField = new JTextField();
		userPasswordTextField.addKeyListener(new KeyAdapter() {// 키 눌렀다 땠을때
			@Override
			public void keyReleased(KeyEvent e) {
				if (userPasswordTextField.getText().equals("")) {
					userPasswordErrorLabel.setText("비밀번호를 입력하세요."); // 오류 있는경우(비밀번호가 빈칸임) 오류출력
					passwordError=true; //비밀번호 에러O
				} else {
					userPasswordErrorLabel.setText(""); // 오류 없는경우 - 오류출력X
					passwordError=false; //비밀번호 에러X
				}
			}
		});
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

		// 유저 비밀번호 중복 오류 라벨
		JLabel userPassword2TextErrorLabel = new JLabel(
				"\uBE44\uBC00\uBC88\uD638\uAC00 \uD2C0\uB838\uC2B5\uB2C8\uB2E4.");
		userPassword2TextErrorLabel.setForeground(Color.RED);
		userPassword2TextErrorLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 14));
		userPassword2TextErrorLabel.setBounds(356, 293, 249, 30);
		panel.add(userPassword2TextErrorLabel);

		// 유저 비밀번호 중복 텍스트필드
		userPassword2TextFiled = new JTextField();
		userPassword2TextFiled.addKeyListener(new KeyAdapter() {// 키 눌렀다 땠을때ㄴ
			@Override
			public void keyReleased(KeyEvent e) {// 키 눌렀다 땠을때
				if (userPassword2TextFiled.getText().equals("")) {
					userPassword2TextErrorLabel.setText("중복확인을 해주세요.");// 오류 있는경우(비밀번호가 빈칸임) 오류출력
					password2Error=true; //비밀번호 중복 에러O
				} else if (!userPassword2TextFiled.getText().equals(userPasswordTextField.getText())) {
					userPassword2TextErrorLabel.setText("비밀번호가 틀렸습니다.");// 오류 있는경우(비밀번호가 다름) 오류출력
					password2Error=true; //비밀번호 중복 에러O
				} else {
					userPassword2TextErrorLabel.setText("");// 오류 없는경우 - 오류출력X
					password2Error=false; //비밀번호 중복 에러X
				}
			}
		});
		userPassword2TextFiled.setHorizontalAlignment(SwingConstants.CENTER);
		userPassword2TextFiled.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		userPassword2TextFiled.setColumns(10);
		userPassword2TextFiled.setBounds(105, 293, 239, 30);
		panel.add(userPassword2TextFiled);

		// 중복확인 버튼
		JButton dupleButton = new JButton("\uC911\uBCF5 \uD655\uC778");
		dupleButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { //클릭했을 때
				if(userPhoneErrorLabel.getText().equals("중복 확인을 해주세요.")) { //문자관련 오류들이 없어서 중복확인을 요구할떄
				String userPhone=userPhoneFirstTextField.getText()+userPhoneSecondTextField.getText()+userPhoneThirdTextField.getText();
				String sql = "select USER_PHONE\r\n"+" from USER where USER_PHONE = '"+userPhone+"'";

				ResultSet rs=dbConn.executeQuery(sql);
				try {
					if(rs.next()) { //결과가 null이 아닌경우(검색결과가 존재하는 경우)
						userPhoneErrorLabel.setText("이미 가입된 회원입니다.");
						phoneError=true;
					}
					else {
						userPhoneErrorLabel.setText("");
						isEmailDupleChecked=true;
						phoneError=false;
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}	
			}
			}
		});
		dupleButton.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		dupleButton.setBounds(361, 130, 91, 32);
		panel.add(dupleButton);

		// 유저 이메일 중복확인 에러 라벨
		JLabel userEmailDupleTextErrorLabel = new JLabel("코드를 입력해 주세요.");
		userEmailDupleTextErrorLabel.setForeground(Color.RED);
		userEmailDupleTextErrorLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 14));
		userEmailDupleTextErrorLabel.setBounds(356, 214, 249, 30);
		panel.add(userEmailDupleTextErrorLabel);
		
		// 유저 이메일 인증코드 발송 버튼
		JButton get_verification_Button = new JButton("코드 받기");
		get_verification_Button.setFont(new Font("Dialog", Font.PLAIN, 12));
		get_verification_Button.setBounds(361, 172, 91, 32);
		panel.add(get_verification_Button);
		get_verification_Button.addMouseListener(new MouseAdapter() {	
			
			//코드받기 버튼 클릭시 호출되는 리스너
			@Override
			public void mouseClicked(MouseEvent e) {
				//메일을 보내는 쪽의 정보
				String host	="smtp.naver.com";	//호스트 네이버로 설정
				final String user = "";	//메일을 보낼 네이버 아이디
				final String password = "";	//메일을 보낼 네이버 비밀번호
				int port = 587;	//포트번호		네이버는 465라뜨던데 안되고 587해야 됨
				
				//메일을 받을 주소
				String to = userEmailTextField.getText();
				
				Properties	props = System.getProperties();
				props.put("mail.smtp.host", host);
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.port", port);
				props.put("mail.smptp.ssl.enable", "true");
				props.put("mail.smtp.ssl.trust", host);
				
				Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(user, password);
					}
				});
				session.setDebug(true);
				
				verification_code = rand.nextInt(10000);	//0<= code <10000 난수 발생

				//메세지 작성
				try {
					MimeMessage message = new MimeMessage(session);
					message.setFrom(new InternetAddress(user));	//보내는 사람의 메일
					message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));	//받을 사람의 메일
					
					//제목
					message.setSubject("자바 프로젝트 인증코드 발송");
					
					//내용
					message.setText("인증코드 : "+verification_code);
					
					Transport.send(message);
					JOptionPane.showMessageDialog(null, "인증코드가 발송되었습니다. 네이버 메일이 아니라면 0000을 입력해 주세요.", "인증코드 발송 안내", JOptionPane.INFORMATION_MESSAGE);
				}catch(MessagingException e1) {
					System.out.println("회원가입 메일로 메시지 인증코드 발송 과정에서 오류발생");
					e1.printStackTrace();
				}
			}
		});
		
		// 유저 이메일 중복코드 텍스트필드
		userEmailDupleTextField = new JTextField();
		userEmailDupleTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {// 키 눌렀다 땠을때
				if (userEmailDupleTextField.getText().equals("")) {
					userEmailDupleTextErrorLabel.setText("코드를 입력해 주세요."); // 오류 있는경우(빈칸임) 오류출력
					emailDupleError=true; //이메일 중복 에러O
				} 
				//0000이거나 이메일로 발송된 난수이면 
				else if (userEmailDupleTextField.getText().equals("0000")||userEmailDupleTextField.getText().equals(Integer.toString(verification_code))) {
					userEmailDupleTextErrorLabel.setText(""); // 오류 없는경우 - 오류출력X
					emailDupleError=false; //이메일 중복 에러X
				}  
				else {
					userEmailDupleTextErrorLabel.setText("틀린 코드입니다. 다시 확인해주세요."); // 오류 있는경우(코드가 같지않음) 오류출력
					emailDupleError=true; //이메일 중복 에러O
				}
			}
		});
		
		userEmailDupleTextField.setHorizontalAlignment(SwingConstants.CENTER);
		userEmailDupleTextField.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		userEmailDupleTextField.setColumns(10);
		userEmailDupleTextField.setBounds(105, 214, 239, 30);
		panel.add(userEmailDupleTextField);

		// 유저 이미지 라벨
		JLabel userPhotoLabel = new JLabel("\uC0AC\uC9C4");
		userPhotoLabel.setHorizontalAlignment(SwingConstants.LEFT);
		userPhotoLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
		userPhotoLabel.setBackground(Color.WHITE);
		userPhotoLabel.setBounds(12, 331, 81, 30);
		panel.add(userPhotoLabel);

		// 유저 이미지 라벨(실제 사진 들어가는 라벨)
		JLabel userPhotoShowLabel = new JLabel("\uC720\uC800 \uC0AC\uC9C4");
		userPhotoShowLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userPhotoShowLabel.setBackground(new Color(255, 250, 250));
		userPhotoShowLabel.setBounds(105, 333, 128, 132);
		panel.add(userPhotoShowLabel);

		//이미지 파일 필터
		JFileChooser user_img = new JFileChooser();
		FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("All Images", "jpg","jpge","png","gif");
		user_img.setFileFilter(fileFilter);
		
		// 사진 불러오기 버튼
		JButton loadingButton = new JButton("\uBD88\uB7EC \uC624\uAE30...");
		loadingButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int ret = user_img.showOpenDialog(null);	//파일 찾는 창을 띄우줌
				if(ret == 0) { //파일을 선택했다면
					
					filePath = user_img.getSelectedFile().getPath();	//파일 경로를 filePath에 저장
					
					JOptionPane.showMessageDialog(null, filePath,"당신이 선택한 파일은",JOptionPane.NO_OPTION);	//선택한 파일경로를 메시지 창으로 띄움
					try {
						//유저 이미지
						File tmpFile = new File(filePath);

						Image img = ImageIO.read(tmpFile); // 읽어온 이미지를 img에 저장
						Image resize_img = img.getScaledInstance(170, 140, Image.SCALE_SMOOTH); // 이미지 크기 170x140 로 크기 조절하여
																								// resize_img에 저장
						ImageIcon icon = new ImageIcon(resize_img); // 조절한 크기의 이미지를 icon에 저장
						userPhotoShowLabel.setIcon(icon); // 유저 사진 레이블에 이미지 부착
						userPhotoShowLabel.setBorder(new LineBorder(Color.black, 1, false)); // 레이블 테두리 검은색으로 그려줌
						
					}catch(FileNotFoundException e1) {
						System.out.println("도서추가 화면에서 파일 찾기 에러");
					}catch (IOException e1) {
						System.out.println("도서추가 화면에서 읽어온 파일 출력 에러");
					}
				}
		
			}
		});
		loadingButton.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		loadingButton.setBounds(245, 333, 91, 32);
		panel.add(loadingButton);

		// 회원가입 버튼
		JButton singUpButton = new JButton("\uD68C\uC6D0\uAC00\uC785");
		singUpButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!nameError&&!birthError&&!sexError&&!phoneError&&!emailError&&!emailDupleError&&!passwordError&&!password2Error) { //에러가 1개라도 없는 경우
					
					//전화번호
					String userPhone=userPhoneFirstTextField.getText()+userPhoneSecondTextField.getText()+userPhoneThirdTextField.getText();
					
					if(Integer.parseInt(month)<10) //월숫자 중에서 10미만숫자들
						month="0"+month; //형식 바꾸기(ex 0 -> 01)
					if(Integer.parseInt(day)<10) //일숫자 중에서 10미만 숫자들
						day="0"+day; //형식 바꾸기(ex 0 -> 01)
					String userBirth=year+"-"+month+"-"+day;
					
					String img="USER_IMAGE,\r\n";
					String imgResult="?";
					
					String sql = "insert into USER(\r\n"
							+ "USER_PHONE,\r\n"
							+ "USER_NAME,\r\n"
							+ "USER_BIRTH,\r\n"
							+ "USER_SEX,\r\n"
							+ "USER_MAIL,\r\n"
							+ "USER_IMAGE,\r\n"
							+ "USER_REG_DATE,\r\n"
							+ "USER_PW\r\n"
							+ ")values(\r\n"
							+"?, ?, '"+userBirth+"', ?, ?, ?, now(), ?);";
					
				try { // DB 접근
					PreparedStatement ps;
					//유저 사진
					try { //유저 이미지 있음
						FileInputStream fin = new FileInputStream(filePath);
						
						ps = dbConn.conn.prepareStatement(sql);
						
						ps.setString(1, userPhone);							//유저 전화번호
						ps.setString(2, userNameTextField.getText());		//유저 이름
						ps.setBoolean(3, manRadioButton.isSelected());		//유저 성별
						ps.setString(4, userEmailTextField.getText());		//유저 이메일
						ps.setBinaryStream(5, fin, fin.available());		//유저 이미지
						ps.setString(6, userPasswordTextField.getText());	//유저 비번
					}catch(NullPointerException e1) { //유저 이미지 null임
						sql ="insert into USER(\r\n"
								+ "USER_PHONE,\r\n"
								+ "USER_NAME,\r\n"
								+ "USER_BIRTH,\r\n"
								+ "USER_SEX,\r\n"
								+ "USER_MAIL,\r\n"
								+ "USER_REG_DATE,\r\n"
								+ "USER_PW\r\n"
								+ ")values(\r\n"
								+"?, ?, '"+userBirth+"', ?, ?, now(), ?);"; //유저 이미지 삽입 명령 삭제된 버전
						ps = dbConn.conn.prepareStatement(sql);
						
						ps.setString(1, userPhone);							//유저 전화번호
						ps.setString(2, userNameTextField.getText());		//유저 이름
						ps.setBoolean(3, manRadioButton.isSelected());		//유저 성별
						ps.setString(4, userEmailTextField.getText());		//유저 이메일
						ps.setString(5, userPasswordTextField.getText());	//유저 비번
					}
					
					int count = ps.executeUpdate();
					if(count==0) {	
						JOptionPane.showMessageDialog(null, "회원가입에 실패하였습니다...", "회원가입", JOptionPane.ERROR_MESSAGE);
					}
					else {		
						JOptionPane.showMessageDialog(null, "회원가입에 성공하였습니다!", "회원가입", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();	//에러 추적
					System.out.println("회원가입 SQL 실행 에러");
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
					
				} else { //에러가 1개라도 있는 경우
					JOptionPane.showMessageDialog(null, "회원가입에 실패하였습니다...", "회원가입", JOptionPane.ERROR_MESSAGE);
				}
				dispose();
			}
		});
		singUpButton.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		singUpButton.setBounds(105, 475, 91, 32);
		panel.add(singUpButton);
		

	}
}
