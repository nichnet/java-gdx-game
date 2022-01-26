package com.engine.ui;

public abstract class ButtonBase extends Label {

	public ButtonBase(String name) {
		super(name,"");//TODO 
	}
	public ButtonBase(String name, String value) {
		super(name, value);
	}

	private IClickListener clickListener;
	
	public void addClickListener(IClickListener clickListener) {
		this.clickListener = clickListener;
	}

	public void leftClick() {
		if(clickListener != null) {
			clickListener.leftClick();
		}
	}
}
