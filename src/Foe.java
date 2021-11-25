import javafx.geometry.Rectangle2D;

public class Foe extends  AnimatedThing{
    private final double g=0.7;
    private final double m=20;
    public double v_x = 1,v_y;
    public int IsAvailable;
    private double cos;
    private double sin;
    private double norm;
    private double a_x,a_y;
    private double f_x,f_y;
    protected final int yGround=150;

    public Foe (double x, double y, int attitude, long a, double duration, int maxa, double sizex, double siezy, double Hitx,double Hity, int offset, String filename) {
        super(x, y, attitude, a, duration, maxa, sizex, siezy,Hitx,Hity, offset, filename);
    }
    public void jump(){
        if (y>=yGround + sizey){
            f_y +=200;
        }
    }
    public void setForce(double f_x,double f_y){
        this.f_x=f_x;
        this.f_y=f_y;
    }
    public void setX(double x){this.x=x;}
    public void setY(double y){this.y=y;}
    public void setSpeed(double v_x){
        this.v_x=v_x;
    }
    public void addForce(double f_x) {
        this.f_x+=f_x;
    }
    public void Reset(double x){
        super.x=x-100;
        super.y=270;
        super.imageView.setVisible(true);
        IsAvailable=1;
    }
    public Rectangle2D GetHitbox (){
        Rectangle2D h=new Rectangle2D(super.x,super.y,40,70);
        return h;
    }
    public void Dive(double x1,double y1){
        if(x-x1<200 & x-x1>180 & attitude==2) {
            norm=Math.sqrt((int)Math.pow(x1-x,2)+(int)Math.pow(y1-y,2));
            cos=(x1-x)/norm;
            sin=(y1-y)/norm;
            v_x=3*cos*v_x;
            v_y=3*sin*v_x;
            attitude=3;
        }
        else if ((x-x1<-10 | x-x1>200)&attitude==3){
            attitude=2;
        }
    }
    @Override
    public void updateAttitude() {
        if (attitude==2){
            attitute=Attitude.FLYING;
            v_x=1;
            v_y=0;
        }
        if (attitude==3){
            attitute=Attitude.DIVING;
        }
    }
    @Override
    public void update(long t,Camera camera) {
        super.update(t,camera);
        updateAttitude();
        if(attitute == Attitude.FLYING){
            this.imageView.setViewport(new Rectangle2D(a*(sizex+14),0,sizex,sizey));
        }
        if(attitute== Attitude.DIVING ){
            x+=v_x;
            y-=v_y;
        }
        else {
            x -= v_x;
        }
        setForce(0,0);
    }
}