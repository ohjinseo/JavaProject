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
	EditableBookInfo bookinfo;
	JLabel bookPictureLabel;
	JButton bookEditButton = new JButton("\uB3C4\uC11C \uC218\uC815");
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public EditBook(String book_ISBN, String user_phone, Boolean manager, EditableBookInfo bookinfo) {
		this.user_phone = user_phone;
		this.manager = manager;
		this.book_ISBN = book_ISBN;
		this.bookinfo = bookinfo;
		setTitle("\uB3C4\uC11C \uAD00\uB9AC \uD504\uB85C\uADF8\uB7A8 - \uB3C4\uC11C\uC218\uC815");
		setBounds(100, 100, 848, 596);
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

		// ?? ???? ????
		bookPictureLabel = new JLabel("\uCC45\uC0AC\uC9C4");
		bookPictureLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bookPictureLabel.setBounds(12, 10, 157, 147);
		panel.add(bookPictureLabel);

		// ?????? ???? ????
		JFileChooser book_img = new JFileChooser();
		FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("All Images", "jpg", "jpge", "png", "gif");
		book_img.setFileFilter(fileFilter);

		// ?? ???? ????
		JLabel book_img_path = new JLabel("?????? ????");
		book_img_path.setBounds(12, 157, 157, 29);
		panel.add(book_img_path);

		// ?? ?????? ???? ????
		JButton bookImageFindButton = new JButton("????????");

		bookImageFindButton.addMouseListener(new MouseAdapter() { // ??????????
			@Override
			public void mouseClicked(MouseEvent e) {
				int ret = book_img.showOpenDialog(null); // ???? ???? ???? ??????
				String filePath = book_img.getSelectedFile().getPath(); // ???? ?????? filePath?? ????

				JOptionPane.showMessageDialog(null, filePath, "?????? ?????? ??????", JOptionPane.NO_OPTION); // ?????? ?????????? ??????
																										// ?????? ????
				book_img_path.setText(filePath);
				try { // DB ????
						// ?? ??????
					File tmpFile = new File(book_img_path.getText());

					Image img = ImageIO.read(tmpFile); // ?????? ???????? img?? ????
					Image resize_img = img.getScaledInstance(170, 147, Image.SCALE_SMOOTH); // ?????? ???? 170x140 ?? ???? ????????
																							// resize_img?? ????
					ImageIcon icon = new ImageIcon(resize_img); // ?????? ?????? ???????? icon?? ????
					bookPictureLabel.setIcon(icon); // ?? ???? ???????? ?????? ????
					bookPictureLabel.setBorder(new LineBorder(Color.black, 1, false)); // ?????? ?????? ?????????? ??????

					String sql = "UPDATE BOOK SET BOOK_IMAGE = ? WHERE BOOK_ISBN = ?;";
					PreparedStatement ps = dbConn.conn.prepareStatement(sql);
					
					// ???? ??????
					FileInputStream fin = new FileInputStream(book_img_path.getText());
					ps.setBinaryStream(1, fin, fin.available());
					ps.setString(2, book_ISBN);

					int count = ps.executeUpdate();
					if (count == 0) {
						JOptionPane.showMessageDialog(null, "???? ?????? ?????? ??????????????.", "???? ?????? ???? ????",
								JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "???? ?????? ?????? ??????????????.", "???? ?????? ???? ????",
								JOptionPane.NO_OPTION);
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace(); // ???? ????
					System.out.println("???? ???? ???????? SQL ???? ????");
				} catch (FileNotFoundException e1) {
					System.out.println("???? ???? ???????? ???? ???? ????");
				} catch (IOException e1) {
					System.out.println("???? ???? ???????? ?????? ???? ???? ????");
				}
			}
		});
		bookImageFindButton.setFont(new Font("\uD55C\uCEF4\uC0B0\uB73B\uB3CB\uC6C0",
				bookImageFindButton.getFont().getStyle() | Font.BOLD, bookImageFindButton.getFont().getSize() + 3));
		bookImageFindButton.setBounds(12, 190, 157, 39);
		panel.add(bookImageFindButton);

		// ?? ???? ????
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(205, 31, 612, 239);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		// ?? ???? ??????????
		bookNameTextField = new JTextField();
		bookNameTextField.setFont(new Font("??????????", Font.BOLD, 22));
		bookNameTextField.setBounds(88, 10, 371, 36);
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
				    	 bookEditButton.setEnabled(false);
				     }
				     else {
				    	 bookEditButton.setEnabled(true);
				    }

				  }
				});

		// ?? ???? ????
		JLabel bookHeaderLabel_1 = new JLabel("\uC800\uC790 :");
		bookHeaderLabel_1.setFont(new Font("????????????", Font.PLAIN, 16));
		bookHeaderLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		bookHeaderLabel_1.setBounds(12, 60, 46, 26);
		panel_1.add(bookHeaderLabel_1);

		// ?? ???? ??????????
		bookHeaderTextField_1 = new JTextField();
		bookHeaderTextField_1.setFont(new Font("????????????", Font.PLAIN, 16));
		bookHeaderTextField_1.setColumns(10);
		bookHeaderTextField_1.setBounds(98, 60, 165, 26);
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
				    	 bookEditButton.setEnabled(false);
				     }
				     else {
				    	 bookEditButton.setEnabled(true);
				    }

				  }
				});

		// ?? ?????? ????
		JLabel bookHeaderLabel_2 = new JLabel("\uCD9C\uD310\uC0AC : ");
		bookHeaderLabel_2.setFont(new Font("????????????", Font.PLAIN, 16));
		bookHeaderLabel_2.setBounds(280, 60, 58, 26);
		panel_1.add(bookHeaderLabel_2);

		// ?? ?????? ??????????
		bookHeaderTextField_2 = new JTextField();
		bookHeaderTextField_2.setFont(new Font("????????????", Font.PLAIN, 16));
		bookHeaderTextField_2.setColumns(10);
		bookHeaderTextField_2.setBounds(350, 56, 172, 26);
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
				    	 bookEditButton.setEnabled(false);
				     }
				     else {
				    	 bookEditButton.setEnabled(true);
				    }

				  }
				});

		// ?? ???????? ????
		JLabel bookLinkLabel = new JLabel("\uAD00\uB828\uB9C1\uD06C : ");
		bookLinkLabel.setFont(new Font("????????????", Font.PLAIN, 16));
		bookLinkLabel.setBounds(12, 203, 83, 26);
		panel_1.add(bookLinkLabel);

		// ?? ???????? ??????????
		bookLinkTextField = new JTextField();
		bookLinkTextField.setFont(new Font("????????????", Font.PLAIN, 16));
		bookLinkTextField.setColumns(10);
		bookLinkTextField.setBounds(95, 203, 364, 26);
		panel_1.add(bookLinkTextField);

		// ?? ???? ????
		JLabel bookPriceLabel = new JLabel("\uAC00\uACA9 : ");
		bookPriceLabel.setFont(new Font("????????????", Font.PLAIN, 16));
		bookPriceLabel.setBounds(12, 99, 46, 26);
		panel_1.add(bookPriceLabel);
		JLabel lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(88, 131, 210, 15);
		panel_1.add(lblNewLabel_1);
		// ?? ???? ??????????
		bookPriceTextField = new JTextField();
		bookPriceTextField.setFont(new Font("????????????", Font.PLAIN, 16));
		bookPriceTextField.setColumns(10);
		bookPriceTextField.setBounds(93, 99, 366, 26);
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
	            	 lblNewLabel_1.setText("?????? ?????? ??????????.");
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
				    	 bookEditButton.setEnabled(false);
				     }
				     else {
				    	 bookEditButton.setEnabled(true);
				    }
				     
				     if (bookPriceTextField.getText().length() > 10) {
				    	 bookEditButton.setEnabled(false);
				    	 lblNewLabel_1.setText("?????? 10???? ?????? ??????????.");
				     }else {
				    	 bookEditButton.setEnabled(true);
				    	 lblNewLabel_1.setText("");
				     }

				  }
				});

		// ?? ISBN ????
		JLabel bookISBNLabel = new JLabel("ISBN : ");
		bookISBNLabel.setFont(new Font("????????????", Font.PLAIN, 16));
		bookISBNLabel.setBounds(12, 156, 58, 26);
		panel_1.add(bookISBNLabel);
		

		// ?? ISBN ??????????
		bookISBNTextField = new JTextField();
		bookISBNTextField.setEditable(false);
		bookISBNTextField.setFont(new Font("????????????", Font.PLAIN, 16));
		bookISBNTextField.setColumns(10);
		bookISBNTextField.setBounds(93, 156, 366, 26);
		panel_1.add(bookISBNTextField);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(103, 181, 210, 15);
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
				    	 bookEditButton.setEnabled(false);
				     }
				     else {
				    	 bookEditButton.setEnabled(true);
				    }
				     
				     if(bookISBNTextField.getText().length() > 13) {
				    	 bookEditButton.setEnabled(false);
				    	 lblNewLabel.setText("ISBN?? 13???? ?????? ??????????.");
				     }else {
				    	 bookEditButton.setEnabled(true);
				    	 lblNewLabel.setText("");
				     }

				  }
				});

		// ?? ?????? ????
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(12, 283, 805, 142);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(new Color(128, 128, 128), 1, true));
		scrollPane.setBounds(0, 0, 805, 142);
		panel_2.add(scrollPane);


	

		// ?? ?????? ??????????
		bookDescriptionTextField = new JTextArea();
		scrollPane.setViewportView(bookDescriptionTextField);
		bookDescriptionTextField.setColumns(10);
		bookDescriptionTextField.setLineWrap(true);
		bookDescriptionTextField.setFont(new Font("????????????", Font.PLAIN, 16));
		
		
		// ?? ???? ????
		
		bookEditButton.setFont(new Font("????????????", Font.PLAIN, 15));
		bookEditButton.setBounds(335, 466, 132, 48);
		bookEditButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				String sql = "UPDATE BOOK\r\n"
						+ "SET BOOK_TITLE = ?, BOOK_AUTHOR = ?, BOOK_PUB = ?, BOOK_PRICE = ?, BOOK_ISBN = ?, BOOK_LINK = ?, BOOK_DESCRIPTION = ?\r\n"
						+ "WHERE BOOK_ISBN = ?;";

				try { // DB ????
					PreparedStatement ps = dbConn.conn.prepareStatement(sql);

					ps.setString(1, bookNameTextField.getText()); // ????
					ps.setString(2, bookHeaderTextField_1.getText()); // ????
					ps.setString(3, bookHeaderTextField_2.getText()); // ??????
					ps.setString(4, bookPriceTextField.getText()); // ????
					ps.setString(5, bookISBNTextField.getText()); // ISBN
					ps.setString(6, bookLinkTextField.getText()); // ????????
					ps.setString(7, bookDescriptionTextField.getText());	//??????
					ps.setString(8, book_ISBN);	//???? PK
					
					int count = ps.executeUpdate();
					if (count == 0) {
						JOptionPane.showMessageDialog(null, "???? ?????? ??????????????.", "???? ???? ????",
								JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "???? ?????? ??????????????.", "???? ???? ????",
								JOptionPane.NO_OPTION);
					}
				} catch (SQLException e1) {
					e1.printStackTrace(); // ???? ????
					System.out.println("???????? ???? SQL ???? ????");
				}
				try {
					bookinfo.getBookInfo();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				dispose();
			}
		});
		getBookInfo();
		contentPane.add(bookEditButton);
		
	}
	public void getBookInfo() {
		try { // DB ????
			// db???? ISBN???? ?? ???? ????
		ResultSet rs = dbConn.executeQuery(
				"SELECT BOOK_TITLE, BOOK_AUTHOR, BOOK_PUB, BOOK_PRICE, BOOK_ISBN, BOOK_LINK, BOOK_DESCRIPTION, BOOK_IMAGE\r\n"
						+ "FROM BOOK\r\n" + "WHERE BOOK_ISBN = '" + book_ISBN + "' AND BOOK_PRE = TRUE;");
		while (rs.next()) {
			bookNameTextField.setText(rs.getString("BOOK_TITLE")); // ?? ???? ????
			bookHeaderTextField_1.setText(rs.getString("BOOK_AUTHOR")); // ?? ???? ????
			bookHeaderTextField_2.setText(rs.getString("BOOK_PUB")); // ?? ?????? ????
			bookPriceTextField.setText(rs.getString("BOOK_PRICE")); // ?? ???? ????
			bookISBNTextField.setText(rs.getString("BOOK_ISBN")); // ?? ISBN ????
			bookLinkTextField.setText(rs.getString("BOOK_LINK")); // ?? ???????? ????
			bookDescriptionTextField.setText(rs.getString("BOOK_DESCRIPTION")); // ?? ?????? ????
			// ?? ?????? ????
			InputStream inputStream = rs.getBinaryStream("BOOK_IMAGE"); // ???????? ??????
			try {
				Image img = ImageIO.read(inputStream); // ?????? ???????? img?? ????
				Image resize_img = img.getScaledInstance(170, 147, Image.SCALE_SMOOTH); // ?????? ???? 170x147?? ???? ????????
																						// resize_img?? ????
				ImageIcon icon = new ImageIcon(resize_img); // ?????? ?????? ???????? icon?? ????
				bookPictureLabel.setIcon(icon); // ?? ?????? ????
				bookPictureLabel.setBorder(new LineBorder(Color.black, 1, false)); // ?????? ?????? ?????? ?????????? ????
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	} catch (SQLException e2) {
		System.out.println("SQL ???? ????");
	}
	}
}
