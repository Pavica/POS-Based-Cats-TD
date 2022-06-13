package ahif18.htlkaindorf.at.cats;

/**
 * This class represents a BrownCat
 *
 * @author Clark | Luka
 * @version 1.0
 * Last modified: 16.05.2022
 */

public class BrownCat extends AoeCat{
    //upgrade speed and aoe

    /**
     * Specific constructor that is used identify the range and body hitbox of each cat created.
     * @param x : used to identify the width of the range and body hitbox
     * @param y : used to identify the height of the range and body hitbox
     */
    public BrownCat(float x, float y) {
        super(x,y);
    }

    @Override
    public float getBaseDamage() {
        return 500;
    }

    @Override
    public float getBaseAttackInterval() {
        return 2500;
    }

    @Override
    public int getCost() {
        return 300;
    }

    @Override
    public int getID() {
        return 2;
    }

    @Override
    public float getBaseRangeWidth() {
        return 100;
    }

    @Override
    public float getBaseRangeHeight() {
        return 100;
    }

    @Override
    public int getBaseAoeAmount() {
        return 5;
    }

    @Override
    public int getAoeAmountMultiplierHelp() {
        return 2;
    }

    @Override
    public float getDamageMultiplierHelp() {
        return 0.1f;
    }

    @Override
    public float getAttackIntervalMultiplierHelp() {
        return 0.15f;
    }

    @Override
    public float getRangeMultiplierHelp() {
        return 0.1f;
    }

    @Override
    public float getBaseRangeUpgradeCost() {
        return 100;
    }

    @Override
    public float getBaseDamageUpgradeCost() {
        return 100;
    }

    @Override
    public float getBaseAttackIntervalUpgradeCost() {
        return 100;
    }

    @Override
    public float getBaseAoeAmountUpgradeCost() {
        return 100;
    }

    @Override
    public void upgradeOne() {
        setAttackIntervalLevel(getAttackIntervalLevel()+1);
    }

    @Override
    public void upgradeTwo() {
        setAoeAmountLevel(getAoeAmountLevel()+1);
    }

    @Override
    public int getOne() {
        return getAttackIntervalLevel();
    }

    @Override
    public int getTwo() {
        return getAoeAmountLevel();
    }

    @Override
    public float getOneCost() {
        return getAttackIntervalUpgradeCost();
    }

    @Override
    public float getTwoCost() {
        return getAoeAmountUpgradeCost();
    }

    @Override
    public String getOneName() {
        return names.Speed.name();
    }

    @Override
    public String getTwoName() {
        return names.AOE.name();
    }

}
