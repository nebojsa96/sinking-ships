package sinkships.connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import sinkships.engine.Engine;


public abstract class Connection extends Thread{

	protected Socket socket;
	protected int port;
	protected DataOutputStream out;
	protected DataInputStream in;
	protected boolean waiting;
	protected boolean connected;
	protected boolean streamsReady;
	
	protected String type;
	protected Engine engine;
	
	public Connection(String type, int port, Engine engine){
		this.engine = engine;
		
		init(type, port);
	}
	
	public void init(String type, int port){
		this.type = type;
		this.port = port;
		connected = false;
		streamsReady = false;
		
		System.out.println(type);
	}
	
	
	protected void setupStreams() throws IOException{
		System.out.println("connection.setupStreams()");
		
		out = new DataOutputStream(socket.getOutputStream());
		out.flush();
		in = new DataInputStream(socket.getInputStream());
		streamsReady = true;
	}
	
	protected void send(String msg) throws IOException{
		out.writeUTF(msg);
		out.flush();
	}
	
	protected String receive() throws IOException{
		return in.readUTF();
	}
	
	public void sendMessage(String msg){
		try {
			send(msg);
		} catch (IOException e) {
			if(connected)
				closeConnection();
			//e.printStackTrace();
			System.out.println("Greska slanja!");
		}
	}
	
	public void whileConnected(){
		String msg;
		while(connected){
			try{
				msg = receive();
				if(msg!=null)
					engine.routeMessage(msg);
				
			}catch(IOException e){
				if(connected)
					closeConnection();
				//e.printStackTrace();
				System.out.println("Greska primanja!");
			}
		}
	}
	
	

	public String getType(){ //SERVER ili CLIENT
		return type;
	}
	
	public String getIp(){
		return socket.getInetAddress().getHostName();
	}
	
	public boolean areStreamsReady(){
		return streamsReady;
	}
	
	public boolean isConnected(){ //DA LI SU POVEZANI IGRACI
		return connected;
	}
		
	public abstract void closeConnection();

}
