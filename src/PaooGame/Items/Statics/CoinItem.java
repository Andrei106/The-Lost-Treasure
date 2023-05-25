package PaooGame.Items.Statics;

import PaooGame.Graphics.Assets;
import PaooGame.RefElem;
import PaooGame.Tiles.Tile;
import java.awt.*;

    /*! \class public class CoinItem extends StaticEntity
        \brief Reprezinta un obiect de tipul ban de aur
     */
public class CoinItem extends StaticEntity {
    public int id = 1; /*!< Referinta catre id.*/

    /*! \fn public CoinItem(RefElem handler, float x, float y)
        \brief Constructor de initializare a unei entitati Statice de tip recompensa.

        \param handler Un obiect de tip RefElem.
        \param x Coordonata pe orizontala.
        \param y Coordonata pe verticala.
     */
    public CoinItem(RefElem handler, float x, float y) {
        super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
    }

    /*! \fn  public void die()
        \brief Functie ce defineste o actiune atunci cand o entitate statica trebuie sa dispara ("moare").
     */
    @Override
    public void die() {}

    /*! \fn   public void Update()
           \brief  Actualizeaza starea entitatii.
     */
    @Override
    public void Update() {}

    /*! \fn private void Draw()
       \brief Deseneaza elementele grafice(banutii) in fereastra coresponzator starilor actualizate ale elementelor.
    */
    @Override
    public void Draw(Graphics g) {
        g.drawImage(Assets.gold[0],(int) x,(int) y,width,height,null);
    }

    /*! \fn public int getId()
           \brief  Returneaza id-ul entitatii.
     */
    public int getId() {
        return id;
    }

    /*! \fn public void setId(int id)
            \brief  Seteaza id-ul entitatii.
     */
    public void setId(int id) {
        this.id = id;
    }
}
