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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import java.sql.SQLIntegrityConstraintViolationException;

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
	int ret = 1;	//���� ���� ���θ� �˷��ִ� ����
	JButton bookAddButton;
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
		setBounds(100, 100, 848, 622);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(12, 31, 181, 252);
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
					ret = book_img.showOpenDialog(null);	//���� ã�� â�� �����
					if(ret == 0) { //������ �����ߴٸ�
						
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
			}
		});
		bookImageFindButton.setFont(new Font("\uD55C\uCEF4\uC0B0\uB73B\uB3CB\uC6C0",
				bookImageFindButton.getFont().getStyle() | Font.BOLD, bookImageFindButton.getFont().getSize() + 3));
		bookImageFindButton.setBounds(12, 203, 157, 39);
		panel.add(bookImageFindButton);
		

		// å ���� �г�
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(205, 31, 612, 252);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		//å �̸� ��
		JLabel bookNameLabel = new JLabel("���� : ");
		bookNameLabel.setFont(new Font("���Ļ�浸��", Font.PLAIN, 16));
		bookNameLabel.setBounds(12, 10, 46, 46);
		panel_1.add(bookNameLabel);

		// å �̸� �ؽ�Ʈ�ʵ�
		bookNameTextField = new JTextField();
		bookNameTextField.setFont(new Font("���ʷҵ���", Font.BOLD, 22));
		bookNameTextField.setBounds(92, 20, 407, 26);
		panel_1.add(bookNameTextField);
		bookNameTextField.setColumns(10);
		bookNameTextField.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				    changed();
				  }
				  public void removeUpdate(DocumentEvent e) {
				    changed();
				  }
				  public void insertUpdate(DocumentEvent e) {
				    changed();
				  }

				  public void changed() {
				     if (bookISBNTextField.getText().equals("")){
				    	 bookAddButton.setEnabled(false);
				     }
				     else {
				    	 bookAddButton.setEnabled(true);
				    }

				  }
				});

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
		bookHeaderTextField_1.setBounds(92, 60, 157, 26);
		panel_1.add(bookHeaderTextField_1);
		bookHeaderTextField_1.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				    changed();
				  }
				  public void removeUpdate(DocumentEvent e) {
				    changed();
				  }
				  public void insertUpdate(DocumentEvent e) {
				    changed();
				  }

				  public void changed() {
				     if (bookISBNTextField.getText().equals("")){
				    	 bookAddButton.setEnabled(false);
				     }
				     else {
				    	 bookAddButton.setEnabled(true);
				    }

				  }
				});

		// å ���ǻ� ��
		JLabel bookHeaderLabel_2 = new JLabel("\uCD9C\uD310\uC0AC : ");
		bookHeaderLabel_2.setFont(new Font("���Ļ�浸��", Font.PLAIN, 16));
		bookHeaderLabel_2.setBounds(270, 60, 60, 26);
		panel_1.add(bookHeaderLabel_2);

		// å ���ǻ� �ؽ�Ʈ�ʵ�
		bookHeaderTextField_2 = new JTextField();
		bookHeaderTextField_2.setFont(new Font("���Ļ�浸��", Font.PLAIN, 16));
		bookHeaderTextField_2.setColumns(10);
		bookHeaderTextField_2.setBounds(342, 60, 157, 26);
		panel_1.add(bookHeaderTextField_2);
		bookHeaderTextField_2.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				    changed();
				  }
				  public void removeUpdate(DocumentEvent e) {
				    changed();
				  }
				  public void insertUpdate(DocumentEvent e) {
				    changed();
				  }

				  public void changed() {
				     if (bookISBNTextField.getText().equals("")){
				    	 bookAddButton.setEnabled(false);
				     }
				     else {
				    	 bookAddButton.setEnabled(true);
				    }

				  }
				});

		// å ���ø�ũ ��
		JLabel bookLinkLabel = new JLabel("\uAD00\uB828\uB9C1\uD06C : ");
		bookLinkLabel.setFont(new Font("���Ļ�浸��", Font.PLAIN, 16));
		bookLinkLabel.setBounds(12, 216, 83, 26);
		panel_1.add(bookLinkLabel);

		// å ���ø�ũ �ؽ�Ʈ�ʵ�
		bookLinkTextField = new JTextField();
		bookLinkTextField.setFont(new Font("���Ļ�浸��", Font.PLAIN, 16));
		bookLinkTextField.setColumns(10);
		bookLinkTextField.setBounds(92, 216, 407, 26);
		panel_1.add(bookLinkTextField);

		// å ���� ��
		JLabel bookPriceLabel = new JLabel("\uAC00\uACA9 : ");
		bookPriceLabel.setFont(new Font("���Ļ�浸��", Font.PLAIN, 16));
		bookPriceLabel.setBounds(12, 118, 46, 26);
		panel_1.add(bookPriceLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(91, 145, 225, 15);
		panel_1.add(lblNewLabel_1);
		// å ���� �ؽ�Ʈ�ʵ�
		bookPriceTextField = new JTextField();
		bookPriceTextField.setFont(new Font("���Ļ�浸��", Font.PLAIN, 16));
		bookPriceTextField.setColumns(10);
		bookPriceTextField.setBounds(92, 118, 407, 26);
		panel_1.add(bookPriceTextField);
		bookPriceTextField.addKeyListener(new KeyAdapter() {
	         public void keyPressed(KeyEvent ke) {
	             String value = bookPriceTextField.getText();
	             int l = value.length();
	             if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
	            	 bookPriceTextField.setEditable(true);
	            	 lblNewLabel_1.setText("");
	             } else {
	            	 bookPriceTextField.setEditable(false);
	            	 lblNewLabel_1.setText("������ ���ڸ� �����մϴ�.");
	             }
	          }
	       });
		
		bookPriceTextField.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				  
				    changed();
				  }
				  public void removeUpdate(DocumentEvent e) {
				    changed();
				  }
				  public void insertUpdate(DocumentEvent e) {
				    changed();
				  }
				  
				  public void changed() {
				     if (bookISBNTextField.getText().equals("")){
				    	 bookAddButton.setEnabled(false);
				     }
				     else {
				    	 bookAddButton.setEnabled(true);
				    }
				   
				     if (bookPriceTextField.getText().length() > 10) {
				    	 bookAddButton.setEnabled(false);
				    	 lblNewLabel_1.setText("������ 10�ڸ� ���ϸ� �����մϴ�.");
				     }else {
				    	 bookAddButton.setEnabled(true);
				    	 lblNewLabel_1.setText("");
				     }

				  }
				});

		// å ISBN ��
		JLabel bookISBNLabel = new JLabel("ISBN : ");
		bookISBNLabel.setFont(new Font("���Ļ�浸��", Font.PLAIN, 16));
		bookISBNLabel.setBounds(12, 170, 58, 26);
		panel_1.add(bookISBNLabel);
		

		// å ISBN �ؽ�Ʈ�ʵ�
		bookISBNTextField = new JTextField();
		bookISBNTextField.setFont(new Font("���Ļ�浸��", Font.PLAIN, 16));
		bookISBNTextField.setColumns(10);
		bookISBNTextField.setBounds(92, 170, 407, 26);
		panel_1.add(bookISBNTextField);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(91, 202, 225, 15);
		panel_1.add(lblNewLabel);
		
		
		bookISBNTextField.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				    changed();
				  }
				  public void removeUpdate(DocumentEvent e) {
				    changed();
				  }
				  public void insertUpdate(DocumentEvent e) {
				    changed();
				  }

				  public void changed() {
				     if (bookISBNTextField.getText().equals("")){
				    	 bookAddButton.setEnabled(false);
				     }
				     else {
				    	 bookAddButton.setEnabled(true);
				    }
				     
				     if(bookISBNTextField.getText().length() > 13) {
				    	 bookAddButton.setEnabled(false);
				    	 lblNewLabel.setText("ISBN�� 13�ڸ� ���ϸ� �����մϴ�.");
				     }else {
				    	 bookAddButton.setEnabled(true);
				    	 lblNewLabel.setText("");
				     }

				  }
				});
		

		// å �ٰŸ� �г�
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(12, 310, 805, 177);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(new Color(128, 128, 128), 1, true));
		scrollPane.setBounds(0, 0, 805, 177);
		panel_2.add(scrollPane);

		// å �ٰŸ� �ؽ�Ʈ�ʵ�
		bookDescriptionTextField = new JTextField("�ٰŸ��� �Է��ϼ���,,,,");
		scrollPane.setViewportView(bookDescriptionTextField);
		bookDescriptionTextField.setColumns(10);

		// å �߰� ��ư
		bookAddButton = new JButton("\uB3C4\uC11C \uCD94\uAC00");
		bookAddButton.setEnabled(false);
		bookAddButton.setFont(new Font("���Ļ�浸��", Font.PLAIN, 15));
		bookAddButton.setBounds(348, 511, 132, 48);
		//å �߰� ��ư�� ������ ȣ��Ǵ� �޼ҵ� ����
		bookAddButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean isSuccess = false;

					String sql = "insert into BOOK(\r\n"
							+ "BOOK_ISBN,\r\n"
							+ "BOOK_TITLE,\r\n"
							+ "BOOK_AUTHOR,\r\n"
							+ "BOOK_PUB,\r\n"
							+ "BOOK_PRICE,\r\n"
							+ "BOOK_DESCRIPTION,\r\n"
							+ "BOOK_LINK,\r\n"
							+ "BOOK_IMAGE,\r\n"
							+ "BOOK_APPEND_DATE\r\n"
							+ ")values(\r\n"
							+ "?, ?, ?, ?, ?, ?, ?, ?, date_format(now(),'%Y-%m-%d'));";
					
				try { // DB ����
					PreparedStatement ps = dbConn.conn.prepareStatement(sql);
					
					ps.setString(1, bookISBNTextField.getText());			//ISBN
					ps.setString(2, bookNameTextField.getText());			//å ����
					ps.setString(3, bookHeaderTextField_1.getText());		//å ����
					ps.setString(4, bookHeaderTextField_2.getText());		//å ���ǻ�
					ps.setInt(5, Integer.parseInt(bookPriceTextField.getText()));	//å ����	(���ڸ� ����)
					ps.setString(6, bookDescriptionTextField.getText());	//å �ٰŸ�
					ps.setString(7, bookLinkTextField.getText());			//å ���ø�ũ
					
					if(ret == 1) book_img_path.setText("images/nobook.png");
					//å �̹���
					FileInputStream fin = new FileInputStream(book_img_path.getText());
					ps.setBinaryStream(8, fin, fin.available());
					
					
					
					int count = ps.executeUpdate();
					if(count==0) {	
						JOptionPane.showMessageDialog(null,"ISBN : "+bookISBNTextField.getText()+"��(��) ��Ͽ� �����Ͽ����ϴ�.", "�űԵ������ ����", JOptionPane.ERROR_MESSAGE);
					}
					else {		
						JOptionPane.showMessageDialog(null,"ISBN : "+bookISBNTextField.getText()+"��(��) ��Ͽ� �����Ͽ����ϴ�.", "�űԵ������ ����", JOptionPane.NO_OPTION);
						isSuccess = true;
					}
				}
				catch (NumberFormatException e1) {
					e1.printStackTrace();	//���� ����
					JOptionPane.showMessageDialog(null, "���ݿ� ���ڸ� �Է°����մϴ�.", "�Է� ����", JOptionPane.ERROR_MESSAGE);	//���ݿ� ���� �Է½� �޽��� ȣ��
				}
				catch(SQLIntegrityConstraintViolationException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "�Է��� ISBN�� �̹� �����մϴ�.", "�Է� ����", JOptionPane.ERROR_MESSAGE);	//���ݿ� ���� �Է½� �޽��� ȣ��
				}
				catch (SQLException e1) {
					e1.printStackTrace();	//���� ����
					System.out.println("�����߰� ȭ�鿡�� SQL ���� ����");
				}catch(FileNotFoundException e1) {
					e1.printStackTrace();
					System.out.println("�����߰� ȭ�鿡�� ���� ã�� ����");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				if(isSuccess)
					dispose();	//�߰� �� â �ݱ�
			}
		});
		contentPane.add(bookAddButton);	//å �߰� ��ư ����
		
	
	}
}
