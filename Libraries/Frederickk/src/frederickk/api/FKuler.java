package frederickk.api;

/**
 *  Frederickk.Api 0.0.5
 *  FKuler.java
 *
 *  Ken Frederick
 *  ken.frederick@gmx.de
 *
 *  http://cargocollective.com/kenfrederick/
 *  http://kenfrederick.blogspot.com/
 *
 *  a library for easier use of the kuler api
 *  http://github.com/frederickk/frederickk
 *
 */



//-----------------------------------------------------------------------------
// libraries
//-----------------------------------------------------------------------------
import processing.core.*;
import processing.data.XML;
//import java.util.ArrayList;



public class FKuler {
//public class FKuler	implements Runnable {
	//-----------------------------------------------------------------------------
	// properties
	//-----------------------------------------------------------------------------
	private PApplet papplet;
	//	private Thread thread;

	private String serverPage = "http://kuler-api.adobe.com/rss/";
	private String typ;
	private String pageTyp = ".cfm";

	private int maxItems = 20;
	private int startIndex = 0;

	private String query;
	private String key;

	//private String[] FKulerThemes;

	private XML[] themeItems;
	private XML themeItem;

	private XML[] themeSwatches;
	private XML[] themeTag;

	private boolean bVerbose = false;



	//-----------------------------------------------------------------------------
	// constructor
	//-----------------------------------------------------------------------------
	/**
	 * initialize the FControl gui elements class
	 * 
	 * @param p5
	 * 			instance of the applet
	 * @param _key 
	 * 			required Kuler developer API key http://kuler.adobe.com/api 
	 * 
	 */
	public FKuler(PApplet p5, String _key) {
		papplet = p5;
		setKey(_key);
	}



	//-----------------------------------------------------------------------------
	// methods
	//-----------------------------------------------------------------------------
	//	/**
	//	 * run() is a required method
	//	 */
	//	public void run() {
	//		gather();
	//	}
	//
	//	/**
	//	 * let the threading end
	//	 */
	//	public void stop() {
	//		thread = null;
	//	}
	//
	//	/**
	//	 * this will magically be called by the parent once the user hits stop
	//	 * this functionality hasn't been tested heavily so if it doesn't work, file a bug
	//	 */
	//	public void dispose() {
	//		stop();
	//	}


	private void initialize(String query, String typ) { 
		String url = serverPage + typ + pageTyp + "?itemsPerPage=" + maxItems + "&startIndex=" + startIndex + query + "&key=" + key;
		String urlPrint = typ + pageTyp + "?itemsPerPage=" + maxItems + "&startIndex=" + startIndex + query;

		//ArrayList themes = new ArrayList();
		XML xml;

		try {
			xml = new XML( papplet, url.toString() );

			if(bVerbose) System.out.println("\n" + urlPrint.toString());
			if(bVerbose) System.out.println("-----------------------------------------------------------------------------");

			if (xml.getChild("success") != null && xml.getChild("success").getContent().equals("false")) {
				if(bVerbose) System.out.println("The following error appears while calling kuler service:");
				if(bVerbose) System.out.println(xml.getChild("error/errorText").getContent());
				if(bVerbose) System.out.println("-----------------------------------------------------------------------------");

			} else {
				themeItems = xml.getChildren("channel/item/kuler:themeItem");
				if(bVerbose) System.out.println(themeItems.length + " theme results were returned");

				/*
	        for(int i=0; i<themeItems.length; i++) {
	          themeSwatches = themeItems[i].getChildren("kuler:themeSwatches/kuler:swatch/kuler:swatchHexColor");

	          int[] swatches = new int[themeSwatches.length];
	          for (int j=0; j < themeSwatches.length; j++) {
	            swatches[j] = unhex("FF" + themeSwatches[j].getContent());
	            if(bVerbose) System.out.println("swatch color " + j + " " + swatches[j]);
	          }

	        }
	        if(bVerbose) System.out.println("-----------------------------------------------------------------------------");
				 */
			}

		} catch(Exception e) {
			if(bVerbose) System.out.println("FKuler XML Error " + e);
			if(bVerbose) System.out.println("-----------------------------------------------------------------------------");
		}

	}



	//-----------------------------------------------------------------------------
	//sets
	//-----------------------------------------------------------------------------
	/**
	 * @param key 
	 * 			required Kuler developer API key http://kuler.adobe.com/api 
	 */
	public void setKey(String key) {
		this.key = key;
	}

	//-----------------------------------------------------------------------------
	/**
	 * @param maxItems
	 * 			max number of themes to return 
	 */
	public void setMaxItems(int maxItems) {
		this.maxItems = maxItems;
	}

	//-----------------------------------------------------------------------------
	/**
	 * @param startIndex
	 * 			A 0-based index into the list that specifies the first item to display.
	 * 			Default is 0, which displays the first item in the list. 
	 */
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	//-----------------------------------------------------------------------------
	/**
	 * @param num
	 * 			theme 
	 */
	public void setTheme(int num) {
		themeItem = themeItems[num];
		if(num < themeItems.length) {
			themeSwatches = themeItem.getChildren("kuler:themeSwatches/kuler:swatch/kuler:swatchHexColor");
			//themeTag = themeItem.getChildren("kuler:themeTags"); //)[0].getContent());
			themeTag = themeItem.getChildren("kuler:themeTags");//[0].getContent();
		}
	}

	//-----------------------------------------------------------------------------
	/**
	 * @param val
	 * 			toggle verbose feedback 
	 */
	public void setVerbose(boolean val) {
		bVerbose = val;
	}



	//-----------------------------------------------------------------------------
	// gets 
	//-----------------------------------------------------------------------------
	/**
	 * @param search
	 * 			themes containing entered search query will be returned 
	 */
	public void getSearch(String search) {
		query = "&searchQuery=" + search;
		typ = "search";

		initialize(query, typ);
	}

	/**
	 * search for themes using one of the predefined filters listed:
	 * 
	 * @param searchQuery
	 * 			themeID - search on a specific themeID
	 * 			userID - search on a specific userID
	 * 			email - search on a specific email
	 * 			tag - search on a tag word
	 * 			hex - search on a hex color value (can be in the format "ABCDEF" or "0xABCDEF")
	 * 			title - search on a theme title
	 * @param val
	 * 			themes containing entered query value 
	 */
	public void getSearch(String searchQuery, String val) {
		query = "&searchQuery=" + searchQuery + ":" + val;
		typ = "search";

		initialize(query, typ);
	}


	//-----------------------------------------------------------------------------
	/**
	 * @param tag
	 * 			themes containing entered tag will be returned 
	 */
	public void getSearchTag(String tag) {
		query = "&searchQuery=tag:" + tag;
		typ = "search";

		initialize(query, typ);
	}

	//-----------------------------------------------------------------------------
	/**
	 * @param hex
	 * 			themes containing entered hex color value (can be in the format "ABCDEF" or "0xABCDEF") will be returned 
	 */
	public void getSearchHex(String hex) {
		query = "&searchQuery=hex:" + hex;
		typ = "search";

		initialize(query, typ);
	}


	//-----------------------------------------------------------------------------
	/**
	 * most recent themes will be returned 
	 * 
	 * @param timeSpan
	 * 			value in days to limit the set of themes retrieved
	 */
	public void getRecent(int timeSpan) {
		query = "&listtype=recent&timespan=" + timeSpan;
		typ = "get";

		initialize(query, typ);
	}

	/**
	 * most recent themes will be returned 
	 */
	public void getRecent() {
		getRecent(0);
	}


	//-----------------------------------------------------------------------------
	/**
	 * most popular themes will be returned 
	 * 
	 * @param timeSpan
	 * 			value in days to limit the set of themes retrieved
	 */
	public void getPopular(int timeSpan) {
		query = "&listtype=popular&timespan=" + timeSpan;
		typ = "get";
		initialize(query, typ);
	}

	/**
	 * most popular themes will be returned 
	 */
	public void getPopular() {
		getPopular(0);
		//		query = "&listType=popular";
		//		typ = "get";
		//		initialize(query, typ);
	}


	//-----------------------------------------------------------------------------
	/**
	 * highest-rated themes will be returned 
	 * 
	 * @param timeSpan
	 * 			value in days to limit the set of themes retrieved
	 */
	public void getHighestRated(int timeSpan) {
		query = "&listtype=rating&timespan=" + timeSpan;
		typ = "get";
		initialize(query, typ);
	}

	/**
	 * highest-rated themes will be returned 
	 */
	public void getHighestRated() {
		getHighestRated(0);
	}




	//-----------------------------------------------------------------------------
	/**
	 * random themes will be returned 
	 * 
	 * @param timeSpan
	 * 			value in days to limit the set of themes retrieved
	 */
	public void getRandom(int timeSpan) {
		query = "&listtype=random&timespan=" + timeSpan;
		typ = "get";
		initialize(query, typ);
	}

	/**
	 * random themes will be returned 
	 */
	public void getRandom() {
		getRandom(0);
	}


	//-----------------------------------------------------------------------------
	/**
	 * 
	 * @return the number of themes found
	 * 			 
	 */
	public int getThemeCount() {
		try {
			return themeItems.length;
		} catch (Exception e) {
			if(bVerbose) System.out.println("getThemeCount() error " + e);
			return 0;
		}
	}

	//-----------------------------------------------------------------------------
	/**
	 * 
	 * @return the swatches of all the themes returned as an int array
	 * 			 
	 */
	public int[] getSwatches() {
		themeSwatches = themeItems[0].getChildren("kuler:themeSwatches/kuler:swatch/kuler:swatchHexColor");

		int[] swatches = new int[themeSwatches.length];
		for (int j=0; j < themeSwatches.length; j++) {
			swatches[j] = PApplet.unhex("FF" + themeSwatches[j].getContent());
		}

		return swatches;
	}


	/**
	 * 
	 * @param t
	 * 			theme number to return swatches from
	 * 
	 * @return the swatches of chosen theme returned as an int array
	 * 			 
	 */
	public int[] getSwatches(int t) {
		try {
			themeSwatches = themeItems[t].getChildren("kuler:themeSwatches/kuler:swatch/kuler:swatchHexColor");

			int[] swatches = new int[themeSwatches.length];
			for (int j=0; j < themeSwatches.length; j++) {
				swatches[j] = PApplet.unhex("FF" + themeSwatches[j].getContent());
			}
			return swatches;

		} catch(Exception e) {
			//if(bVerbose) System.out.println("getSwatches() error " + e);
			int[] swatches = {};
			return swatches;
		}

	}

	//-----------------------------------------------------------------------------
	public String[] getThemeTag() {
		String[] themeTags = new String[themeTag.length];
		try {
			String holder = themeTag[0].getContent().toString();
			String[] tagsList = PApplet.split(holder, ", ");

			return tagsList;
		} catch(Exception e) {
			//if(bVerbose) System.out.println("error reading tags: " + e);
			themeTags[0] = "";
		}
		return themeTags;
	}



}