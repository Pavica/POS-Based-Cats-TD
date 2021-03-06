package ahif18.htlkaindorf.at.cats;

import ahif18.htlkaindorf.at.fishes.Fish;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * This class represents an Area of Effect Cat
 *
 * @author Clark | Luka
 * @version 1.0
 * Last modified: 16.05.2022
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class AoeCat extends Cat{
    private int aoeAmountLevel = 1;

    public AoeCat(float x, float y) {
        super(x, y);
    }
    /**
     * method used to code how the attacks work for all "Area of Effect" cats, meaning attacking multiple fish at a time
     *
     * @param allFish : Array of all fish that currently exist in the game
     *
     * @return returns all fish that just have been hit, otherwise returning null if no fish has been hit.
     */
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

    /** returns the base AOE amount of the specified cat */
    public abstract int getBaseAoeAmount();

    /** returns the real AOE amount of the specified cat. Including consideration for levels*/
    public int getAoeAmount(){
        return getBaseAoeAmount() + (aoeAmountLevel -1)* getAoeAmountMultiplierHelp();
    }

    /** returns the help value used to calculate the AOE amount*/
    public abstract int getAoeAmountMultiplierHelp();

    /** returns the base AOE amount upgrade cost of the specified cat.*/
    public abstract float getBaseAoeAmountUpgradeCost();

    /** returns the real AOE amount cost of the specified cat. Including consideration for levels*/
    public float getAoeAmountUpgradeCost(){
        return getBaseAoeAmountUpgradeCost() * getAoeAmountLevel();
    }
}
