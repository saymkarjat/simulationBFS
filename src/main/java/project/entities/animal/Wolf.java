package project.entities.animal;

public class Wolf extends Predator{
    private final int WOLF_MAX_SPEED = 2;
    private final int WOLF_HP = 100;
    private final int WOLF_POWER = 70;

    public Wolf() {
        this.setMaxHp(WOLF_HP);
        this.setHp(WOLF_HP);
        this.setMaxSpeed(WOLF_MAX_SPEED);
        this.setAttackPower(WOLF_POWER);
    }

    @Override
    public String toString() {
        return "\uD83D\uDC3A";
    }
}
