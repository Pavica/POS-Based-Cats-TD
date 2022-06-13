package ahif18.htlkaindorf.at.fishes;

/**
 * This class represents a ClownFish
 *
 * @author Clark | Luka
 * @version 1.0
 * Last modified: 23.05.2022
 */
public class ClownFish extends Fish{

    /**
     * Specific constructor that is used identify the range and body hitbox of the clownfish.
     * @param x : used to identify the width of the range and body hitbox
     * @param y : used to identify the height of the range and body hitbox
     */
    public ClownFish(float x, float y) {
        super(x, y);
    }

    @Override
    public float getBaseHealth() {
        return 100;
    }

    @Override
    public int getGoldDrop() {
        return 2;
    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public int getDamage() {
        return 5;
    }

    @Override
    public float getSpeed() {
        return 100;
    }

    @Override
    public float getFishWidth() {
        return 32;
    }

    @Override
    public float getFishHeight() {
        return 32;
    }


}
