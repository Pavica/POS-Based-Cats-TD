package ahif18.htlkaindorf.at.fishes;

public class VomitFish extends Fish{

    public VomitFish(float x, float y) {
        super(x, y);
    }

    @Override
    public int getBaseHealth() {
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
