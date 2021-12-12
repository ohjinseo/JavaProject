package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JScrollBar;
import javax.swing.AbstractListModel;
import javax.swing.border.CompoundBorder;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.SystemColor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.*;
import java.util.Properties;

public class SearchBook extends JFrame {
	dbConnector dbConn = new dbConnector();
	public String user_phone = "";
	public Boolean manager = false;
	private JPanel contentPane;
	private JTextField searchTextField;
	private JTable table;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();

	private Main mainFrame;
	private UserInfo userInfoFrame;

	/**
	 * ���� ã�� ����ȭ��
	 */
	public SearchBook(String user_phone, Boolean manager) {
		this.user_phone = user_phone;
		this.manager = manager;
		setTitle("\uB3C4\uC11C \uAD00\uB9AC \uD504\uB85C\uADF8\uB7A8 - \uB3C4\uC11C\uAC80\uC0C9");
		setBounds(100, 100, 881, 706);
		contentPane = new JPanel(); // ���� ������
		contentPane.setBackground(SystemColor.menu);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false); // â ũ�� ����
		setLocationRelativeTo(null); // �߾ӿ� ���

		// �޴���
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 865, 47);
		menuBar.setBorderPainted(false);
		menuBar.setBackground(new Color(51, 51, 51));
		contentPane.add(menuBar);

		// Ȩ������ �޴�
		JMenu homeIconMenu = new JMenu(" Ȩ ");
		homeIconMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mainFrame = new Main(user_phone, manager);
				mainFrame.setVisible(true);
				setVisible(false);
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
		JMenu findBookMenu = new JMenu("����ã��"); // �޴� - ����ã��
		findBookMenu.setForeground(new Color(255, 255, 255));
		findBookMenu.setFont(new Font("���Ļ�浸��", Font.PLAIN, 16));
		menuBar.add(findBookMenu);

		// ȸ�� ���� �޴�
		JMenu userInfoMenu = new JMenu("ȸ������");
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

		// �����߰� �޴� (�����ڸ�)
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

		// �˻� �г�
		JPanel panel = new JPanel();
		panel.setBounds(22, 57, 819, 38);
		contentPane.add(panel);
		panel.setLayout(null);

		// �˻� �׸� �޺��ڽ�
		JComboBox searchComboBox = new JComboBox();
		searchComboBox.setFont(new Font("���Ļ�浸��", Font.PLAIN, 14));
		searchComboBox.setModel(new DefaultComboBoxModel(
				new String[] { "\uC804\uCCB4", "\uC81C\uBAA9", "\uC800\uC790", "\uCD9C\uD310\uC0AC" }));
		searchComboBox.setBackground(Color.WHITE);
		searchComboBox.setBounds(0, 0, 129, 38);
		panel.add(searchComboBox);

		// �˻� �Է� ���� �ؽ�Ʈ�ʵ�
		searchTextField = new JTextField();
		searchTextField.setBounds(129, 0, 569, 38);
		panel.add(searchTextField);
		searchTextField.setColumns(10);

		// �˻� ��ư
		JButton searchButton = new JButton("�˻�");
		searchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// �˻� ��ư �������� �۵��Ǵ� ������
				try { // DB ����
					String search_how = "��ü"; // �˻� ������ �� search_how (����, ����..)
					ResultSet rs;
					switch (searchComboBox.getSelectedItem().toString()) {
					case "����": // �˻������� "����"�� ��
						search_how = "TITLE";
						break;
					case "����": // �˻������� "����"�� ��
						search_how = "AUTHOR";
						break;
					case "���ǻ�": // �˻������� "���ǻ�"�� ��
						search_how = "PUB";
						break;
					case "��ü": // �˻������� "��ü"�� ��
						search_how = "TITLE LIKE '" + searchTextField.getText() + "%' OR BOOK_AUTHOR LIKE '"
								+ searchTextField.getText() + "%' OR BOOK_PUB";
						break;
					}
					rs = dbConn.executeQuery("SELECT BOOK_TITLE, BOOK_AUTHOR, BOOK_PUB, BOOK_CATEGORY, BOOK_ISBN\r\n"
							+ "FROM BOOK\r\n" + "WHERE (BOOK_" + search_how + " LIKE '" + searchTextField.getText()
							+ "%')AND BOOK_PRE = TRUE;"); // DB���� �˻�â�� �Էµ� ������ å ���� �˻�

					if (rs != null) // �˻������ ������
						set_table(rs); // ���̺� �籸��
					else // ������
						System.out.println("�˻������ �����ϴ�.");
				} catch (SQLException e2) {
					System.out.println("SQL ���� ����");
				}
			}
		});
		searchButton.setFont(new Font("���ʷҵ���", Font.BOLD, 14));
		searchButton.setBounds(690, 0, 129, 38);
		panel.add(searchButton);

		// �˻� ��� �г�
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(169, 112, 672, 520);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		// �˻� ��� ��ũ�� �г�
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 672, 520);
		panel_1.add(scrollPane);

		// ���̺�
		table = new JTable();
		// ���̺��� Ŭ���ϸ� ȣ��Ǵ� �޼ҵ�
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow(); // ������ row
				int col = table.getSelectedColumn(); // ������ col
				String book_title = table.getModel().getValueAt(row, 0).toString(); // Ŭ���� ���� å ������ ����
				String book_ISBN = ""; // Ŭ���� å�� ISBN�� ������ ����
				try { // DB ����

					ResultSet rs = dbConn.executeQuery("SELECT BOOK_ISBN\r\n" + "FROM BOOK\r\n" + "WHERE BOOK_TITLE ='"
							+ book_title + "' AND BOOK_PRE = TRUE;"); // DB���� å �������� ISBN �˻�
					while (rs.next()) {
						book_ISBN = rs.getString("BOOK_ISBN"); // ISBN ����
					}
				} catch (SQLException e2) {
					System.out.println("SQL ���� ����");
				}
				if (manager) {
					EditableBookInfo manager_book_info = new EditableBookInfo(book_ISBN, user_phone, manager);
					// å����â���� â�� ������ ȣ��Ǵ� �޼ҵ�
					manager_book_info.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							e.getWindow().dispose();
							try { // DB ����
								ResultSet rs = dbConn.executeQuery(
										"SELECT BOOK_TITLE, BOOK_AUTHOR, BOOK_PUB, BOOK_CATEGORY, BOOK_ISBN FROM BOOK WHERE BOOK_PRE = TRUE;");
								set_table(rs); // �����ڸ� ������ ȸ������ ������ ���̺��� ����
							} catch (SQLException e1) {
								System.out.println("ȸ���˻�â �ʱ� ���̺� ������ SQL ���� ����");
							}
						}
					});

					manager_book_info.setLocationRelativeTo(null); // ȭ���߾ӿ� ���
					manager_book_info.setVisible(true); // å ����â ���
				} else {
					BookInfo bookinfo = new BookInfo(book_ISBN, user_phone); // å ����â ��ü ���� (�Ű����� : Ŭ���� å�� ISBN)
					// å����â���� â�� ������ ȣ��Ǵ� �޼ҵ�
					bookinfo.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							e.getWindow().dispose();
						}
					});

					bookinfo.setLocationRelativeTo(null); // ȭ���߾ӿ� ���
					bookinfo.setVisible(true); // å ����â ���
				}
			}
		});

		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);
		table.setFont(new Font("���Ļ�浸��", Font.PLAIN, 14));

		String[] columns = { "����", "����", "���ǻ�", "ī�װ�", "���⿩�� (Y/N)" }; // ���̺��� ����
		String[][] data;
		DefaultTableModel model = new DefaultTableModel(null, columns);
		table.setModel(model); // ���̺� ����

		try { // DB ����
			//db�� �ִ� å ���� �˻�
			ResultSet rs = dbConn.executeQuery(
					"SELECT BOOK_TITLE, BOOK_AUTHOR, BOOK_PUB, BOOK_CATEGORY, BOOK_ISBN FROM BOOK WHERE BOOK_PRE = TRUE;");
			set_table(rs);
		} catch (SQLException e) {
			System.out.println("SQL ���� ����");
		}
		// ���� ����
		table.getColumnModel().getColumn(0).setPreferredWidth(110);
		table.getColumnModel().getColumn(1).setPreferredWidth(101);
		table.getColumnModel().getColumn(2).setPreferredWidth(106);
		table.getColumnModel().getColumn(3).setPreferredWidth(115);
		table.getColumnModel().getColumn(4).setPreferredWidth(115);

		// �˻� ���λ��� �г�
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.text);
		panel_2.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		panel_2.setBounds(22, 112, 135, 520);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		// �˻� ī�װ� ��
		JLabel categoryLabel = new JLabel("\uCE74\uD14C\uACE0\uB9AC");
		categoryLabel.setBounds(12, 10, 70, 20);
		categoryLabel.setFont(new Font("���Ļ�浸��", Font.PLAIN, 15));
		panel_2.add(categoryLabel);

		// ī�װ� üũ�ڽ�
		JCheckBox oneCheckBox = new JCheckBox("\uD504\uB85C\uADF8\uB798\uBC0D");
		oneCheckBox.setBackground(Color.WHITE);
		oneCheckBox.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		oneCheckBox.setBounds(8, 36, 107, 23);
		panel_2.add(oneCheckBox);

		// ī�װ� üũ�ڽ�
		JCheckBox twoCheckBox = new JCheckBox("\uACFC\uD559");
		twoCheckBox.setBackground(Color.WHITE);
		twoCheckBox.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		twoCheckBox.setBounds(8, 56, 107, 23);
		panel_2.add(twoCheckBox);

		// ī�װ� üũ�ڽ�
		JCheckBox threeCheckBox = new JCheckBox("\uBB38\uD559");
		threeCheckBox.setBackground(Color.WHITE);
		threeCheckBox.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		threeCheckBox.setBounds(8, 81, 107, 23);
		panel_2.add(threeCheckBox);

		// ī�װ� üũ�ڽ�
		JCheckBox fourCheckBox = new JCheckBox("\uC778\uBB38 / \uC0AC\uD68C");
		fourCheckBox.setBackground(Color.WHITE);
		fourCheckBox.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		fourCheckBox.setBounds(8, 106, 107, 23);
		panel_2.add(fourCheckBox);

		// ī�װ� üũ�ڽ�
		JCheckBox fiveCheckBox = new JCheckBox("\uBE44\uC988\uB2C8\uC2A4\uC640 \uACBD\uC81C");
		fiveCheckBox.setBackground(Color.WHITE);
		fiveCheckBox.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		fiveCheckBox.setBounds(8, 131, 107, 23);
		panel_2.add(fiveCheckBox);

		// ī�װ� üũ�ڽ�
		JCheckBox sixCheckBox = new JCheckBox("\uB9CC\uD654");
		sixCheckBox.setBackground(Color.WHITE);
		sixCheckBox.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		sixCheckBox.setBounds(8, 156, 107, 23);
		panel_2.add(sixCheckBox);

		// ī�װ� üũ�ڽ�
		JCheckBox sevenCheckBox = new JCheckBox("\uC5B4\uB9B0\uC774 / \uCCAD\uC18C\uB144");
		sevenCheckBox.setBackground(Color.WHITE);
		sevenCheckBox.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		sevenCheckBox.setBounds(8, 181, 107, 23);
		panel_2.add(sevenCheckBox);

		// ī�װ� üũ�ڽ�
		JCheckBox eightCheckBox = new JCheckBox("\uC218\uD5D8\uC11C / \uC790\uACA9\uC99D");
		eightCheckBox.setBackground(Color.WHITE);
		eightCheckBox.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		eightCheckBox.setBounds(8, 204, 107, 23);
		panel_2.add(eightCheckBox);

		// ī�װ� üũ�ڽ�
		JCheckBox nineCheckBox = new JCheckBox("\uC608\uC220 ");
		nineCheckBox.setBackground(Color.WHITE);
		nineCheckBox.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		nineCheckBox.setBounds(8, 228, 107, 23);
		panel_2.add(nineCheckBox);

		// �з� ��
		JLabel sortLabel = new JLabel("\uC815\uB82C");
		sortLabel.setFont(new Font("���Ļ�浸��", Font.PLAIN, 15));
		sortLabel.setBounds(12, 267, 70, 20);
		panel_2.add(sortLabel);

		// �ֽż� ������ư
		JRadioButton recentRadioButton = new JRadioButton("\uCD5C\uC2E0\uC21C");
		recentRadioButton.setBackground(Color.WHITE);
		buttonGroup_1.add(recentRadioButton);
		recentRadioButton.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		recentRadioButton.setBounds(12, 293, 113, 23);
		panel_2.add(recentRadioButton);

		// �α�� ������ư
		JRadioButton popularityRadioButton = new JRadioButton("\uC778\uAE30\uC21C");
		popularityRadioButton.setBackground(Color.WHITE);
		buttonGroup_1.add(popularityRadioButton);
		popularityRadioButton.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		popularityRadioButton.setBounds(12, 318, 113, 23);
		panel_2.add(popularityRadioButton);

		// ������ ���� ��ư
		JRadioButton gradeRadioButton = new JRadioButton("\uD3C9\uC810\uC21C");
		gradeRadioButton.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		buttonGroup_1.add(gradeRadioButton);
		gradeRadioButton.setBackground(Color.WHITE);
		gradeRadioButton.setBounds(12, 343, 113, 23);
		panel_2.add(gradeRadioButton);

		// ���⿩�� ��
		JLabel canBorrowLabel = new JLabel("\uB300\uC5EC\uC5EC\uBD80");
		canBorrowLabel.setFont(new Font("���Ļ�浸��", Font.PLAIN, 15));
		canBorrowLabel.setBounds(12, 378, 70, 20);
		panel_2.add(canBorrowLabel);

		// ���Ⱑ�� ������ư
		JRadioButton canborrowRadioButton = new JRadioButton("\uB300\uCD9C\uAC00\uB2A5");
		canborrowRadioButton.setBackground(Color.WHITE);
		buttonGroup_2.add(canborrowRadioButton);
		canborrowRadioButton.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		canborrowRadioButton.setBounds(12, 404, 113, 23);
		panel_2.add(canborrowRadioButton);

		// ������ ������ư
		JRadioButton borrowingNewRadioButton = new JRadioButton("\uB300\uCD9C\uC911");
		borrowingNewRadioButton.setBackground(Color.WHITE);
		buttonGroup_2.add(borrowingNewRadioButton);
		borrowingNewRadioButton.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		borrowingNewRadioButton.setBounds(12, 429, 113, 23);
		panel_2.add(borrowingNewRadioButton);

	}

	// ResultSet�� �޾� ���̺� �籸���ϴ� �Լ�
	public void set_table(ResultSet rs) throws SQLException {
		try {

			int row = 0;
			if (rs.last()) { // Ŀ���� ���������� �̵�
				row = rs.getRow(); // row�� ���� row�� �ε����� ����(�� row�� ������ ����)
				rs.beforeFirst(); // �ٽ� ������ �̵���Ŵ
			}

			String[][] data = new String[row][5]; // ���̺� ���� �����͸� ������ �迭
			int i = 0;
			// ���� �����ͷ� ���̺� ����
			while (rs.next()) {
				data[i][0] = rs.getString("BOOK_TITLE");
				data[i][1] = rs.getString("BOOK_AUTHOR");
				data[i][2] = rs.getString("BOOK_PUB");
				data[i][3] = rs.getString("BOOK_CATEGORY");
				String book_ISBN = rs.getString("BOOK_ISBN");
				//�ش� å�� �������� �������� �˻�
				ResultSet rs_rent = dbConn.executeQuery(
						"SELECT BOOK_ISBN FROM RENT WHERE BOOK_ISBN='" + book_ISBN + "' AND RENT_RETURN_YN IS NULL;"); 
				if (rs_rent.next()) {
					if (book_ISBN.equals(rs_rent.getString("BOOK_ISBN"))) { // �˻��ؼ� ���� ISBN�� �ش� å�� ISBN�� ������ (�������� �����̸�)
						data[i][4] = "������";
					}
				} else {
					data[i][4] = "���Ⱑ��";
				}
				i++;
			}
			String[] columns = { "����", "����", "���ǻ�", "ī�װ�", "���⿩��" }; // ���̺��� ����
			table.setModel(new DefaultTableModel(data, columns)); // ���̵� �ٽ� ����
		} catch (SQLException e) {
			System.out.println("SQL ���� ����");
		}
	}
}
