import javax.sound.midi.*;

public class MiniMusicPlayer implements ControllerEventListener{
	public static void main(String[] args){
		MiniMusicPlayer player = new MiniMusicPlayer();
		player.go();
	}
	public void go(){	
		try{
			
			Sequencer sequencer =MidiSystem.getSequencer();
			sequencer.open();
			
			int[] EventNeeded={127};
			sequencer.addControllerEventListener(this,EventNeeded);
			
			Sequence seq = new Sequence(Sequence.PPQ, 4);
			Track track = seq.createTrack();
			
			for (int i=5;i<61;i+= 4){
				
				track.add(makeEvent(144,1,i,100,i));
				track.add(makeEvent(176,1,127,0,i));//dummy event for controlChange
				track.add(makeEvent(128,1,i,100,i+2));
			}
			
			sequencer.setSequence(seq);
			sequencer.setTempoInBPM(220);
			sequencer.start();
		}catch(Exception ex){ex.printStackTrace();}
	}
	
	public void controlChange(ShortMessage ev){
		System.out.println("la");
	}	
	
	
	public  MidiEvent makeEvent(int comd,int chan, int one,int two,int tick){
		
		MidiEvent event = null;
		try{
			ShortMessage a = new ShortMessage();
			a.setMessage(comd,chan,one,two);
			event= new MidiEvent(a,tick);
			
		}catch (Exception e){}
		return event;
	}

}