import java.io.*;
import java.awt.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import java.applet.*;
import java.awt.event.*;

public class Program5Option1 extends JFrame {
   private UpperPanel upperPanel;
   private MiddlePanel middlePanel;
   private LowerPanel lowerPanel;
   private AudioClip obstacleMusic;
   private AudioClip caissons;
   private AudioClip recap;
   private AudioClip[] attemptsMusic;
   private AudioClip youWon;
   private AudioClip gameOver;
   private Image applauding;
   private Image laughing;
   private String message;
   private String message1;
   private int score;
   private ImagePanel imagePanel;

   private String readURL(String url) {
      try {
         BufferedReader input = new BufferedReader(new InputStreamReader(new URL(url).openStream()));

         char[] chars = new char[0];

         int length = 0;

         char[] buffer = new char[4000*1024];

         while ((length = input.read(buffer,0,buffer.length)) != -1) {
            char[] temp = new char[chars.length+length];

            System.arraycopy(chars,0,temp,0,chars.length);
            System.arraycopy(buffer,0,temp,chars.length,length);

            chars = temp;
         }

         input.close();

         return(String.valueOf(chars));
      } catch (IOException ie) {
         return("");
      }
   }

   private String getTemperature() {
      String line = "";	   
      
	  try { 
         line = readURL("http://www.w3.weather.com/weather/local/36609");

         int index = line.indexOf("CLASS=obsTempTextA>");

         line = line.substring(index + "CLASS=obsTempTextA>".length());

         index = line.indexOf("&deg");

         line = line.substring(0,index);
	  } catch (Exception e) {
		  System.out.println(e);
	  }

      return(line);
   }

   private Image getImage(URL url) {
      try {
         BufferedInputStream input = new BufferedInputStream(url.openStream());

         byte[] bytes = new byte[0];
 
         int length = 0;

         byte[] buffer = new byte[4000*1024];

         while ((length = input.read(buffer,0,buffer.length)) != -1) {
            byte[] temp = new byte[bytes.length+length];

            System.arraycopy(bytes,0,temp,0,bytes.length);
            System.arraycopy(buffer,0,temp,bytes.length,length);

            bytes = temp;
         }

         input.close();

         MediaTracker tracker = new MediaTracker(Program5Option1.this);

         Image image = Toolkit.getDefaultToolkit().createImage(bytes);

         tracker.addImage(image,1);

         try {
            tracker.waitForAll();
         } catch (InterruptedException ie) {
         }

         return(image);
      } catch (IOException ie) {
         return(null);
      }
   }
    
   private Image[] digits() {
      try {
         String[] names = {"zero.gif","one.gif","two.gif","three.gif",
                           "four.gif","five.gif","six.gif","seven.gif",
                           "eight.gif","nine.gif"};

         Image[] images = new Image[names.length];

         for (int counter=0;counter<images.length;counter++) {
        	 images[counter] = new ImageIcon(this.getClass().getResource("/imagesprogram5option1/" + names[counter])).getImage();
        	 
        	 System.out.println(images[counter]);
        	 
         }
        	 

         return(images);
      } catch (Exception e) {
    	  System.out.println(e);
    	  
         return(null);
      }
   }

   private Image colon() {
      try {
    	  Image colon = new ImageIcon(getClass().getResource("/imagesprogram5option1/colon.gif")).getImage();
    	  
    	  return(colon);
      } catch (Exception e) {
    	  System.out.println(e);
    	  
         return(null);
      }
   }

   private Image am() {
      try {
    	 Image am = new ImageIcon(getClass().getResource("/imagesprogram5option1/am.gif")).getImage(); 
    	  
    	 return(am);
      } catch (Exception e) {
         return(null);
      }
   }

   private Image pm() {
      try {
    	  Image pm = new ImageIcon(getClass().getResource("/imagesprogram5option1/pm.gif")).getImage(); 
    	 
    	  return(pm);
      } catch (Exception e) {
         return(null);
      }
   }

   private void pause(double seconds) {
      try {
         Thread.sleep((int)(seconds*1000));
      } catch (InterruptedException ie) {
      }
   }

   public String topStories() {
      try {
         String line = readURL("http://www.cnn.com");

         int index = line.indexOf("Top stories");
         
         System.out.println("index is " + index);

         line = line.substring(index);

         index = line.indexOf("[/div]");

         line = line.substring(0,index);

         System.out.println("Hello");

         line = line.replaceAll("<div[a-zA-z0-9=\" ]*>","");

         line = line.replaceAll("!","");

         line = line.replaceAll("<a href=","!");

         String[] lines = line.split("!");

         Vector v = new Vector();

         for (int counter=0;counter<lines.length;counter++) {
            index = lines[counter].indexOf("</a>");

            if (index != -1) {
               lines[counter] = lines[counter].substring(0,index);

               index = lines[counter].indexOf('>');

               if (index > -1) {
                  lines[counter] = lines[counter].substring(index+1);
      
                  if (!lines[counter].startsWith("<") &&
                      !lines[counter].equals("Video") &&
                      !lines[counter].equals("Interactive"))
                     v.addElement(lines[counter]);
               }
            }
         }

         String[] stories = (String[])v.toArray(new String[0]);

         String output = "";

         for (int counter=0;counter<stories.length;counter++)
            output += stories[counter] + " - ";

         output = output.substring(0,output.length()-2);

         return(output);
      } catch (Exception e) {
    	  System.out.println(e);
    	  
         return("Can't get the stories");
      }
   }

   private String getTime() {
      Calendar c = Calendar.getInstance();

      int hour = c.get(Calendar.HOUR_OF_DAY);
      int minute = c.get(Calendar.MINUTE);
      int second = c.get(Calendar.SECOND);

      String amorpm = (hour >= 12) ? "pm" : "am";

      hour -= (hour > 12) ? 12 : 0;

      hour = (hour == 0) ? 12 : hour;

      String time = hour + ":";
      time += (minute < 10) ? "0" + minute : "" + minute;
      time += ":";
      time += (second < 10) ? "0" + second : "" + second;

      time += " " + amorpm;

      return(time);
   }
      
   public class TimePanel extends JPanel implements Runnable {
      private String time;
      private Image[] digits;
      private Image colon;
      private Image am;
      private Image pm;
      private Thread thread;
      private boolean keepGoing;
   
      public TimePanel(int width,
                       int height,
                       int xlocation,
                       int ylocation) {
         super(true);

         setSize(width,height);
         setLocation(xlocation,ylocation);

         setBackground(Color.black);

         digits = digits();
         colon = colon();
         am = am();
         pm = pm();

         time = getTime();

         thread = new Thread(this);
         keepGoing = true;
         thread.start();
      }

      public void paint(Graphics g) {
    	  super.paint(g);
    	  
         g.clearRect(0,0,getWidth(),getHeight());

         super.paint(g);

         int startx = 20;

         String[] temp = time.split(" ");

         for (int counter=0;counter<temp[0].length();counter++)
            try {
               int digit = new Integer(time.substring(counter,counter+1)).intValue();

               g.drawImage(digits[digit],startx,0,this);

               startx += digits[digit].getWidth(this);
            } catch (NumberFormatException nfe) {
               g.drawImage(colon,startx,0,this);

               startx += colon.getWidth(this);
            } catch (Exception e) {
            	System.out.println(e);            	
            }

         Image image = (temp[1].equals("am")) ? am : pm;

         g.drawImage(image,startx+10,0,this);
      }

      public void run() {
         while (keepGoing) {
            time = getTime();

            repaint();

            pause(0.5);
         }
      }
   }

   public class TemperaturePanel extends JPanel implements Runnable {
      private Image[] digits;
      private String temperature;
      private Thread thread;
      private boolean keepGoing;

      public TemperaturePanel(int width,
                              int height,
                              int xlocation,
                              int ylocation) {
         super(true);

         setSize(width,height);
         setLocation(xlocation,ylocation);

         setBackground(Color.black);

         digits = digits();

         temperature = getTemperature();

         thread = new Thread(this);
         keepGoing = true;
         thread.start();
      }

      public void paint(Graphics g) {
    	  super.paint(g);
    	  
         g.clearRect(0,0,getWidth(),getHeight());

         super.paint(g);

         String message = "Temperature in Mobile";

         Font font = new Font("Arial",Font.BOLD,12);

         g.setFont(font);

         FontMetrics metrics = g.getFontMetrics();

         int width = metrics.stringWidth(message);

         g.setColor(Color.white);

         g.drawString(message,0,15);
         g.drawString("according to weather.com",0,35);

         int startx = width+20;

         for (int counter=0;counter<temperature.length();counter++) {
            int digit = new Integer(temperature.substring(counter,counter+1)).intValue();

            g.drawImage(digits[digit],startx,0,this);

            startx += digits[digit].getWidth(this);
         }
      }

      public void run() {
         while (keepGoing) {
            temperature = getTemperature();

            repaint();

            pause(60);
         }
      }
   }
       
   public class UpperPanel extends JPanel {
      private TimePanel timePanel;
      private TemperaturePanel temperaturePanel;

      public UpperPanel(int width,
                        int height,
                        int xlocation,
                        int ylocation) {
         super(true);

         setSize(width,height);
         setLocation(xlocation,ylocation);

         setLayout(null);

         setBackground(Color.black);

         add(timePanel = new TimePanel(200,40,0,0));
         add(temperaturePanel = new TemperaturePanel(300,40,200,0));
      }
   }

   public class MyString {
      private LinkedList list;

      public class LinkedList {
         private Node head;

         public class Node {
            public char character;
            public int index;
            public Node next;

            public Node(char character,
                        int index,
                        Node next) {
               this.character = character;
               this.index = index;
               this.next = next;
            }
         }

         public LinkedList() {
            head = null;
         }

         public void addCharacter(char character) {
            if (head == null)
               head = new Node(character,0,head);
            else {
               Node current = head;

               while (current.next != null)
                  current = current.next;

               current.next = new Node(character,
                                       current.index+1,
                                       null);
            }
         }

         public void insertCharacterAt(char character,
                                       int index) {
            if (head != null) {
               Node current = head;

               while (current.next != null &&
                      current.index < index)
                  current = current.next;
            }
         }

         public String traverse() {
            String output = "";

            for (Node current=head;current.next != null;current=current.next)
               output += current.character;

            return(output);
         }
      }

      public MyString(char[] characters) {
         list = new LinkedList();

         for (int counter=0;counter<characters.length;counter++)
            list.addCharacter(characters[counter]);
      }

      public String toString() {
         return(list.traverse());
      }
   }

   public class MiddlePanel extends JPanel implements Runnable {
      private String message;
      private int index;
      private int width;
      private String message1;
      private Thread thread;
      private boolean keepGoing;

      public MiddlePanel(int width,
                         int height,
                         int xlocation,
                         int ylocation) {
         super(true);

         setSize(width,height);
         setLocation(xlocation,ylocation);

         setBackground(new Color(0,0,200));

         index = 500;

         message = topStories();

         message1 = "Top Stories updated at " + getTime();

         thread = new Thread(this);
         keepGoing = true;
         thread.start();
      }

      public void paint(Graphics g) {
    	  super.paint(g);
    	  
         g.clearRect(0,0,getWidth(),getHeight());

         super.paint(g);

         Font font = new Font("Serif",Font.BOLD+Font.ITALIC,50);

         g.setFont(font);

         g.setColor(Color.white);

         FontMetrics metrics = g.getFontMetrics();

         width = metrics.stringWidth(message);

         int height = metrics.getHeight();

         g.drawString(message,index,getHeight()/2+height/4);

         font = new Font("Arial",Font.BOLD,12);

         g.setFont(font);

         metrics = g.getFontMetrics();

         int width1 = metrics.stringWidth(message1);

         g.drawString(message1,getWidth()/2-width1/2,getHeight()-5);
      }

      public void run() {
         while (keepGoing) {
            index -= 10;

            repaint();

            if (index + width < 0) {
               message = topStories();

               message1 = "Top Stories updated at " + getTime();

               index = 500;
            }

            pause(0.1);
         }
      }
   }

   public class MyButton extends JButton {
      private String label;
      private int row;
      private int column;
      private boolean obstacle;

      public MyButton(int width,
                      int height,
                      int xlocation,
                      int ylocation,
                      int row,
                      int column) {
         setSize(width,height);
         setLocation(xlocation,ylocation);

         setBackground(new Color(200,200,200));

         Insets insets = getInsets();

         insets.left = 0;
         insets.right = 0;

         setMargin(insets);

         label = "";

         this.row = row;
         this.column = column;
      }

      public void setTheLabel(String label) {
         this.label = label;

         repaint();
      }

      public void setObstacle() {
         obstacle = true;
      }

      public boolean obstacle() {
         return(obstacle);
      }

      public void paint(Graphics g) {
    	  super.paint(g);
    	  
         g.clearRect(0,0,getWidth(),getHeight());

         super.paint(g);

         FontMetrics metrics = g.getFontMetrics();

         int width = metrics.stringWidth(label);

         int height = metrics.getHeight();

         g.drawString(label,getWidth()/2-width/2,getHeight()/2+height/4);
      }
   }

   public class Stack {
      private LinkedList list;

      public class LinkedList {
         private Node head;

         public class Node {
            private Object datum;
            private Node next;

            public Node(Object datum,
                        Node next) {
               this.datum = datum;
               this.next = next;
            }
         }

         public LinkedList() {
            head = null;
         }

         public void add(Object o) {
            head = new Node(o,head);
         }

         public Object remove() {
            if (head == null)
               return(null);
            else {
               Object o = head.datum;

               head = head.next;

               return(o);
            }
         }

         public Object first() {
            if (head == null)
               return(null);
            else
               return(head.datum);
         }

         public boolean empty() {
            return(head == null);
         }

         public Vector traverse() {
            Vector v = new Vector();

            Node current = head;

            while (current != null) {
               v.addElement(current.datum);

               current = current.next;
            }

            return(v);
         }
      }

      public Stack() {
         list = new LinkedList();
      }

      public void push(Object o) {
         list.add(o);
      }

      public Object pop() {
         return(list.remove());
      }

      public Object peek() {
         return(list.first());
      }

      public boolean empty() {
         return(list.empty());
      }

      public Stack copy() {
         Vector v = list.traverse();

         Stack stack = new Stack();

         for (int counter=v.size()-1;counter>=0;counter--)
            stack.push(v.elementAt(counter));

         return(stack);
      }
   }

   public class Queue {
      private LinkedList list;
      private int counter;

      public class LinkedList {
         private Node head;

         public class Node {
            public Object datum;
            public Node next;

            public Node(Object datum,
                        Node next) {
               this.datum = datum;
               this.next = next;
            }
         }

         public LinkedList() {
            head = null;
         }

         public void add(Object o) {
            counter++;

            if (head == null)
               head = new Node(o,head);
            else {
               Node current = head;

               while (current.next != null)
                  current = current.next;

               current.next = new Node(o,null);
            }
         }

         public Object remove() {
            if (head == null)
               return(null);
            else {
               Object o = head.datum;

               head = head.next;

               return(o);
            }
         }

         public boolean empty() {
            return(head == null);
         }
      }

      public Queue() {
         list = new LinkedList();
      }

      public int counter() {
         return(counter);
      }

      public void enqueue(Object o) {
         list.add(o);
      }

      public Object dequeue() {
         return(list.remove());
      }

      public boolean empty() {
         return(list.empty());
      }
   }

   public class LowerPanel extends JPanel implements KeyListener {
      private MyButton[][] grid;
      private int therow;
      private int thecolumn;
      private Stack stack;
      private Queue queue;
      private boolean suspended;
      private int attempts;

      public class PopStack extends Thread {
         private Stack stack;

         public PopStack(Stack stack) {
            this.stack = stack;

            start();
         }

         public void run() {
            if (obstacleMusic != null)
               obstacleMusic.play();

            pause(2);

            if (caissons != null)
               caissons.loop();

            while (!stack.empty()) {
               MyButton button = (MyButton)stack.pop();

               button.setTheLabel("X");

               pause(1);

               button.setTheLabel("");
            }

            suspended = false;

            grid[0][0].setTheLabel("X");

            if (caissons != null)
              caissons.stop();

            attempts++;

            if (attempts == 10) {
               suspended = true;

               if (gameOver != null)
                  gameOver.play();

               Program5Option1.this.message = "Game Over, you loser";
               Program5Option1.this.message1 = "Final Score: " + score;

               Program5Option1.this.repaint();

               DequeueQueue dequeueQueue = new DequeueQueue(queue);
            } else if (attempts == 1) {
               imagePanel.setImage(laughing);
            }
         }
      }

      public class DequeueQueue extends Thread {
         private Queue queue;

         public DequeueQueue(Queue queue) {
            this.queue = queue;

            start();
         }

         public void run() {
            for (int row=0;row<=9;row++)
               for (int column=0;column<=19;column++)
                  if (grid[row][column].obstacle())
                     grid[row][column].setTheLabel("O");

            pause(2);

            if (recap != null)
               recap.play();

            pause(5);

            int counter = 0;

            while (!queue.empty()) {
               Stack stack = (Stack)queue.dequeue();

               Stack stack1 = new Stack();

               while (!stack.empty())
                  stack1.push(stack.pop());

               if (attemptsMusic[counter] != null)
                  attemptsMusic[counter].play();

               pause(5);

               while (!stack1.empty()) {
                  MyButton button = (MyButton)stack1.pop();

                  button.setTheLabel("X");

                  pause(0.1);

                  button.setTheLabel("");
               }

               pause(0.5);

               counter++;
            }
         }
      }

      public LowerPanel(int width,
                        int height,
                        int xlocation,
                        int ylocation) {
         super(true);

         setSize(width,height);
         setLocation(xlocation,ylocation);

         setLayout(null);

         grid = new MyButton[10][20];

         int buttonWidth = getWidth()/20;
         int buttonHeight = getHeight()/10;

         for (int row=0;row<10;row++)
            for (int column=0;column<20;column++)
               add(grid[row][column] = new MyButton(buttonWidth,
                                                    buttonHeight,
                                                    buttonWidth*column,
                                                    buttonHeight*row,
                                                    row,
                                                    column));

         grid[0][0].setTheLabel("X");

         therow = 0;
         thecolumn = 0;

         addKeyListener(this);

         setFocusable(true);

         requestFocus();

         chooseObstacles();
//            for (int row=0;row<=9;row++)
//               for (int column=0;column<=19;column++)
//                  if (grid[row][column].obstacle())
//                     grid[row][column].setTheLabel("O");
         stack = new Stack();

         stack.push(grid[0][0]);

         queue = new Queue();
      }

      private void chooseObstacles() {
         Vector v = new Vector();

         while (v.size() < 20) {
            int row = (int)(10*Math.random());
            int column = (int)(20*Math.random());

            if (!v.contains(grid[row][column]) &&
                !(row == 0 &&
                  column == 0) &&
                !(row == 9 &&
                  column == 19))
               v.addElement(grid[row][column]);
         }

         for (int counter=0;counter<v.size();counter++)
            ((MyButton)v.elementAt(counter)).setObstacle();
      }

      private MyButton nextButton(KeyEvent e) { 
         if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (thecolumn > 0)
               thecolumn--;
         } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (thecolumn < 19)
               thecolumn++;
         } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (therow > 0)
               therow--;
         } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (therow < 9)
               therow++;
         }

         return(grid[therow][thecolumn]);
      }

      public void paint(Graphics g) {
         super.paint(g);
      }

      public void keyTyped(KeyEvent e) {
      }

      public void keyPressed(KeyEvent e) {
         if (!suspended) {
            MyButton button = grid[therow][thecolumn];

            MyButton nextButton = nextButton(e);

            if (nextButton == grid[9][19]) {
               button.setTheLabel("");
               nextButton.setTheLabel("X");

               if (youWon != null)
                  youWon.play();

               imagePanel.setImage(applauding);

               Program5Option1.this.message = "You won";
               Program5Option1.this.message1 = "Final Score: " + score;

               Program5Option1.this.repaint();

               suspended = true;

               queue.enqueue(stack);

               DequeueQueue dequeueQueue = new DequeueQueue(queue);
             } else if (nextButton.obstacle()) {
               suspended = true;

               Stack temp = stack.copy();

               queue.enqueue(temp);

               PopStack popStack = new PopStack(stack);

               score = 100-10*queue.counter();

               Program5Option1.this.message = "You have " + (9-attempts) + " attempts left";
               Program5Option1.this.message1 = "Current Score: " + score;

               Program5Option1.this.repaint();

               stack = new Stack();

               stack.push(grid[0][0]);

               therow = 0;
               thecolumn = 0;
            } else if (button != nextButton) {
               button.setTheLabel("");
               nextButton.setTheLabel("X");

               stack.push(nextButton);
            }
         }
      }

      public void keyReleased(KeyEvent e) {
      }
   }

   public class ImagePanel extends JPanel {
      private Image image;

      public ImagePanel(int width,
                        int height,
                        int xlocation,
                        int ylocation) {
         super(true);

         setSize(width,height);
         setLocation(xlocation,ylocation);

         setBackground(Color.white);
      }

      public void setImage(Image image) {
         this.image = image;

         System.out.println(image);

         repaint();
      }

      public void paint(Graphics g) {
    	  super.paint(g);
    	  
         g.clearRect(0,0,getWidth(),getHeight());

         if (image != null) {
            int width = image.getWidth(this);

            for (int counter=0;counter<24;counter++)
               g.drawImage(image,(width+5)*counter-10,0,this);
         }
      }
   }

   private AudioClip loadAudioClip(String fileName) {
	   try {
   	      AudioClip music = Applet.newAudioClip(this.getClass().getResource("/soundsprogram5option1/" + fileName));
   	      
   	      return(music);
	   } catch (Exception e) {
		   return(null);
	   }
   }
  
   public Program5Option1(String title) {
      super(title);

      Container container = getContentPane();

      container.setLayout(null);

      obstacleMusic = loadAudioClip("ohno2.wav");
      caissons = loadAudioClip("caisson.mid");

      attemptsMusic = new AudioClip[10];

      for (int counter=0;counter<attemptsMusic.length;counter++)
         attemptsMusic[counter] = loadAudioClip("attempt" + (counter+1) + ".wav");

      recap = loadAudioClip("recap.wav");

      youWon = loadAudioClip("youwon.wav");
      gameOver = loadAudioClip("gameover.wav");

      try {
         applauding = getImage(new File("imagesprogram5option1/applauding.gif").toURL());
         laughing = getImage(new File("imagesprogram5option1/laughing.gif").toURL());
      } catch (IOException ie) {
      }

      container.setBackground(Color.darkGray);

      container.add(upperPanel = new UpperPanel(500,40,0,0));
      container.add(middlePanel = new MiddlePanel(500,100,0,40));
      container.add(lowerPanel = new LowerPanel(480,300,5,160));
      container.add(imagePanel = new ImagePanel(550,35,-10,500));
     
      message = "You have 10 attempts left";
      message1 = "Current Score: 100";
   }

   public void paint(Graphics g) {
      super.paint(g);

      g.setColor(Color.white);

      g.drawString(message,10,185);

      g.drawString(message1,250,185);

      g.drawString("The goal is to move the X with the arrow keys to the lower right corner without hitting",10,505);

      g.drawString("an obstacle. Your score is 100 - 10 times the number of times you hit an obstacle.",10,525);
   }

   public static void main(String[] args) {
      Program5Option1 program5Option1 = new Program5Option1("Program 5 Option 1");

      program5Option1.setSize(500,575);

      program5Option1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
      program5Option1.setVisible(true);
   }

}