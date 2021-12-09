package uet.oop.bomberman.entities.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.*;

public class SpeedItem extends Items{
    public static int speed = 1;

    public SpeedItem(int xUnit, int yUnit, Image img, boolean received) {
        super(xUnit, yUnit, img, received);
    }

    @Override
    public void update(){
        for(Entity entity : block){
            if(entity instanceof SpeedItem && !this.received){
                if(listKill[entity.getX()/32][entity.getY()/32] == 4){
                    entity.setImg(Sprite.powerup_speed.getFxImage());
                }
            }
        }

        if(!this.received){
            if(player.getX() == this.x && player.getY() == this.y){
                this.received = true;
                this.setImg(Sprite.grass.getFxImage());
                speed ++;
            }
        }
    }
}
