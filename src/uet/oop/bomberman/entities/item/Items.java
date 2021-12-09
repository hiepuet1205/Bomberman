package uet.oop.bomberman.entities.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class Items extends Entity {
    protected boolean received = false;

    public Items( int xUnit, int yUnit, Image img, boolean received) {
        super(xUnit, yUnit, img);
        this.received = received;
    }

    public boolean isReceived() {
        return received;
    }

    public void setReceived(boolean received) {
        this.received = received;
    }

    @Override
    public void update(){

    }
}
