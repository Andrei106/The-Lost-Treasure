package PaooGame.States;

import PaooGame.Game;
import PaooGame.Graphics.Assets;
import PaooGame.Graphics.Background;
import PaooGame.RefElem;
import PaooGame.UI.Sound;
import PaooGame.UI.UIImageButton;
import PaooGame.UI.UIManager;

import java.awt.*;

/*! \class public class MenuState extends State
    \brief Implementeaza notiunea de meniu pentru joc.
 */
public class MenuState extends State
{
    private Background background = new Background("/textures/TLTreasure.png"); /*!< Background-ul pentru meniu.*/
    public UIManager uiManager;  /*!< Managerul de interfata cu utilizatorul.*/

    /*! \fn public MenuState(RefElem refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink - referinta catre un obiect ce contine o serie de referinte utile in program.
     */
    public MenuState(RefElem refLink)
    {
            ///Apel al constructorului clasei de baza.
        super(refLink);

        /// User Interface Manager
        uiManager=new UIManager(refLink);
        refLink.GetMouseManager().setUIManager(uiManager);


        uiManager.addObject(new UIImageButton(20, 320, 110, 56, Assets.play, () -> {
        if(State.GetState()==refLink.GetGame().getMenuState())
        {

            State.SetState(refLink.GetGame().newGame());
            refLink.GetMouseManager().setUIManager(refLink.GetGame().getPlayState().GetUIManager());


        }
        }));
        uiManager.addObject(new UIImageButton(20,385,160,50,Assets.load,()->{
            if(State.GetState()==refLink.GetGame().getMenuState())
            {
                State.SetState(refLink.GetGame().continueGame());
                refLink.GetMouseManager().setUIManager(refLink.GetGame().getPlayState().GetUIManager());

            }
        }));
        uiManager.addObject(new UIImageButton(20,440,180,60, Assets.options,()->{
            if(State.GetState()==refLink.GetGame().getMenuState())
            {
                State.SetState(refLink.GetGame().getOptionsState());
                refLink.GetMouseManager().setUIManager(refLink.GetGame().getOptionsState().GetUIManager());
            }
        }));
        uiManager.addObject(new UIImageButton(20,510,180,45,Assets.quit,()->{
            if(State.GetState()==refLink.GetGame().getMenuState())
            {
                System.exit(0);
            }
        }));
    }
    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a meniului.
     */
    @Override
    public void Update()
    {
        uiManager.Update();
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a meniului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
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
    public UIManager GetUIManager()
    {
        return uiManager;
    }
}
