package uet.oop.bomberman.entities.level;

import javafx.scene.image.Image;

import static uet.oop.bomberman.BombermanGame.authorView;
import static uet.oop.bomberman.BombermanGame.l;
import static uet.oop.bomberman.entities.block.Portal.*;

public class NextLevel {
    public static boolean wait;
    public static long waitingTime;

    public static void waitToLevelUp() {
        if (wait) {
            Image waitToNext = new Image("images/levelup1.png");
            authorView.setImage(waitToNext);
            long now = System.currentTimeMillis();
            if (now - waitingTime > 3000) {
                switch (l) {
                    case 1:
                        isPortal = false;
                        new level2();
                        break;
                    case 2:
                        new level3();
                        break;
                    case 3:
                        new level1();
                }
                wait = false;
            }
        }
    }
}
