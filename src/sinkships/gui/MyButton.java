package sinkships.gui;

import java.awt.Color;

import javax.swing.JButton;

public class MyButton extends JButton {
	
	public int x;
	public int y;
	private boolean clickable;
	
	public MyButton(int x, int y){
		this.x = x;
		this.y = y;
		clickable = true;
	}

	public boolean isClickable() {
		return clickable;
	}

	public void setClickable(boolean clickable) {
		this.clickable = clickable;
	}
	
	public void toggleColor(Color c1, Color c2){
		if(getBackground() == c1)
			setBackground(c2);
		else
			setBackground(c1);
	}
	
}
