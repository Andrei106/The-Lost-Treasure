package PaooGame.Items;

import java.awt.*;
import java.awt.image.BufferedImage;
import PaooGame.Game;
import PaooGame.Items.Statics.MapKey;
import PaooGame.Items.Statics.TreasureKey;
import PaooGame.RefElem;
import PaooGame.Graphics.Assets;
import PaooGame.States.PlayState;
import PaooGame.States.State;
import PaooGame.Tiles.Tile;

import javax.swing.*;

/*! \class public class Enemy extends Character
    \brief Implementeaza notiunea inamic.

 */
public class Enemy extends Character {
    private BufferedImage image;    /*!< Referinta catre imaginea curenta .*/
    private static int aux = 0;     /*!< Variabila auxiliara care ajuta la desenare.*/
    private int left=0;
    /*! \fn public Enemy(RefElem refLink, float x, float y)
        \brief Constructorul de initializare al clasei.

        \param refLink Referinta catre un obiect ce retine o serie de referinte din program.
        \param x Pozitia initiala pe axa X.
        \param y Pozitia initiala pe axa Y.
     */
    public Enemy(RefElem refLink, float x, float y) {

        ///Apel al constructorului clasei de baza.
        super(refLink, x, y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);
        /*!< Pozitie inițiala(coordonate x si y)*/
        ///Seteaza imaginea de start a inamicului
        image = Assets.enemyright[0];
        ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune.
        normalBounds.x = 3;
        normalBounds.y = 2;
        normalBounds.width = 36;
        normalBounds.height = 56;


    }

    /*! \fn  public void die()
        \brief Prin aceasta functie se realizeaza anumite operatii atunci cand caracterul moare.
     */
    @Override
    public void die(){}
    /*! \fn public void Update()
        \brief Actualizeaza pozitia si imaginea .
     */
    @Override
    public void Update() {

        if(left==0)
        {
            x++;
            if (aux >= 5) {
                aux = 0;
            }
            image = Assets.enemyright[aux];
            ++aux;
            if(checkCollisions())
                left=1;
        }
        if(left==1)
        {
            x--;
            if (aux >= 5) {
                aux = 0;
            }
            image = Assets.enemyleft[aux];
            ++aux;
            if(checkCollisions())
                left=0;
        }


    }

    /*! \fn public void Draw(Graphics g)
        \brief Randeaza/deseneaza inamicul in noua pozitie.

        \brief g Contextul grafic in care trebuie efectuata desenarea.
     */
    @Override
    public void Draw(Graphics g)
    {
        if(left == 0)
            g.drawImage(image, (int) x , (int) y-10, width, height, null);
        else
        if(left==1)
            g.drawImage(image, (int) x-35, (int) y-10, width, height, null);
        else
            g.drawImage(image, (int) x, (int) y-10, width, height, null);
    }
    /*! \fn public int getId()
        \brief Returneaza id-ul caracterului.
     */
    @Override
    public int getId() {
        return 99;
    }
    /*! \fn public void jumpUpdate()
        \brief Functie de update a sariturii caracterului.
     */

}
