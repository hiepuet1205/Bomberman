package uet.oop.bomberman.entities.animal;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;
import static uet.oop.bomberman.BombermanGame.*;

public class Bomber extends Animal{
    public static int animationDead = 0;
//    public static int countKill = 0;

    // khoi tao
    public Bomber(int isMove, int animation, String direction, int count, int countToRun) {
        super(8, 1, "down", 0, 0);
    }

    // khoi tao
    public Bomber() {
    }

    // khoi tao
    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    // hoat canh nguoi choi chet
    public static void animationBomberDead(Animal animal){
        if(animationDead == 0){
            animal.setImg(Sprite.player_dead1.getFxImage());
            animationDead = 1;
        }else if(animationDead == 1){
            animal.setImg(Sprite.player_dead2.getFxImage());
            animationDead = 2;
        }else if(animationDead == 2){
            animal.setImg(Sprite.player_dead3.getFxImage());
            animationDead = 3;
        }else{
            animal.setImg(Sprite.transparent.getFxImage());
            running = false;
            authorView.setImage(new Image("images/gameover1.png")); // set hinh anh gameover
        }
    }

    // check xem co bi bomb no chet ko
    public static void checkBomb(){
        if(listKill[player.getX()/32][player.getY()/32] == 4){
            player.live = false;
        }
    }

    // check xem co bi quai can chet ko
    public static void checkEnemy(){
        for(Animal a : enemy){
            if(a.getX() / 32 == player.getX() / 32 && a.getY() / 32 == player.getY() / 32){
                player.live = false;
                break;
            }
        }
    }

    @Override
    public void update() {
        checkBomb();
        checkEnemy();
//        countKill++;
        // neu chet thi goi ham hoat canh
        if(!player.live){
            animationBomberDead(player);
        }
    }
}
