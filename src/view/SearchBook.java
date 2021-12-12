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
import javax.swing.SwingUtilities;
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
import java.awt.SystemColor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.*;
import java.util.Properties;

public class SearchBook extends JFrame {
	dbConnector dbConn = new dbConnector();
	public String user_phone = "";
	public Boolean manager = false;
	private JPanel contentPane;
	private JTextField searchTextField;
	private JTable table;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();

	private Main mainFrame;
	private UserInfo userInfoFrame;

	/**
	 * 도서 찾기 메인화면
	 */
	public SearchBook(String user_phone, Boolean manager) {
		this.user_phone = user_phone;
		this.manager = manager;
		setTitle("\uB3C4\uC11C \uAD00\uB9AC \uD504\uB85C\uADF8\uB7A8 - \uB3C4\uC11C\uAC80\uC0C9");
		setBounds(100, 100, 881, 706);
		contentPane = new JPanel(); // 메인 프레임
		contentPane.setBackground(SystemColor.menu);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false); // 창 크기 고정
		setLocationRelativeTo(null); // 중앙에 출력

		// 메뉴바
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 865, 47);
		menuBar.setBorderPainted(false);
		menuBar.setBackground(new Color(51, 51, 51));
		contentPane.add(menuBar);

		// 홈아이콘 메뉴
		JMenu homeIconMenu = new JMenu(" 홈 ");
		homeIconMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mainFrame = new Main(user_phone, manager);
				mainFrame.setVisible(true);
				setVisible(false);
			}
		});
		homeIconMenu.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		homeIconMenu.setForeground(new Color(255, 255, 255));
		ImageIcon icon5 = new ImageIcon("D:\\\uC0C8 \uD3F4\uB354\\pngegg.png");
		Image img5 = icon5.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon changeIcon5 = new ImageIcon(img5);
		homeIconMenu.setIcon(changeIcon5);
		menuBar.add(homeIconMenu);

		// 도서 찾기 메뉴
		JMenu findBookMenu = new JMenu("도서찾기"); // 메뉴 - 도서찾기
		findBookMenu.setForeground(new Color(255, 255, 255));
		findBookMenu.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		menuBar.add(findBookMenu);

		// 회원 정보 메뉴
		JMenu userInfoMenu = new JMenu("회원정보");
		userInfoMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				userInfoFrame = new UserInfo(user_phone, manager);
				userInfoFrame.setLocationRelativeTo(null); // 중앙에 출력
				userInfoFrame.setVisible(true);
				setVisible(false);
			}
		});
		userInfoMenu.setForeground(new Color(255, 255, 255));
		userInfoMenu.setBackground(new Color(230, 230, 250));
		userInfoMenu.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		menuBar.add(userInfoMenu);

		// 도서추가 메뉴 (관리자만)
		if (manager) {
			JMenu search_user_menu = new JMenu("회원검색");
			search_user_menu.addMouseListener(new MouseAdapter() { // 마우스 클릭 이벤트 발생시 호출
				@Override
				public void mouseClicked(MouseEvent e) {
					SearchUser search_user_frame = new SearchUser(user_phone, manager); // 유저검색창 호출
					search_user_frame.setLocationRelativeTo(null); // 중앙에 출력
					search_user_frame.setResizable(false); // 창 크기 고정
					search_user_frame.setVisible(true);
					setVisible(false);
					// 유저검색 창에서 창을 닫으면 호출되는 메소드
					search_user_frame.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							e.getWindow().dispose();
						}
					});
				}
			});
			search_user_menu.setForeground(new Color(255, 255, 255));
			search_user_menu.setBackground(new Color(230, 230, 250));
			search_user_menu.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
			menuBar.add(search_user_menu);

			JMenu add_book_menu = new JMenu("도서추가");
			add_book_menu.addMouseListener(new MouseAdapter() { // 마우스 클릭 이벤트 발생시 호출
				@Override
				public void mouseClicked(MouseEvent e) {
					AddBook add_book_frame = new AddBook(user_phone, manager); // 도서추가창 호출
					add_book_frame.setLocationRelativeTo(null); // 중앙에 출력
					add_book_frame.setResizable(false); // 창 크기 고정
					add_book_frame.setVisible(true);
					// 도서추가 창에서 창을 닫으면 호출되는 메소드
					add_book_frame.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							e.getWindow().dispose();
						}
					});
				}
			});
			add_book_menu.setForeground(new Color(255, 255, 255));
			add_book_menu.setBackground(new Color(230, 230, 250));
			add_book_menu.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
			menuBar.add(add_book_menu);
		}

		// 검색 패널
		JPanel panel = new JPanel();
		panel.setBounds(22, 57, 819, 38);
		contentPane.add(panel);
		panel.setLayout(null);

		// 검색 항목 콤보박스
		JComboBox searchComboBox = new JComboBox();
		searchComboBox.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 14));
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
		JButton searchButton = new JButton("검색");
		searchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 검색 버튼 눌렀을때 작동되는 리스너
				try { // DB 접근
					String search_how = "전체"; // 검색 조건이 들어갈 search_how (제목, 저자..)
					ResultSet rs;
					switch (searchComboBox.getSelectedItem().toString()) {
					case "제목": // 검색조건이 "제목"일 때
						search_how = "TITLE";
						break;
					case "저자": // 검색조건이 "저자"일 때
						search_how = "AUTHOR";
						break;
					case "출판사": // 검색조건이 "출판사"일 때
						search_how = "PUB";
						break;
					case "전체": // 검색조건이 "전체"일 때
						search_how = "TITLE LIKE '" + searchTextField.getText() + "%' OR BOOK_AUTHOR LIKE '"
								+ searchTextField.getText() + "%' OR BOOK_PUB";
						break;
					}
					rs = dbConn.executeQuery("SELECT BOOK_TITLE, BOOK_AUTHOR, BOOK_PUB, BOOK_CATEGORY, BOOK_ISBN\r\n"
							+ "FROM BOOK\r\n" + "WHERE (BOOK_" + search_how + " LIKE '" + searchTextField.getText()
							+ "%')AND BOOK_PRE = TRUE;"); // DB에서 검생창에 입력된 값으로 책 정보 검색

					if (rs != null) // 검색결과가 있으면
						set_table(rs); // 테이블 재구성
					else // 없으면
						System.out.println("검색결과가 없습니다.");
				} catch (SQLException e2) {
					System.out.println("SQL 실행 에러");
				}
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
		// 테이블을 클릭하면 호출되는 메소드
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow(); // 선택한 row
				int col = table.getSelectedColumn(); // 선택한 col
				String book_title = table.getModel().getValueAt(row, 0).toString(); // 클릭한 열의 책 제목을 저장
				String book_ISBN = ""; // 클릭한 책의 ISBN을 저장할 변수
				try { // DB 접근

					ResultSet rs = dbConn.executeQuery("SELECT BOOK_ISBN\r\n" + "FROM BOOK\r\n" + "WHERE BOOK_TITLE ='"
							+ book_title + "' AND BOOK_PRE = TRUE;"); // DB에서 책 제목으로 ISBN 검색
					while (rs.next()) {
						book_ISBN = rs.getString("BOOK_ISBN"); // ISBN 저장
					}
				} catch (SQLException e2) {
					System.out.println("SQL 실행 에러");
				}
				if (manager) {
					EditableBookInfo manager_book_info = new EditableBookInfo(book_ISBN, user_phone, manager);
					// 책정보창에서 창을 닫으면 호출되는 메소드
					manager_book_info.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							e.getWindow().dispose();
							try { // DB 접근
								ResultSet rs = dbConn.executeQuery(
										"SELECT BOOK_TITLE, BOOK_AUTHOR, BOOK_PUB, BOOK_CATEGORY, BOOK_ISBN FROM BOOK WHERE BOOK_PRE = TRUE;");
								set_table(rs); // 관리자를 제외한 회원들의 정보로 테이블을 구성
							} catch (SQLException e1) {
								System.out.println("회원검색창 초기 테이블 구성중 SQL 실행 에러");
							}
						}
					});

					manager_book_info.setLocationRelativeTo(null); // 화면중앙에 출력
					manager_book_info.setVisible(true); // 책 정보창 띄움
				} else {
					BookInfo bookinfo = new BookInfo(book_ISBN, user_phone); // 책 정보창 객체 생성 (매개변수 : 클릭한 책의 ISBN)
					// 책정보창에서 창을 닫으면 호출되는 메소드
					bookinfo.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							e.getWindow().dispose();
						}
					});

					bookinfo.setLocationRelativeTo(null); // 화면중앙에 출력
					bookinfo.setVisible(true); // 책 정보창 띄움
				}
			}
		});

		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);
		table.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 14));

		String[] columns = { "제목", "저자", "출판사", "카테고리", "대출여부 (Y/N)" }; // 테이블의 구성
		String[][] data;
		DefaultTableModel model = new DefaultTableModel(null, columns);
		table.setModel(model); // 테이블 세팅

		try { // DB 접근
			//db에 있는 책 정보 검색
			ResultSet rs = dbConn.executeQuery(
					"SELECT BOOK_TITLE, BOOK_AUTHOR, BOOK_PUB, BOOK_CATEGORY, BOOK_ISBN FROM BOOK WHERE BOOK_PRE = TRUE;");
			set_table(rs);
		} catch (SQLException e) {
			System.out.println("SQL 실행 에러");
		}
		// 간격 조절
		table.getColumnModel().getColumn(0).setPreferredWidth(110);
		table.getColumnModel().getColumn(1).setPreferredWidth(101);
		table.getColumnModel().getColumn(2).setPreferredWidth(106);
		table.getColumnModel().getColumn(3).setPreferredWidth(115);
		table.getColumnModel().getColumn(4).setPreferredWidth(115);

		// 검색 세부사항 패널
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.text);
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
		JCheckBox oneCheckBox = new JCheckBox("\uD504\uB85C\uADF8\uB798\uBC0D");
		oneCheckBox.setBackground(Color.WHITE);
		oneCheckBox.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		oneCheckBox.setBounds(8, 36, 107, 23);
		panel_2.add(oneCheckBox);

		// 카테고리 체크박스
		JCheckBox twoCheckBox = new JCheckBox("\uACFC\uD559");
		twoCheckBox.setBackground(Color.WHITE);
		twoCheckBox.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		twoCheckBox.setBounds(8, 56, 107, 23);
		panel_2.add(twoCheckBox);

		// 카테고리 체크박스
		JCheckBox threeCheckBox = new JCheckBox("\uBB38\uD559");
		threeCheckBox.setBackground(Color.WHITE);
		threeCheckBox.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		threeCheckBox.setBounds(8, 81, 107, 23);
		panel_2.add(threeCheckBox);

		// 카테고리 체크박스
		JCheckBox fourCheckBox = new JCheckBox("\uC778\uBB38 / \uC0AC\uD68C");
		fourCheckBox.setBackground(Color.WHITE);
		fourCheckBox.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		fourCheckBox.setBounds(8, 106, 107, 23);
		panel_2.add(fourCheckBox);

		// 카테고리 체크박스
		JCheckBox fiveCheckBox = new JCheckBox("\uBE44\uC988\uB2C8\uC2A4\uC640 \uACBD\uC81C");
		fiveCheckBox.setBackground(Color.WHITE);
		fiveCheckBox.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		fiveCheckBox.setBounds(8, 131, 107, 23);
		panel_2.add(fiveCheckBox);

		// 카테고리 체크박스
		JCheckBox sixCheckBox = new JCheckBox("\uB9CC\uD654");
		sixCheckBox.setBackground(Color.WHITE);
		sixCheckBox.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		sixCheckBox.setBounds(8, 156, 107, 23);
		panel_2.add(sixCheckBox);

		// 카테고리 체크박스
		JCheckBox sevenCheckBox = new JCheckBox("\uC5B4\uB9B0\uC774 / \uCCAD\uC18C\uB144");
		sevenCheckBox.setBackground(Color.WHITE);
		sevenCheckBox.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		sevenCheckBox.setBounds(8, 181, 107, 23);
		panel_2.add(sevenCheckBox);

		// 카테고리 체크박스
		JCheckBox eightCheckBox = new JCheckBox("\uC218\uD5D8\uC11C / \uC790\uACA9\uC99D");
		eightCheckBox.setBackground(Color.WHITE);
		eightCheckBox.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		eightCheckBox.setBounds(8, 204, 107, 23);
		panel_2.add(eightCheckBox);

		// 카테고리 체크박스
		JCheckBox nineCheckBox = new JCheckBox("\uC608\uC220 ");
		nineCheckBox.setBackground(Color.WHITE);
		nineCheckBox.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		nineCheckBox.setBounds(8, 228, 107, 23);
		panel_2.add(nineCheckBox);

		// 분류 라벨
		JLabel sortLabel = new JLabel("\uC815\uB82C");
		sortLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 15));
		sortLabel.setBounds(12, 267, 70, 20);
		panel_2.add(sortLabel);

		// 최신순 라디오버튼
		JRadioButton recentRadioButton = new JRadioButton("\uCD5C\uC2E0\uC21C");
		recentRadioButton.setBackground(Color.WHITE);
		buttonGroup_1.add(recentRadioButton);
		recentRadioButton.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		recentRadioButton.setBounds(12, 293, 113, 23);
		panel_2.add(recentRadioButton);

		// 인기순 라디오버튼
		JRadioButton popularityRadioButton = new JRadioButton("\uC778\uAE30\uC21C");
		popularityRadioButton.setBackground(Color.WHITE);
		buttonGroup_1.add(popularityRadioButton);
		popularityRadioButton.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		popularityRadioButton.setBounds(12, 318, 113, 23);
		panel_2.add(popularityRadioButton);

		// 평점순 라디오 버튼
		JRadioButton gradeRadioButton = new JRadioButton("\uD3C9\uC810\uC21C");
		gradeRadioButton.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		buttonGroup_1.add(gradeRadioButton);
		gradeRadioButton.setBackground(Color.WHITE);
		gradeRadioButton.setBounds(12, 343, 113, 23);
		panel_2.add(gradeRadioButton);

		// 대출여부 라벨
		JLabel canBorrowLabel = new JLabel("\uB300\uC5EC\uC5EC\uBD80");
		canBorrowLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 15));
		canBorrowLabel.setBounds(12, 378, 70, 20);
		panel_2.add(canBorrowLabel);

		// 대출가능 라디오버튼
		JRadioButton canborrowRadioButton = new JRadioButton("\uB300\uCD9C\uAC00\uB2A5");
		canborrowRadioButton.setBackground(Color.WHITE);
		buttonGroup_2.add(canborrowRadioButton);
		canborrowRadioButton.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		canborrowRadioButton.setBounds(12, 404, 113, 23);
		panel_2.add(canborrowRadioButton);

		// 대출중 라디오버튼
		JRadioButton borrowingNewRadioButton = new JRadioButton("\uB300\uCD9C\uC911");
		borrowingNewRadioButton.setBackground(Color.WHITE);
		buttonGroup_2.add(borrowingNewRadioButton);
		borrowingNewRadioButton.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		borrowingNewRadioButton.setBounds(12, 429, 113, 23);
		panel_2.add(borrowingNewRadioButton);

	}

	// ResultSet을 받아 테이블 재구성하는 함수
	public void set_table(ResultSet rs) throws SQLException {
		try {

			int row = 0;
			if (rs.last()) { // 커서를 마지막으로 이동
				row = rs.getRow(); // row에 현재 row의 인덱스를 저장(총 row의 개수를 저장)
				rs.beforeFirst(); // 다시 앞으로 이동시킴
			}

			String[][] data = new String[row][5]; // 테이블에 넣을 데이터를 저장할 배열
			int i = 0;
			// 일은 데이터로 테이블 구성
			while (rs.next()) {
				data[i][0] = rs.getString("BOOK_TITLE");
				data[i][1] = rs.getString("BOOK_AUTHOR");
				data[i][2] = rs.getString("BOOK_PUB");
				data[i][3] = rs.getString("BOOK_CATEGORY");
				String book_ISBN = rs.getString("BOOK_ISBN");
				//해당 책이 대출중인 도서인지 검색
				ResultSet rs_rent = dbConn.executeQuery(
						"SELECT BOOK_ISBN FROM RENT WHERE BOOK_ISBN='" + book_ISBN + "' AND RENT_RETURN_YN IS NULL;"); 
				if (rs_rent.next()) {
					if (book_ISBN.equals(rs_rent.getString("BOOK_ISBN"))) { // 검색해서 나온 ISBN과 해당 책의 ISBN이 같으면 (대출중인 도서이면)
						data[i][4] = "대출중";
					}
				} else {
					data[i][4] = "대출가능";
				}
				i++;
			}
			String[] columns = { "제목", "저자", "출판사", "카테고리", "대출여부" }; // 테이블의 구성
			table.setModel(new DefaultTableModel(data, columns)); // 테이들 다시 세팅
		} catch (SQLException e) {
			System.out.println("SQL 실행 에러");
		}
	}
}
