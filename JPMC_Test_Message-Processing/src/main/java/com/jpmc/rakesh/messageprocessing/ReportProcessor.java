package com.jpmc.rakesh.messageprocessing;

import com.jpmc.rakesh.messageprocessing.inout.MessageWriter;
import com.jpmc.rakesh.messageprocessing.config.Configuration;
import com.jpmc.rakesh.messageprocessing.model.FinalReport;
import com.jpmc.rakesh.messageprocessing.model.IntervalReport;
import com.jpmc.rakesh.messageprocessing.model.Product;
import com.jpmc.rakesh.messageprocessing.model.Report;

import java.util.Map;


public class ReportProcessor {

	private Report intervalReport;
	private Report finalReport;

	// List products
	private Map<String, Product> listProducts;
	private MessageWriter messageWriter;

	public ReportProcessor(MessageWriter messageWriter) {
		this.messageWriter = messageWriter;
		initalizeReports();
	}

	private void initalizeReports() {
		intervalReport = new IntervalReport();
		intervalReport.setOutputMessageWriter(this.messageWriter);
		finalReport = new FinalReport();
		finalReport.setOutputMessageWriter(this.messageWriter);
	}

	public void processIntervalReports(int count) {
		if (count % Configuration.REPORT_INTERVAL == 0) {
			intervalReport.Process(listProducts, count);
		}
	}

	public void processFinalReport(int maxCount) {
		finalReport.Process(listProducts, maxCount);
	}

	public Report getIntervalReport() {
		return intervalReport;
	}

	public void setIntervalReport(Report intervalReport) {
		this.intervalReport = intervalReport;
	}

	public Map<String, Product> getListProducts() {
		return listProducts;
	}

	public void setListProducts(Map<String, Product> listProducts) {
		this.listProducts = listProducts;
	}

}
