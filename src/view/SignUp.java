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

	// ������ ����
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
		userNameLabel.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		userNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		userNameLabel.setBackground(Color.WHITE);
		userNameLabel.setBounds(12, 10, 81, 30);
		panel.add(userNameLabel);

		// ���� �̸� �ؽ�Ʈ�ʵ�
		userNameTextField = new JTextField();
		userNameTextField.setText("\uC624\uC9C4\uC11C");
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

		// ���� �⵵ �޺��ڽ�
		JComboBox yearComboBox = new JComboBox();
		yearComboBox.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		yearComboBox.setEditable(true);
		yearComboBox.setBounds(105, 52, 74, 30);
		panel.add(yearComboBox);

		// ���� �� �޺��ڽ�
		JComboBox monthComboBox = new JComboBox();
		monthComboBox.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		monthComboBox.setBounds(188, 52, 74, 30);
		panel.add(monthComboBox);

		// ���� �� �޺��ڽ�
		JComboBox dayComboBox = new JComboBox();
		dayComboBox.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		dayComboBox.setBounds(270, 52, 74, 30);
		panel.add(dayComboBox);

		// ���� ���� ��
		JLabel userSexLabel = new JLabel("\uC131\uBCC4");
		userSexLabel.setHorizontalAlignment(SwingConstants.LEFT);
		userSexLabel.setFont(new Font("���Ļ�浸��", Font.BOLD, 15));
		userSexLabel.setBackground(Color.WHITE);
		userSexLabel.setBounds(12, 90, 81, 30);
		panel.add(userSexLabel);

		// ���� ������ư
		JRadioButton manRadioButton = new JRadioButton("\uB0A8\uC790");
		manRadioButton.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		manRadioButton.setBounds(105, 95, 113, 23);
		panel.add(manRadioButton);

		// ���� ������ư
		JRadioButton womanRadioButton = new JRadioButton("\uC5EC\uC790");
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

		// ���� ��ȭ��ȣ ù��°�ڸ� �ؽ�Ʈ�ʵ�
		userPhoneFirstTextField = new JTextField();
		userPhoneFirstTextField.setText("000");
		userPhoneFirstTextField.setHorizontalAlignment(SwingConstants.CENTER);
		userPhoneFirstTextField.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		userPhoneFirstTextField.setColumns(10);
		userPhoneFirstTextField.setBounds(105, 132, 74, 30);
		panel.add(userPhoneFirstTextField);

		// ���� ��ȭ��ȣ �ι�°�ڸ� �ؽ�Ʈ�ʵ�
		userPhoneSecondTextField = new JTextField();
		userPhoneSecondTextField.setText("0000");
		userPhoneSecondTextField.setHorizontalAlignment(SwingConstants.CENTER);
		userPhoneSecondTextField.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		userPhoneSecondTextField.setColumns(10);
		userPhoneSecondTextField.setBounds(188, 132, 74, 30);
		panel.add(userPhoneSecondTextField);

		// ���� ��ȭ��ȣ ����°�ڸ� �ؽ�Ʈ�ʵ�
		userPhoneThirdTextField = new JTextField();
		userPhoneThirdTextField.setText("0000");
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

		// ���� �̸��� �ؽ�Ʈ�ʵ�
		userEmailTextField = new JTextField();
		userEmailTextField.setText("abj123@gmail.com");
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

		// ���� ��й�ȣ �ؽ�Ʈ�ʵ�
		userPasswordTextField = new JTextField();
		userPasswordTextField.setText("*******");
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

		// ���� ��й�ȣ �ߺ� �ؽ�Ʈ�ʵ�
		userPassword2TextFiled = new JTextField();
		userPassword2TextFiled.setText("*******");
		userPassword2TextFiled.setHorizontalAlignment(SwingConstants.CENTER);
		userPassword2TextFiled.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		userPassword2TextFiled.setColumns(10);
		userPassword2TextFiled.setBounds(105, 293, 239, 30);
		panel.add(userPassword2TextFiled);

		// �ߺ�Ȯ�� ��ư
		JButton dupleButton = new JButton("\uC911\uBCF5 \uD655\uC778");
		dupleButton.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		dupleButton.setBounds(361, 130, 91, 32);
		panel.add(dupleButton);

		//���� �̸��� �ߺ��ڵ� �ؽ�Ʈ�ʵ�
		userEmailDupleTextField = new JTextField();
		userEmailDupleTextField.setText("(\uC911\uBCF5 \uCF54\uB4DC \uC790\uB9AC)");
		userEmailDupleTextField.setHorizontalAlignment(SwingConstants.CENTER);
		userEmailDupleTextField.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		userEmailDupleTextField.setColumns(10);
		userEmailDupleTextField.setBounds(105, 214, 239, 30);
		panel.add(userEmailDupleTextField);

		//���� �̹��� ��
		JLabel userPhotoLabel = new JLabel("\uC0AC\uC9C4");
		userPhotoLabel.setHorizontalAlignment(SwingConstants.LEFT);
		userPhotoLabel.setFont(new Font("���Ļ�浸��", Font.BOLD, 15));
		userPhotoLabel.setBackground(Color.WHITE);
		userPhotoLabel.setBounds(12, 331, 81, 30);
		panel.add(userPhotoLabel);

		//���� �̹��� ��(���� ���� ���� ��)
		JLabel userPhotoShowLabel = new JLabel("\uC720\uC800 \uC0AC\uC9C4");
		userPhotoShowLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userPhotoShowLabel.setBackground(new Color(255, 250, 250));
		userPhotoShowLabel.setBounds(105, 333, 128, 132);
		panel.add(userPhotoShowLabel);

		//���� �ҷ����� ��ư
		JButton loadingButton = new JButton("\uBD88\uB7EC \uC624\uAE30...");
		loadingButton.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		loadingButton.setBounds(245, 333, 91, 32);
		panel.add(loadingButton);

		//ȸ������ ��ư
		JButton singUpButton = new JButton("\uD68C\uC6D0\uAC00\uC785");
		singUpButton.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		singUpButton.setBounds(105, 475, 91, 32);
		panel.add(singUpButton);
	}
}
