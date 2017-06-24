import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
public class Gameplay extends JPanel implements KeyListener, ActionListener{
	private int[] snakeXLength = new int[750];
	private int[] snakeYLength = new int[750];
		
	private int[] fruitX = {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,
			500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};//34 elements
	private int[] fruitY = {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,
			500,525,550,575,600,625};//23 elements
	
	private ImageIcon fruitIcon;
	private Random ran = new Random();
	private int xPos = ran.nextInt(34);
	private int yPos = ran.nextInt(23);
	
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	
	private int lengthOfSnake = 3;
	private int moves = 0;
	private int score = 0;
	
	private ImageIcon rightMouth;
	private ImageIcon leftMouth;
	private ImageIcon upMouth;
	private ImageIcon downMouth;
	
	private Timer timer;
	private int delay = 100;
	private ImageIcon snakeImage;
	private ImageIcon titleImage;
	
	
	
	
	public Gameplay(){
		
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay,this);
		timer.start();
	}
	
	public void paint(Graphics g){
		
		if(moves == 0){
			snakeXLength[0]=100;
			snakeXLength[1]=75;
			snakeXLength[2]=50;
			
			snakeYLength[0]=100;
			snakeYLength[1]=100;
			snakeYLength[2]=100;
		}
		
		
		//border title
		g.setColor(Color.white);
		g.drawRect(24, 10, 851, 55);
		
		
		//title image
		titleImage = new ImageIcon("snaketitle.jpg");
		titleImage.paintIcon(this, g, 25, 11);
		
		//border gameplay
		g.setColor(Color.white);
		g.drawRect(24, 74, 851, 577);
		
		//background gameplay
		g.setColor(Color.black);
		g.fillRect(25, 75, 850, 575);
		
		//score
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial",Font.PLAIN,14));
		g.drawString("Score: " + score, 780, 30);
		
		//length of snake
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial",Font.PLAIN,14));
		g.drawString("Length: " + lengthOfSnake, 780, 50);
		
		rightMouth = new ImageIcon("rightmouth.png");
		rightMouth.paintIcon(this, g, snakeXLength[0], snakeYLength[0]);
		
		for(int i=0;i<lengthOfSnake;i++){
			if(i == 0 && right){
				rightMouth = new ImageIcon("rightmouth.png");
				rightMouth.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);
			}
			if(i == 0 && left){
				leftMouth = new ImageIcon("leftmouth.png");
				leftMouth.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);
			}
			if(i == 0 && down){
				downMouth = new ImageIcon("downmouth.png");
				downMouth.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);
			}
			if(i == 0 && up){
				upMouth = new ImageIcon("upmouth.png");
				upMouth.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);
			}
			
			if(i != 0){
				snakeImage = new ImageIcon("snakeimage.png");
				snakeImage.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);
			}
		}
		
		fruitIcon = new ImageIcon("enemy.png");
		
		if(fruitX[xPos] == snakeXLength[0] && fruitY[yPos] == snakeYLength[0]){
			score++; 
			lengthOfSnake++;
			xPos = ran.nextInt(34);
			yPos = ran.nextInt(23); 
		}
		
		fruitIcon.paintIcon(this, g, fruitX[xPos], fruitY[yPos]);
		
		
		
		//collision with body
		for(int b=1;b< lengthOfSnake; b++){
			if(snakeXLength[b] == snakeXLength[0] && snakeYLength[b] == snakeYLength[0]){
				right=false;left=false;up=false;down=false;
				g.setColor(Color.white);
				g.setFont(new Font("arial",Font.BOLD,50));
				g.drawString("Game over", 300, 300);
				
				g.setFont(new Font("arial",Font.LAYOUT_LEFT_TO_RIGHT,20));
				g.drawString("Press SPACE to restart", 330, 340);
			}
		}
		
		
		g.dispose();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		
		if(right){
			for(int r=lengthOfSnake - 1; r >= 0; r--){
				snakeYLength[r+1] = snakeYLength[r];
			}
			
			for(int r = lengthOfSnake; r >=0; r--){
				if(r==0){
					snakeXLength[r] +=25;
				}
				else{
					snakeXLength[r] = snakeXLength[r-1];
				}
				if(snakeXLength[r] >850){
					snakeXLength[r] = 25;
				}
				
			}
			repaint();
		}
		
		
		if(left){
			for(int r=lengthOfSnake - 1; r >= 0; r--){
				snakeYLength[r+1] = snakeYLength[r];
			}
			
			for(int r = lengthOfSnake; r >=0; r--){
				if(r==0){
					snakeXLength[r] -=25;
				}
				else{
					snakeXLength[r] = snakeXLength[r-1];
				}
				if(snakeXLength[r] < 25){
					snakeXLength[r] = 850;
				}
				
			}
			repaint();
		}
		
		
		if(up){
			for(int r=lengthOfSnake - 1; r >= 0; r--){
				snakeXLength[r+1] = snakeXLength[r];
			}
			
			for(int r = lengthOfSnake; r >=0; r--){
				if(r==0){
					snakeYLength[r] -=25;
				}
				else{
					snakeYLength[r] = snakeYLength[r-1];
				}
				if(snakeYLength[r] <  75){
					snakeYLength[r] = 625;
				}
				
			}
			repaint();
			
		}
		
		
		if(down){
			for(int r=lengthOfSnake - 1; r >= 0; r--){
				snakeXLength[r+1] = snakeXLength[r];
			}
			
			for(int r = lengthOfSnake; r >=0; r--){
				if(r==0){
					snakeYLength[r] +=25;
				}
				else{
					snakeYLength[r] = snakeYLength[r-1];
				}
				if(snakeYLength[r] >  625){
					snakeYLength[r] = 75;
				}
				
			}
			repaint();
	
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			moves=0;
			score=0;
			lengthOfSnake=3;
			repaint();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			moves++;
			right = true;up=false;down=false;
			if(left){
				right=false;
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			moves++;
			left = true;up=false;down=false;
			if(right){
				left=false;
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP){
			moves++;
			up = true;left=false;right=false;
			if(down){
				up=false;
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN){
			moves++;
			down = true;left=false;right=false;
			if(up){
				down=false;
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	
}
