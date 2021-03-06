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

		// ?? ???? ????
		bookPictureLabel = new JLabel("\uCC45\uC0AC\uC9C4");
		bookPictureLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bookPictureLabel.setBounds(12, 10, 157, 170);
		panel.add(bookPictureLabel);

		// ?? ?????? ????
		bookBorrowButton = new JButton("\uB300\uCD9C\uAC00\uB2A5");
		bookBorrowButton.setEnabled(false);
		bookBorrowButton.addMouseListener(new MouseAdapter() { // ??????????
			@Override
			public void mouseClicked(MouseEvent e) {
				if (bookBorrowButton.getText().equals("????????")) // ???????? ???? ?????? ??
				{
					if (true) // ???? ???? ???? ??
					{
						JOptionPane.showMessageDialog(null, "???? ?????? ??????????????.\n1000-00-00 ???? ?????? ??????.", "????????",
								JOptionPane.INFORMATION_MESSAGE);
						bookBorrowButton.setText("????????");
					} else if (false) // ???? ???? ???? ??(???? ???? ?????? ????)
						JOptionPane.showMessageDialog(null, "???? ?????? ???????? ??????????????.\n???? ?????? ???? ?? ???? ?????? ??????.", "????????",
								JOptionPane.WARNING_MESSAGE);
					else // ???? ????(???? ???? ???? ??(?????? ?? ????))m	m	m
						JOptionPane.showMessageDialog(null, "?????? ???? ????????.\n?????? ???? ?????? ??????.", "????????",
								JOptionPane.WARNING_MESSAGE);
				} else if (bookBorrowButton.getText().equals("????????")) // ???????? ???? ?????? ??
				{
					if (true) // ?????? ?? ?? ????
						JOptionPane.showMessageDialog(null, "?????? ??????????????", "????????", JOptionPane.INFORMATION_MESSAGE);
					else // ?????? ????
						JOptionPane.showMessageDialog(null, "?????? ??????????????\n???????? ???????? 8????????.\n???? ???????? ???? ???????? ?? ????????.",
								"????????", JOptionPane.INFORMATION_MESSAGE);
					// ???? ?????? ????
					// WriteReview writereview = new WriteReview();
					// writereview.setVisible(true);
					bookBorrowButton.setText("????????"); // ???????? ???????? ????
				}
			}
		});
		bookBorrowButton.setFont(new Font("\uD55C\uCEF4\uC0B0\uB73B\uB3CB\uC6C0",
				bookBorrowButton.getFont().getStyle() | Font.BOLD, bookBorrowButton.getFont().getSize() + 3));
		bookBorrowButton.setBounds(12, 190, 157, 19);
		panel.add(bookBorrowButton);

		// ?? ???? ????
		JButton bookDeleteButton_1_1 = new JButton("\uC0AD\uC81C");
		bookDeleteButton_1_1.setFont(new Font("????????????", Font.BOLD, 15));
		bookDeleteButton_1_1.setBounds(92, 210, 77, 19);
		bookDeleteButton_1_1.addMouseListener(new MouseAdapter() {
			// ???????? ?????? ???????? ??
			@Override
			public void mouseClicked(MouseEvent e) {

				String sql1 = "UPDATE BOOK\r\n"
						+ "SET BOOK_PRE = FALSE\r\n"
						+ "WHERE BOOK_ISBN = ?;";
				
				try {
					ResultSet rs2 = dbConn.executeQuery("SELECT BOOK_ISBN FROM RENT WHERE BOOK_ISBN = '"
							+ book_ISBN + "' AND RENT_RETURN_YN IS NULL;");
					
					if (rs2.next()) {
						
						
						JOptionPane.showMessageDialog(null, "???????? ??????????.", "???? ???? ????",
								JOptionPane.ERROR_MESSAGE);
					} else {
						PreparedStatement ps1 = dbConn.conn.prepareStatement(sql1);

						ps1.setString(1, book_ISBN);	//???? PK
						
						int count1 = ps1.executeUpdate();
						
						ResultSet isFavorite = dbConn.executeQuery("SELECT BOOK_ISBN FROM FAVORITES WHERE BOOK_ISBN = '"
								+ book_ISBN + "';");
						
						ResultSet isReview = dbConn.executeQuery("SELECT BOOK_ISBN FROM REVIEW WHERE BOOK_ISBN = '"
								+ book_ISBN + "';");
						
						int count2 = 0;
						int count3 = 0;
						
						if(isFavorite.next()) {
							//???????? ????
							String sql2="DELETE from FAVORITES\r\n"
									+ "WHERE FAVORITES.BOOK_ISBN = '"+book_ISBN+"';";
							
							
							PreparedStatement ps2 = dbConn.conn.prepareStatement(sql2);
							count2 = ps2.executeUpdate();
						}else {
							count2 = 1;
						}
						
						if(isReview.next()) {
							//???? ????
							String sql3="DELETE from REVIEW\r\n"
									+ "WHERE REVIEW.BOOK_ISBN = '"+book_ISBN+"';";
							PreparedStatement ps3=dbConn.conn.prepareStatement(sql3);
							count3 = ps3.executeUpdate();	
						}else {
							count3 = 1;
						}
						
						
						if (count1 == 0||count2==0||count3==0) {
							JOptionPane.showMessageDialog(null, "???? ?????? ??????????????.", "???? ???? ????",
									JOptionPane.ERROR_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "???? ?????? ??????????????.", "???? ???? ????",
									JOptionPane.NO_OPTION);

						}
					}
				} catch (SQLException e1) {
					e1.printStackTrace(); // ???? ????
					System.out.println("???????? ???????? SQL ???? ????");
				}
			}
		});
		panel.add(bookDeleteButton_1_1);
		

		// ?? ???? ????
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(205, 31, 612, 239);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		// ?? ???? ????
		bookNameLabel = new JLabel("\uBA85\uD488 \uC790\uBC14 \uC5D0\uC13C\uC15C");
		bookNameLabel.setFont(new Font("\uD568\uCD08\uB86C\uB3CB\uC6C0", bookNameLabel.getFont().getStyle() | Font.BOLD,
				bookNameLabel.getFont().getSize() + 10));
		bookNameLabel.setBounds(12, 10, 178, 46);
		panel_1.add(bookNameLabel);

		// ?? ???? ????
		 bookHeaderLabel_1 = new JLabel("\uC800\uC790 : \uD669\uAE30\uD0DC");
		bookHeaderLabel_1.setFont(new Font("????????????", Font.PLAIN, 16));
		bookHeaderLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		bookHeaderLabel_1.setBounds(12, 60, 100, 26);
		panel_1.add(bookHeaderLabel_1);

		// ?? ?????? ????
		bookHeaderLabel_2 = new JLabel("\uCD9C\uD310\uC0AC : \uC0DD\uB2A5\uCD9C\uD310\uC0AC");
		bookHeaderLabel_2.setFont(new Font("????????????", Font.PLAIN, 16));
		bookHeaderLabel_2.setBounds(131, 60, 193, 26);
		panel_1.add(bookHeaderLabel_2);

		// ?? ???????? ????
		bookLinkLabel = new JLabel("\uAD00\uB828\uB9C1\uD06C : www.abc.com");
		bookLinkLabel.setFont(new Font("????????????", Font.PLAIN, 16));
		bookLinkLabel.setBounds(12, 200, 567, 26);
		panel_1.add(bookLinkLabel);

		// ?? ???? ????
		bookGradeLabel = new JLabel("\uD3C9\uC810 : \u2605\u2605\u2605\u2605\u2606");
		bookGradeLabel.setFont(new Font("????????????", Font.PLAIN, 16));
		bookGradeLabel.setBounds(12, 92, 193, 26);
		panel_1.add(bookGradeLabel);

		// ?? ???? ????
		bookPriceLabel = new JLabel("\uAC00\uACA9 : 28000\uC6D0");
		bookPriceLabel.setFont(new Font("????????????", Font.PLAIN, 16));
		bookPriceLabel.setBounds(12, 128, 193, 26);
		panel_1.add(bookPriceLabel);

		// ?? ISBN ????
		bookISBNLabel = new JLabel("ISBN : 123456789");
		bookISBNLabel.setFont(new Font("????????????", Font.PLAIN, 16));
		bookISBNLabel.setBounds(12, 164, 193, 26);
		panel_1.add(bookISBNLabel);

		// ?? ?????? ????
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
		bookDescriptionLabel.setFont(new Font("????????????", Font.PLAIN, 16));

		// ?? ???? ????
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

		// ?? ???? ????
		JButton bookEditButton = new JButton("\uC218\uC815");
		bookEditButton.setFont(new Font("????????????", Font.BOLD, 15));
		bookEditButton.setBounds(12, 210, 77, 19);
		bookEditButton.addMouseListener(new MouseAdapter() {
			// ???????? ?????? ???????? ??
			@Override
			public void mouseClicked(MouseEvent e) {

				edit_book_frame.setLocationRelativeTo(null); // ?????????? ????
				edit_book_frame.setResizable(false); // ?? ???? ????
				edit_book_frame.setVisible(true); // ?? ?????? ????
			}
		});
		panel.add(bookEditButton);
		try { // DB ????
			//???? ?????? ???? ???? ????????

			 ResultSet rs = dbConn.executeQuery(
					"SELECT COUNT(*) FROM REVIEW WHERE BOOK_ISBN = '" + book_ISBN + "';"
					);
			if(rs.next()) {
				bookReviewCnt = rs.getInt(1);
			}
				// db???? ISBN???? ?? ???? ????
			getBookInfo();
		} catch (SQLException e2) {
			e2.printStackTrace();
			System.out.println("EditbalBookInfo SQL ???? ????");
		}
		
		//?? ???? ??????
		int bookScore = 0;
		if(bookReviewCnt != 0) {
			bookScore = bookReviewGrade / bookReviewCnt;
		}
		switch(bookScore) {
		case 0:
			bookGradeLabel.setText("??????????");
			break;
		case 1:
			bookGradeLabel.setText("??????????");
			break;
		case 2:
			bookGradeLabel.setText("??????????");
			break;
		case 3:
			bookGradeLabel.setText("??????????");
			break;
		case 4:
			bookGradeLabel.setText("??????????");
			break;
		case 5:
			bookGradeLabel.setText("??????????");
			break;			
		}
	}
	
	public void getBookInfo() throws SQLException {
		ResultSet rs = dbConn.executeQuery(
				"SELECT BOOK_TITLE, BOOK_AUTHOR, BOOK_PUB, BOOK_PRICE, BOOK_ISBN, BOOK_LINK, BOOK_DESCRIPTION, BOOK_IMAGE, BOOK_GRADE\r\n"
						+ "FROM BOOK\r\n" + "WHERE BOOK_ISBN = '" + book_ISBN + "' AND BOOK_PRE = TRUE;");
		while (rs.next()) {
			bookNameLabel.setText(rs.getString("BOOK_TITLE")); // ?? ???? ????
			bookHeaderLabel_1.setText("???? : " + rs.getString("BOOK_AUTHOR")); // ?? ???? ????
			bookHeaderLabel_2.setText("?????? : " + rs.getString("BOOK_PUB")); // ?? ?????? ????
			bookPriceLabel.setText("???? : " + rs.getString("BOOK_PRICE") + "??"); // ?? ???? ????
			bookISBNLabel.setText("ISBN : " + rs.getString("BOOK_ISBN")); // ?? ISBN ????
			bookLinkLabel.setText("???????? : " + rs.getString("BOOK_LINK")); // ?? ???????? ????
			bookDescriptionLabel.setText(rs.getString("BOOK_DESCRIPTION")); // ?? ?????? ????
			// ?? ?????? ????
			InputStream inputStream = rs.getBinaryStream("BOOK_IMAGE"); // ???????? ??????
			bookReviewGrade = Integer.parseInt(rs.getString("BOOK_GRADE"));	//?? ????
			try {
				Image img = ImageIO.read(inputStream); // ?????? ???????? img?? ????
				Image resize_img = img.getScaledInstance(170, 170, Image.SCALE_SMOOTH); // ?????? ???? 170x170?? ???? ????????
																						// resize_img?? ????
				ImageIcon icon = new ImageIcon(resize_img); // ?????? ?????? ???????? icon?? ????
				bookPictureLabel.setIcon(icon); // ?? ?????? ????
				bookPictureLabel.setBorder(new LineBorder(Color.black, 1, false)); // ?????? ?????? ?????? ?????????? ????
			} catch (IOException e) {
				e.printStackTrace();
			}
			// ???????? ???????? ???????? ????
			rs = dbConn.executeQuery(
					"SELECT BOOK_ISBN FROM RENT WHERE BOOK_ISBN='" + book_ISBN + "' AND RENT_RETURN_YN IS NULL;");
			if (rs.next()) {
				if (book_ISBN.equals(rs.getString("BOOK_ISBN"))) { // ???????? ???? ISBN?? ???? ???? ISBN?? ?????? (???????? ????????)
					bookBorrowButton.setText("??????");
					bookBorrowButton.setEnabled(false);
				}
			}
			// ???? ?????? ???????? ????
			rs = dbConn.executeQuery(
					"SELECT BOOK_ISBN FROM RENT WHERE USER_PHONE='" + user_phone + "' AND RENT_RETURN_YN IS NULL;");
			if (rs.next()) {
				if (book_ISBN.equals(rs.getString("BOOK_ISBN"))) { // ???????? ???? ISBN?? ???? ???? ISBN?? ?????? (???? ???????? ????????)
					bookBorrowButton.setText("????????");
					bookBorrowButton.setEnabled(true);
				}
			}
			bookDescriptionLabel.setEnabled(false); // ???? ????
			

			getUserReview();
		}
	}
	// ???? ???????? ????
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
