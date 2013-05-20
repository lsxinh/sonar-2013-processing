import frederickk.tools.*;

/**
 *  FImageLoader Example
 *  Ken Frederick
 * 
 *  Example showing the usage of the FImageLoader
 * 
 */



//-----------------------------------------------------------------------------
// properties
//-----------------------------------------------------------------------------
FImageLoader imageLoader;
PFont typeface;

void setup() {
  size(600,200);
  typeface = loadFont("FuturaT-Bold-10.vlw");
  textFont(typeface);  

  imageLoader = new FImageLoader(this, "/data/images/");

}

//-----------------------------------------------------------------------------
void draw() {
  background(0);

  int num = imageLoader.getImageNum();
  fill(255);
  for(int i=0; i<num; i++) {
    PImage img = imageLoader.getImage(i); 
    image(img, i*img.width,0); 
    text(imageLoader.getFile(i), (i*img.width)+15,height-15);
  }

}

