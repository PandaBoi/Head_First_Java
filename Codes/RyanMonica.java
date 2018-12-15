class BankAccount{
	private int balance =100;
	
	public int getBalance(){
		return balance;
		
	}
	
	public void takeOut(int val){
		balance = balance-val;
		}
}

public class RyanMonica implements Runnable{
	
	private BankAccount acc = new BankAccount();
	
	public static void main(String[] args){
		RyanMonica thing= new RyanMonica();
		Thread one = new Thread(thing);
		Thread two = new Thread(thing);
		one.setName("Ryan");
		two.setName("Monica");
		one.start();
		two.start();
	}
	
	public void run(){
		for(int x=0;x<10;x++){
			withdraw(10);
			if(acc.getBalance()<0){
				System.out.println("overdrawn");
			}
		}
	}
	
	private synchronized void withdraw(int amount){
		if(acc.getBalance()>amount){
			System.out.println(Thread.currentThread().getName() + " is about to withdraw");
			
			try{
				System.out.println(Thread.currentThread().getName() +" is going to sleep");
				Thread.sleep(500);
			}catch(InterruptedException e){e.printStackTrace();}
			
			System.out.println(Thread.currentThread().getName() +" woke up");
			acc.takeOut(amount);
			System.out.println(Thread.currentThread().getName() +" completes withdraw");
		}
		else{
			System.out.println("Sorry not enough money for "+Thread.currentThread().getName() );
		}
	}
}