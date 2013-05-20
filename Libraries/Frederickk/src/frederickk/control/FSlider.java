package frederickk.control;

/*
 *  Frederickk.Control 0.0.5
 *  FSlider.java
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
import frederickk.tools.FTools;



public class FSlider extends FControlBase {
	//-----------------------------------------------------------------------------
	// properties
	//-----------------------------------------------------------------------------
	private static final long serialVersionUID = 004L;

	private int posMin, posMax;
	private static int loose;
	protected float pos, posNew, vMin, vMax;
	protected float val;
	private int dir;



	//-----------------------------------------------------------------------------
	// constructor
	//-----------------------------------------------------------------------------
	public FSlider(PApplet p5) {
		super(p5);
		loose = 1; //3;
	}

	public FSlider(PApplet p5, String _name, float _x, float _y, int _w, int _h, float _vMin, float _vMax, float _val, int _labelType) {
		super(p5);
		loose = 1; //3;

		setName(_name);
		setSize(_w,_h);
		setPos(_x,_y);

		posNew = pos;
		setValueRange(_vMin,_vMax);
		setValue(_val);

		// labels
		setLabelType(_labelType);
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
		drag();


		//-----------------------------------------
		// controller background
		//-----------------------------------------
		p5.pushStyle();
		p5.noStroke();
		p5.fill( getColorInactive() );
		p5.rect(x,y, width,height);


		//-----------------------------------------
		// contoller amount
		//-----------------------------------------
		p5.fill( getColorOver() );
		if(dir == HORIZONTAL) 	p5.rect(x, y, pos-x, height);
		if(dir == VERTICAL)		p5.rect(x, y, width, pos-y);


		//-----------------------------------------
		// contoller handle
		//-----------------------------------------
		if( LOCKED ) p5.fill( getColorOver() );
		else p5.fill( getColorInactive() );

		if(dir == HORIZONTAL)	p5.rect(pos,y, 3,height);
		if(dir == VERTICAL)		p5.rect(x,pos, width,3);


		//-----------------------------------------
		// label
		//-----------------------------------------
		if(showLabels) {
			p5.fill( getColorOver() );
			labelName.draw( name );

			int a = (getColorInactive() >> 24) & 0xFF;
			p5.fill( white, a );
			if(labelType == LABEL_FLOAT) labelVal.draw( getStrValue( getFloatValue(),2 ) );
			else if(labelType == LABEL_INT) labelVal.draw( getStrValue( getIntValue() ) );

		}
		p5.popStyle();
	}
	
	

	//-----------------------------------------------------------------------------
	// events
	//-----------------------------------------------------------------------------
	private void drag() {
		if( LOCKED ) {
			if(dir == HORIZONTAL) {
				posNew = PApplet.constrain((float) (MOUSE_X - (height * 0.5)), posMin, posMax); 
				if( SNAP ) posNew = PApplet.constrain( FTools.snap( (float) (MOUSE_X - (height * 0.5)), SNAP_INC, -5), posMin, posMax);;
			} else if(dir == VERTICAL) {
				posNew = PApplet.constrain((float) (MOUSE_Y - (width * 0.5)), posMin, posMax); 
				if( SNAP ) posNew = PApplet.constrain( FTools.snap( (float) (MOUSE_Y - (width * 0.5)), SNAP_INC, -5), posMin, posMax);;
			}
		}

		if(PApplet.abs(posNew - pos) > 1) {
			pos = pos + (posNew-pos)/loose;
			MOVED = true;
		} else {
			MOVED = false;
		}
	}



	//-----------------------------------------------------------------------------
	// sets
	//-----------------------------------------------------------------------------
	public void setLoose(int val) {
		loose = val;
	}
	
	//-----------------------------------------------------------------------------
	public void setSize(int _w, int _h) {
		super.setSize(_w,_h);
		if(height > width) setDirection(VERTICAL);
		else setDirection(HORIZONTAL);
	}
	
	//-----------------------------------------------------------------------------
	public void setValue(int _val) {
		val = _val;
		posNew = (int) PApplet.map(val, vMin,vMax, posMin,posMax);
	}
	public void setValue(float _val) {
		val = _val;
		posNew = PApplet.map(val, vMin,vMax, posMin,posMax);
	}

	public void setValueRange(float _vMin, float _vMax) {
		vMin = _vMin;
		vMax = _vMax;
	}

	//-----------------------------------------------------------------------------
	private void setDirection(int _dir) {
		dir = _dir;

		if(dir == HORIZONTAL) {
			pos = x + width*0.5f - height*0.5f;
			posMin = (int) x;
			posMax = (int) (x + width);

		} else if(dir == VERTICAL) {
			pos = x + height*0.5f - width*0.5f;
			posMin = (int) y;
			posMax = (int) (y + height);
		}
	}



	//-----------------------------------------------------------------------------
	// gets
	//-----------------------------------------------------------------------------
	public float getFloatValue() {
		return PApplet.map(posNew, posMin,posMax, vMin,vMax);
	}

	public int getIntValue() {
		return (int) getFloatValue();
	}

	public float getValueMin() {
		return vMin;
	}

	public float getValueMax() {
		return vMax;
	}
}