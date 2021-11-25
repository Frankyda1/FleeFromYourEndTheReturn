
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.input.KeyCode;




public class GameScene extends Scene {
    int CNT = 1;
    public Camera camera ;
    private final int desertSizeX=800;
    private final int desertSizeY=400;
    public double GameSpeed=10;
    public EnergyBall EnergyBall;
    public StaticThing left;
    public StaticThing middle;
    public StaticThing right;
    public Hero hero;
    public StaticThing HP_BAR;
    public DifficultyHandler Difficulty;

    public GameScene(Pane pane, double v, double v1, boolean b) {
        super(pane, v, v1, b);
        this.hero =       new Hero(110,250,0,0,100000000,6,77,100,77,100,10,"file:Img/heros.png");;
        this.EnergyBall = new EnergyBall(100, -100, 1,0,100000000,3,50,60,50,60,10,"file:Img/NRJ.png");
        this.camera =     new Camera(0,0);
        this.Difficulty=  new DifficultyHandler();
        EnergyBall.SetLocky();

        this.left=new StaticThing("file:Img/desert.png",-desertSizeX,0,0,0);
        left.getImageView().setViewport(new Rectangle2D(0,0,desertSizeX,desertSizeY));
        pane.getChildren().add(left.getImageView());

        this.middle=new StaticThing("file:Img/desert.png",0,0,0,0);
        middle.getImageView().setViewport(new Rectangle2D(0,0,desertSizeX,desertSizeY));
        pane.getChildren().add(middle.getImageView());

        this.right=new StaticThing("file:Img/desert.png",desertSizeX,0,0,0);
        right.getImageView().setViewport(new Rectangle2D(0,0,desertSizeX,desertSizeY));
        pane.getChildren().add(right.getImageView());

        this.HP_BAR=new StaticThing("file:Img/BarreDePv.png",20,0,180,200);
        pane.getChildren().add(HP_BAR.getImageView());


        //Stop gunnning when key released
        this.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.SHIFT) {
                    hero.attitude=0;
                }
            }
        });

        this.setOnKeyPressed(new EventHandler<KeyEvent>() {

            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode()==KeyCode.D) {
                    hero.Turn(1);
                    hero.addSpeed(2);
                }
                else if(keyEvent.getCode()==KeyCode.Q){
                    hero.Turn(-1);
                    hero.addSpeed(-2);
                }
                else if(keyEvent.getCode()==KeyCode.SHIFT) {
                    hero.attitude=1;
                    EnergyBall.Shoot(hero.getX(),hero.getY());
                }
                else if(keyEvent.getCode()==KeyCode.SPACE){
                    hero.jump();

                }
            }
        });
    }

    public void Update_Images() {
        // Permutation des images
        hero.getImageView().setX(hero.getX() - camera.getX());
        hero.getImageView().setY(hero.getY() - camera.getY());

        right.getImageView().setY(-camera.getY());
        middle.getImageView().setY(-camera.getY());
        left.getImageView().setY(-camera.getY());

        EnergyBall.getImageView().setX((EnergyBall.getX() - camera.getX()));
        EnergyBall.getImageView().setY((EnergyBall.getY() - camera.getY()));

        hero.setSpeed(GameSpeed);

        camera.setSpeed(GameSpeed);


        GameSpeed=10+Difficulty.GetDiff()*5;


        HP_BAR.getImageView().setViewport(new Rectangle2D(30, 220 - (6 - hero.pv) * 41, 135, 30));

        if (camera.getX() > desertSizeX * CNT) {

            CNT += 1;
        }
        if (CNT % 3 == 0) {
            left.getImageView().setX(desertSizeX * (CNT - 1) - camera.getX());
            middle.getImageView().setX(desertSizeX * (CNT) - camera.getX());
            right.getImageView().setX(desertSizeX * (CNT + 1) - camera.getX());

        } else if (CNT % 3 == 1) {
            left.getImageView().setX(desertSizeX * (CNT + 1) - camera.getX());
            middle.getImageView().setX(desertSizeX * (CNT - 1) - camera.getX());
            right.getImageView().setX(desertSizeX * (CNT) - camera.getX());
        } else if (CNT % 3 == 2) {
            left.getImageView().setX(desertSizeX * (CNT) - camera.getX());
            middle.getImageView().setX(desertSizeX * (CNT + 1) - camera.getX());
            right.getImageView().setX(desertSizeX * (CNT - 1) - camera.getX());

        }
        if (EnergyBall.getX() > camera.getX() + 2*desertSizeX) {
            EnergyBall.reset();
            EnergyBall.CanShoot = Boolean.TRUE;
        }

        for (int a = 0; a < Difficulty.Foes.length; a++) {

            Difficulty.Foes[a].getImageView().setX((Difficulty.Foes[a].getX() - camera.getX()));
            Difficulty.Foes[a].getImageView().setY((Difficulty.Foes[a].getY() - camera.getY()));
            if (Difficulty.Foes[a].getX()<0){
                Difficulty.Foes[a].Reset(0);
            }

            if (hero.GetHitbox().intersects(Difficulty.Foes[a].GetHitbox())) {
                if(!hero.Invincibility) {
                    hero.SetPV(hero.pv - 1);
                    hero.Invincibility = true;

                }
            }
            if (EnergyBall.GetHitbox().intersects(Difficulty.Foes[a].GetHitbox())) {
                Difficulty.Foes[a].Reset(hero.getX());
                Difficulty.Foes[a].imageView.setVisible(false);
                Difficulty.Foes[a].Reset(0);
                Difficulty.AddScore();

                System.out.println("Touché");
            }
            if(Difficulty.Foes[a].getX()< camera.getX()){
                Difficulty.Foes[a].Reset(-100);
            }
        }
    }
}



