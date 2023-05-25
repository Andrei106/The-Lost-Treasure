package PaooGame.Items.Statics;

import PaooGame.Graphics.Assets;
import PaooGame.RefElem;
import java.awt.*;

/*! \class public class BadWater extends StaticEntity
        \brief Defineste notiunea de apa rea(otrava).
     */
public class BadWater extends StaticEntity {
    public int id = 14; /*!< Referinta catre id.*/

    /*! \fn public RedWater (RefElem handler, float x, float y)
        \brief Constructor de initializare a unei entitati Statice de tip apa rosie.

        \param handler Un obiect de tip RefElem.
        \param x Coordonata pe orizontala.
        \param y Coordonata pe verticala.
     */
    public BadWater(RefElem handler, float x, float y) {
        super(handler, x, y, 96, 20);
        bounds.x=15;
        bounds.y=0;
        bounds.width=86;
        bounds.height=20;
    }
    /*! \fn  public void die()
        \brief Functie ce defineste o actiune atunci cand o entitate statica "moare".
     */
    @Override
    public void die() {}

    /*! \fn public void Update()
        \brief Actualizeaza starea entitatii de tip apa otravita din joc.

     */
    @Override
    public void Update() {}

     /*! \fn public void Draw(Graphics g)
         \brief  Deseneaza apa otravita.
         \param g Contextul grafic in care trebuie sa deseneze enitatea pe ecran.
      */
    @Override
    public void Draw(Graphics g) {
        g.drawImage(Assets.obstacle[1],(int) x,(int) y,width,height,null);
    }

    /*! \fn public int getId()
        \brief  Returneaza id-ul
     */
    public int getId() {
        return id;
    }

    /*! \fn public void setId(int id)
        \brief  Seteaza id-ul.
        \param ID-ul entitatii.
     */
    public void setId(int id) {
        this.id = id;
    }
}
