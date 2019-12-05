import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;



public class GameMain {
	
	public static void main(String[] args) {

		Model model = new Model(10,10);
		GameView view = new GameView(model);
		GameController control = new GameController(view, model);
		
		JFrame main_frame = new JFrame();
		main_frame.setTitle("Game of Life");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel top_panel = new JPanel();
		top_panel.setLayout(new BorderLayout());
		main_frame.setContentPane(top_panel);
		
		top_panel.add(view, BorderLayout.CENTER);
		
		main_frame.pack();
		main_frame.setVisible(true);
	}
	
}