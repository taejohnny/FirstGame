import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

//The screen to reflect the end-of-game menu screen
public class EndScreen extends JPanel{
	
	JPanel overallPanel;
	JPanel centerPanel;
	JLabel endLabel, endLabel2;
	JButton replay;
	
	public EndScreen(){
		this.setBackground(Color.YELLOW);
		overallPanel = new JPanel(new BorderLayout());
		overallPanel.setPreferredSize(new Dimension(1000, 600));
		centerPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		Font titleFont = new Font("Jokerman", Font.BOLD, 35);
		Font subFont = new Font("Jokerman", Font.BOLD, 20);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		endLabel = new JLabel("Congratulations");
		endLabel.setFont(titleFont);
		centerPanel.add(endLabel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		endLabel2 = new JLabel("You have finished the game");
		endLabel2.setFont(subFont);
		centerPanel.add(endLabel2, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		//If the PLAY AGAIN button is pressed, the game screen replaces the end game screen again
		replay = new JButton("PLAY AGAIN");
		replay.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GameScreen newGame = new GameScreen();
				MainScreen.getMainScreen().getContentPane().removeAll();
				MainScreen.getMainScreen().getContentPane().add(newGame);
				MainScreen.getMainScreen().repaint();
				MainScreen.getMainScreen().validate();
				newGame.requestFocusInWindow();
			}
		});
		replay.setPreferredSize(new Dimension(150, 50));
		centerPanel.add(replay, gbc);
		centerPanel.setBackground(Color.YELLOW);
		
		overallPanel.add(centerPanel, BorderLayout.CENTER);
		this.add(overallPanel);
	}
	
}
