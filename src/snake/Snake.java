package snake;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Snake implements ActionListener, KeyListener{
	
	public static Snake snake;
	public JFrame jframe;
	public RenderPanel renderPanel;
	
	
	public Timer timer = new Timer(20, this);
	
	public ArrayList<Point> snakeParts = new ArrayList<Point>();
	
	public int ticks = 0, direction =down, score, tailLength=10, time;
	public static final int up=0,down=1, left=2, right=3, scale=10;
	
	public Point head, cherry;
	public Random random;
	public boolean over=false, paused;
	public Dimension dim;
	
	public Snake() {
		dim = Toolkit.getDefaultToolkit().getScreenSize();
	
		jframe = new JFrame("Snake");
		jframe.setVisible(true);
		jframe.setSize(805, 700);
		jframe.setResizable(false);
		jframe.setLocation(dim.width/2-jframe.getWidth()/2, (int) (dim.getHeight()/2-jframe.getHeight()/2));
		jframe.add(renderPanel=new RenderPanel());
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.addKeyListener(this);
		startGame();
	}
		public void startGame() {
			
			over = false;
			paused = false;
			time=0;
			score=0;
			tailLength=1;
			ticks=0;
			direction=down;
			head=new Point(0,-1);
			random=new Random();
			snakeParts.clear();
			cherry=new Point(random.nextInt(79),random.nextInt(66));
			
			
			for(int i=0;i < tailLength;i++) {
				snakeParts.add(new Point(head.x,head.y));
			}
			timer.start();
		
		}
		
		
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		renderPanel.repaint();
		ticks++;
		if(ticks%4==0 && head !=null && !over && !paused) {
			time++;
			snakeParts.add(new Point(head.x, head.y));
			
			
			if (direction==up) {
				if(head.y-1>=0 && noTailAt(head.x, head.y-1)) {
					head = new Point(head.x, head.y-1);
				}else
					over=true;
			}
		
				
			if (direction==down) {
				if(head.y+1<67 && noTailAt(head.x, head.y+1)) {
					head = new Point(head.x, head.y+1);
				}else
					over=true;
			}
				
			if (direction==left) {
				if(head.x-1>=0 && noTailAt(head.x-1, head.y)) {
					head = new Point(head.x-1, head.y);
				}else
					over=true;
			}
				
			if (direction==right)
				if(head.x+1<80 && noTailAt(head.x+1, head.y)) {
					head = new Point(head.x+1, head.y);
				}else
					over=true;
			
			if(snakeParts.size() > tailLength)
				snakeParts.remove(0);
			
		
			if (cherry!=null) {
				if(head.equals(cherry)) {
					score+=10;
					tailLength++;
					cherry.setLocation(random.nextInt(79),random.nextInt(66));
				}
			}
		}
	}
	
	public boolean noTailAt(int x, int y) {
		for (Point point: snakeParts) {
			if(point.equals(new Point(x,y)))
				return false;
		}
		return true;
	}
	
	
	public static void main(String[] args) {
		snake=new Snake();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int i = e.getKeyCode();
		if (i==KeyEvent.VK_A && direction!= right)
			direction = left;
		if (i==KeyEvent.VK_D && direction!= left)
			direction = right;
		if (i==KeyEvent.VK_W && direction!= down)
			direction = up;
		if (i==KeyEvent.VK_S && direction!= up)
			direction = down;
		if(i==KeyEvent.VK_SPACE) {
			if(over)
				startGame();
			else
				paused=!paused;
		}
			
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
