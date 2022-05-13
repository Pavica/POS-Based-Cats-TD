package ahif18.htlkaindorf.at.cats;

public class BrownCat extends AoeCat{
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
    public int getTextureID() {
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
