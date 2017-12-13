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
        this.map.render(-645, -20, 1);
        this.map.render(-645, -20, 2);
    }

    public void renderForeground() {
        this.map.render(-645, -20, 3);
        //this.map.render(0, 0, 4);
    }

    public boolean isCollision(float futurX, float futurY) {
        /*if (futurX < 0)
            return true;
        if (futurX + dimPlayerX > dimWindowX)
            return true;
        if (futurY < 0)
            return true;
        if (futurY + dimPlayerY > dimWindowY)
            return true;
        return false;*/
        int tileW = this.map.getTileWidth();
        int tileH = this.map.getTileHeight();
        int logicLayer = this.map.getLayerIndex("hitbox");
        Image tile = this.map.getTileImage( (int) (futurX + dimPlayerX) / tileW, (int) (futurY + dimPlayerY) / tileH, logicLayer);
        boolean collision = tile != null;
        if (collision) {
            Color color = tile.getColor((int) futurY % tileW, (int) futurX % tileH);
            collision = color.getAlpha() > 0;
        }
        return collision;
    }

    public TiledMap getMap() {
        return map;
    }

}
