package app.Views;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import acm.util.RandomGenerator;

public class Audio {

    public static void play(String path) {
        AudioInputStream stream;
        try {
            stream = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
            // create clip reference
            Clip clip = AudioSystem.getClip();
            // open audioInputStream to the clip
            clip.open(stream);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

    }

    public static void playPlacement() {
        int r = RandomGenerator.getInstance().nextInt(1, 4);
        play("assets/cdsAudio/placementSound" + r + ".wav");
    }

    public static void playWinning() {
        play("assets/cdsAudio/winningSound.wav");
    }

    public static void playBackgroundMusic() {
        AudioInputStream stream;
        try {
            stream = AudioSystem.getAudioInputStream(new File("assets/backgroundAudio/onion.wav").getAbsoluteFile());
            // create clip reference
            Clip clip = AudioSystem.getClip();


            // open audioInputStream to the clip
            clip.open(stream);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-20.0f); // Reduce volume by 10 decibels.
            clip.start();



        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

    }

}