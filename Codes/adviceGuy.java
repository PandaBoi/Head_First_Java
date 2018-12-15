import java.io.*;
import java.net.*;

public class adviceGuy{
	String[] adviceList={"sone se pehle lights off karna","padh le warna sem back lagega",
	"i think therefore i am","kabhi kabhi lagta hai apun hi bhagwaan hai","lol@u","loss aadmi hai tu"};
	
	public void go(){
	try{
		ServerSocket serverSock = new ServerSocket(4242);
		
		while(true){
			Socket sock = serverSock.accept();
			PrintWriter writer = new PrintWriter(sock.getOutputStream());
			String advice=getAdvice();
			writer.println(advice);
			writer.close();
			System.out.println(advice);
		}
	}catch(IOException e){e.printStackTrace();}
		
	}
	
	private String getAdvice(){
		int rand =(int) (Math.random()* adviceList.length);
		return adviceList[rand];
	}
	
	public static void main(String[] args){
		adviceGuy gui = new adviceGuy();
		gui.go();
	}
	
}