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

public class SignUp extends JFrame {

	private JPanel contentPane;
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
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	

	/**
	 * Create the frame.
	 */
	public SignUp() {
		
		setBounds(100, 100, 732, 605);
		contentPane = new JPanel();	//메인 프레임
		contentPane.setBackground(SystemColor.menu);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ImageIcon icon5 = new ImageIcon("D:\\\uC0C8 \uD3F4\uB354\\pngegg.png");
		Image img5 = icon5.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon changeIcon5 = new ImageIcon(img5);
		
		String[] years = new String[122];
		int k = 0;
		for(int i = 2021; i >= 1900; i--) {
			years[k++] = Integer.toString(i);
		}
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 10, 694, 549);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel userNameLabel = new JLabel("\uC774\uB984 ");
		userNameLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		userNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		userNameLabel.setBackground(Color.WHITE);
		userNameLabel.setBounds(12, 10, 81, 30);
		panel.add(userNameLabel);
		
		textField = new JTextField();
		textField.setText("\uC624\uC9C4\uC11C");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 14));
		textField.setColumns(10);
		textField.setBounds(105, 12, 239, 30);
		panel.add(textField);
		
		JLabel userBirthLabel = new JLabel("\uC0DD\uB144\uC6D4\uC77C");
		userBirthLabel.setHorizontalAlignment(SwingConstants.LEFT);
		userBirthLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
		userBirthLabel.setBackground(Color.WHITE);
		userBirthLabel.setBounds(12, 50, 81, 30);
		panel.add(userBirthLabel);
		
		JComboBox yearComboBox = new JComboBox();
		yearComboBox.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		yearComboBox.setEditable(true);
		yearComboBox.setBounds(105, 52, 74, 30);
		panel.add(yearComboBox);
		
		JComboBox monthComboBox = new JComboBox();
		monthComboBox.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		monthComboBox.setBounds(188, 52, 74, 30);
		panel.add(monthComboBox);
		
		JComboBox dayComboBox = new JComboBox();
		dayComboBox.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		dayComboBox.setBounds(270, 52, 74, 30);
		panel.add(dayComboBox);
		
		JLabel userSexLabel = new JLabel("\uC131\uBCC4");
		userSexLabel.setHorizontalAlignment(SwingConstants.LEFT);
		userSexLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
		userSexLabel.setBackground(Color.WHITE);
		userSexLabel.setBounds(12, 90, 81, 30);
		panel.add(userSexLabel);
		
		JLabel userPhoneLabel = new JLabel("\uC804\uD654\uBC88\uD638");
		userPhoneLabel.setHorizontalAlignment(SwingConstants.LEFT);
		userPhoneLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
		userPhoneLabel.setBackground(Color.WHITE);
		userPhoneLabel.setBounds(12, 130, 81, 30);
		panel.add(userPhoneLabel);
		
		textField_1 = new JTextField();
		textField_1.setText("000");
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		textField_1.setColumns(10);
		textField_1.setBounds(105, 132, 74, 30);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setText("0000");
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		textField_2.setColumns(10);
		textField_2.setBounds(188, 132, 74, 30);
		panel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setText("0000");
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		textField_3.setColumns(10);
		textField_3.setBounds(270, 132, 74, 30);
		panel.add(textField_3);
		
		JLabel userEmailLabel = new JLabel("\uC774\uBA54\uC77C");
		userEmailLabel.setHorizontalAlignment(SwingConstants.LEFT);
		userEmailLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
		userEmailLabel.setBackground(Color.WHITE);
		userEmailLabel.setBounds(12, 172, 81, 30);
		panel.add(userEmailLabel);
		
		textField_4 = new JTextField();
		textField_4.setText("abj123@gmail.com");
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		textField_4.setColumns(10);
		textField_4.setBounds(105, 174, 239, 30);
		panel.add(textField_4);
		
		JLabel userPasswordLabel = new JLabel("\uBE44\uBC00\uBC88\uD638");
		userPasswordLabel.setHorizontalAlignment(SwingConstants.LEFT);
		userPasswordLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
		userPasswordLabel.setBackground(Color.WHITE);
		userPasswordLabel.setBounds(12, 251, 81, 30);
		panel.add(userPasswordLabel);
		
		textField_5 = new JTextField();
		textField_5.setText("*******");
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		textField_5.setColumns(10);
		textField_5.setBounds(105, 253, 239, 30);
		panel.add(textField_5);
		
		JLabel userNameLabel_1_5 = new JLabel("\uC911\uBCF5\uD655\uC778");
		userNameLabel_1_5.setHorizontalAlignment(SwingConstants.LEFT);
		userNameLabel_1_5.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
		userNameLabel_1_5.setBackground(Color.WHITE);
		userNameLabel_1_5.setBounds(12, 291, 81, 30);
		panel.add(userNameLabel_1_5);
		
		textField_6 = new JTextField();
		textField_6.setText("*******");
		textField_6.setHorizontalAlignment(SwingConstants.CENTER);
		textField_6.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		textField_6.setColumns(10);
		textField_6.setBounds(105, 293, 239, 30);
		panel.add(textField_6);
		
		JButton dupleButton = new JButton("\uC911\uBCF5 \uD655\uC778");
		dupleButton.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		dupleButton.setBounds(361, 130, 91, 32);
		panel.add(dupleButton);
		
		textField_7 = new JTextField();
		textField_7.setText("(\uC911\uBCF5 \uCF54\uB4DC \uC790\uB9AC)");
		textField_7.setHorizontalAlignment(SwingConstants.CENTER);
		textField_7.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		textField_7.setColumns(10);
		textField_7.setBounds(105, 214, 239, 30);
		panel.add(textField_7);
		
		JLabel userNameLabel_1_5_1 = new JLabel("\uC911\uBCF5\uD655\uC778");
		userNameLabel_1_5_1.setHorizontalAlignment(SwingConstants.LEFT);
		userNameLabel_1_5_1.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
		userNameLabel_1_5_1.setBackground(Color.WHITE);
		userNameLabel_1_5_1.setBounds(12, 331, 81, 30);
		panel.add(userNameLabel_1_5_1);
		
		JLabel userPictureLabel = new JLabel("\uC720\uC800 \uC0AC\uC9C4");
		userPictureLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userPictureLabel.setBackground(new Color(255, 250, 250));
		userPictureLabel.setBounds(105, 333, 128, 132);
		panel.add(userPictureLabel);
		
		JButton loadingButton = new JButton("\uBD88\uB7EC \uC624\uAE30...");
		loadingButton.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		loadingButton.setBounds(245, 333, 91, 32);
		panel.add(loadingButton);
		
		JButton singUpButton = new JButton("\uD68C\uC6D0\uAC00\uC785");
		singUpButton.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		singUpButton.setBounds(105, 475, 91, 32);
		panel.add(singUpButton);
		
		JRadioButton manRadioButton = new JRadioButton("\uB0A8\uC790");
		manRadioButton.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		manRadioButton.setBounds(105, 95, 113, 23);
		panel.add(manRadioButton);
		
		JRadioButton womanRadioButton = new JRadioButton("\uC5EC\uC790");
		womanRadioButton.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		womanRadioButton.setBounds(225, 95, 113, 23);
		panel.add(womanRadioButton);
	}
}
