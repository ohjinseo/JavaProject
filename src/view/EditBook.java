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

		// 책 사진 라벨
		bookPictureLabel = new JLabel("\uCC45\uC0AC\uC9C4");
		bookPictureLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bookPictureLabel.setBounds(12, 10, 157, 147);
		panel.add(bookPictureLabel);

		// 이미지 파일 필터
		JFileChooser book_img = new JFileChooser();
		FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("All Images", "jpg", "jpge", "png", "gif");
		book_img.setFileFilter(fileFilter);

		// 책 주소 라벨
		JLabel book_img_path = new JLabel("이미지 주소");
		book_img_path.setBounds(12, 157, 157, 29);
		panel.add(book_img_path);

		// 책 이미지 찾기 버튼
		JButton bookImageFindButton = new JButton("찾아보기");

		bookImageFindButton.addMouseListener(new MouseAdapter() { // 클릭이벤트
			@Override
			public void mouseClicked(MouseEvent e) {
				int ret = book_img.showOpenDialog(null); // 파일 찾는 창을 띄우줌
				String filePath = book_img.getSelectedFile().getPath(); // 파일 경로를 filePath에 저장

				JOptionPane.showMessageDialog(null, filePath, "당신이 선택한 파일은", JOptionPane.NO_OPTION); // 선택한 파일경로를 메시지
																										// 창으로 띄움
				book_img_path.setText(filePath);
				try { // DB 접근
						// 책 이미지
					File tmpFile = new File(book_img_path.getText());

					Image img = ImageIO.read(tmpFile); // 읽어온 이미지를 img에 저장
					Image resize_img = img.getScaledInstance(170, 147, Image.SCALE_SMOOTH); // 이미지 크기 170x140 로 크기 조절하여
																							// resize_img에 저장
					ImageIcon icon = new ImageIcon(resize_img); // 조절한 크기의 이미지를 icon에 저장
					bookPictureLabel.setIcon(icon); // 책 사진 레이블에 이미지 부착
					bookPictureLabel.setBorder(new LineBorder(Color.black, 1, false)); // 레이블 테두리 검은색으로 그려줌

					String sql = "UPDATE BOOK SET BOOK_IMAGE = ? WHERE BOOK_ISBN = ?;";
					PreparedStatement ps = dbConn.conn.prepareStatement(sql);
					
					// 유저 이미지
					FileInputStream fin = new FileInputStream(book_img_path.getText());
					ps.setBinaryStream(1, fin, fin.available());
					ps.setString(2, book_ISBN);

					int count = ps.executeUpdate();
					if (count == 0) {
						JOptionPane.showMessageDialog(null, "도서 이미지 수정에 실패하였습니다.", "도서 이미지 수정 실패",
								JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "도서 이미지 수정에 성공하였습니다.", "도서 이미지 수정 성공",
								JOptionPane.NO_OPTION);
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace(); // 에러 추적
					System.out.println("도서 수정 화면에서 SQL 실행 에러");
				} catch (FileNotFoundException e1) {
					System.out.println("도서 수정 화면에서 파일 찾기 에러");
				} catch (IOException e1) {
					System.out.println("도서 수정 화면에서 읽어온 파일 출력 에러");
				}
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

		// 책 출판사 라벨
		JLabel bookHeaderLabel_2 = new JLabel("\uCD9C\uD310\uC0AC : ");
		bookHeaderLabel_2.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		bookHeaderLabel_2.setBounds(280, 60, 58, 26);
		panel_1.add(bookHeaderLabel_2);

		// 책 출판사 텍스트필드
		bookHeaderTextField_2 = new JTextField();
		bookHeaderTextField_2.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
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

		// 책 관련링크 라벨
		JLabel bookLinkLabel = new JLabel("\uAD00\uB828\uB9C1\uD06C : ");
		bookLinkLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		bookLinkLabel.setBounds(12, 203, 83, 26);
		panel_1.add(bookLinkLabel);

		// 책 관련링크 텍스트필드
		bookLinkTextField = new JTextField();
		bookLinkTextField.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		bookLinkTextField.setColumns(10);
		bookLinkTextField.setBounds(95, 203, 364, 26);
		panel_1.add(bookLinkTextField);

		// 책 가격 라벨
		JLabel bookPriceLabel = new JLabel("\uAC00\uACA9 : ");
		bookPriceLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		bookPriceLabel.setBounds(12, 99, 46, 26);
		panel_1.add(bookPriceLabel);
		JLabel lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(88, 131, 210, 15);
		panel_1.add(lblNewLabel_1);
		// 책 가격 텍스트필드
		bookPriceTextField = new JTextField();
		bookPriceTextField.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
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
	            	 lblNewLabel_1.setText("가격은 숫자만 가능합니다.");
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
				    	 lblNewLabel_1.setText("가격은 10자리 이하만 가능합니다.");
				     }else {
				    	 bookEditButton.setEnabled(true);
				    	 lblNewLabel_1.setText("");
				     }

				  }
				});

		// 책 ISBN 라벨
		JLabel bookISBNLabel = new JLabel("ISBN : ");
		bookISBNLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		bookISBNLabel.setBounds(12, 156, 58, 26);
		panel_1.add(bookISBNLabel);
		

		// 책 ISBN 텍스트필드
		bookISBNTextField = new JTextField();
		bookISBNTextField.setEditable(false);
		bookISBNTextField.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
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
				    	 lblNewLabel.setText("ISBN은 13자리 이하만 가능합니다.");
				     }else {
				    	 bookEditButton.setEnabled(true);
				    	 lblNewLabel.setText("");
				     }

				  }
				});

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
		bookDescriptionTextField = new JTextArea();
		scrollPane.setViewportView(bookDescriptionTextField);
		bookDescriptionTextField.setColumns(10);
		bookDescriptionTextField.setLineWrap(true);
		bookDescriptionTextField.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		
		
		// 책 수정 버튼
		
		bookEditButton.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 15));
		bookEditButton.setBounds(335, 466, 132, 48);
		bookEditButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				String sql = "UPDATE BOOK\r\n"
						+ "SET BOOK_TITLE = ?, BOOK_AUTHOR = ?, BOOK_PUB = ?, BOOK_PRICE = ?, BOOK_ISBN = ?, BOOK_LINK = ?, BOOK_DESCRIPTION = ?\r\n"
						+ "WHERE BOOK_ISBN = ?;";

				try { // DB 접근
					PreparedStatement ps = dbConn.conn.prepareStatement(sql);

					ps.setString(1, bookNameTextField.getText()); // 제목
					ps.setString(2, bookHeaderTextField_1.getText()); // 저자
					ps.setString(3, bookHeaderTextField_2.getText()); // 출판사
					ps.setString(4, bookPriceTextField.getText()); // 가격
					ps.setString(5, bookISBNTextField.getText()); // ISBN
					ps.setString(6, bookLinkTextField.getText()); // 관련링크
					ps.setString(7, bookDescriptionTextField.getText());	//줄거리
					ps.setString(8, book_ISBN);	//도서 PK
					
					int count = ps.executeUpdate();
					if (count == 0) {
						JOptionPane.showMessageDialog(null, "도서 수정에 실패하였습니다.", "도서 수정 실패",
								JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "도서 수정에 성공하였습니다.", "도서 수정 성공",
								JOptionPane.NO_OPTION);
					}
				} catch (SQLException e1) {
					e1.printStackTrace(); // 에러 추적
					System.out.println("도서수정 에서 SQL 실행 에러");
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
		try { // DB 접근
			// db에서 ISBN으로 책 정보 검색
		ResultSet rs = dbConn.executeQuery(
				"SELECT BOOK_TITLE, BOOK_AUTHOR, BOOK_PUB, BOOK_PRICE, BOOK_ISBN, BOOK_LINK, BOOK_DESCRIPTION, BOOK_IMAGE\r\n"
						+ "FROM BOOK\r\n" + "WHERE BOOK_ISBN = '" + book_ISBN + "' AND BOOK_PRE = TRUE;");
		while (rs.next()) {
			bookNameTextField.setText(rs.getString("BOOK_TITLE")); // 책 제목 설정
			bookHeaderTextField_1.setText(rs.getString("BOOK_AUTHOR")); // 책 저자 설정
			bookHeaderTextField_2.setText(rs.getString("BOOK_PUB")); // 책 출판사 설정
			bookPriceTextField.setText(rs.getString("BOOK_PRICE")); // 책 가격 설정
			bookISBNTextField.setText(rs.getString("BOOK_ISBN")); // 책 ISBN 설정
			bookLinkTextField.setText(rs.getString("BOOK_LINK")); // 책 관련링크 설정
			bookDescriptionTextField.setText(rs.getString("BOOK_DESCRIPTION")); // 책 줄거리 설정
			// 책 이미지 설정
			InputStream inputStream = rs.getBinaryStream("BOOK_IMAGE"); // 이미지를 읽어옴
			try {
				Image img = ImageIO.read(inputStream); // 읽어온 이미지를 img에 저장
				Image resize_img = img.getScaledInstance(170, 147, Image.SCALE_SMOOTH); // 이미지 크기 170x147로 크기 조절하여
																						// resize_img에 저장
				ImageIcon icon = new ImageIcon(resize_img); // 조절한 크기의 이미지를 icon에 저장
				bookPictureLabel.setIcon(icon); // 책 이미지 설정
				bookPictureLabel.setBorder(new LineBorder(Color.black, 1, false)); // 이미지 레이블 테두리 검은색으로 설정
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	} catch (SQLException e2) {
		System.out.println("SQL 실행 에러");
	}
	}
}
