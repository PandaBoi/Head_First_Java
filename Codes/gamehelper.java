import java.io.*;
public class gamehelper{

	public String getuserinput(String prompt){
		
		String inp=null;
		System.out.print(prompt+" ");
		try{
		
			BufferedReader is = new BufferedReader(
			new InputStreamReader(System.in));
			inp=is.readLine();
			if(inp.length()==0) return null;
			
		}catch(IOException e){
			System.out.println("IOException: "+ e);
		}
		return inp;
	}
}