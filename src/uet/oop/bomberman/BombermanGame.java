package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.animal.Animal;
import uet.oop.bomberman.entities.animal.Bomber;
import uet.oop.bomberman.entities.block.Portal;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.audio.Sound.updateSound;
import static uet.oop.bomberman.entities.Menu.*;
import static uet.oop.bomberman.entities.Move.*;
import static uet.oop.bomberman.entities.block.Bomb.putBomb;
import static uet.oop.bomberman.entities.block.Portal.isPortal;
import static uet.oop.bomberman.entities.level.NextLevel.*;

public class BombermanGame extends Application {
    
    public static final int WIDTH = 25;
    public static final int HEIGHT = 15;
    public static int w = 0;
    public static int h = 0;
    public static int l = 1;
    public static boolean running;

    public static final List<Entity> block = new ArrayList<>();
    public static List<Animal> enemy = new ArrayList<>();
    public static int[][] listKill;
    public static int[][] check;
    public static Animal player;
    
    private GraphicsContext gc;
    private Canvas canvas;


    public static ImageView authorView;

    private int frame = 1;
    private long lastTime;

    public static Stage mainStage = null;


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {

        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        canvas.setTranslateY(32);
        gc = canvas.getGraphicsContext2D();

        Image author = new Image("images/author1.png");
        authorView = new ImageView(author);
        authorView.setX(-255);
        authorView.setY(-75);
        authorView.setScaleX(0.65);
        authorView.setScaleY(0.65);

        // Tao root container
        Group root = new Group();
        createMenu(root);
        root.getChildren().add(canvas);
        root.getChildren().add(authorView);

        // Tao scene
        Scene scene = new Scene(root);

        scene.setOnKeyPressed(event  -> {
            if (player.isLiVe())
                switch (event.getCode()) {
                    case UP:
                        up(player);
                        break;
                    case DOWN:
                        down(player);
                        break;
                    case LEFT:
                        left(player);
                        break;
                    case RIGHT:
                        right(player);
                        break;
                    case SPACE:
                        putBomb();
                        break;
                }
        });


        // Them scene vao stage
        stage.setScene(scene);
        stage.setTitle("Bomberman");
        mainStage = stage;
        mainStage.show();

        lastTime = System.currentTimeMillis();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (running) {
                    render();
                    update();
                    time();
                    updateMenu();
                }
            }
        };
        timer.start();

        player = new Bomber(1, 1, Sprite.player_right_2.getFxImage());
        player.setLive(false);
    }

    /*public void createMap() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                Entity object;
                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
                    object = new Wall(i, j, Sprite.wall.getFxImage());
                }
                else {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                }
                stillObjects.add(object);
            }
        }
    }*/

    public void update() {
        block.forEach(Entity::update);
        enemy.forEach(Entity::update);
        player.update();

        player.setCountToRun(player.getCountToRun() + 1);
        if (player.getCountToRun() == 4) {
            checkRun(player);
            player.setCountToRun(0);
        }

        for (Animal a : enemy) {
            a.setCountToRun(a.getCountToRun() + 1);
            if (a.getCountToRun() == 8) {
                checkRun(a);
                a.setCountToRun(0);
            }
        }

        if (enemy.size() == 0 && !isPortal && !wait) {
            Entity portal = new Portal(w - 2, h - 2, Sprite.portal.getFxImage());
            block.add(portal);
            if (player.getX() / 32 == portal.getX() / 32 && player.getY() / 32 == portal.getY() / 32) {
                wait = true;
                waitingTime = System.currentTimeMillis();
            }
        }
        waitToLevelUp();
        updateSound();
    }

    public void time() {
        frame++;

        long now = System.currentTimeMillis();
        if (now - lastTime > 1000) {
            lastTime = System.currentTimeMillis();
            mainStage.setTitle("Bomberman | " + frame + " frame");
            frame = 0;

            time.setText("Time: " + timeNumber);
            timeNumber--;
            if (timeNumber < 0)
                player.setLive(false);
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        block.forEach(g -> g.render(gc));
        enemy.forEach(g -> g.render(gc));
        player.render(gc);
    }
}
