import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SynchronizingThreads extends JApplet implements ActionListener,Runnable {
   private Thread[] threads;
   private boolean[] suspended;
   private JButton[] buttons;
   private JTextField[] fields;

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

      button.setBorder(BorderFactory.createLineBorder(Color.red));

      return(button);
   }

   private JTextField createJTextField(int width,
                                       int height,
                                       int x,
                                       int y) {
      JTextField field = new JTextField();
      field.setSize(width,height);
      field.setLocation(x,y);

      field.setText("0");

      return(field);
   }

   public void init() {
      Container container = getContentPane();

      container.setLayout(null);

      threads = new Thread[5];

      suspended = new boolean[5];

      buttons = new JButton[5];

      for (int i=0;i<buttons.length;i++) {
         container.add(buttons[i] = createJButton("Thread " + (i+1),60,20,10+70*i,100));
         buttons[i].addActionListener(this);
      }

      fields = new JTextField[5];

      for (int i=0;i<fields.length;i++)
         container.add(fields[i] = createJTextField(60,20,10+70*i,150));
   }

   public void start() {
      for (int i=0;i<threads.length;i++) {
         if (threads[i] != null)
            threads[i] = null;
         threads[i] = new Thread(this);
         suspended[i] = true;
         threads[i].start();
      }
   }

   public int getIndex(Thread thread) {
      int index = 0;

      for (int i=0;i<threads.length;i++)
         if (thread == threads[i])
            index = i;

      return(index);
   }

   public void run() {
      Thread currentThread = Thread.currentThread();
      int index = getIndex(currentThread);

      while (threads[index] == currentThread) {
         try {
            Thread.sleep(500);
         } catch (InterruptedException ie) {
         }
      
         synchronized(this) {
            System.out.println(Thread.currentThread());
            try {
               while (suspended[index] &&
                      threads[index] == currentThread)
                  wait();
            } catch (InterruptedException ie) {
            }
         }
         fields[index].setText(String.valueOf(new Integer(fields[index].getText()).intValue() + 1));
//         System.out.println("Current thread is Thread " + (index+1));
      }
   }

   public void stop() {
      for (int i=0;i<suspended.length;i++)
         suspended[i] = true;
   }

   public void destroy() {
      for (int i=0;i<threads.length;i++)
         threads[i] = null;
   }

   public synchronized void actionPerformed(ActionEvent e) {
      for (int i=0;i<buttons.length;i++)
         if (e.getSource() == buttons[i]) {
            suspended[i] = false;
            for (int j=0;j<suspended.length;j++)
               if (j != i)
                  suspended[j] = true;

            notifyAll();
         }
   }
}



