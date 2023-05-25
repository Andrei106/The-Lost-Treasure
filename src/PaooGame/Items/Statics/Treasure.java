package PaooGame.Items.Statics;

import PaooGame.Graphics.Assets;
import PaooGame.RefElem;
import PaooGame.Tiles.Tile;
import java.awt.*;

/*! \class public class Treasure extends StaticEntity
\brief Defineste notiunea de cheie pentru a putea trece la nivelul urmator.
*/
public class Treasure extends StaticEntity {
public int id = 55; /*!< Referinta catre id.*/

/*! \fn public Treasure(RefElem handler, float x, float y)
    \brief Constructor de initializare a unei entitati Statice de tip cheie.

    \param handler Un obiect de tip RefElem.
    \param x Coordonata pe orizontala.
    \param y Coordonata pe verticala.
 */
public Treasure(RefElem handler, float x, float y) {
    super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
}

 /*! \fn  public void die()
     \brief Functie ce defineste o actiune atunci cand o entitate statica dispare.
  */
@Override
public void die() {}

/*! \fn public void Update()
    \brief Actualizeaza starea entitatii de tip cheie din joc.
 */
@Override
public void Update() {}

/*! \fn public void Draw(Graphics g)
    \brief  Deseneaza cheia de tip comoara pe ecran.
    \param g Contextul grafic in care trebuie sa deseneze enitatea pe ecran.
 */
@Override
public void Draw(Graphics g) {
    g.drawImage(Assets.key[2],(int) x,(int) y,width,height,null);
}

/*! \fn public int getId()
    \brief  Returneaza id-ul
 */
public int getId() {
    return id;
}

/*! \fn public void setId(int id)
    \brief  Seteaza id-ul.
 */
public void setId(int id) {
    this.id = id;
}
}
