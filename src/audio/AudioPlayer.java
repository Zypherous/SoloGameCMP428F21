package audio;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer {
	private List<AudioClip> audioClips;

    public AudioPlayer() {
        audioClips = new ArrayList<>();
    }
    
    public void playMusic(String fileName) {
    	final Clip clip = getClip(fileName);
    }
    public void playSound(String fileName) {
    	final Clip clip = getClip(fileName);
    }
    
    private Clip getClip(String fileName)   {
    	final URL soundFile = AudioPlayer.class.getResource("/sounds/" + fileName);
    	try(AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile)){
    		final Clip clip = AudioSystem.getClip();
    		clip.open(audioInputStream);
    		clip.setMicrosecondPosition(0);
    		return clip;
    	}
    	catch(UnsupportedAudioFileException | IOException | LineUnavailableException e) {
    		System.out.println(e);
    	}
    	return null;
    }
}
