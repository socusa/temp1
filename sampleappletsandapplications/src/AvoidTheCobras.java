/* Programming Assignment 5

Problem Statement:

You will write an applet to play the game "Avoid the Cobras". 

Create a grid of blocks on the screen 10 wide and 10 deep. 

Provide a JTextField for the player in which the player will input a number. 

Provide a JTextField for the player in which their score is placed. 

Provide a button labeled Start. 

When the player clicks the Start button, retrieve the text that is stored in the first JTextField. You
may assume that the player will place a positive number in that field. 

Randomly place the letter "C" in the number of blocks specified by the player. (That is, if the
player places "5" in the JTextField, then pick five blocks randomly and place the letter "C" in
them.) 

Pause for a few seconds, and then randomly scatter those letters around the grid and hide them.
Play some small music file while the letters are being scrambled. You may use spacemusic.au or
any other music file you want. 

The purpose of the game is for the player to pick blocks beginning anywhere on the bottom row of
the grid, and proceed by picking adjacent blocks. The player wins the game if they successfully
pick a block on the top row. 

If the next block that the player picks is not one of the blocks where the letter "C" was hidden,
then the game proceeds, and the player's score is increased by 20 times the number of letters the
player chose to hide. Play a small music file. You may use yahoo1.au or any other music file you
want. If the block the player picks is on the top row, then the game is over. Display an appropriate
message if the player wins. Highlight the borders of all of the blocks the user has successfully
chosen in blue. 

If the next block that the player picks is one of the blocks where the letter "C" was hidden, then
the game is over. You should display an appropriate message on the screen. You should also play
a small music file. You may use gong.au or any other music file you want. 

After the player successfully picks another block, you should rescramble the letters "C" again. Play
some small music file while the letters are being rescrambled. You may use spacemusic.au or any
other music file you want. You should not allow any of the letters to be placed in blocks the player
has already picked. 

After the game is over, nothing should happen if the player clicks any of the blocks. If the player
clicks the Start button again, then the game starts over. If the player starts the game over, the
JTextField containing their score should be set back to "0", and the blocks should be set back to
the way they were when we started the game.
*/

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;
import javax.swing.border.*;

public class AvoidTheCobras extends JApplet implements ActionListener {
   private MyButton[]         blocks;
   private Container       container;
   private AudioClip      spaceMusic;
   private AudioClip            gong;
   private AudioClip           yahoo;
   private JButton             start;
   private JTextField         cobras;
   private int        numberOfCobras;
   private int[]      cobraPositions;
   private JTextField          score;
   private MyButton[]    adjacencies;
   private MyButton[] selectedBlocks;
   private boolean          gameOver;
   private boolean    steppedOnCobra;
   private OutputPanel        output;
   private boolean         shuffling;

// This inner class is used to create an extension of JButton
// which allows us to add additional properties to the button.
// In particular, we can determine if a cobra is in the button,
// whether the button is selected, the index of the button in
// the array, and the row and column of the button.

   public class MyButton extends JButton {
      private boolean cobra;
      private boolean selected;
      private int index;
      private int row;
      private int column;

      public MyButton(String label) {
         super(label);
      }

      public boolean selected() {
         return(selected);
      }

      public void setCobra() {
         setText("C");
         setActionCommand("C");
      }

      public void hideCobra() {
         setText("");
      }

      public void clearCobra() {
         hideCobra();
         setActionCommand("");
      }

      public int index() {
         return(index);
      }

      public void setIndex(int index) {
         this.index = index;
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
   }

   private void resetMargins(JButton button) {
      Insets insets = button.getInsets();

      insets.left = 0;
      insets.right = 0;

      button.setMargin(insets);
   }
   
   private MyButton createMyButton(String label,
                                   int width,
                                   int height,
                                   int x,
                                   int y) {
      MyButton button = new MyButton(label);
      button.setSize(width,height);
      button.setLocation(x,y);

      button.addActionListener(this);

      resetMargins(button);

      return(button);
   }

   private JButton createJButton(String label,
                                 int width,
                                 int height,
                                 int x,
                                 int y) {
      JButton button = new JButton(label);
      button.setSize(width,height);
      button.setLocation(x,y);

      resetMargins(button);

      button.addActionListener(this);

      return(button);
   }

   private JTextField createJTextField(String value,
                                       int width,
                                       int height,
                                       int x,
                                       int y) {
      JTextField field = new JTextField();
      field.setSize(width,height);
      field.setLocation(x,y);

      field.setText(value);

      return(field);
   }

   public JLabel createJLabel(String label,
                              int width,
                              int height,
                              int x,
                              int y) {
      JLabel temp = new JLabel();
      temp.setText(label);
      temp.setSize(width,height);
      temp.setLocation(x,y);

      return(temp);
   }

// We now need to get random locations for the cobras.
// We begin with an array with one element in it, and
// then add new locations if the button at that location
// hasn't already been selected.

   private int[] randomLocations(int number) {
      int numbers[] = new int[1];

      numbers[0] = 1 + (int)(9999*Math.random()/100);

      while (numbers.length < number) {
         int temp = 1 + (int)(9999*Math.random()/100);

         boolean found = false;

         for (int i=0;i<numbers.length;i++)
            found = (numbers[i] == temp) ? true : found;

         if (!found && !blocks[temp].selected()) {
            int[] temp1 = new int[numbers.length+1];

            for (int i=0;i<numbers.length;i++)
               temp1[i] = numbers[i];

            temp1[numbers.length] = temp;

            numbers = temp1;
         }
      }

      return(numbers);
   }

// Since the player must move to a block adjacent to one already
// chosen, we create an adjacency matrix. This method is used to
// add a block to the adjacency matrix.

   private void addToAdjacencyMatrix(MyButton block) {
      if (adjacencies != null) {
         MyButton[] temp = new MyButton[adjacencies.length+1];

         for (int i=0;i<adjacencies.length;i++)
            temp[i] = adjacencies[i];

         temp[adjacencies.length] = block;

         adjacencies = temp;
      } else {
         adjacencies = new MyButton[1];

         adjacencies[0] = block;
      }
   }

// We need to determine if the user has selected a block which is
// adjacent to one already selected.

   private boolean adjacent(MyButton block) {
      boolean found = false;

      for (int i=0;i<adjacencies.length;i++)
         found = (adjacencies[i] == block) ? true : found;

      return(found);
   }

// We also keep track of the blocks that have been selected.
// This method adds a block to the selected matrix.

   private void addToSelectedMatrix(MyButton block) {
      MyButton[] temp = new MyButton[selectedBlocks.length+1];

      for (int i=0;i<selectedBlocks.length;i++)
         temp[i] = selectedBlocks[i];

      temp[selectedBlocks.length] = block;

      selectedBlocks = temp;
   }

// We need a method to determine whether a block is in the
// selected matrix.

   private boolean selectedBlock(MyButton block) {
      boolean found = false;

      for (int i=0;i<selectedBlocks.length;i++)
         found = (selectedBlocks[i] == block) ? true : found;

      return(found);
   }

// We use this method to determine the index in the array of
// blocks of the block at the intersection of row and column.

   private int getIndex(int row,int column) {
      int index = -1;

      for (int i=1;i<blocks.length;i++)
         if (blocks[i].row() == row &&
             blocks[i].column() == column)
            index = i;

      return(index);
   }         

// Once a block has successfully been selected, we then collect
// all blocks that are adjacent to that block that aren't already
// in the adjacency matrix.

   private void getAdjacencies() {
      for (int i=0;i<selectedBlocks.length;i++) {
         int row = selectedBlocks[i].row();
         int column = selectedBlocks[i].column();

         if (row-1 >= 0) {
            int index = getIndex(row-1,column);
            MyButton block = blocks[index];

            if (!selectedBlock(block))
               addToAdjacencyMatrix(block);
         }

         if (row+1 < 10) {
            int index = getIndex(row+1,column);
            MyButton block = blocks[index];

            if (!selectedBlock(block))
               addToAdjacencyMatrix(block);
         }

         if (column-1 >= 0) {
            int index = getIndex(row,column-1);
            MyButton block = blocks[index];

            if (!selectedBlock(block))
               addToAdjacencyMatrix(block);
         }

         if (column+1 < 10) {
            int index = getIndex(row,column+1);
            MyButton block = blocks[index];

            if (!selectedBlock(block))
               addToAdjacencyMatrix(block);
         }
      }            
   }

   private void highlightBlock(MyButton block,Color color) {
      block.setBorder(BorderFactory.createLineBorder(color));
   }

// This inner class is used to hide the cobras.

   public class HideCobras extends Thread {
   
      public HideCobras() {
         this.start();
      }

      public void run() {
         numberOfCobras = Integer.parseInt(cobras.getText());

         int[] numbers = randomLocations(numberOfCobras);

         for (int i=0;i<numbers.length;i++)
            blocks[numbers[i]].setCobra();

         try {
            Thread.sleep(3000);
         } catch (InterruptedException ie) {
         }

         for (int j=0;j<2;j++) { 
            for (int i=0;i<numbers.length;i++)
               blocks[numbers[i]].clearCobra();

            numbers = randomLocations(Integer.parseInt(cobras.getText()));
   
            for (int i=0;i<numbers.length;i++)
               blocks[numbers[i]].setCobra();

            try {
               Thread.sleep(1000);
            } catch (InterruptedException ie) {
            }

            for (int i=0;i<numbers.length;i++)
               blocks[numbers[i]].clearCobra();

            cobraPositions = randomLocations(Integer.parseInt(cobras.getText()));

            for (int i=0;i<numbers.length;i++) {
               blocks[numbers[i]].setCobra();
               blocks[numbers[i]].hideCobra();
            }
         }
      }

   }

// This inner class is used to hold the start button and the textfields where
// the user inputs information.

   public class OutputPanel extends JPanel {

      public OutputPanel(int width,
                         int height,
                         int x,
                         int y) {
         setSize(width,height);
         setLocation(x,y);

         setLayout(null);

         setBackground(Color.white);

         add(start = createJButton("Start",50,20,50,10));

         add(cobras = createJTextField("1",50,20,50,40));

         add(score = createJTextField("0",50,20,50,90));
      }

   }

// This method takes the cobras from their current positions,
// gets another collection of random locations, and then hides
// the cobras there.

   private void shuffleCobras() {
      spaceMusic.play();

      shuffling = true;

      repaint();

      for (int i=0;i<cobraPositions.length;i++)
         blocks[cobraPositions[i]].clearCobra();

      cobraPositions = randomLocations(Integer.parseInt(cobras.getText()));
   
      for (int i=0;i<cobraPositions.length;i++)
         blocks[cobraPositions[i]].setCobra();

      try {
         Thread.sleep(1000);
      } catch (InterruptedException ie) {
      }

      for (int i=0;i<cobraPositions.length;i++)
         blocks[cobraPositions[i]].clearCobra();

      cobraPositions = randomLocations(Integer.parseInt(cobras.getText()));

      for (int i=0;i<cobraPositions.length;i++) {
         blocks[cobraPositions[i]].setCobra();
         blocks[cobraPositions[i]].hideCobra();
      }
   }

// This method places the blocks onto the screen.

   public void setUpCobras() {
      for (int i=1;i<101;i++) {
         int x = 20*((i-1) % 10);
         int y = 50 + 20*((i-1) / 10);

         container.add(blocks[i] = createMyButton("",20,20,x,y));

         blocks[i].setIndex(i);
         blocks[i].setRow((i-1) / 10);
         blocks[i].setColumn((i-1) % 10);

         blocks[i].setBorder(BorderFactory.createLineBorder(Color.gray));
      }
   }

   public void hideCobras() {
      Thread thread = new Thread(new HideCobras());

      spaceMusic.play();
   }   
   
   public void init() {
      container = getContentPane();

      container.setLayout(null);

      container.setBackground(Color.white);

      blocks = new MyButton[101];

      setUpCobras();

      container.add(output = new OutputPanel(200,200,200,100));

      gameOver = false;

      steppedOnCobra = false;

      spaceMusic = getAudioClip(getCodeBase(),"soundsavoidthecobras/spacemusic.au");
      gong = getAudioClip(getCodeBase(),"soundsavoidthecobras/gong.au");
      yahoo = getAudioClip(getCodeBase(),"soundsavoidthecobras/yahoo1.au");
   }

   public void paint(Graphics g) {
      for (int i=1;i<101;i++)
         blocks[i].repaint();

      start.repaint();

      cobras.repaint();

      score.repaint();

      Font originalFont = g.getFont();

      Font font = new Font("Serif",Font.BOLD,20);

      g.setFont(font);

      FontMetrics metrics = g.getFontMetrics();

      int width = metrics.stringWidth("Avoid The Cobras");

      g.drawString("Avoid The Cobras",getWidth()/2-width/2,20);

      g.setFont(originalFont);

      g.drawString("Number of Cobras",220,175);

      g.drawString("Score",259,225);

      if (steppedOnCobra) {
         width = metrics.stringWidth("You stepped on a cobra");

         g.drawString("You stepped on a cobra",getWidth()/2-width/2,300);
      }

      if (shuffling && !steppedOnCobra) {
         width = metrics.stringWidth("The cobras are moving");

         g.drawString("The cobras are moving",getWidth()/2-width/2,300);

         shuffling = false;
      } 
   }

   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == start) {
         score.setText("0");

         if (gameOver) {
            setUpCobras();

            gameOver = false;
         }

         hideCobras();
      } else if (!gameOver) {
         MyButton button = (MyButton)e.getSource();


         if (selectedBlocks == null) {
            int row = button.row();

            if (row == 9) {
               selectedBlocks = new MyButton[1];

               selectedBlocks[0] = button;

               yahoo.play();

               score.setText(String.valueOf(Integer.parseInt(score.getText())+20*numberOfCobras));

               highlightBlock(button,Color.blue);

               getAdjacencies();
            }
         } else {
            if (adjacent(button)) {
               if (button.getActionCommand().equals("C")) {
                  gong.play();

                  gameOver = true;

                  steppedOnCobra = true;

                  repaint();
               } else {
                  yahoo.play();

                  score.setText(String.valueOf(Integer.parseInt(score.getText())+20*numberOfCobras));

                  highlightBlock(button,Color.blue);

                  addToSelectedMatrix(button);

                  getAdjacencies();

                  shuffleCobras();
               }
            }
         }
      }
   }

}
