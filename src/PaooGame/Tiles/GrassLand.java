package PaooGame.Tiles;

import PaooGame.Graphics.Assets;


/*! \class public class GrassTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip sol/pamant.
 */
public class GrassLand extends Tile
{

    /*! \fn public GrassTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public GrassLand(int id)
    {
        super(Assets.grass, id);
    }

    @Override
    public boolean IsSolid()
    {
        return true;
    }

}
