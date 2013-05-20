import frederickk.api.*;

/**
 *  FPrettyColors Example 
 *  Ken Frederick
 * 
 *  Example showing the usage of FPrettyColors
 *  to create pallets from http://prettycolors.tumblr.com/
 * 
 *  in this example palettes can be "exported" as .png files 
 *  by clicking the mouse, palettes can be refreshed by
 *  pressing the spacebar.
 * 
 */



//-----------------------------------------------------------------------------
// properties
//-----------------------------------------------------------------------------
FPrettyColors pc;
int choose[];

PFont typeface;



//-----------------------------------------------------------------------------
// methods
//-----------------------------------------------------------------------------
void setup() {
  size(500,500);
  noLoop();
  noStroke(); 

  choose = new int[3];
  choose[0] = (int) random(10);
  choose[1] = (int) random(10);
  choose[2] = (int) random(50);

  pc = new FPrettyColors(this);

  typeface = createFont("LucidaGrande-Bold",9);
  textFont(typeface);
}


//-----------------------------------------------------------------------------
void draw() {
  background(255);
  noStroke();

  // palettes
  pc.draw(0,0,            width,height/5, pc.ORDER, choose[0]);
  pc.draw(0,(height/5),   width,height/5, pc.BRIGHTNESS, choose[1]);
  pc.draw(0,(height/5)*2, width,height/5, pc.COMPLEMENT, choose[2]);
  pc.draw(0,(height/5)*3, width,height/5, pc.RANDOM);
  pc.draw(0,(height/5)*4, width,height/5, pc.RANDOM_ALL);

  // labels
  fill(0);
  text("Order",      15,15);  
  text("Brightness", 15,(height/5) +15);  
  text("Complement", 15,(height/5)*2 +15);  
  text("Random",     15,(height/5)*3 +15);  
  text("Random All", 15,(height/5)*4 +15);  

}



//-----------------------------------------------------------------------------
// events
//-----------------------------------------------------------------------------
/**
 *  pressing mouse will save the palettes as 5x1 pixel images for later use
 */
void mousePressed() {
  pc.getPalette(pc.ORDER, choose[0]).save();
  pc.getPalette(pc.BRIGHTNESS, choose[1]).save();
  pc.getPalette(pc.COMPLEMENT, choose[2]).save();
  pc.getPalette(pc.RANDOM).save();
  pc.getPalette(pc.RANDOM_ALL).save();
}


/**
 *  pressing the spacebar will randomly create new palettes
 */
void keyPressed() {
  if(key == ' ') {
    choose[0] = (int) random(10);
    choose[1] = (int) random(10);
    choose[2] = (int) random(50);
    redraw();
  }
}
