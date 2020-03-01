package com.jpmc.rakesh.messageprocessing.model;


import com.jpmc.rakesh.messageprocessing.inout.MessageWriter;

import java.util.Map;
import java.util.Map.Entry;

public class IntervalReport implements Report {
	private MessageWriter messageWriter;

	public void setOutputMessageWriter(MessageWriter messageWriter) {
		this.messageWriter = messageWriter;

	}

	public void Process(Map<String, Product> listProducts, int messageCount) {
		String logId = "SaleAfter: " + messageCount;
		messageWriter.writeMessageLine(logId);
		
		for (Entry<String, Product> product : listProducts.entrySet()) {
			String Line = "Product: " + product.getKey() + "  Value: " + product.getValue().getTotalValue() + " Sales:"
					+ product.getValue().getNumberOfSales();
			messageWriter.writeMessageLine(Line);
		}
	}

}
