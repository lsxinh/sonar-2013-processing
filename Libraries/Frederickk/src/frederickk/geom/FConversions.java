package frederickk.geom;

/*
 *  Frederickk.Tools 0.0.5
 *  FConversions.java
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
//import processing.core.PApplet;


public class FConversions {
	//-----------------------------------------------------------------------------
	// properties
	//-----------------------------------------------------------------------------
	/**
	 *  convert 1 point (or pixel) to millimeter 
	 */
	public static float POINT_TO_MM = (float) 0.352777778;

	/**
	 *  convert 1 millimeter to pixel (or point)
	 *  
	 *  size(210*MM_TO_POINT, 297*MM_TO_POINT); // A4 sized 
	 */
	public static float MM_TO_POINT = (float) 2.83464567;

	/**
	 *  convert 1 point (or pixel) to centimeter 
	 */
	public static float POINT_TO_CM = (float) 0.0352777778;

	/**
	 *  convert 1 inch to pixel (or point)
	 *  
	 *  size( 21.0*CM_TO_POINT, (int)(29.7*CM_TO_POINT)); // A4 sized 
	 */
	public static float CM_TO_POINT = (float) 28.3464567;

	/**
	 *  convert 1 point (or pixel) to inch 
	 */
	public static float POINT_TO_INCH = (float) 0.0138888889;

	/**
	 *  convert 1 inch to pixel (or point)
	 *  
	 *  size( (int)(8.5*INCH_TO_POINT), 11*INCH_TO_POINT); // US Letter sized 
	 */
	public static float INCH_TO_POINT = 72;

	/**
	 *  convert 1 point (or pixel) to pica 
	 */
	public static float POINT_TO_PICA = (float) 0.0833333333;
	public static float PICA_TO_POINT = 12;
}
