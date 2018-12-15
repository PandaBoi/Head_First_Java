import java.awt.*;
import javax.swing.*;

public class Jpanel extends JPanel{//to use drawing graphics we extend existing JPanel
	
	public void paintComponent(Graphics g){//g is the object we will work with
		//never call the paintComponent function
		
		g.fillRect(0,0,this.getWidth(),this.getHeight());
		
		//**creating color**
		int r=(int) (Math.random() * 255);
		int b=(int) (Math.random() * 255);
		int gr=(int) (Math.random() * 255);
		Color randcolor = new Color(r,b,gr); 
		//*******************
		
		g.setColor(randcolor);
		g.fillOval(70,70,100,100);
	
		g.setColor(Color.blue);
		g.fillRect(400, 50, 100, 80);//x,y,w,l
		
		//Image img = new ImageIcon("IMG_20170903_113856853.jpg").getImage();
		//g.drawImage(img,20,3,this);
		
		Graphics2D g2d = (Graphics2D) g;
		
		GradientPaint gradient = new GradientPaint(70,70,Color.blue,150,150,Color.white);
		
		g2d.setPaint(gradient);
		g2d.fillOval(70,70,100,100);
		
		
	}
	
	public static void main(String [] args){
		
		JFrame frame = new JFrame();
		Jpanel thing = new Jpanel();
		
		
		frame.getContentPane().add(thing);
		frame.setSize(1000,1000);
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	
	
}