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

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 301);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(SystemColor.menu);
		panel.setBounds(12, 89, 475, 113);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel idLabel = new JLabel("\uC544\uC774\uB514");
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);
		idLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 16));
		idLabel.setBounds(12, 10, 80, 36);
		panel.add(idLabel);
		
		JLabel passwordLabel = new JLabel("\uBE44\uBC00\uBC88\uD638");
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 16));
		passwordLabel.setBounds(12, 60, 80, 36);
		panel.add(passwordLabel);
		
		textField = new JTextField();
		textField.setBackground(new Color(245, 245, 245));
		textField.setBounds(102, 17, 267, 28);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBackground(new Color(245, 245, 245));
		textField_1.setColumns(10);
		textField_1.setBounds(104, 67, 265, 28);
		panel.add(textField_1);
		
		JButton loginButton = new JButton("Login");
		loginButton.setFont(new Font("한컴산뜻돋움", Font.BOLD, 16));
		loginButton.setBounds(378, 17, 85, 79);
		panel.add(loginButton);
		
		JLabel loginLabel = new JLabel("\uB85C\uADF8\uC778");
		loginLabel.setForeground(SystemColor.desktop);
		loginLabel.setBackground(Color.WHITE);
		loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 24));
		loginLabel.setBounds(154, 30, 172, 49);
		contentPane.add(loginLabel);
		
		JButton findIdButton = new JButton("\uC544\uC774\uB514 \uCC3E\uAE30");
		findIdButton.setFont(new Font("한컴산뜻돋움", Font.BOLD, 12));
		findIdButton.setBounds(36, 212, 113, 35);
		contentPane.add(findIdButton);
		
		JButton findPasswordButton = new JButton("\uBE44\uBC00\uBC88\uD638 \uCC3E\uAE30");
		findPasswordButton.setFont(new Font("한컴산뜻돋움", Font.BOLD, 12));
		findPasswordButton.setBounds(195, 212, 113, 35);
		contentPane.add(findPasswordButton);
		
		JButton signupButton = new JButton("\uD68C\uC6D0\uAC00\uC785");
		signupButton.setFont(new Font("한컴산뜻돋움", Font.BOLD, 12));
		signupButton.setBounds(357, 212, 113, 35);
		contentPane.add(signupButton);
	}
}
