package com.jpmc.rakesh.messageprocessing;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;
import java.util.Map;

import com.jpmc.rakesh.messageprocessing.model.Message;
import com.jpmc.rakesh.messageprocessing.model.MessageTypeOne;
import com.jpmc.rakesh.messageprocessing.model.Product;
import org.junit.Before;
import org.junit.Test;


public class MessageTypeOneTest {
	private Message messageTypeOne;
	private Map<String, Product> listProducts;

	@Before
	public void setUp() {
		messageTypeOne = new MessageTypeOne();
		listProducts = new LinkedHashMap<String, Product>();

	}

	@Test
	public void TestValidMessageTypeOne1() {
		String messageLine = "asdsds";
		boolean valid = messageTypeOne.ifValidMessage(messageLine);
		assertEquals(valid, false);
	}

	@Test
	public void TestValidMessageTypeOne2() {
		String messageLine = "apple at 10p";
		boolean valid = messageTypeOne.ifValidMessage(messageLine);
		assertEquals(valid, true);
	}

	@Test
	public void TestValidMessageTypeOne3() {
		String messageLine = "apple at 10";
		boolean valid = messageTypeOne.ifValidMessage(messageLine);
		assertEquals(valid, false);
	}

	@Test
	public void TestValidMessageTypeOne4() {
		String messageLine = "apple   10p";
		boolean valid = messageTypeOne.ifValidMessage(messageLine);
		assertEquals(valid, false);
	}

	@Test
	public void TestValidMessageTypeOne5() {
		String messageLine = "apple at 10pp";
		boolean valid = messageTypeOne.ifValidMessage(messageLine);
		assertEquals(valid, false);
	}

	@Test
	public void TestValidMessageTypeOne6() {
		String messageLine = "orange at 10p";
		boolean valid = messageTypeOne.ifValidMessage(messageLine);
		assertEquals(valid, true);
	}

	@Test
	public void TestValidMessageTypeOne7() {
		String messageLine = "bate on 10p";
		boolean valid = messageTypeOne.ifValidMessage(messageLine);
		assertEquals(valid, false);
	}

	@Test
	public void TestValidMessageTypeOne8() {
		String messageLine = "20 sales of oranges at 41p each";
		boolean valid = messageTypeOne.ifValidMessage(messageLine);
		assertEquals(valid, false);
	}

	@Test
	public void TestValidMessageTypeOne9() {
		String messageLine = "Multiply 20p apples";
		boolean valid = messageTypeOne.ifValidMessage(messageLine);
		assertEquals(valid, false);
	}

	@Test
	public void TestMessageTypeOne1() {
		String messageLine = "apple at 10p";
		listProducts.put("apple", new Product());
		messageTypeOne.Process(messageLine, listProducts, 1);
		messageLine = "apple at 10p";
		messageTypeOne.Process(messageLine, listProducts, 2);

		int numberOfSales = listProducts.get("apple").getNumberOfSales();
		int totalValue = listProducts.get("apple").getTotalValue();
		assertEquals(numberOfSales, 2);
		assertEquals(totalValue, 20);
	}

	@Test
	public void TestMessageTypeOne2() {
		String messageLine = "apple at 10p";
		listProducts.put("apple", new Product());

		messageTypeOne.Process(messageLine, listProducts, 1);

		int numberOfSales = listProducts.get("apple").getNumberOfSales();
		int totalValue = listProducts.get("apple").getTotalValue();
		assertEquals(numberOfSales, 1);
		assertEquals(totalValue, 10);
	}

}
