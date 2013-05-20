package frederickk.geom;

/*
 *  Frederickk.Tools 0.0.5
 *  FSphere.java
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
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;
import java.util.ArrayList;
//import net.java.games.jogl.*;



public class FSphere {
	//-----------------------------------------------------------------------------
	// properties
	//-----------------------------------------------------------------------------
	private static PApplet p5;
	
	private float r;
	private float c = 0.5f;
	private int[][] colors;

	private int lats = 6;
	private int longs = 6;
	private ArrayList<PVector> points;



	//-----------------------------------------------------------------------------
	// constructor
	//-----------------------------------------------------------------------------
	/**
	 *  @param papplet
	 *  			PApplet 
	 *  @param _r
	 *  			radius of sphere
	 */
	public FSphere(PApplet papplet, float _r) {
		p5 = papplet;
		setSize(r);
		//c = new PVector[3];
		//for(int i=0; i<c.length; i++) c[i] = new PVector();
	}

	/**
	 *  @param papplet
	 *  			PApplet 
	 *  @param _r
	 *  			radius of sphere
	 *  @param _lats
	 *  			number of latitude segments
	 *  @param _longs
	 *  			number of longitude segments
	 */
	public FSphere(PApplet papplet, float _r, int _lats, int _longs) {
		p5 = papplet;
		setSize(r);
		setLatsLongs(_lats, _longs);
		calculate(lats, longs);
	}



	//-----------------------------------------------------------------------------
	// methods
	//-----------------------------------------------------------------------------
	/**
	 *  @param lats
	 *  			number of latitude segments
	 *  @param longs
	 *  			number of longitude segments
	 */
	public void draw(int lats, int longs) {
		calculate(lats, longs);

		p5.beginShape(PConstants.TRIANGLE_STRIP);
		for(PVector pt : points) {
			p5.normal(pt.x, pt.y, pt.z);
			p5.vertex(pt.x, pt.y, pt.z);
		}
		p5.endShape(PConstants.CLOSE);
	}

	//-----------------------------------------------------------------------------
	/**
	 *  @param lats
	 *  			number of latitude segments
	 *  @param longs
	 *  			number of longitude segments
	 */
	private void calculate(int lats, int longs) {
		points = new ArrayList<PVector>();

		for(int i=0; i<=lats; i++) {
			float lat0 = (float) (Math.PI * (-c + ( (float) (i-1)/lats) ));
			float z0   = (float) Math.sin(lat0);
			float zr0  = (float) Math.cos(lat0);

			float lat1 = (float) (Math.PI * (-c + ( (float) i/lats) ));
			float z1   = (float) Math.sin(lat1);
			float zr1  = (float) Math.cos(lat1);

			for(int j=0; j<=longs; j++) {
				float lng = (float) ((Math.PI*2) * ( (float) (j-1)/longs ));
				float x = (float) Math.cos(lng);
				float y = (float) Math.sin(lng);

				points.add( new PVector( x*zr0, y*zr0, z0 ) );
				points.add( new PVector( x*zr1, y*zr1, z1 ) );
			} // longs
		} // lats
	}



	//-----------------------------------------------------------------------------
	// sets
	//-----------------------------------------------------------------------------
	/**
	 *  @param r
	 *  		radius of sphere
	 */
	public void setSize(float _r) {
		r = _r;
	}

	/**
	 *  @param r
	 *  		radius of sphere
	 */
	public void setCompletion(float _c) {
		c = _c;
	}

	/**
	 *  @param lats
	 *  			number of latitude segments
	 *  @param longs
	 *  			number of longitude segments
	 */
	public void setLatsLongs(int _lats, int _longs) {
		lats = _lats;
		longs = _longs;
	}

	

	//-----------------------------------------------------------------------------
	// gets
	//-----------------------------------------------------------------------------
	public ArrayList<PVector> getPoints() {
		return points;
	}


}
