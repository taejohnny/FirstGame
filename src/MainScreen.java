import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

//The screen to reflect the main menu/start-up screen
public class MainScreen extends JFrame implements MouseListener{
	
	private static MainScreen mScreen;
	private JPanel mainPanel;
	private JLabel title, instruct, instruct2;
	
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				mScreen = new MainScreen();
			}
		});
	}
	
	public static MainScreen getMainScreen(){
		return mScreen;
	}
	
	//Attributes of the JFrame
	public MainScreen(){
		createView();
		
		setTitle("Coin Collector");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setSize(1000, 600);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	//Creates the components of the main menu and makes them visible
	private void createView() {
		mainPanel = new JPanel(new BorderLayout());
		getContentPane().add(mainPanel);
		
		JPanel centerPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		Font titleFont = new Font("Jokerman", Font.BOLD, 35);
		Font instructFont = new Font("Garmond", Font.BOLD, 18);
		
		c.gridx = 0;
		c.gridy = 0;
		title = new JLabel("COIN COLLECTOR: THE GAME");
		title.setFont(titleFont);
		centerPanel.add(title, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(0, 0, 30, 0);
		instruct = new JLabel("Collect the coins until you reach 100 points");
		instruct.setFont(instructFont);
		centerPanel.add(instruct, c);
		
		c.gridx = 0;
		c.gridy = 2;
		instruct2 = new JLabel("Click to start");
		instruct2.setFont(instructFont);
		centerPanel.add(instruct2, c);
		
		centerPanel.setBackground(Color.YELLOW);
		
		mainPanel.addMouseListener(this);
		mainPanel.add(centerPanel);
	}

	//When mouse is clicked, the game screen replaces the main menu screen
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		GameScreen coinPanel = new GameScreen();
		mScreen.getContentPane().remove(mainPanel);
		mScreen.getContentPane().add(coinPanel);
		mScreen.repaint();
		mScreen.validate();
		coinPanel.requestFocusInWindow();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
