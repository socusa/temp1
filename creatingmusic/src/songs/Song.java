package songs;

import static music.CreatingMusic.player;
import static music.CreatingMusic.track;
import static music.CreatingMusic.A;
import static music.CreatingMusic.B;
import static music.CreatingMusic.T;
import static music.CreatingMusic.player;
import static music.Util.addNote;
import static music.Util.addNotes;
import static music.Util.addRest;
import static music.CreatingMusic.measureMonitor;

import music.MeasureMonitor;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;

import music.MyMetaEventListener;

public abstract class Song {
	
	public abstract void measures();
	
	public void play() {
		try {			
            player = MidiSystem.getSequencer();
			
 		    player.open();

 		    player.addMetaEventListener(new MyMetaEventListener());

 		    Sequence sequence = new Sequence(Sequence.PPQ,4);
			
			track = sequence.createTrack();

			measureMonitor = new MeasureMonitor("Measure Monitor");

			measures();
			
 		    player.setSequence(sequence);
			
			player.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
