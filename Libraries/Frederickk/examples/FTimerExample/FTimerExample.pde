import frederickk.tools.*;

/**
 *  FTimer Example
 *  Ken Frederick
 * 
 *  Example showing the usage of the very rudimentary FTimer
 *
 */



// ------------------------------------------------------------------------
// properties
// ------------------------------------------------------------------------
FTimer timer;
boolean timerRun = true;

PFont typeface;



// ------------------------------------------------------------------------
// methods
// ------------------------------------------------------------------------
void setup() {
  size(200,200);
  typeface = createFont("LucidaGrande-Bold", 9);
  textFont(typeface);

  //initiate timer
  //1000 = 1 second
  timer = new FTimer(this, 1000);
}

// ------------------------------------------------------------------------
void draw() {
  background(0);
  noStroke();

  //toggle the timer to start with timerRun
  //if(!timerRun) timer.stop();

  //show if we have set the timer
  if(timerRun) {
    fill(0,255,0);
  } else {
    fill(255,0,0);
  }
  ellipse(width/2,height/2 - 25, 50,50);

  //if the timer is triggered turn the ellipse yellow
  //else turn it gray
  if(!timer.getTrigger()) {
    fill(255,255,0);
  } else {
    fill(100);
  }
  ellipse(width/2,height/2 + 25, 50,50);

}



// ------------------------------------------------------------------------
// events
// ------------------------------------------------------------------------
void mousePressed() {
  timerRun = !timerRun;
}

