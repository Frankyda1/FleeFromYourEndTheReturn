import javafx.geometry.Rectangle2D;

public class EnergyBall extends AnimatedThing{
    public Boolean CanShoot = Boolean.TRUE;
    private double v_x;




    public EnergyBall(double x, double y, int attitude, long a, double duration, int maxa, double sizex, double siezy, double Hitx,double Hity, int offset, String filename) {
        super(x, y, attitude, a, duration, maxa, sizex, siezy, Hitx,Hity, offset, filename);
    }

    public void reset(){
        x=0;
        v_x=0;
        attitude=0;
    }
    public void Shoot(double heroX,double heroY){
        if (CanShoot==Boolean.TRUE ){
            attitude=1;
            this.x=heroX;
            this.y=heroY;
            CanShoot=Boolean.FALSE;

        }
    }


    @Override
    public void update(long t,Camera camera) {
        super.update(t,camera);
        v_y=0;
        a_y=0;
        f_y=0;
        if(attitute==Attitude.PEW){
            this.imageView.setViewport(new Rectangle2D(a*(sizex+20)+400,-10, sizex,sizey));
            v_x=camera.GetSpeed();
            x+=3*v_x;
        }
        updateAttitude();
    }
    public void updateAttitude(){

        if ( attitude==1){
            attitute=Attitude.PEW;
        }
        else{
            attitute=Attitude.STILL;
        }
    }
}
