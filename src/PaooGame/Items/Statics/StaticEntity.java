package PaooGame.Items.Statics;


import PaooGame.Items.Item;
import PaooGame.RefElem;

    /*! \class StaticEntity extends Entity
        \brief Defineste notiunea abstracta de cheie/usa/monede din joc.
     */
public abstract class StaticEntity extends Item {
    /*! \fn public StaticEntity(RefElem handler, float x, float y, int width, int height)
        \brief Constructor de initializare a unei entitati statice.

        \param handler Un obiect de tip RefElem.
        \param x Coordonata pe orizontala.
        \param y Coordonata pe verticala.
        \param width Latimea entitatii.
        \param height Inaltimea entitatii.
     */
    public StaticEntity(RefElem handler, float x, float y, int width, int height){
        super(handler, x, y, width, height);
    }
}
