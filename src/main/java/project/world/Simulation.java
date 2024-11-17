package project.world;

import project.actions.*;
import project.entities.animal.Creature;
import project.entities.animal.Herbivore;
import project.entities.animal.Predator;
import project.entities.static_objects.Grass;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    WorldMapImpl worldMap;
    List<Action> initActions;
    List<Action> turnActions;
    Renderer renderer;
    private boolean isGameOver = false;
    int iteration = 0;

    public Simulation(int weight, int height) {
        worldMap = new WorldMapImpl(weight, height);
        initActions = new ArrayList<>();
        turnActions = new ArrayList<>();
        renderer = new Renderer();
    }

    public void nextTurn(){
        iteration++;
        for (Action turnAction : turnActions) {
            turnAction.perform(worldMap);
           // addGrass();
            renderer.renderMap(worldMap, worldMap.getMapX(), worldMap.getMapY());
            printCountOfCreatures();

        }
    }

    private void printCountOfCreatures(){
        long countOfHerbivores = worldMap.getAllCoordinates().stream()
                .map(worldMap::getEntity)
                .filter(e -> e instanceof Herbivore)
                .count();
        if (countOfHerbivores == 0){
            stopSimulation();
        }

        long countOfPredators = worldMap.getAllCoordinates().stream()
                .map(worldMap::getEntity)
                .filter(e -> e instanceof Predator)
                .count();

        System.out.println("Ход №" + iteration +"." + "\nНа карте " + countOfHerbivores + " травоядных и " + countOfPredators + " хищников.");
        System.out.println();
    }

    public void startSimulation(){
        createActions();
        for (Action initAction : initActions) {
            initAction.perform(worldMap);
        }
        while (!isGameOver){
            nextTurn();
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    private void stopSimulation(){
        isGameOver = true;
        System.out.println("Симуляция завершена!" + "количество ходов = "+iteration);
    }

    private void addGrass(){
        long grassCount = worldMap.getAllCoordinates().stream()
                .map(worldMap::getEntity)
                .filter(e -> e instanceof Grass)
                .count();
        if (grassCount<3){
            GrassSpawnAction spawnGrass = new GrassSpawnAction(worldMap);
            spawnGrass.perform(worldMap);
        }
    }

    private void createActions(){
        initActions.add(new RockSpawnAction(worldMap));
        initActions.add(new GrassSpawnAction(worldMap));
        initActions.add(new TreeSpawnAction(worldMap));
        initActions.add(new WolfSpawnAction(worldMap));
        initActions.add(new RabbitSpawnAction(worldMap));
        turnActions.add(new MoveAction());
    }


}
