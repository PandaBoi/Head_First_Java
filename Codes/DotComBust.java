import java.util.*;
public class DotComBust{

		private helper helper = new helper();
		private ArrayList<DotCom> placements=new ArrayList<DotCom>();
		private int numofguess=0;
		
		private void Setup(){
			
			DotCom one = new DotCom();
			one.setname("Pets.Com");
			DotCom two = new DotCom();
			two.setname("lol.uk");
			DotCom three = new DotCom();
			three.setname("thisisit.lolwa");
			placements.add(one);
			placements.add(two);
			placements.add(three);
			
			System.out.println("YOur goal is to sink all three dotcoms");
			System.out.println("Try to sink them in min no of guesses");
			
			for(DotCom each:placements){
				
				ArrayList<String> theloc =helper.placeDotCom(3);
				each.SetLocationCells(theloc);
			}
		}
		private void start(){
			while(!placements.isEmpty()){
				String userguess =helper.getUserInput("enter your guess: ");
				checkUserInput(userguess);
			}
			finishgame();
		}
		public void checkUserInput(String userguess){
			
			numofguess++;
			String res="miss";
			
			for(DotCom each:placements){
				res=each.checkyourself(userguess);
				if(res.equals("hit")){
					break;
				}
				if(res.equals("kill")){
					placements.remove(each);
					break;
				}
			}
			System.out.println(res);
		}
		
		private void finishgame(){
			System.out.println("all coms are dead");
			System.out.println("you took "+ numofguess +" guesses");
			
		}
		public static void main(String[] args){
			DotComBust game=new DotComBust();
			game.Setup();
			game.start();
		}

}