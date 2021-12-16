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

// bomb
public class Bomb extends Entity {
    private static long timeBomb;
    private static long timeTmp;
    private static Entity bomb; // bomb
    private static int animation = 1; // de dieu khien hoat anh bomb
    private static int animationBombExplosion = 1; // de dieu khien hoat anh bomb no

    private static final List<Entity> listBombMiddleW = new ArrayList<>(); // de tao ra cac thanh phan khi bomb no theo chieu ngang
    private static final List<Entity> listBombMiddleH = new ArrayList<>(); // de tao ra cac thanh phan khi bomb no theo chieu doc

    public static int powerBomb = 0; // chi so suc manh cua bomb

    private static int powerBombDown = 0;// chi so suc manh cua bomb duoi
    private static int powerBombUp = 0;// chi so suc manh cua bomb tren
    private static int powerBombLeft = 0;// chi so suc manh cua bomb trai
    private static int powerBombRight = 0;// chi so suc manh cua bomb phai

    private static Entity edge_down = null; // cac thanh phan canh khi bomb no (duoi)
    private static Entity edge_up = null; // cac thanh phan canh khi bomb no (tren)
    private static Entity edge_left = null; // cac thanh phan canh khi bomb no (trai)
    private static Entity edge_right = null; // cac thanh phan canh khi bomb no (phai)
    private static boolean isEdge = false;
    private static boolean isMiddle = false;

    public static int isBomb = 0;   //0 no bomb /1 had bomb /2 explosion

    // khoi tao
    public Bomb(int x, int y, Image img) {
        super(x, y, img);
    }

    // dat bomb
    public static void putBomb() {
        // neu nhu khong co bomb va so luong bomb > 0
        if (isBomb == 0 && bombNumber > 0) {
            bombNumber--; // giam so luong bomb
            isBomb = 1; // chuyen trang thai sang co bomb
            timeBomb = System.currentTimeMillis();// set time
            timeTmp = timeBomb;// set time
            int x = player.getX() / 32; // lay ra chi so x
            int y = player.getY() / 32;// lay ra chi so y
            x = Math.round(x);// lam tron
            y = Math.round(y);// lam tron
            bomb = new Bomb(x, y, Sprite.bomb.getFxImage()); // tao ra bomb tai vi tri x y
            block.add(bomb);// danh sach cac khoi them bomb
            check[player.getX() / 32][player.getY() / 32] = 4; // set vi tri x y = 4(die)
        }
    }

    // hoat cach cua bomb
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

    // tao ra cac canh khi bomb no
    public static void createEdge() {
        // check xem co bi chan duoi ko
        if (Blocked.blockBombDown(bomb, 0)) {
            edge_down = new Bomb(bomb.getX() / 32, bomb.getY() / 32 + 1,
                    Sprite.bomb_exploded.getFxImage()); // khoi tao canh tai vi tri cua bomb
            // neu powerbomb > 0 => di chuyen canh den vi tri
            if (powerBomb > 0)
                for (int i = 1; i <= powerBomb; i++)
                    if (Blocked.blockBombDown(bomb, i)) {
                        edge_down.setY(bomb.getY() + 32 + i * 32); // set vi tri cua canh
                        powerBombDown++;
                    } else break;
            block.add(edge_down); // them canh vao danh sach cac khoi
        }
        // check xem co bi chan tren ko
        if (Blocked.blockBombUp(bomb, 0)) {
            edge_up = new Bomb(bomb.getX() / 32, bomb.getY() / 32 - 1,
                    Sprite.bomb_exploded.getFxImage());// khoi tao canh tai vi tri cua bomb
            // neu powerbomb > 0 => di chuyen canh den vi tri
            if (powerBomb > 0)
                for (int i = 1; i <= powerBomb; i++)
                    if (Blocked.blockBombUp(bomb, i)) {
                        edge_up.setY(bomb.getY() - 32 - i * 32);// set vi tri cua canh
                        powerBombUp++;
                    } else break;
            block.add(edge_up);// them canh vao danh sach cac khoi
        }
        // check xem co bi chan trai ko
        if (Blocked.blockBombLeft(bomb, 0)) {
            edge_left = new Bomb(bomb.getX() / 32 - 1, bomb.getY() / 32,
                    Sprite.bomb_exploded.getFxImage());// khoi tao canh tai vi tri cua bomb
            // neu powerbomb > 0 => di chuyen canh den vi tri
            if (powerBomb > 0)
                for (int i = 1; i <= powerBomb; i++)
                    if (Blocked.blockBombLeft(bomb, i)) {
                        edge_left.setX(bomb.getX() - 32 - i * 32);// set vi tri cua canh
                        powerBombLeft++;
                    } else break;
            block.add(edge_left);// them canh vao danh sach cac khoi
        }
        // check xem co bi chan phai ko
        if (Blocked.blockBombRight(bomb, 0)) {
            edge_right = new Bomb(bomb.getX() / 32 + 1, bomb.getY() / 32,
                    Sprite.bomb_exploded.getFxImage());// khoi tao canh tai vi tri cua bomb
            // neu powerbomb > 0 => di chuyen canh den vi tri
            if (powerBomb > 0)
                for (int i = 1; i <= powerBomb; i++)
                    if (Blocked.blockBombRight(bomb, i)) {
                        edge_right.setX(bomb.getX() + 32 + i * 32);// set vi tri cua canh
                        powerBombRight++;
                    } else break;
            block.add(edge_right);// them canh vao danh sach cac khoi
        }
    }

    // khoi tao cac thanh phan ben trong cua bomb khi no
    public static void createMiddle() {
        Entity middle;// khoi tao
        for (int i = 1; i <= powerBombDown; i++) {
            middle = new Bomb(bomb.getX() / 32, bomb.getY() / 32 + i,
                    Sprite.bomb_exploded.getFxImage()); // tao ra cac middle tu bomb xuong duoi
            listBombMiddleH.add(middle); // them vao danh sach middle
        }
        for (int i = 1; i <= powerBombUp; i++) {
            middle = new Bomb(bomb.getX() / 32, bomb.getY() / 32 - i,
                    Sprite.bomb_exploded.getFxImage());// tao ra cac middle tu bomb xuong tren
            listBombMiddleH.add(middle); // them vao danh sach middle
        }
        for (int i = 1; i <= powerBombLeft; i++) {
            middle = new Bomb(bomb.getX() / 32 - i, bomb.getY() / 32,
                    Sprite.bomb_exploded.getFxImage());// tao ra cac middle tu bomb xuong trai
            listBombMiddleW.add(middle); // them vao danh sach middle
        }
        for (int i = 1; i <= powerBombRight; i++) {
            middle = new Bomb(bomb.getX() / 32 + i, bomb.getY() / 32,
                    Sprite.bomb_exploded.getFxImage());// tao ra cac middle tu bomb xuong phai
            listBombMiddleW.add(middle); // them vao danh sach middle
        }
        block.addAll(listBombMiddleW); // danh sach khoi them tat ca cac middle theo chieu ngang
        block.addAll(listBombMiddleH); // danh sach khoi them tat ca cac middle theo chieu doc
    }

    // hoat canh khi bomb no
    public static void animationExplosion() {
        if (animationBombExplosion == 1) {
            bomb.setImg(Sprite.bomb_exploded.getFxImage());
            listKill[bomb.getX() / 32][bomb.getY() / 32] = 4; // set vi tri x y = 4(die)
            if (Blocked.blockBombDown(bomb, powerBombDown)) {
                edge_down.setImg(Sprite.explosion_vertical_down_last.getFxImage());
                listKill[edge_down.getX() / 32][edge_down.getY() / 32] = 4; // set vi tri x y = 4(die)
            }
            if (Blocked.blockBombUp(bomb, powerBombUp)) {
                edge_up.setImg(Sprite.explosion_vertical_top_last.getFxImage());
                listKill[edge_up.getX() / 32][edge_up.getY() / 32] = 4; // set vi tri x y = 4(die)
            }
            if (Blocked.blockBombLeft(bomb, powerBombLeft)) {
                edge_left.setImg(Sprite.explosion_horizontal_left_last.getFxImage());
                listKill[edge_left.getX() / 32][edge_left.getY() / 32] = 4; // set vi tri x y = 4(die)
            }
            if (Blocked.blockBombRight(bomb, powerBombRight)) {
                edge_right.setImg(Sprite.explosion_horizontal_right_last.getFxImage());
                listKill[edge_right.getX() / 32][edge_right.getY() / 32] = 4; // set vi tri x y = 4(die)
            }
            if (listBombMiddleH.size() > 0)
                for (Entity e : listBombMiddleH) {
                    e.setImg(Sprite.explosion_vertical.getFxImage());
                    listKill[e.getX() / 32][e.getY() / 32] = 4;// set vi tri x y = 4(die)
                }
            if (listBombMiddleW.size() > 0)
                for (Entity e : listBombMiddleW) {
                    e.setImg(Sprite.explosion_horizontal.getFxImage());
                    listKill[e.getX() / 32][e.getY() / 32] = 4;// set vi tri x y = 4(die)
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

    // check bomb
    private static void checkActive() {
        // neu co bomb
        if (isBomb == 1) {
            // sau 2s => dem nguoc bang cach tang timeTmp => dong thoi goi ham animationBomb de tao hoat cach bomb
            if (System.currentTimeMillis() - timeBomb < 2000) {
                if (System.currentTimeMillis() - timeTmp > 100) {
                    animationBomb();
                    timeTmp += 100;
                }
            } else {
                // dem nguoc xong set isBomb=2(bomb no)
                // reset lai time
                isBomb = 2;
                timeBomb = System.currentTimeMillis();
                timeTmp = timeBomb;
            }
        }
    }

    private static void checkExplosion() {
        // isBomb=2(bomb no)
        if (isBomb == 2)
            // sau 1s thi dem nguoc tuong tu ben tren
            if (System.currentTimeMillis() - timeBomb < 1000) {
                if (System.currentTimeMillis() - timeTmp > 100) {
                    // neu chua co canh => tao canh => set isEdge=true
                    if (!isEdge) {
                        createEdge();
                        isEdge = true;
                    }
                    // neu power > 0 => tao middle => set isMiddle=true
                    if (powerBomb > 0) {
                        if (!isMiddle) {
                            createMiddle();
                            isMiddle = true;
                        }
                    }
                    // am thanh bomb no
                    new Sound("sound/bomb_explosion.wav", "explosion");
                    // dong thoi goi ham animationExplosion de tao hoat cach bomb no
                    animationExplosion();
                    timeTmp += 100;
                }
            } else {
                // dem nguoc xong reset lai cac thanh phan
                reset();
            }
    }

    // ham lam moi lai cac thanh phan
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

    @Override
    public void update() {
        checkActive(); // check bomb
        checkExplosion(); // check bomb no
    }
}