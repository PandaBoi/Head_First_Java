import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleGui3 implements ActionListener{
	JFrame frame = new JFrame();
	
	public static void main(String[] args){
		SimpleGui3 gui = new SimpleGui3();
		gui.go();
	}
	
	public void go(){
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton button = new JButton("Press to change color");
		button.addActionListener(this);
		
		MyDrawPanel drawPanel = new MyDrawPanel();//for drawing mutiple stuff on a panel
		
		frame.getContentPane().add(BorderLayout.SOUTH,button);
		frame.getContentPane().add(BorderLayout.CENTER,drawPanel);
		frame.setSize(500,500);
		frame.setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent action){
		
		frame.repaint();
	}
	
	
}

class MyDrawPanel extends JPanel{
		
		
		public void paintComponent(Graphics g){
			int r=(int) (Math.random() * 255);
			int b=(int) (Math.random() * 255);
			int gr=(int) (Math.random() * 255);
			Color randcolor = new Color(r,b,gr); 
		
			g.setColor(randcolor);
			g.fillOval(70,70,100,100);
		}	
}