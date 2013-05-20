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
 *	color sorting methods/classes
 *
 *	http://processing.org/discourse/yabb2/YaBB.pl?num=1272458371
 *
 */


//-----------------------------------------------------------------------------
// libaries
//-----------------------------------------------------------------------------
import java.util.Comparator;
import frederickk.tools.FColor;



public class LuminanceComparator implements Comparator<Integer> {
	public int compare(Integer _col1, Integer _col2) {
		int col1 = _col1.intValue();
		int col2 = _col2.intValue();
		if( FColor.luminance(col1) != FColor.luminance(col2) ) {
			return (int) (FColor.luminance(col1) - FColor.luminance(col2));
		}
		return (int) (FColor.hue(col1) - FColor.hue(col2));
	}
}


