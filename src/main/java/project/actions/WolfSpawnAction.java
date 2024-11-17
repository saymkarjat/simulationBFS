package project.actions;

import project.entities.Entity;
import project.entities.animal.Wolf;
import project.factory.Entities;
import project.factory.FabricEntities;
import project.world.WorldMapImpl;

public class WolfSpawnAction extends SpawnAction<Wolf> {

    public WolfSpawnAction(WorldMapImpl worldMap) {
        super.spawnRate = (double) (worldMap.getMapX() * worldMap.getMapY()) / 90;
    }

    @Override
    public Entity spawnEntity() {
        return FabricEntities.createEntity(Entities.WOLF);
    }
}
