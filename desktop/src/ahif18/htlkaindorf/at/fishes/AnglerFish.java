package ahif18.htlkaindorf.at.fishes;

public class AnglerFish extends Fish{
    public AnglerFish(float x, float y) {
        super(x, y);
    }

    @Override
    public int getBaseHealth() {
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
