package ahif18.htlkaindorf.at.cats;

public class BaseCat extends SingleTargetCat{

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
    public int getTextureID() {
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
