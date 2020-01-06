import java.applet.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Countdown extends JApplet implements Runnable {
   private Thread thread;
   private boolean keepGoing;
   private Image picture[];
   private boolean am;
   private int currentDay;
   private int numberOfDaysLeft;
   private int actual;
   private int target;
   private int adjustment;
   private AudioClip audio;
   private MediaTracker tracker;
   private CountdownPanel countdownPanel;
   private ControlPanel controlPanel;
   private boolean started;
   
   public class CountdownPanel extends JPanel {
	   
	   public CountdownPanel(int width,int height) {
		   setPreferredSize(new Dimension(width,height));
		   
		   setBackground(Color.blue);
		   
	   }
	   
	   public void paintComponent(Graphics g) {
		   super.paintComponent(g);
		   
		   g.setColor(Color.white);
		   
	      if (started) {	      
	    	  
	         int current = get_current() + adjustment;

	         int difference = target-current;

	    	  System.out.println("current is " + current);
	    	  
	    	  System.out.println("target is " + target);
	    	  
	         numberOfDaysLeft = MyCalendar.number_of_days(difference);

	         String output = String.valueOf(numberOfDaysLeft);

	         if (numberOfDaysLeft<10)
	            output = " " + output;

//	         for (int count=0;count<s.length();count++)
//	            if (!s.substring(count,count+1).equals(" "))
//	               g.drawImage(picture[new Integer(s.substring(count,count+1)).intValue()],20*count,0,20,20,this);
	         
	         g.setFont(new Font("Comic Sand MS",Font.BOLD+Font.ITALIC,40));
	        
	         FontMetrics metrics = g.getFontMetrics();
	         
	         output += " days ";
	        
//	         g.drawString("days", 20*s.length() + 10, 30);
	         
	         int width = metrics.stringWidth("days");
	         
	         int numberOfHoursLeft = MyCalendar.number_of_hours(difference);

	         String s = String.valueOf(numberOfHoursLeft);

	         if (numberOfHoursLeft<10)
	            s = " " + s;
	         
	         output += s;
	         
	         output += " hours ";

//	         for (int count=0;count<s.length();count++)
//	            if (!s.substring(count,count+1).equals(" "))
//	               g.drawImage(picture[new Integer(s.substring(count,count+1)).intValue()],width + 10 + +20*count,0,20,20,this);
	         

//	         g.drawString("minutes", width + 10 + 20*2 + 10, 30);
	         
	         int numberOfMinutesLeft = MyCalendar.number_of_minutes(difference);

	         s = String.valueOf(numberOfMinutesLeft);

	         if (numberOfMinutesLeft<10)
	            s = " " + s;
	         
	         output += s;
	         
	         int startx = width + 10 + 20*2;

	    	 width = metrics.stringWidth("minutes");

	         output += " minutes ";
	         
//	         for (int count=0;count<s.length();count++)
//	            if (!s.substring(count,count+1).equals(" "))
//	               g.drawImage(picture[new Integer(s.substring(count,count+1)).intValue()],startx+20*count,0,20,20,this);

	         int numberOfSecondsLeft = MyCalendar.number_of_seconds(difference);

	         s = String.valueOf(numberOfSecondsLeft);

	         if (numberOfSecondsLeft<10)
	            s = " " + s;
	         
	         output += s;
	         
	         output += " seconds";

//	         for (int count=0;count<s.length();count++)
//	            if (!s.substring(count,count+1).equals(" "))
//	               g.drawImage(picture[new Integer(s.substring(count,count+1)).intValue()],300+20*count,0,20,20,this);
	         
	         g.drawString("There are ", 0, 35);
	         
	         int height = metrics.getHeight();
	         
	         g.drawString(output, 0, height + 30);
	         
	         g.drawString("until " + controlPanel.getTime(), 0, 2*height + 25);

	      }
	   }  
	   
   }
   
   public class ControlPanel extends JPanel implements ActionListener {
	   private JComboBox months;
	   private JComboBox targetDay;
	   private int[] days;
	   private int month;
	   private Map<String,Integer> daysInMonths;
	   private JComboBox targetHour;
	   private JComboBox targetMinute;
	   private JComboBox targetSecond;
	   private JComboBox targetAmOrPm; 
	   private JButton startCountdown;
	   private String[] names;
	   
	   public ControlPanel(int width,int height) {
		   setPreferredSize(new Dimension(width,height));
		   
		   setBackground(Color.cyan);
		   
		   setLayout(new FlowLayout());
		   
		   JPanel panel = new JPanel();
		   
		   panel.setLayout(new GridLayout(2,7));
		   
		   panel.add(new JLabel("Target Month"));
		   
		   java.util.List<String> list = new ArrayList<>();
		   
		   Calendar c = Calendar.getInstance();
		   
		   month = c.get(Calendar.MONTH);
		   
		   days = new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
		   
		   names = new String[]{"January","February","March",
				                "April","May","June",
				                "July","August","September",
				                "October","November","Decmeber"};
		   
		   daysInMonths = new HashMap<>();
		   
		   daysInMonths.put("January", 31);
		   daysInMonths.put("February", 28);
		   daysInMonths.put("March", 31);
		   daysInMonths.put("April", 30);
		   daysInMonths.put("May", 31);
		   daysInMonths.put("June",30);
		   daysInMonths.put("July", 31);
		   daysInMonths.put("August", 31);
		   daysInMonths.put("September", 30);
		   daysInMonths.put("October", 31);
		   daysInMonths.put("November",30);
		   daysInMonths.put("December",31);
		   
		   for (int counter=month;counter<names.length;counter++)
			   list.add(names[counter]);
		   
		   months = new JComboBox(list.toArray(new String[list.size()]));
		   
		   months.addActionListener(this);
		   
		   panel.add(months);
		   
		   panel.add(new JLabel("Target Day"));
		   
		   java.util.List<Integer> list1 = new ArrayList<>();
		   
		   String currentMonth = names[month];
		   
		   for (int counter=1;counter<=daysInMonths.get(currentMonth);counter++)
			   list1.add(counter);
		   
		   targetDay = new JComboBox(list1.toArray(new Integer[list1.size()]));
		   
		   panel.add(targetDay);
		   
		   panel.add(new JLabel("Target Hour"));
		   
		   targetHour = new JComboBox();
		   
		   for (int counter=1;counter<12;counter++)
			   targetHour.addItem(counter);
		   
		   panel.add(targetHour);
		   
		   panel.add(new JLabel("Target Minute"));
		   
		   targetMinute = new JComboBox();
		   
		   for (int counter=1;counter<60;counter++)
			   targetMinute.addItem(counter);
		   
		   panel.add(targetMinute);
		   
		   panel.add(new JLabel("Target Second"));
		   
		   targetSecond = new JComboBox();
		   
		   for (int counter=1;counter<60;counter++)
			   targetSecond.addItem(counter);
		   
		   panel.add(targetSecond);
		   
		   panel.add(new JLabel("Target Am or Pm"));
		   
		   targetAmOrPm = new JComboBox();
		   
		   targetAmOrPm.addItem("am");
		   targetAmOrPm.addItem("pm");
		   
		   panel.add(targetAmOrPm);
		   
		   add(panel);
		   
		   add(startCountdown = new JButton("Start Countdown"));
		   
		   startCountdown.addActionListener(this);
	   }
	   
	   public int getTarget() {
		   String month = this.months.getSelectedItem().toString();
		   int day = Integer.parseInt(targetDay.getSelectedItem().toString());
		   int hour = Integer.parseInt(targetHour.getSelectedItem().toString());
		   int minute = Integer.parseInt(targetMinute.getSelectedItem().toString());
		   int second = Integer.parseInt(targetSecond.getSelectedItem().toString());
		   
		   String amOrPm = targetAmOrPm.getSelectedItem().toString();
		   
		   if (amOrPm.equalsIgnoreCase("pm"))
			      hour += 12;

		   int[] months = {31,28,31,30,31,30,31,31,30,31,30,31};

		   int total = 0;
		   
		   int index = 0;
		   
		   for (int counter=0;counter<names.length;counter++)
			   if (names[counter].equalsIgnoreCase(month)) {
				   index = counter;
				   
				   break;
			   }

		   for (int i=0;i<index;i++)
		      total += months[i]*24*60*60;

		   total += day*24*60*60 + hour*60*60 + minute*60 + second;
		   
		   return(total);
	   }
	   
	   public String getTime() {
		   String month = months.getSelectedItem().toString();
		   String day = targetDay.getSelectedItem().toString();
		   String hour = targetHour.getSelectedItem().toString();
		   String minute = targetMinute.getSelectedItem().toString();
		   String second = targetSecond.getSelectedItem().toString();
		   String amOrPm = targetAmOrPm.getSelectedItem().toString();
		   
		   String output = month + " " + day + " at " + hour + ":";
		   
		   String s = String.valueOf(minute);
		   
		   if (s.trim().length() == 1)
			   s = "0" + minute;
		   
		   s += ":";
		   
		   output += s;
		   
		   s = String.valueOf(second);
		   
		   if (s.trim().length() == 1)
			   s = "0" + second;
		   
		   output += s;
		   
		   output += " " + amOrPm;
		   
		   return(output);
	   }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == months) {
			targetDay.removeAllItems();
			
			for (int counter=1;counter<=daysInMonths.get(months.getSelectedItem());counter++)	{
				System.out.println(counter);
				
				targetDay.addItem(counter);
			}
		} else if (e.getSource() == startCountdown) {
			target = getTarget();
			
			started = true;
			
			audio.loop();
			
			thread = new Thread(Countdown.this);

			keepGoing = true;

			thread.start();
			
			startCountdown.setVisible(false);
		}
		
	}
	   
	   
   }

   public void init() {
	  setLayout(new GridLayout(2,1));

      audio = getAudioClip(getCodeBase(),"soundscountdown/badmoonrising.au"); 
      
	  countdownPanel = new CountdownPanel(600,200);
	  
	  controlPanel = new ControlPanel(600,200);
	  
	  add(controlPanel);
	  add(countdownPanel);
      
      picture = new Image[14];

      picture[0]=getImage(getCodeBase(),"digitscountdown/zero.gif");
      picture[1]=getImage(getCodeBase(),"digitscountdown/one.gif");
      picture[2]=getImage(getCodeBase(),"digitscountdown/two.gif");
      picture[3]=getImage(getCodeBase(),"digitscountdown/three.gif");
      picture[4]=getImage(getCodeBase(),"digitscountdown/four.gif");
      picture[5]=getImage(getCodeBase(),"digitscountdown/five.gif");
      picture[6]=getImage(getCodeBase(),"digitscountdown/six.gif");
      picture[7]=getImage(getCodeBase(),"digitscountdown/seven.gif");
      picture[8]=getImage(getCodeBase(),"digitscountdown/eight.gif");
      picture[9]=getImage(getCodeBase(),"digitscountdown/nine.gif");
      picture[10]=getImage(getCodeBase(),"digitscountdown/colon.gif");
      picture[11]=getImage(getCodeBase(),"digitscountdown/am.gif");
      picture[12]=getImage(getCodeBase(),"digitscountdown/pm.gif");
      picture[13]=getImage(getCodeBase(),"digitscountdown/zero.gif");

      tracker = new MediaTracker(this);
  
      for (int i=0;i<14;i++)
         tracker.addImage(picture[i],1);

      try {
         tracker.waitForAll();
      } catch (InterruptedException ie) {
      }

      currentDay = 0;
      numberOfDaysLeft = 1;
   }

   private int get_current() {
      Calendar c = Calendar.getInstance();

      int month = c.get(Calendar.MONTH);
      int day = c.get(Calendar.DAY_OF_MONTH);
      int hour = c.get(Calendar.HOUR);
      int minute = c.get(Calendar.MINUTE);
      int second = c.get(Calendar.SECOND);

      int[] months = {31,28,31,30,31,30,31,31,30,31,30,31};

      int total = 0;

      for (int i=0;i<month;i++)
         total += months[i]*24*60*60;

      total += day*24*60*60 + hour*60*60 + minute*60 + second;

      return(total);
   }

   public void run() {
      while (keepGoing) {
         try {
            Thread.sleep(1000);
         } catch (InterruptedException ie) {
         }
         
         System.out.println("current day is " + currentDay);;
         System.out.println("number of days ledft is " + numberOfDaysLeft);

         int current = get_current() + adjustment;

         int difference = target-current;

         numberOfDaysLeft = MyCalendar.number_of_days(difference);
         
         countdownPanel.repaint();
         
//         if (currentDay != numberOfDaysLeft) {       
//            countdownPanel.repaint();
            
//            currentDay = numberOfDaysLeft;
//         }
      }
   }
   
   /*

   public void start() {
      if (thread == null) {
         thread = new Thread(this);
           
         keepGoing = true;

         thread.start();
      }

      audio.loop();
   }
   
   */
   
   public void stop() {
      keepGoing = false;

      thread = null;

      audio.stop();
   }

   public void destroy() {
      keepGoing = false;

      if (thread != null)
         thread = null;
   }
}
