import javafx.beans.value.ObservableValue;
import javafx.geometry.Rectangle2D;


public class Hero extends  AnimatedThing{
    public double Add_v_x;
    public Boolean gameOver;

    public Hero(double x, double y, int attitude, int a, double duration, int maxa, double sizex, double siezy,double Hitx,double Hity, int offset, String filename) {
        super(x, y, attitude, a, duration, maxa, sizex, siezy,Hitx,Hity, offset, filename);
    }

    public void setForce(double f_x,double f_y){
        super.f_x=f_x;
        super.f_y=f_y;
    }
    public void Turn(double H){
        if(Add_v_x*H<0){
            f_x=0;
            a_x=0;
            Add_v_x=H;
        }
    }


    public void addSpeed(double Add_v_x) {
        if (Math.abs(this.Add_v_x) <= 4 ) {
            this.Add_v_x += Add_v_x;
        }
    }

    @Override
    public void updateAttitude() {
        if (v_y>0){
            attitute=Attitude.JUMPING_DOWN;
            if(attitude==1){
                attitute=Attitude.DOWNnGUN;
            }
        }
        else if (v_y<0){
            attitute=Attitude.JUMPING_UP;
            if (attitude==1){
                attitute=Attitude.UPnGun;
            }
        }

        else if (attitude==1){
            attitute=Attitude.RUNnGUN;
        }
        else{
            attitute=Attitude.RUNNING;
        }
        if (v_x==0){
            attitute=Attitude.STILL;
        }
    }

    @Override
    public void update(long t,Camera camera) {
        super.update(t,camera);
        updateAttitude();

        if (this.x- camera.getX() < 100 ) {
            a_x=0;
            v_x=camera.GetSpeed();
            x+=v_x+1;
        }
        else if(this.x- camera.getX() > 1500){
            a_x=0;
            v_x=camera.GetSpeed();
            x+=v_x-1;
        }
        else{
            a_x = f_x / masse;
            v_x += a_x;
            this.x += v_x+Add_v_x;
        }
         if (attitute==Attitude.UPnGun){
            this.imageView.setViewport(new Rectangle2D(offset,480,sizex,sizey));
        }
        else if (attitute==Attitude.DOWNnGUN){
            this.imageView.setViewport(new Rectangle2D(95,480,sizex,sizey));
        }
        else if (attitute==Attitude.RUNnGUN){
            this.imageView.setViewport(new Rectangle2D(a*(sizex+10),330,sizex-7,100));
        }
        if (pv==0){
            gameOver=true;
        }

        setForce(0,0);
    }
}
