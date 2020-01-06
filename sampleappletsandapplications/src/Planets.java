import java.awt.*;
import javax.swing.*;

public class Planets extends JApplet {
// Because I want to be able to use the applet's container
// within an inner class, we make container an instance
// variable.

   private Container container;
   private Orbit mercury;
   private Orbit venus;
   private Orbit earth;
   private Orbit mars;
   private Orbit jupiter;
   private Orbit saturn;
   private Orbit uranus;
   private Orbit neptune;
   private Orbit pluto;

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

// We use this inner class to store a point. We
// make the instance variables public to make them
// easy to access.

   public class Point {
      public double x;
      public double y;

      public Point(double x,
                   double y) {
         this.x = x;
         this.y = y;
      }
   }

// Note that this inner class directly extends
// Thread. We cannot do this with an applet
// because applet's extend JApplet.
   
   public class Orbit extends Thread {
      private int centerx;
      private int centery;
      private int radius;
      private double earthYears;
      private String letter;
      private Point[] points;
      private boolean keepGoing;
      private JButton planet;      

      private double toRadian(int degrees) {
         return(degrees*3.1415926535/180);
      }

      public Orbit(int centerx,
                   int centery,
                   int radius,
                   double earthYears,
                   String letter) {
         this.centerx = centerx;
         this.centery = centery;
         this.radius = radius;
         this.earthYears = earthYears;
         this.letter = letter;

         points = new Point[8];

         points[0] = new Point(centerx,centery-radius);
         points[1] = new Point(centerx+radius*Math.cos(toRadian(45)),
                               centery-radius*Math.sin(toRadian(45)));
         points[2] = new Point(centerx+radius,centery);
         points[3] = new Point(centerx+radius*Math.cos(toRadian(45)),
                               centery+radius*Math.sin(toRadian(45)));
         points[4] = new Point(centerx,centery+radius);
         points[5] = new Point(centerx-radius*Math.cos(toRadian(45)),
                               centery+radius*Math.sin(toRadian(45)));
         points[6] = new Point(centerx-radius,centery);
         points[7] = new Point(centerx-radius*Math.cos(toRadian(45)),
                               centery-radius*Math.sin(toRadian(45)));

// We now add a block indicating the planet to the applet's
// container.

         container.add(planet = createJButton(letter,16,16,
                                              (int)(points[2].x-8),
                                              (int)(points[2].y-8)));
         
         keepGoing = true;

         start();
      }

      public void run() {
         while (keepGoing) {
            for (int i=2;i<10;i++) {
               planet.setVisible(false);

               planet.setLocation((int)(points[i-2].x-8),
                                  (int)(points[i-2].y-8));

               planet.setVisible(true);

               try {
                  Thread.sleep((int)(500*earthYears));
               } catch (InterruptedException ie) {
               }

               repaint();
            }
         }
      }
   }

// We now want to place independent panels on the
// applet's container. We want a seperate program
// to run for the panel so we make it implement
// Runnable.

   public class Star extends JPanel implements Runnable {
      private Thread thread;
      private boolean keepGoing;
      private int centerx;
      private int centery;
      private int radius;
      private int factor;

      public Star(int width,
                  int height,
                  int x,
                  int y) {
         centerx = width/2;
         centery = height/2;
         radius = width/2;

         setSize(width,height);
         setLocation(x,y);

         thread = new Thread(this);
         keepGoing = true;
         thread.start();
      }

      public void paint(Graphics g) {
         g.clearRect(0,0,getWidth(),getHeight());

         g.setColor(Color.white);

// We now draw a rectangle as part of the star.

         g.fillRect(centerx-radius/factor,centery-radius/factor,2*radius/factor,2*radius/factor);

// We now fill the star.

         int[] xpoints1 = {centerx-radius/factor,centerx+radius/factor,centerx};
         int[] ypoints1 = {centery-radius/factor,centery-radius/factor,centery-radius};

         g.fillPolygon(xpoints1,ypoints1,3);

         int[] xpoints2 = {centerx+radius/factor,centerx+radius/factor,centerx+radius};
         int[] ypoints2 = {centery-radius/factor,centery+radius/factor,centery};

         g.fillPolygon(xpoints2,ypoints2,3);

         int[] xpoints3 = {centerx-radius/factor,centerx+radius/factor,centerx};
         int[] ypoints3 = {centery+radius/factor,centery+radius/factor,centery+radius};

         g.fillPolygon(xpoints3,ypoints3,3);

         int[] xpoints4 = {centerx-radius/factor,centerx-radius/factor,centerx-radius};
         int[] ypoints4 = {centery-radius/factor,centery+radius/factor,centery};

         g.fillPolygon(xpoints4,ypoints4,3);
      }

      public void run() {
         while (keepGoing) {
            if (factor == 3)
               factor = 6;
            else
               factor = 3;

            try {
               Thread.sleep(1000);
            } catch (InterruptedException ie) {
            }

            this.repaint();
         }
      }
   }

   public void init() {
      container = getContentPane();

      container.setLayout(null);

      container.setBackground(Color.black);

      mercury = new Orbit(getWidth()/2,getHeight()/2,30,0.24,"M");

      venus = new Orbit(getWidth()/2,getHeight()/2,50,0.62,"V");

      earth = new Orbit(getWidth()/2,getHeight()/2,70,1.0,"E");

      mars = new Orbit(getWidth()/2,getHeight()/2,90,1.88,"M");

      jupiter = new Orbit(getWidth()/2,getHeight()/2,110,12,"J");

      saturn = new Orbit(getWidth()/2,getHeight()/2,130,29.5,"S");
 
      uranus = new Orbit(getWidth()/2,getHeight()/2,150,84,"U");

      neptune = new Orbit(getWidth()/2,getHeight()/2,170,165,"N");

      pluto = new Orbit(getWidth()/2,getHeight()/2,190,248,"P");

      for (int i=0;i<100;i++)
         container.add(new Star(10,10,
                                (int)(getWidth()*Math.random()),
                                (int)(getHeight()*Math.random())));
   }

   public void paint(Graphics g) {
      setBackground(Color.black);

      g.setColor(Color.yellow);

      g.fillOval(getWidth()/2-5,getHeight()/2-5,10,10);
   }
}