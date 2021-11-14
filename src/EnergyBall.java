public class EnergyBall extends AnimatedThing{
    public Boolean CanShoot;
    private double v_x;


    public EnergyBall(double x, double y, int attitude, long a, double duration, int maxa, double sizex, double siezy, int offset, String filename) {
        super(x, y, attitude, a, duration, maxa, sizex, siezy, offset, filename);
    }
    public void reset(){
        x=0;
    }
    public void Shoot(double heroX,double heroY){
        if (CanShoot==Boolean.TRUE ){
            this.x=heroX;
            this.y=heroY;
            CanShoot=Boolean.FALSE;

        }
    }

    public void SetSpeed(double vx){
        this.v_x=vx;
    }
    public void Launch(double x, double y, int attitude, long a, double duration, int maxa, double sizex, double siezy, int offset, String filename){
        EnergyBall Ball= new EnergyBall(x,  y, attitude,  a,  duration,  maxa,  sizex,  siezy,  offset, filename);

    }
    @Override
    public void update(long t) {
        super.update(t);
        updateAttitude();
        x+=2*v_x;
    }
    public void updateAttitude(){
        if ( attitude==1){
            attitute=Attitude.PEW;
        }
        else{
            x=500;
        }
    };
}
