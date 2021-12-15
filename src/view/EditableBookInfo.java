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
		JButton bookBorrowButton = new JButton("\uB300\uCD9C\uAC00\uB2A5");
		bookBorrowButton.setEnabled(false);
		bookBorrowButton.addMouseListener(new MouseAdapter() { // Ŭ���̺�Ʈ
			@Override
			public void mouseClicked(MouseEvent e) {
				if (bookBorrowButton.getText().equals("�����ϱ�")) // �����ϱ� ��ư ������ ��
				{
					if (true) // ���� ���� ���� ��
					{
						JOptionPane.showMessageDialog(null, "�ش� ������ �����Ͽ����ϴ�.\n1000-00-00 ���� �ݳ��� �ּ���.", "���⼺��",
								JOptionPane.INFORMATION_MESSAGE);
						bookBorrowButton.setText("�ݳ��ϱ�");
					} else if (false) // ���� ���� ���� ��(���� ���� ������ �ʰ�)
						JOptionPane.showMessageDialog(null, "���� ������ �������� �ʰ��Ͽ����ϴ�.\n�ٸ� ������ �ݳ� �� �ٽ� �õ��� �ּ���.", "�������",
								JOptionPane.WARNING_MESSAGE);
					else // ���� ���(���� ���� ���� ��(��ü�� å ����))
						JOptionPane.showMessageDialog(null, "��ü�� å�� �ֽ��ϴ�.\n���߿� �ٽ� �õ��� �ּ���.", "�������",
								JOptionPane.WARNING_MESSAGE);
				} else if (bookBorrowButton.getText().equals("�ݳ��ϱ�")) // �ݳ��ϱ� ��ư ������ ��
				{
					if (true) // ��ü�� �� �� ���
						JOptionPane.showMessageDialog(null, "�ݳ��� �����Ͽ����ϴ�", "�ݳ�����", JOptionPane.INFORMATION_MESSAGE);
					else // ��ü�� ���
						JOptionPane.showMessageDialog(null, "�ݳ��� �����Ͽ����ϴ�\nȸ������ ��ü���� 8���Դϴ�.\n�ش� �Ⱓ���� å�� �̿��Ͻ� �� �����ϴ�.",
								"�ݳ�����", JOptionPane.INFORMATION_MESSAGE);
					// ���� �ۼ�â ���
					// WriteReview writereview = new WriteReview();
					// writereview.setVisible(true);
					bookBorrowButton.setText("�����ϱ�"); // �����ϱ� ��ư���� ����
				}
			}
		});
		bookBorrowButton.setFont(new Font("\uD55C\uCEF4\uC0B0\uB73B\uB3CB\uC6C0",
				bookBorrowButton.getFont().getStyle() | Font.BOLD, bookBorrowButton.getFont().getSize() + 3));
		bookBorrowButton.setBounds(12, 190, 157, 19);
		panel.add(bookBorrowButton);

		// å ���� ��ư
		JButton bookDeleteButton_1_1 = new JButton("\uC0AD\uC81C");
		bookDeleteButton_1_1.setFont(new Font("���Ļ�浸��", Font.BOLD, 15));
		bookDeleteButton_1_1.setBounds(92, 210, 77, 19);
		panel.add(bookDeleteButton_1_1);

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
		bookNameLabel.setBounds(12, 10, 178, 46);
		panel_1.add(bookNameLabel);

		// å ���� ��
		JLabel bookHeaderLabel_1 = new JLabel("\uC800\uC790 : \uD669\uAE30\uD0DC");
		bookHeaderLabel_1.setFont(new Font("���Ļ�浸��", Font.PLAIN, 16));
		bookHeaderLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		bookHeaderLabel_1.setBounds(12, 60, 100, 26);
		panel_1.add(bookHeaderLabel_1);

		// å ���ǻ� ��
		JLabel bookHeaderLabel_2 = new JLabel("\uCD9C\uD310\uC0AC : \uC0DD\uB2A5\uCD9C\uD310\uC0AC");
		bookHeaderLabel_2.setFont(new Font("���Ļ�浸��", Font.PLAIN, 16));
		bookHeaderLabel_2.setBounds(131, 60, 193, 26);
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

		// å ���� ��ư
		JButton bookEditButton = new JButton("\uC218\uC815");
		bookEditButton.setFont(new Font("���Ļ�浸��", Font.BOLD, 15));
		bookEditButton.setBounds(12, 210, 77, 19);
		bookEditButton.addMouseListener(new MouseAdapter() {
			// �����ϱ� ��ư�� Ŭ������ ��
			@Override
			public void mouseClicked(MouseEvent e) {
				EditBook edit_book_frame = new EditBook(book_ISBN,user_phone,manager);

				edit_book_frame.setLocationRelativeTo(null); // ȭ���߾ӿ� ���
				edit_book_frame.setVisible(true); // å ����â ���
				setVisible(false);
			}
		});
		panel.add(bookEditButton);
		try { // DB ����
			
			//�ش� ������ ���� ���� ��������
			
			 ResultSet rs = dbConn.executeQuery(
					"SELECT COUNT(*) FROM REVIEW WHERE BOOK_ISBN = '" + book_ISBN + "';"
					);
			if(rs.next()) {
				bookReviewCnt = rs.getInt(1);
			}
			
				// db���� ISBN���� å ���� �˻�
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
				// å �̹��� ����
				InputStream inputStream = rs.getBinaryStream("BOOK_IMAGE"); // �̹����� �о��
				bookReviewGrade = Integer.parseInt(rs.getString("BOOK_GRADE"));	//å ����
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
				// �ش�å�� �������� �������� �˻�
				rs = dbConn.executeQuery(
						"SELECT BOOK_ISBN FROM RENT WHERE BOOK_ISBN='" + book_ISBN + "' AND RENT_RETURN_YN IS NULL;");
				if (rs.next()) {
					if (book_ISBN.equals(rs.getString("BOOK_ISBN"))) { // �˻��ؼ� ���� ISBN�� �ش� å�� ISBN�� ������ (�������� �����̸�)
						bookBorrowButton.setText("�뿩��");
						bookBorrowButton.setEnabled(false);
					}
				}
				// ���� ������ �������� �˻�
				rs = dbConn.executeQuery(
						"SELECT BOOK_ISBN FROM RENT WHERE USER_PHONE='" + user_phone + "' AND RENT_RETURN_YN IS NULL;");
				if (rs.next()) {
					if (book_ISBN.equals(rs.getString("BOOK_ISBN"))) { // �˻��ؼ� ���� ISBN�� �ش� å�� ISBN�� ������ (���� �������� �����̸�)
						bookBorrowButton.setText("�ݳ��ϱ�");
						bookBorrowButton.setEnabled(true);
					}
				}
				bookDescriptionLabel.setEnabled(false); // ���� ����
			}
		} catch (SQLException e2) {
			System.out.println("SQL ���� ����");
		}
		
		//å ���� �ű��
				int bookScore = 0;
				if(bookReviewCnt != 0) {
					bookScore = bookReviewGrade / bookReviewCnt;
				}
				switch(bookScore) {
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
}
