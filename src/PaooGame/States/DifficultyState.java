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


/*! \class public class DifficultyState extends State
    \brief Implementeaza notiunea de dificultate pentru joc.
 */
public class DifficultyState extends State
{
    public static int[] s;
    private Background background = new Background("/textures/BackGroundPause.jpg");                /*!< Background-ul pentru meniul pauza.*/
    public UIManager uiManager;                                                                           /*!< Managerul de interfata cu utilizatorul.*/

    /*! \fn public DifficultyState(RefElem refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink Referinta catre un obiect ce retine o serie de referinte din program.
     */
    public DifficultyState(RefElem refLink) {
        ///Apel al construcotrului clasei de baza.
        super(refLink);
        uiManager = new UIManager(refLink);

        uiManager.addObject(new UIImageButton(20, 320, 220, 56,Assets.easy,()->{
            if(State.GetState()==refLink.GetGame().getDifficultyState())
            {
                Game.leveldifficulty=1;
            }

        }));
        uiManager.addObject(new UIImageButton(20,385,220,56, Assets.normal, () -> {
            if(State.GetState()==refLink.GetGame().getDifficultyState())
            {
                Game.leveldifficulty=2;
            }
        }));
        uiManager.addObject(new UIImageButton(20,460,220,56, Assets.hard, () -> {
            if(State.GetState()==refLink.GetGame().getDifficultyState())
            {
                Game.leveldifficulty=3;
            }
        }));
        uiManager.addObject(new UIImageButton(20,535,220,56, Assets.back, () -> {
            if(State.GetState()==refLink.GetGame().getDifficultyState())
            {
                State.SetState(refLink.GetGame().getOptionsState());
                refLink.GetMouseManager().setUIManager(refLink.GetGame().getOptionsState().GetUIManager());
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
