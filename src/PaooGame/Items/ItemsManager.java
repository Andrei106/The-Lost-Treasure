package PaooGame.Items;

import PaooGame.Exceptions.ZeroException;
import PaooGame.RefElem;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

    /*! \class public ItemsManager
        \brief Defineste notiunea de manager de entitati in joc.
    */
public class ItemsManager {
    private RefElem handler;          /*!< Referinta catre un obiect a carui sarcina este doar de a retine diverse referinte pentru a fi usor accesibile.*/
    private John john;       /*!< Player de tip John.*/
    private ArrayList<Item> entities;  /*!< Array de entitati din joc specifice fiecarui nivel.*/
    private Comparator<Item> comparator = (e1, e2) -> {
        if (e1.GetWidth() < e2.GetWidth())
            return -1;
        return 1;
    }; /*!< Variabila de tip Comparator.*/

    /*! \fn public ItemsManager(RefElem reflink,John john)
        \brief Constructor de initializare al clasei ItemsManager.

        \param reflink Referinta catre un obiect ce retine o serie de referinte din program..
        \param john Entitate de tip personaj principal.

     */
    public ItemsManager(RefElem reflink, John john){
        this.handler = reflink;
        this.john = john;
        entities = new ArrayList<>();
        addEntity(john);
    }
    /*! \fn  public void Update()
        \brief Actualizeaza starea entitatilor din joc.
     */
    public void Update() throws ZeroException {
        for(int i=0;i<entities.size();++i) {
            Item e =entities.get(i);
            e.Update();
            if (!e.isActive()) {
                entities.remove(e);
            }
        }
        entities.sort(comparator);
    }
    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza entitatile grafice in fereastra jocului, coresponzator starilor actualizate ale elementelor.

        \param g Contextul grafic in care se deseneaza.
     */
    public void Draw(Graphics g){
        for (Item e : entities) {
            e.Draw(g);
        }
    }
    /*! \fn public void addEntity(Item e)
        \brief Functie prin care se adauga entitati in joc.
        \param e Entitatea adaugata la array.
     */
    public void addEntity(Item e){
        entities.add(e);
    }


    /*! \fn public  RefLinks getHandler()
        \brief Functie prin care se obtine o referinta la ItemManager.
     */
    public RefElem getHandler() {
        return handler;
    }
    /*! \fn public  John getJohn()
        \brief Functie prin care se obtine o referinta la playerul de tip john.
     */
    public John getJohn() {
        return john;
    }
    /*! \fn public  ArrayList<Item> getEntities()
        \brief Functie prin care se obtine array-ul de entitati.
     */
    public ArrayList<Item> getEntities() {
        return entities;
    }
}

