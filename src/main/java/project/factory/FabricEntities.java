package project.factory;

import project.entities.Entity;
import project.entities.animal.Rabbit;
import project.entities.animal.Wolf;
import project.entities.static_objects.Grass;
import project.entities.static_objects.Rock;
import project.entities.static_objects.Tree;

public class FabricEntities {
    public static Entity createEntity(Entities entity){

        return switch(entity){
            case WOLF -> new Wolf();
            case RABBIT -> new Rabbit();
            case GRASS -> new Grass();
            case TREE -> new Tree();
            case ROCK -> new Rock();
        };
    }
}
