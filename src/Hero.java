
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Hero {
    private double x;
    private double y;
    private Image spriteSheet=null;
    private ImageView sprite;

    public Hero(int x, int y) {
    }

    public Image getSpriteSheet() {
        return spriteSheet;
    }

    public Hero(double x, double y, String fileName){
        this.x=x;
        this.y=y;

        Image spriteSheet=new Image("D:\\Users\\franc\\IdeaProjects\\FleeFromYourEndTheReturn\\Img\\heros.png");
        sprite = new ImageView(spriteSheet);

        sprite.setViewport(new Rectangle2D(20,15,60,80));
        sprite.setX(200);
        sprite.setY(300);

    }
}
