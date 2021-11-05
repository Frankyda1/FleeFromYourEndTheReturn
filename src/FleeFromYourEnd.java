import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class FleeFromYourEnd extends Application {

    Stage window;
    Scene start, game;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;

        // When user clicks start, program enters game
        Button btn = new Button("Start Game");

        btn.setOnAction(e -> window.setScene(game));

        // Create the display for start button
        GridPane pane1 = new GridPane();
        pane1.getChildren().addAll(btn);
        start = new Scene(pane1, 200, 200);

        // Create display for game
        GridPane pane2 = new GridPane();
        Hero hero = new Hero(30,20);
        Image back = new
                Image(
                "D:\\Users\\franc\\IdeaProjects\\FleeFromYourEndTheReturn\\Img\\desert.png",
                800, 400, false, false);
        pane2.getChildren().addAll(new ImageView(back));
        pane2.getChildren().addAll(new ImageView(  ));
        game = new Scene(pane2, 800, 400);


        window.setScene(start);
        window.setTitle("Matching Cards");
        window.show();
    }
}