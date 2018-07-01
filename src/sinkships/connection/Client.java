package sinkships.connection;

import java.net.*;
import java.io.*;

import sinkships.engine.Engine;

public class Client extends Connection{
	private String serverIP;
	
	public Client(String host,int port, Engine engine){
		super("client", port, engine);
		serverIP = host;
	}	
	
	
	private void connectToServer() throws IOException{
		System.out.println("client.connectToServer()");
		
		engine.connectionChatInfo("Attempting connection...\n"); 
		try{
			socket = new Socket(InetAddress.getByName(serverIP),port);
			connected = true;
		}catch(ConnectException e){
			//engine.connectionInfo("Zeljeni server nije dostupan...\n");
			engine.connectionChatInfo("There is no server to connect...\n"); 
			closeConnection();
		}
	}
	
	public void closeConnection(){
		System.out.println("client.closeConnection()");
		
		engine.connectionChatInfo("\nClosing connection...\n");
		try {
			if(socket!=null)
				socket.close();
			
			connected = false;
			engine.afterConnection(); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//NIT ZA KLIJENTA
	public void run(){
		try{
			connectToServer();
			if(socket!=null)
			{	
				setupStreams();
				engine.waitingEnemy();
				whileConnected();
			}
			
		}catch(IOException e){
			e.printStackTrace();
		}
		//System.out.println("NA KRAJU KLIJENTA");
		//if(connected)
			//closeConnection();
	}
	
}
