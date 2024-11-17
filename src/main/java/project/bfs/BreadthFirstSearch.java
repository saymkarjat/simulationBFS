package project.bfs;

import project.entities.Entity;
import project.entities.animal.Herbivore;
import project.entities.animal.Predator;
import project.entities.static_objects.Grass;
import project.world.Coordinate;
import project.world.WorldMapImpl;

import java.util.*;

public class BreadthFirstSearch {

    WorldMapImpl worldMap;
    Set<Coordinate> visited;
    Queue<Coordinate> scanEveryCoordinate;
    Map<Coordinate, Coordinate> pathConnector;
    private static final List<Coordinate> DIRECTIONS = Arrays.asList(
            new Coordinate(0, -1),  // Вверх
            new Coordinate(0, 1),   // Вниз
            new Coordinate(-1, 0),  // Влево
            new Coordinate(1, 0),   // Вправо
            new Coordinate(-1, -1), // Верхний левый угол
            new Coordinate(-1, 1),  // Верхний правый угол
            new Coordinate(1, -1),  // Нижний левый угол
            new Coordinate(1, 1)    // Нижний правый угол
    );

    public BreadthFirstSearch(WorldMapImpl worldMap) {
        this.worldMap = worldMap;
        this.visited = new HashSet<>();
        this.scanEveryCoordinate = new LinkedList<>();
        this.pathConnector = new HashMap<>();
    }

    private boolean isPointInsideGrid(Coordinate coordinate) {
        if (coordinate.getX() >= 0 && coordinate.getX() < worldMap.getMapX() && coordinate.getY() >= 0 && coordinate.getY() < worldMap.getMapY()) {
            return true;
        }
        return false;
    }

    private boolean isValidCoordinate(Coordinate coordinate, Entity thisEntity) {
        if (!isPointInsideGrid(coordinate)) {
            return false;
        }
        // Получаем сущность по данной координате
        Entity entityExistOrNotExist = worldMap.getEntity(coordinate);

        // Если по координате ничего нет, возвращаем true
        if (entityExistOrNotExist == null) {
            return true;
        }
        if (entityExistOrNotExist instanceof Predator) {
            return false;
        }

        if (thisEntity instanceof Predator && entityExistOrNotExist instanceof Herbivore) {
            return true; // Хищник нашел травоядного, возвращаем путь (true)
        }

        if (thisEntity instanceof Herbivore && entityExistOrNotExist instanceof Grass) {
            return true;
        }
        return false;
    }

    private boolean isFoodFoundByCreature(Entity entity, Coordinate coordinate) {
        Entity targetEntity = worldMap.getEntity(coordinate);
        if (entity instanceof Predator && targetEntity instanceof Herbivore) {
            return true; // Хищник нашел травоядного
        }
        if (entity instanceof Herbivore && targetEntity instanceof Grass) {
            return true; // Травоядное нашло траву
        }
        return false;
    }

    public List<Coordinate> findPathToFood(Entity entity) {
        Coordinate startCoordinate = entity.getCoordinate();
        scanEveryCoordinate.add(startCoordinate);
        visited.add(startCoordinate);

        // Пока очередь не пуста, продолжаем поиск в ширину
        while (!scanEveryCoordinate.isEmpty()) {
            Coordinate currentCoordinate = scanEveryCoordinate.poll();

            // Перебираем все возможные направления из текущей координаты
            for (Coordinate direction : DIRECTIONS) {
                int newX = currentCoordinate.getX() + direction.getX();
                int newY = currentCoordinate.getY() + direction.getY();
                Coordinate neighbourCoordinate = new Coordinate(newX, newY);

                // Проверяем, допустима ли эта координата и ещё не была ли она посещена
                if (!visited.contains(neighbourCoordinate) && isValidCoordinate(neighbourCoordinate, entity)) {
                    // Добавляем в очередь для дальнейшего сканирования и отмечаем как посещённую
                    scanEveryCoordinate.add(neighbourCoordinate);
                    visited.add(neighbourCoordinate);

                    // Сохраняем связь в пути, чтобы затем можно было восстановить путь
                    pathConnector.put(neighbourCoordinate, currentCoordinate);

                    // Если нашли цель (например, трава для травоядного или травоядное для хищника), возвращаем список
                    if (isFoodFoundByCreature(entity, neighbourCoordinate)) {
                        return buildPath(neighbourCoordinate);
                    }
                }

            }
        }

        return new ArrayList<>();
    }

    private List<Coordinate> buildPath(Coordinate targetCoordinate) {
        List<Coordinate> path = new LinkedList<>();
        Coordinate current = targetCoordinate;
        while (current != null) {
            path.add(0, current); // Добавляем в начало списка, чтобы получить путь от старта к цели
            current = pathConnector.get(current);
        }
        return path;
    }
}






