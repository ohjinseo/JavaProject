package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;

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

public class Main extends JFrame {

	private JPanel contentPane;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 881, 694);
		contentPane = new JPanel();	//���� ������
		contentPane.setBackground(new Color(245, 245, 245));
		setContentPane(contentPane);
		contentPane.setLayout(null);	//���� ������ ���̾ƿ� null�� ����
		
		//�޴���
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.setBackground(new Color(51, 51, 51));
		menuBar.setBounds(0, 0, 865, 47);
		contentPane.add(menuBar);
		
		//Ȩ������ �޴�
		JMenu homeIconMenu = new JMenu(" \uD648 ");
		homeIconMenu.setFont(new Font("���Ļ�浸��", Font.PLAIN, 16));
		homeIconMenu.setForeground(new Color(255, 255, 255));
		ImageIcon icon5 = new ImageIcon("D:\\\uC0C8 \uD3F4\uB354\\pngegg.png");
		Image img5 = icon5.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon changeIcon5 = new ImageIcon(img5);
		homeIconMenu.setIcon(changeIcon5);
		menuBar.add(homeIconMenu);
		
		//���� ã�� �޴�
		JMenu findBookMenu = new JMenu("\uB3C4\uC11C\uCC3E\uAE30");	//�޴� - ����ã��
		findBookMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "�ش� ������ �����Ͽ����ϴ�.\n1000-00-00 ���� �ݳ��� �ּ���.", "���⼺��",
						JOptionPane.INFORMATION_MESSAGE);
				//����ȵ�
			}
		});
		findBookMenu.setForeground(new Color(255, 255, 255));
		findBookMenu.setFont(new Font("���Ļ�浸��", Font.PLAIN, 16));
		menuBar.add(findBookMenu);
		
		//ȸ�� ���� �޴�
		JMenu userInfoMenu = new JMenu("\uD68C\uC6D0\uC815\uBCF4");
		userInfoMenu.setForeground(new Color(255, 255, 255));
		userInfoMenu.setBackground(new Color(230, 230, 250));
		userInfoMenu.setFont(new Font("���Ļ�浸��", Font.PLAIN, 16));
		menuBar.add(userInfoMenu);
		
		//���ã���ѵ��� ��
		JLabel popularBookLabel = new JLabel("\uC778\uAE30\uB3C4\uC11C");
		popularBookLabel.setForeground(new Color(51, 51, 51));
		popularBookLabel.setFont(new Font("���Ļ�浸��", Font.BOLD, 20));
		popularBookLabel.setBounds(22, 63, 123, 40);
		contentPane.add(popularBookLabel);
		
		//�α⵵�� �г�
		JPanel popularBookPanel = new JPanel();
		popularBookPanel.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		popularBookPanel.setBackground(SystemColor.menu);
		popularBookPanel.setBounds(22, 101, 820, 125);
		contentPane.add(popularBookPanel);
		popularBookPanel.setLayout(null);

		//�Ű����� ��
		JLabel newlyBookLabel = new JLabel("\uC2E0\uAC04\uB3C4\uC11C");
		newlyBookLabel.setFont(new Font("���Ļ�浸��", Font.BOLD, 20));
		newlyBookLabel.setBounds(22, 260, 123, 40);
		contentPane.add(newlyBookLabel);
		
		//�Ű����� �г�
		JPanel newlyBookPanel = new JPanel();
		newlyBookPanel.setForeground(new Color(255, 255, 255));
		newlyBookPanel.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		newlyBookPanel.setBounds(22, 298, 820, 125);
		contentPane.add(newlyBookPanel);
		
		//���ã�� �г�
		JPanel favoriteBookPanel = new JPanel();
		favoriteBookPanel.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		favoriteBookPanel.setBounds(22, 502, 820, 125);
		contentPane.add(favoriteBookPanel);
		
		//�α⵵�� �г�
		JLabel favoriteBookLabel = new JLabel("\uC990\uACA8\uCC3E\uAE30");
		favoriteBookLabel.setFont(new Font("���Ļ�浸��", Font.BOLD, 20));
		favoriteBookLabel.setBounds(22, 462, 123, 40);
		contentPane.add(favoriteBookLabel);
	}

}
