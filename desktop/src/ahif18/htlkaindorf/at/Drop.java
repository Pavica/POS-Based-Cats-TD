package ahif18.htlkaindorf.at;

import java.util.Comparator;
import java.util.Iterator;
import ahif18.htlkaindorf.at.cats.*;
import ahif18.htlkaindorf.at.fishes.AnglerFish;
import ahif18.htlkaindorf.at.fishes.ClownFish;
import ahif18.htlkaindorf.at.fishes.Fish;
import ahif18.htlkaindorf.at.fishes.VomitFish;
import ahif18.htlkaindorf.at.libs.GifDecoder;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * This class
 *
 * @author Clark | Luka
 * @version 1.0
 * Last modified: 23.05.2022
 */
public class Drop extends ApplicationAdapter {

    /** used for the max health of a player */
    private static final int MAX_HEALTH = 100;

    /** used to identify at which speed the game is running */
    private static final int GAME_SPEED = 1;


    /** amount of gold that is available at the beginning of the game */
    private int gold = 1000;

    /** speed of cats that they are spawned in (in milliseconds) */
    private int speed = 1000000000;

    /** used for the max health of a player */
    private int health = MAX_HEALTH;

    /** IDK */
    private int countFish = 0;


    /** texture of the clownfish */
    private Texture clownFish;

    /** texture of the clownFishRotated */
    private Texture clownFishRotated;

    /** texture of the vomitFish */
    private Texture vomitFish;

    /** texture of the vomitFishRotated */
    private Texture vomitFishRotated;

    /** texture of the anglerFish */
    private Texture anglerFish;

    /** texture of the anglerFishRotated */
    private Texture anglerFishRotated;


    /** texture of the background image*/
    private Texture backgroundImage;

    /** texture of the bridges in the background*/
    private Texture backgroundImageBridges;


    /** texture of the base cat */
    private Texture baseCatImage;

    /** texture of the moon cat */
    private Texture moonCatImage;

    /** texture of the brown cat */
    private Texture brownCatImage;

    /** texture of the moo cat */
    private Texture mooCatImage;


    /** texture of the cat holder image in the top right corner */
    private Texture catHolderImage;

    /** IDK */
    private Texture hit;

    /** texture of the x that is shown when something cannot be placed */
    private Texture x;


    /** Sprite of the background */
    private Sprite backgroundSprite;

    /** Sprite of the bridges in the background */
    private Sprite backgroundBridgesSprite;

    /** Sprite of the cat holder in the top right corner */
    private Sprite catHolderSprite;


    /** IDK */
    private SpriteBatch batch;

    /** IDK */
    private ShapeRenderer shapeRenderer;


    /** Used for the font of the text that is shown */
    private BitmapFont font;


    /** IDK */
    private OrthographicCamera camera;


    /** IDK */
    private Array<Float> timeElapsed;

    /** Array of cats that exist in the game */
    private Array<Cat> catTypes;

    /** Used for the idle animation of the moon cat */
    Animation<TextureRegion> moonCatAnimation;

    /** Used for the idle animation of the base cat */
    Animation<TextureRegion> baseCatAnimation;

    /** Used for the idle animation of the brown cat */
    Animation<TextureRegion> brownCatAnimation;

    /** Used for the idle animation of the moo cat */
    Animation<TextureRegion> mooCatAnimation;


    /** boolean used to see if a cat has been clicked */
    private boolean catIsClicked = false;

    /** IDK */
    private Rectangle helpCatRectangleRange;

    /** IDK */
    private Rectangle helpCatRectangleBody;


    /** Used for the cat holder in the top right corner */
    private Rectangle catHolder;

    /** Array of animations of cat animations */
    private Array<Animation> catAnimations;

    /** Array of textures of fish */
    private Array<Texture> fishTextures;

    /** Array of all cats in the game */
    private Array<Cat> allCats;

    /** Array of all fish in the game */
    private Array<Fish> allFish;

    /** IDK */
    private long lastDropTime;


    /** used for the max health of a player */
    private boolean isMoving = false;
    private Vector3 touchPosIsMoving;


    /** used for the max health of a player */
    //0, 2, 3, 1
    private final Vector3[] catHolderPositions = {
            new Vector3(677, 377,0),
            new Vector3(747,297,0),
            new Vector3(747,377,0),
            new Vector3(677, 297,0),
    };


    /** used for the max health of a player */
    private final Rectangle[] points = {
        new Rectangle(515,470,8,8),
        new Rectangle(515, 360, 8,8),
        new Rectangle(70,360, 8,8),
        new Rectangle(70, 280, 8,8),
        new Rectangle(280,280, 8,8),
        new Rectangle(370,220, 8,8),
        new Rectangle(635, 220, 8,8),
        new Rectangle(650, 160, 8,8),
        new Rectangle(600, 115, 8,8),
        new Rectangle(130, 115, 8,8),
        new Rectangle(100, 80, 8,8),
        new Rectangle(100, -10, 8,8)
    };


    /** used for the max health of a player */
    //draw these so you can check if they align with the river and then use it as a hitbox for not allowing it to place
    //BEFORE THAT CHECK IF YOU COULD DO IT WITH THE FIRST ONE
    private final Rectangle[] riverHitbox = {
            new Rectangle(points[0].x -10,points[0].y - 10,50,50),
            new Rectangle(points[1].x - 10,points[1].y - 10, 50,140),
            new Rectangle(points[2].x - 10,points[2].y - 10, 450,60),
            new Rectangle(points[3].x - 10,points[3].y - 10, 260,60),
            new Rectangle(points[4].x - 10,points[4].y - 60, 100,60),
            new Rectangle(points[5].x - 10,points[5].y - 10, 300,60),
            new Rectangle(points[6].x - 10,points[6].y - 10, 60,60),
            new Rectangle(points[8].x - 10,points[8].y - 10, 100,60),
            new Rectangle(points[9].x - 10,points[9].y - 10, 500,60),
            new Rectangle(points[10].x - 10,points[10].y - 10, 70,60),
            new Rectangle(points[11].x - 10,points[11].y - 10, 60,100)
    };

    private float myDeltaTime(){
        return Gdx.graphics.getDeltaTime() * GAME_SPEED;
    }

    @Override
    public void create() {
        // load the images for the droplet and the moonCat, 32x32 pixels each
        backgroundImage = new Texture(Gdx.files.internal("backgroundElements/background.png"));
        backgroundImageBridges = new Texture(Gdx.files.internal("backgroundElements/background-2.png"));

        clownFish = new Texture(Gdx.files.internal("fishImages/fish.png"));
        clownFishRotated = new Texture(Gdx.files.internal("fishImages/fish2.png"));
        vomitFish = new Texture(Gdx.files.internal("fishImages/vomitFish.png"));
        vomitFishRotated = new Texture(Gdx.files.internal("fishImages/vomitFish2.png"));
        anglerFish = new Texture(Gdx.files.internal("fishImages/anglerFish.png"));
        anglerFishRotated = new Texture(Gdx.files.internal("fishImages/anglerFish2.png"));

        moonCatImage = new Texture(Gdx.files.internal("catImages/MoonCat.png"));
        baseCatImage = new Texture(Gdx.files.internal("catImages/BaseCat.png"));
        mooCatImage = new Texture(Gdx.files.internal("catImages/MooCat.png"));
        brownCatImage = new Texture(Gdx.files.internal("catImages/BrownCat.png"));

        catHolderImage = new Texture(Gdx.files.internal("backgroundElements/catHolder.png"));
        hit = new Texture(Gdx.files.internal("hit.png"));
        x = new Texture(Gdx.files.internal("x.png"));

        fishTextures = new Array<>();
        fishTextures.add(clownFish);
        fishTextures.add(clownFishRotated);
        fishTextures.add(vomitFish);
        fishTextures.add(vomitFishRotated);
        fishTextures.add(anglerFish);
        fishTextures.add(anglerFishRotated);

        backgroundSprite =new Sprite(backgroundImage);
        backgroundBridgesSprite = new Sprite(backgroundImageBridges);
        catHolderSprite = new Sprite(catHolderImage);

        catHolder = new Rectangle(630,240,34*5, 48*5);

        font = FontGenerator.generateFreetypeFont(16);

        // load the drop sound effect and the rain background "music"
        //dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        //rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));

        // start the playback of the background music immediately
        //rainMusic.setLooping(true);
        //rainMusic.play();

        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();

        //USE moonCat CODE FOR MOVING THE CATS WITH MOUSE

        // create a Rectangle to logically represent the moonCa
        catTypes = new Array<>();
        catTypes.add(new BaseCat(catHolderPositions[0].x, catHolderPositions[0].y));
        catTypes.add(new MoonCat(catHolderPositions[1].x, catHolderPositions[1].y));
        catTypes.add(new BrownCat(catHolderPositions[2].x, catHolderPositions[2].y));
        catTypes.add(new MooCat(catHolderPositions[3].x, catHolderPositions[3].y));

        //create Cats animations
        baseCatAnimation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("catImages/BaseCat.gif").read());
        moonCatAnimation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("catImages/MoonCat.gif").read());
        brownCatAnimation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("catImages/BrownCat.gif").read());
        mooCatAnimation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("catImages/MooCat.gif").read());

        catAnimations = new Array<>();
        timeElapsed = new Array<>();
        catAnimations.add(baseCatAnimation);
        catAnimations.add(moonCatAnimation);
        catAnimations.add(brownCatAnimation);
        catAnimations.add(mooCatAnimation);

        // create the fishs array and spawn the first fish
        allFish = new Array<>();
        spawnFish();

        allCats = new Array<>();
    }

    private void showHitboxAll(){
        //RENDER THE HITBOX OF EVERYTHING
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        for(int i=0; i<= riverHitbox.length -1; i++){
            shapeRenderer.rect(riverHitbox[i].x, riverHitbox[i].y, riverHitbox[i].getWidth(), riverHitbox[i].getHeight(), com.badlogic.gdx.graphics.Color.BLACK, com.badlogic.gdx.graphics.Color.BLACK, com.badlogic.gdx.graphics.Color.BLACK, com.badlogic.gdx.graphics.Color.BLACK);
        }

        for(int i=0; i< catTypes.size; i++){
            shapeRenderer.rect(catTypes.get(i).getBody().x, catTypes.get(i).getBody().y, catTypes.get(i).getBody().width, catTypes.get(i).getBody().height, com.badlogic.gdx.graphics.Color.BLACK, com.badlogic.gdx.graphics.Color.BLACK, com.badlogic.gdx.graphics.Color.BLACK, com.badlogic.gdx.graphics.Color.BLACK);
        }

        for(int i=0; i< allFish.size; i++){
            shapeRenderer.rect(allFish.get(i).getBody().x, allFish.get(i).getBody().y, allFish.get(i).getBody().width, allFish.get(i).getBody().height, com.badlogic.gdx.graphics.Color.BLACK, com.badlogic.gdx.graphics.Color.BLACK, com.badlogic.gdx.graphics.Color.BLACK, com.badlogic.gdx.graphics.Color.BLACK);
        }


        for(int i= 0; i< allCats.size; i++){
            shapeRenderer.rect(allCats.get(i).getRange().x, allCats.get(i).getRange().y, allCats.get(i).getRange().getWidth(), allCats.get(i).getRange().getHeight(), com.badlogic.gdx.graphics.Color.BLACK, com.badlogic.gdx.graphics.Color.BLACK, com.badlogic.gdx.graphics.Color.BLACK, com.badlogic.gdx.graphics.Color.BLACK);
            shapeRenderer.rect(allCats.get(i).getBody().x, allCats.get(i).getBody().y, allCats.get(i).getBody().getWidth(), allCats.get(i).getBody().getHeight(), com.badlogic.gdx.graphics.Color.BLACK, com.badlogic.gdx.graphics.Color.BLACK, com.badlogic.gdx.graphics.Color.BLACK, com.badlogic.gdx.graphics.Color.BLACK);
        }

        shapeRenderer.rect(catHolder.x, catHolder.y, catHolder.width, catHolder.height);
        shapeRenderer.end();
    }

    private void fishMove(Fish fish){
        if (fish.getBody().overlaps(points[fish.getCurrentPoint()]) && fish.getCurrentPoint() != points.length - 1) {
            fish.setCurrentPoint(fish.getCurrentPoint() + 1);
        }

        if (points[fish.getCurrentPoint()].x < fish.getBody().x) {
            fish.getBody().x -= fish.getSpeed() * myDeltaTime();
            fish.setDirectionLeft(true);
        }
        if (points[fish.getCurrentPoint()].x > fish.getBody().x) {
            fish.getBody().x += fish.getSpeed() * myDeltaTime();
            fish.setDirectionLeft(false);
        }

        if (points[fish.getCurrentPoint()].y < fish.getBody().y) {
            fish.getBody().y -= fish.getSpeed() * myDeltaTime();
        }
        if (points[fish.getCurrentPoint()].y > fish.getBody().y) {
            fish.getBody().y += fish.getSpeed() * myDeltaTime();
        }
    }

    //idea for proper fish spawning make two different values the amount of fish COUNT and the type of fish TYPE
    //intertwine them in some kind of array and make a method that reads it and spawns the fish accordingly
    private void spawnFish() {
        Fish fish;
        if(countFish % 15 == 0){
            fish = new AnglerFish(points[0].x,points[0].y);
        }else if(countFish % 5 == 0){
            fish = new VomitFish(points[0].x,points[0].y);
        }else{
            fish = new ClownFish(points[0].x,points[0].y);
        }
        allFish.add(fish);
        lastDropTime = TimeUtils.nanoTime();
        countFish++;
    }

    //add x, y coordinates to spawn the cat where you drag it
    private void spawnCat(float screenX, float screenY, int catID){
        //for some reason the cat is a bit further to the right than it should be, you cna compensate for this by just making
        // the values uneven but i think that will break some automation down the line, but it probably wont be an issue considering
        // all the cats are the same so you would just have to decrease or increase it by a certain amount

        switch (catID) {
            case 0:
                Cat baseCat = new BaseCat(screenX, screenY);
                allCats.add(baseCat);
                break;
            case 1:
                Cat moonCat = new MoonCat(screenX, screenY);
                allCats.add(moonCat);
                break;
            case 2:
                Cat brownCat = new BrownCat(screenX, screenY);
                allCats.add(brownCat);
                break;
            case 3:
                Cat mooCat = new MooCat(screenX, screenY);
                allCats.add(mooCat);
                break;
            default:
                System.out.println("No cats found");
        }
        timeElapsed.add(0f);
    }

    private int checkWhichCat(Rectangle rectangle){
        for (Cat catType: catTypes){
            if(catType.getBody().overlaps(rectangle)){
                return catType.getID();
            }
        }
        return -1;
    }

    //pls change this its annoying tbh even if you have to place a cat that you cant

    private Cat findCat(Vector3 touchPos){
        camera.unproject(touchPos);
        Rectangle touchPosRect = new Rectangle(touchPos.x, touchPos.y, 0,0);
        for (Cat cat: allCats){
            if(cat.getBody().overlaps(touchPosRect)){
                return cat;
            }
        }
        return null;
    }

    private boolean checkIfOverlaps(Rectangle catPosition){
        for (Rectangle riverPoint : riverHitbox) {
            if (catPosition.overlaps(riverPoint)) {
                return true;
            }
        }
        for (Cat cat : allCats) {
            if (catPosition.overlaps(cat.getBody())) {
                return true;
            }
        }
        if (catPosition.overlaps(catHolder)) {
                return true;
        }
        return false;
    }

    @Override
    public void render() {
        // clear the screen with a dark blue color. The
        // arguments to clear are the red, green
        // blue and alpha component in the range [0,1]
        // of the color to be used to clear the screen.
        ScreenUtils.clear(0, 0, 0.2f, 1);

        // tell the camera to update its matrices.
        camera.update();

        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        batch.setProjectionMatrix(camera.combined);

        // begin a new batch and draw the moonCat and
        // all drops
        batch.begin();
        backgroundSprite.draw(batch);

        //Background
        batch.draw(backgroundImage, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        //Fish
        for (Fish fish : allFish) {
            if (fish.isDirectionLeft()) {
                batch.draw(fishTextures.get(fish.getID()), fish.getBody().x, fish.getBody().y, fish.getFishWidth(), fish.getFishHeight());

            } else {
                batch.draw(fishTextures.get(fish.getID() + 1), fish.getBody().x, fish.getBody().y, fish.getFishWidth(), fish.getFishHeight());
            }
        }

        //Bridges
        backgroundBridgesSprite.draw(batch);
        batch.draw(backgroundBridgesSprite, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        //Cats
        for (int i = 0; i < allCats.size; i++) {
            timeElapsed.set(i, timeElapsed.get(i) + myDeltaTime());
            batch.draw((TextureRegion) catAnimations.get(allCats.get(i).getID()).getKeyFrame(timeElapsed.get(i)), allCats.get(i).getBody().x, allCats.get(i).getBody().y, Cat.CAT_WIDTH, Cat.CAT_HEIGHT);
        }

        //Cat Holder
        catHolderSprite.draw(batch);

        //Gold
        GlyphLayout glyphLayout = new GlyphLayout();
        String goldValue = gold + "";
        glyphLayout.setText(font, goldValue);
        float m = glyphLayout.width;
        font.draw(batch, goldValue, 755 - m, 460);

        //World health
        font.draw(batch, "HP: " + health, 20, 460);

        //Gold cost for cats
        for(int i=0; i< catHolderPositions.length; i++){
            font.setColor(gold < catTypes.get(i).getCost() ? Color.RED : Color.WHITE);
            font.draw(batch, catTypes.get(i).getCost() + "", catHolderPositions[i].x - 10, catHolderPositions[i].y -28);
        }
        font.setColor(Color.WHITE);

        //Cats inside holder (dummys)
        for(int i= 0; i<catTypes.size; i++){
            batch.draw((TextureRegion) catAnimations.get(catTypes.get(i).getID()).getKeyFrame(0), catTypes.get(i).getBody().x, catTypes.get(i).getBody().y, Cat.CAT_WIDTH, Cat.CAT_HEIGHT);
        }

        //draw x if cat overlaps an area where you can not place it
        if(isMoving){
            Rectangle rect = new Rectangle(touchPosIsMoving.x, touchPosIsMoving.y, 0, 0);
            if(checkIfOverlaps(rect)){
                batch.draw(x, rect.x-Cat.CAT_BODY_WIDTH/2, rect.y-Cat.CAT_BODY_HEIGHT/2, Cat.CAT_BODY_WIDTH, Cat.CAT_BODY_HEIGHT);
            }
        }
        batch.end();

        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            Cat cat = findCat(touchPos);
            if(cat!= null){
                catIsClicked = true;
                helpCatRectangleRange = cat.getRange();
                helpCatRectangleBody = cat.getBody();
            }else{
                catIsClicked = false;
            }
        }

        //also draw when moving
        if(catIsClicked || isMoving){
            shapeRenderer = new ShapeRenderer();
            Gdx.gl.glEnable(GL20.GL_BLEND);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            shapeRenderer.setProjectionMatrix(camera.combined);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.DARK_GRAY.r,Color.DARK_GRAY.g,Color.DARK_GRAY.b, 0.4f);
            shapeRenderer.rect(helpCatRectangleRange.x, helpCatRectangleRange.y, helpCatRectangleRange.width, helpCatRectangleRange.height);
            shapeRenderer.rect(helpCatRectangleBody.x, helpCatRectangleBody.y, helpCatRectangleBody.width, helpCatRectangleBody.height);
            shapeRenderer.end();
            Gdx.gl.glDisable(GL20.GL_BLEND);
        }
        //remove cat if right click and return half the money used to buy the cat (not including upgrades) temporary method
        if(Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)){
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            Cat cat = findCat(touchPos);
            if(cat!= null){
                gold += catTypes.get(cat.getID()).getCost()/2;
                allCats.removeValue(cat, false);
                catIsClicked = false;
            }
        }

        showHitboxAll();

        // process user input
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            //find a way to put this into buttonDown
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            Rectangle rectangle = new Rectangle(touchPos.x, touchPos.y, 0, 0);

            int checkWhichCat = checkWhichCat(rectangle);

            if (checkWhichCat != -1 && !isMoving && gold >= catTypes.get(checkWhichCat).getCost()) {
                Gdx.input.setInputProcessor(new InputProcessor() {
                    @Override
                    public boolean keyDown(int keycode) {
                        return false;
                    }

                    @Override
                    public boolean keyUp(int keycode) {
                        return false;
                    }

                    @Override
                    public boolean keyTyped(char character) {
                        return false;
                    }

                    @Override
                    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                        return true;
                    }

                    @Override
                    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                        isMoving = false;
                        Vector3 touchPos = new Vector3(screenX, screenY, 0);
                        camera.unproject(touchPos);
                        Rectangle catPosition = new Rectangle(touchPos.x, touchPos.y, 0, 0);

                        boolean helpOverlaps = checkIfOverlaps(catPosition);

                        //try to make it so that you cant move the cats if you dont have enough money

                        //figure out a way to make getCatCost a part of Cat class
                        //If the cats DONT overlap with the river OR any other cats AND the gold amount is above CAT_COST you can spawn the cat
                        if (!helpOverlaps && gold >= catTypes.get(checkWhichCat).getCost()) {
                            gold -= catTypes.get(checkWhichCat).getCost();
                            spawnCat(touchPos.x, touchPos.y, checkWhichCat);
                        }
                        for(Cat catType: catTypes){
                            if(checkWhichCat == catType.getID()){
                                catType.getBody().x = catHolderPositions[catType.getID()].x;
                                catType.getBody().y = catHolderPositions[catType.getID()].y;
                                catType.setBodyPosition();
                                break;
                            }
                        }
                        //sort the cats based on REVERSE y values
                        allCats.sort(Comparator.comparing(cat -> -cat.getBody().y));
                        return false;
                    }

                    @Override
                    public boolean touchDragged(int screenX, int screenY, int pointer) {
                        isMoving = true;
                        Vector3 touchPos = new Vector3(screenX, screenY, 0);

                        camera.unproject(touchPos);

                        touchPosIsMoving = touchPos;

                        for(Cat catType : catTypes){
                            if(checkWhichCat == catType.getID()){
                                catType.getBody().x = touchPos.x - Cat.CAT_BODY_WIDTH / 2;
                                catType.getBody().y = touchPos.y - Cat.CAT_BODY_WIDTH / 2;

                                catType.getRange().x = touchPos.x -  catType.getRange().width / 2;
                                catType.getRange().y = touchPos.y -  catType.getRange().height / 2;

                                helpCatRectangleRange = catType.getRange();
                                helpCatRectangleBody = catType.getBody();
                                break;
                            }
                        }
                        return false;
                    }

                    @Override
                    public boolean mouseMoved(int screenX, int screenY) {
                        return false;
                    }

                    @Override
                    public boolean scrolled(float amountX, float amountY) {
                        return false;
                    }
                });
            }
        } else {
            Gdx.input.setInputProcessor(null);
        }

        // check if we need to create a new fish
        if (TimeUtils.nanoTime() - lastDropTime > speed) spawnFish();
        speed *= 0.9995;
        // move the fishs, remove any that are beneath the bottom edge of
        // the screen or that hit the moonCat. In the latter case we play back
        // a sound effect as well.
        for (Iterator<Fish> iter = allFish.iterator(); iter.hasNext(); ) {
            Fish fish = iter.next();
            fishMove(fish);

            if (fish.getBody().y < 0) {
                health -= fish.getDamage();
                iter.remove();
            }

            if (fish.getHealth() <= 0) {
                gold += fish.getGoldDrop();
                iter.remove();
            }

            if (health <= 0) {
                Gdx.app.exit();
            }
        }

        for (Cat cat : allCats) {
            Array<Integer> attackedFishId = cat.attack(allFish);
            if(attackedFishId!= null){
                attackedFishId.forEach(attackedFish -> {
                    batch.begin();
                    batch.draw(hit, allFish.get(attackedFish).getBody().x, allFish.get(attackedFish).getBody().y, allFish.get(attackedFish).getFishWidth(), allFish.get(attackedFish).getFishHeight());
                    batch.end();
                });
            }
        }
    }
    @Override
    public void dispose() {
        // dispose of all the native resources
        clownFish.dispose();
        clownFishRotated.dispose();
        vomitFish.dispose();
        vomitFishRotated.dispose();
        anglerFish.dispose();
        anglerFishRotated.dispose();
        backgroundImage.dispose();
        backgroundImageBridges.dispose();
        baseCatImage.dispose();
        moonCatImage.dispose();
        brownCatImage.dispose();
        mooCatImage.dispose();
        catHolderImage.dispose();
        hit.dispose();
        x.dispose();
    }
}