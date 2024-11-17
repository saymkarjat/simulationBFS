package project.actions;

import lombok.NonNull;
import project.entities.Entity;
import project.world.Coordinate;
import project.world.WorldMap;
import project.world.WorldMapImpl;

import java.util.Random;

public abstract class SpawnAction<T extends Entity> extends Action {
    protected double spawnRate;
    private final Random random = new Random();

    @Override
    public void perform(WorldMapImpl worldMap) {
        int rate = 0;
        while (rate < spawnRate) {
            Coordinate coordinate = generateUniqueCoordinates(worldMap);
            Entity entity = spawnEntity();
            worldMap.addEntity(coordinate, entity);
            rate++;
        }
    }

    public Coordinate generateUniqueCoordinates(WorldMapImpl worldMap) {
        int maxAttempts = worldMap.getMapX() * worldMap.getMapY();
        int attempts = 0;
        Coordinate uniqueCoordinate;
        do {
            if (attempts++ > maxAttempts) {
                throw new RuntimeException("Свободных координат не осталось!");
            }
            int x = random.nextInt(worldMap.getMapX());
            int y = random.nextInt(worldMap.getMapY());
            uniqueCoordinate = new Coordinate(x, y);
        } while (worldMap.isEntityExistAtCoordinate(uniqueCoordinate));
        return uniqueCoordinate;
    }

    public abstract Entity spawnEntity();
}
