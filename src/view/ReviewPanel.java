package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

public class ReviewPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public ReviewPanel() {
		setLayout(null);
		
		//유저 이름 라벨
		JLabel userNameLabel = new JLabel("\uC131\uC774\uB984");
		userNameLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 12));
		userNameLabel.setBounds(12, 5, 85, 23);
		add(userNameLabel);
		
		//유저 별 라벨
		JLabel userGradeLabel = new JLabel("\u2605\u2605\u2605\u2606\u2606");
		userGradeLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 12));
		userGradeLabel.setBounds(141, 2, 116, 26);
		add(userGradeLabel);
		
		//유저 리뷰내용 라벨
		JLabel userReviewLabel = new JLabel("\uC7AC\uBBF8\uC788\uC5B4\uC694\uC7AC\uBBF8\uC788\uC5B4\uC694\uC7AC\uBBF8\uC788\uC5B4\uC694\uC7AC\uBBF8\uC788\uC5B4\uC694\uC7AC\uBBF8\uC788\uC5B4\uC694\uC7AC\uBBF8\uC788\uC5B4\uC694\uC7AC\uBBF8\uC788\uC5B4\uC694\uC7AC\uBBF8\uC788\uC5B4\uC694\uC7AC\uBBF8\uC788\uC5B4\uC694\uC7AC\uBBF8\uC788\uC5B4\uC694\uC7AC\uBBF8\uC788\uC5B4\uC694\uC7AC\uBBF8\uC788\uC5B4\uC694\uC7AC\uBBF8\uC788\uC5B4\uC694\uC7AC\uBBF8\uC788\uC5B4\uC694\uC7AC\uBBF8\uC788\uC5B4\uC694\uC7AC\uBBF8\uC788\uC5B4\uC694\uC7AC\uBBF8\uC788\uC5B4\uC694\uC7AC\uBBF8\uC788\uC5B4\uC694");
		userReviewLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		userReviewLabel.setBounds(12, 26, 792, 30);
		add(userReviewLabel);
	}

}
