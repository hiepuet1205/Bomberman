package uet.oop.bomberman.entities;

import uet.oop.bomberman.entities.block.Brick;
import uet.oop.bomberman.entities.block.Grass;
import uet.oop.bomberman.entities.block.Portal;
import uet.oop.bomberman.entities.block.Wall;
import uet.oop.bomberman.entities.item.FlameItem;
import uet.oop.bomberman.entities.item.SpeedItem;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

import static uet.oop.bomberman.BombermanGame.*;

public class Map {
    public static void createMap(String path) {
        System.out.println(System.getProperty("user.dir"));
        final File fileName = new File(path);
        try (FileReader inputFile = new FileReader(fileName)) {
            Scanner sc = new Scanner(inputFile);
            String line = sc.nextLine();

            StringTokenizer tokens = new StringTokenizer(line);
            l = Integer.parseInt(tokens.nextToken());
            h = Integer.parseInt(tokens.nextToken());
            w = Integer.parseInt(tokens.nextToken());

            while (sc.hasNextLine()) {
                check = new int[w][h];
                listKill = new int[w][h];
                for (int i = 0; i < h; ++i) {
                    String lineTile = sc.nextLine();
                    StringTokenizer tokenTile = new StringTokenizer(lineTile);

                    for (int j = 0; j < w; j++) {
                        int s = Integer.parseInt(tokenTile.nextToken());
                        Entity entity;
                        switch (s) {
                            case 1:
                                entity = new Portal(j, i, Sprite.grass.getFxImage());
                                s = 0;
                                break;
                            case 2:
                                entity = new Wall(j, i, Sprite.wall.getFxImage());
                                break;
                            case 3:
                                entity = new Brick(j, i, Sprite.brick.getFxImage());
                                break;
                            case 6:
                                entity = new SpeedItem(j, i, Sprite.brick.getFxImage(), false);
                                break;
                            case 7:
                                entity = new FlameItem(j, i, Sprite.brick.getFxImage(), false);
                                break;
                            default:
                                entity = new Grass(j, i, Sprite.grass.getFxImage());
                        }
                        check[j][i] = s;
                        block.add(entity);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
