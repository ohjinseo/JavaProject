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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class SearchBook extends JFrame {

	private JPanel contentPane;
	private JTextField searchTextField;
	private JTable table;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();

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
		contentPane = new JPanel(); // 메인 프레임
		contentPane.setBackground(new Color(245, 245, 245));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 메뉴바
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 865, 47);
		menuBar.setBorderPainted(false);
		menuBar.setBackground(new Color(51, 51, 51));
		contentPane.add(menuBar);

		// 홈아이콘 메뉴
		JMenu homeIconMenu = new JMenu(" \uD648 ");
		homeIconMenu.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		homeIconMenu.setForeground(new Color(255, 255, 255));
		ImageIcon icon5 = new ImageIcon("D:\\\uC0C8 \uD3F4\uB354\\pngegg.png");
		Image img5 = icon5.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon changeIcon5 = new ImageIcon(img5);
		homeIconMenu.setIcon(changeIcon5);
		menuBar.add(homeIconMenu);

		// 도서 찾기 메뉴
		JMenu findBookMenu = new JMenu("\uB3C4\uC11C\uCC3E\uAE30"); // 메뉴 - 도서찾기
		findBookMenu.setForeground(new Color(255, 255, 255));
		findBookMenu.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		menuBar.add(findBookMenu);

		// 회원 정보 메뉴
		JMenu userInfoMenu = new JMenu("\uD68C\uC6D0\uC815\uBCF4");
		userInfoMenu.setForeground(new Color(255, 255, 255));
		userInfoMenu.setBackground(new Color(230, 230, 250));
		userInfoMenu.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		menuBar.add(userInfoMenu);

		// 검색 패널
		JPanel panel = new JPanel();
		panel.setBounds(22, 57, 819, 38);
		contentPane.add(panel);
		panel.setLayout(null);

		// 검색 항목 콤보박스
		JComboBox searchComboBox = new JComboBox();
		searchComboBox.setModel(new DefaultComboBoxModel(
				new String[] { "\uC804\uCCB4", "\uC81C\uBAA9", "\uC800\uC790", "\uCD9C\uD310\uC0AC" }));
		searchComboBox.setBackground(Color.WHITE);
		searchComboBox.setBounds(0, 0, 129, 38);
		panel.add(searchComboBox);

		// 검색 입력 영역 텍스트필드
		searchTextField = new JTextField();
		searchTextField.setBounds(129, 0, 569, 38);
		panel.add(searchTextField);
		searchTextField.setColumns(10);

		// 검색 버튼
		JButton searchButton = new JButton("\uAC80\uC0C9");
		searchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 검색 버튼 눌렀을때 작동되는 리스너
			}
		});
		searchButton.setFont(new Font("함초롬돋움", Font.BOLD, 14));
		searchButton.setBounds(690, 0, 129, 38);
		panel.add(searchButton);

		// 검색 목록 패널
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(169, 112, 672, 520);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		// 검색 목록 스크롤 패널
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 672, 520);
		panel_1.add(scrollPane);

		// 테이블
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				for (int i = 0; i < table.getColumnCount(); i++) {
					BookInfo bookinfo = new BookInfo();
					bookinfo.setVisible(true);
				}
			}
		});
		table.setBackground(new Color(245, 245, 245));
		scrollPane.setViewportView(table);
		table.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 14));
		table.setModel(new DefaultTableModel(new Object[][] {
				{ "\uC790\uBC14 \uD504\uB85C\uADF8\uB798\uBC0D", "\uD669\uAE30\uD0DC", "\uC0DD\uB2A5\uCD9C\uD310\uC0AC",
						"\uD504\uB85C\uADF8\uB798\uBC0D", "N" },
				{ "", null, null, null, null }, { "", null, null, null, null }, { "", null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, },
				new String[] { "\uC81C\uBAA9", "\uC800\uC790", "\uCD9C\uD310\uC0AC", "\uCE74\uD14C\uACE0\uB9AC",
						"\uB300\uCD9C\uC5EC\uBD80" }) {
			Class[] columnTypes = new Class[] { String.class, Object.class, Object.class, Object.class, Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(110);
		table.getColumnModel().getColumn(1).setPreferredWidth(101);
		table.getColumnModel().getColumn(2).setPreferredWidth(106);
		table.getColumnModel().getColumn(3).setPreferredWidth(115);
		table.getColumnModel().getColumn(4).setPreferredWidth(115);

		// 검색 세부사항 패널
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		panel_2.setBounds(22, 112, 135, 520);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		// 검색 카테고리 라벨
		JLabel categoryLabel = new JLabel("\uCE74\uD14C\uACE0\uB9AC");
		categoryLabel.setBounds(12, 10, 70, 20);
		categoryLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 15));
		panel_2.add(categoryLabel);

		// 카테고리 체크박스
		JCheckBox oneCheckBox = new JCheckBox("New check box");
		oneCheckBox.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		oneCheckBox.setBounds(8, 36, 107, 23);
		panel_2.add(oneCheckBox);

		// 카테고리 체크박스
		JCheckBox twoCheckBox = new JCheckBox("New check box");
		twoCheckBox.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		twoCheckBox.setBounds(8, 56, 107, 23);
		panel_2.add(twoCheckBox);

		// 카테고리 체크박스
		JCheckBox threeCheckBox = new JCheckBox("New check box");
		threeCheckBox.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		threeCheckBox.setBounds(8, 81, 107, 23);
		panel_2.add(threeCheckBox);

		// 카테고리 체크박스
		JCheckBox fourCheckBox = new JCheckBox("New check box");
		fourCheckBox.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		fourCheckBox.setBounds(8, 106, 107, 23);
		panel_2.add(fourCheckBox);

		// 카테고리 체크박스
		JCheckBox fiveCheckBox = new JCheckBox("New check box");
		fiveCheckBox.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		fiveCheckBox.setBounds(8, 131, 107, 23);
		panel_2.add(fiveCheckBox);

		// 카테고리 체크박스
		JCheckBox sixCheckBox = new JCheckBox("New check box");
		sixCheckBox.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		sixCheckBox.setBounds(8, 156, 107, 23);
		panel_2.add(sixCheckBox);

		// 카테고리 체크박스
		JCheckBox sevenCheckBox = new JCheckBox("New check box");
		sevenCheckBox.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		sevenCheckBox.setBounds(8, 181, 107, 23);
		panel_2.add(sevenCheckBox);

		// 카테고리 체크박스
		JCheckBox eightCheckBox = new JCheckBox("New check box");
		eightCheckBox.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		eightCheckBox.setBounds(8, 204, 107, 23);
		panel_2.add(eightCheckBox);

		// 카테고리 체크박스
		JCheckBox nineCheckBox = new JCheckBox("New check box");
		nineCheckBox.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		nineCheckBox.setBounds(8, 228, 107, 23);
		panel_2.add(nineCheckBox);

		// 분류 라벨
		JLabel sortLabel = new JLabel("\uC815\uB82C");
		sortLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 15));
		sortLabel.setBounds(12, 253, 70, 20);
		panel_2.add(sortLabel);

		// 최신순 라디오버튼
		JRadioButton recentRadioButton = new JRadioButton("\uCD5C\uC2E0\uC21C");
		buttonGroup_1.add(recentRadioButton);
		recentRadioButton.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		recentRadioButton.setBounds(12, 281, 113, 23);
		panel_2.add(recentRadioButton);

		// 인기순 라디오버튼
		JRadioButton popularityRadioButton = new JRadioButton("\uC778\uAE30\uC21C");
		buttonGroup_1.add(popularityRadioButton);
		popularityRadioButton.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		popularityRadioButton.setBounds(12, 306, 113, 23);
		panel_2.add(popularityRadioButton);

		// 대출여부 라벨
		JLabel canBorrowLabel = new JLabel("\uB300\uC5EC\uC5EC\uBD80");
		canBorrowLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 15));
		canBorrowLabel.setBounds(12, 335, 70, 20);
		panel_2.add(canBorrowLabel);

		// 대출가능 라디오버튼
		JRadioButton canborrowRadioButton = new JRadioButton("\uB300\uCD9C\uAC00\uB2A5");
		buttonGroup_2.add(canborrowRadioButton);
		canborrowRadioButton.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		canborrowRadioButton.setBounds(12, 361, 113, 23);
		panel_2.add(canborrowRadioButton);

		// 대출중 라디오버튼
		JRadioButton borrowingNewRadioButton = new JRadioButton("\uB300\uCD9C\uC911");
		buttonGroup_2.add(borrowingNewRadioButton);
		borrowingNewRadioButton.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		borrowingNewRadioButton.setBounds(12, 386, 113, 23);
		panel_2.add(borrowingNewRadioButton);
	}
}
