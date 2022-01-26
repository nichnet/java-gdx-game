package com.engine.ui;

public class Label extends Component {

	private String value;
	
	public Label(String name, String value) {
		super(name, 0,0,0,0);//todo determine size of label
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
