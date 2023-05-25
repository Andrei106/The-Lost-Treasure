package PaooGame.States;

import PaooGame.Game;
import PaooGame.Graphics.Assets;
import PaooGame.Graphics.Background;
import PaooGame.Graphics.ImageLoader;
import PaooGame.RefElem;

import PaooGame.UI.Music;
import PaooGame.UI.UIImageButton;
import PaooGame.UI.UIManager;

import java.awt.*;
import java.awt.image.BufferedImage;

import static PaooGame.UI.Music.playSound;
import static PaooGame.UI.Music.stopLastSong;

/*! \class public class PauseState extends State
    \brief Implementeaza notiunea de pauza pentru joc.
 */
public class PauseState extends State
{
    private Background background = new Background("/textures/BackGroundPause.jpg");                /*!< Background-ul pentru meniul pauza.*/
    private BufferedImage settings= ImageLoader.LoadImage("/textures/PAUSESCREEN.png");    /*!< Imagine utila pentru meniul pauza.*/
    public UIManager uiManager;                                                                           /*!< Managerul de interfata cu utilizatorul.*/

    /*! \fn public PauseState(RefElem refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink  referinta catre un obiect ce contine o serie de referinte utile in program..
     */
    public PauseState(RefElem refLink) {
        ///Apel al construcotrului clasei de baza.
        super(refLink);
        uiManager = new UIManager(refLink);


        uiManager.addObject(new UIImageButton(540, 300, 110, 52,Assets.resume,()->{
            if(State.GetState()==refLink.GetGame().getPauseState())
            {
                State.SetState(refLink.GetGame().getPlayState());
                refLink.GetMouseManager().setUIManager(refLink.GetGame().getPlayState().GetUIManager());
            }

        }));
        uiManager.addObject(new UIImageButton(360, 300, 115, 52, Assets.home, () -> {
            if(State.GetState()==refLink.GetGame().getPauseState())
            {
                State.SetState(refLink.GetGame().getMenuState());
                refLink.GetMouseManager().setUIManager(refLink.GetGame().getMenuState().GetUIManager());
            }
        }));
        uiManager.addObject(new UIImageButton(710, 300, 110, 52, Assets.retry, () -> {
            if(State.GetState()==refLink.GetGame().getPauseState())
            {
                State.SetState(refLink.GetGame().continueGame());
                refLink.GetMouseManager().setUIManager(refLink.GetGame().getPlayState().GetUIManager());
                stopLastSong();
                if(Game.level==1){
                    playSound(Music.level1_clip);
                }else if(Game.level==2)
                    playSound(Music.level2_clip);
                else
                    playSound(Music.level3_clip);
            }
        }));
        uiManager.addObject(new UIImageButton(549, 380, 110, 52, Assets.sounds, () -> {
            if(State.GetState()==refLink.GetGame().getPauseState())
            {
                if(Game.audio) {
                    Game.audio=false;
                } else{
                    Game.audio=true;
                }
            }
        }));


    }

    /*! \fn public void Update()
        \brief Actualizeaza starea meniului.
     */
    @Override
    public void Update()
    {
        uiManager.Update();
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran setarile.

        \param g Contextul grafic in care trebuie sa deseneze starea meniului pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {
        background.Draw(g);
        g.drawImage(settings,200,70,800,500,null);
        uiManager.draw(g);
    }

    /*! \fn public UIManager GetUIManager()
        \brief Returneaza managerul de interfata cu utilizatorul.
    */
    @Override
    public UIManager GetUIManager()
    {
        return uiManager;
    }
}
