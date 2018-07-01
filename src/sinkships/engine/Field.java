package sinkships.engine;

public class Field {
	public int x;
	public int y;
	
	private boolean part;
	private Ship ship;
	private boolean hitted;
	private boolean destroyed;
	
	
	public Field(int x, int y){
		this.x = x;
		this.y = y;
		
		setDefault();
	}
	
	public void setDefault(){
		part = false;
		ship = null;
		hitted = false;
		destroyed = false;
	}
	
	public void setPart(boolean part)
	{
		this.part = part;
	}
	
	public boolean isEmpty() {
		if(!part)
			return true;
		else
			return false;
	}

	
	public void setShip(Ship ship) {
		this.ship = ship;
	}
	
	public Ship getShip() {
		return ship;
	}
	
	public boolean isHitted() {
		return hitted;
	}


	public void setHitted(boolean hitted) {
		this.hitted = hitted;
	}


	public boolean isDestroyed() {
		return destroyed;
	}


	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}


	
	public String toString(){
		return "["+x+"]"+"["+y+"]"+" hitted(" + hitted+") destroyed("+destroyed+")";
	}

}
