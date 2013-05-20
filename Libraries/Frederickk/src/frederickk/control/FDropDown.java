package frederickk.control;

/*
 *  Frederickk.Control 0.0.5
 *  FDropDown.java
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
import java.util.ArrayList;
import processing.core.PApplet;



public class FDropDown extends FButton {
	//-----------------------------------------------------------------------------
	// properties
	//-----------------------------------------------------------------------------
	private static final long serialVersionUID = 1L;

	private ArrayList<FButton> FItems = new ArrayList<FButton>();
	private int val = -1;

	private int heightHolder;
	
	private boolean OPENED = false;
	private String selStrVal = new String();
	private int selIntVal = -1;


	
	//-----------------------------------------------------------------------------
	// constructor
	//-----------------------------------------------------------------------------
	public FDropDown(PApplet p5) {
		super(p5);
	}
	
	public FDropDown(PApplet p5, String _name, float _x, float _y, int _w, int _h, String[] items) {
		super(p5);
		setName(_name);
		setSize(_w,_h);
		setPos(_x,_y);

		addItem(items);
	}
	public FDropDown(PApplet p5, String _name, float _x, float _y, int _w, int _h, String[] items, String selected) {
		super(p5);
		setName(_name);
		setSize(_w,_h);
		setPos(_x,_y);

		selStrVal = selected;
		addItem(items);
	}

	//-----------------------------------------------------------------------------
	// methods
	//-----------------------------------------------------------------------------
	protected void update() {
		if( isOver() && DOWN ) LOCKED = true;
//		getSelection();
	}


	//-----------------------------------------------------------------------------
	public void draw() {
		update();
		toggle();
		
		
		//-----------------------------------------
		// controller
		//-----------------------------------------
		p5.pushStyle();

		//-----------------------------------------
		// background
		//-----------------------------------------
		if( isOver() || isDown() ) {
			p5.fill( getColorOver() );
			height = heightHolder*(FItems.size()+1);
			OPENED = true;
		}
		else {
			p5.fill( getColorInactive() );
			height = heightHolder;
			OPENED = false;
		}
		p5.noStroke();
		p5.rect(x,y, width,height);

		
		//-----------------------------------------
		// elements
		//-----------------------------------------
		if( OPENED ) {
			getSelection();

			for(FButton fc : FItems) {
				fc.showLabels( true );

				fc.setColorOver( colorOver );
				fc.setColorPressed( colorPressed );
				fc.setColorInactive( colorInactive );
				
				fc.draw();
			}
		}

		
		//-----------------------------------------
		// label
		//-----------------------------------------
		if(showLabels) {
			int a = (getColorInactive() >> 24) & 0xFF;
			p5.fill( white, a );
//			labelName.uncontained();
			labelName.setPos( x+5, y );
			labelName.setSize( width-10, heightHolder);
			labelName.draw( name, PApplet.LEFT, BOLD );
			
//			labelVal.uncontained();
			labelVal.setPos( x-5, y );
			labelVal.setSize( width-10, heightHolder);
			labelVal.draw( selStrVal, PApplet.RIGHT, REGULAR );
		}
		p5.popStyle();
	}	


	//-----------------------------------------------------------------------------
	public void addItem(String[] items) {
		float yh = y+height;
		
		System.out.println( "addItems() ");
		for(int i=0; i<items.length; i++) {
			FButton fc = new FButton(p5);
			fc.setName(items[i]);
			fc.setPos(x, yh);
			fc.setSize(width, height);
//			fc.setValue(false);

			FLabel LabelName = new FLabel(p5);
			fc.setLabels(LabelName);

			FItems.add(fc);
			yh += height;
		}

	}
	public void addItem(String _name) {
//		FItems.add(new FButton(p5, _name, x,y, width,height, LABEL_INT) );
//		FItems.add( new FButton(p5, _name, x,y, width, false) );
	}


	
	//-----------------------------------------------------------------------------
	// sets
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

		heightHolder = _h;
	}
	

	
	//-----------------------------------------------------------------------------
	// gets
	//-----------------------------------------------------------------------------
	protected String getSelection() {
		int i = 0;
		for(FButton fc : FItems) {
			if( fc.isClicked() ) {
				selStrVal = fc.name;
				selIntVal = i;
				break;
			}
			i++;
		}
		return selStrVal;
	}

	protected int getSelectionIndex() {
		return selIntVal;
	}



}