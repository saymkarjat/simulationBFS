package project.actions;

import project.entities.Entity;
import project.entities.animal.Rabbit;
import project.factory.Entities;
import project.factory.FabricEntities;
import project.world.WorldMapImpl;

public class RabbitSpawnAction extends SpawnAction<Rabbit> {

    public RabbitSpawnAction(WorldMapImpl worldMap) {
        super.spawnRate = (double) (worldMap.getMapX() * worldMap.getMapY()) / 20;
    }

    @Override
    public Entity spawnEntity() {
        return FabricEntities.createEntity(Entities.RABBIT);
    }
}
