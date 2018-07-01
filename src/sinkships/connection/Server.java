package sinkships.connection;

import java.io.*;
import java.net.*;

import sinkships.engine.Engine;


public class Server extends Connection{
	private ServerSocket server;
	private boolean loop = true;
		
	public Server(int port, Engine engine){
		super("server", port, engine);
		
	}
	
	private void waitForConnection() throws IOException{
		System.out.println("server.waitForConnection()");
		
		engine.connectionInfo("Ceka se protivnik da se poveze..."); 
		engine.connectionChatInfo("\nWaiting for someone to connect...\n"); 
		
		waiting = true;
		socket = server.accept();
		connected = true;
		waiting = false;
	}
	
	
	public void closeConnection(){
		System.out.println("server.closeConnection()");
		
		if(connected)
			engine.connectionChatInfo("\nClosing connection...\n");
		else
			engine.connectionChatInfo("\nClosing server...\n");
		connected = false;
		try {
			if(socket!=null)
				socket.close();
			loop = false;
			if(server!=null)
				server.close();
			
			engine.afterConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//NIT ZA SERVER
	public void run(){
		try{
			server = new ServerSocket(port,5);
	
			while(loop){
					if(!connected)
					{	
						waitForConnection();
						setupStreams();
						engine.waitingEnemy();
					}
					whileConnected();	
			}
		}catch(BindException e){
			engine.connectionChatInfo("\nPort already taken!\n");
			closeConnection();
			e.printStackTrace();
			
		}catch(IOException e){
			e.printStackTrace();
		}

	}
	
	
}
