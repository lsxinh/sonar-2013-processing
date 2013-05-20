package frederickk.tools;

import processing.core.PApplet;

/*
 *  Frederickk.Tools 0.0.5
 *  FFade.java
 *  
 *  
 *  Copyright (c) 2009, Rui Madeira
 *  
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or (at your option) any later version.
 *  
 *  http://creativecommons.org/licenses/LGPL/2.1/
 *  
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *  
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 *
 *	ported from OpenFrameworks to processing by
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
//import processing.core.PApplet;



public class FFade {
	//-----------------------------------------------------------------------------
	// properties
	//-----------------------------------------------------------------------------
	public static PApplet p5;

	// constants
	final static int DEFAULT_FADE_MILLIS = 1000;
	
	protected float alpha;
	protected int fadeMillis;
	protected long timeStartFade, timeEndFade;
	protected boolean bBeginFade, bFadeIn, bFadeOut;


	
	//-----------------------------------------------------------------------------
	// constructor
	//-----------------------------------------------------------------------------
	public FFade(PApplet _papplet) {
		p5 = _papplet;
		
		bFadeIn = false;
		bFadeOut = false;
		bBeginFade = false;
		alpha = 1.0f;
		fadeMillis = DEFAULT_FADE_MILLIS;
	}



	//-----------------------------------------------------------------------------
	// methods
	//-----------------------------------------------------------------------------
	public void update(){
		long _currentTime = p5.millis();
		update(_currentTime);
	}

	public void update(long currentTime){
		if(bBeginFade){
			bBeginFade = false;
			timeStartFade = currentTime;
			if(bFadeIn)timeEndFade = currentTime + (long)((1.0f - alpha) * fadeMillis);
			else timeEndFade = currentTime + (long)(alpha*fadeMillis);
			if(timeEndFade == currentTime){
				if(bFadeIn){
					bFadeIn = false;
					alpha = 1.0f;
				} else {
					bFadeOut = false;
					alpha = 0.0f;
				}
			}
		}
		if(bFadeIn){
			alpha = 1.0f - ((float)(timeEndFade - currentTime)) / (float)fadeMillis;
			if(currentTime > timeEndFade){
				bFadeIn = false;
				alpha = 1.0f;
			}
		} else if(bFadeOut){
			alpha = (float)(timeEndFade - currentTime) / (float)fadeMillis;
			if(currentTime > timeEndFade){
				bFadeIn = false;
				alpha = 0.0f;
			}
		}
	}

	//-----------------------------------------------------------------------------
	public void fadeIn(){
		if(bFadeIn)return;
		if(alpha == 1.0f) return;
		bBeginFade = true;
		bFadeIn = true;
		bFadeOut = false;
	}

	//-----------------------------------------------------------------------------
	public void fadeOut(){
		if(bFadeOut)return;
		if(alpha == 0.0f) return;
		bBeginFade = true;
		bFadeOut = true;
		bFadeIn = false;
	}

	//-----------------------------------------------------------------------------
	/**
	 *	return fading in
	 * 
	 *	@return bFadeIn 
	 */
	public boolean isFadingIn(){
		return bFadeIn;
	}

	//-----------------------------------------------------------------------------
	/**
	 *	return fading out 
	 * 
	 *	@return bFadeOut 
	 */
	public boolean isFadingOut(){
		return bFadeOut;
	}

	//-----------------------------------------------------------------------------
	/**
	 *	stop fade 
	 */
	public void stopFade(){
		bBeginFade = bFadeIn = bFadeOut = false;
	}



	//-----------------------------------------------------------------------------
	// sets
	//-----------------------------------------------------------------------------
	/**
	 * @param _seconds
	 *		  length of fade in seconds 
	 */
	public void setFadeSeconds(float _seconds){
		setFadeMillis((int)(_seconds * 1000.0f));
	}

	/**
	 * @param _millis
	 *		  length of fade in milliseconds 
	 */
	public void setFadeMillis(int _millis){
		fadeMillis = _millis;
	}

	//-----------------------------------------------------------------------------
	/**
	 * @param val
	 *		  alpha
	 */
	public void setAlpha(float val){
		alpha = val;
	}



	//-----------------------------------------------------------------------------
	// gets
	//-----------------------------------------------------------------------------
	public float getFadeSeconds(){
		return (float)fadeMillis * 0.001f;
	}

	public int getFadeMillis(){
		return fadeMillis;
	}

	//-----------------------------------------------------------------------------
	public float getAlpha(){
		return alpha;
	}


	
}
