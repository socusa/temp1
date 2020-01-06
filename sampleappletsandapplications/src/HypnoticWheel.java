import java.awt.*;
import javax.swing.*;
import java.applet.*;

public class HypnoticWheel extends JApplet implements Runnable {
   private int radius;
   private Thread thread;
   private boolean keepGoing;
   private boolean redAndWhite;
   private Image offScreenImage;
   private Graphics offScreenGraphics;
   private int i;
   private AudioClip music;

   public void init() {
      Container container = getContentPane();

      container.setLayout(null);

      container.setBackground(Color.white);

      radius = new Integer(getParameter("radius")).intValue();

      redAndWhite = true;

      music = getAudioClip(getCodeBase(),"soundshypnoticwheel/sleepy.au");

      offScreenImage = createImage(getWidth(),getHeight());
      offScreenGraphics = offScreenImage.getGraphics();
   }

   public void start() {
      thread = new Thread(this);
      keepGoing = true;
      thread.start();
   }

   public void paint(Graphics g) {
      offScreenGraphics.clearRect(0,0,getWidth(),getHeight());

      for (int j=i+20*10;j>=i;j-=15) {
         offScreenGraphics.setColor(new Color(200,0,0)); 

         offScreenGraphics.fillOval(getWidth()/2-j,getHeight()/2-j,2*j,2*j);

         offScreenGraphics.setColor(Color.white);

         offScreenGraphics.fillOval(getWidth()/2-(j-10),getHeight()/2-(j-10),2*(j-10),2*(j-10));
      }

      g.drawImage(offScreenImage,0,0,this);
   }

   public void run() {
      music.loop();

      while (keepGoing) {
         redAndWhite = !redAndWhite;

         for (i=getWidth()/2+20;i>0;i-=15) {
            try {
               Thread.sleep(100);
            } catch (InterruptedException ie) {
            }

            repaint();
         }
      }
   }

   public void stop() {
      music.stop();

      keepGoing = false;
   }

   public void destroy() {
      thread = null;
   }
}