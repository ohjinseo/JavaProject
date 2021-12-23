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

	// ������ ����
	private Main mainFrame;
	private SearchBook searchBookFrame;
	
	int verification_code = 0;	//���Ϸ� �߼��� �����ڵ�
	Random rand  =new Random();	//�����ڵ� ������ ���� ����
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

	// ���� �߻����� ����
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
	
	// ��ȣ �ߺ� Ȯ�ο��� ����
	private boolean isEmailDupleChecked;
	
	//�̹��� ���� ���
	private String filePath;
	
	//���� ��,��,��
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
		contentPane = new JPanel(); // ���� ������
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

		// ���� ���� �г�
		JPanel panel = new JPanel();
		panel.setBounds(12, 10, 694, 549);
		contentPane.add(panel);
		panel.setLayout(null);

		// ���� �̸� ��
		JLabel userNameLabel = new JLabel("\uC774\uB984 ");
		userNameLabel.setFont(new Font("���Ļ�浸��", Font.BOLD, 15));
		userNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		userNameLabel.setBackground(Color.WHITE);
		userNameLabel.setBounds(12, 10, 81, 30);
		panel.add(userNameLabel);
		
		// ���� �̸� ���� ��
		JLabel userNameErrorLabel = new JLabel("\uC774\uB984\uC744 \uC785\uB825\uD558\uC138\uC694.");
		userNameErrorLabel.setForeground(Color.RED);
		userNameErrorLabel.setFont(new Font("���Ļ�浸��", Font.PLAIN, 14));
		userNameErrorLabel.setBounds(356, 10, 249, 30);
		panel.add(userNameErrorLabel);

		// ���� �̸� �ؽ�Ʈ�ʵ�
		userNameTextField = new JTextField();
		userNameTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {// Ű ������ ������
				if (userNameTextField.getText().equals("")) {
					userNameErrorLabel.setText("�̸��� �Է��ϼ���."); // ���� �ִ°��(�̸��� ��ĭ��) �������
					nameError=true; //�̸� ����O
				} else {
					userNameErrorLabel.setText(""); // ���� ���°�� - �������X
					nameError=false; //�̸� ����X
				}
			}
		});
		userNameTextField.setHorizontalAlignment(SwingConstants.CENTER);
		userNameTextField.setFont(new Font("���Ļ�浸��", Font.PLAIN, 14));
		userNameTextField.setColumns(10);
		userNameTextField.setBounds(105, 12, 239, 30);
		panel.add(userNameTextField);

		// ���� ���� ��
		JLabel userBirthLabel = new JLabel("\uC0DD\uB144\uC6D4\uC77C");
		userBirthLabel.setHorizontalAlignment(SwingConstants.LEFT);
		userBirthLabel.setFont(new Font("���Ļ�浸��", Font.BOLD, 15));
		userBirthLabel.setBackground(Color.WHITE);
		userBirthLabel.setBounds(12, 50, 81, 30);
		panel.add(userBirthLabel);
		
		// ���� ���� ���� ��
		JLabel userBirthErrorLabel = new JLabel("\uC0DD\uB144\uC6D4\uC77C\uC744 \uC124\uC815\uD558\uC138\uC694.");
		userBirthErrorLabel.setForeground(Color.RED);
		userBirthErrorLabel.setFont(new Font("���Ļ�浸��", Font.PLAIN, 14));
		userBirthErrorLabel.setBounds(356, 50, 249, 30);
		panel.add(userBirthErrorLabel);

		// ���� �⵵ �޺��ڽ�
		JComboBox yearComboBox = new JComboBox();
		yearComboBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				year=yearComboBox.getSelectedItem().toString(); //�� ����
			}
		});
		yearComboBox.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		
		years = new String[122];
		k = 0;
		for (int i = 2021; i >= 1900; i--) {
			years[k++] = Integer.toString(i);
		}
		yearComboBox.setModel(new DefaultComboBoxModel(years));

		yearComboBox.setBounds(105, 52, 74, 30);
		panel.add(yearComboBox);

		// ���� �� �޺��ڽ�
		JComboBox dayComboBox = new JComboBox();
		dayComboBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				day=dayComboBox.getSelectedItem().toString(); //�� ����
			}
		});
		dayComboBox.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		dayComboBox.setBounds(270, 52, 74, 30);
		panel.add(dayComboBox);
		
		// ���� �� �޺��ڽ�
		JComboBox monthComboBox = new JComboBox();
		monthComboBox.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		
		monthComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Ŭ��������
				month=monthComboBox.getSelectedItem().toString(); //�� ����
				userBirthErrorLabel.setText(""); // ���� ���°�� - �������X
				birthError=false; //���� ����X
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
		
		// ���� ���� ��
		JLabel userSexLabel = new JLabel("\uC131\uBCC4");
		userSexLabel.setHorizontalAlignment(SwingConstants.LEFT);
		userSexLabel.setFont(new Font("���Ļ�浸��", Font.BOLD, 15));
		userSexLabel.setBackground(Color.WHITE);
		userSexLabel.setBounds(12, 90, 81, 30);
		panel.add(userSexLabel);

		// ���� ���� ���� ��
		JLabel userSexErrorLabel = new JLabel("\uC131\uBCC4\uC744 \uC124\uC815\uD558\uC138\uC694.");
		userSexErrorLabel.setForeground(Color.RED);
		userSexErrorLabel.setFont(new Font("���Ļ�浸��", Font.PLAIN, 14));
		userSexErrorLabel.setBounds(356, 90, 249, 30);
		panel.add(userSexErrorLabel);

		
		// ���� ������ư
		JRadioButton manRadioButton = new JRadioButton("\uB0A8\uC790");
		buttonGroup.add(manRadioButton);
		manRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { //Ŭ������ ��
				userSexErrorLabel.setText(""); //����(���þȵ�) ����� - ���� ���ֱ�
				sexError=false; //���� ����X
			}
		});
		manRadioButton.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		manRadioButton.setBounds(105, 95, 113, 23);
		panel.add(manRadioButton);

		// ���� ������ư
		JRadioButton womanRadioButton = new JRadioButton("\uC5EC\uC790");
		buttonGroup.add(womanRadioButton);
		womanRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { //Ŭ������ ��
				userSexErrorLabel.setText(""); //����(���þȵ�) ����� - ���� ���ֱ�
				sexError=false; //���� ����O
			}
		});
		womanRadioButton.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		womanRadioButton.setBounds(225, 95, 113, 23);
		panel.add(womanRadioButton);

		// ���� ��ȭ��ȣ ��
		JLabel userPhoneLabel = new JLabel("\uC804\uD654\uBC88\uD638");
		userPhoneLabel.setHorizontalAlignment(SwingConstants.LEFT);
		userPhoneLabel.setFont(new Font("���Ļ�浸��", Font.BOLD, 15));
		userPhoneLabel.setBackground(Color.WHITE);
		userPhoneLabel.setBounds(12, 130, 81, 30);
		panel.add(userPhoneLabel);

		// ���� ��ȭ��ȣ ���� ��
		JLabel userPhoneErrorLabel = new JLabel("\uC62C\uBC14\uB978 \uC804\uD654\uBC88\uD638\uB97C \uC785\uB825\uD574\uC8FC\uC138\uC694.");
		userPhoneErrorLabel.setForeground(Color.RED);
		userPhoneErrorLabel.setFont(new Font("���Ļ�浸��", Font.PLAIN, 14));
		userPhoneErrorLabel.setBounds(464, 130, 249, 30);
		panel.add(userPhoneErrorLabel);
		
		// ���� ��ȭ��ȣ ù��°�ڸ� �ؽ�Ʈ�ʵ�
		userPhoneFirstTextField = new JTextField();
		userPhoneFirstTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				isEmailDupleChecked=false; //���� ����Ǿ����Ƿ� �ٽ� �ߺ��˻� �޵�����
				String first=userPhoneFirstTextField.getText();
				if (first.equals("")||!first.matches("[+-]?\\d*(\\.\\d+)?")) { //��ĭ�̰ų� ���ڰ��ƴ�
					firstPhoneError=true; //��ȭ ����O
				} else { //����
					firstPhoneError=false; //��ȭ ����X
				}
				
				//������ºκ�
				if(firstPhoneError||secondPhoneError||thirdPhoneError) // 3���� �ϳ��� ���� �ִ°�� 
				{
					userPhoneErrorLabel.setText("�ùٸ� ��ȭ��ȣ�� �Է����ּ���."); // �ùٸ� ���ڰ��ƴ� �������
						
					phoneError=true; //��ȭ����O
					
				}
				else if(!isEmailDupleChecked)//�ߺ�üũ ���Ѱ��
				{
					userPhoneErrorLabel.setText("�ߺ� Ȯ���� ���ּ���."); // �ߺ�üũ�䱸 �������
					
					phoneError=true; //��ȭ����O
				}
				else
				{
					userPhoneErrorLabel.setText(""); // ���� ���°�� - �������X
					phoneError=false; 
				}
					
			}
		});
		userPhoneFirstTextField.setText("010");
		userPhoneFirstTextField.setHorizontalAlignment(SwingConstants.CENTER);
		userPhoneFirstTextField.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		userPhoneFirstTextField.setColumns(10);
		userPhoneFirstTextField.setBounds(105, 132, 74, 30);
		panel.add(userPhoneFirstTextField);

		// ���� ��ȭ��ȣ �ι�°�ڸ� �ؽ�Ʈ�ʵ�
		userPhoneSecondTextField = new JTextField();
		userPhoneSecondTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				isEmailDupleChecked=false; //���� ����Ǿ����Ƿ� �ٽ� �ߺ��˻� �޵�����
				String second=userPhoneSecondTextField.getText();
				if (second.equals("")||!second.matches("[+-]?\\d*(\\.\\d+)?")) { //��ĭ�̰ų� ���ڰ��ƴ�
					secondPhoneError=true; //��ȭ ����O
				} else { //����
					secondPhoneError=false; //��ȭ ����X
				}
				
				//������ºκ�
				if(firstPhoneError||secondPhoneError||thirdPhoneError) // 3���� �ϳ��� ���� �ִ°�� 
				{
					userPhoneErrorLabel.setText("�ùٸ� ��ȭ��ȣ�� �Է����ּ���."); // �ùٸ� ���ڰ��ƴ� �������
					
					phoneError=true; //��ȭ����O
				}
				else if(!isEmailDupleChecked)//�ߺ�üũ ���Ѱ��
				{
					userPhoneErrorLabel.setText("�ߺ� Ȯ���� ���ּ���."); // �ߺ�üũ�䱸 �������
					
					phoneError=true; //��ȭ����O
				}
				else
				{
					userPhoneErrorLabel.setText(""); // ���� ���°�� - �������X
					phoneError=false; 
				}
			}
		});
		userPhoneSecondTextField.setHorizontalAlignment(SwingConstants.CENTER);
		userPhoneSecondTextField.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		userPhoneSecondTextField.setColumns(10);
		userPhoneSecondTextField.setBounds(188, 132, 74, 30);
		panel.add(userPhoneSecondTextField);

		// ���� ��ȭ��ȣ ����°�ڸ� �ؽ�Ʈ�ʵ�
		userPhoneThirdTextField = new JTextField();
		userPhoneThirdTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				isEmailDupleChecked=false; //���� ����Ǿ����Ƿ� �ٽ� �ߺ��˻� �޵�����
				String third=userPhoneThirdTextField.getText();
				if (third.equals("")||!third.matches("[+-]?\\d*(\\.\\d+)?")) { //��ĭ�̰ų� ���ڰ��ƴ�
					thirdPhoneError=true; //��ȭ ����O
				} else { //����
					thirdPhoneError=false; //��ȭ ����X
				}
				
				if(firstPhoneError||secondPhoneError||thirdPhoneError)// 3���� �ϳ��� ���� �ִ°�� 
				{
					userPhoneErrorLabel.setText("�ùٸ� ��ȭ��ȣ�� �Է����ּ���."); // �ùٸ� ���ڰ��ƴ� �������

					phoneError=true; //��ȭ����O
				}
				else if(!isEmailDupleChecked)//�ߺ�üũ ���Ѱ��
				{
					userPhoneErrorLabel.setText("�ߺ� Ȯ���� ���ּ���."); // �ߺ�üũ�䱸 �������
					
					phoneError=true; //��ȭ����O
				}
				else
				{
					userPhoneErrorLabel.setText(""); // ���� ���°�� - �������X
					phoneError=false; 
				}
			}
		});
		userPhoneThirdTextField.setHorizontalAlignment(SwingConstants.CENTER);
		userPhoneThirdTextField.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		userPhoneThirdTextField.setColumns(10);
		userPhoneThirdTextField.setBounds(270, 132, 74, 30);
		panel.add(userPhoneThirdTextField);

		// ���� �̸��� ��
		JLabel userEmailLabel = new JLabel("\uC774\uBA54\uC77C");
		userEmailLabel.setHorizontalAlignment(SwingConstants.LEFT);
		userEmailLabel.setFont(new Font("���Ļ�浸��", Font.BOLD, 15));
		userEmailLabel.setBackground(Color.WHITE);
		userEmailLabel.setBounds(12, 172, 81, 30);
		panel.add(userEmailLabel);

		// ���� �̸��� ���� ��
		JLabel userEmailErrorLabel = new JLabel(
				"\uC62C\uBC14\uB978 \uC774\uBA54\uC77C\uC744 \uC785\uB825\uD558\uC138\uC694.");
		userEmailErrorLabel.setForeground(Color.RED);
		userEmailErrorLabel.setFont(new Font("���Ļ�浸��", Font.PLAIN, 14));
		userEmailErrorLabel.setBounds(464, 172, 187, 30);
		panel.add(userEmailErrorLabel);

		// ���� �̸��� �ؽ�Ʈ�ʵ�
		userEmailTextField = new JTextField();
		userEmailTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) { // Ű ������ ������
				if (userEmailTextField.getText().equals("")||!userEmailTextField.getText().contains("@")) {
					userEmailErrorLabel.setText("�ùٸ� �̸����� �Է��ϼ���."); //���� �ִ� ���(��ĭ�̰ų� �ùٸ� ������ �ƴ�) �������
					emailError=true; //�̸��� ����O
				} else {
					userEmailErrorLabel.setText(""); // ���� ���°�� - �������X
					emailError=false; //�̸��� ����X
				}
			}
		});
		userEmailTextField.setHorizontalAlignment(SwingConstants.CENTER);
		userEmailTextField.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		userEmailTextField.setColumns(10);
		userEmailTextField.setBounds(105, 174, 239, 30);
		panel.add(userEmailTextField);

		// ���� ��й�ȣ ��
		JLabel userPasswordLabel = new JLabel("\uBE44\uBC00\uBC88\uD638");
		userPasswordLabel.setHorizontalAlignment(SwingConstants.LEFT);
		userPasswordLabel.setFont(new Font("���Ļ�浸��", Font.BOLD, 15));
		userPasswordLabel.setBackground(Color.WHITE);
		userPasswordLabel.setBounds(12, 251, 81, 30);
		panel.add(userPasswordLabel);

		// ���� ��й�ȣ ���� ��
		JLabel userPasswordErrorLabel = new JLabel("\uBE44\uBC00\uBC88\uD638\uB97C \uC785\uB825\uD558\uC138\uC694.");
		userPasswordErrorLabel.setForeground(Color.RED);
		userPasswordErrorLabel.setFont(new Font("���Ļ�浸��", Font.PLAIN, 14));
		userPasswordErrorLabel.setBounds(356, 253, 249, 30);
		panel.add(userPasswordErrorLabel);
		
		// ���� ��й�ȣ �ؽ�Ʈ�ʵ�
		userPasswordTextField = new JTextField();
		userPasswordTextField.addKeyListener(new KeyAdapter() {// Ű ������ ������
			@Override
			public void keyReleased(KeyEvent e) {
				if (userPasswordTextField.getText().equals("")) {
					userPasswordErrorLabel.setText("��й�ȣ�� �Է��ϼ���."); // ���� �ִ°��(��й�ȣ�� ��ĭ��) �������
					passwordError=true; //��й�ȣ ����O
				} else {
					userPasswordErrorLabel.setText(""); // ���� ���°�� - �������X
					passwordError=false; //��й�ȣ ����X
				}
			}
		});
		userPasswordTextField.setHorizontalAlignment(SwingConstants.CENTER);
		userPasswordTextField.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		userPasswordTextField.setColumns(10);
		userPasswordTextField.setBounds(105, 253, 239, 30);
		panel.add(userPasswordTextField);

		// ���� ��й�ȣ �ߺ� ��
		JLabel userPasswordDupleLabel = new JLabel("\uC911\uBCF5\uD655\uC778");
		userPasswordDupleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		userPasswordDupleLabel.setFont(new Font("���Ļ�浸��", Font.BOLD, 15));
		userPasswordDupleLabel.setBackground(Color.WHITE);
		userPasswordDupleLabel.setBounds(12, 291, 81, 30);
		panel.add(userPasswordDupleLabel);

		// ���� ��й�ȣ �ߺ� ���� ��
		JLabel userPassword2TextErrorLabel = new JLabel(
				"\uBE44\uBC00\uBC88\uD638\uAC00 \uD2C0\uB838\uC2B5\uB2C8\uB2E4.");
		userPassword2TextErrorLabel.setForeground(Color.RED);
		userPassword2TextErrorLabel.setFont(new Font("���Ļ�浸��", Font.PLAIN, 14));
		userPassword2TextErrorLabel.setBounds(356, 293, 249, 30);
		panel.add(userPassword2TextErrorLabel);

		// ���� ��й�ȣ �ߺ� �ؽ�Ʈ�ʵ�
		userPassword2TextFiled = new JTextField();
		userPassword2TextFiled.addKeyListener(new KeyAdapter() {// Ű ������ ��������
			@Override
			public void keyReleased(KeyEvent e) {// Ű ������ ������
				if (userPassword2TextFiled.getText().equals("")) {
					userPassword2TextErrorLabel.setText("�ߺ�Ȯ���� ���ּ���.");// ���� �ִ°��(��й�ȣ�� ��ĭ��) �������
					password2Error=true; //��й�ȣ �ߺ� ����O
				} else if (!userPassword2TextFiled.getText().equals(userPasswordTextField.getText())) {
					userPassword2TextErrorLabel.setText("��й�ȣ�� Ʋ�Ƚ��ϴ�.");// ���� �ִ°��(��й�ȣ�� �ٸ�) �������
					password2Error=true; //��й�ȣ �ߺ� ����O
				} else {
					userPassword2TextErrorLabel.setText("");// ���� ���°�� - �������X
					password2Error=false; //��й�ȣ �ߺ� ����X
				}
			}
		});
		userPassword2TextFiled.setHorizontalAlignment(SwingConstants.CENTER);
		userPassword2TextFiled.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		userPassword2TextFiled.setColumns(10);
		userPassword2TextFiled.setBounds(105, 293, 239, 30);
		panel.add(userPassword2TextFiled);

		// �ߺ�Ȯ�� ��ư
		JButton dupleButton = new JButton("\uC911\uBCF5 \uD655\uC778");
		dupleButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { //Ŭ������ ��
				if(userPhoneErrorLabel.getText().equals("�ߺ� Ȯ���� ���ּ���.")) { //���ڰ��� �������� ��� �ߺ�Ȯ���� �䱸�ҋ�
				String userPhone=userPhoneFirstTextField.getText()+userPhoneSecondTextField.getText()+userPhoneThirdTextField.getText();
				String sql = "select USER_PHONE\r\n"+" from USER where USER_PHONE = '"+userPhone+"'";

				ResultSet rs=dbConn.executeQuery(sql);
				try {
					if(rs.next()) { //����� null�� �ƴѰ��(�˻������ �����ϴ� ���)
						userPhoneErrorLabel.setText("�̹� ���Ե� ȸ���Դϴ�.");
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
		dupleButton.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		dupleButton.setBounds(361, 130, 91, 32);
		panel.add(dupleButton);

		// ���� �̸��� �ߺ�Ȯ�� ���� ��
		JLabel userEmailDupleTextErrorLabel = new JLabel("�ڵ带 �Է��� �ּ���.");
		userEmailDupleTextErrorLabel.setForeground(Color.RED);
		userEmailDupleTextErrorLabel.setFont(new Font("���Ļ�浸��", Font.PLAIN, 14));
		userEmailDupleTextErrorLabel.setBounds(356, 214, 249, 30);
		panel.add(userEmailDupleTextErrorLabel);
		
		// ���� �̸��� �����ڵ� �߼� ��ư
		JButton get_verification_Button = new JButton("�ڵ� �ޱ�");
		get_verification_Button.setFont(new Font("Dialog", Font.PLAIN, 12));
		get_verification_Button.setBounds(361, 172, 91, 32);
		panel.add(get_verification_Button);
		get_verification_Button.addMouseListener(new MouseAdapter() {	
			
			//�ڵ�ޱ� ��ư Ŭ���� ȣ��Ǵ� ������
			@Override
			public void mouseClicked(MouseEvent e) {
				//������ ������ ���� ����
				String host	="smtp.naver.com";	//ȣ��Ʈ ���̹��� ����
				final String user = "";	//������ ���� ���̹� ���̵�
				final String password = "";	//������ ���� ���̹� ��й�ȣ
				int port = 587;	//��Ʈ��ȣ		���̹��� 465��ߴ��� �ȵǰ� 587�ؾ� ��
				
				//������ ���� �ּ�
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
				
				verification_code = rand.nextInt(10000);	//0<= code <10000 ���� �߻�

				//�޼��� �ۼ�
				try {
					MimeMessage message = new MimeMessage(session);
					message.setFrom(new InternetAddress(user));	//������ ����� ����
					message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));	//���� ����� ����
					
					//����
					message.setSubject("�ڹ� ������Ʈ �����ڵ� �߼�");
					
					//����
					message.setText("�����ڵ� : "+verification_code);
					
					Transport.send(message);
					JOptionPane.showMessageDialog(null, "�����ڵ尡 �߼۵Ǿ����ϴ�. ���̹� ������ �ƴ϶�� 0000�� �Է��� �ּ���.", "�����ڵ� �߼� �ȳ�", JOptionPane.INFORMATION_MESSAGE);
				}catch(MessagingException e1) {
					System.out.println("ȸ������ ���Ϸ� �޽��� �����ڵ� �߼� �������� �����߻�");
					e1.printStackTrace();
				}
			}
		});
		
		// ���� �̸��� �ߺ��ڵ� �ؽ�Ʈ�ʵ�
		userEmailDupleTextField = new JTextField();
		userEmailDupleTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {// Ű ������ ������
				if (userEmailDupleTextField.getText().equals("")) {
					userEmailDupleTextErrorLabel.setText("�ڵ带 �Է��� �ּ���."); // ���� �ִ°��(��ĭ��) �������
					emailDupleError=true; //�̸��� �ߺ� ����O
				} 
				//0000�̰ų� �̸��Ϸ� �߼۵� �����̸� 
				else if (userEmailDupleTextField.getText().equals("0000")||userEmailDupleTextField.getText().equals(Integer.toString(verification_code))) {
					userEmailDupleTextErrorLabel.setText(""); // ���� ���°�� - �������X
					emailDupleError=false; //�̸��� �ߺ� ����X
				}  
				else {
					userEmailDupleTextErrorLabel.setText("Ʋ�� �ڵ��Դϴ�. �ٽ� Ȯ�����ּ���."); // ���� �ִ°��(�ڵ尡 ��������) �������
					emailDupleError=true; //�̸��� �ߺ� ����O
				}
			}
		});
		
		userEmailDupleTextField.setHorizontalAlignment(SwingConstants.CENTER);
		userEmailDupleTextField.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		userEmailDupleTextField.setColumns(10);
		userEmailDupleTextField.setBounds(105, 214, 239, 30);
		panel.add(userEmailDupleTextField);

		// ���� �̹��� ��
		JLabel userPhotoLabel = new JLabel("\uC0AC\uC9C4");
		userPhotoLabel.setHorizontalAlignment(SwingConstants.LEFT);
		userPhotoLabel.setFont(new Font("���Ļ�浸��", Font.BOLD, 15));
		userPhotoLabel.setBackground(Color.WHITE);
		userPhotoLabel.setBounds(12, 331, 81, 30);
		panel.add(userPhotoLabel);

		// ���� �̹��� ��(���� ���� ���� ��)
		JLabel userPhotoShowLabel = new JLabel("\uC720\uC800 \uC0AC\uC9C4");
		userPhotoShowLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userPhotoShowLabel.setBackground(new Color(255, 250, 250));
		userPhotoShowLabel.setBounds(105, 333, 128, 132);
		panel.add(userPhotoShowLabel);

		//�̹��� ���� ����
		JFileChooser user_img = new JFileChooser();
		FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("All Images", "jpg","jpge","png","gif");
		user_img.setFileFilter(fileFilter);
		
		// ���� �ҷ����� ��ư
		JButton loadingButton = new JButton("\uBD88\uB7EC \uC624\uAE30...");
		loadingButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int ret = user_img.showOpenDialog(null);	//���� ã�� â�� �����
				if(ret == 0) { //������ �����ߴٸ�
					
					filePath = user_img.getSelectedFile().getPath();	//���� ��θ� filePath�� ����
					
					JOptionPane.showMessageDialog(null, filePath,"����� ������ ������",JOptionPane.NO_OPTION);	//������ ���ϰ�θ� �޽��� â���� ���
					try {
						//���� �̹���
						File tmpFile = new File(filePath);

						Image img = ImageIO.read(tmpFile); // �о�� �̹����� img�� ����
						Image resize_img = img.getScaledInstance(170, 140, Image.SCALE_SMOOTH); // �̹��� ũ�� 170x140 �� ũ�� �����Ͽ�
																								// resize_img�� ����
						ImageIcon icon = new ImageIcon(resize_img); // ������ ũ���� �̹����� icon�� ����
						userPhotoShowLabel.setIcon(icon); // ���� ���� ���̺� �̹��� ����
						userPhotoShowLabel.setBorder(new LineBorder(Color.black, 1, false)); // ���̺� �׵θ� ���������� �׷���
						
					}catch(FileNotFoundException e1) {
						System.out.println("�����߰� ȭ�鿡�� ���� ã�� ����");
					}catch (IOException e1) {
						System.out.println("�����߰� ȭ�鿡�� �о�� ���� ��� ����");
					}
				}
		
			}
		});
		loadingButton.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		loadingButton.setBounds(245, 333, 91, 32);
		panel.add(loadingButton);

		// ȸ������ ��ư
		JButton singUpButton = new JButton("\uD68C\uC6D0\uAC00\uC785");
		singUpButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!nameError&&!birthError&&!sexError&&!phoneError&&!emailError&&!emailDupleError&&!passwordError&&!password2Error) { //������ 1���� ���� ���
					
					//��ȭ��ȣ
					String userPhone=userPhoneFirstTextField.getText()+userPhoneSecondTextField.getText()+userPhoneThirdTextField.getText();
					
					if(Integer.parseInt(month)<10) //������ �߿��� 10�̸����ڵ�
						month="0"+month; //���� �ٲٱ�(ex 0 -> 01)
					if(Integer.parseInt(day)<10) //�ϼ��� �߿��� 10�̸� ���ڵ�
						day="0"+day; //���� �ٲٱ�(ex 0 -> 01)
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
					
				try { // DB ����
					PreparedStatement ps;
					//���� ����
					try { //���� �̹��� ����
						FileInputStream fin = new FileInputStream(filePath);
						
						ps = dbConn.conn.prepareStatement(sql);
						
						ps.setString(1, userPhone);							//���� ��ȭ��ȣ
						ps.setString(2, userNameTextField.getText());		//���� �̸�
						ps.setBoolean(3, manRadioButton.isSelected());		//���� ����
						ps.setString(4, userEmailTextField.getText());		//���� �̸���
						ps.setBinaryStream(5, fin, fin.available());		//���� �̹���
						ps.setString(6, userPasswordTextField.getText());	//���� ���
					}catch(NullPointerException e1) { //���� �̹��� null��
						sql ="insert into USER(\r\n"
								+ "USER_PHONE,\r\n"
								+ "USER_NAME,\r\n"
								+ "USER_BIRTH,\r\n"
								+ "USER_SEX,\r\n"
								+ "USER_MAIL,\r\n"
								+ "USER_REG_DATE,\r\n"
								+ "USER_PW\r\n"
								+ ")values(\r\n"
								+"?, ?, '"+userBirth+"', ?, ?, now(), ?);"; //���� �̹��� ���� ��� ������ ����
						ps = dbConn.conn.prepareStatement(sql);
						
						ps.setString(1, userPhone);							//���� ��ȭ��ȣ
						ps.setString(2, userNameTextField.getText());		//���� �̸�
						ps.setBoolean(3, manRadioButton.isSelected());		//���� ����
						ps.setString(4, userEmailTextField.getText());		//���� �̸���
						ps.setString(5, userPasswordTextField.getText());	//���� ���
					}
					
					int count = ps.executeUpdate();
					if(count==0) {	
						JOptionPane.showMessageDialog(null, "ȸ�����Կ� �����Ͽ����ϴ�...", "ȸ������", JOptionPane.ERROR_MESSAGE);
					}
					else {		
						JOptionPane.showMessageDialog(null, "ȸ�����Կ� �����Ͽ����ϴ�!", "ȸ������", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();	//���� ����
					System.out.println("ȸ������ SQL ���� ����");
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
					
				} else { //������ 1���� �ִ� ���
					JOptionPane.showMessageDialog(null, "ȸ�����Կ� �����Ͽ����ϴ�...", "ȸ������", JOptionPane.ERROR_MESSAGE);
				}
				dispose();
			}
		});
		singUpButton.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		singUpButton.setBounds(105, 475, 91, 32);
		panel.add(singUpButton);
		

	}
}
