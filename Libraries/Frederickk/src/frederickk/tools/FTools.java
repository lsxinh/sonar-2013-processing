package frederickk.tools;

/*
 *  Frederickk.Tools 0.0.5
 *  FTools.java
 *
 *  Ken Frederick
 *  ken.frederick@gmx.de
 *
 *  http://cargocollective.com/kenfrederick/
 *  http://kenfrederick.blogspot.com/
 *
 *  A selection of ArrayList Utility Methods modified for processing from
 *  org.apache.commons.lang (ArrayUtils.java)
 *  http://commons.apache.org/lang/
 *  
 *  http://github.com/frederickk/frederickk
 *
 */



//-----------------------------------------------------------------------------
// libraries
//-----------------------------------------------------------------------------
import java.util.ArrayList;
import processing.core.PVector;



public class FTools {
	//------------------------------------------------------------------------
	// properties
	//------------------------------------------------------------------------
	public static final float[] EMPTY_FLOAT_ARRAY = new float[0];
	public static final Float[] EMPTY_FLOAT_OBJECT_ARRAY = new Float[0];
	public static final float[] _map = {90.0f, 9.00f, 4.00f, 2.33f, 1.50f, 1.00f, 0.66f, 0.43f, 0.25f, 0.11f, 0.01f};

	

	//------------------------------------------------------------------------
	// Arrays
	//------------------------------------------------------------------------
	/**
	 * Reverse
	 * 
	 * Reverses the order of the given array. There is no special handling for
	 * multi-dimensional arrays.
	 * 
	 * @param _array
	 *			the array to reverse, may be <code>null</code>
	 */
	public static void reverse(Object[] _array) {
		if (_array == null)
			return;

		int i = 0;
		int j = _array.length - 1;
		Object tmp;
		while (j > i) {
			tmp = _array[j];
			_array[j] = _array[i];
			_array[i] = tmp;
			j--;
			i++;
		}
	}

	/**
	 * Converts an array of object Integers to int[]
	 * 
	 * @param _array
	 *			a <code>Integer</code> array, may be <code>null</code>
	 * @return an <code>int</code> array, <code>null</code> if null array input
	 * @throws NullPointerException
	 *			 if array content is <code>null</code>
	 */
	public static int[] toPrimitive(Integer[] _array) {
		if (_array == null) {
			return null;
		}

		final int[] result = new int[_array.length];
		for (int i = 0; i < _array.length; i++) {
			result[i] = _array[i].intValue();
		}
		return result;
	}

	/**
	 * Converts an ArrayList to int[]
	 * 
	 * @param _array
	 *			a <code>Integer</code> ArrayList, may be <code>null</code>
	 * @return an <code>int</code> array, <code>null</code> if null array input
	 * @throws NullPointerException
	 *			 if array content is <code>null</code>
	 */
	public static int[] toPrimitive(ArrayList<Object> _array) {
		if (_array == null) {
			return null;
		}

		final int[] result = new int[_array.size()];
		for (int i = 0; i < _array.size(); i++) {
			Integer temp = (Integer) _array.get(i);
			result[i] = temp.intValue();
		}
		return result;
	}



	/**
	 *	Float array converters
	 */
	public static float[] toPrimitive(int[] array) {
		if (array == null) {
			return null;
		} else if (array.length == 0) {
			return EMPTY_FLOAT_ARRAY;
		}
		final float[] result = new float[array.length];
		for (int i = 0; i < array.length; i++) {
			result[i] = (float) array[i];
		}
		return result;
	}

	
	/**
	 * <p>Converts an array of object Floats to primitives.</p>
	 *
	 * <p>This method returns <code>null</code> for a <code>null</code> input array.</p>
	 *
	 * @param array  a <code>Float</code> array, may be <code>null</code>
	 * @return a <code>float</code> array, <code>null</code> if null array input
	 * @throws NullPointerException if array content is <code>null</code>
	 */
	public static float[] toPrimitive(Float[] array) {
		if (array == null) {
			return null;
		} else if (array.length == 0) {
			return EMPTY_FLOAT_ARRAY;
		}
		final float[] result = new float[array.length];
		for (int i = 0; i < array.length; i++) {
			result[i] = array[i].floatValue();
		}
		return result;
	}

	/**
	 * <p>Converts an array of object Floats to primitives handling <code>null</code>.</p>
	 *
	 * <p>This method returns <code>null</code> for a <code>null</code> input array.</p>
	 *
	 * @param array  a <code>Float</code> array, may be <code>null</code>
	 * @param valueForNull  the value to insert if <code>null</code> found
	 * @return a <code>float</code> array, <code>null</code> if null array input
	 */
	public static float[] toPrimitive(Float[] array, float valueForNull) {
		if (array == null) {
			return null;
		} else if (array.length == 0) {
			return EMPTY_FLOAT_ARRAY;
		}
		final float[] result = new float[array.length];
		for (int i = 0; i < array.length; i++) {
			Float b = array[i];
			result[i] = (b == null ? valueForNull : b.floatValue());
		}
		return result;
	}

	/**
	 * <p>Converts an array of primitive floats to objects.</p>
	 *
	 * <p>This method returns <code>null</code> for a <code>null</code> input array.</p>
	 *
	 * @param array  a <code>float</code> array
	 * @return a <code>Float</code> array, <code>null</code> if null array input
	 */
	public static Float[] toObject(float[] array) {
		if (array == null) {
			return null;
		} else if (array.length == 0) {
			return EMPTY_FLOAT_OBJECT_ARRAY;
		}
		final Float[] result = new Float[array.length];
		for (int i = 0; i < array.length; i++) {
			result[i] = new Float(array[i]);
		}
		return result;
	}
	
	
	/**
	 * Converts an ArrayList<Object> to float[][]
	 * 
	 * @param _array
	 *			a <code>Integer</code> ArrayList, may be <code>null</code>
	 * @return an <code>int</code> array, <code>null</code> if null array input
	 * @throws NullPointerException
	 *			 if array content is <code>null</code>
	 *
	public static float[][] to2DArray(ArrayList _array) {
		float[][] floatArray = new float[_array.size()][3];

		for (int i = 0; i < _array.size(); i++) {
			float[] temp = (float[]) _array.get(i);
			floatArray[i] = temp;
		}

		return floatArray;
	}
	*/

	/**
	 * Converts an ArrayList<PVector> to float[][]
	 * 
	 * @param _array
	 *			a <code>Integer</code> ArrayList, may be <code>null</code>
	 * @return an <code>int</code> array, <code>null</code> if null array input
	 * @throws NullPointerException
	 *			 if array content is <code>null</code>
	 */
	public static float[][] to2DArray(ArrayList<PVector> _array) {
		float[][] floatArray = new float[_array.size()][3];

		// Class type = _array.getClass().getComponentType();
		// println("type\t" + type);

		for (int i = 0; i < _array.size(); i++) {
			float[] temp = (float[]) _array.get(i).array();
			floatArray[i] = temp;
		}

		return floatArray;
	}


	/**
	 * finds maximum value within an array
	 * 
	 */
	public static float maxValue(float[] array) {
		float max = array[0];
		int len = array.length;
		//for(var i=1; i<len; i++) if(array[i] > max) max = array[i];
		for(int i=1; i<len; i++) if(array[i] > max) max = i;
		return max;
	}
	public static int maxValue(int[] array) {
		int max = array[0];
		int len = array.length;
		//for(var i=1; i<len; i++) if(array[i] > max) max = array[i];
		for(int i=1; i<len; i++) if(array[i] > max) max = i;
		return max;
	}

	
	/**
	 * finds minimum value within an array
	 * 
	 */
	public static float minValue(float[] array) {
		float min = array[0];
		int len = array.length;
		//for (var i=1; i<len; i++) if(array[i] < min) min = this[i];
		for (int i=1; i<len; i++) if(array[i] < min) min = i;
		return min;
	}
	public static int minValue(int[] array) {
		int min = array[0];
		int len = array.length;
		//for (var i=1; i<len; i++) if(array[i] < min) min = this[i];
		for (int i=1; i<len; i++) if(array[i] < min) min = i;
		return min;
	}
	
	
	/**
	 * method which return the index of
	 * the index of the largest value in the array
	 * 
	 * int[] list = { 5, 1, 2, -3 };
	 * int h = FTools.maxIndex(list); // sets h to "0"
	 *  
	 * @param array
	 * 			the array to find the max index of
	 * 
	 */
	public static int maxIndex(float[] array) {
		int max = 0;
		int len = array.length;
		for(int i=1; i<len; i++) if(array[i] > max) max = i;
		return max;
	}
	public static int maxIndex(int[] array) {
		int max = 0;
		int len = array.length;
		for(int i=1; i<len; i++) if(array[i] > max) max = i;
		return max;
	}

	
	/**
	 * method which return the index of
	 * the index of the smallest value in the array
	 * 
	 * int[] list = { 5, 1, 2, -3 };
	 * int h = FTools.minIndex(list); // sets h to "3"
	 *  
	 * @param array
	 * 			the array to find the max index of
	 * 
	 */
	public static int minIndex(float[] array) {
		int min = 0;
		int len = array.length;
		for (int i=1; i<len; i++) if(array[i] < min) min = i;
		return min;
	}
	
	public static int minIndex(int[] array) {
		int min = array[0];
		int len = array.length;
		//for (int i=1; i<len; i++) if(array[i] < min) min = array[i];
		for (int i=1; i<len; i++) if(array[i] < min) min = i;
		return min;
	}
	

	
	//------------------------------------------------------------------------
	// additional tools
	//------------------------------------------------------------------------
	public static int snap(int value, int snapAmt) {
		return snapAmt * Math.round(value / snapAmt);
	}
	public static float snap(float value, float snapAmt) {
		return snapAmt * Math.round(value / snapAmt);
	}
	public static int snap(float _val, float _inc, float _offset) {
		float sf = ((int) (_val/_inc) * _inc) + _offset;
		return (int) sf;
	}

	
	//------------------------------------------------------------------------
	/**
	 *  @param orig
	 *  			input float value
	 *  @param deci
	 *  			decimal point length
	 *  
	 *  @return
	 *  			float with specified decimal length (2.14235454 -> 2.14)
	 * 
	 */
	public static float roundDecimal(float orig, int deci) {
		float multi = (float) (Math.pow(10,deci));
		float num = Math.round(orig * multi)/multi;
		return num;
	}	

	
	//------------------------------------------------------------------------
	/**
	 *	http://www.siafoo.net/snippet/191
	 *
	 *	Returns a number between v1 and v2, including v1 but not v2.
	 *	The bias represents the preference towards lower or higher numbers,
	 *	@param minr
	 *				minimum value  
	 *	@param maxr
	 *				maximum value
	 *	@param bias
	 *				as a number between 0 and 10, randomBias(0, 10, bias=0.9) will return 9 much more often than 1.
	 */
	public static float randomBias(float minr, float maxr, float bias) {
		bias = Math.max(0, Math.min(bias, 1)) * 10;

		int i = (int)(Math.floor(bias));
		float n = _map[i];
		if(bias < 10) n += (_map[i+1]-n) * (bias-i);

		return (float) (Math.pow( Math.random(),n ) * (maxr-minr) + minr);
	}
	public static float randomBias(float maxr, float bias) {
		bias = Math.max(0, Math.min(bias, 1)) * 10;

		int i = (int)(Math.floor(bias));
		float n = _map[i];
		if(bias < 10) n += (_map[i+1]-n) * (bias-i);

		return (float) (Math.pow( Math.random(),n ) * (maxr-0) + 0);
	}	
	
	
	/**
	 * return boolean value as int
	 * 
	 * @param val
	 * 			boolean value
	 * 
	 * @return
	 * 			returns 0 (false) or 1 (true)
	 */
	public static int boolToInt(boolean val) {
		return (val) ? 1:0;
	}

	
}
