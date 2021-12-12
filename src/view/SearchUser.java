package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;

public class SearchUser extends JFrame {
	dbConnector dbConn = new dbConnector();
	String user_phone = "";
	Boolean manager = false;
	private JPanel contentPane;
	private JTextField searchTextField;
	private JTable table;
	private Main mainFrame;
	BookInfo bookInfoFrame;
	SearchUser searchUserFrame;
	SearchBook searchBookFrame;
	UserInfo userInfoFrame;

	public SearchUser(String user_phone, Boolean manager) {
		this.user_phone = user_phone;
		this.manager = manager;

		setTitle("\uB3C4\uC11C \uAD00\uB9AC \uD504\uB85C\uADF8\uB7A8 - \uD68C\uC6D0\uAC80\uC0C9");
		setBounds(100, 100, 881, 706);
		contentPane = new JPanel(); // ���� ������
		contentPane.setBackground(SystemColor.menu);
		setContentPane(contentPane);
		contentPane.setLayout(null);

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
		findBookMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				searchBookFrame = new SearchBook(user_phone, manager);
				searchBookFrame.setVisible(true);
				setVisible(false);

			}
		});

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

		// ȸ���˻� �޴�
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

		// ���� �߰� �޴�
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
		// �˻� �г�
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 245, 245));
		panel.setBounds(22, 57, 819, 38);
		contentPane.add(panel);
		panel.setLayout(null);

		// �˻� ��� �޺��ڽ�
		JComboBox searchComboBox = new JComboBox();
		searchComboBox.setFont(new Font("���Ļ�浸��", Font.PLAIN, 15));
		searchComboBox.setModel(new DefaultComboBoxModel(
				new String[] { "\uC804\uCCB4", "\uC774\uB984", "\uC804\uD654\uBC88\uD638", "\uC774\uBA54\uC77C" }));
		searchComboBox.setBackground(Color.WHITE);
		searchComboBox.setBounds(0, 0, 129, 38);
		panel.add(searchComboBox);

		// �˻� �Է� �ؽ�Ʈ�ʵ�
		searchTextField = new JTextField();
		searchTextField.setBounds(129, 0, 569, 38);
		panel.add(searchTextField);
		searchTextField.setColumns(10);

		// �˻� ��ư
		JButton searchButton = new JButton("\uAC80\uC0C9");
		searchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		searchButton.setBackground(new Color(220, 220, 220));
		searchButton.setFont(new Font("���Ļ�浸��", Font.BOLD, 14));
		searchButton.setBounds(690, 0, 129, 38);
		panel.add(searchButton);

		// ���� ����Ʈ �г�
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(128, 128, 128), 2));
		panel_1.setBounds(22, 112, 819, 520);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		// ���� ����Ʈ ��ũ�� �г�
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 819, 520);
		panel_1.add(scrollPane);

		// ���� ����Ʈ ���̺�
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UserInfo userinfo = new UserInfo(user_phone, manager);
				userinfo.setVisible(true);
			}
		});

		// ���̺�
		table = new JTable();
		// ���̺��� Ŭ���ϸ� ȣ��Ǵ� �޼ҵ�
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow(); // ������ row
				int col = table.getSelectedColumn(); // ������ col
				String clicked_user_phone = table.getModel().getValueAt(row, 2).toString(); // Ŭ���� ���� ȸ�� ��ȭ��ȣ�� ����
				// ���� ����â ��ü ���� (�Ű����� : Ŭ���� ������ phone)
				EditableUserInfo manager_user_info = new EditableUserInfo(clicked_user_phone);

				// ��������â���� â�� ������ ȣ��Ǵ� �޼ҵ�
				manager_user_info.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						e.getWindow().dispose();
						try { // DB ����
							ResultSet rs = dbConn.executeQuery(
									"SELECT USER_NAME, USER_MAIL, USER_PHONE, USER_BIRTH, USER_SUSPENSION, USER_OUT_DATE FROM USER WHERE USER_MANAGER =false;");
							set_table(rs); // �����ڸ� ������ ȸ������ ������ ���̺��� ����
						} catch (SQLException e1) {
							System.out.println("ȸ���˻�â �ʱ� ���̺� ������ SQL ���� ����");
						}
					}
				});

				manager_user_info.setLocationRelativeTo(null); // ȭ���߾ӿ� ���
				manager_user_info.setVisible(true); // ���� ����â ���
			}
		});

		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);
		table.setFont(new Font("���Ļ�浸��", Font.PLAIN, 14));

		String[] columns = { "�̸�", "�̸���", "��ȭ��ȣ", "�������", "��������", "Ż�𿩺�" }; // ���̺��� ����
		String[][] data;
		DefaultTableModel model = new DefaultTableModel(null, columns);
		table.setModel(model); // ���̺� ����

		try { // DB ����
			ResultSet rs = dbConn.executeQuery(
					"SELECT USER_NAME, USER_MAIL, USER_PHONE, USER_BIRTH, USER_SUSPENSION, USER_OUT_DATE FROM USER WHERE USER_MANAGER =false;");
			set_table(rs); // �����ڸ� ������ ȸ������ ������ ���̺��� ����
		} catch (SQLException e) {
			System.out.println("ȸ���˻�â �ʱ� ���̺� ������ SQL ���� ����");
		}
		// ���� ����
		table.getColumnModel().getColumn(0).setPreferredWidth(110);
		table.getColumnModel().getColumn(1).setPreferredWidth(101);
		table.getColumnModel().getColumn(2).setPreferredWidth(106);
		table.getColumnModel().getColumn(3).setPreferredWidth(115);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(115);
	}

	// ResultSet�� �޾� ���̺� �籸���ϴ� �Լ�
	public void set_table(ResultSet rs) throws SQLException {
		try {

			int row = 0;
			if (rs.last()) { // Ŀ���� ���������� �̵�
				row = rs.getRow(); // row�� ���� row�� �ε����� ����(�� row�� ������ ����)
				rs.beforeFirst(); // �ٽ� ������ �̵���Ŵ
			}

			String[][] data = new String[row][6]; // ���̺� ���� �����͸� ������ �迭
			int i = 0;
			// ���� �����ͷ� ���̺� ����
			while (rs.next()) {
				data[i][0] = rs.getString("USER_NAME"); // ȸ�� �̸�
				data[i][1] = rs.getString("USER_MAIL"); // ȸ�� ����
				data[i][2] = rs.getString("USER_PHONE"); // ȸ�� ��ȭ��ȣ
				data[i][3] = rs.getString("USER_BIRTH"); // ȸ�� ����
				// ȸ�� ��������
				if (rs.getString("USER_SUSPENSION")==null) {
					data[i][4] = "N";
				} else {
					data[i][4] = rs.getString("USER_SUSPENSION").substring(0,16);
				}

				// ȸ�� Ż�𿩺�
				if (rs.getString("USER_OUT_DATE")==null) {
					data[i][5] = "N";
				} else {
					data[i][5] = rs.getString("USER_OUT_DATE").substring(0,10);
				}
				i++;
			}
			String[] columns = { "�̸�", "�̸���", "��ȭ��ȣ", "�������", "��������", "Ż�𿩺�" }; // ���̺��� ����
			table.setModel(new DefaultTableModel(data, columns)); // ���̵� �ٽ� ����
		} catch (SQLException e) {
			System.out.println("ȸ���˻�â���� ���̺� ������ SQL ���� ����");
		}
	}

}
