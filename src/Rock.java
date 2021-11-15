import javafx.geometry.Rectangle2D;

public class Rock extends Foe{
    private double xpos;
    private double ypos;
    private double vx=1;

    public Rock(double x, double y, int attitude, int a, double duration, int maxa, double sizex, double siezy, int offset, String filename) {
        super(x, y, attitude, a, duration, maxa, sizex, siezy, offset, filename);
        this.xpos=x;
        this.ypos=y;
    }


    public void setXpos(double x){
        this.xpos=x;
    }
    public void setVx(double v_x){
        this.vx=v_x;
    }
    public double GetXpos(){
        return xpos;
    }
    public double GetYpos(){
        return ypos;
    }

    public Rectangle2D GetHitbox (){
        Rectangle2D h=new Rectangle2D(xpos,ypos,40,70);
        return h;
    }
    public void update(long t){
        super.update(t);
        x=x-vx;
    }
}
