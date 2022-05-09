package ahif18.htlkaindorf.at.cats;

import com.badlogic.gdx.math.Rectangle;

public class BaseCat extends Cat{

    public static int COST = 100;
    public BaseCat(Rectangle range, Rectangle body) {
        super(range, body, 100, 1000, 0, 1);
    }
}
