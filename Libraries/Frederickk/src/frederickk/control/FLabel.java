package frederickk.control;

/*
 *  Frederickk.Control 0.0.5
 *  FLabel.java
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
import processing.core.PConstants;



public class FLabel extends FControlBase implements PConstants, FControlConstants {
	//-----------------------------------------------------------------------------
	// properties
	//-----------------------------------------------------------------------------
	private static final long serialVersionUID = 004L;

	protected String text = "";
	protected boolean CONTAINER = true;

	// constants
	private boolean UNDERLINE = false;

	protected static PFont TYPEFACE_REG = new PFont();
	protected static PFont TYPEFACE_BOLD = new PFont();
	protected static int TYPEFACE_SIZE = 0;



	//-----------------------------------------------------------------------------
	// constructor
	//-----------------------------------------------------------------------------
	FLabel(PApplet _papplet) {
		super(_papplet);
		setText(" ");
	}

	FLabel(PApplet _papplet, float _x, float _y, PFont _typefaceLabel) {
		super(_papplet);
		setText(" ");
		setTypeface(_typefaceLabel);
		setPos(_x, _y);
	}

	FLabel(PApplet _papplet, String _text, float _x, float _y, PFont _typefaceLabel) {
		super(_papplet);
		setText(_text);
		setTypeface(_typefaceLabel);
		setPos(_x, _y);
	}



	//-----------------------------------------------------------------------------
	// methods
	//-----------------------------------------------------------------------------
	@Override
	protected void update() {
		// TODO Auto-generated method stub
		
	}

	//-----------------------------------------------------------------------------
	public void draw() {
		draw(text);
	}
	public void draw(String _text) {
		draw(_text, PApplet.LEFT, BOLD);
	}
	public void draw(String _text, int TEXT_ALIGNMENT) {
		draw(_text, TEXT_ALIGNMENT, BOLD);
	}
	public void draw(String _text, int TEXT_ALIGNMENT, int TYPE_STYLE) {
		setText(_text);

		p5.pushStyle();
		if(TYPE_STYLE == BOLD) p5.textFont(TYPEFACE_BOLD);
		else p5.textFont(TYPEFACE_REG);
		if(CONTAINER) {
			p5.textAlign(TEXT_ALIGNMENT, CENTER);
			p5.text(text, x+5,y, width,height);
		} else {
			p5.textAlign(TEXT_ALIGNMENT);
			p5.text(text, x+5,y+TYPEFACE_SIZE);
		}

		if(UNDERLINE) underline(_text, x,y);		
		
		p5.popStyle();
	}

	//-----------------------------------------------------------------------------
	private void underline(String word, float x, float y) {
		p5.pushStyle();
//		p5.stroke( underlineColor );
		p5.strokeWeight( 1 );
		p5.line( x, y+(getTypeSize()+2), 
				x+p5.textWidth(word), y+(getTypeSize()+2) );
		p5.popStyle();
	}


	
	//-----------------------------------------------------------------------------
	// sets
	//-----------------------------------------------------------------------------
	public void set(float _x, float _y, PFont _typefaceLabel) {
		setTypeface(_typefaceLabel);
		setPos(_x, _y);
		setSize(100,10);
	}

	public void set(float _x, float _y, PFont _typefaceLabel, PFont _typefaceLabelBold) {
		setTypeface(_typefaceLabel,_typefaceLabelBold);
		setPos(_x, _y);
		setSize(100,10);
	}


	//-----------------------------------------------------------------------------
	@SuppressWarnings("deprecation")
	protected void setTypeface(PFont _typefaceLabel) {
		TYPEFACE_REG = _typefaceLabel;
		TYPEFACE_BOLD = _typefaceLabel;

		// broke in Processing 2.0bX
		TYPEFACE_SIZE = (TYPEFACE_REG.getFont().getSize())+1;

	}

	@SuppressWarnings("deprecation")
	protected void setTypeface(PFont _typefaceLabel, PFont _typefaceLabelBold) {
		TYPEFACE_REG = _typefaceLabel;
		TYPEFACE_BOLD = _typefaceLabelBold;

		// broke in Processing 2.0bX
		TYPEFACE_SIZE = (TYPEFACE_REG.getFont().getSize())+1;
	}


	//-----------------------------------------------------------------------------
	public void setText(String _text) {
		text = _text;
	}
	public void setString(String _text) {
		setText(_text);
	}


	//-----------------------------------------------------------------------------
	public void setSize(int _w, int _h) {
		this.width = _w;
		this.height = _h;

		if(this.width < TYPEFACE_SIZE || this.height < TYPEFACE_SIZE) {
			CONTAINER = false;	
		} else {
			CONTAINER = true;	
		}
	}

	//-----------------------------------------------------------------------------
	public void underline(boolean val) {
		UNDERLINE = val;
	}

	//-----------------------------------------------------------------------------
	public void container(boolean val) {
		CONTAINER = val;
	}


	
	//-----------------------------------------------------------------------------
	// gets
	//-----------------------------------------------------------------------------
	public float getTypeSize() {
		return TYPEFACE_SIZE;
	}

	public PFont getTypeface() {
		return TYPEFACE_REG;
	}

	//-----------------------------------------------------------------------------
	public String toString() {
		return text;
	}

	
}
