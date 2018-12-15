import java.awt.*;
import javax.swing.*;
import javax.sound.midi.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;

public class BeatBox{
	
	JPanel mainPanel;
	ArrayList<JCheckBox> checkboxList;
	Sequencer sequencer;
	Sequence sequence;
	Track track;
	JFrame theFrame;
	
	String[] instrNames={"Bass Drum","Closed HI-Hat",
	"Open Hi-Hat","Acoustic Snare","Crash Cymbal",
	"Hand Clap","High Tom","hi Bongo","Marcass",
	"Whistle","Low COngo","Cowbell","Vibraslap","Low-mid TOm",
	"High Agogo","OPen HI Congo"};
	int[] instr={35,42,46,38,49,39,50,60,70,72,64,56,58,47,67,63};
	
	public static void main(String[] arg){
		new BeatBox().buildGui();
	}
	
	
	
	public void buildGui(){
		theFrame = new JFrame("BeatBox Machine");
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BorderLayout layout = new BorderLayout();
		JPanel background = new JPanel(layout);
		background.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		checkboxList = new ArrayList<JCheckBox>();
		Box buttonBox = new Box(BoxLayout.Y_AXIS);
		
		JButton start = new JButton("Start");
		start.addActionListener(new MyStartListener());
		buttonBox.add(start);
		
		JButton stop = new JButton("Stop");
		stop.addActionListener(new MyStopListener());
		buttonBox.add(stop);
		
		JButton upTempo = new JButton("tempo UP");
		upTempo.addActionListener(new MyUpTempoListener());
		buttonBox.add(upTempo);
		
		JButton downTempo = new JButton("tempo Down");
		downTempo.addActionListener(new MyDownTempoListener());
		buttonBox.add(downTempo);
		
		JButton Serializeit = new JButton("Save it");
		Serializeit.addActionListener(new SerializeitListerner());
		buttonBox.add(Serializeit);
		
		JButton Restoreit = new JButton("Restore it");
		Restoreit.addActionListener(new RestoreitListerner());
		buttonBox.add(Restoreit);
		
		Box nameBox = new Box(BoxLayout.Y_AXIS);
		for (int i =0;i<16;i++){
			nameBox.add(new Label(instrNames[i]));
		}
		
		background.add(BorderLayout.EAST,buttonBox);
		background.add(BorderLayout.WEST,nameBox);
		
		theFrame.getContentPane().add(background);
		
		GridLayout grid = new GridLayout(16,16);
		grid.setVgap(1);
		grid.setHgap(2);
		mainPanel = new JPanel(grid);
		background.add(BorderLayout.CENTER,mainPanel);
		
		
		for(int i =0;i<256;i++){
			JCheckBox c = new JCheckBox();
			c.setSelected(false);
			checkboxList.add(c);
			mainPanel.add(c);
		}
		
		setUpMidi();
		theFrame.setBounds(50,50,300,300);
		theFrame.pack();
		theFrame.setVisible(true);
		
		
		
		
		
	}
	
	
	
	
	public void setUpMidi(){
		try{
			sequencer = MidiSystem.getSequencer();
			sequencer.open();
			sequence = new Sequence(Sequence.PPQ,4);
			track= sequence.createTrack();
		}catch(Exception ex){ex.printStackTrace();}
		
		
	}
	
	
	
	public void buildTrackAndStart(){
		int[]trackList = null;
		
		sequence.deleteTrack(track);
		track=sequence.createTrack();
		
		
		for(int i=0;i<16;i++){
			trackList = new int[16];
			
			int key= instr[i];
			
			for(int j=0;j<16;j++){
			
			JCheckBox jc = (JCheckBox) checkboxList.get(j+(16*i));
			if(jc.isSelected()){
				trackList[j]=key;
			}
			else{
				trackList[j]=0;
			}
			
			}
			
			makeTracks(trackList);
			track.add(makeEvent(176,1,127,0,16));
			
		}
		
		track.add(makeEvent(192,9,1,0,15));
		try{
			sequencer.setSequence(sequence);
			sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
			sequencer.start();
			sequencer.setTempoInBPM(120);
		}catch(Exception ex){ex.printStackTrace();}
	}
	
	
	
	public class MyStartListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			buildTrackAndStart();
		}
	}
	
	
	public class MyStopListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			sequencer.stop();
		}
	}
	
	
	public class MyUpTempoListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			float tempoFactor = sequencer.getTempoFactor();
			sequencer.setTempoFactor((float)(tempoFactor*1.03));
		}
	}
	
	
	
	public class MyDownTempoListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			float tempoFactor = sequencer.getTempoFactor();
			sequencer.setTempoFactor((float)(tempoFactor*0.97));
		}
	}
	
	
	
	public class SerializeitListerner implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			boolean[] cbState = new boolean[256];
			
			for(int i =0;i<256;i++){
				
				JCheckBox check = (JCheckBox) checkboxList.get(i);
				if(check.isSelected()){
					cbState[i]=true;
				}
			}
			
			try{
				//ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(new File("chekcbox.ser")));
				//os.writeObject(cbState);
				//os.close();
				
				JFileChooser fileChooser = new JFileChooser();
				
				
				int userSelection = fileChooser.showSaveDialog(BeatBox.this.theFrame);
				
				if(userSelection == JFileChooser.APPROVE_OPTION){
					FileOutputStream filestr= new FileOutputStream(fileChooser.getSelectedFile());
					ObjectOutputStream os = new ObjectOutputStream(filestr);
					os.writeObject(cbState);
				}
				
			}catch(IOException ex){
				ex.printStackTrace();
			}
		}
	}
	
	
	
	
	public class RestoreitListerner implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			boolean[] cbState = null;
			try{
				//FileInputStream fi= new FileInputStream(new File("chekcbox.ser"));
				//ObjectInputStream oi= new ObjectInputStream(fi);
				//cbState = (boolean[]) oi.readObject();
				JFileChooser fileChooser = new JFileChooser();
				int option = fileChooser.showOpenDialog(BeatBox.this.theFrame);
				
				if(option==fileChooser.APPROVE_OPTION){
					FileInputStream fi = new FileInputStream(fileChooser.getSelectedFile());
					ObjectInputStream oi= new ObjectInputStream(fi);
					cbState = (boolean[]) oi.readObject();
				
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
			
			for(int i =0;i<256;i++){
				JCheckBox check = (JCheckBox) checkboxList.get(i);
				
				if(cbState[i]){
					check.setSelected(true);
				}
				else{
					check.setSelected(false);
					
				}
			}
			
			sequencer.stop();
			buildTrackAndStart();
		}
	}
	
	
	
	
	
	public void makeTracks(int[] list){
		
		for(int i =0;i<16;i++){
			int key=list[i];
			
			if(key!=0){
				track.add(makeEvent(149,9,key,100,i));
				track.add(makeEvent(128,9,key,100,i+1));
				
			}
		}
	}
	
	public MidiEvent makeEvent(int comd,int chan, int one,int two,int tick){
		MidiEvent event = null;
		try{
			ShortMessage a = new ShortMessage();
			a.setMessage(comd,chan,one,two);
			event= new MidiEvent(a,tick);
			
		}catch (Exception e){}
		return event;
	}

	
	
}	
