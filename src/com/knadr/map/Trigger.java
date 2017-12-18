package com.knadr.map;

import com.knadr.entitie.Canadair;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Trigger {

    private TiledMap map;
    private Canadair player;

    public Trigger(Map map, Canadair player) {
        this.map = map.getMap();
        this.player = player;
    }


}
