package ahif18.htlkaindorf.at.decorators;

import ahif18.htlkaindorf.at.cats.ClothedCat;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
/**
 * This class represents the cat accessory TopHatDecorator
 *
 * @author Clark | Luka
 * @version 1.0
 * Last modified: 13.06.2022
 */
public class TopHatDecorator extends CatDecorator{
    /**
     * Specific constructor that is used to add the TopHat decoration to the sum.
     * @param wrappee : describes a ClothedCat
     */
    public TopHatDecorator(ClothedCat wrappee) {
        super(wrappee);
    }

    @Override
    public Array<Texture> clothes() {
        Array clothes =  new Array(super.clothes());
        clothes.add(new Texture(Gdx.files.internal("decorations/top_hat.png")));
        return clothes;
    }
}
