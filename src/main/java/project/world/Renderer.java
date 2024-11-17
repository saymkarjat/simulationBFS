package project.world;

import project.entities.Entity;

public class Renderer {

    public void renderMap(WorldMapImpl worldMap, int mapX, int mapY) {
        // Проходим по всем координатам карты
        for (int y = 0; y < mapY; y++) {
            for (int x = 0; x < mapX; x++) {
                Coordinate coordinate = new Coordinate(x, y);
                Entity entity = worldMap.getEntity(coordinate);

                // Выводим эмодзи или символ пустой клетки с фиксированной шириной
                if (entity != null) {
                    System.out.printf("%-3s", entity); // Вывод сущности с выравниванием по ширине 3 символа
                } else {
                    System.out.printf("%-3s", "."); // Символ для пустой клетки с выравниванием по ширине 3 символа
                }
            }
            System.out.println(); // Переход на новую строку
        }
    }
}

