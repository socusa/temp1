import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Conversions extends JApplet {
   private Image[] digits;
   private JCheckBox[] display;
   private String[] bases;
   private String[] equivalents;
   private String decimalNumber;
   private JPanel controlPanel;
   private MyThread counter;
   private int current;

   private JCheckBox createJCheckBox(String label,
                                     int width,
                                     int height,
                                     int x,
                                     int y) {
      JCheckBox box = new JCheckBox(label,true);
      box.setSize(width,height);
      box.setLocation(x,y);

      box.setBackground(Color.white);

      return(box);
   }

   private JPanel createJPanel(int width,
                               int height,
                               int x,
                               int y) {
      JPanel panel = new JPanel();
      panel.setSize(width,height);
      panel.setLocation(x,y);

      panel.setLayout(null);

      panel.setBackground(Color.white);

      return(panel);
   }

   private String convert(int decimalNumber,int base) {
      String output = "";

      int highestPower = (int)(Math.log(decimalNumber)/Math.log(base));

      int remainder = decimalNumber;

      for (int i=highestPower;i>0;i--) {
         int quotient = remainder/((int)(Math.pow(base,i)));

         output += quotient;

         remainder = remainder % ((int)(Math.pow(base,i)));
      }

      output += remainder;

      while (output.length() < 11)
         output = "0" + output;

      return(output);
   }

   public class MyThread extends Thread {
      private boolean keepGoing;

      public MyThread() {
         keepGoing = true;

         current = 1;

         start();
      }

      public void run() {
         while (keepGoing) {
            decimalNumber = String.valueOf(current);

            while (decimalNumber.length() < 11)
               decimalNumber = "0" + decimalNumber;

            repaint();

            try {
               Thread.sleep(1000);
            } catch (InterruptedException ie) {
            }

            current++;
         }
      }
   }
        
   public void init() {
      Container container = getContentPane();

      container.setLayout(null);

      container.setBackground(Color.white);

      digits = new Image[10];

      digits[0] = getImage(getCodeBase(),"digitsoctagonoffortune/zero.gif");
      digits[1] = getImage(getCodeBase(),"digitsoctagonoffortune/one.gif");
      digits[2] = getImage(getCodeBase(),"digitsoctagonoffortune/two.gif");
      digits[3] = getImage(getCodeBase(),"digitsoctagonoffortune/three.gif");
      digits[4] = getImage(getCodeBase(),"digitsoctagonoffortune/four.gif");
      digits[5] = getImage(getCodeBase(),"digitsoctagonoffortune/five.gif");
      digits[6] = getImage(getCodeBase(),"digitsoctagonoffortune/six.gif");
      digits[7] = getImage(getCodeBase(),"digitsoctagonoffortune/seven.gif");
      digits[8] = getImage(getCodeBase(),"digitsoctagonoffortune/eight.gif");
      digits[9] = getImage(getCodeBase(),"digitsoctagonoffortune/nine.gif");

      equivalents = new String[10];

      equivalents[2] = "00000000000";
      equivalents[3] = "00000000000";
      equivalents[4] = "00000000000";
      equivalents[5] = "00000000000";
      equivalents[6] = "00000000000";
      equivalents[7] = "00000000000";
      equivalents[8] = "00000000000";
      equivalents[9] = "00000000000";

      bases = new String[10];

      bases[2] = "Binary: ";
      bases[3] = "Ternary: ";
      bases[4] = "Quadrary: ";
      bases[5] = "Quintary: ";
      bases[6] = "Hexary: ";
      bases[7] = "Septary: ";
      bases[8] = "Octary: ";
      bases[9] = "Nonary: ";

      container.add(controlPanel = createJPanel(getWidth(),50,0,getHeight()-50));

      display = new JCheckBox[10];

      for (int i=2;i<=9;i++)
         controlPanel.add(display[i] = createJCheckBox(bases[i],80,20,10+((i-2)/2)*80,10+((i-2) % 2)*20));

      counter = new MyThread();
   }

   public void drawNumbers(Graphics g,int minimum,int maximum,int horizontalBase) {
      FontMetrics metrics = g.getFontMetrics();

      int maxWidth = 0;

      for (int i=minimum;i<=maximum;i++) {
         g.drawString(bases[i],horizontalBase,80+(i-minimum)*30);

         int width = metrics.stringWidth(bases[i]);

         maxWidth = (width > maxWidth) ? width : maxWidth;
      }

      int x = maxWidth+horizontalBase;

      for (int i=2;i<=9;i++)
         equivalents[i] = convert(current,i);

      for (int i=minimum;i<=maximum;i++) {
         x = maxWidth+horizontalBase;

         if (display[i].isSelected()) {
            for (int j=0;j<equivalents[i].length();j++) {
               int digit = new Integer(equivalents[i].substring(j,j+1)).intValue();

               g.drawImage(digits[digit],x,80+(i-minimum)*30-digits[digit].getHeight(this),this);
   
               x += digits[digit].getWidth(this);
            }
         }
      }         
   }

   public void paint(Graphics g) {
      setBackground(Color.black);

      for (int i=2;i<=9;i++)
         display[i].repaint();

      Font font = new Font("Serif",Font.BOLD,14);

      g.setFont(font);

      g.setColor(Color.white);

      FontMetrics metrics = g.getFontMetrics();

      int width = metrics.stringWidth("Decimal: ");

      g.drawString("Decimal: ",100,30);

      int x = 100+width+10;
      int y = 30-digits[0].getHeight(this);

      for (int i=0;i<decimalNumber.length();i++) {
         int digit = new Integer(decimalNumber.substring(i,i+1)).intValue();

         g.drawImage(digits[digit],x,y,this);

         x += digits[digit].getWidth(this);
      }

      drawNumbers(g,2,5,10);
      drawNumbers(g,6,9,250);
   }
}