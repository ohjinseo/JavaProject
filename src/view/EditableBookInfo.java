package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextArea;
import javax.imageio.ImageIO;
import javax.swing.DropMode;
import javax.swing.ImageIcon;

import java.awt.Panel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import java.awt.Window.Type;
import javax.swing.UIManager;

public class EditableBookInfo extends JFrame {
	dbConnector dbConn = new dbConnector();
	String book_ISBN = "";
	String user_phone = "";
	Boolean manager = false;
	int bookReviewCnt = 0;
	int bookReviewGrade = 0;
	private JPanel contentPane;
	JLabel bookPictureLabel;
	JButton bookBorrowButton;
	JLabel bookNameLabel;
	JLabel bookHeaderLabel_1;
	JLabel bookHeaderLabel_2 ;
	JLabel bookLinkLabel ;
	JLabel bookGradeLabel;
	JLabel bookPriceLabel;
	JLabel bookISBNLabel;
	JTextArea bookDescriptionLabel ;
	ReviewPanel[] review;
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public EditableBookInfo(String book_ISBN, String user_phone, Boolean manager) {
		this.book_ISBN = book_ISBN;
		this.user_phone = user_phone;
		this.manager = manager;
		EditBook edit_book_frame = new EditBook(book_ISBN,user_phone,manager,this);
		
		setTitle("\uB3C4\uC11C \uAD00\uB9AC \uD504\uB85C\uADF8\uB7A8 - \uB3C4\uC11C\uC815\uBCF4");
		setBounds(100, 100, 848, 681);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(12, 31, 181, 239);
		contentPane.add(panel);
		panel.setLayout(null);

		// 책 사진 라벨
		bookPictureLabel = new JLabel("\uCC45\uC0AC\uC9C4");
		bookPictureLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bookPictureLabel.setBounds(12, 10, 157, 170);
		panel.add(bookPictureLabel);

		// 책 빌리기 버튼
		bookBorrowButton = new JButton("\uB300\uCD9C\uAC00\uB2A5");
		bookBorrowButton.setEnabled(false);
		bookBorrowButton.addMouseListener(new MouseAdapter() { // 클릭이벤트
			@Override
			public void mouseClicked(MouseEvent e) {
				if (bookBorrowButton.getText().equals("대출하기")) // 대출하기 버튼 상태일 때
				{
					if (true) // 대출 성공 했을 때
					{
						JOptionPane.showMessageDialog(null, "해당 도서를 대출하였습니다.\n1000-00-00 까지 반납해 주세요.", "대출성공",
								JOptionPane.INFORMATION_MESSAGE);
						bookBorrowButton.setText("반납하기");
					} else if (false) // 대출 실패 했을 때(대출 가능 도서수 초과)
						JOptionPane.showMessageDialog(null, "대출 가능한 도서수를 초과하였습니다.\n다른 도서를 반납 후 다시 시도해 주세요.", "대출실패",
								JOptionPane.WARNING_MESSAGE);
					else // 남은 경우(대출 실패 했을 떄(연체된 책 존재))m	m	m
						JOptionPane.showMessageDialog(null, "연체된 책이 있습니다.\n나중에 다시 시도해 주세요.", "대출실패",
								JOptionPane.WARNING_MESSAGE);
				} else if (bookBorrowButton.getText().equals("반납하기")) // 반납하기 버튼 상태일 때
				{
					if (true) // 연체가 안 된 경우
						JOptionPane.showMessageDialog(null, "반납에 성공하였습니다", "반납성공", JOptionPane.INFORMATION_MESSAGE);
					else // 연체된 경우
						JOptionPane.showMessageDialog(null, "반납에 성공하였습니다\n회원님의 연체일은 8일입니다.\n해당 기간동안 책을 이용하실 수 없습니다.",
								"반납성공", JOptionPane.INFORMATION_MESSAGE);
					// 리뷰 작성창 띄움
					// WriteReview writereview = new WriteReview();
					// writereview.setVisible(true);
					bookBorrowButton.setText("대출하기"); // 대출하기 버튼으로 변경
				}
			}
		});
		bookBorrowButton.setFont(new Font("\uD55C\uCEF4\uC0B0\uB73B\uB3CB\uC6C0",
				bookBorrowButton.getFont().getStyle() | Font.BOLD, bookBorrowButton.getFont().getSize() + 3));
		bookBorrowButton.setBounds(12, 190, 157, 19);
		panel.add(bookBorrowButton);

		// 책 삭제 버튼
		JButton bookDeleteButton_1_1 = new JButton("\uC0AD\uC81C");
		bookDeleteButton_1_1.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
		bookDeleteButton_1_1.setBounds(92, 210, 77, 19);
		bookDeleteButton_1_1.addMouseListener(new MouseAdapter() {
			// 삭제하기 버튼을 클릭했을 때
			@Override
			public void mouseClicked(MouseEvent e) {

				String sql1 = "UPDATE BOOK\r\n"
						+ "SET BOOK_PRE = FALSE\r\n"
						+ "WHERE BOOK_ISBN = ?;";
				
				try {
					ResultSet rs2 = dbConn.executeQuery("SELECT BOOK_ISBN FROM RENT WHERE BOOK_ISBN = '"
							+ book_ISBN + "' AND RENT_RETURN_YN IS NULL;");
					
					if (rs2.next()) {
						
						
						JOptionPane.showMessageDialog(null, "대여중인 도서입니다.", "도서 삭제 실패",
								JOptionPane.ERROR_MESSAGE);
					} else {
						PreparedStatement ps1 = dbConn.conn.prepareStatement(sql1);

						ps1.setString(1, book_ISBN);	//도서 PK
						
						int count1 = ps1.executeUpdate();
						
						ResultSet isFavorite = dbConn.executeQuery("SELECT BOOK_ISBN FROM FAVORITES WHERE BOOK_ISBN = '"
								+ book_ISBN + "';");
						
						ResultSet isReview = dbConn.executeQuery("SELECT BOOK_ISBN FROM REVIEW WHERE BOOK_ISBN = '"
								+ book_ISBN + "';");
						
						int count2 = 0;
						int count3 = 0;
						
						if(isFavorite.next()) {
							//즐겨찾기 삭제
							String sql2="DELETE from FAVORITES\r\n"
									+ "WHERE FAVORITES.BOOK_ISBN = '"+book_ISBN+"';";
							
							
							PreparedStatement ps2 = dbConn.conn.prepareStatement(sql2);
							count2 = ps2.executeUpdate();
						}else {
							count2 = 1;
						}
						
						if(isReview.next()) {
							//리뷰 삭제
							String sql3="DELETE from REVIEW\r\n"
									+ "WHERE REVIEW.BOOK_ISBN = '"+book_ISBN+"';";
							PreparedStatement ps3=dbConn.conn.prepareStatement(sql3);
							count3 = ps3.executeUpdate();	
						}else {
							count3 = 1;
						}
						
						
						if (count1 == 0||count2==0||count3==0) {
							JOptionPane.showMessageDialog(null, "도서 삭제에 실패하였습니다.", "도서 삭제 실패",
									JOptionPane.ERROR_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "도서 삭제에 성공하였습니다.", "도서 삭제 성공",
									JOptionPane.NO_OPTION);

						}
					}
				} catch (SQLException e1) {
					e1.printStackTrace(); // 에러 추적
					System.out.println("회원탈퇴 과정에서 SQL 실행 에러");
				}
			}
		});
		panel.add(bookDeleteButton_1_1);
		

		// 책 정보 패널
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(205, 31, 612, 239);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		// 책 이름 라벨
		bookNameLabel = new JLabel("\uBA85\uD488 \uC790\uBC14 \uC5D0\uC13C\uC15C");
		bookNameLabel.setFont(new Font("\uD568\uCD08\uB86C\uB3CB\uC6C0", bookNameLabel.getFont().getStyle() | Font.BOLD,
				bookNameLabel.getFont().getSize() + 10));
		bookNameLabel.setBounds(12, 10, 178, 46);
		panel_1.add(bookNameLabel);

		// 책 저자 라벨
		 bookHeaderLabel_1 = new JLabel("\uC800\uC790 : \uD669\uAE30\uD0DC");
		bookHeaderLabel_1.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		bookHeaderLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		bookHeaderLabel_1.setBounds(12, 60, 100, 26);
		panel_1.add(bookHeaderLabel_1);

		// 책 출판사 라벨
		bookHeaderLabel_2 = new JLabel("\uCD9C\uD310\uC0AC : \uC0DD\uB2A5\uCD9C\uD310\uC0AC");
		bookHeaderLabel_2.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		bookHeaderLabel_2.setBounds(131, 60, 193, 26);
		panel_1.add(bookHeaderLabel_2);

		// 책 관련링크 라벨
		bookLinkLabel = new JLabel("\uAD00\uB828\uB9C1\uD06C : www.abc.com");
		bookLinkLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		bookLinkLabel.setBounds(12, 200, 567, 26);
		panel_1.add(bookLinkLabel);

		// 책 평점 라벨
		bookGradeLabel = new JLabel("\uD3C9\uC810 : \u2605\u2605\u2605\u2605\u2606");
		bookGradeLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		bookGradeLabel.setBounds(12, 92, 193, 26);
		panel_1.add(bookGradeLabel);

		// 책 가격 라벨
		bookPriceLabel = new JLabel("\uAC00\uACA9 : 28000\uC6D0");
		bookPriceLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		bookPriceLabel.setBounds(12, 128, 193, 26);
		panel_1.add(bookPriceLabel);

		// 책 ISBN 라벨
		bookISBNLabel = new JLabel("ISBN : 123456789");
		bookISBNLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		bookISBNLabel.setBounds(12, 164, 193, 26);
		panel_1.add(bookISBNLabel);

		// 책 줄거리 패널
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(12, 283, 805, 142);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(new Color(128, 128, 128), 1, true));
		scrollPane.setBounds(0, 0, 805, 142);
		panel_2.add(scrollPane);

		bookDescriptionLabel = new JTextArea();
		bookDescriptionLabel.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(bookDescriptionLabel);
		bookDescriptionLabel.setText(
				"\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC");
		bookDescriptionLabel.setLineWrap(true);
		bookDescriptionLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));

		// 책 평점 패널
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setBounds(12, 435, 805, 189);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		review = new ReviewPanel[3];
		review[0] = new ReviewPanel();
		review[0].setSize(805, 65);
		review[0].setLocation(0, 0);
		panel_3.add(review[0]);

		review[1] = new ReviewPanel();
		review[1].setBounds(0, 63, 805, 65);
		panel_3.add(review[1]);

		review[2] = new ReviewPanel();
		review[2].setBounds(0, 124, 805, 65);
		panel_3.add(review[2]);

		JSeparator separator = new JSeparator();
		separator.setBounds(12, 63, -31, 2);
		panel_3.add(separator);

		// 책 수정 버튼
		JButton bookEditButton = new JButton("\uC218\uC815");
		bookEditButton.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
		bookEditButton.setBounds(12, 210, 77, 19);
		bookEditButton.addMouseListener(new MouseAdapter() {
			// 수정하기 버튼을 클릭했을 때
			@Override
			public void mouseClicked(MouseEvent e) {

				edit_book_frame.setLocationRelativeTo(null); // 화면중앙에 출력
				edit_book_frame.setResizable(false); // 창 크기 고정
				edit_book_frame.setVisible(true); // 책 정보창 띄움
			}
		});
		panel.add(bookEditButton);
		try { // DB 접근
			//해당 도서의 평점 개수 가져오기

			 ResultSet rs = dbConn.executeQuery(
					"SELECT COUNT(*) FROM REVIEW WHERE BOOK_ISBN = '" + book_ISBN + "';"
					);
			if(rs.next()) {
				bookReviewCnt = rs.getInt(1);
			}
				// db에서 ISBN으로 책 정보 검색
			getBookInfo();
		} catch (SQLException e2) {
			e2.printStackTrace();
			System.out.println("EditbalBookInfo SQL 실행 에러");
		}
		
		//책 평점 매기기
		int bookScore = 0;
		if(bookReviewCnt != 0) {
			bookScore = bookReviewGrade / bookReviewCnt;
		}
		switch(bookScore) {
		case 0:
			bookGradeLabel.setText("☆☆☆☆☆");
			break;
		case 1:
			bookGradeLabel.setText("★☆☆☆☆");
			break;
		case 2:
			bookGradeLabel.setText("★★☆☆☆");
			break;
		case 3:
			bookGradeLabel.setText("★★★☆☆");
			break;
		case 4:
			bookGradeLabel.setText("★★★★☆");
			break;
		case 5:
			bookGradeLabel.setText("★★★★★");
			break;			
		}
	}
	
	public void getBookInfo() throws SQLException {
		ResultSet rs = dbConn.executeQuery(
				"SELECT BOOK_TITLE, BOOK_AUTHOR, BOOK_PUB, BOOK_PRICE, BOOK_ISBN, BOOK_LINK, BOOK_DESCRIPTION, BOOK_IMAGE, BOOK_GRADE\r\n"
						+ "FROM BOOK\r\n" + "WHERE BOOK_ISBN = '" + book_ISBN + "' AND BOOK_PRE = TRUE;");
		while (rs.next()) {
			bookNameLabel.setText(rs.getString("BOOK_TITLE")); // 책 제목 설정
			bookHeaderLabel_1.setText("저자 : " + rs.getString("BOOK_AUTHOR")); // 책 저자 설정
			bookHeaderLabel_2.setText("출판사 : " + rs.getString("BOOK_PUB")); // 책 출판사 설정
			bookPriceLabel.setText("가격 : " + rs.getString("BOOK_PRICE") + "원"); // 책 가격 설정
			bookISBNLabel.setText("ISBN : " + rs.getString("BOOK_ISBN")); // 책 ISBN 설정
			bookLinkLabel.setText("관련링크 : " + rs.getString("BOOK_LINK")); // 책 관련링크 설정
			bookDescriptionLabel.setText(rs.getString("BOOK_DESCRIPTION")); // 책 줄거리 설정
			// 책 이미지 설정
			InputStream inputStream = rs.getBinaryStream("BOOK_IMAGE"); // 이미지를 읽어옴
			bookReviewGrade = Integer.parseInt(rs.getString("BOOK_GRADE"));	//책 평점
			try {
				Image img = ImageIO.read(inputStream); // 읽어온 이미지를 img에 저장
				Image resize_img = img.getScaledInstance(170, 170, Image.SCALE_SMOOTH); // 이미지 크기 170x170로 크기 조절하여
																						// resize_img에 저장
				ImageIcon icon = new ImageIcon(resize_img); // 조절한 크기의 이미지를 icon에 저장
				bookPictureLabel.setIcon(icon); // 책 이미지 설정
				bookPictureLabel.setBorder(new LineBorder(Color.black, 1, false)); // 이미지 레이블 테두리 검은색으로 설정
			} catch (IOException e) {
				e.printStackTrace();
			}
			// 해당책이 대출중인 도서인지 검색
			rs = dbConn.executeQuery(
					"SELECT BOOK_ISBN FROM RENT WHERE BOOK_ISBN='" + book_ISBN + "' AND RENT_RETURN_YN IS NULL;");
			if (rs.next()) {
				if (book_ISBN.equals(rs.getString("BOOK_ISBN"))) { // 검색해서 나온 ISBN과 해당 책의 ISBN이 같으면 (대출중인 도서이면)
					bookBorrowButton.setText("대여중");
					bookBorrowButton.setEnabled(false);
				}
			}
			// 내가 대출한 도서인지 검색
			rs = dbConn.executeQuery(
					"SELECT BOOK_ISBN FROM RENT WHERE USER_PHONE='" + user_phone + "' AND RENT_RETURN_YN IS NULL;");
			if (rs.next()) {
				if (book_ISBN.equals(rs.getString("BOOK_ISBN"))) { // 검색해서 나온 ISBN과 해당 책의 ISBN이 같으면 (내가 대출중인 도서이면)
					bookBorrowButton.setText("반납하기");
					bookBorrowButton.setEnabled(true);
				}
			}
			bookDescriptionLabel.setEnabled(false); // 도서 설명
			

			getUserReview();
		}
	}
	// 리뷰 가져오는 함수
	public void getUserReview() {
		ResultSet rs = dbConn.executeQuery(
				"SELECT USER_PHONE, REVIEW_TEXT, BOOK_GRADE FROM REVIEW WHERE BOOK_ISBN = '" + book_ISBN + "' order by REVIEW_SEQ DESC;");
		int i = 0;
		try {
			while (rs.next() && i < 3) {

				String reviewUserPhone = rs.getString("USER_PHONE");
				String reviewUserName = "";
				System.out.println();
				ResultSet rs2 = dbConn.executeQuery(
						"SELECT USER_NAME FROM USER\r\n" + "WHERE USER_PHONE = '" + reviewUserPhone + "';");

				if (rs2.next()) {
					reviewUserName = rs2.getString("USER_NAME");
				}
				review[i++].addProperty(reviewUserName, rs.getInt("BOOK_GRADE"), rs.getString("REVIEW_TEXT"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
