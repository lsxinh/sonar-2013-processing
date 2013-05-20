package frederickk.control;

/*
 *  Frederickk.Control 0.0.5
 *  FHandle.java
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
import frederickk.tools.FTools;
import processing.core.PApplet;
import processing.core.PVector;



public class FHandle extends FControlBase {
	//-----------------------------------------------------------------------------
	// properties
	//-----------------------------------------------------------------------------
	private static final long serialVersionUID = 004L;

	protected float x_new,y_new;
	protected PVector val = new PVector();
	protected boolean valSel;

	protected boolean DRAG_OFF = false;



	//-----------------------------------------------------------------------------
	// constructor
	//-----------------------------------------------------------------------------
	public FHandle(PApplet p5) {
		super(p5);
	}

	public FHandle(PApplet p5, String _name, float _x, float _y, int _w, int _h, int _labelType) {
		super(p5);
		setName(_name);
		setSize(_w,_h);
		setPos(_x,_y);
		setLabelType(_labelType);
	}


	
	//-----------------------------------------------------------------------------
	// methods
	//-----------------------------------------------------------------------------
	protected void update() {
		if( isOver() && DOWN ) LOCKED = true;
	}


	//-----------------------------------------------------------------------------
	private void updateLabel() {
		labelVal.setPos( x+(width+5),y );
		labelName.setPos( x+5,y );
	}


	//-----------------------------------------------------------------------------
	public void draw() {
		update();
		updateLabel();
		if(!DRAG_OFF) drag();

		
		//-----------------------------------------
		val.x = x;
		val.y = y;
		

		//-----------------------------------------
		// controller
		p5.pushStyle();
		p5.noStroke();

		if( isOver() ) p5.fill( getColorOver() );
		else if( LOCKED ) p5.fill( getColorPressed() );
		else p5.fill( getColorInactive() );
		
		p5.rect(x,y, width,height);
		

		//-----------------------------------------
		// label
		if(showLabels) {
			int a = (getColorInactive() >> 24) & 0xFF;
			p5.fill( white, a );
			if(labelType == LABEL_FLOAT) labelVal.draw( getStrValue(val.x,val.y, 2) );
			else if(labelType == LABEL_INT) labelVal.draw( getStrValue(val.x, val.y) );
		}
		p5.popStyle();
	}
	

	//-----------------------------------------------------------------------------
	protected void drawItem() {
		update();
		updateLabel();
		toggle();

		//-----------------------------------------
		p5.stroke( getColorInactive() );
		if( isOver() || LOCKED ) p5.fill( getColorOver() );
		else p5.fill( getColorInactive() );
		p5.rect(x,y, width,height);

		//-----------------------------------------
		if(valSel) {
			p5.fill( getColorOver() );
			p5.rect(x,y, 3,height);
		}
		
		//-----------------------------------------
		if(showLabels) {
			if( isOver() || LOCKED ) p5.fill( white );
			else p5.fill( getColorOver() );
			labelName.draw( name );
			//updateLabel();
		}
	}


	//-----------------------------------------------------------------------------
	protected boolean toggle() {
		if( LOCKED ) {
			valSel = !valSel;
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
	// events
	//-----------------------------------------------------------------------------
//	protected boolean getOver() {
//		if(MOUSE_X > x && MOUSE_X < x+w && 
//		   MOUSE_Y > y && MOUSE_Y < y+h) {
//			OVER = true;
//		} else {
//			OVER = false;
//		}
//		return OVER;
//	}


	//-----------------------------------------------------------------------------
	protected void drag() {
		if( LOCKED ) {
			if( SNAP ) {
				x_new = FTools.snap( (float) (MOUSE_X - (width * 0.5)), SNAP_INC, 0);
				y_new = FTools.snap( (float) (MOUSE_Y - (height * 0.5)), SNAP_INC, 0);
			} else {
				x_new = (float) (MOUSE_X - (width * 0.5));
				y_new = (float) (MOUSE_Y - (height * 0.5));
			}
		}

		if(PApplet.abs(x_new - x) > 1) x = (int) x_new;
		if(PApplet.abs(y_new - y) > 1) y = (int) y_new;
		if(PApplet.abs(x_new - x) > 1 || PApplet.abs(y_new - y) > 1) {
			MOVED = true;
		} else {
			MOVED = false;
		}
		
	}


	
	//-----------------------------------------------------------------------------
	// sets
	//-----------------------------------------------------------------------------
	public void setPos(float _x, float _y) {
		super.setPos(_x, _y);
		x_new = x;
		y_new = y;

		val.x = x; 
		val.y = y;
	}

	public void disableDrag(boolean _DRAG_OFF) {
		DRAG_OFF = _DRAG_OFF;
	}

	protected void setToggle() {
		valSel = !valSel;
	}
	protected void setToggle(boolean _valSel) {
		valSel = _valSel;
	}



	//-----------------------------------------------------------------------------
	// gets
	//-----------------------------------------------------------------------------
	public PVector getValue() {
		return val;
	}	

	protected boolean getToggle() {
		return valSel;
	}
}