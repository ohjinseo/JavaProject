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
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class SearchBook extends JFrame {
	dbConnector dbConn = new dbConnector();
	public String user_phone = "";
	public Boolean manager = false;
	private JPanel contentPane;
	private JTextField searchTextField;
	private JTable table;
	JComboBox searchComboBox;
	JCheckBox[] jcb = new JCheckBox[10];
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();
	private int t = 0;
	private Main mainFrame;
	private UserInfo userInfoFrame;
	private int checkNum = 0;
	
	//���͸� ����
	HashMap<Object, RowFilter<Object, Object>> borrowFilter = new HashMap<>();
	HashMap<Object, RowFilter<Object, Object>> categoryFilter = new HashMap<>();	//RowFilter ������Ʈ �ּҰ� �������� �ٲ��� �ؽø����� Ű������ ���� ó��
	TableRowSorter<TableModel> trs;
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
		searchComboBox = new JComboBox();
		searchComboBox.setFont(new Font("���Ļ�浸��", Font.PLAIN, 14));
		searchComboBox.setModel(new DefaultComboBoxModel(
				new String[] { "\uC804\uCCB4", "\uC81C\uBAA9", "\uC800\uC790", "\uCD9C\uD310\uC0AC" }));
		searchComboBox.setBackground(Color.WHITE);
		searchComboBox.setBounds(0, 0, 129, 38);
		panel.add(searchComboBox);

		// �˻� �Է� ���� �ؽ�Ʈ�ʵ�
		searchTextField = new JTextField();
		searchTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//�˻�â �Է� �� ���� �Է½� �۵��Ǵ� ������
				search_event();
			}
		});
		searchTextField.setBounds(129, 0, 569, 38);
		panel.add(searchTextField);
		searchTextField.setColumns(10);

		// �˻� ��ư
		JButton searchButton = new JButton("�˻�");
		searchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// �˻� ��ư �������� �۵��Ǵ� ������
			
				search_event();
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
				int row_index = table.getSelectedRow(); // ������ row 
				int col_index = table.getSelectedColumn(); // ������ col 
				//���� �Ǿ� �������� ���� �����ʹ� ���ĵǾ� ���� ����
				int row = table.convertRowIndexToModel(row_index);		// ���� �𵨿� ����Ǿ� �ִ� �ε��� ����
				int col = table.convertColumnIndexToModel(col_index);	// ���� �𵨿� ����Ǿ� �ִ� �ε��� ����
				String book_title = table.getModel().getValueAt(row, 0).toString(); // Ŭ���� ���� å ������ ����
				String book_ISBN = ""; // Ŭ���� å�� ISBN�� ������ ����
				try { // DB ����

					ResultSet rs = dbConn.executeQuery("SELECT BOOK_ISBN\r\n" + "FROM BOOK\r\n" + "WHERE BOOK_TITLE ='"
							+ book_title + "' AND BOOK_PRE = TRUE;"); // DB���� å �������� ISBN �˻�
					while (rs.next()) {
						book_ISBN = rs.getString("BOOK_ISBN"); // ISBN ����
					}
				} catch (SQLException e2) {
					e2.printStackTrace();
					System.out.println("SQL ���� ����");
				}
				if (manager) {
					EditableBookInfo manager_book_info = new EditableBookInfo(book_ISBN, user_phone, manager);
					// å����â���� â�� ������ ȣ��Ǵ� �޼ҵ�
					manager_book_info.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							try { // DB ����
								ResultSet rs = dbConn.executeQuery(
										"SELECT BOOK_TITLE, BOOK_AUTHOR, BOOK_PUB, BOOK_CATEGORY, BOOK_ISBN, BOOK_GRADE, BOOK_RENT_COUNT, BOOK_APPEND_DATE FROM BOOK WHERE BOOK_PRE = TRUE;");
								set_table(rs);
								setTrs();
							} catch (SQLException e1) {
								e1.printStackTrace();
								System.out.println("���� �˻�â ���̺� ������ SQL ���� ����");
							}
							e.getWindow().dispose();
						}
					});

					manager_book_info.setLocationRelativeTo(null); // ȭ���߾ӿ� ���
					manager_book_info.setResizable(false); // â ũ�� ����
					manager_book_info.setVisible(true); // å ����â ���
				} else {
					BookInfo bookinfo = new BookInfo(book_ISBN, user_phone); // å ����â ��ü ���� (�Ű����� : Ŭ���� å�� ISBN)
					// å����â���� â�� ������ ȣ��Ǵ� �޼ҵ�
					bookinfo.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							try { // DB ����
								ResultSet rs = dbConn.executeQuery(
										"SELECT BOOK_TITLE, BOOK_AUTHOR, BOOK_PUB, BOOK_CATEGORY, BOOK_ISBN, BOOK_GRADE, BOOK_RENT_COUNT, BOOK_APPEND_DATE FROM BOOK WHERE BOOK_PRE = TRUE;");
								set_table(rs);
								setTrs();
							} catch (SQLException e1) {
								e1.printStackTrace();
								System.out.println("���� �˻�â ���̺� ������ SQL ���� ����");
							}
							e.getWindow().dispose();
						}
					});
					
					bookinfo.setLocationRelativeTo(null); // ȭ���߾ӿ� ���
					bookinfo.setResizable(false); // â ũ�� ����
					bookinfo.setVisible(true); // å ����â ���
				}
			}
		});

		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);
		table.setFont(new Font("���Ļ�浸��", Font.PLAIN, 14));

		
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column", "\uC81C\uBAA9", "\uC800\uC790", "\uCD9C\uD310\uC0AC", "\uCE74\uD14C\uACE0\uB9AC", "\uB300\uCD9C\uC5EC\uBD80", "\uD3C9\uC810", "\uB300\uC5EC\uD69F\uC218", "\uB3C4\uC11C\uCD94\uAC00\uB0A0\uC9DC"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(110);
		table.getColumnModel().getColumn(2).setPreferredWidth(101);
		table.getColumnModel().getColumn(3).setPreferredWidth(106);
		table.getColumnModel().getColumn(4).setPreferredWidth(115);
		table.getColumnModel().getColumn(5).setPreferredWidth(115);
		table.getColumnModel().getColumn(6).setPreferredWidth(115);
		table.getColumnModel().getColumn(7).setPreferredWidth(115);
		table.getColumnModel().getColumn(8).setPreferredWidth(115);
		
		try { // DB ����
			//db�� �ִ� å ���� �˻�
			ResultSet rs = dbConn.executeQuery(
					"SELECT BOOK_TITLE, BOOK_AUTHOR, BOOK_PUB, BOOK_CATEGORY, BOOK_ISBN, BOOK_GRADE, BOOK_RENT_COUNT, BOOK_APPEND_DATE FROM BOOK WHERE BOOK_PRE = TRUE;");
			set_table(rs);
			setTrs();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL ���� ����");
		}

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
		
		//ī�װ� ���͸� 
		
		// ī�װ� üũ�ڽ�
		
		jcb[0] = new JCheckBox("�̺з�");
		jcb[0].setBackground(Color.WHITE);
		jcb[0].setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		jcb[0].setBounds(8, 36, 107, 23);
		panel_2.add(jcb[0]);
		

		// ī�װ� üũ�ڽ�
		jcb[1] = new JCheckBox("�������");
		jcb[1].setBackground(Color.WHITE);
		jcb[1].setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		jcb[1].setBounds(8, 56, 107, 23);
		panel_2.add(jcb[1]);

		// ī�װ� üũ�ڽ�
		jcb[2] = new JCheckBox("��");
		jcb[2].setBackground(Color.WHITE);
		jcb[2].setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		jcb[2].setBounds(8, 81, 107, 23);
		panel_2.add(jcb[2]);

		// ī�װ� üũ�ڽ�
		jcb[3] = new JCheckBox("����");
		jcb[3].setBackground(Color.WHITE);
		jcb[3].setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		jcb[3].setBounds(8, 106, 107, 23);
		panel_2.add(jcb[3]);

		// ī�װ� üũ�ڽ�
		jcb[4] = new JCheckBox("�Ҽ�");
		jcb[4].setBackground(Color.WHITE);
		jcb[4].setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		jcb[4].setBounds(8, 131, 107, 23);
		panel_2.add(jcb[4]);

		// ī�װ� üũ�ڽ�
		jcb[5]= new JCheckBox("\uB9CC\uD654");
		jcb[5].setBackground(Color.WHITE);
		jcb[5].setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		jcb[5].setBounds(8, 156, 107, 23);
		panel_2.add(jcb[5]);

		// ī�װ� üũ�ڽ�
		jcb[6] = new JCheckBox("\uC5B4\uB9B0\uC774 / \uCCAD\uC18C\uB144");
		jcb[6].setBackground(Color.WHITE);
		jcb[6].setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		jcb[6].setBounds(8, 181, 107, 23);
		panel_2.add(jcb[6]);

		// ī�װ� üũ�ڽ�
		jcb[7] = new JCheckBox("\uC218\uD5D8\uC11C / \uC790\uACA9\uC99D");
		jcb[7].setBackground(Color.WHITE);
		jcb[7].setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		jcb[7].setBounds(8, 204, 107, 23);
		panel_2.add(jcb[7]);

		
		
		for( t = 0; t < 8; t++) {
			jcb[t].addItemListener(new ItemListener() {
				private int myIndex;	//��ü �ε����� �����ϱ� ���� ��ü ���� ����
				{
					this.myIndex = t;
				}
				public void itemStateChanged(ItemEvent e) {	//üũ �ڽ� ���°� ���Ѵٸ�
					if(e.getStateChange() == ItemEvent.SELECTED) {
						categoryFilter.put(jcb[myIndex].getText(), RowFilter.regexFilter(jcb[myIndex].getText(), 3));	//���̺� 3�࿡ �ִ� ī�װ����� �����׸� �߰����� �ؽøʿ� ����
						checkNum++;
					}else {
						checkNum--;
						if(categoryFilter.containsKey(jcb[myIndex].getText()))	//üũ�� �Ǿ����� �ʴٸ� �ؽøʿ� �ش� ī�װ� Ű �� ���θ� Ȯ���Ͽ� ������ ����
							categoryFilter.remove(jcb[myIndex].getText());
						
					}
					combineOrAndFilters();
					if(checkNum == 0) {
						trs.setRowFilter(null);	//���� üũ ������ 0�̶�� ���� ����
						combineOrAndFilters();
					}
					
				}
				
			});
		}

		// �з� ��
		JLabel sortLabel = new JLabel("\uC815\uB82C");
		sortLabel.setFont(new Font("���Ļ�浸��", Font.PLAIN, 15));
		sortLabel.setBounds(12, 240, 70, 20);
		panel_2.add(sortLabel);
		
		

		List<RowSorter.SortKey> sortKeys = new ArrayList<>();
		
		//����� ������ư
		JRadioButton headerRadioButton = new JRadioButton("\uC81C\uBAA9\uC21C");
		
		buttonGroup_1.add(headerRadioButton);
		headerRadioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
				}else {
					sortKeys.clear();
					
				}
				trs.setSortKeys(sortKeys);
			}
		});
		headerRadioButton.setSelected(false);
		headerRadioButton.setSelected(true);
		
		headerRadioButton.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		headerRadioButton.setBackground(Color.WHITE);
		headerRadioButton.setBounds(12, 266, 113, 23);
		panel_2.add(headerRadioButton);
		
		// �ֽż� ������ư
		JRadioButton recentRadioButton = new JRadioButton("\uCD5C\uC2E0\uC21C");
		recentRadioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					sortKeys.add(new RowSorter.SortKey(7, SortOrder.DESCENDING));
				}else {
					sortKeys.clear();
					
				}
				trs.setSortKeys(sortKeys);
			}
		});
		
		recentRadioButton.setBackground(Color.WHITE);
		buttonGroup_1.add(recentRadioButton);
		recentRadioButton.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		recentRadioButton.setBounds(12, 291, 113, 23);
		panel_2.add(recentRadioButton);

		// �α�� ������ư
		JRadioButton popularityRadioButton = new JRadioButton("\uC778\uAE30\uC21C");
		popularityRadioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					sortKeys.add(new RowSorter.SortKey(6, SortOrder.DESCENDING));
				}else {
					sortKeys.clear();
					
				}
				trs.setSortKeys(sortKeys);
			}
		});
		popularityRadioButton.setBackground(Color.WHITE);
		buttonGroup_1.add(popularityRadioButton);
		popularityRadioButton.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		popularityRadioButton.setBounds(12, 316, 113, 23);
		panel_2.add(popularityRadioButton);

		// ������ ���� ��ư
		JRadioButton gradeRadioButton = new JRadioButton("\uD3C9\uC810\uC21C");
		gradeRadioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					sortKeys.add(new RowSorter.SortKey(5, SortOrder.DESCENDING));
				}else {
					sortKeys.clear();
					
				}
				trs.setSortKeys(sortKeys);
			}
		});
		gradeRadioButton.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		buttonGroup_1.add(gradeRadioButton);
		gradeRadioButton.setBackground(Color.WHITE);
		gradeRadioButton.setBounds(12, 341, 113, 23);
		panel_2.add(gradeRadioButton);

		// ���⿩�� ��
		JLabel canBorrowLabel = new JLabel("\uB300\uC5EC\uC5EC\uBD80");
		canBorrowLabel.setFont(new Font("���Ļ�浸��", Font.PLAIN, 15));
		canBorrowLabel.setBounds(12, 378, 70, 20);
		panel_2.add(canBorrowLabel);
		
		// ���Ⱑ�� ������ư
		JRadioButton canborrowRadioButton = new JRadioButton("\uB300\uCD9C\uAC00\uB2A5");
		
		//���� ���� ���͸� �̺�Ʈ
		canborrowRadioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					borrowFilter.put(canborrowRadioButton.getText(), RowFilter.regexFilter(canborrowRadioButton.getText(), 4));	//���̺� 5�࿡ �ִ� ���⿩�θ��� �����׸� �߰����� �ؽøʿ� ����
				}else {
					
					if(borrowFilter.containsKey(canborrowRadioButton.getText()))	//üũ�� �Ǿ����� �ʴٸ� �ؽøʿ� �ش� ī�װ� Ű �� ���θ� Ȯ���Ͽ� ������ ����
						borrowFilter.remove(canborrowRadioButton.getText());
				}
				
				combineOrAndFilters();
			}
		});
		canborrowRadioButton.setBackground(Color.WHITE);
		buttonGroup_2.add(canborrowRadioButton);
		canborrowRadioButton.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		canborrowRadioButton.setBounds(12, 428, 113, 23);
		panel_2.add(canborrowRadioButton);

		// ������ ������ư
		JRadioButton borrowingNewRadioButton = new JRadioButton("\uB300\uCD9C\uC911");
		borrowingNewRadioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					borrowFilter.put(borrowingNewRadioButton.getText(), RowFilter.regexFilter(borrowingNewRadioButton.getText(), 4));	//���̺� 5�࿡ �ִ� ���⿩�θ��� �����׸� �߰����� �ؽøʿ� ����
				}else {
					
					if(borrowFilter.containsKey(borrowingNewRadioButton.getText()))	//üũ�� �Ǿ����� �ʴٸ� �ؽøʿ� �ش� ī�װ� Ű �� ���θ� Ȯ���Ͽ� ������ ����
						borrowFilter.remove(borrowingNewRadioButton.getText());
				}
				
				combineOrAndFilters();
			}
		});
		borrowingNewRadioButton.setBackground(Color.WHITE);
		buttonGroup_2.add(borrowingNewRadioButton);
		borrowingNewRadioButton.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		borrowingNewRadioButton.setBounds(12, 453, 113, 23);
		panel_2.add(borrowingNewRadioButton);
		
		//��ü���� ������ư
		JRadioButton AllBorrowRadioButton = new JRadioButton("\uC804\uCCB4\uBCF4\uAE30");
		AllBorrowRadioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					borrowFilter.clear();
				}
				
				combineOrAndFilters();
			}
		});
		buttonGroup_2.add(AllBorrowRadioButton);
		AllBorrowRadioButton.setSelected(true);
		AllBorrowRadioButton.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		AllBorrowRadioButton.setBackground(Color.WHITE);
		AllBorrowRadioButton.setBounds(12, 403, 113, 23);
		panel_2.add(AllBorrowRadioButton);
	}
	
	//AndFilter�� OrFilter ���� �Լ�
	public void combineOrAndFilters() {
		RowFilter<Object, Object> or;
		RowFilter<Object, Object> and;
		ArrayList<RowFilter<Object, Object>> finalFilters = new ArrayList<RowFilter<Object, Object>>();
		
		if (categoryFilter.size() > 0) {
			or = RowFilter.orFilter(categoryFilter.values());	//ī�װ� ���ʹ� or���ͷ� ����
			finalFilters.add(or);
		}
		if (borrowFilter.size() > 0) {
			and = RowFilter.andFilter(borrowFilter.values());	//���� ���ʹ� and���ͷ� ����
			finalFilters.add(and);
		}
		trs.setRowFilter(RowFilter.andFilter(finalFilters)); //ī�װ��� ���� ���ʹ� and����
	}
	
	//���̺� ���͸��� ���� �ʱ�ȭ �Լ�
	public void setTrs() {
		trs = new TableRowSorter<>(table.getModel()); 	
		table.setRowSorter(trs);
		trs.setComparator(5, new Comparator<Object>(){	//JTable�� �⺻������ ���ڿ��� �����ϱ� ������ ���ڸ��� �̻���� �����̾ȵǼ� ���� ���ϴ� �Լ��� �ۼ�
			        public int compare(Object o1, Object o2){
			        	Integer a = Integer.parseInt((String) o1);
			        	Integer b = Integer.parseInt((String) o2);
			            return a.compareTo(b);
			        }
			    });
				
				trs.setComparator(6, new Comparator<Object>(){	//JTable�� �⺻������ ���ڿ��� �����ϱ� ������ ���ڸ��� �̻���� �����̾ȵǼ� ���� ���ϴ� �Լ��� �ۼ�
			        public int compare(Object o1, Object o2){
			        	Integer a = Integer.parseInt((String) o1);
			        	Integer b = Integer.parseInt((String) o2);
			            return a.compareTo(b);
			        }
			    });
	}
	
	//�˻� ��� �����ϴ� �̺�Ʈ �Լ�
	public void search_event() {
		
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
			rs = dbConn.executeQuery("SELECT BOOK_TITLE, BOOK_AUTHOR, BOOK_PUB, BOOK_CATEGORY, BOOK_ISBN, BOOK_GRADE, BOOK_RENT_COUNT, BOOK_APPEND_DATE\r\n"
					+ "FROM BOOK\r\n" + "WHERE (BOOK_" + search_how + " LIKE '" + searchTextField.getText()
					+ "%')AND BOOK_PRE = TRUE;"); // DB���� �˻�â�� �Էµ� ������ å ���� �˻�
			if (rs != null) // �˻������ ������
			{
				//���߿� üũ�ڽ� ���󺹱���Ű�� �ڵ嵵 ���� �ۼ�  
				System.out.println("�˻��Ϸ�");
				set_table(rs); // ���̺� �籸��
				setTrs();	//���̺��� �籸���� �����Ƿ� ���͸� �ʱ�ȭ�� ��������
			}
			else // ������
				System.out.println("�˻������ �����ϴ�.");
		} catch (SQLException e2) {
			e2.printStackTrace();
			System.out.println("SQL ���� ����");
		}
	}

	// ResultSet�� �޾� ���̺� �籸���ϴ� �Լ�
	public void set_table(ResultSet rs) throws SQLException {
		try {

			int row = 0;
			if (rs.last()) { // Ŀ���� ���������� �̵�
				row = rs.getRow(); // row�� ���� row�� �ε����� ����(�� row�� ������ ����)
				rs.beforeFirst(); // �ٽ� ������ �̵���Ŵ
			}

			String[][] data = new String[row][8]; // ���̺� ���� �����͸� ������ �迭
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
				
				
				//�ش� ������ ���� ���� ��������
				
				int bookReviewGrade = Integer.parseInt(rs.getString("BOOK_GRADE"));
				int bookReviewCnt = 0;
				ResultSet rs1 = dbConn.executeQuery(
						"SELECT COUNT(*) FROM REVIEW WHERE BOOK_ISBN = '" + book_ISBN + "';"
						);
				if(rs1.next()) {
					bookReviewCnt = rs1.getInt(1);
				}
				
				//å ���� �ű��
				int bookScore = 0;
				if(bookReviewCnt != 0) {
					bookScore = bookReviewGrade / bookReviewCnt;
				}
				
				data[i][5] = Integer.toString(bookScore);
				
				data[i][6] = rs.getString("BOOK_RENT_COUNT");
				data[i][7] = rs.getString("BOOK_APPEND_DATE");
				
				i++;
			}
			String[] columns = { "����", "����", "���ǻ�", "ī�װ�", "���⿩��", "����", "�뿩Ƚ��", "�����߰���¥" }; // ���̺��� ����
			table.setModel(new DefaultTableModel(data, columns)); // ���̵� �ٽ� ����
			
			table.removeColumn(table.getColumnModel().getColumn(5));	//����, �뿩Ƚ��, �����߰���¥ �÷����� ����
			table.removeColumn(table.getColumnModel().getColumn(5));
			table.removeColumn(table.getColumnModel().getColumn(5));
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL ���� ����");
		}
	}
}
