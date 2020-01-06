package music;

import static music.CreatingMusic.player;
import static music.CreatingMusic.track;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import static music.Util.*;

public class CreatingMusic extends JFrame implements ActionListener {
	public static int trebleTime;
	public static int bassTime;
	public static int thirdVoiceTime;
	public static int fourthVoiceTime;
	public static final boolean T = true;
	public static final boolean B = false;
	public static final boolean A = true; // Advance
	public static final boolean D = false; // Don't advance
	public static Track track;
	public static String key = "C";
	public static JComboBox<String> songs;
	public static JButton play;
	public static int factor = 1;
	public static Sequencer player;
	public static MyControllerEventListener myListener;
	public static MeasureMonitor measureMonitor;
	public static JComboBox temp;
	public static Map<String,Class> classes;
	public static MyAdjustmentListener myAdjustmentListener;
	public static JButton checkMeasures;
	public static int checkTempo;
	public static int tupletFactor = 1;
	
	public CreatingMusic(String title) {
		super(title);
		
		setLayout(new FlowLayout());
		
		myAdjustmentListener = new MyAdjustmentListener();
		
	    add(songs = new JComboBox<String>());
	    
	    classes = new HashMap<>();
	    
	    songs.setFont(new Font("Comic Sans MS",Font.BOLD,20));
	    
	    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	    
	    try {
			Enumeration<URL> resources = classLoader.getResources("samples");
			
			while (resources.hasMoreElements()) {
				URL resource = resources.nextElement();
								
				String[] fileNames = new File(resource.getFile()).list();
				
				for (int counter=0;counter<fileNames.length;counter++) {
	               String[] tokens = fileNames[counter].split("\\.");
	               
	               if (tokens.length > 1 
	            	   && tokens[1].contentEquals("class")
	            	   && !tokens[0].equals("Song")) {
	            	   songs.addItem(tokens[0].replaceAll("_"," "));				
	            	  
	            	   try {
						classes.put(tokens[0].replaceAll("_", " "), Class.forName("samples." + tokens[0].replaceAll(" ", "_")));
		  			   } catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					   }
	               }
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	   
	    add(play = new JButton("Play"));
	    
	    play.setFont(new Font("Comic Sans MS",Font.BOLD,20));
	    
	    play.addActionListener(this);
	    
	    add(checkMeasures = new JButton("Check Measures"));
	    
	    checkMeasures.setFont(new Font("Comic Sans MS",Font.BOLD,20));
	    
	    checkMeasures.addActionListener(this);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		CreatingMusic creatingMusic = new CreatingMusic("Creating Music");
		
		creatingMusic.setSize(500,500);
		creatingMusic.setLocationRelativeTo(null);
		creatingMusic.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        creatingMusic.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == checkMeasures)
			checkTempo = 20;
		if (player != null && player.isOpen())
		   JOptionPane.showMessageDialog(null, "You need to exit and restart to play another");
		else {		
			Method play = null;

			try {
				play = classes.get(songs.getSelectedItem()).getMethod("play");
			} catch (Exception e1) {
				e1.printStackTrace();
			} 

			try {
			   play.invoke(classes.get(songs.getSelectedItem()).newInstance());
			} catch (Exception e2) {
			   e2.printStackTrace();
			}
		}
	}
}
