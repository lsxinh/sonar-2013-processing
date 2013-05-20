package frederickk.api;

/**
 *  Frederickk.Api 0.0.5
 *  FFlickrSearch.java
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
 *  multi-threading insight via marius watz
 *  http://processing.org/discourse/yabb2/YaBB.pl?board=Integrate;action=display;num=1204990614
 *
 *  and ben fry
 *  http://processing.org/discourse/yabb2/YaBB.pl?num=1119238260
 * 
 */



//-----------------------------------------------------------------------------
// libraries
//-----------------------------------------------------------------------------
import processing.core.*;
import processing.data.XML;
import java.util.ArrayList;



public class FFlickrSearch implements FFlickrConstants,Runnable {
	//-----------------------------------------------------------------------------
	// properties
	//-----------------------------------------------------------------------------
	private static PApplet p5;
	private Thread thread;

	private String url = "";


	
	//-----------------------------------------------------------------------------
	// constructor
	//-----------------------------------------------------------------------------
	@SuppressWarnings("deprecation")
	public FFlickrSearch(PApplet papplet) {
		p5 = papplet;
		p5.registerDispose(this);
	}



	//-----------------------------------------------------------------------------
	// methods
	//-----------------------------------------------------------------------------
	/**
	 * run() is a required method
	 */
	public void run() {

	}



	//-----------------------------------------------------------------------------
	// sets
	//-----------------------------------------------------------------------------
	protected void setURL(String _url) {
		url = _url;
	}
	
}