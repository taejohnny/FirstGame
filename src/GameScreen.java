
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.Random;

import javax.annotation.Resources;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

//The screen to reflect the game menu screen
public class GameScreen extends JPanel implements ActionListener, KeyListener{
	
	Random random = new Random();
	Timer t = new Timer(3, this);
	private BufferedImage cat, jungle, coin;
	private AudioClip song;
	private URL songPath;
	private int x = 0, y = 0, velX = 0, velY = 0;
	private int coinX, coinY;
	private boolean coinCaptured = false;
	private JLabel gameScore;
	private int score;
	
	public GameScreen(){
		t.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		//Attempts to find and load the images necessary for the program
		try{
			coin = ImageIO.read(getClass().getResource("/resources/coin.png"));
		}catch(Exception e){
			System.out.println("Exception in loading image" + e.toString());
		}
		try{
			jungle = ImageIO.read(getClass().getResource("/resources/jungle.jpg"));
		}catch(Exception e){
			System.out.println("Exception in loading image " + e.toString());
		}
		try{
			cat = ImageIO.read(getClass().getResource("/resources/cat.png"));
		}catch(Exception e){
			System.out.println("Exception in loading image " + e.toString());
		}
		//Loading the audio file necessary for the game 
		try{
			songPath = GameScreen.class.getResource("/resources/RainyWayBackHome.wav");
			song = Applet.newAudioClip(songPath);
		}catch(Exception e){
			System.out.println("Exception in loading sound " + e.toString());
		}
		song.loop();
		
		
		//Random generation of an x and y value to be used as coordinates
		coinX = genRandomX();
		coinY = genRandomY();
		
		JPanel northPanel = new JPanel();
		gameScore = new JLabel("Score: " + score);
		gameScore.setFont(new Font("Jokerman", Font.BOLD, 15));
		gameScore.setForeground(Color.YELLOW);
		northPanel.add(gameScore);
		northPanel.setBackground(Color.BLACK);
		this.setLayout(new BorderLayout());
		this.add(northPanel, BorderLayout.NORTH);
	}
	
	//Generates a random x value that is an integer
	public int genRandomX(){
		int xValue = random.nextInt(946) - 9;
		return xValue;
	}
	
	//Generates a random y value that is an integer
	public int genRandomY(){
		int yValue = random.nextInt(473) + 32;
		return yValue;
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(jungle, 0, 0, null);
		g.drawImage(cat, x, y, null);
		
		//If the cat is colliding with the coin -> increase the score by 10 and set coinCaptured to true
		if((coinX  <= x + 34 && coinX + 50 >= x + 34) && (coinY <= y + 37 && coinY + 50 >= y + 37)){
			coinCaptured = true;
			score = score + 10;
			//If the score has reached 100, the switchScreens method is invoked
			if(score == 100){
				switchScreens();
			}
			gameScore.setText("Score: " + score);
		}
		//If coinCaptured is set to true earlier in the paintComponent method, generate new random x and y variables and move the coin to that point
		if(coinCaptured == true){
			coinX = genRandomX();
			coinY = genRandomY();
			g.drawImage(coin, coinX, coinY, null);
			coinCaptured = false;
		}else{
			//If the cat has not made contact with the coin, the coin stays in place
			g.drawImage(coin, coinX, coinY, null);
		}
	}
	
	//Replaces the game screen with the end game screen
	private void switchScreens() {
		song.stop();
		EndScreen donePanel = new EndScreen();
		MainScreen.getMainScreen().getContentPane().removeAll();
		MainScreen.getMainScreen().getContentPane().add(donePanel);
		MainScreen.getMainScreen().repaint();
		MainScreen.getMainScreen().validate();
		donePanel.requestFocusInWindow();
	}
	
	//Makes sure the cat cannot leave the bounds of the JFrame, and repaints the cat to reflect the movement of the changing x and y values
	public void actionPerformed(ActionEvent e){
		if(x < -9){
			velX = 0;
			x = -9;
		}
		if(x > 937){
			velX = 0;
			x = 937;
		}
		if(y < 32){
			velY = 0;
			y = 32;
		}
		if(y > 505){
			velY = 0;
			y = 505;
		}
		x = x + velX;
		y = y + velY;
		repaint();
	}
	
	//If a certain arrow key is pressed, the x velocity and y velocity is changed to match the corresponding key press
	public void keyPressed(KeyEvent e){
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_UP){
			velY = -2;
			velX = 0;
		}
		if(code == KeyEvent.VK_DOWN){
			velY = 2;
			velX = 0;
		}
		if(code == KeyEvent.VK_RIGHT){
			velY = 0;
			velX = 2;
		}
		if(code == KeyEvent.VK_LEFT){
			velY = 0;
			velX = -2;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
