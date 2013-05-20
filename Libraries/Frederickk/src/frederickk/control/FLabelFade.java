package frederickk.control;

/*
 *  Frederickk.Control 0.0.5
 *  FLabelFade.java
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
import processing.core.PConstants;
import frederickk.tools.FFade;



public class FLabelFade extends FLabel implements PConstants, FControlConstants {
	//-----------------------------------------------------------------------------
	// properties
	//-----------------------------------------------------------------------------
	private static final long serialVersionUID = 004L;
	private FFade fade;
	private boolean bFadeIn;
	private int r,g,b, a = 255;
	private int col;

	
	
	//-----------------------------------------------------------------------------
	// constructor
	//-----------------------------------------------------------------------------
	public FLabelFade(PApplet thePApplet) {
		super(thePApplet);
	    fade = new FFade(p5);
	    fade.setFadeMillis(250);
	    fade.update();

	    setColor(255);
	    bFadeIn = false; // if "true" start fading out
	}

	public FLabelFade(PApplet thePApplet, int _col) {
		super(thePApplet);
	    fade = new FFade(p5);
	    fade.setFadeMillis(250);
	    fade.update();

	    setColor(_col);
	    bFadeIn = false; // if "true" start fading out
	}

	

	//-----------------------------------------------------------------------------
	// methods
	//-----------------------------------------------------------------------------
	public void draw(String _text) {
		if(bFadeIn) {
	    	fade.fadeIn();
		} else if(!bFadeIn) {
	    	fade.fadeOut();
	    }
		
	    a = (int) (fade.getAlpha()*255);
	    r = (col >> 16) & 0xFF;
	    g = (col >> 8) & 0xFF;
	    b = col & 0xFF;
	    p5.fill( r,g,b, a );
		super.draw(_text);
	}

	public void draw(String _text, int TEXT_ALIGNMENT) {
		if(bFadeIn) {
	    	fade.fadeIn();
		} else if(!bFadeIn) {
	    	fade.fadeOut();
	    }

	    a = (int) (fade.getAlpha()*255);
	    r = (col >> 16) & 0xFF;
	    g = (col >> 8) & 0xFF;
	    b = col & 0xFF;
	    p5.fill( r,g,b, a );
		super.draw(_text, TEXT_ALIGNMENT);
	}

	public void draw(String _text, int TEXT_ALIGNMENT, int TYPE_STYLE) {
		if(bFadeIn) {
	    	fade.fadeIn();
		} else if(!bFadeIn) {
	    	fade.fadeOut();
	    }

	    a = (int) (fade.getAlpha()*255);
	    r = (col >> 16) & 0xFF;
	    g = (col >> 8) & 0xFF;
	    b = col & 0xFF;
	    p5.fill( r,g,b, a );
	    super.draw(_text, TEXT_ALIGNMENT, TYPE_STYLE);
	}

	
	
	//-----------------------------------------------------------------------------
	// sets
	//-----------------------------------------------------------------------------
    public void setFadeMillis(int val) {
        fade.setFadeMillis(val);
    }
    
    public void setColor(int val) {
    	col = val;
    }

    public void fadeIn() {
    	bFadeIn = true;
    }
    public void fadeOut() {
    	bFadeIn = false;
    }

    
    
	//-----------------------------------------------------------------------------
	// gets
	//-----------------------------------------------------------------------------
    public int getAlpha() {
    	return (int) (fade.getAlpha()*255);
    }
}
