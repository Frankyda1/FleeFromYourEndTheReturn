import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import java.awt.Font;

import java.awt.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Paths;
import javafx.scene.control.Button;
import javafx.scene.canvas.Canvas;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class FleeFromYourEnd extends Application {
    MediaPlayer mediaPlayer;
    private Boolean Start=false;
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
        button.setText("Start");
        button.setLayoutX(800);
        button.setLayoutY(200);
        button.setStyle("-fx-background-color: #ff0000; ");
        button.setMaxWidth(100);
        button.setMaxHeight(200);

        InputStream stream= new FileInputStream("Img/Start.png");
        Image image = new Image(stream) ;
        ImageView imageView = new ImageView();



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
        Label taskLabel = new Label("Score: ");
        taskLabel.setLayoutX(1400);
        taskLabel.setLayoutY(0);
        taskLabel.setVisible(false);

        Label finishLabel = new Label("Difficulty: ");
        finishLabel.setLayoutX(1400);
        finishLabel.setLayoutY(20);
        finishLabel.setVisible(false);

        pane.getChildren().add(taskLabel);
        pane.getChildren().add(finishLabel);
        pane.getChildren().add(theScene.StartScreen.getImageView());
        pane.getChildren().add(button);

        AnimationTimer timer = new AnimationTimer() {
            public void handle(long time) {
                if((Start)&(theScene.hero.pv>0)) {

                    theScene.hero.update(time, theScene.camera);
                    theScene.EnergyBall.update(time, theScene.camera);
                    theScene.camera.update(time, theScene.hero);
                    theScene.Update_Images();
                    theScene.Difficulty.UpdateDiff(time);
                    finishLabel.setText("Difficulty: "+theScene.Difficulty.GetDiff());
                    taskLabel.setText("Score: "+theScene.Difficulty.Score);
                    for (int a = 0; a < theScene.Difficulty.Foes.length; a++) {
                        theScene.Difficulty.Foes[a].update(time, theScene.camera);
                        theScene.Difficulty.Spawn(theScene.Difficulty.Foes[a], theScene.camera.getX());
                    }
                }

            }
        };


        primaryStage.show();
        button.setOnMouseClicked(event -> {
                    Start=true;
                    theScene.StartScreen.imageView.setVisible(false);
                    button.setVisible(false);

                    // show the label
                    taskLabel.setVisible(true);
                    // hide finish label
                    finishLabel.setVisible(true);
                    // start background computation

                });
        timer.start();
        music();

    }

    public static void main(String[] args) {
        launch(args);
    }

}