package frederickk.control;

/*
 *  Frederickk.Control 0.0.5
 *  FKnob.java
 *
 *  Ken Frederick
 *  ken.frederick@gmx.de
 *
 *  http://cargocollective.com/kenfrederick/
 *  http://kenfrederick.blogspot.com/
 *
 *  a much simpler (for me anyway) processing GUI
 *  http://code.google.com/p/frederickk/
 *
 */



//-----------------------------------------------------------------------------
// libraries
//-----------------------------------------------------------------------------
import processing.core.PApplet;



public class FColorKnob extends FKnob {
	//-----------------------------------------------------------------------------
	// properties
	//-----------------------------------------------------------------------------
	private static final long serialVersionUID = 1L;

	

	//-----------------------------------------------------------------------------
	// constructor
	//-----------------------------------------------------------------------------
	public FColorKnob(PApplet p5) {
		super(p5);
		// TODO Auto-generated constructor stub
	}

	public FColorKnob(PApplet p5, String _name, float _x, float _y, float _radius, float _vMin, float _vMax, float _val, int _labelType) {
		super(p5, _name, _x,_y, _radius, _vMin, _vMax, _val, _labelType);
	}

	
	
	//-----------------------------------------------------------------------------
	// methods
	//-----------------------------------------------------------------------------


}
