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

public class AddBook extends JFrame {
	dbConnector dbConn = new dbConnector();
	public String user_phone="";	//���� PK ������ ������ ����
	public Boolean manager = false;	//������ ���������� Ȯ���� ����
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
	public AddBook(String user_phone, Boolean manager) {
		this.user_phone = user_phone;
		this.manager = manager;
		
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

		// å ���� ��
		JLabel bookPictureLabel = new JLabel("\uCC45\uC0AC\uC9C4");
		bookPictureLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bookPictureLabel.setBounds(12, 10, 157, 137);
		panel.add(bookPictureLabel);

		//�̹��� ���� ����
		JFileChooser book_img = new JFileChooser();
		FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("All Images", "jpg","jpge","png","gif");
		book_img.setFileFilter(fileFilter);
		
		//å �ּ� ��
		JLabel book_img_path = new JLabel("�̹��� �ּ�");
		book_img_path.setBounds(12, 157, 157, 29);
		panel.add(book_img_path);
		
		// å �̹��� ã�� ��ư
		JButton bookImageFindButton = new JButton("\uCC3E\uC544\uBCF4\uAE30");
		
		bookImageFindButton.addMouseListener(new MouseAdapter() { // Ŭ���̺�Ʈ
			@Override
			public void mouseClicked(MouseEvent e) {
					int ret = book_img.showOpenDialog(null);	//���� ã�� â�� �����
					String filePath = book_img.getSelectedFile().getPath();	//���� ��θ� filePath�� ����
					
					JOptionPane.showMessageDialog(null, filePath,"����� ������ ������",JOptionPane.NO_OPTION);	//������ ���ϰ�θ� �޽��� â���� ���
					book_img_path.setText(filePath);
					try { // DB ����
						//å �̹���
						File tmpFile = new File(book_img_path.getText());

						Image img = ImageIO.read(tmpFile); // �о�� �̹����� img�� ����
						Image resize_img = img.getScaledInstance(170, 140, Image.SCALE_SMOOTH); // �̹��� ũ�� 170x140 �� ũ�� �����Ͽ�
																								// resize_img�� ����
						ImageIcon icon = new ImageIcon(resize_img); // ������ ũ���� �̹����� icon�� ����
						bookPictureLabel.setIcon(icon); // å ���� ���̺� �̹��� ����
						bookPictureLabel.setBorder(new LineBorder(Color.black, 1, false)); // ���̺� �׵θ� ���������� �׷���
						
					}catch(FileNotFoundException e1) {
						System.out.println("�����߰� ȭ�鿡�� ���� ã�� ����");
					}catch (IOException e1) {
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
		
		//å �̸� ��
		JLabel bookNameLabel = new JLabel("���� : ");
		bookNameLabel.setBounds(12, 10, 46, 46);
		panel_1.add(bookNameLabel);

		// å �̸� �ؽ�Ʈ�ʵ�
		bookNameTextField = new JTextField();
		bookNameTextField.setFont(new Font("���ʷҵ���", Font.BOLD, 22));
		bookNameTextField.setBounds(54, 10, 525, 46);
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
		bookHeaderTextField_1.setBounds(54, 60, 237, 26);
		panel_1.add(bookHeaderTextField_1);

		// å ���ǻ� ��
		JLabel bookHeaderLabel_2 = new JLabel("\uCD9C\uD310\uC0AC : ");
		bookHeaderLabel_2.setFont(new Font("���Ļ�浸��", Font.PLAIN, 16));
		bookHeaderLabel_2.setBounds(303, 60, 60, 26);
		panel_1.add(bookHeaderLabel_2);

		// å ���ǻ� �ؽ�Ʈ�ʵ�
		bookHeaderTextField_2 = new JTextField();
		bookHeaderTextField_2.setFont(new Font("���Ļ�浸��", Font.PLAIN, 16));
		bookHeaderTextField_2.setColumns(10);
		bookHeaderTextField_2.setBounds(362, 60, 217, 26);
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
		bookLinkTextField.setBounds(91, 171, 497, 26);
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
		bookDescriptionTextField = new JTextField("�ٰŸ��� �Է��ϼ���,,,,");
		scrollPane.setViewportView(bookDescriptionTextField);
		bookDescriptionTextField.setColumns(10);

		// å �߰� ��ư
		JButton bookAddButton = new JButton("\uB3C4\uC11C \uCD94\uAC00");
		bookAddButton.setFont(new Font("���Ļ�浸��", Font.PLAIN, 15));
		bookAddButton.setBounds(378, 508, 132, 48);
		//å �߰� ��ư�� ������ ȣ��Ǵ� �޼ҵ� ����
		bookAddButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

					String sql = "insert into BOOK(\r\n"
							+ "BOOK_ISBN,\r\n"
							+ "BOOK_TITLE,\r\n"
							+ "BOOK_AUTHOR,\r\n"
							+ "BOOK_PUB,\r\n"
							+ "BOOK_PRICE,\r\n"
							+ "BOOK_DESCRIPTION,\r\n"
							+ "BOOK_LINK,\r\n"
							+ "BOOK_IMAGE\r\n"
							+ "BOOK_APPEND_DATE\r\n"
							+ ")values(\r\n"
							+ "?, ?, ?, ?, ?, ?, ?,?, date_format(now(),'%Y-%m-%d'));";
					
				try { // DB ����
					PreparedStatement ps = dbConn.conn.prepareStatement(sql);
					
					ps.setString(1, bookISBNTextField.getText());			//ISBN
					ps.setString(2, bookNameTextField.getText());			//å ����
					ps.setString(3, bookHeaderTextField_1.getText());		//å ����
					ps.setString(4, bookHeaderTextField_2.getText());		//å ���ǻ�
					ps.setInt(5, Integer.parseInt(bookPriceTextField.getText()));	//å ����
					ps.setString(6, bookDescriptionTextField.getText());	//å �ٰŸ�
					ps.setString(7, bookLinkTextField.getText());			//å ���ø�ũ
					
					//å �̹���
					File tmpFile = new File(book_img_path.getText());
					FileInputStream input = new FileInputStream(tmpFile);
					ps.setBinaryStream(8, input, (int)tmpFile.length());
					
					int count = ps.executeUpdate();
					if(count==0) {	//���� �߰� ������
						JOptionPane.showMessageDialog(null,"ISBN : "+bookISBNTextField.getText()+"��(��) ��Ͽ� �����Ͽ����ϴ�.", "�űԵ������ ����", JOptionPane.ERROR_MESSAGE);
					}
					else {		//���� �߰� ���н�
						JOptionPane.showMessageDialog(null,"ISBN : "+bookISBNTextField.getText()+"��(��) ��Ͽ� �����Ͽ����ϴ�.", "�űԵ������ ����", JOptionPane.NO_OPTION);
					}
				} catch (SQLException e1) {
					System.out.println("�����߰� ȭ�鿡�� SQL ���� ����");
				}catch(FileNotFoundException e1) {
					System.out.println("�����߰� ȭ�鿡�� ���� ã�� ����");
				}
				dispose();	//�߰� �� â �ݱ�
			}
		});
		contentPane.add(bookAddButton);	//å �߰� ��ư ����
		
	
	}
}
