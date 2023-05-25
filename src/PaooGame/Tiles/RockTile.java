package PaooGame.Tiles;

import PaooGame.Graphics.Assets;


/*! \class public class RockTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip piatra.
 */
public class RockTile extends Tile
{

    /*! \fn public RockTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public RockTile(int id)
    {
        super(Assets.rock, id);
    }

    @Override
    public boolean IsSolid()
    {
        return true;
    }

}
