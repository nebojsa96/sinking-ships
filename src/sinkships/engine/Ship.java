package sinkships.engine;

public class Ship {
	
	private int type;
	private boolean sinked;
	private Field[] array = new Field[4];
	
	public Ship(){
		sinked = false;
	}
	
	public Ship(int type){
		this.type = type;
		sinked = false;
	}
	
	public void setType(int type){
		this.type = type;
	}
	
	public void addToShip(Field f){
		int i;
		for(i=0; i<type && array[i]!=null; i++);
		if(i<type)
		{
			array[i] = f;
			//System.out.println("SHIP("+type+") "+f);
		}
	}
	
	public boolean checkSinked(){
		int d=0;
		for(int i=0; i<type; i++)
			if(array[i].isHitted())
				d++;
		
		if(d == type)
		{
			for(int i=0; i<type; i++)
				array[i].setDestroyed(true);
				sinked = true;
				return true;
		}
		else
			return false;
	}
	
	public Field[] getArray(){
		return array;
	}
	
	public int getType(){
		return type;
	}
	
	public boolean isSinked(){
		return sinked;
	}
	
	
	public void print(){
		System.out.print("\nSHIP("+type+"): ");
		for(int i=0; i<type; i++)
			System.out.print("  "+array[i]);
	}
}
