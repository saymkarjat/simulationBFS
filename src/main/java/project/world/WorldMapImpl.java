package project.world;

import lombok.Getter;
import lombok.Setter;
import project.entities.Entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class WorldMapImpl implements WorldMap {
    @Setter
    @Getter
    private int mapX;
    @Setter
    @Getter
    private int mapY;
    private Map<Coordinate, Entity> worldMap = new HashMap<>();

    public WorldMapImpl(int mapX, int mapY) {
        this.mapX = mapX;
        this.mapY = mapY;
    }

    public Set<Coordinate> getAllCoordinates() {
        return worldMap.keySet();
    }


    @Override
    public void addEntity(Coordinate coordinates, Entity entity) {
        entity.setCoordinate(coordinates);
        worldMap.put(coordinates, entity);
    }

    @Override
    public Entity getEntity(Coordinate coordinates) {
        return worldMap.get(coordinates);
    }

    @Override
    public boolean isEntityExistAtCoordinate(Coordinate coordinates) {
        if (worldMap.containsKey(coordinates)) {
            return true;
        }

        return false;
    }

    @Override
    public void removeEntity(Coordinate coordinates) {
        worldMap.remove(coordinates);
    }

    @Override
    public void replaceEntity(Coordinate current, Coordinate target) {
        Entity entity = worldMap.get(current);
        removeEntity(current);
        addEntity(target, entity);
    }
}
