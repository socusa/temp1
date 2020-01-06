import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JApplet;
import javax.swing.JPanel;

public class DragPanel extends JApplet {
	public MyPanel myPanel;
	public MyListener listener;
	public int offSetX;
	public int offSetY;
	public Point point;
	public int currentX;
	public int currentY;
	
	public class MyPanel extends JPanel {
		
		public MyPanel(int width,
				       int height,
				       int xLocation,
				       int yLocation) {
			setSize(width,height);
			setLocation(xLocation,yLocation);
			
			setBackground(Color.black);
		}
		
	}
	
	public class MyListener implements MouseListener,MouseMotionListener {
	
		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub

			MyPanel source = (MyPanel)arg0.getSource();
			
            Point upperLeft = source.getLocationOnScreen();
			
			Point location = new Point(arg0.getXOnScreen(),arg0.getYOnScreen());

			
			source.setLocation(location.x-offSetX,location.y-offSetY);
			
			
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub

		   	
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

			MyPanel source = (MyPanel)arg0.getSource();
			
            Point upperLeft = source.getLocationOnScreen();
			
			Point location = new Point(arg0.getXOnScreen(),arg0.getYOnScreen());


			if (point == null) {
				point = new Point();
					
				offSetX = location.x - upperLeft.x;
				offSetY = location.y - upperLeft.y;
			}			
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
			point = null;
			
		}


			
	}
	
	public void init() {
		setLayout(null);
		
		myPanel = new MyPanel(100,100,0,0);
		
		listener = new MyListener();
		
		myPanel.addMouseListener(listener);
		
		myPanel.addMouseMotionListener(listener);
		
	
		add(myPanel);
		
	}

}
