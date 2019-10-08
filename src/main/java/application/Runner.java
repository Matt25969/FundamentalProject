package application;

import application.interoperability.Input;
import application.interoperability.TextInput;

public class Runner {
	public static void main(String[] args) {
		Input input = new TextInput();
		input.start();
	}
}
