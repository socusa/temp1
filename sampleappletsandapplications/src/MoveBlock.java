import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JApplet;


public class MoveBlock extends JApplet {
	private int x;
	private int y;
	private int xOffSet;
	private int yOffSet;
	private Point point;
	private boolean dragging;
	
	public void init() {
		
	   addMouseListener(new MyMouseAdapter());
	   addMouseMotionListener(new MyMouseMotionAdapter());
	}
	
	public class MyMouseAdapter extends MouseAdapter {
		
	   public void mouseReleased(MouseEvent e) {
	      point = null;
	      
	      dragging = false;
	   }
		
	}

	public class MyMouseMotionAdapter extends MouseMotionAdapter {
	
	   public void mouseDragged(MouseEvent e) {
	      if (new Rectangle(x,y,20,20).contains(new Point(e.getX(),e.getY())) || dragging) {	   
	         if (point == null) {
	            xOffSet = e.getX() - x;
	            yOffSet = e.getY() - y;
	         
	            point = new Point(0,0);
	         }
	      
	         x = e.getX() - xOffSet;
	         y = e.getY() - yOffSet;
	         
	         dragging = true;
	      
	         repaint();		   
	      }
	   }
	}
	
	public void paint(Graphics g) {
	   super.paint(g);
	   
	   for (int counter=0;counter<4;counter++) {
	      Rectangle rectangle = new Rectangle(counter/2*getWidth()/2,counter%2*getHeight()/2,getWidth()/2,getHeight()/2 );
	      
	      Rectangle rectangle1 = new Rectangle(x,y,20,20);
	      
	      if (rectangle.contains(rectangle1)) {
	         g.setColor(Color.white);
	         
	         g.fillRect(rectangle.x,rectangle.y,rectangle.width,rectangle.height);
	      }
	   }
	   
	   g.setColor(Color.black);

	   g.fillRect(x,y,20,20);
	}

}
