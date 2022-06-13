package ahif18.htlkaindorf.at.decorators;

import ahif18.htlkaindorf.at.cats.ClothedCat;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public class TurkHatDecorator extends CatDecorator{

    public TurkHatDecorator(ClothedCat wrappee) {
        super(wrappee);
    }

    @Override
    public Array<Texture> clothes() {
        Array clothes =  new Array(super.clothes());
        clothes.add(new Texture(Gdx.files.internal("decorations/turk_hat.png")));
        return clothes;
    }
}
