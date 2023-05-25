package PaooGame.Graphics;

import java.awt.image.BufferedImage;


/*! \class public class SpriteSheet
    \brief Clasa retine o referinta catre o imagine formata din dale (sprite sheet)

    Metoda crop() returneaza o dala de dimensiuni fixe (o subimagine) din sprite sheet
    de la adresa (x * latimeDala, y * inaltimeDala)
 */
public class SpriteSheet
{
    private BufferedImage       spriteSheet;        /*!< Referinta catre obiectul BufferedImage ce contine sprite sheet-ul.*/
    private static final int    tileWidth   = 48;   /*!< Latimea unei dale din sprite sheet.*/
    private static final int    tileHeight  = 48;   /*!< Inaltime unei dale din sprite sheet.*/

    /*! \fn public SpriteSheet(BufferedImage sheet)
        \brief Constructor, initializeaza spriteSheet.

        \param buffImg Un obiect BufferedImage valid.
     */
    public SpriteSheet(BufferedImage buffImg)
    {
            /// Retine referinta catre BufferedImage object.
        spriteSheet = buffImg;
    }

    /*! \fn public BufferedImage cropCharacter(int x, int y)
        \brief Returneaza un obiect BufferedImage ce contine o subimagine (dala).

        Subimaginea este localizata avand ca referinta punctul din stanga sus.

        \param x numarul dalei din sprite sheet pe axa x.
        \param y numarul dalei din sprite sheet pe axa y.

     */

    public BufferedImage cropCharacter(int x, int y)
    {
        /// Subimaginea (dala) este regasita in sprite sheet specificad coltul stanga sus
        /// al imaginii si apoi latimea si inaltimea (totul in pixeli). Coltul din stanga sus al imaginii
        /// se obtine inmultind numarul de ordine al dalei cu dimensiunea in pixeli a unei dale.
        return spriteSheet.getSubimage(x*591, y*500, 591, 500);
    }
    /*! \fn public BufferedImage crop(int x, int y, int width, int height )
            \brief Returneaza un obiect BufferedImage ce contine o subimagine (dala).

            \param x coltul stanga sus al dalei din sprite sheet pe axa x.
            \param y coltul stanga sus al dalei din sprite sheet pe axa y.
            \param width latimea dalei din sprite sheet.
            \param y height inaltimea dalei din sprite sheet.
         */
    public BufferedImage crop(int x, int y, int width, int height)
    {
        /// Subimaginea (dala) este regasita in sprite sheet specificad coltul stanga sus
        /// al imaginii si apoi latimea si inaltimea (totul in pixeli).
        return spriteSheet.getSubimage(x,y, width,height);
    }

}
