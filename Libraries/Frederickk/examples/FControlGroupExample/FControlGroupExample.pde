import frederickk.control.*;

/**
 *  FControlGroup Example
 *  Ken Frederick
 *  
 *  Example showing the usage of the FControl GUI elements:
 *  FButton, FKnob, FCheck, FSlider, FMeter and being able to set your
 *  own custom typeface, by default LucidaGrande-Bold is used
 *  
 */



//-----------------------------------------------------------------------------
// properties
//-----------------------------------------------------------------------------
FControlGroup cgroup;
FControl elements;

PFont typeface;

int[] rgbVals = { (int)random(255), (int)random(255), (int)random(255) };
int rgb;



//-----------------------------------------------------------------------------
// methods
//-----------------------------------------------------------------------------
void setup() {
  size(300, 300);
  //smooth();
  typeface = loadFont("FuturaT-Bold-10.vlw");


  // initiate FControlGroup
  // pushes gui elements into a seperate window
  cgroup = new FControlGroup("gui window", 300,300);
  cgroup.setColsRows(3,3);
  cgroup.showGrid(true);

  // initiate FControl
  elements = cgroup.getElements();
  elements.setTypeface( typeface );

  // add gui elements
  elements.addSlider("R", cgroup.getCol(0), cgroup.getRow(0), cgroup.getMargins()[2]-45,50, 0.0f,255.0f, rgbVals[0]);
  elements.addSlider("G", cgroup.getCol(0), cgroup.getRow(1), cgroup.getMargins()[2]-45,50, 0.0f,255.0f, rgbVals[1]);
  elements.addSlider("B", cgroup.getCol(0), cgroup.getRow(2), cgroup.getMargins()[2]-45,50, 0.0f,255.0f, rgbVals[2]);
}

//-----------------------------------------------------------------------------
void draw() {
  background( rgb );

  // rgb values
  rgb = color(rgbVals[0], rgbVals[1], rgbVals[2]);

  rgbVals[0] = cgroup.getIntValue("R");
  rgbVals[1] = cgroup.getIntValue("G");
  rgbVals[2] = cgroup.getIntValue("B");

  elements.setColorOver( rgb );

}


