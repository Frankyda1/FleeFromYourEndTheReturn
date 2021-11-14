import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.input.KeyCode;


public class GameScene extends Scene {
    int CNT = 1;
    public Camera camera ;
    private final int desertSizeX=800;
    private final int desertSizeY=400;
    public EnergyBall EnergyBall;
    public StaticThing left;
    public StaticThing middle;
    public StaticThing right;
    public AnimationTimer timer;
    public Hero hero;
    public Pane pane;
    public StaticThing Rock;

    public GameScene(Pane pane, double v, double v1, boolean b) {
        super(pane, v, v1, b);
        this.hero = new Hero(110,250,0,0,100000000,6,77,100,10,"file:Img/heros.png");;
        this.EnergyBall = new EnergyBall(100, -100, 1,0,100000000,3,50,60,10,"file:Img/NRJ.png");
        this.camera = new Camera(100,0);

        this.left=new StaticThing("file:Img/desert.png",-desertSizeX,0);
        left.getImageView().setViewport(new Rectangle2D(0,0,desertSizeX,desertSizeY));
        pane.getChildren().add(left.getImageView());

        this.middle=new StaticThing("file:Img/desert.png",0,0);
        middle.getImageView().setViewport(new Rectangle2D(0,0,desertSizeX,desertSizeY));
        pane.getChildren().add(middle.getImageView());

        this.right=new StaticThing("file:Img/desert.png",desertSizeX,0);
        right.getImageView().setViewport(new Rectangle2D(0,0,desertSizeX,desertSizeY));
        pane.getChildren().add(right.getImageView());

        this.Rock=new StaticThing("file:Img/Rock.png",desertSizeX- camera.getX(),270);
        Rock.getImageView().setFitWidth(100);
        Rock.getImageView().setFitHeight(100);
        Rock.getImageView().setRotate(-90);
        Rock.getImageView().setViewport(new Rectangle2D(0,0,desertSizeX,desertSizeY));
        pane.getChildren().add(Rock.getImageView());




        //Stop gunnning when key released
        this.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.SHIFT) {
                    hero.attitude=0;
                }
            }

            ;
        });

        this.setOnKeyPressed(new EventHandler<KeyEvent>() {

            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode()==KeyCode.D) {
                    hero.addForce(10);
                }
                else if(keyEvent.getCode()==KeyCode.Q){
                    hero.addForce(-10);
                }
                else if(keyEvent.getCode()==KeyCode.SHIFT) {
                    hero.attitude=1;
                    EnergyBall.attitude=1;
                    EnergyBall.Shoot(hero.getX(),hero.getY());
                }
                else if(keyEvent.getCode()==KeyCode.SPACE){
                    hero.jump();
                }
            }
        });
    }





    public StaticThing getLeft() {
        return left;
    }

    public StaticThing getRight() {
        return right;
    }

    public void Permute_Images(){
        // Permutation des images
        right.getImageView().setY(-camera.getY());
        middle.getImageView().setY(-camera.getY());
        left.getImageView().setY(-camera.getY());

        if(hero.getX()>desertSizeX*CNT) {
            CNT+=1;
        }
        if (CNT % 3 == 0) {
            left.getImageView().setX(desertSizeX * (CNT-2) - camera.getX());
            middle.getImageView().setX(desertSizeX * (CNT-1) - camera.getX());
            right.getImageView().setX(desertSizeX * (CNT) - camera.getX());

        }
        else if (CNT % 3 == 1) {
            left.getImageView().setX(desertSizeX * (CNT) - camera.getX());
            middle.getImageView().setX(desertSizeX * (CNT-2) - camera.getX());
            right.getImageView().setX(desertSizeX * (CNT-1) - camera.getX());
        }
        else if(CNT % 3 == 2){
            left.getImageView().setX(desertSizeX * (CNT-1) - camera.getX());
            middle.getImageView().setX(desertSizeX * (CNT) - camera.getX());
            right.getImageView().setX(desertSizeX * (CNT-2) - camera.getX());
        }

        hero.getImageView().setX(hero.getX()-camera.getX());
        hero.getImageView().setY(hero.getY()-camera.getY());
        EnergyBall.SetSpeed(hero.setV_x());

        EnergyBall.getImageView().setX((EnergyBall.getX()- camera.getX()));
        EnergyBall.getImageView().setY((EnergyBall.getY()- camera.getY()));
        if(EnergyBall.getX()> hero.getX()+desertSizeX){
            EnergyBall.reset();
            EnergyBall.CanShoot=Boolean.TRUE;
        }


    }


}

