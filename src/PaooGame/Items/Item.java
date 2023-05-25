package PaooGame.Items;

import PaooGame.Exceptions.ZeroException;
import PaooGame.Game;
import PaooGame.Maps.Map;
import PaooGame.RefElem;
import PaooGame.Tiles.Tile;

import java.awt.*;

    /*! \class Item
        \brief Implementeaza notiunea abstracta de entitate activa din joc.
    */
public abstract class Item
{
    public static final int DEFAULT_LIFE = 3;   /*!< Valoarea implicita a vietii unui caracter.*/
    protected float x;                  /*!< Pozitia pe axa X a "tablei" de joc a imaginii entitatii.*/
    protected float y;                  /*!< Pozitia pe axa Y a "tablei" de joc a imaginii entitatii.*/
    protected int width;                /*!< Latimea imaginii entitatii.*/
    protected int height;               /*!< Inaltimea imaginii entitatii.*/
    protected Rectangle bounds;         /*!< Dreptunghiul curent de coliziune.*/
    protected Rectangle normalBounds;   /*!< Dreptunghiul de coliziune aferent starii obisnuite.*/
    protected RefElem refLink;         /*!<  referinte catre un obiect ce contine o serie de referinte utile in program.*/
    protected final float GRAVITY=0.3f; /*!< Constanta gravitationala*/
    protected final int MAX_VELOCITY=5; /*!< Viteza maxima a caracterului.*/
    protected boolean falling =true;    /*!< Variabila pentru a verifica daca se afla in aer caracterul.*/
    protected float jy;                 /*!< Variabila utilizata la functia de jump.*/
    protected float floorHeight;        /*!< Pozitia pamantului.*/
    protected boolean active = true;    /*!< Variabila ce retine valoarea de adevar a variabilei active folosita la coliziune.*/
    protected int life;                 /*!< Retine viata caracterului.*/
    protected  int actualCollision;     /*!< Variabila ce retine id-ul entitatii statice cu care a avut loc coliziunea.*/

    /*! \fn public Item(RefElem refLink, float x, float y, int width, int height)
        \brief Constructor de initializare al clasei Item.

        \param  reflink Referinta catre un obiect ce retine o serie de referinte din program.
        \param  x   Pozitia pe axa X a "tablei" de joc a imaginii entitatii.
        \param  y   Pozitia pe axa Y a "tablei" de joc a imaginii entitatii.
        \param  width   Latimea imaginii entitatii.
        \param  height  Inaltimea imaginii entitatii.
     */
    public Item(RefElem refLink, float x, float y, int width, int height)
    {
        this.x = x;             /*!< Retine coordonata pe axa X.*/
        this.y = y;             /*!< Retine coordonata pe axa X.*/
        this.width = width;     /*!< Retine latimea imaginii.*/
        this.height = height;   /*!< Retine inaltimea imaginii.*/
        this.refLink = refLink; /*!< Retine the "shortcut".*/

            ///Creaza dreptunghi de coliziune pentru modul normal.
        normalBounds = new Rectangle(0, 0, width, height);

            ///Dreptunghiul de coliziune implicit este setat ca fiind cel normal.
        bounds = normalBounds;

    }
    /*! \fn public abstract void die()
        \brief Metoda va fi implementata de clasele ce o vor extinde.
     */
    public abstract void die();

    /*! \fn public void hurt(int tmp)
        \brief Metoda ce scade viata entitatii in anumite conditii si seteaza flag-ul "active" astfel incat sa se semnaleze daca entitatea trebuie sa dispara de pe ecran.
        \param damage - scade din viata entitatii.
     */
    public void hurt(int damage){
        life -= damage;
        if(life <= 0){
            active = false;
            die();
        }
    }

    /*! \fn public boolean checkEntityCollisions(float xOffset,float yOffset)
       \brief Metoda ce verifica daca a avut loc coliziune intre player si alte entitati din joc.
       \param xOffset Pozitia pe axa Ox a jucatorului.
       \param yOffset Pozitia pe axa Oy a jucatorului.

    */
    public boolean checkEntityCollisions(float xOffset,float yOffset){
        for(Item e: refLink.GetMap().getItemsManager().getEntities()){
            if(e.equals(this))
                continue;
            if(e.getCollisionBounds(0f,0f).intersects(getCollisionBounds(xOffset,yOffset)))
            {
                ///Se salveaza id-ul entitatii "lovite".
                actualCollision=e.getId();

                return true;
            }
            else
                actualCollision=0;

        }
        return false;
    }
    /*! \fn public Rectangle getCollisionBounds(float xOffset, float yOffset)
        \brief Metoda ce returneaza un dreptunghi de coliziune pentru entitati.
        \param xOffset Pozitia pe axa Ox a entitatii.
        \param yOffset Pozitia pe axa Oy a entitatii.
      */
        public Rectangle getCollisionBounds(float xOffset, float yOffset){
            return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);

        }
    /*! \fn private boolean checkCollisions()
         \brief Sunt verificate coliziunile in colturile dreptunghiului de coliziune si in mijlocul laturilor verticale.
     */
    protected boolean checkCollisions(){
        Map m=refLink.GetMap();
        if(m.GetTile((int) ((x+5) / Tile.TILE_WIDTH), (int) ((y+13) / Tile.TILE_HEIGHT)).IsSolid()&&
                m.getBounds((int) ((x+5) / Tile.TILE_WIDTH),(int) ((y+13) / Tile.TILE_HEIGHT)).intersects(bounds.getBounds()))
            return true;
        if(m.GetTile((int) ((x+bounds.width) / Tile.TILE_WIDTH), (int) ((y+13) / Tile.TILE_HEIGHT)).IsSolid()&&
                m.getBounds((int)((x + bounds.width) / Tile.TILE_WIDTH), (int) ((y+13) / Tile.TILE_HEIGHT)).intersects(bounds.getBounds()))
            return true;
        if(m.GetTile((int) ((x+5) / Tile.TILE_WIDTH), (int) ((y+bounds.height) / Tile.TILE_HEIGHT)).IsSolid()&&
                m.getBounds((int) ((x+5) / Tile.TILE_WIDTH),(int) ((y+bounds.height) / Tile.TILE_HEIGHT)).intersects(bounds.getBounds())) {

            return true;
        }
        if(m.GetTile((int) ((x+bounds.width) / Tile.TILE_WIDTH), (int) ((y+bounds.height) / Tile.TILE_HEIGHT)).IsSolid()&&
                m.getBounds((int)((x + bounds.width) / Tile.TILE_WIDTH), (int) ((y+bounds.height) / Tile.TILE_HEIGHT)).intersects(bounds.getBounds())) {
            return true;
        }
        if(m.GetTile((int) ((x+5) / Tile.TILE_WIDTH), (int) ((y+bounds.height/2) / Tile.TILE_HEIGHT)).IsSolid()&&
                m.getBounds((int) ((x+5) / Tile.TILE_WIDTH),(int) ((y+bounds.height/2) / Tile.TILE_HEIGHT)).intersects(bounds.getBounds()))
            return true;

        return m.GetTile((int) ((x + bounds.width) / Tile.TILE_WIDTH), (int) ((y + bounds.height / 2) / Tile.TILE_HEIGHT)).IsSolid() &&
                m.getBounds((int) ((x + bounds.width) / Tile.TILE_WIDTH), (int) ((y + bounds.height / 2) / Tile.TILE_HEIGHT)).intersects(bounds.getBounds());

    }
    /*! \fn private boolean isOnFloor()
         \brief Prin aceasta functie se verifica daca jucatorul se afla pe pamant sau in aer.
     */
    protected  boolean isOnFloor()
    {

        Map m=refLink.GetMap();
        if(m.GetTile((int) ((x+5) / Tile.TILE_WIDTH), (int) ((y+bounds.height+5) / Tile.TILE_HEIGHT)).IsSolid()&&
                m.getBounds((int) ((x+5) / Tile.TILE_WIDTH),(int) ((y+bounds.height+5) / Tile.TILE_HEIGHT)).intersects(bounds.getBounds())) {
            return true;
        }
        if(m.GetTile((int) ((x+bounds.width) / Tile.TILE_WIDTH), (int) ((y+bounds.height+5) / Tile.TILE_HEIGHT)).IsSolid()&&
                m.getBounds((int)((x + bounds.width) / Tile.TILE_WIDTH), (int) ((y+bounds.height+5) / Tile.TILE_HEIGHT)).intersects(bounds.getBounds())) {
            return true;
        }
        return m.GetTile((int) ((x + bounds.width / 2) / Tile.TILE_WIDTH), (int) ((y + bounds.height + 5) / Tile.TILE_HEIGHT)).IsSolid() &&
                m.getBounds((int) ((x + bounds.width / 2) / Tile.TILE_WIDTH), (int) ((y + bounds.height + 5) / Tile.TILE_HEIGHT)).intersects(bounds.getBounds());
    }
    /*! \fn private boolean isOnTop()
             \brief Prin aceasta functie se verifica daca jucatorul loveste un obstacol cu capul.
     */
    protected  boolean isOnTop()
    {
        Map m=refLink.GetMap();

        if(m.GetTile((int) ((x+5) / Tile.TILE_WIDTH), (int) ((y+20) / Tile.TILE_HEIGHT)).IsSolid()&&
                m.getBounds((int) ((x+5) / Tile.TILE_WIDTH),(int) ((y+20) / Tile.TILE_HEIGHT)).intersects(bounds.getBounds()))
            return true;
        if(m.GetTile((int) ((x+bounds.width) / Tile.TILE_WIDTH), (int) ((y+20) / Tile.TILE_HEIGHT)).IsSolid()&&
                m.getBounds((int)((x + bounds.width) / Tile.TILE_WIDTH), (int) ((y+20) / Tile.TILE_HEIGHT)).intersects(bounds.getBounds()))
            return true;
        return m.GetTile((int) ((x + bounds.width/2) / Tile.TILE_WIDTH), (int) ((y+20) / Tile.TILE_HEIGHT)).IsSolid() &&
                m.getBounds((int) ((x + bounds.width/2) / Tile.TILE_WIDTH), (int) ((y+20) / Tile.TILE_HEIGHT)).intersects(bounds.getBounds());
    }
    /*! \fn private  void fall()
        \brief Prin aceasta functie se implementeaza forta gravitationala.
     */
    protected void fall() {
        if (isOnFloor()) {
            jy = 0;
            falling = false;
        } else
            falling = true;
        if (falling) {
            jy += GRAVITY;
            if (jy > MAX_VELOCITY)
                jy = MAX_VELOCITY;
            y += jy;
            if (checkCollisions())
                y -= jy;
        }
    }

    /*! \fn  public abstract void Update()
        \brief Actualizeaza starea entitatilor din joc.
     */
    public abstract void Update() throws ZeroException;
    /*! \fn public abstract void Draw(Graphics g)
        \brief Deseneaza entitatile grafice in fereastra coresponzator starilor actualizate ale elementelor.
        \param g Contextul grafic in care se deseneaza.
    */
    public abstract void Draw(Graphics g);

    /*! \fn public float GetX()
        \brief Returneaza coordonata pe axa X.
     */
    public float GetX()
    {
        return x;
    }

    /*! \fn public float GetY()
        \brief Returneaza coordonata pe axa Y.
     */
    public float GetY()
    {
        return y;
    }

    /*! \fn public float GetWidth()
        \brief Returneaza latimea entitatii.
     */
    public int GetWidth()
    {
        return width;
    }

    /*! \fn public float GetHeight()
        \brief Returneaza inaltimea entitatii.
     */
    public int GetHeight()
    {
        return height;
    }

    /*! \fn public float SetX()
        \brief Seteaza coordonata pe axa X.
        \param x Coordonata pe axa X.
     */
    public void SetX(float x)
    {
        this.x = x;
    }

    /*! \fn public float SetY()
        \brief Seteaza coordonata pe axa Y.
        \param y Coordonata pe axa Y.
     */
    public void SetY(float y)
    {
        this.y = y;
    }

    /*! \fn public float SetWidth()
        \brief Seteaza latimea imaginii entitatii.
        \param width Latimea imaginii.
     */
    public void SetWidth(int width)
    {
        this.width = width;
    }

    /*! \fn public float SetHeight()
        \brief Seteaza inaltimea imaginii entitatii.
        \param height Inaltimea imaginii.
     */
    public void SetHeight(int height)
    {
        this.height = height;
    }

    /*! \fn public boolean isActive()
            \brief Se verifica daca o entitate mai este prezenta in joc sau nu.
     */
    public boolean isActive() {
        return active;
    }

    /*! \fn  public abstract int getId()
        \brief Returneaza id-ul entitatilor.
     */
    public abstract int getId();
}
