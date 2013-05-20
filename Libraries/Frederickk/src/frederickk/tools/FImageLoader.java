package frederickk.tools;

/*
 *  Frederickk.Tools 0.0.5
 *  FImageLoader.java
 *
 *  Ken Frederick
 *  ken.frederick@gmx.de
 *
 *  http://cargocollective.com/kenfrederick/
 *  http://kenfrederick.blogspot.com/
 *
 *  a collection of tools that i tend to use frequently
 *  http://github.com/frederickk/frederickk
 *
 */



//-----------------------------------------------------------------------------
// libraries
//-----------------------------------------------------------------------------
import processing.core.PApplet;
import processing.core.PImage;
import java.io.File;



public class FImageLoader {
	//-----------------------------------------------------------------------------
	// properties
	//-----------------------------------------------------------------------------
	public static PApplet p5;

	private File folder;
	private PImage[] img;

	private String[] files;
	//private String filepath;



	//-----------------------------------------------------------------------------
	// constructor
	//-----------------------------------------------------------------------------
	/**
	 * instantiate FImageLoader
	 * 
	 * @param _p5
	 * 			PApplet
	 * @param filepath
	 *		  the path of the images to load
	 */
	public FImageLoader(
			PApplet _p5,
			String filepath) {

		p5 = _p5;
		setFolder(p5.sketchPath + filepath);
	}



	//-----------------------------------------------------------------------------
	// sets
	//-----------------------------------------------------------------------------
	/**
	 * @param filepath
	 *		  the path to load
	 */
	public void setFolder(
			String filepath) {

		//setFolderName(p5.sketchPath + filepath);
		setFolderName(filepath);

		//if(folder.isFile()) {
		boolean ds;
		String dsTest = folder.list()[0];

		if(dsTest.equals(".DS_Store") == true) {
			files = new String[folder.list().length-1];
			ds = true;
		} else {
			files = new String[folder.list().length];
			ds = false;
		}

		if(files != null || files.length != 0) {
			img = new PImage[files.length];

			if(ds) {
				for(int i=0; i != folder.list().length-1; i++) {
					files[i] = folder.list()[i+1];
					img[i] = p5.loadImage(filepath + "/" + files[i]);
					System.out.println("loading image " + files[i]);
				}

			} else if(!ds) {
				for(int i=0; i != folder.list().length; i++) {
					files[i] = folder.list()[i];
					img[i] = p5.loadImage(filepath + "/" + files[i]);
					System.out.println("loading image " + files[i]);
				}
			}

		}

		/*
		} else {
			System.out.println(folder + " is not a file");
		}
		 */
	}

	/**
	 * @param filepath
	 *		  set the folder to read
	 */
	private void setFolderName(String filepath) {
		folder = new File(filepath);
	}

	/**
	 * @param dateiName
	 *		  set the file to read
	 */
	public void setFile(String dateiName) {
		img = new PImage[1];
		files = new String[1];

		for(int i=0; i !=img.length; i++) {
			img[i] = p5.loadImage(dateiName);
			files[i] = dateiName;

			//String[] n_files = p5.split(files[i], '/');
			String[] n_files = files[i].split("/");
			files[i] = n_files[n_files.length-1];

			System.out.println("loading image " + files[i]);
		}
	}



	//-----------------------------------------------------------------------------
	// gets
	//-----------------------------------------------------------------------------
	/**
	 * return number of files
	 * 
	 * @return d
	 */
	public int getFileNum() {
		int d = files.length;
		return d;

	}

	/**
	 * return number of images 
	 * 
	 * @return b
	 */
	public int getImageNum() {
		int b = img.length;
		return b;
	}

	/**
	 * @param w
	 *		  index of image to return
	 *		  
	 * return a specific image
	 * 
	 * @return img[w]
	 */
	public PImage getImage(int w) {
		w = inBounds(w);
		return img[w];
	}

	/**
	 * @param w
	 *		  number of file to use
	 *		  
	 * return a specific file 
	 * 
	 * @return files[w]
	 */
	public String getFile(int w) {
		w = inBounds(w);
		return files[w];
	}

	/**
	 * @param w
	 *		  value to keep within the range of colors created
	 */
	private int inBounds(int w) {
		w = PApplet.constrain(w, 0,getImageNum());
		return w;
	}

}


