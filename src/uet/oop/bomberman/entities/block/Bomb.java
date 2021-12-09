package uet.oop.bomberman.entities.block;

import javafx.scene.image.Image;
import uet.oop.bomberman.audio.Sound;
import uet.oop.bomberman.entities.Blocked;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.entities.Menu.bombNumber;


public class Bomb extends Entity {
    private static long timeBomb;
    private static long timeTmp;
    private static Entity bomb;
    private static int animation = 1;
    private static int animationBombExplosion = 1;

    private static final List<Entity> listBombMiddleW = new ArrayList<>();
    private static final List<Entity> listBombMiddleH = new ArrayList<>();

    public static int powerBomb = 0;

    private static int powerBombDown = 0;
    private static int powerBombUp = 0;
    private static int powerBombLeft = 0;
    private static int powerBombRight = 0;

    private static Entity edge_down = null;
    private static Entity edge_up = null;
    private static Entity edge_left = null;
    private static Entity edge_right = null;
    private static boolean isEdge = false;
    private static boolean isMiddle = false;

    public static int isBomb = 0;   //0 no bomb /1 had bomb /2 explosion

    public Bomb(int x, int y, Image img) {
        super(x, y, img);
    }

    public static void putBomb() {
        if (isBomb == 0 && bombNumber > 0) {
            bombNumber--;
            isBomb = 1;
            timeBomb = System.currentTimeMillis();
            timeTmp = timeBomb;
            int x = player.getX() / 32;
            int y = player.getY() / 32;
            x = Math.round(x);
            y = Math.round(y);
            bomb = new Bomb(x, y, Sprite.bomb.getFxImage());
            block.add(bomb);
            check[player.getX() / 32][player.getY() / 32] = 4;
        }
    }

    public static void animationBomb() {
        if (animation == 1) {
            bomb.setImg(Sprite.bomb.getFxImage());
            animation = 2;
        } else if (animation == 2) {
            bomb.setImg(Sprite.bomb_1.getFxImage());
            animation = 3;
        } else if (animation == 3) {
            bomb.setImg(Sprite.bomb_2.getFxImage());
            animation = 4;
        } else {
            bomb.setImg(Sprite.bomb_1.getFxImage());
            animation = 1;
        }
    }

    public static void createEdge() {
        if (Blocked.blockBombDown(bomb, 0)) {
            edge_down = new Bomb(bomb.getX() / 32, bomb.getY() / 32 + 1,
                    Sprite.bomb_exploded.getFxImage());
            if (powerBomb > 0)
                for (int i = 1; i <= powerBomb; i++)
                    if (Blocked.blockBombDown(bomb, i)) {
                        edge_down.setY(bomb.getY() + 32 + i * 32);
                        powerBombDown++;
                    } else break;
            block.add(edge_down);
        }
        if (Blocked.blockBombUp(bomb, 0)) {
            edge_up = new Bomb(bomb.getX() / 32, bomb.getY() / 32 - 1,
                    Sprite.bomb_exploded.getFxImage());
            if (powerBomb > 0)
                for (int i = 1; i <= powerBomb; i++)
                    if (Blocked.blockBombUp(bomb, i)) {
                        edge_up.setY(bomb.getY() - 32 - i * 32);
                        powerBombUp++;
                    } else break;
            block.add(edge_up);
        }
        if (Blocked.blockBombLeft(bomb, 0)) {
            edge_left = new Bomb(bomb.getX() / 32 - 1, bomb.getY() / 32,
                    Sprite.bomb_exploded.getFxImage());
            if (powerBomb > 0)
                for (int i = 1; i <= powerBomb; i++)
                    if (Blocked.blockBombLeft(bomb, i)) {
                        edge_left.setX(bomb.getX() - 32 - i * 32);
                        powerBombLeft++;
                    } else break;
            block.add(edge_left);
        }
        if (Blocked.blockBombRight(bomb, 0)) {
            edge_right = new Bomb(bomb.getX() / 32 + 1, bomb.getY() / 32,
                    Sprite.bomb_exploded.getFxImage());
            if (powerBomb > 0)
                for (int i = 1; i <= powerBomb; i++)
                    if (Blocked.blockBombRight(bomb, i)) {
                        edge_right.setX(bomb.getX() + 32 + i * 32);
                        powerBombRight++;
                    } else break;
            block.add(edge_right);
        }
    }

    public static void createMiddle() {
        Entity middle;
        for (int i = 1; i <= powerBombDown; i++) {
            middle = new Bomb(bomb.getX() / 32, bomb.getY() / 32 + i,
                    Sprite.bomb_exploded.getFxImage());
            listBombMiddleH.add(middle);
        }
        for (int i = 1; i <= powerBombUp; i++) {
            middle = new Bomb(bomb.getX() / 32, bomb.getY() / 32 - i,
                    Sprite.bomb_exploded.getFxImage());
            listBombMiddleH.add(middle);
        }
        for (int i = 1; i <= powerBombLeft; i++) {
            middle = new Bomb(bomb.getX() / 32 - i, bomb.getY() / 32,
                    Sprite.bomb_exploded.getFxImage());
            listBombMiddleW.add(middle);
        }
        for (int i = 1; i <= powerBombRight; i++) {
            middle = new Bomb(bomb.getX() / 32 + i, bomb.getY() / 32,
                    Sprite.bomb_exploded.getFxImage());
            listBombMiddleW.add(middle);
        }
        block.addAll(listBombMiddleW);
        block.addAll(listBombMiddleH);
    }

    public static void animationExplosion() {
        if (animationBombExplosion == 1) {
            bomb.setImg(Sprite.bomb_exploded.getFxImage());
            listKill[bomb.getX() / 32][bomb.getY() / 32] = 4;
            if (Blocked.blockBombDown(bomb, powerBombDown)) {
                edge_down.setImg(Sprite.explosion_vertical_down_last.getFxImage());
                listKill[edge_down.getX() / 32][edge_down.getY() / 32] = 4;
            }
            if (Blocked.blockBombUp(bomb, powerBombUp)) {
                edge_up.setImg(Sprite.explosion_vertical_top_last.getFxImage());
                listKill[edge_up.getX() / 32][edge_up.getY() / 32] = 4;
            }
            if (Blocked.blockBombLeft(bomb, powerBombLeft)) {
                edge_left.setImg(Sprite.explosion_horizontal_left_last.getFxImage());
                listKill[edge_left.getX() / 32][edge_left.getY() / 32] = 4;
            }
            if (Blocked.blockBombRight(bomb, powerBombRight)) {
                edge_right.setImg(Sprite.explosion_horizontal_right_last.getFxImage());
                listKill[edge_right.getX() / 32][edge_right.getY() / 32] = 4;
            }
            if (listBombMiddleH.size() > 0)
                for (Entity e : listBombMiddleH) {
                    e.setImg(Sprite.explosion_vertical.getFxImage());
                    listKill[e.getX() / 32][e.getY() / 32] = 4;
                }
            if (listBombMiddleW.size() > 0)
                for (Entity e : listBombMiddleW) {
                    e.setImg(Sprite.explosion_horizontal.getFxImage());
                    listKill[e.getX() / 32][e.getY() / 32] = 4;
                }
            animationBombExplosion = 2;

        } else if (animationBombExplosion == 2) {
            bomb.setImg(Sprite.bomb_exploded1.getFxImage());
            if (Blocked.blockBombDown(bomb, powerBombDown))
                edge_down.setImg(Sprite.explosion_vertical_down_last1.getFxImage());
            if (Blocked.blockBombUp(bomb, powerBombUp))
                edge_up.setImg(Sprite.explosion_vertical_top_last1.getFxImage());
            if (Blocked.blockBombLeft(bomb, powerBombLeft))
                edge_left.setImg(Sprite.explosion_horizontal_left_last1.getFxImage());
            if (Blocked.blockBombRight(bomb, powerBombRight))
                edge_right.setImg(Sprite.explosion_horizontal_right_last1.getFxImage());
            if (isMiddle) {
                for (Entity e : listBombMiddleH) {
                    e.setImg(Sprite.explosion_vertical1.getFxImage());
                }
                for (Entity e : listBombMiddleW) {
                    e.setImg(Sprite.explosion_horizontal1.getFxImage());
                }
            }
            animationBombExplosion = 3;

        } else if (animationBombExplosion == 3) {
            bomb.setImg(Sprite.bomb_exploded2.getFxImage());
            if (Blocked.blockBombDown(bomb, powerBombDown))
                edge_down.setImg(Sprite.explosion_vertical_down_last2.getFxImage());
            if (Blocked.blockBombUp(bomb, powerBombUp))
                edge_up.setImg(Sprite.explosion_vertical_top_last2.getFxImage());
            if (Blocked.blockBombLeft(bomb, powerBombLeft))
                edge_left.setImg(Sprite.explosion_horizontal_left_last2.getFxImage());
            if (Blocked.blockBombRight(bomb, powerBombRight))
                edge_right.setImg(Sprite.explosion_horizontal_right_last2.getFxImage());
            if (isMiddle) {
                for (Entity e : listBombMiddleH) {
                    e.setImg(Sprite.explosion_vertical2.getFxImage());
                }
                for (Entity e : listBombMiddleW) {
                    e.setImg(Sprite.explosion_horizontal2.getFxImage());
                }
            }
            animationBombExplosion = 1;
        }
    }

    private static void checkActive() {
        if (isBomb == 1) {
            if (System.currentTimeMillis() - timeBomb < 2000) {
                if (System.currentTimeMillis() - timeTmp > 100) {
                    animationBomb();
                    timeTmp += 100;
                }
            } else {
                isBomb = 2;
                timeBomb = System.currentTimeMillis();
                timeTmp = timeBomb;
            }
        }
    }

    private static void reset(){
        isBomb = 0;
        check[bomb.getX() / 32][bomb.getY() / 32] = 0;
        listKill[bomb.getX() / 32][bomb.getY() / 32] = 0;
        bomb.setImg(Sprite.transparent.getFxImage());
        if (Blocked.blockBombDown(bomb, powerBombDown)) {
            edge_down.setImg(Sprite.transparent.getFxImage());
            check[edge_down.getX() / 32][edge_down.getY() / 32] = 0;
            listKill[edge_down.getX() / 32][edge_down.getY() / 32] = 0;
        }
        if (Blocked.blockBombUp(bomb, powerBombUp)) {
            edge_up.setImg(Sprite.transparent.getFxImage());
            check[edge_up.getX() / 32][edge_up.getY() / 32] = 0;
            listKill[edge_up.getX() / 32][edge_up.getY() / 32] = 0;
        }
        if (Blocked.blockBombLeft(bomb, powerBombLeft)) {
            edge_left.setImg(Sprite.transparent.getFxImage());
            check[edge_left.getX() / 32][edge_left.getY() / 32] = 0;
            listKill[edge_left.getX() / 32][edge_left.getY() / 32] = 0;
        }
        if (Blocked.blockBombRight(bomb, powerBombRight)) {
            edge_right.setImg(Sprite.transparent.getFxImage());
            check[edge_right.getX() / 32][edge_right.getY() / 32] = 0;
            listKill[edge_right.getX() / 32][edge_right.getY() / 32] = 0;
        }
        if (isMiddle) {
            for (Entity e : listBombMiddleW) {
                listKill[e.getX() / 32][e.getY() / 32] = 0;
                check[e.getX() / 32][e.getY() / 32] = 0;
            }
            for (Entity e : listBombMiddleH) {
                listKill[e.getX() / 32][e.getY() / 32] = 0;
                check[e.getX() / 32][e.getY() / 32] = 0;
            }
        }
        block.removeAll(listBombMiddleH);
        block.removeAll(listBombMiddleW);
        listBombMiddleH.clear();
        listBombMiddleW.clear();

        isEdge = false;
        isMiddle = false;
        powerBombDown = 0;
        powerBombUp = 0;
        powerBombLeft = 0;
        powerBombRight = 0;
    }

    private static void checkExplosion() {
        if (isBomb == 2)
            if (System.currentTimeMillis() - timeBomb < 1000) {
                if (System.currentTimeMillis() - timeTmp > 100) {
                    if (!isEdge) {
                        createEdge();
                        isEdge = true;
                    }
                    if (powerBomb > 0) {
                        if (!isMiddle) {
                            createMiddle();
                            isMiddle = true;
                        }
                    }
                    new Sound("sound/bomb_explosion.wav", "explosion");
                    animationExplosion();
                    timeTmp += 100;
                }
            } else {
                reset();
            }
    }

    @Override
    public void update() {
        checkActive();
        checkExplosion();
    }
}