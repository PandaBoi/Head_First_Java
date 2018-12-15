import javax.swing.*;

public class SimpleGui1{
	
	public static void main(String[] args){
		
		JFrame frame = new JFrame();
		JButton button = new JButton("Click moi!");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//makes program quit when the window closes
		
		frame.getContentPane().add(button);
		
		frame.setSize(300,300);
		frame.setVisible(true);
	}
}