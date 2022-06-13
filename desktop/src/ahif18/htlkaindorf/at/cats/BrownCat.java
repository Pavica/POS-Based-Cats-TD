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
    public int getDamage() {
        return 500;
    }

    @Override
    public float getAttackInterval() {
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
    public int getRangeWidth() {
        return 100;
    }

    @Override
    public int getRangeHeight() {
        return 100;
    }

    @Override
    public int getAoeAmount() {
        return 5;
    }
}
