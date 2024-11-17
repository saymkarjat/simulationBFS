package project.actions;

import project.entities.animal.Creature;
import project.world.Coordinate;
import project.world.WorldMapImpl;

import java.util.HashSet;

public class MoveAction extends Action {
    @Override
    public void perform(WorldMapImpl worldMap) {
        HashSet<Coordinate> coordinateHashSet = new HashSet<>(worldMap.getAllCoordinates());
        coordinateHashSet.stream()
                .map(worldMap::getEntity)
                .filter(e->e instanceof Creature)
                .forEach(e->((Creature) e).makeMove(worldMap));
    }
}
