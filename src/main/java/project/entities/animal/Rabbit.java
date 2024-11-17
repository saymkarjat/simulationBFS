package project.entities.animal;

public class Rabbit extends Herbivore {

    private final int RABBIT_MAX_SPEED = 3;
    private final int RABBIT_HP = 100;
    private final int RABBIT_AMOUNT_OF_GRASS = 85;

    public Rabbit() {
        this.setMaxHp(RABBIT_HP);
        this.setHp(RABBIT_HP);
        this.setMaxSpeed(RABBIT_MAX_SPEED);
        this.setAttackPower(RABBIT_AMOUNT_OF_GRASS);
    }

    @Override
    public String toString() {
        return "\uD83D\uDC07";
    }
}
