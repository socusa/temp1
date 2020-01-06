package music;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class MyAdjustmentListener implements AdjustmentListener {

	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) {
		// TODO Auto-generated method stub
		
		e.getAdjustable().setValue(e.getAdjustable().getMaximum());  
	}

}
