package com.jpmc.rakesh.messageprocessing;

import com.jpmc.rakesh.messageprocessing.model.Message;
import com.jpmc.rakesh.messageprocessing.model.MessageTypeThree;
import com.jpmc.rakesh.messageprocessing.model.MessageTypeTwo;
import com.jpmc.rakesh.messageprocessing.model.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class MessageTypeThreeTest {
	Message messageTypeThree, messageTypeTwo;
	private Map<String, Product> listProducts;

	@Before
	public void setUp() {
		messageTypeThree = new MessageTypeThree();
		messageTypeTwo = new MessageTypeTwo();

		listProducts = new LinkedHashMap<String, Product>();
	}

	@Test
	public void TestValidMessageTypeThree1() {
		String messageLine = "asdsds";
		boolean valid = messageTypeThree.ifValidMessage(messageLine);
		assertEquals(valid, false);
	}

	@Test
	public void TestValidMessageTypeThree2() {
		String messageLine = "apple at 10p";
		boolean valid = messageTypeThree.ifValidMessage(messageLine);
		assertEquals(valid, false);
	}

	@Test
	public void TestValidMessageTypeThree3() {
		String messageLine = "Multiply 20p apples";
		boolean valid = messageTypeThree.ifValidMessage(messageLine);
		assertEquals(valid, true);
	}

	@Test
	public void TestValidMessageTypeThree4() {
		String messageLine = "20 sales of oranges at 10p each";
		boolean valid = messageTypeThree.ifValidMessage(messageLine);
		assertEquals(valid, false);
	}

	@Test
	public void TestValidMessageTypeThree5() {
		String messageLine = "Subtract 20p oranges";
		boolean valid = messageTypeThree.ifValidMessage(messageLine);
		assertEquals(valid, true);
	}

	@Test
	public void TestValidMessageTypeThree6() {
		String messageLine = "Add 20p oranges";
		boolean valid = messageTypeThree.ifValidMessage(messageLine);
		assertEquals(valid, true);
	}

	@Test
	public void TestValidMessageTypeThree7() {
		String messageLine = " 20p oranges";
		boolean valid = messageTypeThree.ifValidMessage(messageLine);
		assertEquals(valid, false);
	}

	@Test
	public void TestMessageTypeThree1() {
		String messageLine = "10 sales of apples at 10p each";
		listProducts.put("apple", new Product());
		messageTypeTwo.Process(messageLine, listProducts, 1);
		messageLine = "Multiply 20p apples";
		messageTypeThree.Process(messageLine, listProducts, 2);

		int numberOfSales = listProducts.get("apple").getNumberOfSales();
		int totalValue = listProducts.get("apple").getTotalValue();
		assertEquals(numberOfSales, 10);
		assertEquals(totalValue, 2000);
	}

	@Test
	public void TestMessageTypeThree2() {
		String messageLine = "10 sales of apples at 10p each";
		listProducts.put("apple", new Product());
		messageTypeTwo.Process(messageLine, listProducts, 1);
		messageLine = "Add 30p apples";
		messageTypeThree.Process(messageLine, listProducts, 2);

		int numberOfSales = listProducts.get("apple").getNumberOfSales();
		int totalValue = listProducts.get("apple").getTotalValue();
		assertEquals(numberOfSales, 10);
		assertEquals(totalValue, 400);
	}

	@Test
	public void TestMessageTypeThree3() {
		String messageLine = "10 sales of apples at 10p each";
		listProducts.put("apple", new Product());
		messageTypeTwo.Process(messageLine, listProducts, 1);
		messageLine = "Subtract 5p apples";
		messageTypeThree.Process(messageLine, listProducts, 2);

		int numberOfSales = listProducts.get("apple").getNumberOfSales();
		int totalValue = listProducts.get("apple").getTotalValue();
		assertEquals(numberOfSales, 10);
		assertEquals(totalValue, 50);
	}
}
