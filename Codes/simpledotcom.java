public class simpledotcom{
	
	int[] locationcells;
	int numofhits=0;
	
	public void setlocation(int[] locs){
	
		locationcells=locs;
	
	}
	public String check(String stringGuess){
	
		int guess=Integer.parseInt(stringGuess);
		String result="miss";
		for(int cell : locationcells){
			if(guess==cell){
			
				result="hit";
				numofhits++;
				break;
			}
		
		}
		if(numofhits == locationcells.length){
			result="kill";
		}
		System.out.println(result);
		return result;
	}
	
	public static void main( String[] args){
	
		int numofguess=0;
		
		gamehelper help= new gamehelper();
		
		simpledotcom thedotom= new simpledotcom();
		
		int rand= (int)(Math.random()*5);
		
		int[] location= {rand,rand+1,rand+2};
		
		thedotom.setlocation(location);
		boolean isalive = true;
		
		while(isalive==true){
			
			String guess = help.getuserinput("enter a number");
			
			String result = thedotom.check(guess);
			numofguess++;
			
			if(result.equals("kill")){
				
				isalive=false;
				System.out.println("you took "+ numofguess +" guesses");
				
			
			}
		
		}
	
	
	}

}






