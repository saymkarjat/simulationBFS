package project.actions;

import project.world.WorldMap;
import project.world.WorldMapImpl;

public abstract class Action {
    public abstract void perform(WorldMapImpl worldMap);
}
