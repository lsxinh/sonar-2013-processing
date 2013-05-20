import frederickk.control.*;

/**
 *  FControl InputField Example
 *  Ken Frederick
 * 
 *  Example showing the usage of the FControl InputField GUI element
 *  (work in progress)
 * 
 */



//-----------------------------------------------------------------------------
// properties
//-----------------------------------------------------------------------------
FControl gui;
PFont typeface;

PFont inputTypeface;
boolean bUnderline = false;


//-----------------------------------------------------------------------------
// methods
//-----------------------------------------------------------------------------
void setup() {
  size(450,450);
  typeface = createFont("FuturaT-Bold", 10);
  inputTypeface = loadFont("VerlagP-Black-60.vlw");


  //initiate FControl
  gui = new FControl(this);


  //set typeface (optional)
  //default is "LucidaGrande-Bold"
  gui.setTypeface( typeface );
  gui.setColorOver( color(255,200,0) );
  gui.setColorInactive( color(0) );


  //add gui elements
//  gui.addInputField("inputField", 15,height/2, width-15,90, inputTypeface);
  gui.addInputField("inputField", 15,height/2, width-15,90, "default", inputTypeface);

}

//-----------------------------------------------------------------------------
void draw() {
  background(245);
  
  fill(255,0,255);
  text( gui.getStringValue("inputField"), 15,15 );

  stroke(0);
  int w = gui.getSize("inputField").width;
  int h = gui.getSize("inputField").height;
  
  line( 15,height/2, w,height/2 );
  line( 15,height/2+h, width-15, height/2+h );

  //draw gui elements to screen
  fill(0);
  gui.draw();
  
  
}


//-----------------------------------------------------------------------------
void mousePressed() {
  bUnderline =! bUnderline;
//  if(bUnderline) gui.underline();
//  else gui.deunderline();

  bUnderline =! bUnderline;
  gui.container(bUnderline);
}
