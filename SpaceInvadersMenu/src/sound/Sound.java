package sound;

import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class Sound implements LineListener {

    private Clip soundClip;
    private URL url;
    private AudioInputStream audioInputStream;
    private AudioFormat format;
    private DataLine.Info info;

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

    public void play() {
        soundClip.start();
    }

    public void loop() {
        soundClip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        soundClip.stop();
        soundClip.setFramePosition(1);
    }

    public boolean isPlaying() {
        return soundClip.isRunning();
    }
}//fin de la clase
