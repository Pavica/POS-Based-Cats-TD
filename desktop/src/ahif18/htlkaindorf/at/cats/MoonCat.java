package ahif18.htlkaindorf.at.cats;

public class MoonCat extends AoeCat{
    //upgrade aoe and range
    public MoonCat(float x, float y) {
        super(x, y);
    }

    @Override
    public int getDamage() {
        return 100;
    }

    @Override
    public float getAttackInterval() {
        return 1000;
    }

    @Override
    public int getCost() {
        return 250;
    }

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public int getRangeWidth() {
        return 125;
    }

    @Override
    public int getRangeHeight() {
        return 125;
    }

    @Override
    public int getAoeAmount() {
        return 3;
    }


}
