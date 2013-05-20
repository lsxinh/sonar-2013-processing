import frederickk.control.*;

/**
 *  FControl DropDown Example
 *  Ken Frederick
 * 
 *  Example showing the usage of the FControl DropDown GUI element
 *  (work in progress)
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
  size(200,200);
  typeface = createFont("FuturaT-Bold",10);

  //initiate FControl
  gui = new FControl(this);

  //set typeface (optional)
  //default is "LucidaGrande-Bold"
  gui.setTypeface( typeface );
  gui.setColorOver( color(255,200,0) );
  gui.setColorInactive( color(0) );

  //add gui elements
  String[] items = {"item1", "item2", "item3", "item4"};
  gui.addDropDown("dropdown", 15,15, width-30,30, items, items[1]);
}

//-----------------------------------------------------------------------------
void draw() {
  background(255);
  
  //draw gui elements to screen
  gui.draw();

}

