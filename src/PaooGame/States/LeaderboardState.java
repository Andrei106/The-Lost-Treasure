package PaooGame.States;

import PaooGame.Graphics.Assets;
import PaooGame.Graphics.Background;
import PaooGame.Graphics.ImageLoader;
import PaooGame.RefElem;
import PaooGame.UI.UIImageButton;
import PaooGame.UI.UIManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*! \class public class LeaderboardState extends State
    \brief Implementeaza notiunea de tabela de scoruri pentru joc.
 */
public class LeaderboardState extends State
{
    public static int[] s;
    private Background background = new Background("/textures/BackGroundPause.jpg");                /*!< Background-ul pentru meniul pauza.*/
    private BufferedImage board= ImageLoader.LoadImage("/textures/LEADERBOARD.png");    /*!< Imagine utila pentru tabela cu scoruri.*/
    public UIManager uiManager;                                                                           /*!< Managerul de interfata cu utilizatorul.*/

    /*! \fn public LeaderboardState(RefElem refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink Referinta catre un obiect ce retine o serie de referinte din program.
     */
    public LeaderboardState(RefElem refLink) {
        ///Apel al construcotrului clasei de baza.
        super(refLink);
        uiManager = new UIManager(refLink);
        s=new int[5];

        uiManager.addObject(new UIImageButton(20,460,220,56, Assets.back, () -> {
            if(State.GetState()==refLink.GetGame().getLeaderboardState())
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


    /*! \fn public static void LoadScores()
        \brief Stocheaza intr-un vector valorile scorurilor din baza de date pentru a fi desenate ulterior.
     */
    public static void LoadScores() {
        Connection c;
        Statement stmt;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:scores_game.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM SCORES ORDER BY SCORE DESC;");
            int i=0;
            while (rs.next()) {
                s[i]= rs.getInt("SCORE");
                i++;
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran setarile.

        \param g Contextul grafic in care trebuie sa deseneze starea meniului pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {
        LoadScores();
        background.Draw(g);
        g.drawImage(board,200,70,800,500,null);
        g.setFont(new Font("TimesRoman", Font.BOLD, 35));
        g.drawString(String.valueOf(s[0]), 620, 235);
        g.drawString(String.valueOf(s[1]), 620, 300);
        g.drawString(String.valueOf(s[2]), 620, 365);
        g.drawString(String.valueOf(s[3]), 620, 429);
        g.drawString(String.valueOf(s[4]), 620, 495);
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
