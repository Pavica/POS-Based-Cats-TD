package ahif18.htlkaindorf.at.cats;

import ahif18.htlkaindorf.at.Drop;
import ahif18.htlkaindorf.at.fishes.Fish;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class Cat {
    //maybe change at some point to be dynamic
    public static float CAT_WIDTH = 75;
    public static float CAT_HEIGHT = 75;
    public static float CAT_BODY_WIDTH = 64;
    public static float CAT_BODY_HEIGHT = 64;
    public static int MAX_LEVEL = 5;

    public enum names {
        ATK,
        Range,
        Speed,
        AOE
    }

    private Rectangle range;
    private Rectangle body;

    private long currentInterval = 0;
    private int damageLevel = 1;
    private int rangeLevel = 1;
    private int attackIntervalLevel = 1;

    public Cat(float x, float y) {
        this.range = new Rectangle(x - getRangeWidth()/2,y - getRangeHeight()/2, getRangeWidth(), getRangeHeight());
        this.body = new Rectangle(x- CAT_BODY_WIDTH/2,y - CAT_BODY_HEIGHT/2,CAT_BODY_WIDTH,CAT_BODY_HEIGHT);
    }

    //returns the ids of the affected fish
    public abstract Array<Integer> attack(Array<Fish> allFish);

    public void setBodyPosition(){
        body.setX(body.getX() - CAT_BODY_WIDTH/2);
        body.setY(body.getY() - CAT_BODY_HEIGHT/2);
    }

    public void updateRange(){
        range = new Rectangle(body.x - getRangeWidth()/2 + CAT_BODY_WIDTH/2, body.y - getRangeHeight()/2 + CAT_BODY_HEIGHT/2, getRangeWidth(), getRangeHeight());
    }

    public abstract float getBaseDamage();

    public float getDamage(){
        return getBaseDamage() + (getBaseDamage() * getDamageMultiplier());
    }

    public abstract float getBaseAttackInterval();

    public float getAttackInterval(){
        return  (getBaseAttackInterval() - (getBaseAttackInterval() * getAttackIntervalMultiplier())) / Drop.GAME_SPEED;
    }

    public abstract float getBaseRangeWidth();

    public float getRangeWidth(){
        return  getBaseRangeWidth() + (getBaseRangeWidth() * getRangeMultiplier());
    }

    public abstract float getBaseRangeHeight();

    public float getRangeHeight(){
        return  getBaseRangeHeight() + (getBaseRangeHeight() * getRangeMultiplier());
    }

    public abstract int getCost();

    public abstract int getID();

    public abstract float getDamageMultiplierHelp();

    public abstract float getAttackIntervalMultiplierHelp();

    public abstract float getRangeMultiplierHelp();

    public float getDamageMultiplier() {
        return getDamageMultiplierHelp() * (damageLevel -1);
    }

    public float getAttackIntervalMultiplier(){
        return getAttackIntervalMultiplierHelp() * (attackIntervalLevel - 1);
    }

    public float getRangeMultiplier(){
        return getRangeMultiplierHelp() * (rangeLevel -1);
    }

    public abstract float getBaseRangeUpgradeCost();

    public abstract float getBaseDamageUpgradeCost();

    public abstract float getBaseAttackIntervalUpgradeCost();

    public float getRangeUpgradeCost(){
        return getBaseRangeUpgradeCost() * getRangeLevel();
    }

    public float getDamageUpgradeCost(){
        return getBaseDamageUpgradeCost() * getDamageLevel();
    }

    public float getAttackIntervalUpgradeCost(){
        return getBaseAttackIntervalUpgradeCost() * getAttackIntervalLevel();
    }

    public abstract void upgradeOne();
    public abstract void upgradeTwo();

    public abstract int getOne();
    public abstract int getTwo();

    public abstract float getOneCost();
    public abstract float getTwoCost();

    public abstract String getOneName();
    public abstract String getTwoName();
}