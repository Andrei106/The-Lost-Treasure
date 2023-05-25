package PaooGame.States;

import PaooGame.Game;
import PaooGame.Graphics.Assets;
import PaooGame.Graphics.Background;
import PaooGame.Graphics.ImageLoader;
import PaooGame.RefElem;
import PaooGame.UI.UIImageButton;
import PaooGame.UI.UIManager;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class public class OptionsState extends State
    \brief Implementeaza notiunea de pauza pentru joc.
 */
public class OptionsState extends State
{
    private Background background = new Background("/textures/BackGroundPause.jpg");                /*!< Background-ul pentru meniul pauza.*/
    public UIManager uiManager;                                                                           /*!< Managerul de interfata cu utilizatorul.*/

    /*! \fn public OptionsState(RefElem refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink  referinta catre un obiect ce contine o serie de referinte utile in program.
     */
    public OptionsState(RefElem refLink) {
        ///Apel al construcotrului clasei de baza.
        super(refLink);
        uiManager = new UIManager(refLink);



        uiManager.addObject(new UIImageButton(20,255,220,56, Assets.difficulty, () -> {
            if(State.GetState()==refLink.GetGame().getOptionsState())
            {
                State.SetState(refLink.GetGame().getDifficultyState());
                refLink.GetMouseManager().setUIManager(refLink.GetGame().getDifficultyState().GetUIManager());
            }
        }));

        uiManager.addObject(new UIImageButton(20, 320, 220, 56,Assets.music,()->{
            if(State.GetState()==refLink.GetGame().getOptionsState())
            {
                if(Game.audio==true)
                    Game.audio=false;
                else
                    Game.audio=true;

            }

        }));
        uiManager.addObject(new UIImageButton(20,385,220,56, Assets.leaderbord, () -> {
            if(State.GetState()==refLink.GetGame().getOptionsState())
            {
                State.SetState(refLink.GetGame().getLeaderboardState());
                refLink.GetMouseManager().setUIManager(refLink.GetGame().getLeaderboardState().GetUIManager());
            }
        }));
        uiManager.addObject(new UIImageButton(20,460,220,56, Assets.back, () -> {
            if(State.GetState()==refLink.GetGame().getOptionsState())
            {
                State.SetState(refLink.GetGame().getMenuState());
                refLink.GetMouseManager().setUIManager(refLink.GetGame().getMenuState().GetUIManager());
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
