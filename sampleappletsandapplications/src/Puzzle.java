import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JPanel;


public class Puzzle extends JApplet {
   private MyButton[][] buttons;
   private Color[][] colors;
   private JButton start;
   private PuzzlePanel puzzlePanel;
   private WordsPanel wordsPanel;
   private ArrayList<MyButton> candidate;
 
   public void init() {
      buttons = new MyButton[20][20];
      
      setLayout(new GridLayout(1,2));
      
      add(puzzlePanel = new PuzzlePanel(100,100));
      add(wordsPanel = new WordsPanel(100,100));
   }
   
   public class PuzzlePanel extends JPanel {

      public PuzzlePanel(int width,
    		             int height) {
         setPreferredSize(new Dimension(width,height));
         
         setLayout(new GridLayout(20,20));
         
         for (int counter=0;counter<buttons.length;counter++)
             for (int counter1=0;counter1<buttons[0].length;counter1++) {
            	 buttons[counter][counter1] = new MyButton(" ");
            	 
            	 Insets insets = buttons[counter][counter1].getInsets();
            	 
            	 insets.left = 0;
            	 insets.right = 0;
            	 
            	 buttons[counter][counter1].setMargin(insets);
            	 
            	 add(buttons[counter][counter1]);
            	 
            	 buttons[counter][counter1].addMouseListener(new MyMouseAdapter());
            	 
            	 buttons[counter][counter1].addMouseMotionListener(new MyMouseMotionAdapter());   	 
             }
      }
      
      public void placeWord(String word) {
          int length = word.length();

          int row = ((int)(100*Math.random()))/10;
          int column = ((int)(100*Math.random()))/10;

          int temp = (int)(100*Math.random());

          String direction = (temp <= 50) ? "horizontal" : "vertical";

          while (!willFit(row,column,direction,word)) {
             row = ((int)(100*Math.random()))/5;
             column = ((int)(100*Math.random()))/5;

             temp = (int)(100*Math.random());

             direction = (temp <= 50) ? "horizontal" : "vertical";
          }

          setWordAt(row,column,direction,word);
       }	   
      
       private boolean willFit(int row,
                               int column,
                               String direction,
                               String word) {
          String temp = wordAt(row,column,direction,word.length());

          boolean willfit = true;

          if (!temp.trim().equals("-")) {
             for (int i=0;i<word.length();i++)
                if (!temp.substring(i,i+1).equals(" ") &&
                    !temp.substring(i,i+1).equals(word.substring(i,i+1)))
                   willfit = false;
          } else 
             willfit = false;

          return(willfit);
      }
       
       private String wordAt(int row,
                             int column,
                             String direction,
                             int number) {
         MyButton button = buttons[row][column];

         String word = "";

         if (direction.equals("horizontal")) {
            if (column + number >= 20)
               return("-");
            else {      
               for (int i=column;i<column+number;i++)
                  word += buttons[row][i].getText();
            }
         } else {
            if (row + number >= 20)
               return("-");
            else {      
               for (int i=row;i<row+number;i++)
                  word += buttons[i][column].getText();
            }
         }

         return(word); 
      }
     
       private void setWordAt(int row,
                              int column,
                              String direction,
                              String word) {
          Color highlightColor = Color.red;

          if (direction.equals("horizontal")) {
             for (int i=column;i<column+word.length();i++) {
                JButton block = buttons[row][i];

                block.setText(word.substring(i-column,i-column+1));
             } 
         } else {
             for (int i=row;i<row+word.length();i++) {
                JButton block = buttons[i][column];

                block.setText(word.substring(i-row,i-row+1));
            } 
         }
      }
       
      public void clearButtons() {
         for (int counter=0;counter<buttons.length;counter++)
            for (int counter1=0;counter1<buttons[0].length;counter1++) {
               buttons[counter][counter1].setText(" ");
               
               buttons[counter][counter1].setColor(Color.black);
               
               buttons[counter][counter1].setBackground(new Color(204,204,204));
            }
      }
      
      public void fillInLetters() {
    	 for (int counter=0;counter<buttons.length;counter++)
             for (int counter1=0;counter1<buttons[0].length;counter1++)
                if (buttons[counter][counter1].getText().equals(" "))
                   buttons[counter][counter1].setText(String.valueOf((char)(65 + (int)(26*Math.random()))));
      }
   
   }
   
   public class MyButton extends JButton {
      String text;
      Color color;
      
      public MyButton(String text) {
    	 super.setText(""); 
    	  
         this.text = text;
         
         color = Color.BLACK;
      }
      
      public void setColor(Color color) {
         this.color = color;
      }
      
      public void setText(String text) {
         this.text = text;
      }
      
      public String getText() {
         return(text);
      }
      
      public void paintComponent(Graphics g) {
         super.paintComponent(g);
         
         g.setColor(color);
  	   
  	     FontMetrics metrics = g.getFontMetrics();
  	   
  	     int width = metrics.stringWidth(text);
  	   
  	     int height = metrics.getHeight();
  	   
  	     g.drawString(text,getWidth()/2-width/2,getHeight()/2+height/4);
      }   
   }
   
   public class WordsPanel extends JPanel implements ActionListener {
      private ArrayList<String> words;
      private ArrayList<Font> fonts;
      
      public ArrayList<String> theWords() {
         return(words);
      }
      
      public void setFont(String word) {
         for (int counter=0;counter<words.size();counter++)
            if (words.get(counter).equalsIgnoreCase(word)) {
               Font font = fonts.get(counter);
               
               Map attributes = font.getAttributes();
               
               attributes.put(TextAttribute.STRIKETHROUGH,TextAttribute.STRIKETHROUGH_ON);
              
               fonts.set(counter,new Font(attributes));
            }
      }
	   
      public String[] words() {
         return(new String[]{"CALCULUS","TRIGONOMETRY","ABSTRACT","BOOLEAN",
        		             "TRANSVERSAL","SCOOBY","ALICE","VERA","MEL",
        		             "CONTINUITY","DERIVATIVE","INTEGRAL","MATHEMATICS",
        		             "ALGEBRA","EQUATION","SLOPE","INTERPOLATE","THEOREM",
        		             "PROOF","CIRCLE","TANGENT","SECANT","DISCONTINUOUS",
        		             "LEBESGUE","GALOIS","CANTOR","GAUSS","NEWTON","MANTISSA",
        		             "ORIGIN","COORDINATES","TRIANGLE","RHOMBOS","EXPONENTIATION",
        		             "INTERVAL","BOUNDED","CASORATI","MATRIX","VALUE"});          	 
      }
	   
      public WordsPanel(int width,
    		            int height) {
         setPreferredSize(new Dimension(width,height));
         
         add(start = new JButton("Start"));
         
         start.addActionListener(this);
      }
      
      public void setup() {
    	  puzzlePanel.clearButtons();
    	  
          words = getWords();
          
          fonts = new ArrayList<Font>();
          
          for (int counter=0;counter<words.size();counter++)
             fonts.add(new Font("Helvetica",Font.BOLD,12));
          
          for (String word : words)
             puzzlePanel.placeWord(word);
          
          puzzlePanel.fillInLetters();
          
          repaint();
      }
      
      public ArrayList<String> getWords() {
          String[] temp = words();
          
          words = new ArrayList();
          
          while (words.size() < 10) {
             int index = (int)(temp.length*Math.random());
             
             if (!words.contains(temp[index]))
                words.add(temp[index]);
          }
          
          return(words);
      }
      
      public void actionPerformed(ActionEvent e) {
         setup();
         
         start.setText("Restart");
      }
      
      public void paintComponent(Graphics g) {
         super.paintComponent(g);
         
         if (words != null)
            for (int counter=0;counter<words.size();counter++)
           	   if (words.size() > 0) {
           	      g.setFont(fonts.get(counter));   
           	   
                  g.drawString(words.get(counter),10,50 + counter*20);
           	   }
      }
	   
   }
   
   public class MyMouseAdapter extends MouseAdapter {
	  
      public void mouseReleased(MouseEvent e) {
         if (candidate != null) {
            String wordForward = "";
            String wordBackward = "";
            
            for (MyButton button : candidate) {
               wordForward += button.getText();
               wordBackward += button.getText();
            }
            
            if (wordsPanel.theWords().contains(wordForward) ||
                wordsPanel.theWords().contains(wordBackward)) {
               for (MyButton button : candidate) {
            	   String temp = button.getText();
            	   
            	   button.setColor(Color.white);
            	   
            	   button.repaint();
               }
               
               wordsPanel.setFont(wordForward);
               wordsPanel.setFont(wordBackward);
               
               wordsPanel.repaint();
            } else {
               for (MyButton button : candidate)
                  button.setBackground(new Color(204,204,204));
            }
         }
    	  
         candidate = null;
      }	   
   }
   
   public class MyMouseMotionAdapter extends MouseMotionAdapter {
	   
      public void mouseDragged(MouseEvent e) {
          Point point = new Point(e.getXOnScreen(),e.getYOnScreen());
      
          if (candidate == null)
             candidate = new ArrayList<MyButton>();
          
    	  for (int counter=0;counter<buttons.length;counter++)
             for (int counter1=0;counter1<buttons[0].length;counter1++) {
                Point locationOnScreen = buttons[counter][counter1].getLocationOnScreen();
                Dimension size = buttons[counter][counter1].getSize();
                
                int xinset = size.width/4;
                int yinset = size.height/4;

                Rectangle rectangle = new Rectangle(locationOnScreen.x+xinset,locationOnScreen.y+yinset,
                		                            size.width-2*xinset,size.height-2*yinset);
                
                if (rectangle.contains(point)) {
                   buttons[counter][counter1].setBackground(Color.red);	
                   
                   if (!candidate.contains(buttons[counter][counter1]))
                      candidate.add(buttons[counter][counter1]);
                }
             }

          
      }
	   
   }

}