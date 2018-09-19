package ChessGame.GUI;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class ChessMain {

	public static JFrame startFrm; // starting frame
	public static JButton startButton; 
	public static JFrame mainFrm; 
	public static BoardPanel p1; 
	
	public static void main(String[] args) {
	
		
		startFrm = new JFrame(); 
		startButton = new JButton("Start Game");

		startButton.setLocation(35,35);

		startFrm.setTitle("Chess Game");
		startFrm.setSize(600,300);
		startFrm.setLayout(new FlowLayout());
		startFrm.setLocationRelativeTo(null);
		startFrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startFrm.setVisible(true);

		startFrm.add(startButton);
		startButton.addActionListener(new Action()); // Trigger an action if the button pressed

	}

	public static class Action implements ActionListener{

		public void actionPerformed (ActionEvent e) {
	
			startFrm.dispose(); // close the start frame before opening the main frame
	    		
			p1 = new BoardPanel(); // create BoardPanel to apply paintComponent.
			SidePanel p2 = new SidePanel(); // create SidePanel to apply paintComponent.

			// Creating the Frame and sets its properties.
			mainFrm = new JFrame();
			mainFrm.setTitle("Chess Game");
			mainFrm.setSize(1300,1000);
			mainFrm.setLocationRelativeTo(null);
			mainFrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			mainFrm.setVisible(true);
	
			mainFrm.getContentPane().add(p2, BorderLayout.EAST);
			mainFrm.getContentPane().add(p1, BorderLayout.CENTER);
	
		}
    }
}
