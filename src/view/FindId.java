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

public class FindId extends JFrame {

	private JPanel contentPane;
	
	//������ ����
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
		
		setBounds(100, 100, 510, 301);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//ȸ�� ���� �г�
		JPanel panel = new JPanel();
		panel.setBounds(23, 33, 442, 142);
		contentPane.add(panel);
		panel.setLayout(null);
		
		//���� �̸� ��
		JLabel userNameLabel = new JLabel("\uC774\uB984");
		userNameLabel.setFont(new Font("���Ļ�浸��", Font.PLAIN, 15));
		userNameLabel.setBounds(12, 10, 50, 15);
		panel.add(userNameLabel);
		
		//���� �̸� �ؽ�Ʈ�ʵ�
		userNameTextField = new JTextField();
		userNameTextField.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		userNameTextField.setBounds(108, 8, 312, 21);
		panel.add(userNameTextField);
		userNameTextField.setColumns(10);
		
		//���� ��ȭ��ȣ ��
		JLabel userPhoneLabel = new JLabel("\uC804\uD654\uBC88\uD638");
		userPhoneLabel.setFont(new Font("���Ļ�浸��", Font.PLAIN, 15));
		userPhoneLabel.setBounds(12, 56, 73, 15);
		panel.add(userPhoneLabel);
		
		//���� ��ȭ��ȣ ù��°�ڸ� �ؽ�Ʈ�ʵ�
		userPhoneFirstTextField = new JTextField();
		userPhoneFirstTextField.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		userPhoneFirstTextField.setColumns(10);
		userPhoneFirstTextField.setBounds(108, 54, 96, 21);
		panel.add(userPhoneFirstTextField);
		
		//���� ��ȭ��ȣ �ι����ڸ� �ؽ�Ʈ�ʵ�
		userPhoneSecondTextField = new JTextField();
		userPhoneSecondTextField.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		userPhoneSecondTextField.setColumns(10);
		userPhoneSecondTextField.setBounds(216, 54, 96, 21);
		panel.add(userPhoneSecondTextField);
		
		//���� ��ȭ��ȣ �������ڸ� �ؽ�Ʈ�ʵ�
		userPhoneThirdTextField = new JTextField();
		userPhoneThirdTextField.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		userPhoneThirdTextField.setColumns(10);
		userPhoneThirdTextField.setBounds(324, 54, 96, 21);
		panel.add(userPhoneThirdTextField);
		
		//���� ������� ��
		JLabel userBirthLabel = new JLabel("\uC0DD\uB144\uC6D4\uC77C");
		userBirthLabel.setFont(new Font("���Ļ�浸��", Font.PLAIN, 15));
		userBirthLabel.setBounds(12, 105, 73, 15);
		panel.add(userBirthLabel);
		
		//���� �⵵ �޺��ڽ�
		JComboBox yearComboBox = new JComboBox();
		yearComboBox.setEditable(true);
		yearComboBox.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		yearComboBox.setBounds(108, 99, 96, 21);
		panel.add(yearComboBox);
		
		//���� �� �޺��ڽ�
		JComboBox monthComboBox = new JComboBox();
		monthComboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		monthComboBox.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		monthComboBox.setBounds(216, 99, 96, 21);
		panel.add(monthComboBox);
		
		//���� �� �޺��ڽ�
		JComboBox dayComboBox = new JComboBox();
		dayComboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		dayComboBox.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		dayComboBox.setBounds(324, 99, 96, 21);
		panel.add(dayComboBox);
		
		//���̵� ã�� ��ư
		JButton findIdButton = new JButton("\uC544\uC774\uB514 \uCC3E\uAE30");
		findIdButton.setFont(new Font("���Ļ�浸��", Font.PLAIN, 15));
		findIdButton.setBounds(23, 199, 119, 35);
		contentPane.add(findIdButton);
	}
}
