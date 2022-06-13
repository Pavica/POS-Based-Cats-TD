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
    public int getDamage() {
        return 100;
    }

    @Override
    public float getAttackInterval() {
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
    public int getRangeWidth(){
        return 150;
    }

    @Override
    public int getRangeHeight(){
        return 150;
    }
}
