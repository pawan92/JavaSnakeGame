package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RenderPanel extends JPanel{
	
	public static Color green = new Color(1666073);
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(green);
		g.fillRect(0, 0, 800, 700);
		Snake snake=Snake.snake;
		g.setColor(Color.BLUE);
		for(Point point : snake.snakeParts) {
			//g.setColor(Color.RED);
			g.fillRect(point.x*Snake.scale, point.y*Snake.scale, Snake.scale, Snake.scale);
			
		}
		g.fillRect(snake.head.x*Snake.scale, snake.head.y*Snake.scale, Snake.scale, Snake.scale);
		
		g.setColor(Color.RED);
		g.fillRect(snake.cherry.x*Snake.scale, snake.cherry.y*Snake.scale, Snake.scale, Snake.scale);
		String string = "Score: " + snake.score+ ", Length: " + snake.tailLength + "Time: "+ snake.time / 20;
		g.setColor(Color.white);
		g.drawString(string, (int) (snake.dim.getWidth()/2-string.length()*2.5f), 10);
		
	}

}
