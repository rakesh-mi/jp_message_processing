package com.jpmc.rakesh.messageprocessing.inout;

import java.util.Scanner;

public class ConsoleMessageReader implements MessageReader {
	private Scanner in;

	public ConsoleMessageReader() {
		in = new Scanner(System.in);

	}

	public String getNextMessageLine() {
		return in.nextLine();
	}

	public boolean hasNext() {
		return in.hasNext();
	}

	public void finish() {
		in.close();

	}
}
