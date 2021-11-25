import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;

public abstract class AnimatedThing {
    enum Attitude{JUMPING_UP,JUMPING_DOWN,RUNNING,STILL,DEAD,RUNnGUN,UPnGun,DOWNnGUN,PEW,FLYING,DIVING}
    protected Attitude attitute;
    public double x;
    public double y;
    protected double v_x,v_y;
    protected double a_x,a_y;
    protected double f_x,f_y;
    protected final double g=0.5;
    protected final double masse=20;
    protected int Locky;
    public double Hitx;
    public double Hity;
    public int attitude;
    public long a;
    public double duration;
    public int maxa;
    public  double sizex;
    public double sizey;
    public int offset;
    public String filename;
    public ImageView imageView;
    public AnimationTimer timer;
    public Boolean hit;
    public int pv =6;
    protected final int yGround=150;
    public Boolean Invincibility =false;
    int m=0;



    public AnimatedThing(double x,double y , int attitude,long a,double duration, int maxa,double sizex ,double siezy,double Hitx,double Hity,int offset,String filename){
        this.x=x;
        this.y=y;
        this.attitude=attitude;
        this.a=a;
        this.duration=duration;
        this.maxa=maxa;
        this.sizex=sizex;
        this.sizey=siezy;
        this.offset=offset;
        this.filename=filename;
        this.imageView = new ImageView(filename);
        this.Hitx=Hitx;
        this.Hity=Hity;
        imageView.setFitHeight(Hity);
        imageView.setFitWidth(Hitx);
    }
    //Getter&Setter
    public ImageView getImageView() {
        return imageView;
    }

    public void jump(){
        if (y>=yGround + sizey){
            f_y +=250;
        }
    }
    public void SetY(double y){this.y=y;}
    public void SetX(double x){this.x=x;}
    public void setSpeed(double v_x){this.v_x=v_x;}
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    public void SetPV(int pv){
        this.pv=pv;
    }

    public void SetLocky(){Locky=1;}

    public Rectangle2D GetHitbox (){
        Rectangle2D h=new Rectangle2D(x,y,Hitx,Hity);
        return h;
    }

    public void update(long t,Camera camera){
        a=(int) ((t%(maxa*duration))/duration);


        if(attitute==Attitude.RUNNING){
            this.imageView.setViewport(new Rectangle2D(a*(sizex+10),0, sizex,100));
        }
        else if (attitute==Attitude.JUMPING_UP){
            this.imageView.setViewport(new Rectangle2D(offset,160,sizex,sizey));
        }

        else if(attitute==Attitude.JUMPING_DOWN){
            this.imageView.setViewport(new Rectangle2D(95,160,sizex,sizey));
        }
        else if(attitute==Attitude.STILL){
            this.imageView.setViewport(new Rectangle2D(0,0,sizex,sizey));
        }


        if (Locky==1){
            a_y =0;
            v_y = 0;
        }
        else if(y< yGround+sizey | f_y>0){
            a_y =(g-f_y /masse);
            v_y += a_y;
            y += v_y;
        }
        else{
            v_y=0;
            y=yGround+sizey;
        }

    }

    public abstract void updateAttitude();

}