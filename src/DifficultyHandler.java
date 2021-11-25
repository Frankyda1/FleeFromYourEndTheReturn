import java.awt.*;
import java.lang.Math;

public class DifficultyHandler {
    private int Diff=0;
    public int Score;
    public Label A = new Label(""+Score);
    Foe Cactus =new Foe(1600,270,1,0,100000000,6,594,600,100,100,10,"file:Img/Rock.png");
    Foe Raven = new Foe(1600,100,2,0,100000000, 8,193,200,100,100,10,"file:Img/Raveny.png");
    Foe[] Foes=new Foe[2];

    public DifficultyHandler(){
        Foes[0]=Raven;
        Foes[0].SetLocky();

        Foes[1]=Cactus;
        Foes[1].SetLocky();
        Foes[1].Invincibility=true;

    }

    public void AddScore(){
        Score+=10;
        System.out.println("+1");
    }


    public void UpdateDiff(long t){
        if (Score>(Diff+1)*100){
            Diff++;
            AddFoe();
            System.out.println("Foe Added");
        }
    }
    public void Spawn(Foe foe,double x){
        if (foe.IsAvailable==1) {
            foe.setX(x+1600);
            foe.IsAvailable = 0;
            if(foe.attitude==2){
                foe.setY((int)(Math.random()*200));
                foe.v_x=(int)(Math.random()*5);
            }
        }
    }
    public int GetDiff(){
        return Diff;
    }

    public void AddFoe(){
        int value =0;
        Foe[] tmp= new Foe[Foes.length+1];
        for (int i=0;i<Foes.length;i++){
            tmp[i]=Foes[i];
        }
        if(value%2==0){
            tmp[Foes.length]=new Foe(1600,270,1,0,100000000,6,594,600,100,100,10,"file:Img/Rock.png");
        }
        else{
            tmp[Foes.length]=new Foe(1600,100,2,0,100000000, 8,193,200,100,100,10,"file:Img/Raveny.png");
        }
        Foes=tmp;
        Spawn(Foes[Foes.length-1],0);
        if(Foes[value].attitude==2){
            Foes[Foes.length-1].SetLocky();
        }
    }
}
