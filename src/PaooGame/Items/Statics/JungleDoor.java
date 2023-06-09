package PaooGame.Items.Statics;

import PaooGame.Graphics.Assets;
import PaooGame.RefElem;


import java.awt.*;

    /*! \class JungleDoor extends StaticEntity
        \brief Defineste notiunea abstracta de usa de trecere la urmatorul nivel.
     */
public class JungleDoor extends StaticEntity {

    public int id = 111; /*!< Referinta catre id.*/

    /*! \fn   public JungleDoor(RefElem refLink, float x, float y)
        \brief Constructor de initializare a unei entitati Statice de tip usa .

        \param refLink Un obiect de tip RefElem.
        \param x Pozitia pe axa Ox a entitatii.
        \param y Pozitia pe axa Oy a entitatii.
     */
    public JungleDoor(RefElem refLink, float x, float y) {
        super(refLink, x, y, 75, 100);
    }

    /* \fn  public void die()
            \brief Functie ce defineste o actiune atunci cand o entitate statica dispare.
     */
    @Override
    public void die() {}

    /*! \fn public void Update()
        \brief Actualizeaza starea entitatii de tip usa din joc.
     */
    @Override
    public void Update() {}

    /*! \fn public void Draw(Graphics g)
       \brief Deseneaza usa pe ecran.

          \param g Contextul grafic in care trebuie sa deseneze enitatea pe ecran.
    */
    @Override
    public void Draw(Graphics g) {
        g.drawImage(Assets.doors[0],(int) x,(int) y,width,height,null);

    }

    /*! \fn public int getId()
            \brief  Returneaza  id-ul entitatii.
         */
    public int getId() {
        return id;
    }

    /*! \fn public void setId(boolean id)
            \brief Seteaza id-ul entitatii.
            \param id Id-ul entitatii.
    */
    public void setId(int id) {
        this.id = id;
    }
}