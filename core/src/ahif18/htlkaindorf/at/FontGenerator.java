package ahif18.htlkaindorf.at;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class FontGenerator {
    private static FreeTypeFontGenerator fontGenerator;

    public static BitmapFont generateFreetypeFont(int fontSize, Color color)
    {
        if(fontGenerator == null){
            fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Swansea-q3pd.ttf"));
        }

        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = fontSize;
        return fontGenerator.generateFont(parameter);
    }
}
