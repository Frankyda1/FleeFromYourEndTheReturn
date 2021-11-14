import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import java.nio.file.Paths;
import java.io.File;
import java.net.URI;

public class FleeFromYourEnd extends Application {
    MediaPlayer mediaPlayer;
    public void music(){
        try {
            String s = "Music/JoJo.mp3";
            Media h = new Media(Paths.get(s).toUri().toString());
            mediaPlayer = new MediaPlayer(h);
            mediaPlayer.play();
        }
        catch (Exception e){
            System.out.println("Oups");
            e.printStackTrace();
        }
    }
    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("FleeFromYourEnd");
        Group root = new Group();
        Pane pane = new Pane(root);

        GameScene theScene = new GameScene(pane, 800, 400, true);
        primaryStage.setScene(theScene);
        pane.getChildren().add(theScene.hero.getImageView());
        pane.getChildren().add(theScene.EnergyBall.getImageView());


        AnimationTimer timer = new AnimationTimer() {
            public void handle(long time) {
                theScene.hero.update(time);
                theScene.EnergyBall.update(time);
                theScene.camera.update(time, theScene.hero);
                theScene.Permute_Images();

            }
        };
        timer.start();
        primaryStage.show();
        music();
    }



    public static void main(String[] args) {
        launch(args);
    }

}