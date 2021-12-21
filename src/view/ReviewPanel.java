package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.SystemColor;

public class ReviewPanel extends JPanel {

	JLabel userNameLabel;
	JLabel userGradeLabel;
	JLabel userReviewLabel;
	public ReviewPanel() {
		setBackground(SystemColor.controlLtHighlight);
		setBorder(new LineBorder(Color.GRAY, 2, true));
		setLayout(null);
		
		//���� �̸� ��
		userNameLabel = new JLabel();
		userNameLabel.setFont(new Font("���Ļ�浸��", Font.BOLD, 12));
		userNameLabel.setBounds(12, 5, 85, 23);
		add(userNameLabel);
		
		//���� �� ��
		userGradeLabel = new JLabel();
		userGradeLabel.setFont(new Font("���Ļ�浸��", Font.BOLD, 12));
		userGradeLabel.setBounds(141, 2, 116, 26);
		add(userGradeLabel);
		
		//���� ���䳻�� ��
		userReviewLabel = new JLabel();
		userReviewLabel.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		userReviewLabel.setBounds(12, 26, 792, 30);
		add(userReviewLabel);
	}
	
	public void addProperty(String userName, int starCnt, String userReviewText){
		userNameLabel.setText(userName);
		
		switch (starCnt) {
		case 0:
			userGradeLabel.setText("�١١١١�");
			break;
		case 1:
			userGradeLabel.setText("�ڡ١١١�");
			break;
		case 2:
			userGradeLabel.setText("�ڡڡ١١�");
			break;
		case 3:
			userGradeLabel.setText("�ڡڡڡ١�");
			break;
		case 4:
			userGradeLabel.setText("�ڡڡڡڡ�");
			break;
		case 5:
			userGradeLabel.setText("�ڡڡڡڡ�");
			break;
		}
		
		userReviewLabel.setText(userReviewText);
	}

}
