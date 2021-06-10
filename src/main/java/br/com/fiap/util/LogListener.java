package br.com.fiap.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class LogListener implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent event) {
		//System.out.println("AFTER - " + event.getPhaseId());
		
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		//System.out.println("BEFORE - " + event.getPhaseId());
		
	}

	@Override
	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return PhaseId.ANY_PHASE;
	}
	

}
