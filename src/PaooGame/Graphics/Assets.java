package PaooGame.Graphics;

import java.awt.image.BufferedImage;

/*! \class public class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini, sunete, harti etc.
 */
public class Assets
{
        /// Referinte catre elementele grafice (dale) utilizate in joc.

    public  static BufferedImage[] enemyleft;
    public  static BufferedImage[] enemyright;

    public  static BufferedImage[] enemyleft2;
    public  static BufferedImage[] enemyright2;

    public  static BufferedImage[] enemyleft3;
    public  static BufferedImage[] enemyright3;

    public static BufferedImage[] pirateLeft;
    public static BufferedImage[] pirateRight;
    public static BufferedImage pirateStand;
    public static BufferedImage[] pirateUp;
    public static BufferedImage[] pirateDown;

    public static BufferedImage grass;
    public static BufferedImage rock;

    public static BufferedImage notile;
    public static BufferedImage[] play;
    public static BufferedImage[] quit;
    public static BufferedImage[] options;
    public static BufferedImage[] load;

    public static BufferedImage[] gold;

    public static BufferedImage[] doors;

    public static BufferedImage[] key;
    public static BufferedImage[] obstacle;
    public static BufferedImage[] pause;
    public static BufferedImage[] home;
    public static BufferedImage[] resume;
    public static BufferedImage[] retry;
    public static BufferedImage[] sounds;
    public static BufferedImage[] leaderbord;
    public static BufferedImage[] music;
    public static BufferedImage[] easy;
    public static BufferedImage[] normal;
    public static BufferedImage[] hard;
    public static BufferedImage[] difficulty;
    public static BufferedImage[] back;
    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */
    public static void Init()
    {
            /// Se creaza temporar un obiect SpriteSheet initializat prin intermediul clasei ImageLoader

        SpriteSheet pirate=new SpriteSheet(ImageLoader.LoadImage("/textures/mainc.png"));
        SpriteSheet grasst=new SpriteSheet(ImageLoader.LoadImage("/textures/greentiles.png"));
        SpriteSheet buttons=new SpriteSheet(ImageLoader.LoadImage("/textures/BUTTONS.png"));
        SpriteSheet enemy1=new SpriteSheet(ImageLoader.LoadImage("/textures/enemy1.png"));
        SpriteSheet enemy2=new SpriteSheet(ImageLoader.LoadImage("/textures/enemy2.png"));
        SpriteSheet enemy3=new SpriteSheet(ImageLoader.LoadImage("/textures/enemy3.png"));

        grass=grasst.crop(1,0,48,48);
        rock =grasst.crop(5*48,0*48,48,48);

        enemyright=new BufferedImage[6];
        enemyleft=new BufferedImage[6];

        enemyright[0]=enemy1.crop(0,0,337,294);
        enemyright[1]=enemy1.crop(1*337,0,337,294);
        enemyright[2]=enemy1.crop(2*337,0,337,294);
        enemyright[3]=enemy1.crop(3*337,0,337,294);
        enemyright[4]=enemy1.crop(4*337,0,337,294);
        enemyright[5]=enemy1.crop(5*337,0,337,294);
        enemyleft[0]=enemy1.crop(6*337,0,337,294);
        enemyleft[1]=enemy1.crop(7*337,0,337,294);
        enemyleft[2]=enemy1.crop(8*337,0,337,294);
        enemyleft[3]=enemy1.crop(9*337,0,337,294);
        enemyleft[4]=enemy1.crop(10*337,0,337,294);
        enemyleft[5]=enemy1.crop(11*337,0,337,294);

        enemyright2=new BufferedImage[6];
        enemyleft2=new BufferedImage[6];

        enemyright2[0]=enemy2.crop(0,0,347,293);
        enemyright2[1]=enemy2.crop(1*347,0,347,293);
        enemyright2[2]=enemy2.crop(2*347,0,347,293);
        enemyright2[3]=enemy2.crop(3*347,0,347,293);
        enemyright2[4]=enemy2.crop(4*347,0,347,293);
        enemyright2[5]=enemy2.crop(5*347,0,347,293);
        enemyleft2[0]=enemy2.crop(6*347,0,347,293);
        enemyleft2[1]=enemy2.crop(7*347,0,347,293);
        enemyleft2[2]=enemy2.crop(8*347,0,347,293);
        enemyleft2[3]=enemy2.crop(9*347,0,347,293);
        enemyleft2[4]=enemy2.crop(10*347,0,347,293);
        enemyleft2[5]=enemy2.crop(11*347,0,347,293);


        enemyright3=new BufferedImage[6];
        enemyleft3=new BufferedImage[6];

        enemyright3[0]=enemy3.crop(0,0,445,420);
        enemyright3[1]=enemy3.crop(1*445,0,445,420);
        enemyright3[2]=enemy3.crop(2*445,0,445,420);
        enemyright3[3]=enemy3.crop(3*445,0,445,420);
        enemyright3[4]=enemy3.crop(4*445,0,445,420);
        enemyright3[5]=enemy3.crop(5*445,0,445,420);
        enemyleft3[0]=enemy3.crop(6*445,0,445,420);
        enemyleft3[1]=enemy3.crop(7*445,0,445,420);
        enemyleft3[2]=enemy3.crop(8*445,0,445,420);
        enemyleft3[3]=enemy3.crop(9*445,0,445,420);
        enemyleft3[4]=enemy3.crop(10*445,0,445,420);
        enemyleft3[5]=enemy3.crop(11*445,0,445,420);

        pirateLeft = new BufferedImage[6];
        pirateRight = new BufferedImage[6];
        pirateUp = new BufferedImage[3];
        pirateDown = new BufferedImage[3];
        pirateStand=pirate.cropCharacter(1,0) ;
        pirateLeft[0]=pirate.cropCharacter(15,0);
        pirateLeft[1]=pirate.cropCharacter(16,0);
        pirateLeft[2]=pirate.cropCharacter(17,0);
        pirateLeft[3]=pirate.cropCharacter(18,0);
        pirateLeft[4]=pirate.cropCharacter(19,0);
        pirateLeft[5]=pirate.cropCharacter(20,0);

        pirateRight[0]=pirate.cropCharacter(8,0);
        pirateRight[1]=pirate.cropCharacter(9,0);
        pirateRight[2]=pirate.cropCharacter(10,0);
        pirateRight[3]=pirate.cropCharacter(11,0);
        pirateRight[4]=pirate.cropCharacter(12,0);
        pirateRight[5]=pirate.cropCharacter(14,0);

        pirateUp[0]=pirate.cropCharacter(2,0);
        pirateUp[1]=pirate.cropCharacter(3,0);
        pirateUp[2]=pirate.cropCharacter(4,0);

        pirateDown[0]=pirate.cropCharacter(5,0);
        pirateDown[1]=pirate.cropCharacter(6,0);
        pirateDown[2]=pirate.cropCharacter(7,0);


        play=new BufferedImage[2];
        options =new BufferedImage[2];
        load =new BufferedImage[2];
        quit=new BufferedImage[2];
        gold =new BufferedImage[2];
        doors=new BufferedImage[2];
        obstacle=new BufferedImage[2];
        key=new BufferedImage[3];

        home=new BufferedImage[2];
        resume=new BufferedImage[2];
        retry=new BufferedImage[2];
        pause=new BufferedImage[2];
        sounds = new BufferedImage[2];

        leaderbord=new BufferedImage[2];
        music=new BufferedImage[2];
        difficulty=new BufferedImage[2];
        easy=new BufferedImage[2];
        normal=new BufferedImage[2];
        hard=new BufferedImage[2];
        back=new BufferedImage[2];

        play[0]=ImageLoader.LoadImage("/textures/Start (Colored).png");
        play[1]=ImageLoader.LoadImage("/textures/Start.png");
        options[0]=ImageLoader.LoadImage("/textures/Options (Colored).png");
        options[1]=ImageLoader.LoadImage("/textures/Options.png");
        load[0]=ImageLoader.LoadImage("/textures/Load(Colored).png");
        load[1]=ImageLoader.LoadImage("/textures/Load.png");
        quit[0]=ImageLoader.LoadImage("/textures/Quit(Colored).png");
        quit[1]=ImageLoader.LoadImage("/textures/Quit.png");

        resume[0]=buttons.crop(0,0,240,240);
        resume[1]=buttons.crop(0,0,230,230);

        home[0]=buttons.crop(4*240,3*240,240,240);
        home[1]=buttons.crop(4*240,3*240,230,230);

        retry[0]=buttons.crop(240, 240,240,240);
        retry[1]=buttons.crop(240, 240,230,230);

        pause[0]=buttons.crop(3*240,0,240,240);
        pause[1]=buttons.crop(3*240,0,230,230);

        sounds[0]=buttons.crop(0,4*240,240,240);
        sounds[1]=buttons.crop(0,4*240,230,230);

        leaderbord[0]=ImageLoader.LoadImage("/textures/Score2.png");
        leaderbord[1]=ImageLoader.LoadImage("/textures/Score.png");

        music[0]=ImageLoader.LoadImage("/textures/music2.png");
        music[1]=ImageLoader.LoadImage("/textures/music.png");

        back[0]=ImageLoader.LoadImage("/textures/BTNBack2.png");
        back[1]=ImageLoader.LoadImage("/textures/BTNBack.png");

        music[0]=ImageLoader.LoadImage("/textures/music2.png");
        music[1]=ImageLoader.LoadImage("/textures/music.png");

        easy[0]=ImageLoader.LoadImage("/textures/Easy2.png");
        easy[1]=ImageLoader.LoadImage("/textures/Easy.png");

        normal[0]=ImageLoader.LoadImage("/textures/Normal2.png");
        normal[1]=ImageLoader.LoadImage("/textures/Normal.png");

        hard[0]=ImageLoader.LoadImage("/textures/Hard2.png");
        hard[1]=ImageLoader.LoadImage("/textures/Hard.png");

        difficulty[0]=ImageLoader.LoadImage("/textures/Difficulty2.png");
        difficulty[1]=ImageLoader.LoadImage("/textures/Difficulty.png");

        doors[0]=ImageLoader.LoadImage("/textures/DeepForestDoor.png");

        gold[0]=ImageLoader.LoadImage("/textures/Coin.png");
        key[0]=ImageLoader.LoadImage("/textures/Map.png");
        key[1]=ImageLoader.LoadImage("/textures/treasurekey.png");
        key[2]=ImageLoader.LoadImage("/textures/TreasureImage.png");
        obstacle[0]=ImageLoader.LoadImage("/textures/DMGPoison.png");
        obstacle[1]=ImageLoader.LoadImage("/textures/DMGPoison.png");


    }
}
