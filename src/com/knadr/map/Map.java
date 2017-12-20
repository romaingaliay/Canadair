package com.knadr.map;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import java.io.File;

import static com.knadr.entitie.Canadair.dimPlayerX;
import static com.knadr.entitie.Canadair.dimPlayerY;

public class Map {

    private TiledMap map;

    public void init() throws SlickException {
        this.map = new TiledMap("res" + File.separator + "img" + File.separator + "map" + File.separator + "knadrMapV2.tmx",
                "res" + File.separator + "img" + File.separator + "map" + File.separator + "objets");
    }

    public void renderBackground() {
        this.map.render(-645, 0, 0);
        this.map.render(-645, -15, 1);
        this.map.render(-645, -20, 2);
    }

    public void renderForeground() {
        this.map.render(-645, -15, 3);
        //this.map.render(-645, 0, 4);
        //this.map.render(-645, 15, 5);
    }

    public boolean isCollision(float x, float y) {
        int tileW = this.map.getTileWidth();
        int tileH = this.map.getTileHeight();
        int logicLayer = this.map.getLayerIndex("hitbox");
        Image tile = this.map.getTileImage((int) (x + dimPlayerX + 645) / tileW, (int) (y + dimPlayerY) / tileH, logicLayer);
        boolean collision = tile != null;

        if (collision) {
            Color color = tile.getColor((int) (x + 645) % tileW, (int) y % tileH);
            collision = color.getAlpha() > 0;
        }

        return collision;
    }

    public boolean isSubmerger(float y) {
        return y + dimPlayerY > 719;

    }

    public boolean isTooHigh(float y) {
        return y < 0;
    }

    public boolean isInWater(float x, float y) {
        int tileW = this.map.getTileWidth();
        int tileH = this.map.getTileHeight();
        int logicLayer = this.map.getLayerIndex("water");
        Image tile = this.map.getTileImage((int) (x + dimPlayerX + 645) / tileW, (int) (y + dimPlayerY - 15) / tileH, logicLayer);

        return tile != null;
    }

    public TiledMap getMap() {
        return map;
    }

}
