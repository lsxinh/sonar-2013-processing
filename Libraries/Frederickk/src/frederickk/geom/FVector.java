package frederickk.geom;

/*
 *  Frederickk.Tools 0.0.5
 *  FVector.java
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
 *	some stolen code from toxiclibs so that i could
 *	use PVector instead of Vec2D
 *
 */



//-----------------------------------------------------------------------------
// libraries
//-----------------------------------------------------------------------------
//import processing.core.PApplet;

import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;



public class FVector extends PVector {
	//-----------------------------------------------------------------------------
	// properties
	//-----------------------------------------------------------------------------
	private static final long serialVersionUID = -5550722905642289605L;


	private static float[] arcLenIndex;


	
	//-----------------------------------------------------------------------------
	// methods
	//-----------------------------------------------------------------------------
	/**
	 *  Calculates the vertex positions of a line parallel to the original
	 *  line at an approximate fixed distance.
	 *  
	 *  https://forum.processing.org/topic/points-with-equal-distance-repulsion-from-a-line
	 *  
	 *  @param points
	 *  		a collection of points
	 *  @param distance
	 *  		the distance to maitain in result
	 *  
	 *  @return	
	 *  		a collection of points parallel to input points at specified distance
	 *  
	 */
	public static PVector[] getParallelLine(PVector[] points, float distance) {
		PVector[] parallelPoints = new PVector[points.length]; 
		parallelPoints[0] = findNormal(points[0], points[0], points[1], distance);

		for (int i=1; i<parallelPoints.length-1; i++ ) {
			parallelPoints[i] = findNormal(points[i], points[i-1], points[i+1], distance);
		}

		parallelPoints[points.length-1] = findNormal(points[points.length-1], points[points.length-2], points[points.length-1], distance);

		return parallelPoints;
	}


	//-----------------------------------------------------------------------------
	/**
	 *  Finds the normal at p0 - the intersection of two vectors p1 and p2.
	 *  Joining adjacent normals maintains parallelism with points p1-p0-p2.
	 *  
	 *  https://forum.processing.org/topic/points-with-equal-distance-repulsion-from-a-line
	 *  
	 *  @param p0
	 *  		first point
	 *  @param p1
	 *  		second point
	 *  @param p2
	 *  		third point
	 *  @param distance
	 *  		distance to maintain
	 *  
	 *  @return
	 *  		PVector of ?
	 */
	public static PVector findNormal(PVector p0, PVector p1, PVector p2, float distance) {
		PVector v1 = new PVector(p1.x-p0.x, p1.y-p0.y);
		PVector v2 = new PVector(p2.x-p0.x, p2.y-p0.y);
		v1.normalize();
		v2.normalize();

		PVector v = new PVector(v2.x-v1.x, v2.y-v1.y);
		v.normalize();

		float rAngleBetween = angleBetween(v1, v2);
		if (Float.isNaN(rAngleBetween)) {
			rAngleBetween = (float) Math.PI;
		}

		float d = (float) (distance/Math.sin(rAngleBetween*.5));
		return new PVector(p0.x+d*v.y, p0.y-d*v.x);
	}	


	// ------------------------------------------------------------------------
	/**
	 *	breaks a line (defined as a space between points) into
	 *	a specified number of line segments code was taken directly from toxilibs
	 *
	 *  https://bitbucket.org/postspectacular/toxiclibs/src/9d124c80e8af/src.core/toxi/geom/LineStrip2D.java
	 *  
	 *	modified for use with Processing's native PVector
	 *
	 *	@param vertices
	 *				a List<PVector> of vertices to break
	 *	@param step
	 *				the number of steps between the start point and end point
	 *	@param doAddFinalVertex
	 *				include final vertex in results
	 *
	 *	@return a List<PVector> of the stepped number of points
	 *
	 */
	public static List<PVector> getDecimatedVertices(List<PVector> vertices, float step, boolean doAddFinalVertex) {
		List<PVector> uniform = new ArrayList<PVector>();
		if (vertices.size() < 3) {
			if (vertices.size() == 2) {
				uniform = splitIntoSegments( vertices.get(0), vertices.get(1), vertices, step, false);
				//      new Line2D( vertices.get(0), vertices.get(1) ).splitIntoSegments(uniform, step, true);
				if (!doAddFinalVertex) {
					uniform.remove(uniform.size() - 1);
				}
			} 
			else {
				return null;
			}
		}

		float arcLen = getEstimatedArcLength(vertices);
		if (arcLen > 0) {
			float delta = step / arcLen;
			int currIdx = 0;
			for (float t = 0; t < 1.0; t += delta) {
				float currT = t * arcLen;
				while (currT >= arcLenIndex[currIdx]) {
					currIdx++;
				}
				PVector p = vertices.get(currIdx - 1);
				PVector q = vertices.get(currIdx);
				float frac = (float) ((currT - arcLenIndex[currIdx - 1]) / (arcLenIndex[currIdx] - arcLenIndex[currIdx - 1]));
				PVector i = interpolateTo(p, q, frac);
				uniform.add(i);
			}
			if (doAddFinalVertex) {
				uniform.add(vertices.get(vertices.size() - 1));
			}
		}
		return uniform;
	}


	// ------------------------------------------------------------------------
	public static float getEstimatedArcLength(List<PVector> vertices) {
		if (arcLenIndex == null || ( arcLenIndex != null && arcLenIndex.length != vertices.size() )) {
			arcLenIndex = new float[vertices.size()];
		}
		float arcLen = 0;
		for (int i = 1; i < arcLenIndex.length; i++) {
			PVector p = vertices.get(i - 1);
			PVector q = vertices.get(i);
			arcLen += p.dist(q);
			arcLenIndex[i] = arcLen;
		}
		return arcLen;
	}


	// ------------------------------------------------------------------------
	public static List<PVector> splitIntoSegments(PVector a, PVector b, List<PVector> segments, float stepLength, boolean addFirst) {
		if (segments == null) {
			segments = new ArrayList<PVector>();
		}
		if (addFirst) {
			segments.add(a);
		}
		float dist = a.dist(b);
		if (dist > stepLength) {
			PVector pos = a;
			PVector subbed = sub(b, a);
			PVector step = limit(subbed, stepLength);
			while (dist > stepLength) {
				pos.add(step);
				segments.add(pos);
				dist -= stepLength;
			}
		}
		segments.add(b);
		return segments;
	}


	// ------------------------------------------------------------------------
	/**
	 *
	 *  https://bitbucket.org/postspectacular/toxiclibs/src/9d124c80e8af/src.core/toxi/geom/Vec2D.java
	 *
	 */
	public static PVector interpolateTo(PVector v1, PVector v2, float f) {
		return new PVector(v1.x + (v2.x - v1.x) * f, v1.y + (v2.y - v1.y) * f);
	}
	public void interpolateTo(PVector v2, float f) {
		this.x += ((v2.x - this.x) * f);
		this.y += ((v2.y - this.y) * f);
	}


	// ------------------------------------------------------------------------
	public static PVector limit(PVector v1, float lim) {
		if (v1.magSq() > lim * lim) {
			v1.normalize();
			v1.mult(lim);
			//			v1.scale(lim);
			//	    return normalize().scaleSelf(lim);
			return v1;
		}
		return v1;
	}

	// ------------------------------------------------------------------------
	public static float magSquared(PVector v1) {
		return v1.x * v1.x + v1.y * v1.y;
	}



	// ------------------------------------------------------------------------
	/**
	 *	return an arraylist of PVector in a grid with specified spacing between cells
	 *
	 *	@param width
	 *				width of the entire grid
	 *	@param height
	 *				height of the entire grid
	 *	@param spacing
	 *				a PVector representing the spacing (or size of cells)
	 *
	 *	@return an ArrayList<PVector> of the grid points
	 * 
	 */
	public static ArrayList<PVector> grid(int width, int height, PVector spacing) {
		return grid(width, height, spacing, false);
	}
	public static ArrayList<PVector> grid(int width, int height, PVector spacing, boolean bRandom) {
		ArrayList<PVector> out = new ArrayList<PVector>();
		for (int y=0; y<height; y+=spacing.y) {
			for (int x=0; x<width; x+=spacing.x) {
				if(bRandom)
					out.add( new PVector( (float)Math.random()*width, (float)Math.random()*height ) );
				else
					out.add( new PVector(x, y) );
			}
		}
		return out;
	}


	// -----------------------------------------------------------------------------
	/**
	 *	return a PVector that snaps to a specific grid (starting at 0,0)
	 *
	 *	@param pt
	 *			the PVector to snap
	 *	@param spacing
	 *				a PVector representing the spacing (or size of cells)
	 *
	 *	@return PVector snapped to grid
	 * 
	 */
	public static PVector snapGrid(PVector pt, PVector spacing) {
		float ix, iy;
		ix = Math.round(pt.y/spacing.y - pt.x/spacing.x);
		iy = Math.round(pt.y/spacing.y + pt.x/spacing.x);

		return new PVector(
				(iy - ix)/2*spacing.x, 
				(iy + ix)/2*spacing.y
		);
	}

	/**
	 *	snaps PVector to a specific grid (based on 0,0)
	 *
	 *	@param spacing
	 *				a PVector representing the spacing (or size of cells)
	 * 
	 */
	public void snapGrid(PVector spacing) {
		float ix, iy;
		ix = Math.round(this.y/spacing.y - this.x/spacing.x);
		iy = Math.round(this.y/spacing.y + this.x/spacing.x);
		this.x = (iy - ix)/2*spacing.x; 
		this.y = (iy + ix)/2*spacing.y;
	}

	// -----------------------------------------------------------------------------
	/**
	 *	return a PVector that snaps to an isometric grid (starting at 0,0)
	 *
	 *	@param pt
	 *			the PVector to snap
	 *	@param scale
	 *				the scale of the grid (based is 32 x 16)
	 *
	 *	@return PVector snapped to isometric grid
	 * 
	 */
	public static PVector snapIso(PVector pt, float scale) {
		return snapGrid( pt, new PVector(32*scale, 16*scale) );
	}

	/**
	 *	snaps PVector to an isometric grid (starting at 0,0)
	 *
	 *	@param scale
	 *				the scale of the grid (based is 32 x 16)
	 * 
	 */
	public void snapIso(float scale) {
		this.snapGrid( new PVector(32*scale, 16*scale) );
	}


}
