package project.world;

import project.entities.Entity;

public interface WorldMap {

    void addEntity( Coordinate coordinates, Entity entity);

    Entity getEntity(Coordinate coordinates);

    boolean isEntityExistAtCoordinate(Coordinate coordinates);

    void removeEntity(Coordinate coordinates);

    void replaceEntity(Coordinate current, Coordinate target);
}
