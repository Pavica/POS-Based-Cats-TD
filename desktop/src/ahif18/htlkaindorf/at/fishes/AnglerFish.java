package ahif18.htlkaindorf.at.fishes;

/**
 * This class represents a AnglerFish
 *
 * @author Clark | Luka
 * @version 1.0
 * Last modified: 23.05.2022
 */
public class AnglerFish extends Fish{
    @Override
    public float getBaseHealth() {
        return 500;
    }

    @Override
    public int getGoldDrop() {
        return 10;
    }

    @Override
    public int getID() {
        return 4;
    }

    @Override
    public int getDamage() {
        return 50;
    }

    @Override
    public float getSpeed() {
        return 75;
    }

    @Override
    public float getFishWidth() {
        return 52;
    }

    @Override
    public float getFishHeight() {
        return 52;
    }
}
