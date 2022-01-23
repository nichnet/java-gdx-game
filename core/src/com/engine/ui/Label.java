package com.engine.ui;

public class Label extends Component {

	private String value;
	
	public Label(String name, String value) {
		super(name);
		this.value = value;
	}
	public Label(String name) {
		super(name);
	}
	
	public String getValue() {
		return value;
	}
}
