package PaooGame.States;

import PaooGame.Exceptions.ZeroException;
import PaooGame.Game;
import PaooGame.Graphics.Assets;
import PaooGame.RefElem;
import PaooGame.Maps.Map;
import PaooGame.UI.UIImageButton;
import PaooGame.UI.UIManager;
import java.awt.*;


/*! \class public class PlayState extends State
    \brief Implementeaza/controleaza jocul.
 */
public class PlayState extends State
{
    private Map map;                /*!< Referinta catre harta curenta.*/
    private UIManager uiManager;    /*!< Referinta catre managerul de interfata cu utilizatorul.*/
    public static int gcoin = 0; /*!< Referinta statica catre numarul de banuti, initializat cu 0.*/
    public static int lifes = 3;    /*!< Referinta statica catre numarul de vieti, initializat cu 3.*/
    public static int score = 0;    /*!< Referinta statica catre numarul de puncte.*/
    public static int mapKEY = 0;      /*!< Referinta statica catre numarul de chei, initializat cu 0.*/
    public static int treasureKEY = 0;      /*!< Referinta statica catre numarul de chei, initializat cu 0.*/
    public static int treasure = 0;      /*!< Referinta statica catre numarul de chei, initializat cu 0.*/
    /*! \fn public PlayState(RefElem refLink)
        \brief Constructorul de initializare al clasei

        \param refLink  referinta catre un obiect ce contine o serie de referinte utile in program.
     */
    public PlayState(RefElem refLink)
    {
            ///Apel al constructorului clasei de baza
        super(refLink);
            ///Construieste harta jocului
        map = new Map(refLink);
            //Pentru a fi accesibila si in alte clase ale programului se seteaza harta si in obiectul refLink.
        refLink.SetMap(map);
        uiManager=new UIManager(refLink);
        uiManager.addObject(new UIImageButton(1150, 2, 40, 40, Assets.pause, () -> {
            if(State.GetState()==refLink.GetGame().getPlayState()) {
                State.SetState(refLink.GetGame().getPauseState());
                refLink.GetMouseManager().setUIManager(refLink.GetGame().getPauseState().GetUIManager());
            }
        }));
    }


    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a jocului.
     */
    @Override
    public void Update() throws ZeroException {
        map.Update();
        uiManager.Update();
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a jocului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {
        map.Draw(g);

        g.setColor(Color.red);
        g.setFont(new Font("TimesRoman", Font.BOLD, 35));
        g.drawString("Lives: " + lifes , 55, 30);

        if(PlayState.mapKEY !=0)
            g.drawImage(Assets.key[0],400,0,48,48,null);
        if(PlayState.treasureKEY !=0)
            g.drawImage(Assets.key[1],450,0,48,48,null);

        if(PlayState.treasure!=0){
            PlayState.mapKEY=0;
            PlayState.treasureKEY=0;
            g.drawImage(Assets.key[2],400,0,48,48,null);

        }
        g.setColor(Color.orange);
        g.drawString("Score : "  + score , 750, 30);
        g.setColor(Color.black);
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
