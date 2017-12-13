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

    public void update() throws SlickException {
        if (isInTrigger(0))
            if ("teleport".equals(this.map.getObjectType(0, 4)))
                this.teleport(4);
    }

    private boolean isInTrigger(int id) {
        System.out.println(this.map.getObjectType(0, id));
        System.out.println(this.map.getObjectX(0, id));
        System.out.println(this.map.getObjectY(0, id));
        System.out.println(this.map.getObjectWidth(0, id));
        System.out.println(this.map.getObjectHeight(0, id));

        return this.player.getX() > this.map.getObjectX(0, id)
                && this.player.getX() < this.map.getObjectX(0, id) + this.map.getObjectWidth(0, id)
                && this.player.getY() > this.map.getObjectY(0, id)
                && this.player.getY() < this.map.getObjectY(0, id) + this.map.getObjectHeight(0, id);
    }

    private void teleport(int objectID) {
        this.player.setX(Float.parseFloat(this.map.getObjectProperty(0, objectID, "dest-x",
                Float.toString(this.player.getX()))));
        this.player.setY(Float.parseFloat(this.map.getObjectProperty(0, objectID, "dest-y",
                Float.toString(this.player.getY()))));
    }

    /*


 <objectgroup name="trigger" width="500" height="720">
  <object id="4" name="teleport" type="teleport" x="13460" y="0" width="500" height="720">
   <properties>
    <property name="dest-x" value="1290"/>
    <property name="dest-y" value="360"/>
   </properties>
  </object>
 </objectgroup>

     */
}
