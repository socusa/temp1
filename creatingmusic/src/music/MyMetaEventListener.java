package music;

import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;

import static music.CreatingMusic.measureMonitor;

public class MyMetaEventListener implements MetaEventListener {

	@Override
	public void meta(MetaMessage meta) {
		// TODO Auto-generated method stub
		
		System.out.println(music.CreatingMusic.trebleTime);
		System.out.println(music.CreatingMusic.bassTime);
		
		if (meta.getType() == 1) {
			byte[] bytes = meta.getData();
			
			String line = new String(bytes);
			
			System.out.println(line);
			
			String[] tokens = line.split(";");
			
			int measure = Integer.parseInt(tokens[0]);
			int trebleTime = Integer.parseInt(tokens[1]);
			int bassTime = Integer.parseInt(tokens[2]);
						
			measureMonitor.setMessage(measure,
			                          trebleTime,
			                          bassTime);
		}
	}

}
