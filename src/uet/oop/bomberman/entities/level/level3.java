package uet.oop.bomberman.entities.level;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.animal.Animal;
import uet.oop.bomberman.entities.animal.Ballom;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.entities.Map.createMap;
import static uet.oop.bomberman.entities.Menu.bombNumber;
import static uet.oop.bomberman.entities.Menu.timeNumber;
import static uet.oop.bomberman.entities.animal.Bomber.animationDead;
import static uet.oop.bomberman.entities.block.Bomb.isBomb;
import static uet.oop.bomberman.entities.block.Bomb.powerBomb;
import static uet.oop.bomberman.entities.item.SpeedItem.speed;
import static uet.oop.bomberman.audio.Sound.*;

public class level3 {
    public level3() {
        enemy.clear();
        block.clear();
        animationDead = 1;
        powerBomb = 0;
        createMap("res/levels/Level3.txt");
        player.setLive(true);
        player.setX(32);
        player.setY(32);
        isSoundDied = false;
        isSoundTitle = false;
        timeNumber = 120;
        bombNumber = 40;
        isBomb = 0;
        speed = 1;

        player.setImg(Sprite.player_right_2.getFxImage());
        Image transparent = new Image("images/transparent.png");
        authorView.setImage(transparent);

        Animal enemy1 = new Ballom(5, 5, Sprite.ballom_left1.getFxImage());
        Animal enemy2 = new Ballom(11, 9, Sprite.ballom_left1.getFxImage());
        enemy.add(enemy1);
        enemy.add(enemy2);

        for (Animal animal : enemy) {
            animal.setLive(true);
        }
    }
}
