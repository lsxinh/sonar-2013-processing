package frederickk.control;

/*
 *  Frederickk.Control 0.0.5
 *  FCheck.java
 *
 *  Ken Frederick
 *  ken.frederick@gmx.de
 *
 *  http://cargocollective.com/kenfrederick/
 *  http://kenfrederick.blogspot.com/
 *
 *  a much simpler (for me anyway) processing GUI
 *  http://github.com/frederickk/frederickk
 *
 */



//-----------------------------------------------------------------------------
// libraries
//-----------------------------------------------------------------------------
import processing.core.PApplet;
import processing.core.PVector;



public class FCheck extends FControlBase {
	//-----------------------------------------------------------------------------
	// properties
	//-----------------------------------------------------------------------------
	private static final long serialVersionUID = 004L;

	protected boolean val;



	//-----------------------------------------------------------------------------
	// constructor
	//-----------------------------------------------------------------------------
	public FCheck(PApplet p5) {
		super(p5);
		val = false;
	}

	public FCheck(PApplet p5, String _name, float _x, float _y, int _sz, boolean _val) {
		super(p5);
		setName(_name);
		setSize(_sz,_sz);
		setPos(_x,_y);
		setValue(_val);
	}



	//-----------------------------------------------------------------------------
	// methods
	//-----------------------------------------------------------------------------
	protected void update() {
		if( isOver() && DOWN ) LOCKED = true;
	}


	//-----------------------------------------------------------------------------
	public void draw() {
		update();
		toggle();

		
		//-----------------------------------------
		// controller
		//-----------------------------------------
		p5.pushStyle();
		p5.fill( getColorInactive() );
		if( isOver() ) p5.stroke( getColorOver() );
		else if( LOCKED ) p5.stroke( getColorPressed() );
		else p5.noStroke();
		p5.rect(x,y, width,height);

		
		//-----------------------------------------
		// x'd
		//-----------------------------------------
		if(val) {
//			PVector xsz = new PVector((float) (width*0.6), (float) (height*0.6));
			p5.noFill();
			p5.strokeWeight(2);
			p5.stroke( getColorOver() );
			p5.beginShape();
			p5.vertex(x+(width*0.3f), y+height/2);
			p5.vertex(x+width/2,      y+height-(height*0.3f));
			p5.vertex(x+width-(width*0.3f), y+(height*0.3f));
			p5.endShape();

//			p5.ellipse(x+width/2,y+height/2, xsz.x,xsz.y);
		}


		//-----------------------------------------
		// label
		//-----------------------------------------
		if(showLabels) {
			p5.fill( getColorOver() );
			labelName.setSize(width*3,height);
			labelName.container(false);
			labelName.draw(name, PApplet.LEFT, BOLD);
			//labelVal.draw("");
		}
		p5.popStyle();
	}


	//-----------------------------------------------------------------------------
	protected boolean toggle() {
		if( LOCKED ) {
			val = !val;
			
			LOCKED = !LOCKED;
			OVER = !OVER;
			DOWN = !DOWN;
			
			return true;
		}
		else {
			return false;
		}
	}



	//-----------------------------------------------------------------------------
	// sets
	//-----------------------------------------------------------------------------
	public void setValue(boolean _val) {
		val = _val;
	}



	//-----------------------------------------------------------------------------
	// gets
	//-----------------------------------------------------------------------------
	public boolean getValue() {
		return val;
	}

}