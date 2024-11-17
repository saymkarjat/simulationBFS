package project.actions;

import project.entities.Entity;
import project.entities.static_objects.Rock;
import project.factory.Entities;
import project.factory.FabricEntities;
import project.world.WorldMapImpl;

public class RockSpawnAction extends SpawnAction<Rock>{

    public RockSpawnAction(WorldMapImpl worldMap) {
        super.spawnRate = (double) (worldMap.getMapX() * worldMap.getMapY()) / 90;
    }

    @Override
    public Entity spawnEntity() {
        return FabricEntities.createEntity(Entities.ROCK);
    }
}
