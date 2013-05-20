package frederickk.api;

///**
// *	Frederickk.Api 0.0.5
// *	FWeather.java
// *
// *	Ken Frederick
// *	ken.frederick@gmx.de
// *
// *	http://cargocollective.com/kenfrederick/
// *	http://kenfrederick.blogspot.com/
// *
// *	a class to pull weather feed information from
// *	http://www.weather.com/
// *
// */
//
//
//
////-----------------------------------------------------------------------------
//// libraries
////-----------------------------------------------------------------------------
//import processing.core.*;
//import processing.xml.*;
//
//
//
//public class FWeather {
//	//-----------------------------------------------------------------------------
//	// properties
//	//-----------------------------------------------------------------------------
//	private static PApplet p5;
//	private XMLElement xml;
//	protected String place;
//	protected String feed;
//	protected String duration = "5";
//
//	protected String key;
//	protected String parId;
//	
//	private boolean bWerror;
//	private boolean bVerbose = false;
//	
//	//location();
//	private String dham,
//	tm,
//	lat,
//	lon,
//	//sunr,
//	//suns,
//	zone;
//
//	//current()
//	private String lsup,
//	obst,
//	tmp,
//	flik,
//	t,
//	bar_r,
//	bar_d,
//	wind_s,
//	wind_gust,
//	wind_d,
//	wind_t,
//	hmid,
//	vis,
//	uv_i,
//	uv_t,
//	dewp,
//	moon_t;
//
//	//forecast()
//	private String hi,
//	low,
//	sunr,
//	suns,
//	part_day_t,
//	part_day_wind_s,
//	part_day_wind_gust,
//	part_day_wind_d,
//	part_day_wind_t,
//	part_day_bt,
//	part_day_ppcp,
//	part_day_hmid,
//	part_night_t,
//	part_night_wind_s,
//	part_night_wind_gust,
//	part_night_wind_d,
//	part_night_wind_t,
//	part_night_bt,
//	part_night_ppcp,
//	part_night_hmid;
//
//
//
//	//-----------------------------------------------------------------------------
//	// constructor
//	//-----------------------------------------------------------------------------
//	/**
//	 * instantiate FWeather
//	 * 
//	 * @param p
//	 * 			PApplet
//	 * @param key
//	 * 			place code
//	 * @param parId
//	 * 			place code
//	 */
//	public FWeather(PApplet p, String key, String parId, String place) {
//		p5 = p;
//		this.place = place;
//
//		setPlace(place);
//		//if(bVerbose) System.out.println(xml);
//	}
//
//
//
//	//-----------------------------------------------------------------------------
//	// methods
//	//-----------------------------------------------------------------------------
//	private void location() {
//		try {
//			XMLElement loc = xml.getChild(1);
//
//			dham = loc.getChild(0).getContent();
//			tm = loc.getChild(1).getContent();
//			lat = loc.getChild(2).getContent();
//			lon = loc.getChild(3).getContent();
//			sunr = loc.getChild(4).getContent();
//			suns = loc.getChild(5).getContent();
//			zone = loc.getChild(6).getContent();
//
//			XMLElement cc = xml.getChild(3);
//
//			lsup = cc.getChild(0).getContent();
//		} catch(Exception e) {
//			if(bVerbose) System.out.println("Error location(): " + e);
//			bWerror = true;
//		}
//	}
//
//	//-----------------------------------------------------------------------------
//	private void current() {
//		try {
//			XMLElement cc = xml.getChild(3);
//
//			obst = cc.getChild(1).getContent();
//			tmp = cc.getChild(2).getContent();
//			flik = cc.getChild(3).getContent();
//			t = cc.getChild(4).getContent();
//
//			bar_r = cc.getChild(6).getChild(0).getContent();
//			bar_d = cc.getChild(6).getChild(1).getContent();
//
//			wind_s = cc.getChild(7).getChild(0).getContent();
//			wind_gust = cc.getChild(7).getChild(1).getContent();
//			wind_d = cc.getChild(7).getChild(2).getContent();
//			wind_t = cc.getChild(7).getChild(3).getContent();
//
//			hmid = cc.getChild(8).getContent();
//			vis = cc.getChild(9).getContent();
//
//			uv_i = cc.getChild(10).getChild(0).getContent();
//			uv_t = cc.getChild(10).getChild(1).getContent();
//
//			dewp = cc.getChild(11).getContent();
//
//			moon_t = cc.getChild(12).getChild(1).getContent();
//		} catch(Exception e) {
//			if(bVerbose) System.out.println("Error current(): " + e);
//			bWerror = true;
//		}
//	}
//
//	//-----------------------------------------------------------------------------
//	private void forecast(int d) {
//		try {
//			d = d + 1;
//			XMLElement dayf = xml.getChild(4);
//			//if(bVerbose) System.out.println("DAYF");
//			//if(bVerbose) System.out.println(dayf);
//			XMLElement _day = dayf.getChild(d);
//			//if(bVerbose) System.out.println("DAY");
//			//if(bVerbose) System.out.println(_day);
//			hi = _day.getChild(0).getContent();
//			low = _day.getChild(1).getContent();
//			sunr = _day.getChild(2).getContent();
//			suns = _day.getChild(3).getContent();
//
//			//day
//			part_day_t = _day.getChild(4).getChild(1).getContent();
//
//			part_day_wind_s = _day.getChild(4).getChild(2).getChild(0).getContent();
//			part_day_wind_gust = _day.getChild(4).getChild(2).getChild(1).getContent();
//			part_day_wind_d = _day.getChild(4).getChild(2).getChild(2).getContent();
//			part_day_wind_t = _day.getChild(4).getChild(2).getChild(3).getContent();
//
//			part_day_bt = _day.getChild(4).getChild(3).getContent();
//			part_day_ppcp = _day.getChild(4).getChild(4).getContent();
//			part_day_hmid = _day.getChild(4).getChild(5).getContent();
//
//			//night
//			part_night_t = _day.getChild(5).getChild(1).getContent();
//
//			part_night_wind_s = _day.getChild(5).getChild(2).getChild(0).getContent();
//			part_night_wind_gust = _day.getChild(5).getChild(2).getChild(1).getContent();
//			part_night_wind_d = _day.getChild(5).getChild(2).getChild(2).getContent();
//			part_night_wind_t = _day.getChild(5).getChild(2).getChild(3).getContent();
//
//			part_night_bt = _day.getChild(5).getChild(3).getContent();
//			part_night_ppcp = _day.getChild(5).getChild(4).getContent();
//			part_night_hmid = _day.getChild(5).getChild(5).getContent();
//
//		} catch(Exception e) {
//			if(bVerbose) System.out.println(e);
//			bWerror = true;
//		}
//	}
//
//
//
//	//-----------------------------------------------------------------------------
//	//sets
//	//-----------------------------------------------------------------------------
//	protected void setKey(String key) {
//		this.key = key;
//	}
//	protected void setParId(String parId) {
//		this.parId = parId;
//	}
//	
//	//-----------------------------------------------------------------------------
//	/**
//	 * @param place
//	 * 			set location
//	 */ 
//	public void setPlace(String place) {
//		this.place = place;
//		if(bVerbose) System.out.println(place + "!");
//		feed = "http://xoap.weather.com/weather/local/" + place + "?cc=*&dayf=" + duration + "&link=xoap&prod=xoap&par=[PAR_ID]&key=[KEY]";
//		xml = new XMLElement(p5, feed);
//	}
//
//	//-----------------------------------------------------------------------------
//	public void setDuration(int duration) {
//		this.duration = Integer.toString(duration);
//	}
//
//	//-----------------------------------------------------------------------------
//	/**
//	 * @param val
//	 * 			set verbose feedback
//	 */ 
//	public void setVerbose(boolean val) {
//		bVerbose = val;
//	}
//
//	
//	
//	//-----------------------------------------------------------------------------
//	// gets 
//	//-----------------------------------------------------------------------------
//	public boolean getError() {
//		return bWerror;
//	}
//
//	//-----------------------------------------------------------------------------
//	/**
//	 * return place
//	 * 
//	 * @return place
//	 */
//	public String getPlace() {
//		place = xml.getChild(1).getChild(0).getContent();
//		return place;
//	}
//
//	//-----------------------------------------------------------------------------
//	/**
//	 * return duration as String (number of days to forecast)
//	 * 
//	 * @return duration
//	 */
//	public String getDuration() {
//		return duration;
//	}
//	/**
//	 * return duration as int (number of days to forecast)
//	 * 
//	 * @return duration
//	 */
//	public int getDurationInt() {
//		return Integer.parseInt(duration);
//	}
//
//	//-----------------------------------------------------------------------------
//	/**
//	 * return location info as String[]
//	 * lsup, dham, tm, latitude, longitude, sunrise, sunset, timezone
//	 * 
//	 * @return loc
//	 */
//	public String[] getLocation() {
//		location();
//		String[] loc = {
//			lsup,
//			dham,
//			tm,
//			lat,
//			lon,
//			sunr,
//			suns,
//			zone
//		};
//		return loc;
//	}
//
//	//-----------------------------------------------------------------------------
//	/**
//	 * return current conditions as String[]
//	 * obst, temperature, flik, t, bar_r, bar_d, windspeed, wind gust, wind_d, wind_t, humidity, visibility, uv_i, uv_t, dew point, moon_t
//	 * 
//	 * @return loc
//	 */
//	public String[] getCurrent() {
//		current();
//		String[] cc = {
//			obst,
//			tmp,
//			flik,
//			t,
//			bar_r,
//			bar_d,
//			wind_s,
//			wind_gust,
//			wind_d,
//			wind_t,
//			hmid,
//			vis,
//			uv_i,
//			uv_t,
//			dewp,
//			moon_t
//		};
//		return cc;
//	}
//
//	//-----------------------------------------------------------------------------
//	/**
//	 * return current the forecast as String[][]
//	 * String[ duration ][ forcast info for day ]
//	 * hi,low, sunrise, sunset, part_day_t, part_day_windspeed, part_day_wind gust
//	 * 
//	 * @return loc
//	 */
//	public String[][] getForecast() {
//		String[][] fo = new String[ Integer.parseInt(duration) ][20];
//		for (int i=0; i < Integer.parseInt(duration); i++) {
//			forecast(i);
//			fo[i][0] = hi;
//			fo[i][1] = low;
//			fo[i][2] = sunr;
//			fo[i][3] = suns;
//			fo[i][4] = part_day_t;
//			fo[i][5] = part_day_wind_s;
//			fo[i][6] = part_day_wind_gust;
//			fo[i][7] = part_day_wind_d;
//			fo[i][8] = part_day_wind_t;
//			fo[i][9] = part_day_bt;
//			fo[i][10] = part_day_ppcp;
//			fo[i][11] = part_day_hmid;
//			fo[i][12] = part_night_t;
//			fo[i][13] = part_night_wind_s;
//			fo[i][14] = part_night_wind_gust;
//			fo[i][15] = part_night_wind_d;
//			fo[i][16] = part_night_wind_t;
//			fo[i][17] = part_night_bt;
//			fo[i][18] = part_night_ppcp;
//			fo[i][19] = part_night_hmid;
//		}
//		return fo;
//	}
//}