package game.settings;

public class AudioSettings {
    private float musicVolume;
    private float soundVolume;

    
    // Default values for the audio volume and sound effect volume
    public AudioSettings() {
        musicVolume = 0.7f;
        soundVolume = 0.8f;
    }

    public float getMusicVolume() {
        return musicVolume;
    }

    public void setMusicVolume(float musicVolume) {
        this.musicVolume = musicVolume;
    }

    public float getSoundVolume() {
        return soundVolume;
    }

    public void setSoundVolume(float soundVolume) {
        this.soundVolume = soundVolume;
    }
}
