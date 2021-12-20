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
	long diffDays = 0; // ��ü���� ��Ÿ���� ����

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * 
	 * @throws ParseException
	 */
	public BookInfo(String book_ISBN, String user_phone) {
		this.book_ISBN = book_ISBN; // ��ü�� �����ɶ� �Ű������� ���� book_ISBN�� ����
		this.user_phone = user_phone; // ��ü�� �����ɶ� �Ű������� ���� user_phone�� ����

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

		// å ���� ��
		JLabel bookPictureLabel = new JLabel("\uCC45\uC0AC\uC9C4");
		bookPictureLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bookPictureLabel.setBounds(12, 10, 157, 170);
		panel.add(bookPictureLabel);

		// å ������ ��ư
		JButton bookBorrowButton = new JButton("�����ϱ�");
		bookBorrowButton.addMouseListener(new MouseAdapter() { // Ŭ���̺�Ʈ
			@Override
			public void mouseClicked(MouseEvent e) {

				if (bookBorrowButton.getText().equals("�����ϱ�")) // �����ϱ� ��ư ������ ��
				{
					if (isSuspension()) {	//���� ��ü �������
						userSus = true;
					}else {	//���� ��ü ������ �ƴϰų� �������� ���� �������
						updateUserSuspension(0);
					}

					if ((((userPoint < 50 && bookBorrowCnt < 3) || (userPoint >= 50 && bookBorrowCnt < 5))) && !userSus) // ���� ���� ������
					{
						if (BookRent() == 1) {
							Date now = new Date();
							
							Calendar cal = Calendar.getInstance();
							cal.setTime(now); 
							cal.add(Calendar.DATE, 14);
							Date date = cal.getTime();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							String dt = sdf.format(date);
							
							JOptionPane.showMessageDialog(null, "�ش� ������ �����Ͽ����ϴ�.\n" + dt + "���� �ݳ��� �ּ���.", "���⼺��",
									JOptionPane.INFORMATION_MESSAGE);
							bookBorrowButton.setText("�ݳ��ϱ�");
							updateBookRentCnt();
						}

					} else if (((userPoint < 50 && bookBorrowCnt >= 3) || (userPoint >= 50 && bookBorrowCnt >= 5))
							&& !userSus) // ���� ���� ���� ��(���� ���� ������ �ʰ�)
						JOptionPane.showMessageDialog(null, "���� ������ �������� �ʰ��Ͽ����ϴ�.\n�ٸ� ������ �ݳ� �� �ٽ� �õ��� �ּ���.", "�������",
								JOptionPane.WARNING_MESSAGE);

					else if (userSus) // ���� ���(���� ���� ���� ��(��ü�� å ����))
						JOptionPane.showMessageDialog(null, "��ü�� å�� �ֽ��ϴ�.\n���߿� �ٽ� �õ��� �ּ���.", "�������",
								JOptionPane.WARNING_MESSAGE);

				} else if (bookBorrowButton.getText().equals("�ݳ��ϱ�")) // �ݳ��ϱ� ��ư ������ ��
				{
					if (!userSus) 
					{
						JOptionPane.showMessageDialog(null, "�ݳ��� �����Ͽ����ϴ�", "�ݳ�����", JOptionPane.INFORMATION_MESSAGE);
					} else // ��ü�� ���
						JOptionPane.showMessageDialog(null,
								"�ݳ��� �����Ͽ����ϴ�\nȸ������ ��ü���� " + diffDays + "���Դϴ�.\n�ش� �Ⱓ���� å�� �̿��Ͻ� �� �����ϴ�.", "�ݳ�����",
								JOptionPane.INFORMATION_MESSAGE);
					 //���� �ۼ�â ���
					 WriteReview writereview = new WriteReview(book_ISBN, user_phone);
					 writereview.setVisible(true);
					updateBookReturn();	//�ݳ� �����Լ�
					bookBorrowButton.setText("�����ϱ�"); // �����ϱ� ��ư���� ����
				}

			}
		});
		bookBorrowButton.setFont(new Font("\uD55C\uCEF4\uC0B0\uB73B\uB3CB\uC6C0",
				bookBorrowButton.getFont().getStyle() | Font.BOLD, bookBorrowButton.getFont().getSize() + 3));
		bookBorrowButton.setBounds(12, 190, 157, 39);
		panel.add(bookBorrowButton);

		// å ���� �г�
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(205, 31, 612, 239);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		// å �̸� ��
		JLabel bookNameLabel = new JLabel("\uBA85\uD488 \uC790\uBC14 \uC5D0\uC13C\uC15C");
		bookNameLabel.setFont(new Font("\uD568\uCD08\uB86C\uB3CB\uC6C0", bookNameLabel.getFont().getStyle() | Font.BOLD,
				bookNameLabel.getFont().getSize() + 10));
		bookNameLabel.setBounds(12, 10, 535, 46);
		panel_1.add(bookNameLabel);

		// å ���� ��
		JLabel bookHeaderLabel_1 = new JLabel("\uC800\uC790 : \uD669\uAE30\uD0DC");
		bookHeaderLabel_1.setFont(new Font("���Ļ�浸��", Font.PLAIN, 16));
		bookHeaderLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		bookHeaderLabel_1.setBounds(12, 60, 330, 26);
		panel_1.add(bookHeaderLabel_1);

		// å ���ǻ� ��
		JLabel bookHeaderLabel_2 = new JLabel("\uCD9C\uD310\uC0AC : \uC0DD\uB2A5\uCD9C\uD310\uC0AC");
		bookHeaderLabel_2.setFont(new Font("���Ļ�浸��", Font.PLAIN, 16));
		bookHeaderLabel_2.setBounds(354, 60, 225, 26);
		panel_1.add(bookHeaderLabel_2);

		// å ���ø�ũ ��
		JLabel bookLinkLabel = new JLabel("\uAD00\uB828\uB9C1\uD06C : www.abc.com");
		bookLinkLabel.setFont(new Font("���Ļ�浸��", Font.PLAIN, 16));
		bookLinkLabel.setBounds(12, 200, 567, 26);
		panel_1.add(bookLinkLabel);

		// å ���� ��
		JLabel bookGradeLabel = new JLabel("\uD3C9\uC810 : \u2605\u2605\u2605\u2605\u2606");
		bookGradeLabel.setFont(new Font("���Ļ�浸��", Font.PLAIN, 16));
		bookGradeLabel.setBounds(12, 92, 193, 26);
		panel_1.add(bookGradeLabel);

		// å ���� ��
		JLabel bookPriceLabel = new JLabel("\uAC00\uACA9 : 28000\uC6D0");
		bookPriceLabel.setFont(new Font("���Ļ�浸��", Font.PLAIN, 16));
		bookPriceLabel.setBounds(12, 128, 193, 26);
		panel_1.add(bookPriceLabel);

		// å ISBN ��
		JLabel bookISBNLabel = new JLabel("ISBN : 123456789");
		bookISBNLabel.setFont(new Font("���Ļ�浸��", Font.PLAIN, 16));
		bookISBNLabel.setBounds(12, 164, 193, 26);
		panel_1.add(bookISBNLabel);

		// å ���ã�� ��
		JLabel bookFavoritesLabel = new JLabel("\u2606");
		bookFavoritesLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (bookFavoritesLabel.getText() == "��") // �� üũ�� �ȵȰ��
					bookFavoritesLabel.setText("��"); // �� üũ
				else // ������ ���(�� üũ�� �� ���)
					bookFavoritesLabel.setText("��"); // �� üũ ����
			}
		});
		bookFavoritesLabel.setFont(new Font("���Ļ�浸��", Font.BOLD, 20));
		bookFavoritesLabel.setBounds(559, 10, 41, 46);
		panel_1.add(bookFavoritesLabel);

		// å �ٰŸ� �г�
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
		bookDescriptionLabel.setFont(new Font("���Ļ�浸��", Font.PLAIN, 16));

		// å ���� �г�
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

		try { // DB ����

			// �ش� ������ ���� ���� ��������
			ResultSet rs = dbConn.executeQuery("SELECT COUNT(*) FROM REVIEW WHERE BOOK_ISBN = '" + book_ISBN + "';");
			if (rs.next()) {
				bookReviewCnt = rs.getInt(1);
				System.out.println("bookReviewCnt : "+bookReviewCnt);
			}

			// ������� �뿩 Ƚ�� ��������
			rs = dbConn.executeQuery("SELECT COUNT(*) FROM RENT WHERE USER_PHONE = '" + user_phone + "' AND RENT_RETURN_YN is NULL;");
			if (rs.next()) {
				bookBorrowCnt = rs.getInt(1);
				System.out.println("bookBorrowCnt : "+bookBorrowCnt);
			}

			// ������� ���� ��� �������� (�ǽð����� �ݿ��� �Ǿ��ϹǷ� �Ű����� �����̾ƴ� �Ź� ���������� ��ȸ)
			rs = dbConn.executeQuery("SELECT USER_POINT FROM USER\r\n" + "WHERE USER_PHONE = '" + user_phone + "';");
			if (rs.next()) {
				userPoint = Integer.parseInt(rs.getString("USER_POINT"));
			}

			// ISBN���� ���� ���� �˻�
			rs = dbConn.executeQuery(
					"SELECT BOOK_TITLE, BOOK_AUTHOR, BOOK_PUB, BOOK_PRICE, BOOK_ISBN, BOOK_LINK, BOOK_DESCRIPTION, BOOK_IMAGE, BOOK_GRADE\r\n"
							+ "FROM BOOK\r\n" + "WHERE BOOK_ISBN = '" + book_ISBN + "' AND BOOK_PRE = TRUE;");
			while (rs.next()) {

				bookNameLabel.setText(rs.getString("BOOK_TITLE")); // å ���� ����
				bookHeaderLabel_1.setText("���� : " + rs.getString("BOOK_AUTHOR")); // å ���� ����
				bookHeaderLabel_2.setText("���ǻ� : " + rs.getString("BOOK_PUB")); // å ���ǻ� ����
				bookPriceLabel.setText("���� : " + rs.getString("BOOK_PRICE") + "��"); // å ���� ����
				bookISBNLabel.setText("ISBN : " + rs.getString("BOOK_ISBN")); // å ISBN ����
				bookLinkLabel.setText("���ø�ũ : " + rs.getString("BOOK_LINK")); // å ���ø�ũ ����
				bookDescriptionLabel.setText(rs.getString("BOOK_DESCRIPTION")); // å �ٰŸ� ����
				bookReviewGrade = Integer.parseInt(rs.getString("BOOK_GRADE")); // å ����
				// å �̹��� ����
				InputStream inputStream = rs.getBinaryStream("BOOK_IMAGE"); // �̹����� �о��
				try {
					Image img = ImageIO.read(inputStream); // �о�� �̹����� img�� ����
					Image resize_img = img.getScaledInstance(170, 170, Image.SCALE_SMOOTH); // �̹��� ũ�� 170x170�� ũ�� �����Ͽ�
																							// resize_img�� ����
					ImageIcon icon = new ImageIcon(resize_img); // ������ ũ���� �̹����� icon�� ����
					bookPictureLabel.setIcon(icon); // å �̹��� ����
					bookPictureLabel.setBorder(new LineBorder(Color.black, 1, false)); // �̹��� ���̺� �׵θ� ���������� ����
				} catch (IOException e) {
					e.printStackTrace();
				}
				// �ش� å�� �������� å���� �˻�
				rs = dbConn.executeQuery(
						"SELECT BOOK_ISBN FROM RENT WHERE BOOK_ISBN='" + book_ISBN + "' AND RENT_RETURN_YN IS NULL;");
				if (rs.next()) {
					if (book_ISBN.equals(rs.getString("BOOK_ISBN"))) { // �˻��ؼ� ���� ISBN�� �ش� å�� ISBN�� ������ (�������� �����̸�)
						bookBorrowButton.setText("�뿩��");
						bookBorrowButton.setEnabled(false);

					}
				}
				// ���� ������ å���� �˻�
				rs = dbConn.executeQuery(
						"SELECT BOOK_ISBN FROM RENT WHERE USER_PHONE='" + user_phone + "' AND RENT_RETURN_YN IS NULL;");

				while (rs.next()) { // ������ �뿩���� ���� �����Ƿ� while������^^
					if (book_ISBN.equals(rs.getString("BOOK_ISBN"))) { // �˻��ؼ� ���� ISBN�� �ش� å�� ISBN�� ������ (���� �������� �����̸�)
						bookBorrowButton.setText("�ݳ��ϱ�");
						bookBorrowButton.setEnabled(true);
					}
				}
				bookDescriptionLabel.setEnabled(false); // ���� ����
				break;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			System.out.println("SQL ���� ����");
		}

		// å ���� �ű��
		int bookScore = 0;
		if (bookReviewCnt != 0) {
			bookScore = bookReviewGrade / bookReviewCnt;
		}
		switch (bookScore) {
		case 0:
			bookGradeLabel.setText("�١١١١�");
			break;
		case 1:
			bookGradeLabel.setText("�ڡ١١١�");
			break;
		case 2:
			bookGradeLabel.setText("�ڡڡ١١�");
			break;
		case 3:
			bookGradeLabel.setText("�ڡڡڡ١�");
			break;
		case 4:
			bookGradeLabel.setText("�ڡڡڡڡ�");
			break;
		case 5:
			bookGradeLabel.setText("�ڡڡڡڡ�");
			break;
		}
		
	}

	// ������ ���� ���θ� ������Ʈ �ϴ� �Լ�
	public void updateUserSuspension(int day) {
		String sql;
		if(day == 0) {	//��ü���� 0���
			sql = "update USER\r\n" + "SET USER_SUSPENSION = NULL\r\n" + "WHERE USER_PHONE = '"
					+ user_phone + "';"; // �ش� ������ �������� NULL�� ������Ʈ
		}
		else {
			Date now = new Date();
	
			Calendar cal = Calendar.getInstance();
			cal.setTime(now); // ���� ������ ������ ��¥�� �������� �������� �߰���Ŵ.
			cal.add(Calendar.DATE, day);
			Date dt = cal.getTime(); // Calendar Ÿ���� DateŸ������ ��ȯ
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String diffTime = sdf.format(dt); // DateŸ���� Mysql DATETIME �������� ����
	
			sql = "update USER\r\n" + "SET USER_POINT = USER_POINT-5, USER_SUSPENSION = '" + diffTime + "'\r\n" + "WHERE USER_PHONE = '"
					+ user_phone + "';"; // �ش� ������ �������� ������Ʈ��Ű�� ����Ʈ 5���� ����.
			
			sql = "update USER SET USER_POINT = USER_POINT + 5\r\n" + "WHERE USER_PHONE = '" + user_phone + "';";
			
		}
		try { // DB ����
			PreparedStatement ps = dbConn.conn.prepareStatement(sql);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("updateUserSuspension sql ����");
		}
	}

	// ���� ���θ� Ȯ�����ִ� �Լ�
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
						s.substring(0, 10); // Mysql DATETIME Ÿ�Կ��� ��¥�� ������
						susDate = dateFormat.parse(s);
						
						int compare = nowDate.compareTo(susDate); // ���� ��¥�� ���� ��¥�� ���ϸ� compare�� 0���� ũ�ٸ� ����
						
						if (compare > 0) { // ���� ���� ��¥�� ���� ��¥���� �ڿ��ִٸ�
							return true;
						}
					}
				}
				
				ResultSet rs = dbConn.executeQuery("SELECT RENT_RETURN_DATE FROM RENT WHERE USER_PHONE='" + user_phone
						+ "' AND RENT_RETURN_YN IS NULL;");

				while (rs.next()) {
					String s = rs.getString("RENT_RETURN_DATE");
					s.substring(0, 10); // Mysql DATETIME Ÿ�Կ��� ��¥�� ������
					returnDate = dateFormat.parse(s);

					int compare = nowDate.compareTo(returnDate); // ���� ��¥�� �ݳ� ��¥�� ���ϸ� compare�� 0���� ũ�ٸ� �ݳ� ������ ����
					
					if (compare > 0) { // ���� �ݳ� ��¥�� ���� ��¥�� �����ٸ�
						Calendar nowCal = Calendar.getInstance();
						nowCal.setTime(nowDate);
						Calendar returnCal = Calendar.getInstance();
						returnCal.setTime(returnDate);

						long diffSec = (nowCal.getTimeInMillis() - returnCal.getTimeInMillis()) / 1000;
						diffDays = diffSec / (24 * 60 * 60); // ���� ��¥�� �ݳ� ���ڼ� ����

						updateUserSuspension((int) diffDays); // �ش� ������ �������� ������Ʈ�ϱ� ���� ���� �����Լ��� ȣ����.
						
						
						return true;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("isSuspension sql ����");
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		return false;
	}

	// �ش� ���� �뿩�� Rent���̺� �����Լ�
	public int BookRent() {

		String sql = "insert into RENT(\r\n" + "BOOK_ISBN,\r\n" + "USER_PHONE,\r\n" + "RENT_DATE,\r\n"
				+ "RENT_RETURN_DATE\r\n" + ")values(\r\n" + "?, ?, NOW(), DATE_ADD(NOW(),INTERVAL 14 DAY));";
		try { // DB ����
			PreparedStatement ps = dbConn.conn.prepareStatement(sql);
			ps.setString(1, book_ISBN);
			ps.setString(2, user_phone);
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("sql ����");
		}
		return 0;
	}

	// �ش� ���� �뿩 Ƚ�� 1���� �Լ�
	public void updateBookRentCnt() {
		String sql = "update BOOK\r\n" + "SET BOOK_RENT_COUNT = BOOK_RENT_COUNT + 1\r\n" + "WHERE BOOK_ISBN = '"
				+ book_ISBN + "';";
		try { // DB ����
			PreparedStatement ps = dbConn.conn.prepareStatement(sql);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("bookRentCnt sql ����");
		}
	}
	
	//���� �ݳ� �Լ�
	public void updateBookReturn() {
		String sql = "update RENT\r\n" 
	+ "SET RENT_RETURN_YN = NOW()\r\n" 
	+ "WHERE RENT.USER_PHONE = '"
	+ user_phone + "' AND RENT.BOOK_ISBN = '" + book_ISBN + "';";
		
		
		
		try { // DB ����
			PreparedStatement ps = dbConn.conn.prepareStatement(sql);
			ps.executeUpdate();
			sql = "update USER SET USER_POINT = USER_POINT + 5\r\n" + "WHERE USER_PHONE = '" + user_phone + "';";
			ps = dbConn.conn.prepareStatement(sql); //�ݳ��� ���������Ƿ� ���� ����Ʈ�� 5����
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("updateBookReturn sql ����");
		}

	}

}
