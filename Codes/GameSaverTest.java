import java.io.*;

public class GameSaverTest{
	public static void main(String[] args){
		GameCharecter one = new GameCharecter(50, "Elf",new String[]{"bow","Sword","Dust"});
		GameCharecter two = new GameCharecter(200, "Troll",new String[]{"big hands","Sword","biceps"});
		GameCharecter three = new GameCharecter(120, "Mage",new String[]{"bobs","sleeping potion","God"});
		
		try{
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream ("Game.ser"));
			os.writeObject(one);
			os.writeObject(two);
			os.writeObject(three);
			os.close()
			
		}catch(IOException ex){ex.printStackTrace();}
		one=null;
		two=null;
		three=null;
		
		
		try{
			ObjectInputStream is = new ObjectInputStream(new FileImageInputStream("Game.ser"));
			GameCharecter oneR=(GameCharecter) is.readObject();
			GameCharecter twoR=(GameCharecter) is.readObject();
			GameCharecter threeR=(GameCharecter) is.readObject();
			
			System.out.println("one's type: "+ oneR.getType());
			System.out.println("two's type: "+ twoR.getType());
			System.out.println("three's type: "+ threeR.getType());
			
		}catch(IOException ex){ex.printStackTrace();}
	}
}

public class GameCharecter implements Serializable{
	int power;
	String type;
	String[] weapons;
	
	public GameCharecter(int p,String t,String[] w){
		power=p;
		type=t;
		weapons=w;
	}
	
	public int getPower(){
		return power;
	}
	public String getType(){
		return type;
	}
	public int getWeapons(){
		String wList="";
		
		for(int i =0;i<weapons.length;i++){
		wList+= weapons[i]+" ";
		}
		return wList;
	}
}