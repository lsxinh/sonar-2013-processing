package frederickk.api;

/**
 *  Frederickk.Api 0.0.5
 *  FFlickr.java
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
import processing.data.XML;
import java.util.ArrayList;



public class FFlickr implements FFlickrConstants, Runnable {
	//-----------------------------------------------------------------------------
	// properties
	//-----------------------------------------------------------------------------
	private static PApplet papplet;
	private Thread thread;

	private static String key = "";		//"681a16a0f5448bb7c7db02431e59c7fa";
	private static String secret = "";	//"919c1384422fa4aa";
	private static String userID = "";	//"7632105@N06";

	private String method = PHOTOS_SEARCH;
	private String date = "";
	private int maxItems = 1;
	private String mstb = "-";
	private int privacy = 1; 

	private String query = "";
	private String tag = "";

	protected ArrayList<PImage> images;
	protected ArrayList<String> imageList;
	// FFlickrLoader imageLoader;
	private int width;
	private int height;

	private String sizeURL;
	private String[] idList;
	public String search;	

	private boolean FIRST_RUN = true;


	
	//-----------------------------------------------------------------------------
	// constructor
	//-----------------------------------------------------------------------------
	/**
	 * instantiate FFlickr
	 * 
	 * @param papplet
	 * 			PApplet
	 */
	public FFlickr(PApplet _papplet) {
		papplet = _papplet;
		//imageLoader = new FFlickrLoader(papplet);
	}

	/**
	 * instantiate FFlickr
	 * 
	 * @param papplet
	 * 			PApplet
	 * @param _key
	 * 			authentication key
	 * 			http://www.flickr.com/services/api/misc.userauth.html
	 * @param _secret
	 * 			authentication secret
	 * 			http://www.flickr.com/services/api/misc.userauth.html
	 */
	public FFlickr(PApplet _papplet, String _key, String _secret) {
		papplet = _papplet;
		key = _key;
		secret = _secret;
		//imageLoader = new FFlickrLoader(papplet);
	}

	private void noKey() {
		System.out.println( "" );
		System.out.println( "-----------------------------------------------------------------------------" );
		System.out.println( "Error: no authentication key was entered" );
		System.out.println( "http://www.flickr.com/services/api/misc.userauth.html" );
	}


	
	//-----------------------------------------------------------------------------
	// methods
	//-----------------------------------------------------------------------------
	/**
	 * @param _query
	 * 			search criteria
	 * @throws Exception 
	 */ 
	public void search(String _query) throws Exception {
		query = _query;
		initialize(query);
	}

	/**
	 * @param _query
	 * 			search criteria
	 * @throws Exception 
	 */ 
	public void search(String _query, String _method) throws Exception {
		query = _query;
		method = _method;
		initialize(query);
	}

	/*
	public void searchCustom(String _url) {
		//url = _url;
	}
	 */

	private void initialize(String query) throws Exception {
		if( key != "" ) {
			if(!FIRST_RUN) {
				thread = new Thread(this);
				thread.start();
			} else {
				gather();
			}
		} else {
			noKey();
		}
	}


	/**
	 * gather() is threadless
	 * 
	 * @throws Exception 
	 */
	protected void gather() throws Exception {
		String url = SERVICE_URL_REST + 
		"?method=" + method +
		"&api_key=" + key +
		"&date=" + date +
		"&tags=" + query +
		"&sort=relevance" +
		"&privacy_filter=" + privacy +
		"&per_page=" + maxItems +
		"&page=1";
		//System.out.println(url);


		XML xml = new XML( papplet, url );
		XML photos = xml.getChild(0);
		XML[] photo = photos.getChildren();

		if(photo.length > 0) {
			//System.out.println( photo.length );

			idList = new String[photo.length];
			imageList = new ArrayList<String>();
			//images = new ArrayList();

			for(int i=0; i<photo.length; i++) {
				XML kid = photos.getChild(i);

				String farm = kid.getString("farm");
				String server = kid.getString("server");
				String id = kid.getString("id");
				idList[i] = id;
				String secret = kid.getString("secret");

				System.out.println("http://farm" + farm + ".static.flickr.com/" + server + "/" + id + "_" + secret + mstb + ".jpg?v=0");
				//String imageURL = "http://farm" + farm + ".static.flickr.com/" + server + "/" + id + "_" + secret + mstb + ".jpg?v=0";
				//images[i] = papplet.loadImage( imageURL );

				//get the sizes of the photos
				getSizes(id, 3);
				//get the list of tags
				//getTagList(id);
				//tag = getTagList(id)[0];
				//System.out.print("search\t" + search + " -> tag\t");
				/*for(int j=0; j<getTagList(id).length; j++) {
			System.out.print( "["+j+"] " + getTagList(id)[j] + ", " );
		}
		System.out.println("");
				 */

				//load the image
				imageList.add( sizeURL );
				//images[i] = papplet.loadImage(sizeURL);
				images.add( papplet.loadImage(sizeURL) );

			}
		}

		//feed our loader
		//imageLoader.toLoad( imageList );
		//imageLoader.start();

		if(FIRST_RUN) FIRST_RUN = false;
	}
	
	/**
	 * run() is a required method
	 */
	public void run() {
		try {
			gather();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * let the threading end
	 */
	public void stop() {
		thread = null;
	}

	/**
	 * this will magically be called by the parent once the user hits stop
	 * this functionality hasn't been tested heavily so if it doesn't work, file a bug
	 */
	public void dispose() {
		stop();
	}


	
	//-----------------------------------------------------------------------------
	// sets
	//-----------------------------------------------------------------------------
	/**
	 * @param _key
	 * 			authentication key
	 * 			http://www.flickr.com/services/api/misc.userauth.html
	 */ 
	public void setKey(String _key) {
		key = _key;
	} 

	/**
	 * @param _secret
	 * 			authentication secret
	 * 			http://www.flickr.com/services/api/misc.userauth.html
	 */ 
	public void setSecret(String _secret) {
		secret = _secret;
	}

	/**
	 * @param _userID
	 * 			flickr user identification
	 */
	public void setUserID(String _userID) {
		userID = _userID;
	}

	/**
	 * @param _method
	 * 			set method (FFlickrConstants)
	 * 			http://www.flickr.com/services/api/
	 */ 
	public void setAPIMethod(String _method) {
		method = _method;
	}

	/**
	 * @param _date
	 * 			set for date sensitive searches YYYY-MM-DD ("2010-10-02")
	 */ 
	public void setDate(String _date) {
		date = _date;
	}

	/**
	 * @param _maxItems
	 * 			maximum number of items to return
	 */ 
	public void setMaxItems(int _maxItems) {
		maxItems = _maxItems;
	}

	/**
	 * @param _mstb
	 * 			s - small square 75x75</br>
	 * 			t - thumbnail, 100 on longest side</br>
	 * 			m - small, 240 on longest side</br>
	 * 			- - medium, 500 on longest side</br>
	 * 			b - large, 1024 on longest side (only exists for very large original images)</br>
	 * 			o - original image, either a jpg, gif or png, depending on source format
	 *
	 */
	public void setSize(String _mstb) {
		mstb = "_" + _mstb;
	}

	/**
	 * @param _privacy
	 * 			set privacy filter</p>
	 * 
	 * 			1 - public photos</br>
	 * 			2 - private photos visible to friends</br>
	 * 			3 - private photos visible to family</br>
	 * 			4 - private photos visible to friends & family</br>
	 * 			5 - completely private photos
	 */
	public void setPrivacy(int _privacy) {
		privacy = _privacy;
	}


	
	//-----------------------------------------------------------------------------
	// gets
	//-----------------------------------------------------------------------------
	/**
	 * @param _search
	 * 			search by tag
	 * @throws Exception 
	 * 
	 */ 
	public void getSearchTag(String _search) throws Exception {
		search = _search;
		query = "&tags=" + search;
		search(query);
	}

	/**
	 * @param _search
	 * 			search by text
	 * @throws Exception 
	 * 
	 */ 
	public void getSearchText(String _search) throws Exception {
		search = _search;
		query = "&text=" + search;
		search(query);
	}

	/**
	 * return images found as an array
	 * 
	 */ 
	public PImage[] getImages() {
		//images = imageLoader.getImages();
		//System.out.println( "images.size()\t" + imageLoader.getImages() );

		//PImage[] imagesArray = null;
		PImage[] imagesArray;
		imagesArray = new PImage[images.size()];
		for(int i=0; i<imagesArray.length; i++) {
			PImage img = images.get(i);
			imagesArray[i] = img;
		}
		//imageLoader.getImages().toArray(imagesArray);

		return imagesArray;
	}

	public ArrayList<String> getImageList() {
		return imageList;
	}

	/**
	 * return number of images found
	 * 
	 * @return images
	 */
	public int getImageNum() {
		try {
			return getImages().length;
		} catch (Exception e) {
			System.out.println("getImageNum() error " + e);
			return 0;
		}
	}

	/**
	 * @param w
	 *		  index of image to return
	 *		  
	 * return a specific image
	 * 
	 * @return images[w]
	 */
	public PImage getImage(int w) {
		w = inBounds(w);

		try {
			return getImages()[w];
		} catch (Exception e) {
			System.out.println("getImage(" + w + ") error " + e);
			return null;
		}
	}

	/**
	 * return the sizes of photo
	 *		  
	 * @param id
	 *		  photo id
	 * @param sz
	 *		  index of image to return</p>
	 *		  
	 *		  0 - Square</br>
	 *		  1 - Thumbnail</br>
	 *		  2 - Small</br>
	 *		  3 - Medium
	 *		  
	 * @throws Exception 
	 */
	public void getSizes(String id, int sz) throws Exception {
		String url = SERVICE_URL_REST + 
		"?method=" + PHOTOS_GET_SIZES + 
		"&api_key=" + key +
		"&photo_id=" + id;
		//System.out.println(url);

		XML xml = new XML( papplet, url );
		XML photos  = xml.getChild(0);
		XML[] sizes = photos.getChildren();

		sizeURL = sizes[sz].getString("source");
		width = sizes[sz].getInt("width");
		height = sizes[sz].getInt("height");

		System.out.println(sizes[sz].getString("label") + " : " + sizeURL);
	}	

	/**
	 * return the id list as an array
	 * 
	 * @return idList
	 */
	public String[] getIdList() {
		return idList;
	}

	/**
	 * return the search tag
	 * 
	 * @return tag
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * @param id
	 *		  photo id
	 *		  
	 * return the search tag list of photo as array
	 * 
	 * @return tag
	 * @throws Exception 
	 */
	public String[] getTagList(String id) throws Exception {
		String url = SERVICE_URL_REST + 
		"?method=" + TAGS_LIST_PHOTO +
		"&api_key=" + key +
		"&photo_id=" + id;

		XML xml = new XML( papplet, url );
		XML photos  = xml.getChild(0);
		XML tags = photos.getChild(0);
		XML[] tag = tags.getChildren();

		String[] tagsList = new String[tag.length];
		for(int i=0; i<tagsList.length; i++) {
			tagsList[i] = tag[i].getString("raw");
		}

		return tagsList;
	}  

	/**
	 * @param query
	 *		  search criteria
	 *		  
	 * return the search as a raw XML
	 * 
	 * @return xml
	 * @throws Exception 
	 */
	public XML getRawFeed(String query) throws Exception {
		if( key != "" ) {
			String url = SERVICE_URL_REST + 
			"?method=" + method +
			"&api_key=" + key +
			"&date=" + date +
			"&tags=" + query +
			"&sort=relevance" +
			"&privacy_filter=" + privacy +
			"&per_page=" + maxItems +
			"&page=1";
			//System.out.println(url);

			return new XML( papplet, url );
		} else {
			noKey();
			return null;
		}
	}

	/**
	 * @param query
	 *		  search criteria
	 * @param _method
	 * 			set method (FFlickrConstants)
	 * 			http://www.flickr.com/services/api/
	 * @param tokens
	 *		  additional url tokens that correspond to api method
	 *		  (i.e. method = INTERESTINGNESS tokens = "&date=2010-10-02&per_page=10&page=1")
	 *		  
	 * return the search as a raw XML
	 * 
	 * @return xml
	 * @throws Exception 
	 */
	public XML getRawFeed(String query, String _method, String tokens) throws Exception {
		if( key != "" ) {
			String url = SERVICE_URL_REST + 
			"?method=" + _method +
			"&api_key=" + key;
			url += tokens;
			System.out.println(url);

			return new XML( papplet, url );
		} else {
			noKey();
			return null;
		}
	}


	/**
	 * @param w
	 *		  value to keep within the range of images found
	 */
	private int inBounds(int w) {
		//w = PApplet.constrain(w, 0,getImageNum());
		//return w;
		return (w < 0) ? 0 : ((w > getImageNum()) ? getImageNum() : w);
	}

}
