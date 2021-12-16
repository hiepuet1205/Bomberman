package uet.oop.bomberman.entities.animal;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.enemy;
import static uet.oop.bomberman.BombermanGame.player;
import static uet.oop.bomberman.entities.Move.*;

// con nay tuong tu con ballom nhung co kha nang duoi theo nguoi choi
public class Oneal extends Animal{
    public static int animationDead = 0;
//    public static int countKill = 0;

    public Oneal(int x, int y, Image img) {
        super(x, y, img);
    }

    public Oneal(int isMove, int animation, String direction, int count, int countToRun) {
        super(4, 1, "up", 0, 0);
    }

    public Oneal(boolean live) {
        super(live);
    }

    public Oneal() {
    }

    public static void animationOnealDead(Animal animal){
        if(animationDead == 0){
            animal.setImg(Sprite.oneal_dead.getFxImage());
            animationDead = 1;
        }else if(animationDead == 1){
            animal.setImg(Sprite.player_dead3.getFxImage());
            animationDead = 2;
        } else{
            animal.setLive(false);
            enemy.remove(animal);
            animationDead = 1;
        }
    }

    @Override
    public void update() {
//        countKill++;

        for(Animal animal: enemy){
            if(!animal.isLiVe() && animal instanceof Oneal){
                animationOnealDead(animal);
            }
        }

        // duoi theo nguoi choi
        if (this.y % 16 == 0 && this.x % 16 == 0){
            if (player.getX() < this.x) {
                left(this);
            }
            if (player.getX() > this.x) {
                right(this);
            }
            if (player.getY() > this.y) {
                down(this);
            }
            if (player.getY() < this.y) {
                up(this);
            }
        }
    }

}
