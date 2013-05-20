import frederickk.tools.*;

/**
 *  FTools Example
 *  Ken Frederick
 * 
 *  Example showing the usage of the FTools class
 *
 *  A selection of ArrayList Utility Methods modified for processing from
 *  org.apache.commons.lang (ArrayUtils.java)
 *  http://commons.apache.org/lang/
 * 
 */



//-----------------------------------------------------------------------------
// properties
//-----------------------------------------------------------------------------
Integer[] integerExample;
ArrayList numClassList = new ArrayList();
ArrayList PVectorList = new ArrayList();



//-----------------------------------------------------------------------------
// methods
//-----------------------------------------------------------------------------
void setup() {
  /*
   * for more information on arrays and arrayLists
   *
   * http://processing.org/reference/Array.html
   * http://processing.org/reference/ArrayList.html
   *
   */


  // there is no need to instantiate the FTools class


  // populate Array integerExample
  // with Integer numbers
  // http://download.oracle.com/javase/1.5.0/docs/api/java/lang/Integer.html

  println();
  println("integerExample");
  integerExample = new Integer[4];
  for(int i=0; i<integerExample.length; i++) {
    integerExample[i] = i;
    println(integerExample[i]);
  } 


  /*
   * reverse
   *
   * reverse the order of any array
   */
  println();
  println("FTools.reverse( integerExample )");
  FTools.reverse( integerExample );
  for(int i=0; i<integerExample.length; i++) {
    println(integerExample[i]);
  } 


  /*
   * toPrimitive (int[])
   *
   * convert an Integer[] array into primitive int[] array
   * useful when coverting ArrayLists to arrays
   */
  println();
  println("FTools.toPrimitive( integerExample )");
  int[] tempInt1 = FTools.toPrimitive( integerExample );
  for(int i=0; i<integerExample.length; i++) {
    println("tempInt1[" + i + "]\t" + tempInt1[i]);
  } 


  // populate ArrayList numClassList
  // with an NumClass of misc. numbers
  for(int i=0; i<4; i++) {
    numClassList.add( new NumClass(i, i+1, i/0.3) );
  }


  // populate ArrayList PVectorList
  // with PVector objects
  // http://processing.org/reference/PVector.html
  println();
  println("PVectorList");
  for(int i=0; i<4; i++) {
    float x = random(width);
    float y = random(height);

    println(x + ", " + y);
    PVectorList.add( new PVector(x,y) );
  }


  /*
   * to2DArray (float[][])
   *
   * convert an ArrayList of PVector into a float[][3] 2D array
   */
  println();
  println("FTools.toPrimitive( integerExample )");
  float[][] temp2D = (float[][]) FTools.to2DArray( PVectorList );
  for(int i=0; i<temp2D.length; i++) {
    for(int j=0; j<temp2D[i].length; j++) {
      println("temp2D[" + i + "][" + j + "]\t" + temp2D[i][j]);
    }
  }

}


