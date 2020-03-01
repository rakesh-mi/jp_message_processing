package com.jpmc.rakesh.messageprocessing.model;

import java.util.Map;

/**
 * 
 * @author rakesh </br>
 *         This interface defines message. If you want to add new message type
 *         you must implement these methods
 */
public interface Message {
	/**
	 * 
	 * @param Message
	 * @param ListProducts:
	 *            list of products
	 * @param messageCount:
	 *            messages number to be used in history
	 * @return
	 */
	public boolean Process(String Message, Map<String, Product> ListProducts, int messageCount);

	/**
	 * This method to validate message format. implement your own validator (
	 * regex, checks ....)
	 * 
	 * @param messageLine
	 * @return
	 */
	public boolean ifValidMessage(String messageLine);
}
