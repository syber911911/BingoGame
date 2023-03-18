package GAME;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;

public class Server {
	
	private String[] name;
	private int[] score;
	private int[] sortlist;
	private int count;
	private String string;
	
	public Server() {
		 name = new String[10];
		 score = new int[10];
		 count = 0;
		 Arrays.fill(score, 25565);
	}
	
	public void setList(int s,String n) {
		if( count < score.length-1) {
			name[count] = n;
			score[count] = s;
			count += 1;
			this.sort();
		}
		else {
			this.sort();
			if(score[count] > s) {
				name[count] = n;
				score[count] = s;
				this.sort();
			}
		}
	}
	
	public void sort() {
		String tempn;
		int temps;
		
	    for(int i = 0; i < count+1; i++) {
	        for(int j = 0 ; j < count - i ; j++) {
	            if(score[j] > score[j+1]) {
	                temps = score[j+1];
	                tempn = name[j+1];
	                score[j+1] = score[j];
	                name[j+1] = name[j];
	                score[j] = temps;
	                name[j] = tempn;
	            }
	        }
	    }

	}
	
	public void listToString() {
		string = "";
		for(int i=0; i < score.length; i++) {
			string += name[i] + " " ;
		}
		for(int i=0; i < score.length; i++) {
			string += Integer.toString(score[i])+ " ";
		}
	}
	public void run() {
        try {
        	ServerSocket serverSocket = new ServerSocket(2300);
            Socket socket = serverSocket.accept();       
            BufferedReader bufReader =
              new BufferedReader( new InputStreamReader( socket.getInputStream()));
            String n = bufReader.readLine();
            int s = Integer.parseInt(bufReader.readLine());
            BufferedWriter bufWriter = 
              new BufferedWriter( new OutputStreamWriter( socket.getOutputStream()));
            this.setList(s, n);
            this.listToString();
            //System.out.println("Server Running");
            bufWriter.write(string);
            bufWriter.newLine();
            bufWriter.flush();     
            socket.close();
            serverSocket.close();
            bufReader.close();
            bufWriter.close();
            this.run();
          }
          catch( Exception e ){
            e.printStackTrace();
          }
        
	}
    public static void main(String[] args ) {
    	Server server = new Server();
    	server.run();
    }
}
