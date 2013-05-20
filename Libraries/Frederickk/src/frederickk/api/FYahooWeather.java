package frederickk.api;

/**
 *	Frederickk.Api 0.0.5
 *	FYahooWeather.java
 *
 *	Ken Frederick
 *	ken.frederick@gmx.de
 *
 *	http://cargocollective.com/kenfrederick/
 *	http://kenfrederick.blogspot.com/
 *
 *	a class to pull weather feed information from
 *	http://yahoo.com/weather
 *  http://developer.yahoo.com/weather/
 *  
 */



//-----------------------------------------------------------------------------
// libraries
//-----------------------------------------------------------------------------
import processing.core.*;
import processing.data.XML;



public class FYahooWeather {
	//-----------------------------------------------------------------------------
	// properties
	//-----------------------------------------------------------------------------
	private static final String WOEID_URL = "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20geo.places%20where%20text%3D%22";
	private static final String WEATHER_URL = "http://weather.yahooapis.com/forecastrss?w=";
	private static final String [] XPATHS = {"//yweather:atmosphere[@humidity]", 
		                                     "//yweather:condition[@temp]", 
		                                     "//yweather:condition[@text]", 
		                                     "//yweather:wind[@direction]" };

	private static PApplet p5;
	private XML xml;

	protected String place;

	
	
	//-----------------------------------------------------------------------------
	// constructor
	//-----------------------------------------------------------------------------
	public FYahooWeather(PApplet papplet, String place) {
		p5 = papplet;
		setPlace(place);
	}
	
	
	
	//-----------------------------------------------------------------------------
	// sets
	//-----------------------------------------------------------------------------
	protected void setPlace(String place) {
		this.place = place;
	}

	
	
	//-----------------------------------------------------------------------------
	// gets
	//-----------------------------------------------------------------------------
	private String getWoeid() {
		String woeid = null;
//		try {
//			String content = Utilities.readUrl(new URL(WOEID_URL + place + "%22"));
//			Document doc = Utilities.documentFromString(content, true);
//			NodeList nl = Utilities.executeXPath(doc, "//yahooDefault:woeid");
//			if (nl.item(0) != null)
//			{
//				woeid = nl.item(0).getTextContent();
//			}
//		} catch (MalformedURLException mue)
//		{
//			mue.printStackTrace();
//		}
		return woeid;
	}

}