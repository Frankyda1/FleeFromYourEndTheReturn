import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class AnimatedThing {
    enum Attitude{JUMPING_UP,JUMPING_DOWN,RUNNING,STILL,DEAD,RUNnGUN,UPnGun,DOWNnGUN,PEW}
    protected Attitude attitute;
    public double x;
    public double y;
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



    public AnimatedThing(double x,double y , int attitude,long a,double duration, int maxa,double sizex ,double siezy,int offset,String filename){
        this.x =x;
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
    }
    //Getter&Setter
    public ImageView getImageView() {
        return imageView;
    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void SetPV(int pv){
        this.pv=pv;
    }

    public Rectangle2D GetHitbox (){
        Rectangle2D h=new Rectangle2D(x,y,sizex,sizey);
        return h;
    }

    public void update(long t){
        a=(int) ((t%(maxa*duration))/duration);


        if(attitute==Attitude.RUNNING){

            this.imageView.setViewport(new Rectangle2D(a*(sizex+10),0, sizex,100));


        }
        else if (attitute==Attitude.JUMPING_UP){

            this.imageView.setViewport(new Rectangle2D(offset,160,sizex,sizey));

        }
        else if (attitute==Attitude.UPnGun){

            this.imageView.setViewport(new Rectangle2D(offset,480,sizex,sizey));

        }
        else if (attitute==Attitude.DOWNnGUN){

            this.imageView.setViewport(new Rectangle2D(95,480,sizex,sizey));

        }
        else if (attitute==Attitude.RUNnGUN){

            this.imageView.setViewport(new Rectangle2D(a*(sizex+10),330,sizex-7,100));

        }
        else if(attitute==Attitude.JUMPING_DOWN){

            this.imageView.setViewport(new Rectangle2D(95,160,sizex,sizey));

        }
        else if(attitute==Attitude.STILL){

            this.imageView.setViewport(new Rectangle2D(0,0,sizex,sizey));
        }
        else if(attitute==Attitude.PEW){
            this.imageView.setViewport(new Rectangle2D(a*(sizex+20)+400,-10, sizex,sizey));
        }
    }
    public abstract void updateAttitude();

}