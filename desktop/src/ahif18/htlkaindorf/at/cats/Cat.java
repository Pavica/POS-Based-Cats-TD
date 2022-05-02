package ahif18.htlkaindorf.at.cats;

import com.badlogic.gdx.math.Rectangle;

public class Cat {
    private Rectangle range;
    private Rectangle body;
    private int damage;
    private float attackInterval;
    private long currentInterval = 0;

    public Cat(Rectangle range, Rectangle body, int damage, float attackInterval) {
        this.range = range;
        this.body = body;
        this.damage = damage;
        this.attackInterval = attackInterval;
    }

    public Rectangle getBody() {
        return body;
    }

    public long getCurrentInterval() {
        return currentInterval;
    }

    public void setCurrentInterval(long currentInterval) {
        this.currentInterval = currentInterval;
    }

    public float getAttackInterval() {
        return attackInterval;
    }

    public int getDamage() {
        return damage;
    }

    public Rectangle getRange() {
        return range;
    }
}
