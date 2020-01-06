import java.awt.*;
import javax.swing.*;

public class BouncingBall extends JApplet implements Runnable {
   private Thread thread;
   private boolean keepGoing;
   private int x;
   private int y;
   private double angle;

   public class GoneTooFarException extends Exception {

      public GoneTooFarException() {
      }

      public GoneTooFarException(String message) {
         super(message);
      }
   }

   public void init() {
      x = (int)(getWidth()*Math.random());
      y = (int)(getHeight()*Math.random());
      angle = 360*Math.random();
   }

   public void start() {
      if (thread != null)
         thread = null;

      thread = new Thread(this);
      keepGoing = true;
      thread.start();
   }

   public void paint(Graphics g) {
      g.clearRect(0,0,getWidth(),getHeight());
      g.fillOval(x-10,y-10,20,20);
   }

   public void run() {
      while (keepGoing) {
         try {
            Thread.sleep(50);
         } catch (InterruptedException ie) {
         }

         boolean newBouncingBallOutsideOfScreen = true;

         while (newBouncingBallOutsideOfScreen) {
            try {
               x += (int)(5*Math.cos(angle*3.1415926535/180));
               y += (int)(5*Math.sin(angle*3.1415926535/180));
               if (x-10 <= 0 ||
                   x+10 >= getWidth() ||
                   y-10 <= 0 ||
                   y+10 >= getHeight())
                  throw new GoneTooFarException();
               newBouncingBallOutsideOfScreen = false;
            } catch (GoneTooFarException gtfe) {
               angle = 360*Math.random();
            }
         }

         repaint();
      }
   }
}
