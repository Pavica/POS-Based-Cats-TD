package ahif18.htlkaindorf.at.cats;

/**
 * This class represents a MooCat
 *
 * @author Clark | Luka
 * @version 1.0
 * Last modified: 16.05.2022
 */

public class MooCat extends SingleTargetCat{
    //upgrade range and speed

    /**
     * Specific constructor that is used identify the range and body hitbox of each cat created.
     * @param x : used to identify the width of the range and body hitbox
     * @param y : used to identify the height of the range and body hitbox
     */
    public MooCat(float x, float y) {
        super(x, y);
    }

    @Override
    public int getDamage() {
        return 200;
    }

    @Override
    public float getAttackInterval() {
        return 500;
    }

    @Override
    public int getCost() {
        return 200;
    }

    @Override
    public int getID() {
        return 3;
    }

    @Override
    public int getRangeWidth() {
        return 200;
    }

    @Override
    public int getRangeHeight() {
        return 200;
    }
}
