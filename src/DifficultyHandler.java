import java.awt.*;
import java.lang.Math;

public class DifficultyHandler {
    private int Diff=0;
    public int Score;
    public Label A = new Label(""+Score);
    Foe Cactus =new Foe(900,270,1,0,100000000,6,594,600,100,100,10,"file:Img/Rock.png");
    Foe Raven = new Foe(900,100,2,0,100000000, 8,193,200,100,100,10,"file:Img/Raveny.png");
    Foe[] Foes=new Foe[2];

    public DifficultyHandler(){
        Foes[0]=Raven;
        Foes[0].SetLocky();
        Foes[1]=Cactus;
        Foes[1].SetLocky();

    }

    public void AddScore(){
        Score+=10;
        System.out.println("+1");
    }


    public void UpdateDiff(long t){
        if (Score>Diff*100){
            Diff++;
            AddFoe();
            System.out.println("Foe Added");
        }
    }
    public void Spawn(Foe foe,double x){
        if (foe.IsAvailable==1) {
            foe.setX(x+1600);
            foe.IsAvailable = 0;
        }
    }
    public int GetDiff(){
        return Diff;
    }

    public void AddFoe(){
        int value = (int)(Math.random()* Foes.length);
        Foe[] tmp= new Foe[Foes.length+1];
        for (int i=0;i<Foes.length;i++){
            tmp[i]=Foes[i];
        }
        tmp[Foes.length]=Foes[value];
        Foes=tmp;
        if(value==3){
            Foes[Foes.length].SetLocky();
        }
    }
}
