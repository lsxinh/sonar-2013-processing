package frederickk.api;

/**
 *  Frederickk.Api 0.0.5
 *  FFlickrSource.java
 *
 *  Ken Frederick
 *  ken.frederick@gmx.de
 *
 *  http://cargocollective.com/kenfrederick/
 *  http://kenfrederick.blogspot.com/
 *
 *  a library for easier use of the flickr api
 *  http://github.com/frederickk/frederickk
 *
 */



//-----------------------------------------------------------------------------
// libraries
//-----------------------------------------------------------------------------
import processing.core.*;
import processing.core.PApplet;



class FFlickrSource {
	//-----------------------------------------------------------------------------
	// properties
	//-----------------------------------------------------------------------------
	private static PApplet p5;
	
	protected PImage img;
	protected boolean LOADED = false;
	protected boolean ERROR = false;
	protected String url;



	//-----------------------------------------------------------------------------
	// constructor
	//-----------------------------------------------------------------------------
	public FFlickrSource(PApplet papplet, String _url) {
		url = _url;
	}



	//-----------------------------------------------------------------------------
	// methods
	//-----------------------------------------------------------------------------
	void load() {
		System.out.println("nLoading image: " + url);
		img = p5.loadImage(url);

		if(img == null) ERROR = true;
		LOADED = true;

		if(ERROR) System.out.println("Error occurred when loading image.");
		else System.out.println("Load successful. " + 
				"Image size is " + img.width + "x" + img.height);

	}
}