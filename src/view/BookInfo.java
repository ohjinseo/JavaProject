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
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import javax.swing.DropMode;
import java.awt.Panel;
import javax.swing.JScrollPane;

public class BookInfo extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookInfo frame = new BookInfo();
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
	public BookInfo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 845, 663);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
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
		JButton bookBorrowButton = new JButton("\uB300\uCD9C\uD558\uAE30");
		bookBorrowButton.addMouseListener(new MouseAdapter() { // Ŭ���̺�Ʈ
			@Override
			public void mouseClicked(MouseEvent e) {
				if (bookBorrowButton.getText() == "�����ϱ�") // �����ϱ� ��ư ������ ��
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
				} else // ������(�ݳ��ϱ� ��ư ������ ��)
				{
					if (true) // ��ü�� �� �� ���
						JOptionPane.showMessageDialog(null, "�ݳ��� �����Ͽ����ϴ�", "�ݳ�����", JOptionPane.INFORMATION_MESSAGE);
					else // ��ü�� ���
						JOptionPane.showMessageDialog(null, "�ݳ��� �����Ͽ����ϴ�\nȸ������ ��ü���� 8���Դϴ�.\n�ش� �Ⱓ���� å�� �̿��Ͻ� �� �����ϴ�.",
								"�ݳ�����", JOptionPane.INFORMATION_MESSAGE);
					WriteReview writereview=new WriteReview();
					writereview.setVisible(true);
					bookBorrowButton.setText("�����ϱ�");
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
		bookNameLabel.setBounds(12, 10, 178, 46);
		panel_1.add(bookNameLabel);

		// å ���� ��
		JLabel bookHeaderLabel_1 = new JLabel("\uC800\uC790 : \uD669\uAE30\uD0DC");
		bookHeaderLabel_1.setFont(new Font("���Ļ�浸��", Font.PLAIN, 14));
		bookHeaderLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		bookHeaderLabel_1.setBounds(12, 60, 100, 26);
		panel_1.add(bookHeaderLabel_1);

		// å ���ǻ� ��
		JLabel bookHeaderLabel_2 = new JLabel("\uCD9C\uD310\uC0AC : \uC0DD\uB2A5\uCD9C\uD310\uC0AC");
		bookHeaderLabel_2.setFont(new Font("���Ļ�浸��", Font.PLAIN, 14));
		bookHeaderLabel_2.setBounds(131, 60, 193, 26);
		panel_1.add(bookHeaderLabel_2);

		// å ���ø�ũ ��
		JLabel bookLinkLabel = new JLabel("\uAD00\uB828\uB9C1\uD06C : www.abc.com");
		bookLinkLabel.setFont(new Font("���Ļ�浸��", Font.PLAIN, 20));
		bookLinkLabel.setBounds(12, 200, 567, 26);
		panel_1.add(bookLinkLabel);

		// å ���� ��
		JLabel bookGradeLabel = new JLabel("\uD3C9\uC810 : \u2605\u2605\u2605\u2605\u2606");
		bookGradeLabel.setFont(new Font("���Ļ�浸��", Font.PLAIN, 20));
		bookGradeLabel.setBounds(12, 92, 193, 26);
		panel_1.add(bookGradeLabel);

		// å ���� ��
		JLabel bookPriceLabel = new JLabel("\uAC00\uACA9 : 28000\uC6D0");
		bookPriceLabel.setFont(new Font("���Ļ�浸��", Font.PLAIN, 20));
		bookPriceLabel.setBounds(12, 128, 193, 26);
		panel_1.add(bookPriceLabel);

		// å ISBN ��
		JLabel bookISBNLabel = new JLabel("ISBN : 123456789");
		bookISBNLabel.setFont(new Font("���Ļ�浸��", Font.PLAIN, 20));
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
		panel_2.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(12, 283, 805, 142);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 805, 142);
		panel_2.add(scrollPane);
		
		JTextArea bookDescriptionLabel = new JTextArea();
		bookDescriptionLabel.setText("\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC\uC904\uAC70\uB9AC");
		bookDescriptionLabel.setLineWrap(true);
		bookDescriptionLabel.setFont(new Font("���Ļ�浸��", Font.PLAIN, 20));
		scrollPane.setViewportView(bookDescriptionLabel);

		// å ���� �г�
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setBounds(12, 435, 805, 179);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		ReviewPanel review = new ReviewPanel();
		review.setSize(781, 55);
		review.setLocation(12, 10);
		panel_3.add(review);
		
		ReviewPanel review_1 = new ReviewPanel();
		review_1.setBounds(12, 63, 781, 55);
		panel_3.add(review_1);
		
		ReviewPanel review_2 = new ReviewPanel();
		review_2.setBounds(12, 114, 781, 55);
		panel_3.add(review_2);
	}
}
