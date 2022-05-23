package ahif18.htlkaindorf.at.fishes;

public class ClownFish extends Fish{

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
