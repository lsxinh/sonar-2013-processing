package frederickk.control;

/*
 *  Frederickk.Control 0.0.5
 *  FControlBase.java
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
import processing.core.PImage;
//import processing.core.PFont;
import processing.core.PVector;

import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.Rectangle;

import frederickk.tools.FTools;




abstract public class FControlBase extends Rectangle implements FControlConstants, MouseListener, MouseMotionListener {
	//-----------------------------------------------------------------------------
	// properties
	//-----------------------------------------------------------------------------
	private static final long serialVersionUID = 004L;
	protected static PApplet p5;

	
	// constants
	protected boolean OVER, DOWN, CLICK, LOCKED, RELEASE, SNAP, DRAG, MOVED;
	protected int CLICK_COUNT;
	protected int MOUSE_X, MOUSE_Y, MOUSE_BUTTON;
	protected int MOUSE_X_NEW, MOUSE_Y_NEW;
	protected float SNAP_INC;


	// labels
	protected String name;

	protected FLabel labelVal;
	protected FLabel labelName;
	protected boolean showLabels;
	protected int labelType;

	
	// colors
	protected int colorInactive;
	protected int colorOver;
	protected int colorPressed;
	protected int white;


	// sprites
	PImage[] states;

	
	private boolean bVerbose = false;

	

	//-----------------------------------------------------------------------------
	// constructor
	//-----------------------------------------------------------------------------
	public FControlBase(PApplet thePApplet) {
		// the applet
		p5 = thePApplet;


		// mouse events
		OVER = false;
		DOWN = false;
		CLICK = false;
		LOCKED = false;
		RELEASE = true;
		SNAP = false;
		DRAG = false;
		MOVED = false;
		
		p5.addMouseListener(this);
		p5.addMouseMotionListener(this);


		// colors
		Color _white = new Color(255,255,255, 204);
		white = _white.getRGB();
	}	



	//-----------------------------------------------------------------------------
	// methods
	//-----------------------------------------------------------------------------
	protected abstract void update();
	public abstract void draw();

	protected void onRollOver() {}				// called when mouse enters object x, y, width, height
	protected void onRollOut() {}				// called when mouse leaves object x, y, width, height
	protected void onMouseMove() {}				// called when mouse moves while over object x, y, width, height
	protected void onDragOver() {}				// called when mouse moves while over object and button is down
	protected void onDragOutside() {}			// called when mouse moves while outside the object after being clicked on it
	protected void onPress() {}					// called when mouse presses while over object
	protected void onPressOutside() {}			// called when mouse presses while outside object
	protected void onRelease() {}				// called when mouse releases while over object
	protected void onReleaseOutside() {}		// called when mouse releases outside of object after being pressed on object

	

	//-----------------------------------------------------------------------------
	// events
	//-----------------------------------------------------------------------------
	/**
	 * mouse clicked
	 * 
	 * @param event
	 * 
	 */
	public void mouseClicked(MouseEvent event) {
		int _x = event.getX();
		int _y = event.getY();
		int _button = event.getButton();
		CLICK_COUNT = event.getClickCount();
		
		if(bVerbose) System.out.println("mouseClicked() x:" + _x + " y:" + _y + " button:" + _button);
		
		MOUSE_X = _x;
		MOUSE_Y = _y;
		MOUSE_BUTTON = _button;

		if(hitTest(MOUSE_X,MOUSE_Y)) {
			if(!DOWN || !CLICK) {
				//onPress();
				CLICK = true;
			}
		} else {
			// nothing
			// DOWN = false;
		}
	}
	
	/**
	 * mouse pressed
	 * 
	 * @param event
	 * 
	 */
	public void mousePressed(MouseEvent event) {
		int _x = event.getX();
		int _y = event.getY();
		int _button = event.getButton();
		CLICK_COUNT = event.getClickCount();
		
		if(bVerbose) System.out.println("mousePressed() x:" + _x + " y:" + _y + " button:" + _button);
		
		MOUSE_X = _x;
		MOUSE_Y = _y;
		MOUSE_BUTTON = _button;

		if(hitTest(MOUSE_X,MOUSE_Y)) {
			if(!DOWN) {
				onPress();
				DOWN = true;
			}
		} else {
			// nothing
			// DOWN = false;
			onPressOutside();
		}
	}

	/**
	 * mouse released
	 * 
	 * @param event
	 * 
	 */
	public void mouseReleased(MouseEvent event) {
		int _x = event.getX();
		int _y = event.getY();
		int _button = event.getButton();
		
		if(bVerbose) System.out.println("mouseReleased() x:" + _x + " y:" + _y + " button:" + _button);

		MOUSE_X = _x;
		MOUSE_Y = _y;
		MOUSE_BUTTON = _button;
		CLICK_COUNT = event.getClickCount();
		
		if(hitTest(MOUSE_X,MOUSE_Y)) {
			// release inside
			onRelease();
		} else {
			// release outside
			if(DOWN) {
				onReleaseOutside();
			}
		}

		RELEASE = true;
		DOWN = false;
		CLICK = false;
		LOCKED = false;
	}

	/**
	 * mouse enter
	 * 
	 * @param event
	 * 
	 */
	public void mouseEntered(MouseEvent event) {
		MOUSE_X = event.getX();
		MOUSE_Y = event.getY();
	}

	/**
	 * mouse exit
	 * 
	 * @param event
	 * 
	 */
	public void mouseExited(MouseEvent event) {
		MOUSE_X = event.getX();
		MOUSE_Y = event.getY();
	}

	/**
	 * mouse moved
	 * 
	 * @param event
	 * 
	 */
	public void mouseMoved(MouseEvent event) {
		int _x = event.getX();
		int _y = event.getY();
		//int _button = event.getButton();

		if(bVerbose) System.out.println("mouseMoved() x:" + _x + " y:" + _y);

		MOUSE_X = _x;
		MOUSE_Y = _y;

		if(hitTest(MOUSE_X,MOUSE_Y)) {
			if(!OVER) {
				onRollOver();
				OVER = true;
			}
			onMouseMove();
		}
		else if(OVER) {
			onRollOut();
			OVER = false;
		}
	}

	/**
	 * mouse dragged
	 * 
	 * @param event
	 * 
	 */
	public void mouseDragged(MouseEvent event) {
		int _x = event.getX();
		int _y = event.getY();
		int _button = event.getButton();
		
		if(bVerbose) System.out.println("mouseDragged() x:" + _x + " y:" + _y + " button:" + _button);

		MOUSE_X = _x;
		MOUSE_Y = _y;
		MOUSE_BUTTON = _button;
		
		if(hitTest(MOUSE_X,MOUSE_Y)) {
			if(!OVER) {
				// onPress(x,y);
				OVER = true;
			}
			onDragOver();
		} else {
			if(OVER) {
				onRollOut();
				OVER = false;
			}
			if(DOWN) {
				onDragOutside();
				// drag outside
				//LOCKED = false;
			}
		}
	}


	//-----------------------------------------------------------------------------
	protected boolean hitTest(int mx, int my) {
		return ((mx > x) && (mx < x + width) && (my > y) && (my < y + height));
	}


	//-----------------------------------------------------------------------------
	protected boolean isOver() {
		return OVER;
	}
	protected boolean isDown() {
		return DOWN;
	}
	protected boolean isClicked() {
		return CLICK;
	}
	public int getClickCount() {
		return CLICK_COUNT;
	}
	protected boolean isLocked() {
		return LOCKED;
	}
	protected boolean isMoved() {
		return MOVED;
	}
	protected boolean isReleased() {
		return RELEASE;
	}
	
	protected int getMouseX() {
		return MOUSE_X;
	}
	protected int getMouseY() {
		return MOUSE_Y;
	}



	//-----------------------------------------------------------------------------
	// sets
	//-----------------------------------------------------------------------------
	/**
	 * @param _val
	 *		  toggle verbose output
	 */
	public void setVerbose(boolean _val) {
		bVerbose = _val;
	}

	//-----------------------------------------------------------------------------
	/**
	 * @param _name
	 *		  the name of the gui element
	 */
	public void setName(String _name) {
		name = _name;
	}

	//-----------------------------------------------------------------------------
	/**
	 * @param _w
	 *		  width
	 * @param _h
	 *		  height
	 */
	public void setSize(int _w, int _h) {
		this.width = _w;
		this.height = _h;
	}
	/**
	 * @param _x
	 *		  x position
	 * @param _y
	 *		  y position
	 */
	public void setPos(float _x, float _y) {
		this.x = (int) _x;
		this.y = (int) _y;
	}
	/**
	 * @param _x
	 *		  x position
	 * @param _h
	 *		  y position
	 */
	public void setPosAndSize(float _x, float _y, float _w, float _h) {
		setPos(_x,_y);
		setSize((int)_w,(int)_h);
	}


	//-----------------------------------------------------------------------------
	/**
	 * loading a sprite (vertical)
	 * |------|------|------|
	 * |-[0]U-|-[1]O-|-[2]D-|
	 * |------|------|------|
	 * up, over, down
	 * 
	 * @param img
	 * 			sprite source image
	 * @param _sw
	 * 			sprite width (individual)
	 * @param _sh
	 * 			sprite height (individual)
	 * 
	 */
	public void setSprite(PImage img, int _sw, int _sh) {
		states = new PImage[3];
		for (int i=0; i<states.length; i++) {
			states[i] = img.get(i*_sw ,0, _sw,_sh);
		}		
		setSize(_sw,_sh);
	}
	

	//-----------------------------------------------------------------------------
	public void showLabels(boolean _showLabels) {
		showLabels = _showLabels;
	}
	public void showLabels(boolean _showLabels, int _labelType) {
		showLabels = _showLabels;
		setLabelType(_labelType);
	}

	
	//-----------------------------------------------------------------------------
	/**
	 * @param _labelVal
	 *		  sets label for showing value
	 * @param _labelName
	 *		  sets label for showing name/info
	 */
	public void setLabels(FLabel _labelVal, FLabel _labelName) {
		labelVal = _labelVal;
		labelVal.setSize( width,height );
		labelVal.setPos( x+5,y );

		labelName = _labelName;
		labelName.setSize( width,height );
		labelName.setPos( x+(width+5),y );
	}

	/**
	 * @param _label
	 *		  sets generic label for showing value and name/info
	 */
	public void setLabels(FLabel _label) {
		setLabels(_label,_label);
	}

	public void setLabelType(int _labelType) {
		labelType = _labelType;
	}


	//	/**
	//	 * @param _typefaceLabel
	//	 *		  sets regular typeface weight for labels
	//	 * @param _typefaceLabelBold
	//	 *		  sets bold typeface weight for labels
	//	 */
	//	public void setTypeface(PFont _typefaceLabel, PFont _typefaceLabelBold) {
	//		labelVal.setTypeface( _typefaceLabel,_typefaceLabelBold );
	//		labelName.setTypeface( _typefaceLabel,_typefaceLabelBold );
	//	}
	//
	//	/**
	//	 * @param _typefaceLabel
	//	 *		  sets typeface weight for labels
	//	 */
	//	public void setTypeface(PFont _typefaceLabel) {
	//		labelVal.setTypeface( _typefaceLabel );
	//		labelName.setTypeface( _typefaceLabel );
	//	}


	//-----------------------------------------------------------------------------
	/**
	 * @param _colorInactive
	 *		  the inactive color of all gui elements
	 */
	public void setColorInactive(int _colorInactive) {
		colorInactive = _colorInactive;
	}
	/**
	 * @param _colorOver
	 *		  the mouseOver color of all gui elements
	 */
	public void setColorOver(int _colorOver) {
		colorOver = _colorOver;
	}
	/**
	 * @param _colorPressed
	 *		  the mousePressed color of all gui elements
	 */
	public void setColorPressed(int _colorPressed) {
		colorPressed = _colorPressed;
	}


	//-----------------------------------------------------------------------------
	/**
	 * @param _val
	 *		  enable element value snap 
	 */
	public void enableSnap(float _val) {
		SNAP_INC = _val;
		SNAP = true;
	}
	/**
	 * disable element value snap 
	 *		  
	 */
	public void disableSnap() {
		SNAP = false;
	}		



	//-----------------------------------------------------------------------------
	// gets
	//-----------------------------------------------------------------------------
	protected PVector getPos() {
		return new PVector(x,y);
	}

	
	//-----------------------------------------------------------------------------
	public int getColorInactive() {
		return colorInactive;
	}
	public int getColorOver() {
		return colorOver;
	}
	public int getColorPressed() {
		return colorPressed;
	}

	//-----------------------------------------------------------------------------
	// singular
	protected String getStrValue(float _input, int deci) {
		String val = "";
		val = java.lang.Float.toString( FTools.roundDecimal(_input,deci) );
		return val;
	}
	protected String getStrValue(float _input) {
		String val = "";
		val = Integer.toString( (int) _input );
		return val;
	}


	//-----------------------------------------------------------------------------
	// plural
	protected String getStrValue(float _x, float _y, int deci) {
		String val = "";
		val += "x " + java.lang.Float.toString( FTools.roundDecimal(_x,deci) ) + "\n";
		val += "y " + java.lang.Float.toString( FTools.roundDecimal(_y,deci) ) + "\n";
		//val += "z " + Float.toString( (float) roundDecimal( getValue().x,deci ) );
		return val;
	}
	protected String getStrValue(float _x, float _y) {
		String val = "";
		val += "x " + Integer.toString( (int) _x ) + "\n";
		val += "y " + Integer.toString( (int) _y ) + "\n";
		//val += "z " + Float.toString( (float) roundDecimal( getValue().x,deci ) );
		return val;
	}


}