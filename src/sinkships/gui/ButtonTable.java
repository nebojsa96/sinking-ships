package sinkships.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class ButtonTable {
	private MyButton[][] table = new MyButton[10][10];
	private boolean enabled;
	private boolean clickable;
	
	
	public ButtonTable(JPanel panel,int btnWidth, int btnHeight){
		enabled = false;
		clickable = true;
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				table[i][j] = new MyButton(i,j); 
				table[i][j].setPreferredSize(new Dimension(btnWidth, btnHeight));
				table[i][j].setEnabled(false);
				panel.add(table[i][j]);
			}
		}
	}
	
	public void addListener(ActionListener listener){
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				table[i][j].addActionListener(listener);
	}
	
	public void setEnabled(boolean enable){ //ENABLE ILI DISABLE TABLU
		
		if(enable == enabled) //AKO JE VEC TO STO SE TRAZI
			return;
		
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++) 
				table[i][j].setEnabled(enable);
		enabled = enable;		
	}
	
	public void setClickable(boolean click){ //TABLA MOZE/NE MOZE DA SE KLIKCE  
		
		for(int i=0; i<10; i++) 
			for(int j=0; j<10; j++)
				table[i][j].setClickable(click); 
	}
	
	public void setFieldColor(int i, int j, Color color){
		table[i][j].setBackground(color);
	}
	
	
	public boolean isEnabled(){
		return enabled;
	}
	
	public boolean isClickable(){
		return clickable;
	}
	
	public void print(){
		System.out.println("\n********************");
		for(int i=0; i<10; i++) 
		{
			for(int j=0; j<10; j++)
				if(table[i][j].isClickable())
					System.out.print("_");
				else
					System.out.print("X");
			System.out.println();
		}
	}
}
