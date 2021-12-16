package uet.oop.bomberman.entities;

import static uet.oop.bomberman.BombermanGame.*;

public class Blocked {
    // check xem co bi chan duoi ko(danh cho di chuyen cua animal)
    public static boolean blockDown(Entity entity){
        return check[entity.getX()/32][entity.getY()/32+1] == 0;
    }

    // check xem co bi chan tren ko(danh cho di chuyen cua animal)
    public static boolean blockUp(Entity entity){
        return check[entity.getX()/32][entity.getY()/32-1] == 0;
    }

    // check xem co bi chan trai ko(danh cho di chuyen cua animal)
    public static boolean blockLeft(Entity entity){
        return check[entity.getX()/32-1][entity.getY()/32] == 0;
    }

    // check xem co bi chan phai ko(danh cho di chuyen cua animal)
    public static boolean blockRight(Entity entity){
        return check[entity.getX()/32+1][entity.getY()/32] == 0;
    }

    // check xem bomb co bi chan duoi ko
    public static boolean blockBombDown(Entity entity, int power){
        return check[entity.getX() / 32][entity.getY() / 32 + 1 + power] == 0// ko co j
                || check[entity.getX() / 32][entity.getY() / 32 + 1 + power] == 3 // brick
                || check[entity.getX() / 32][entity.getY() / 32 + 1 + power] == 6 // SpeedItem
                || check[entity.getX() / 32][entity.getY() / 32 + 1 + power] == 7 // FlameItem
                || check[entity.getX() / 32][entity.getY() / 32 + 1 + power] == 8;
    }

    // check xem bomb co bi chan tren ko
    public static boolean blockBombUp(Entity entity, int power){
        return check[entity.getX() / 32][entity.getY() / 32 - 1 - power] == 0
                || check[entity.getX() / 32][entity.getY() / 32 - 1 - power] == 3
                || check[entity.getX() / 32][entity.getY() / 32 - 1 - power] == 6
                || check[entity.getX() / 32][entity.getY() / 32 - 1 - power] == 7
                || check[entity.getX() / 32][entity.getY() / 32 - 1 - power] == 8;
    }

    // check xem bomb co bi chan trai ko
    public static boolean blockBombLeft(Entity entity, int power){
        return check[entity.getX() / 32 - 1 - power][entity.getY() / 32] == 0
                || check[entity.getX() / 32 - 1 - power][entity.getY() / 32] == 3
                || check[entity.getX() / 32 - 1 - power][entity.getY() / 32] == 6
                || check[entity.getX() / 32 - 1 - power][entity.getY() / 32] == 7
                || check[entity.getX() / 32 - 1 - power][entity.getY() / 32] == 8;
    }

    // check xem bomb co bi chan phai ko
    public static boolean blockBombRight(Entity entity, int power){
        return check[entity.getX() / 32 + 1 + power][entity.getY() / 32] == 0
                || check[entity.getX() / 32 + 1 + power][entity.getY() / 32] == 3
                || check[entity.getX() / 32 + 1 + power][entity.getY() / 32] == 6
                || check[entity.getX() / 32 + 1 + power][entity.getY() / 32] == 7
                || check[entity.getX() / 32 + 1 + power][entity.getY() / 32] == 8;
    }
}
