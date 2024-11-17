package project.entities.animal;

import lombok.Getter;
import lombok.Setter;
import project.bfs.BreadthFirstSearch;
import project.entities.Damageable;
import project.entities.Entity;
import project.world.Coordinate;
import project.world.WorldMapImpl;

import java.util.List;
import java.util.Random;

@Setter
@Getter
public abstract class Creature extends Entity implements Damageable {

    private int hp;
    private int maxHp;
    private int maxSpeed;
    private int attackPower;

    public void makeMove(WorldMapImpl map){
        BreadthFirstSearch pathBFS = new BreadthFirstSearch(map);
        int currentSpeed = new Random().nextInt(maxSpeed);
        List<Coordinate> path = pathBFS.findPathToFood(this);
        if (path.isEmpty()){
            return;
        }
        if (path.size()-1 <= currentSpeed){
            Entity victim = map.getEntity(path.get(path.size()-1));
            attack((Damageable) victim);
            if (!((Damageable) victim).isAlive()){
                map.replaceEntity(this.getCoordinate(), victim.getCoordinate());
            }
        }
        else {
            map.replaceEntity(this.getCoordinate(), path.get(currentSpeed));
        }
    }

    public void attack(Damageable target) {
       target.takeDamage(this.attackPower);
       recover(target);
    }

    @Override
    public void recover(Damageable target) {
        setHp(getHp()+target.getHealthBoost());
    }

    public void setHp(int hp) {
        if (hp < 0) {
            this.hp = 0;
        } else if (hp > maxHp) {
            this.hp = maxHp;
        } else {
            this.hp = hp;
        }
    }
    @Override
    public void takeDamage(int damage) {
        setHp(getHp()-damage);
    }

    @Override
    public boolean isAlive() {
        if (getHp() == 0){
            return false;
        }
        return true;
    }

    @Override
    public int getHealthBoost() {
        return attackPower;
    }
}
