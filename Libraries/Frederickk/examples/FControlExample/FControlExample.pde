import frederickk.control.*;

/**
 *  FControl Example
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
FControl gui;
PFont typeface;



//-----------------------------------------------------------------------------
// methods
//-----------------------------------------------------------------------------
void setup() {
  size(300,300);
  //smooth();
  typeface = loadFont("FuturaT-Bold-10.vlw");

  // initiate FControl
  gui = new FControl(this);
  gui.showFrameRateTitle(); // show framerate of sketch in title


  // set typeface (optional)
  // default is "LucidaGrande-Bold"
  gui.setTypeface( typeface );
  gui.setColorOver( color(255,200,0) );


  // add gui elements
  gui.addButton("button", width*0.5,height*0.5+60, 48,30);
  gui.addCheck("check", 15,15, 20, true);
  gui.addKnob("knob", width*0.5,height*0.5, 100);
  gui.addSlider("slider", 15,45, 200,20);

  gui.addHandle("handle", random(width),random(height), 8,8);


  // enable snap to grid i.e. 20x20
  //gui.enableSnap("handle", 20);

}

//-----------------------------------------------------------------------------
void draw() {
  background(0);
  
  // draw gui elements to screen
  gui.draw();
}




