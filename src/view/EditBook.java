package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javax.swing.JTextField;

public class EditBook extends JFrame {
	dbConnector dbConn = new dbConnector();
	String book_ISBN = "";
	String user_phone ="";
	Boolean manager = false;
	private JPanel contentPane;
	private JTextField bookNameTextField;
	private JTextField bookHeaderTextField_1;
	private JTextField bookHeaderTextField_2;
	private JTextField bookPriceTextField;
	private JTextField bookISBNTextField;
	private JTextField bookLinkTextField;
	private JTextArea bookDescriptionTextField;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public EditBook(String book_ISBN, String user_phone, Boolean manager) {
		this.user_phone = user_phone;
		this.manager = manager;
		this.book_ISBN = book_ISBN;
		setTitle("\uB3C4\uC11C \uAD00\uB9AC \uD504\uB85C\uADF8\uB7A8 - \uB3C4\uC11C\uC218\uC815");
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
		bookPictureLabel.setBounds(12, 10, 157, 147);
		panel.add(bookPictureLabel);

		// �̹��� ���� ����
		JFileChooser book_img = new JFileChooser();
		FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("All Images", "jpg", "jpge", "png", "gif");
		book_img.setFileFilter(fileFilter);

		// å �ּ� ��
		JLabel book_img_path = new JLabel("�̹��� �ּ�");
		book_img_path.setBounds(12, 157, 157, 29);
		panel.add(book_img_path);

		// å �̹��� ã�� ��ư
		JButton bookImageFindButton = new JButton("\uCC3E\uC544\uBCF4\uAE30");

		bookImageFindButton.addMouseListener(new MouseAdapter() { // Ŭ���̺�Ʈ
			@Override
			public void mouseClicked(MouseEvent e) {
				int ret = book_img.showOpenDialog(null); // ���� ã�� â�� �����
				String filePath = book_img.getSelectedFile().getPath(); // ���� ��θ� filePath�� ����

				JOptionPane.showMessageDialog(null, filePath, "����� ������ ������", JOptionPane.NO_OPTION); // ������ ���ϰ�θ� �޽���
																										// â���� ���
				book_img_path.setText(filePath);
				try { // DB ����
						// å �̹���
					File tmpFile = new File(book_img_path.getText());

					Image img = ImageIO.read(tmpFile); // �о�� �̹����� img�� ����
					Image resize_img = img.getScaledInstance(170, 147, Image.SCALE_SMOOTH); // �̹��� ũ�� 170x140 �� ũ�� �����Ͽ�
																							// resize_img�� ����
					ImageIcon icon = new ImageIcon(resize_img); // ������ ũ���� �̹����� icon�� ����
					bookPictureLabel.setIcon(icon); // å ���� ���̺� �̹��� ����
					bookPictureLabel.setBorder(new LineBorder(Color.black, 1, false)); // ���̺� �׵θ� ���������� �׷���

				} catch (FileNotFoundException e1) {
					System.out.println("�����߰� ȭ�鿡�� ���� ã�� ����");
				} catch (IOException e1) {
					System.out.println("�����߰� ȭ�鿡�� �о�� ���� ��� ����");
				}
			}
		});
		bookImageFindButton.setFont(new Font("\uD55C\uCEF4\uC0B0\uB73B\uB3CB\uC6C0",
				bookImageFindButton.getFont().getStyle() | Font.BOLD, bookImageFindButton.getFont().getSize() + 3));
		bookImageFindButton.setBounds(12, 190, 157, 39);
		panel.add(bookImageFindButton);

		// å ���� �г�
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(205, 31, 612, 239);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		// å �̸� �ؽ�Ʈ�ʵ�
		bookNameTextField = new JTextField();
		bookNameTextField.setFont(new Font("���ʷҵ���", Font.BOLD, 22));
		bookNameTextField.setBounds(12, 10, 567, 46);
		panel_1.add(bookNameTextField);
		bookNameTextField.setColumns(10);

		// å ���� ��
		JLabel bookHeaderLabel_1 = new JLabel("\uC800\uC790 :");
		bookHeaderLabel_1.setFont(new Font("���Ļ�浸��", Font.PLAIN, 16));
		bookHeaderLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		bookHeaderLabel_1.setBounds(12, 60, 46, 26);
		panel_1.add(bookHeaderLabel_1);

		// å ���� �ؽ�Ʈ�ʵ�
		bookHeaderTextField_1 = new JTextField();
		bookHeaderTextField_1.setFont(new Font("���Ļ�浸��", Font.PLAIN, 16));
		bookHeaderTextField_1.setColumns(10);
		bookHeaderTextField_1.setBounds(54, 60, 76, 26);
		panel_1.add(bookHeaderTextField_1);

		// å ���ǻ� ��
		JLabel bookHeaderLabel_2 = new JLabel("\uCD9C\uD310\uC0AC : ");
		bookHeaderLabel_2.setFont(new Font("���Ļ�浸��", Font.PLAIN, 16));
		bookHeaderLabel_2.setBounds(131, 60, 58, 26);
		panel_1.add(bookHeaderLabel_2);

		// å ���ǻ� �ؽ�Ʈ�ʵ�
		bookHeaderTextField_2 = new JTextField();
		bookHeaderTextField_2.setFont(new Font("���Ļ�浸��", Font.PLAIN, 16));
		bookHeaderTextField_2.setColumns(10);
		bookHeaderTextField_2.setBounds(188, 60, 136, 26);
		panel_1.add(bookHeaderTextField_2);

		// å ���ø�ũ ��
		JLabel bookLinkLabel = new JLabel("\uAD00\uB828\uB9C1\uD06C : ");
		bookLinkLabel.setFont(new Font("���Ļ�浸��", Font.PLAIN, 16));
		bookLinkLabel.setBounds(12, 171, 83, 26);
		panel_1.add(bookLinkLabel);

		// å ���ø�ũ �ؽ�Ʈ�ʵ�
		bookLinkTextField = new JTextField();
		bookLinkTextField.setFont(new Font("���Ļ�浸��", Font.PLAIN, 16));
		bookLinkTextField.setColumns(10);
		bookLinkTextField.setBounds(82, 171, 497, 26);
		panel_1.add(bookLinkTextField);

		// å ���� ��
		JLabel bookPriceLabel = new JLabel("\uAC00\uACA9 : ");
		bookPriceLabel.setFont(new Font("���Ļ�浸��", Font.PLAIN, 16));
		bookPriceLabel.setBounds(12, 99, 46, 26);
		panel_1.add(bookPriceLabel);

		// å ���� �ؽ�Ʈ�ʵ�
		bookPriceTextField = new JTextField();
		bookPriceTextField.setFont(new Font("���Ļ�浸��", Font.PLAIN, 16));
		bookPriceTextField.setColumns(10);
		bookPriceTextField.setBounds(54, 99, 525, 26);
		panel_1.add(bookPriceTextField);

		// å ISBN ��
		JLabel bookISBNLabel = new JLabel("ISBN : ");
		bookISBNLabel.setFont(new Font("���Ļ�浸��", Font.PLAIN, 16));
		bookISBNLabel.setBounds(12, 135, 58, 26);
		panel_1.add(bookISBNLabel);

		// å ISBN �ؽ�Ʈ�ʵ�
		bookISBNTextField = new JTextField();
		bookISBNTextField.setFont(new Font("���Ļ�浸��", Font.PLAIN, 16));
		bookISBNTextField.setColumns(10);
		bookISBNTextField.setBounds(64, 135, 407, 26);
		panel_1.add(bookISBNTextField);

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


	

		// å �ٰŸ� �ؽ�Ʈ�ʵ�
		bookDescriptionTextField = new JTextArea();
		scrollPane.setViewportView(bookDescriptionTextField);
		bookDescriptionTextField.setColumns(10);
		bookDescriptionTextField.setLineWrap(true);
		bookDescriptionTextField.setFont(new Font("���Ļ�浸��", Font.PLAIN, 16));
		
		try { // DB ����
				// db���� ISBN���� å ���� �˻�
			ResultSet rs = dbConn.executeQuery(
					"SELECT BOOK_TITLE, BOOK_AUTHOR, BOOK_PUB, BOOK_PRICE, BOOK_ISBN, BOOK_LINK, BOOK_DESCRIPTION, BOOK_IMAGE\r\n"
							+ "FROM BOOK\r\n" + "WHERE BOOK_ISBN = '" + book_ISBN + "' AND BOOK_PRE = TRUE;");
			while (rs.next()) {
				bookNameTextField.setText(rs.getString("BOOK_TITLE")); // å ���� ����
				bookHeaderTextField_1.setText(rs.getString("BOOK_AUTHOR")); // å ���� ����
				bookHeaderTextField_2.setText(rs.getString("BOOK_PUB")); // å ���ǻ� ����
				bookPriceTextField.setText(rs.getString("BOOK_PRICE")); // å ���� ����
				bookISBNTextField.setText(rs.getString("BOOK_ISBN")); // å ISBN ����
				bookLinkTextField.setText(rs.getString("BOOK_LINK")); // å ���ø�ũ ����
				bookDescriptionTextField.setText(rs.getString("BOOK_DESCRIPTION")); // å �ٰŸ� ����
				// å �̹��� ����
				InputStream inputStream = rs.getBinaryStream("BOOK_IMAGE"); // �̹����� �о��
				try {
					Image img = ImageIO.read(inputStream); // �о�� �̹����� img�� ����
					Image resize_img = img.getScaledInstance(170, 147, Image.SCALE_SMOOTH); // �̹��� ũ�� 170x147�� ũ�� �����Ͽ�
																							// resize_img�� ����
					ImageIcon icon = new ImageIcon(resize_img); // ������ ũ���� �̹����� icon�� ����
					bookPictureLabel.setIcon(icon); // å �̹��� ����
					bookPictureLabel.setBorder(new LineBorder(Color.black, 1, false)); // �̹��� ���̺� �׵θ� ���������� ����
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e2) {
			System.out.println("SQL ���� ����");
		}
		// å ���� ��ư
		JButton bookEditButton = new JButton("\uB3C4\uC11C \uC218\uC815");
		bookEditButton.setFont(new Font("���Ļ�浸��", Font.PLAIN, 15));
		bookEditButton.setBounds(378, 508, 132, 48);
		bookEditButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//���� ���� ������ sql�� �ۼ��ؾ���
				
				EditableBookInfo manager_book_info = new EditableBookInfo(book_ISBN, user_phone, manager);
				manager_book_info.setLocationRelativeTo(null); // ȭ���߾ӿ� ���
				manager_book_info.setVisible(true); // å ����â ���
				dispose();
			}
		});
		contentPane.add(bookEditButton);
	}
}
