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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FindPassword extends JFrame {
	dbConnector dbConn = new dbConnector();
	private JPanel contentPane;
	
	//������ ����
	private Main mainFrame;
	private JTextField EmailTextField;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public FindPassword() {
		setTitle("\uB3C4\uC11C \uAD00\uB9AC \uD504\uB85C\uADF8\uB7A8 - \uBE44\uBC00\uBC88\uD638 \uCC3E\uAE30");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		setResizable(false);	//â ũ�� ����
		setBounds(100, 100, 510, 194);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//�н����� ã�� �г�
		JPanel panel = new JPanel();
		panel.setBounds(12, 10, 472, 139);
		contentPane.add(panel);
		panel.setLayout(null);
		
		//�н����� ã�� ��ư
		JButton findPasswordButton = new JButton("\uBE44\uBC00\uBC88\uD638 \uCC3E\uAE30");
		findPasswordButton.setBounds(120, 80, 150, 35);
		panel.add(findPasswordButton);
		findPasswordButton.setFont(new Font("���Ļ�浸��", Font.PLAIN, 15));
		findPasswordButton.addMouseListener(new MouseAdapter() {	//��й�ȣ ã�� ��ư�� ������ ȣ��Ǵ� �޼ҵ�
			@Override
			public void mouseClicked(MouseEvent e) {
				int flag = 0; // 1�̸� ã�� ����, 0�̸� ã������
				String pw = "";	//��й�ȣ�� ã���� ������ ����
				try { // DB ����

					ResultSet rs = dbConn.executeQuery("select USER_MAIL, USER_PW from USER"); // USER ���̺��� MAIL�� PW �˻�
					
					//������ ���
					while (rs.next()) {
						if (EmailTextField.getText().equals(rs.getString("USER_MAIL"))) // �̸� â�� �Է��� �̸��� ��ġ�ϴ� �̸��� DB�� ������
							{
								flag = 1; // ã�� ����
								pw = rs.getString("USER_PW");	//ã�� ��й�ȣ ����
								break;
							}
					}
				}catch (SQLException e2) {
					System.out.println("SQL ���� ����");
				}
				if (flag == 1) {	//ã������ ���̵� �޽����� ���
					JOptionPane.showMessageDialog(null,"����� ��й�ȣ�� "+pw+" �Դϴ�.","��й�ȣ ã�� ����!",JOptionPane.INFORMATION_MESSAGE);
				}
				else //ã�� �������� ���� �޽��� ���
					JOptionPane.showMessageDialog(null,"�̸����� �������� �ʽ��ϴ�.","��й�ȣ ã�� ����",JOptionPane.ERROR_MESSAGE);
			}
		});
	
		
		//�̸��� �ؽ�Ʈ�ʵ�
		EmailTextField = new JTextField();
		EmailTextField.setBounds(119, 21, 312, 21);
		panel.add(EmailTextField);
		EmailTextField.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		EmailTextField.setColumns(10);
		
		//�̸��� ��
		JLabel userEmailLabel = new JLabel("\uC774\uBA54\uC77C");
		userEmailLabel.setBounds(23, 23, 50, 15);
		panel.add(userEmailLabel);
		userEmailLabel.setFont(new Font("���Ļ�浸��", Font.PLAIN, 15));
	}
}
