package music;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.ShortMessage;
import javax.swing.JOptionPane; 

import static music.CreatingMusic.*;
import static music.Util.getNote;
import static music.Util.lengthOfDuration;

public class Util {

	public static int getNote(String noteName,
			                  int octave) {
		Map<String,Integer> map = new TreeMap<>();
		
		map.put("C", 60);
		map.put("C#", 61);
		map.put("Db",61);
		map.put("D", 62);
		map.put("D#", 63);
		map.put("Eb",63);
		map.put("E", 64);
		map.put("E#", 65);
		map.put("F", 65);
		map.put("F#", 66);
		map.put("Gb",66);
		map.put("G", 67);
		map.put("G#", 68);
		map.put("Ab",68);
		map.put("A", 69);
		map.put("A#", 70);
		map.put("Bb",70);
		map.put("B", 71);
		map.put("B#",60);
		map.put("Cb",70);
		
		map.put("Cn", 60);
		map.put("Dn", 62);
		map.put("En", 64);
		map.put("Fn", 65);
		map.put("Gn", 67);
		map.put("An", 69);
		map.put("Bn", 71);
		
	//	System.out.println("key is " + key);
		
		switch (key.toUpperCase()) {
		
		case "C#" : map.put("B",72);
		case "F#" : map.put("E",65);
		case "B"  : map.put("A",70);
		case "E"  : map.put("D",63);
		case "A"  : map.put("G",68);
		case "D"  : map.put("C",61);
		case "G"  : map.put("F",66);
		
		            break;
		            
		case "CB" : map.put("F",64);
		case "GB" : map.put("C",59);
		case "DB" : map.put("G",66);
		case "AB" : map.put("D",61);
		case "EB" : map.put("A",68);
		case "BB" : map.put("E",63);
		case "F"  : map.put("B",70);
		
		            break;
		}
		
//	System.out.println(map);
		
		return(map.get(noteName) + (octave - 5)*12);
	}
	
	public static int lengthOfDuration(String duration) {
		Map<String,Integer> map = new TreeMap<>();
		
		/*
		 
		map.put("s", 1);
		map.put("i", 2);
		map.put("q", 4);
		map.put("h", 8);
		map.put("w", 16);
		
		*/
		
		map.put("t", 1*factor);
		map.put("s", 2*factor);
		map.put("i", 4*factor);
		map.put("q", 8*factor);
		map.put("h", 16*factor);
		map.put("w", 32*factor);
		
		int output = 0;
		
		if (!duration.endsWith("."))		
		   output = map.get(duration);
		else {
			int temp = map.get(duration.substring(0,1));
			
			output = temp + temp/2;
		}
		
		switch (tupletFactor) {
		case 2 : output = output*2/3;
		         break;
		case 3 : output = output*3/4;
		         break;
		case 7 : output = output*7/8;
		}
		
		return(output);
	}
	
	public static void addNote(String note,
                               boolean advance,
                               boolean treble) {
       String[] tokens = note.split("\\d");

       String noteName = tokens[0];
       String duration = tokens[1];
       int octave = Integer.parseInt(note.substring(noteName.length(),noteName.length()+1));
       
       int temp = (treble) ? trebleTime : bassTime;
       
       ShortMessage message = new ShortMessage();
       
       try {
		message.setMessage(144,1,getNote(noteName,octave),120);
       } catch (InvalidMidiDataException e) {
    	   // TODO Auto-generated catch block
    	   e.printStackTrace();
       }
       
       track.add(new MidiEvent(message,temp));
       
       temp += lengthOfDuration(duration);
       
       message = new ShortMessage();
       
       try {
		message.setMessage(128,1,getNote(noteName,octave),120);
       } catch (InvalidMidiDataException e) {
    	   // TODO Auto-generated catch block
    	   e.printStackTrace();
       }
       
       track.add(new MidiEvent(message,temp));
       
       if (advance) {
    	   trebleTime = (treble) ? temp : trebleTime;
    	   bassTime = (!treble) ? temp : bassTime;
       }
	}

	public static void addNote(String note,
                               boolean advance,
                               boolean treble,
                               boolean third) {
       String[] tokens = note.split("\\d");

       String noteName = tokens[0];
       String duration = tokens[1];
       int octave = Integer.parseInt(note.substring(noteName.length(),noteName.length()+1));
       
       int temp = (third) ? thirdVoiceTime : fourthVoiceTime;
       
       ShortMessage message = new ShortMessage();
       
       try {
		message.setMessage(144,1,getNote(noteName,octave),120);
       } catch (InvalidMidiDataException e) {
    	   // TODO Auto-generated catch block
    	   e.printStackTrace();
       }
       
       track.add(new MidiEvent(message,temp));
       
       temp += lengthOfDuration(duration);
       
       message = new ShortMessage();
       
       try {
		message.setMessage(128,1,getNote(noteName,octave),120);
       } catch (InvalidMidiDataException e) {
    	   // TODO Auto-generated catch block
    	   e.printStackTrace();
       }
       
       track.add(new MidiEvent(message,temp));
       
       if (advance) {
    	   thirdVoiceTime = (third) ? temp : thirdVoiceTime;
    	   fourthVoiceTime = (!third) ? temp : fourthVoiceTime;
       }
	}
	

	public static void addNote(String noteName,
			                   int duration,
			                   int octave,
			                   boolean advance,
			                   boolean treble) {
		int temp = (treble) ? trebleTime : bassTime;
	       
	    ShortMessage message = new ShortMessage();
	       
	    try {
			message.setMessage(144,1,getNote(noteName,octave),120);
		} catch (InvalidMidiDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       
	    track.add(new MidiEvent(message,temp));
	    
	    temp += duration;
	       
	    message = new ShortMessage();
	       
	    try {
			message.setMessage(128,1,getNote(noteName,octave),120);
		} catch (InvalidMidiDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       
	    track.add(new MidiEvent(message,temp));
	    
	    if (advance) {
	        trebleTime = (treble) ? temp : trebleTime;
	    	bassTime = (!treble) ? temp : bassTime;
	    } 
	}

	public static void addNote(String noteName,
			                   int duration,
			                   int octave,
			                   boolean advance,
			                   boolean treble,
			                   boolean third) {
		int temp = (third) ? thirdVoiceTime : fourthVoiceTime;
	       
	    ShortMessage message = new ShortMessage();
	       
	    try {
			message.setMessage(144,1,getNote(noteName,octave),120);
		} catch (InvalidMidiDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       
	    track.add(new MidiEvent(message,temp));
	    
	    temp += duration;
	       
	    message = new ShortMessage();
	       
	    try {
			message.setMessage(128,1,getNote(noteName,octave),120);
		} catch (InvalidMidiDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       
	    track.add(new MidiEvent(message,temp));
	    
	    if (advance) {
	        thirdVoiceTime = (third) ? temp : thirdVoiceTime;
	    	fourthVoiceTime = (!third) ? temp : fourthVoiceTime;
	    } 
	}
	
	public static void addNotes(String notes,
			                    boolean treble) {
		String[] tokens = notes.split(" ");
		
		for (int counter=0;counter<tokens.length;counter++) {
			if (!tokens[counter].contains("+"))
				addNote(tokens[counter],true,treble);
			else {
				String[] tokens1 = tokens[counter].split("\\+");
				
				for (int counter1=0;counter1<tokens1.length-1;counter1++)
					addNote(tokens1[counter1],false,treble);
				
				addNote(tokens1[tokens1.length-1],true,treble);
			}			
		}
	}

	public static void addNotes(String notes,
			                    boolean treble,
			                    boolean third) {
		String[] tokens = notes.split(" ");
		
		for (int counter=0;counter<tokens.length;counter++) {
			if (!tokens[counter].contains("+"))
				addNote(tokens[counter],true,treble,third);
			else {
				String[] tokens1 = tokens[counter].split("\\+");
				
				for (int counter1=0;counter1<tokens1.length-1;counter1++)
					addNote(tokens1[counter1],false,treble,third);
				
				addNote(tokens1[tokens1.length-1],true,treble,third);
			}			
		}
	}
	
	public static void addNotesInTimeOf(String notes,
			                            int numberOfNotes,
			                            int timeOf,
			                            boolean treble) {
		String[] tokens = notes.split(" ");
		
		int duration = 0;
		
		for (int counter=0;counter<tokens.length;counter++) {
           String[] tokens1 = tokens[counter].split("\\d");			
           
           duration = Integer.parseInt(tokens1[tokens1.length-1]); 			
		}
		
		int fullDuration = timeOf*duration;
		
		duration = fullDuration/numberOfNotes;
		
	}
	
	public static void tie(boolean advance,
			               boolean treble,
			               String... notes) {
		String[] noteNames = new String[notes.length];
		String[] durations = new String[notes.length];
		int[] octaves = new int[notes.length];
		
		int duration = 0;
		
		for (int counter=0;counter<notes.length;counter++) {
		   String[] tokens = notes[counter].split("\\d");

	       noteNames[counter] = tokens[0];
	       durations[counter] = tokens[1];
	       
	       duration += lengthOfDuration(durations[counter]);
	       
	       octaves[counter] = Integer.parseInt(notes[counter].substring(noteNames[counter].length(),noteNames[counter].length()+1));
		}
		
	    String error = "";
	    
	    boolean noteNamesNotTheSame = false;
	    
	    for (int counter=1;counter<notes.length;counter++)
	    	noteNamesNotTheSame &= (noteNames[0].equals(noteNames[counter]));
	    
        boolean octavesNotTheSame = false;
	    
	    for (int counter=1;counter<notes.length;counter++)
	    	octavesNotTheSame &= (octaves[0] == octaves[counter]);
	    
	    if (noteNamesNotTheSame)
	    	error += "The note names are not the same\n";
	    
	    if (octavesNotTheSame)
	    	error += "The octaves are not the same";
	    
	    if (!error.equals(""))
	    	JOptionPane.showMessageDialog(null, error);
	    else {
	    	addNote(noteNames[0],
	    			duration,
	    			octaves[0],
	    			advance,
	    			treble);	    	
	    }

	}

	public static void tie(boolean advance,
			               boolean treble,
			               boolean third,
			               String... notes) {
		String[] noteNames = new String[notes.length];
		String[] durations = new String[notes.length];
		int[] octaves = new int[notes.length];
		
		int duration = 0;
		
		for (int counter=0;counter<notes.length;counter++) {
		   String[] tokens = notes[counter].split("\\d");

	       noteNames[counter] = tokens[0];
	       durations[counter] = tokens[1];
	       
	       duration += lengthOfDuration(durations[counter]);
	       
	       octaves[counter] = Integer.parseInt(notes[counter].substring(noteNames[counter].length(),noteNames[counter].length()+1));
		}
		
	    String error = "";
	    
	    boolean noteNamesNotTheSame = false;
	    
	    for (int counter=1;counter<notes.length;counter++)
	    	noteNamesNotTheSame &= (noteNames[0].equals(noteNames[counter]));
	    
        boolean octavesNotTheSame = false;
	    
	    for (int counter=1;counter<notes.length;counter++)
	    	octavesNotTheSame &= (octaves[0] == octaves[counter]);
	    
	    if (noteNamesNotTheSame)
	    	error += "The note names are not the same\n";
	    
	    if (octavesNotTheSame)
	    	error += "The octaves are not the same";
	    
	    if (!error.equals(""))
	    	JOptionPane.showMessageDialog(null, error);
	    else {
	    	addNote(noteNames[0],
	    			duration,
	    			octaves[0],
	    			advance,
	    			treble,
	    			third);	    	
	    }
	}

	public static void addRest(String duration,
                               boolean treble) {
       if (treble)
    	   trebleTime += lengthOfDuration(duration);
       else
    	   bassTime += lengthOfDuration(duration);
	}
	
	public static void addRest(String duration,
			                   int numberOfTimes,
                               boolean treble) {
		for (int counter=0;counter<numberOfTimes;counter++)
            if (treble)
                trebleTime += lengthOfDuration(duration);
            else
                bassTime += lengthOfDuration(duration);
    }

	public static void addRest(String duration,
                               boolean treble,
                               boolean third) {
       if (third)
    	   thirdVoiceTime += lengthOfDuration(duration);
       else
    	   fourthVoiceTime += lengthOfDuration(duration);
	}
	
	public static void addRest(String duration,
			                   int numberOfTimes,
                               boolean treble,
                               boolean third) {
		for (int counter=0;counter<numberOfTimes;counter++)
            if (third)
    	        thirdVoiceTime += lengthOfDuration(duration);
            else
    	        fourthVoiceTime += lengthOfDuration(duration);
	}
	
	public static void measure(int measure) {
		String line = measure + ";" + trebleTime + ";" + bassTime;
		
		try {
			track.add(new MidiEvent(new MetaMessage(1,line.getBytes(),line.getBytes().length),trebleTime));
		} catch (InvalidMidiDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void setTempo(int tempo) {
		if (checkTempo == 0)
			player.setTempoInBPM(tempo);
		else
			player.setTempoInBPM(100000*factor);
	}
	
	/*
	
	public static void measure(int measure,
			                   int trebleTime) {
		System.out.println(trebleTime);
		
		try {
			track.add(new MidiEvent(new MetaMessage(1,new byte[] {(byte)measure},1),trebleTime));
		} catch (InvalidMidiDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	*/
	
}
