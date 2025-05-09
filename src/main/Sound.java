package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    Clip clip;
    URL  soundURL[]= new URL[30];
    public Sound(){
        soundURL[0]= getClass().getResource("/res/sound/main.wav");
        soundURL[1]= getClass().getResource("/res/sound/coin.wav");
        soundURL[2]= getClass().getResource("/res/sound/powerup.wav");
        soundURL[3]= getClass().getResource("/res/sound/unlock.wav");
        soundURL[4]= getClass().getResource("/res/sound/fanfare.wav");
        soundURL[5]= getClass().getResource("/res/sound/blocked.wav");
        soundURL[6]= getClass().getResource("/res/sound/hitmonster.wav");
        soundURL[7]= getClass().getResource("/res/sound/receivedamage.wav");
        soundURL[8]= getClass().getResource("/res/sound/swingweapon.wav");
    }
    public void setFile(int i){//open audio file in java
        try{
            AudioInputStream input = AudioSystem.getAudioInputStream(soundURL[i]);
            clip=AudioSystem.getClip();
            clip.open(input);
        }catch(Exception e){ e.printStackTrace(); }
    }
    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
}
