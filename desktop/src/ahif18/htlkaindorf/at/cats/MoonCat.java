package ahif18.htlkaindorf.at.cats;

public class MoonCat extends AoeCat{
    //upgrade aoe and range
    public MoonCat(float x, float y) {
        super(x, y);
    }

    @Override
    public float getBaseDamage() {
        return 100;
    }

    @Override
    public float getBaseAttackInterval() {
        return 1000;
    }

    @Override
    public int getCost() {
        return 250;
    }

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public float getBaseRangeWidth() {
        return 125;
    }

    @Override
    public float getBaseRangeHeight() {
        return 125;
    }

    @Override
    public int getBaseAoeAmount() {
        return 3;
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
        return 0.1f;
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
        setAoeAmountLevel(getAoeAmountLevel()+1);
    }

    @Override
    public void upgradeTwo() {
        setRangeLevel(getRangeLevel()+1);
    }

    @Override
    public int getOne() {
        return getAoeAmountLevel();
    }

    @Override
    public int getTwo() {
        return getRangeLevel();
    }

    @Override
    public float getOneCost() {
        return getAoeAmountUpgradeCost();
    }

    @Override
    public float getTwoCost() {
        return getRangeUpgradeCost();
    }

    @Override
    public String getOneName() {
        return names.AOE.name();
    }

    @Override
    public String getTwoName() {
        return names.Range.name();
    }
}
