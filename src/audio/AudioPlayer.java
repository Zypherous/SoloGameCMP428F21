package audio;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer {
	private List<AudioClip> audioClips;

    public AudioPlayer() {
        audioClips = new ArrayList<>();
    }
    
    public Clip getClip(String fileName) {
    	final URL soundFIle = Audio.class.getResource("/sounds/" + fileName);
    	try(audioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile)){
    		
    	}
    	catch(UnsupportedAudioFileException | IOException e) {
    		System.out.println(e);
    	}
    }
}
