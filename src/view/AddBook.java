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
import javax.swing.JSeparator;
import java.awt.Window.Type;
import javax.swing.UIManager;
import javax.swing.JTextField;

public class AddBook extends JFrame {

	private JPanel contentPane;
	private JTextField bookNameTextField;
	private JTextField bookHeaderTextField_1;
	private JTextField bookHeaderTextField_2;
	private JTextField bookPriceTextField;
	private JTextField bookISBNTextField;
	private JTextField bookLinkTextField;
	private JTextField bookDescriptionTextField;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public AddBook() {
		setTitle("\uB3C4\uC11C \uAD00\uB9AC \uD504\uB85C\uADF8\uB7A8 - \uB3C4\uC11C\uCD94\uAC00");
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
		JButton bookImageFindButton = new JButton("\uCC3E\uC544\uBCF4\uAE30");
		bookImageFindButton.addMouseListener(new MouseAdapter() { // 클릭이벤트
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		bookImageFindButton.setFont(new Font("\uD55C\uCEF4\uC0B0\uB73B\uB3CB\uC6C0",
				bookImageFindButton.getFont().getStyle() | Font.BOLD, bookImageFindButton.getFont().getSize() + 3));
		bookImageFindButton.setBounds(12, 190, 157, 39);
		panel.add(bookImageFindButton);

		// 책 정보 패널
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(205, 31, 612, 239);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		// 책 이름 텍스트필드
		bookNameTextField = new JTextField();
		bookNameTextField.setFont(new Font("함초롬돋움", Font.BOLD, 22));
		bookNameTextField.setBounds(12, 10, 567, 46);
		panel_1.add(bookNameTextField);
		bookNameTextField.setColumns(10);

		// 책 저자 라벨
		JLabel bookHeaderLabel_1 = new JLabel("\uC800\uC790 :");
		bookHeaderLabel_1.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		bookHeaderLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		bookHeaderLabel_1.setBounds(12, 60, 46, 26);
		panel_1.add(bookHeaderLabel_1);

		// 책 저자 텍스트필드
		bookHeaderTextField_1 = new JTextField();
		bookHeaderTextField_1.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		bookHeaderTextField_1.setColumns(10);
		bookHeaderTextField_1.setBounds(54, 60, 76, 26);
		panel_1.add(bookHeaderTextField_1);

		// 책 출판사 라벨
		JLabel bookHeaderLabel_2 = new JLabel("\uCD9C\uD310\uC0AC : ");
		bookHeaderLabel_2.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		bookHeaderLabel_2.setBounds(131, 60, 58, 26);
		panel_1.add(bookHeaderLabel_2);

		// 책 출판사 텍스트필드
		bookHeaderTextField_2 = new JTextField();
		bookHeaderTextField_2.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		bookHeaderTextField_2.setColumns(10);
		bookHeaderTextField_2.setBounds(188, 60, 136, 26);
		panel_1.add(bookHeaderTextField_2);

		// 책 관련링크 라벨
		JLabel bookLinkLabel = new JLabel("\uAD00\uB828\uB9C1\uD06C : ");
		bookLinkLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		bookLinkLabel.setBounds(12, 171, 83, 26);
		panel_1.add(bookLinkLabel);

		// 책 관련링크 텍스트필드
		bookLinkTextField = new JTextField();
		bookLinkTextField.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		bookLinkTextField.setColumns(10);
		bookLinkTextField.setBounds(82, 171, 497, 26);
		panel_1.add(bookLinkTextField);

		// 책 가격 라벨
		JLabel bookPriceLabel = new JLabel("\uAC00\uACA9 : ");
		bookPriceLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		bookPriceLabel.setBounds(12, 99, 46, 26);
		panel_1.add(bookPriceLabel);

		// 책 가격 텍스트필드
		bookPriceTextField = new JTextField();
		bookPriceTextField.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		bookPriceTextField.setColumns(10);
		bookPriceTextField.setBounds(54, 99, 525, 26);
		panel_1.add(bookPriceTextField);

		// 책 ISBN 라벨
		JLabel bookISBNLabel = new JLabel("ISBN : ");
		bookISBNLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		bookISBNLabel.setBounds(12, 135, 58, 26);
		panel_1.add(bookISBNLabel);

		// 책 ISBN 텍스트필드
		bookISBNTextField = new JTextField();
		bookISBNTextField.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		bookISBNTextField.setColumns(10);
		bookISBNTextField.setBounds(64, 135, 407, 26);
		panel_1.add(bookISBNTextField);

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

		// 책 줄거리 텍스트필드
		bookDescriptionTextField = new JTextField();
		scrollPane.setViewportView(bookDescriptionTextField);
		bookDescriptionTextField.setColumns(10);

		// 책 추가 버튼
		JButton bookAddButton = new JButton("\uB3C4\uC11C \uCD94\uAC00");
		bookAddButton.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 15));
		bookAddButton.setBounds(378, 508, 132, 48);
		contentPane.add(bookAddButton);
	}
}
