package music;

import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.ShortMessage;

public class MyControllerEventListener implements ControllerEventListener {

	@Override
	public void controlChange(ShortMessage event) {
		// TODO Auto-generated method stub
		
		System.out.println(event.getData1());
	}

}
