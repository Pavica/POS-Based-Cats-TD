package ahif18.htlkaindorf.at.cats;

import ahif18.htlkaindorf.at.fishes.Fish;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * This class represents a SingleTargetCat
 *
 * @author Clark | Luka
 * @version 1.0
 * Last modified: 16.05.2022
 */

public abstract class SingleTargetCat extends Cat {
    public SingleTargetCat(float x, float y) {
        super(x, y);
    }

    /**
     * method used to code how the attacks work for all single target cats, meaning only attacking one fish at a time
     *
     * @param allFish : Array of all fish that currently exist in the game
     *
     * @return returns the fish that just has been hit, otherwise returning null if no fish has been hit.
     */
    @Override
    public Array<Integer> attack(Array<Fish> allFish) {
        if (TimeUtils.timeSinceMillis(getCurrentInterval()) >= getAttackInterval()) {
            Array<Integer> hitFish = new Array<>();
            for (int i = 0; i < allFish.size; i++) {
                if (getRange().overlaps(allFish.get(i).getBody())) {
                    allFish.get(i).setHealth(allFish.get(i).getHealth() - getDamage());
                    setCurrentInterval(TimeUtils.millis());
                    hitFish.add(i);
                    return hitFish;
                }
            }
        }
        return null;
    }
}
