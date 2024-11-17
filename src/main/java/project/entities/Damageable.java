package project.entities;

public interface Damageable extends Recoverable {
    void takeDamage(int damage);
    boolean isAlive();
    ;
}
