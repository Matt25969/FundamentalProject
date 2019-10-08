package application.models;

public class Item {
	
	private String name;
	private int value;

	public Item(String itemName, int itemValue) {
		
		this.name = itemName;
		this.value = itemValue;
		
	}

	public Item() {
		// TODO Auto-generated constructor stub
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
