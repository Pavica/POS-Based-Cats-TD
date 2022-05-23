package ahif18.htlkaindorf.at.cats;

import ahif18.htlkaindorf.at.fishes.Fish;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class AoeCat extends Cat{
    private int aoeAmountLevel = 1;

    public AoeCat(float x, float y) {
        super(x, y);
    }

    @Override
    public Array<Integer> attack(Array<Fish> allFish){
        if (TimeUtils.timeSinceMillis(getCurrentInterval()) >= getAttackInterval()) {
            Array<Integer> hitFish = new Array<>();
            for (int i = 0; i < allFish.size; i++) {
                if (getRange().overlaps(allFish.get(i).getBody())) {
                    allFish.get(i).setHealth(allFish.get(i).getHealth() - getDamage());
                    setCurrentInterval(TimeUtils.millis());
                    hitFish.add(i);
                }
                if(hitFish.size == getAoeAmount()){
                    break;
                }
            }
            return  hitFish;
        }
        return null;
    }

    public abstract int getBaseAoeAmount();

    public int getAoeAmount(){
        return getBaseAoeAmount() + (aoeAmountLevel -1)* getAoeAmountMultiplierHelp();
    }

    public abstract int getAoeAmountMultiplierHelp();

    public abstract float getBaseAoeAmountUpgradeCost();

    public float getAoeAmountUpgradeCost(){
        return getBaseAoeAmountUpgradeCost() * getAoeAmountLevel();
    }
}
