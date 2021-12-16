package uet.oop.bomberman.entities.animal;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class Animal extends Entity {
    public int isMove;      //jump with pixel
    public int animation;        //hoat canh chuyen dong
    public String direction;    //huong di chuyen
    public boolean live;       //true = còn sống
    public int count;       //count step of a jump
    public int countToRun;   //run after count frame

    // khoi tao
    public Animal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    // khoi tao
    public Animal(int isMove, int animation, String direction, int count, int countToRun) {
        this.isMove = isMove;
        this.animation = animation;
        this.direction = direction;
        this.count = count;
        this.countToRun = countToRun;
    }

    // khoi tao
    public Animal(boolean live){
        this.live = live;
    }

    // khoi tao
    public Animal(){}

    // get / set
    public int getIsMove() {
        return isMove;
    }

    public void setIsMove(int isMove) {
        this.isMove = isMove;
    }

    public boolean isLiVe() {
        return live;
    }

    public void setLive(boolean life) {
        this.live = life;
    }

    public int getAnimation() {
        return animation;
    }

    public void setAnimation(int swap) {
        this.animation = swap;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCountToRun() {
        return countToRun;
    }

    public void setCountToRun(int countToRun) {
        this.countToRun = countToRun;
    }

    @Override
    public void update() {

    }
}
