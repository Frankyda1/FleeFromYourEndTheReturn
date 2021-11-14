public class Hero extends  AnimatedThing{
    private final double g=0.5;
    private final double m=20;
    private int Gun;
    private double v_x,v_y;
    private double a_x,a_y;
    private double f_x,f_y;
    protected final int yGround=150;

    public Hero(double x, double y, int attitude, int a, double duration, int maxa, double sizex, double siezy, int offset, String filename) {
        super(x, y, attitude, a, duration, maxa, sizex, siezy, offset, filename);
    }

    public void jump(){
        if (y>=yGround + sizey){
            f_y +=250;
        }

    }
    public void setForce(double f_x,double f_y){
        this.f_x=f_x;
        this.f_y=f_y;
    }

    public void addForce(double f_x) {
        this.f_x+=f_x;
    }
    public double setV_x(){
        return (v_x);
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
    public void update(long t) {
        super.update(t);
        updateAttitude();
        a_x=f_x/m;
        v_x+=a_x;
        x += v_x;

        a_y =(g-f_y /m);
        v_y += a_y;
        y += v_y;

        if (y > yGround + sizey) {
            if (v_y > 0) {
                v_y = 0;
            }
            y = yGround + sizey;
        }
        setForce(0,0);
    }

}
