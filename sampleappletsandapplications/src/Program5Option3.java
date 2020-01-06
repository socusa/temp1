import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**

<h2><font face="Comic Sans MS" color=#8F0000>Programming Assignment 5 Option 3 Solution

<br><br>

Spring Semester 2004</font></h2>

Full Source Code

<br><br>

<a href="Program5Option3.java">Program5Option3.java</a>

*/

public class Program5Option3 extends JFrame implements ActionListener {
/**

A reference to a linked list.

*/
   private LinkedList list;

/**

A reference to a display panel.

*/
   private DisplayPanel displayPanel;
/**

A reference to an array of rectangles to defines regions
on the edges of the panel.

*/
   private Rectangle[] regions;
/**

An int to indicate the next id.

*/
   private int nextID;
/**

A reference to a PrintWriter.

*/
   private PrintWriter output;
/**

A reference to a button to stop the program.

*/
   private JButton stop;
/**

A reference to a button to give an explanation for
how to approximate pi.

*/
   private JButton explanation;
/**

A reference to a JTextArea where collisions will be
written.

*/
   private JTextArea area;
/**

A reference to the content pane of the frame.

*/
   private Container container;
/**

A reference to a report panel.

*/
   private ReportPanel reportPanel;
/**

An int to hold the number of random points in a circle.

*/
   private int numberInCircle;
/**

An int to hold the number of random points in a square.

*/
   private int numberInSquare;
/**

A reference to a String to display a message.

*/
   private JLabel message;

/**

A private message to cause the currently executing thread
to pause for a specified number of seconds.

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

A private method to create a JButton.

<font size="4" color=#00008F>
<pre>
   private JButton createJButton(String label,
                                 int width,
                                 int height,
                                 int xlocation,
                                 int ylocation,
                                 ActionListener listener) {
      JButton button = new JButton(label);
      button.setSize(width,height);
      button.setLocation(xlocation,ylocation);

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
                                 int width,
                                 int height,
                                 int xlocation,
                                 int ylocation,
                                 ActionListener listener) {
      JButton button = new JButton(label);
      button.setSize(width,height);
      button.setLocation(xlocation,ylocation);

      Insets insets = button.getInsets();

      insets.left = 0;
      insets.right = 0;

      button.setMargin(insets);

      button.addActionListener(listener);

      return(button);
   }

/**

A private method to create a JScrollPane.

<font size="4" color=#00008F>
<pre>
   private JScrollPane createJScrollPane(Component component,
                                         int width,
                                         int height,
                                         int xlocation,
                                         int ylocation) {
      JScrollPane pane = new JScrollPane(component);
      pane.setSize(width,height);
      pane.setLocation(xlocation,ylocation);

      return(pane);
   }
</pre>
</font>

*/

   private JScrollPane createJScrollPane(Component component,
                                         int width,
                                         int height,
                                         int xlocation,
                                         int ylocation) {
      JScrollPane pane = new JScrollPane(component);
      pane.setSize(width,height);
      pane.setLocation(xlocation,ylocation);

      return(pane);
   }

/**

A private method to create a JLabel.

<font size="4" color=#00008F>
<pre>
   private JLabel createJLabel(String text,
                               int width,
                               int height,
                               int xlocation,
                               int ylocation) {
      JLabel label = new JLabel(text);
      label.setSize(width,height);
      label.setLocation(xlocation,ylocation);

      return(label);
   }
</pre>
</font>

*/

   private JLabel createJLabel(String text,
                               int width,
                               int height,
                               int xlocation,
                               int ylocation) {
      JLabel label = new JLabel(text);
      label.setSize(width,height);
      label.setLocation(xlocation,ylocation);

      return(label);
   }

   public abstract class Ball extends Thread {
/**

A controlling boolean to determine if the thread should keep running.

*/
      protected boolean keepGoing;
/**

An int to hold the radius of the ball.

*/
      protected int radius;
/**

An int to hold the direction of the ball.

*/
      protected int direction;
/**

An int to hold the current x coordinate of the ball.

*/
      protected int currentx;
/**

An int to hold the current y coordinate of the ball.

*/
      protected int currenty;
/**

A reference to the rightmost x point.

*/
      protected int rightmostx;
/**

A reference to the upper y point.

*/
      protected int uppery;
/**

A reference to an array of directions.

*/
      protected int[] directions;
/**

A reference to the color of the ball.

*/
      protected Color color;
/**

A reference to an id.

*/
      protected int id;

/**

This constructor of the superclass extracts the origin of the bounding box
and the size of the bounding box. We then randomly choose a direction that
isn't 90 degrees. We choose a random radius and a random color. We then set the
id for the ball. We set the controlling boolean to true before we start the thread.

<font size="4" color=#00008F>
<pre>
      public Ball(Point point,
                  Dimension dimension) {
         directions = new int[360/5];

         for (int counter=0;counter&#60directions.length;counter++)
            directions[counter] = 5*counter;

         direction = directions[(int)(directions.length*Math.random())];

         while (direction % 90 == 0)
            direction = directions[(int)(directions.length*Math.random())];

         radius = 5 + (int)(31*Math.random());

         currentx = point.x;
         currenty = point.y;

         rightmostx = dimension.width;
         uppery = dimension.height;

         color = new Color(50 + (int)(205*Math.random()),
                           50 + (int)(205*Math.random()),
                           50 + (int)(205*Math.random()));

         id = nextID++;

         keepGoing = true;

         start();
      }
</pre>
</font>

*/

      public Ball(Point point,
                  Dimension dimension) {
         directions = new int[360/5];

         for (int counter=0;counter<directions.length;counter++)
            directions[counter] = 5*counter;

         direction = directions[(int)(directions.length*Math.random())];

         while (direction % 90 == 0)
            direction = directions[(int)(directions.length*Math.random())];

         radius = 5 + (int)(31*Math.random());

         currentx = point.x;
         currenty = point.y;

         rightmostx = dimension.width;
         uppery = dimension.height;

         color = new Color(50 + (int)(205*Math.random()),
                           50 + (int)(205*Math.random()),
                           50 + (int)(205*Math.random()));

         id = nextID++;

         keepGoing = true;

         start();
      }

/**

A get method to get the radius.

<font size="4" color=#00008F>
<pre>
      public int radius() {
         return(radius);
      }
</pre>
</font>

*/

      public int radius() {
         return(radius);
      }

/**

A get method to get the direction.

<font size="4" color=#00008F>
<pre>
      public int direction() {
         return(direction);
      }
</pre>
</font>

*/

      public int direction() {
         return(direction);
      }

/**

A set method to set the radius.

<font size="4" color=#00008F>
<pre>
      public void setRadius(int radius) {
         this.radius = radius;
      }
</pre>
</font>

*/

      public void setRadius(int radius) {
         this.radius = radius;
      }

/**

A set method to set the direction.

<font size="4" color=#00008F>
<pre>
      public void setDirection(int direction) {
         this.direction = direction;
      }
</pre>
</font>

*/

      public void setDirection(int direction) {
         this.direction = direction;
      }

/**

A method to move the ball to a certain amount. We use our friends
sin and cos to determine where we move.

<font size="4" color=#00008F>
<pre>
      public void moveDistance(double distance) {
         currentx += (int)(distance*Math.cos(direction*3.1415926535/180));
         currenty += (int)(distance*Math.sin(direction*3.1415926535/180));
      }
</pre>
</font>

*/

      public void moveDistance(double distance) {
         currentx += (int)(distance*Math.cos(direction*3.1415926535/180));
         currenty += (int)(distance*Math.sin(direction*3.1415926535/180));
      }

/**

A method to retrieve the bounding box of the rectangle.

<font size="4" color=#00008F>
<pre>
      public Rectangle boundingBox() {
         return(new Rectangle(currentx-radius,
                              currenty-radius,
                              2*radius,
                              2*radius));
      }
</pre>
</font>

*/
      
      public Rectangle boundingBox() {
         return(new Rectangle(currentx-radius,
                              currenty-radius,
                              2*radius,
                              2*radius));
      }

/**

A method to retrieve the center of the ball.

<font size="4" color=#00008F>
<pre>
      public Point center() {
         return(new Point(currentx,currenty));
      }
</pre>
</font>

*/

      public Point center() {
         return(new Point(currentx,currenty));
      }

/**

An instance method to determine if a rectangle intersects
the bounding box of this ball.

<font size="4" color=#00008F>
<pre>
      private boolean intersects(Rectangle rectangle) {
         return(boundingBox().intersects(rectangle));
      }
</pre>
</font>

*/

      private boolean intersects(Rectangle rectangle) {
         return(boundingBox().intersects(rectangle));
      }

/**

A toString method to return the id of this ball.

<font size="4" color=#00008F>
<pre>
      public String toString() {
         return(id + "");
      }
</pre>
</font>

*/

      public String toString() {
         return(id + "");
      }

/**

An abstract method that will be filled in by subclasses.

*/

      protected abstract void draw(Graphics g);

/**

In order to find a new direction for the ball, we determine which
region around the edge of the panel is intersected by the ball. Using
this information we then choose a direction between a max and min angle
as long as the angle is not a multiple of 90.

<font size="4" color=#00008F>
<pre>
      private int newDirection(int region) {
         int upperLimit = 0;
         int lowerLimit = 0;

         switch (region) {
            case 0 : {
                        upperLimit = 360;
                        lowerLimit = 270;

                        break;
                     }
            case 1 : {
                        upperLimit = 90;
                        lowerLimit = 0;

                        break;
                     }
            case 3 : {
                        upperLimit = 180;
                        lowerLimit = 90;

                        break;
                     }
            default : {
                         upperLimit = 270;
                         lowerLimit = 180;
                      }
         }

         int temp = directions[(int)(directions.length*Math.random())];

         while (temp % 90 == 0 ||
                temp < lowerLimit ||
                temp > upperLimit)
            temp = directions[(int)(directions.length*Math.random())];

         return(temp);
      }
</pre>
</font>

*/

      private int newDirection(int region) {
         int upperLimit = 0;
         int lowerLimit = 0;

         switch (region) {
            case 0 : {
                        upperLimit = 360;
                        lowerLimit = 270;

                        break;
                     }
            case 1 : {
                        upperLimit = 90;
                        lowerLimit = 0;

                        break;
                     }
            case 3 : {
                        upperLimit = 180;
                        lowerLimit = 90;

                        break;
                     }
            default : {
                         upperLimit = 270;
                         lowerLimit = 180;
                      }
         }

         int temp = directions[(int)(directions.length*Math.random())];

         while (temp % 90 == 0 ||
                temp < lowerLimit ||
                temp > upperLimit)
            temp = directions[(int)(directions.length*Math.random())];

         return(temp);
      }

/**

A method to stop the thread running through the controlling boolean.

<font size="4" color=#00008F>
<pre>
      public void stopThread() {
         keepGoing = false;
      }
</pre>
</font>

*/

      public void stopThread() {
         keepGoing = false;
      }

/**

This method defines what the thread does. As long as the controlling boolean
is true we move the ball by a distance of 2. If the ball has intersected one of
the edges of the panel or another ball, then we change its direction and reduce
its radius based on the size the ball relative to the other ball.

<font size="4" color=#00008F>
<pre>
      public void run() {
         while (keepGoing) {
            displayPanel.repaint();

            moveDistance(2);

            int temp = direction;

            if (intersects(regions[0]) ||
                intersects(regions[7]))
               direction = newDirection(0);
            else if (intersects(regions[1]) ||
                     intersects(regions[2]))
               direction = newDirection(1);
            else if (intersects(regions[3]) ||
                     intersects(regions[4]))
               direction = newDirection(3);
            else if (intersects(regions[5]) ||
                     intersects(regions[6]))
               direction = newDirection(5);

            if (temp != direction &&
                output != null)
               output.println(toString() + " bounced off a wall with center located at (" + currentx + "," + currenty + ")");

            list.detectCollision(this);

            pause(0.05);
         }
      }
</pre>
</font>

*/
                    
      public void run() {
         while (keepGoing) {
            displayPanel.repaint();

            moveDistance(2);

            int temp = direction;

            if (intersects(regions[0]) ||
                intersects(regions[7]))
               direction = newDirection(0);
            else if (intersects(regions[1]) ||
                     intersects(regions[2]))
               direction = newDirection(1);
            else if (intersects(regions[3]) ||
                     intersects(regions[4]))
               direction = newDirection(3);
            else if (intersects(regions[5]) ||
                     intersects(regions[6]))
               direction = newDirection(5);

            if (temp != direction &&
                output != null)
               output.println(toString() + " bounced off a wall with center located at (" + currentx + "," + currenty + ")");

            list.detectCollision(this);

            pause(0.05);
         }
      }
   }

/**

This inner class define a ball that will simply bounce.

*/

   public class BouncyBall extends Ball {

/**

This constructor simply calls the superclass constructor.

<font size="4" color=#00008F>
<pre>
      public BouncyBall(Point point,
                        Dimension dimension) {
         super(point,dimension);
      }
</pre>
</font>

*/

      public BouncyBall(Point point,
                        Dimension dimension) {
         super(point,dimension);
      }

/**

This method was inherited from the superclass. We simply
draw the ball.

<font size="4">
<pre>
      public void draw(Graphics g) {
         g.setColor(color);

         g.fillOval(currentx-radius,
                    currenty-radius,
                    2*radius,
                    2*radius);
      }
</pre>
</font>

*/

      public void draw(Graphics g) {
         g.setColor(color);

         g.fillOval(currentx-radius,
                    currenty-radius,
                    2*radius,
                    2*radius);
      }

   }

/**

This inner class defines an exploding ball.

*/

   public class ExplodingBall extends Ball {
/**

An instance variable to determine if the ball should explode.

*/
      private boolean exploding;


/**

This constructor simply calls the superclass constructor.

<font size="4" color=#00008F>
<pre>
      public ExplodingBall(Point point,
                           Dimension dimension) {
         super(point,dimension);
      }
</pre>
</font>

*/

      public ExplodingBall(Point point,
                           Dimension dimension) {
         super(point,dimension);
      }

/**

An instance method to set exploding to true.

<font size="4" color=#00008F>
<pre>
      public void explode() {
         exploding = true;
 
         try {
            repaint();
         } catch (NullPointerException npe) {
         }
      }
</pre>
</font>

*/

      public void explode() {
         exploding = true;
 
         try {
            repaint();
         } catch (NullPointerException npe) {
         }
      }

/**

This method was inherited from the superclass. If exploding is true, we
draw several small balls. Otherwise we draw the ball.

<font size="4" color=#00008F>
<pre>
      public void draw(Graphics g) {
         g.setColor(color);

         if (exploding) {
            Point[] points = new Point[10];

            for (int counter=0;counter&#60points.length;counter++) {
               points[counter] = new Point();

               points[counter].x = currentx + (int)(radius*Math.cos(counter*36*3.1415926535/180));
               points[counter].y = currenty + (int)(radius*Math.sin(counter*36*3.1415926535/180));

               g.fillOval(points[counter].x-(int)(radius/10.0),
                          points[counter].y-(int)(radius/10.0),
                          (int)(radius/5.0),
                          (int)(radius/5.0));
            }

            exploding = false;
         } else
            g.fillOval(currentx-radius,
                       currenty-radius,
                       2*radius,
                       2*radius);
      }
</pre>
</font>

*/

      public void draw(Graphics g) {
         g.setColor(color);

         if (exploding) {
            Point[] points = new Point[10];

            for (int counter=0;counter<points.length;counter++) {
               points[counter] = new Point();

               points[counter].x = currentx + (int)(radius*Math.cos(counter*36*3.1415926535/180));
               points[counter].y = currenty + (int)(radius*Math.sin(counter*36*3.1415926535/180));

               g.fillOval(points[counter].x-(int)(radius/10.0),
                          points[counter].y-(int)(radius/10.0),
                          (int)(radius/5.0),
                          (int)(radius/5.0));
            }

            exploding = false;
         } else
            g.fillOval(currentx-radius,
                       currenty-radius,
                       2*radius,
                       2*radius);
      }

   }

/**

This inner class defines a linked list.

*/

   public class LinkedList {
/**

A reference to the head of the list.

*/
      private Node head;

/**

This inner class defines the node.

*/

      public class Node {
/**

A reference to the object in the node.

*/
         private Object datum;
/**

A reference to the next node of the list.

*/
         private Node next;

/**

This constructor initializes the datum and pointer.

<font size="4" color=#00008F>
<pre>
         public Node(Object datum,
                     Node next) {
            this.datum = datum;
            this.next = next;
         }
</pre>
</font>

*/

         public Node(Object datum,
                     Node next) {
            this.datum = datum;
            this.next = next;
         }
      }

/**

This constructor sets the head of the list to null.

<font size="4" color=#00008F>
<pre>
      public LinkedList() {
         head = null;
      }
</pre>
</font>

*/

      public LinkedList() {
         head = null;
      }

/**

This method adds a new node to the list. If the list is empty,
we make the new node the head of the list. Otherwise we put the
new node at the end of the list.

<font size="4" color=#00008F>
<pre>
      public void add(Object o) {
         if (head == null)
            head = new Node(o,head);
         else {
            Node current = head;

            while (current.next != null)
               current = current.next;

            current.next = new Node(o,null);
         }
      }
</pre>
</font>

*/

      public void add(Object o) {
         if (head == null)
            head = new Node(o,head);
         else {
            Node current = head;

            while (current.next != null)
               current = current.next;

            current.next = new Node(o,null);
         }
      }

/**

This method searches through the list and removes the
node containing the ball.

<font size="4" color=#00008F>
<pre>
      public synchronized void remove(Ball ball) {
         Node previous = head;
         Node current = head;

         if (current != null &&
             current.datum == ball)
            head = head.next;
         else {
            current = current.next;

            while (current != null &&
                   current.datum != ball) {
               current = current.next;
               previous = previous.next;
            }

            if (current == null)
               previous.next = null;
            else
               previous.next = current.next;
         }
</pre>
</font>

*/

      public synchronized void remove(Ball ball) {
         Node previous = head;
         Node current = head;

         if (current != null &&
             current.datum == ball)
            head = head.next;
         else {
            current = current.next;

            while (current != null &&
                   current.datum != ball) {
               current = current.next;
               previous = previous.next;
            }

            if (current == null)
               previous.next = null;
            else
               previous.next = current.next;
         }
      }

/**

This method traverses through the list and redraws each ball.

<font size="4" color=#00008F>
<pre>
      public void traverse(Graphics g) {
         for (Node current=head;current != null;current = current.next)
            ((Ball)current.datum).draw(g);
      }
</pre>
</font>

*/

      public void traverse(Graphics g) {
         for (Node current=head;current != null;current = current.next)
            ((Ball)current.datum).draw(g);
      }

/**

This method traverses through the list and stops each ball from moving.

<font size="4" color=#00008F>
<pre>
      public void traverse() {
         for (Node current=head;current != null;current = current.next)
            ((Ball)current.datum).stopThread();
      }
</pre>
</font>

*/

      public void traverse() {
         for (Node current=head;current != null;current = current.next)
            ((Ball)current.datum).stopThread();
      }

/**

This method traverses through the list and determines if the ball intersects
any ball in the list. We do this by measuring the distance between the centers
of the ball to see if the distance is less than the sum of the radii.

<font size="4" color=#00008F>
<pre>
      public void detectCollision(Ball ball) {
         for (Node current=head;current != null;current = current.next) {
            Ball temp = (Ball)current.datum;

            Point center1 = ball.center();
            Point center2 = temp.center();

            int radius1 = ball.radius();
            int radius2 = temp.radius();

            int direction1 = ball.direction();
            int direction2 = temp.direction();

            double distance = Math.sqrt(Math.pow(center1.x-center2.x,2) +
                                        Math.pow(center1.y-center2.y,2));

            if (ball != temp &&
                distance <= radius1+radius2) {
               if (output != null) {
                  output.println(ball + ";" + center1.x + "," + center1.y);
                  output.println(temp + ";" + center2.x + "," + center2.y);
               }

               area.append("\n" + ball + " collided with " + temp + " with center located at (" + center1.x + "," + center1.y + ")");
               area.append("\n" + temp + " collided with " + ball + " with center located at (" + center2.x + "," + center2.y + ")");

               if (radius1 > radius2) {
                  temp.setRadius(radius2-2);
                  ball.setRadius(radius1-1);
               } else if (radius1 < radius2) {
                  temp.setRadius(radius2-1);
                  ball.setRadius(radius1-2);
               } else {
                  temp.setRadius(radius2-1);
                  ball.setRadius(radius1-1);
               }

               ball.moveDistance(radius1);
               temp.moveDistance(radius2);

               if (ball instanceof BouncyBall)
                  ball.setDirection((direction1 + 45) % 360);
               else
                  ((ExplodingBall)ball).explode();

               if (temp instanceof BouncyBall)
                  temp.setDirection((direction2 + 45) % 360);
               else
                  ((ExplodingBall)temp).explode();
            }

            if (ball.radius <= 2)
               remove(ball);

            if (temp.radius <= 2)
               remove(temp);
         }
      }
</pre>
</font>

*/

      public void detectCollision(Ball ball) {
         for (Node current=head;current != null;current = current.next) {
            Ball temp = (Ball)current.datum;

            Point center1 = ball.center();
            Point center2 = temp.center();

            int radius1 = ball.radius();
            int radius2 = temp.radius();

            int direction1 = ball.direction();
            int direction2 = temp.direction();

            double distance = Math.sqrt(Math.pow(center1.x-center2.x,2) +
                                        Math.pow(center1.y-center2.y,2));

            if (ball != temp &&
                distance <= radius1+radius2) {
               if (output != null) {
                  output.println(ball + ";" + center1.x + "," + center1.y);
                  output.println(temp + ";" + center2.x + "," + center2.y);
               }

               area.append("\n" + ball + " collided with " + temp + " with center located at (" + center1.x + "," + center1.y + ")");
               area.append("\n" + temp + " collided with " + ball + " with center located at (" + center2.x + "," + center2.y + ")");

               if (radius1 > radius2) {
                  temp.setRadius(radius2-2);
                  ball.setRadius(radius1-1);
               } else if (radius1 < radius2) {
                  temp.setRadius(radius2-1);
                  ball.setRadius(radius1-2);
               } else {
                  temp.setRadius(radius2-1);
                  ball.setRadius(radius1-1);
               }

               ball.moveDistance(radius1);
               temp.moveDistance(radius2);

               if (ball instanceof BouncyBall)
                  ball.setDirection((direction1 + 45) % 360);
               else
                  ((ExplodingBall)ball).explode();

               if (temp instanceof BouncyBall)
                  temp.setDirection((direction2 + 45) % 360);
               else
                  ((ExplodingBall)temp).explode();
            }

            if (ball.radius <= 2)
               remove(ball);

            if (temp.radius <= 2)
               remove(temp);
         }
      }
   }

/**

This inner class defines a panel where the balls will be drawn.

*/

   public class DisplayPanel extends JPanel implements MouseListener {
/**

A private boolean to determine if we should show a report.

*/
      private boolean report;
/**

A reference to a String array containing the lines to display.

*/
      private String[] lines;

/**

This constructor sets up the panel, sets its background, and adds a 
mouse listener.

<font size="4" color=#00008F>
<pre>
      public DisplayPanel(int width,
                          int height,
                          int xlocation,
                          int ylocation) {
         super(true);

         setSize(width,height);
         setLocation(xlocation,ylocation);

         setBackground(Color.white);

         addMouseListener(this);
      }
</pre>
</font>

*/

      public DisplayPanel(int width,
                          int height,
                          int xlocation,
                          int ylocation) {
         super(true);

         setSize(width,height);
         setLocation(xlocation,ylocation);

         setBackground(Color.white);

         addMouseListener(this);
      }

/**

This paint method draws all lightweight components and traverses the list
to draw the balls.

<font size="4" color=#00008F>
<pre>
      public void paint(Graphics g) {
         super.paint(g);

         list.traverse(g);
      }
</pre>
</font>

*/

      public void paint(Graphics g) {
         super.paint(g);

         list.traverse(g);
      }

      public void mouseClicked(MouseEvent e) {}

      public void mouseEntered(MouseEvent e) {}
      public void mouseExited(MouseEvent e) {}

/**

In this method we create a new ball using the point where the
mouse is clicked.

<font size="4" color=#00008F>
<pre>
      public void mousePressed(MouseEvent e) { 
         int x = e.getX();
         int y = e.getY();

         list.add(createNewBall(new Point(x,y),
                                new Dimension(displayPanel.getWidth(),
                                              displayPanel.getHeight())));
      }
</pre>
</font>

*/

      public void mousePressed(MouseEvent e) { 
         int x = e.getX();
         int y = e.getY();

         list.add(createNewBall(new Point(x,y),
                                new Dimension(displayPanel.getWidth(),
                                              displayPanel.getHeight())));
      }

      public void mouseReleased(MouseEvent e) {}         
   }

/**

This inner class defines the report panel.

*/

   public class ReportPanel extends JPanel {
/**

An array containing the collisions.

*/
      private int[] collisions;

/**

This constructor sets up the panel, creates a new array of collisions and reads
the information from a file.

<font size="4" color=#00008F>
<pre>
      public ReportPanel(int width,
                         int height) {
         super(true);

         setPreferredSize(new Dimension(width,height));

         collisions = new int[nextID-1];

         try {
            FileReader file = new FileReader("output.data");
            BufferedReader input = new BufferedReader(file);

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

            String[] lines = String.valueOf(chars).split("\n");

            for (int counter=0;counter&#60lines.length;counter++)
               if (lines[counter].indexOf(";") > -1) {
                  String[] temp = lines[counter].split(";");

                  collisions[new Integer(temp[0]).intValue()-1]++;
               }
         } catch (IOException ie) {
         }
      }
</pre>
</font>

*/
   
      public ReportPanel(int width,
                         int height) {
         super(true);

         setPreferredSize(new Dimension(width,height));

         collisions = new int[nextID-1];

         try {
            FileReader file = new FileReader("output.data");
            BufferedReader input = new BufferedReader(file);

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

            String[] lines = String.valueOf(chars).split("\n");

            for (int counter=0;counter<lines.length;counter++)
               if (lines[counter].indexOf(";") > -1) {
                  String[] temp = lines[counter].split(";");

                  collisions[new Integer(temp[0]).intValue()-1]++;
               }
         } catch (IOException ie) {
         }
      }

/**

This paint method draws all of the collisions. We then make sure that the
panel is large enough to display the collisions.

<font size="4" color=#00008F>
<pre>
      public void paint(Graphics g) {
         g.clearRect(0,0,getWidth(),getHeight());

         super.paint(g);

         Font originalFont = g.getFont();
 
         Font font = new Font("Arial",Font.BOLD+Font.ITALIC,20);

         g.setFont(font);

         FontMetrics metrics = g.getFontMetrics();

         String message = "These are the collisions which occurred";

         int width = metrics.stringWidth(message);

         g.drawString(message,getWidth()/2-width/2,20);

         g.setFont(originalFont);

         for (int counter=0;counter&#60collisions.length;counter++)
            g.drawString("Ball " + (counter+1) + ": " + collisions[counter],20,60+20*counter);

         setPreferredSize(new Dimension(getWidth(),60+20*collisions.length));

         revalidate();
      }
   }
</pre>
</font>

*/

      public void paint(Graphics g) {
         g.clearRect(0,0,getWidth(),getHeight());

         super.paint(g);

         Font originalFont = g.getFont();
 
         Font font = new Font("Arial",Font.BOLD+Font.ITALIC,20);

         g.setFont(font);

         FontMetrics metrics = g.getFontMetrics();

         String message = "These are the collisions which occurred";

         int width = metrics.stringWidth(message);

         g.drawString(message,getWidth()/2-width/2,20);

         g.setFont(originalFont);

         for (int counter=0;counter<collisions.length;counter++)
            g.drawString("Ball " + (counter+1) + ": " + collisions[counter],20,60+20*counter);

         setPreferredSize(new Dimension(getWidth(),60+20*collisions.length));

         revalidate();
      }
   }

/**

This inner class approximates pi.

*/

   public class ApproximatingPi extends Thread {
/**

This boolean is used to determine if the thread should keep running.

*/
      private boolean keepGoing;

/**

This constructor sets the controlling boolean to true and starts the thread.

<font size="4" color=#00008F>
<pre>
      public ApproximatingPi() {
         keepGoing = true;

         start();
      }
</pre>
</font>

*/

      public ApproximatingPi() {
         keepGoing = true;

         start();
      }

/**

This run method will keep generating random numbers and determining
if they are in a circle or its bounding box. We use this to approximate
pi.

<font size="4" color=#00008F>
<pre>
      public void run() {
         while (keepGoing) {
            int x = (int)(400*Math.random());
            int y = (int)(400*Math.random());

            double distanceFromCenter = Math.sqrt(Math.pow(x-200,2) +
                                                  Math.pow(y-200,2));

            if (distanceFromCenter <= 200)
               numberInCircle++;
            
            numberInSquare++;

            double estimate = 4.0*numberInCircle/numberInSquare;

            message.setText("Approximation of pi: " + estimate);

            pause(0.000005);
         }
      }
</pre>
</font>

*/

      public void run() {
         while (keepGoing) {
            int x = (int)(400*Math.random());
            int y = (int)(400*Math.random());

            double distanceFromCenter = Math.sqrt(Math.pow(x-200,2) +
                                                  Math.pow(y-200,2));

            if (distanceFromCenter <= 200)
               numberInCircle++;
            
            numberInSquare++;

            double estimate = 4.0*numberInCircle/numberInSquare;

            message.setText("Approximation of pi: " + estimate);

            pause(0.000005);
         }
      }
   }

/**

This method creates a new ball and randomly determines if its a bouncy
ball or exploding ball.

<font size="4" color=#00008F>
<pre>
   public Ball createNewBall(Point point,
                             Dimension dimension) {
      int number = (int)(100*Math.random());

      if (number < 50)
         return(new BouncyBall(point,dimension));
      else
         return(new ExplodingBall(point,dimension));
   }
</pre>
</font>

*/

   public Ball createNewBall(Point point,
                             Dimension dimension) {
      int number = (int)(100*Math.random());

      if (number < 50)
         return(new BouncyBall(point,dimension));
      else
         return(new ExplodingBall(point,dimension));
   }

/**

This constructor adds all of the components to the frame's content pane. We
also defines rectangles around the edges of the panel.

<font size="4" color=#00008F>
<pre>
   public Program5Option3(String title) {
      super(title);

      container = getContentPane();

      container.setLayout(null);

      list = new LinkedList();

      container.add(displayPanel = new DisplayPanel(500,400,0,0));

      container.add(createJScrollPane(area = new JTextArea(),
                                      480,70,
                                      5,410));

      container.add(message = createJLabel("",250,20,20,485));

      container.add(explanation = createJButton("Explanation",100,20,280,485,this));

      container.add(stop = createJButton("Stop",50,20,420,485,this));

      nextID = 1;

      try {
         output = new PrintWriter(new FileWriter("output.data"));
      } catch (IOException ie) {
      }

      regions = new Rectangle[8];

      regions[0] = new Rectangle(0,200,5,195);
      regions[1] = new Rectangle(0,5,5,195);
      regions[2] = new Rectangle(5,0,245,5);
      regions[3] = new Rectangle(250,0,235,5);
      regions[4] = new Rectangle(485,5,5,195);
      regions[5] = new Rectangle(485,200,5,195);
      regions[6] = new Rectangle(250,395,235,5);
      regions[7] = new Rectangle(5,395,245,5);

      new ApproximatingPi();
   }
</pre>
</font>

*/

   public Program5Option3(String title) {
      super(title);

      container = getContentPane();

      container.setLayout(null);

      list = new LinkedList();

      container.add(displayPanel = new DisplayPanel(500,400,0,0));

      container.add(createJScrollPane(area = new JTextArea(),
                                      480,70,
                                      5,410));

      container.add(message = createJLabel("",250,20,20,485));

      container.add(explanation = createJButton("Explanation",100,20,280,485,this));

      container.add(stop = createJButton("Stop",50,20,420,485,this));

      nextID = 1;

      try {
         output = new PrintWriter(new FileWriter("output.data"));
      } catch (IOException ie) {
      }

      regions = new Rectangle[8];

      regions[0] = new Rectangle(0,200,5,195);
      regions[1] = new Rectangle(0,5,5,195);
      regions[2] = new Rectangle(5,0,245,5);
      regions[3] = new Rectangle(250,0,235,5);
      regions[4] = new Rectangle(485,5,5,195);
      regions[5] = new Rectangle(485,200,5,195);
      regions[6] = new Rectangle(250,395,235,5);
      regions[7] = new Rectangle(5,395,245,5);

      new ApproximatingPi();
   }

/**

The actionPerformed method. If the user clicks stop, we stop all the balls
and display the report. If the user clicks explanation, they get an explanation
of how pi is approximated.

<font size="4" color=#00008F>
<pre>
   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == stop) {
         output.close();

         displayPanel.setVisible(false);

         list.traverse();

         container.add(createJScrollPane(reportPanel = new ReportPanel(490,400),
                                         490,400,
                                         0,0));
      } else if (e.getSource() == explanation) {
         String message = "One interesting way of estimating the value of\n";
         message += "pi is to throw darts. Here we simulate dart throws by\n";
         message += "generating random points within a square of size 400 by 400.\n";
         message += "Consider this square as a bounding box with a circle\n";
         message += "contained in it. The ratio of the number of points generated\n";
         message += "within the circle to the number of points generated is an\n";
         message += "an approximation of the ratio of the area of the circle to\n";
         message += "the area of the square which is pi/4. So we multiply the\n";
         message += "ratio by 4 to produce an approximation of pi. Recall that because\n";
         message += "pi is irrational (and also transcendental) its decimal expansion\n";
         message += "can never be written exactly. We can only approximate it.";

         JOptionPane.showMessageDialog(null,message,"Explanation",JOptionPane.INFORMATION_MESSAGE);
      }
</pre>
</font>

*/

   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == stop) {
         output.close();

         displayPanel.setVisible(false);

         list.traverse();

         container.add(createJScrollPane(reportPanel = new ReportPanel(490,400),
                                         490,400,
                                         0,0));
      } else if (e.getSource() == explanation) {
         String message = "One interesting way of estimating the value of\n";
         message += "pi is to throw darts. Here we simulate dart throws by\n";
         message += "generating random points within a square of size 400 by 400.\n";
         message += "Consider this square as a bounding box with a circle\n";
         message += "contained in it. The ratio of the number of points generated\n";
         message += "within the circle to the number of points generated is an\n";
         message += "an approximation of the ratio of the area of the circle to\n";
         message += "the area of the square which is pi/4. So we multiply the\n";
         message += "ratio by 4 to produce an approximation of pi. Recall that because\n";
         message += "pi is irrational (and also transcendental) its decimal expansion\n";
         message += "can never be written exactly. We can only approximate it.";

         JOptionPane.showMessageDialog(null,message,"Explanation",JOptionPane.INFORMATION_MESSAGE);
      }
   }

/**

This main method creates a new instance of the frame, sets its size, sets its default
close operation, and makes it visible.

<font size="4" color=#00008F>
<pre>
   public static void main(String[] args) {
      Program5Option3 program5Option3 = new Program5Option3("Program 5 Option 3");

      program5Option3.setSize(500,540);

      program5Option3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      program5Option3.setVisible(true);
   }
</pre>
</font>

*/

   public static void main(String[] args) {
      Program5Option3 program5Option3 = new Program5Option3("Program 5 Option 3");

      program5Option3.setSize(500,540);

      program5Option3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      program5Option3.setVisible(true);
   }   

} 
