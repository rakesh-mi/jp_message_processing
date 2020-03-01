package com.jpmc.rakesh.messageprocessing;

import com.jpmc.rakesh.messageprocessing.model.Message;
import com.jpmc.rakesh.messageprocessing.model.MessageTypeTwo;
import com.jpmc.rakesh.messageprocessing.model.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;


public class MessageTypeTwoTest {
	Message messageTypeTwo;
	private Map<String, Product> listProducts;

	@Before
	public void setUp() {
		messageTypeTwo = new MessageTypeTwo();
		listProducts = new LinkedHashMap<String, Product>();
	}

	@Test
	public void TestValidMessageTypeTwo1() {
		String messageLine = "asdsds";
		boolean valid = messageTypeTwo.ifValidMessage(messageLine);
		assertEquals(valid, false);
	}

	@Test
	public void TestValidMessageTypeTwo2() {
		String messageLine = "apple at 10p";
		boolean valid = messageTypeTwo.ifValidMessage(messageLine);
		assertEquals(valid, false);
	}

	@Test
	public void TestValidMessageTypeTwo3() {
		String messageLine = "Multiply 20p apples";
		boolean valid = messageTypeTwo.ifValidMessage(messageLine);
		assertEquals(valid, false);
	}

	@Test
	public void TestValidMessageTypeTwo4() {
		String messageLine = "20 sales of oranges at 10p each";
		boolean valid = messageTypeTwo.ifValidMessage(messageLine);
		assertEquals(valid, true);
	}

	@Test
	public void TestValidMessageTypeTwo5() {
		String messageLine = "20 sales of oranges at 10 each";
		boolean valid = messageTypeTwo.ifValidMessage(messageLine);
		assertEquals(valid, false);
	}

	@Test
	public void TestValidMessageTypeTwo6() {
		String messageLine = "20 sales of oranges at 10p eachs";
		boolean valid = messageTypeTwo.ifValidMessage(messageLine);
		assertEquals(valid, false);
	}

	@Test
	public void TestValidMessageTypeTwo7() {
		String messageLine = " sales of oranges at 10p each";
		boolean valid = messageTypeTwo.ifValidMessage(messageLine);
		assertEquals(valid, false);
	}

	@Test
	public void TestMessageTypeTwo1() {
		String messageLine = "20 sales of apples at 10p each";
		listProducts.put("apple", new Product());
		messageTypeTwo.Process(messageLine, listProducts, 1);

		int numberOfSales = listProducts.get("apple").getNumberOfSales();
		int totalValue = listProducts.get("apple").getTotalValue();
		assertEquals(numberOfSales, 20);
		assertEquals(totalValue, 200);
	}

	@Test
	public void TestMessageTypeTwo2() {
		String messageLine = "20 sales of apples at 10p each";
		listProducts.put("apple", new Product());
		messageTypeTwo.Process(messageLine, listProducts, 1);

		messageLine = "10 sales of apples at 5p each";
		messageTypeTwo.Process(messageLine, listProducts, 2);

		int numberOfSales = listProducts.get("apple").getNumberOfSales();
		int totalValue = listProducts.get("apple").getTotalValue();
		assertEquals(numberOfSales, 30);
		assertEquals(totalValue, 250);
	}
}
