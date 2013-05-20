package frederickk.tools;

/*
 *  Frederickk.Tools 0.0.5
 *  FPalette.java
 *
 *  Ken Frederick
 *  ken.frederick@gmx.de
 *
 *  http://cargocollective.com/kenfrederick/
 *  http://kenfrederick.blogspot.com/
 *
 *  a collection of tools that i tend to use frequently
 *  http://github.com/frederickk/frederickk
 *
 */



//-----------------------------------------------------------------------------
// libraries
//-----------------------------------------------------------------------------
import processing.core.PApplet;
import processing.core.PImage;
import java.awt.Color;



public class FPalette {
	//-----------------------------------------------------------------------------
	// properties
	//-----------------------------------------------------------------------------
	public static PApplet p5;

	public PImage img;
	public int[] colors;

	private String pfad = "";
	private int loc;

	//private boolean DIRECTION = true; //default direction is TOP_BOTTOM


	
	//-----------------------------------------------------------------------------
	// constructor
	//-----------------------------------------------------------------------------
	/**
	 * instantiate FPalette
	 * 
	 * @param papplet
	 * 			PApplet
	 * @param p
	 *		  the path of the palette image to load
	 * 
	 */
	public FPalette(
			PApplet papplet,
			String p) {

		p5 = papplet;
		setPath(p);
		setImage( p5.loadImage( p5.sketchPath + pfad) );
	}
	/**
	 * instantiate FPalette
	 * 
	 * @param papplet
	 * 			PApplet
	 * @param img
	 *		  the image of the palette
	 * 
	 */
	public FPalette(
			PApplet papplet,
			PImage img) {

		p5 = papplet;
		setImage(img);
	}



	//-----------------------------------------------------------------------------
	// methods
	//-----------------------------------------------------------------------------
	private void load() {
		img.loadPixels();
		colors = new int[img.width*img.height];
		int index = 0;
		//top to bottom
		//System.out.println("TOP_BOTTOM");
		for (int i=0; i<img.width; i++) {
			for (int j=0; j<img.height; j++) {
				loc = i + j*img.width; 
				colors[index] = img.pixels[loc];
				index++;
			}  
		}
	}
	
	/**
	 * @param w
	 *		  value to keep within the range of colors created
	 */
	private int inBounds(int w) {
		w = PApplet.constrain(w, 0,getCount());
		return w;
	}

	/**
	 * find a color within the palette which corresponds to input color's brightness
	 * 
	 * @param _col
	 *		  (int) color to to find brightness match of
	 * @param lo
	 *		  starting point within FPalette array
	 * @param hi
	 *		  ending point within FPalette array
	 * @param thresh
	 *		  range within to find color of matching brightness (1-255)
	 */
	public int matchBrightness(int _col, int lo, int hi, int thresh) {
		if(thresh <= 1) thresh = 1;
		if(lo < 0) lo = 0;
		if(hi > colors.length) hi = colors.length;
		int val = -1;
	  
		if(colors.length > 0) {
			for(int i=lo; i<hi; i++) {
				int r = thresh;
				do {
					if( p5.brightness(_col) > (p5.brightness(colors[i]) - r) &&
						p5.brightness(_col) < (p5.brightness(colors[i]) + r) ) {
						val = colors[i];
						if(val != -1) break;
					} else {
						r += thresh;              
					}
				} while(val == -1);
			}
		}
		return val;
	}

	/**
	 * find a color within the palette which corresponds to input color's brightness
	 * 
	 * @param _col
	 *		  (int) color to to find brightness match of
	 * @param thresh
	 *		  range within to find color of matching brightness (1-255)
	 */
	public int matchBrightness(int _col, int thresh) {
		return matchBrightness(_col, 0,colors.length, thresh);
	}


	  
	//-----------------------------------------------------------------------------
	// sets
	//-----------------------------------------------------------------------------
	/**
	 * @param pfad
	 *		  the path of the palette image to load
	 */
	public void setPath(String pfad) {
		this.pfad = pfad;
	}

	/**
	 * @param img
	 *		  the image of the palette
	 */
	public void setImage(PImage img) {
		this.img = img;
		load();
	}

	/**
	 * @param val
	 *		  set the direction of color gathering TOP_BOTTOM or LEFT_RIGHT
	 */
	/*
	public void setRichtung(String val) {
		if(val == "TOP_BOTTOM") {
			DIRECTION = true;
		} else if(val == "LEFT_RIGHT") {
			DIRECTION = false;
		} else {
			DIRECTION = true;
			System.out.println("invalid: default TOP_BOTTOM");
		}
	}
	*/



	//-----------------------------------------------------------------------------
	// gets
	//-----------------------------------------------------------------------------
	/**
	 * return the palette base image path as string  
	 * 
	 * @return pfad
	 */
	public String getPath() {
		return pfad;
	}

	/**
	 * return all of the created colors as an int array 
	 * 
	 * @return colors
	 */
	public int[] getColors() {
		return colors;
	}

	/**
	 * return selected color of the created colors 
	 * 
	 * @return colors[]
	 */
	public int getColor(int w) {
		return colors[w];
	}

	/**
	 * return number of colors available 
	 * 
	 * @return f
	 */
	public int getCount() {
		int f = colors.length;
		return f;
	}

	/**
	 * @param w
	 *		  number of color to use
	 * @param val
	 *		  percentage of transparency 0.0 - 1.0
	 */
	public int getColorTrans(int w, float val) {
		w = inBounds(w);
		Color color = new Color( colors[w] );

		int f = color.getRGB();
		float r =  ( f >> 16 ) & 0xFF;
		float g = ( f >> 8 ) & 0xFF;
		float b = f & 0xFF;

		Color colorHold = new Color(r,g,b, 255*val);
		int farbeTrans = colorHold.getRGB();
		return farbeTrans;
	}

}

