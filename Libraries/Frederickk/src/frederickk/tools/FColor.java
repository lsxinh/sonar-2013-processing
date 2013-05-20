package frederickk.tools;

/*
 *  Frederickk.Tools 0.0.5
 *  FColor.java
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
// libaries
//-----------------------------------------------------------------------------
import java.awt.Color;



public class FColor {
	//-----------------------------------------------------------------------------
	// properties
	//-----------------------------------------------------------------------------
	/**
	 *  array of hsb values
	 * 
	 *  hsbVals[0] = hue
	 *  hsbVals[1] = saturation
	 *  hsbVals[2] = brightness
	 */
	protected static float[] hsbVals = new float[3];

	/**
	 *  array of int rgba values (0 - 255)
	 * 
	 *  rgbaVals[0] = red
	 *  rgbaVals[1] = green
	 *  rgbaVals[2] = blue
	 *  rgbaVals[2] = alpha
	 */
	protected static int[] rgbaVals = new int[4];

	/**
	 *  array of float rgba values (0.0 - 1.0)
	 * 
	 *  rgbaFVals[0] = red
	 *  rgbaFVals[1] = green
	 *  rgbaFVals[2] = blue
	 *  rgbaFVals[2] = alpha
	 */
	protected static float[] rgbaFVals = new float[4];



	//-----------------------------------------------------------------------------
	// methods
	//-----------------------------------------------------------------------------
	/**
	 * return the RGBA break down of an int/color (0.0 - 1.0) as float[4]   

	 * @param col
	 * 			input color as int 
	 * 
	 * @return rgba
	 */
	public static float[] getColorF(int col) {
		int r = (col >> 16) & 0xFF;
		int g = (col >> 8) & 0xFF;
		int b = col & 0xFF;
		int a = (col >> 24) & 0xFF;

		float[] rgba = { 
				r/255,g/255, b/255, a/255
		};
		return rgba;
	}


	/**
	 * return the RGBA break down of an int/color (0 - 255) as int[4]  
	 * 
	 * @param col
	 * 			input color as int 
	 * 
	 * @return rgba
	 */
	public static int[] getColor(int col) {
		int r = (col >> 16) & 0xFF;
		int g = (col >> 8) & 0xFF;
		int b = col & 0xFF;
		int a = (col >> 24) & 0xFF;

		int[] rgba = { r,g,b,a }; 
		return rgba;
	}


	/**
	 * return int of color from a Hexidecimal color code  
	 * 
	 * @param hexStr
	 * 			input color as hex string
	 * 
	 * @return opacityMask
	 */
	public static int getColor(String hexStr) {
		// http://processing.org/bugs/bugzilla/attachments/191
		int opacityMask = 0xFF000000;
		return opacityMask | (Integer.parseInt(hexStr.substring(1), 16)) & 0xFFFFFF;
	}


	//-----------------------------------------------------------------------------
	/**
	 * return desaturated color as int  
	 * 
	 * @param col
	 * 			input color as int 
	 * @param pct
	 * 			percentage of desaturation as a floating-point value (0.0 - 1.0) 
	 * 
	 * @return rgba
	 */
	public static int desaturate(int col, float pct) {
		//		int[] rgbaVals = getColor(col);
		rgbaVals = getColor(col);

		float[] hsb = Color.RGBtoHSB(rgbaVals[0], rgbaVals[1], rgbaVals[2], null); 

		int rgba = Color.HSBtoRGB(hsb[0], pct, hsb[1]);
		return rgba;
	}

	/**
	 * return desaturated color as int  
	 * 
	 * @param col
	 * 			input color as int 
	 * @param sat
	 * 			percentage of desaturation as a floating-point value (0.0 - 1.0) 
	 * @param bright
	 * 			percentage of brightness as a floating-point value (0.0 - 1.0) 
	 * 
	 * @return rgba
	 */
	public static int desaturate(int col, float sat, float bright) {
		//		int[] rgbaVals = getColor(col);
		rgbaVals = getColor(col);

		float[] hsb = Color.RGBtoHSB(rgbaVals[0], rgbaVals[1], rgbaVals[2], null); 

		int rgba = Color.HSBtoRGB(hsb[0], sat, bright);
		return rgba;
	}


	/**
	 * return darkened color as int  
	 * 
	 * @param col
	 * 			input color as int 
	 * @param pct
	 * 			percentage of darkening as a floating-point value (0.0 - 1.0) 
	 * 
	 * @return rgba
	 */
	public static int darken(int col, float pct) {
		//		float[] rgbaFVals = getColorF(col);
		rgbaFVals = getColorF(col);

		rgbaFVals[0] -= pct;
		if(rgbaFVals[0] < 0.0) rgbaFVals[0] = 0.0f;
		rgbaFVals[1] -= pct;
		if(rgbaFVals[1] < 0.0) rgbaFVals[1] = 0.0f;
		rgbaFVals[2] -= pct;
		if(rgbaFVals[2] < 0.0) rgbaFVals[2] = 0.0f;

		Color c = new Color( Math.abs(rgbaFVals[0]), Math.abs(rgbaFVals[1]), Math.abs(rgbaFVals[2]), rgbaFVals[3]);
		int rgba = c.getRGB();
		return rgba;
	}


	/**
	 * return lightened color as int  
	 * 
	 * @param col
	 * 			input color as int 
	 * @param pct
	 * 			percentage lightening as a floating-point value (0.0 - 1.0) 
	 * 
	 * @return rgba
	 */
	public static int lighten(int col, float pct) {
		//		float[] rgbaFVals = getColorF(col);
		rgbaFVals = getColorF(col);

		rgbaFVals[0] += pct;
		if(rgbaFVals[0] > 1.0) rgbaFVals[0] = 1.0f;
		rgbaFVals[1] += pct;
		if(rgbaFVals[1] > 1.0) rgbaFVals[1] = 1.0f;
		rgbaFVals[2] += pct;
		if(rgbaFVals[2] > 1.0) rgbaFVals[2] = 1.0f;

		Color c = new Color( Math.abs(rgbaFVals[0]), Math.abs(rgbaFVals[1]), Math.abs(rgbaFVals[2]), rgbaFVals[3]);
		int rgba = c.getRGB();
		return rgba;
	}


	//-----------------------------------------------------------------------------
	/**
	 * hue
	 * 
	 * @param col
	 * 			color value 
	 * 
	 */
	public static float hue(int col) {
		//		int[] rgbaVals = getColor(col);
		rgbaVals = getColor(col);

		Color.RGBtoHSB(rgbaVals[0],rgbaVals[1],rgbaVals[2], hsbVals);	
		return hsbVals[0]; 
	}


	/**
	 * saturation
	 * 
	 * @param col
	 * 			color value 
	 * 
	 */
	public static float saturation(int col) {
		//		int[] rgbaVals = getColor(col);
		rgbaVals = getColor(col);

		Color.RGBtoHSB(rgbaVals[0],rgbaVals[1],rgbaVals[2], hsbVals);	
		return hsbVals[1]; 
	}


	/**
	 * bitwise luminance
	 * http://processing.org/discourse/yabb2/YaBB.pl?num=1164286894
	 * 
	 * @param col
	 * 			color value 
	 * 
	 */
	public static int luminance(int col){
		//		int[] rgbaVals = getColor(col);
		rgbaVals = getColor(col);
		return ( rgbaVals[0]*9 + rgbaVals[1]*19 + (rgbaVals[2]<<2) ) >> 5;
	}


	/**
	 * bitwise brightness
	 * 
	 * @param col
	 * 			color value 
	 * 
	 */
	public static int brightness(int col){
		//		int[] rgbaVals = getColor(col);
		rgbaVals = getColor(col);

		//max function taken from processing 
		return (rgbaVals[0] > rgbaVals[1]) ? ((rgbaVals[0] > rgbaVals[2]) ? rgbaVals[0] : rgbaVals[2]) : ((rgbaVals[1] > rgbaVals[2]) ? rgbaVals[1] : rgbaVals[2]);
	}


	//-----------------------------------------------------------------------------
	/**
	 * return random RGB color as int  
	 * 
	 * @return rgb
	 */
	public static int randomRGBColor() {
		int r = new Double(Math.random()*255).intValue();
		int g = new Double(Math.random()*255).intValue();
		int b = new Double(Math.random()*255).intValue();
		int a = 255;

		Color c = new Color(r,g,b,a);
		int rgb = c.getRGB();
		return rgb;
	}


	/**
	 * return random RGB color within hue range as int  
	 * 
	 * @param hue1
	 * 			0-360 
	 * @param hue2
	 * 			0-360 
	 * 
	 * @return rgb
	 */
	public static int randomRGBColor(int hue1, int hue2) {
//		float h = (float) ( Math.abs(hue1) + (Math.random() * (Math.abs(hue2) - Math.abs(hue1))) );
//		float hnorm = (float) (h/360.0f);
//		Color c = Color.getHSBColor(hnorm, 1.0f,1.0f);
//		return c.getRGB();
		return randomRGBColor(hue1, hue2, 1.0f, 1.0f);
	}


	/**
	 * return random RGB color within hue range with specified saturation and brightness as int  
	 * 
	 * @param hue1
	 * 			0-360 
	 * @param hue2
	 * 			0-360 
	 * @param sat
	 * 			percentage of desaturation as a floating-point value (0.0 - 1.0) 
	 * @param bright
	 * 			percentage of brightness as a floating-point value (0.0 - 1.0) 
	 * 
	 * @return rgb
	 */
	public static int randomRGBColor(int hue1, int hue2, float sat, float bright) {
		float h = (float) ( Math.abs(hue1) + (Math.random() * (Math.abs(hue2) - Math.abs(hue1))) );
		float hnorm = (float) (h/360.0f);
		Color c = Color.getHSBColor(hnorm, sat, bright);
		return c.getRGB();
	}
	
	
	/**
	 * return random RGBA color as int  
	 * 
	 * @return rgba
	 */
	public static int randomRGBAColor() {
		int r = new Double(Math.random()*255).intValue();
		int g = new Double(Math.random()*255).intValue();
		int b = new Double(Math.random()*255).intValue();
		int a = new Double(Math.random()*255).intValue();
		
		Color c = new Color(r,g,b,a);
		int rgba = c.getRGB();
		return rgba;
	}


	/**
	 * return random grayscale color as int  
	 * 
	 * @return rgba
	 */
	public static int randomGrayColor() {
		int g = new Double(Math.random()*255).intValue();
		Color c = new Color(g,g,g, 255);
		int rgba = c.getRGB();
		return rgba;
	}


	/**
	 * return random grayscale within a percent range color as int  
	 * 
	 * @param pct1
	 * 			0.0 - 1.0 
	 * @param pct2
	 * 			0.0 - 1.0 
	 * 
	 * @return rgba
	 */
	public static int randomGrayColor(float pct1, float pct2) {
		int g = (int) ((Math.abs(pct1) + Math.random() * (Math.abs(pct2) - Math.abs(pct1))) * 255);
		
		Color c = new Color(g,g,g, 255);
		int rgba = c.getRGB();
		return rgba;
	}
	

	//-----------------------------------------------------------------------------
	/*
	 * Color Utilities
	 * 
	 * @author		Tom Beddard
	 * @version 	0.1
	 *
	 * Licensed under the MIT License
	 * http://www.opensource.org/licenses/mit-license.php
	 * 
	 */

	/**
	 * Return a color blended between two source colors with a midpoint 
	 * 
	 * @param c1
	 * 			color 1
	 * @param c2
	 * 			color 2
	 * @param p
	 * 			dist away from the first color
	 * 
	 * @return result
	 */
	public int blend(int c1, int c2, float p) {
		return blend(c1, c2, p, 0xFFFFFFFF);
	}


	/**
	 * Return a color blended between two source colors with a midpoint 
	 * 
	 * @param c1
	 * 			color 1
	 * @param c2
	 * 			color 2
	 * @param p
	 * 			distance away from the first color
	 * @param bc
	 * 			base color is handy for getting the alpha blending working on a solid color background
	 * 
	 * @return result
	 */
	public int blend(int c1, int c2, float p, int bc) {
		if (p >= 1) return c2;
		if (p <= 0) return c1;

		int[] rgba1 = getColor(c1);
		int[] rgba2 = getColor(c2);

		int r = (int) (rgba1[0] + (rgba2[0] - rgba1[0]) * p);
		int g = (int) (rgba1[1] + (rgba2[1] - rgba1[1]) * p);
		int b = (int) (rgba1[2] + (rgba2[2] - rgba1[2]) * p);
		int a = (c1 == bc) ? rgba2[3] : Math.min(rgba1[3] + rgba2[0], 255);

		Color c = new Color(r,g,b,a);
		return c.getRGB();
	}
	
	
	/**
	 * Return an array of colors blended from two colors
	 * 
	 * @param c1
	 * 			color 1
	 * @param c2
	 * 			color 2
	 * @param n
	 * 			number of intermediate steps
	 * 
	 * @return rgba
	 */
	public int[] blendArray(int c1, int c2, int n) {
		int[] a = new int[n];
		float p = 1 / (n + 1);
		for (int i=0; i<a.length; i++) a[i] = blend(c1, c2, i*p);
		return a;
	}



}
