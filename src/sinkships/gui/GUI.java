package sinkships.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import sinkships.engine.Engine;
import sinkships.engine.Field;


public class GUI extends GameScreen {
	
	private Engine engine;
	private MyButton picked;
	
	public GUI(String title) {
		super(title);
		
		setMainScreen(); 
		setGamePanel();
		setChatPanel();
		
		engine = new Engine(this);
		engine.welcome();
		setListeners();
		
		pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	
	
	private void setListeners(){
		///////////////////////////////////// DUGME CONNECT /////////////////////////////////////
		btnConnect.addActionListener(new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) {
				if(btnConnect.getText().equals("Connect"))
				{	
					makeConnection(); //PRAVI SE KONEKCIJA
				}
				else
				{
					String[] options = {"Da","Ne"}; //KAD SE PRITISNE X
					
					int confirm = JOptionPane.showOptionDialog(null,
							"Da li ste sigurni da zelite da prekinete konekciju?", 
				            "Potvrda diskonekcije", 0, 
				            JOptionPane.QUESTION_MESSAGE, null, options, null); 
				        if (confirm == 0) 
				        {
				        	engine.closeConnection();
				        }
				}
				
			}
		});
		
		/////////////////////////////////////// DUGME PLAY  //////////////////////////////////////
		btnPlay.addActionListener(new ActionListener() { //ZAPOCINJE SE IGRA
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!engine.isPlaying())
				{
					if(engine.checkTable())
					{
						//System.out.println("USPESNO POSTAVLJENI BRODOVI");
						engine.setMeReady(true);
						if(engine.isEnemyReady())
						{	
							engine.startPlay();
						}
						else
						{
							myTable.setEnabled(false);
							enableBtnPlay(false);
							showInfo("Ceka se da protivnik postavi brodove...");
						}
						
					}
					
				}
			}
		});
		
		///////////////////////////////// MY TABLE ////////////////////////////////////
		myTable.addListener(new ActionListener() { //DUGMICI NA MOJOJ TABLI			
					MyButton btn;
					@Override
					public void actionPerformed(ActionEvent e) {
						btn = (MyButton) e.getSource();
						if(btn.isClickable())
						{
							engine.toggleClick(btn.x, btn.y);
							btn.toggleColor(Color.BLUE, null);
						}
					}
				});
		
		//////////////////////////////////// ENEMY TABLE /////////////////////////////////
		enemyTable.addListener(new ActionListener() { //DUGMICI NA PROTIVNIKOVOJ TABLI			
					MyButton btn;
					@Override
					public void actionPerformed(ActionEvent e) {
						btn = (MyButton) e.getSource();
						if(btn.isClickable() && engine.isMyTurn())
						{
							if(picked!=null) //AKO SE PREDOMSILIM
								picked.toggleColor(Color.YELLOW, null);
							picked = btn;
							picked.toggleColor(Color.YELLOW, null);
						}	
					}
				});
		
		/////////////////////////////////// DUGME FIRE ///////////////////////////////////
		btnFire.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(picked!=null)
				{	
					picked.setClickable(false);
					engine.makeMove(picked.x, picked.y);
					picked = null;
					
					//enemyTable.print();
				}
				else
					showInfo("Izaberite polje koje zelite da gadjate.");
			}
		});
		
		////////////////////////////////// MSG INPUT FIELD ///////////////////////////////////
		msgField.addActionListener(new ActionListener() { //SLANJE CHAT PORUKE
			@Override
			
			public void actionPerformed(ActionEvent e) {
				String msg = e.getActionCommand();
				showMessage("\n"+engine.getMe()+": "+msg, senderColor);
				engine.sendChat(msg);
				msgField.setText("");		
			}
		});
		
		////////////////////////////////// X NA PROZORU ///////////////////////////////////
		WindowListener windowClosingListener = new WindowAdapter() { 
			
			String[] options = {"Da","Ne"}; //KAD SE PRITISNE X
			
			public void windowClosing(WindowEvent e) {
		        int confirm = JOptionPane.showOptionDialog(
		            null, "Da li ste sigurni da zelite da zatvorite aplikaciju?", 
		             "Potvrda izlaza", 0, 
		             JOptionPane.QUESTION_MESSAGE, null, options, null); 
		        if (confirm == 0) 
		        {
		           System.exit(0);
		        }
		    }
		};
		addWindowListener(windowClosingListener);
		
	}
	
	private void makeConnection(){ //PRAVLJENJE KONEKCIJE 
		System.out.println("gui.makeConnection()");
		
		if(rbServer.isSelected() || rbClient.isSelected())
		{
			String name = nameField.getText();
			String type;
			String ip;
			
			if(rbServer.isSelected())
			{
				type = "server";
				ip = ipField.getText();
				if(name.equals(""))
					name = "P1";
			}
			else
			{
				type = "client";
				ip = null;
				if(name.equals(""))
					name = "P2";
			}	
			engine.setMe(name);
			engine.makeConnection(type, portField.getText(), ip);
			
			showMessage("\n*************\n"+name+" connected!\n", sysColor);
			btnConnect.setText("Disconnect");
		
		}
		else
		{
			showInfo("Pick one Server or Client!");
			
		}
	}
	
	
	////////////////////////////////// STANJA GUI-ja ///////////////////////////////
	public void welcome(){
		System.out.println("gui.welcome()");
		
		showInfo("Dobrodosli u SINK SHIPS!");
		
		myTable.setEnabled(false);
		enemyTable.setEnabled(false);
		
		refresh();
	}
	
	public void waitingEnemy(){
		System.out.println("gui.waitingEnemy()");
		
		showInfo("Ceka se protivnik...");
		
		myTable.setEnabled(false);
		enemyTable.setEnabled(false);
		enableBtnFire(false);
		
		refresh(); //ZBOG CLEAR TABLES
	}
	
	public void prepareGame(){  //PRIPREMA ZA IGRU
		System.out.println("gui.prepareGame()");
		
		showInfo("Rasporedite brodove HORIZONTALNO:  1x[_][_][_][_]  2x[_][_][_]  3x[_][_]  i  4x[_]");
		
		myTable.setEnabled(true);
		myTable.setClickable(true);
		enemyTable.setClickable(true);
		enableBtnPlay(true);
		ableToType(true);

		//refresh();
	}
	
	public void startPlay(){
		System.out.println("gui.startPlay()");
		
		myTable.setEnabled(false);
		enableBtnPlay(false);
		changeTurn();
	}
	
	public void afterConnection(){ //POSPREMANJE POSLE GASENJA KONEKCIJE
		System.out.println("gui.afterConnection()");
		
		JOptionPane.showMessageDialog(this, "Konekcija prekinuta.\n", "Greska", JOptionPane.ERROR_MESSAGE);
		ableToType(false);
		myTable.setEnabled(false);
		enableBtnConnect(true);
		enableBtnPlay(false);
		enableBtnFire(false);
	}
	
	public void endGame(String winner){
		System.out.println("gui.endGame()");
		
		String[] options = {"Da","Ne"};
		
		int res = JOptionPane.showOptionDialog(this,"Kraj igre.\nPobednik je "+winner+". Da li zelite novu?","KRAJ", 0, JOptionPane.QUESTION_MESSAGE, null, options, null);
		if(res == 0)
		{
			engine.waitingEnemy();
		}
		else
			System.exit(1);
	}
	


	public void refresh(){ //OSVEZAVANJE GUI-JA
		System.out.println("gui.refresh()");
		
		Field[][] enMyTable = engine.getMyTable();
		Field[][] enEnemyTable = engine.getEnemyTable();
		
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
			{ 
				//MOJA TABLA
				if(enMyTable[i][j].isEmpty())
				{
					if(enMyTable[i][j].isHitted())
						myTable.setFieldColor(i, j, Color.CYAN);
					else
						myTable.setFieldColor(i, j, null);
				}
				else
				{
					if(!enMyTable[i][j].isHitted())
						myTable.setFieldColor(i, j, Color.BLUE);
					else
					{
						if(!enMyTable[i][j].isDestroyed())
							myTable.setFieldColor(i, j, Color.RED);
						else
							myTable.setFieldColor(i, j, Color.BLACK);
					}
						
				}
				
				//PROTIVNIKOVA TABLA
				if(enEnemyTable[i][j].isEmpty())
				{
					if(enEnemyTable[i][j].isHitted())
						enemyTable.setFieldColor(i, j, Color.CYAN);
					else
						enemyTable.setFieldColor(i, j, null);
				}
				else
				{
					if(!enEnemyTable[i][j].isHitted())
						enemyTable.setFieldColor(i, j, Color.BLUE);
					else
					{
						if(!enEnemyTable[i][j].isDestroyed())
							enemyTable.setFieldColor(i, j, Color.RED);
						else
							enemyTable.setFieldColor(i, j, Color.BLACK);
					}
						
				}
				
			}
				
			if(engine.isPlaying() && !engine.isMyTurn()) //OBAVESTENJE O KRAJU IGRE
			{
				if(engine.isEnd())
					endGame(engine.getEnemy());	
			}
	}

	
	

	// POMOCNE
	public void changeTurn(){
		System.out.println("gui.changeTurn()");
		if(engine.isMyTurn())
		{
			enemyTable.setEnabled(true);
			enableBtnFire(true);
		}
		else
		{
			enemyTable.setEnabled(false);
			enableBtnFire(false);
		}
		showInfo("Na potezu je: "+engine.getOnMove());
	}
	
	public void showInfo(String msg){ //PORUKA U INFO DELU
		lbInfo.setText(msg);
	}
	
	public void showMessage(final String msg, Color color){ //PORUKA U CHAT-U
				StyleContext sc = StyleContext.getDefaultStyleContext();
		        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, color);

		        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
		        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

		        int len = chatWindow.getDocument().getLength();
		        chatWindow.setCaretPosition(len);
		        chatWindow.setCharacterAttributes(aset, false);
		        chatWindow.replaceSelection(msg);
	}
	
	public void ableToType(final boolean perm) { //DA LI MOZE DA SE KUCA NOVA PORUKA
		msgField.setEditable(perm);
	}

	public void enableBtnPlay(boolean enable){
		if(enable){
			btnPlay.setEnabled(true);
			btnPlay.setBackground(Color.RED);
			btnPlay.setForeground(Color.WHITE);
		}
		else
		{
			btnPlay.setEnabled(false);
			btnPlay.setBackground(null);
			btnPlay.setForeground(null);

		}
	}
	
	public void enableBtnFire(boolean enable){
		if(enable){
			btnFire.setEnabled(true);
			btnFire.setBackground(Color.RED);
			btnFire.setForeground(Color.WHITE);
		}
		else
		{
			btnFire.setEnabled(false);
			btnFire.setBackground(null);
			btnFire.setForeground(null);
		}
	}
		
	public void enableBtnConnect(boolean enable){
		if(enable){
			btnConnect.setText("Connect");
			//btnConnect.setBackground(Color.yellow);
		}
		else
		{
			btnConnect.setText("Disconnect");
			//btnConnect.setBackground(null);
		}
	}
	
	public void updateName(String name){
		nameField.setText(name);
	}
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() { //Event Dispatch Thread
			public void run() {
				try {
					new GUI("Sink Ships!");
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
