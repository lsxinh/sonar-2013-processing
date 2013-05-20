package frederickk.api;

/**
 *	Frederickk.Api 0.0.5
 *	FPrettyColors.java
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
 */



//-----------------------------------------------------------------------------
// libraries
//-----------------------------------------------------------------------------
import processing.core.*;
import processing.data.XML;
import java.util.ArrayList;
import java.util.Collections; 

import frederickk.tools.FColor;
import frederickk.tools.BrightnessComparator;



public class FPrettyColors {
	//-----------------------------------------------------------------------------
	// properties
	//-----------------------------------------------------------------------------
	private PApplet papplet;
	private int total;
	private int returned;

	private int numToReturn = 50;
	private int colorsReturn[];

	// palette types
	private FPrettyColorsPalette paletteOrder[];
	private FPrettyColorsPalette paletteBrightness[];
	private FPrettyColorsPalette paletteComplement[];
	private FPrettyColorsPalette paletteRandom;
	private FPrettyColorsPalette paletteRandomAll;

	/**
	 * pallete statics
	 * 
	 * ORDER palettes based on posting order, most recent colors appear first</br>
	 * BRIGHTNESS palettes based on brightness* (default creates 10 palettes)</br>
	 * COMPLEMENT palettes of complementary* colors (default creates 10 palettes)</br>
	 * RANDOM palettes of random colors (default uses the 50 most recent colors)</br>
	 * RANDOM_ALL palettes of random colors (uses all posted colors)
	 */
	public static final int ORDER = 0;
	public static final int BRIGHTNESS = 1;
	public static final int COMPLEMENT = 2;
	public static final int RANDOM = 3;
	public static final int RANDOM_ALL = 4;
	
	
	
	//-----------------------------------------------------------------------------
	// constructor
	//-----------------------------------------------------------------------------
	/**
	 * instantiate FPrettyColors
	 * 
	 * @param _papplet
	 * 			PApplet
	 */
	public FPrettyColors(PApplet _papplet) {
		papplet = _papplet;

		try {
			XML xml = new XML(papplet, "http://prettycolors.tumblr.com/api/read?num=" + numToReturn + "&filter=text");
			XML posts = xml.getChild(1);
			total = posts.getInt("total");
			returned = posts.getChildCount();
	
			//collect all returned colors into array
			colorsReturn = new int[returned];
			for (int i=0; i<colorsReturn.length; i++) {
				String hexs = posts.getChild(i).getChild(0).getContent();
				colorsReturn[i] = FColor.getColor(hexs);
			}
			
			//sort the collected colors into palettes
			paletteOrder = new FPrettyColorsPalette[ (returned/5) ];
			paletteBrightness = new FPrettyColorsPalette[ (returned/5) ];
			paletteComplement = new FPrettyColorsPalette[ returned ];

			paletteRandom = new FPrettyColorsPalette(papplet);
			paletteRandom.type = "random";
			paletteRandomAll = new FPrettyColorsPalette(papplet);
			paletteRandomAll.type = "randomAll";
	
			createPaletteOrder();
			createPaletteBrightness();
			createPaletteComplement();
			createPaletteRandomAll();

		} catch(Exception e) {
			System.out.println("error: " + e);
			System.out.println("probably because the tumblr servers are down. shocking, i know");
		}
	}



	//-----------------------------------------------------------------------------
	// methods
	//-----------------------------------------------------------------------------
	private int parseElement(XML xml, int num) {
		XML post = xml.getChild(1);
		int count = post.getChildCount();

		if (num > count) num = count;
		String hexs = post.getChild(num).getChild(0).getContent();
		return FColor.getColor(hexs);
	}


	//-----------------------------------------------------------------------------
	/**
	 *	create palettes (groups of 5), based on order posted
	 */
	private void createPaletteOrder() {
		int index = 0;
		for(int i=0; i<paletteOrder.length; i++) {
			paletteOrder[i] = new FPrettyColorsPalette(papplet);
			paletteOrder[i].type = "order_" + Integer.toString(i);
			for(int j=0; j<paletteOrder[i].AMOUNT; j++) {
				paletteOrder[i].set(j, colorsReturn[index]);
				index++;
			}
		}
	}

	/**
	 *	create palettes (groups of 5), based on brightness
	 */
	private void createPaletteBrightness() {
		ArrayList<Integer> colorsTemp = new ArrayList<Integer>();
		for(int i=0; i<colorsReturn.length; i++) {
			colorsTemp.add( new Integer(colorsReturn[i]) );
		}
		Collections.sort(colorsTemp, new BrightnessComparator());
		
		int index = 0;
		for(int i=0; i<paletteBrightness.length; i++) {
			paletteBrightness[i] = new FPrettyColorsPalette(papplet);
			paletteBrightness[i].type = "brightness_" + Integer.toString(i);
			for(int j=0; j<paletteBrightness[i].AMOUNT; j++) {
				paletteBrightness[i].set(j, colorsTemp.get(index).intValue());
				index++;
			}
		}

	}

	
	/**
	 *	create palettes (groups of 5), based on complements
	 *	very very very rudimentary, if not downright wrong
	 */
	private void createPaletteComplement() {
		findContrast(getRandom(), colorsReturn);
		
		for(int i=0; i<paletteComplement.length; i++) {
			int con = findContrast(colorsReturn[i], colorsReturn);
			int sat = findSaturation(con, colorsReturn);

			paletteComplement[i] = new FPrettyColorsPalette(papplet);
			paletteComplement[i].type = "complement_" + Integer.toString(i);
			paletteComplement[i].set(0, colorsReturn[i]);
			paletteComplement[i].set(1, con);
			paletteComplement[i].set(2, findContrast(con, colorsReturn));
			paletteComplement[i].set(3, sat);
			paletteComplement[i].set(4, findSaturation(sat, colorsReturn));
		}
	}


	/**
	 * TODO:
	 *	create palettes (groups of 5), based on color rules -- analagous, complementary, etc.
	 */



	//-----------------------------------------------------------------------------
	/**
	 *	create palettes (groups of 5), random returned
	 */
	private void createPaletteRandom() {
		for(int j=0; j<paletteRandom.AMOUNT; j++) {
			paletteRandom.set(j, getRandom());
		}
	}

	/**
	 *	create palettes (groups of 5), random all
	 */
	private void createPaletteRandomAll() {
		for(int j=0; j<paletteRandomAll.AMOUNT; j++) {
			paletteRandomAll.set(j, getRandom());
		}
	}



	//-----------------------------------------------------------------------------
	/**
	 * draw palettes as rectangles to the screen
	 * number of colors per palette default is 5
	 * 
	 * @param x
	 * 			x position of palette
	 * @param y
	 * 			y position of palette
	 * @param w
	 * 			width of palette
	 * @param h
	 * 			height of palette
	 * @param type
	 * 			type of palette to draw</br>
	 * 			ORDER based on posting order, most recent colors appear first</br>
	 * 			BRIGHTNESS based on brightness</br>
	 * 			COMPLEMENT complementary colors</br>
	 * 			RANDOM random colors (default uses the 50 most recent colors)</br>
	 * 			RANDOM_ALL random colors (uses all posted colors)
	 * 
	 */
	public void draw(float x, float y, float w, float h, int type) {
		draw(x,y, w,h, type,0);
	}

	/**
	 * draw palettes as rectangles to the screen
	 * 
	 * @param x
	 * 			x position of palette
	 * @param y
	 * 			y position of palette
	 * @param w
	 * 			width of palette
	 * @param h
	 * 			height of palette
	 * @param type
	 * 			type of palette to draw</br>
	 * 			ORDER based on posting order, most recent colors appear first</br>
	 * 			BRIGHTNESS based on brightness</br>
	 * 			COMPLEMENT complementary colors</br>
	 * 			RANDOM random colors (default uses the 50 most recent colors)</br>
	 * 			RANDOM_ALL random colors (uses all posted colors)
	 * @param num
	 * 			num of palettes to draw
	 * 
	 */	public void draw(float x, float y, float w, float h, int type, int num) {
		int amt = getPalette(type,num).AMOUNT;
		for(int i=0; i<amt; i++) {
			papplet.fill( getPalette(type,num).get(i) );
			papplet.rect(i*(w/amt),y, w/amt,h);
		}
	}



	//-----------------------------------------------------------------------------
	private int findBrightness(int colOrig, int target) {
		int val = -1;
		int thresh = 5;
		for(int i=0; i<colorsReturn.length; i++) {
			int r = 5;
			do {
				if( FColor.brightness(colorsReturn[i]) > target-r &&
					FColor.brightness(colorsReturn[i]) < target+r ) {
					val = colorsReturn[i];
					if(val != -1) break;
				} else {
					r+=5;							
				}
			} while(val == -1);
		}
		return val;
	}
	
	private int findSaturation(int colOrig, int target) {
		int val = -1;
		for(int i=0; i<colorsReturn.length; i++) {
			int r = 5;
			do {
				if( FColor.saturation(colorsReturn[i]) > target-r && 
					FColor.saturation(colorsReturn[i]) < target+r ) {
					val = colorsReturn[i];
					if(val != -1) break;
				} else {
					r+=5;							
				}
			} while(val == -1);
		}
		return val;
	}

	//-----------------------------------------------------------------------------
	private int findContrast(int colOrig, int[] colAll) {
		int target = 0;
		if(FColor.brightness(colOrig) > 255*0.4) { //0.3) {
			target = (int) (255*0.1f + FColor.brightness(colOrig) * 0.25f);
			return findBrightness(colOrig, target);
		} else {
			target = (int) (255*1.0f - FColor.brightness(colOrig) * 0.25f);
			return findBrightness(colOrig, target);
		}
	}
	private int findSaturation(int colOrig, int[] colAll) {
		int target = 0;
//		target = (int) (255*0.1f + FColor.saturation(colOrig) * 0.25f);
//		return findSaturation(colOrig, target);
		if(FColor.saturation(colOrig) > 255*0.4) { //0.3) {
			target = (int) (255*0.1f + FColor.saturation(colOrig) * 0.25f);
			return findSaturation(colOrig, target);
		} else {
			target = (int) (255*1.0f - FColor.saturation(colOrig) * 0.25f);
			return findSaturation(colOrig, target);
		}
	}



	//-----------------------------------------------------------------------------
	// gets 
	//-----------------------------------------------------------------------------
	/**
	 * returns first palette of a particular type
	 * 
	 * @param type
	 * 			type of palette to return
	 * 			ORDER, BRIGHTNESS, COMPLEMENT, RANDOM, RANDOM_ALL
	 * 
	 * @return result
	 */
	public FPrettyColorsPalette getPalette(int type) {
		return getPalette(type,0);
	}

	/**
	 * returns a palette of a particular type
	 * 
	 * @param type
	 * 			type of palette to return
	 * 			ORDER, BRIGHTNESS, COMPLEMENT, RANDOM, RANDOM_ALL
	 * @param index
	 * 			index number of palette
	 * 
	 * @return result
	 */
	public FPrettyColorsPalette getPalette(int type, int index) {
		if(type == ORDER) {
			return paletteOrder[ PApplet.constrain(index, 0,paletteOrder.length) ];

		} else if(type == BRIGHTNESS) {
			return paletteBrightness[ PApplet.constrain(index, 0,paletteBrightness.length) ];
		
		} else if(type == COMPLEMENT) {
			return paletteComplement[ PApplet.constrain(index, 0,paletteComplement.length) ];

		} else if(type == RANDOM) {
			createPaletteRandom();
			return paletteRandom;

		} else if(type == RANDOM_ALL) {
			//put the reload into a thread?
			createPaletteRandomAll();
			return paletteRandomAll;

		} else {
			System.out.println("not a valid type");
			return null;
		}
	}
	
	//-----------------------------------------------------------------------------
	/**
	 * returns 
	 *
	 * @return result
	 */
	public int getRandomAll() {
		int rand = (int) (Math.random()*total);
		try {
			XML xml = new XML(papplet, "http://prettycolors.tumblr.com/api/read?num=1&filter=text&start=" + rand );
			return parseElement(xml, 0);
		} catch(Exception e) {
			return 0;
		}
	}

	/**
	 * returns random palette
	 *
	 * @return result
	 */
	public int getRandom() {
		int rand = (int) (Math.random()*colorsReturn.length);
		return colorsReturn[rand];
	}



} //end FPrettyColors