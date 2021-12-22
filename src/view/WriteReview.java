package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.SystemColor;

public class WriteReview extends JFrame {
	dbConnector dbConn = new dbConnector();
	private String user_phone;
	private String book_ISBN;
	private JPanel contentPane;
	private int starCnt = 0;


	/**
	 * Create the frame.
	 */
	public WriteReview(String book_ISBN, String user_phone) {
		this.user_phone = user_phone;
		this.book_ISBN = book_ISBN;
		
		setTitle("\uD55C\uC904\uD3C9 \uC791\uC131");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// ���� �г�
		JPanel panel = new JPanel();
		panel.setBounds(12, 10, 412, 210);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel InfoLabel = new JLabel(
				"\uC7AC\uBBF8\uC788\uAC8C \uC77D\uC73C\uC168\uB2E4\uBA74 \uD55C\uC904\uD3C9\uC744 \uC791\uC131\uD574 \uC8FC\uC138\uC694");
		InfoLabel.setFont(new Font("���Ļ�浸��", Font.BOLD, 15));
		InfoLabel.setBounds(12, 8, 327, 15);
		panel.add(InfoLabel);

		// �� ù��° ��
		JLabel starFirstLabel = new JLabel("\u2606");
		starFirstLabel.setFont(new Font("���Ļ�浸��", Font.BOLD, 30));
		starFirstLabel.setBounds(12, 33, 30, 29);
		panel.add(starFirstLabel);

		// �� �ι�° ��
		JLabel starSecondLabel = new JLabel("\u2606");
		starSecondLabel.setFont(new Font("���Ļ�浸��", Font.BOLD, 30));
		starSecondLabel.setBounds(54, 33, 30, 29);
		panel.add(starSecondLabel);

		// �� ����° ��
		JLabel starThirdLabel = new JLabel("\u2606");
		starThirdLabel.setFont(new Font("���Ļ�浸��", Font.BOLD, 30));
		starThirdLabel.setBounds(96, 33, 30, 29);
		panel.add(starThirdLabel);

		// �� �׹��� ��
		JLabel starFourthLabel = new JLabel("\u2606");
		starFourthLabel.setFont(new Font("���Ļ�浸��", Font.BOLD, 30));
		starFourthLabel.setBounds(138, 33, 30, 29);
		panel.add(starFourthLabel);

		// �� �ټ���° ��
		JLabel starFifthLabel = new JLabel("\u2606");
		starFifthLabel.setFont(new Font("���Ļ�浸��", Font.BOLD, 30));
		starFifthLabel.setBounds(180, 33, 30, 29);
		panel.add(starFifthLabel);

		//1��° �� ������
		starFirstLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (starFirstLabel.getText() == "��") {
					
					starFirstLabel.setText("��");
					starCnt = 1;
				}
				else
				{
					starCnt = 0;
					starFirstLabel.setText("��");
					starSecondLabel.setText("��");
					starThirdLabel.setText("��");
					starFourthLabel.setText("��");
					starFifthLabel.setText("��");
				}
					
			}
		});
		
		//2��° �� ������
		starSecondLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (starSecondLabel.getText() == "��") {
					starCnt = 2;
					starFirstLabel.setText("��");
					starSecondLabel.setText("��");
				} else {
					starCnt = 1;
					starSecondLabel.setText("��");
					starThirdLabel.setText("��");
					starFourthLabel.setText("��");
					starFifthLabel.setText("��");
				}
					
			}
		});
		
		//3��° �� ������
		starThirdLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (starThirdLabel.getText() == "��") {
					starCnt = 3;
					starFirstLabel.setText("��");
					starSecondLabel.setText("��");
					starThirdLabel.setText("��");
				} else {
					starCnt = 2;
					starThirdLabel.setText("��");
					starFourthLabel.setText("��");
					starFifthLabel.setText("��");
				}
					
			}
		});
		
		//4��° �� ������
		starFourthLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (starFourthLabel.getText() == "��") {
					starCnt = 4;
					starFirstLabel.setText("��");
					starSecondLabel.setText("��");
					starThirdLabel.setText("��");
					starFourthLabel.setText("��");
				} else {
					starCnt = 3;
					starFourthLabel.setText("��");
					starFifthLabel.setText("��");
				}
					
			}
		});
		
		//5��° �� ������
		starFifthLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (starFifthLabel.getText() == "��") {
					starCnt = 5;
					starFirstLabel.setText("��");
					starSecondLabel.setText("��");
					starThirdLabel.setText("��");
					starFourthLabel.setText("��");
					starFifthLabel.setText("��");
				} else {
					starCnt = 4;
					starFifthLabel.setText("��");
				}
			}
		});
		
		// ���� �ؽ�Ʈ�ʵ�
		JTextArea ReviewTextArea = new JTextArea();
		ReviewTextArea.setLineWrap(true);
		ReviewTextArea.setBounds(12, 72, 388, 128);
		panel.add(ReviewTextArea);

		// ��ư �г�
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 218, 412, 33);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		// Ȯ�� ��ư
		JButton OkButton = new JButton("Ok");
		OkButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					String sql = "insert into REVIEW(\r\n"
							+ "BOOK_ISBN,\r\n"
							+ "USER_PHONE,\r\n"
							+ "REVIEW_TEXT,\r\n"
							+ "BOOK_GRADE\r\n"
							+ ")values(\r\n"
							+ "?, ?, ?, ?);";
					PreparedStatement ps = dbConn.conn.prepareStatement(sql);
					
					ps.setString(1, book_ISBN);
					ps.setString(2,  user_phone);
					ps.setString(3,  ReviewTextArea.getText());
					ps.setInt(4, starCnt);
					
					int count = ps.executeUpdate();
					if(count==0) {	
						JOptionPane.showMessageDialog(null, "���� ��Ͻ���", "�����Ͻ���", JOptionPane.ERROR_MESSAGE);
					}
					else {		
						updateBookGrade();
						JOptionPane.showMessageDialog(null, "���� �����մϴ�", "�����ϼ���",JOptionPane.NO_OPTION);
						
					}
				}catch(SQLException e1) {
					e1.printStackTrace();
					System.out.println("���� sql ����");
				}
				dispose();
			}
		});
		OkButton.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		OkButton.setBounds(209, 5, 91, 23);
		panel_1.add(OkButton);

		// ��� ��ư
		JButton CancleButton = new JButton("Cancel");
		CancleButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		CancleButton.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		CancleButton.setBounds(312, 5, 91, 23);
		panel_1.add(CancleButton);
		
		
	}
	
	public void updateBookGrade() {
		String sql = "update BOOK\r\n" + "SET BOOK_GRADE = BOOK_GRADE + " + starCnt + "\r\nWHERE BOOK_ISBN = '"
				+ book_ISBN + "';";
		try { // DB ����
			PreparedStatement ps = dbConn.conn.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("updateBookGrade sql ����");
		}
	}
	
	
}
