package uet.oop.bomberman.entities.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.block.Bomb;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.*;

public class FlameItem extends Items{
    public FlameItem(int xUnit, int yUnit, Image img, boolean received) {
        super(xUnit, yUnit, img, received);
    }

    @Override
    public void update(){
        for(Entity entity : block){
            if(entity instanceof FlameItem && !this.received){
                if(listKill[entity.getX()/32][entity.getY()/32] == 4){
                    entity.setImg(Sprite.powerup_flames.getFxImage());
                }
            }
        }

        if(!this.received){
            if(player.getX() == this.getX() && player.getY() == this.getY()){
                this.received = true;
                this.setImg(Sprite.grass.getFxImage());
                Bomb.powerBomb = Bomb.powerBomb + 2;
            }
        }
    }
}
