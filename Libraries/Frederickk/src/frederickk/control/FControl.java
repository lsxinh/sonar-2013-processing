package frederickk.control;

/*
 *  Frederickk.Control 0.0.5
 *  FControl.java
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
import processing.core.PFont;
import processing.core.PImage;
import processing.core.PVector;
import processing.core.PConstants;

//import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Dimension;
//import java.awt.Frame;
import java.util.ArrayList;



public class FControl implements PConstants, FControlConstants {
	//-----------------------------------------------------------------------------
	// properties
	//-----------------------------------------------------------------------------
	protected static PApplet p5;
	//protected static Frame frame;

	private int sleepTime = 40;


	// controllers
	private ArrayList<FButton> FButtons = new ArrayList<FButton>();
	private ArrayList<FHandle> FHandles = new ArrayList<FHandle>();
	private ArrayList<FHandle3D> FHandle3Ds = new ArrayList<FHandle3D>();
	private ArrayList<FCheck> FChecks = new ArrayList<FCheck>();
	private ArrayList<FSlider> FSliders = new ArrayList<FSlider>();
	private ArrayList<FKnob> FKnobs = new ArrayList<FKnob>();
	private ArrayList<FDropDown> FDropDowns = new ArrayList<FDropDown>();
	private ArrayList<FLabel> FLabels = new ArrayList<FLabel>();
	private ArrayList<FInputField> FInputFields = new ArrayList<FInputField>();


	// values
	private boolean boolVal;
	private int intVal;
	private float floatVal;
	private String strVal = new String();
	private String selStrVal = new String();
	private int selIntVal;


	// colors
	//	private static int colorInactive;
	//	private static int colorOver;
	//	private static int colorPressed;
	private int colorInactive;
	private int colorOver;
	private int colorPressed;


	// labels
	protected static PFont typefaceReg;
	protected static PFont typefaceBold;
	private boolean bShowLabels = true;
	private boolean bShowFrameRate = true;


	// debug
	private boolean bVerbose = false;



	//-----------------------------------------------------------------------------
	// constructor
	//-----------------------------------------------------------------------------
	/**
	 * initialize the FControl gui elements class
	 * 
	 * @param _papplet
	 *		  instance of the applet
	 * 
	 */
	public FControl(PApplet _papplet) {
		welcome();

		p5 = _papplet;
		//frame = p5.frame;

		// set default resting (inactive) color
		Color _colorInactive = new Color(215,215,215, 96);
		colorInactive = _colorInactive.getRGB();

		// set default over color
		Color _colorOver = new Color(210,210,210, 230);
		colorOver = _colorOver.getRGB();

		// set default pressed color
		Color _colorPressed = new Color(255,255,255, 230);
		colorPressed = _colorPressed.getRGB();

		// set default typeface
		try {
			//setTypeface( p5.createFont("LucidaGrande",9), p5.createFont("LucidaGrande-Bold",9) );
			setTypeface( p5.createFont("LucidaGrande-Bold", 10) );

		} catch (Exception e) {
			setTypeface( p5.createFont("SansSerif", 9) );

			System.out.println( "-----------------------------------------------------------------------------" );
			System.out.println( "FControl Error: " + e );
			System.out.println( "it appers you don't have \"LucidaGrande-Bold\" on your system,");
			System.out.println( "so \"SansSerif\" has been loaded as a substitute, however" );
			System.out.println( "setTypeface() can be use to change the typeface");
		}

	}


	/**
	 * initialize the FControl gui elements class
	 * 
	 * @param _papplet
	 *		  instance of the applet
	 * @param _typeface
	 *		  typeface for use with interface elements
	 * 
	 */
	public FControl(PApplet _papplet, PFont _typeface) {
		welcome();

		p5 = _papplet;
		//frame = p5.frame;

		// set default resting (inactive) color
		Color _colorInactive = new Color(215,215,215, 96);
		colorInactive = _colorInactive.getRGB();

		// set default over color
		Color _colorOver = new Color(210,210,210, 230);
		colorOver = _colorOver.getRGB();

		// set default pressed color
		Color _colorPressed = new Color(255,255,255, 230);
		colorPressed = _colorPressed.getRGB();

		//set typeface
		setTypeface( _typeface );
	}


	/**
	 * initialize the FControl gui elements class
	 * 
	 * @param _papplet
	 *		  instance of the applet
	 * @param _typeface
	 *		  typeface for use with interface elements
	 * @param _typefaceBold
	 *		  bold typeface for use with interface elements
	 * 
	 */
	public FControl(PApplet _papplet, PFont _typeface, PFont _typefaceBold) {
		welcome();

		p5 = _papplet;
		//frame = p5.frame;

		// set default resting (inactive) color
		Color _colorInactive = new Color(215,215,215, 96);
		colorInactive = _colorInactive.getRGB();

		// set default over color
		Color _colorOver = new Color(210,210,210, 230);
		colorOver = _colorOver.getRGB();

		// set default pressed color
		Color _colorPressed = new Color(255,255,255, 230);
		colorPressed = _colorPressed.getRGB();

		//set typeface
		setTypeface( _typeface, _typefaceBold );
	}


	//-----------------------------------------------------------------------------
	private void welcome() {
		System.out.println( "\n" );
		System.out.println( "-----------------------------------------------------------------------------" );
		System.out.println( "Frederickk Library 0.0.5" );
		System.out.println( "FControl" );
		System.out.println( "http://github.com/frederickk/frederickk" );
		System.out.println( "http://kenfrederick.blogspot.com/\n" );
	}



	//-----------------------------------------------------------------------------
	// controllers
	//-----------------------------------------------------------------------------

	//-----------------------------------------
	// FButton
	//-----------------------------------------
	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _w
	 *		  width of gui element
	 * @param _h
	 *		  height of gui element
	 *		  
	 */
	public void addButton(String _name, float _x, float _y, int _w, int _h) {
		FButton fb = new FButton(p5);
		fb.setName(_name);
		fb.setPos(_x, _y);
		fb.setSize(_w, _h);

		FLabel LabelValue = new FLabel(p5);
		FLabel LabelInfo = new FLabel(p5);
		LabelValue.setTypeface( typefaceReg, typefaceBold );
		LabelInfo.setTypeface( typefaceReg, typefaceBold );

		fb.setLabels(LabelValue, LabelInfo);
		fb.setLabelType( LABEL_STRING );

		FButtons.add(fb);
	}

	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _w
	 *		  width of gui element
	 * @param _h
	 *		  height of gui element
	 *		  
	 */
	public void addButton(String _name, int _series, float _x, float _y, int _w, int _h) {
		addButton(_name + Integer.toString(_series), _x,_y, _w,_h);
	}

	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _w
	 *		  width of gui element
	 * @param _h
	 *		  height of gui element
	 * @param img
	 * 			sprite source image, sprites must be square
	 * 			loading a sprite (vertical)
	 * 			|------|------|------|
	 *			|-[0]U-|-[1]O-|-[2]D-|
	 * 			|------|------|------|
	 * 			up, over, down
	 *		  
	 */
	public void addButton(String _name, float _x, float _y, int _w, int _h, PImage img) {
		FButton fb = new FButton(p5);
		fb.setName(_name);
		fb.setPos(_x, _y);
		fb.setSize(_w, _h);
		fb.setSprite(img, img.height,img.height);

		FLabel LabelValue = new FLabel(p5);
		FLabel LabelInfo = new FLabel(p5);
		LabelValue.setTypeface( typefaceReg, typefaceBold );
		LabelInfo.setTypeface( typefaceReg, typefaceBold );

		fb.setLabels(LabelValue, LabelInfo);
		fb.setLabelType( LABEL_STRING );

		FButtons.add(fb);
	}

	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _w
	 *		  width of gui element
	 * @param _h
	 *		  height of gui element
	 * @param img
	 * 			sprite source image, sprites must be square
	 * 			loading a sprite (vertical)
	 * 			|------|------|------|
	 *			|-[0]U-|-[1]O-|-[2]D-|
	 * 			|------|------|------|
	 * 			up, over, down
	 *		  
	 */
	public void addButton(String _name, int _series, float _x, float _y, int _w, int _h, PImage img) {
		addButton(_name + Integer.toString(_series), _x,_y, _w,_h, img);
	}

	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _w
	 *		  width of gui element
	 * @param _h
	 *		  height of gui element
	 * @param _mode
	 *		  BUTTON_NORMAL creates a generic button
	 *		  BUTTON_LOAD creates a button which opens a file dialog use getFilePath() to retrieve the path to the file
	 *
	 */
	public void addButton(String _name, float _x, float _y, int _w, int _h, int _mode) {
		FButton fb = new FButton(p5);
		fb.setName(_name);
		fb.setPos(_x, _y);
		fb.setSize(_w, _h);
		fb.setMode(_mode);

		FLabel LabelValue = new FLabel(p5);
		FLabel LabelInfo = new FLabel(p5);
		LabelValue.setTypeface( typefaceReg, typefaceBold );
		LabelInfo.setTypeface( typefaceReg, typefaceBold );

		fb.setLabels(LabelValue, LabelInfo);
		fb.setLabelType( LABEL_STRING );

		FButtons.add(fb);
	}

	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _w
	 *		  width of gui element
	 * @param _h
	 *		  height of gui element
	 * @param _mode
	 *		  BUTTON_NORMAL creates a generic button
	 *		  BUTTON_LOAD creates a button which opens a file dialog use getFilePath() to retrieve the path to the file
	 *		  
	 */
	public void addButton(String _name, int _series, float _x, float _y, int _w, int _h, int _mode) {
		addButton(_name + Integer.toString(_series), _x,_y, _w,_h, _mode);
	}


	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _w
	 *		  width of gui element
	 * @param _h
	 *		  height of gui element
	 * @param _mode
	 *		  BUTTON_NORMAL creates a generic button
	 *		  BUTTON_LOAD creates a button which opens a file dialog use getFilePath() to retrieve the path to the file
	 * @param img
	 * 			sprite source image, sprites must be square
	 * 			loading a sprite (vertical)
	 * 			|------|------|------|
	 *			|-[0]U-|-[1]O-|-[2]D-|
	 * 			|------|------|------|
	 * 			up, over, down
	 *
	 */
	public void addButton(String _name, float _x, float _y, int _w, int _h, int _mode, PImage img) {
		FButton fb = new FButton(p5);
		fb.setName(_name);
		fb.setPos(_x, _y);
		fb.setSize(_w, _h);
		fb.setMode(_mode);
		fb.setSprite(img, img.height,img.height);

		FLabel LabelValue = new FLabel(p5);
		FLabel LabelInfo = new FLabel(p5);
		LabelValue.setTypeface( typefaceReg, typefaceBold );
		LabelInfo.setTypeface( typefaceReg, typefaceBold );

		fb.setLabels(LabelValue, LabelInfo);
		fb.setLabelType( LABEL_STRING );

		FButtons.add(fb);
	}	

	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _w
	 *		  width of gui element
	 * @param _h
	 *		  height of gui element
	 * @param _mode
	 *		  BUTTON_NORMAL creates a generic button
	 *		  BUTTON_LOAD creates a button which opens a file dialog use getFilePath() to retrieve the path to the file
	 * @param img
	 * 			sprite source image, sprites must be square
	 * 			loading a sprite (vertical)
	 * 			|------|------|------|
	 *			|-[0]U-|-[1]O-|-[2]D-|
	 * 			|------|------|------|
	 * 			up, over, down
	 *		  
	 */
	public void addButton(String _name, int _series, float _x, float _y, int _w, int _h, int _mode, PImage img) {
		addButton(_name + Integer.toString(_series), _x,_y, _w,_h, _mode, img);
	}


	//-----------------------------------------
	// FHandle
	//-----------------------------------------
	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _w
	 *		  width of gui element
	 * @param _h
	 *		  height of gui element
	 *		  
	 */
	public void addHandle(String _name, float _x, float _y, int _w, int _h, int _labelType) {
		FHandle fh = new FHandle(p5);
		fh.setName(_name);
		fh.setPos(_x, _y);
		fh.setSize(_w, _h);

		FLabel LabelValue = new FLabel(p5);
		LabelValue.setTypeface( typefaceReg, typefaceBold );

		FLabel LabelInfo = new FLabel(p5);
		LabelInfo.setTypeface( typefaceReg, typefaceBold );

		fh.setLabels(LabelValue, LabelInfo);
		fh.setLabelType( _labelType );

		FHandles.add(fh);
	}

	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _w
	 *		  width of gui element
	 * @param _h
	 *		  height of gui element
	 *		  
	 */
	public void addHandle(String _name, float _x, float _y, int _w, int _h) {
		addHandle(_name, _x,_y, _w,_h, LABEL_FLOAT );
	}

	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _w
	 *		  width of gui element
	 * @param _h
	 *		  height of gui element
	 *		  
	 */
	public void addHandle(String _name, int _series, float _x, float _y, int _w, int _h) {
		addHandle(_name + Integer.toString(_series), _x,_y, _w,_h);
	}


	//-----------------------------------------
	// FHandle3D
	//-----------------------------------------
	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _z
	 *		  z value of gui element
	 * @param _w
	 *		  width of gui element
	 *		  
	 */
	public void addHandle3D(String _name, float _x, float _y, float _z, int _w, int _labelType) {
		FHandle3D fh3 = new FHandle3D(p5);
		fh3.setName(_name);
		fh3.setPos(_x, _y, _z);
		fh3.setSize(_w, _w);

		FLabel LabelValue = new FLabel(p5);
		LabelValue.setTypeface( typefaceReg, typefaceBold );

		FLabel LabelInfo = new FLabel(p5);
		LabelInfo.setTypeface( typefaceReg, typefaceBold );

		fh3.setLabels(LabelValue, LabelInfo);
		fh3.setLabelType( _labelType );

		FHandle3Ds.add(fh3);
	}

	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _z
	 *		  z value of gui element
	 * @param _w
	 *		  width of gui element
	 *		  
	 */
	public void addHandle3D(String _name, float _x, float _y, float _z, int _w) {
		addHandle3D(_name, _x,_y,_z, _w, LABEL_FLOAT );
	}

	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _z
	 *		  z value of gui element
	 * @param _w
	 *		  width of gui element
	 *		  
	 */
	public void addHandle3D(String _name, int _series, float _x, float _y, float _z, int _w) {
		addHandle3D(_name + Integer.toString(_series), _x,_y,_z, _w);
	}


	//-----------------------------------------
	// FCheck
	//-----------------------------------------
	/**
	 * @param fcheck
	 *		  fcheck element
	 */
	public void addCheck(FCheck fcheck) {
		FLabel LabelName = new FLabel(p5);
		LabelName.setTypeface( typefaceBold );
		fcheck.setLabels(LabelName);

		FChecks.add(fcheck);
	}

	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _sz
	 *		  width & height of gui element
	 * @param _val
	 *		 initial state of gui element
	 *		 
	 */
	public void addCheck(String _name, float _x, float _y, int _sz, boolean _val) {
		FCheck fc = new FCheck(p5);
		fc.setName(_name);
		fc.setPos(_x, _y);
		fc.setSize(_sz,_sz);
		fc.setValue(_val);

		FLabel LabelName = new FLabel(p5);
		LabelName.setTypeface( typefaceBold );
		fc.setLabels(LabelName);

		FChecks.add(fc);
	}

	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _sz
	 *		  width & height of gui element
	 *		 
	 */
	public void addCheck(String _name, float _x, float _y, int _sz) {
		addCheck(_name, _x,_y, _sz, false);
	}

	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _sz
	 *		  width & height of gui element
	 * @param _val
	 *		 initial state of gui element
	 *		 
	 */
	public void addCheck(String _name, int _series, float _x, float _y, int _sz, boolean _val) {
		addCheck(_name + Integer.toString(_series), _x,_y, _sz, _val);
	}


	//-----------------------------------------
	// FSlider
	//-----------------------------------------
	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _w
	 *		  width of gui element
	 * @param _h
	 *		  height of gui element
	 * @param _vMin
	 *		  minimum value of gui element
	 * @param _vMax
	 *		  maximum value of gui element
	 * @param _val
	 *		  initial value of gui element
	 * @param _labelType
	 *		  display type of value LABEL_FLOAT or LABEL_INT
	 *		  
	 */
	public void addSlider(String _name, float _x, float _y, int _w, int _h, float _vMin, float _vMax, float _val, int _labelType) {
		FSlider fs = new FSlider(p5);
		fs.setName(_name);
		fs.setPos(_x, _y);
		fs.setSize(_w,_h);
		fs.setValueRange(_vMin,_vMax);
		fs.setValue(_val);

		FLabel LabelValue = new FLabel(p5);
		FLabel LabelInfo = new FLabel(p5);
		LabelValue.setTypeface( typefaceReg, typefaceBold );
		LabelInfo.setTypeface( typefaceReg, typefaceBold );
		fs.setLabels(LabelValue, LabelInfo);
		fs.setLabelType( _labelType );

		FSliders.add(fs);
	}

	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _w
	 *		  width of gui element
	 * @param _h
	 *		  height of gui element
	 * @param _vMin
	 *		  minimum value of gui element
	 * @param _vMax
	 *		  maximum value of gui element
	 * @param _val
	 *		  initial value of gui element
	 *		  
	 */
	public void addSlider(String _name, float _x, float _y, int _w, int _h, float _vMin, float _vMax, float _val) {
		addSlider(_name, _x,_y, _w,_h, _vMin,_vMax, _val, LABEL_FLOAT);
	}

	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _w
	 *		  width of gui element
	 * @param _h
	 *		  height of gui element
	 *		  
	 */
	public void addSlider(String _name, float _x, float _y, int _w, int _h) {
		addSlider(_name, _x,_y, _w,_h, 0.0f,1.0f, 0.0f, LABEL_FLOAT);
	}

	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _w
	 *		  width of gui element
	 * @param _h
	 *		  height of gui element
	 * @param _vMin
	 *		  minimum value of gui element
	 * @param _vMax
	 *		  maximum value of gui element
	 * @param _val
	 *		  initial value of gui element
	 *		  
	 */
	public void addSlider(String _name, int _series, float _x, float _y, int _w, int _h, float _vMin, float _vMax, float _val) {
		addSlider(_name + Integer.toString(_series), _x,_y, _w,_h, _vMin,_vMax, _val, LABEL_FLOAT);
	}

	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _w
	 *		  width of gui element
	 * @param _h
	 *		  height of gui element
	 * @param _vMin
	 *		  minimum value of gui element
	 * @param _vMax
	 *		  maximum value of gui element
	 * @param _val
	 *		  initial value of gui element
	 * @param _labelType
	 *		  display type of value LABEL_FLOAT or LABEL_INT
	 *		  
	 */
	public void addSlider(String _name, int _series, float _x, float _y, int _w, int _h, float _vMin, float _vMax, float _val, int _labelType) {
		addSlider(_name + Integer.toString(_series), _x,_y, _w,_h, _vMin,_vMax, _val, _labelType);
	}


	//-----------------------------------------
	// FKnob
	//-----------------------------------------
	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _r
	 *		  radius of gui element
	 * @param _vMin
	 *		  minimum value of gui element
	 * @param _vMax
	 *		  maximum value of gui element
	 * @param _val
	 *		  initial value of gui element
	 * @param _labelType
	 *		  display type of value LABEL_FLOAT or LABEL_INT
	 *		  
	 */
	public void addKnob(String _name, float _x, float _y, int _r, float _vMin, float _vMax, float _val, int _labelType) {
		FKnob fk = new FKnob(p5);
		fk.setName(_name);
		fk.setPos(_x, _y);
		fk.setRadius(_r);
		fk.setValueRange(_vMin,_vMax);
		fk.setValue(_val);

		FLabel LabelValue = new FLabel(p5);
		FLabel LabelInfo = new FLabel(p5);
		LabelValue.setTypeface( typefaceReg, typefaceBold );
		LabelInfo.setTypeface( typefaceReg, typefaceBold );
		fk.setLabels(LabelValue, LabelInfo);
		fk.setLabelType( LABEL_FLOAT );

		FKnobs.add(fk);
	}

	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _r
	 *		  radius of gui element
	 * @param _vMin
	 *		  minimum value of gui element
	 * @param _vMax
	 *		  maximum value of gui element
	 * @param _val
	 *		  initial value of gui element
	 *		  
	 */
	public void addKnob(String _name, float _x, float _y, int _r, float _vMin, float _vMax, float _val) {
		addKnob(_name, _x,_y, _r, _vMin,_vMax, _val, LABEL_FLOAT);
	}

	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _r
	 *		  radius of gui element
	 *		  
	 */
	public void addKnob(String _name, float _x, float _y, int _r) {
		addKnob(_name, _x,_y, _r, 0.0f,1.0f, 0.0f, LABEL_FLOAT);
	}

	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _r
	 *		  radius of gui element
	 * @param _vMin
	 *		  minimum value of gui element
	 * @param _vMax
	 *		  maximum value of gui element
	 * @param _val
	 *		  initial value of gui element
	 *		  
	 */
	public void addKnob(String _name, int _series, float _x, float _y, int _r, float _vMin, float _vMax, float _val) {
		addKnob(_name + Integer.toString(_series), _x,_y, _r, _vMin,_vMax, _val, LABEL_FLOAT);
	}

	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _r
	 *		  radius of gui element
	 * @param _vMin
	 *		  minimum value of gui element
	 * @param _vMax
	 *		  maximum value of gui element
	 * @param _val
	 *		  initial value of gui element
	 * @param _labelType
	 *		  display type of value LABEL_FLOAT or LABEL_INT
	 *		  
	 */
	public void addKnob(String _name, int _series, float _x, float _y, int _r, float _vMin, float _vMax, float _val, int _labelType) {
		addKnob(_name + Integer.toString(_series), _x,_y, _r, _vMin,_vMax, _val, _labelType);
	}


	//-----------------------------------------
	// FDropDown
	//-----------------------------------------
	// TODO: fix buggy click

	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _w
	 *		  width of gui element
	 * @param _h
	 *		 height of gui element
	 * @param items
	 *		 array of menu items
	 *		 
	 */
	public void addDropDown(String _name, float _x, float _y, int _w, int _h, String[] items) {
		FDropDown fd = new FDropDown(p5);
		fd.setName(_name);
		fd.setPos(_x, _y);
		fd.setSize(_w, _h);

		fd.addItem(items);

		FLabel LabelValue = new FLabel(p5);
		FLabel LabelInfo = new FLabel(p5);
		LabelValue.setTypeface( typefaceReg, typefaceBold );
		LabelInfo.setTypeface( typefaceReg, typefaceBold );
		fd.setLabels(LabelValue, LabelInfo);
		fd.setLabelType( LABEL_FLOAT );

		FDropDowns.add(fd);
	}

	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _w
	 *		  width of gui element
	 * @param _h
	 *		 height of gui element
	 * @param items
	 *		 array of menu items
	 * @param selected
	 *		 initial value
	 *		 
	 */
	public void addDropDown(String _name, float _x, float _y, int _w, int _h, String[] items, String selected) {
		FDropDown fd = new FDropDown(p5, _name, _x,_y, _w,_h, items, selected);	

		FLabel LabelValue = new FLabel(p5);
		FLabel LabelInfo = new FLabel(p5);
		LabelValue.setTypeface( typefaceReg, typefaceBold );
		LabelInfo.setTypeface( typefaceReg, typefaceBold );
		fd.setLabels(LabelValue, LabelInfo);
		fd.setLabelType( LABEL_STRING );

		FDropDowns.add(fd);
	}

	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _w
	 *		  width of gui element
	 * @param _h
	 *		 height of gui element
	 * @param items
	 *		 array of menu items
	 *		 
	 */
	public void addDropDown(String _name, int _series, float _x, float _y, int _w, int _h, String[] items) {
		addDropDown(_name + Integer.toString(_series), _x,_y, _w,_h, items);
	}

	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _w
	 *		  width of gui element
	 * @param _h
	 *		 height of gui element
	 * @param items
	 *		 array of menu items
	 * @param selected
	 *		 initial value
	 *		 
	 */
	public void addDropDown(String _name, int _series, float _x, float _y, int _w, int _h, String[] items, String selected) {
		addDropDown(_name + Integer.toString(_series), _x,_y, _w,_h, items, selected);
	}	


	//-----------------------------------------
	// FLabel
	//-----------------------------------------
	/**
	 * @param _text
	 *		  the text to display
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 *		 
	 */
	public void addLabel(String _text, float _x, float _y) {
		FLabel fl = new FLabel(p5);
		fl.setText(_text);
		fl.setPos(_x, _y);
		fl.setTypeface( typefaceBold );

		FLabels.add(fl);
	}	

	/**
	 * @param _text
	 *		  the text to display
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _typefaceLabel
	 * 		  the typeface to use for the label
	 *		 
	 */
	public void addLabel(String _text, float _x, float _y, PFont _typefaceLabel) {
		FLabel fl = new FLabel(p5);
		fl.setText(_text);
		fl.setPos(_x, _y);
		fl.setTypeface( _typefaceLabel );

		FLabels.add(fl);
	}	



	//-----------------------------------------
	// FInputField
	//-----------------------------------------
	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _w
	 *		  width of gui element
	 * @param _h
	 *		 height of gui element
	 *		 
	 */
	public void addInputField(String _name, float _x, float _y, int _w, int _h) {
		System.out.println("addInputField");
		FInputField fi = new FInputField(p5);
		fi.setName(_name);
		fi.setPos(_x, _y);
		fi.setSize(_w, _h);
		fi.setTypeface( typefaceReg );

		FLabel LabelValue = new FLabel(p5);
		FLabel LabelInfo = new FLabel(p5);
		LabelValue.setTypeface( typefaceReg, typefaceBold );
		LabelInfo.setTypeface( typefaceReg, typefaceBold );

		FInputFields.add(fi);
	}	

	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _w
	 *		  width of gui element
	 * @param _h
	 *		 height of gui element
	 * @param _typeface
	 * 		typeface to use for input
	 *		 
	 */
	public void addInputField(String _name, float _x, float _y, int _w, int _h, PFont _typeface) {
		FInputField fi = new FInputField(p5);
		fi.setName(_name);
		fi.setPos(_x, _y);
		fi.setSize(_w, _h);
		fi.setTypeface(_typeface);

		FLabel LabelValue = new FLabel(p5);
		FLabel LabelInfo = new FLabel(p5);
		LabelValue.setTypeface( typefaceReg, typefaceBold );
		LabelInfo.setTypeface( typefaceReg, typefaceBold );

		FInputFields.add(fi);
	}

	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _w
	 *		  width of gui element
	 * @param _h
	 *		 height of gui element
	 * @param _t
	 *		 default string
	 *		 
	 */
	public void addInputField(String _name, float _x, float _y, int _w, int _h, String _t) {
		FInputField fi = new FInputField(p5);
		fi.setName(_name);
		fi.setPos(_x, _y);
		fi.setSize(_w, _h);
		fi.setTypeface( typefaceReg );
		fi.setString(_t);

		FLabel LabelValue = new FLabel(p5);
		FLabel LabelInfo = new FLabel(p5);
		LabelValue.setTypeface( typefaceReg, typefaceBold );
		LabelInfo.setTypeface( typefaceReg, typefaceBold );

		FInputFields.add(fi);
	}	

	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _w
	 *		  width of gui element
	 * @param _h
	 *		 height of gui element
	 * @param _t
	 *		 default string
	 * @param _typeface
	 * 		typeface to use for input
	 *		 
	 */
	public void addInputField(String _name, float _x, float _y, int _w, int _h, String _t, PFont _typeface) {
		FInputField fi = new FInputField(p5);
		fi.setName(_name);
		fi.setPos(_x, _y);
		fi.setSize(_w, _h);
		fi.setTypeface(_typeface);
		fi.setString(_t);

		FLabel LabelValue = new FLabel(p5);
		FLabel LabelInfo = new FLabel(p5);
		LabelValue.setTypeface( typefaceReg, typefaceBold );
		LabelInfo.setTypeface( typefaceReg, typefaceBold );

		FInputFields.add(fi);
	}

	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _w
	 *		  width of gui element
	 * @param _h
	 *		 height of gui element
	 *		  
	 */
	public void addInputField(String _name, int _series, float _x, float _y, int _w, int _h) {
		addInputField(_name + Integer.toString(_series), _x,_y, _w,_h);
	}

	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _w
	 *		  width of gui element
	 * @param _h
	 *		 height of gui element
	 * @param _typeface
	 * 		typeface to use for input
	 *		  
	 */
	public void addInputField(String _name, int _series, float _x, float _y, int _w, int _h, PFont _typeface) {
		addInputField(_name + Integer.toString(_series), _x,_y, _w,_h, _typeface);
	}

	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _w
	 *		  width of gui element
	 * @param _h
	 *		 height of gui element
	 * @param _t
	 *		 default string
	 *		  
	 */
	public void addInputField(String _name, int _series, float _x, float _y, int _w, int _h, String _t) {
		addInputField(_name + Integer.toString(_series), _x,_y, _w,_h, _t);
	}

	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _w
	 *		  width of gui element
	 * @param _h
	 *		 height of gui element
	 * @param _t
	 *		 default string
	 * @param _typeface
	 * 		typeface to use for input
	 *		  
	 */
	public void addInputField(String _name, int _series, float _x, float _y, int _w, int _h, String _t, PFont _typeface) {
		addInputField(_name + Integer.toString(_series), _x,_y, _w,_h, _t, _typeface);
	}



	//-----------------------------------------------------------------------------
	// methods
	//-----------------------------------------------------------------------------
	/**
	 * draw initiated gui elements
	 * 
	 */
	public void draw() {
		if(bVerbose) {
			// debug info
			System.out.println( "FButtons " + FButtons.size() );
			System.out.println( "FHandles " + FHandles.size() );
			System.out.println( "FHandle3Ds " + FHandle3Ds.size() );
			System.out.println( "FChecks " + FChecks.size() );
			System.out.println( "FSliders " + FSliders.size() );
			System.out.println( "FKnobs " + FKnobs.size() );
			System.out.println( "FDropDowns " + FDropDowns.size() );
			System.out.println( "FLabels " + FLabels.size() );
			System.out.println( "FInputFields " + FInputFields.size() );
		}


		// frameRate in title
		//		if(bShowFrameRate) {
		//			p5.frame.setTitle( Float.toString(p5.frameRate) );
		//		}


		// draw controllers
		p5.pushStyle();
		p5.rectMode(CORNER);
		p5.strokeWeight(1);
		for(FButton fb : FButtons) {
			fb.setColorOver( colorOver );
			fb.setColorPressed( colorPressed );
			fb.setColorInactive( colorInactive );
			fb.showLabels( bShowLabels );
			fb.draw();
		}
		for(FHandle fh : FHandles) {
			fh.setColorOver( colorOver );
			fh.setColorPressed( colorPressed );
			fh.setColorInactive( colorInactive );
			fh.showLabels( bShowLabels );
			fh.draw();
		}
		for(FHandle3D fh3 : FHandle3Ds) {
			fh3.setColorOver( colorOver );
			fh3.setColorPressed( colorPressed );
			fh3.setColorInactive( colorInactive );
			fh3.showLabels( bShowLabels );
			fh3.draw();
		}
		for(FCheck fc : FChecks) {
			fc.setColorOver( colorOver );
			fc.setColorPressed( colorPressed );
			fc.setColorInactive( colorInactive );
			fc.showLabels( bShowLabels );
			fc.draw();
		}
		for(FSlider fs : FSliders) {
			fs.setColorOver( colorOver );
			fs.setColorPressed( colorPressed );
			fs.setColorInactive( colorInactive );
			fs.showLabels( bShowLabels );
			fs.draw();
		}
		for(FKnob fk : FKnobs) {
			fk.setColorOver( colorOver );
			fk.setColorPressed( colorPressed );
			fk.setColorInactive( colorInactive );
			fk.showLabels( bShowLabels );
			fk.draw();
		}
		for(FDropDown fd : FDropDowns) {
			fd.setColorOver( colorOver );
			fd.setColorPressed( colorPressed );
			fd.setColorInactive( colorInactive );
			fd.showLabels( bShowLabels );
			fd.draw();
		}
		for(FLabel fl : FLabels) {
			fl.draw();
		}
		for(FInputField fi : FInputFields) {
			fi.setColorOver( colorOver );
			fi.setColorPressed( colorPressed );
			fi.setColorInactive( colorInactive );
			fi.showLabels( bShowLabels );
			fi.draw();
		}
		p5.popStyle();
		// end controllers
	}


	//-----------------------------------------------------------------------------
	/**
	 * @param val
	 *		  toggle showing value labels for all gui elements
	 */
	public void showLabels(boolean val) {
		bShowLabels = val;
	}

	/**
	 * @param val
	 *		  toggle showing value label for the gui element
	 * @param _name
	 *		  name of the gui element
	 */
	public void showLabels(boolean val, String _name) {
		/*
		for(FButton fb : FButtons) {
			FButton fb = (FButton) FButtons.get(i);
			if(_name.compareTo(fb.name) == 0) fb.showLabels( val );
		}
		for(FHandle fh : FHandles) {
			FHandle fh = (FHandle) FHandles.get(i);
			if(_name.compareTo(fh.name) == 0) fh.showLabels( val );
		}
		for(FCheck fc : FChecks) {
			FCheck fc = (FCheck) FChecks.get(i);
			if(_name.compareTo(fc.name) == 0) fc.showLabels( val );
		}
		for(FSlider fs : FSliders) {
			FSlider fs = (FSlider) FSliders.get(i);
			if(_name.compareTo(fs.name) == 0) fs.showLabels( val );
		}
		for(FKnob fk : FKnobs) {
			FKnob fk = (FKnob) FKnobs.get(i);
			if(_name.compareTo(fk.name) == 0) fk.showLabels( val );
		}
		 */
	}


	//-----------------------------------------------------------------------------
	/**
	 * show frame rate in the application title
	 */
	public void showFrameRateTitle() {
		bShowFrameRate = true;
	}

	/**
	 * @param val
	 *		  toggle showing frame rate in the application title
	 */
	public void showFrameRateTitle(boolean val) {
		bShowFrameRate = val;
	}


	//-----------------------------------------------------------------------------
	/**
	 * @param val
	 *		  toggle verbose
	 */
	public void verbose(boolean val) {
		bVerbose = val;
	}



	//-----------------------------------------------------------------------------
	// sets
	//-----------------------------------------------------------------------------
	/**
	 * @param _typeface
	 *		  set global typeface for values and labels, applies to all gui elements
	 */
	public void setTypeface(PFont _typeface) {
		typefaceReg = _typeface;
		typefaceBold = _typeface;
		//System.out.println( "FControl typeface " + typeface.getFont().getName() );
	}

	/**
	 * 
	 * @param _typeface
	 * 		  set typeface for values [0] and labels [1], applies to all gui elements
	 */
	public void setTypeface(PFont _typeface[]) {
		typefaceReg = _typeface[0];
		typefaceBold = _typeface[1];
		//System.out.println( "FControl typeface " + typeface.getFont().getName() );
	}

	/**
	 * 
	 * @param _typeface
	 * 		  set typeface for values, applies to all gui elements
	 * @param _typefaceBold
	 * 		  set typeface for labels, applies to all gui elements
	 */
	public void setTypeface(PFont _typeface, PFont _typefaceBold) {
		typefaceReg = _typeface;
		typefaceBold = _typefaceBold;
		//System.out.println( "FControl typeface " + typeface.getFont().getName() );
	}


	//-----------------------------------------------------------------------------
	/**
	 * @param _colorInactive
	 *		  set global inactive color for all gui elements
	 */
	public void setColorInactive(int _colorInactive) {
		colorInactive = _colorInactive;
	}

	/**
	 * @param _colorOver
	 *		  set global over color for all gui elements
	 */
	public void setColorOver(int _colorOver) {
		colorOver = _colorOver;
	}

	/**
	 * @param _colorPressed
	 * 		  set global pressed color for all gui elemetns
	 */
	public void setColorPressed(int _colorPressed) {
		colorPressed = _colorPressed;
	}


	//-----------------------------------------------------------------------------
	/**
	 * FButton
	 * FHandle
	 * FHandle3D
	 * FSlider
	 * FDropDown
	 * FInputField
	 * 
	 * set width,height of specific element
	 * 
	 * @param _name
	 *		  name of the gui element
	 * @param _width
	 *		  set width of gui element 
	 * @param _height
	 *		  set height of gui element 
	 */
	public void setSize(String _name, float _width, float _height) {
		for(FButton fb : FButtons) {
			if(_name.compareTo(fb.name) == 0) fb.setSize((int)_width, (int)_height);
		}
		for(FHandle fh : FHandles) {
			if(_name.compareTo(fh.name) == 0) fh.setSize((int)_width, (int)_height);
		}
		for(FHandle3D fh3 : FHandle3Ds) {
			if(_name.compareTo(fh3.name) == 0) fh3.setSize((int)_width, (int)_height);
		}
		for(FSlider fs : FSliders) {
			if(_name.compareTo(fs.name) == 0) fs.setSize((int)_width, (int)_height);
		}
		for(FDropDown fd : FDropDowns) {
			if(_name.compareTo(fd.name) == 0) fd.setSize((int)_width, (int)_height);
		}
		for(FInputField fi : FInputFields) {
			if(_name.compareTo(fi.name) == 0) fi.setSize((int)_width, (int)_height);
		}
	}

	/**
	 * FButton
	 * FHandle
	 * FHandle3D
	 * FSlider
	 * FDropDown
	 * FInputField
	 * 
	 * set width,height of series element
	 * 
	 * @param _name
	 *		  name of the gui element
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 * @param _width
	 *		  set width of gui element 
	 * @param _height
	 *		  set height of gui element 
	 */
	public void setSize(String _name, int _series, float _width, float _height) {
		setSize(_name + Integer.toString(_series), _width, _height);
	}

	/**
	 * FCheck
	 * FKnob
	 * 
	 * set radius of specfic element
	 * 
	 * @param _name
	 *		  name of the gui element
	 * @param _radius
	 *		  radius of element 
	 */
	public void setSize(String _name, float _radius) {
		for(FCheck fc : FChecks) {
			if(_name.compareTo(fc.name) == 0) fc.setSize((int)_radius,(int)_radius);
		}
		for(FKnob fk : FKnobs) {
			if(_name.compareTo(fk.name) == 0) fk.setSize((int)_radius,(int)_radius);
		}
	}

	/**
	 * FCheck
	 * FKnob
	 * 
	 * set radius of series element
	 * 
	 * @param _name
	 *		  name of the gui element
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 * @param _radius
	 *		  radius of element 
	 */
	public void setSize(String _name, int _series, float _radius) {
		setSize(_name + Integer.toString(_series), _radius);
	}

	//-----------------------------------------------------------------------------
	/**
	 * FButton
	 * FHandle
	 * FSliders
	 * FDropDowns
	 * FCheck
	 * FKnob
	 * FLabel
	 * FInputField
	 * 
	 * set x,y position of specfic element
	 * 
	 * @param _name
	 *		  name of the gui element
	 * @param _x
	 *		  set x position of gui element 
	 * @param _y
	 *		  set y position of gui element 
	 */
	public void setPos(String _name, float _x, float _y) {
		for(FButton fb : FButtons) {
			if(_name.compareTo(fb.name) == 0) fb.setPos((int)_x, (int)_y);
		}
		for(FHandle fh : FHandles) {
			if(_name.compareTo(fh.name) == 0) fh.setPos((int)_x, (int)_y);
		}
		for(FSlider fs : FSliders) {
			if(_name.compareTo(fs.name) == 0) fs.setPos((int)_x, (int)_y);
		}
		for(FDropDown fd : FDropDowns) {
			if(_name.compareTo(fd.name) == 0) fd.setPos((int)_x, (int)_y);
		}
		for(FCheck fc : FChecks) {
			if(_name.compareTo(fc.name) == 0) fc.setPos((int)_x,(int)_y);
		}
		for(FKnob fk : FKnobs) {
			if(_name.compareTo(fk.name) == 0) fk.setPos((int)_x,(int)_y);
		}
		for(FLabel fl : FLabels) {
			if(_name.compareTo(fl.text) == 0) fl.setPos((int)_x,(int)_y);
		}
		for(FInputField fi : FInputFields) {
			if(_name.compareTo(fi.name) == 0) fi.setPos((int)_x,(int)_y);
		}
	}

	/**
	 * FButton
	 * FHandle
	 * FSliders
	 * FDropDowns
	 * FCheck
	 * FKnob
	 * FInputField
	 * 
	 * set x,y position of series element
	 * 
	 * @param _name
	 *		  name of the gui element
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 * @param _x
	 *		  set x position of gui element 
	 * @param _y
	 *		  set y position of gui element 
	 */
	public void setPos(String _name, int _series, float _x, float _y) {
		setPos(_name + Integer.toString(_series), _x,_y);
	}


	/**
	 * FHandle3D
	 * 
	 * set x,y,z position of specific FHandle3D element
	 * 
	 * @param _name
	 *		  name of the gui element
	 * @param _x
	 *		  set x position of gui element 
	 * @param _y
	 *		  set y position of gui element 
	 * @param _z
	 *		  set z position of gui element 
	 */
	public void setPos(String _name, float _x, float _y, float _z) {
		for(FHandle3D fh3 : FHandle3Ds) {
			if(_name.compareTo(fh3.name) == 0) fh3.setPos((int)_x, (int)_y, (int)_z);
		}
	}

	/**
	 * FHandle3D
	 * 
	 * set x,y,z position of series FHandle3D element
	 * 
	 * @param _name
	 *		  name of the gui element
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 * @param _x
	 *		  set x position of gui element 
	 * @param _y
	 *		  set y position of gui element 
	 * @param _z
	 *		  set z position of gui element 
	 */
	public void setPos(String _name, int _series, float _x, float _y, float _z) {
		setPos(_name + Integer.toString(_series), _x,_y,_z);
	}


	//-----------------------------------------------------------------------------
	/**
	 * FHandle
	 * FHandle3D
	 * 
	 * single element
	 * 
	 * @param _name
	 *		  name of the gui element
	 * @param _val
	 *		  set element PVector value 
	 */
	public void setValue(String _name, PVector _val) {
		for(FHandle fh : FHandles) {
			if(_name.compareTo(fh.name) == 0) fh.setPos(_val.x,_val.y);
		}
		for(FHandle3D fh3 : FHandle3Ds) {
			if(_name.compareTo(fh3.name) == 0) fh3.setPos(_val.x,_val.y,_val.z);
		}
	}


	/**
	 * FHandle
	 * FHandle3D
	 * 
	 * series elements
	 * 
	 * @param _name
	 *		  name of the gui element
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 * @param _val
	 *		  set element PVector value 
	 */
	public void setValue(String _name, int _series, PVector _val) {
		String seriesName = _name + Integer.toString(_series);
		for(FHandle fh : FHandles) {
			if(seriesName.compareTo(fh.name) == 0) fh.setPos(_val.x,_val.y);
		}
		for(FHandle3D fh3 : FHandle3Ds) {
			if(seriesName.compareTo(fh3.name) == 0) fh3.setPos(_val.x,_val.y,_val.z);
		}
	}


	//-----------------------------------------------------------------------------
	/**
	 * FCheck
	 * 
	 * single element
	 * 
	 * @param _name
	 *		  name of the gui element
	 * @param _val
	 *		  set element boolean value 
	 */
	public void setValue(String _name, boolean _val) {
		for(FCheck fc : FChecks) {
			if(_name.compareTo(fc.name) == 0) fc.setValue(_val);
		}
	}

	/**
	 * FCheck
	 * 
	 * series elements
	 * 
	 * @param _name
	 *		  name of the gui element
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 * @param _val
	 *		  set element boolean value 
	 */
	public void setValue(String _name, int _series, boolean _val) {
		setValue(_name + Integer.toString(_series), _val);
	}



	//-----------------------------------------------------------------------------
	/**
	 * FSlider
	 * FKnob
	 * 
	 * set the loose for FSlider and FKnob elements
	 * 
	 * @param _val
	 *		  set loosenes of the sliding mechanism (globally for every slider and knob) 
	 */
	public void setLoose(int _val) {
		for(FSlider fs : FSliders) {
			fs.setLoose(_val);
		}
		for(FKnob fk : FKnobs) {
			fk.setLoose(_val);
		}
	}

	//-----------------------------------------------------------------------------
	/**
	 * FSlider
	 * FKnob
	 * 
	 * single element
	 * 
	 * @param _name
	 *		  name of the gui element
	 * @param _val
	 *		  set element int value 
	 */
	public void setValue(String _name, int _val) {
		for(FSlider fs : FSliders) {
			if(_name.compareTo(fs.name) == 0) fs.setValue(_val);
		}
		for(FKnob fk : FKnobs) {
			if(_name.compareTo(fk.name) == 0) fk.setValue(_val);
		}
	}

	/**
	 * FSlider
	 * FKnob
	 * 
	 * single element
	 * 
	 * @param _name
	 *		  name of the gui element
	 * @param _val
	 *		  set element float value 
	 */
	public void setValue(String _name, float _val) {
		for(FSlider fs : FSliders) {
			if(_name.compareTo(fs.name) == 0) fs.setValue(_val);
		}
		for(FKnob fk : FKnobs) {
			if(_name.compareTo(fk.name) == 0) fk.setValue(_val);
		}
	}	

	/**
	 * FSlider
	 * FKnob
	 * 
	 * series elements
	 * 
	 * @param _name
	 *		  name of the gui element
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 * @param _val
	 *		  set element int value 
	 */
	public void setValue(String _name, int _series, int _val) {
		setValue(_name + Integer.toString(_series), _val);
	}

	/**
	 * FSlider
	 * FKnob
	 * 
	 * series elements
	 * 
	 * @param _name
	 *		  name of the gui element
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 * @param _val
	 *		  set element float/int value 
	 */
	public void setValue(String _name, int _series, float _val) {
		setValue(_name + Integer.toString(_series), _val);
	}


	//-----------------------------------------------------------------------------
	/**
	 * FSlider
	 * FKnob
	 * 
	 * single element
	 * 
	 * @param _name
	 *		  name of the gui element
	 * @param _vMin
	 *		  minimum value of gui element
	 * @param _vMax
	 *		  maximum value of gui element
	 */
	public void setValueRange(String _name, float _vMin, float _vMax) {
		for(FSlider fs : FSliders) {
			if(_name.compareTo(fs.name) == 0) fs.setValueRange(_vMin,_vMax);
		}
		for(FKnob fk : FKnobs) {
			if(_name.compareTo(fk.name) == 0) fk.setValueRange(_vMin,_vMax);
		}
	}

	/**
	 * FSlider
	 * FKnob
	 * 
	 * series elements
	 * 
	 * @param _name
	 *		  name of the gui element
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 * @param _vMin
	 *		  minimum value of gui element
	 * @param _vMax
	 *		  maximum value of gui element
	 */
	public void setValueRange(String _name, int _series, float _vMin, float _vMax) {
		setValueRange(_name + Integer.toString(_series), _vMin,_vMax);
	}


	//-----------------------------------------------------------------------------
	/**
	 * FDropDown
	 * 
	 * single element
	 * 
	 * @param _name
	 *		  name of the gui element
	 * @param _nameItem
	 *		  name of the added menu item
	 *		  
	 */
	public void addItem(String _name, String _nameItem) {
		for(FDropDown fd : FDropDowns) {
			if(_name.compareTo(fd.name) == 0) fd.addItem(_nameItem);
		}
	}

	/**
	 * FDropDown
	 * 
	 * series element
	 * 
	 * @param _name
	 *		  name of the gui element
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 * @param _nameItem
	 *		  name of the added menu item
	 *		  
	 */
	public void addItem(String _name, int _series, String _nameItem) {
		addItem(_name + Integer.toString(_series), _nameItem);
	}


	//-----------------------------------------------------------------------------
	/**
	 * FInputField
	 * 
	 * single element
	 * set typeface for FInputField to display
	 * 
	 * @param _name
	 * 		  name of the gui element
	 * @param _typeface
	 * 		  typeface to display
	 * 
	 */
	public void setTypeface(String _name, PFont _typeface) {
		for(FInputField fi : FInputFields) {
			if(_name.compareTo(fi.name) == 0) fi.setTypeface(_typeface);
		}
	}

	/**
	 * FInputField
	 * 
	 * series element
	 * set typeface for FInputField to display
	 * 
	 * @param _name
	 * 		  name of the gui element
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 * @param _typeface
	 * 		  typeface to display
	 * 
	 */
	public void setTypeface(String _name, int _series, PFont _typeface) {
		setTypeface(_name + Integer.toString(_series), _typeface);
	}

	//-----------------------------------------------------------------------------
	/**
	 * FHandle
	 * FSlider
	 * FKnob
	 * 
	 * single element
	 * 
	 * @param _name
	 *		  name of the gui element
	 * @param _val
	 *		  increment value
	 */
	public void enableSnap(String _name, float _val) {
		for(FHandle fh : FHandles) {
			if(_name.compareTo(fh.name) == 0) fh.enableSnap(_val);
		}
		for(FSlider fs : FSliders) {
			if(_name.compareTo(fs.name) == 0) fs.enableSnap(_val);
		}
		for(FKnob fk : FKnobs) {
			if(_name.compareTo(fk.name) == 0) fk.enableSnap(_val);
		}
	}

	/**
	 * FHandle
	 * FSlider
	 * FKnob
	 * 
	 * series element
	 * 
	 * @param _name
	 *		  name of the gui element
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 * @param _val
	 *		  increment value
	 */
	public void enableSnap(String _name, int _series, float _val) {
		enableSnap(_name + Integer.toString(_series), _val);
	}	

	/**
	 * FHandle
	 * FSlider
	 * FKnob
	 * 
	 * single element
	 * 
	 * @param _name
	 *		  name of the gui element
	 */
	public void disableSnap(String _name) {
		for(FHandle fh : FHandles) {
			if(_name.compareTo(fh.name) == 0) fh.disableSnap();
		}
		for(FSlider fs : FSliders) {
			if(_name.compareTo(fs.name) == 0) fs.disableSnap();
		}
		for(FKnob fk : FKnobs) {
			if(_name.compareTo(fk.name) == 0) fk.disableSnap();
		}
	}

	/**
	 * FHandle
	 * FSlider
	 * FKnob
	 * 
	 * series element
	 * 
	 * @param _name
	 *		  name of the gui element
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 */
	public void disableSnap(String _name, int _series) {
		disableSnap(_name + Integer.toBinaryString(_series));
	}


	//-----------------------------------------------------------------------------
	/**
	 * FHandles
	 * FHandles3D
	 * FSlider
	 * FKnob
	 * 
	 * global
	 * 
	 * @param _labelType
	 *		  increment value
	 *		  LABEL_FLOAT displays value as a 2 decimal float
	 *		  LABEL_INT displays value as an integer
	 *		  LABEL_STRING displays values as a string
	 */
	public void setLabelType(int _labelType) {
		for(FHandle fh : FHandles) {
			fh.setLabelType(_labelType);
		}
		for(FHandle3D fh3 : FHandle3Ds) {
			fh3.setLabelType(_labelType);
		}
		for(FSlider fs : FSliders) {
			fs.setLabelType(_labelType);
		}
		for(FKnob fk : FKnobs) {
			fk.setLabelType(_labelType);
		}
	}

	/**
	 * FHandles
	 * FHandles3D
	 * FSlider
	 * FKnob
	 * 
	 * single element
	 * 
	 * @param _name
	 *		  name of the gui element 
	 * @param _labelType
	 *		  increment value
	 *		  LABEL_FLOAT displays value as a 2 decimal float
	 *		  LABEL_INT displays value as an integer
	 *		  LABEL_STRING displays values as a string
	 */
	public void setLabelType(String _name, int _labelType) {
		for(FHandle fh : FHandles) {
			if(_name.compareTo(fh.name) == 0) fh.setLabelType(_labelType);
		}
		for(FHandle3D fh3 : FHandle3Ds) {
			if(_name.compareTo(fh3.name) == 0) fh3.setLabelType(_labelType);
		}
		for(FSlider fs : FSliders) {
			if(_name.compareTo(fs.name) == 0) fs.setLabelType(_labelType);
		}
		for(FKnob fk : FKnobs) {
			if(_name.compareTo(fk.name) == 0) fk.setLabelType(_labelType);
		}
	}

	/**
	 * FHandles
	 * FHandles3D
	 * FSlider
	 * FKnob
	 * 
	 * series element
	 * 
	 * @param _name
	 *		  name of the gui element 
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 * @param _labelType
	 *		  increment value
	 *		  LABEL_FLOAT displays value as a 2 decimal float
	 *		  LABEL_INT displays value as an integer
	 *		  LABEL_STRING displays values as a string
	 */
	public void setLabelType(String _name, int _series, int _labelType) {
		setLabelType(_name + Integer.toString(_series), _labelType);
	}


	//-----------------------------------------------------------------------------
	/**
	 * FButton
	 * 
	 * single element
	 * 
	 * @param _name
	 *		  name of the gui element 
	 * @param img
	 * 			sprite source image
	 * 			loading a sprite (vertical)
	 * 			|------|------|------|
	 *			|-[0]U-|-[1]O-|-[2]D-|
	 * 			|------|------|------|
	 * 			up, over, down
	 * @param _sw
	 * 			sprite width (individual)
	 * @param _sh
	 * 			sprite height (individual)
	 * 
	 */
	public void setSprite(String _name, PImage img, int _sw, int _sh) {
		for(FButton fb : FButtons) {
			if(_name.compareTo(fb.name) == 0) fb.setSprite(img, _sw,_sh);
		}
	}

	/**
	 * FButton
	 * 
	 * series element
	 * 
	 * @param _name
	 *		  name of the gui element 
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 * @param img
	 * 			sprite source image
	 * 			loading a sprite (vertical)
	 * 			|------|------|------|
	 *			|-[0]U-|-[1]O-|-[2]D-|
	 * 			|------|------|------|
	 * 			up, over, down
	 * @param _sw
	 * 			sprite width (individual)
	 * @param _sh
	 * 			sprite height (individual)
	 * 
	 */
	public void setSprite(String _name, int _series, PImage img, int _sw, int _sh) {
		setSprite(_name + Integer.toString(_series), img, _sw,_sh);
	}


	//-----------------------------------------------------------------------------
	/**
	 * FLabel
	 * FInputField
	 * 
	 * single element
	 * underline specific input field
	 * 
	 * @param _name
	 *		  name of the gui element 
	 * @param _val
	 * 		  toggle underline
	 *
	 */
	public void underline(String _name, boolean _val) {
		for(FInputField fi : FInputFields) {
			if(_name.compareTo(fi.name) == 0) fi.underline(_val);
			//else return null;
		}
	}

	/**
	 * FLabel
	 * FInputField
	 * 
	 * series element
	 * underline specific input field
	 * 
	 * @param _name
	 *		  name of the gui element 
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 * @param _val
	 * 		  toggle underline
	 *
	 */
	public void underline(String _name, int _series, boolean _val) {
		underline(_name + Integer.toString(_series), _val);
	}

	/**
	 * FLabel
	 * FInputField
	 * 
	 * underline all input fields
	 * 
	 * @param _val
	 * 		  toggle underline
	 *
	 */
	public void underline(boolean _val) {
		for(FLabel fl : FLabels) fl.underline(_val);
		for(FInputField fi : FInputFields) fi.underline(_val);
	}


	//-----------------------------------------------------------------------------
	/**
	 * FLabel
	 * FInputField
	 * 
	 * toggle container around specific input field/label
	 * 
	 * @param _name
	 *		  name of the gui element 
	 * @param _val
	 *		  container toggle value 
	 *
	 */
	public void container(String _name, boolean _val) {
		for(FLabel fl : FLabels) {
			if(_name.compareTo(fl.text) == 0) fl.container(_val);
			//else return null;
		}
		for(FInputField fi : FInputFields) {
			if(_name.compareTo(fi.name) == 0) fi.container(_val);
			//else return null;
		}
	}

	/**
	 * FLabel
	 * FInputField
	 * 
	 * toggle container around specific input field/label
	 * 
	 * @param _name
	 *		  name of the gui element 
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 * @param _val
	 *		  container toggle value 
	 *
	 */
	public void container(String _name, int _series, boolean _val) {
		container(_name + Integer.toString(_series), _val);
	}

	/**
	 * FInputField
	 * 
	 * toggle container around all input fields
	 * 
	 * @param _val
	 *		  container toggle value 
	 *
	 */
	public void container(boolean _val) {
		for(FInputField fi : FInputFields) fi.container(_val);
	}	

	//-----------------------------------------------------------------------------
	/**
	 * FInputField
	 * 
	 * toggle caps around specific input field
	 * 
	 * @param _name
	 *		  name of the gui element 
	 * @param _val
	 *		  caps toggle value 
	 *
	 */
	public void caps(String _name, boolean _val) {
		for(FInputField fi : FInputFields) {
			if(_name.compareTo(fi.name) == 0) fi.caps(_val);
			//else return null;
		}
	}

	/**
	 * FInputField
	 * 
	 * toggle caps around specific input field
	 * 
	 * @param _name
	 *		  name of the gui element 
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 * @param _val
	 *		  caps toggle value 
	 *
	 */
	public void caps(String _name, int _series, boolean _val) {
		caps(_name + Integer.toString(_series), _val);
	}

	/**
	 * FInputField
	 * 
	 * toggle caps around all input fields
	 * 
	 * @param _val
	 *		  caps toggle value 
	 *
	 */
	public void caps(boolean _val) {
		for(FInputField fi : FInputFields) fi.caps(_val);
	}	


	//-----------------------------------------------------------------------------
	// gets
	//-----------------------------------------------------------------------------
	/**
	 * an audit of elements 
	 * 
	 */
	public void listElements() {
		System.out.println( "FButtons " + FButtons.size() );
		System.out.println( "FHandles " + FHandles.size() );
		System.out.println( "FHandle3Ds " + FHandle3Ds.size() );
		System.out.println( "FChecks " + FChecks.size() );
		System.out.println( "FSliders " + FSliders.size() );
		System.out.println( "FKnobs " + FKnobs.size() );
		System.out.println( "FDropDowns " + FDropDowns.size() );
		System.out.println( "FLabels" + FLabels.size());
		System.out.println( "FInputFields " + FInputFields.size() );
	}
	/**
	 * @return total number of elements 
	 * 
	 */
	public int totalElements() {
		return 
		FButtons.size() + 
		FHandles.size() + 
		FHandle3Ds.size() + 
		FChecks.size() + 
		FSliders.size() + 
		FKnobs.size() + 
		FDropDowns.size() + 
		FLabels.size() +
		FInputFields.size(); 
	}

	/**
	 * @return an ArrayList of all FButton elements
	 * 
	 */
	public ArrayList<FButton> getButtons() {
		return FButtons;
	}
	/**
	 * @return an ArrayList of all FHandle elements
	 * 
	 */
	public ArrayList<FHandle> getHandles() {
		return FHandles;
	}
	/**
	 * @return an ArrayList of all FHandle3D elements
	 * 
	 */
	public ArrayList<FHandle3D> getHandle3Ds() {
		return FHandle3Ds;
	}
	/**
	 * @return an ArrayList of all FCheck elements
	 * 
	 */
	public ArrayList<FCheck> getChecks() {
		return FChecks;
	}
	/**
	 * @return an ArrayList of all FSlider elements 
	 * 
	 */
	public ArrayList<FSlider> getSliders() {
		return FSliders;
	}
	/**
	 * @return an ArrayList of all FKnob elements 
	 * 
	 */
	public ArrayList<FKnob> getKnobs() {
		return FKnobs;
	}
	/**
	 * @return an ArrayList of all FDropDown elements 
	 * 
	 */
	public ArrayList<FDropDown> getDropDowns() {
		return FDropDowns;
	}
	/**
	 * @return an ArrayList of all FLabel elements
	 * 
	 */
	public ArrayList<FLabel> getLabels() {
		return FLabels;
	}
	/**
	 * @return an ArrayList of all FInputField elements
	 * 
	 */
	public ArrayList<FInputField> getInputFields() {
		return FInputFields;
	}


	//-----------------------------------------------------------------------------
	/**
	 * return inactive color (as int) 
	 * 
	 */
	public int getColorInactive() {
		return colorInactive;
	}
	/**
	 * return over color (as int) 
	 * 
	 */
	public int getColorOver() {
		return colorOver;
	}
	/**
	 * return pressed color (as int) 
	 * 
	 */
	public int getColorPressed() {
		return colorPressed;
	}


	//-----------------------------------------------------------------------------
	/**
	 * FHandle
	 * FHandle3D
	 * FCheck
	 * FSlider
	 * FKnob
	 * FButton
	 * FDropDown
	 * FLabel
	 * FInputField
	 * 
	 * single element
	 * 
	 * return PVector value of gui element 
	 * 
	 * @param _name
	 *		  name of the gui element 
	 *		  
	 */
	public PVector getPos(String _name) {
		PVector vector = new PVector();

		for(FHandle fh : FHandles) {
			if(_name.compareTo(fh.name) == 0) vector = fh.getPos();
		}
		for(FHandle3D fh3 : FHandle3Ds) {
			if(_name.compareTo(fh3.name) == 0) vector = fh3.getPos();
		}
		for(FCheck fc : FChecks) {
			if(_name.compareTo(fc.name) == 0) vector = fc.getPos();
		}
		for(FSlider fs : FSliders) {
			if(_name.compareTo(fs.name) == 0) vector = fs.getPos();
		}
		for(FKnob fk : FKnobs) {
			if(_name.compareTo(fk.name) == 0) vector = fk.getPos();
		}
		for(FButton fb : FButtons) {
			if(_name.compareTo(fb.name) == 0) vector = fb.getPos();
		}
		for(FDropDown fd : FDropDowns) {
			if(_name.compareTo(fd.name) == 0) vector = fd.getPos();
		}
		for(FLabel fl : FLabels) {
			if(_name.compareTo(fl.text) == 0) vector = fl.getPos();
		}
		for(FInputField fi : FInputFields) {
			if(_name.compareTo(fi.name) == 0) vector = fi.getPos();
		}

		return vector;
	}


	/**
	 * FHandle
	 * FHandle3D
	 * FCheck
	 * FSlider
	 * FKnob
	 * FButton
	 * FDropDown
	 * FLabel
	 * FInputField
	 * 
	 * series elements
	 * 
	 * return PVector value of gui element 
	 * 
	 * @param _name
	 *		  name of the gui element 
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 *		  
	 */
	public PVector getPos(String _name, int _series) {
		return getPos(_name + Integer.toString(_series));
	}


	//-----------------------------------------------------------------------------
	/**
	 * FHandle
	 * FHandle3D
	 * FCheck
	 * FSlider
	 * FKnob
	 * FButton
	 * FDropDown
	 * FLabel
	 * FInputField
	 * 
	 * single element
	 * 
	 * return Dimension of gui element 
	 * (i.e. int h = getSize("_name").height; ) 
	 * 
	 * @param _name
	 *		  name of the gui element 
	 *		  
	 */
	public Dimension getSize(String _name) {
		Dimension dimension = new Dimension();

		for(FHandle fh : FHandles) {
			if(_name.compareTo(fh.name) == 0) dimension = fh.getSize();
		}
		for(FHandle3D fh3 : FHandle3Ds) {
			if(_name.compareTo(fh3.name) == 0) dimension = fh3.getSize();
		}
		for(FCheck fc : FChecks) {
			if(_name.compareTo(fc.name) == 0) dimension = fc.getSize();
		}
		for(FSlider fs : FSliders) {
			if(_name.compareTo(fs.name) == 0) dimension = fs.getSize();
		}
		for(FKnob fk : FKnobs) {
			if(_name.compareTo(fk.name) == 0) dimension = fk.getSize();
		}
		for(FButton fb : FButtons) {
			if(_name.compareTo(fb.name) == 0) dimension = fb.getSize();
		}
		for(FDropDown fd : FDropDowns) {
			if(_name.compareTo(fd.name) == 0) dimension = fd.getSize();
		}
		for(FLabel fl : FLabels) {
			if(_name.compareTo(fl.text) == 0) dimension = fl.getSize();
		}
		for(FInputField fi : FInputFields) {
			if(_name.compareTo(fi.name) == 0) dimension = fi.getSize();
		}

		return dimension;
	}

	/**
	 * FHandle
	 * FHandle3D
	 * FCheck
	 * FSlider
	 * FKnob
	 * FButton
	 * FDropDown
	 * FLabel
	 * FInputField
	 * 
	 * series elements
	 * 
	 * return Dimension of gui element 
	 * (i.e. int h = getSize("_name", series).height; ) 
	 * 
	 * @param _name
	 *		  name of the gui element 
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 *		  
	 */
	public Dimension getSize(String _name, int _series) {
		return getSize(_name + Integer.toString(_series));
	}	


	//-----------------------------------------------------------------------------
	/**
	 * FCheck
	 * 
	 * single element
	 * 
	 * return boolean value of gui element 
	 * 
	 * @param _name
	 *		  name of the gui element 
	 *		  
	 */
	public boolean getBoolValue(String _name) {
		for(FCheck fc : FChecks) {
			if(_name.compareTo(fc.name) == 0) boolVal = fc.getValue();
			//else bool = null;
		}
		return boolVal;
	}

	/**
	 * FCheck
	 * 
	 * return boolean value of gui element 
	 * 
	 * @param _name
	 *		  name of the gui element 
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 *		  
	 */
	public boolean getBoolValue(String _name, int _series) {
		return getBoolValue( _name + Integer.toString(_series));
	}


	//-----------------------------------------------------------------------------
	/**
	 * FCheck
	 * FSlider
	 * FKnob
	 * 
	 * single element
	 * 
	 * return int value of gui element 
	 * 
	 * @param _name
	 *		  name of the gui element
	 *
	 * @return intVal
	 *		  
	 */
	public int getIntValue(String _name) {
		for(FCheck fc : FChecks) {
			if(_name.compareTo(fc.name) == 0) intVal = ( fc.getValue() )?1:0;
			//else bool = null;
		}
		for(FSlider fs : FSliders) {
			if(_name.compareTo(fs.name) == 0) intVal = fs.getIntValue();
			//else return null;
		}
		for(FKnob fk : FKnobs) {
			if(_name.compareTo(fk.name) == 0) intVal = fk.getIntValue();
			//else return null;
		}

		return intVal;
	}

	/**
	 * FCheck
	 * FSlider
	 * FKnob
	 * 
	 * return int value of gui element 
	 * 
	 * @param _name
	 *		  name of the gui element 
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 *
	 * @return int value
	 *		  
	 */
	public int getIntValue(String _name, int _series) {
		return getIntValue(_name + Integer.toString(_series));
	}	


	//-----------------------------------------------------------------------------
	/**
	 * FSlider
	 * FKnob
	 * 
	 * single element
	 * 
	 * return float value of gui element 
	 * 
	 * @param _name
	 *		  name of the gui element
	 *
	 * @return float value
	 *		  
	 */
	public float getFloatValue(String _name) {
		for(FSlider fs : FSliders) {
			if(_name.compareTo(fs.name) == 0) floatVal = fs.getFloatValue();
			//else return null;
		}
		for(FKnob fk : FKnobs) {
			if(_name.compareTo(fk.name) == 0) floatVal = fk.getFloatValue();
			//else return null;
		}

		return floatVal;
	}

	/**
	 * FSlider
	 * FKnob
	 * 
	 * return float value of gui element 
	 * 
	 * @param _name
	 *		  name of the gui element 
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 *
	 * @return float value
	 *		  
	 */
	public float getFloatValue(String _name, int _series) {
		return getFloatValue(_name + Integer.toString(_series));
	}


	//-----------------------------------------------------------------------------
	/**
	 * FCheck
	 * FSlider
	 * FKnob
	 * FLabel
	 * FInputField
	 * 
	 * single element
	 * 
	 * @param _name
	 *		  name of the gui element 
	 * @return
	 * 		  String value of gui element
	 */
	public String getStringValue(String _name) {
		for(FCheck fc : FChecks) {
			if(_name.compareTo(fc.name) == 0) strVal = ( fc.getValue() )? "true":"false";
			//else return null;
		}
		for(FSlider fs : FSliders) {
			if(_name.compareTo(fs.name) == 0) strVal = Float.toString( fs.getFloatValue() );
			//else return null;
		}
		for(FKnob fk : FKnobs) {
			if(_name.compareTo(fk.name) == 0) strVal = Float.toString( fk.getFloatValue() );
			//else return null;
		}
		for(FLabel fl : FLabels) {
			if(_name.compareTo(fl.text) == 0) strVal = fl.toString();
			//else return null;
		}
		for(FInputField fi : FInputFields) {
			if(_name.compareTo(fi.name) == 0) strVal = fi.getValue();
			//else return null;
		}

		return strVal;
	}


	/**
	 * FCheck
	 * FSlider
	 * FKnob
	 * FLabel
	 * FInputField
	 * 
	 * series element
	 * 
	 * @param _name
	 *		  name of the gui element 
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 * @return
	 * 		  String value of gui element
	 */
	public String getStringValue(String _name, int _series) {
		return getStringValue(_name + Integer.toString(_series));
	}


	//-----------------------------------------------------------------------------
	/**
	 * FDropDown
	 * 
	 * single element
	 * return the selection from FDropDown 
	 * 
	 * @param _name
	 *		  name of the dropdown gui element
	 *
	 * @return
	 * 		  String (name) of the element selected
	 *		  
	 */
	public String getSelection(String _name) {
		for(FDropDown fd : FDropDowns) {
			if(_name.compareTo(fd.name) == 0) selStrVal = fd.getSelection();
		}
		return selStrVal;
	}

	/**
	 * FDropDown
	 * 
	 * series element
	 * return the selection from FDropDown 
	 * 
	 * @param _name
	 *		  name of the dropdown gui element
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 *
	 * @return
	 * 		  String (name) of the element selected
	 *		  
	 */
	public String getSelection(String _name, int _series) {
		return getSelection(_name + Integer.toString(_series));
	}

	/**
	 * FDropDown
	 * 
	 * single element
	 * return the selection from FDropDown 
	 * 
	 * @param _name
	 *		  name of the dropdown gui element
	 *
	 * @return
	 * 		  int (index) of the element selected
	 *		  
	 */
	public int getSelectionIndex(String _name) {
		for(FDropDown fd : FDropDowns) {
			if(_name.compareTo(fd.name) == 0) selIntVal = fd.getSelectionIndex();
		}
		return selIntVal;
	}

	/**
	 * FDropDown
	 * 
	 * series element
	 * return the selection from FDropDown 
	 * 
	 * @param _name
	 *		  name of the dropdown gui element
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 *
	 * @return
	 * 		  int (index) of the element selected
	 *		  
	 */
	public int getSelectionIndex(String _name, int _series) {
		return getSelectionIndex(_name + Integer.toString(_series));
	}


	//-----------------------------------------------------------------------------
	/**
	 * FButton
	 * 
	 * single element
	 * 
	 * return filePath of gui element 
	 * 
	 * @param _name
	 *		  name of the gui element 
	 *		  
	 */
	public String getFilePath(String _name) {
		String filePath = "";
		for(FButton fb : FButtons) {
			if(_name.compareTo(fb.name) == 0) filePath = fb.getFilePath();
			//else return null;
		}

		return filePath;
	}

	/**
	 * FButton
	 * 
	 * series element
	 * return filePath of gui element 
	 * 
	 * @param _name
	 *		  name of the gui element 
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 *		  
	 */
	public String getFilePath(String _name, int _series) {
		return getFilePath(_name + Integer.toString(_series));
	}


	//-----------------------------------------------------------------------------
	/**
	 * FLabel
	 * FInputField
	 * 
	 * single element
	 * 
	 * return type size of input typeface 
	 * 
	 * @param _name
	 *		  name of the gui element 
	 *		  
	 */
	public int getTypeSize(String _name) {
		int typeSize = 0;
		for(FLabel fl : FLabels) {
			if(_name.compareTo(fl.text) == 0) typeSize = (int) fl.getTypeSize();
		}
		for(FInputField fi : FInputFields) {
			if(_name.compareTo(fi.name) == 0) typeSize = fi.getTypeSize();
		}
		return typeSize;
	}

	/**
	 * FLabel
	 * FInputField
	 * 
	 * series element
	 * 
	 * return type size of input typeface 
	 * 
	 * @param _name
	 *		  name of the gui element 
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 *		  
	 */
	public int getTypeSize(String _name, int _series) {
		return getTypeSize(_name + Integer.toString(_series));
	}



	//-----------------------------------------------------------------------------
	// events
	//-----------------------------------------------------------------------------
	/**
	 * return whether mouse is over the gui element 
	 * 
	 * @param _name
	 *		  name of the gui element 
	 *		  
	 */
	public boolean getOver(String _name) {
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		boolean _over = false;
		for(FHandle fh : FHandles) {
			if(_name.compareTo(fh.name) == 0) {
				_over = fh.isOver();
				break;
			}
		}
		for(FHandle3D fh3 : FHandle3Ds) {
			if(_name.compareTo(fh3.name) == 0) {
				_over = fh3.isOver();
				break;
			}
		}
		for(FCheck fc : FChecks) {
			if(_name.compareTo(fc.name) == 0) {
				_over = fc.isOver();
				break;
			}
		}
		for(FSlider fs : FSliders) {
			if(_name.compareTo(fs.name) == 0) {
				_over = fs.isOver();
				break;
			}
		}
		for(FKnob fk : FKnobs) {
			if(_name.compareTo(fk.name) == 0) {
				_over = fk.isOver();
				break;
			}
		}
		for(FInputField fi : FInputFields) {
			if(_name.compareTo(fi.name) == 0) {
				_over = fi.isOver();
				break;
			}
		}

		return _over;
	}

	/**
	 * return whether mouse is over the gui element 
	 * 
	 * @param _name
	 *		  name of the gui element 
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 *		  
	 */
	public boolean getOver(String _name, int _series) {
		return getOver(_name + Integer.toString(_series));
	}


	/**
	 * return whether the element has been clicked 
	 * 
	 * @param _name
	 *		  name of the gui element 
	 *		  
	 */
	public boolean getClicked(String _name) {
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		boolean _clicked = false;

		for(FButton fb : FButtons) {
			if(_name.compareTo(fb.name) == 0) {
				_clicked = fb.isClicked();
				break;
			}
			else continue;
		}		
		for(FCheck fc : FChecks) {
			if(_name.compareTo(fc.name) == 0) {
				_clicked = fc.isClicked();
				break;
			}
			else continue;
		}		
		for(FHandle fh : FHandles) {
			if(_name.compareTo(fh.name) == 0) {
				_clicked = fh.isClicked();
				break;
			}
			else continue;
		}
		for(FHandle3D fh3 : FHandle3Ds) {
			if(_name.compareTo(fh3.name) == 0) {
				_clicked = fh3.isClicked();
				break;
			}
			else continue;
		}
		for(FSlider fs : FSliders) {
			if(_name.compareTo(fs.name) == 0) {
				_clicked = fs.isClicked();
				break;
			}
			else continue;
		}
		for(FKnob fk : FKnobs) {
			if(_name.compareTo(fk.name) == 0) {
				_clicked = fk.isClicked();
				break;
			}
			else continue;
		}
		for(FInputField fi : FInputFields) {
			if(_name.compareTo(fi.name) == 0) {
				_clicked = fi.isClicked();
				break;
			}
		}

		return _clicked;
	}

	/**
	 * return whether the element has been clicked 
	 * 
	 * @param _name
	 *		  name of the gui element 
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 *		  
	 */
	public boolean getClicked(String _name, int _series) {
		return getClicked(_name + Integer.toString(_series));
	}


	/**
	 * return whether the element has been pressed 
	 * 
	 * @param _name
	 *		  name of the gui element 
	 *		  
	 */
	public boolean getPressed(String _name) {
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		boolean _pressed = false;
		for(FButton fb : FButtons) {
			if(_name.compareTo(fb.name) == 0) {
				_pressed = fb.isDown();
				break;
			}
			else continue;
		}		
		for(FCheck fc : FChecks) {
			if(_name.compareTo(fc.name) == 0) {
				_pressed = fc.isDown();
				break;
			}
			else continue;
		}		
		for(FHandle fh : FHandles) {
			if(_name.compareTo(fh.name) == 0) {
				_pressed = fh.isDown();
				break;
			}
			else continue;
		}
		for(FHandle3D fh3 : FHandle3Ds) {
			if(_name.compareTo(fh3.name) == 0) {
				_pressed = fh3.isDown();
				break;
			}
			else continue;
		}
		for(FSlider fs : FSliders) {
			if(_name.compareTo(fs.name) == 0) {
				_pressed = fs.isDown();
				break;
			}
			else continue;
		}
		for(FKnob fk : FKnobs) {
			if(_name.compareTo(fk.name) == 0) {
				_pressed = fk.isDown();
				break;
			}
			else continue;
		}
		for(FInputField fi : FInputFields) {
			if(_name.compareTo(fi.name) == 0) {
				_pressed = fi.isDown();
				break;
			}
		}

		return _pressed;
	}

	/**
	 * return whether the element has been pressed 
	 * 
	 * @param _name
	 *		  name of the gui element 
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 *		  
	 */
	public boolean getPressed(String _name, int _series) {
		return getPressed(_name + Integer.toString(_series));
	}


	/**
	 * return whether the element is locked on 
	 * 
	 * @param _name
	 *		  name of the gui element 
	 *		  
	 */
	public boolean getLocked(String _name) {
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		boolean _locked = false;

		for(FButton fb : FButtons) {
			if(_name.compareTo(fb.name) == 0) {
				_locked = fb.isLocked();
				break;
			}
			else continue;
		}		
		for(FCheck fc : FChecks) {
			if(_name.compareTo(fc.name) == 0) {
				_locked = fc.isLocked();
				break;
			}
			else continue;
		}		
		for(FHandle fh : FHandles) {
			if(_name.compareTo(fh.name) == 0) {
				_locked = fh.isLocked();
				break;
			}
			else continue;
		}
		for(FHandle3D fh3 : FHandle3Ds) {
			if(_name.compareTo(fh3.name) == 0) {
				_locked = fh3.isLocked();
				break;
			}
			else continue;
		}
		for(FSlider fs : FSliders) {
			if(_name.compareTo(fs.name) == 0) {
				_locked = fs.isLocked();
				break;
			}
			else continue;
		}
		for(FKnob fk : FKnobs) {
			if(_name.compareTo(fk.name) == 0) {
				_locked = fk.isLocked();
				break;
			}
			else continue;
		}
		for(FInputField fi : FInputFields) {
			if(_name.compareTo(fi.name) == 0) {
				_locked = fi.isLocked();
				break;
			}
		}

		return _locked;
	}

	/**
	 * return whether the element is locked on 
	 * 
	 * @param _name
	 *		  name of the gui element 
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 *		  
	 */
	public boolean getLocked(String _name, int _series) {
		return getLocked(_name + Integer.toString(_series));
	}


	/**
	 * return whether any element has been moved/adjusted 
	 */
	public boolean getMoved() {
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		boolean _moved = false;

		for(FHandle fh : FHandles) {
			_moved = fh.isMoved();
		}
		for(FHandle3D fh3 : FHandle3Ds) {
			_moved = fh3.isMoved();
		}
		for(FSlider fs : FSliders) {
			_moved = fs.isMoved();
		}
		for(FKnob fk : FKnobs) {
			_moved = fk.isMoved();
		}

		return _moved;
	}



	/**
	 * return whether the element has been moved/adjusted 
	 * 
	 * @param _name
	 *		  name of the gui element 
	 *		  
	 */
	public boolean getMoved(String _name) {
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		boolean _moved = false;

		for(FHandle fh : FHandles) {
			if(_name.compareTo(fh.name) == 0) {
				_moved = fh.isMoved();
				break;
			}
		}
		for(FHandle3D fh3 : FHandle3Ds) {
			if(_name.compareTo(fh3.name) == 0) {
				_moved = fh3.isMoved();
				break;
			}
		}
		for(FSlider fs : FSliders) {
			if(_name.compareTo(fs.name) == 0) {
				_moved = fs.isMoved();
				break;
			}
		}
		for(FKnob fk : FKnobs) {
			if(_name.compareTo(fk.name) == 0) {
				_moved = fk.isMoved();
				break;
			}
		}

		return _moved;
	}

	/**
	 * return whether any element has been moved/adjusted 
	 * 
	 * @param _name
	 *		  name of the gui element 
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 *		  
	 */
	public boolean getMoved(String _name, int _series) {
		return getMoved(_name + Integer.toString(_series));
	}


	/**
	 * return whether the element has been released
	 * 
	 * @param _name
	 *		  name of the gui element 
	 *		  
	 */
	public boolean getReleased(String _name) {
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		boolean _released = false;

		for(FButton fb : FButtons) {
			if(_name.compareTo(fb.name) == 0) {
				_released = fb.isReleased();
				break;
			}
		}		
		for(FCheck fc : FChecks) {
			if(_name.compareTo(fc.name) == 0) {
				_released = fc.isReleased();
				break;
			}
		}		
		for(FHandle fh : FHandles) {
			if(_name.compareTo(fh.name) == 0) {
				_released = fh.isReleased();
				break;
			}
		}
		for(FHandle3D fh3 : FHandle3Ds) {
			if(_name.compareTo(fh3.name) == 0) {
				_released = fh3.isReleased();
				break;
			}
		}
		for(FSlider fs : FSliders) {
			if(_name.compareTo(fs.name) == 0) {
				_released = fs.isReleased();
				break;
			}
		}
		for(FKnob fk : FKnobs) {
			if(_name.compareTo(fk.name) == 0) {
				_released = fk.isReleased();
				break;
			}
		}
		for(FInputField fi : FInputFields) {
			if(_name.compareTo(fi.name) == 0) {
				_released = fi.isReleased();
				break;
			}
		}

		return _released;
	}

	/**
	 * return whether the element has been released
	 * 
	 * @param _name
	 *		  name of the gui element 
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 *		  
	 */
	public boolean getReleased(String _name, int _series) {
		return getReleased(_name + Integer.toString(_series));
	}


}