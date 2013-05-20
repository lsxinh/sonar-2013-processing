package frederickk.tools;

/*
 *  Frederickk.Tools 0.0.5
 *  FDataReader.java
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
import java.io.File;



public class FDataReader {
	//-----------------------------------------------------------------------------
	// properties
	//-----------------------------------------------------------------------------
	public static PApplet p5;

	private File folder;
	private String[] files;
	//private String folderName;



	//-----------------------------------------------------------------------------
	// constructor
	//-----------------------------------------------------------------------------
	/**
	 * instantiate FDataReader
	 * 
	 * @param _p5
	 * 			PApplet
	 * @param folderName
	 *		  the path of the files to load
	 */
	public FDataReader(
			PApplet _p5,
			String folderName) {

		p5 = _p5;
		setFolder(folderName);
	}



	//-----------------------------------------------------------------------------
	// methods
	//-----------------------------------------------------------------------------
	/**
	 * @param folderName
	 *		  the path of the files to load
	 */
	public void setFolder(
			String folderName) {

		setFolderName(p5.sketchPath + folderName);

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

			if(ds) {
				for(int i=0; i != folder.list().length-1; i++) {
					files[i] = folder.list()[i+1];
					System.out.println("file " + files[i]);
				}

			} else if(!ds) {
				for(int i=0; i != folder.list().length; i++) {
					files[i] = folder.list()[i];
					System.out.println("file " + files[i]);
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
	 * @param folderName
	 *		  set the file to read
	 */
	private void setFolderName(String folderName) {
		folder = new File(folderName);
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
		w = PApplet.constrain(w, 0,getFileNum());
		return w;
	}

}


