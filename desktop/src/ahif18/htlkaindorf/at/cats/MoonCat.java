package ahif18.htlkaindorf.at.cats;

/**
 * This class represents a MoonCat
 *
 * @author Clark | Luka
 * @version 1.0
 * Last modified: 16.05.2022
 */

public class MoonCat extends AoeCat{
    //upgrade aoe and range

    /**
     * Specific constructor that is used identify the range and body hitbox of each cat created.
     * @param x : used to identify the width of the range and body hitbox
     * @param y : used to identify the height of the range and body hitbox
     */
    public MoonCat(float x, float y) {
        super(x, y);
    }

    @Override
    public int getDamage() {
        return 100;
    }

    @Override
    public float getAttackInterval() {
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
    public int getRangeWidth() {
        return 125;
    }

    @Override
    public int getRangeHeight() {
        return 125;
    }

    @Override
    public int getAoeAmount() {
        return 3;
    }


}
