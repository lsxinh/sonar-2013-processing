package frederickk.api;

/**
 *  Frederickk.Api 0.0.5
 *  FASE.java
 *
 *  Ken Frederick
 *  ken.frederick@gmx.de
 *
 *  http://cargocollective.com/kenfrederick/
 *  http://kenfrederick.blogspot.com/
 *
 *  a class to load and save ASE files
 *  code stolen and modified from the following
 *
 *  loading:
 *  https://github.com/DanielWeber/kulerviewer/
 *
 *  saving:
 *  http://www.generative-gestaltung.de/
 *
 */



//-----------------------------------------------------------------------------
// libraries
//-----------------------------------------------------------------------------
import processing.core.*;
import java.util.ArrayList;
import java.util.Arrays;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;



public class FASE {
	//-----------------------------------------------------------------------------
	// properties
	//-----------------------------------------------------------------------------
	private PApplet papplet;

	private ArrayList<Integer> palette;
	private boolean bVerbose = false;



	//-----------------------------------------------------------------------------
	// constructor
	//-----------------------------------------------------------------------------
	public FASE(PApplet _papplet) {
		papplet = _papplet;
		palette = new ArrayList<Integer>();
	}
	public FASE(PApplet _papplet, String filepath) {
		papplet = _papplet;
		palette = new ArrayList<Integer>();
		load(filepath);
	}



	//-----------------------------------------------------------------------------
	// methods
	//-----------------------------------------------------------------------------
	/**
	 * @param filepath
	 * 			filepath of ASE file
	 */
	public void load(String filepath) {
		parseASEFile(papplet.sketchPath + filepath);
	}
	
	/**
	 *
	 *  load ASE
	 *  https://github.com/DanielWeber/kulerviewer/
	 *
	 */
	private void parseASEFile(String filepath) {
		File ase = new File(filepath);
		FileInputStream inStream;
		ByteBuffer buf;

		try {
			inStream = new FileInputStream(ase);
			buf = ByteBuffer.allocate((int)inStream.getChannel().size() * 2);
			buf.order(ByteOrder.BIG_ENDIAN);
			inStream.getChannel().read(buf);

			buf.rewind();

			byte[] signature = new byte[4];
			byte[] expectedSignature = new byte[] { 'A', 'S', 'E', 'F' };
			buf.get(signature);
			if (!Arrays.equals(expectedSignature, signature)) {
				throw new IOException("'" + ase.getAbsolutePath() + "' is not an .ase file. Signature does not match!");
			}
			/* int major = */    buf.getShort();
			/* int minor = */    buf.getShort();
			int noBlocks = buf.getInt();
			for (int i=0; i<noBlocks; ++i) {
				readBlock(buf, palette);
			}
		}
		catch(Exception e) {
			if(bVerbose) System.out.println(e);
		}
	}

	//-----------------------------------------------------------------------------
	private void readBlock(ByteBuffer buf, ArrayList<Integer> _palette) {
		buf.mark();
		short blockType = buf.getShort();
		/* int blockLength = */    buf.getInt();
		short nameLen = buf.getShort();

		switch(blockType) {
		case (short)0xc001:
			// group start
			String name = readString(buf);
		if(bVerbose) System.out.println("Starting group '" + name + "'");
		break;
		case (short)0xc002:
			// group end
			buf.position(buf.position() + nameLen);
		break;

		case 0x0001:
			// color entry
			String colorName = readString(buf);
			// 4 byte color model
			byte first = buf.get();
			buf.get();
			buf.get();
			buf.get();

			if ('R' == first) {
				int r = (int)(buf.getFloat() * 255);
				int g = (int)(buf.getFloat() * 255);
				int b = (int)(buf.getFloat() * 255);
				short type = buf.getShort();
				String types[] = new String[] { "Global", "Spot", "Normal" };
				if(bVerbose) System.out.printf("   %s (%3d, %3d, %3d) / %s", colorName, r, g, b, types[type]);
				if(bVerbose) System.out.println();
				_palette.add( papplet.color(r, g, b) );
			}
			else if ('C' == first) {
				throw new RuntimeException("Unable to handle CMYK colors");
			}
			else if ('L' == first) {
				throw new RuntimeException("Unable to handle LAB colors");
			}
			else if ('G' == first) {
				throw new RuntimeException("Unable to handle Gray colors");
			}

			break;
		}
	}

	//-----------------------------------------------------------------------------
	/**
	 * Reads a null terminated string from the given buffer.
	 * 
	 * @param buf to read from
	 * @return The string that has been read from the buffer's current position
	 */
	private String readString(ByteBuffer buf) {
		final StringBuilder ret = new StringBuilder(10);
		char next = buf.getChar();
		while (next != (char)0) {
			ret.append(next);
			next = buf.getChar();
		}
		return ret.toString();
	}


	//-----------------------------------------------------------------------------
	/**
	 *
	 *  save ASE
	 *  http://www.generative-gestaltung.de
	 *
	 */

	/**
	 * Exports an .ase (Adobe Swatch Echange) file with default names
	 * 
	 * @param theParent
	 *            Reference to a PApplet. Typically use "this"
	 * @param theColors
	 *            color values (rgb 0-255) of the swatches
	 * @param filepath
	 *            filename of the .ase file
	 */
	private void saveASE(PApplet theParent, int[] theColors, String filepath) {
		String[] names = new String[theColors.length];

		for (int i = 0; i < theColors.length; i++) {
			int rDec = (theColors[i] >> 16) & 0xFF;
			int gDec = (theColors[i] >> 8) & 0xFF;
			int bDec = theColors[i] & 0xFF;

			names[i] = "R=" + PApplet.nf(rDec, 3) + " G=" + PApplet.nf(gDec, 3) + " B=" + PApplet.nf(bDec, 3);
		}

		saveASE(theParent, theColors, names, filepath);
	}

	/**
	 * Exports an .ase (Adobe Swatch Echange) file
	 * 
	 * @param theParent
	 *            Reference to a PApplet. Typically use "this"
	 * @param theColors
	 *            color values (rgb 0-255) of the swatches
	 * @param theNames
	 *            names of the swatches
	 * @param filepath
	 *            filename of the .ase file
	 */
	private void saveASE(PApplet theParent, int[] theColors, String[] theNames, String filepath) {
		String NUL = new Character((char) 0).toString();
		String SOH = new Character((char) 1).toString();

		int countColors = theColors.length;

		String ase = "ASEF" + NUL + SOH + NUL + NUL;
		for (int i = 24; i >= 0; i -= 8) {
			ase += new Character((char) ((countColors >> i) & 0xFF)).toString();
		}
		ase += NUL;

		for (int i = 0; i < countColors; i++) {
			ase += SOH + NUL + NUL + NUL;
			ase += new Character( (char) ((((theNames[i].length() + 1) * 2) + 20)) ).toString() + NUL;
			ase += new Character( (char) (theNames[i].length() + 1) ).toString() + NUL;

			for (int j = 0; j < theNames[i].length(); j++) {
				ase += theNames[i].substring(j, j + 1) + NUL;
			}

			// extract red, green, and blue components from colors[i]
			float rDec = (theColors[i] >> 16) & 0xFF;
			float gDec = (theColors[i] >> 8) & 0xFF;
			float bDec = theColors[i] & 0xFF;

			String r = new String(floatTobytes(rDec / 255f));
			String g = new String(floatTobytes(gDec / 255f));
			String b = new String(floatTobytes(bDec / 255f));

			ase += NUL + "RGB ";
			ase += r.substring(0, 1) + r.substring(1, 2) + r.substring(2, 3) + NUL;
			ase += g.substring(0, 1) + g.substring(1, 2) + g.substring(2, 3) + NUL;
			ase += b.substring(0, 1) + b.substring(1, 2) + b.substring(2, 3) + NUL;
			if ((i + 1) != countColors) {
				ase += NUL + NUL + NUL;
			}
		}
		ase += NUL + NUL;

		byte[] bytes = ase.getBytes();
		theParent.saveBytes(filepath, bytes);
	}

	// mini helper function for "saveASE"
	private byte[] floatTobytes(float theNumber) {
		ByteBuffer buf = ByteBuffer.allocate(4);
		buf.putFloat(theNumber);
		return buf.array();
	}



	//-----------------------------------------------------------------------------
	// sets
	//-----------------------------------------------------------------------------
	public void savePalette(int[] theColors, String filepath) {
		saveASE(papplet, theColors, filepath);
	}

	//-----------------------------------------------------------------------------
	public void setVerbose(boolean val) {
		bVerbose = val;
	}



	//-----------------------------------------------------------------------------
	// gets
	//-----------------------------------------------------------------------------
	public ArrayList<Integer> getPalette() {
		return palette;
	}
}