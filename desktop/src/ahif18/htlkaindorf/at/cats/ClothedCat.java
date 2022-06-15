package ahif18.htlkaindorf.at.cats;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

/**
 * This interface represents a ClothedCat
 *
 * @author Clark | Luka
 * @version 1.0
 * Last modified: 13.06.2022
 */
public interface ClothedCat {
    /** method used for adding clothes to a cat */
    Array<Texture> clothes();
}
