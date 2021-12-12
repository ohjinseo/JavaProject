package view;

import java.awt.*;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.event.MenuListener;
import javax.swing.event.MenuEvent;
import javax.imageio.ImageIO;
import java.awt.*;

public class Main extends JFrame {
	public dbConnector dbConn = new dbConnector();
	public String user_phone = ""; // 유저 PK 정보를 저장할 변수
	public Boolean manager = false; // 유저가 관리자인지 확인할 변수
	private JPanel contentPane;
	BookInfo bookInfoFrame;
	SearchUser searchUserFrame;
	SearchBook searchBookFrame;
	UserInfo userInfoFrame;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Main(String user_phone, Boolean manager) {
		this.user_phone = user_phone; // 로그인한 유저의 PK
		this.manager = manager;
		setTitle("\uB3C4\uC11C \uAD00\uB9AC \uD504\uB85C\uADF8\uB7A8 - \uBA54\uC778");
		setBounds(100, 100, 881, 694);
		contentPane = new JPanel(); // 메인 프레임
		contentPane.setBackground(SystemColor.menu);
		setContentPane(contentPane);
		contentPane.setLayout(null); // 메인 프레임 레이아웃 null로 설정
		setResizable(false); // 창 크기 고정
		setLocationRelativeTo(null); // 중앙에 출력

		// 메뉴바
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.setBackground(new Color(51, 51, 51));
		menuBar.setBounds(0, 0, 865, 47);
		contentPane.add(menuBar);

		// 홈아이콘 메뉴
		JMenu homeIconMenu = new JMenu(" \uD648 ");
		homeIconMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

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
		JMenu findBookMenu = new JMenu("\uB3C4\uC11C\uCC3E\uAE30"); // 메뉴 - 도서찾기
		findBookMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				searchBookFrame = new SearchBook(user_phone, manager);
				searchBookFrame.setVisible(true);
				setVisible(false);

			}
		});

		findBookMenu.setForeground(new Color(255, 255, 255));
		findBookMenu.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		menuBar.add(findBookMenu);

		// 회원 정보 메뉴
		JMenu userInfoMenu = new JMenu("\uD68C\uC6D0\uC815\uBCF4");
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

		// 관리자만 보이는 메뉴
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

		// 인기도서 라벨
		JLabel popularBookLabel = new JLabel("\uC778\uAE30\uB3C4\uC11C");
		popularBookLabel.setForeground(new Color(51, 51, 51));
		popularBookLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		popularBookLabel.setBounds(22, 63, 123, 40);
		contentPane.add(popularBookLabel);

		// 인기도서 패널
		JPanel popularBookPanel = new JPanel();
		popularBookPanel.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		popularBookPanel.setBackground(Color.WHITE);
		popularBookPanel.setBounds(22, 101, 820, 125);
		popularBookPanel.setLayout(null);

		JLabel[] popularBookLabel_array = new JLabel[6];
		// 패널에 출력할 책 이미지를 담을 Label 6개 생성
		JLabel popular_img1 = new JLabel("popular_img1");
		popular_img1.setBounds(12, 10, 105, 105);
		popularBookPanel.add(popular_img1);
		popularBookLabel_array[0] = popular_img1;

		JLabel popular_img2 = new JLabel("popular_img2");
		popular_img2.setBounds(150, 10, 105, 105);
		popularBookPanel.add(popular_img2);
		popularBookLabel_array[1] = popular_img2;

		JLabel popular_img3 = new JLabel("popular_img3");
		popular_img3.setBounds(288, 10, 105, 105);
		popularBookPanel.add(popular_img3);
		popularBookLabel_array[2] = popular_img3;

		JLabel popular_img4 = new JLabel("popular_img4");
		popular_img4.setBounds(426, 10, 105, 105);
		;
		popularBookPanel.add(popular_img4);
		popularBookLabel_array[3] = popular_img4;

		JLabel popular_img5 = new JLabel("popular_img5");
		popular_img5.setBounds(564, 10, 105, 105);
		popularBookPanel.add(popular_img5);
		popularBookLabel_array[4] = popular_img5;

		JLabel popular_img6 = new JLabel("popular_img6");
		popular_img6.setBounds(703, 10, 105, 105);
		popularBookPanel.add(popular_img6);
		popularBookLabel_array[5] = popular_img6;

		// 신간도서 라벨
		JLabel newlyBookLabel = new JLabel("\uC2E0\uAC04\uB3C4\uC11C");
		newlyBookLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		newlyBookLabel.setBounds(22, 260, 123, 40);
		contentPane.add(newlyBookLabel);

		// 신간도서 패널
		JPanel newlyBookPanel = new JPanel();
		newlyBookPanel.setBackground(Color.WHITE);
		newlyBookPanel.setForeground(new Color(255, 255, 255));
		newlyBookPanel.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		newlyBookPanel.setBounds(22, 298, 820, 125);
		newlyBookPanel.setLayout(null);

		JLabel[] newlyBookLabel_array = new JLabel[6];
		// 패널에 출력할 책 이미지를 담을 Label 6개 생성
		JLabel newly_img1 = new JLabel("newly_img1");
		newly_img1.setBounds(12, 10, 105, 105);
		newlyBookPanel.add(newly_img1);
		newlyBookLabel_array[0] = newly_img1;

		JLabel newly_img2 = new JLabel("newly_img2");
		newly_img2.setBounds(150, 10, 105, 105);
		newlyBookPanel.add(newly_img2);
		newlyBookLabel_array[1] = newly_img2;

		JLabel newly_img3 = new JLabel("newly_img3");
		newly_img3.setBounds(288, 10, 105, 105);
		newlyBookPanel.add(newly_img3);
		newlyBookLabel_array[2] = newly_img3;

		JLabel newly_img4 = new JLabel("newly_img4");
		newly_img4.setBounds(426, 10, 105, 105);
		newlyBookPanel.add(newly_img4);
		newlyBookLabel_array[3] = newly_img4;

		JLabel newly_img5 = new JLabel("newly_img5");
		newly_img5.setBounds(564, 10, 105, 105);
		newlyBookPanel.add(newly_img5);
		newlyBookLabel_array[4] = newly_img5;

		JLabel newly_img6 = new JLabel("newly_img6");
		newly_img6.setBounds(703, 10, 105, 105);
		newlyBookPanel.add(newly_img6);
		newlyBookLabel_array[5] = newly_img6;

		// 즐겨찾기 라벨
		JLabel favoriteBookLabel = new JLabel("\uC990\uACA8\uCC3E\uAE30");
		favoriteBookLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		favoriteBookLabel.setBounds(22, 462, 123, 40);
		contentPane.add(favoriteBookLabel);

		// 즐겨찾기 패널
		JPanel favoriteBookPanel = new JPanel();
		favoriteBookPanel.setBackground(Color.WHITE);
		favoriteBookPanel.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		favoriteBookPanel.setBounds(22, 502, 820, 125);
		contentPane.add(favoriteBookPanel);

		try { // DB 접근
			ResultSet rs = dbConn.executeQuery(
					"SELECT BOOK_IMAGE FROM BOOK WHERE BOOK_PRE = TRUE\r\n" + "order by BOOK_GRADE DESC;"); // 인기순으로 정렬

			append_img(rs, popularBookLabel_array); // 인기순으로 상위 6개의 이미지를 인기순 패널의 popularBookLabel에 삽입

			contentPane.add(popularBookPanel); // 컨텐트팬에 인기순 패널 부착

			rs = dbConn.executeQuery(
					"SELECT BOOK_IMAGE FROM BOOK WHERE BOOK_PRE = TRUE\r\n" + "order by BOOK_APPEND_DATE DESC;"); // 최신순으로
																													// 정렬

			append_img(rs, newlyBookLabel_array); // 인기순으로 상위 6개의 이미지를 인기순 패널의 popularBookLabel에 삽입

			contentPane.add(newlyBookPanel);

		} catch (SQLException e2) {
			System.out.println("메인화면에서 SQL 실행 에러");
		}

	}

	// ResultSet과 JLabel을 받아 JLabel에 읽은 이미지를 저장하는 함수
	public void append_img(ResultSet rs, JLabel[] array) throws SQLException {
		int i = 0;
		while (rs.next()) {
			InputStream inputStream = rs.getBinaryStream("BOOK_IMAGE"); // 이미지를 읽어옴
			try {
				Image img = ImageIO.read(inputStream); // 읽어온 이미지를 img에 저장
				Image resize_img = img.getScaledInstance(105, 105, Image.SCALE_SMOOTH); // 이미지 크기 105x105로 크기 조절하여
																						// resize_img에 저장
				ImageIcon icon = new ImageIcon(resize_img); // 조절한 크기의 이미지를 icon에 저장
				array[i].setIcon(icon); // 인기순 패널에 icon 차례로 저장
				array[i].setBorder(new LineBorder(Color.black, 1, false)); // 레이블 테두리 검은색으로 그려줌
			} catch (IOException e) {
				e.printStackTrace();
			}
			i++;
			if (i == 6) // 6개까지만 출력
				break;
		}
	}
}
