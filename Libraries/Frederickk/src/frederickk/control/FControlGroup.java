package frederickk.control;

/*
 *  Frederickk.Control 0.0.5
 *  FButton.java
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
 *  http:processing.org/discourse/yabb2/YaBB.pl?board=OpenGL;action=display;num=1231010113
 *
 */



//-----------------------------------------------------------------------------
// libraries
//-----------------------------------------------------------------------------
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import processing.core.PVector;

import java.awt.*;
import java.util.ArrayList;

// import frederickk.tools.FTools;



public class FControlGroup extends PApplet {
	//-----------------------------------------------------------------------------
	// properties
	//-----------------------------------------------------------------------------
	protected static final long serialVersionUID = 2974580295219608175L;

	protected Frame frame;
	protected String name = "";
	protected int width;
	protected int height;
	private int titlebarHeight = 22; // mac
	
	// gui
	protected FControl elements;

	protected int[] margins = new int[4];
	protected ArrayList<Integer> cols;
	protected ArrayList<Integer> rows;
	protected boolean bGrid = false;

	// view
	protected int bgColor;
	protected boolean bShowFrameRate = false;

	//private boolean bVerbose = true;



	//-----------------------------------------------------------------------------
	// constructor
	//-----------------------------------------------------------------------------
	@SuppressWarnings("deprecation")
	/**
	 * @param _name
	 *		  name of the group/window
	 * @param _gui
	 *		  the FControl object of control elements
	 * @param _width
	 *		  width of the group/window
	 * @param _height
	 *		  height of the group/window
	 *
	 */
	public FControlGroup(String _name, int _width, int _height) {
		name = _name;
		//setElements( new FControl(this) );
		setName(_name);
		width = _width;
		height = _height;

		frame = new Frame();
		frame.setBounds(0,0, width,height+titlebarHeight);
		frame.setLocation(0,0);
		frame.add( this );
		this.init();
		frame.show();

		elements = new FControl(this);
	}

	//-----------------------------------------------------------------------------
	@SuppressWarnings("deprecation")
	/**
	 * @param _name
	 *		  name of the group/window
	 * @param _gui
	 *		  the FControl object of control elements
	 * @param _x
	 *		  x position of the group/window
	 * @param _y
	 *		  y position of the group/window
	 * @param _width
	 *		  width of the group/window
	 * @param _height
	 *		  height of the group/window
	 *
	 */
	public FControlGroup(String _name, int _x, int _y, int _width, int _height) {
		name = _name;
		setName(_name);
		//setElements( new FControl(this) );
		width = _width;
		height = _height;

		frame = new Frame();
		frame.setBounds(0,0, width,height+titlebarHeight);
		frame.setLocation(_x,_y);
		frame.add( this );
		this.init();
		frame.show();

		elements = new FControl(this);
	}

	//-----------------------------------------------------------------------------




	//-----------------------------------------------------------------------------
	// methods
	//-----------------------------------------------------------------------------
	public void setup() {
		size(width, height);
		frameRate(15);
		frame.setTitle( name );

		// set default margin values
		setMargins(15, 15, width-15, height-15);

		// default rows & cols
		if( cols == null ) setCols(3);
		if( rows == null ) setRows(3);
	}

	//-----------------------------------------------------------------------------
	public void update() {
	}

	//-----------------------------------------------------------------------------
	public void draw() {
		background( bgColor );
		update();

		// frameRate in title
		if(bShowFrameRate) {
			frame.setTitle( Float.toString(frameRate) );
		}

		// render the elements to the screen
		if(elements != null) {
//			if(bVerbose) elements.listElements(); System.out.println("---------");
			if( elements.totalElements() > 0) elements.draw();
		}

		// render grid
		if(bGrid) drawGrid();
	}

	//-----------------------------------------------------------------------------
	private void drawGrid() {
		pushStyle();
		noFill();
		// grid
		stroke(0,255,255);
		for(int x=0; x<getCols().size(); x++) {
			line(getCol(x), 0, getCol(x), height);
		}
		for(int y=0; y<getRows().size(); y++) {
			line(0, getCol(y), width, getCol(y));
		}
		// margin
		stroke(255,0,255);
		line(margins[0], 0, margins[0], height);
		line(0, margins[1], width, margins[1]);
		line(margins[2], 0, margins[2], height);
		line(0, margins[3], width, margins[3]);
		popStyle();
	}


	//-----------------------------------------------------------------------------
	/**
	 * margins of the the window, default values are:
	 * 15 (left) 15 (top) width-15 (right) height-15 (bottom)
	 */
	public void setMargins(int _left, int _top, int _right, int _bottom) {
		margins[0] = _left;
		margins[1] = _top;
		margins[2] = _right;
		margins[3] = _bottom;
	}
	/**
	 * @return margins of the the window, default values are:
	 * 			15 (left) 15 (top) width-15 (right) height-15 (bottom)
	 */
	public int[] getMargins() {
		return margins;
	}


	//-----------------------------------------------------------------------------
	/**
	 * 
	 * @param _cols
	 * 			number of columns
	 * @param _rows
	 * 			number of rows
	 */
	public void setColsRows(int _cols, int _rows) {
		setCols(_cols);
		setRows(_rows);
	}

	
	/**
	 * 
	 * @param _cols
	 * 			number of columns
	 */
	protected void setCols(int _cols) {
		cols = new ArrayList<Integer>();
		for(int x=0; x<_cols; x++) {
			cols.add( (int) map(x, 0,_cols, margins[0],margins[2] ) );
		}
	}
	/**
	 * 
	 * @param _rows
	 * 			number of rows
	 */
	protected void setRows(int _rows) {
		rows = new ArrayList<Integer>();
		for(int y=0; y<_rows; y++) {
			rows.add( (int) map(y, 0,_rows, margins[1],margins[3] ) );
		}
	}

	
	/**
	 * @return x values of cols
	 */
	public ArrayList<Integer> getCols() {
		return cols;
	}
	public int getCol(int num) {
		return cols.get( constrain(num, 0,cols.size()) );
	}
	/**
	 * @return y values of rows
	 */
	public ArrayList<Integer> getRows() {
		return rows;
	}
	public int getRow(int num) {
		return rows.get( constrain(num, 0,rows.size()) );
	}

	
	//-----------------------------------------------------------------------------
	public void showGrid(boolean val) {
		bGrid = val;
	}


	//-----------------------------------------------------------------------------
	// sets
	//-----------------------------------------------------------------------------
	/**
	 * @param _elements
	 *		  set the gui elements for this FControlGroup (only 1 per group)
	 */
	public void setElements(FControl _elements) {
		elements = _elements;
	}

	//-----------------------------------------------------------------------------
	/**
	 * @param _colorInactive
	 *		  set global inactive color for all gui elements
	 */
	public void setColorInactive(int _colorInactive) {
		elements.setColorInactive(_colorInactive);
	}

	/**
	 * @param _colorOver
	 *		  set global over color for all gui elements
	 */
	public void setColorOver(int _colorOver) {
		elements.setColorOver(_colorOver);
	}

	/**
	 * @param _colorPressed
	 * 		  set global pressed color for all gui elemetns
	 */
	public void setColorPressed(int _colorPressed) {
		elements.setColorPressed(_colorPressed);
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
		elements.setSize(_name, _width, _height);
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
		elements.setSize(_name + Integer.toString(_series), _width, _height);
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
		elements.setSize(_name, (int)_radius,(int)_radius);
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
		elements.setSize(_name + Integer.toString(_series), _radius);
	}

	//-----------------------------------------------------------------------------
	/**
	 * FButton
	 * FHandle
	 * FSliders
	 * FDropDowns
	 * FCheck
	 * FKnob
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
		elements.setPos(_name, (int)_x,(int)_y);
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
		elements.setPos(_name + Integer.toString(_series), _x,_y);
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
		elements.setPos(_name, (int)_x, (int)_y, (int)_z);
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
		elements.setPos(_name + Integer.toString(_series), _x,_y,_z);
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
		elements.setPos(_name, _val.x,_val.y,_val.z);
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
		elements.setPos(_name, _val.x,_val.y,_val.z);
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
		elements.setValue(_name, _val);
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
		elements.setValue(_name + Integer.toString(_series), _val);
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
		elements.setLoose(_val);
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
		elements.setValue(_name, _val);
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
		elements.setValue(_name, _val);
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
		elements.setValue(_name + Integer.toString(_series), _val);
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
		elements.setValue(_name + Integer.toString(_series), _val);
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
		elements.setValueRange(_name, _vMin,_vMax);
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
		elements.setValueRange(_name + Integer.toString(_series), _vMin,_vMax);
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
		elements.addItem(_name, _nameItem);
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
		elements.addItem(_name + Integer.toString(_series), _nameItem);
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
		elements.setTypeface(_name, _typeface);
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
		elements.setTypeface(_name + Integer.toString(_series), _typeface);
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
		elements.enableSnap(_name, _val);
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
		elements.enableSnap(_name + Integer.toString(_series), _val);
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
		elements.disableSnap(_name);
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
		elements.disableSnap(_name + Integer.toBinaryString(_series));
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
		elements.setLabelType(_labelType);
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
		elements.setLabelType(_name, _labelType);
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
		elements.setLabelType(_name + Integer.toString(_series), _labelType);
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
		elements.setSprite(_name, img, _sw,_sh);
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
		elements.setSprite(_name + Integer.toString(_series), img, _sw,_sh);
	}


	//-----------------------------------------------------------------------------
	/**
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
		elements.underline(_name, _val);
	}

	/**
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
		elements.underline(_name + Integer.toString(_series), _val);
	}

	/**
	 * FInputField
	 * 
	 * underline all input fields
	 * 
	 * @param _val
	 * 		  toggle underline
	 *
	 */
	public void underline(boolean _val) {
		elements.underline(_val);
	}


	//-----------------------------------------------------------------------------
	/**
	 * FInputField
	 * 
	 * toggle container around specific input field
	 * 
	 * @param _name
	 *		  name of the gui element 
	 * @param _val
	 *		  container toggle value 
	 *
	 */
	public void container(String _name, boolean _val) {
		elements.container(_name, _val);
	}

	/**
	 * FInputField
	 * 
	 * toggle container around specific input field
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
		elements.container(_name + Integer.toString(_series), _val);
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
		elements.container(_val);
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
		elements.caps(_name, _val);
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
		elements.caps(_name + Integer.toString(_series), _val);
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
		elements.caps(_val);
	}	


	//-----------------------------------------------------------------------------
	// gets
	//-----------------------------------------------------------------------------
	/**
	 * an audit of elements 
	 * 
	 */
	public void listElements() {
		elements.listElements();
	}
	/**
	 * @return an ArrayList of all FButton elements
	 * 
	 */
	public ArrayList<FButton> getButtons() {
		return elements.getButtons();
	}
	/**
	 * @return an ArrayList of all FHandle elements
	 * 
	 */
	public ArrayList<FHandle> getHandles() {
		return elements.getHandles();
	}
	/**
	 * @return an ArrayList of all FHandle3D elements
	 * 
	 */
	public ArrayList<FHandle3D> getHandle3Ds() {
		return elements.getHandle3Ds();
	}
	/**
	 * @return an ArrayList of all FCheck elements
	 * 
	 */
	public ArrayList<FCheck> getChecks() {
		return elements.getChecks();
	}
	/**
	 * @return an ArrayList of all FSlider elements 
	 * 
	 */
	public ArrayList<FSlider> getSliders() {
		return elements.getSliders();
	}
	/**
	 * @return an ArrayList of all FKnob elements 
	 * 
	 */
	public ArrayList<FKnob> getKnobs() {
		return elements.getKnobs();
	}
	/**
	 * @return an ArrayList of all FDropDown elements 
	 * 
	 */
	public ArrayList<FDropDown> getDropDowns() {
		return elements.getDropDowns();
	}
	/**
	 * @return an ArrayList of all FInputField elements
	 * 
	 */
	public ArrayList<FInputField> getInputFields() {
		return elements.getInputFields();
	}


	//-----------------------------------------------------------------------------
	/**
	 * return inactive color (as int) 
	 * 
	 */
	public int getColorInactive() {
		return elements.getColorInactive();
	}
	/**
	 * return over color (as int) 
	 * 
	 */
	public int getColorOver() {
		return elements.getColorOver();
	}
	/**
	 * return pressed color (as int) 
	 * 
	 */
	public int getColorPressed() {
		return elements.getColorPressed();
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
		return elements.getPos(_name);
	}


	/**
	 * FHandle
	 * FHandle3D
	 * FCheck
	 * FSlider
	 * FKnob
	 * FButton
	 * FDropDown
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
		return elements.getPos(_name + Integer.toString(_series));
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
		return elements.getSize(_name);
	}

	/**
	 * FHandle
	 * FHandle3D
	 * FCheck
	 * FSlider
	 * FKnob
	 * FButton
	 * FDropDown
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
		return elements.getSize(_name + Integer.toString(_series));
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
		return elements.getBoolValue(_name);
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
		return elements.getBoolValue( _name + Integer.toString(_series));
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
	 */
	public int getIntValue(String _name) {
		return elements.getIntValue(_name);
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
	 */
	public int getIntValue(String _name, int _series) {
		return elements.getIntValue(_name + Integer.toString(_series));
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
	 */
	public float getFloatValue(String _name) {
		return elements.getFloatValue(_name);
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
	 */
	public float getFloatValue(String _name, int _series) {
		return elements.getFloatValue(_name + Integer.toString(_series));
	}


	//-----------------------------------------------------------------------------
	/**
	 * FCheck
	 * FSlider
	 * FKnob
	 * 
	 * single element
	 * 
	 * @param _name
	 *		  name of the gui element 
	 * @return
	 * 		  String value of gui element
	 */
	public String getStringValue(String _name) {
		return elements.getStringValue(_name);
	}


	/**
	 * FCheck
	 * FSlider
	 * FKnob
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
		return elements.getStringValue(_name + Integer.toString(_series));
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
		return elements.getSelection(_name);
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
		return elements.getSelection(_name + Integer.toString(_series));
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
		return elements.getSelectionIndex(_name);
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
		return elements.getSelectionIndex(_name + Integer.toString(_series));
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
		return elements.getFilePath(_name);
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
		return elements.getFilePath(_name + Integer.toString(_series));
	}


	//-----------------------------------------------------------------------------
	/**
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
		return elements.getTypeSize(_name);
	}

	/**
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
		return elements.getTypeSize(_name + Integer.toString(_series));
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
		return elements.getOver(_name);
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
		return elements.getOver(_name + Integer.toString(_series));
	}


	/**
	 * return whether the element has been clicked 
	 * 
	 * @param _name
	 *		  name of the gui element 
	 *		  
	 */
	public boolean getClicked(String _name) {
		return elements.getClicked(_name);
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
		return elements.getClicked(_name + Integer.toString(_series));
	}


	/**
	 * return whether the element has been pressed 
	 * 
	 * @param _name
	 *		  name of the gui element 
	 *		  
	 */
	public boolean getPressed(String _name) {
		return elements.getPressed(_name);
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
		return elements.getPressed(_name + Integer.toString(_series));
	}


	/**
	 * return whether the element is locked on 
	 * 
	 * @param _name
	 *		  name of the gui element 
	 *		  
	 */
	public boolean getLocked(String _name) {
		return elements.getLocked(_name);
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
		return elements.getLocked(_name + Integer.toString(_series));
	}


	/**
	 * return whether any element has been moved/adjusted 
	 */
	public boolean getMoved() {
		return elements.getMoved();
	}



	/**
	 * return whether the element has been moved/adjusted 
	 * 
	 * @param _name
	 *		  name of the gui element 
	 *		  
	 */
	public boolean getMoved(String _name) {
		return elements.getMoved(_name);
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
		return elements.getMoved(_name + Integer.toString(_series));
	}


	/**
	 * return whether the element has been released
	 * 
	 * @param _name
	 *		  name of the gui element 
	 *		  
	 */
	public boolean getReleased(String _name) {
		return elements.getReleased(_name);
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
		return elements.getReleased(_name + Integer.toString(_series));
	}



	//-----------------------------------------------------------------------------
	public FControl getElements() {
		return elements;
	}

	//-----------------------------------------------------------------------------
	public PApplet getApplet() {
		return this;
	}

}
