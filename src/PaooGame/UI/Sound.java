package PaooGame.UI;

import javax.sound.sampled.*;
import java.io.File;


/*! \public class Sound
    \brief Defineste notiunea de sunet din joc.
 */
public class Sound {


    public static File goldcoin ;//file pt sunetul banutilor
    public static File keys ;//file pt sunetul cheilor
    public static File jump ;//file pt sunetul sariturii
    public static File hurt ;//file pt sunetul momentului in care esti ranit
    public static File die ;//file pt sunetul cand mori
    public static File win ;//file pt sunetul cand castigi


    static Clip clip;

    /*! \fn public static void InitSound()
             \brief Initializeaza parametrii clasei.
         */
    public static void InitSound(){
        goldcoin = new File("res/Music/Coin.wav");
        keys=new File("res/Music/Keys.wav");
        jump=new File("res/Music/jump.wav");
        die=new File("res/Music/die.wav");
        hurt=new File("res/Music/hurt.wav");
        win=new File("res/Music/Win.wav");

        try {
            clip = AudioSystem.getClip();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
    /*! \fn  public static void playSound(File sound)
            \brief Activeaza sunetul.
            \param sound Sunetul ce se va activa.
        */
    public static void playSound(File sound)  {

        try {

            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(sound));

            clip.start();

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
