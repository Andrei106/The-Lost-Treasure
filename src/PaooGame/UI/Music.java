package PaooGame.UI;


import javax.sound.sampled.*;
import java.io.File;


/*! \public class Music
    \brief Defineste notiunea de muzica din joc.
 */
public class Music {

    public static File level1_song_file;//file pt muzica level 1
    public static File level2_song_file;//file pt muzica level 2
    public static File level3_song_file;//file pt muzica level 3
    public static Clip level1_clip;//muzica level 1
    public static Clip level2_clip;//muzica level 2
    public static Clip level3_clip;//muzica level 3


    static Clip lastPlayedMusic;

    /*! \fn public static void InitMusic()
         \brief Initializeaza parametrii clasei.
     */
    public static void InitMusic(){
        try {
            level1_song_file = new File("res/Music/MusicLevel1.wav");
            level2_song_file = new File("res/Music/MusicLevel2.wav");
            level3_song_file = new File("res/Music/MusicLevel3.wav");

            level1_clip = AudioSystem.getClip();
            level1_clip.open(AudioSystem.getAudioInputStream(level1_song_file));

            level2_clip = AudioSystem.getClip();
            level2_clip.open(AudioSystem.getAudioInputStream(level2_song_file));

            level3_clip = AudioSystem.getClip();
            level3_clip.open(AudioSystem.getAudioInputStream(level3_song_file));


        }catch (Exception e){
            System.out.println(e.getMessage());        }
    }
    /*! \fn  public static void playSound(Clip sound)
        \brief Activeaza muzica.
        \param sound Muzica ce se va activa.
    */
    public static void playSound(Clip sound)  {
        try {

            lastPlayedMusic = sound;
            sound.setFramePosition(0);
            sound.start();


        }
        catch (Exception e){
            System.out.println(e.getMessage());        }
    }
    /*! \fn  public static void playSound(Clip sound)
           \brief Opresete ultima melodie pornita.
       */
    public static void stopLastSong(){
            lastPlayedMusic.stop();
    }


}
