package ahif18.htlkaindorf.at.cats;

import ahif18.htlkaindorf.at.Drop;
import ahif18.htlkaindorf.at.fishes.Fish;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents a Cat
 *
 * @author Clark | Luka
 * @version 1.0
 * Last modified: 16.05.2022
 */

@Data
@NoArgsConstructor
public abstract class Cat implements ClothedCat{
    //maybe change at some point to be dynamic

    /** width of every cat */
    public static float CAT_WIDTH = 75;

    /** height of every cat */
    public static float CAT_HEIGHT = 75;

    /** width of hitbox of each cat */
    public static float CAT_BODY_WIDTH = 64;

    /** height of hitbox of each cat */
    public static float CAT_BODY_HEIGHT = 64;

    /** describes the maximum level an attribute can have */
    public static int MAX_LEVEL = 5;

    /** enum for different cat attributes */
    public enum names {
        ATK,
        Range,
        Speed,
        AOE
    }

    /** used for range radius (how far a cat can attack) */
    private Rectangle range;

    /** used for the hitbox of the body of a cat */
    private Rectangle body;

    private Array<Texture> decorations = new Array<>();

    /** cooldown of attacks */
    private long currentInterval = 0;

    /** describes the level of the damage attribute*/
    private int damageLevel = 1;
    /** describes the level of the range attribute*/
    private int rangeLevel = 1;
    /** describes the level of the attack interval attribute*/
    private int attackIntervalLevel = 1;

    /**
     * Specific constructor that is used identify the range and body hitbox of each cat created.
     * @param x : used to identify the width of the range and body hitbox
     * @param y : used to identify the height of the range and body hitbox
     */
    public Cat(float x, float y) {
        this.range = new Rectangle(x - getRangeWidth()/2,y - getRangeHeight()/2, getRangeWidth(), getRangeHeight());
        this.body = new Rectangle(x- CAT_BODY_WIDTH/2,y - CAT_BODY_HEIGHT/2,CAT_BODY_WIDTH,CAT_BODY_HEIGHT);
    }

    @Override
    public Array<Texture> clothes() {
        return new Array<>();
    }

    /**
     * abstract class to return which fish had been attacked by the cat
     *
     * @return which fish had been attacked by the cat
     */
    public abstract Array<Integer> attack(Array<Fish> allFish);

    /**
     * setter method used to set the exact body hitbox
     */
    public void setBodyPosition(){
        body.setX(body.getX() - CAT_BODY_WIDTH/2);
        body.setY(body.getY() - CAT_BODY_HEIGHT/2);
    }

    /** updates the range after a cats range attribute has been modified */
    public void updateRange(){
        range = new Rectangle(body.x - getRangeWidth()/2 + CAT_BODY_WIDTH/2, body.y - getRangeHeight()/2 + CAT_BODY_HEIGHT/2, getRangeWidth(), getRangeHeight());
    }

    /** returns the baseDamage of the specified cat */
    public abstract float getBaseDamage();

    /** returns the real damage of the specified cat. Including consideration for levels */
    public float getDamage(){
        return getBaseDamage() + (getBaseDamage() * getDamageMultiplier());
    }

    /** returns the base attack interval in which a specified cat attacks*/
    public abstract float getBaseAttackInterval();

    /** returns the real attack interval in which a specified cat attacks. Including consideration for levels*/
    public float getAttackInterval(){
        return  (getBaseAttackInterval() - (getBaseAttackInterval() * getAttackIntervalMultiplier())) / Drop.GAME_SPEED;
    }

    /** returns the base range width of a specified cat*/
    public abstract float getBaseRangeWidth();

    /** returns the real range width of a specified cat. Including consideration for levels*/
    public float getRangeWidth(){
        return  getBaseRangeWidth() + (getBaseRangeWidth() * getRangeMultiplier());
    }

    /** returns the base range height of a specified cat*/
    public abstract float getBaseRangeHeight();

    /** returns the real range height of a specified cat. Including consideration for levels*/
    public float getRangeHeight(){
        return  getBaseRangeHeight() + (getBaseRangeHeight() * getRangeMultiplier());
    }

    /** returns the cost of a specified Cat*/
    public abstract int getCost();

    /** returns the id of a specified Cat*/
    public abstract int getID();

    /** returns a help value that is used ot calculate the damage*/
    public abstract float getDamageMultiplierHelp();

    /** returns a help value that is used ot calculate the attack interval*/
    public abstract float getAttackIntervalMultiplierHelp();

    /** returns a help value that is used ot calculate the range*/
    public abstract float getRangeMultiplierHelp();

    /** returns the damage multiplier. Including consideration for levels*/
    public float getDamageMultiplier() {
        return getDamageMultiplierHelp() * (damageLevel -1);
    }

    /** returns the attack interval multiplier. Including consideration for levels*/
    public float getAttackIntervalMultiplier(){
        return getAttackIntervalMultiplierHelp() * (attackIntervalLevel - 1);
    }

    /** returns the range multiplier. Including consideration for levels*/
    public float getRangeMultiplier(){
        return getRangeMultiplierHelp() * (rangeLevel -1);
    }

    /** returns the base Upgrade cost of the range*/
    public abstract float getBaseRangeUpgradeCost();

    /** returns the base Upgrade cost of the damage*/
    public abstract float getBaseDamageUpgradeCost();

    /** returns the base Upgrade cost of the attack interval*/
    public abstract float getBaseAttackIntervalUpgradeCost();

    /** returns the real Upgrade cost of the Range. Including consideration for levels*/
    public float getRangeUpgradeCost(){
        return getBaseRangeUpgradeCost() * getRangeLevel();
    }

    /** returns the real Upgrade cost of the Damage. Including consideration for levels*/
    public float getDamageUpgradeCost(){
        return getBaseDamageUpgradeCost() * getDamageLevel();
    }

    /** returns the real Upgrade cost of the . Including consideration for levels*/
    public float getAttackIntervalUpgradeCost(){
        return getBaseAttackIntervalUpgradeCost() * getAttackIntervalLevel();
    }

    /** method used to upgrade the first value in the upgrade menu*/
    public abstract void upgradeOne();
    /** method used to upgrade the second value in the upgrade menu*/
    public abstract void upgradeTwo();

    /** method used to get the first value in the upgrade menu*/
    public abstract int getOne();
    /** method used to get the second value in the upgrade menu*/
    public abstract int getTwo();

    /** method used to get the cost for the first value in the upgrade menu*/
    public abstract float getOneCost();
    /** method used to get the cost for the second value in the upgrade menu*/
    public abstract float getTwoCost();

    /** method used to get the name of the first value in the upgrade menu*/
    public abstract String getOneName();
    /** method used to get the name of the second value in the upgrade menu*/
    public abstract String getTwoName();
}