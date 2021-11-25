import javafx.geometry.Rectangle2D;

public class Rock extends Foe{
    private double xpos;
    private double ypos;

    public Rock(double x, double y, int attitude, int a, double duration, int maxa, double sizex, double siezy, double Hitx,double Hity, int offset, String filename) {
        super(x, y, attitude, a, duration, maxa, sizex, siezy,Hitx,Hity, offset, filename);

    }


    public void setXpos(double x){
        this.xpos=x;
    }
    public double GetXpos(){
        return xpos;
    }
    public double GetYpos(){
        return ypos;
    }


}
