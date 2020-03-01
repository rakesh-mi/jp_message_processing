package com.jpmc.rakesh.messageprocessing;

import com.jpmc.rakesh.messageprocessing.inout.MessageReader;
import com.jpmc.rakesh.messageprocessing.inout.MessageWriter;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class MessageProcessingApplicationTest {
	private MessageProcessor messageProcessor;
	private ReportProcessor reportProcessor;

	private MessageReader testMessageReader;
	private MessageWriter testMessgeWriter;
	private List<String> inputList;
	private List<String> outputList;

	private static final String resouceFolderPath = "src/test/resources/";

	@Before
	public void setUp() {
		inputList = new ArrayList<String>();
		outputList = new ArrayList<String>();
		testMessageReader = new TestMessageReader(inputList);
		testMessgeWriter = new TestMessageWriter();
		reportProcessor = new ReportProcessor(testMessgeWriter);
		messageProcessor = new MessageProcessor(testMessageReader, testMessgeWriter, reportProcessor);
	}

	@Test
	public void MessageProcessingApplicationTest1() throws IOException {
		inputList.clear();
		FileLinesToArrayList.readFileLinesToArrayList(resouceFolderPath + "input1.txt", inputList);
		outputList.clear();
		FileLinesToArrayList.readFileLinesToArrayList(resouceFolderPath + "output1.txt", outputList);
		messageProcessor.startProcessing();
		assertEquals(((TestMessageWriter) testMessgeWriter).getLines(), outputList);
	}

	@Test(expected = IllegalArgumentException.class)
	public void MessageProcessingApplicationTest2() {
		inputList.clear();
		inputList.add("applesss10p");
		inputList.add("20 sales of oranges at 41p each");
		inputList.add("Multiply 20p apples");
		inputList.add("orange at 10p");
		inputList.add("20 sales of oranges at 10p each");
		inputList.add("Multiply 20p apples");
		inputList.add("Subtract 20p oranges");
		inputList.add("Subtract 20p oranges");
		inputList.add("Multiply 20p apples");
		inputList.add("Add 20p apples");
		outputList.clear();
		outputList.add("SaleDataAfter: 10");
		outputList.add("Product: apple  Value: 80020 Sales:1");
		outputList.add("Product: orange  Value: 20 Sales:41");
		messageProcessor.startProcessing();
	}

	@Test
	public void MessageProcessingApplicationTest3() throws IOException {
		inputList.clear();
		FileLinesToArrayList.readFileLinesToArrayList(resouceFolderPath + "input2.txt", inputList);
		outputList.clear();
		FileLinesToArrayList.readFileLinesToArrayList(resouceFolderPath + "output2.txt", outputList);
		messageProcessor.startProcessing();
		assertEquals(((TestMessageWriter) testMessgeWriter).getLines(), outputList);
	}

}
