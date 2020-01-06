package music;

import static music.CreatingMusic.*;

import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Track;
import javax.sound.midi.Transmitter;

import static music.Util.*;

public class Songs {
	
	public static void playJingle_Bells(CreatingMusic creatingMusic) {
		try {			
            player = MidiSystem.getSequencer();
			
 		    player.open();
			
			player.setTempoInBPM(320);
			
			System.out.println("I'm here");
			
			Sequence sequence = new Sequence(Sequence.PPQ,4);
			
			track = sequence.createTrack();
			
// Measure 1
			
			addNotes("G4q E5q D5q C5q",T);
			addNotes("C3h G3h",B);

// Measure 2
			
			addNotes("G4h. G4i G4i",T);
			addNotes("C3h G3h",B);

// Measure 3
			
			addNotes("G4q E5q D5q C5q",T);
			addNotes("C3h G3h",B);
			
// Measure 4

			addNotes("A4h.",T);
			addRest("q",T);
            addNotes("F3h C4h",B);
            
// Measure 5

			addNotes("A4q F5q E5q D5q",T);
			addNotes("F3h C4h",B);
			
// Measure 6

			addNote("B4h.",A,T);
			addRest("q",T);
			addNotes("G3h D4h",B);
			
// Measure 7

			addNotes("G5q G5q F5q D5q",T);
			addNotes("G3h B3h",B);
			
// Measure 8
			
			addNote("E5h.",A,T);
			addRest("q",T);
			addNotes("C4h E4h",B);
			
// Measure 9

			addNotes("G4q E5q D5q C5q",T);
			addNotes("C3h G3h",B);
			
// Measure 10

			addNote("G4h.",A,T);
			addRest("q",T);
			addNotes("C3h G3h",B);
			
// Measure 11
			
			addNotes("G4q E5q D5q C5q",T);
			addNotes("C3h G3h",B);
			
// Measure 12

			addNotes("A4h. A4q",T);
			addNotes("F3h C4h",B);
			
// Measure 13

			addNotes("A4q F5q E5q D5q",T);
			addNotes("F3h C4h",B);
			
// Measure 14

			addNotes("G5q G5q G5q G5q",T);
			addNotes("F3h C4h",B);
			
// Measure 15

			addNotes("A5q G5q F5q D5q",T);
			addNotes("G3h B3h",B);
			
// Measure 16

			addNotes("C5h B4h+G5h",T);
			addNotes("C4h G3h+D4h",B);
			
// Measure 17

			addNotes("C5q+E5q C5q+E5q C5h+E5h",T);
			addNotes("C4h B3h",B);
			
// Measure 18

			addNotes("C5q+E5q C5q+E5q C5h+E5h",T);
			addNotes("A3h G4h",B);
			
// Measure 19

			addNotes("E5q G5q C5q. D5i",T);
			addNotes("C4h G3h",B);
			
// Measure 20

			addNotes("C5h.+E5h.",T);
			addRest("q",T);
			addNotes("C4h G3h",B);
			
// Measure 21
			
			addNotes("F5q F5q F5q. F5i",T);
			addNotes("F3h A4h",B);
			
// Measure 22
			
			addNotes("F5q E5q E5q E5i E5i",T);
			addNotes("C3h G3h",B);
			
// Measure 23

			addNotes("E5q D5q D5q E5q",T);
			addNotes("D3h F#3h",B);
			
// Measure 24

			addNotes("D5h B4h+G5h",T);
			addNotes("G3h G3h",B);
			
// Measure 25

			addNotes("C5q+E5q C5q+E5q C5h+E5h",T);
			addNotes("C4h B3h",B);
			
// Measure 26

			addNotes("C5q+E5q C5q+E5q C5h+E5h",T);
			addNotes("A3h G3h",B);
			
// Measure 27

			addNotes("E5q G5q C5q. D5i",T);
			addNotes("C4h G3h",B);
			
// Measure 28

			addNotes("C5h.+E5h.",T);
			addRest("q",T);
			addNotes("C4h G3h",B);
			
// Measure 29
			
			addNotes("F5q F5q F5q. F5i",T);
			addNotes("F3h A3h",B);
			
// Measure 30

			addNotes("F5q E5q E5q E5i E5i",T);
			addNotes("C3h G3h",B);
			
// Measure 31

			addNotes("G5q G5q F5q D5q",T);
			addNotes("G3h B3h",B);
			
// Measure 32

			addNote("C5w",A,T);
			addNotes("C4w+E4w",B);
			
			player.setSequence(sequence);
			
			player.start();
			
			System.out.println(player.isRunning());
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		}
		
	public static void playThe_Entertainer(CreatingMusic creatingMusic) {
		try {
	        player = MidiSystem.getSequencer();
				
			player.open();
				
			player.setTempoInBPM(180);
				
			Sequence sequence = new Sequence(Sequence.PPQ,4);
				
			track = sequence.createTrack();
				
// Measure 1
			
			addNotes("D6s+D7s E6s+E7s C6s+C7s",T);
			tie(D,T,"A5s","A5s");
			tie(A,T,"A6s","A6s");
			addNotes("B5s+B6s G5i+G6i",T);
			
			for (int counter=0;counter<6;counter++)
				addRest("s",B);
			
			addRest("i",B);
			
// Measure 2
			
			addNotes("D6s E6s C6s",T);
			tie(A,T,"A5s","A5s");
	    	addNotes("B5s G5i",T);
			
			addNotes("D5s E5s C5s",B);
			tie(A,B,"A4s","A4s");
			addNotes("B4s G4i",B);
			
// Measure 3
			
			addNotes("D5s E5s C5s",T);
			tie(A,T,"A4s","A4s");
			addNotes("B4s A4s Ab4s",T);
			
			addNotes("D4s E4s C4s",B);
			tie(A,B,"A3s","A3s");
			addNotes("B4s A3s Ab4s",B);
			
// Measure 4
			
			addRest("i",T);
			addRest("i",T);
			addNotes("G5i+B5i+D6i+G6i D5s D#5s",T);
			addNotes("G3i+G4i",B);
			addRest("i",B);
			addNotes("G3i+G2i G4i+B4i",B);
			
// Measure 5
			
			addNotes("E5s C6i E5s C6i E5s",T);
			addNotes("C4i E4i+G4i+C5i G3i+G4i G4i+Bb4i+C5i",B);
			
// Measure 6
			
			tie(A,T,"C6s","C6q","C6s");
			addNotes("C6s+E6s+C7s",T);
			addNotes("D6s+F6s+D7s",T);
			addNotes("D#6s+F#6s+D#7s",T);
			addNotes("F3i+F4i A4i+C5i E3i+E4i G4i+C5i",B);
			
// Measure 7
			
			addNotes("E6s+G6s+E7s C6s+E6s+C7s D6s+F6s+D7s",T);
			tie(D,T,"E6s","E6s");
			tie(D,T,"G6s","G6s");
			tie(A,T,"E7s","E7s");
			addNotes("B5s+D6s+B6s",T);
			addNotes("D6i+F6i+D7i",T);
			addNotes("G3i E4i+G4i+C5i G3i F4i+G4i+B4i",B);
			
// Measure 8
			
			tie(D,T,"C6q","C6i");
			tie(D,T,"E6q","E6i");
			tie(A,T,"C7q","C7i");
			addNotes("D5s D#5s",T);
			addNotes("C4i E4i+G4i+C5i E4i+G4i+C5i G4i+B4i",B);
			
// Measure 9
			
			addNotes("E5s C6i E5s C6i E5s",T);
			addNotes("C4i E4i+G4i+C5i G3i+G4i G4i+Bb4i+C5i",B);

// Measure 10
			
			tie(A,T,"C6s","C6q","C6i");
			addNotes("A5s+C6s+B6s G5s+C6s+B6s",T);
			addNotes("F3i+F4i A4i+C5i E3i+E4i Eb3i+Eb4i",B);
			
// Measure 11
			
			addNotes("F#5s+C6s+F#6s A5s+A6s C6s+C7s",T);
			tie(D,T,"E6s","E6s");
			tie(A,T,"E7s","E7s");
			addNotes("D6s+D7s C6s+C7s A5s+A6s",T);
			addNotes("D3i+D4i D4i+F#4i+A4i+C5i D4i F4i+A4i+C5i",B);
			
// Measure 12
			
			tie(D,T,"D6q","D6i");
			tie(D,T,"F6q","F6i");
			tie(A,T,"D7q","D7i");
			addNotes("D5s D#5s",T);
			addNotes("G4i+B4i G3i+G4i A3i+A4i B3i+B4i",B);
			
// Measure 13
			
			addNotes("E5s C6i E5s C6i E5s",T);
			addNotes("C4i E4i+G4i+C5i G3i+G4i G4i+Bb4i+C5i",B);
						
// Measure 14
						
			tie(A,T,"C6s","C6q","C6s");
		    addNotes("C6s+E6s+C7s",T);
			addNotes("D6s+F6s+D7s",T);
			addNotes("D#6s+F#6s+D#7s",T);
			addNotes("F3i+F4i A4i+C5i E3i+E4i G4i+C5i",B);
						
// Measure 15
						
			addNotes("E6s+G6s+E7s C6s+E6s+C7s D6s+F6s+D7s",T);
			tie(D,T,"E6s","E6s");
			tie(D,T,"G6s","G6s");
			tie(A,T,"E7s","E7s");
			addNotes("B5s+D6s+B6s",T);
			addNotes("D6i+F6i+D7i",T);
			addNotes("G3i E4i+G4i+C5i G3i F4i+G4i+B4i",B);
									
// Measure 16
			
			tie(D,T,"C6q","C6i");
			tie(D,T,"E6q","E6i");
			tie(A,T,"C7q","C7i");
			addNotes("C6s+C7s D6s+D7s",T);
			addNotes("C4i E4i+G4i+C5i G4i+C5i+E5i",B);
			addRest("i",B);
			
// Measure 17
			
			addNotes("E6s+E7s C6s+C7s D6s+D7s",T);
			tie(D,T,"E6s","E6s");
			tie(A,T,"E7s","E7s");
			addNotes("C6s+C7s D6s+D7s C6s+C7s",T);
			addNotes("C4i+C5i G4i+C5i+E5i Bb3i+Bb4i G4i+C5i+E5i",B);
			
// Measure 18
			
			addNotes("E6s+E7s C6s+C7s D6s+D7s",T);
			tie(D,T,"E6s","E6s");
			tie(A,T,"E7s","E7s");
			addNotes("C6s+C7s D6s+D7s C6s+C7s",T);
			addNotes("A3i+A4i A4i+C5i+F5i Ab3i+Ab4i Ab4i+C5i+F5i",B);
			
// Measure 19
			
			addNotes("E6s+G6s+E7s C6s+E6s+C7s D6s+F6s+D7s",T);
			tie(D,T,"E6s","E6s");
			tie(D,T,"G6s","G6s");
			tie(A,T,"E7s","E7s");
			addNotes("B5s+D6s+B6s D6i+F6i+D7i",T);
			addNotes("G3i+G4i G4i+C5i+E5i G3i G4i+B4i",B);
			
// Measure 20
			
			tie(D,T,"C6q","C6i");
			tie(D,T,"E6q","E6i");
			tie(A,T,"C7q","C7i");
			addNotes("D5s D#5s",T);
			addNotes("C4i+G4i+C5i G3i+G4i A3i+A4i B3i+B4i",B);
			
			
// Measure 21
			
			addNotes("E5s C6i E5s C6i E5s",T);
			addNotes("C4i E4i+G4i+C5i G3i+G4i G4i+Bb4i+C5i",B);
						
// Measure 22
						
			tie(A,T,"C6s","C6q","C6s");
			addNotes("C6s+E6s+C7s",T);
			addNotes("D6s+F6s+D7s",T);
			addNotes("D#6s+F#6s+D#7s",T);
			addNotes("F3i+F4i A4i+C5i E3i+E4i G4i+C5i",B);
						
// Measure 23
						
			addNotes("E6s+G6s+E7s C6s+E6s+C7s D6s+F6s+D7s",T);
			tie(D,T,"E6s","E6s");
			tie(D,T,"G6s","G6s");
			tie(A,T,"E7s","E7s");
			addNotes("B5s+D6s+B6s",T);
			addNotes("D6i+F6i+D7i",T);
			addNotes("G3i E4i+G4i+C5i G3i F4i+G4i+B4i",B);
						
// Measure 24
						
			tie(D,T,"C6q","C6i");
			tie(D,T,"E6q","E6i");
			tie(A,T,"C7q","C7i");
			addNotes("D5s D#5s",T);
			addNotes("C4i E4i+G4i+C5i E4i+G4i+C5i G4i+B4i",B);
						
// Measure 25
						
			addNotes("E5s C6i E5s C6i E5s",T);
			addNotes("C4i E4i+G4i+C5i G3i+G4i G4i+Bb4i+C5i",B);

// Measure 26
						
			tie(A,T,"C6s","C6q","C6i");
			addNotes("A5s+C6s+B6s G5s+C6s+B6s",T);
			addNotes("F3i+F4i A4i+C5i E3i+E4i Eb3i+Eb4i",B);
						
// Measure 27
						
			addNotes("F#5s+C6s+F#6s A5s+A6s C6s+C7s",T);
			tie(D,T,"E6s","E6s");
			tie(A,T,"E7s","E7s");
			addNotes("D6s+D7s C6s+C7s A5s+A6s",T);
			addNotes("D3i+D4i D4i+F#4i+A4i+C5i D4i F4i+A4i+C5i",B);
						
// Measure 28
						
		    tie(D,T,"D6q","D6i");
			tie(D,T,"F6q","F6i");
			tie(A,T,"D7q","D7i");
			addNotes("D5s D#5s",T);
			addNotes("G4i+B4i G3i+G4i A3i+A4i B3i+B4i",B);
						
// Measure 29
						
			addNotes("E5s C6i E5s C6i E5s",T);
			addNotes("C4i E4i+G4i+C5i G3i+G4i G4i+Bb4i+C5i",B);
									
// Measure 30
									
			tie(A,T,"C6s","C6q","C6s");
			addNotes("C6s+E6s+C7s",T);
			addNotes("D6s+F6s+D7s",T);
			addNotes("D#6s+F#6s+D#7s",T);
			addNotes("F3i+F4i A4i+C5i E3i+E4i G4i+C5i",B);
									
// Measure 31
									
			addNotes("E6s+G6s+E7s C6s+E6s+C7s D6s+F6s+D7s",T);
			tie(D,T,"E6s","E6s");
			tie(D,T,"G6s","G6s");
			tie(A,T,"E7s","E7s");
			addNotes("B5s+D6s+B6s",T);
			addNotes("D6i+F6i+D7i",T);
			addNotes("G3i E4i+G4i+C5i G3i F4i+G4i+B4i",B);
												
// Measure 32
						
			tie(D,T,"C6q","C6i");
			tie(D,T,"E6q","E6i");
			tie(A,T,"C7q","C7i");
			addNotes("C6s+C7s D6s+D7s",T);
			addNotes("C4i E4i+G4i+C5i G4i+C5i+E5i",B);
			addRest("i",B);
						
// Measure 33
						
			addNotes("E6s+E7s C6s+C7s D6s+D7s",T);
			tie(D,T,"E6s","E6s");
			tie(A,T,"E7s","E7s");
			addNotes("C6s+C7s D6s+D7s C6s+C7s",T);
			addNotes("C4i+C5i G4i+C5i+E5i Bb3i+Bb4i G4i+C5i+E5i",B);
						
// Measure 34
						
			addNotes("E6s+E7s C6s+C7s D6s+D7s",T);
			tie(D,T,"E6s","E6s");
			tie(A,T,"E7s","E7s");
			addNotes("C6s+C7s D6s+D7s C6s+C7s",T);
			addNotes("A3i+A4i A4i+C5i+F5i Ab3i+Ab4i Ab4i+C5i+F5i",B);
						
// Measure 35
						
			addNotes("E6s+G6s+E7s C6s+E6s+C7s D6s+F6s+D7s",T);
		    tie(D,T,"E6s","E6s");
		    tie(D,T,"G6s","G6s");
			tie(A,T,"E7s","E7s");
		    addNotes("B5s+D6s+B6s D6i+F6i+D7i",T);
			addNotes("G3i+G4i G4i+C5i+E5i G3i G4i+B4i",B);
				
			

// Beginning of part 2
			
// Measure 36
			
			tie(D,T,"C6q","C6s");
			tie(D,T,"E6q","E6s");
			tie(A,T,"C7q","C7s");
			addNotes("E5s+C6s+E6s F5s+D6s+F6s F#5s+D#6s+F#6s",T);
			addNotes("C4i+G4i+C5i G3i+G4i C3i+C4i",B);
			addRest("i",B);
			
// Measure 37
			
			addNotes("G5i+E6i+G6i A5s+E6s+A6s",T);
			tie(D,T,"G5s","G5s");
			tie(D,T,"E6s","E6s");
			tie(A,T,"G6s","G6s");
			addNotes("E5s+C6s+E6s F5s+D6s+F6s F#5s+D#6s+F#6s",T);
			addNotes("C3i+C4i G4i+C5i+E5i G3i G4i+C5i+E5i",B);
			
// Measure 38
			
			addNotes("G5i+E6i+G6i A5s+E6s+A6s",T);
			tie(D,T,"G5s","G5s");
			tie(D,T,"E6s","E6s");
			tie(A,T,"G6s","G6s");
			addNotes("E6s C6s G5s",T);
			addNotes("C4i G4i+C5i+E5i G3i G4i+C5i+E5i",B);
			
// Measure 39
			
			addNotes("A5s B5s C6s D6s E6s D6s C6s D6s",T);
			addNotes("F3i A4i+C5i+F5i F4i Ab4i+C5i+F5i",B);
			
// Measure 40
			
			addNotes("G5s E6s F6s G6s A6s G6s E6s F6s",T);
			addNotes("E4i G4i+C5i+E5i G3i G4i+C5i+E5i",B);
			
// Measure 41
			
			addNotes("G5i+E6i+G6i A5s+E6s+A6s",T);
			tie(D,T,"G5s","G5s");
			tie(D,T,"E6s","E6s");
			tie(A,T,"G6s","G6s");
			addNotes("E5s+C6s+E6s F5s+D6s+F6s F#5s+D#6s+F#6s",T);
			addNotes("B3i G4i+C5i+E5i G3i G4i+C5i+E5i",B);
			
// Measure 42
			
			addNotes("G5i+E6i+G6i A5s+E6s+A6s",T);
			tie(D,T,"G5s","G5s");
			tie(D,T,"E6s","E6s");
			tie(A,T,"G6s","G6s");
			addNotes("G6s A6s A#6s",T);
			addNotes("B3i G4i+C5i+E5i E4i Eb4i",B);
			
// Measure 43
			
			addNotes("D6s+G6s+B6s D6i+G6i+B6i",T);
			tie(D,T,"C6s","C6s");
			tie(D,T,"F#6s","F#6s");
			tie(A,T,"B6s","B6s");
			addNotes("A6s C6s+F#6s D6s",T);
			addNotes("D4i G4i+B4i+D5i D4i A4i+C5i+D5i",B);
			
// Measure 44
			
			tie(D,T,"B5q","B5s");
			tie(A,T,"G6q","G6s");
			addNotes("E5s+C6s+E6s F5s+D6s+F6s F#5s+D#6s+F#6s",T);
			addNotes("G4i+B4i+D5i F3i+F4i E3i+E4i D3i+D4i",B);
			
// Measure 45
			
			addNotes("G5i+E6i+G6i A5s+E6s+A6s",T);
			tie(D,T,"G5s","G5s");
			tie(D,T,"E6s","E6s");
			tie(A,T,"G6s","G6s");
			addNotes("E5s+C6s+E6s F5s+D6s+F6s F#5s+D#6s+F#6s",T);
			addNotes("C3i+C4i G4i+C5i+E5i G3i G4i+C5i+E5i",B);

// Measure 46 
			
			addNotes("G5i+E6i+G6i A5s+E6s+A6s",T);
			tie(D,T,"G5s","G5s");
			tie(D,T,"E6s","E6s");
			tie(A,T,"G6s","G6s");
			addNotes("E6s C6s G5s",T);
			addNotes("C4i G4i+C5i+E5i G3i G4i+C5i+E5i",B);
			
// Measure 47
			
			addNotes("A5s B5s C6s D6s E6s D6s C6s D6s",T);
			addNotes("F3i A4i+C5i+F5i F4i Ab4i+C5i+F5i",B);
			
// Measure 48
			
			tie(A,T,"C6q","C6s");
			addNotes("G5s F#5s G5s",T);
			addNotes("E4i G4i+C5i+E5i B3i Bb4i+C5i+E5i",B);
			
// Measure 49
			
			addNotes("C6i A5s",T);
			tie(A,T,"C6s","C6s");
			addNotes("A5s C6s A5s",T);
			addNotes("F4i+A4i+C5i+F5i F4i+A4i+C5i+F5i F#4i+A4i+C#5i+D5i F#4i+A4i+C#5i+D5i",B);

// Measure 50
			
			addNotes("G5s C6s E6s",T);
			tie(A,T,"G6s","G6s");
			addNotes("E6s C6s G5s",T);
			addNotes("G4i+C5i+E5i G4i+C5i+E5i G4i+C5i+E5i G4i+C5i+E5i",B);
			
// Measure 51
			
			addNotes("F#5i+A5i F#5i+C6i F5s+E6s F5i+D6i",T);
			addNotes("D4i+C5i D4i+A4i G4i+B4i G4i+B4i",B);
			
// Measure 52
			
			tie(D,T,"E5s","E5q","E5s");
			tie(A,T,"C6s","C6q","C6s");
			addNotes("E5s+C6s+E6s F5s+D6s+F6s F#5s+D#6s+F#6s",T);
			addNotes("C4i+C5i G3i+G4i E3i+E4i D3i+D4i",B);

// Measure 53
			
			addNotes("G6i+E7i+G7i A6s+E7s+A7s",T);
			tie(D,T,"G6s","G6s");
			tie(D,T,"E7s","E7s");
			tie(A,T,"G7s","G7s");
			addNotes("E6s+C7s+E7s F6s+D7s+F7s F#6s+D#7s+F#7s",T);
			addNotes("C3i+C4i G4i+C5i+E5i G3i G4i+C5i+E5i",B);
						
// Measure 54
						
			addNotes("G6i+E7i+G7i A6s+E7s+A7s",T);
			tie(D,T,"G6s","G6s");
			tie(D,T,"E7s","E7s");
			tie(A,T,"G7s","G7s");
			addNotes("E7s C7s G6s",T);
			addNotes("C4i G4i+C5i+E5i G3i G4i+C5i+E5i",B);
						
// Measure 55
						
			addNotes("A6s B6s C7s D7s E7s D7s C7s D7s",T);
			addNotes("F3i A4i+C5i+F5i F4i Ab4i+C5i+F5i",B);
	
// Measure 56
						
			addNotes("G6s E7s F7s G7s A7s G7s E7s F7s",T);
			addNotes("E4i G4i+C5i+E5i G3i G4i+C5i+E5i",B);
						
// Measure 57
						
			addNotes("G6i+E7i+G7i A6s+E7s+A7s",T);
			tie(D,T,"G6s","G6s");
		    tie(D,T,"E7s","E7s");
			tie(A,T,"G7s","G7s");
			addNotes("E6s+C7s+E7s F6s+D7s+F7s F#6s+D#7s+F#7s",T);
			addNotes("B3i G4i+C5i+E5i G3i G4i+C5i+E5i",B);
		
// Measure 58
						
			addNotes("G6i+E7i+G7i A6s+E7s+A7s",T);
			tie(D,T,"G6s","G6s");
			tie(D,T,"E7s","E7s");
			tie(A,T,"G7s","G7s");
			addNotes("G7s A7s A#7s",T);
			addNotes("B3i G4i+C5i+E5i E4i Eb4i",B);
						
// Measure 59
						
			addNotes("D7s+G7s+B7s D7i+G7i+B7i",T);
			tie(D,T,"C7s","C7s");
			tie(D,T,"F#7s","F#7s");
			tie(A,T,"B7s","B7s");
			addNotes("A7s C7s+F#7s D7s",T);
			addNotes("D4i G4i+B4i+D5i D4i A4i+C5i+D5i",B);
	
// Measure 60
						
			tie(D,T,"B6q","B6s");
			tie(A,T,"G7q","G7s");
			addNotes("E6s+C7s+E7s F6s+D7s+F7s F#6s+D#7s+F#7s",T);
			addNotes("G4i+B4i+D5i F3i+F4i E3i+E4i D3i+D4i",B);
						
// Measure 61
						
			addNotes("G6i+E7i+G7i A6s+E7s+A7s",T);
			tie(D,T,"G6s","G6s");
			tie(D,T,"E7s","E7s");
			tie(A,T,"G7s","G7s");
			addNotes("E6s+C7s+E7s F6s+D7s+F7s F#6s+D#7s+F#7s",T);
			addNotes("C3i+C4i G4i+C5i+E5i G3i G4i+C5i+E5i",B);
						
// Measure 62 
						
			addNotes("G6i+E7i+G7i A6s+E7s+A7s",T);
			tie(D,T,"G6s","G6s");
			tie(D,T,"E7s","E7s");
			tie(A,T,"G7s","G7s");
			addNotes("E7s C7s G6s",T);
			addNotes("C4i G4i+C5i+E5i G3i G4i+C5i+E5i",B);
						
// Measure 63
						
			addNotes("A6s B6s C7s D7s E7s D7s C7s D7s",T);
			addNotes("F3i A4i+C5i+F5i F4i Ab4i+C5i+F5i",B);
		
// Measure 64
						
			tie(A,T,"C7q","C7s");
			addNotes("G6s F#6s G6s",T);
			addNotes("E4i G4i+C5i+E5i B3i Bb4i+C5i+E5i",B);
						
// Measure 65
						
			addNotes("C7i A6s",T);
			tie(A,T,"C7s","C7s");
			addNotes("A6s C7s A6s",T);
			addNotes("F4i+A4i+C5i+F5i F4i+A4i+C5i+F5i F#4i+A4i+C#5i+D5i F#4i+A4i+C#5i+D5i",B);
						
// Measure 66
						
			addNotes("G6s C7s E7s",T);
			tie(A,T,"G7s","G7s");
			addNotes("E7s C7s G6s",T);
			addNotes("G4i+C5i+E5i G4i+C5i+E5i G4i+C5i+E5i G4i+C5i+E5i",B);
		
// Measure 67
						
			addNotes("F#6i+A6i F#6i+C7i F6s+E7s F6i+D7i E6s+C7s",T);
			addNotes("D4i+C5i D4i+A4i G4i+B4i G4i+B4i",B);

// Meaasure 68
			
			tie(D,T,"E5q","E5i");
			tie(A,T,"C6q","C6i");
			addNotes("D5s D#5s",T);
			addNotes("C4i+C5i G3i+G4i C3i+C4i",B);
			addRest("i",B);

// Measure 69
			
			addNotes("E5s C6i E5s C6i E5s",T);
			addNotes("C4i E4i+G4i+C5i G3i+G4i G4i+Bb4i+C5i",B);
			
// Measure 70
			
			tie(A,T,"C6s","C6q","C6s");
			addNotes("C6s+E6s+C7s D6s+F6s+D7s D#6s+F#6s+D#7s",T);
			addNotes("F3i+F4i A4i+C5i E3i+E4i G4i+C5i",B);
			
// Measure 71
			
			addNotes("E6s+G6s+E7s C6s+E6s+C7s D6s+F6s+D7s",T);
			tie(D,T,"E6s","E6s");
			tie(D,T,"G6s","G6s");
			tie(A,T,"E7s","E7s");
			addNotes("B5s+D6s+B6s D6i+F6i+D7i",T);
			addNotes("G3i E4i+G4i+C5i G3i F4i+G4i+B4i",B);
			
// Measure 72
			
			tie(D,T,"C6q","C6i");
			tie(D,T,"E6q","E6i");
			tie(A,T,"C7q","C7i");
			addNotes("D5s D#5s",T);
			addNotes("C4i E4i+G4i+C5i E4i+G4i+C5i G4i+B4i",B);
			
// Measure 73
			
			addNotes("E5s C6i E5s C6i E5s",T);
			addNotes("C4i E4i+G4i+C5i G3i+G4i G4i+Bb4i+C5i",B);
			
// Measure 74
			
			tie(A,T,"C6s","C6q","C6i");
			addNotes("A5s+C6s+A6s G5s+C6s+G6s",T);
			addNotes("F3i+F4i A4i+C5i E3i+E4i Eb3i+Eb4i",B);
			
// Measure 75
			
			addNotes("F#5s+C6s+F#6s A5s+A6s C6s+C7s",T);
			tie(D,T,"E6s","E6s");
			tie(A,T,"E7s","E7s");
			addNotes("D6s+D7s C6s+C7s A5s+A6s",T);
			addNotes("D3i+D4i D4i+F#4i+A4i+C5i D4i F#4i+A4i+C5i",B);
			
// Measure 76
			
			tie(D,T,"D6q","D6i");
			tie(D,T,"F6q","F6i");
			tie(A,T,"D7q","D7i");
			addNotes("D5s D#5s",T);
			addNotes("G4i+B4i G3i+G4i A3i+A4i B3i+B4i",B);
			
			
			
// Measure 77
			
			addNotes("E5s C6i E5s C6i E5s",T);
			addNotes("C4i E4i+G4i+C5i G3i+G4i G4i+Bb4i+C5i",B);
			
// Measure 78
			
			tie(A,T,"C6s","C6q","C6s");
			addNotes("C6s+E6s+C7s D6s+F6s+D7s D#6s+F#6s+D#7s",T);
			addNotes("F3i+F4i A4i+C5i E3i+E4i G4i+C5i",B);
			
// Measure 79
			
			addNotes("E6s+G6s+E7s C6s+E6s+C7s D6s+F6s+D7s",T);
			tie(D,T,"E6s","E6s");
			tie(D,T,"G6s","G6s");
			tie(A,T,"E7s","E7s");
			addNotes("B5s+D6s+B6s D6i+F6i+D7i",T);
			addNotes("G3i E4i+G4i+C5i G3i F4i+G4i+B4i",B);

// Measure 80
			
			tie(D,T,"C6q","C6i");
			tie(D,T,"E6q","E6i");
			tie(A,T,"C7q","C7i");
			addNotes("C6s+C7s D6s+D7s",T);
			addNotes("C4i E4i+G4i+C5i G4i+C5i+E5i",B);
			addRest("i",B);

// Measure 81
			
			addNotes("E6s+E7s C6s+C7s D6s+D7s",T);
			tie(D,T,"E6s","E6s");
			tie(A,T,"E7s","E7s");
			addNotes("C6s+C7s D6s+D7s C6s+C7s",T);
			addNotes("C4i+C5i G4i+C5i+E5i Bb3i+Bb4i G4i+C5i+E5i",B);

// Measure 82
			
			addNotes("E6s+E7s C6s+C7s D6s+D7s",T);
			tie(D,T,"E6s","E6s");
			tie(A,T,"E7s","E7s");
			addNotes("C6s+C7s D6s+D7s C6s+C7s",T);
			addNotes("A3i+A4i A4i+C5i+F5i Ab3i+Ab4i Ab4i+C5i+F5i",B);

// Measure 83
			
			addNotes("E6s+G6s+E7s C6s+E6s+C7s D6s+F6s+D7s",T);
			tie(D,T,"E6s","E6s");
			tie(D,T,"G6s","G6s");
			tie(A,T,"E7s","E7s");
			addNotes("B5s+D6s+B6s D6i+F6i+D7i",T);
			addNotes("G3i+G4i G4i+C5i+E5i G3i G4i+B4i",B);
			
// Measure 84
			
			addNotes("C6q+E6q+C7q C6i+E6i+C7i",T);
			addRest("i",T);
			addNotes("C4i+G4i+C5i G3i+G4i C3i+C4i",B);
			addRest("i",B);
			
			
			key = "F";
			
// Measure 85
			
			addNotes("F6s+A6s G#6s",T);
			tie(D,T,"F6i","F6i");
			tie(A,T,"A6i","A6i");
			addNotes("F6i+A6i+C7i",T);
			addNotes("F3i A4i+C5i+F5i C4i A4i+C5i+F5i",B);
			
			thirdVoiceTime = trebleTime;

// Measure 86
			
			addRest("i",T);
			addNotes("B5s A5s B5s C6s D6i",T);
			addNotes("B3i B4i+D5i+F5i F4i B4i+D5i+F5i",B);
			addNotes("F6h+B6h+D7h",T,T);

// Measure 87
			
			addNotes("D6s+F6s E6s",T);
			tie(D,T,"D6i","D6i");
			tie(A,T,"F6i","F6i");
			addNotes("D6i+F6i+A6i",T);
			addNotes("D3i A4i+D5i+F5i A3i A4i+D5i+F5i",B);

// Measure 88
			
			thirdVoiceTime = trebleTime;
			
			addRest("i",T);
			addNotes("G5s F#5s G5s A5s B5i",T);
			addNotes("G3i B4i+D5i D4i B4i+D5i",B);
			tie(D,T,T,"D6q","D6i.");
			tie(D,T,T,"G6q","G6i.");
			tie(A,T,T,"B6q","B6i.");
			addNotes("G6s",T,T);

// Measure 89
			
			addNotes("D6i G6s",T);
			tie(A,T,"D6s","D6s");
			addNotes("G6s D6i",T);
			addNotes("B3i+B4i B4i+D5i G3i+G4i G#3i+G#4i",B);

// Measure 90
			
			addNotes("C6q F6q",T);
			addNotes("A3i+A4i A4i+C5i+F5i D4i A4i+D5i+F5i",B);

// Measure 91
			
			addNotes("E6s G#6s Bn6s",T);
			tie(A,T,"E7s","E7s");
			addNotes("D7s Bn6s C7s",T);
			addNotes("E4i Bn4i+D5i+F5i G#4i Bn4i+D5i+F5i",B);

// Measure 92
			
			addNotes("A6q Bb6q",T);
			addNotes("A4q+C5q+E5q Gn4i+C5i+E5i C4i",B);

// Measure 93
			
			addNotes("F6s+A6s G#6s",T);
			tie(D,T,"F6i","F6i");
			tie(A,T,"A6i","A6i");
			addNotes("F6i+A6i+C7i",T);
			addNotes("F3i A4i+C5i+F5i C4i A4i+C5i+F5i",B);

			thirdVoiceTime = trebleTime;
			
// Measure 94
			
			addRest("i",T);
			addNotes("B5s A5s B5s C6s D6i",T);
			addNotes("B3i B4i+D5i+F5i F4i A4i+C5i+F5i",B);
			addNotes("F6h+B6h+D7h",T,T);

// Measure 95
			
			addNotes("D6s+F6s E6s",T);
			tie(D,T,"D6i","D6i");
			tie(A,T,"F6i","F6i");
			addNotes("D6i+F6i+A6i",T);
			addNotes("D3i A4i+D5i+F5i A3i A4i+D5i+F5i",B);

// Measure 96
			
			thirdVoiceTime = trebleTime;
			
			addRest("i",T);
			addNotes("G5s F#5s G5s A5s B5i",T);
			addNotes("G3i B4i+D5i D4i B4i+D5i",B);
			tie(D,T,T,"D6q","D6i.");
			tie(D,T,T,"G6q","G6i.");
			tie(A,T,T,"B6q","B6i.");
			addNotes("G6s",T,T);

// Measure 97
			
			addNotes("D6i G6s",T);
			tie(A,T,"D6s","D6s");
			addNotes("G6s D6i",T);
			addNotes("B3i+B4i B4i+D5i G3i+G4i G#3i+G#4i",B);

// Measure 98
			
			addNotes("C6q G#5i.+Bn5i.+F6i. F6s",T);
			addNotes("A3s+A4s F3s+F4s E3s+E4s D3s+D4s Db3q+Db4q",B);

			thirdVoiceTime = trebleTime;
			
// Measure 99
			
			addNotes("A5s+C6s+A6s C6i+C7i",T);
			tie(A,T,"G6s","G6s");
			addNotes("C6s D6s E6s",T);
			addNotes("C3i+C4i A4i+C5i+F5i C4i+C5i C3i+C4i",B);
			addRest("s",T,T);
			addRest("i",T,T);
			tie(A,T,T,"B5s","B5i");
			addNotes("B5i",T,T);

// Measure 100
			
			addNotes("A5i+F6i Bn5s C6s D6s E6s F6s G6s",T);
			addNotes("F3i+F4i",B);
			addRest("i",B);
			addRest("q",B);

// Measure 101
			
			addNotes("F6s+A6s G#6s",T);
			tie(D,T,"F6i","F6i");
			tie(A,T,"A6i","A6i");
			addNotes("F6i+A6i+C7i",T);
			addNotes("F3i A4i+C5i+F5i C4i A4i+C5i+F5i",B);
						
			thirdVoiceTime = trebleTime;

// Measure 102
						
			addRest("i",T);
			addNotes("B5s A5s B5s C6s D6i",T);
			addNotes("B3i B4i+D5i+F5i F4i B4i+D5i+F5i",B);
			addNotes("F6h+B6h+D7h",T,T);

// Measure 103
						
			addNotes("D6s+F6s E6s",T);
			tie(D,T,"D6i","D6i");
			tie(A,T,"F6i","F6i");
			addNotes("D6i+F6i+A6i",T);
			addNotes("D3i A4i+D5i+F5i A3i A4i+D5i+F5i",B);

// Measure 104
						
			thirdVoiceTime = trebleTime;
						
			addRest("i",T);
			addNotes("G5s F#5s G5s A5s B5i",T);
			addNotes("G3i B4i+D5i D4i B4i+D5i",B);
			tie(D,T,T,"D6q","D6i.");
			tie(D,T,T,"G6q","G6i.");
			tie(A,T,T,"B6q","B6i.");
			addNotes("G6s",T,T);

// Measure 105
						
			addNotes("D6i G6s",T);
			tie(A,T,"D6s","D6s");
			addNotes("G6s D6i",T);
			addNotes("B3i+B4i B4i+D5i G3i+G4i G#3i+G#4i",B);

// Measure 106
						
			addNotes("C6q F6q",T);
			addNotes("A3i+A4i A4i+C5i+F5i D4i A4i+D5i+F5i",B);

// Measure 107
						
			addNotes("E6s G#6s Bn6s",T);
			tie(A,T,"E7s","E7s");
			addNotes("D7s Bn6s C7s",T);
			addNotes("E4i Bn4i+D5i+F5i G#4i Bn4i+D5i+F5i",B);

// Measure 108
						
			addNotes("A6q Bb6q",T);
			addNotes("A4q+C5q+E5q Gn4i+C5i+E5i C4i",B);

// Measure 109
						
			addNotes("F6s+A6s G#6s",T);
			tie(D,T,"F6i","F6i");
			tie(A,T,"A6i","A6i");
			addNotes("F6i+A6i+C7i",T);
			addNotes("F3i A4i+C5i+F5i C4i A4i+C5i+F5i",B);

			thirdVoiceTime = trebleTime;
			
// Measure 110
						
			addRest("i",T);
			addNotes("B5s A5s B5s C6s D6i",T);
			addNotes("B3i B4i+D5i+F5i F4i A4i+C5i+F5i",B);
			addNotes("F6h+B6h+D7h",T,T);

// Measure 111
						
			addNotes("D6s+F6s E6s",T);
			tie(D,T,"D6i","D6i");
			tie(A,T,"F6i","F6i");
			addNotes("D6i+F6i+A6i",T);
			addNotes("D3i A4i+D5i+F5i A3i A4i+D5i+F5i",B);

// Measure 112
						
			thirdVoiceTime = trebleTime;
						
			addRest("i",T);
			addNotes("G5s F#5s G5s A5s B5i",T);
			addNotes("G3i B4i+D5i D4i B4i+D5i",B);
			tie(D,T,T,"D6q","D6i.");
			tie(D,T,T,"G6q","G6i.");
			tie(A,T,T,"B6q","B6i.");
			addNotes("G6s",T,T);

// Measure 113
						
			addNotes("D6i G6s",T);
			tie(A,T,"D6s","D6s");
			addNotes("G6s D6i",T);
			addNotes("B3i+B4i B4i+D5i G3i+G4i G#3i+G#4i",B);


// Measure 114
						
			addNotes("C6q G#5i.+Bn5i.+F6i. F6s",T);
			addNotes("A3s+A4s F3s+F4s E3s+E4s D3s+D4s Db3q+Db4q",B);

			thirdVoiceTime = trebleTime;
						
// Measure 115
						
			addNotes("A5s+C6s+A6s C6i+C7i",T);
			tie(A,T,"G6s","G6s");
			addNotes("C6s D6s E6s",T);
			addNotes("C3i+C4i A4i+C5i+F5i C4i+C5i C3i+C4i",B);
			addRest("s",T,T);
			addRest("i",T,T);
			tie(A,T,T,"B5s","B5i");
			addNotes("B5i",T,T);
			
// Measure 116
			
			addNotes("A5i+F6i",T);
			addRest("i",T);
			addNotes("F6i+A6i+C7i+F7i",T);
			addRest("i",T);
			addNotes("F3i+F4i",B);
			addRest("i",B);
			addNotes("F2i+F3i",B);
			addRest("i",B);
			
			key = "C";
			
// Measure 117
			
			addNotes("C6i A5s",T);
			tie(A,T,"C6s","C6s");
			addNotes("A5s C6s A5s",T);
			addNotes("F4i+A4i+C5i+F5i F4i+A4i+C5i+F5i F#4i+A4i+C#5i+D5i F#4i+A4i+C#5i+D5i",B);
			
// Measure 118
			
			addNotes("G5s C6s E6s",T);
			tie(A,T,"G6s","G6s");
			addNotes("E6s C6s G5s",T);
			addNotes("G4i+C5i+E5i G4i+C5i+E5i G4i+C5i+E5i G4i+C5i+E5i",B);


// Measure 119
			
			addNotes("F#5i+A5i F#5i+C6i F5s+E6s F5i+D6i",T);
			addNotes("D4i+C5i D4i+A4i G4i+B4i G4i+B4i",B);
	
// Measure 120
			
			tie(D,T,"E5s","E5q");
			tie(A,T,"C6s","C6q");
			addNotes("C6i+E6i+G6i+C7i",T);
			addRest("i",T);
			addNotes("C4q+C5q C3i+C4i",B);
			addRest("i",B);

// Measure 121
			
			addNotes("D5i+F5i C#5s+E5s",T);
			tie(D,T,"D5s","D5s");
			tie(A,T,"F5s","F5s");
			addNotes("C5s+E5s D5i+F5i",T);
			addNotes("F3i F4i+A4i A3i F4i+A4i",B);
			
// Measure 122
			
			addRest("s",T);
			addNotes("A5s F5s+D6s A5s C6s D6s C6s A5s",T);
			addNotes("F3i F4i+A4i A3i F4i+A4i",B);

// Measure 123
			
			addNotes("E5i+G5i D#5s+F#5s",T);
			tie(D,T,"E5s","E5s");
			tie(A,T,"G5s","G5s");
			addNotes("D5s+F5s E5i+G5i",T);
			addNotes("C4i E4i+G4i+C5i G3i E4i+G4i+C5i",B);

// Measure 124
			
			addRest("s",T);
			addNotes("C6s G5s+E6s C6s D6s E6s D6s C6s",T);
			addNotes("C4i E4i+G4i+C5i G3i E4i+G4i+C5i",B);

// Measure 125
			
			addNotes("B5i+D6i A#5s+C#6s",T);
			tie(D,T,"B5s","B5s");
			tie(A,T,"D6s","D6s");
			addNotes("A5s+C6s B5i+D6i",T);
			addNotes("G3i F4i+G4i+B4i B3i F4i+G4i+B4i",B);
	
// Measure 126
			
			addRest("s",T);
			addNotes("F6s B5s+A6s F6s G6s A6s G6s F6s",T);
			addNotes("G3i F4i+G4i+B4i D4i F4i+G4i+B4i",B);

			
// Measure 127
			
			addNotes("C6s+C7s C6s+C7s C6q+C7q C6i+A6i",T);
			addNotes("D#4i+F#4i+C5i D#4q+F#4q+C5q D#4i+F#4i+C5i",B);
			
// Measure 128
			
			addNotes("C6i+G6i E5s+G5s E5s+G5s E5i+G5i E5i+G5i",T);
			addNotes("E4i+G4i+C5i",B);
			addRest("i",B);
			addRest("q",B);
			
// Measure 129
			
			addNotes("D5i+F5i C#5s+E5s",T);
			tie(D,T,"D5s","D5s");
			tie(A,T,"F5s","F5s");
			addNotes("C5s+E5s D5i+F5i",T);
			addNotes("F3i F4i+A4i A3i F4i+A4i",B);
			
// Measure 130
			
			addRest("s",T);
			addNotes("A5s F5s+D6s A5s C6s D6s C6s A5s",T);
			addNotes("F3i F4i+A4i A3i F4i+A4i",B);
			
// Measure 131
			
			addNotes("E5i+G5i D#5s+F#5s",T);
			tie(D,T,"E5s","E5s");
			tie(A,T,"G5s","G5s");
			addNotes("D5s+F5s E5i+G5i",T);
			addNotes("C4i E4i+G4i+C5i G3i E4i+G4i+C5i",B);
			
// Measure 132
			
			addRest("s",T);
			addNotes("C6s G5s+E6s C6s D6s E6s D6s C6s",T);
			addNotes("C4i E4i+G4i+C5i G3i E4i+G4i+C5i",B);

// Measure 133
			
			addNotes("A5s G#5s A5s",T);
			tie(D,T,"A5s","A5s");
			tie(A,T,"G6s","G6s");
			addNotes("A5i+F6i A5s+C6s",T);
			addNotes("F3i+F4i D3i+D4i E3i+E4i F3i+F4i",B);
			
// Measure 134
			
			addNotes("G5s+E6s D#6s E6s",T);
			tie(A,T,"A6s","A6s");
			addNotes("C7s G6s E6s",T);
			addNotes("G3i+G4i G4i+C5i+E5i F#4i+C#5i+D5i G4i+C5i+E5i",B);
			
// Measure 135
			
			addNotes("F#5i+C6i F#5i+C6i Fn5s+B5s+E6s F5i+B5i+D6i",T);
			addNotes("A3i+A4i D3i+D4i G3i+G4i B3i+B4i",B);
			
// Measure 136
			
			tie(D,T,"E5s","E5i");
			tie(D,T,"G5s","G5i");
			tie(A,T,"C6s","C6i");
			addNotes("E5s+G5s E5s+G5s E5i+G5i E5i+G5i",T);
			addNotes("C4i+C5i",B);
			addRest("i",B);
			addRest("q",B);
			
// Measure 137
			
			addNotes("D5i+F5i C#5s+E5s",T);
			tie(D,T,"D5s","D5s");
			tie(A,T,"F5s","F5s");
			addNotes("C5s+E5s D5i+F5i",T);
			addNotes("F3i F4i+A4i A3i F4i+A4i",B);
						
// Measure 138
						
			addRest("s",T);
			addNotes("A5s F5s+D6s A5s C6s D6s C6s A5s",T);
		    addNotes("F3i F4i+A4i A3i F4i+A4i",B);

// Measure 139
						
			addNotes("E5i+G5i D#5s+F#5s",T);
			tie(D,T,"E5s","E5s");
			tie(A,T,"G5s","G5s");
			addNotes("D5s+F5s E5i+G5i",T);
			addNotes("C4i E4i+G4i+C5i G3i E4i+G4i+C5i",B);

// Measure 140
						
			addRest("s",T);
			addNotes("C6s G5s+E6s C6s D6s E6s D6s C6s",T);
			addNotes("C4i E4i+G4i+C5i G3i E4i+G4i+C5i",B);

// Measure 141
						
			addNotes("B5i+D6i A#5s+C#6s",T);
			tie(D,T,"B5s","B5s");
			tie(A,T,"D6s","D6s");
			addNotes("A5s+C6s B5i+D6i",T);
			addNotes("G3i F4i+G4i+B4i B3i F4i+G4i+B4i",B);
				
// Measure 142
						
			addRest("s",T);
			addNotes("F6s B5s+A6s F6s G6s A6s G6s F6s",T);
			addNotes("G3i F4i+G4i+B4i D4i F4i+G4i+B4i",B);
			
// Measure 143
						
			addNotes("C6s+C7s C6s+C7s C6q+C7q C6i+A6i",T);
			addNotes("D#4i+F#4i+C5i D#4q+F#4q+C5q D#4i+F#4i+C5i",B);
						
// Measure 144
						
			addNotes("C6i+G6i E5s+G5s E5s+G5s E5i+G5i E5i+G5i",T);
			addNotes("E4i+G4i+C5i",B);
			addRest("i",B);
			addRest("q",B);
						
// Measure 145
						
			addNotes("D5i+F5i C#5s+E5s",T);
			tie(D,T,"D5s","D5s");
			tie(A,T,"F5s","F5s");
			addNotes("C5s+E5s D5i+F5i",T);
			addNotes("F3i F4i+A4i A3i F4i+A4i",B);
						
// Measure 146
						
			addRest("s",T);
			addNotes("A5s F5s+D6s A5s C6s D6s C6s A5s",T);
			addNotes("F3i F4i+A4i A3i F4i+A4i",B);
						
// Measure 147
						
			addNotes("E5i+G5i D#5s+F#5s",T);
			tie(D,T,"E5s","E5s");
			tie(A,T,"G5s","G5s");
			addNotes("D5s+F5s E5i+G5i",T);
		    addNotes("C4i E4i+G4i+C5i G3i E4i+G4i+C5i",B);
						
// Measure 148
						
		    addRest("s",T);
			addNotes("C6s G5s+E6s C6s D6s E6s D6s C6s",T);
			addNotes("C4i E4i+G4i+C5i G3i E4i+G4i+C5i",B);

// Measure 149
						
			addNotes("A5s G#5s A5s",T);
			tie(D,T,"A5s","A5s");
			tie(A,T,"G6s","G6s");
			addNotes("A5i+F6i A5s+C6s",T);
			addNotes("F3i+F4i D3i+D4i E3i+E4i F3i+F4i",B);
						
// Measure 150
						
			addNotes("G5s+E6s D#6s E6s",T);
		    tie(A,T,"A6s","A6s");
			addNotes("C7s G6s E6s",T);
			addNotes("G3i+G4i G4i+C5i+E5i F#4i+C#5i+D5i G4i+C#5i+E5i",B);

// Measure 151
						
			addNotes("F#5i+C6i F#5i+C6i Fn5s+B5s+E6s F5i+B5i+D6i E5s+G5s+C6s",T);
			addNotes("A3i+A4i D3i+D4i G3i+G4i B3i+B4i",B);

// Measure 152
			
			addNotes("E5q+G5q+C6q",T);
			addNotes("C6i+E6i+G6i+C7i",T);
			addRest("i",T);
			addNotes("C4i+C5i G3i+G4i C3i+C4i",B);
			addRest("i",B);
				
			player.setSequence(sequence);
				
			player.start();	
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}
	
	public static void playItsy_Bitsy_Spider(CreatingMusic creatingMusic) {
		try {
	        player = MidiSystem.getSequencer();
				
			player.open();
				
			player.setTempoInBPM(180);
				
			Sequence sequence = new Sequence(Sequence.PPQ,4);
				
			track = sequence.createTrack();
			
			key = "G";
			
			addRest("i",T);
			addNotes("D5i",T);
			addRest("q",B);
			
			addNotes("G5i. G5s G5i. A5s B5q B5i. B5s",T);
			addNotes("G4h+B4h G4h+B4h",B);
			
			addNotes("A5i. G5s A5i. B5s G5q",T);
			addRest("q",T);
			addNotes("D4h+F4h G4h+B4h",B);
			
			addNotes("B5q B5i. C6s D6q D6q",T);
			addNotes("G4h+B4h G4h+B4h",B);
			
			addNotes("C6i. B5s C6i. D6s B5q",T);
			addRest("q",T);
			
			addNotes("G5q G5i. A5s B5q B5q",T);
			addNotes("G4i+B4i G4i+B4i",B);
			
			addNotes("A5i. G5s A5i. B5s G5q D5i. D5s",T);
			addNotes("D4h+F4h G4h+B4h",B);
			
			addNotes("G5i. G5s G5i. A5s B5q B5i. B5s",T);
			addNotes("G4h+B4h G4h+B4h",B);
			
			addNotes("A5i. G5s A5i. B5s G5q",T);
			addRest("q",T);
			addNotes("D4h+F4h G4h+B4h",B);
			
			
			
			player.setSequence(sequence);
			
			player.start();	
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}
	
	public static void playRondo_Alla_Turca(CreatingMusic creatingMusic) {
		try {
	        player = MidiSystem.getSequencer();
				
			player.open();
				
			player.setTempoInBPM(240);
				
			Sequence sequence = new Sequence(Sequence.PPQ,4);
				
			track = sequence.createTrack();
			
// Measure 1
			
			addNotes("B5s A5s G#5s A5s",T);
			addRest("q",B);

// Measure 2
			
			addNotes("C6i",T);
			addRest("i",T);
			addNotes("D6s C6s B5s C6s",T);
			addNotes("A4i C5i+E5i C5i+E5i C5i+E5i",B);

// Measure 3
			
			addNotes("E6i",T);
			addRest("i",T);
			addNotes("F6s E6s D#6s E6s",T);
			addNotes("A4i C5i+E5i C5i+E5i C5i+E5i",B);

// Measure 4
			
			addNotes("B6s A6s G#6s A6s B6s A6s G6s A6s",T);
			addNotes("A4i C5i+E5i A4i C5i+E5i",B);

// Measure 5
			
			addNotes("C7q A6i C7i",T);
			addNotes("A4i C5i+E5i C5i+E5i C5i+E5i",B);

// Measure 6
			
			addNotes("B6i F#6i+A6i E6i+G6i F6i+A6i",T);
			addNotes("E4i B4i+E5i B4i+E5i B4i+E5i",B);
			
// Measure 7

			addNotes("B6i F#6i+A6i E6i+G6i F6i+A6i",T);
			addNotes("E4i B4i+E5i B4i+E5i B4i+E5i",B);
			
// Measure 8
			
			addNotes("B6i F#6i+A6i E6i+G6i D#6i+F#6i",T);
			addNotes("E4i B4i+E5i B3i B4i",B);
		
// Measure 9
			
			addNote("E6q",A,T);
			addNote("E4q",A,B);
			
// Measure 10
			
			addNotes("B5s A5s G#5s A5s",T);
			addRest("q",B);

// Measure 11
						
		    addNotes("C6i",T);
		    addRest("i",T);
			addNotes("D6s C6s B5s C6s",T);
			addNotes("A4i C5i+E5i C5i+E5i C5i+E5i",B);

// Measure 12
						
			addNotes("E6i",T);
			addRest("i",T);
			addNotes("F6s E6s D#6s E6s",T);
			addNotes("A4i C5i+E5i C5i+E5i C5i+E5i",B);

// Measure 13
						
			addNotes("B6s A6s G#6s A6s B6s A6s G6s A6s",T);
			addNotes("A4i C5i+E5i A4i C5i+E5i",B);

// Measure 14
						
			addNotes("C7q A6i C7i",T);
			addNotes("A4i C5i+E5i C5i+E5i C5i+E5i",B);

// Measure 15
						
			addNotes("B6i F#6i+A6i E6i+G6i F6i+A6i",T);
			addNotes("E4i B4i+E5i B4i+E5i B4i+E5i",B);
						
// Measure 16

			addNotes("B6i F#6i+A6i E6i+G6i F6i+A6i",T);
			addNotes("E4i B4i+E5i B4i+E5i B4i+E5i",B);
						
// Measure 17
						
			addNotes("B6i F#6i+A6i E6i+G6i D#6i+F#6i",T);
			addNotes("E4i B4i+E5i B3i B4i",B);
					
// Measure 18
						
			addNote("E6q",A,T);
			addNote("E4q",A,B);
									
// Measure 19
									
			addNotes("C6i+E6i Dn6i+Fn6i",T);
			addRest("q",B);
			
// Measure 20
			
			addNotes("E6i+G6i E6i+G6i A6s G6s F6s E6s",T);
			addNotes("C4i C5i E4i E5i",B);
			
			thirdVoiceTime = trebleTime;
			
// Measure 21
			
			addNotes("B5i G5i C6i+E6i Dn6i+Fn6i",T);
			addNote("G4q",A,B);
			addRest("q",B);
			addNote("D6q",A,T,T);
			
// Measure 22
			
			addNotes("E6i+G6i E6i+G6i A6s G6s F6s E6s",T);
			addNotes("C4i C5i E4i E5i",B);
			
// Measure 23
			
			addNotes("B5q+D6q A5i+C6i B5i+D6i",T);
			addNote("G4q",A,B);
			addRest("q",B);
			
// Measure 24
			
			addNotes("C6i+E6i C6i+E6i F6s E6s D6s C6s",T);
			addNotes("A3i A4i C4i C5i",B);
			
			thirdVoiceTime = trebleTime;
			
// Measure 24
			
			addNotes("G#5i E5i A5i+C6i B5i+D6i",T);
			addNote("E4q",A,B);
			addRest("q",B);
			addNote("B5q",A,T,T);
			
// Measure 25
			
			addNotes("C6i+E6i C6i+E6i F6s E6s D6s C6s",T);
			addNotes("A3i A4i C4i C5i",B);
			
// Measure 26
			
			addNotes("G#5q+B5q B5s A5s G5s A5s",T);
			addNote("E4q",A,B);
			addRest("q",B);
			
// Measure 27
			
			addNote("C6i",A,T);
			addRest("i",T);
			addNotes("D6s C6s B5s C6s",T);
			addNotes("A4i C5i+E5i C5i+E5i C5i+E5i",B);

// Measure 28
			
			addNote("E6i",A,T);
			addRest("i",T);
			addNotes("F6s E6s D#6s E6s",T);
			addNotes("A4i C5i+E5i C5i+E5i C5i+E5i",B);
			
// Measure 29
			
			addNotes("B6s A6s G#6s A6s B6s A6s G6s A6s",T);
			addNotes("A4i C5i+E5i A4i C5i+E5i",B);
			
// Measure 30
			
			addNotes("C7q A6i B6i",T);
			addNotes("F4i A4i+D#5i A4i+D#5i A4i+D#5i",B);
			
// Measure 31
			
			addNotes("C7i B6i A6i G#6i",T);
			addNotes("F4i A4i+E5i Dn4i F4i+B4i",B);
			
// Measure 32
			
			addNotes("A6i E6i F6i D6i",T);
			addNotes("C4i E4i+A4i D4i F4i+B4i",B);
			
// Measure 33
			
			addNotes("C6q B5i. A5t B5t",T);
			addNotes("E4i+A4i E4i+A4i E4i+G#4i E4i+G#4i",B);
			
// Measure 34
			
			addNote("A5q",A,T);
			addNotes("A3q+A4q",B);
			
// Measure 35
			
		    addNotes("C6i+E6i Dn6i+Fn6i",T);
			addRest("q",B);
						
// Measure 36
						
			addNotes("E6i+G6i E6i+G6i A6s G6s F6s E6s",T);
			addNotes("C4i C5i E4i E5i",B);
						
			thirdVoiceTime = trebleTime;
						
// Measure 37
						
			addNotes("B5i G5i C6i+E6i Dn6i+Fn6i",T);
			addNote("G4q",A,B);
			addRest("q",B);
			addNote("D6q",A,T,T);
						
// Measure 38
						
			addNotes("E6i+G6i E6i+G6i A6s G6s F6s E6s",T);
			addNotes("C4i C5i E4i E5i",B);
						
// Measure 39
						
			addNotes("B5q+D6q A5i+C6i B5i+D6i",T);
		    addNote("G4q",A,B);
			addRest("q",B);
						
// Measure 40
						
			addNotes("C6i+E6i C6i+E6i F6s E6s D6s C6s",T);
			addNotes("A3i A4i C4i C5i",B);
						
			thirdVoiceTime = trebleTime;
						
// Measure 41
						
			addNotes("G#5i E5i A5i+C6i B5i+D6i",T);
			addNote("E4q",A,B);
			addRest("q",B);
			addNote("B5q",A,T,T);
						
// Measure 42
						
			addNotes("C6i+E6i C6i+E6i F6s E6s D6s C6s",T);
			addNotes("A3i A4i C4i C5i",B);
						
// Measure 43
						
			addNotes("G#5q+B5q B5s A5s G5s A5s",T);
			addNote("E4q",A,B);
			addRest("q",B);
						
// Measure 44
						
			addNote("C6i",A,T);
			addRest("i",T);
			addNotes("D6s C6s B5s C6s",T);
			addNotes("A4i C5i+E5i C5i+E5i C5i+E5i",B);

// Measure 45
						
			addNote("E6i",A,T);
			addRest("i",T);
			addNotes("F6s E6s D#6s E6s",T);
			addNotes("A4i C5i+E5i C5i+E5i C5i+E5i",B);
						
// Measure 46
						
			addNotes("B6s A6s G#6s A6s B6s A6s G6s A6s",T);
			addNotes("A4i C5i+E5i A4i C5i+E5i",B);
						
// Measure 47
						
			addNotes("C7q A6i B6i",T);
			addNotes("F4i A4i+D#5i A4i+D#5i A4i+D#5i",B);
						
// Measure 48
						
			addNotes("C7i B6i A6i G#6i",T);
			addNotes("F4i A4i+E5i Dn4i F4i+B4i",B);
						
// Measure 49
						
			addNotes("A6i E6i F6i D6i",T);
			addNotes("C4i E4i+A4i D4i F4i+B4i",B);
						
// Measure 50
						
			addNotes("C6q B5i. A5t B5t",T);
			addNotes("E4i+A4i E4i+A4i E4i+G#4i E4i+G#4i",B);
						
// Measure 51
						
			addNote("A5q",A,T);
			addNotes("A3q+A4q",B);
			
            key = "A";
            
// Measure 52
            
            addNotes("A5i+A6i B5i+B6i",T);
            addRest("q",B);
            
// Measure 53
            
            addNotes("C6q+C7q A5i+A6i B5i+B6i",T);
            addNotes("A4i A4i A4i A4i",B);
            
// Measure 54
            
            addNotes("C6i+C7i B5i+B5i A5i+A6i G5i+G6i",T);
            addNotes("A4i A4i A4i A4i",B);
            
// Measure 55
            
            addNotes("F5i+F6i G5i+G6i A5i+A6i B5i+B6i",T);
            addNotes("D4i D4i D#4i D#4i",B);
			
// Measure 56
            
            addNotes("G5i+G6i E5i+E6i A5i+A6i B5i+B6i",T);
            addNotes("E4i E4i E4i E4i",B);
            
// Measure 57
            
            addNotes("C6q+C7q A5i+A6i B5i+B6i",T);
            addNotes("A4i A4i A4i A4i",B);
            
// Measure 58
            
            addNotes("C6i+C7i B5i+B6i A5i+A6i G5i+G6i",T);
            addNotes("A4i A4i A4i A4i",B);
            
// Measure 59
            
            addNotes("F5i+F5i B5i+B6i G5i+G6i E5i+E6i",T);
            addNotes("D4i D4i E4i E4i",B);
            
// Measure 60
            
            addNotes("A5q+A6q",T);
            addNote("A3q",A,B);
            
// Measure 61
			
            addNote("A5q",A,T);
         	addNotes("A3q+A4q",B);
         			
            key = "A";
                     
// Measure 62
                     
            addNotes("A5i+A6i B5i+B6i",T);
            addRest("q",B);
                     
// Measure 63
                     
            addNotes("C6q+C7q A5i+A6i B5i+B6i",T);
            addNotes("A4i A4i A4i A4i",B);
                     
// Measure 64
                     
            addNotes("C6i+C7i B5i+B5i A5i+A6i G5i+G6i",T);
            addNotes("A4i A4i A4i A4i",B);
                     
// Measure 65
                     
            addNotes("F5i+F6i G5i+G6i A5i+A6i B5i+B6i",T);
            addNotes("D4i D4i D#4i D#4i",B);
         			
// Measure 66
                     
            addNotes("G5i+G6i E5i+E6i A5i+A6i B5i+B6i",T);
            addNotes("E4i E4i E4i E4i",B);
                     
// Measure 67
                     
            addNotes("C6q+C7q A5i+A6i B5i+B6i",T);
            addNotes("A4i A4i A4i A4i",B);
                     
// Measure 68
                     
            addNotes("C6i+C7i B5i+B6i A5i+A6i G5i+G6i",T);
            addNotes("A4i A4i A4i A4i",B);
                     
// Measure 69
                     
            addNotes("F5i+F5i B5i+B6i G5i+G6i E5i+E6i",T);
            addNotes("D4i D4i E4i E4i",B);
                     
// Measure 70
                     
            addNotes("A5q+A6q",T);
            addNote("A3q",A,B);
            
// Measure 71
            
            addNotes("C7s D7s C7s B6s",T);
            addRest("q",B);
            
// Measure 72
            
            addNotes("A6s B6s A6s G6s F6s A6s G6s F6s",T);
            addNotes("F4i A4i+C5i A4i+C5i A4i+C5i",B);
            
// Measure 73
            
            addNotes("E#6s F6s G6s E6s C6s D#6s E6s C6s",T);
            addNotes("G4i B4i+C5i B4i+C5i B4i+C5i",B);
            
// Measure 74
            
            addNotes("F6s E#6s F6s G6s A6s G6s A6s B6s",T);
            addNotes("F4i A4i+C5i A4i+C5i A4i+C5i",B);
            
// Measure 75
            
            addNotes("C7s B#6s C7s B6s C7s D7s C7s Bn6s",T);
            addNotes("E#4i G4i+C5i G4i+C5i G4i+C5i",B);
            
// Measure 76
            
            addNotes("A6s B6s A6s G6s F6s A6s G6s F6s",T);
            addNotes("F4i A4i+C5i A4i+C5i A4i+C5i",B);
            
// Measure 77
            
            addNotes("En6s F6s G6s E6s C6s D#6s E6s C6s",T);
            addNotes("G4i C5i+En5i C5i+En5i C5i+En5i",B);
            
// Measure 78
            
            addNotes("D#6s E6s F6s D#6s B#5s C6s D#6s B5s",T);
            addNotes("G4i D#5i+F5i D#5i+F5i D#5i+F5i",B);
            
// Measure 79
            
            addNote("C6q",A,T);
            addNotes("C5q+E5q",B);

// Measure 80
            
            addNotes("C7s D7s C7s B6s",T);
            addRest("q",B);
            
// Measure 81
            
            addNotes("A6s B6s A6s G6s F6s A6s G6s F6s",T);
            addNotes("F4i A4i+C5i A4i+C5i A4i+C5i",B);
            
// Measure 82
            
            addNotes("E#6s F6s G6s E6s C6s D#6s E6s C6s",T);
            addNotes("G4i B4i+C5i B4i+C5i B4i+C5i",B);
            
// Measure 83
            
            addNotes("F6s E#6s F6s G6s A6s G6s A6s B6s",T);
            addNotes("F4i A4i+C5i A4i+C5i A4i+C5i",B);
            
// Measure 84
            
            addNotes("C7s B#6s C7s B6s C7s D7s C7s Bn6s",T);
            addNotes("E#4i G4i+C5i G4i+C5i G4i+C5i",B);
            
// Measure 85
            
            addNotes("A6s B6s A6s G6s F6s A6s G6s F6s",T);
            addNotes("F4i A4i+C5i A4i+C5i A4i+C5i",B);
            
// Measure 86
            
            addNotes("En6s F6s G6s E6s C6s D#6s E6s C6s",T);
            addNotes("G4i C5i+En5i C5i+En5i C5i+En5i",B);
            
// Measure 87
            
            addNotes("D#6s E6s F6s D#6s B#5s C6s D#6s B5s",T);
            addNotes("G4i D#5i+F5i D#5i+F5i D#5i+F5i",B);
            
// Measure 88
            
            addNote("C6q",A,T);
            addNotes("C5q+E5q",B);
            
// Measure 89
            
            addNotes("E6s Dn6s C6s Bn5s",T);
            addRest("q",B);
            
// Measure 90
            
            addNotes("A5s B5s C6s D6s E6s F6s G6s A6s",T);
            addNotes("A4i C5i+E5i C5i+E5i C5i+E5i",B);
            
// Measure 91
            
            addNotes("A6s G6s F6s E6s E6s D6s C6s B5s",T);
            addNotes("B4i D5i+E5i G4i D5i+E5i",B);
            
// Measure 92
            
            addNotes("A5s B5s C6s D6s E6s F6s G6s A6s",T);
            addNotes("A4i C5i+E5i C5i+E5i C5i+E5i",B);
            
// Measure 93
            
            addNotes("A#6i B6i E6s D6s C6s B5s",T);
            addNotes("E4i G4i+D5i G4i+D5i G4i+D5i",B);
            
// Measure 94
            
            addNotes("A5s B5s C6s D6s E6s F6s G6s A6s",T);
            addNotes("A4i C5i+E5i C5i+E5i C5i+E5i",B);
            
// Measure 95
            
            addNotes("A6s G6s F6s E6s E6s D6s C6s B5s",T);
            addNotes("B4i D5i+E5i G4i D5i+E5i",B);
            
// Measure 96
            
            addNotes("C6s E6s A5s C6s B5s D6s G5s B5s",T);
            addNotes("A4i F4i D4i E4i",B);
            
// Measure 97
            
            addNotes("A5q C7s D7s C7s B6s",T);
            addNotes("A3i A4i",B);
            addRest("q",B);
            
// Measure 98
            
            addNotes("A6s B6s A6s G6s F6s A6s G6s F6s",T);
            addNotes("F4i A4i+C5i A4i+C5i A4i+C5i",B);
            
// Measure 99
            
            addNotes("E#6s F6s G6s E#6s C6s D#6s E6s C6s",T);
            addNotes("G4i B4i+C5i B4i+C5i B4i+C5i",B);
            
// Measure 100
            
            addNotes("F6s E#6s F6s G6s A6s G6s A6s B6s",T);
            addNotes("F4i A4i+C5i A4i+C5i A4i+C5i",B);
            
// Measure 101
            
            addNotes("C7s B#6s C7s B6s C7s B6s C7s A#6s",T);
            addNotes("C4i G4i+C5i Gn4i+C5i F4i+C5i",B);
            
// Measure 102
            
            addNotes("D7s C7s D7s C7s D7s C7s D7s C7s",T);
            addNotes("B3i F4i+B4i F4i+B4i F4i+B4i",B);
            
// Measure 103
            
            addNotes("D7s C7s B6s A6s G6s A6s B6s G6s",T);
            addNotes("B3i G#4i+B4i G#4i+B4i G#4i+B4i",B);
            
// Measure 104
            
            addNotes("A6s B6s C7s F6s E#6s F6s G6s E6s",T);
            addNotes("C4i F4i+A4i C4i G4i+B4i",B);
            
// Measure 105
            
            addNote("F6q",A,T);
            addNotes("F4q+A4q",B);

// Measure 106
            
            addNotes("E6s Dn6s C6s Bn5s",T);
            addRest("q",B);
            
// Measure 107
            
            addNotes("A5s B5s C6s D6s E6s F6s G6s A6s",T);
            addNotes("A4i C5i+E5i C5i+E5i C5i+E5i",B);
            
// Measure 108
            
            addNotes("A6s G6s F6s E6s E6s D6s C6s B5s",T);
            addNotes("B4i D5i+E5i G4i D5i+E5i",B);
            
// Measure 109
            
            addNotes("A5s B5s C6s D6s E6s F6s G6s A6s",T);
            addNotes("A4i C5i+E5i C5i+E5i C5i+E5i",B);
            
// Measure 110
            
            addNotes("A#6i B6i E6s D6s C6s B5s",T);
            addNotes("E4i G4i+D5i G4i+D5i G4i+D5i",B);
            
// Measure 111
            
            addNotes("A5s B5s C6s D6s E6s F6s G6s A6s",T);
            addNotes("A4i C5i+E5i C5i+E5i C5i+E5i",B);
            
// Measure 112
            
            addNotes("A6s G6s F6s E6s E6s D6s C6s B5s",T);
            addNotes("B4i D5i+E5i G4i D5i+E5i",B);
            
// Measure 113
            
            addNotes("C6s E6s A5s C6s B5s D6s G5s B5s",T);
            addNotes("A4i F4i D4i E4i",B);
            
// Measure 114
            
            addNotes("A5q C7s D7s C7s B6s",T);
            addNotes("A3i A4i",B);
            addRest("q",B);
            
// Measure 115
            
            addNotes("A6s B6s A6s G6s F6s A6s G6s F6s",T);
            addNotes("F4i A4i+C5i A4i+C5i A4i+C5i",B);
            
// Measure 116
            
            addNotes("E#6s F6s G6s E#6s C6s D#6s E6s C6s",T);
            addNotes("G4i B4i+C5i B4i+C5i B4i+C5i",B);
            
// Measure 117
            
            addNotes("F6s E#6s F6s G6s A6s G6s A6s B6s",T);
            addNotes("F4i A4i+C5i A4i+C5i A4i+C5i",B);
            
// Measure 118
            
            addNotes("C7s B#6s C7s B6s C7s B6s C7s A#6s",T);
            addNotes("C4i G4i+C5i Gn4i+C5i F4i+C5i",B);
            
// Measure 119
            
            addNotes("D7s C7s D7s C7s D7s C7s D7s C7s",T);
            addNotes("B3i F4i+B4i F4i+B4i F4i+B4i",B);
            
// Measure 120
            
            addNotes("D7s C7s B6s A6s G6s A6s B6s G6s",T);
            addNotes("B3i G#4i+B4i G#4i+B4i G#4i+B4i",B);
            
// Measure 121
            
            addNotes("A6s B6s C7s F6s E#6s F6s G6s E6s",T);
            addNotes("C4i F4i+A4i C4i G4i+B4i",B);
            
// Measure 122
            
            addNote("F6q",A,T);
            addNotes("F4q+A4q",B);
            
// Measure 123
            
            addNotes("A5i+A6i B5i+B6i",T);
            addRest("q",B);
            
// Measure 124
            
            addNotes("C6q+C7q A5i+A6i B5i+B6i",T);
            addNotes("A4i A4i A4i A4i",B);
            
// Measure 125
            
            addNotes("C6i+C7i B5i+B6i A5i+A6i G5i+G6i",T);
            addNotes("A4i A4i A4i A4i",B);
            
// Measure 126
            
            addNotes("F5i+F6i G5i+G6i A5i+A6i B5i+B6i",T);
            addNotes("D4i D4i D#4i D#4i",B);
            
// Measure 127
            
            addNotes("G5i+G6i E5i+E6i A5i+A6i B5i+B6i",T);
            addNotes("E4i E4i E4i E4i",B);
            
// Measure 128
            
            addNotes("C6q+C7q A5i+A6i B5i+B6i",T);
            addNotes("A4i A4i A4i A4i",B);
            
// Measure 129
            
            addNotes("C6i+C7i B5i+B6i A5i+A6i G5i+G6i",T);
            addNotes("A4i A4i A4i A4i",B);
            
// Measure 130
            
            addNotes("F5i+F6i B5i+B6i G5i+G6i E5i+E6i",T);
            addNotes("D4i D4i E4i E4i",B);
            
// Measure 131
            
            addNotes("A5q+A6q",T);
            addNote("A3q",A,B);
            
// Measure 132
            
            addNotes("A5i+A6i B5i+B6i",T);
            addRest("q",B);
            
// Measure 133
            
            addNotes("C6q+C7q A5i+A6i B5i+B6i",T);
            addNotes("A4i A4i A4i A4i",B);
            
// Measure 134
            
            addNotes("C6i+C7i B5i+B6i A5i+A6i G5i+G6i",T);
            addNotes("A4i A4i A4i A4i",B);
            
// Measure 135
            
            addNotes("F5i+F6i G5i+G6i A5i+A6i B5i+B6i",T);
            addNotes("D4i D4i D#4i D#4i",B);
            
// Measure 136
            
            addNotes("G5i+G6i E5i+E6i A5i+A6i B5i+B6i",T);
            addNotes("E4i E4i E4i E4i",B);
            
// Measure 137
            
            addNotes("C6q+C7q A5i+A6i B5i+B6i",T);
            addNotes("A4i A4i A4i A4i",B);
            
// Measure 138
            
            addNotes("C6i+C7i B5i+B6i A5i+A6i G5i+G6i",T);
            addNotes("A4i A4i A4i A4i",B);
            
// Measure 139
            
            addNotes("F5i+F6i B5i+B6i G5i+G6i E5i+E6i",T);
            addNotes("D4i D4i E4i E4i",B);
            
// Measure 140
            
            addNotes("A5q+A6q",T);
            addNote("A3q",A,B);
            
            key = "C";
            
// Measure 141
            
            addNotes("B5s A5s G#5s A5s",T);
            addRest("q",B);
            
// Measure 142
            
            addNote("C6i",A,T);
            addRest("i",T);
            addNotes("D6s C6s B5s C6s",T);
            addNotes("A4i C5i+E5i C5i+E5i C5i+E5i",B);
            
// Measure 143
            
            addNote("E6i",A,T);
            addRest("i",T);
            addNotes("F6s E6s D#6s E6s",T);
            addNotes("A4i C5i+E5i C5i+E5i C5i+E5i",B);
            
// Measure 144
            
            addNotes("B6s A6s G#6s A6s B6s A6s G6s A6s",T);
            addNotes("A4i C5i+E5i C5i+E5i C5i+E5i",B);
            
// Measure 145
            
            addNotes("C7q A6i C7i",T);
            addNotes("A4i C5i+E5i C5i+E5i C5i+E5i",B);
            
// Measure 146
            
            addNotes("B6i F#6i+A6i E6i+G6i F6i+A6i",T);
            addNotes("E4i B4i+E5i B4i+E5i B4i+E5i",B);
            
// Measure 147
            
            addNotes("B6i F#6i+A6i E6i+G6i F6i+A6i",T);
            addNotes("E4i B4i+E5i B4i+E5i B4i+E5i",B);
            
// Measure 148
            
            addNotes("B6i F#6i+A6i E6i+G6i D#6i+F6i",T);
            addNotes("E4i B4i+E5i B3i B4i",B);
            
// Measure 149
            
            addNote("E6q",A,T);
            addNote("E4q",A,B);
            
// Measure 150
            
            addNotes("B5s A5s G#5s A5s",T);
            addRest("q",B);
            
// Measure 151
            
            addNote("C6i",A,T);
            addRest("i",T);
            addNotes("D6s C6s B5s C6s",T);
            addNotes("A4i C5i+E5i C5i+E5i C5i+E5i",B);
            
// Measure 152
            
            addNote("E6i",A,T);
            addRest("i",T);
            addNotes("F6s E6s D#6s E6s",T);
            addNotes("A4i C5i+E5i C5i+E5i C5i+E5i",B);
            
// Measure 153
            
            addNotes("B6s A6s G#6s A6s B6s A6s G6s A6s",T);
            addNotes("A4i C5i+E5i C5i+E5i C5i+E5i",B);
            
// Measure 154
            
            addNotes("C7q A6i C7i",T);
            addNotes("A4i C5i+E5i C5i+E5i C5i+E5i",B);
            
// Measure 155
            
            addNotes("B6i F#6i+A6i E6i+G6i F6i+A6i",T);
            addNotes("E4i B4i+E5i B4i+E5i B4i+E5i",B);
            
// Measure 156
            
            addNotes("B6i F#6i+A6i E6i+G6i F6i+A6i",T);
            addNotes("E4i B4i+E5i B4i+E5i B4i+E5i",B);
            
// Measure 157
            
            addNotes("B6i F#6i+A6i E6i+G6i D#6i+F6i",T);
            addNotes("E4i B4i+E5i B3i B4i",B);
            
// Measure 158
            
            addNote("E6q",A,T);
            addNote("E4q",A,B);
            
// Measure 159
            
            addNotes("C6i+E6i Dn6i+Fn6i",T);
            addRest("q",B);
            
// Measure 160
            
            addNotes("E6i+G6i E6i+G6i A6s G6s F6s E6s",T);
            addNotes("C4i C5i E4i E5i",B);

            thirdVoiceTime = trebleTime;
            
// Measure 161
            
            addNotes("B5i G5i C6i+E6i Dn6i+Fn6i",T);
            addNote("G4q",A,B);
            addRest("q",B);
            addNote("D6q",A,T,T);
            
// Measure 162
            
            addNotes("E6i+G6i E6i+G6i A6s G6s F6s E6s",T);
            addNotes("C4i C5i E4i E5i",B);
            
// Measure 163
            
            addNotes("B5q+D6q A5i+C6i B5i+D6i",T);
            addNote("G4q",A,B);
            addRest("q",B);
            
// Measure 164
            
            addNotes("C6i+E6i D6i+E6i F6s E6s D6s C6s",T);
            addNotes("A3i A4i C4i C5i",B);
            
            thirdVoiceTime = trebleTime;
            
// Measure 165
            
            addNotes("G#5i E5i A5i+C6i B5i+D6i",T);
            addNote("E4q",A,B);
            addRest("q",B);
            addNote("B5q",A,T,T);
            
// Measure 166
            
            addNotes("C6i+E6i C6i+E6i F6s E6s D6s C6s",T);
            addNotes("A3i A4i C4i C5i",B);
            
// Measure 167
            
            addNotes("G#5q+B5q B5s A5s G5s A5s",T);
            addNote("E4q",A,B);
            addRest("q",B);
            
// Measure 168
            
            addNote("C6i",A,T);
            addRest("i",T);
            addNotes("D6s C6s B5s C6s",T);
            addNotes("A4i C5i+E5i C5i+E5i C5i+E5i",B);

// Measure 169
            
            addNote("E6i",A,T);
            addRest("i",T);
            addNotes("F6s E6s D#6s E6s",T);
            addNotes("A4i C5i+E5i C5i+E5i C5i+E5i",B);

// Measure 170
            
            addNotes("B6s A6s G#6s A6s B6s A6s G6s A6s",T);
            addNotes("A4i C5i+E5i A4i C5i+E5i",B);

// Measure 171
            
            addNotes("C7q A6i B6i",T);
            addNotes("F4i A4i+D#5i A4i+D#5i A4i+D#5i",B);

// Measure 172
            
            addNotes("C7i B6i A6i G#6i",T);
            addNotes("E4i A4i+E5i Dn4i F4i+B4i",B);

// Measure 173
            
            addNotes("A6i E6i F6i D6i",T);
            addNotes("C4i E4i+A4i D4i F4i+B4i",B);

// Measure 174
            
            addNotes("C6q B5i. A5t B5t",T);
            addNotes("E4i+A4i E4i+A4i E4i+G#4i E4i+G#4i",B);

// Measure 175
            
            addNote("A5q",A,T);
            addNotes("A3q+A4q",B);
            
// Measure 176
            
            addNotes("C6i+E6i Dn6i+Fn6i",T);
            addRest("q",B);
            
// Measure 177
            
            addNotes("E6i+G6i E6i+G6i A6s G6s F6s E6s",T);
            addNotes("C4i C5i E4i E5i",B);

            thirdVoiceTime = trebleTime;
            
// Measure 178
            
            addNotes("B5i G5i C6i+E6i Dn6i+Fn6i",T);
            addNote("G4q",A,B);
            addRest("q",B);
            addNote("D6q",A,T,T);
            
// Measure 179
            
            addNotes("E6i+G6i E6i+G6i A6s G6s F6s E6s",T);
            addNotes("C4i C5i E4i E5i",B);
            
// Measure 180
            
            addNotes("B5q+D6q A5i+C6i B5i+D6i",T);
            addNote("G4q",A,B);
            addRest("q",B);
            
// Measure 181
            
            addNotes("C6i+E6i D6i+E6i F6s E6s D6s C6s",T);
            addNotes("A3i A4i C4i C5i",B);
            
            thirdVoiceTime = trebleTime;
            
// Measure 182
            
            addNotes("G#5i E5i A5i+C6i B5i+D6i",T);
            addNote("E4q",A,B);
            addRest("q",B);
            addNote("B5q",A,T,T);
            
// Measure 183
            
            addNotes("C6i+E6i C6i+E6i F6s E6s D6s C6s",T);
            addNotes("A3i A4i C4i C5i",B);
            
// Measure 184
            
            addNotes("G#5q+B5q B5s A5s G5s A5s",T);
            addNote("E4q",A,B);
            addRest("q",B);
            
// Measure 185
            
            addNote("C6i",A,T);
            addRest("i",T);
            addNotes("D6s C6s B5s C6s",T);
            addNotes("A4i C5i+E5i C5i+E5i C5i+E5i",B);

// Measure 186
            
            addNote("E6i",A,T);
            addRest("i",T);
            addNotes("F6s E6s D#6s E6s",T);
            addNotes("A4i C5i+E5i C5i+E5i C5i+E5i",B);

// Measure 187
            
            addNotes("B6s A6s G#6s A6s B6s A6s G6s A6s",T);
            addNotes("A4i C5i+E5i A4i C5i+E5i",B);

// Measure 188
            
            addNotes("C7q A6i B6i",T);
            addNotes("F4i A4i+D#5i A4i+D#5i A4i+D#5i",B);

// Measure 189
            
            addNotes("C7i B6i A6i G#6i",T);
            addNotes("E4i A4i+E5i Dn4i F4i+B4i",B);

// Measure 190
            
            addNotes("A6i E6i F6i D6i",T);
            addNotes("C4i E4i+A4i D4i F4i+B4i",B);

// Measure 191
            
            addNotes("C6q B5i. A5t B5t",T);
            addNotes("E4i+A4i E4i+A4i E4i+G#4i E4i+G#4i",B);

// Measure 192
            
            addNote("A5q",A,T);
            addNotes("A3q+A4q",B);
            
            key = "A";
            
// Measure 193
            
            addNotes("A5s A6s B5s B6s",T);
            addRest("q",B);

// Measure 194
            
            addNotes("C6s C7s",T);
            addRest("i",T);
            addNotes("A5s A6s B5s B6s",T);
            addNotes("A4i A4i A4i A4i",B);
  
// Measure 195
            
            addNotes("C6s C7s B5s B6s A5s A6s G5s G6s",T);
            addNotes("A4i A4i A4i A4i",B);

// Measure 196
            
            addNotes("F5s F6s G5s G6s A5s A6s B5s B6s",T);
            addNotes("D4i D4i D#4i D#4i",B);

// Measure 197
            
            addNotes("G5s G6s E5s E6s A5s A6s B5s B6s",T);
            addNotes("E4i E4i E4i E4i",B);

// Measure 198
            
            addNotes("C6s C7s",T);
            addRest("i",T);
            addNotes("A5s A6s B5s B6s",T);
            addNotes("A4i A4i A4i A4i",B);
 
// Measure 199
            
            addNotes("C6s C7s B5s B6s A5s A6s G5s G6s",T);
            addNotes("A4i A4i A4i A4i",B);

// Measure 200
            
            addNotes("F5s F6s B5s B6s G5s G6s E5s E6s",T);
            addNotes("D4i D4i E4i E4i",B);

// Measure 201
            
            addNotes("A5q+A6q",T);
            addNote("A3q",A,B);
            
// Measure 202
            
            addNotes("A5s A6s B5s B6s",T);
            addRest("q",B);

// Measure 203
            
            addNotes("C6s C7s",T);
            addRest("i",T);
            addNotes("A5s A6s B5s B6s",T);
            addNotes("A4i A4i A4i A4i",B);
  
// Measure 204
            
            addNotes("C6s C7s B5s B6s A5s A6s G5s G6s",T);
            addNotes("A4i A4i A4i A4i",B);

// Measure 205
            
            addNotes("F5s F6s G5s G6s A5s A6s B5s B6s",T);
            addNotes("D4i D4i D#4i D#4i",B);

// Measure 206
            
            addNotes("G5s G6s E5s E6s A5s A6s B5s B6s",T);
            addNotes("E4i E4i E4i E4i",B);

// Measure 207
            
            addNotes("C6s C7s",T);
            addRest("i",T);
            addNotes("A5s A6s B5s B6s",T);
            addNotes("A4i A4i A4i A4i",B);
 
// Measure 208
            
            addNotes("C6s C7s B5s B6s A5s A6s G5s G6s",T);
            addNotes("A4i A4i A4i A4i",B);

// Measure 209
            
            addNotes("F5s F6s B5s B6s G5s G6s E5s E6s",T);
            addNotes("D4i D4i E4i E4i",B);
            
            thirdVoiceTime = trebleTime;
            
// Measure 210
            
            addNotes("A5q+A6q C7i. C7s",T);
            addNotes("A4i A4i A4i A4i",B);
            addRest("q",T,T);
            addNote("C6q",A,T,T);

            thirdVoiceTime = trebleTime;
            
// Measure 211
            
            addNotes("C6q+E6q+A6q",T);
            addRest("q",T);
            addNotes("A4i A4i A4i A4i",B);
            addNote("C7h",A,T,T);

            thirdVoiceTime = trebleTime;
            
// Measure 212
            
            addNotes("C6q+E6q+A6q",T);
            addRest("q",T);
            addNotes("A4i A4i A4i A4i",B);
            addNote("C7h",A,T,T);

// Measure 213
            
            addNotes("D7s C7s B6s C7s D7s C7s B6s C7s",T);
            addNotes("A4i A4i A4i A4i",B);

// Measure 214
            
            addNotes("F6h+A6h+D7h",T);
            addNotes("D4i D4i D4i D4i",B);

// Measure 215
            
            addNotes("E6i+A6i+C7i E6i+A6i+C7i E6i+A6i+C7i E6i+A6i+C7i",T);
            addNotes("A4i A4i A4i A4i",B);
            
            thirdVoiceTime = trebleTime;
            
// Measure 216
            
            addNotes("B6q. E7i",T);
            addNotes("E4i E4i E4i E4i",B);
            addNotes("E6h+B6h",T,T);

            thirdVoiceTime = trebleTime;
            
// Measure 217
            
            addNotes("C6q+E6q+A6q",T);
            addRest("q",T);
            addNotes("A4i A4i A4i A4i",B);
            addNote("C7h",A,T,T);
			  
            thirdVoiceTime = trebleTime;
            
// Measure 218
            
            addNotes("C6q+E6q+A6q",T);
            addRest("q",T);
            addNotes("A4i A4i A4i A4i",B);
            addNote("C7h",A,T,T);
            
// Measure 219
            
            addNotes("D7s C7s B6s C7s D7s C7s B6s C7s",T);
            addNotes("A4i A4i A4i A4i",B);
            
// Measure 220
            
            addNotes("F6h+A6h+D7h",T);
            addNotes("D4i D4i D4i D4i",B);
            
// Measure 221
            
            addNotes("E6h+A6h+C7h",T);
            addNotes("A4i A4i A4i A4i",B);
            
// Measure 222
            
            addNotes("E6i+G6i+B6i E6i+G6i+B6i E6i+G6i+B6i E6i+G6i+B6i",T);
            addNotes("E4i E4i E4i E4i",B);
            
// Measure 223
            
            addNotes("A6q C7i. C7s",T);
            addNotes("A4s E5s C5s E5s A4s E5s C5s E5s",B);
            
// Measure 224
            
            addNote("C7h",A,T);
            addNotes("A4s E5s C5s E5s A4s E5s C5s E5s",B);
            
// Measure 225
            
            addNote("C7h",A,T);
            addNotes("A4s E5s C5s E5s A4s E5s C5s E5s",B);
            
// Measure 226
            
            addNotes("D7s C7s B6s C7s D7s C7s B6s C7s",T);
            addNotes("A4s E5s C5s E5s A4s E5s C5s E5s",B);
            
// Measure 227
            
            addNote("D7h",A,T);
            addNotes("A4s F5s D5s F5s A4s F5s D5s F5s",B);
            
// Measure 228
            
            addNotes("C7i C7i C7i C7i",T);
            addNotes("A4s E5s C5s E5s A4s E5s C5s E5s",B);
            
// Measure 229
            
            addNotes("B6q E7i",T);
            addRest("q",T);
            addNotes("E4s E5s G4s E5s E4s E5s G4s E5s",B);
            
            thirdVoiceTime = trebleTime;
            
// Measure 230
            
            addNotes("C6q+E6q+A6q",T);
            addNotes("A4i A4i A4i A4i",B);
            addNote("C7h",A,T,T);
            


            



			System.out.println(trebleTime);
			System.out.println(bassTime);
            
            

            
            
            
            
			
			
			
			
			player.setSequence(sequence);
			
			player.start();	
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}

	public static void playFur_Elise(CreatingMusic creatingMusic) {
		try {
	        player = MidiSystem.getSequencer();
				
			player.open();
			
			factor = 300;
				
			player.setTempoInBPM(120*300);

			player.addMetaEventListener(new MyMetaEventListener());

			Sequence sequence = new Sequence(Sequence.PPQ,4);
				
			track = sequence.createTrack();
			
			key = "C";
			
// Measure 1
			
			measure(1);
			
			addNotes("E6s D#6s",T);
			addRest("s",2,B);
			
// Measure 2
			
			measure(2);
			
			addNotes("E6s D#6s E6s B5s Dn6s C6s",T);
			addRest("s",6,B);
			
// Measure 3
			
			measure(3);
			
			addNote("A5i",A,T);
			addRest("s",T);
			addNotes("C5s E5s A5s",T);
			addNotes("A3s E4s A4s",B);
			addRest("s",B);
			addRest("i",B);
			
// Measure 4
			
			measure(4);
			
			addNote("B5i",A,T);
			addRest("s",T);
			addNotes("E5s G#5s B5s",T);
			addNotes("E3s E4s G#4s",B);
			addRest("s",B);
			addRest("i",B);


// Measure 5
			
			measure(5);
			
			addNote("C6i",A,T);
			addRest("s",T);
			addNotes("E5s E6s D#6s",T);
			addNotes("A3s E4s A4s",B);
			addRest("s",B);
			addRest("i",B);
			
// Measure 6
			
			measure(6);
			
			addNotes("E6s D#6s E6s B5s Dn6s C6s",T);
			addRest("s",6,B);
			
// Measure 7
			
			measure(7);
			
			addNote("A5i",A,T);
			addRest("s",T);
			addNotes("C5s E5s A5s",T);
			addNotes("A3s E4s A4s",B);
			addRest("s",B);
			addRest("i",B);
		
// Measure 8
			
			measure(8);
			
			addNote("B5i",A,T);
			addRest("s",T);
			addNotes("E5s C6s B5s",T);
			addNotes("E3s E4s G#4s",B);
			addRest("s",B);
			addRest("i",B);
		
// Measure 9
			
			measure(9);
			
			addNote("A5q",A,T);
			addNotes("A3s E4s A4s",B);
			addRest("s",B);

// Measure 10
			
			measure(10);
						
			addNotes("E6s D#6s",T);
			addRest("s",2,B);
			
// Measure 11
			
			measure(11);
						
		    addNotes("E6s D#6s E6s B5s Dn6s C6s",T);
			addRest("s",6,B);
			
// Measure 12
			
			measure(12);
						
		    addNote("A5i",A,T);
			addRest("s",T);
			addNotes("C5s E5s A5s",T);
			addNotes("A3s E4s A4s",B);
			addRest("s",B);
			addRest("i",B);
			
// Measure 13
			
			measure(13);
						
			addNote("B5i",A,T);
			addRest("s",T);
			addNotes("E5s G#5s B5s",T);
			addNotes("E3s E4s G#4s",B);
			addRest("s",B);
			addRest("i",B);

// Measure 14
			
			measure(14);
						
		    addNote("C6i",A,T);
			addRest("s",T);
			addNotes("E5s E6s D#6s",T);
			addNotes("A3s E4s A4s",B);
			addRest("s",B);
			addRest("i",B);
			
// Measure 15
			
			measure(15);
						
		    addNotes("E6s D#6s E6s B5s Dn6s C6s",T);
			addRest("s",6,B);
			
// Measure 16
			
			measure(16);
						
			addNote("A5i",A,T);
			addRest("s",T);
			addNotes("C5s E5s A5s",T);
			addNotes("A3s E4s A4s",B);
			addRest("s",B);
			addRest("i",B);
			
// Measure 17
			
			measure(17);
						
			addNote("B5i",A,T);
			addRest("s",T);
			addNotes("E5s C6s B5s",T);
			addNotes("E3s E4s G#4s",B);
			addRest("s",B);
			addRest("i",B);
								
// Measure 18
			
			measure(18);
			
			addNote("A5i",A,T);
			addRest("s",T);
			addNotes("B5s C6s D6s",T);
			addNotes("A3s E4s A4s",B);
			addRest("s",B);
			addRest("i",B);
	        
// Measure 19
			
			measure(19);
			
			addNotes("E6i. G5s F6s E6s",T);
			addNotes("C4s G4s C5s",B);
			addRest("s",B);
			addRest("i",B);

// Measure 20
			
			measure(20);
			
			addNotes("D6i. F5s E6s D6s",T);
			addNotes("G3s G4s B4s",B);
			addRest("s",B);
			addRest("i",B);
			
// Measure 21
			
			measure(21);
			
			addNotes("C6i. E5s D6s C6s",T);
			addNotes("A3s E4s A4s",B);
			addRest("s",B);
			addRest("i",B);
			
// Measure 22
			
			measure(22);
			
			addNote("B5i",A,T);
			addRest("s",T);
			addNotes("E5s E6s",T);
			addRest("s",T);
			addNotes("E3s E4s E5s",B);
			addRest("s",2,B);
			addNote("E5s",A,B);
			
// Measure 23
			
			measure(23);
			
			addRest("s",T);
			addNotes("E6s E7s",T);
			addRest("s",2,T);
			addNote("D#6s",A,T);
			addNote("E6s",A,B);
			addRest("s",2,B);
			addNotes("D#6s E6s",B);
			addRest("s",B);
			
// Measure 24
			
			measure(24);
			
			addNote("E6i",A,T);
			addRest("s",T);
			addNotes("D#6s E6s D6s",T);
			addRest("s",B);
			addNotes("D#6s E6s",B);
			addRest("s",B);
			addRest("i",B);
			
// Measure 25
			
			measure(25);
			
			addNotes("E6s D#6s E6s B5s Dn6s C6s",T);
			addRest("s",6,B);
			
// Measure 26
			
			measure(26);
			
			addNote("A5i",A,T);
			addRest("s",T);
			addNotes("C5s E5s A5s",T);
			addNotes("A3s E4s A4s",B);
			addRest("s",B);
			addRest("i",B);
			
// Measure 27
			
			measure(27);
			
			addNote("B5i",A,T);
			addRest("s",T);
			addNotes("E5s G#5s B5s",T);
			addNotes("E3s E4s G#4s",B);
			addRest("s",B);
			addRest("i",B);
			
// Measure 28
			
			measure(28);
			
			addNote("C6i",A,T);
			addRest("s",T);
			addNotes("E5s E6s D#6s",T);
			addNotes("A3s E4s A4s",B);
			addRest("s",B);
			addRest("i",B);
			
// Measure 29
			
			measure(29);
			
			addNotes("E6s D#6s E6s B5s Dn6s C6s",T);
			addRest("s",6,B);
			
// Measure 30
			
			measure(30);
			
			addNote("A5i",A,T);
			addRest("s",T);
			addNotes("C5s E5s A5s",T);
			addNotes("A3s E4s A4s",B);
			addRest("s",B);
			addRest("i",B);
			
// Measure 31
			
			measure(31);
			
			addNote("B5i",A,T);
			addRest("s",T);
			addNotes("E5s C6s B5s",T);
			addNotes("E3s E4s G#4s",B);
			addRest("s",B);
			addRest("i",B);
			
// Measure 32
			
			measure(32);
			
			addNote("A5i",A,T);
			addRest("s",T);
			addNotes("B5s C6s D6s",T);
			addNotes("A3s E4s A4s",B);
			addRest("s",B);
			addRest("i",B);
			
// Measure 33
			
			measure(33);
			
			addNotes("E6i. G5s F6s E6s",T);
			addNotes("C4s G4s C5s",B);
			addRest("s",B);
			addRest("i",B);

// Measure 34
			
			measure(34);
						
			addNotes("D6i. F5s E6s D6s",T);
			addNotes("G3s G4s B4s",B);
			addRest("s",B);
			addRest("i",B);
						
// Measure 35
			
			measure(35);
						
			addNotes("C6i. E5s D6s C6s",T);
			addNotes("A3s E4s A4s",B);
			addRest("s",B);
			addRest("i",B);
						
// Measure 36
			
			measure(36);
						
			addNote("B5i",A,T);
			addRest("s",T);
			addNotes("E5s E6s",T);
			addRest("s",T);
			addNotes("E3s E4s E5s",B);
			addRest("s",2,B);
			addNote("E5s",A,B);
						
// Measure 37
			
			measure(37);
						
			addRest("s",T);
			addNotes("E6s E7s",T);
			addRest("s",2,T);
			addNote("D#6s",A,T);
			addNote("E6s",A,B);
			addRest("s",2,B);
			addNotes("D#6s E6s",B);
			addRest("s",B);
						
// Measure 38
			
			measure(38);
						
			addNote("E6i",A,T);
			addRest("s",T);
			addNotes("D#6s E6s D6s",T);
			addRest("s",B);
			addNotes("D#6s E6s",B);
			addRest("s",B);
			addRest("i",B);
						
// Measure 39
			
			measure(39);
						
			addNotes("E6s D#6s E6s B5s Dn6s C6s",T);
			addRest("s",6,B);
						
// Measure 40
			
			measure(40);
						
			addNote("A5i",A,T);
			addRest("s",T);
			addNotes("C5s E5s A5s",T);
			addNotes("A3s E4s A4s",B);
			addRest("s",B);
			addRest("i",B);
						
// Measure 41
			
			measure(41);
						
			addNote("B5i",A,T);
			addRest("s",T);
			addNotes("E5s G#5s B5s",T);
			addNotes("E3s E4s G#4s",B);
			addRest("s",B);
			addRest("i",B);
						
// Measure 42
			
			measure(42);
						
			addNote("C6i",A,T);
			addRest("s",T);
			addNotes("E5s E6s D#6s",T);
			addNotes("A3s E4s A4s",B);
			addRest("s",B);
			addRest("i",B);
						
// Measure 43
			
			measure(43);
						
			addNotes("E6s D#6s E6s B5s Dn6s C6s",T);
			addRest("s",6,B);
						
// Measure 44
			
			measure(44);
						
			addNote("A5i",A,T);
			addRest("s",T);
			addNotes("C5s E5s A5s",T);
			addNotes("A3s E4s A4s",B);
			addRest("s",B);
			addRest("i",B);
						
// Measure 45
			
			measure(45);
						
			addNote("B5i",A,T);
			addRest("s",T);
			addNotes("E5s C6s B5s",T);
			addNotes("E3s E4s G#4s",B);
			addRest("s",B);
			addRest("i",B);
			
// Measure 46
			
			measure(46);
			
			addNote("A5i",A,T);
			addRest("s",T);
			addNotes("E5s+C6s F5s+C6s E5s+G5s+C6s",T);
			addNotes("A3s E4s A4s Bb4s+C5s A4s+C5s G4s+B4s+C5s",B);
			
// Measure 47
			
			measure(47);
			
			addNotes("C6q F6s. E6t",T);
			addNotes("F4s A4s C5s A4s C5s A4s",B);

// Measure 48
			
			measure(48);
			
			addNotes("E6i D6i Bb6s. A6t",T);
			addNotes("F4s Bb4s D5s Bb4s D5s B4s",B);

// Measure 49
			
			measure(49);
			
			addNotes("A6s G6s F6s E6s D6s C6s",T);
			addNotes("F4s E5s F4s+G4s+Bb4s E5s F4s+G4s+Bb4s E5s",B);

// Measure 50
			
			measure(50);
			
			addNotes("Bb5i A5i A5t G5t A5t B5t",T);
			addNotes("F4s A4s C5s A4s C5s A4s",B);

// Measure 51
			
			measure(51);
			
			addNotes("C6q D6s D#6s",T);
			addNotes("F4s A4s C5s A4s C5s A4s",B);

// Measure 52
			
			measure(52);
			
			addNotes("E6i. E6s F6s A5s",T);
			addNotes("E4s A4s C5s A4s D4s+D5s F4s",B);

// Measure 53
			
			measure(53);
			
			addNotes("C6q D6s. B5t",T);
			addNotes("G4s E5s G4s E5s G4s F4s",B);

// Measure 54
			
			measure(54);
			
			addNotes("C6t G6t G5t G6t A5t G6t B5t G6t C6t G6t D6t G6t",T);
			addNotes("C5i+E5i",B);
			addRest("s",B);
			addNotes("F5s+G5s E5s+G5s D5s+F5s+G5s",B);

// Measure 55
			
			measure(55);
			
			addNotes("E6t G6t C7t B6t A6t G6t F6t E6t D6t G6t F6t D6t",T);
			addNotes("C5i+E5i+G5i F4i+A4i G4i+B4i",B);

// Measure 56
			
			measure(56);
			
			addNotes("C6t G6t G5t G6t A5t G6t B5t G6t C6t G6t D6t G6t",T);
			addNote("C5i",A,B);
			addRest("s",B);
			addNotes("F5s+G5s E5s+G5s D5s+F5s+G5s",B);

// Measure 57
			
			measure(57);
			
			addNotes("E6t G6t C7t B6t A6t G6t F6t E6t D6t G6t F6t D6t",T);
			addNotes("C5i+E5i+G5i",B);
			addNotes("F4i+A4i G4i+B4i",B);

// Measure 58
			
			measure(58);
			
			addNotes("E6t F6t E6t D#6t E6t B5t E6t D6t E6t B5t E6t D6t",T);
			addNotes("G#4i+B4i",B);
			addRest("i",2,B);

// Measure 59
			
			measure(59);
			
			addNotes("E6i. B5s E6s D#6s",T);
			addRest("i",B);
			addRest("s",4,B);

// Measure 60
			
			measure(60);
			
			addNotes("E6i. B5s E6s D#6s",T);
			addRest("i",B);
			addRest("s",4,B);

// Measure 61
			
			measure(61);
			
			addNotes("E6s D#6s E6s B5s Dn6s C6s",T);
			addRest("s",6,B);

// Measure 62
			
			measure(62);
			
			addNote("A5i",A,T);
			addRest("s",T);
			addNotes("C5s E5s A5s",T);
			addNotes("A3s E4s A4s",B);
			addRest("s",B);
			addRest("i",B);

// Measure 63
			
			measure(63);
			
			addNote("B5i",A,T);
			addRest("s",T);
			addNotes("E5s G#5s B5s",T);
			addNotes("E3s E4s G#4s",B);
			addRest("s",B);
			addRest("i",B);

// Measure 64
			
			measure(64);
			
			addNote("C6i",A,T);
			addRest("s",T);
			addNotes("E5s E6s D#6s",T);
			addNotes("A3s E4s A4s",B);
			addRest("s",B);
			addRest("i",B);

// Measure 65
			
			measure(65);
			
			addNotes("E6s D#6s E6s B5s Dn6s C6s",T);
			addRest("s",6,B);

// Measure 66
			
			measure(66);
			
			addNote("A5i",A,T);
			addRest("s",T);
			addNotes("C5s E5s A5s",T);
			addNotes("A3s E4s A4s",B);
			addRest("s",B);
			addRest("i",B);

// Measure 67
			
			measure(67);
			
			addNote("B5i",A,T);
			addRest("s",T);
			addNotes("E5s C6s B5s",T);
			addNotes("E3s E4s G#4s",B);
			addRest("s",B);
			addRest("i",B);

// Measure 68
			
			measure(68);
			
			addNote("A5i",A,T);
			addRest("s",T);
			addNotes("B5s C6s D6s",T);
			addNotes("A3s E4s A4s",B);
			addRest("s",B);
			addRest("i",B);

// Measure 69
			
			measure(69);
			
			addNotes("E6i. G5s F6s E6s",T);
			addNotes("E4s G4s C5s",B);
			addRest("s",B);
			addRest("i",B);

// Measure 70
			
			measure(70);
			
			addNotes("D6i. F5s E6s D6s",T);
			addNotes("G3s G4s B4s",B);
			addRest("s",B);
			addRest("i",B);

// Measure 71
			
			measure(71);
			
			addNotes("C6i. E5s D6s C6s",T);
			addNotes("A3s E4s A4s",B);
			addRest("s",B);
			addRest("i",B);

// Measure 72
			
			measure(72);
			
			addNote("B5i",A,T);
			addRest("s",T);
			addNotes("E5s E6s",T);
			addRest("s",T);
			addNotes("E3s E4s E5s",B);
			addRest("s",2,B);
			addNote("E5s",A,B);

// Measure 73
			
			measure(73);
			
			addRest("s",T);
			addNotes("E6s E7s",T);
			addRest("s",2,T);
			addNote("D#6s",A,T);
			addNote("E6s",A,B);
			addRest("s",2,B);
			addNotes("D#6s E6s",B);
			addRest("s",B);

// Measure 74
			
			measure(74);
			
			addNote("E6s",A,T);
			addRest("s",2,T);
			addNotes("D#6s E6s D6s",T);
			addRest("s",B);
			addNotes("D#6s E6s",B);
			addRest("s",B);
			addRest("i",B);

// Measure 75
			
			measure(75);
			
			addNotes("E6s D#6s E6s B5s Dn6s C6s",T);
			addRest("s",6,B);

// Measure 76
			
			measure(76);
			
			addNote("A5i",A,T);
			addRest("s",T);
			addNotes("C5s E5s C6s",T);
			addNotes("A3s E4s A4s",B);
			addRest("s",B);
			addRest("i",B);

// Measure 77
			
			measure(77);
			
			addNote("B5i",A,T);
			addRest("s",T);
			addNotes("E5s G#5s B5s",T);
			addNotes("E3s E4s G#4s",B);
			addRest("s",B);
			addRest("i",B);

// Measure 78
			
			measure(78);
			
			addNote("C6i",A,T);
			addRest("s",T);
			addNotes("E5s E6s D#6s",T);
			addNotes("A3s E4s A4s",B);
			addRest("s",B);
			addRest("i",B);

// Measure 79
			
			measure(79);
			
			addNotes("E6s D#6s E6s B5s Dn6s C6s",T);
			addRest("s",6,B);

// Measure 80
			
			measure(80);
			
			addNote("A5i",A,T);
			addRest("s",T);
			addNotes("C5s E5s A5s",T);
			addNotes("A3s E4s A4s",B);
			addRest("s",B);
			addRest("i",B);

// Measure 81
			
			measure(81);
			
			addNote("B5i",A,T);
			addRest("s",T);
			addNotes("E5s C6s B5s",T);
			addNotes("E3s E4s G#4s",B);
			addRest("s",B);
			addRest("i",B);

// Measure 82
			
			measure(82);
			
			addNote("A5i",A,T);
			addRest("i",2,T);
			addNotes("A3s A3s A3s A3s A3s A3s",B);

// Measure 83
			
			measure(83);
			
			addNotes("E5q.+G5q.+Bb5q.+D#6q.",T);
			addNotes("A3s A3s A3s A3s A3s A3s",B);

// Measure 84
			
			measure(84);
			
			addNotes("F5q+A5s+D6q C#6s+E6s D6s+F6s",T);
			addNotes("A3s A3s A3s A3s A3s A3s",B);
			
// Measure 85
			
			measure(85);
			
			addNotes("G#5q+D6q+F6q D5i+D6i+F6i",T);
			addNotes("A3s A3s A3s A3s A3s A3s",B);
			
// Measure 86
			
			measure(86);
			
			addNotes("A5q.+Cn6q.+E6q.",T);
			addNotes("A3s A3s A3s A3s A3s A3s",B);

// Measure 87
			
			measure(87);
			
			addNotes("F5q+D6q E5s+C6s D5s+B5s",T);
			addNotes("D3s+A3s D3s+A3s D3s+A3s D3s+A3s D3s+A3s D3s+A3s",B);

// Measure 88
			
			measure(88);
			
			addNotes("C5q+F#5q+A5q C5i+A5i",T);
			addNotes("D#3s+A3s D#3s+A3s D#3s+A3s D#3s+A3s D#3s+A3s D#3s+A3s",B);

// Measure 89
			
			measure(89);
			
			addNotes("C5i+A5i E5i+C6i D5i+B5i",T);
			addNotes("E3s+A3s E3s+A3s E3s+A3s E3s+A3s E3s+G#3s E3s+G#3s",B);

// Measure 90
			
			measure(90);
			
			addNotes("C5q.+A5q.",T);
			addNotes("A2s+A3s A3s A3s A3s A3s A3s",B);

// Measure 91
			
			measure(91);
			
			addNotes("E5q.+G5q.+Bb5q.+C#6q.",T);
			addNotes("A3s A3s A3s A3s A3s A3s",B);

// Measure 92
			
			measure(92);
			
			addNotes("F5q+A5q+D6q C#6s+E6s D6s+F6s",T);
			addNotes("A3s A3s A3s A3s A3s A3s",B);

// Measure 93
			
			measure(93);
			
			addNotes("D6q+F6q D6i+F6i",T);
			addNotes("A3s A3s A3s A3s A3s A3s",B);

// Measure 94
			
			measure(94);
			
			addNotes("D6q.+F6q.",T);
			addNotes("Bb3s Bb3s Bb3s Bb3s Bb3s Bb3s",B);

// Measure 95
			
			measure(95);
			
			addNotes("G5q+Eb6q F5s+D6s Eb5s+C6s",T);
			addNotes("Bb3s Bb3s Bb3s Bb3s Bb3s Bb3s",B);

// Measure 96
			
			measure(96);
			
			addNotes("D5q+F5q+B5q D5i+F5i+A5i",T);
			addNotes("Bb3s Bb3s Bb3s Bb3s Bb3s Bb3s",B);

// Measure 97
			
			measure(97);
			
			addNotes("D5q+F#5q+G5q D5i+F5i+G5i",T);
			addNotes("Bn3s Bn3s Bn3s Bn3s Bn3s Bn3s",B);

// Measure 98
			
			measure(98);
			
			addNotes("C5q+En5q+A5q",T);
			addRest("i",T);
			addNote("C4q",A,B);
			addRest("i",B);

// Measure 99
			
			measure(99);
			
			addNotes("F5i+B5i",T);
			addRest("i",2,T);
			addNotes("C4i+G#4i",B);
			addRest("i",2,B);

// Measure 100
			
			measure(100);
			
			tupletFactor = 2;
			
			addNotes("A4s C5s F5s",T);
			addNotes("A5s C6s E6s",T);
			addNotes("D6s C6s B5s",T);
			
			tupletFactor = 1;
			
			addNote("A2i",A,B);
			addRest("i",B);
			addNotes("A4i+C5i+E5i",B);

// Measure 101
			
			measure(101);
			
			tupletFactor = 2;
			
			addNotes("A5s C6s E6s",T);
			addNotes("A6s C7s E7s",T);
			addNotes("D7s C7s B6s",T);
			
			tupletFactor = 1;
			
			addNotes("A4i+C5i+E5i",B);
			addRest("i",B);
			addNotes("A4i+C5i+E5i",B);
			
// Measure 102
			
			measure(102);
			
			tupletFactor = 2;
			
			addNotes("A5s C6s E6s",T);
			addNotes("A6s C7s E7s",T);
			addNotes("D7s C7s B6s",T);
			
			tupletFactor = 1;
			
			addNotes("A4i+C5i+E5i",B);
			addRest("i",B);
			addNotes("A4i+C5i+E5i",B);
			
// Measure 103
			
			measure(103);
			
			tupletFactor = 2;
			
			addNotes("Bb6s A6s G#6s",T);
			addNotes("Gn6s F#7s Fn7s",T);
			addNotes("E7s D#7s Dn7s",T);
			
			tupletFactor = 1;
			
			addNotes("A4i+C5i+E5i",B);
			addRest("i",2,B);
			
// Measure 104
			
			measure(104);
			
			addNotes("C#7s Cn7s B6s Bb6s A6s G#6s Gn6s F#6s Fn6s",T);
			addRest("s",9,B);
			
// Measure 105
			
			measure(105);
			
			addNotes("E6s D#6s E6s B5s Dn6s C6s",T);
			addRest("s",6,B);
			
// Measure 106
			
			measure(106);
			
			addNote("A5i",A,T);
			addRest("s",T);
			addNotes("C5s E5s A5s",T);
			addNotes("A3s E4s A4s",B);
			addRest("s",B);
			addRest("i",B);
			
// Measure 107
			
			measure(107);
			
			addNote("B5i",A,T);
			addRest("s",T);
			addNotes("E5s G#5s B5s",T);
			addNotes("E3s E4s G#4s",B);
			addRest("s",B);
			addRest("i",B);
			
// Measure 108
			
			measure(108);
			
			addNote("C6i",A,T);
			addRest("s",T);
			addNotes("E5s E6s D#6s",T);
			addNotes("A3s E4s A4s",B);
			addRest("s",B);
			addRest("i",B);
			
// Measure 109
			
			measure(109);
			
			addNotes("E6s D#6s E6s B5s Dn6s C6s",T);
			addRest("s",6,B);
			
// Measure 110
			
		    measure(110);	
			
			addNote("A5i",A,T);
			addRest("s",T);
			addNotes("C5s E5s A5s",T);
			addNotes("A3s E4s A4s",B);
			addRest("s",B);
			addRest("i",B);
			
// Measure 111
			
			measure(111);
			
			addNote("B5i",A,T);
			addRest("s",T);
			addNotes("E5s C6s B5s",T);
			addNotes("E3s E4s G#4s",B);
			addRest("s",B);
			addRest("i",B);
			
// Measure 112
			
			measure(112);
			
			addNote("A5i",A,T);
			addRest("s",T);
			addNotes("B5s C6s D6s",T);
			addNotes("A3s E4s A4s",B);
			addRest("s",B);
			addRest("i",B);
			
// Measure 113
			
			measure(113);
			
			addNotes("E6i. G5s F6s E6s",T);
			addNotes("C4s G4s C5s",B);
			addRest("s",B);
			addRest("i",B);
			
// Measure 114
			
			measure(114);
			
			addNotes("D6i. F5s E6s D6s",T);
			addNotes("G3s G4s B4s",B);
			addRest("s",B);
			addRest("i",B);
			
// Measure 115
			
			measure(115);
			
			addNotes("C6i. E5s D6s C6s",T);
			addNotes("A3s E4s A4s",B);
			addRest("s",B);
			addRest("i",B);
			
// Measure 116
			
			measure(116);
			
			addNote("B5i",A,T);
			addRest("s",T);
			addNotes("E5s E6s",T);
			addRest("s",T);
			addNotes("E3s E4s E5s",B);
			addRest("s",2,B);
			addNote("E5s",A,B);

// Measure 117
			
			measure(117);
			
			addRest("s",T);
			addNotes("E6s E7s",T);
			addRest("s",2,T);
			addNote("D#6s",A,T);
			addNote("E6s",A,B);
			addRest("s",2,B);
			addNotes("D#6s E6s",B);
			addRest("s",B);

// Measure 118
			
			measure(118);
			
			addNote("E6s",A,T);
			addRest("s",2,T);
			addNotes("D#6s E6s D#6s",T);
			addRest("s",B);
			addNotes("D#6s E6s",B);
			addRest("s",B);
			addRest("i",B);

// Measure 119
			
			measure(119);
			
			addNotes("E6s D#6s E6s B5s Dn6s C6s",T);
			addRest("s",6,B);

// Measure 120
			
			measure(120);
			
			addNote("A5i",A,T);
			addRest("s",T);
			addNotes("C5s E5s A5s",T);
			addNotes("A3s E4s A4s",B);
			addRest("s",B);
			addRest("i",B);

// Measure 121
			
			measure(121);
			
			addNote("B5i",A,T);
			addRest("s",T);
			addNotes("E5s G#5s B5s",T);
			addNotes("E3s E4s G#4s",B);
			addRest("s",B);
			addRest("i",B);

// Measure 122
			
			measure(122);
			
			addNote("C6i",A,T);
			addRest("s",T);
			addNotes("E5s E6s D#6s",T);
			addNotes("A3s E4s A4s",B);
			addRest("s",B);
			addRest("i",B);

// Measure 123
			
			measure(123);
			
			addNotes("E6s D#6s E6s B5s Dn6s C6s",T);
			addRest("s",6,B);

// Measure 124
			
			measure(124);
			
			addNote("A5i",A,T);
			addRest("s",T);
			addNotes("C5s E5s A5s",T);
			addNotes("A3s E4s A4s",B);
			addRest("s",B);
			addRest("i",B);

// Measure 125
			
			measure(125);
			
			addNote("B5i",A,T);
			addRest("s",T);
			addNotes("E5s C6s B5s",T);
			addNotes("E3s E4s G#4s",B);
			addRest("s",B);
			addRest("i",B);

// Measure 126
			
			measure(126);
			
			addNote("A5i",A,T);
			addRest("i",T);
			addNotes("A2i+A3i",B);
			addRest("i",B);
						
			System.out.println(trebleTime);
			System.out.println(bassTime);
			
			
			
			
			
			player.setSequence(sequence);
			
			player.start();	
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}		
	
	public static void playPineapple_Rag(CreatingMusic creatingMusic) {
		try {
	        player = MidiSystem.getSequencer();
				
			player.open();
				
			player.setTempoInBPM(200);
				
			Sequence sequence = new Sequence(Sequence.PPQ,4);
				
			track = sequence.createTrack();
			
            key = "Bb";
            
// Measure 1
            
            addNotes("G5s F5i E5s D5s C#5s D5s Cn5s",T);
            addNotes("G4s F4i E4s D4s C#4s D4s Cn4s",B);
            
// Measure 2
            
            addNotes("B4s C5s D5s",T);
            tie(A,T,"F5s","F5q");
            addNotes("B3s C4s D4s",B);
            tie(A,B,"F4s","F4q");
            
// Measure 3
            
            addNotes("G5s G5i F#5s G5s A5s B5i",T);
            addNotes("G4s G4i F#4s G4s A4s B4i",B);
            
// Measure 4
            
            addNote("C6i",A,T);
            tie(A,T,"F5i","F5i");
            addNote("F5i",A,T);
            addNote("C5i",A,B);
            tie(A,B,"F4i","F4i");
            addNote("F4i",A,B);
            
// Measure 5
            
            addNotes("B5s+D6s+G6s F6i E6s D6s C#6s D6s Cn6s",T);
            addNotes("B3i F4i+B4i+D5i F3i F4i+B4i+D5i",B);
            
// Measure 6
            
            addNotes("B5s C6s D6s",T);
            tie(A,T,"F6s","F6s");
            addNotes("B6s F6i+D7i",T);
            addNotes("B3i F4i+B4i+D5i F3i F4i+B4i+D5i",B);
            
// Measure 7
            
            addNotes("B5s+D6s+G6s F6i E6s D6s C#6s D6s E6s",T);
            addNotes("B3i F4i+B4i+D5i F3i F4i+B4i+D5i",B);
            
// Measure 8
            
            addNotes("B5i+D6i+F6i B5s+F6s D6s B5s+F6s D6s B5i+F6i",T);
            addNotes("B3i D4i F4i Ab4i",B);
            
// Measure 9
            
            addNotes("B5s E6i+B6i B5s E6i+B6i B5s",T);
            addNotes("G3i+G4i G4i+B4i+E5i Gb3i+Gb4i G4i+B4i+E5i",B);
            
            thirdVoiceTime = trebleTime;

// Measure 10
            
            tie(D,T,"F6s","F6s");
            tie(A,T,"B6s","B6s");
            addNotes("D7s B6s D6s F6s En6s F6s G6s",T);
            addNotes("F3i+F4i F4i+B4i+D5i D3i+D4i Db3i+Db4i",B);
            
            addRest("s",4,T,T);
            addNote("B5q",A,T,T);
            
// Measure 11
            
            addNotes("A6s C6i+F6i A6s B5i+En6i+G6i B5s+E6s+A6s",T);
            addNotes("C3i+C4i A4i+C5i+F5i C3i+C4i A4i+C5i+En5i",B);

            
// Measure 12
            
            tie(D,T,"A5s","A5i");
            tie(A,T,"F6s","F6i");
            addNotes("A5s B5s C6s D6s E6s F6s",T);
            addNotes("F3i+F4i C4i A3i F3i",B);
            
// Measure 13
            
            addNotes("B5s+D6s+G6s F6i E6s D6s C#6s D6s Cn6s",T);
            addNotes("B3i F4i+B4i+D5i F3i F4i+B4i+D5i",B);
            
// Measure 14
            
            addNotes("B5s C6s D6s",T);
            tie(A,T,"F6s","F6s");
            addNotes("B6s F6i+D7i",T);
            addNotes("B3i F4i+B4i+D5i F3i F4i+B4i+D5i",B);
            
// Measure 15
            
            addNotes("B5s+D6s+G6s F6i E6s D6s C#6s D6s E6s",T);
            addNotes("B3i F4i+B4i+D5i F3i F4i+B4i+D5i",B);

            
// Measure 16
            
            addNotes("B5i+D6i+F6i B5s+F6s D6s B5s+F6s D6s B5i+F6i",T);
            addNotes("B3i D4i F4i Ab4i",B);

// Measure 17
            
            addNotes("B5s E6i+B6i B5s E6i+B6i B5s",T);
            addNotes("G3i+G4i G4i+B4i+E5i Gb3i+Gb4i Gb4i+B4i+E5i",B);

            
// Measure 18
            
            tie(D,T,"E6s","E6s");
            tie(A,T,"B6s","B6s");
            addNotes("D7s B6s F6s D6s F6s G6s B6s",T);
            addNotes("F3i+F4i F4i+B4i+D5i F3i+F4i F4i+B4i+D5i",B);
            
            thirdVoiceTime = trebleTime;
            
// Measure 19
            
            addNotes("En5i Bb5i E5i.+A5i.",T);
            addNotes("G3i+G4i Gb3i+Gb4i F3q+F4q",B);
            
            addNotes("B5q C6s D6i",T,T);
            addRest("s",T,T);
            
// Measure 20
            
            tie(D,T,"D5s","D5i");
            tie(A,T,"B5s","B5i");
            addNotes("B5s C6s D6s E6s A5i+E6i+F6i",T);
            addNotes("B3i+B4i",B);
            addRest("i",2,B);
            addNotes("F3i+F4i",B);
            


            System.out.println(trebleTime);
            System.out.println(bassTime); 

            
            
            
			

			player.setSequence(sequence);
			
			player.start();	
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}		
	
	public static void playRock_A_Bye_Baby(CreatingMusic creatingMusic) {
		try {
	        player = MidiSystem.getSequencer();
				
			player.open();
				
			player.setTempoInBPM(200);
				
			Sequence sequence = new Sequence(Sequence.PPQ,4);
				
			track = sequence.createTrack();
			
			key = "F";
			
// Measure 1
			
			addRest("q",2,T);
			addNote("A5q",A,T);
			addNotes("A4q C5q",B);
			addRest("q",B);
			
// Measure 2
			
			addNotes("G5h F5q",T);
			addRest("h",B);
			addRest("q",B);
			
// Measure 3
			
			addRest("q",2,T);
			addNote("F5q",A,T);
			addNotes("A4q C5q",B);
			addRest("q",B);
			
// Measure 4
			
			addNote("E5h.",A,T);
			addNote("C5h.",A,B);
			
// Measure 5
			
			addRest("q",2,T);
			addNote("B5q",A,T);
			addNotes("B4q D5q",B);
			addRest("q",B);
			
// Measure 6
			
			addNotes("A5h G5q",T);
			addRest("h",B);
			addRest("q",B);
			
// Measure 7
			
			addNotes("G5q F5q",T);
			addRest("q",T);
			addRest("q",2,B);
			addNote("D5q",A,B);
			
// Measure 8
			
			addRest("h",T);
			addRest("q",T);
			addNote("C5h.",A,B);
			
// Measure 9
			
			addRest("q",2,T);
			addNote("A5q",A,T);
			addNotes("A4q C5q",B);
			addRest("q",B);
			
// Measure 10
			
			addNotes("G5h F5q",T);
			addRest("h",B);
			addRest("q",B);
			
// Measure 11
			
			addRest("q",2,T);
			addNote("F5q",A,T);
			addNotes("A4q C5q",B);
			addRest("q",B);
			
			
			
			System.out.println(trebleTime);
			System.out.println(bassTime);

			player.setSequence(sequence);
			
			player.start();	
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}		
	
	public static void playTest(CreatingMusic creatingMusic) {
		try {
	        player = MidiSystem.getSequencer();
				
			player.open();
				
			player.setTempoInBPM(140*300);
				
			Sequence sequence = new Sequence(Sequence.PPQ,4);
				
			track = sequence.createTrack();
			
            key = "Bb";
            
            factor = 300;
            
// Measure 1
            
            addRest("q",T);
            addNotes("G5i A5s",T);
            tie(A,T,"C6s","C6i");
            addNotes("B5i A5i B5s",T);
            addNotes("E4i B4i E5i B4i E4i B4i E5i B4i",B);
                        
// Measure 2
            
            tie(A,T,"D5s","D5i");
            tie(A,T,"F5q.","F5h");
            addNotes("B3i F4i B4i D5i B4i D5i A4i D5i",B);
            
// Measure 3
            
            addRest("q",T);
            addNotes("G5i A5s",T);
            tie(A,T,"C6s","C6i");
            addNotes("B5i A5i B5s",T);
            addNotes("G4i B4i D5i B4i G4i B4i D5i G4i",B);
            
// Measure 4
            
            tie(A,T,"F6s","F6w");
            addNotes("D4i F4i A4i D5i D4i F4i A4i D5i",B);
            
// Measure 5
            
            tupletFactor = 2;
            
            addNotes("G6s+B5s A6s+C6s G6s+A5s",T);
            
            tupletFactor = 1;
            
            tie(D,T,"F6i","F6h.");
            tie(A,T,"A5i","A5h.");
            addRest("q",B);
            addNotes("F4i F3i C4i F4i A4i F4i",B);
            
// Measure 6
            
            tupletFactor = 2;
            
            addNotes("B5s+D6s C6s+E6s B5s+D6s",T);
            
            tupletFactor = 1;
            
            tie(D,T,"C6i","C6h.");
            tie(A,T,"A5i","A5h.");
            addNotes("F3i C4i F4i A4i C5i G4i C5i F5i",B);

            thirdVoiceTime = trebleTime;
            
// Measure 7
            
            tupletFactor = 2;
            
            addNotes("B5s+G6s C6s+A6s B5s+G6s",T);
            
            tupletFactor = 1;
            
            tie(A,T,"F6i","F6q");
            
            tupletFactor = 2;
            
            addNotes("D6s+F6s E6s+G6s D6s+F6s",T);
            
            tupletFactor = 1;
            
            tie(D,T,"C6i","C6q");
            tie(A,T,"E6i","E6q");
            addRest("s",2,T,T);
            tie(A,T,T,"A5i","A5h.");
            addRest("q",B);
            addNote("F4i",A,B);
            tie(A,B,"F3i","F3q");
            addNote("F4i",A,B);
            
// Measure 8
            
            tupletFactor = 2;
            
            addNotes("B5s+D6s C6s+E6s B5s+D6s",T);
            
            tupletFactor = 1;
            
            tie(D,T,"A5i","A5h.");
            tie(A,T,"C6i","C6h.");
            tie(A,B,"F3i","F3q");
            addNotes("F4i F3i C4i F4i A4i F4i",B);
                      

            System.out.println(trebleTime);
            System.out.println(bassTime); 
            
            
            
			

			player.setSequence(sequence);
			
			player.start();	
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}		
	
	public static void playOde_To_Joy(CreatingMusic creatingMusic) {
		try {
	        player = MidiSystem.getSequencer();
				
			player.open();
				
			player.setTempoInBPM(240);
				
			Sequence sequence = new Sequence(Sequence.PPQ,4);
				
			track = sequence.createTrack();
			
// Measure 1
			
			addNotes("E5q E5q F5q G5q",T);
			addNote("C4w",A,B);
			
// Measure 2
			
			addNotes("G5q F5q E5q D5q",T);
			addNote("G4w",A,B);
			
// Measure 3
			
			addNotes("C5q C5q D5q E5q",T);
			addNote("E4w",A,B);
			
// Measure 4
			
			addNotes("E5q. D5i D5h",T);
			addNote("G4w",A,B);
			
// Measure 5
			
			addNotes("E5q E5q F5q G5q",T);
			addNote("C4w",A,B);
			
// Measure 6
			
			addNotes("G5q F5q E5q D5q",T);
			addNote("G4w",A,B);
			
// Measure 7
			
			addNotes("C5q C5q D5q E5q",T);
			addNote("E4w",A,B);
			
// Measure 8
			
			addNotes("D5q. C5i C5h",T);
			addNotes("F4h+G4h E4h+G4h",B);
			
// Measure 9
			
			addNotes("D5q D5q E5q C5q",T);
			addNote("G4w",A,B);
			
// Measure 10
			
			addNotes("D5q E5i F5i E5q C5q",T);
			addNote("G4w",A,B);
			
// Measure 11
			
			addNotes("D5q E5i F5i E5q D5q",T);
			addNotes("G4h G#4h",B);
			
// Measure 12
			
			addNotes("C5q D5q",T);
			addRest("h",T);
			addNotes("A4h G4h",B);
			
// Measure 13
			
			addNotes("E5q E5q F5q G5q",T);
			addNote("C4w",A,B);
			
// Measure 14
			
			addNotes("G5q F5q E5q D5q",T);
			addNote("G4w",A,B);
			
// Measure 15
			
			addNotes("C5q C5q D5q E5q",T);
			addNote("E4w",A,B);
			
// Measure 16
			
			addNotes("D5q. C5i C5h",T);
			addNotes("F4h+G4h E4h+G4h",B);

            System.out.println(trebleTime);
            System.out.println(bassTime);
			
			player.setSequence(sequence);
			
			player.start();	
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}		
	
	public static void playTwinkle_Twinkle_Little_Star(CreatingMusic creatingMusic) {
		try {
	        player = MidiSystem.getSequencer();

			int[] controllersOfInterest = new int[128];
			
			for (int counter=0;counter<controllersOfInterest.length;counter++)
				controllersOfInterest[counter] = counter;
			   
			player.addControllerEventListener(new MyControllerEventListener(), controllersOfInterest);
			
			player.addMetaEventListener(new MyMetaEventListener());
			   
			Transmitter transmitter = player.getTransmitter();
			
			transmitter.setReceiver(new MyReceiver());
			
			player.open();
				
			player.setTempoInBPM(240);
				
			Sequence sequence = new Sequence(Sequence.PPQ,4);
				
			track = sequence.createTrack();
			
// Measure 1
			
			addNotes("C5q C5q",T);
			addNote("C4h",A,B);
			
// Measure 2
			
			addNotes("G5q G5q",T);
			addNote("E4h",A,B);
			
// Measure 3
			
			addNotes("A5q A5q",T);
			addNote("F4h",A,B);
			
// Measure 4
			
			addNote("G5h",A,T);
			addNote("E4h",A,B);
			
// Measure 5
			
			addNotes("F5q F5q",T);
			addNote("D4h",A,B);
			
// Measure 6
			
			addNotes("E5q E5q",T);
			addNote("C4h",A,B);
			
// Measure 7
			
			addNotes("D5q D5q",T);
			addNotes("F4q G4q",B);
			
// Measure 8
			
			addNote("C5h",A,T);
			addNote("E4h",A,B);
			
// Measure 9
			
			addNotes("G5q G5q",T);
			addNote("E4h",A,B);
			
// Measure 10
			
			addNotes("F5q F5q",T);
			addNote("D4h",A,B);
			
// Measure 11
			
			addNotes("E5q E5q",T);
			addNote("C4h",A,B);
			
// Measure 12
			
			addNote("D5h",A,T);
			addNotes("B3q G4q",B);
			
// Measure 13
			
			addNotes("G5q G5q",T);
			addNote("E4h",A,B);
			
// Measure 14
			
			addNotes("F5q F5q",T);
			addNote("F4h",A,B);
			
// Measure 15
			
			addNotes("E5q E5i F5i",T);
			addNote("C4h",A,B);
			
// Measure 16
			
			addNotes("E5q D5q",T);
			addNotes("C4q B3q",B);
			
// Measure 17
			
			addNotes("C5q C5q",T);
			addNote("C4h",A,B);
			
// Measure 18
			
			addNotes("G5q G5q",T);
			addNote("E4h",A,B);
			
// Measure 19
			
			addNotes("A5q A5q",T);
			addNote("F4h",A,B);
			
// Measure 20
			
			addNote("G5h",A,T);
			addNote("E4h",A,B);
			
// Measure 21
			
			addNotes("F5q F5q",T);
			addNote("D4h",A,B);
			
// Measure 22
			
			addNotes("E5q E5q",T);
			addNote("C4h",A,B);
			
// Measure 23
			
			addNotes("D5q D5i E5i",T);
			addNotes("F4q G4q",B);
			
// Measure 24
			
			addNote("C5h",A,T);
			addNote("C4h",A,B);
			
			System.out.println(trebleTime);
			System.out.println(bassTime);

			player.setSequence(sequence);
			
			player.start();	
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}		
	

	public static void playNo_4_Polonaise(CreatingMusic creatingMusic) {
		try {
	        player = MidiSystem.getSequencer();

			int[] controllersOfInterest = new int[128];
			
			for (int counter=0;counter<controllersOfInterest.length;counter++)
				controllersOfInterest[counter] = counter;
			   
			player.addControllerEventListener(new MyControllerEventListener(), controllersOfInterest);
			
			player.addMetaEventListener(new MyMetaEventListener());
			   
			Transmitter transmitter = player.getTransmitter();
			
			transmitter.setReceiver(new MyReceiver());
			
			player.open();
				
			player.setTempoInBPM(240);
				
			Sequence sequence = new Sequence(Sequence.PPQ,4);
				
			track = sequence.createTrack();
			
// Measure 1
			
			addNotes("C6i C6s B5s C6i G5i E6q",T);
			addNote("E3q",A,B);
			addRest("i",B);
			addNotes("E3i E3i E3i",B);
			
// Measure 2
			
			addNotes("D6i D6s C6s D6i G5i F6q",T);
			addNote("C5q",A,B);
			addRest("i",B);
			addNotes("C5i C5i C5i",B);
			
// Measure 3
			
			addNotes("E6s G6s C7s B6s B6s A6s A6s G6s G6s F6s F6s E6s",T);
			addNotes("C5i E6i F6i E6i D6i C6i",B);
			
// Measure 4
			
			addNotes("D6s E6s D6s C6s B5s C6s B5s A5s G5q",T);
			addNotes("B5i B5s C6s D6i D6s C6s B5i G5i",B);
			
// Measure 5
			
			addNotes("C6i C6s B5s C6i G5i E6q",T);
			addNote("C5q",A,B);
			addRest("i",B);
			addNotes("C5i C5i C5i",B);
			
// Measure 6
			
			addNotes("D6i D6s C6s D6i G5i F6q",T);
			addNote("B4q",A,B);
			addRest("i",B);
			addNotes("B4i B4i B4i",B);
			
// Measure 7
			
			addNotes("E6s G6s C7s B6s B6s A6s A6s G6s G6s F6s F6s E6s",T);
			addNotes("C5i E6i F6i E6i D6i C6i",B);

		
			System.out.println(trebleTime);
			System.out.println(bassTime);

			player.setSequence(sequence);
			
			player.start();	
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}		
	
}

