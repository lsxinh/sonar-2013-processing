import frederickk.tools.*;
import frederickk.control.*;

/**
 *  FEdgeDetect Example 
 *  Ken Frederick
 * 
 *  Example showing the usage of the FEdgeDetect
 *  shows the edges of an image inspired from http://processing.org/learning/topics/edgedetection.html
 * 
 */



// -----------------------------------------------------------------------------
// properties
// -----------------------------------------------------------------------------
FControl gui;
FEdgeDetect edges;
float thresh;
int density;
float b_low;
float b_high;

PImage img;



// -----------------------------------------------------------------------------
void setup() {
  size(536,666);
  gui = new FControl(this);

  img = loadImage("http://mikaeleliasson.com/images/Mikael_Eliasson_066.jpg");

  /**
   *  edge detect
   */
  thresh = 10.0;
  density = 20;   // every 20pixels we look for an edge
  b_low = 50;    // any pixel of the original image with a brightness above 50 ...
  b_high = 255;  // and below 255 is valid

  edges = new FEdgeDetect();
  edges.process(img, thresh, true);
  edges.findEdges(density, b_low,b_high);

}


// -----------------------------------------------------------------------------
void draw() {
  background(0);
  gui.draw();
  image(img, 0,0);

  noStroke();
  fill(0,255,255);

  density = (int) map(mouseX, 0,width, 5,40);

  // edges.getEdgePVector() returns an arrayList of PVectors
  // http://download.oracle.com/javase/1.4.2/docs/api/java/util/ArrayList.html
  for(int i=0; i<edges.getEdgePVector().size(); i++) {
    PVector pt = (PVector) edges.getEdgePVector().get(i);
    
    // draw a cirlce on every edge detected
    ellipse(pt.x,pt.y, 5,5);
  }

}



// -----------------------------------------------------------------------------
// Events
// -----------------------------------------------------------------------------
void mouseMoved() {
  edges.process(img, thresh, true);
  edges.findEdges(density, b_low,b_high);
}

