package audio;

import game.settings.AudioSettings;
import game.settings.GameSettings;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AudioPlayer {

    private AudioSettings audioSettings;
    private List<AudioClip> audioClips;

    // Constructor
    public AudioPlayer(AudioSettings audioSettings) {
        this.audioSettings = audioSettings;
        audioClips = new ArrayList<>();
    }
    // Updates the audio clips
    // makes a copy of the list and checks if an audio clip has finished playing, if it has,
    // it calls the clean up to remove it from the list. Copy list is used to not modify the list
    // as it is being iterated through
    public void update() {
        audioClips.forEach(audioClip -> audioClip.update(audioSettings));

        List.copyOf(audioClips).forEach(audioClip -> {
            if(audioClip.hasFinishedPlaying()) {
                audioClip.cleanUp();
                audioClips.remove(audioClip);
            }
        });
    }

    public void playMusic(String fileName) {
        final Clip clip = getClip(fileName);
        final MusicClip musicClip = new MusicClip(clip);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        musicClip.setVolume(audioSettings);
        audioClips.add(musicClip);
    }

    public void playSound(String fileName) {
        final Clip clip = getClip(fileName);
        final SoundClip soundClip = new SoundClip(clip);
        soundClip.setVolume(audioSettings);
        audioClips.add(soundClip);
    }

    // Opens a line to a clip and sets first/last frames.
    // Uses javas class get resource to obtain file path 
    // returns the clip
    private Clip getClip(String fileName) {
        final URL soundFile = AudioPlayer.class.getResource("/sounds/" + fileName);
        try(AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile)) {
            final Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.setMicrosecondPosition(0);
            return clip;

            // required error handling
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }

        return null;
    }

    public void clear() {
        audioClips.forEach(AudioClip::cleanUp);
        audioClips.clear();
    }
}
