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
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JTextArea;
import javax.imageio.ImageIO;
import javax.swing.DropMode;
import javax.swing.ImageIcon;

import java.awt.Panel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import java.awt.Window.Type;
import javax.swing.UIManager;

public class BookInfo extends JFrame {
	dbConnector dbConn = new dbConnector();
	String book_ISBN = "";
	String user_phone = "";
	private JPanel contentPane;
	int bookReviewCnt = 0;
	int bookReviewGrade = 0;
	int bookBorrowCnt = 3;
	int userPoint = 0;
	boolean userSus = false;
	long diffDays = 0; // 연체일을 나타내는 변수

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * 
	 * @throws ParseException
	 */
	public BookInfo(String book_ISBN, String user_phone) {
		this.book_ISBN = book_ISBN; // 객체가 생성될때 매개변수로 받은 book_ISBN을 저장
		this.user_phone = user_phone; // 객체가 생성될때 매개변수로 받은 user_phone을 저장

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
		JLabel bookPictureLabel = new JLabel("\uCC45\uC0AC\uC9C4");
		bookPictureLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bookPictureLabel.setBounds(12, 10, 157, 170);
		panel.add(bookPictureLabel);

		// 책 빌리기 버튼
		JButton bookBorrowButton = new JButton("대출하기");
		bookBorrowButton.addMouseListener(new MouseAdapter() { // 클릭이벤트
			@Override
			public void mouseClicked(MouseEvent e) {

				if (bookBorrowButton.getText().equals("대출하기")) // 대출하기 버튼 상태일 때
				{
					if (isSuspension()) {	//만약 연체 유저라면
						userSus = true;
					}else {	//만약 연체 유저가 아니거나 정지일이 지난 유저라면
						updateUserSuspension(0);
					}

					if ((((userPoint < 50 && bookBorrowCnt < 3) || (userPoint >= 50 && bookBorrowCnt < 5))) && !userSus) // 대출 성공 했을때
					{
						if (BookRent() == 1) {
							Date now = new Date();
							
							Calendar cal = Calendar.getInstance();
							cal.setTime(now); 
							cal.add(Calendar.DATE, 14);
							Date date = cal.getTime();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							String dt = sdf.format(date);
							
							JOptionPane.showMessageDialog(null, "해당 도서를 대출하였습니다.\n" + dt + "까지 반납해 주세요.", "대출성공",
									JOptionPane.INFORMATION_MESSAGE);
							bookBorrowButton.setText("반납하기");
							updateBookRentCnt();
						}

					} else if (((userPoint < 50 && bookBorrowCnt >= 3) || (userPoint >= 50 && bookBorrowCnt >= 5))
							&& !userSus) // 대출 실패 했을 때(대출 가능 도서수 초과)
						JOptionPane.showMessageDialog(null, "대출 가능한 도서수를 초과하였습니다.\n다른 도서를 반납 후 다시 시도해 주세요.", "대출실패",
								JOptionPane.WARNING_MESSAGE);

					else if (userSus) // 남은 경우(대출 실패 했을 떄(연체된 책 존재))
						JOptionPane.showMessageDialog(null, "연체된 책이 있습니다.\n나중에 다시 시도해 주세요.", "대출실패",
								JOptionPane.WARNING_MESSAGE);

				} else if (bookBorrowButton.getText().equals("반납하기")) // 반납하기 버튼 상태일 때
				{
					if (!userSus) 
					{
						JOptionPane.showMessageDialog(null, "반납에 성공하였습니다", "반납성공", JOptionPane.INFORMATION_MESSAGE);
					} else // 연체된 경우
						JOptionPane.showMessageDialog(null,
								"반납에 성공하였습니다\n회원님의 연체일은 " + diffDays + "일입니다.\n해당 기간동안 책을 이용하실 수 없습니다.", "반납성공",
								JOptionPane.INFORMATION_MESSAGE);
					 //리뷰 작성창 띄움
					 WriteReview writereview = new WriteReview(book_ISBN, user_phone);
					 writereview.setVisible(true);
					updateBookReturn();	//반납 업뎃함수
					bookBorrowButton.setText("대출하기"); // 대출하기 버튼으로 변경
				}

			}
		});
		bookBorrowButton.setFont(new Font("\uD55C\uCEF4\uC0B0\uB73B\uB3CB\uC6C0",
				bookBorrowButton.getFont().getStyle() | Font.BOLD, bookBorrowButton.getFont().getSize() + 3));
		bookBorrowButton.setBounds(12, 190, 157, 39);
		panel.add(bookBorrowButton);

		// 책 정보 패널
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(205, 31, 612, 239);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		// 책 이름 라벨
		JLabel bookNameLabel = new JLabel("\uBA85\uD488 \uC790\uBC14 \uC5D0\uC13C\uC15C");
		bookNameLabel.setFont(new Font("\uD568\uCD08\uB86C\uB3CB\uC6C0", bookNameLabel.getFont().getStyle() | Font.BOLD,
				bookNameLabel.getFont().getSize() + 10));
		bookNameLabel.setBounds(12, 10, 535, 46);
		panel_1.add(bookNameLabel);

		// 책 저자 라벨
		JLabel bookHeaderLabel_1 = new JLabel("\uC800\uC790 : \uD669\uAE30\uD0DC");
		bookHeaderLabel_1.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		bookHeaderLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		bookHeaderLabel_1.setBounds(12, 60, 330, 26);
		panel_1.add(bookHeaderLabel_1);

		// 책 출판사 라벨
		JLabel bookHeaderLabel_2 = new JLabel("\uCD9C\uD310\uC0AC : \uC0DD\uB2A5\uCD9C\uD310\uC0AC");
		bookHeaderLabel_2.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		bookHeaderLabel_2.setBounds(354, 60, 225, 26);
		panel_1.add(bookHeaderLabel_2);

		// 책 관련링크 라벨
		JLabel bookLinkLabel = new JLabel("\uAD00\uB828\uB9C1\uD06C : www.abc.com");
		bookLinkLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		bookLinkLabel.setBounds(12, 200, 567, 26);
		panel_1.add(bookLinkLabel);

		// 책 평점 라벨
		JLabel bookGradeLabel = new JLabel("\uD3C9\uC810 : \u2605\u2605\u2605\u2605\u2606");
		bookGradeLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		bookGradeLabel.setBounds(12, 92, 193, 26);
		panel_1.add(bookGradeLabel);

		// 책 가격 라벨
		JLabel bookPriceLabel = new JLabel("\uAC00\uACA9 : 28000\uC6D0");
		bookPriceLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		bookPriceLabel.setBounds(12, 128, 193, 26);
		panel_1.add(bookPriceLabel);

		// 책 ISBN 라벨
		JLabel bookISBNLabel = new JLabel("ISBN : 123456789");
		bookISBNLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		bookISBNLabel.setBounds(12, 164, 193, 26);
		panel_1.add(bookISBNLabel);

		// 책 즐겨찾기 라벨
		JLabel bookFavoritesLabel = new JLabel("\u2606");
		bookFavoritesLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (bookFavoritesLabel.getText() == "☆") // 별 체크가 안된경우
					bookFavoritesLabel.setText("★"); // 별 체크
				else // 나머지 경우(별 체크가 된 경우)
					bookFavoritesLabel.setText("☆"); // 별 체크 해제
			}
		});
		bookFavoritesLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		bookFavoritesLabel.setBounds(559, 10, 41, 46);
		panel_1.add(bookFavoritesLabel);

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

		JTextArea bookDescriptionLabel = new JTextArea();
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
		ReviewPanel review = new ReviewPanel();
		review.setSize(805, 65);
		review.setLocation(0, 0);
		panel_3.add(review);

		ReviewPanel review_1 = new ReviewPanel();
		review_1.setBounds(0, 63, 805, 65);
		panel_3.add(review_1);

		ReviewPanel review_2 = new ReviewPanel();
		review_2.setBounds(0, 124, 805, 65);
		panel_3.add(review_2);

		JSeparator separator = new JSeparator();
		separator.setBounds(12, 63, -31, 2);
		panel_3.add(separator);

		try { // DB 접근

			// 해당 도서의 평점 개수 가져오기
			ResultSet rs = dbConn.executeQuery("SELECT COUNT(*) FROM REVIEW WHERE BOOK_ISBN = '" + book_ISBN + "';");
			if (rs.next()) {
				bookReviewCnt = rs.getInt(1);
				System.out.println("bookReviewCnt : "+bookReviewCnt);
			}

			// 사용자의 대여 횟수 가져오기
			rs = dbConn.executeQuery("SELECT COUNT(*) FROM RENT WHERE USER_PHONE = '" + user_phone + "' AND RENT_RETURN_YN is NULL;");
			if (rs.next()) {
				bookBorrowCnt = rs.getInt(1);
				System.out.println("bookBorrowCnt : "+bookBorrowCnt);
			}

			// 사용자의 유저 등급 가져오기 (실시간으로 반영이 되야하므로 매개변수 전달이아닌 매번 쿼리문으로 조회)
			rs = dbConn.executeQuery("SELECT USER_POINT FROM USER\r\n" + "WHERE USER_PHONE = '" + user_phone + "';");
			if (rs.next()) {
				userPoint = Integer.parseInt(rs.getString("USER_POINT"));
			}

			// ISBN으로 도서 정보 검색
			rs = dbConn.executeQuery(
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
				bookReviewGrade = Integer.parseInt(rs.getString("BOOK_GRADE")); // 책 평점
				// 책 이미지 설정
				InputStream inputStream = rs.getBinaryStream("BOOK_IMAGE"); // 이미지를 읽어옴
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
				// 해당 책이 대출중인 책인지 검색
				rs = dbConn.executeQuery(
						"SELECT BOOK_ISBN FROM RENT WHERE BOOK_ISBN='" + book_ISBN + "' AND RENT_RETURN_YN IS NULL;");
				if (rs.next()) {
					if (book_ISBN.equals(rs.getString("BOOK_ISBN"))) { // 검색해서 나온 ISBN과 해당 책의 ISBN이 같으면 (대출중인 도서이면)
						bookBorrowButton.setText("대여중");
						bookBorrowButton.setEnabled(false);

					}
				}
				// 내가 대출한 책인지 검색
				rs = dbConn.executeQuery(
						"SELECT BOOK_ISBN FROM RENT WHERE USER_PHONE='" + user_phone + "' AND RENT_RETURN_YN IS NULL;");

				while (rs.next()) { // 여러권 대여했을 수도 있으므로 while문으로^^
					if (book_ISBN.equals(rs.getString("BOOK_ISBN"))) { // 검색해서 나온 ISBN과 해당 책의 ISBN이 같으면 (내가 대출중인 도서이면)
						bookBorrowButton.setText("반납하기");
						bookBorrowButton.setEnabled(true);
					}
				}
				bookDescriptionLabel.setEnabled(false); // 도서 설명
				break;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			System.out.println("SQL 실행 에러");
		}

		// 책 평점 매기기
		int bookScore = 0;
		if (bookReviewCnt != 0) {
			bookScore = bookReviewGrade / bookReviewCnt;
		}
		switch (bookScore) {
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

	// 유저의 정지 여부를 업데이트 하는 함수
	public void updateUserSuspension(int day) {
		String sql;
		if(day == 0) {	//연체일이 0라면
			sql = "update USER\r\n" + "SET USER_SUSPENSION = NULL\r\n" + "WHERE USER_PHONE = '"
					+ user_phone + "';"; // 해당 유저의 정지일을 NULL로 업데이트
		}
		else {
			Date now = new Date();
	
			Calendar cal = Calendar.getInstance();
			cal.setTime(now); // 현재 유저가 접속한 날짜를 기준으로 정지일을 추가시킴.
			cal.add(Calendar.DATE, day);
			Date dt = cal.getTime(); // Calendar 타입을 Date타입으로 변환
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String diffTime = sdf.format(dt); // Date타입을 Mysql DATETIME 형식으로 포멧
	
			sql = "update USER\r\n" + "SET USER_POINT = USER_POINT-5, USER_SUSPENSION = '" + diffTime + "'\r\n" + "WHERE USER_PHONE = '"
					+ user_phone + "';"; // 해당 유저의 정지일을 업데이트시키고 포인트 5점을 깎음.
			
			sql = "update USER SET USER_POINT = USER_POINT + 5\r\n" + "WHERE USER_PHONE = '" + user_phone + "';";
			
		}
		try { // DB 접근
			PreparedStatement ps = dbConn.conn.prepareStatement(sql);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("updateUserSuspension sql 오류");
		}
	}

	// 정지 여부를 확인해주는 함수
	public boolean isSuspension() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date returnDate;
		Date susDate;
		Date nowDate;
		try {
			nowDate = dateFormat.parse(LocalDate.now().toString());
			try {
				ResultSet rs1 = dbConn.executeQuery("SELECT USER_SUSPENSION FROM USER WHERE USER_PHONE='" + user_phone + "';");
				while(rs1.next()) {
					if(rs1.getString("USER_SUSPENSION") != null) {
							
						String s = rs1.getString("USER_SUSPENSION");
						s.substring(0, 10); // Mysql DATETIME 타입에서 날짜만 가져옴
						susDate = dateFormat.parse(s);
						
						int compare = nowDate.compareTo(susDate); // 현재 날짜와 정지 날짜를 비교하면 compare가 0보다 크다면 정지
						
						if (compare > 0) { // 만약 정지 날짜가 현재 날짜보다 뒤에있다면
							return true;
						}
					}
				}
				
				ResultSet rs = dbConn.executeQuery("SELECT RENT_RETURN_DATE FROM RENT WHERE USER_PHONE='" + user_phone
						+ "' AND RENT_RETURN_YN IS NULL;");

				while (rs.next()) {
					String s = rs.getString("RENT_RETURN_DATE");
					s.substring(0, 10); // Mysql DATETIME 타입에서 날짜만 가져옴
					returnDate = dateFormat.parse(s);

					int compare = nowDate.compareTo(returnDate); // 현재 날짜와 반납 날짜를 비교하면 compare가 0보다 크다면 반납 기한을 지남
					
					if (compare > 0) { // 만약 반납 날짜가 현재 날짜를 지났다면
						Calendar nowCal = Calendar.getInstance();
						nowCal.setTime(nowDate);
						Calendar returnCal = Calendar.getInstance();
						returnCal.setTime(returnDate);

						long diffSec = (nowCal.getTimeInMillis() - returnCal.getTimeInMillis()) / 1000;
						diffDays = diffSec / (24 * 60 * 60); // 현재 날짜와 반납 일자수 차이

						updateUserSuspension((int) diffDays); // 해당 유저의 정지일을 업데이트하기 위해 정지 업뎃함수를 호출함.
						
						
						return true;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("isSuspension sql 오류");
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		return false;
	}

	// 해당 도서 대여시 Rent테이블 업뎃함수
	public int BookRent() {

		String sql = "insert into RENT(\r\n" + "BOOK_ISBN,\r\n" + "USER_PHONE,\r\n" + "RENT_DATE,\r\n"
				+ "RENT_RETURN_DATE\r\n" + ")values(\r\n" + "?, ?, NOW(), DATE_ADD(NOW(),INTERVAL 14 DAY));";
		try { // DB 접근
			PreparedStatement ps = dbConn.conn.prepareStatement(sql);
			ps.setString(1, book_ISBN);
			ps.setString(2, user_phone);
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("sql 에러");
		}
		return 0;
	}

	// 해당 도서 대여 횟수 1증가 함수
	public void updateBookRentCnt() {
		String sql = "update BOOK\r\n" + "SET BOOK_RENT_COUNT = BOOK_RENT_COUNT + 1\r\n" + "WHERE BOOK_ISBN = '"
				+ book_ISBN + "';";
		try { // DB 접근
			PreparedStatement ps = dbConn.conn.prepareStatement(sql);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("bookRentCnt sql 오류");
		}
	}
	
	//도서 반납 함수
	public void updateBookReturn() {
		String sql = "update RENT\r\n" 
	+ "SET RENT_RETURN_YN = NOW()\r\n" 
	+ "WHERE RENT.USER_PHONE = '"
	+ user_phone + "' AND RENT.BOOK_ISBN = '" + book_ISBN + "';";
		
		
		
		try { // DB 접근
			PreparedStatement ps = dbConn.conn.prepareStatement(sql);
			ps.executeUpdate();
			sql = "update USER SET USER_POINT = USER_POINT + 5\r\n" + "WHERE USER_PHONE = '" + user_phone + "';";
			ps = dbConn.conn.prepareStatement(sql); //반납에 성공했으므로 유저 포인트를 5증가
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("updateBookReturn sql 오류");
		}

	}

}
