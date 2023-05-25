package PaooGame.Items;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import PaooGame.Exceptions.ZeroException;
import PaooGame.Game;
import PaooGame.Items.Statics.MapKey;
import PaooGame.Items.Statics.Treasure;
import PaooGame.Items.Statics.TreasureKey;
import PaooGame.RefElem;
import PaooGame.Graphics.Assets;
import PaooGame.States.PlayState;
import PaooGame.States.State;
import PaooGame.Tiles.Tile;
import PaooGame.UI.Music;


import javax.swing.*;

import static PaooGame.UI.Sound.*;

/*! \class public class John extends Character
    \brief Implementeaza notiunea de erou (caracterul controlat de jucator).

    Elementele suplimentare pe care le aduce fata de clasa de baza sunt:
        imaginea
        deplasarea
        dreptunghiul de coliziune
 */
public class John extends Character {
    private BufferedImage image;    /*!< Referinta catre imaginea curenta a eroului.*/
    private static int aux = 0;     /*!< Variabila auxiliara care ajuta la desenarea eroului.*/
    private static int scorecheck = 5000;     /*!< Variabila auxiliara care ajuta la desenarea eroului.*/
    private final float startY;
    private final float startX;   /*!< Pozitie inițiala(coordonate x si y)*/

    /*! \fn public John(RefElem refLink, float x, float y)
        \brief Constructorul de initializare al clasei Hero.

        \param refLink Referinta catre un obiect ce retine o serie de referinte din program.
        \param x Pozitia initiala pe axa X a eroului.
        \param y Pozitia initiala pe axa Y a eroului.
     */
    public John(RefElem refLink, float x, float y) {

        ///Apel al constructorului clasei de baza.
        super(refLink, x, y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);
        startX = x;
        startY = y;
        ///Seteaza imaginea de start a eroului
        image = Assets.pirateStand;
        ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune.
        normalBounds.x = 3;
        normalBounds.y = 2;
        normalBounds.width = 36;
        normalBounds.height = 56;

        ///Stabileste inaltimea pamantului.
        floorHeight = y + bounds.height;

    }
    /*! \fn private void checkCollect()
        \brief Prin aceasta functie se colecteaza itemii din joc(banuti, harta)
    */
        private void checkCollect() {
            Rectangle cb = getCollisionBounds(0, 0);
            Rectangle cr = new Rectangle();
            int crSize = 20;
            cr.width = crSize;
            cr.height = crSize;

            if (refLink.GetKeyManager().space) {
                cr.x = cb.x + cb.width / 2 - crSize / 2;
                cr.y = cb.y + cb.height;
            } else
                return;

            switch (Game.level) {
                ///Caz pentru nivelul 1.
                case 1:
                    for (Item e : refLink.GetMap().getItemsManager().getEntities()) {
                        if (e.getCollisionBounds(0, 0).intersects(cr)) {
                            if (e.getId() == 1 || e.getId() == 33) {
                                if (e.getId() == 1) {
                                    if (Game.audio)
                                        playSound(goldcoin);
                                        
                                    PlayState.gcoin++;
                                    PlayState.score += 500;
                                    if (PlayState.gcoin == 4)
                                        refLink.GetMap().addEntity(1, new MapKey(refLink, 1100, 290));
                                } else {
                                    if (Game.audio)
                                        playSound(keys);
                                    PlayState.mapKEY++;
                                    PlayState.score += 2000;
                                }
                                e.hurt(3);

                            }else if(e.getId()==111 && PlayState.mapKEY !=0){

                                Game.level++;
                                Connection c = null;
                                Statement stmt = null;
                                try {
                                    Class.forName("org.sqlite.JDBC");
                                    c = DriverManager.getConnection("jdbc:sqlite:load_game.db");
                                    c.setAutoCommit(false);
                                    stmt = c.createStatement();
                                    String sql = "UPDATE LOAD_GAME set SAVED_FILE = " + Game.level + " where ID=1;";
                                    stmt.executeUpdate(sql);
                                    sql = "UPDATE LOAD_GAME set SAVED_SCORE = " + PlayState.score + " where ID=1;";
                                    stmt.executeUpdate(sql);
                                    sql = "UPDATE LOAD_GAME set SAVED_NR_LIFES = " + PlayState.lifes + " where ID=1;";
                                    stmt.executeUpdate(sql);
                                    sql = "UPDATE LOAD_GAME set KEY_1 = " + PlayState.mapKEY + " where ID=1;";
                                    stmt.executeUpdate(sql);
                                    sql = "UPDATE LOAD_GAME set KEY_2 = " + PlayState.treasureKEY + " where ID=1;";
                                    stmt.executeUpdate(sql);
                                    c.commit();
                                    stmt.close();
                                    c.close();
                                } catch (Exception e1) {
                                    System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
                                    System.exit(0);
                                }
                                PlayState.gcoin = 0;
                                if (State.GetState() == refLink.GetGame().getPlayState()) {
                                    State.SetState(refLink.GetGame().continueGame());
                                    refLink.GetMouseManager().setUIManager(refLink.GetGame().getPlayState().GetUIManager());
                                }
                            }
                            return;
                        }
                    }
                    break;
                ///Caz pentru nivelul 2.

                case 2:
                    for (Item e : refLink.GetMap().getItemsManager().getEntities()) {
                        if (e.getCollisionBounds(0, 0).intersects(cr)) {
                            if (e.getId() == 1 || e.getId() == 44) {
                                if (e.getId() == 1) {
                                    if (Game.audio)
                                        playSound(goldcoin);
                                    PlayState.gcoin++;
                                    PlayState.score += 500;
                                    if (PlayState.gcoin == 4)
                                        refLink.GetMap().addEntity(2, new TreasureKey(refLink, 100, 290));
                                } else {
                                    if (Game.audio)
                                        playSound(keys);
                                    PlayState.treasureKEY++;
                                    PlayState.score += 2000;
                                }
                                e.hurt(3);

                            }else if(e.getId()==111 && PlayState.treasureKEY !=0){

                                Game.level++;
                                Connection c;
                                Statement stmt;
                                try {
                                    Class.forName("org.sqlite.JDBC");
                                    c = DriverManager.getConnection("jdbc:sqlite:load_game.db");
                                    c.setAutoCommit(false);
                                    stmt = c.createStatement();
                                    String sql = "UPDATE LOAD_GAME set SAVED_FILE = " + Game.level + " where ID=1;";
                                    stmt.executeUpdate(sql);
                                    stmt.executeUpdate(sql);
                                    sql = "UPDATE LOAD_GAME set SAVED_SCORE = " + PlayState.score + " where ID=1;";
                                    stmt.executeUpdate(sql);
                                    sql = "UPDATE LOAD_GAME set SAVED_NR_LIFES = " + PlayState.lifes + " where ID=1;";
                                    stmt.executeUpdate(sql);
                                    sql = "UPDATE LOAD_GAME set KEY_1 = " + PlayState.mapKEY + " where ID=1;";
                                    stmt.executeUpdate(sql);
                                    sql = "UPDATE LOAD_GAME set KEY_2 = " + PlayState.treasureKEY + " where ID=1;";
                                    stmt.executeUpdate(sql);
                                    c.commit();
                                    stmt.close();
                                    c.close();
                                } catch (Exception e1) {
                                    System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
                                    System.exit(0);
                                }
                                PlayState.gcoin = 0;
                                if (State.GetState() == refLink.GetGame().getPlayState()) {
                                    State.SetState(refLink.GetGame().continueGame());
                                    refLink.GetMouseManager().setUIManager(refLink.GetGame().getPlayState().GetUIManager());
                                }
                            }
                            return;
                        }
                    }break;
                    ///Caz pentru nivelul 3
                case 3:
                    for (Item e : refLink.GetMap().getItemsManager().getEntities()) {
                        if (e.getCollisionBounds(0, 0).intersects(cr)) {
                            if (e.getId() == 1 || e.getId() == 55) {
                                if (e.getId() == 1) {
                                    if (Game.audio)
                                        playSound(goldcoin);
                                    PlayState.gcoin++;
                                    PlayState.score += 500;
                                    if (PlayState.gcoin == 4)
                                        refLink.GetMap().addEntity(3, new Treasure(refLink, 1100, 200));;
                                } else {
                                    if (Game.audio)
                                        playSound(keys);
                                    PlayState.treasure++;
                                    PlayState.score += 5000;
                                }
                                e.hurt(3);

                            }else if(e.getId()==111 && PlayState.treasure !=0){
                                if (Game.audio)
                                    playSound(win);
                                JOptionPane.showMessageDialog(null, "You won!\n\t\tYour FINAL score is: " + PlayState.score);
                                Game.level = 1;
                                Connection c = null;
                                Statement stmt = null;
                                int[]vv=new int[5];
                                try {
                                    Class.forName("org.sqlite.JDBC");
                                    c = DriverManager.getConnection("jdbc:sqlite:scores_game.db");
                                    c.setAutoCommit(false);
                                    stmt = c.createStatement();
                                    ResultSet rs = stmt.executeQuery("SELECT * FROM SCORES ORDER BY SCORE DESC;");
                                    int i=0;
                                    while (rs.next()) {

                                        vv[i]= rs.getInt("SCORE");
                                        i++;


                                    }
                                    int j = -1;
                                    for(i=0;i<vv.length;i++)
                                    {
                                        if(PlayState.score>vv[i]){
                                            j=i;
                                            break;
                                        }
                                    }

                                    if(j!=-1) {
                                        for (i = vv.length - 1; i > j; i--)
                                            vv[i] = vv[i--];
                                        vv[j]=PlayState.score;
                                    }

                                    String sql = "UPDATE SCORES set score = " + vv[0] +" where ID=1";
                                    stmt.executeUpdate(sql);
                                    sql = "UPDATE SCORES set score = " + vv[1] +" where ID=2";
                                    stmt.executeUpdate(sql);
                                    sql = "UPDATE SCORES set score = " + vv[2] +" where ID=3";
                                    stmt.executeUpdate(sql);
                                    sql = "UPDATE SCORES set score = " + vv[3] +" where ID=4";
                                    stmt.executeUpdate(sql);
                                    sql = "UPDATE SCORES set score = " + vv[4] +" where ID=5";
                                    stmt.executeUpdate(sql);
                                    c.commit();
                                    rs.close();
                                    stmt.close();
                                    c.close();
                                } catch (Exception e2) {
                                    System.err.println(e2.getClass().getName() + ": " + e2.getMessage());
                                    System.exit(0);
                                }
                                PlayState.score=0;
                                PlayState.lifes=3;
                                PlayState.treasure=0;
                                try {
                                    Class.forName("org.sqlite.JDBC");
                                    c = DriverManager.getConnection("jdbc:sqlite:load_game.db");
                                    c.setAutoCommit(false);
                                    stmt = c.createStatement();
                                    String sql = "UPDATE LOAD_GAME set SAVED_FILE = " + Game.level + " where ID=1;";
                                    stmt.executeUpdate(sql);
                                    sql = "UPDATE LOAD_GAME set SAVED_SCORE = " + PlayState.score + " where ID=1;";
                                    stmt.executeUpdate(sql);
                                    sql = "UPDATE LOAD_GAME set SAVED_NR_LIFES = " + PlayState.lifes + " where ID=1;";
                                    stmt.executeUpdate(sql);
                                    sql = "UPDATE LOAD_GAME set KEY_1 = " + PlayState.mapKEY + " where ID=1;";
                                    stmt.executeUpdate(sql);
                                    sql = "UPDATE LOAD_GAME set KEY_2 = " + PlayState.treasureKEY + " where ID=1;";
                                    stmt.executeUpdate(sql);

                                    c.commit();
                                    stmt.close();
                                    c.close();
                                } catch (Exception e1) {
                                    System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
                                    System.exit(0);
                                }

                                if (State.GetState() == refLink.GetGame().getPlayState() ) {
                                    Music.stopLastSong();
                                    State.SetState(refLink.GetGame().getMenuState());
                                    refLink.GetMouseManager().setUIManager(refLink.GetGame().getMenuState().GetUIManager());
                                }
                            }
                            return;
                        }
                    }break;

            }
        }

    /*! \fn  public void checkObstacle()
       \brief Aici se sesizeaza coliziunea cu obstacolele care produc damage sau au influenta asupra
        caracterului si se realizeaza diferite operatii.
    */
    public void checkObstacle() {
        if (checkEntityCollisions(xMove, yMove)) {
            if (actualCollision == 14||actualCollision == 99) {
                if (PlayState.lifes != 1) {
                    if (Game.audio)
                        playSound(hurt);
                    --PlayState.lifes;

                } else {
                    die();
                }
                x = startX;
                y = startY;
            }
        }
    }
       /*! \fn  public void die()
           \brief Prin aceasta functie se realizeaza anumite operatii atunci cand caracterul moare.
        */
       @Override
       public void die(){
           if (Game.audio)
               playSound(die);
           JOptionPane.showMessageDialog(null, "You lost!\n\t\tSCORE:" + PlayState.score);
           if (State.GetState() == refLink.GetGame().getPlayState() ) {
               State.SetState(refLink.GetGame().getMenuState());
               refLink.GetMouseManager().setUIManager(refLink.GetGame().getMenuState().GetUIManager());
           }

           Connection c;
           Statement stmt;
           int[]s=new int[5];
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
               int j = -1;
               for(i=0;i<s.length;i++)
               {
                   if(PlayState.score>s[i]){
                     j=i;
                     break;
                   }
               }

               if(j!=-1) {
                   for (i = s.length - 1; i > j; i--)
                       s[i] = s[i--];
                   s[j]=PlayState.score;
               }

               String sql = "UPDATE SCORES set score = " + s[0] +" where ID=1";
               stmt.executeUpdate(sql);
                sql = "UPDATE SCORES set score = " + s[1] +" where ID=2";
               stmt.executeUpdate(sql);
                sql = "UPDATE SCORES set score = " + s[2] +" where ID=3";
               stmt.executeUpdate(sql);
                sql = "UPDATE SCORES set score = " + s[3] +" where ID=4";
               stmt.executeUpdate(sql);
                sql = "UPDATE SCORES set score = " + s[4] +" where ID=5";
               stmt.executeUpdate(sql);
               c.commit();
               rs.close();
               stmt.close();
               c.close();
           } catch (Exception e) {
               System.err.println(e.getClass().getName() + ": " + e.getMessage());
               System.exit(0);
           }

       }
       /*! \fn public void Update()
           \brief Actualizeaza pozitia si imaginea eroului.
        */
       @Override
       public void Update() throws ZeroException {

           checkObstacle();
           checkCollect();
           ///Verifica daca a fost apasata o tasta
           GetInput();
           ///Actualizeaza pozitia
           Move();
           jumpUpdate();
           if(PlayState.score!=0 &&PlayState.score%scorecheck==0){++PlayState.lifes;scorecheck=scorecheck+5000;}
           ///Actualizeaza imaginea
           if (refLink.GetKeyManager().left && (!refLink.GetKeyManager().right)) {
               if (aux >= 5) {
                   aux = 0;
               }
               image = Assets.pirateLeft[aux];
               ++aux;
           } else {
               if (refLink.GetKeyManager().right && (!refLink.GetKeyManager().left)) {
                   if (aux >= 5) {
                       aux = 0;
                   }
                   image = Assets.pirateRight[aux];
                   ++aux;
               } else {
                   if (refLink.GetKeyManager().up)
                       image = Assets.pirateUp[1];
                   else {
                       if (falling) {
                           image = Assets.pirateDown[1];
                       }
                       else {

                           if ((refLink.GetKeyManager().left && refLink.GetKeyManager().right) ||
                                   ((!refLink.GetKeyManager().left) && (!refLink.GetKeyManager().right)))

                               image = Assets.pirateStand;
                       }
                   }
               }
           }


       }
    /*! \fn private void GetInput()
        \brief Verifica daca a fost apasata o tasta din cele stabilite pentru controlul eroului.
     */
    private void GetInput()
    {
            ///Implicit eroul nu trebuie sa se deplaseze daca nu este apasata o tasta
        xMove = 0;
        yMove = 0;
            ///Verificare apasare tasta "up"
        if(refLink.GetKeyManager().up)
        {
            jump();
        }
            ///Verificare apasare tasta "left"
        if(refLink.GetKeyManager().left&&(!refLink.GetKeyManager().right))
        {
            xMove = -speed;
        }
            ///Verificare apasare tasta "right"
        if(refLink.GetKeyManager().right&&(!refLink.GetKeyManager().left))
        {
            xMove = speed;
        }
    }

    /*! \fn public void Draw(Graphics g)
        \brief Randeaza/deseneaza eroul in noua pozitie.

        \brief g Contextul grafic in care trebuie efectuata desenarea eroului.
     */
    @Override
    public void Draw(Graphics g)
    {

            if(refLink.GetKeyManager().right&&(!refLink.GetKeyManager().left))
                g.drawImage(image, (int) x , (int) y-10, width, height, null);
            else
                if(refLink.GetKeyManager().left&&(!refLink.GetKeyManager().right))
                    g.drawImage(image, (int) x-30, (int) y-10, width, height, null);
                else
                        g.drawImage(image, (int) x, (int) y-10, width, height, null);



            ///Urmatoarele doua linii sunt pentru vizualizarea dreptunghiului de coliziune
        //g.setColor(Color.red);
        //g.fillRect((int)(x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height);
    }
    /*! \fn public int getId()
        \brief Returneaza id-ul caracterului.
     */
    @Override
    public int getId() {
        return 0;
    }
    /*! \fn public void jumpUpdate()
        \brief Functie de update a sariturii caracterului.
     */
    protected  void jumpUpdate() throws ZeroException {
            if (!isOnFloor()) {

                if (y - jumpStrength > 5) {

                    y -= jumpStrength;
                }
                    if (isOnTop()) {
                        y += jumpStrength;
                        jumpStrength = 0.21f;
                    }
                    if (isOnFloor())
                        y += jumpStrength;
                    else {
                        jumpStrength -=  0.21f;
                        if (jumpStrength < 0)
                            jumpStrength -= 0.3f;
                    }
                }

        if(!falling)
        {
            if(isOnFloor()) {
                if (Tile.TILE_HEIGHT != 0) {
                    y = (int) ((y + bounds.height) / Tile.TILE_HEIGHT) * Tile.TILE_HEIGHT + 45 - bounds.height;
                    floorHeight = y + bounds.height;
                }else{
                    throw new ZeroException("Invalid Tile Height");
                }
            }
        }
    }
}
