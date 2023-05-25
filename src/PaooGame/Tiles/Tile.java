package PaooGame.Tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class public class Tile
    \brief Retine toate dalele intr-un vector si ofera posibilitatea regasirii dupa un id.
 */
public class Tile
{
    private static final int NR_TILES = 32;
    public static Tile[] tiles= new Tile[NR_TILES];       /*!< Vector de referinte de tipuri de dale.*/
    public static Tile misstiles = new Nothing(0);     /*!< Dala de tip spatiu*/
    public static Tile gland = new GrassLand(4);
    public static Tile rock = new RockTile(8);
    public static final int TILE_WIDTH  = 48;                     /*!< Latimea unei dale.*/
    public static final int TILE_HEIGHT = 48;                     /*!< Inaltimea unei dale.*/

    protected BufferedImage img;                                  /*!< Imaginea aferenta tipului de dala.*/
    protected final int id;                                       /*!< Id-ul unic aferent tipului de dala.*/

    /*! \fn public Tile(BufferedImage texture, int id)
        \brief Constructorul aferent clasei.

        \param image Imaginea corespunzatoare dalei.
        \param id Id-ul dalei.
     */
    public Tile(BufferedImage image, int idd)
    {
        img = image;
        id = idd;
        tiles[id] = this;
    }

    /*! \fn public void Update()
        \brief Actualizeaza proprietatile dalei.
     */
    public void Update()
    {
    }

    /*! \fn public void Draw(Graphics g, int x, int y)
        \brief Deseneaza in fereastra dala.

        \param g Contextul grafic in care sa se realizeze desenarea.
        \param x Coordonata x in cadrul ferestrei unde sa fie desenata dala.
        \param y Coordonata y in cadrul ferestrei unde sa fie desenata dala.
     */
    public void Draw(Graphics g, int x, int y)
    {
            /// Desenare dala
        g.drawImage(img, x, y, TILE_WIDTH, TILE_HEIGHT, null);

    }

    /*! \fn public boolean IsSolid()
        \brief Returneaza proprietatea de dala solida (supusa coliziunilor) sau nu.
     */
    public boolean IsSolid()
    {
        return false;
    }

    /*! \fn public int GetId()
        \brief Returneaza id-ul dalei.
     */
    public int GetId()
    {
        return id;
    }


}
