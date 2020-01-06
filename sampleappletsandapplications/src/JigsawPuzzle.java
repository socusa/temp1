import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class JigsawPuzzle extends JApplet {
	private Image image;
	private Image[] images;
	private BufferedImage newImage;
	private ImagePanel imagePanel;
	private PuzzlePanel puzzlePanel;
	private PuzzlePiecesPanel puzzlePiecesPanel;
	private ControlPanel controlPanel;
	private JComboBox<Integer> numberOfPieces;
	private JButton loadImage;
	private JButton breakUpImage;
	private MyListener listener;
	private String fileName;
	private PuzzlePiecePanel[] puzzlePieces;
	private boolean[] bordersDisplayed;
	private PuzzlePieceLocation[] puzzlePieceLocations;
	private int rows;
	private JButton lock;
	private JButton unLock;
	private AudioClip sound;
	private AudioClip incorrect;
	private AudioClip correct;
	
	public class ControlPanel extends JPanel {
		
		public ControlPanel(int width,int height) {
			setSize(width,height);
			
			add(new JLabel("Number of Pieces"));
			
			add(numberOfPieces = new JComboBox<Integer>(new Integer[]{4,9,16,25}));
			
			add(loadImage = new JButton("Load Image"));
			
			add(breakUpImage = new JButton("Break Up Image"));
			
			add(lock = new JButton("Lock"));
			
			add(unLock = new JButton("Unlock"));
			
			loadImage.addActionListener(listener);
			
			breakUpImage.addActionListener(listener);
			
			lock.addActionListener(listener);
			
			unLock.addActionListener(listener);
			
		}
		
	}
	
	public class PuzzlePiecePanel extends JPanel implements MouseListener,MouseMotionListener {
		private Image image;
		private int width;
		private int height;
		private boolean borderDisplayed;
		private Point point;
		private int offsetX;
		private int offsetY;
		private int index;
		
		public PuzzlePiecePanel(int width,
				                int height,
				                Image image) {
			setSize(width,height);
			
			this.width = width;
			this.height = height;
			
			this.image = image;
			
			addMouseListener(this);
			
			addMouseMotionListener(this);
		}
		
		public int getWidth() {
			return(width);
		}
		
		public int getHeight() {
			return(height);
		}
		
		public Image getImage() {
			return(image);
		}
		
		public boolean getBorderDisplayed() {
			return(borderDisplayed);
		}
		
		public void setIndex(int index) {
			this.index = index;
		}
		
		public int getIndex() {
			return(index);
		}

		public Rectangle getMainArea() {
			Point location = getLocationOnScreen();
			
			return(new Rectangle(location.x + 10,
					             location.y + 10,
					             width-20,
					             height-20));
		}
		
		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub

			/*
			 
			for (int counter=0;counter<puzzlePieces.length;counter++) {
				if (puzzlePieces[counter] != this)
					puzzlePieces[counter].setBorderDisplayed(false);
				else
					puzzlePieces[counter].setBorderDisplayed(true);
			}
			
			
			if (point == null) {
				point = new Point();
				
				int x = arg0.getXOnScreen();
				int y = arg0.getYOnScreen();
				
				int upperLeftX = arg0.getLocationOnScreen().x;
				int upperLeftY = arg0.getLocationOnScreen().y;
				
				offsetX = x - upperLeftX;
				offsetY = y - upperLeftY;
			}
			
			*/
			
			
			
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

			for (int counter=0;counter<puzzlePieces.length;counter++) {
				if (puzzlePieces[counter] != this)
					puzzlePieces[counter].setBorderDisplayed(false);
				else
					puzzlePieces[counter].setBorderDisplayed(true);
			}
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		public void setBorderDisplayed(boolean borderDisplayed) {
			this.borderDisplayed = borderDisplayed;
			
			displayBorder();
		}
		
		public void displayBorder() {
			if (borderDisplayed)
				setBorder(new LineBorder(new Color(200,0,0),1));
			else
				setBorder(null);
			
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			if (image != null)
				g.drawImage(image, 0, 0, width,height,null);
			
		}

		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
			int x = arg0.getXOnScreen();
			int y = arg0.getYOnScreen();
			
			setLocation(x,y - 50);
			
			JigsawPuzzle.this.repaint();
			
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		
	}
	
	public class PuzzlePieceLocation extends JPanel implements MouseListener {
		private boolean borderDisplayed;
		private Image image;
		private int width;
		private int height;
		private int xlocation;
		private int ylocation;
		private int index;
		private int candidate;
		
		public PuzzlePieceLocation(int width,
				                   int height,
				                   int xlocation,
				                   int ylocation) {
			setSize(width,height);
			setLocation(xlocation,ylocation);
			
			setBackground(Color.black);
			
			this.width = width;
			
			this.height = height;
			
			addMouseListener(this);
		}
		
		public void setImage(Image image) {
			this.image = image;
			
			repaint();
		}
		
		public Image getImage() {
			return(image);
		}
		
		public boolean getBorderDisplayed() {
			return(borderDisplayed);
		}
		
		public void setIndex(int index) {
			this.index = index;
		}
		
		public int getIndex() {
			return(index);
		}
		
		public void setCandidate(int candidate) {
			this.candidate = candidate;
		}
		
		public int getCandidate() {
			return(candidate);
		}
		
		public Rectangle getMainArea() {
			return(new Rectangle(xlocation + 10,
					             ylocation + 10,
					             width-20,
					             height-20));
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

			for (int counter=0;counter<puzzlePieces.length;counter++) {
				if (puzzlePieceLocations[counter] != this)
					puzzlePieceLocations[counter].setBorderDisplayed(false);
				else
					puzzlePieceLocations[counter].setBorderDisplayed(true);
			}
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		

		public void setBorderDisplayed(boolean borderDisplayed) {
			this.borderDisplayed = borderDisplayed;
			
			displayBorder();
		}
		
		public void displayBorder() {
			if (borderDisplayed)
				setBorder(new LineBorder(new Color(255,255,255),1));
			else
				setBorder(null);
			
		}
		
		public void paint(Graphics g) {
			super.paint(g);
			
			if (image != null)
				g.drawImage(image, 0, 0, width,height,null);
		}
		
	}
	
	
	private PuzzlePiecePanel[] getPuzzlePieces(Image image,
			                                   int numberOfPieces) {
		BufferedImage image1 = new BufferedImage(image.getWidth(null), 
				                                 image.getHeight(null),
		                                         BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g = image1.createGraphics();
		    
		g.drawImage(image, 0, 0, null);
		
		g.dispose();
		    
		int imageWidth = image1.getWidth(null);
		int imageHeight = image1.getHeight(null);

		PuzzlePiecePanel[] panels = new PuzzlePiecePanel[numberOfPieces];
		
		rows = (int)(Math.sqrt(numberOfPieces));
		
		int width = imageWidth/rows;
		int height = imageHeight/rows;
		
		BufferedImage[] images = new BufferedImage[panels.length];
		
		for (int counter=0;counter<panels.length;counter++) {
			images[counter] = image1.getSubimage(width*(counter % rows),
					                             height*(counter / rows),
					                             width,
					                             height);
			
			panels[counter] = new PuzzlePiecePanel(width,height,images[counter]);	
		}
		
		return(panels);
	}
	
	public class ImagePanel extends JPanel {
		
		public ImagePanel(int width,int height) {
			setPreferredSize(new Dimension(width,height));
			
			setBackground(Color.black);
			
			
			
			
			repaint();
		}
		
	}
	
	public class PuzzlePanel extends JPanel {
		
		public PuzzlePanel(int width,int height) {
			setPreferredSize(new Dimension(width,height));
			
			setBackground(Color.green);
			
		}
		
	}
	
	public class PuzzlePiecesPanel extends JPanel {
		
		public PuzzlePiecesPanel(int width,int height) {
			setPreferredSize(new Dimension(width,height));
			
			setLayout(null);
			
		}
		
	}

	public void paint(Graphics g) {
		super.paint(g);
		
		controlPanel.repaint();
		
		if (image != null)
			g.drawImage(image,0, 100, image.getWidth(null), image.getHeight(null), null);		
	}
	
	public class MyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub

			if (arg0.getSource() == loadImage) {
				String location = getCodeBase() + "imagesjigsawpuzzle/";
				
				JFileChooser chooser = new JFileChooser(location.substring(6));
				
				int returnValue = chooser.showOpenDialog(JigsawPuzzle.this);
				
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					fileName = chooser.getSelectedFile().getName();
					
					image = getImage(getCodeBase(),"imagesjigsawpuzzle/" + fileName).getScaledInstance(200, 200, Image.SCALE_DEFAULT);
					
					MediaTracker tracker = new MediaTracker(JigsawPuzzle.this);
					
					tracker.addImage(image, 1);
					
					try {
					   tracker.waitForAll();
					} catch (InterruptedException ie) {
						System.out.println(ie);
					}
					
				}	
			} else if (arg0.getSource() == breakUpImage) {
				puzzlePieces = getPuzzlePieces(image,Integer.parseInt(numberOfPieces.getSelectedItem().toString()));
				
				puzzlePieceLocations = new PuzzlePieceLocation[puzzlePieces.length];
				
				int startx = 0;
				
				int starty = 400;
				
				int xLocation = 400;
				
				int yLocation = 100;
				
				for (int counter=0;counter<puzzlePieces.length;counter++) {
					if (startx >= getHeight() - puzzlePieces[counter].getWidth() - 10) {
						starty += puzzlePieces[counter].getHeight() + 10;
						
						startx = 0;
					}
					
					puzzlePieces[counter].setLocation(startx,starty);
					
					puzzlePieces[counter].setIndex(counter);
					
					puzzlePieceLocations[counter] = new PuzzlePieceLocation(puzzlePieces[counter].getWidth(),
							                                                puzzlePieces[counter].getHeight(),
							                                                xLocation,
							                                                yLocation);
					
					puzzlePieceLocations[counter].setIndex(counter);

					if (counter > 0 && (counter + 1) % rows == 0) {
						xLocation = 400;
						
						yLocation += puzzlePieces[counter].getHeight();
					} else {
						xLocation += puzzlePieces[counter].getWidth();
					}
					
					startx += puzzlePieces[counter].getWidth() + 10;
											
					add(puzzlePieces[counter]);
					
					add(puzzlePieceLocations[counter]);
					
					
				}
				
				sound.play();
				
				repaint();	
				
				revalidate();
			} else if (arg0.getSource() == lock) {
				Image image = null;
				
				for (int counter=0;counter<puzzlePieces.length;counter++)
					if (puzzlePieces[counter].getBorderDisplayed())
						image = puzzlePieces[counter].getImage();
				
				for (int counter=0;counter<puzzlePieceLocations.length;counter++)
					if (puzzlePieceLocations[counter].getBorderDisplayed()) {
						puzzlePieceLocations[counter].setImage(image);
						
						puzzlePieceLocations[counter].setCandidate(puzzlePieces[counter].getIndex());
						
						puzzlePieces[counter].setVisible(false);
					}
				
				boolean allLocked = true;
				
				for (int counter=0;counter<puzzlePieceLocations.length;counter++)
					if (puzzlePieceLocations[counter].getImage() == null)
						allLocked = false;
				
				System.out.println(allLocked);
				
				if (allLocked) {
					boolean correct = true;
					
					for (int counter=0;counter<puzzlePieceLocations.length;counter++) {
						System.out.println("puzzlePieceLocations[" + counter + "].getCandidate() is " + puzzlePieceLocations[counter].getCandidate());

						System.out.println("puzzlePieceLocations[" + counter + "].getIndex() is " + puzzlePieceLocations[counter].getIndex());
						
						if (puzzlePieceLocations[counter].getCandidate() != puzzlePieceLocations[counter].getIndex())
							correct = false;
					}
					
					if (!correct)
						incorrect.play();
					else
						JigsawPuzzle.this.correct.play();
				}
			} else if (arg0.getSource() == unLock) {
				for (int counter=0;counter<puzzlePieceLocations.length;counter++)
					if (puzzlePieceLocations[counter].getBorderDisplayed()) {
					    puzzlePieceLocations[counter].setImage(null);	
						
						puzzlePieces[counter].setVisible(true);
					}
			}
			
		}
		
	}
	
	public void init() {
		setLayout(null);
		
		listener = new MyListener();
		
		controlPanel = new ControlPanel(getWidth(),50);
		
		sound = getAudioClip(getCodeBase(),"soundsjigsawpuzzle/glass_break_02.wav");

		incorrect = getAudioClip(getCodeBase(),"soundsjigsawpuzzle/ohno2.wav");

		correct = getAudioClip(getCodeBase(),"soundsjigsawpuzzle/youwon.wav");
		
		controlPanel.setLocation(0,0);

		getContentPane().setBackground(Color.blue);
		
//		imagePanel = new ImagePanel(getWidth()/2,getHeight()/2);
		
//		puzzlePanel = new PuzzlePanel(getWidth()/2-10,getHeight()/2);
		
//		puzzlePiecesPanel = new PuzzlePiecesPanel(getWidth(),getHeight()/2-50);
		
		add(controlPanel);
		
//		add(imagePanel);
		
//		add(puzzlePanel);
		
//		add(puzzlePiecesPanel);
		
	}
	
	

}
