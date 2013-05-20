/**
 *  Sonar 2013
 *  OSC Test
 *
 *  Ken Frederick
 *  ken.frederick@gmx.de
 *
 *  http://cargocollective.com/kenfrederick/
 *  http://kenfrederick.blogspot.com/
 *
 *  receive OSC message and pass it along 
 *  serially to an arduino
 *
 *  tested with Processing 1.5.1
 *
 */
 



//------------------------------------------------------------------------
//  Libraries
//------------------------------------------------------------------------
import processing.opengl.*;
import processing.serial.*;

import oscP5.*;
import netP5.*;



//------------------------------------------------------------------------
//  Properties
//------------------------------------------------------------------------
OscP5 osc;
NetAddress netAddress;

int[] oscVal;

Serial arduino;



//------------------------------------------------------------------------
//  Methods
//------------------------------------------------------------------------
void setup() {
  size(720,720);
  

  /*
   *  OSC Setup
   */
  // receive
  osc = new OscP5(this, 12000);
  // send
//  netAddress = new NetAddress("192.168.178.109", 12000);

  // the 4 int values received from Max/MSP
  oscVal = new int[4];
  osc.plug(this, "one", "/one");
  osc.plug(this, "two", "/two");
  osc.plug(this, "three", "/three");
  osc.plug(this, "four", "/four");
  

  /*
   *  Arduino Setup
   */
  println( Serial.list() );
  String port = Serial.list()[0];
  int baud = 9600;
  arduino = new Serial(this, port, baud);


}

//------------------------------------------------------------------------
void draw() {
  background(0);

  // Prepare packet to send to arduino
  arduino.write('M');

  // visually show the OSC values
  noStroke();
  for(int i=0; i<oscVal.length; i++) {
    // draw square
    fill( color(oscVal[i]*255) );
    rect( i*(width/oscVal.length),0, (width/oscVal.length),height ); 
  
    // send value to arduino
    arduino.write( oscVal[i] );
    
  }

  // clean out the serial channel
//  arduino.flush();


}



//------------------------------------------------------------------------
//  Events
//------------------------------------------------------------------------
/*
 *  debugging with Daniel
 */
void oscEvent(OscMessage message) {
  println( message.addrPattern() );

  print( " /" + message.addrPattern().charAt(4) );
  print( " /" + message.addrPattern().charAt(6) );
  print( " /" + message.addrPattern().charAt(8) );
  println( " /" + message.addrPattern().charAt(10) );

  print( " /" + (int(message.addrPattern().charAt(4))-48) );
  print( " /" + (int(message.addrPattern().charAt(6))-48) );
  print( " /" + (int(message.addrPattern().charAt(8))-48) );
  println( " /" + (int(message.addrPattern().charAt(10))-48) );

  println( "------" );

  oscVal[0] = (int) (message.addrPattern().charAt(4))-48;
  oscVal[1] = (int) (message.addrPattern().charAt(6))-48;
  oscVal[2] = (int) (message.addrPattern().charAt(8))-48;
  oscVal[3] = (int) (message.addrPattern().charAt(10))-48;
}


/*
 *  each event coorellates to a 
 *  value sent from Max/MSP
 */
public void one(int value) {
  println( "one --- " + value );
  oscVal[0] = value;
}

public void two(int value) {
  println( "two --- " + value );
  oscVal[1] = value;
}

public void three(int value) {
  println( "three - " + value );
  oscVal[2] = value;
}

public void four(int value) {
  println( "four -- " + value );
  println(" --------- ");
  oscVal[3] = value;
}



