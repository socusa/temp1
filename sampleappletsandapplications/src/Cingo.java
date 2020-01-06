import java.awt.*;
import javax.swing.*;
import java.applet.*;
import java.awt.event.*;
import java.util.*;

/**

Program 3.

<h2><font face="Comic Sans MS" color=#8F0000>Programming Assignment 3 Solution

<br><br>

Spring Semester 2004</font></h2>

<b>Full Source Code</a>

<br><br>

<a href="Cingo.java">Cingo.java</a>

<br><br>

<b>Audio clips played at the beginning of the game.</b>

<br><br>

<li><a href=" ../../programmingassignments/program3/welcome.wav">welcome.wav</a>
<li><a href="../../programmingassignments/program3/welcome1.wav">welcome1.wav</a>
<li><a href="../../programmingassignments/program3/welcome2.wav">welcome2.wav</a>
<li><a href="../../programmingassignments/program3/welcome3.wav">welcome3.wav</a>
<li><a href="../../programmingassignments/program3/welcome4.wav">welcome4.wav</a>
<li><a href="../../programmingassignments/program3/welcome5.wav">welcome5.wav</a>

<br><br>

<b>Audio clip played when the user is told to pull numbers.</b>

<br><br>

<li><a href="../../programmingassignments/program3/pull.wav">pull.wav</a>

<br><br>

<b>Audio clip to play applause.</b>

<br><br>

<li><a href="../../programmingassignments/program3/applause.wav">applause.wav</a>

<br><br>

<b>Insults</b>

<br><br>

<li><a href="../../programmingassignments/program3/bozo.wav">bozo.wav</a>
<li><a href="../../programmingassignments/program3/donate.wav">donate.wav</a>
<li><a href="../../programmingassignments/program3/doofus.wav">doofus.wav</a>
<li><a href="../../programmingassignments/program3/dummy.wav">dummy.wav</a>
<li><a href="../../programmingassignments/program3/embycill.wav">embycill.wav</a>
<li><a href="../../programmingassignments/program3/idiot.wav">idiot.wav</a>
<li><a href="../../programmingassignments/program3/medic.wav">medic.wav</a>
<li><a href="../../programmingassignments/program3/moron.wav">moron.wav</a>
<li><a href="../../programmingassignments/program3/roadkill.wav">roadkill.wav</a>
<li><a href="../../programmingassignments/program3/slimeball.wav">slimeball.wav</a>
<li><a href="../../programmingassignments/program3/stupid.wav">stupid.wav</a>
<li><a href="../../programmingassignments/program3/wasteofoxygen.wav">wasteofoxygen.wav</a>
<li><a href="../../programmingassignments/program3/workatit.wav">workatit.wav</a>

*/

public class Cingo extends JApplet implements KeyListener {
/**

An instance variable to hold a reference to the panel where
the buttons corresponding to the characters in the word
are displayed.

*/
   private WordPanel wordPanel;
/**

An instance variable to hold a reference to the panel where
the countdown will be displayed.

*/
   private TimerPanel timerPanel;
/**

An instance variable to hold a reference to the panel where
the cingo board will be displayed.

*/
   private CingoBoardPanel cingoBoardPanel;
/**

An instance variable to hold a reference to the panel where
the score is displayed.

*/
   private ScorePanel scorePanel;
/**

A reference to a String array to hold the words that haven't
been used yet.

*/
   private String[] words;
/**

A reference to a String array to hold the word that have
already been used.

*/
   private String[] usedWords;
/**

An instance variable to hold the player's score.

*/
   private int score;
/**

A boolean to indicate whether the word panel or
cingo board panel is active.

*/
   private boolean wordPanelActive;
/**

A boolean to indicate whether the game is over.

*/
   private boolean gameOver;
/**

An AudioClip which tells the user to pull their
numbers.

*/
   private AudioClip pull;
/**

An AudioClip array which holds insults.

*/
   private AudioClip[] insults;

/**

A private method to pause the currently executing thread.
When this method is called, the thread that calls it will
pause for the number of seconds indicated.

<font size="4" color=#00008F>
<pre>
   private void pause(double seconds) {
      try {
         Thread.sleep((int)(seconds*1000));
      } catch (InterruptedException ie) {
      }
   }
</pre>
</font>

*/
   private void pause(double seconds) {
      try {
         Thread.sleep((int)(seconds*1000));
      } catch (InterruptedException ie) {
      }
   }

/**

A private method to create a JButton. This method
accepts appropriate information related to a button.
This include actionCommand which is a value related to
a button that isn't visible.

<font size="4" color=#00008F>
<pre>
   private JButton createJButton(String label,
                                 String actionCommand,
                                 int width,
                                 int height,
                                 int xlocation,
                                 int ylocation,
                                 ActionListener listener) {
      JButton button = new JButton(label);
      button.setSize(width,height);
      button.setLocation(xlocation,ylocation);

      button.setActionCommand(actionCommand);

      Insets insets = button.getInsets();

      insets.left = 0;
      insets.right = 0;

      button.setMargin(insets);

      button.addActionListener(listener);

      return(button);
   }
</pre>
</font>

*/
   private JButton createJButton(String label,
                                 String actionCommand,
                                 int width,
                                 int height,
                                 int xlocation,
                                 int ylocation,
                                 ActionListener listener) {
      JButton button = new JButton(label);
      button.setSize(width,height);
      button.setLocation(xlocation,ylocation);

      button.setActionCommand(actionCommand);

      Insets insets = button.getInsets();

      insets.left = 0;
      insets.right = 0;

      button.setMargin(insets);

      button.addActionListener(listener);

      return(button);
   }

/**

A private utility method to determine if a String is in
an array. This method is used to determine whether a particular
String is found in an array.

<font size="4" color=#00008F>
<pre>
   private boolean contains(String[] words,
                            String word) {
      boolean contains = false;

      for (int counter=0;counter&#60words.length & !contains;counter++)
         if (words[counter].equals(word))
            contains = true;

      return(contains);
   }
</pre>
</font>

*/
   private boolean contains(String[] words,
                            String word) {
      boolean contains = false;

      for (int counter=0;counter<words.length & !contains;counter++)
         if (words[counter].equals(word))
            contains = true;

      return(contains);
   }

/**

An instance method to produce the next word to guess.
This method attempts to pick a word from the original 
String array. However, if that word has already been
used, then we want to choose another one. So we check
the array usedWords to see if the word we've picked has
been used. If it has we keep picking new words until we
find a word that hasn't been used. Then we add that word
to usedWords.

<font size="4" color=#00008F>
<pre>
   private String getNewWord() {
      int random = (int)(words.length*Math.random());

      while (contains(usedWords,words[random]))
         random = (int)(words.length*Math.random());

      String[] temp = new String[usedWords.length+1];

      System.arraycopy(usedWords,0,temp,0,usedWords.length);

      temp[usedWords.length] = words[random];

      usedWords = temp;

      return(words[random]);
   }
</pre>
</font>

*/
   private String getNewWord() {
      int random = (int)(words.length*Math.random());

      while (contains(usedWords,words[random]))
         random = (int)(words.length*Math.random());

      String[] temp = new String[usedWords.length+1];

      System.arraycopy(usedWords,0,temp,0,usedWords.length);

      temp[usedWords.length] = words[random];

      usedWords = temp;

      return(words[random]);
   }

/**

A simple extension of thread used to play the introduction.
It contains an AudioClip array and a parallel array of delay
times. Once references to these arrays have been stored in the
instance variables, we start the thread. It plays each sound
from the AudioClip array and then pauses for the amount of time
indicated by the corresponding value in the delay array.

After all sounds have been played, the game is set up. We choose
a word and place buttons on the screen for each character of the
word. Then its starts the countdown add adds a key listener for the
applet. Note that the way we access a reference to the outer class
is with the outerclassname.

*/ 
   public class MusicThread extends Thread {
/**

An instance method to hold a reference to an array of audio clips.

*/
      private AudioClip[] clips;
/**

An instance method to hold a reference to an array of delays.

*/
      private double[] delay;

/**

A constructor to create a MusicThread. We simply initialize the
two instance variables and start the thread.

<font size="4" color=#00008F>
<pre>
      public MusicThread(AudioClip[] clips,
                         double[] delay) {
         this.clips = clips;
         this.delay = delay;

         start();
      }
</pre>
</font>

*/
      public MusicThread(AudioClip[] clips,
                         double[] delay) {
         this.clips = clips;
         this.delay = delay;

         start();
      }

/**

The inherited run method. We run through the array of audio clips
and play them and pause the thread. This makes sure that we can hear
all of the audio clips. After we have played all of the audio clips,
then we choose a word and set up the word panel. It will also start
the timer and add a key listener to the applet.

<font size="4" color=#00008F>
<pre>
      public void run() {
         for (int counter=0;counter&#60clips.length;counter++) {
            clips[counter].play();

            pause(delay[counter]);
         }

         String word = getNewWord();

         wordPanel.setWord(word);

         wordPanel.setIndex(0);

         timerPanel.start();

         cingoBoardPanel.repaint();

         Cingo.this.addKeyListener(Cingo.this);
      }
   }
</pre>
</font>

*/
      public void run() {
         for (int counter=0;counter<clips.length;counter++) {
            clips[counter].play();

            pause(delay[counter]);
         }

         String word = getNewWord();

         wordPanel.setWord(word);

         wordPanel.setIndex(0);

         timerPanel.start();

         cingoBoardPanel.repaint();

         Cingo.this.addKeyListener(Cingo.this);
      }
   }

/** The inner class WordButton.

This inner class is used to create an extension
of a JButton that will be used to display the
buttons for the characters in a word. This class
overrides the paint method so that this button's
background color and text color can be changed.

*/
   public class WordButton extends JButton {
/** 

An instance variable to hold the text color.
Since we have overridden the paint method, we
can change the background color.

*/
      private Color textColor;
/**

An instance variable to hold the label we want
to appear.

*/
      private String label;

/**

A constructor to create a WordButton. This
constructor accepts the label that we will
display as well as an action command for the
button. We also send the size and location.

<font size="4" color=#00008F>
<pre>
      public WordButton(String label,
                        String actionCommand,
                        int width,
                        int height,
                        int xlocation,
                        int ylocation) {
         this.label = label;         

         setSize(width,height);
         setLocation(xlocation,ylocation);

         setActionCommand(actionCommand);
 
         Insets insets = getInsets();

         insets.left = 0;
         insets.right = 0;

         setMargin(insets);

// Set the initial background color to gray.

         setBackground(new Color(204,204,204));
      }
</pre>
</font>

*/
      public WordButton(String label,
                        String actionCommand,
                        int width,
                        int height,
                        int xlocation,
                        int ylocation) {
         this.label = label;         

         setSize(width,height);
         setLocation(xlocation,ylocation);

         setActionCommand(actionCommand);
 
         Insets insets = getInsets();

         insets.left = 0;
         insets.right = 0;

         setMargin(insets);

// Set the initial background color to gray.

         setBackground(new Color(204,204,204));
      }

/**

An instance method to set the background and
text color. After these two are set we make sure
that the button is redrawn.

<font size="4" color=#00008F>
<pre>
      public void setColor(Color backGroundColor,
                           Color textColor) {
         setBackground(backGroundColor);
   
         this.textColor = textColor;

         repaint();
      }
</pre>
</font>

*/
      public void setColor(Color backGroundColor,
                           Color textColor) {
         setBackground(backGroundColor);
   
         this.textColor = textColor;

         repaint();
      }

/**

A get method. This method returns the background
color of the button.

<font size="4" color=#00008F>
<pre>
      public Color backgroundColor() {
         return(getBackground());
      }
</pre>
</font>

*/
      public Color backgroundColor() {
         return(getBackground());
      }

/**

A set method. This method sets the label of the
button and makes sure it's redrawn.

<font size="4" color=#00008F>
<pre>
      public void setButtonLabel(String label) {
         this.label = label;

         repaint();
      }
</pre>
</font>

*/
      public void setButtonLabel(String label) {
         this.label = label;

         repaint();
      }

/**

A get method. This method returns the label of
the button.

<font size="4" color=#00008F>
<pre>
      public String getButtonLabel() {
         return(label);
      }
</pre>
</font>

*/
      public String getButtonLabel() {
         return(label);
      }

/**

A private instance method. This method is used
to determine how much space is taken up by a
String using the font associated with a
graphics context.

<font size="4" color=#00008F>
<pre>
      private int width(Graphics g,
                        String string) {
         FontMetrics metrics = g.getFontMetrics();

         return(metrics.stringWidth(string));
      }
</pre>
</font>

*/
      private int width(Graphics g,
                        String string) {
         FontMetrics metrics = g.getFontMetrics();

         return(metrics.stringWidth(string));
      }

/**

A private instance method. This method will return
the amount of vertical space taken up by a font
associated with a graphics context.

<font size="4" color=#00008F>
<pre>
      private int height(Graphics g) {
         FontMetrics metrics = g.getFontMetrics();

         return(metrics.getHeight());
      }
</pre>
</font>

*/
      private int height(Graphics g) {
         FontMetrics metrics = g.getFontMetrics();

         return(metrics.getHeight());
      }

/**

The overridden paint method. This is the key
method in this class definition. Here we make the
best guess at a font size to use in the button. If
the width of the button is greater than or equal
to 50, then we use point size 40. Otherwise, we use
point size 20.

<font size="4" color=#00008F>
<pre>
      public void paint(Graphics g) {
         super.paint(g);

         Font font = (getWidth() >= 50)
                     ? new Font("Arial",Font.BOLD,40)
                     : new Font("Arial",Font.BOLD,20); 

         g.setFont(font);     

         g.setColor(textColor);
   
         g.drawString(label,getWidth()/2-width(g,label)/2,getHeight()/2+height(g)/4);
      }
   }
</pre>
</font>

*/
      public void paint(Graphics g) {
         super.paint(g);

         Font font = (getWidth() >= 50)
                     ? new Font("Arial",Font.BOLD,40)
                     : new Font("Arial",Font.BOLD,20); 

         g.setFont(font);     

         g.setColor(textColor);
   
         g.drawString(label,getWidth()/2-width(g,label)/2,getHeight()/2+height(g)/4);
      }
   }

/**

The inner class to display the buttons for the
characters in a word. We use several arrays to
hold information about the characters in the word.
We need to know how many have been filled in. Also
we need to know if the button should be turned red
or yellow.

*/
   public class WordPanel extends JPanel implements Runnable {
/**

An instance variable to hold the word being guessed.

*/
      private String word;
/**

An array used to hold the buttons which will represent
the characters.

*/
      private WordButton[] buttons;
/**

An array to determine which indices in the array
have been guessed already.

*/
      private int[] free;
/**

A variable to indicate an index.

*/
      private int index;
/**

This array is used to indicate whether a letter
has already been marked as being in the word.

*/
      private boolean[] yellow;

/**

A constructor to create the word panel. We simply
set the size and location. We take away the layout
manager of the panel and set it's initial background
color to dark blue.

<font size="4" color=#00008F>
<pre>
      public WordPanel(int width,
                       int height,
                       int xlocation,
                       int ylocation) {
         setSize(width,height);
         setLocation(xlocation,ylocation);

         setLayout(null);

         setBackground(new Color(0,0,200));
      }
</pre>
</font>

*/
      public WordPanel(int width,
                       int height,
                       int xlocation,
                       int ylocation) {
         setSize(width,height);
         setLocation(xlocation,ylocation);

         setLayout(null);

         setBackground(new Color(0,0,200));
      }

/**

A set method. This method sets the value of index.

<font size="4" color=#00008F>
<pre>
      public void setIndex(int index) {
         this.index = index;
      }
</pre>
</font>

*/
      public void setIndex(int index) {
         this.index = index;
      }

/**

A set method. This method sets the background and
text color of the button at position index. We make
sure the button is redrawn.

<font size="4" color=#00008F>
<pre>
      public void setColor(int index,
                           Color backGroundColor,
                           Color textColor) {
         buttons[index].setColor(backGroundColor,textColor);

         repaint();
      }
</pre>
</font>

*/
      public void setColor(int index,
                           Color backGroundColor,
                           Color textColor) {
         buttons[index].setColor(backGroundColor,textColor);

         repaint();
      }

/**

An instance method to place a character into a button. When
the user types a character, this method is called to place
that character into the button whose index is index. When a
player has typed into characters into all the buttons whose
indices are in the free array, then we set a boolean to
indicate that we should ignore any key the user types, and
then we start this panel's thread.

<font size="4" color=#00008F>
<pre>
      public void setCharacter(char character) {
         if (index <= free.length) {
            if (buttons[free[index]].backgroundColor() == Color.yellow)
               setColor(free[index],new Color(204,204,204),Color.black);

            buttons[free[index]].setButtonLabel(character + "");

            index++;
         }
         
         if (index == free.length) {
            wordPanelActive = false;

            Thread thread = new Thread(this);

            thread.start();
         }
      }
</pre>
</font>

*/
      public void setCharacter(char character) {
         if (index <= free.length) {
            if (buttons[free[index]].backgroundColor() == Color.yellow)
               setColor(free[index],new Color(204,204,204),Color.black);

            buttons[free[index]].setButtonLabel(character + "");

            index++;
         }
         
         if (index == free.length) {
            wordPanelActive = false;

            Thread thread = new Thread(this);

            thread.start();
         }
      }

/**

An instance method to place the buttons onto the panel.
We set the instance method word, and then create a button
array and set the action commands of the buttons to be
the characters in the word.

<font size="4" color=#00008F>
<pre>
      public void setWord(String word) {
         this.word = word;

// Remove any components in the panel.

         removeAll();

         buttons = new WordButton[word.length()];

// Make the width of the buttons so that the array
// of buttons fills up the panel horizontally.

         int buttonWidth = getWidth()/word.length();

// Here we place the buttons onto the panel, setting
// the action command to be the character associated
// with the button.

         for (int counter=0;counter&#60buttons.length;counter++)
            add(buttons[counter] = new WordButton("",
                                                  word.substring(counter,counter+1),
                                                  buttonWidth,buttonWidth,
                                                  getWidth()/2-word.length()*buttonWidth/2+counter*buttonWidth,
                                                  getHeight()/2-buttonWidth/2));

// Here we initialize the free array.

         free = new int[word.length()];

// We now place all of the indices of the button array
// into free.
       
         for (int counter=0;counter&#60free.length;counter++)
            free[counter] = counter;
 
         repaint();
      }
</pre>
</font>

*/
      public void setWord(String word) {
         this.word = word;

// Remove any components in the panel.

         removeAll();

         buttons = new WordButton[word.length()];

// Make the width of the buttons so that the array
// of buttons fills up the panel horizontally.

         int buttonWidth = getWidth()/word.length();

// Here we place the buttons onto the panel, setting
// the action command to be the character associated
// with the button.

         for (int counter=0;counter<buttons.length;counter++)
            add(buttons[counter] = new WordButton("",
                                                  word.substring(counter,counter+1),
                                                  buttonWidth,buttonWidth,
                                                  getWidth()/2-word.length()*buttonWidth/2+counter*buttonWidth,
                                                  getHeight()/2-buttonWidth/2));

// Here we initialize the free array.

         free = new int[word.length()];

// We now place all of the indices of the button array
// into free.
       
         for (int counter=0;counter<free.length;counter++)
            free[counter] = counter;
 
         repaint();
      }

/**

An instance method used to display all of the characters in the
word. When time has run out we display all of the characters in
the word.

<font size="4" color=#00008F>
<pre>
      public void showWord() {
         for (int counter=0;counter&#60buttons.length;counter++) {
            setColor(counter,new Color(204,204,204),Color.black);

            buttons[counter].setButtonLabel(buttons[counter].getActionCommand());
         }
      }
</pre>
</font>

*/
      public void showWord() {
         for (int counter=0;counter<buttons.length;counter++) {
            setColor(counter,new Color(204,204,204),Color.black);

            buttons[counter].setButtonLabel(buttons[counter].getActionCommand());
         }
      }

/**

The overridden paint method. All we do here is make sure
that all lightweight components are redrawn.

<font size="4" color=#00008F>
<pre>
      public void paint(Graphics g) {
         super.paint(g);
      }
</pre>
</font>

*/
      public void paint(Graphics g) {
         super.paint(g);
      }

/**

An instance method to determine where in the free
array an index is found. Recall that the free array
contains a list of indices.

<font size="4" color=#00008F>
<pre>
      private int location(int index) {
         int location = -1;

         for (int counter=0;counter&#60free.length;counter++)
            location = (free[counter] == index) ? counter : location;

         return(location);
      }
</pre>
</font>

*/
      private int location(int index) {
         int location = -1;

         for (int counter=0;counter<free.length;counter++)
            location = (free[counter] == index) ? counter : location;

         return(location);
      }

/**

A method to remove an index from the free array.
Once a player has correctly guessed a character
at a certain position, we want to remove that position
from the free array. We do this by creating a new
array and copying all but the element we want to
remove into it. Then we point free to the new array.

<font size="4" color=#00008F>
<pre>
      private void removeIndex(int index) {
         int[] temp = new int[free.length-1];

         int location = location(index);

         System.arraycopy(free,0,temp,0,location);
         System.arraycopy(free,location+1,temp,location,free.length-location-1);

         free = temp;
      }
</pre>
</font>

*/
      private void removeIndex(int index) {
         int[] temp = new int[free.length-1];

         int location = location(index);

         System.arraycopy(free,0,temp,0,location);
         System.arraycopy(free,location+1,temp,location,free.length-location-1);

         free = temp;
      }

/**

A method to remove a set of indices. This method
simply removes an array of indices from free.

<font size="4" color=#00008F>
<pre>
      private void removeIndices(int[] indices) {
         for (int counter=0;counter&#60indices.length;counter++)
            removeIndex(indices[counter]);
      }
</pre>
</font>

*/
      private void removeIndices(int[] indices) {
         for (int counter=0;counter<indices.length;counter++)
            removeIndex(indices[counter]);
      }

/**

An instance method to determine if a character is found
beginning at a certain position. This method is used to
determine if we should color a button yellow. We only want
to color a button yellow if it contains a letter that is
in the word. But we don't want to color every instance of
the letter yellow, only one.

<font size="4" color=#00008F>
<pre>
      private boolean characterInWord(char character,
                                      int index) {
         boolean found = false;

         boolean characterPlaced = false;

// We now set a boolean to indicate whether the
// character shows up at another position starting
// at index.

         for (int counter=index;counter&#60free.length;counter++)
            characterPlaced = ((buttons[free[counter]].getActionCommand().charAt(0) ==
                                buttons[free[counter]].getButtonLabel().charAt(0)) &&
                               (buttons[free[counter]].getButtonLabel().charAt(0) ==
                                character))
                              ? true
                              : characterPlaced;

// We now set a boolean to indicate whether the
// character we are looking for is found.

         for (int counter=0;counter&#60free.length;counter++)
            found = (buttons[free[counter]].getActionCommand().charAt(0) ==
                     character)
                    ? true
                    : found;

// A character is found is found is true, but it's not found in a
// position after index, and this character hasn't already been
// colored yellow.

         return(found && !characterPlaced && !yellow[(int)character]);
      }
</pre>
</font>

*/
      private boolean characterInWord(char character,
                                      int index) {
         boolean found = false;

         boolean characterPlaced = false;

// We now set a boolean to indicate whether the
// character shows up at another position starting
// at index.

         for (int counter=index;counter<free.length;counter++)
            characterPlaced = ((buttons[free[counter]].getActionCommand().charAt(0) ==
                                buttons[free[counter]].getButtonLabel().charAt(0)) &&
                               (buttons[free[counter]].getButtonLabel().charAt(0) ==
                                character))
                              ? true
                              : characterPlaced;

// We now set a boolean to indicate whether the
// character we are looking for is found.

         for (int counter=0;counter<free.length;counter++)
            found = (buttons[free[counter]].getActionCommand().charAt(0) ==
                     character)
                    ? true
                    : found;

// A character is found is found is true, but it's not found in a
// position after index, and this character hasn't already been
// colored yellow.

         return(found && !characterPlaced && !yellow[(int)character]);
      }

/**

The inherited run method. We use this method to search through
the button array to see if any of the characters are correct. If
the character is correct, then we turn that button's background
red. If the character is not correct, but is found in the word,
then we turn that button's background yellow. We use the yellow
array and the fact that casting a character to an integer produces
the ASCII value of the character to determine if we have already
set a button yellow containing that character. This array is reset
to a new boolean array each time we run this thread.

<font size="4" color=#00008F>
<pre>
      public void run() {
         int[] correct = new int[0];

         yellow = new boolean[255];

         for (int counter=0;counter&#60free.length;counter++) {
            if (buttons[free[counter]].getButtonLabel().charAt(0) ==
                buttons[free[counter]].getActionCommand().charAt(0)) {
               setColor(free[counter],Color.red,Color.white);

               int[] temp = new int[correct.length+1];

               System.arraycopy(correct,0,temp,0,correct.length);
               
               temp[correct.length] = free[counter];

               correct = temp;

               getAudioClip(getCodeBase(),"quack.au").play();

               yellow[(int)(buttons[free[counter]].getButtonLabel().charAt(0))] = true;
            } else if (characterInWord(buttons[free[counter]].getButtonLabel().charAt(0),counter)) {
               setColor(free[counter],Color.yellow,new Color(0,0,200));

               yellow[(int)(buttons[free[counter]].getButtonLabel().charAt(0))] = true;
            } else {
               buttons[free[counter]].setButtonLabel("");
            }

            pause(0.5);
         }

// We remove from the free array any index where we have
// a correct character.

         removeIndices(correct);

// We reset index back to 0;

         index = 0;

// The word panel is now active so when the user clicks
// a button that character will show up.

         wordPanelActive = true;

// If the user got all of the characters correct, then
// we update the score panel, make the word panel inactive
// so that clicking a key won't affect the panel, and we
// start the Cingo board panel. If all of the characters
// aren't correct, the player is insulted.

         if (free.length == 0) {
            scorePanel.updateScore(25);

            wordPanelActive = false;

            cingoBoardPanel.start();            
         } else
            playInsult();
      }
   }
</pre>
</font>

*/
      public void run() {
         int[] correct = new int[0];

         yellow = new boolean[255];

         for (int counter=0;counter<free.length;counter++) {
            if (buttons[free[counter]].getButtonLabel().charAt(0) ==
                buttons[free[counter]].getActionCommand().charAt(0)) {
               setColor(free[counter],Color.red,Color.white);

               int[] temp = new int[correct.length+1];

               System.arraycopy(correct,0,temp,0,correct.length);
               
               temp[correct.length] = free[counter];

               correct = temp;

               getAudioClip(getCodeBase(),"soundscingo/quack.au").play();

               yellow[(int)(buttons[free[counter]].getButtonLabel().charAt(0))] = true;
            } else if (characterInWord(buttons[free[counter]].getButtonLabel().charAt(0),counter)) {
               setColor(free[counter],Color.yellow,new Color(0,0,200));

               yellow[(int)(buttons[free[counter]].getButtonLabel().charAt(0))] = true;
            } else {
               buttons[free[counter]].setButtonLabel("");
            }

            pause(0.5);
         }

// We remove from the free array any index where we have
// a correct character.

         removeIndices(correct);

// We reset index back to 0;

         index = 0;

// The word panel is now active so when the user clicks
// a button that character will show up.

         wordPanelActive = true;

// If the user got all of the characters correct, then
// we update the score panel, make the word panel inactive
// so that clicking a key won't affect the panel, and we
// start the Cingo board panel. If all of the characters
// aren't correct, the player is insulted.

         if (free.length == 0) {
            scorePanel.updateScore(25);

            wordPanelActive = false;

            cingoBoardPanel.start();            
         } else
            playInsult();
      }
   }

/**

An inner class to create a timer. This panel runs in its
own thread. We determine how many seconds we want to play
the game. Then we determine the target number of seconds.
We then run a thread which will keep asking how much
time is left, and displaying it until the game is over.

*/
   public class TimerPanel extends JPanel implements Runnable {
/**

An instance variable to hold how many seconds are left in
the game.

*/
      private int secondsLeft;

/**

An instance method which is used to determine how many
seconds are between now and midnight. We create an
instance of a Calendar object and ask it what the current
hour, minute, and second are.

<font size="4" color=#00008F>
<pre>
      private int currentSeconds() {
         Calendar c = Calendar.getInstance();

         int hour = c.get(Calendar.HOUR_OF_DAY);
         int minute = c.get(Calendar.MINUTE);
         int second = c.get(Calendar.SECOND);

         return(hour*60*60+minute*60+second);
      }
</pre>
</font>

*/
      private int currentSeconds() {
         Calendar c = Calendar.getInstance();

         int hour = c.get(Calendar.HOUR_OF_DAY);
         int minute = c.get(Calendar.MINUTE);
         int second = c.get(Calendar.SECOND);

         return(hour*60*60+minute*60+second);
      }

/**

A constructor to create the timer. We set the size
and location and set the background color to dark
blue.

<font size="4" color=#00008F>
<pre>
      public TimerPanel(int width,
                        int height,
                        int xlocation,
                        int ylocation) {
         setSize(width,height);
         setLocation(xlocation,ylocation);

         setBackground(new Color(0,0,200));
      }
</pre>
</font>

*/
      public TimerPanel(int width,
                        int height,
                        int xlocation,
                        int ylocation) {
         setSize(width,height);
         setLocation(xlocation,ylocation);

         setBackground(new Color(0,0,200));
      }

/**

An instance method to start the timer. This method
is used to start the thread.

<font size="4" color=#00008F>
<pre>
      public void start() {
         Thread thread = new Thread(this);

         thread.start();
      }
</pre>
</font>

*/
      public void start() {
         Thread thread = new Thread(this);

         thread.start();
      }

/**

An instance method to determine how much width is
taken up by a String in a font associated with a
graphics context.

<font size="4" color=#00008F>
<pre>
      private int width(Graphics g,
                        String string) {
         FontMetrics metrics = g.getFontMetrics();

         return(metrics.stringWidth(string));
      }
</pre>
</font>

*/
      private int width(Graphics g,
                        String string) {
         FontMetrics metrics = g.getFontMetrics();

         return(metrics.stringWidth(string));
      }

/**

An instance method to determine how much height is
taken up by a font associated with a graphics context.

<font size="4" color=#00008F>
<pre>
      private int height(Graphics g) {
         FontMetrics metrics = g.getFontMetrics();

         return(metrics.getHeight());
      }
</pre>
</font>

*/
      private int height(Graphics g) {
         FontMetrics metrics = g.getFontMetrics();

         return(metrics.getHeight());
      }

/**

The overridden paint method. This paint method makes
sure that all lightweight components are redrawn. It
also breaks down the number of seconds left into
seconds and minutes and formats the string to be
displayed.

<font size="4" color=#00008F>
<pre>
      public void paint(Graphics g) {
         super.paint(g);

         int minutes = secondsLeft/60;
         int seconds = secondsLeft % 60;

         String time = minutes + ":";
         time += (seconds < 10) ? "0" + seconds : "" + seconds;

         g.setFont(new Font("Arial Black",Font.BOLD,20));

         g.setColor(Color.white);

         g.drawString(time,getWidth()/2-width(g,time)/2,getHeight()/2+height(g)/4);
      }
</pre>
</font>

*/
      public void paint(Graphics g) {
         super.paint(g);

         int minutes = secondsLeft/60;
         int seconds = secondsLeft % 60;

         String time = minutes + ":";
         time += (seconds < 10) ? "0" + seconds : "" + seconds;

         g.setFont(new Font("Arial Black",Font.BOLD,20));

         g.setColor(Color.white);

         g.drawString(time,getWidth()/2-width(g,time)/2,getHeight()/2+height(g)/4);
      }

/**

The inherited run method. We retrieve from the parameters sent
to the applet the amount of seconds the game should last. We then
determine what the target is. We then set up a loop which keeps
going until the number of seconds left is 0. When time runs out
we indicate that the game is over, stop the audio clip in the
Cingo board panel if its playing, sets the score panel to say
Final score, play a sound, and show all of the characters in
the word that the player is guessing.

<font size="4" color=#00008F>
<pre>
      public void run() {
         int target = new Integer(getParameter("duration")).intValue()
                      + currentSeconds();

         secondsLeft = target-currentSeconds();

         while (secondsLeft > 0) {
            repaint();

            pause(0.1);

            secondsLeft = target-currentSeconds();
         }

         secondsLeft = 0;

         repaint();

         gameOver = true;

         Cingo.this.pull.stop();

         scorePanel.setTitle("Final");

         getAudioClip(getCodeBase(),"deeptone.wav").play();

         wordPanel.showWord();
      }
   }
</pre>
</font>

*/
      public void run() {
         int target = new Integer(getParameter("duration")).intValue()
                      + currentSeconds();

         secondsLeft = target-currentSeconds();

         while (secondsLeft > 0) {
            repaint();

            pause(0.1);

            secondsLeft = target-currentSeconds();
         }

         secondsLeft = 0;

         repaint();

         gameOver = true;

         Cingo.this.pull.stop();

         scorePanel.setTitle("Final");

         getAudioClip(getCodeBase(),"soundscingo/deeptone.wav").play();

         wordPanel.showWord();
      }
   }

/**

An inner class we use to define a button to display in
the Cingo panel. We override the paint method so we can
make the button circular.

*/
   public class MyButton extends JButton {
/**

An instance variable to hold the number to be displayed
in the button.

*/
      private int number;
/**

An instance variable to hold the background color of the
button.

*/
      private Color color;
/**

An instance variable to hold the text color of the button.

*/
      private Color textColor;

/**

A constructor to create an instance of MyButton. We set
the label of the button, and its size and location.
Initially the background color is yellow, and the text
color is black.

<font size="4" color=#00008F>
<pre>
      public MyButton(String label,
                      int width,
                      int height,
                      int xlocation,
                      int ylocation) {
         super(label);

         setSize(width,height);
         setLocation(xlocation,ylocation);

         color = Color.yellow;

         textColor = Color.black;
      }
</pre>
</font>

*/
      public MyButton(String label,
                      int width,
                      int height,
                      int xlocation,
                      int ylocation) {
         super(label);

         setSize(width,height);
         setLocation(xlocation,ylocation);

         color = Color.yellow;

         textColor = Color.black;
      }

/**

A set method. An instance method to set the number to
be displayed in the button.

<font size="4" color=#00008F>
<pre>
      public void setNumber(int number) {
         this.number = number;

         repaint();
      }
</pre>
</font>

*/
      public void setNumber(int number) {
         this.number = number;

         repaint();
      }

/**

A set method. An instance method to set the background
color to be used in the button.

<font size="4" color=#00008F>
<pre>
      public void setColor(Color color) {
         this.color = color;

         repaint();
      }
</pre>
</font>

*/
      public void setColor(Color color) {
         this.color = color;

         repaint();
      }

/**

A set method. An instance method to set the text color
of the button.

<font size="4" color=#00008F>
<pre>
      public void setTextColor(Color textColor) {
         this.textColor = textColor;

         repaint();
      }
</pre>
</font>

*/
      public void setTextColor(Color textColor) {
         this.textColor = textColor;

         repaint();
      }

/**

An instance method to determine the amount of space
taken up by a String in a font associated with a
graphics context.

<font size="4" color=#00008F>
<pre>
      private int width(Graphics g,
                        String string) {
         FontMetrics metrics = g.getFontMetrics();

         return(metrics.stringWidth(string));
      }
</pre>
</font>

*/
      private int width(Graphics g,
                        String string) {
         FontMetrics metrics = g.getFontMetrics();

         return(metrics.stringWidth(string));
      }

/**

The overridden paint method. We first make sure all
lightweight components are redrawn. Then we create
and fill an oval with the background color, and the
text color to the text color. We then draw the number.

<font size="4" color=#00008F>
<pre>
      public void paint(Graphics g) {
         g.setColor(color);

         g.fillOval(0,0,20,20);

         g.setColor(textColor);

         g.drawString(number + "",getWidth()/2-width(g,number + "")/2+1,15);
      }
</pre>
</font>

*/
      public void paint(Graphics g) {
         g.setColor(color);

         g.fillOval(0,0,20,20);

         g.setColor(textColor);

         g.drawString(number + "",getWidth()/2-width(g,number + "")/2+1,15);
      }
   }

   public class CingoBoardPanel extends JPanel implements Runnable {
/**

An instance variable to hold the buttons in the Cingo board.

*/
      private MyButton[] buttons;
/**

An instance variable to hold four buttons. These buttons will
be displayed to indicate how many pulls the player has done.

*/
      private JButton[] pulls;
/**

An instance variable to hold the random numbers.

*/
      private int[] numbers;
/**

An instance variable to hold the indices which have already
been used.

*/
      private int[] usedIndices;
/**

An instance variable to indicate the pull number.

*/
      private int pull;
/**

An instance variable to refer to  a magic square to determine if the
player has filled in the board.

*/
      private int[] magicSquare;
/**

An instance variable to refer to an array of indices that have been filled
in.

*/
      private int[] filledIn;
/**

An instance variable to hold the title to be displayed.

*/ 
     private String title;

/**

An instance method to determine if a number is in an array.
We simply search through the array until we find the number
of have examined all of the numbers in the array. We then
return a boolean to indicate if we found the number.

<font size="4" color=#00008F>
<pre>
      private boolean contains(int[] numbers,
                               int number) {
         boolean contains = false;

         for (int counter=0;counter&#60numbers.length & !contains;counter++)
            if (numbers[counter] == number)
               contains = true;

         return(contains);
      }
</pre>
</font>

*/
      private boolean contains(int[] numbers,
                               int number) {
         boolean contains = false;

         for (int counter=0;counter<numbers.length & !contains;counter++)
            if (numbers[counter] == number)
               contains = true;

         return(contains);
      }

/**

An instance method to generate random numbers. We begin
by creating a reference to an empty array. As long as the
length of that array is less than 25, we pick another random
number that we haven't already picked, and then add that to
the array.

<font size="4" color=#00008F>
<pre>
      private int[] generateNumbers() {
         int[] numbers = new int[0];

         while (numbers.length < 25) {
            int number = (int)(100*Math.random());

            while (contains(numbers,number))
               number = (int)(100*Math.random());

            int[] temp = new int[numbers.length+1];

            System.arraycopy(numbers,0,temp,0,numbers.length);

            temp[numbers.length] = number;

            numbers = temp;
         }

         return(numbers);
      }
</pre>
</font>

*/
      private int[] generateNumbers() {
         int[] numbers = new int[0];

         while (numbers.length < 25) {
            int number = (int)(100*Math.random());

            while (contains(numbers,number))
               number = (int)(100*Math.random());

            int[] temp = new int[numbers.length+1];

            System.arraycopy(numbers,0,temp,0,numbers.length);

            temp[numbers.length] = number;

            numbers = temp;
         }

         return(numbers);
      }

/**

An instance method to display the numbers. After we generate
the random numbers, we place them in the buttons.

<font size="4" color=#00008F>
<pre>
      public void setNumbers() {
         numbers = generateNumbers();

         for (int counter=0;counter&#60buttons.length;counter++)
            buttons[counter].setNumber(numbers[counter]);
      }
</pre>
</font>

*/
      public void setNumbers() {
         numbers = generateNumbers();

         for (int counter=0;counter<buttons.length;counter++)
            buttons[counter].setNumber(numbers[counter]);
      }

/**

An instance method to place numbers in the magic square.

<font size="4" color=#00008F>
<pre>
      private int[] magicSquare() {
         int[] temp = {3,7,14,16,25,
                       11,20,23,2,9,
                       22,4,6,15,18,
                       10,13,17,24,1,
                       19,21,5,8,12};

         return(temp);
      }
</pre>
</font>

*/
      private int[] magicSquare() {
         int[] temp = {3,7,14,16,25,
                       11,20,23,2,9,
                       22,4,6,15,18,
                       10,13,17,24,1,
                       19,21,5,8,12};

         return(temp);
      }

/**

An instance method to determine if we have filled in
a full row, column, or diagonal. We form a dot product
between the filled in array and the magic square. If
any of the sums of the rows, columns, or diagonals are
65, this means that one row, column, or diagonal was
filled in.

<font size="4" color=#00008F>
<pre>
      private boolean checkSums() {
         for (int counter=0;counter<5;counter++) {
            int sum = 0;

            for (int counter1=0;counter1<5;counter1++)
               sum += magicSquare[counter*5+counter1]*
                      filledIn[counter*5+counter1];

            if (sum == 65)
               return(true);
         }

         for (int counter=0;counter<5;counter++) {
            int sum = 0;

            for (int counter1=0;counter1<5;counter1++)
               sum += magicSquare[counter % 5+counter1*5]*
                      filledIn[counter % 5+counter1*5];

            if (sum == 65)
               return(true);
         }

         int sum = 0;

         for (int counter=0;counter<5;counter++)
            sum += magicSquare[counter*6]*filledIn[counter*6];

         if (sum == 65)
            return(true);

         sum = 0;

         for (int counter=0;counter<5;counter++)
            sum += magicSquare[20-(4-counter)*4]*
                   filledIn[20-(4-counter)*4];

         if (sum == 65)
            return(true);

         return(false);
      }
</pre>
</font>

*/
      private boolean checkSums() {
         for (int counter=0;counter<5;counter++) {
            int sum = 0;

            for (int counter1=0;counter1<5;counter1++)
               sum += magicSquare[counter*5+counter1]*
                      filledIn[counter*5+counter1];

            if (sum == 65)
               return(true);
         }

         for (int counter=0;counter<5;counter++) {
            int sum = 0;

            for (int counter1=0;counter1<5;counter1++)
               sum += magicSquare[counter % 5+counter1*5]*
                      filledIn[counter % 5+counter1*5];

            if (sum == 65)
               return(true);
         }

         int sum = 0;

         for (int counter=0;counter<5;counter++)
            sum += magicSquare[counter*6]*filledIn[counter*6];

         if (sum == 65)
            return(true);

         sum = 0;

         for (int counter=0;counter<5;counter++)
            sum += magicSquare[20-(4-counter)*4]*
                   filledIn[20-(4-counter)*4];

         if (sum == 65)
            return(true);

         return(false);
      }

/**

A constructor to create the cingo board panel. We set
the size and location and remove its layout manager.

<font size="4" color=#00008F>
<pre>
      public CingoBoardPanel(int width,
                             int height,
                             int xlocation,
                             int ylocation) {
         setSize(width,height);
         setLocation(xlocation,ylocation);

         setLayout(null);

         setBackground(new Color(51,255,208));

         buttons = new MyButton[25];

         for (int counter=0;counter&#60buttons.length;counter++)
            add(buttons[counter] = new MyButton("",
                                                20,20,
                                                getWidth()/2-50 + (counter%5)*20,
                                                getHeight()/2-50 + (counter/5)*20));

         setNumbers();

         usedIndices = new int[0];

         magicSquare = magicSquare();

         filledIn = new int[25];

         title = "";
      }
</pre>
</font>

*/
      public CingoBoardPanel(int width,
                             int height,
                             int xlocation,
                             int ylocation) {
         setSize(width,height);
         setLocation(xlocation,ylocation);

         setLayout(null);

         setBackground(new Color(51,255,208));

         buttons = new MyButton[25];

         for (int counter=0;counter<buttons.length;counter++)
            add(buttons[counter] = new MyButton("",
                                                20,20,
                                                getWidth()/2-50 + (counter%5)*20,
                                                getHeight()/2-50 + (counter/5)*20));

         setNumbers();

         usedIndices = new int[0];

         magicSquare = magicSquare();

         filledIn = new int[25];

         title = "";
      }

/**

An instance method to start the thread running.

<font size="4" color=#00008F>
<pre>
      public void start() {
         Thread thread = new Thread(this);

         thread.start();
      }
</pre>
</font>

*/
      public void start() {
         Thread thread = new Thread(this);

         thread.start();
      }

/**

An instance method to pull a number. If we still have
pulls left, then we randomly pick another number, and
change the background and text color of the button where
the number is displayed. After each pull we check to see
if a full row, column, or diagonal has been filled in. If
we have made four pulls, then we make the Cingo board
panel inactive and the word panel active. If we have filled
in a full row, column, or diagonal then we create a new
Cingo board panel, and reset the numbers and filled in array.

<font size="4" color=#00008F>
<pre>
      public void pullNumber() {
         if (pull < 4) {
            pull++;

            int index = (int)(25*Math.random());

            while (contains(usedIndices,index))
               index = (int)(25*Math.random());

            filledIn[index] = 1;           

            buttons[index].setColor(Color.red);
            buttons[index].setTextColor(Color.white);

            int[] temp = new int[usedIndices.length+1];

            System.arraycopy(usedIndices,0,temp,0,usedIndices.length);

            temp[usedIndices.length] = index;

            usedIndices = temp;

            remove(pulls[pull-1]);

            repaint();

            if (checkSums()) {
               getAudioClip(getCodeBase(),"applause.wav").play();

               scorePanel.updateScore(100);

               wordPanel.setWord(getNewWord());

               wordPanelActive = true;

               setNumbers();

               usedIndices = new int[0];

               filledIn = new int[25];

               for (int counter=0;counter&#60buttons.length;counter++) {
                  buttons[counter].setColor(Color.yellow);
                  buttons[counter].setTextColor(Color.black);
               }

               for (int counter=pull;counter<4;counter++)
                  remove(pulls[counter]);

               pull = 0;

               title = "You got it!";

               repaint();
            }
         }

         if (pull == 4) {
            wordPanel.setWord(getNewWord());

            wordPanel.setIndex(0);

            wordPanelActive = true;

            pull = 0;
         }         
      }
</pre>
</font>

*/

      public void pullNumber() {
         if (pull < 4) {
            pull++;

            int index = (int)(25*Math.random());

            while (contains(usedIndices,index))
               index = (int)(25*Math.random());

            filledIn[index] = 1;           

            buttons[index].setColor(Color.red);
            buttons[index].setTextColor(Color.white);

            int[] temp = new int[usedIndices.length+1];

            System.arraycopy(usedIndices,0,temp,0,usedIndices.length);

            temp[usedIndices.length] = index;

            usedIndices = temp;

            remove(pulls[pull-1]);

            repaint();

            if (checkSums()) {
               getAudioClip(getCodeBase(),"soundscingo/applause.wav").play();

               scorePanel.updateScore(100);

               wordPanel.setWord(getNewWord());

               wordPanelActive = true;

               setNumbers();

               usedIndices = new int[0];

               filledIn = new int[25];

               for (int counter=0;counter<buttons.length;counter++) {
                  buttons[counter].setColor(Color.yellow);
                  buttons[counter].setTextColor(Color.black);
               }

               for (int counter=pull;counter<4;counter++)
                  remove(pulls[counter]);

               pull = 0;

               title = "You got it!";

               repaint();
            }
         }

         if (pull == 4) {
            wordPanel.setWord(getNewWord());

            wordPanel.setIndex(0);

            wordPanelActive = true;

            pull = 0;
         }         
      }

/**

The overridden paint method. We make sure that all
lightweight components are redrawn. We then draw a
small border around the panel and display the title.

<font size="4" color=#00008F>
<pre>
      public void paint(Graphics g) {
         super.paint(g);

         g.setColor(Color.black);

         g.fillRect(0,0,getWidth(),2);

         g.fillRect(getWidth()-2,0,getWidth()-2,getHeight());

         g.fillRect(0,getHeight()-2,getWidth(),getHeight());

         g.fillRect(0,0,2,getHeight());

         g.setFont(new Font("Comic Sans MS",Font.BOLD,14));

         FontMetrics metrics = g.getFontMetrics();

         int width = metrics.stringWidth(title);

         g.drawString(title,getWidth()/2-width/2,20);
      }
</pre>
</font>

*/
      public void paint(Graphics g) {
         super.paint(g);

         g.setColor(Color.black);

         g.fillRect(0,0,getWidth(),2);

         g.fillRect(getWidth()-2,0,getWidth()-2,getHeight());

         g.fillRect(0,getHeight()-2,getWidth(),getHeight());

         g.fillRect(0,0,2,getHeight());

         g.setFont(new Font("Comic Sans MS",Font.BOLD,14));

         FontMetrics metrics = g.getFontMetrics();

         int width = metrics.stringWidth(title);

         g.drawString(title,getWidth()/2-width/2,20);
      }

/**

A set method. This method is used to set the title.

<font size="4" color=#00008F>
<pre>
      public void setTitle(String title) {
         this.title = title;

         repaint();
      }
</pre>
</font>

*/
      public void setTitle(String title) {
         this.title = title;

         repaint();
      }

/**

The inherited run method. This method simply places the buttons
on the panel, plays applause, and plays a message if the game
isn't over.

<font size="4" color=#00008F>
<pre>
      public void run() {
         if (!gameOver) {
            pulls = new JButton[4];

            for (int counter=0;counter&#60pulls.length;counter++)
               add(pulls[counter] = createJButton("Pull " + (counter+1),"",50,20,20,20+counter*30,null));

            getAudioClip(getCodeBase(),"applause.wav").play();

            pause(5);

            if (!gameOver)
               Cingo.this.pull.play();

            repaint();
         }
      }
</pre>
</font>

*/
      public void run() {
         if (!gameOver) {
            pulls = new JButton[4];

            for (int counter=0;counter<pulls.length;counter++)
               add(pulls[counter] = createJButton("Pull " + (counter+1),"",50,20,20,20+counter*30,null));
            
            Cingo.this.requestFocus();

            getAudioClip(getCodeBase(),"soundscingo/applause.wav").play();

            pause(5);

            if (!gameOver)
               Cingo.this.pull.play();

            repaint();
         }
      }

   }

/**

An inner class to hold the score. This panel simply displays
the score.

*/
   public class ScorePanel extends JPanel {
/**

An instance method to hold the title.

*/
      private String title;
/**

An instance method to hold the players score.

*/
      private int score;

/**

A constructor to create the score panel. We set
the size and location. We also remove the layout
manager. We set the background color to yellow
and make the title "Current".

<font size="4" color=#00008F>
<pre>
      public ScorePanel(int width,
                        int height,
                        int xlocation,
                        int ylocation) {
         setSize(width,height);
         setLocation(xlocation,ylocation);

         setLayout(null);

         setBackground(Color.yellow);

         title = "Current";
      }
</pre>
</font>

*/
      public ScorePanel(int width,
                        int height,
                        int xlocation,
                        int ylocation) {
         setSize(width,height);
         setLocation(xlocation,ylocation);

         setLayout(null);

         setBackground(Color.yellow);

         title = "Current";
      }

/**

An instance method to update the player's score.

<font size="4" color=#00008F>
<pre>
      public void updateScore(int score) {
         this.score += score;

         repaint();
      }
</pre>
</font>

*/
      public void updateScore(int score) {
         this.score += score;

         repaint();
      }

/**

A set method. This method will set the title.

<font size="4" color=#00008F>
<pre>
      public void setTitle(String title) {
         this.title = title;

         repaint();
      }
</pre>
</font>

*/
      public void setTitle(String title) {
         this.title = title;

         repaint();
      }

/**

An instance method to determine how much space is taken up
by a String in a font associated with a graphics context.

<font size="4" color=#00008F>
<pre>
      public int width(Graphics g,
                       String string) {
         FontMetrics metrics = g.getFontMetrics();

         return(metrics.stringWidth(string));
      }
</pre>
</font>

*/
      public int width(Graphics g,
                       String string) {
         FontMetrics metrics = g.getFontMetrics();

         return(metrics.stringWidth(string));
      }

/**

The overridden paint method. This method makes sure that
all lightweight components are redrawn. Then it displays
the title and score.

<font size="4" color=#00008F>
<pre>
      public void paint(Graphics g) {
         super.paint(g);

         g.setFont(new Font("Comic Sans MS",Font.BOLD,20));

         g.drawString(title,getWidth()/2-width(g,title)/2,20);
         g.drawString("Score:",getWidth()/2-width(g,"Score:")/2,40);
         g.drawString(score + "",getWidth()/2-width(g,score + "")/2,80);
      }
</pre>
</font>

*/
      public void paint(Graphics g) {
         super.paint(g);

         g.setFont(new Font("Comic Sans MS",Font.BOLD,20));

         g.drawString(title,getWidth()/2-width(g,title)/2,20);
         g.drawString("Score:",getWidth()/2-width(g,"Score:")/2,40);
         g.drawString(score + "",getWidth()/2-width(g,score + "")/2,80);
      }
   }

/**

An instance method to return a reference to an array
of AudioClips.

<font size="4" color=#00008F>
<pre>
   private AudioClip[] getInsults() {
      AudioClip[] temp = {getAudioClip(getCodeBase(),"bozo.wav"),
                          getAudioClip(getCodeBase(),"donate.wav"),
                          getAudioClip(getCodeBase(),"doofus.wav"),
                          getAudioClip(getCodeBase(),"dummy.wav"),
                          getAudioClip(getCodeBase(),"embycill.wav"),
                          getAudioClip(getCodeBase(),"idiot.wav"),
                          getAudioClip(getCodeBase(),"medic.wav"),
                          getAudioClip(getCodeBase(),"moron.wav"),
                          getAudioClip(getCodeBase(),"roadkill.wav"),
                          getAudioClip(getCodeBase(),"slimeball.wav"),
                          getAudioClip(getCodeBase(),"stupid.wav"),
                          getAudioClip(getCodeBase(),"wasteofoxygen.wav"),
                          getAudioClip(getCodeBase(),"workatit.wav")};

      return(temp);
   }
</pre>
</font>

*/
   private AudioClip[] getInsults() {
      AudioClip[] temp = {getAudioClip(getCodeBase(),"soundscingo/bozo.wav"),
                          getAudioClip(getCodeBase(),"soundscingo/donate.wav"),
                          getAudioClip(getCodeBase(),"soundscingo/doofus.wav"),
                          getAudioClip(getCodeBase(),"soundscingo/dummy.wav"),
                          getAudioClip(getCodeBase(),"soundscingo/embycill.wav"),
                          getAudioClip(getCodeBase(),"soundscingo/idiot.wav"),
                          getAudioClip(getCodeBase(),"soundscingo/medic.wav"),
                          getAudioClip(getCodeBase(),"soundscingo/moron.wav"),
                          getAudioClip(getCodeBase(),"soundscingo/roadkill.wav"),
                          getAudioClip(getCodeBase(),"soundscingo/slimeball.wav"),
                          getAudioClip(getCodeBase(),"soudnscingo/stupid.wav"),
                          getAudioClip(getCodeBase(),"soundscingo/wasteofoxygen.wav"),
                          getAudioClip(getCodeBase(),"soudnscingo/workatit.wav")};

      return(temp);
   }

/**

An instance method which will play a random insult.

<font size="4" color=#00008F>
<pre>
   private void playInsult() {
      int index = (int)(insults.length*Math.random());

      insults[index].play();
   }
</pre>
</font>

*/
   private void playInsult() {
      int index = (int)(insults.length*Math.random());

      insults[index].play();
   }

/**

The init method of the applet. This method gets a reference to
the content pane of the applet, and removes its layout manager.
An array of audio clips, and a parallel array of delays are
instantiated to start the initial thread to play the startup
sounds. We then retrieve the words that were sent as parameters
and breaks them up into an array. We then instantiate the four
inner classes and add them to the panel. We then play the sounds,
make the word panel active, and get references to the array of
insults and an audio clip for the Cingo board panel.

<font size="4" color=#00008F>
<pre>
   public void init() {
      Container container = getContentPane();

      container.setLayout(null);

      AudioClip[] clips = {getAudioClip(getCodeBase(),"welcome.wav"),
                           getAudioClip(getCodeBase(),"welcome1.wav"),
                           getAudioClip(getCodeBase(),"welcome2.wav"),
                           getAudioClip(getCodeBase(),"welcome3.wav"),
                           getAudioClip(getCodeBase(),"welcome4.wav"),
                           getAudioClip(getCodeBase(),"welcome5.wav")};

      double[] delay = {15,11,15,7,7,10};

      words = getParameter("words").split(";");

      usedWords = new String[0];

      container.add(wordPanel = new WordPanel(300,100,0,0));
      container.add(timerPanel = new TimerPanel(100,100,300,0));
      container.add(cingoBoardPanel = new CingoBoardPanel(300,150,0,100));
      container.add(scorePanel = new ScorePanel(100,150,300,100));     

      MusicThread musicThread = new MusicThread(clips,delay);

      wordPanelActive = true;

      pull = getAudioClip(getCodeBase(),"pull.wav");

      insults = getInsults();
   }
</pre>
</font>

*/
   public void init() {
      Container container = getContentPane();
      
      setFocusable(true);

      container.setLayout(null);

      AudioClip[] clips = {getAudioClip(getCodeBase(),"soundscingo/welcome.wav"),
                           getAudioClip(getCodeBase(),"soundscingo/welcome1.wav"),
                           getAudioClip(getCodeBase(),"soundscingo/welcome2.wav"),
                           getAudioClip(getCodeBase(),"soundscingo/welcome3.wav"),
                           getAudioClip(getCodeBase(),"soundscingo/welcome4.wav"),
                           getAudioClip(getCodeBase(),"soundscingo/welcome5.wav")};

      double[] delay = {15,11,15,7,7,10};
      
      Scanner scanner = new Scanner(getClass().getResourceAsStream("resources/words.txt"));
      
      ArrayList<String> list = new ArrayList<>();
      
      while (scanner.hasNext())
    	  list.add(scanner.next());

      words = list.toArray(new String[list.size()]);

      usedWords = new String[0];

      container.add(wordPanel = new WordPanel(300,100,0,0));
      container.add(timerPanel = new TimerPanel(100,100,300,0));
      container.add(cingoBoardPanel = new CingoBoardPanel(300,150,0,100));
      container.add(scorePanel = new ScorePanel(100,150,300,100));     

      MusicThread musicThread = new MusicThread(clips,delay);

      wordPanelActive = true;

      pull = getAudioClip(getCodeBase(),"soundscingo/pull.wav");

      insults = getInsults();
   }

   public void keyTyped(KeyEvent e) {
   }

/**

The inherited keyPressed method. If the game is over we
play an insult. Otherwise, if the word panel is active and
we haven't hit F12 then we place the character in the
word panel. If the Cingo board panel is active and we hit
F12 then we pull a new number in the Cingo board panel.

<font size="4" color=#00008F>
<pre>
   public void keyPressed(KeyEvent e) {
      if (gameOver)
         getAudioClip(getCodeBase(),"thegamesover.wav").play();
      else if (e.getKeyCode() != KeyEvent.VK_F12 &&
          wordPanelActive) {
         cingoBoardPanel.setTitle("");

         wordPanel.setCharacter(e.getKeyChar());
      }else if (e.getKeyCode() == KeyEvent.VK_F12 &&
               !wordPanelActive)
         cingoBoardPanel.pullNumber();
   }
</pre>
</font>

*/
   public void keyPressed(KeyEvent e) {
      if (gameOver)
         getAudioClip(getCodeBase(),"soundscingo/thegamesover.wav").play();
      else if (e.getKeyCode() != KeyEvent.VK_F12 &&
          wordPanelActive) {
         cingoBoardPanel.setTitle("");

         wordPanel.setCharacter(e.getKeyChar());
      }else if (e.getKeyCode() == KeyEvent.VK_F12 &&
               !wordPanelActive)
         cingoBoardPanel.pullNumber();
   }

   public void keyReleased(KeyEvent e) {
   }
}
