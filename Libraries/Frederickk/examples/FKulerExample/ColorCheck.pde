/**
 *  ColorCheck.pde
 *
 *  Ken Frederick
 *  ken.frederick@gmx.de
 *
 *  http://cargocollective.com/kenfrederick/
 *  http://kenfrederick.blogspot.com/
 *
 *  example of a custom checkbox FControl gui element
 *
 */


public class ColorCheck extends FCheck {
  // ------------------------------------------------------------------------
  // properties
  // ------------------------------------------------------------------------
  PApplet papplet;
  int fillColor;



  // ------------------------------------------------------------------------
  // constructor
  // ------------------------------------------------------------------------
  public ColorCheck(PApplet p5, String _name, float _x, float _y, float _w, float _h, int _fillColor) {
    super(p5);

    setColor(_fillColor);

    setName(_name);
    setCoord(_x, _y);
    setSize((int)_w, (int)_h);
    setValue(false);
  }



  // ------------------------------------------------------------------------
  // methods
  // ------------------------------------------------------------------------
  /**
   *  draw() is a required method
   */
  public void draw() {
    update(); // required method to include
    toggle(); // required method to include


    //
    // start drawing what the custom checkbox
    //
    pushStyle();

    // over pointer
    noStroke();
    if ( getOver() || LOCKED ) fill(255, 255*0.95);
    else noFill();
    triangle(
      x+width/2, y+height+6, 
      x+width/2-6, y+height+15, 
      x+width/2+6, y+height+15
    );

    // x'd
    if (!val) {
      fill(fillColor);
    }
    else {
      int fillColorDark = FColor.desaturate(fillColor, 0.5, 0.2);
      fill(fillColorDark);
    }

    rect(x, y, width, height);
    popStyle();
  }



  // ------------------------------------------------------------------------
  // sets
  // ------------------------------------------------------------------------
  public void setColor(int _fillColor) {
    fillColor = _fillColor;
  }
}

