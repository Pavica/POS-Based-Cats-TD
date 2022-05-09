package ahif18.htlkaindorf.at.cats;

import com.badlogic.gdx.math.Rectangle;

public class Cat {
    public static float CAT_WIDTH = 75;
    public static float CAT_HEIGHT = 75;

    public static float CAT_BODY_WIDTH = 64;
    public static float CAT_BODY_HEIGHT = 64;

    private Rectangle range;
    private Rectangle body;
    private int damage;
    private float attackInterval;
    private long currentInterval = 0;
    private int textureID;
    private int aoeAmount;

    public Cat(Rectangle range, Rectangle body, int damage, float attackInterval, int textureID, int aoeAmount) {
        this.range = range;
        this.body = body;
        this.damage = damage;
        this.attackInterval = attackInterval;
        this.textureID = textureID;
        this.aoeAmount = aoeAmount;
    }

    public int getAoeAmount() {
        return aoeAmount;
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
