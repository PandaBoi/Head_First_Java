import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TwoButtons{
	
	JFrame frame;
	JLabel label;
	MyDrawPanel drawpanel;
	private static int check=0;
	
	public static void main(String[] args){
		TwoButtons gui = new TwoButtons();
		gui.go();
	}
	
	public void go(){
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton Colorbutton = new JButton("Press to change color");
		Colorbutton.addActionListener(new ColorListener());
		
		JButton Labelbutton = new JButton("Press to change label");
		Labelbutton.addActionListener(new LabelListener());
		
		label = new JLabel("im a label");
		drawpanel = new MyDrawPanel();
		
		frame.getContentPane().add(BorderLayout.SOUTH,Colorbutton);
		frame.getContentPane().add(BorderLayout.CENTER,drawpanel);
		frame.getContentPane().add(BorderLayout.EAST,Labelbutton);
		frame.getContentPane().add(BorderLayout.WEST,label);
		
		frame.setSize(500,500);
		frame.setVisible(true);
	}
	
	
	class ColorListener implements ActionListener{
		
		public void actionPerformed(ActionEvent action2){
			drawpanel.setChangeLabel(false);
			frame.repaint();
		}
	}
	
	class LabelListener implements ActionListener{
		
		public void actionPerformed(ActionEvent action1){
			drawpanel.setChangeLabel(true);
			if (check==0){
			label.setText("ouch!");
			check =1;
			}
			else{
				label.setText("uff!");
				check =0;	
			}
		}
		
	}
	
}

class MyDrawPanel extends JPanel{
		
		boolean changeLabel = false;
		Color randcolor ;
		public void setChangeLabel(boolean changeLabel){
			
			this.changeLabel = changeLabel;
		}
		public void paintComponent(Graphics g){
						 
			if(!changeLabel){
				setCircleColor();
			}
			g.setColor(randcolor);
			g.fillOval(70,70,100,100);
		}
		
		public void setCircleColor(){
			
			int r=(int) (Math.random() * 255);
			int b=(int) (Math.random() * 255);
			int gr=(int) (Math.random() * 255);
			randcolor = new Color(r,b,gr);
			
		}	
}