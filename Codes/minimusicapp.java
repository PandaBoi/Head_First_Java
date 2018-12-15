import javax.sound.midi.*;

public class minimusicapp{
	
	public static void main(String[] args){
		minimusicapp mini = new minimusicapp();
		mini.play();
	}
	
	public void play(){
		
		try{
			//get a new sequencer and open it
			Sequencer player = MidiSystem.getSequencer();
			player.open();
			
			//make a  new sequence
			Sequence seq= new Sequence(Sequence.PPQ,4);
			
			//get a new track from the sequence
			Track track= seq.createTrack();
			
			//filling track with midi events
			ShortMessage c = new ShortMessage();
			c.setMessage(144,1,44,100);
			MidiEvent con= new MidiEvent(c,1);
			track.add(con);
			
			ShortMessage e = new ShortMessage();
			e.setMessage(144,1,46,100);
			MidiEvent eon= new MidiEvent(e,1);
			track.add(eon);
			
			ShortMessage g = new ShortMessage();
			g.setMessage(144,1,48,100);
			MidiEvent gon= new MidiEvent(g,1);
			track.add(gon);
			
			ShortMessage ao = new ShortMessage();
			ao.setMessage(128,1,44,100);
			MidiEvent aoff= new MidiEvent(ao,16);
			track.add(aoff);
			
			ShortMessage eo = new ShortMessage();
			eo.setMessage(128,1,46,100);
			MidiEvent eoff= new MidiEvent(eo,16);
			track.add(eoff);
			
			ShortMessage go = new ShortMessage();
			go.setMessage(128,1,48,100);
			MidiEvent goff= new MidiEvent(go,16);
			track.add(goff);
			
			//putting cd in cdplayer
			player.setSequence(seq);
			//playing it
			player.start();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}