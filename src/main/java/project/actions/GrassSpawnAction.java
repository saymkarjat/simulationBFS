package project.actions;

import project.entities.Entity;
import project.entities.static_objects.Grass;
import project.factory.Entities;
import project.factory.FabricEntities;
import project.world.WorldMapImpl;

public class GrassSpawnAction extends SpawnAction<Grass> {

    public GrassSpawnAction(WorldMapImpl worldMap) {
        super.spawnRate = (double) (worldMap.getMapX() * worldMap.getMapY()) / 10;
    }

    @Override
    public Entity spawnEntity() {
        return FabricEntities.createEntity(Entities.GRASS);
    }
}
