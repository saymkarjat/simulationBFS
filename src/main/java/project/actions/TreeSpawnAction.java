package project.actions;

import project.entities.Entity;
import project.entities.static_objects.Tree;
import project.factory.Entities;
import project.factory.FabricEntities;
import project.world.WorldMapImpl;

public class TreeSpawnAction extends SpawnAction<Tree>{

    public TreeSpawnAction(WorldMapImpl worldMap) {
        super.spawnRate = (double) (worldMap.getMapX() * worldMap.getMapY()) /20;
    }

    @Override
    public Entity spawnEntity() {
        return FabricEntities.createEntity(Entities.TREE);
    }
}
