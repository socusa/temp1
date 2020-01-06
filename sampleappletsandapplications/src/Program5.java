


import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.applet.*;

public class Program5 extends JApplet {
   private FilledJuliaSetPanel juliaSet;
   private MoveButtonsPanel moveButtons;
   private MoveImagesPanel moveImages;
   private ClockPanel clock;
   private JuliaSetControlPanel juliaSetControl;
   private ControlPanel control;
   private ScrollPanel scroll;

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

   private JCheckBox createJCheckBox(String label,
                                     int width,
                                     int height,
                                     int xlocation,
                                     int ylocation,
                                     ItemListener listener) {
      JCheckBox box = new JCheckBox(label);
      box.setSize(width,height);
      box.setLocation(xlocation,ylocation);

      box.addItemListener(listener);

      return(box);
   }

   private JRadioButton createJRadioButton(String label,
                                           ButtonGroup group,
                                           Color color,
                                           int width,
                                           int height,
                                           int xlocation,
                                           int ylocation,
                                           ItemListener listener) {
      JRadioButton button = new JRadioButton(label);
      button.setSize(width,height);
      button.setLocation(xlocation,ylocation);

      button.setBackground(color);

      group.add(button);

      button.addItemListener(listener);

      return(button);
   }

   private JComboBox createJComboBox(String[] choices,
                                     int width,
                                     int height,
                                     int xlocation,
                                     int ylocation,
                                     ItemListener listener) {
      JComboBox box = new JComboBox(choices);
      box.setSize(width,height);
      box.setLocation(xlocation,ylocation);

      box.addItemListener(listener);

      return(box);
   }

   private JTextField createJTextField(int width,
                                       int height,
                                       int xlocation,
                                       int ylocation) {
      JTextField field = new JTextField();
      field.setSize(width,height);
      field.setLocation(xlocation,ylocation);

      return(field);
   }

   private JLabel createJLabel(String text,
                               int width,
                               int height,
                               int xlocation,
                               int ylocation) {
      JLabel label = new JLabel("" + text + "");
      label.setSize(width,height);
      label.setLocation(xlocation,ylocation);

      return(label);
   }

   private void pause(double seconds) {
      try {
         Thread.sleep((int)(seconds*1000));
      } catch (InterruptedException ie) {
      }
   }

// In this function, we compute z^2 + c, where the complex
// number z is given as the parameter. We determine the
// real and imaginary parts of the complex number c, by 
// calling instance methods in the control panel. We then
// multiply the complex number z by itself and add the
// complex number c. We return the sum.

   private ComplexNumber f(ComplexNumber c1) {
      double creal = juliaSetControl.creal();
      double cimaginary = juliaSetControl.cimaginary();

      ComplexNumber temp = c1.multiply(c1);

      temp = temp.add(new ComplexNumber(creal,cimaginary));

      return(temp);
   }

// To make the tasks of multiply, adding, and finding the
// magnitudes of complex numbers more efficient, we create
// a definition of a complex number. The only instance
// variables associated with a complex number are its real
// and imaginary parts.

   public class ComplexNumber {
      private double realPart;
      private double imaginaryPart;

      public ComplexNumber(double realPart,
                           double imaginaryPart) {
         this.realPart = realPart;
         this.imaginaryPart = imaginaryPart;
      }

// A getter method to return the real part.

      public double realPart() {
         return(realPart);
      }

// A getter method to return the imaginary part.

      public double imaginaryPart() {
         return(imaginaryPart);
      }

// An instance method which computes the magnitude
// of the complex number.

      public double magnitude() {
         return(Math.sqrt(Math.pow(realPart,2) + Math.pow(imaginaryPart,2)));
      }

// An instance method which forms a new complex number
// by adding the real and imaginary parts of the caller
// of the method and the paramter. The sum is returned.

      public ComplexNumber add(ComplexNumber c) {
         double realPart = this.realPart() + c.realPart();
         double imaginaryPart = this.imaginaryPart() + c.imaginaryPart();

         return(new ComplexNumber(realPart,imaginaryPart));
      }

// An instance method which forms a new complex number
// by multiplying the caller of the method with the parameter.
// We use the simple rules of multiplication of polynomials.
// If the first complex number is a+bi and the second complex
// number is c+di, then the product is found as

// (a+bi)(c+di) = ac + bci + adi + bdi*i = (ac - bd) + (bc + ad)i

// We return the product.

      public ComplexNumber multiply(ComplexNumber c) {
         double realPart = this.realPart*c.realPart() - this.imaginaryPart*c.imaginaryPart();
         double imaginaryPart = this.realPart*c.imaginaryPart() + this.imaginaryPart()*c.realPart();

         return(new ComplexNumber(realPart,imaginaryPart));
      }
   }

// The panel that displays the filled Julia Set will be composed of
// a mosaic of small panels. Here we define those panels. Each panel
// contains a Color as an instance variable. When we determine the
// color associated with this panel, we call the setter method to set
// the color.

   public class MyPanel extends JPanel {
      private Color color;

      public MyPanel(int width,
                     int height,
                     int xlocation,
                     int ylocation) {
         super(true);

         setSize(width,height);
         setLocation(xlocation,ylocation);

         color = Color.white;
      }

// A setter method to set the color of the panel. After the
// color is set, the panel is repainted.

      public void setColor(Color color) {
         this.color = color;

         repaint();
      }

// We simply clear out the panel, set the drawing color, and
// then fill in the panel.

      public void paint(Graphics g) {
         g.clearRect(0,0,getWidth(),getHeight());

         super.paint(g);

         g.setColor(color);

         g.fillRect(0,0,getWidth(),getHeight());
      }
   }

// This is the filled Julia Set Panel. It contains a two-dimensional
// array of MyPanels.

   public class FilledJuliaSetPanel extends JPanel implements Runnable {
      private MyPanel[][] panels;

// An instance method which will convert a Java x-coordinate into
// a real x-coordinate.

      private double actualX(int value) {
         int upperx = juliaSetControl.upperx();
         int uppery = juliaSetControl.uppery();
         int lowerx = juliaSetControl.lowerx();
         int lowery = juliaSetControl.lowery();

         return((lowerx-upperx)/200.0*value+upperx);
      }

// An instance method which will convert a Java y-coordinate into
// a real y-coordinate.

      private double actualY(int value) {
         int upperx = juliaSetControl.upperx();
         int uppery = juliaSetControl.uppery();
         int lowerx = juliaSetControl.lowerx();
         int lowery = juliaSetControl.lowery();

         return((lowery-uppery)/200.0*value+uppery);
      }

// Here we perform the major task associated with drawing the
// filled Julia Set. We take the x and y coordinates, the real
// and imaginary parts of a complex number, and form a ComplexNumber.
// We then send the complex number to the function which computes
// z^2 + c. We take the output of that method and check its magnitude.
// If the magntide has gone beyond the value the user chose for
// infinity, then we return the number of times we have called the
// method. If we do this 15 times without going beyond infinity,
// we assume that the magnitude will not go beyond infinity and
// return the value indicating that the color black should be used.

      public int value(double x,
                       double y) {
         ComplexNumber temp = new ComplexNumber(x,y);

         for (int counter=1;counter<15;counter++) {
            temp = f(temp);

            double magnitude = temp.magnitude();

            if (magnitude > juliaSetControl.infinity())
               return(counter);
         }

// It is possible that the return will not be executed in the loop,
// so we have to include this return statement.

         return(15);
      }

// This thread definition will determine the colors of the panels
// in a column. The column is sent to the Thread as a parameter.
// We simply step through the column and convert each point in the
// column to real coordinates, and determine the color of the panel
// associated with a point as seen by Java.

      public class MyThread extends Thread {
         private int column;

         public MyThread(int column) {
            this.column = column;

            start();
         }

         public void run() {
            for (int counter=0;counter<getHeight();counter++) {
               double x = actualX(column);
               double y = actualY(counter);

               panels[column][counter].setColor(juliaSetControl.color(value(x,y)));
            }
         }
      }            

      public FilledJuliaSetPanel(int width,
                                 int height,
                                 int xlocation,
                                 int ylocation) {
         super(true);

         setSize(width,height);
         setLocation(xlocation,ylocation);

         setLayout(null);

         setBackground(Color.black);

// We make sure that the instance variable points to a real
// two-dimensional array of MyPanels. Then we create each
// one as a 1 x 1 panel. We add each small panel to the Filled
// Julia Set panel to form the mosaic.

         panels = new MyPanel[getWidth()][getHeight()];

         for (int counter=0;counter<panels.length;counter++)
            for (int counter1=0;counter1<panels[0].length;counter1++)
               add(panels[counter][counter1] = new MyPanel(1,1,counter,counter1));
      }

// When this method is called, the Filled Julia Set will be drawn.

      public void fill() {
         new Thread(this).start();
      }

// A MyThread is created for each column, and each thread runs
// independently.

      public void run() {
         for (int counter=0;counter<getWidth();counter++)
            new MyThread(counter);
      }
   }

// We now place the controls for the user.

   public class JuliaSetControlPanel extends JPanel implements ActionListener,ItemListener {
      private JComboBox red;
      private JComboBox green;
      private JComboBox blue;
      private int[] redValues;
      private int[] greenValues;
      private int[] blueValues;
      private JComboBox iterations;
      private JButton store;
      private JTextField upperx;
      private JTextField uppery;
      private JTextField lowerx;
      private JTextField lowery;
      private JTextField creal;
      private JTextField cimaginary;
      private JTextField infinity;
      private JButton draw;

// We initialize the arrays containing the colors associated
// with the iterations. We must be careful here. If we were
// to create the array within the method, we would not be able
// to affect the parameters.

      private void initialize(int[] red,
                              int[] green,
                              int[] blue) {
         for (int counter=0;counter<red.length-1;counter++) {
            red[counter] = 255-10*counter;
            green[counter] = 0;
            blue[counter] = 100+10*counter;
         }

         red[14] = 0;
         green[14] = 0;
         blue[14] = 0;

         upperx.setText("-2");
         uppery.setText("2");
         lowerx.setText("2");
         lowery.setText("-2");
         creal.setText("-1");
         cimaginary.setText("0");
         infinity.setText("4");
      }

      public JuliaSetControlPanel(int width,
                                  int height,
                                  int xlocation,
                                  int ylocation) {
         super(true);

         setSize(width,height);
         setLocation(xlocation,ylocation);

         setBackground(Color.blue);

         setLayout(null);

         String[] choices = new String[256];

         for (int counter=0;counter<choices.length;counter++)
            choices[counter] = counter + "";

         add(createJLabel("Red",50,20,20,5));
         add(red = createJComboBox(choices,50,20,70,5,this));
         add(createJLabel("Green",50,20,140,5));
         add(green = createJComboBox(choices,50,20,200,5,this));
         add(createJLabel("Blue",50,20,270,5));
         add(blue = createJComboBox(choices,50,20,330,5,this));

         choices = new String[15];

         for (int counter=0;counter<choices.length;counter++)
            choices[counter] = (counter+1) + "";

         add(createJLabel("Iterations",100,20,20,35));
         add(iterations = createJComboBox(choices,50,20,130,35,this));

         add(store = createJButton("Store",50,20,220,35,this));

         redValues = new int[15];
         greenValues = new int[15];
         blueValues = new int[15];

         red.setSelectedItem(redValues[0] + "");
         green.setSelectedItem(greenValues[0] + "");
         blue.setSelectedItem(blueValues[0] + "");

         add(createJLabel("Upper X",100,20,20,60));
         add(upperx = createJTextField(100,20,130,60));
         add(createJLabel("Upper Y",100,20,20,80));
         add(uppery = createJTextField(100,20,130,80));
         add(createJLabel("Lower X",100,20,20,100));
         add(lowerx = createJTextField(100,20,130,100));
         add(createJLabel("Lower Y",100,20,20,120));
         add(lowery = createJTextField(100,20,130,120));
         add(createJLabel("C real",100,20,20,140));
         add(creal = createJTextField(100,20,130,140));
         add(createJLabel("C imaginary",100,20,20,160));
         add(cimaginary = createJTextField(100,20,130,160));
         add(createJLabel("Infinity",100,20,20,180));
         add(infinity = createJTextField(100,20,130,180));

         initialize(redValues,greenValues,blueValues);
 
         red.setSelectedItem(redValues[0] + "");
         green.setSelectedItem(greenValues[0] + "");
         blue.setSelectedItem(blueValues[0] + "");

         add(draw = createJButton("Draw",100,100,getWidth()-125,getHeight()-125,this));
      }

      public int upperx() {
         return(Integer.parseInt(upperx.getText()));
      }

      public int uppery() {
         return(Integer.parseInt(uppery.getText()));
      }

      public int lowerx() {
         return(Integer.parseInt(lowerx.getText()));
      }

      public int lowery() {
         return(Integer.parseInt(lowery.getText()));
      }

      public double creal() {
         return(Double.parseDouble(creal.getText()));
      }

      public double cimaginary() {
         return(Double.parseDouble(cimaginary.getText()));
      }

      public double infinity() {
         return(Double.parseDouble(infinity.getText()));
      }

      public Color color(int number) {
         return(new Color(redValues[number-1],
                          greenValues[number-1],
                          blueValues[number-1]));
      }

// If the user clicks Draw, then we call the fill method in
// the Filled Julia Set panel. If the user chooses store,
// we store the current values in the JComboBoxes in the color
// arrays.

      public void actionPerformed(ActionEvent e) {
         if (e.getSource() == draw)
            juliaSet.fill();
         else if (e.getSource() == store) {
            int number = Integer.parseInt(iterations.getSelectedItem().toString());

            redValues[number-1] = Integer.parseInt(red.getSelectedItem().toString());
            greenValues[number-1] = Integer.parseInt(green.getSelectedItem().toString());
            blueValues[number-1] = Integer.parseInt(blue.getSelectedItem().toString());
         }
      }

// Whenever the user selects one of the iterations, then
// show the color associated with the iteration.

      public void itemStateChanged(ItemEvent e) {
         if (e.getSource() == iterations && e.getStateChange() == ItemEvent.SELECTED) {
            int iteration = Integer.parseInt(iterations.getSelectedItem().toString());

            red.setSelectedItem(redValues[iteration-1] + "");
            green.setSelectedItem(greenValues[iteration-1] + "");
            blue.setSelectedItem(blueValues[iteration-1] + "");
         }
      }
   }

// Now we create the panel which will scroll the facts about Julia.
// We create a String variable called message. We then place all
// of the facts into this String. We use the variable startx to
// determine where the String will be drawn.

   public class ScrollPanel extends JPanel implements Runnable {
      private String message;
      private int startx;
      private boolean keepGoing;

      public ScrollPanel(int width,
                         int height,
                         int xlocation,
                         int ylocation) {
         super(true);

         setSize(width,height);
         setLocation(xlocation,ylocation);

         setBackground(Color.yellow);

         message = "Gaston Julia was born in 1893. ";
         message += "When Gaston Julia was 25 he published a masterpiece. ";
         message += "Gaston Julia was wounded in World War I. ";
         message += "Gaston Julia had to wear a leather strap across his face. ";
         message += "Gaston Julia became a professor in Paris. ";
         message += "Gaston Julia published a beautiful paper in 1918 about iterations of a rational function f. ";
         message += "There was a seminar in Germany in 1925 to study his work. ";
         message += "Gaston Julia had an interest in music. ";
         message += "Gaston Julia's work was forgotten until Mandelbrot mentioned it. ";
         message += "Gaston Julia discovered the Julia Set with his contemporary Pierre Fatou.";

// We initially draw the String to the right of the right edge of the applet.

         startx = getWidth()+10;

         start();
      }

      public void start() {
         keepGoing = true;

         new Thread(this).start();
      }

      public void stop() {
         keepGoing = false;
      }

// In the paint method, we use the FontMetrics class to
// determine how much space the String message takes up
// in the font used in the panel. If the sum of startx
// and the width of the String is less than 0, then that
// means the String has moved off the left-hand side of
// the applet. In this case, we change startx to be off
// the right-hand side of the applet.

      public void paint(Graphics g) {
         g.clearRect(0,0,getWidth(),getHeight());

         super.paint(g);

         g.setFont(new Font("Arial",Font.BOLD+Font.ITALIC,20));

         FontMetrics metrics = g.getFontMetrics();

         int width = metrics.stringWidth(message);

         int height = metrics.getHeight();

         if (startx + width < 0)
            startx = getWidth()+10;

         g.drawString(message,startx,getHeight()/2+height/4);
      }

// In the run method, we decrease startx by 10, repaint the applet,
// and pause for 1/10 of a second.

      public void run() {
         while (keepGoing) {
            startx -= 10;

            repaint();

            pause(0.1);
         }
      }
   }

// We now move buttons around a panel. We create an array of JButtons.

   public class MoveButtonsPanel extends JPanel implements Runnable {
      private JButton[] buttons;
      private boolean keepGoing;
      private AudioClip clip;

// We use this method to produce the next y position for a button.
// This method produces the sawtooth.

      private int f(int input) {
         if (input < 100)
            return(input);
         else if (input >= 100 && input < 200)
            return(200-input);
         else if (input >= 200 && input < 300)
            return(input-200);
         else
            return(400-input);
      }

      public MoveButtonsPanel(int width,
                              int height,
                              int xlocation,
                              int ylocation) {
         super(true);

         setSize(width,height);
         setLocation(xlocation,ylocation);

         setBackground(Color.green);

         setLayout(null);

         String[] labels = {"C","A","L","C","U","L","U","S"};

         buttons = new JButton[8];

         for (int counter=0;counter<buttons.length;counter++)
            add(buttons[counter] = createJButton(labels[counter],
                                                 20,20,
                                                 400+counter*30,f(400+counter*30),
                                                 null));

         clip = getAudioClip(getCodeBase(),"soundsprogram5/survivors_eyeofthetiger.mid");

         start();
      }

// This method allows us to either stop or start the music.

      public void play(boolean play) {
         if (play)
            clip.loop();
         else
            clip.stop();
      }

// This method allows us to start the thread.

      public void start() {
         keepGoing = true;

         new Thread(this).start();
      }

// This method allows us to stop the buttons from
// moving.

      public void stop() {
         keepGoing = false;
      }

// In this run method we keep moving the buttons from the right
// side to the left side until all of the buttons disappear. When
// they all have, we start the buttons again from the right side.

      public void run() {
         int startx = 400;

         while (keepGoing) {
            for (int counter=0;counter<buttons.length;counter++)
               buttons[counter].setLocation(startx+30*counter-10,
                                            f(startx+30*counter)-10);

            pause(0.1);

            startx -= 5;

            if (buttons[buttons.length-1].getLocation().x < 0)
               startx = 400;
         }
      }
   }

// In this panel, we make use of the fact that we do not have to
// draw things on a panel in the visible area.

   public class MoveImagesPanel extends JPanel implements Runnable {
      private Image[] images;
      private int startx;
      private boolean keepGoing;

      public MoveImagesPanel(int width,
                             int height,
                             int xlocation,
                             int ylocation) {
         super(true);

         setSize(width,height);
         setLocation(xlocation,ylocation);

         setBackground(Color.red);

         String[] fileNames = {"iolab2.jpg","1.jpg","2.jpg","dorothy.jpg","effie.jpg",
                               "effie2.jpg","eunice.jpg","fransdre.jpg","iola.jpg","iolab.jpg"};

// We now load the images.

         images = new Image[fileNames.length];

         for (int counter=0;counter<images.length;counter++)
            images[counter] = getImage(getCodeBase(),"imagesprogram5/" + fileNames[counter]);

// We use the variable startx to determine where the first image will begin
// to be drawn.

         startx = getWidth()+10;

         start();
      }

      public void start() {
         keepGoing = true;

         new Thread(this).start();
      }

      public void stop() {
         keepGoing = false;
      }

// In this paint method, all images are drawn relative to 
// startx. We draw the first image at startx. From that
// point we draw all of the images relative to startx and
// put 10 pixels between images.

      public void paint(Graphics g) {
         g.clearRect(0,0,getWidth(),getHeight());

         super.paint(g);

         int nextx = startx;

         for (int counter=0;counter<images.length;counter++) {
            g.drawImage(images[counter],nextx,getHeight()/2-images[counter].getHeight(this)/2,this);

            nextx += images[counter].getWidth(this) + 10;
         }

// If nextx is negative, that means that all of the images are off
// the left-hand side of the applet, so we reset startx to be off
// the right-hand side of the applet.

         if (nextx < 0)
            startx = getWidth()+10;
      }

// In this run method, we decrease startx by 10, repaint the panel,
// and pause for 1/10 of a second.

      public void run() {
         while (keepGoing) {
            startx -= 10;

            repaint();

            pause(0.1);
         }
      }
   }

// In this panel, we define the clock. We have four JRadioButtons
// that controls which timezone we use. We use the instance variable
// adjustment to indicate what adjustment needs to be made to the
// current hour for the timezone that the user has selected.

   public class ClockPanel extends JPanel implements Runnable,ItemListener {
      private JRadioButton[] zones;
      private String time;
      private boolean keepGoing;
      private int adjustment;

      public ClockPanel(int width,
                        int height,
                        int xlocation,
                        int ylocation) {
         super(true);

         setSize(width,height);
         setLocation(xlocation,ylocation);

         setBackground(Color.orange);

         setLayout(null);

         String[] labels = {"Pacific","Mountain","Central","Eastern"};

         zones = new JRadioButton[4];

         ButtonGroup group = new ButtonGroup();

         for (int counter=0;counter<zones.length;counter++)
            add(zones[counter] = createJRadioButton(labels[counter],
                                                    group,
                                                    Color.orange,
                                                    100,20,
                                                    (getWidth()-400)/2+counter*100,getHeight()-30,
                                                    this));

         zones[2].setSelected(true);

         adjustment = 0;

         start();
      }

      public void start() {
         keepGoing = true;

         new Thread(this).start();
      }

      public void stop() {
         keepGoing = false;
      }

      public void paint(Graphics g) {
         g.clearRect(0,0,getWidth(),getHeight());

         super.paint(g);

         g.setFont(new Font("Arial",Font.BOLD,40));

         FontMetrics metrics = g.getFontMetrics();

         int width = metrics.stringWidth(time);

         g.setColor(new Color(0,0,200));
    
         g.drawString(time,getWidth()/2-width/2,40);
      }

// In the run method, we do all of the work. We determine
// the current hour of the day, the minute, and the second.
// We then add the adjustment. Now that we have the correct
// hour, we figure out whether it is am or pm. After determing
// this, we then decrease hour so that it is not greater than 12.
// When the put the information together and repaint the panel.

      public void run() {
         while (keepGoing) {
            Calendar c = Calendar.getInstance();

            int hour = (c.get(Calendar.HOUR_OF_DAY)+adjustment) % 24;
            int minute = c.get(Calendar.MINUTE);
            int second = c.get(Calendar.SECOND);

            String amorpm = (hour >= 12) ? "pm" : "am";

            hour -= (hour > 12) ? 12 : 0;

            hour = (hour == 0) ? 12 : hour;

            time = hour + ":";
            time += (minute >= 10) ? "" + minute : "0" + minute;
            time += ":";
            time += (second >= 10) ? "" + second : "0" + second;
            time += " " + amorpm;

            repaint();

            pause(0.5);
         }
      }

      public void itemStateChanged(ItemEvent e) {
         JRadioButton button = (JRadioButton)e.getSource();

         if (button == zones[0])
            adjustment = -2;
         else if (button == zones[1])
            adjustment = -1;
         else if (button == zones[2])
            adjustment = 0;
         else
            adjustment = 1;
      }
   }

// In this panel, we place JCheckBoxes that indicate whether or not
// the other panels will be displayed. Initially they are all unselected.

   public class ControlPanel extends JPanel implements ItemListener {
      private JCheckBox[] boxes;

      public ControlPanel(int width,
                          int height,
                          int xlocation,
                          int ylocation) {
         super(true);

         setSize(width,height);
         setLocation(xlocation,ylocation);
 
         setBackground(new Color(204,204,204));

         setLayout(null);

         String[] labels = {"Julia Set","Buttons","Clock","Images","Scroller"};

         boxes = new JCheckBox[5];

         for (int counter=0;counter<boxes.length;counter++)
            add(boxes[counter] = createJCheckBox(labels[counter],
                                                 100,20,
                                                 (getWidth()-500)/2+counter*100,getHeight()/2-10,
                                                 this));
      }

// In this method, we determine what the source of the event is,
// and whether it was a selection.

// If the user clicked on the Julia Set, then we display both the
// FilledJuliaSet panel, and the Julia Set Control Panel, or hide
// both depending on whether there was a selection or deselection.

// If the user clicked on the Button panel, then we start the buttons
// moving and play the music depending on whether there was a selection
// or deselection.

// If the user clicked on the others, then we either display or hide
// the panels depending on whether it was a selection or deselection.

      public void itemStateChanged(ItemEvent e) {
         JCheckBox source = (JCheckBox)e.getSource();

         boolean selected = (e.getStateChange() == ItemEvent.SELECTED);

         if (source == boxes[0]) {
            juliaSet.setVisible(selected);
            juliaSetControl.setVisible(selected);
         } else if (source == boxes[1]) {
            moveButtons.setVisible(selected);
            moveButtons.play(selected);
         } else if (source == boxes[2])
            clock.setVisible(selected);
         else if (source == boxes[3])
            moveImages.setVisible(selected);
         else if (source == boxes[4])
            scroll.setVisible(selected);
      }            
   }

   public void init() {
      Container container = getContentPane();

      container.setLayout(null);

      container.setBackground(Color.white);

      container.add(juliaSet = new FilledJuliaSetPanel(200,200,0,0));
      container.add(moveButtons = new MoveButtonsPanel(400,100,200,0));
      container.add(clock = new ClockPanel(400,100,200,100));
      container.add(moveImages = new MoveImagesPanel(200,200,400,200));
      container.add(juliaSetControl = new JuliaSetControlPanel(400,200,0,200));
      container.add(scroll = new ScrollPanel(600,50,0,400));
      container.add(control = new ControlPanel(600,50,0,450));

      juliaSet.setVisible(false);
      moveButtons.setVisible(false);
      clock.setVisible(false);
      moveImages.setVisible(false);
      juliaSetControl.setVisible(false);
      scroll.setVisible(false);
   }

// If the Buttons panel is visible when the applet is displayed,
// then we start the music.

   public void start() {
      if (moveButtons.isVisible())
         moveButtons.play(true);
   }

// We stop the music from playing when the applet's page isn't visible.

   public void stop() {
      moveButtons.play(false);
   }   
}