package frederickk.api;

/**
 *	Frederickk.Api 0.0.5
 *	FPrettyColorsPalette.java
 *
 *	Ken Frederick
 *	ken.frederick@gmx.de
 *
 *	http://cargocollective.com/kenfrederick/
 *	http://kenfrederick.blogspot.com/
 *
 *	A class for grabbing color values and
 *	creating palettes from http://prettycolors.tumblr.com/
 *
 *	FPrettyColors Palette holder class
 *
 */



//-----------------------------------------------------------------------------
// libraries
//-----------------------------------------------------------------------------
import frederickk.tools.FTime;
import processing.core.*;



public class FPrettyColorsPalette {
	//-----------------------------------------------------------------------------
	// properties
	//-----------------------------------------------------------------------------
	private PApplet papplet;

	private int palette[];
	public String type = "";

	/**
	 *  default number of colors in palette
	 */
	public final int AMOUNT = 5;



	//-----------------------------------------------------------------------------
	// constructor
	//-----------------------------------------------------------------------------
	/**
	 * instantiate FPrettyColors
	 * 
	 * @param _papplet
	 * 			PApplet
	 */
	public FPrettyColorsPalette(PApplet _papplet) {
		papplet = _papplet;
		palette = new int[AMOUNT];
	}



	//-----------------------------------------------------------------------------
	// methods
	//-----------------------------------------------------------------------------
	/**
	 * save palette as .png
	 * 
	 */
	public void save() {
		String filename = type + "_" + FTime.date() + "_" + FTime.time() + ".png";
		PImage img = papplet.createImage(AMOUNT,1, PApplet.ARGB); 
		
		img.loadPixels();
		for(int j=0; j<img.pixels.length; j++) img.pixels[j] = papplet.color(palette[j]);
		img.updatePixels();
		img.save("palette/" + filename);

		System.out.println(filename + "\tsaved!");
	}	
	
	

	//-----------------------------------------------------------------------------
	// sets
	//-----------------------------------------------------------------------------
	/**
	 * set chosen color (index) to value 
	 * 
	 * @param index
	 * 			index number of color
	 * @param val
	 * 			int value of desired color
	 * 
	 */
	public void set(int index, int val) {
		palette[index] = val;
	}



	//-----------------------------------------------------------------------------
	// gets 
	//-----------------------------------------------------------------------------
	/**
	 * returns palette colors as int array 
	 * 
	 * @return result
	 */
	public int[] get() {
		return palette;
	}

	
	/**
	 * returns palette color as int 
	 * 
	 * @param index
	 * 			index number of color to return
	 * 
	 * @return result
	 */
	public int get(int index) {
		return palette[ PApplet.constrain(index, 0,palette.length) ];
	}

} //end FPrettycolorsPalette