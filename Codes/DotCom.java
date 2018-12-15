import java.util.*;
public class DotCom{
	private ArrayList<String> location ;
	private String name;
	
	public void setname(String naam){
		name=naam;
	}
	public void SetLocationCells(ArrayList<String> loc){
		location=loc;
	}
	
	public String checkyourself(String Userinp){
		String result="miss";
		//System.out.println(Userinp);
		int idx=location.indexOf(Userinp);
		//System.out.println(idx);
		if(idx>=0){
			location.remove(idx);
			
			if(location.isEmpty()){
				result="kill";
				System.out.println("ouch! YOu sunk "+ name);
				
			}
			else{
				result="hit";
				
			}
				
		}
		//System.out.println("the return is giving "+result);
		
		return result;
	}
}