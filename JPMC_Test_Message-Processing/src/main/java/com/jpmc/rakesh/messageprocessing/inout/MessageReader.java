package com.jpmc.rakesh.messageprocessing.inout;

/**
 * 
 * @author rakesh </br>
 *         This interface This interface defines input method. If you want to
 *         add your own input method, just implement this
 */
public interface MessageReader {

	public String getNextMessageLine();

	/**
	 * check if message reader has another lines or not
	 * 
	 * @return
	 */
	public boolean hasNext();

	/**
	 * This method to be called after finishing reading messages. </br>
	 * It is very helpful to close any opened buffer
	 */
	public void finish();
}
