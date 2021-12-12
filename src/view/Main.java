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
	public String user_phone = ""; // ���� PK ������ ������ ����
	public Boolean manager = false; // ������ ���������� Ȯ���� ����
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
		this.user_phone = user_phone; // �α����� ������ PK
		this.manager = manager;
		setTitle("\uB3C4\uC11C \uAD00\uB9AC \uD504\uB85C\uADF8\uB7A8 - \uBA54\uC778");
		setBounds(100, 100, 881, 694);
		contentPane = new JPanel(); // ���� ������
		contentPane.setBackground(SystemColor.menu);
		setContentPane(contentPane);
		contentPane.setLayout(null); // ���� ������ ���̾ƿ� null�� ����
		setResizable(false); // â ũ�� ����
		setLocationRelativeTo(null); // �߾ӿ� ���

		// �޴���
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.setBackground(new Color(51, 51, 51));
		menuBar.setBounds(0, 0, 865, 47);
		contentPane.add(menuBar);

		// Ȩ������ �޴�
		JMenu homeIconMenu = new JMenu(" \uD648 ");
		homeIconMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		homeIconMenu.setFont(new Font("���Ļ�浸��", Font.PLAIN, 16));
		homeIconMenu.setForeground(new Color(255, 255, 255));
		ImageIcon icon5 = new ImageIcon("D:\\\uC0C8 \uD3F4\uB354\\pngegg.png");
		Image img5 = icon5.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon changeIcon5 = new ImageIcon(img5);
		homeIconMenu.setIcon(changeIcon5);
		menuBar.add(homeIconMenu);

		// ���� ã�� �޴�
		JMenu findBookMenu = new JMenu("\uB3C4\uC11C\uCC3E\uAE30"); // �޴� - ����ã��
		findBookMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				searchBookFrame = new SearchBook(user_phone, manager);
				searchBookFrame.setVisible(true);
				setVisible(false);

			}
		});

		findBookMenu.setForeground(new Color(255, 255, 255));
		findBookMenu.setFont(new Font("���Ļ�浸��", Font.PLAIN, 16));
		menuBar.add(findBookMenu);

		// ȸ�� ���� �޴�
		JMenu userInfoMenu = new JMenu("\uD68C\uC6D0\uC815\uBCF4");
		userInfoMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				userInfoFrame = new UserInfo(user_phone, manager);
				userInfoFrame.setLocationRelativeTo(null); // �߾ӿ� ���
				userInfoFrame.setVisible(true);
				setVisible(false);
			}
		});
		userInfoMenu.setForeground(new Color(255, 255, 255));
		userInfoMenu.setBackground(new Color(230, 230, 250));
		userInfoMenu.setFont(new Font("���Ļ�浸��", Font.PLAIN, 16));
		menuBar.add(userInfoMenu);

		// �����ڸ� ���̴� �޴�
		if (manager) {
			JMenu search_user_menu = new JMenu("ȸ���˻�");
			search_user_menu.addMouseListener(new MouseAdapter() { // ���콺 Ŭ�� �̺�Ʈ �߻��� ȣ��
				@Override
				public void mouseClicked(MouseEvent e) {
					SearchUser search_user_frame = new SearchUser(user_phone, manager); // �����˻�â ȣ��
					search_user_frame.setLocationRelativeTo(null); // �߾ӿ� ���
					search_user_frame.setResizable(false); // â ũ�� ����
					search_user_frame.setVisible(true);
					setVisible(false);
					// �����˻� â���� â�� ������ ȣ��Ǵ� �޼ҵ�
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
			search_user_menu.setFont(new Font("���Ļ�浸��", Font.PLAIN, 16));
			menuBar.add(search_user_menu);

			JMenu add_book_menu = new JMenu("�����߰�");
			add_book_menu.addMouseListener(new MouseAdapter() { // ���콺 Ŭ�� �̺�Ʈ �߻��� ȣ��
				@Override
				public void mouseClicked(MouseEvent e) {
					AddBook add_book_frame = new AddBook(user_phone, manager); // �����߰�â ȣ��
					add_book_frame.setLocationRelativeTo(null); // �߾ӿ� ���
					add_book_frame.setResizable(false); // â ũ�� ����
					add_book_frame.setVisible(true);
					// �����߰� â���� â�� ������ ȣ��Ǵ� �޼ҵ�
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
			add_book_menu.setFont(new Font("���Ļ�浸��", Font.PLAIN, 16));
			menuBar.add(add_book_menu);
		}

		// �α⵵�� ��
		JLabel popularBookLabel = new JLabel("\uC778\uAE30\uB3C4\uC11C");
		popularBookLabel.setForeground(new Color(51, 51, 51));
		popularBookLabel.setFont(new Font("���Ļ�浸��", Font.BOLD, 20));
		popularBookLabel.setBounds(22, 63, 123, 40);
		contentPane.add(popularBookLabel);

		// �α⵵�� �г�
		JPanel popularBookPanel = new JPanel();
		popularBookPanel.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		popularBookPanel.setBackground(Color.WHITE);
		popularBookPanel.setBounds(22, 101, 820, 125);
		popularBookPanel.setLayout(null);

		JLabel[] popularBookLabel_array = new JLabel[6];
		// �гο� ����� å �̹����� ���� Label 6�� ����
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

		// �Ű����� ��
		JLabel newlyBookLabel = new JLabel("\uC2E0\uAC04\uB3C4\uC11C");
		newlyBookLabel.setFont(new Font("���Ļ�浸��", Font.BOLD, 20));
		newlyBookLabel.setBounds(22, 260, 123, 40);
		contentPane.add(newlyBookLabel);

		// �Ű����� �г�
		JPanel newlyBookPanel = new JPanel();
		newlyBookPanel.setBackground(Color.WHITE);
		newlyBookPanel.setForeground(new Color(255, 255, 255));
		newlyBookPanel.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		newlyBookPanel.setBounds(22, 298, 820, 125);
		newlyBookPanel.setLayout(null);

		JLabel[] newlyBookLabel_array = new JLabel[6];
		// �гο� ����� å �̹����� ���� Label 6�� ����
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

		// ���ã�� ��
		JLabel favoriteBookLabel = new JLabel("\uC990\uACA8\uCC3E\uAE30");
		favoriteBookLabel.setFont(new Font("���Ļ�浸��", Font.BOLD, 20));
		favoriteBookLabel.setBounds(22, 462, 123, 40);
		contentPane.add(favoriteBookLabel);

		// ���ã�� �г�
		JPanel favoriteBookPanel = new JPanel();
		favoriteBookPanel.setBackground(Color.WHITE);
		favoriteBookPanel.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		favoriteBookPanel.setBounds(22, 502, 820, 125);
		contentPane.add(favoriteBookPanel);

		try { // DB ����
			ResultSet rs = dbConn.executeQuery(
					"SELECT BOOK_IMAGE FROM BOOK WHERE BOOK_PRE = TRUE\r\n" + "order by BOOK_GRADE DESC;"); // �α������ ����

			append_img(rs, popularBookLabel_array); // �α������ ���� 6���� �̹����� �α�� �г��� popularBookLabel�� ����

			contentPane.add(popularBookPanel); // ����Ʈ�ҿ� �α�� �г� ����

			rs = dbConn.executeQuery(
					"SELECT BOOK_IMAGE FROM BOOK WHERE BOOK_PRE = TRUE\r\n" + "order by BOOK_APPEND_DATE DESC;"); // �ֽż�����
																													// ����

			append_img(rs, newlyBookLabel_array); // �α������ ���� 6���� �̹����� �α�� �г��� popularBookLabel�� ����

			contentPane.add(newlyBookPanel);

		} catch (SQLException e2) {
			System.out.println("����ȭ�鿡�� SQL ���� ����");
		}

	}

	// ResultSet�� JLabel�� �޾� JLabel�� ���� �̹����� �����ϴ� �Լ�
	public void append_img(ResultSet rs, JLabel[] array) throws SQLException {
		int i = 0;
		while (rs.next()) {
			InputStream inputStream = rs.getBinaryStream("BOOK_IMAGE"); // �̹����� �о��
			try {
				Image img = ImageIO.read(inputStream); // �о�� �̹����� img�� ����
				Image resize_img = img.getScaledInstance(105, 105, Image.SCALE_SMOOTH); // �̹��� ũ�� 105x105�� ũ�� �����Ͽ�
																						// resize_img�� ����
				ImageIcon icon = new ImageIcon(resize_img); // ������ ũ���� �̹����� icon�� ����
				array[i].setIcon(icon); // �α�� �гο� icon ���ʷ� ����
				array[i].setBorder(new LineBorder(Color.black, 1, false)); // ���̺� �׵θ� ���������� �׷���
			} catch (IOException e) {
				e.printStackTrace();
			}
			i++;
			if (i == 6) // 6�������� ���
				break;
		}
	}
}
