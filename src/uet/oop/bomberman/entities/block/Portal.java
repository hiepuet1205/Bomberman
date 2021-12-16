package uet.oop.bomberman.entities.block;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

// cong de chuyen sang man sau
public class Portal extends Entity {
    public static boolean isPortal = false; // check xem cong co mo ko

    // khoi tao
    public Portal(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }
}
