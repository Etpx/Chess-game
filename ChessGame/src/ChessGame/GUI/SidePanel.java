package ChessGame.GUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SidePanel extends JPanel{

	private JTabbedPane tp = new JTabbedPane();
	
	private JPanel p1 = new JPanel();

	

    private final JTextField JLturn1 = new JTextField(" P2  Turn ");
    private final JTextField JLturn2 = new JTextField(" P1  Turn ");
    private final JTextField JLwhite = new JTextField("  White ");
    private final JTextField JLblack = new JTextField("  Black ");
   
    private final JLabel Screen1 = new JLabel();
    private final JLabel  Screen2 = new JLabel();
    
    public static JTextArea movementsRecordArea = new JTextArea(40,1); // this TextArea to store all the moments

    private JButton button = new JButton("NEW GAME ");
    private JButton button1 = new JButton("EXIT");

	
		SidePanel(){

			p1.setBackground(new Color(127, 80, 44));
			p1.setPreferredSize(new Dimension(1000, 200));

			
			tp.addTab("Settings", p1);
			tp.setPreferredSize(new Dimension(340, 960)); // to set the size of the JTabbedPane as the Panel
			tp.setMaximumSize(new Dimension(340,960 )); // 32767
			tp.setMinimumSize(new Dimension(340, 960));
			
			tp.setBackground(Color.BLACK);
			
			setMaximumSize(new Dimension(340,200 )); // 32767
			setMinimumSize(new Dimension(340, 100));
			setPreferredSize(new Dimension(340, 200));
			setBackground(new Color(252,67,13)); // red
			setVisible(true);
			
			setLayout(new BorderLayout());
			
			add(tp); // to add the JTabbedPane to the Panel


	        
	        JLturn1.setSize(60,25);
	        JLturn1.setLocation(20,10);
	        Screen1.setSize(100,25);
	        Screen1.setLocation(90,25);
	        
	        JLblack.setSize(60,25);
	        JLblack.setLocation(20,34);
	        
	        JLturn1.setEnabled(false);
	        JLturn1.setBackground(Color.ORANGE);
	        JLturn1.setDisabledTextColor(Color.BLACK);
	        JLturn1.setFont(new Font("Arial",Font.BOLD,12));
	        
	        JLblack.setEnabled(false);
	        JLblack.setBackground(new Color(176,130,9));
	        JLblack.setFont(new Font("Arial",Font.BOLD,12));
	        JLblack.setDisabledTextColor(Color.BLACK);
	        
	        JLturn2.setSize(60,25);
	        JLturn2.setLocation(20,254);
	        Screen2.setSize(100,25);
	        Screen2.setLocation(90,254);
	        JLwhite.setSize(60,25);
	        JLwhite.setLocation(20,230);
	        
	        JLturn2.setEnabled(false);
	        JLturn2.setBackground(Color.ORANGE);
	        JLturn2.setDisabledTextColor(Color.BLACK);
	        JLturn2.setFont(new Font("Arial",Font.BOLD,12));
	        
	        JLwhite.setEnabled(false);
	        JLwhite.setBackground(new Color(176,130,9));
	        JLwhite.setFont(new Font("Arial",Font.BOLD,12));
	        JLwhite.setDisabledTextColor(Color.BLACK);
	
	        movementsRecordArea.setEditable(false);
	        movementsRecordArea.setWrapStyleWord(true);
	        movementsRecordArea.setLineWrap(true);
	        movementsRecordArea.setSize(300,1500);
	        movementsRecordArea.setBackground(new Color(150, 101, 41));
	        movementsRecordArea.setForeground(Color.white);
	        
	        button1.setBackground(Color.yellow);
	        button1.setLocation(80,254);
	        
	        button1.addActionListener( new ActionListener() {// anonymous inner class
	        	   public void actionPerformed(ActionEvent e) {
	        			   ChessMain.mainFrm.dispose(); // if 'Exit' clicked, close the entire game 
	        			
	        	   }
	        }); 

	        button.setBackground(Color.yellow);
	        button.setLocation(80,254);
	        button.addActionListener(new ActionListener() { // anonymous inner class
	        	   public void actionPerformed(ActionEvent e) {

	        	   }
	        });

	        // add the components to the side panel
	        p1.add(JLturn1, BorderLayout.NORTH);
	        p1.add(JLblack, BorderLayout.NORTH);
	        p1.add(JLturn2, BorderLayout.NORTH);
	        p1.add(JLwhite, BorderLayout.NORTH);
	        p1.add(Screen1, BorderLayout.NORTH);
	        p1.add(Screen2, BorderLayout.NORTH);
	        p1.add(movementsRecordArea, BorderLayout.CENTER);
	        p1.add(button, BorderLayout.SOUTH);
	        p1.add(button1, BorderLayout.SOUTH);

		}





		
}
