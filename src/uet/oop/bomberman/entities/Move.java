package uet.oop.bomberman.entities;

import uet.oop.bomberman.entities.animal.Animal;
import uet.oop.bomberman.entities.animal.Ballom;
import uet.oop.bomberman.entities.animal.Bomber;
import uet.oop.bomberman.entities.animal.Oneal;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.entities.item.SpeedItem.speed;

public class Move {

    // goi ham di chuyen va giam so buoc 
    public static void checkRun(Animal animal) {
        if (animal instanceof Bomber && animal.getCount() > 0) {
            setDirection(animal.getDirection(), animal, 8 * speed);
            animal.setCount(animal.getCount() - 1);
        }
        if (animal instanceof Ballom || animal instanceof Oneal && animal.getCount() > 0) {
            setDirection(animal.getDirection(), animal, 4);
            animal.setCount(animal.getCount() - 1);
        }
    }

    // di chuyen
    public static void setDirection(String direction, Animal animal, int length){
        switch (direction) {
            case "down":
                down_step(animal);
                animal.setY(animal.getY() + length); // cap nhat lai vi tri animal
                break;
            case "up":
                up_step(animal);
                animal.setY(animal.getY() - length);// cap nhat lai vi tri animal
                break;
            case "left":
                left_step(animal);
                animal.setX(animal.getX() - length);// cap nhat lai vi tri animal
                break;
            case "right":
                right_step(animal);
                animal.setX(animal.getX() + length);// cap nhat lai vi tri animal
                break;
        }
    }

    // di chuyen xuong duoi
    public static void down(Animal animal) {
        if (animal.getY() % 32 == 0 && animal.getX() % 32 == 0) {
            if (animal instanceof Bomber && Blocked.blockDown(animal)) {
                animal.setDirection("down");
                animal.setCount(4 / speed);
                checkRun(animal);
            }
            if ((animal instanceof Ballom) && Blocked.blockDown(animal)) {
                animal.setDirection("down");
                animal.setCount(8);
                checkRun(animal);
            }
            if ((animal instanceof Oneal) && Blocked.blockDown(animal)) {
                animal.setDirection("down");
                animal.setCount(8);
                checkRun(animal);
            }
        }
    }

    // hoat canh di chuyen
    public static void down_step(Animal animal) {
        if (animal instanceof Bomber) {
            if (animal.getAnimation() == 0) {
                animal.setImg(Sprite.player_down.getFxImage());
                animal.setAnimation(1);
            } else if (animal.getAnimation() == 1) {
                animal.setImg(Sprite.player_down_1.getFxImage());
                animal.setAnimation(2);
            } else if (animal.getAnimation() == 2) {
                animal.setImg(Sprite.player_down.getFxImage());
                animal.setAnimation(3);
            } else {
                animal.setImg(Sprite.player_down_2.getFxImage());
                animal.setAnimation(1);
            }
        }
        if (animal instanceof Ballom) {
            if (animal.getAnimation() == 0) {
                animal.setImg(Sprite.ballom_right1.getFxImage());
                animal.setAnimation(1);
            } else if (animal.getAnimation() == 1) {
                animal.setImg(Sprite.ballom_right2.getFxImage());
                animal.setAnimation(2);
            } else if (animal.getAnimation() == 2) {
                animal.setImg(Sprite.ballom_right3.getFxImage());
                animal.setAnimation(3);
            } else {
                animal.setImg(Sprite.ballom_right1.getFxImage());
                animal.setAnimation(1);
            }
        }
        if (animal instanceof Oneal) {
            if (animal.getAnimation() == 0) {
                animal.setImg(Sprite.oneal_right1.getFxImage());
                animal.setAnimation(1);
            } else if (animal.getAnimation() == 1) {
                animal.setImg(Sprite.oneal_right2.getFxImage());
                animal.setAnimation(2);
            } else if (animal.getAnimation() == 2) {
                animal.setImg(Sprite.oneal_right3.getFxImage());
                animal.setAnimation(3);
            } else {
                animal.setImg(Sprite.oneal_right1.getFxImage());
                animal.setAnimation(1);
            }
        }
    }

    // di chuyen xuong tren
    public static void up(Animal animal) {
        if (animal.getY() % 32 == 0 && animal.getX() % 32 == 0) {
            if (animal instanceof Bomber && Blocked.blockUp(animal)) {
                animal.setDirection("up");
                animal.setCount(4 / speed);
                checkRun(animal);
            }
            if ((animal instanceof Ballom) && Blocked.blockUp(animal)) {
                animal.setDirection("up");
                animal.setCount(8);
                checkRun(animal);
            }
            if ((animal instanceof Oneal) && Blocked.blockUp(animal)) {
                animal.setDirection("up");
                animal.setCount(8);
                checkRun(animal);
            }
        }
    }

    // hoat canh di chuyen
    public static void up_step(Animal animal) {
        if (animal instanceof Bomber) {
            if (animal.getAnimation() == 0) {
                animal.setImg(Sprite.player_up.getFxImage());
                animal.setAnimation(1);
            } else if (animal.getAnimation() == 1) {
                animal.setImg(Sprite.player_up_1.getFxImage());
                animal.setAnimation(2);
            } else if (animal.getAnimation() == 2) {
                animal.setImg(Sprite.player_up.getFxImage());
                animal.setAnimation(3);
            } else {
                animal.setImg(Sprite.player_up_2.getFxImage());
                animal.setAnimation(1);
            }
        }
        if (animal instanceof Ballom) {
            if (animal.getAnimation() == 0) {
                animal.setImg(Sprite.ballom_left1.getFxImage());
                animal.setAnimation(1);
            } else if (animal.getAnimation() == 1) {
                animal.setImg(Sprite.ballom_left2.getFxImage());
                animal.setAnimation(2);
            } else if (animal.getAnimation() == 2) {
                animal.setImg(Sprite.ballom_left3.getFxImage());
                animal.setAnimation(3);
            } else {
                animal.setImg(Sprite.ballom_left1.getFxImage());
                animal.setAnimation(1);
            }
        }
        if (animal instanceof Oneal) {
            if (animal.getAnimation() == 0) {
                animal.setImg(Sprite.oneal_left1.getFxImage());
                animal.setAnimation(1);
            } else if (animal.getAnimation() == 1) {
                animal.setImg(Sprite.oneal_left2.getFxImage());
                animal.setAnimation(2);
            } else if (animal.getAnimation() == 2) {
                animal.setImg(Sprite.oneal_left3.getFxImage());
                animal.setAnimation(3);
            } else {
                animal.setImg(Sprite.oneal_left1.getFxImage());
                animal.setAnimation(1);
            }
        }
    }

    // di chuyen xuong trai
    public static void left(Animal animal) {
        if (animal.getY() % 32 == 0 && animal.getX() % 32 == 0) {
            if (animal instanceof Bomber && Blocked.blockLeft(animal)) {
                animal.setDirection("left");
                animal.setCount(4 / speed);
                checkRun(animal);
            }
            if ((animal instanceof Ballom) && Blocked.blockLeft(animal)) {
                animal.setDirection("left");
                animal.setCount(8);
                checkRun(animal);
            }
            if ((animal instanceof Oneal) && Blocked.blockLeft(animal)) {
                animal.setDirection("left");
                animal.setCount(8);
                checkRun(animal);
            }
        }
    }

    // hoat canh di chuyen
    public static void left_step(Animal animal) {
        if (animal instanceof Bomber) {
            if (animal.getAnimation() == 0) {
                animal.setImg(Sprite.player_left.getFxImage());
                animal.setAnimation(1);
            } else if (animal.getAnimation() == 1) {
                animal.setImg(Sprite.player_left_1.getFxImage());
                animal.setAnimation(2);
            } else if (animal.getAnimation() == 2) {
                animal.setImg(Sprite.player_left.getFxImage());
                animal.setAnimation(3);
            } else {
                animal.setImg(Sprite.player_left_2.getFxImage());
                animal.setAnimation(1);
            }
        }
        if (animal instanceof Ballom) {
            if (animal.getAnimation() == 0) {
                animal.setImg(Sprite.ballom_right1.getFxImage());
                animal.setAnimation(1);
            } else if (animal.getAnimation() == 1) {
                animal.setImg(Sprite.ballom_right2.getFxImage());
                animal.setAnimation(2);
            } else if (animal.getAnimation() == 2) {
                animal.setImg(Sprite.ballom_right3.getFxImage());
                animal.setAnimation(3);
            } else {
                animal.setImg(Sprite.ballom_right1.getFxImage());
                animal.setAnimation(1);
            }
        }
        if (animal instanceof Oneal) {
            if (animal.getAnimation() == 0) {
                animal.setImg(Sprite.oneal_right1.getFxImage());
                animal.setAnimation(1);
            } else if (animal.getAnimation() == 1) {
                animal.setImg(Sprite.oneal_right2.getFxImage());
                animal.setAnimation(2);
            } else if (animal.getAnimation() == 2) {
                animal.setImg(Sprite.oneal_right3.getFxImage());
                animal.setAnimation(3);
            } else {
                animal.setImg(Sprite.oneal_right1.getFxImage());
                animal.setAnimation(1);
            }
        }
    }

    // di chuyen xuong phai
    public static void right(Animal animal) {
        if (animal.getY() % 32 == 0 && animal.getX() % 32 == 0) {
            if (animal instanceof Bomber && Blocked.blockRight(animal)) {
                animal.setDirection("right");
                animal.setCount(4 / speed);
                checkRun(animal);
            }
            if ((animal instanceof Ballom) && Blocked.blockRight(animal)) {
                animal.setDirection("right");
                animal.setCount(8);
                checkRun(animal);
            }
            if ((animal instanceof Oneal) && Blocked.blockRight(animal)) {
                animal.setDirection("right");
                animal.setCount(8);
                checkRun(animal);
            }
        }
    }

    // hoat canh di chuyen
    public static void right_step(Animal animal) {
        if (animal instanceof Bomber) {
            if (animal.getAnimation() == 0) {
                animal.setImg(Sprite.player_right.getFxImage());
                animal.setAnimation(1);
            } else if (animal.getAnimation() == 1) {
                animal.setImg(Sprite.player_right_1.getFxImage());
                animal.setAnimation(2);
            } else if (animal.getAnimation() == 2) {
                animal.setImg(Sprite.player_right.getFxImage());
                animal.setAnimation(3);
            } else {
                animal.setImg(Sprite.player_right_2.getFxImage());
                animal.setAnimation(1);
            }
        }
        if (animal instanceof Ballom) {
            if (animal.getAnimation() == 0) {
                animal.setImg(Sprite.ballom_left1.getFxImage());
                animal.setAnimation(1);
            } else if (animal.getAnimation() == 1) {
                animal.setImg(Sprite.ballom_left2.getFxImage());
                animal.setAnimation(2);
            } else if (animal.getAnimation() == 2) {
                animal.setImg(Sprite.ballom_left3.getFxImage());
                animal.setAnimation(3);
            } else {
                animal.setImg(Sprite.ballom_left1.getFxImage());
                animal.setAnimation(1);
            }
        }
        if (animal instanceof Oneal) {
            if (animal.getAnimation() == 0) {
                animal.setImg(Sprite.oneal_left1.getFxImage());
                animal.setAnimation(1);
            } else if (animal.getAnimation() == 1) {
                animal.setImg(Sprite.oneal_left2.getFxImage());
                animal.setAnimation(2);
            } else if (animal.getAnimation() == 2) {
                animal.setImg(Sprite.oneal_left3.getFxImage());
                animal.setAnimation(3);
            } else {
                animal.setImg(Sprite.oneal_left1.getFxImage());
                animal.setAnimation(1);
            }
        }
    }
}
