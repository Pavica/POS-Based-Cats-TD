package ahif18.htlkaindorf.at.cats;

/**
 * This class represents the BaseCat
 *
 * @author Clark | Luka
 * @version 1.0
 * Last modified: 16.05.2022
 */

public class BaseCat extends SingleTargetCat{
    //upgrade damage and range

    /**
     * Specific constructor used to set the body with of each cat
     * @param x : used to identify the width of the body hitbox
     * @param y : used to identify the height of the body hitbox
     */
    public BaseCat(float x, float y) {
        super(x,y);
    }

    @Override
    public float getBaseDamage() {
        return 100;
    }

    @Override
    public float getBaseAttackInterval() {
        return 500;
    }

    @Override
    public int getCost() {
        return 100;
    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public float getBaseRangeWidth(){
        return 150;
    }

    @Override
    public float getBaseRangeHeight(){
        return 150;
    }

    @Override
    public float getDamageMultiplierHelp() {
        return 1f;
    }

    @Override
    public float getAttackIntervalMultiplierHelp() {
        return 0.1f;
    }

    @Override
    public float getRangeMultiplierHelp() {
        return 0.2f;
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
    public void upgradeOne() {
        setDamageLevel(getDamageLevel()+1);
    }

    @Override
    public void upgradeTwo() {
        setRangeLevel(getRangeLevel()+1);
    }

    @Override
    public int getOne() {
        return getDamageLevel();
    }

    @Override
    public int getTwo() {
        return getRangeLevel();
    }

    @Override
    public float getOneCost() {
        return getDamageUpgradeCost();
    }

    @Override
    public float getTwoCost() {
        return getRangeUpgradeCost();
    }

    @Override
    public String getOneName() {
        return names.ATK.name();
    }

    @Override
    public String getTwoName() {
        return names.Range.name();
    }
}
