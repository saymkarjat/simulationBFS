package project.entities;

public interface Recoverable {
    int getHealthBoost();
    void recover(Damageable target);
}
