package music;

import javax.sound.midi.*;

public class MyReceiver implements Receiver {

	@Override
	public void send(MidiMessage message, long timeStamp) {
		// TODO Auto-generated method stub
		
		if (message instanceof ShortMessage) {
			ShortMessage shortMessage = (ShortMessage)message;
			
			if (shortMessage.getCommand() == 144)
				System.out.println(shortMessage.getData1());
		}		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

}
