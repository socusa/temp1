import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.applet.*;
import java.util.ArrayList;
import java.util.Scanner;

public class HangmanRoulette extends JApplet implements KeyListener,Runnable {
   private String[]              words;
   private JButton[]            blocks;
   private JButton[]          alphabet;
   private String              theWord;
   private final int  BLOCK_WIDTH = 20;
   private final int BLOCK_HEIGHT = 20;
   private Circle[]            circles;
   private boolean[]       fillCircles;
   private AudioClip           welcome;
   private AudioClip           nowplay;
   private AudioClip            scream;
   private AudioClip             click;
   private AudioClip            youwon;
   private AudioClip          applause;
   private boolean         gameStarted;
   private Thread               thread;
   private int             lettersLeft;
   private int      numberOfRedCircles;
   private boolean            mustPlay;
   private boolean            gameOver;

// This method is used to modularize our program so that
// we don't have to type all of these lines each time we
// create a JButton.

   private JButton createJButton(String label,
                                 int width,
                                 int height,
                                 int x,
                                 int y) {
      JButton button = new JButton(label);
      button.setSize(width,height);
      button.setLocation(x,y);

      Insets insets = button.getInsets();

      insets.left = 0;
      insets.right = 0;

      button.setMargin(insets);

      return(button);
   }

// This inner class is used to store the information about
// a circle. We also include methods to draw and fill
// the circle.

   public class Circle {
      private int radius;
      private int centerx;
      private int centery;

      public Circle(int radius,int centerx,int centery) {
         this.radius = radius;
         this.centerx = centerx;
         this.centery = centery;
      }

      public void draw(Graphics graphicsContext) {
         graphicsContext.drawOval(centerx-radius,centery-radius,2*radius,2*radius);
      }

      public void fill(Graphics graphicsContext,boolean fill) {
         graphicsContext.clearRect(centerx-radius-1,centery-radius-1,2*radius+2,2*radius+2);

// If we need to fill the circle, we first get a reference to the
// current color, fill the circle with red, and then reset the
// drawing color.

         if (fill) {
            Color originalColor = graphicsContext.getColor();

            graphicsContext.setColor(new Color(255,0,0));

            graphicsContext.fillOval(centerx-radius,centery-radius,2*radius,2*radius);

            graphicsContext.setColor(originalColor);
         } else
            graphicsContext.drawOval(centerx-radius,centery-radius,2*radius,2*radius);
      }
   }

   public void init() {
      Container container = getContentPane();

      container.setLayout(null);

      container.setBackground(Color.white);

      ArrayList<String> list = new ArrayList<>();  
    
      Scanner scanner = new Scanner(this.getClass().getResourceAsStream("resources/words.txt"));
      
      while (scanner.hasNext())
    	  list.add(scanner.next());
      
      words = list.toArray(new String[list.size()]);

//      for (int i=0;i<numberOfWords;i++)
//         words[i] = getParameter("word" + i).toUpperCase();

// We now randomly pick one of the words.

      int index = (int)(words.length*Math.random());

      theWord = words[index];

// We need to determine how many letters are left so we
// can know if the player wins the game.

      lettersLeft = theWord.length();

// We now set up the blocks for the letters in the word.

      blocks = new JButton[theWord.length()];

      int xposition = getWidth()/2-BLOCK_WIDTH*theWord.length()/2;
      int yposition = getHeight()/2-BLOCK_HEIGHT/2;

      for (int i=0;i<theWord.length();i++) {
         container.add(blocks[i] = createJButton("",BLOCK_WIDTH,BLOCK_HEIGHT,xposition+BLOCK_WIDTH*i,yposition));

         blocks[i].setActionCommand(theWord.substring(i,i+1));
      }

// Now we create the six circles and place them around the blocks.

      circles = new Circle[6];

      circles[0] = new Circle(10,getWidth()/2,getHeight()/2-50);
      circles[1] = new Circle(10,xposition+BLOCK_WIDTH*theWord.length()+20,getHeight()/2-30);
      circles[2] = new Circle(10,xposition+BLOCK_WIDTH*theWord.length()+20,getHeight()/2+30);
      circles[3] = new Circle(10,getWidth()/2,getHeight()/2+50);
      circles[4] = new Circle(10,xposition-20,getHeight()/2+30);
      circles[5] = new Circle(10,xposition-20,getHeight()/2-30);

      fillCircles = new boolean[6];

// We now load sound files. Note that because of download times
// some of these sounds files aren't available to the applet
// when it runs.

      welcome = getAudioClip(getCodeBase(),"sounds/welcome.au");

      nowplay = getAudioClip(getCodeBase(),"sounds/nowplay.au");

      scream = getAudioClip(getCodeBase(),"sounds/scream.wav");

      click = getAudioClip(getCodeBase(),"sounds/click.wav");

      youwon = getAudioClip(getCodeBase(),"sounds/youwon.wav");

      applause = getAudioClip(getCodeBase(),"sounds/applause.wav");

// We now create the blocks for the alphabet and place them
// on the screen.

      alphabet = new JButton[26];

      for (int i=0;i<alphabet.length;i++)
         container.add(alphabet[i] = createJButton(String.valueOf((char)(i+65)),
                                                   20,20,
                                                   getWidth()/2-130+20*(i % 13),350+20*(i/13)));

// We now add a key listener to the applet.

      addKeyListener(this);
   }

// Note that the only thing that changes in the paint method are
// the colors of the circles. Everything else is constant.

   public void paint(Graphics g) {
      super.paint(g);	   
	   
      for (int i=0;i<blocks.length;i++)
         blocks[i].repaint();

      for (int i=0;i<alphabet.length;i++)
         alphabet[i].repaint();

      Font font = new Font("Serif",Font.ITALIC,40);

      g.setFont(font);

      Color originalColor = g.getColor();

      g.setColor(new Color(200,0,0));

      FontMetrics metrics = g.getFontMetrics();

      int width = metrics.stringWidth("Hangman Roulette");

      g.drawString("Hangman Roulette",getWidth()/2-width/2,30);

      g.setColor(new Color(0,0,200));

      font = new Font("Serif",Font.BOLD,12);

      g.setFont(font);

      metrics = g.getFontMetrics();

      width = metrics.stringWidth("Unused Letters:");

      g.drawString("Unused Letters:",getWidth()/2-width/2,320);

      g.setColor(originalColor);

      if (mustPlay) {
         width = metrics.stringWidth("You must press F1");

         g.drawString("You must press F1",getWidth()/2-width/2,300);
      } else {
         g.setColor(Color.white);

         g.clearRect(0,290,getWidth(),14);

         g.setColor(originalColor);
      }

      width = metrics.stringWidth("If a red circle lands at the top, you lose");

      g.drawString("If a red circle lands at the top, you lose",getWidth()/2-width/2,100);

// If we haven't started the game, then we draw the 6 circles
// and play the sound that welcomes the user. When a component
// becomes visible its paint method is called so we make sure
// that we don't try to draw red circles before the game is
// started.

      if (!gameStarted) {
         for (int i=0;i<6;i++)
            circles[i].draw(g);

         welcome.play();

         gameStarted = true;
      } else {
         String output = "";
 
         for (int i=0;i<fillCircles.length;i++)
            output += fillCircles[i] + " ";

         for (int i=0;i<6;i++) {
            output += "Now filling " + i + " fillCircles[" + i + "] = " + fillCircles[i] + "\n";            

            circles[i].fill(g,fillCircles[i]);
         }
      }

// This draws the figure.

      g.fillRect(getWidth()/2-2,getHeight()/2-52,5,5);

      g.drawLine(getWidth()/2+3,getHeight()/2-47,getWidth()/2+3,getHeight()/2-45);
      g.drawLine(getWidth()/2-3,getHeight()/2-47,getWidth()/2-3,getHeight()/2-45);
      
      requestFocus();
   }

   public void run() {
      int numberOfSpins = 10 + (int)(10*Math.random());

// We generate a random number of spins.

      for (int j=0;j<numberOfSpins;j++) {
// We now shift the red buttons around

         boolean temp = fillCircles[5];

         for (int i=5;i>0;i--)
           fillCircles[i] = fillCircles[i-1];

         fillCircles[0] = temp;

         repaint();

         click.play();

         try {
            Thread.sleep(500);
         } catch (InterruptedException ie) {
         }
      }

// We test to see if the circle at the top is filled.
// If it is, we play the sound and set the boolean
// gameOver to true. We then fill in the remaining
// letters in the word that the player was guessing.

      if (fillCircles[0]) {
         scream.play();

         gameOver = true;

         for (int i=0;i<blocks.length;i++)
            blocks[i].setText(blocks[i].getActionCommand());
      } else
         applause.play();

      repaint();
   }      

   public void keyTyped(KeyEvent e) {
   }

   public void keyReleased(KeyEvent e) {
   }

   public void keyPressed(KeyEvent e) {
      int code = e.getKeyCode();

// If the game is not over and the key that is pressed is an alphabetic
// character or F1, then we obtain the key that was pressed.

      if (!gameOver && ((code >= 65 && code <=90) || code == 112)) {
         String letter = String.valueOf((char)code).toUpperCase();

// There are three conditions that must be true in order for us to
// make use of the key that was pressed.

// We first check to make sure the that key wasn't F1.
// We check to make sure that the user is not supposed to play
// Hangman Roullette.
// We check to make sure that the letter has not already been picked.

         if (code < 112 && 
             !mustPlay &&
             alphabet[(int)(letter.charAt(0)) - 65].isVisible()) {
            boolean found = false;

// If the key pressed is an alphabetic character that hasn't been
// picked, then we set the button containing the letter invisible.

            alphabet[(int)(letter.charAt(0)) - 65].setVisible(false);
            
            repaint();

// We now check to see if the character is contained in the word.

            for (int i=0;i<theWord.length();i++)
               if (letter.equals(theWord.substring(i,i+1))) {
                  blocks[i].setText(blocks[i].getActionCommand());

                  found = true;

                  lettersLeft--;
               }

            repaint();

// We check to see if the player has won.

            if (lettersLeft == 0)
               youwon.play();

// If the letter is not in the word, then the player
// must play Hangman Roullette.

            if (!found) {
               nowplay.play();

// We increase the number of red circles by 1.

               numberOfRedCircles++;

// We then color that many circles red making sure
// that the red one is not at the top unless all six
// are red.
      
               for (int i=1;i<circles.length;i++)
                  if (i <= numberOfRedCircles)
                     fillCircles[i] = true;
                  else
                     fillCircles[i] = false;

               repaint();

// We now set the boolean mustPlay to true so we will
// know whether or not the player must play Hangman
// Roullette.

               mustPlay = true;
            }
         } else if (code == 112) {
            mustPlay = false;

// We set the boolean mustPlay to false so that the user can
// pick another letter if they make it through a spin.

            thread = new Thread(this);
            thread.start();
         }
      }
   }

}