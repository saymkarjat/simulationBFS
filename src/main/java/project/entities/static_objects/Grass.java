package project.entities.static_objects;

import lombok.Getter;
import lombok.Setter;
import project.entities.Damageable;
import project.entities.Entity;

public class Grass extends Entity implements Damageable {
    @Getter
    @Setter
    private int amount;
    private final int maxAmount;

    public Grass() {
        this.amount = 100;
        this.maxAmount = 100;
    }

    @Override
    public String toString() {
        return "\uD83C\uDF40";
    }

    @Override
    public void takeDamage(int damage) {
        amount -= damage; // Уменьшаем amount на damage

        if (amount < 0) {
            amount = 0; // Не допускаем отрицательных значений
        }

    }

    @Override
    public boolean isAlive() {
       if (amount == 0){
           return false;
       }
       return true;
    }

    @Override
    public int getHealthBoost() {
        return amount;
    }

    @Override
    public void recover(Damageable target) {

    }
}
