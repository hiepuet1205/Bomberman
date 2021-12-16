package uet.oop.bomberman.entities.block;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.block;
import static uet.oop.bomberman.BombermanGame.listKill;

// cac khoi co the bi pha huy
public class Brick extends Entity {
    // khoi tao
    public Brick(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        // check xem da bi pha huy chua
        // dat lai hinh anh
        for(Entity entity : block){
            if(entity instanceof Brick){
                if(listKill[entity.getX()/32][entity.getY()/32] == 4){
                    entity.setImg(Sprite.grass.getFxImage());
                }
            }
        }
    }
}
