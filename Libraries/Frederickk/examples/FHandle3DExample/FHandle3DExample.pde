import processing.opengl.*;
import frederickk.control.*;

/**
 *  FHandle3D Example
 *  Ken Frederick
 * 
 *  Example showing the usage of the FHandle3D GUI element
 * 
 */



//-----------------------------------------------------------------------------
// properties
//-----------------------------------------------------------------------------
FControl gui;
PFont typeface;



//-----------------------------------------------------------------------------
// methods
//-----------------------------------------------------------------------------
void setup() {
  size(200,200, OPENGL);
  typeface = loadFont("FuturaT-Bold-10.vlw");

  //initiate FControl
  gui = new FControl(this);

  //set typeface (optional)
  //default is "LucidaGrande-Bold"
  //gui.setTypeface( typeface );
  gui.setColorOver( color(255,200,0) );

  //add gui elements
  for(int i=0; i<5; i++) {
    gui.addHandle3D("Handle3D", i, random(width),random(height), random(100), 12);
  }

}

//-----------------------------------------------------------------------------
void draw() {
  background(0);
  
  //draw gui elements to screen
    gui.draw();

}

