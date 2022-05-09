package ahif18.htlkaindorf.at.cats;

import com.badlogic.gdx.math.Rectangle;

public class BrownCat extends Cat{

    public static int COST = 300;
    public BrownCat(Rectangle range, Rectangle body) {
        super(range, body, 400, 3000, 2, 5);
    }
}
