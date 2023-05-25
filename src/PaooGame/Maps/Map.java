package PaooGame.Maps;

import PaooGame.Exceptions.ZeroException;
import PaooGame.Game;
import PaooGame.Graphics.Background;
import PaooGame.Items.*;
import PaooGame.Items.Statics.*;
import PaooGame.RefElem;
import PaooGame.Tiles.Tile;

import java.awt.*;

/*! \class public class Map
    \brief Implementeaza notiunea de harta a jocului(cuprinde si entitatile, nu doar dalele).
 */
public class Map {
    private RefElem refLink;       /*!< o referinte catre un obiect ce contine o serie de referinte utile in program.*/
    private int width;              /*!< Latimea hartii in numar de dale.*/
    private int height;             /*!< Inaltimea hartii in numar de dale.*/
    private int spawnX,spawnY;  /*!< Locul unde va fi afisat caracterul.*/
    private int[][] tiles;     /*!< Referinta catre o matrice cu codurile dalelor ce vor construi harta.*/
    private Background backgroundLvl1 = new Background("/textures/forestlevel1.png"); /*!< Background-ul  pentru lvl. 1.*/
    private Background backgroundLvl2 = new Background("/textures/nightforestlvl2.png"); /*!< Background-ul  pentru lvl. 2.*/
    private Background backgroundLvl3 = new Background("/textures/backgroundlevel3.png"); /*!< Background-ul  pentru lvl. 3.*/

    //Entities
    private ItemsManager itemsManagerLvl1;  /*!< Managerul de entitati pentru lvl. 1*/
    private ItemsManager itemsManagerLvl2;  /*!< Managerul de entitati pentru lvl. 2*/
    private ItemsManager itemsManagerLvl3;  /*!< Managerul de entitati pentru lvl. 3*/




    /*! \fn public Map(RefElem refLink)
            \brief Constructorul de initializare al clasei.

            \param refLink Referinta catre un obiect ce contine o serie de referinte utile in program.
     */
    public Map(RefElem refLink) {
        this.refLink = refLink;
        if(Game.leveldifficulty==1) {
            ///Managerul de entitati pentru lvl. 1
            itemsManagerLvl1 = new ItemsManager(refLink, new John(refLink, 200, 562));
            itemsManagerLvl1.addEntity(new CoinItem(refLink, 900, 580));
            itemsManagerLvl1.addEntity(new CoinItem(refLink, 264, 435));
            itemsManagerLvl1.addEntity(new CoinItem(refLink, 1000, 100));
            itemsManagerLvl1.addEntity(new CoinItem(refLink, 264, 96));
            itemsManagerLvl1.addEntity(new JungleDoor(refLink, 634, 430));
            itemsManagerLvl1.addEntity(new BadWater(refLink, 654, 621));

            ///Managerul de entitati pentru lvl. 2
            itemsManagerLvl2 = new ItemsManager(refLink, new John(refLink, 200, 562));
            itemsManagerLvl2.addEntity(new CoinItem(refLink, 1030, 580));
            itemsManagerLvl2.addEntity(new CoinItem(refLink, 264, 435));
            itemsManagerLvl2.addEntity(new CoinItem(refLink, 1070, 195));
            itemsManagerLvl2.addEntity(new CoinItem(refLink, 264, 96));
            itemsManagerLvl2.addEntity(new JungleDoor(refLink, 1050, 2));
            itemsManagerLvl2.addEntity(new BadWater(refLink, 634, 621));
            itemsManagerLvl2.addEntity(new BadWater(refLink, 340, 621));


            ///Managerul de entitati pentru lvl. 3
            itemsManagerLvl3 = new ItemsManager(refLink, new John(refLink, 1050, 2));
            itemsManagerLvl3.addEntity(new CoinItem(refLink, 1050, 530));
            itemsManagerLvl3.addEntity(new CoinItem(refLink, 264, 388));
            itemsManagerLvl3.addEntity(new CoinItem(refLink, 1000, 52));
            itemsManagerLvl3.addEntity(new CoinItem(refLink, 264, 96));
            itemsManagerLvl3.addEntity(new JungleDoor(refLink, 634, 240));
            itemsManagerLvl3.addEntity(new BadWater(refLink, 290, 621));
            itemsManagerLvl3.addEntity(new BadWater(refLink, 135, 621));
            itemsManagerLvl3.addEntity(new BadWater(refLink, 654, 186));
            itemsManagerLvl3.addEntity(new BadWater(refLink, 734, 186));
            itemsManagerLvl3.addEntity(new BadWater(refLink, 935, 621));
            itemsManagerLvl3.addEntity(new BadWater(refLink, 594, 621));

        }else
            if(Game.leveldifficulty==2){
                ///Managerul de entitati pentru lvl. 1
                itemsManagerLvl1 = new ItemsManager(refLink, new John(refLink, 200, 562));
                itemsManagerLvl1.addEntity(new Enemy(refLink, 900, 560));
                itemsManagerLvl1.addEntity(new CoinItem(refLink, 1030, 580));
                itemsManagerLvl1.addEntity(new CoinItem(refLink, 264, 435));
                itemsManagerLvl1.addEntity(new CoinItem(refLink, 1000, 100));
                itemsManagerLvl1.addEntity(new CoinItem(refLink, 264, 96));
                itemsManagerLvl1.addEntity(new JungleDoor(refLink, 634, 430));


                ///Managerul de entitati pentru lvl. 2
                itemsManagerLvl2 = new ItemsManager(refLink, new John(refLink, 200, 562));
                itemsManagerLvl2.addEntity(new CoinItem(refLink, 1030, 580));
                itemsManagerLvl2.addEntity(new CoinItem(refLink, 264, 435));
                itemsManagerLvl2.addEntity(new CoinItem(refLink, 1070, 195));
                itemsManagerLvl2.addEntity(new CoinItem(refLink, 264, 96));
                itemsManagerLvl2.addEntity(new JungleDoor(refLink, 1050, 2));
                itemsManagerLvl2.addEntity(new BadWater(refLink, 634, 621));
                itemsManagerLvl2.addEntity(new BadWater(refLink, 340, 621));
                itemsManagerLvl2.addEntity(new Enemy2(refLink, 1030, 560));

                ///Managerul de entitati pentru lvl. 3
                itemsManagerLvl3 = new ItemsManager(refLink, new John(refLink, 1050, 2));
                itemsManagerLvl3.addEntity(new CoinItem(refLink, 1050, 530));
                itemsManagerLvl3.addEntity(new CoinItem(refLink, 264, 388));
                itemsManagerLvl3.addEntity(new CoinItem(refLink, 1000, 52));
                itemsManagerLvl3.addEntity(new CoinItem(refLink, 264, 96));
                itemsManagerLvl3.addEntity(new JungleDoor(refLink, 634, 240));
                itemsManagerLvl3.addEntity(new BadWater(refLink, 584, 621));
                itemsManagerLvl3.addEntity(new Enemy3(refLink, 960, 563));
                itemsManagerLvl3.addEntity(new BadWater(refLink, 654, 186));
                itemsManagerLvl3.addEntity(new BadWater(refLink, 734, 186));
                itemsManagerLvl3.addEntity(new BadWater(refLink, 290, 621));
                itemsManagerLvl3.addEntity(new BadWater(refLink, 135, 621));


            }
            else
            {
                ///Managerul de entitati pentru lvl. 1
                itemsManagerLvl1 = new ItemsManager(refLink, new John(refLink, 200, 562));
                itemsManagerLvl1.addEntity(new Enemy(refLink, 900, 560));
                itemsManagerLvl1.addEntity(new CoinItem(refLink, 1030, 580));
                itemsManagerLvl1.addEntity(new CoinItem(refLink, 264, 435));
                itemsManagerLvl1.addEntity(new CoinItem(refLink, 1000, 100));
                itemsManagerLvl1.addEntity(new CoinItem(refLink, 264, 96));
                itemsManagerLvl1.addEntity(new JungleDoor(refLink, 634, 430));
                itemsManagerLvl1.addEntity(new BadWater(refLink, 654, 621));

                ///Managerul de entitati pentru lvl. 2
                itemsManagerLvl2 = new ItemsManager(refLink, new John(refLink, 200, 562));
                itemsManagerLvl2.addEntity(new CoinItem(refLink, 1030, 580));
                itemsManagerLvl2.addEntity(new CoinItem(refLink, 264, 435));
                itemsManagerLvl2.addEntity(new CoinItem(refLink, 1070, 195));
                itemsManagerLvl2.addEntity(new CoinItem(refLink, 264, 96));
                itemsManagerLvl2.addEntity(new JungleDoor(refLink, 1050, 2));
                itemsManagerLvl2.addEntity(new Enemy(refLink, 634, 561));
                itemsManagerLvl2.addEntity(new Enemy2(refLink, 1030, 560));
                itemsManagerLvl2.addEntity(new BadWater(refLink, 340, 621));


                ///Managerul de entitati pentru lvl. 3
                itemsManagerLvl3 = new ItemsManager(refLink, new John(refLink, 1050, 2));
                itemsManagerLvl3.addEntity(new CoinItem(refLink, 1050, 530));
                itemsManagerLvl3.addEntity(new CoinItem(refLink, 264, 388));
                itemsManagerLvl3.addEntity(new CoinItem(refLink, 1000, 52));
                itemsManagerLvl3.addEntity(new CoinItem(refLink, 264, 96));
                itemsManagerLvl3.addEntity(new JungleDoor(refLink, 634, 240));
                itemsManagerLvl3.addEntity(new BadWater(refLink, 290, 621));
                itemsManagerLvl3.addEntity(new BadWater(refLink, 135, 621));
                itemsManagerLvl3.addEntity(new BadWater(refLink, 654, 186));
                itemsManagerLvl3.addEntity(new BadWater(refLink, 734, 186));
                itemsManagerLvl3.addEntity(new Enemy3(refLink, 960, 563));
                itemsManagerLvl3.addEntity(new Enemy2(refLink, 584, 567));

            }

        ///incarca harta de start.
        LoadWorld(Game.level);
        switch (Game.level){
            case 1:
                itemsManagerLvl1.getJohn().SetX(spawnX=200);
                itemsManagerLvl1.getJohn().SetY(spawnY=562);
                break;
            case 2:
                itemsManagerLvl2.getJohn().SetX(spawnX=200);
                itemsManagerLvl2.getJohn().SetY(spawnY=562);
            case 3:
                itemsManagerLvl3.getJohn().SetX(spawnX=1050);
                itemsManagerLvl3.getJohn().SetY(spawnY=2);


        }
    }
    /*! \fn public void addEntity(int id, StaticEntity e)
        \brief Functie pentru adaugarea entitatilor necesare construirii nivelului.
        \param id ID-ul entitatii.
        \param e Entitatea adaugata.
     */
    public void addEntity(int lvl, StaticEntity e)
    {
        switch(lvl) {
            case 1:
                itemsManagerLvl1.addEntity(e);
                break;
            case 2:
                itemsManagerLvl2.addEntity(e);
                break;
            case 3:
                itemsManagerLvl3.addEntity(e);
                break;

        }
    }
    /*! \fn public  void Update()
        \brief Actualizarea hartii in functie de evenimente
     */
    public void Update() throws ZeroException {
        switch(Game.level) {
            case 1:
                itemsManagerLvl1.Update();
                break;
            case 2:
                itemsManagerLvl2.Update();
                break;
            case 3:
                itemsManagerLvl3.Update();
                break;
            default:
                itemsManagerLvl1.Update();
                break;
        }

    }

    /*! \fn public void Draw(Graphics g)
        \brief Functia de desenare a hartii.

        \param g Contextul grafic in care se realizeaza desenarea.
     */
    public void Draw(Graphics g) {
        switch(Game.level){
            case 1:
                backgroundLvl1.Draw(g);
                break;
            case 2:
                backgroundLvl2.Draw((g));
                break;
            case 3:
                backgroundLvl3.Draw((g));
                break;
            default:
                backgroundLvl1.Draw(g);
        }
        ///Se parcurge matricea de dale (codurile aferente) si se deseneaza harta respectiva
        for (int y = 0; y <= refLink.GetGame().GetHeight() / Tile.TILE_HEIGHT; y++) {
            for (int x = 0; x < refLink.GetGame().GetWidth() / Tile.TILE_WIDTH; x++) {
                GetTile(x, y).Draw(g, x * Tile.TILE_HEIGHT, y * Tile.TILE_WIDTH);

            }
        }
        switch(Game.level){
            case 1:
                itemsManagerLvl1.Draw(g);
                break;
            case 2:
                itemsManagerLvl2.Draw(g);
                break;
            case 3:
                itemsManagerLvl3.Draw(g);
                break;
            default:
                itemsManagerLvl1.Draw(g);
        }

    }

    /*! \fn public Tile GetTile(int x, int y)
        \brief Intoarce o referinta catre dala aferenta codului din matrice de dale.

     */
    public Tile GetTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {

            return Tile.rock;
        }
        Tile t = Tile.tiles[tiles[x][y]];
        if (t == null) {
            return Tile.rock;
        }
        return t;
    }

    /*! \fn private void LoadWorld()
        \brief Functie de incarcare a hartii jocului.
        Aici se poate genera sau incarca din fisier harta. Momentan este incarcata static.
     */
    private void LoadWorld(int level) {
        width = 27;
        height = 14;
        ///Se construieste matricea de coduri de dale
        tiles = new int[width][height];
        //Se incarca matricea
        MapFactory selectMap = new MapFactory();
        LevelMap map = selectMap.getMap(level);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x][y] = map.getMap(y, x);
            }
        }

    }

    /*! \fn public Polygon getBounds(int x, int y)
        \brief Functie ce returneaza poligonul de coliziune.
        \param x Pozitia pe axa Ox a dalei.
        \param y Pozitia pe axa Oy a dalei.
     */
    public Polygon getBounds(int x, int y) {
        if (GetTile(x, y) == Tile.gland) {
            int[] a = {x, x + Tile.TILE_WIDTH, x + Tile.TILE_WIDTH, x};
            int[] b = {y, y, y + Tile.TILE_HEIGHT, y + Tile.TILE_HEIGHT};
            return new Polygon(a, b, a.length);
        } else {
            int[] a = {x, x + Tile.TILE_WIDTH, x + Tile.TILE_WIDTH, x};
            int[] b = {y, y, y + Tile.TILE_HEIGHT, y + Tile.TILE_HEIGHT};
            return new Polygon(a, b, a.length);
        }

    }

    /*! \fn public ItemsManager getItemsManager()
        \brief Functie ce returneaza managerul de entitati specific fiecarui nivel.
     */
    public ItemsManager getItemsManager() {
        switch(Game.level){
            case 1:
                return itemsManagerLvl1;
            case 2:
                return itemsManagerLvl2;
            case 3:
                return itemsManagerLvl3;
            default:
                return itemsManagerLvl1;
        }
    }
}

