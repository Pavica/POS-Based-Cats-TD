package ahif18.htlkaindorf.at.cats;

import com.badlogic.gdx.math.Rectangle;

public class Cat {
    public static float catWidth = 75;
    public static float catHeight = 75;

    private Rectangle range;
    private Rectangle body;
    private int damage;
    private float attackInterval;
    private long currentInterval = 0;
    private int textureID;
    //implement different hitboxes
    //Implement AOE attack and AOE attack amount (how many fish are hit by one AOE attack) and ifAOE boolean

    public Cat(Rectangle range, Rectangle body, int damage, float attackInterval, int textureID) {
        this.range = range;
        this.body = body;
        this.damage = damage;
        this.attackInterval = attackInterval;
        this.textureID = textureID;
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

    public int getTextureID() { return textureID; }

    public Rectangle getRange() {
        return range;
    }
}
