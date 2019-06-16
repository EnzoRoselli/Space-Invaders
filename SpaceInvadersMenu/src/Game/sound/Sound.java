package Game.sound;

import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

/**
 * This class creates the sound effects.
 * @author InvadersTeam
 * @since March 2019
 */
public class Sound implements LineListener {

    private Clip soundClip;
    private URL url;
    private AudioInputStream audioInputStream;
    private AudioFormat format;
    private DataLine.Info info;

    /**
     * This constructor creates the sound clips.
     * @param path Sound URL
     */
    public Sound(String path) {
        try {
            url = getClass().getResource(path);
            audioInputStream = AudioSystem.getAudioInputStream(url);
            format = audioInputStream.getFormat();
            info = new Info(Clip.class, format);
            soundClip = (Clip) AudioSystem.getLine(info);
            soundClip.open(audioInputStream);
            soundClip.addLineListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(LineEvent event) {
        if (event.getType().equals(LineEvent.Type.STOP)) {
            soundClip.setFramePosition(1);
        }
    }

    /**
     * Plays sound.
     */
    public void play() {
        soundClip.start();
    }

    /**
     * Plays sound loop.
     */
    public void loop() {
        soundClip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Stops sound.
     */
    public void stop() {
        soundClip.stop();
        soundClip.setFramePosition(1);
    }

    /**
     * 
     * @return True if the sound is playing.
     */
    public boolean isPlaying() {
        return soundClip.isRunning();
    }
}//fin de la clase
