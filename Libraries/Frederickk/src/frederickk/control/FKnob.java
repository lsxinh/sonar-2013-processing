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
import processing.core.PVector;



public class FKnob extends FSlider {
	//-----------------------------------------------------------------------------
	// properties
	//-----------------------------------------------------------------------------
	private static final long serialVersionUID = 004L;

	private float hx,hy, radius,radiusInner,radiusHandle;
	private float angle = PApplet.PI; //place our handle to the far left



	//-----------------------------------------------------------------------------
	//constructor
	//-----------------------------------------------------------------------------
	public FKnob(PApplet p5) {
		super(p5);
	}

	public FKnob(PApplet p5, String _name, float _x, float _y, float _radius, float _vMin, float _vMax, float _val, int _labelType) {
		super(p5);
		
		setRadius(_radius);
		setPos(_x,_y);
		setName(_name);

		setValueRange(_vMin,_vMax);
		setValue(_val);

		setLabelType(_labelType);
	}



	//-----------------------------------------------------------------------------
	// methods
	//-----------------------------------------------------------------------------
	public void draw() {
		update();
		drag();

		
		//-----------------------------------------
		hx = getAngle((float) (radiusHandle*0.5)).x;
		hy = getAngle((float) (radiusHandle*0.5)).y;

		
		//-----------------------------------------
		// controller
		//-----------------------------------------
		p5.pushStyle();
		p5.noFill();
		p5.stroke( getColorInactive() );
		p5.ellipse(x,y,radius,radius);
		p5.ellipse(x,y, (float) (radiusInner), (float) (radiusInner) );
		//p5.line(x,y,hx,hy);

		
		//-----------------------------------------
		// handle
		//-----------------------------------------
		p5.noStroke();
		if( isOver() ) p5.fill( getColorOver() );
		else if( isOver() && LOCKED ) p5.fill( getColorPressed() );
		else p5.fill( getColorInactive() );
		p5.ellipse(hx,hy, radiusHandle,radiusHandle);

		//		p5.beginShape();
		//		p5.endShape();

		
		//-----------------------------------------
		// label
		//-----------------------------------------
		if(showLabels) {
			p5.fill( getColorOver() );
			labelName.setPos( (int) (x+((radius*0.5)+8)), (int) (y-radius*0.5));
			labelName.draw( name, PApplet.LEFT, BOLD );

			int a = (getColorInactive() >> 24) & 0xFF;
			p5.fill( white, a );
			labelVal.setPos( (int) (x-radius*0.5), (int) (y-radius*0.5));
			labelVal.setSize( (int) radius, (int) radius);
			if(labelType == LABEL_FLOAT) labelVal.draw( getStrValue(getFloatValue(),2), PApplet.CENTER );
			else if(labelType == LABEL_INT) labelVal.draw( getStrValue(getIntValue()), PApplet.CENTER );

		}
		p5.popStyle();
	}

	
	
	//-----------------------------------------------------------------------------
	// events
	//-----------------------------------------------------------------------------
	protected boolean isOver() {
		if(MOUSE_X > x-radius*0.5 && MOUSE_X < x+radius*0.5 && 
		   MOUSE_Y > y-radius*0.5 && MOUSE_Y < y+radius*0.5) {
			OVER = true;
		} else {
			OVER = false;
		}
		return OVER;
	}


	//-----------------------------------------------------------------------------
	private void drag() {
		if(LOCKED) {
			angle = (float) Math.atan2(MOUSE_Y-y,MOUSE_X-x);
		}
		/*
    if(abs(posNew - pos) > 1) {
      pos = pos + (posNew-pos)/loose;
      MOVED = true;
    } else {
      MOVED = false;
    }
		 */

	}


	
	//-----------------------------------------------------------------------------
	//sets
	//-----------------------------------------------------------------------------
	public void setRadius(float _radius) {
		radius = _radius;
		//radiusInner = (float) (radius*0.618);
		radiusInner = (float) (radius*0.381924);
		radiusHandle = (float) ((radius - radiusInner)*0.5); 

		width = (int) radius;
		height = (int) radius;
	}


	
	//-----------------------------------------------------------------------------
	// gets 
	//-----------------------------------------------------------------------------
	private PVector getAngle(float offset) {
		float ax = (float) (x + Math.cos( angle ) * (radius*0.5 - offset));
		float ay = (float) (y + Math.sin( angle ) * (radius*0.5 - offset));
		return new PVector(ax,ay);  
	}

	public float getFloatValue() {
		return PApplet.map(angle, -PApplet.PI,PApplet.PI, vMin,vMax);
	}

	
}