package frederickk.tools;

/*
 *  Frederickk.Tools 0.0.5
 *  FMultiTimer.java
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
 *	FTimer code improvement by 
 *	Eugen Kern-Emden
 *	eugn@kern-emden.de
 *
 */



//-----------------------------------------------------------------------------
// libraries
//-----------------------------------------------------------------------------
import processing.core.PApplet;



public class FMultiTimer {
	//-----------------------------------------------------------------------------
	// properties
	//-----------------------------------------------------------------------------
	private PApplet p5;

	private float startTime;
	private float freq;

	private float[] interval;
	int index;

	private boolean firstCall;
	private boolean trigger;



	//-----------------------------------------------------------------------------
	// constructor
	//-----------------------------------------------------------------------------
	/**
	 * instantiate FTimer (work in progess, more so than FTimer)
	 * 
	 * @param thePApplet
	 * 			PApplet
	 */
	public FMultiTimer(PApplet thePApplet) {
		p5 = thePApplet;
		firstCall = true;
	}

	public FMultiTimer(PApplet thePApplet, float _interval) {
		p5 = thePApplet;
		firstCall = true;
		setFreq(_interval);
	}

	public FMultiTimer(PApplet thePApplet, float[] _interval) {
		p5 = thePApplet;
		firstCall = true;
		setFreq(_interval);
	}



	//-----------------------------------------------------------------------------
	// methods
	//-----------------------------------------------------------------------------
	public void start() {
		startTime = p5.millis();
		//startTime = System.currentTimeMillis();
		firstCall = false;
	}

	public void stop() {
		firstCall = true;

		index++;
		if (index >= interval.length)
			index = 0;
	}



	//-----------------------------------------------------------------------------
	// sets
	//-----------------------------------------------------------------------------
	public void setFreq(float _interval) {
		interval = new float[1];
		interval[0] = _interval;
	}

	public void setFreq(float[] _interval) {
		interval = new float[_interval.length];
		for (int i = 0; i < _interval.length; i++) {
			interval[i] = _interval[i];
		}
	}



	//-----------------------------------------------------------------------------
	// gets
	//-----------------------------------------------------------------------------
	public boolean getTrigger() {
		if (firstCall)
			start();
		freq = interval[index];

		if ((p5.millis() - startTime) < freq) {
		//if( (System.currentTimeMillis() - startTime) < freq) {
			trigger = false;
		} else {
			stop();
			trigger = true;
		}

		return trigger;
	}

}
