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
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JScrollBar;
import javax.swing.AbstractListModel;
import javax.swing.border.CompoundBorder;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

public class SearchBook extends JFrame {

	private JPanel contentPane;
	private JTextField searchTextField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchBook frame = new SearchBook();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 도서 찾기 메인화면
	 */
	public SearchBook() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 881, 706);
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
				
				JPanel panel = new JPanel();
				panel.setBounds(22, 57, 819, 38);
				contentPane.add(panel);
				panel.setLayout(null);
				
				JComboBox searchComboBox = new JComboBox();
				searchComboBox.setBackground(Color.WHITE);
				searchComboBox.setBounds(0, 0, 129, 38);
				panel.add(searchComboBox);
				
				searchTextField = new JTextField();
				searchTextField.setBounds(129, 0, 569, 38);
				panel.add(searchTextField);
				searchTextField.setColumns(10);
				
				JButton searchButton = new JButton("\uAC80\uC0C9");
				searchButton.setFont(new Font("함초롬돋움", Font.BOLD, 14));
				searchButton.setBounds(690, 0, 129, 38);
				panel.add(searchButton);
				
				JPanel panel_1 = new JPanel();
				panel_1.setBounds(169, 112, 672, 520);
				contentPane.add(panel_1);
				panel_1.setLayout(null);
				
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(0, 0, 672, 520);
				panel_1.add(scrollPane);
				
				//테이블
				table = new JTable();
				table.setBackground(new Color(245, 245, 245));
				scrollPane.setViewportView(table);
				table.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 14));
				table.setModel(new DefaultTableModel(
					new Object[][] {
						{"\uC790\uBC14 \uD504\uB85C\uADF8\uB798\uBC0D", "\uD669\uAE30\uD0DC", "\uC0DD\uB2A5\uCD9C\uD310\uC0AC", "\uD504\uB85C\uADF8\uB798\uBC0D", "N"},
						{"", null, null, null, null},
						{"", null, null, null, null},
						{"", null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null},
					},
					new String[] {
						"\uC81C\uBAA9", "\uC800\uC790", "\uCD9C\uD310\uC0AC", "\uCE74\uD14C\uACE0\uB9AC", "\uB300\uCD9C\uC5EC\uBD80"
					}
				) {
					Class[] columnTypes = new Class[] {
						String.class, Object.class, Object.class, Object.class, Object.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
				table.getColumnModel().getColumn(0).setPreferredWidth(110);
				table.getColumnModel().getColumn(1).setPreferredWidth(101);
				table.getColumnModel().getColumn(2).setPreferredWidth(106);
				table.getColumnModel().getColumn(3).setPreferredWidth(115);
				table.getColumnModel().getColumn(4).setPreferredWidth(115);
				
				JPanel panel_2 = new JPanel();
				panel_2.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
				panel_2.setBounds(22, 112, 135, 520);
				contentPane.add(panel_2);
	}
}
