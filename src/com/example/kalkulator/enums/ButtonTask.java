package com.example.kalkulator.enums;

public enum ButtonTask {
	CANCEL("C"),
	ONE("1"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9"), ZERO("0"),
	DIVIDE("/"), MULTIPLY("*"), SUBTRACT("-"), ADD("+"), DECIMAL(","), EQUALS("=");

	private String label;

	ButtonTask(String buttonL) {
		label = buttonL;
	}

	public String getLabel() {
		return label;
	}
}
