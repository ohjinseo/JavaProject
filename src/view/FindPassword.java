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

public class FindPassword extends JFrame {

	private JPanel contentPane;
	
	//프레임 변수
	private Main mainFrame;
	private JTextField EmailTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FindPassword frame = new FindPassword();
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
	public FindPassword() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 194);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//아이디 찾기 패널
		JPanel panel = new JPanel();
		panel.setBounds(12, 10, 472, 139);
		contentPane.add(panel);
		panel.setLayout(null);
		
		//아이디 찾기 버튼
		JButton findPasswordButton = new JButton("\uBE44\uBC00\uBC88\uD638 \uCC3E\uAE30");
		findPasswordButton.setBounds(120, 80, 150, 35);
		panel.add(findPasswordButton);
		findPasswordButton.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 15));
		
		//이메일 텍스트필드
		EmailTextField = new JTextField();
		EmailTextField.setBounds(119, 21, 312, 21);
		panel.add(EmailTextField);
		EmailTextField.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		EmailTextField.setColumns(10);
		
		//이메일 라벨
		JLabel userEmailLabel = new JLabel("\uC774\uBA54\uC77C");
		userEmailLabel.setBounds(23, 23, 50, 15);
		panel.add(userEmailLabel);
		userEmailLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 15));
	}
}
