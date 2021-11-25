import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import java.nio.file.Paths;
import javafx.scene.control.Button;
import javafx.scene.canvas.Canvas;


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
        Button button = new Button("Press Me to start new Thread");

        Label taskLabel = new Label("Task Running..");
        taskLabel.setVisible(false);
        Label finishLabel = new Label("Task Completed.");
        finishLabel.setVisible(false);

        primaryStage.setTitle("FleeFromYourEnd");
        Group root = new Group();
        Pane pane = new Pane(root);
        Canvas c = new Canvas();


        GameScene theScene = new GameScene(pane, 1600, 400, true);
        primaryStage.setScene(theScene);
        pane.getChildren().add(theScene.hero.getImageView());
        pane.getChildren().add(theScene.EnergyBall.getImageView());
        for (int i=0;i<theScene.Difficulty.Foes.length;i++){
            pane.getChildren().add(theScene.Difficulty.Foes[i].getImageView());

        }

        AnimationTimer timer = new AnimationTimer() {
            public void handle(long time) {
                theScene.hero.update(time,theScene.camera);
                theScene.EnergyBall.update(time, theScene.camera);
                theScene.camera.update(time,theScene.hero);
                theScene.Update_Images();
                theScene.Difficulty.UpdateDiff(time);

                for (int a=0;a<theScene.Difficulty.Foes.length;a++){
                    theScene.Difficulty.Foes[a].update(time, theScene.camera);
                    theScene.Difficulty.Spawn(theScene.Difficulty.Foes[a],theScene.camera.getX());
                }

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