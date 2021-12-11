package audio;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import game.settings.AudioSettings;

public abstract class AudioClip {

    private final Clip clip;

    public AudioClip(Clip clip) {
        this.clip = clip;
        clip.start();
    }

    public void update(AudioSettings audioSettings) {
        setVolume(audioSettings);
    }

    void setVolume(AudioSettings audioSettings) {
        final FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float range = control.getMaximum() - control.getMinimum();
        float gain = (range * getVolume(audioSettings)) + control.getMinimum();
        
        control.setValue(gain);
    }

    protected abstract float getVolume(AudioSettings audioSettings);

    public boolean hasFinishedPlaying() {
        return !clip.isRunning();
    }

    public void cleanUp() {
        clip.close();
    }
}
