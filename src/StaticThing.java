import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class StaticThing {
    private double x;
    private double y;
    public ImageView imageView;

    public StaticThing(String fileName,double x,double y){
        this.imageView=new ImageView(new Image(fileName));
        this.x=x;
        this.y=y;
        this.imageView.setY(y);
        this.imageView.setX(x);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public ImageView getImageView() {
        return imageView;
    }
}