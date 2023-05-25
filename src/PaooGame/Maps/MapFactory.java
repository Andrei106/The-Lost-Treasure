package PaooGame.Maps;

    /*! \class public class MapFactory
        \brief Implementeaza notiunea de fabrica pentru harti.
    */
public class MapFactory {
    /*! \fn public LevelMap getMap(int level)
        \brief Functie ce returneaza harta corespunzatoare nivelului selectat.
        \param level Nivelul actual.
     */
    public LevelMap getMap(int level)
    {
        if(level==1)
            return new Map1();
        if(level==2)
            return new Map2();
        if(level==3)
            return new Map3();
        return null;
    }
}
