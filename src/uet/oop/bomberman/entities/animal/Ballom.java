package uet.oop.bomberman.entities.animal;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

import static uet.oop.bomberman.BombermanGame.enemy;
import static uet.oop.bomberman.BombermanGame.listKill;
import static uet.oop.bomberman.entities.Move.*;

public class Ballom extends Animal{
    private static int animationDead = 1;
    private static int countKill = 0;

    public Ballom(int ismove, int swap, String direction, int count, int countToRun) {
        super(4, 1, "up", 0, 0);
    }

    public Ballom() {
    }

    public Ballom(int x, int y, Image img) {
        super(x, y, img);
    }


    public static void animationBallomDead(Animal animal){
        if(animationDead == 0){
            animal.setImg(Sprite.mob_dead1.getFxImage());
            animationDead = 1;
        }else if(animationDead == 1){
            animal.setImg(Sprite.mob_dead2.getFxImage());
            animationDead = 2;
        }else if(animationDead == 2){
            animal.setImg(Sprite.mob_dead3.getFxImage());
            animationDead = 3;
        }else{
            animal.setLive(false);
            enemy.remove(animal);
            animationDead = 1;
        }
    }

    public static void checkDead(){
        for(Animal animal: enemy){
            if(listKill[animal.getX()/32][animal.getY()/32] == 4){
                animal.setLive(false);
            }
        }
    }

    public void randomMove(){
        if (this.y % 16 == 0 && this.x % 16 == 0){
            Random random = new Random();
            int direction = random.nextInt(4);

            switch (direction){
                case 0:
                    down(this);
                    break;
                case 1:
                    up(this);
                    break;
                case 2:
                    left(this);
                    break;
                case 3:
                    right(this);
                    break;
            }
        }
    }

    @Override
    public void update() {
        checkDead();
        countKill++;

        for(Animal animal: enemy){
            if(!animal.isLiVe() && animal instanceof Ballom){
                animationBallomDead(animal);
            }
        }

        randomMove();
    }
}
