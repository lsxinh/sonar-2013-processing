import frederickk.tools.*;
import frederickk.control.*;
import frederickk.api.*;

/**
 *  FKuler Example
 *  Ken Frederick
 * 
 *  Example showing the usage of FKuler for color palettes
 *  as well as how to implement a custom FControl gui element
 * 
 */



// ------------------------------------------------------------------------
// properties
// ------------------------------------------------------------------------
FKuler kuler;
String kulerKey = "5F5D21FE5CA6CBE00A40BD4457BAF3BA";

FControl gui;



// ------------------------------------------------------------------------
// methods
// ------------------------------------------------------------------------
void setup() {
  size(300,300);
  smooth();


  gui = new FControl(this);


  /*
   *  kuler
   */
  kuler = new FKuler(this, kulerKey);
//  kuler.getSearch( "email", "ken.frederick@gmx.de" );
  kuler.getHighestRated();
   
  println("FKuler Theme Count\t" + kuler.getThemeCount());
  
  // choose a random theme from the themes pulled in
  int rand = 0; //(int) random(kuler.getThemeCount());


  /*
   *  custom checkbox
   */
  ColorCheck[] cc = new ColorCheck[kuler.getSwatches(rand).length];
  for(int i=0; i<cc.length; i++) {
    String name = "cc_" + i;
    float w = width/cc.length;

    cc[i] = new ColorCheck(this, name, i*w,0, w,280, color(kuler.getSwatches(rand)[i]) );
    gui.addCheck(cc[i]);
  }

}

// ------------------------------------------------------------------------
void draw() {
  background(0);

  gui.draw();
}





