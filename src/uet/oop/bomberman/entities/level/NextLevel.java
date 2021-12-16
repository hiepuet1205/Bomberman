package uet.oop.bomberman.entities.level;

import javafx.scene.image.Image;

import static uet.oop.bomberman.BombermanGame.authorView;
import static uet.oop.bomberman.BombermanGame.l;
import static uet.oop.bomberman.entities.block.Portal.*;

public class NextLevel {
    public static boolean wait; // check xem co dang doi de chuyen man khac ko
    public static long waitingTime; // thoi gian doi

    public static void waitToLevelUp() {
        // neu dang doi
        if (wait) {
            Image waitToNext = new Image("images/levelup1.png"); // set hinh anh levelup
            authorView.setImage(waitToNext);
            long now = System.currentTimeMillis();
            // sau 3s
            if (now - waitingTime > 3000) {
                // check xem level tiep theo la level nao
                switch (l) {
                    case 1:
                        isPortal = false; // lam moi trang thai cua cong
                        new level2();
                        break;
                    case 2:
                        new level3();
                        break;
                    case 3:
                        new level1();
                }
                wait = false; // lam moi
            }
        }
    }
}
