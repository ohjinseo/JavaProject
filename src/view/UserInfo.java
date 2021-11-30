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

public class UserInfo extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;

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
		
		JLabel userPictureLabel = new JLabel("\uC720\uC800 \uC0AC\uC9C4");
		userPictureLabel.setBackground(new Color(255, 250, 250));
		userPictureLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userPictureLabel.setBounds(22, 71, 186, 225);
		contentPane.add(userPictureLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(128, 128, 128), 2));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(233, 71, 606, 351);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel userNameLabel = new JLabel("\uC774\uB984 ");
		userNameLabel.setFont(new Font("\uD55C\uCEF4\uC0B0\uB73B\uB3CB\uC6C0", userNameLabel.getFont().getStyle() | Font.BOLD, userNameLabel.getFont().getSize() + 3));
		userNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		
		userNameLabel.setBackground(new Color(255, 255, 255));
		userNameLabel.setBounds(20, 10, 49, 30);
		panel.add(userNameLabel);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("\uD55C\uCEF4\uC0B0\uB73B\uB3CB\uC6C0", textField.getFont().getStyle() | Font.BOLD, textField.getFont().getSize() + 3));
		textField.setText("\uC624\uC9C4\uC11C");
		textField.setBounds(113, 12, 239, 30);
		panel.add(textField);
		textField.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(128, 128, 128), 2));
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(22, 432, 817, 213);
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
		btnNewButton.setBounds(340, 162, 117, 39);
		panel_1.add(btnNewButton);
	}

}
