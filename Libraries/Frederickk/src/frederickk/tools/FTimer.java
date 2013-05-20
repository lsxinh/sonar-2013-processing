package frederickk.tools;

/*
 *  Frederickk.Tools 0.0.5
 *  FTimer.java
 *
 *  Ken Frederick
 *  ken.frederick@gmx.de
 *
 *  http://cargocollective.com/kenfrederick/
 *  http://kenfrederick.blogspot.com/
 *
 *  a collection of tools that i tend to use frequently
 *  http://github.com/frederickk/frederickk
 *  
 */



public class FTimer {
	//-----------------------------------------------------------------------------
	// properties
	//-----------------------------------------------------------------------------
	private float startTime;
	private float freq;

	private boolean firstCall;



	//-----------------------------------------------------------------------------
	// constructor
	//-----------------------------------------------------------------------------
	/**
	 * instantiate FTimer
	 */
	public FTimer() {
		firstCall = true;
	}

	/**
	 * instantiate FTimer
	 * 
	 * @param _freq
	 * 			frequency (in millis) the timer reacts
	 * 
	 */
	public FTimer(float _freq) {
		firstCall = true;
		setFreq(_freq);
	}



	//-----------------------------------------------------------------------------
	// methods
	//-----------------------------------------------------------------------------
	public void start() {
		startTime = (int) System.currentTimeMillis();
		firstCall = false;
	}

	public void stop() {
		firstCall = true;
	}
	
	public void reset() {
	}



	//-----------------------------------------------------------------------------
	// sets
	//-----------------------------------------------------------------------------
	public void setFreq(float _freq) {
		freq = _freq;
	}



	//-----------------------------------------------------------------------------
	// gets
	//-----------------------------------------------------------------------------
	public boolean getTrigger() {
		if(firstCall) start();

		int elapsed = (int) (System.currentTimeMillis() - startTime);
		if(freq > elapsed) {
			return true;
		} else {
			return false;
		}
	}

}

