/**
 *  FEdgeDetect.pde
 *
 *  Ken Frederick
 *  ken.frederick@gmx.de
 *
 *  edge detection class inspired by
 *  http://processing.org/learning/topics/edgedetection.html
 */



public class FEdgeDetect {
  // -----------------------------------------------------------------------------
  // properties
  // -----------------------------------------------------------------------------
  private PImage img;
  private PImage edgeImg;
  private float thresh;
  private float density;
  private float[][] kernel = {{ -1, -1, -1 }, 
                              { -1,  1, -1 }, 
                              { -1, -1, -1 }};

  public ArrayList<PVector> edgePVector = new ArrayList<PVector>();
  public ArrayList<Float> edgeFloat = new ArrayList<Float>();

  public boolean bProcessEdge = false;
  public boolean bProcessImage = false;
  public boolean bProcessMovie = false;



  // -----------------------------------------------------------------------------
  // constructor
  // -----------------------------------------------------------------------------
  public FEdgeDetect() {
  }

  public FEdgeDetect(PImage _img) {
    setImage(_img);
  }



  // -----------------------------------------------------------------------------
  // methods
  // -----------------------------------------------------------------------------
  public void process(float _thresh) {
    if (_thresh != thresh || bProcessImage || bProcessMovie) {
      thresh = _thresh;
      bProcessImage = false;
      kernel[1][1] = thresh;

      for (int y=1; y<img.height-1; y++) {
        for (int x=1; x<img.width-1; x++) {
          float sum = 0; // Kernel sum for this pixel

          for (int ky = -1; ky <= 1; ky++) {
            for (int kx = -1; kx <= 1; kx++) {
              int pos = (y + ky)*img.width + (x + kx);
              int val = (img.pixels[pos] >> 16) & 0xFF;
              sum += kernel[ky+1][kx+1] * val;
            }
          }

          edgeImg.pixels[y*img.width + x] = color(sum);
        }
      }

      edgeImg.updatePixels();
    } // end check
  }

  public void process(PImage img, float thresh, boolean _process) {
    setImage(img);
    bProcessMovie = _process;
    process(thresh);
  }

  public void findEdges(int _density, float b_low, float b_high) {
    // println(_density + "\t!=\t" + density);
    if (_density != density || bProcessEdge || bProcessMovie) {
      density = _density;
      bProcessEdge = false;

      edgePVector.clear();
      edgeFloat.clear();

      for (int x=1; x<edgeImg.width-1; x+=density) {
        for (int y=1; y<edgeImg.height-1; y+=density) {
          //float b = brightness( edgeImg.get(x,y) );
          float b = FTools.luminance( edgeImg.get(x, y) );
          if ( b > b_low && b < b_high ) {
            float z = norm(b, 0, 255);
            edgePVector.add( new PVector(x, y, z) );

            edgeFloat.add( new Float(x) );
            edgeFloat.add( new Float(y) );
            edgeFloat.add( new Float(z) );
          }
        }
      }
    } // end check

    if (edgePVector.size() == 0) {
      edgePVector.add( new PVector(0, 0, 0) );
    }
    if (edgeFloat.size() == 0) {
      edgeFloat.add( 0.0f );
      edgeFloat.add( 0.0f );
      edgeFloat.add( 0.0f );
    }
  }



  // -----------------------------------------------------------------------------
  // sets
  // -----------------------------------------------------------------------------
  protected void setImage(PImage _img) { 
    img = _img;
    img.loadPixels();
    edgeImg = createImage(img.width, img.height, RGB);
    // edgeImg.filter(THRESHOLD, .4);
  }



  // -----------------------------------------------------------------------------
  // gets
  // -----------------------------------------------------------------------------
  public PImage getSource() {
    return img;
  }

  public PImage getEdge() {
    return edgeImg;
  }

  public ArrayList getEdgePVector() {
    return edgePVector;
  }

  /**
   *  need to figure out a better solution for 
   *  smoother integration with OpenGL VBOs
   */
}

