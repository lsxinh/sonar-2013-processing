package frederickk.control;

/*
 *  Frederickk.Control 0.0.5
 *  FInputField.java
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

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



public class FInputField extends FControlBase implements KeyListener {
	//-----------------------------------------------------------------------------
	// properties
	//-----------------------------------------------------------------------------
	private static final long serialVersionUID = 004L;


	private String t = "";
	private PFont inputTypeface;
	private int inputTypeSize;

	private int underlineColor = colorOver;
	private int underlineWeight = 6;

	private boolean FOCUS = false;
	private boolean UNDERLINE = false;
	private boolean CONTAINER = false;
	private boolean CAPS = false;



	//-----------------------------------------------------------------------------
	// constructor
	//-----------------------------------------------------------------------------
	public FInputField(PApplet p5) {
		super(p5);

		p5.addKeyListener(this);
	}

	public FInputField(PApplet p5, String _name, float _x, float _y, int _w, int _h) {
		super(p5);
		setName(_name);
		setSize(_w,_h);
		setPos(_x,_y);

		p5.addKeyListener(this);
	}

	public FInputField(PApplet p5, String _name, float _x, float _y, int _w, int _h, String t) {
		super(p5);
		setName(_name);
		setSize(_w,_h);
		setPos(_x,_y);
		setString(t); // specified default value

		p5.addKeyListener(this);
	}


	//-----------------------------------------------------------------------------
	// methods
	//-----------------------------------------------------------------------------
	protected void update() {
		if(CAPS) t = t.toUpperCase();
		if( width < (p5.textWidth(t)+100) ) width = (int) (p5.textWidth(t)+100);
		if( isOver() && DOWN ) LOCKED = true;
	}

	//-----------------------------------------------------------------------------
	public void draw() {
		update();

		p5.pushStyle();
		if(FOCUS) {
			p5.noFill();
			p5.stroke(0, 255*0.2f);
			p5.rect(x,y, width,height);
		}
		else if(OVER) p5.fill(colorOver);
		else if(DOWN) p5.fill(colorPressed);
		else p5.fill(colorInactive);

		//if(UNDERLINE) underline(breakup(t), x,y);

		p5.noStroke();
		p5.textFont(inputTypeface);

		if(!CONTAINER) 
			p5.text(t, x+10,y+getTypeSize());
		else
			p5.text(breakup(t), x+10,y+10, width-10,height-10);
		p5.popStyle();

	}


	//-----------------------------------------------------------------------------
	public void underline(String word, float x, float y) {
		p5.pushStyle();
		p5.stroke( underlineColor );
		p5.strokeWeight( underlineWeight );
		p5.line( x, y+(getTypeSize()+(underlineWeight*2)), 
				x+p5.textWidth(word), y+(getTypeSize()+(underlineWeight*2)) );
		p5.popStyle();
	}


	//-----------------------------------------------------------------------------
	private String breakup(String t) {
		String broken = "";
		if( !CONTAINER && p5.textWidth(t) > width*0.75f ) {
			int index = t.length();
			for (int i=index; i>0; i-=2) {
				String sub = t.substring(i, t.length());
				if( p5.textWidth(sub) > (width*0.75f) ) {
					index = i;
					break;
				}
			}
			broken = "..." + t.substring(index, t.length());
		}
		else {
			broken = t;
		}
		return broken;
	}  



	//-----------------------------------------------------------------------------
	// events
	//-----------------------------------------------------------------------------
	public void keyPressed(KeyEvent event) {
		int id = event.getID();
		char key = event.getKeyChar();

		if(FOCUS) {
			if (id == KeyEvent.VK_SPACE) {
				if (t.length() < 40) {
					t += key;
				}
			}		
			else if(id == KeyEvent.VK_ENTER) {
				FOCUS = false;
			}
			else if(id == KeyEvent.VK_ESCAPE) {
				FOCUS = false;
			}
		}
	}

	public void keyReleased(KeyEvent event) {
		// TODO Auto-generated method stub
	}

	public void keyTyped(KeyEvent event) {
		char key = event.getKeyChar();

		if(FOCUS) {
			if (key == KeyEvent.VK_BACK_SPACE || key == KeyEvent.VK_DELETE) {
				if (t.length() > 0) {
					t = t.substring(0,t.length() - 1);
				}
			}
			else {
				t += key;
			}
		}
	}

	//-----------------------------------------------------------------------------
	protected void onPress() {
		//		if(LOCKED) {
		FOCUS = true;
		//		}
	}
	protected void onPressOutside() {
		FOCUS = false;
	}	



	//-----------------------------------------------------------------------------
	// sets
	//-----------------------------------------------------------------------------
	@SuppressWarnings("deprecation")
	public void setTypeface(PFont typeface) {
		inputTypeface = typeface;
		inputTypeSize = inputTypeface.getFont().getSize();
		if(height < inputTypeSize) height = inputTypeSize+10; 
	}

	//-----------------------------------------------------------------------------
	public void setName(String _name) {
		name = _name;
		setString(_name);
	}

	//-----------------------------------------------------------------------------
	public void setString(String _t) {
		t = _t;
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
	public void caps(boolean val) {
		CAPS = val;
	}



	//-----------------------------------------------------------------------------
	// gets
	//-----------------------------------------------------------------------------
	public String getValue() {
		return t;
	}

	//-----------------------------------------------------------------------------
	public int getTypeSize() {
		return inputTypeSize;
	}
}
