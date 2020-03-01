package com.jpmc.rakesh.messageprocessing.model;


import com.jpmc.rakesh.messageprocessing.utils.Regex;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author rakesh </br>
 *         This type of messages can add multiple sales to product
 */
public class MessageTypeTwo implements Message {
	// regex for essage format validating
	private String MessageTypeTwo_REGEX = "(\\d+)\\ssales\\sof\\s(\\w+)s\\sat\\s(\\d+)p\\seach$";

	public boolean ifValidMessage(String messageLine) {
		boolean isValid = Regex.isRegexMatch(MessageTypeTwo_REGEX, messageLine);
		return isValid;
	}

	public boolean Process(String Message, Map<String, Product> ListProducts, int messageCount) {

		List<String> tokens = Regex.getRegexTokens(this.MessageTypeTwo_REGEX, Message);

		int nOfSales = Integer.parseInt(tokens.get(1));
		String productName = tokens.get(2);

		// if product not found before add it to map
		if (!ListProducts.containsKey(productName))
			ListProducts.put(productName, new Product());
		Product product = ListProducts.get(productName);
		int saleValue = Integer.parseInt(tokens.get(3));
		product.addDuplicatedSalesToProduct(new Sale(saleValue), nOfSales);

		String historyRecordLine = "At message#" + messageCount + " Adding (" + nOfSales + ") sales to: " + productName
				+ " with value: " + saleValue;
		product.addHistoryRecord(historyRecordLine);

		return true;

	}

}
