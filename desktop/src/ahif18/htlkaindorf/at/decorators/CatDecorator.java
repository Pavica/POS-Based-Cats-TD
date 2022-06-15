package ahif18.htlkaindorf.at.decorators;

import ahif18.htlkaindorf.at.cats.ClothedCat;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
/**
 * This class represents a CatDecorator
 *
 * @author Clark | Luka
 * @version 1.0
 * Last modified: 13.06.2022
 */
public class CatDecorator implements ClothedCat {
    /** */
    private ClothedCat wrappee;

    /**
     * Specific constructor that is used to initiate the Decorator pattern.
     * @param wrappee : describes a ClothedCat
     */
    public CatDecorator(ClothedCat wrappee){
        this.wrappee = wrappee;
    }

    @Override
    public Array<Texture> clothes() {
        return wrappee.clothes();
    }
}
