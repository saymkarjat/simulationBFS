package project.entities;

import lombok.Getter;
import lombok.Setter;
import project.world.Coordinate;
@Getter
@Setter
public abstract class Entity {
    private Coordinate coordinate;

}
