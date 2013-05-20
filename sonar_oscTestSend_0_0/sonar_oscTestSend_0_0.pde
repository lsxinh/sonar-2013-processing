/**
 *  Sonar 2013
 *  OSC Test Send
 *
 *  Ken Frederick
 *  ken.frederick@gmx.de
 *
 *  http://cargocollective.com/kenfrederick/
 *  http://kenfrederick.blogspot.com/
 *
 *  send OSC messages
 *
 *  tested with Processing 1.5.1
 *
 */
 



//------------------------------------------------------------------------
//  Libraries
//------------------------------------------------------------------------
import processing.opengl.*;

import oscP5.*;
import netP5.*;



//------------------------------------------------------------------------
//  Properties
//------------------------------------------------------------------------
OscP5 osc;
NetAddress netAddress;

boolean[] bOscVal;


//------------------------------------------------------------------------
//  Methods
//------------------------------------------------------------------------
void setup() {
  size(360,360);
  

  /*
   *  OSC Setup
   */
  // receive
  osc = new OscP5(this, 12000);
  // send
  netAddress = new NetAddress("192.168.178.109", 12000);

  // the 4 int values received from Max/MSP
  bOscVal = new boolean[4];
  

}

//------------------------------------------------------------------------
void draw() {
  background(255);

  // visually show the sent values
  noStroke();
  for(int i=0; i<bOscVal.length; i++) {
    // draw square
    fill( color( boolToInt(bOscVal[i])*255) );
    rect( i*(width/bOscVal.length),0, (width/bOscVal.length),height ); 
  }

}

//------------------------------------------------------------------------
void sendMessage(String name, int val) {
  OscMessage message = new OscMessage(name);
  message.add( val );
  osc.send(message, netAddress);
}

//------------------------------------------------------------------------
int boolToInt(boolean val) {
  return (val) ? 1 : 0;
}



//------------------------------------------------------------------------
//  Events
//------------------------------------------------------------------------
void keyPressed() {

  if(key == '1') {
    bOscVal[0] =! bOscVal[0];
    sendMessage( "/one",  boolToInt(bOscVal[0]) );
  }
  if(key == '2') {
    bOscVal[1] =! bOscVal[1];
    sendMessage( "/two",  boolToInt(bOscVal[1]) );
  }
  if(key == '3') {
    bOscVal[2] =! bOscVal[2];
    sendMessage( "/three",  boolToInt(bOscVal[2]) );
  }
  if(key == '4') {
    bOscVal[3] =! bOscVal[3];
    sendMessage( "/four",  boolToInt(bOscVal[3]) );
  }
  
}

