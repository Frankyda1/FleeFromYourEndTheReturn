public class Camera {
    private int x;
    private int y;
    private double vx,vy;
    private double ax,ay;
    private final double k=1;
    private final double m=20;
    private final double f=10;

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public Camera(int x,int y){
        this.x = x;
        this.y = y;
    }
    public void setSpeed(double v_x){
        this.vx=v_x;
    }
    public double GetSpeed(){
        return vx;
    }
    @Override
    public String toString(){
        return "("+x+";"+y+")";
    }


    public void update(long time,Hero hero){



        x+=vx;
    }
}