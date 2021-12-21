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
		
		//유저 이름 라벨
		userNameLabel = new JLabel();
		userNameLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 12));
		userNameLabel.setBounds(12, 5, 85, 23);
		add(userNameLabel);
		
		//유저 별 라벨
		userGradeLabel = new JLabel();
		userGradeLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 12));
		userGradeLabel.setBounds(141, 2, 116, 26);
		add(userGradeLabel);
		
		//유저 리뷰내용 라벨
		userReviewLabel = new JLabel();
		userReviewLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		userReviewLabel.setBounds(12, 26, 792, 30);
		add(userReviewLabel);
	}
	
	public void addProperty(String userName, int starCnt, String userReviewText){
		userNameLabel.setText(userName);
		
		switch (starCnt) {
		case 0:
			userGradeLabel.setText("☆☆☆☆☆");
			break;
		case 1:
			userGradeLabel.setText("★☆☆☆☆");
			break;
		case 2:
			userGradeLabel.setText("★★☆☆☆");
			break;
		case 3:
			userGradeLabel.setText("★★★☆☆");
			break;
		case 4:
			userGradeLabel.setText("★★★★☆");
			break;
		case 5:
			userGradeLabel.setText("★★★★★");
			break;
		}
		
		userReviewLabel.setText(userReviewText);
	}

}
