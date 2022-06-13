package ahif18.htlkaindorf.at.decorators;

import ahif18.htlkaindorf.at.cats.Cat;
import ahif18.htlkaindorf.at.cats.ClothedCat;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public class CatDecorator implements ClothedCat {
    private ClothedCat wrappee;

    public CatDecorator(ClothedCat wrappee){
        this.wrappee = wrappee;
    }

    @Override
    public Array<Texture> clothes() {
        return wrappee.clothes();
    }
}
