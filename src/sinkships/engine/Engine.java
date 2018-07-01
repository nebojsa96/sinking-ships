package sinkships.engine;

import sinkships.connection.*;
import sinkships.gui.GUI;

public class Engine{
	
	private GUI gui;
	private Connection connection;
	private Field[][] myTable = new Field[10][10];
	private Field[][] enemyTable = new Field[10][10];
	private Ship[] ships = new Ship[10];
	
	private String me;
	private String enemy;
	private boolean meReady;
	private boolean enemyReady;
	private boolean playing;
	private String onMove;

	
	public Engine(GUI gui){
		this.gui = gui;
		
		for(int i=0; i<10; i++)
			for(int j=0; j<10; j++)
			{
				myTable[i][j] = new Field(i,j); 
				enemyTable[i][j] = new Field(i,j); 
			}
		
	}
	
	public void routeMessage(String msg){
		System.out.println("engine.routeMessage()");
		
		if(msg.startsWith("#chat#"))
			gui.showMessage("\n"+enemy+": "+trimMessage(msg), gui.receiverColor);
		
		else if(msg.startsWith("#move#"))
			submitMove(trimMessage(msg));
		
		else if(msg.startsWith("#dmginfo#"))
			afterMove(trimMessage(msg));
		
		else if(msg.startsWith("#meeting#"))
			meeting(trimMessage(msg));
		
		else if(msg.startsWith("#ready#"))
			enemyReadyMessage();
		
		else if(msg.startsWith("#end#"))
			gui.endGame(me);
	}

	
	
	public void welcome(){
		System.out.println("engine.welcome()");
		
		clearTables();
		// S //
		myTable[0][0].setPart(true);
		myTable[0][0].setHitted(true);
		myTable[0][0].setDestroyed(true);
		myTable[0][1].setPart(true);
		myTable[0][1].setHitted(true);
		myTable[0][1].setDestroyed(true);
		myTable[0][2].setPart(true);
		myTable[0][2].setHitted(true);
		myTable[0][2].setDestroyed(true);
		myTable[1][0].setPart(true);
		myTable[1][0].setHitted(true);
		myTable[1][0].setDestroyed(true);
		myTable[2][0].setPart(true);
		myTable[2][0].setHitted(true);
		myTable[2][0].setDestroyed(true);
		myTable[2][1].setPart(true);
		myTable[2][1].setHitted(true);
		myTable[2][1].setDestroyed(true);
		myTable[2][2].setPart(true);
		myTable[2][2].setHitted(true);
		myTable[2][2].setDestroyed(true);
		myTable[3][2].setPart(true);
		myTable[3][2].setHitted(true);
		myTable[3][2].setDestroyed(true);
		myTable[4][0].setPart(true);
		myTable[4][0].setHitted(true);
		myTable[4][0].setDestroyed(true);
		myTable[4][1].setPart(true);
		myTable[4][1].setHitted(true);
		myTable[4][1].setDestroyed(true);
		myTable[4][2].setPart(true);
		myTable[4][2].setHitted(true);
		myTable[4][2].setDestroyed(true);
		
		// i //
		myTable[0][4].setPart(true);
		myTable[0][4].setHitted(true);
		myTable[2][4].setPart(true);
		myTable[2][4].setHitted(true);
		myTable[2][4].setDestroyed(true);
		myTable[3][4].setPart(true);
		myTable[3][4].setHitted(true);
		myTable[3][4].setDestroyed(true);
		myTable[4][4].setPart(true);
		myTable[4][4].setHitted(true);
		myTable[4][4].setDestroyed(true);
		
		// N //
		myTable[0][6].setPart(true);
		myTable[0][6].setHitted(true);
		myTable[0][6].setDestroyed(true);
		myTable[1][6].setPart(true);
		myTable[1][6].setHitted(true);
		myTable[1][6].setDestroyed(true);
		myTable[2][6].setPart(true);
		myTable[2][6].setHitted(true);
		myTable[2][6].setDestroyed(true);
		myTable[3][6].setPart(true);
		myTable[3][6].setHitted(true);
		myTable[3][6].setDestroyed(true);
		myTable[4][6].setPart(true);
		myTable[4][6].setHitted(true);
		myTable[4][6].setDestroyed(true);
		myTable[2][7].setPart(true);
		myTable[2][7].setHitted(true);
		myTable[2][7].setDestroyed(true);
		myTable[3][8].setPart(true);
		myTable[3][8].setHitted(true);
		myTable[3][8].setDestroyed(true);
		myTable[0][9].setPart(true);
		myTable[0][9].setHitted(true);
		myTable[0][9].setDestroyed(true);
		myTable[1][9].setPart(true);
		myTable[1][9].setHitted(true);
		myTable[1][9].setDestroyed(true);
		myTable[2][9].setPart(true);
		myTable[2][9].setHitted(true);
		myTable[2][9].setDestroyed(true);
		myTable[3][9].setPart(true);
		myTable[3][9].setHitted(true);
		myTable[3][9].setDestroyed(true);
		myTable[4][9].setPart(true);
		myTable[4][9].setHitted(true);
		myTable[4][9].setDestroyed(true);
		
		// K //
		enemyTable[0][0].setPart(true);
		enemyTable[0][0].setHitted(true);
		enemyTable[0][0].setDestroyed(true);
		enemyTable[1][0].setPart(true);
		enemyTable[1][0].setHitted(true);
		enemyTable[1][0].setDestroyed(true);
		enemyTable[2][0].setPart(true);
		enemyTable[2][0].setHitted(true);
		enemyTable[2][0].setDestroyed(true);
		enemyTable[3][0].setPart(true);
		enemyTable[3][0].setHitted(true);
		enemyTable[3][0].setDestroyed(true);
		enemyTable[4][0].setPart(true);
		enemyTable[4][0].setHitted(true);
		enemyTable[4][0].setDestroyed(true);
		enemyTable[2][1].setPart(true);
		enemyTable[2][1].setHitted(true);
		enemyTable[2][1].setDestroyed(true);
		enemyTable[1][2].setPart(true);
		enemyTable[1][2].setHitted(true);
		enemyTable[1][2].setDestroyed(true);
		enemyTable[0][3].setPart(true);
		enemyTable[0][3].setHitted(true);
		enemyTable[0][3].setDestroyed(true);
		enemyTable[3][2].setPart(true);
		enemyTable[3][2].setHitted(true);
		enemyTable[3][2].setDestroyed(true);
		enemyTable[4][3].setPart(true);
		enemyTable[4][3].setHitted(true);
		enemyTable[4][3].setDestroyed(true);
		
		// S //
		myTable[5][3].setPart(true);
		myTable[5][4].setPart(true);
		myTable[5][5].setPart(true);
		myTable[6][3].setPart(true);
		myTable[7][3].setPart(true);
		myTable[7][4].setPart(true);
		myTable[7][5].setPart(true);
		myTable[8][5].setPart(true);
		myTable[9][3].setPart(true);
		myTable[9][4].setPart(true);
		myTable[9][5].setPart(true);
		
		// h //
		myTable[5][7].setPart(true);
		myTable[6][7].setPart(true);
		myTable[7][7].setPart(true);
		myTable[8][7].setPart(true);
		myTable[9][7].setPart(true);
		myTable[7][8].setPart(true);
		myTable[7][9].setPart(true);
		myTable[8][9].setPart(true);
		myTable[8][9].setPart(true);
		myTable[9][9].setPart(true);
		
		// i //
		enemyTable[5][1].setPart(true);
		enemyTable[5][1].setHitted(true);
		enemyTable[7][1].setPart(true);
		enemyTable[8][1].setPart(true);
		enemyTable[9][1].setPart(true);
		
		// P //
		
		enemyTable[6][3].setPart(true);
		enemyTable[7][3].setPart(true);
		enemyTable[8][3].setPart(true);
		enemyTable[9][3].setPart(true);
		enemyTable[6][4].setPart(true);
		enemyTable[6][5].setPart(true);
		enemyTable[7][5].setPart(true);
		enemyTable[8][4].setPart(true);
		enemyTable[8][5].setPart(true);
		
		// S //
		enemyTable[5][7].setPart(true);
		enemyTable[5][8].setPart(true);
		enemyTable[5][9].setPart(true);
		enemyTable[6][7].setPart(true);
		enemyTable[7][7].setPart(true);
		enemyTable[7][8].setPart(true);
		enemyTable[7][9].setPart(true);
		enemyTable[8][9].setPart(true);
		enemyTable[9][7].setPart(true);
		enemyTable[9][8].setPart(true);
		enemyTable[9][9].setPart(true);
		
		gui.welcome();
	}

	public void makeConnection(String type, String port, String ip){
		System.out.println("engine.makeConnection()");
		
		if(type.equals("server"))
		{
			connection = new Server(Integer.parseInt(port), this);
		}
		else
		{
			connection = new Client(ip, Integer.parseInt(port), this);
		}
		connection.start();
	}

	public void waitingEnemy(){ //POCETNE VREDNOSTI, CEKA SE PROTIVNIK
		System.out.println("engine.waitingEnemy()");
		
		meReady = false;
		enemyReady = false;
		playing = false;
		
		connection.sendMessage("#meeting#"+me);
		clearTables();
		gui.waitingEnemy();
	}
	
	public void meeting(String enemy){ //RAZMENJIVANJE IMENA IGRACA
		System.out.println("engine.meeting()");
		
		this.enemy = enemy;	
		if(me.equals(enemy))
		{
			if(connection.getType().equals("server"))
			{
				me = me +"(P1)";
				this.enemy = this.enemy +"(P2)";
			}
			else
			{
				me = me +"(P2)";
				this.enemy = this.enemy +"(P1)";
			}
			gui.updateName(me);
			
		}
		
		gui.showMessage("\nConnected to: "+enemy, gui.sysColor);
		gui.prepareGame();
	}
	
	public boolean checkTable() { //DA LI SU PRAVILNO POSTAVLJENI BRODOVI
		System.out.println("engine.checkTable()");
		
		int n = 0;
		int type = 0;
		int[] ships = new int[4]; //i-ti clan niza je broj postavljenih brodova tipa i+1 
		boolean inShip = false;
		
		for(int i=0; i<10; i++)
		{	
			for(int j=0; j<10; j++)
			{				
				if(!myTable[i][j].isEmpty()) //KLIKNUTO
				{
					if(i<9 && !myTable[i+1][j].isEmpty())
					{
						gui.showInfo("Brodovi moraju biti HORIZONTALNO postavljeni u razmaku od bar jednog polja!");
						return false;
					}
					
					if(!inShip) // PRVA KLIKNUTA - ZAPOCNI BROD
					{
						type = 1;
						n++;
						inShip = true;
					}
					else
					{
						type++;
					}
					
					if(j==9) // POSLEDNJA KOLONA - ZAVRSI BROD
					{
						ships[type-1]++;
						inShip = false;
					}
					
				}
								//NIJE KLIKNUTO
				else if(inShip) //PRVA PRAZNA VAN BRODA - ZAVRSI BROD
				{
					ships[type-1]++;
					inShip = false;
				}
				
				if(type>4)
				{
					gui.showInfo("Brodovi ne smeju biti veci od [_][_][_][_]");
					return false;
				}
			}	
			
//			System.out.print("\nRED"+i);
//			for(int k=0; k<4; k++)
//				System.out.print("    ["+(k+1)+"]:"+ships[k]);	
		}
			
		if(n!=10)	
		{
			gui.showInfo("Broj brodova je ("+n+"), a potrebno je (10)!");
			return false;
		}
		
		for(int i=0; i<4; i++)
		 if(ships[i]!= 4-i)
		 {
			 gui.showInfo("Brodova duzine ["+(i+1)+"] mora biti TACNO ("+(4-i)+")");
			 return false;
		 }
		
		return true;

	}

	public void makeShips(){ //PREPOZNAJU SE I PRAVE BRODOVI SA MOJE TABLE
		System.out.println("engine.makeShips()");
		
		int n = 0;
		boolean inShip = false;
		int type = 0;
		
		for(int i=0; i<10; i++)
		{
			inShip = false;
			for(int j=0; j<10; j++)
				if(!myTable[i][j].isEmpty())
				{
					if(inShip == false)
					{
						type = 1;
						ships[n] = new Ship(type);
						ships[n].addToShip(myTable[i][j]);
						myTable[i][j].setShip(ships[n]);
						inShip = true;
						n++;
					}
					else
					{
						type++;
						ships[n-1].setType(type);
						ships[n-1].addToShip(myTable[i][j]);
						myTable[i][j].setShip(ships[n-1]);
					}
				}
				else
				{
					inShip = false;
				}
		}
		//for(int i=0; i<10; i++)
			//ships[i].print();
	}
	
	public void enemyReadyMessage(){ //PROTIVNIKOVA PORUKA DA JE SPREMAN
		System.out.println("engine.enemyReadyMessage()");
		
		enemyReady = true;
		if(!isPlaying())
		{
			if(isMeReady())
				startPlay();
			else
				gui.showInfo("Protivnik je spreman...");
		}
	}
	
	public void startPlay(){ //ZAPOCINJE SE IGRA		
		System.out.println("engine.startPlay()");
		
		playing = true;
		makeShips(); 
		
		if(connection.getType().equals("server"))
			onMove = me;
		else
			onMove = enemy;
		
		gui.startPlay();
	}
	
	public void makeMove(int x, int y) { //MOJ POTEZ SE SALJE PORUKOM PROTIVNIKU
		System.out.println("engine.makeMove()");
		
		String move = "#move#" + x + "-" +y;
		connection.sendMessage(move);
	}
	
	public void submitMove(String msg){ //PRIHVATA SE PROTIVNIKOV POTEZ (AZURIRANJE MOJE TABLE, IZVESTAJ ZA PROTIVNIKA)
		System.out.println("engine.submitMove()");
		
		String[] args = msg.split("-"); 
		int x = (int)args[0].charAt(0)-'0';
		int y = (int)args[1].charAt(0)-'0';
		
		boolean sinked = false;
		if(myTable[x][y].isEmpty()) //PROMASAJ
		{
			myTable[x][y].setHitted(true);
			changeTurn(); //NA POTEZU SAM JA(OBAVESTENJE KOD MENE)
		}
		else //POGODAK
		{
			myTable[x][y].setHitted(true);
			sinked = myTable[x][y].getShip().checkSinked(); //DA LI JE BROD POTOPLJEN?
		}
		
		
		if(!sinked)// NIJE POTOPLJEN, IZVESTAJ O JEDNOM POLJU
			connection.sendMessage("#dmginfo#"+x+"-"+y+"-"+myTable[x][y].isEmpty()+"-"+myTable[x][y].isDestroyed());
		else // POTOPLJEN, IZVESTAJ O CELOM BRODU
		{
			Ship ship = myTable[x][y].getShip();
			Field field;
			for(int i=0; i< ship.getType(); i++)
			{
				field = ship.getArray()[i];
				connection.sendMessage("#dmginfo#"+field.x+"-"+field.y+"-"+field.isEmpty()+"-"+field.isDestroyed());
			}
		}
		
		gui.refresh();	
	}
	
	public void afterMove(String msg) { //ODGOVOR PROTIVNIKA NA MOJ POTEZ (STANJE PROTIVNIKOVE TABLE)
		System.out.println("engine.afterMove()");
		
		String[] args = msg.split("-");
		int x = (int)args[0].charAt(0)-'0';
		int y = (int)args[1].charAt(0)-'0';
		boolean empty = Boolean.parseBoolean(args[2]);
		boolean destroyed = Boolean.parseBoolean(args[3]);
		
		//System.out.println(msg+" = "+x+y+empty+destroyed);
		
		enemyTable[x][y].setHitted(true);
		if(empty) // PROMASAJ
			changeTurn(); //NA POTEZU DRUGI IGRAC(OBAVESTENJE KOD MENE)
		else // POGODAK
		{
			enemyTable[x][y].setPart(true);
			if(destroyed)
				enemyTable[x][y].setDestroyed(true);
		}	
		gui.refresh();
	}
	
	public void changeTurn(){ //PROMENA KO JE NA POTEZU
		System.out.println("engine.changeTurn()");
		
		if(onMove.equals(me))
			onMove = enemy;
		else
			onMove = me;
		
		gui.changeTurn();
	}
	
	public void afterConnection(){
		System.out.println("engine.afterConnection()");
		
		gui.afterConnection();
		welcome();
	}
	


	
	// POMOCNE
	public void clearTables(){
		System.out.println("engine.clearTables()");
		
		for(int i=0; i<10; i++)
			for(int j=0; j<10; j++)
			{
				myTable[i][j].setDefault(); 
				enemyTable[i][j].setDefault();  
			}
	}
	
	protected String trimMessage(String msg){ //SKIDA ZAGLAVLJE PORUKE
		return msg.substring(msg.indexOf('#',1)+1);
	}
	
	public void toggleClick(int x,int y){ //SELEKTOVANJE I DESELEKTOVANJE POLJA PRI POSTAVLJANJU BRODOVA
		if(myTable[x][y].isEmpty())
		{
			myTable[x][y].setPart(true);
		}
		else
		{
			myTable[x][y].setPart(false);
		}	
	}
	
	public void sendChat(String msg){
		connection.sendMessage("#chat#"+msg);
	}
	
	public void connectionInfo(String info){
		gui.showInfo(info);
	}
	
	public void connectionChatInfo(String info){
		gui.showMessage(info, gui.sysColor);
	}
	
	public void closeConnection(){
		System.out.println("DUGMETEM");
		connection.closeConnection();
	}
	
	
	// GETTERS & SETTERS	
	public Field[][] getMyTable(){
		return myTable;
	}

	public Field[][] getEnemyTable(){
		return enemyTable;
	}
	
	public String getMe(){
		return me;
	}
	
	public void setMe(String name){
		me = name;
	}
	
	public String getEnemy(){
		return enemy;
	}
	
	public boolean isMeReady(){
		return meReady;
	}
	
	public void setMeReady(boolean ready){
		meReady = ready;
		if(ready)
			connection.sendMessage("#ready#");
	}
	
	public boolean isEnemyReady(){
		return enemyReady;
	}
	
	public void setEnemyReady(boolean ready){
		enemyReady = ready;
	}
	
	public boolean isPlaying(){
		return playing;
	}
	
	public void setPlaying(boolean playing){
		this.playing = playing;
	}
	
	public String getOnMove(){
		return onMove;
	}
	
	public boolean isMyTurn(){
		if(onMove.equals(me))
			return true;
		else
			return false;
	}
	
	public boolean isEnd(){	// PROVERA DA LI SU MENI SVI BRODOVI POTOPLJENI
		int s=0;
		for(int i=0; i<10; i++)
			if(ships[i].isSinked())
				s++;
		if(s==10)
		{
			connection.sendMessage("#end#");
			return true;
		}
		else
			return false;
	}
	
}
