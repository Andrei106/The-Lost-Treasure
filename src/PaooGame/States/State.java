package PaooGame.States;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import PaooGame.Exceptions.ZeroException;
import PaooGame.Game;
import PaooGame.RefElem;
import PaooGame.UI.Music;
import PaooGame.UI.UIManager;

import static PaooGame.UI.Music.playSound;
import static PaooGame.UI.Music.stopLastSong;

/*! \class State
    \brief Implementeaza notiunea abstracta de stare a jocului/programului.
 */
public abstract class State
{
    private static State previousState  = null; /*!< Referinta catre starea anterioara a jocului.*/
    private static State currentState   = null; /*!< Referinta catre starea curenta a jocului: game, meniu, load, options, etc.*/
    private static RefElem refLink;            /*!< Referinta catre un obiect ce contine o serie de referinte utile in program.*/

    /*! \fn public State(RefElem refLink)
            \brief Constructorul de initializare al clasei.

            \param refLink o referinta catre un obiect ce contine o serie de referinte utile in program.
     */
    public State(RefElem refLink)
    {
        State.refLink = refLink;
    }

    /*! \fn public static void SetState(State state)
        \brief Seteaza starea curenta a jocului.

        \param state Noua stare a programului (jocului).
     */
    public static void SetState(State state)
    {
        previousState = currentState;
        currentState = state;

      if(Game.audio) {

          if (state == refLink.GetGame().getMenuState())
              playSound(Music.level1_clip);
          else
              stopLastSong();

            if (state == refLink.GetGame().getPlayState())
            {
                if(Game.level==1){
                    playSound(Music.level1_clip);
                }else if(Game.level==2)
                    playSound(Music.level2_clip);
                else
                    playSound(Music.level3_clip);
            }else
                stopLastSong();

        }

    }

    /*! \fn public abstract UIManager GetUIManager()
        \brief Returneaza managerul de interfata cu utilizatorul.
    */
    public abstract UIManager GetUIManager();

    /*! \fn public static State GetState()
        \brief Returneaza starea curenta.
    */
    public static State GetState()
    {
        return currentState;
    }

    /*! \fn public static State GetPreviousState()
        \brief Returneaza starea anterioara.
    */
    public static State GetPreviousState()
    {
        return previousState;
    }

    /*! \fn public void Update()
        \brief Actualizeaza starea .
       */
    public abstract void Update() throws ZeroException;

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starile.
        \param g Contextul grafic in care trebuie sa deseneze starea setarilor pe ecran.
     */
    public abstract void Draw(Graphics g);

    /*! \fn  public static void LoadGame()
      \brief Initializeaza cu valorile din baza de date variabilele jocului ,astfel incat sa se poata continua de unde a ramas jucatorul ultima data.
   */
    public static void LoadGame() {
        Connection c;
        Statement stmt;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:load_game.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM LOAD_GAME;");
            while (rs.next()) {
                Game.level= rs.getInt("SAVED_FILE");
                PlayState.score=rs.getInt("SAVED_SCORE");
                PlayState.lifes=rs.getInt("SAVED_NR_LIFES");
                PlayState.mapKEY=rs.getInt("KEY_1");
                PlayState.treasureKEY=rs.getInt("KEY_2");


            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
