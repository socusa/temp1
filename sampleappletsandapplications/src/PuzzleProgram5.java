/* Programming Assignment 5

Problem Statement:

You will play a game in this program in which you try to located words hidden in a grid. 

This assignment will consist of two different peices. 

In the first part, you will write a small application that will open a text file containing a collection of
words of between length 3 and length 9. 

The application will then create an output file which will write an HTML document to load an
applet and send all of the words loaded from the data file to the applet through one or more param
tags. 

In the applet, you will create a grid of blocks 10x10 as in assignment 4. You will place a text field
on the applet in which the user will place a number. The number must be a positive integer less
than or equal to 10. You will also place a text field on the applet in which the user will place a
number of seconds. The number of seconds must be a positive integer. 

You will then randomly pick that number of words from the group of words sent through param
tags. You will then place these words randomly in the grid either vertically or horizontally. You will
then fill the remaining blocks with random letters. 

You must also use at least two threads in your program. 

Thread 1 

Thread 1 must run when the applet loads. It will load the number from the second text field placed
on the applet. You will convert that to a number of minutes and number of seconds, and display a
countdown of the amount of time left in the game.

During the last ten seconds of the game, during each second you should play a small music file. 

Thread 2 

You should also place on the applet's container a button labeled Guess. 

You should also place a textfield on the applet's container that will contain the number of correct
words the player found. 

The first time that the user clicks a button, the second thread should begin executing. 

The thread will communicate with the applet through a shared String variable called letter, and a
shared boolean labeled done. 

The initial value of letter should be an empty string. The initial value of done should be false. 

The thread will collect a String variable called guess. 

As long as letter is the empty string, the thread should voluntarily wait. 

When letter is not an empty string, the thread should resume execution, and concatenate the value
of letter to guess. Then it should reset letter to an empty string will will place the thread in the
waiting state again. 

When the user clicks the button labeled Guess, the value of done should be set to true, which
should break the loop in the run method of the thread. After that loop exits, the thread will
compare the value of guess that it collected to the list of words that were hidden. If the guess is
found, then you should increment the number of words the user found. Otherwise print an error
message on the applet's window. 

After the first letter is picked by the user, the next letter picked will determine whether the user is
choosing a vertical or horizontal word. Once the direction has been determined, then if the user
clicks a letter that is not the next on the row or the column they picked, then do nothing. 

The player wins if they find all of the words that were hidden before time runs out. The player loses
if they don't. 

You should display an appropriate message on the applet when the game is over. 

You may also use music files during the game.
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.applet.*;

public class PuzzleProgram5 extends JApplet implements ActionListener {
   private JTextField numberOfSecondsField;
   private Image[]                  digits;
   private Image                     colon;
   private Countdown             countdown;
   private PuzzlePanel              puzzle;
   private ControlPanel            control;
   private MessagePanel            message;
   private OutputPanel              output;
   private int                     minutes;
   private int                     seconds;
   private MyBlock[]                blocks;
   private String[]                  words;
   private JButton                   start;
   private JTextField        numberOfWords;
   private Collector             collector;
   private String                    guess;
   private String                   letter;
   private JButton                  guess1;
   private boolean               suspended;
   private DynamicArray        validBlocks;
   private DynamicArray         usedBlocks;
   private int                  currentRow;
   private int               currentColumn;
   private boolean                vertical;
   private boolean                    left;
   private boolean                      up;
   private int               numberCorrect;
   private AudioClip                 music;

// This inner class is used to display the timer. When it is started,
// it reads the number of seconds that the user entered. It then determines
// the current number of seconds since midnight and adds the number of
// seconds that the user entered to the current number of seconds. Using
// this number, it then repeatedly determines the current number of seconds
// and divides this into minutes and seconds and displays them. A start and
// stop method are provided.

   public class Countdown extends JPanel implements Runnable {
      private Thread thread;
      private boolean keepGoing;
      private int targetSeconds;

// This method determines the number of seconds that have elapsed since
// midnight.

      private int currentSeconds() {
         Calendar c = Calendar.getInstance();
         
         int hour = c.get(Calendar.HOUR_OF_DAY);
         int minute = c.get(Calendar.MINUTE);
         int second = c.get(Calendar.SECOND);

         return(hour*3600+minute*60+second);
      }  

      public Countdown(int width,
                       int height,
                       int x,
                       int y) {
         setSize(width,height);
         setLocation(x,y);

         setBackground(Color.yellow);
      }

// Since we don't want the timer to start automatically, we provide
// this start method. This method determines what the target is and
// then starts the thread.

      public void start(int numberOfSeconds) {
         targetSeconds = currentSeconds();

         targetSeconds += numberOfSeconds;

         thread = new Thread(this);
         keepGoing = true;
         thread.start();
      }

// This methods displays the digits corresponding to the number of
// minutes and seconds left. The run method determines the number of
// minutes and number of seconds left. We then determine the tens and
// units digits of minutes and seconds.
//
// In order to determine where to place the images, we use a variable
// called startx, and then increment that variable with the width of
// each image.

      public void paint(Graphics g) {
         g.clearRect(0,0,getWidth(),getHeight());

         g.setColor(Color.yellow);

         g.fillRect(0,0,getWidth(),getHeight());

         int tens = minutes/10;
         int units = minutes % 10;

         int startx = 0;

         g.drawImage(digits[tens],startx,0,this);

         startx += digits[tens].getWidth(this);

         g.drawImage(digits[units],startx,0,this);
  
         startx += digits[units].getWidth(this);

         g.drawImage(colon,startx,0,this);

         startx += colon.getWidth(this);

         tens = seconds/10;
         units = seconds % 10;

         g.drawImage(digits[tens],startx,0,this);

         startx += digits[tens].getWidth(this);

         g.drawImage(digits[units],startx,0,this);
      }

      public void stop() {
         keepGoing = false;
      }

// The run method determines the number of seconds left and then
// turns that into minutes and seconds. If there are no seconds left
// then we display a message.

      public void run() {
         while (keepGoing) {
            try {
               Thread.sleep(1000);
            } catch (InterruptedException ie) {
            }

            int secondsLeft = targetSeconds - currentSeconds();

            if (secondsLeft == 0) {
               keepGoing = false;

               message.setMessage("You lose!");

               usedBlocks.highlightBlocks();
            } else if (secondsLeft < 10)
               music.play();

            minutes = secondsLeft/60;
            seconds = secondsLeft % 60;

            repaint();
         }
      }
   }

// This inner class is used to define an extension of a JButton.
// This allows us to add extra properties to a button. In particular,
// we can record what row and column the button is in, and what
// color we should color the border if we highlight the button.

   public class MyBlock extends JButton {
      private int row;
      private int column;
      private Color highlightColor;

      public MyBlock(String label) {
         super(label);
      }

      public int row() {
         return(row);
      }

      public void setRow(int row) {
         this.row = row;
      }

      public int column() {
         return(column);
      }

      public void setColumn(int column) {
         this.column = column;
      }

      public Color highlightColor() {
         return(highlightColor);
      }

      public void setHighlightColor(Color highlightColor) {
         this.highlightColor = highlightColor;
      }
   }

// This inner class is used to place the buttons on the screen where
// we place the letters.

   public class PuzzlePanel extends JPanel {

      public PuzzlePanel(int width,
                         int height,
                         int x,
                         int y) {
         setSize(width,height);
         setLocation(x,y);

         setLayout(null);

// We use modulus and integer division to determine where to place each
// block on the screen. We then record the row and column and add an
// ActionListener to each button.

         for (int i=1;i<101;i++) {
            add(blocks[i] = createMyBlock(" ",20,20,20*((i-1) % 10),
                                                    20*((i-1) / 10)));
 
            blocks[i].setRow((i-1) / 10);
            blocks[i].setColumn((i-1) % 10);

            blocks[i].addActionListener(PuzzleProgram5.this);
         }
      }

// This method is a key method that is used to place the words onto the
// puzzle. We begin by determining the length of the word to be placed
// and randomly choose a row, column, and direction. We then repeatedly
// choose a new row, column, and direction until the word will fit in
// the puzzle. Once we have found a place to put the word, we then place
// the letters on the puzzle.

      public void placeWord(String word) {
         int length = word.length();

         int row = ((int)(100*Math.random()))/10;
         int column = ((int)(100*Math.random()))/10;

         int temp = (int)(100*Math.random());

         String direction = (temp <= 50) ? "horizontal" : "vertical";

         while (!willFit(row,column,direction,word)) {
            row = ((int)(100*Math.random()))/10;
            column = ((int)(100*Math.random()))/10;

            temp = (int)(100*Math.random());

            direction = (temp <= 50) ? "horizontal" : "vertical";
         }

         setWordAt(row,column,direction,word);
      }

   }

// This inner class is used to hold the labels, textfields, and buttons
// which allow the user to input how many seconds they want to play, how
// many words they want to hide, and the buttons to start the game and
// see if they have chosen a correct word.

   public class ControlPanel extends JPanel {

      public ControlPanel(int width,
                          int height,
                          int x,
                          int y) {
         setSize(width,height);
         setLocation(x,y);

         setLayout(null);

         setBackground(Color.yellow);

         add(numberOfSecondsField = createJTextField(50,20,125,20));

         numberOfSecondsField.setText("100");

         JLabel label = new JLabel("How many seconds");

         label.setSize(120,20);
         label.setLocation(5,20);

         add(label);

         label = new JLabel("do you need?");

         label.setSize(120,20);
         label.setLocation(5,40);

         add(label);

         add(start = createMyBlock("Start",100,20,50,70));

         start.addActionListener(PuzzleProgram5.this);

         label = new JLabel("How many words");

         label.setSize(120,20);
         label.setLocation(5,100);

         add(label);

         label = new JLabel("do you want to hide?");
        
         label.setSize(120,20);
         label.setLocation(5,120);

         add(label);

         add(numberOfWords = createJTextField(50,20,125,100));

         numberOfWords.setText("1");

         add(guess1 = createMyBlock("Guess",100,20,50,150));

         guess1.addActionListener(PuzzleProgram5.this);

         guess1.setVisible(false);
      }

   }

// This inner class is used to place messages on the applet.

   public class MessagePanel extends JPanel {
      private JLabel message;

      public MessagePanel(int width,
                          int height,
                          int x,
                          int y) {
         setSize(width,height);
         setLocation(x,y);

         setLayout(null);

         setBackground(Color.yellow);

         add(message = new JLabel(" "));
 
         message.setSize(200,20);
         message.setLocation(100,50);
      }

      public void setMessage(String message) {
         this.message.setText(message);
      }
   }

// This inner class is used to display the number of words found
// and the word that the player is spelling.

   public class OutputPanel extends JPanel {
      private boolean started;

      public OutputPanel(int width,
                         int height,
                         int x,
                         int y) {
         setSize(width,height);
         setLocation(x,y);

         setLayout(null);
      }

      public void start() {
         started = true;
      }

      public void paint(Graphics g) {
         g.clearRect(0,0,getWidth(),getHeight());

         g.setColor(Color.yellow);

         g.fillRect(0,0,getWidth(),getHeight());

         g.setColor(Color.black);

         if (started) {
            FontMetrics metrics = g.getFontMetrics();

            String message = "You have found " + numberCorrect + " out of " + words.length + " words";

            int width = metrics.stringWidth(message);

            g.drawString(message,getWidth()/2-width/2,50);

            if (guess.length() > 0) {
               message = "You are spelling ";

               width = metrics.stringWidth(message);

               int width1 = metrics.stringWidth(guess);

               g.drawString(message,getWidth()/2-(width+width1)/2,70);

               g.setColor(Color.red);

               g.drawString(guess,getWidth()/2+width/2-width1/2,70);
            }
         }
      }
   }

// This inner class is used for the second thread which collects the guess.
// This thread begins executing when the object is created and immediately
// places itself into the waiting state. When the thread wakes up it adds
// the value of letter to the guess which we are collecting. After it does this
// it places itself back in the waiting state.

   public class Collector extends Thread {
      boolean keepGoing;

      public Collector() {
         keepGoing = true;
         suspended = true;
         start();
      }

      public void stopThread() {
         keepGoing = false;
      }

      public void run() {
         while (keepGoing) {
            synchronized (PuzzleProgram5.this) {
               while (suspended)
                  try {
                     PuzzleProgram5.this.wait();
                  } catch (InterruptedException ie) {
                  }
            }

            guess += letter;

            output.repaint();

            suspended = true;
         }
      }
   }

// This inner class is used to create a dynamic structure in which we can collect
// blocks.

   public class DynamicArray {
      private MyBlock[] array;
      private int used;

      public DynamicArray() {
         array = new MyBlock[10];

         used = 0;
      }

// When we try to add to the structure, if it is full, we add more space
// and then place the block.

      public void add(MyBlock block) {
         if (used == array.length) {
            MyBlock[] temp = new MyBlock[array.length+10];

            for (int i=0;i<array.length;i++)
               temp[i] = array[i];

            array = temp;
         }

         array[used++] = block;
      }

      public boolean contains(MyBlock block) {
         boolean found = false;

         for (int i=0;i<array.length;i++)
            found = array[i] == block ? true : found;

         return(found);
      }

      public void highlightBlocks() {
         for (int i=0;i<array.length;i++)
            if (array[i] != null)
               highlightBlock(array[i],array[i].highlightColor());
         }

// When we want to clear the structure we arrange the pointer array to 
// point to a new array.

      public void empty() {
         array = new MyBlock[10];

         used = 0;
      }

      public boolean isEmpty() {
         return(used == 0);
      }
   }

// This is an auxillary method used to create a JTextField.

   private JTextField createJTextField(int width,
                                       int height,
                                       int x,
                                       int y) {
      JTextField field = new JTextField();
      field.setSize(width,height);
      field.setLocation(x,y);

      return(field);
   }

// This auxillary method is used to create an instance of a block.

   private MyBlock createMyBlock(String label,
                                 int width,
                                 int height,
                                 int x,
                                 int y) {
      MyBlock block = new MyBlock(label);
      block.setSize(width,height);
      block.setLocation(x,y);

      Insets insets = block.getInsets();

      insets.right = 0;
      insets.left = 0;

      block.setMargin(insets);

      return(block);
   }

// We use this method to highlight the border of the block.

   private void highlightBlock(MyBlock block,
                               Color color) {
      block.setBorder(BorderFactory.createLineBorder(color));
   }

// We need a way to determine what button is located in a particular
// row and column. This method searches through the array looking for
// the button whose row and column are equal to the row and column
// we send.

   private MyBlock button(int row,
                          int column) {
      for (int i=1;i<101;i++)
         if (blocks[i].row() == row &&
             blocks[i].column() == column)
            return(blocks[i]);

      return(blocks[0]);
   }

// We use this method to determine what is located in a particular
// part of the puzzle. If we determine that there are not enough
// blocks to form a word, we return a -. Otherwise we collect all
// of the labels of the buttons into a word.

   private String wordAt(int row,
                         int column,
                         String direction,
                         int number) {
      MyBlock button = button(row,column);

      String word = "";

      if (direction.equals("horizontal")) {
         if (column + number >= 10)
            return("-");
         else {      
            for (int i=column;i<column+number;i++)
               word += button(row,i).getText();
         }
      } else {
         if (row + number >= 10)
            return("-");
         else {      
            for (int i=row;i<row+number;i++)
               word += button(i,column).getText();
         }
      }

      return(word);
   }

// This method let's us determine whether or not a particular word will
// fit in a part of the puzzle. It determines what word is stored in that
// part of the array, and then character by character determines if our
// word will fit there.

   private boolean willFit(int row,
                           int column,
                           String direction,
                           String word) {
      String temp = wordAt(row,column,direction,word.length());

      boolean willfit = true;

      if (!temp.equals("-")) {
         for (int i=0;i<word.length();i++)
            if (!temp.substring(i,i+1).equals(" ") &&
                !temp.substring(i,i+1).equals(word.substring(i,i+1)))
               willfit = false;
      } else
         willfit = false;

      return(willfit);
   }

// This is a key method used to place a word onto the puzzle. It is only
// called after we determine that the word will fit in that part of the
// puzzle.

   private void setWordAt(int row,
                          int column,
                          String direction,
                          String word) {
      Color highlightColor = Color.red;

      if (direction.equals("horizontal")) {
         for (int i=column;i<column+word.length();i++) {
            MyBlock block = button(row,i);

            block.setText(word.substring(i-column,i-column+1));

            block.setHighlightColor(highlightColor);

// We place the blocks into the usedBlock array so that if the player
// loses the game, we can highlight the borders of the blocks.

            usedBlocks.add(block);
         } 
      } else {
         for (int i=row;i<row+word.length();i++) {
            MyBlock block = button(i,column);

            block.setText(word.substring(i-row,i-row+1));

            block.setHighlightColor(highlightColor);

            usedBlocks.add(block);
         } 
      }
   }

   private boolean contains(String[] array,
                            String string) {
      boolean found = false;

      for (int i=0;i<array.length;i++)
         found = array[i].equals(string) ? true : found;

      return(found);
   }

   public void init() {
      Container container = getContentPane();

      container.setLayout(null);

      container.setBackground(Color.yellow);

      digits = new Image[10];

      digits[0] = getImage(getCodeBase(),"digitspuzzleprogram5/zero.gif");
      digits[1] = getImage(getCodeBase(),"digitspuzzleprogram5/one.gif");
      digits[2] = getImage(getCodeBase(),"digitspuzzleprogram5/two.gif");
      digits[3] = getImage(getCodeBase(),"digitspuzzleprogram5/three.gif");
      digits[4] = getImage(getCodeBase(),"digitspuzzleprogram5/four.gif");
      digits[5] = getImage(getCodeBase(),"digitspuzzleprogram5/five.gif");
      digits[6] = getImage(getCodeBase(),"digitspuzzleprogram5/six.gif");
      digits[7] = getImage(getCodeBase(),"digitspuzzleprogram5/seven.gif");
      digits[8] = getImage(getCodeBase(),"digitspuzzleprogram5/eight.gif");
      digits[9] = getImage(getCodeBase(),"digitspuzzleprogram5/nine.gif");

      colon = getImage(getCodeBase(),"digitspuzzleprogram5/colon.gif");

      container.add(control = new ControlPanel(200,200,200,100));

      container.add(countdown = new Countdown(100,100,300,0));

      blocks = new MyBlock[101];

      container.add(puzzle = new PuzzlePanel(200,200,0,0));

      container.add(message = new MessagePanel(400,100,0,300));

      container.add(output = new OutputPanel(200,100,0,200));

// The HTML code contains the number of words we are sending to the
// applet. We first determine that number, then one by one we read in
// those words into an array.

      int numberOfWords = Integer.parseInt(getParameter("numberofwords"));

      words = new String[numberOfWords];

      for (int i=0;i<words.length;i++) {
         words[i] = getParameter("word" + i);
      }

      collector = new Collector();

      guess = "";

      validBlocks = new DynamicArray();

      usedBlocks = new DynamicArray();

      music = getAudioClip(getCodeBase(),"soundspuzzleprogram5/quack.au");
   }

// We use this method to dispose of the resources we have used in the applet.

   public void destroy() {
      countdown.stop();

      countdown = null;

      puzzle = null;

      control = null;
   
      collector.stopThread();

      message = null;

      output = null;

      music.stop();
   }

   public synchronized void actionPerformed(ActionEvent e) {
// When the start button is pressed, the start button disappears, and the
// guess button appears. We then collect the number of words the user
// specifies into a temporary array, and then point the variable words
// to refer to the new array.

      if (e.getSource() == start) {
         start.setVisible(false);

         guess1.setVisible(true);

         countdown.start(Integer.parseInt(numberOfSecondsField.getText()));

         output.start();

         output.repaint();

         int numberOfWords = Integer.parseInt(this.numberOfWords.getText());

         int max = (numberOfWords < words.length) ? numberOfWords : words.length;

         String[] theWords = new String[max];

         for (int i=0;i<theWords.length;i++)
            theWords[i] = "";

         for (int i=0;i<theWords.length;i++) {
            int index = (int)((words.length-1)*Math.random());

            while (contains(theWords,words[index]))
               index = (int)((words.length-1)*Math.random());
            
            theWords[i] = words[index];
         }

         for (int i=0;i<theWords.length;i++) {
            puzzle.placeWord(theWords[i]);
        }

// We now randomly place letters into the other blocks.

         String[] letters = {"a","b","c","d","e","f","g","h",
                             "i","j","k","l","m","n","o","p",
                             "q","r","s","t","u","v","w","x",
                             "y","z"};

         for (int i=1;i<101;i++)
            if (blocks[i].getText().equals(" ")) {
               int index = (int)(25*Math.random());

               blocks[i].setText(letters[index]);
            }

         words = theWords;
      } else if (e.getSource() == guess1) {
// When the user clicks the guess button, we determine if the word the
// user is collecting is one that was hidden. If the player has found
// all of the words, they win.

         if (contains(words,guess)) {
            message.setMessage("You found one of the words");

            numberCorrect++;

            output.repaint();

            if (numberCorrect == words.length) {
               countdown.stop();

               message.setMessage("You won!");
            }
         } else
            message.setMessage("That is not one of the words");

         guess = "";

         output.repaint();

         letter = "";

         validBlocks.empty();
      } else {
// If the player didn't click the start or guess button, then they picked
// one of the other blocks. When then get a pointer to that block. If the
// array of valid blocks is empty, then we place all of the adjacent blocks
// into the valid blocks array.

         MyBlock block = (MyBlock)e.getSource();

         if (validBlocks.isEmpty()) {
            letter = block.getText();

            currentRow = block.row();
            currentColumn = block.column();

            if (currentRow-1 >= 0)
               validBlocks.add(button(currentRow-1,currentColumn));

            if (currentRow+1 < 10)
               validBlocks.add(button(currentRow+1,currentColumn));

            if (currentColumn-1 >= 0)
               validBlocks.add(button(currentRow,currentColumn-1));

            if (currentColumn+1 < 10)
               validBlocks.add(button(currentRow,currentColumn+1));
         } else {
// If the valid blocks array is not emtpy, then we get a reference to
// the block. This determines the direction that the user is going. We
// empty the valid blocks array, and then place the next block that the
// user should choose in the valid blocks array.

            letter = "";

            if (validBlocks.contains(block) &&
                guess.length() == 1) {
// If the length of guess is 1, then we know that this is the second
// block that was picked.

               int newRow = block.row();
               int newColumn = block.column();

               letter = block.getText();

               validBlocks.empty();

               vertical = newRow != currentRow;

               up = !(vertical && newRow > currentRow);
               left = !(!vertical && newColumn > currentColumn);

               if (vertical && up && newRow > 0)
                  validBlocks.add(button(newRow-1,newColumn));

               if (vertical && !up && newRow < 9)
                  validBlocks.add(button(newRow+1,newColumn));

               if (!vertical && left && newColumn > 0)
                  validBlocks.add(button(newRow,newColumn-1));

               if (!vertical && !left && newColumn < 9)
                  validBlocks.add(button(newRow,newColumn+1));    
            } else if (validBlocks.contains(block)) {
// If the length of guess is not 1, then we are picking the next block
// in the line.

               int newRow = block.row();
               int newColumn = block.column();

               letter = block.getText();

               validBlocks.empty();

               if (vertical && up && newRow > 0)
                  validBlocks.add(button(newRow-1,newColumn));

               if (vertical && !up && newRow < 9)
                  validBlocks.add(button(newRow+1,newColumn));

               if (!vertical && left && newColumn > 0)
                  validBlocks.add(button(newRow,newColumn-1));

               if (!vertical && !left && newColumn < 9)
                  validBlocks.add(button(newRow,newColumn+1));
            } else
// If the block the user picks is not valid, then we display a message.

               message.setMessage("That block is not valid");
         }

// No matter what button is picked, we wake up the waiting thread by setting
// its suspended variable to false and calling notify. If a valid button has
// not been picked, then the variable letter will be empty.
           
         suspended = false;

         notify();
      }
   }
}
