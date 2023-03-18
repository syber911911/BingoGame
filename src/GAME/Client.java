package GAME;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {
	private static String[] rank;
   
	public String[] runClient(String name, String score) {
        try {
            Socket socket = new Socket("192.168.0.11",2300);
            BufferedWriter bufWriter = new BufferedWriter( new OutputStreamWriter( socket.getOutputStream()));
            bufWriter.write(name);
            bufWriter.newLine();
            bufWriter.flush();
            bufWriter.write(score);
            bufWriter.newLine();
            bufWriter.flush();
            BufferedReader bufReader =
              new BufferedReader( new InputStreamReader( socket.getInputStream()));
            String list = bufReader.readLine();
            rank = list.split(" ");
            socket.close();
            bufReader.close();
            bufWriter.close();
          }
          catch( Exception e ){
            e.printStackTrace();
          }
        return rank;
    }

}




