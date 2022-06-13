package ahif18.htlkaindorf.at.fishes;

/**
 * This class represents a VomitFish
 *
 * @author Clark | Luka
 * @version 1.0
 * Last modified: 23.05.2022
 */
public class VomitFish extends Fish{
    @Override
    public float getBaseHealth() {
        return 250;
    }

    @Override
    public int getGoldDrop() {
        return 5;
    }

    @Override
    public int getID() {
        return 2;
    }

    @Override
    public int getDamage() {
        return 25;
    }

    @Override
    public float getSpeed() {
        return 87.5f;
    }

    @Override
    public float getFishWidth() {
        return 36;
    }

    @Override
    public float getFishHeight() {
        return 36;
    }
}
