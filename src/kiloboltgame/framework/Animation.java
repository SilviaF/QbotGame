package kiloboltgame.framework;

import java.awt.Image;
import java.util.ArrayList;

public class Animation {

	private ArrayList frames; //will contain an image and a duration it is displayed
	private int currentFrame;
	private long animTime; 
	private long totalDuration;
	
	public Animation(){
		frames = new ArrayList();
		totalDuration = 0;
		/* We create a synchronized block so animTime and currentFrame are always
		 * called sequentially (together) so one is not called first and worked with
		 * and then the other*/
		synchronized(this){
			animTime = 0;
			currentFrame = 0;
		}
	}
	
	/**
	 * Takes an AnimFrame object and "appends" it to the frames ArrayList
	 * We make this method synchronized so that the frames of the animation come sequentially
	 * @param image
	 * @param duration
	 */
	public synchronized void addFrame(Image image, long duration){
		totalDuration += duration;
		frames.add(new AnimFrame(image, totalDuration));
	}
	
	/**
	 * Switches frames as necessary. Is called repeatedly.
	 * @param elapsedTime
	 */
	public synchronized void update(long elapsedTime){
		if(frames.size()>1){
			animTime += elapsedTime;
			if(animTime >= totalDuration){
				animTime = animTime % totalDuration;
				currentFrame = 0;
			}
			while(animTime > getFrame(currentFrame).endTime){
				currentFrame++;
			}
		}
	}
	
	/**
	 * Returns the image that belongs to the currentFrame
	 * @return
	 */
	public synchronized Image getImage(){
		if(frames.size() == 0){
			return null;
		} else {
			return getFrame(currentFrame).image;
		}
	}
	
	/**
	 * Returns the current AnimFrame of the animation sequence
	 * @param i
	 * @return
	 */
	private AnimFrame getFrame(int i){
		return (AnimFrame) frames.get(i);
	}
	
	
	/*Nested class only used by the Animation class. Contains the values of image and duration of the frame.*/
	private class AnimFrame {
		Image image;
		long endTime;
		
		public AnimFrame(Image image, long endTime){
			this.image = image;
			this.endTime = endTime;
		}
	}
	

}
