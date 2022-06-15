package ahif18.htlkaindorf.at;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;

import ahif18.htlkaindorf.at.cats.*;
import ahif18.htlkaindorf.at.decorators.*;
import ahif18.htlkaindorf.at.fishes.AnglerFish;
import ahif18.htlkaindorf.at.fishes.ClownFish;
import ahif18.htlkaindorf.at.fishes.Fish;
import ahif18.htlkaindorf.at.fishes.VomitFish;
import ahif18.htlkaindorf.at.libs.GifDecoder;
import ahif18.htlkaindorf.at.waves.FishType;
import ahif18.htlkaindorf.at.waves.Wave;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * This class is the main class where libGDX methods are used to run the process
 *
 * @author Clark | Luka
 * @version 1.0
 * Last modified: 15.06.2022
 */
public class Drop extends ApplicationAdapter {
    /** used for the max health of a player */
    private static final int MAX_HEALTH = 100;

    /** used to identify at which speed the game is running */
    public static final int GAME_SPEED = 1;

    /** amount of gold that is available at the beginning of the game */
    private int gold = 1000;

    /** speed of cats that they are spawned in (in milliseconds) */
    private int speed = 1000000000/GAME_SPEED;

    /** used for the max health of a player */
    private int health = MAX_HEALTH;

    /** used to determine random clothes for cats*/
    Random rand = new Random();

    /** used to keep up with the count of fish in a fishData Object */
    private int countFish = 0;

    /** used to keep up with the count of fishData in a wave Object */
    private int countFishData = 0;

    /** used to show the upgrade menu */
    private Stage stage;

    /** used to show the upgrade menu */
    private Viewport viewport;
  
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

    /** texture of the cat upgrade image in the bottom right corner */
    private Texture catUpgradeImage;

    /** texture of the hit effect that appear when a fish has been hit by a cat */
    private Texture hit;

    /** texture of the x that is shown when something cannot be placed */
    private Texture x;

    /** Sprite of the background */
    private Sprite backgroundSprite;

    /** Sprite of the bridges in the background */
    private Sprite backgroundBridgesSprite;

    /** Sprite of the cat holder in the top right corner */
    private Sprite catHolderSprite;
    private Sprite catUpgradeSprite;

    /** used to draw textures */
    private SpriteBatch batch;

    /** used to render the shapes */
    private ShapeRenderer shapeRenderer;

    /** Used for the font of the text that is shown */
    private BitmapFont font;

    /** Used for another font of the text that is shown */
    private BitmapFont font2;

    /** is used to project and unproject a view */
    private OrthographicCamera camera;

    /** used to make every cat have their own animation cycle */
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

    /** used to draw the range of a cat when the cat is selected */
    private Rectangle helpCatRectangleRange;

    /** used to draw the body of a cat when the cat is selected*/
    private Rectangle helpCatRectangleBody;

    /** Used for the cat holder in the top right corner */
    private Rectangle catHolder;

    /** Used for the upgrade window in the bottom right corner */
    private Rectangle catUpgrade;
  
    /** Array of animations of cat animations */
    private Array<Animation<TextureRegion>> catAnimations;

    /** Array of textures of fish */
    private Array<Texture> fishTextures;

    /** Array of all cats in the game */
    private Array<Cat> allCats;

    /** Array of all fish in the game */
    private Array<Fish> allFish;

    /** used to determine the time between the spawning of fish */
    private long lastDropTime;

    /** used to tell if a cat is being moved */
    private boolean isMoving = false;

    /** used to tell the position of the moving cat */
    private Vector3 touchPosIsMoving;

    /** used to tell if the Hitbox should be shown */
    private boolean showHitbox = false;

    /** used to use and manipulate multiple Input Processors */
    private final InputMultiplexer multiplexer = new InputMultiplexer();

    /** used to tell if the upgrade window is open */
    private boolean upgradeIsOpen = false;

    /** used for the button to upgrade the first stat*/
    private Button upgradeOne;

    /** used for the button to upgrade the second stat*/
    private Button upgradeTwo;

    /** used to display the text of the button for the first stat*/
    private Label upgradeOneText;

    /** used to display the text of the button for the second stat*/
    private Label upgradeTwoText;

    /** used to display the text of the label above the button of the first stat*/
    private Label upgradeOneLabel;

    /** used to display the text of the label above the button of the second stat*/
    private Label upgradeTwoLabel;

    /** used for the button to close the upgrade window */
    private Button closeUpgrade;

    /** used for the button to delete a cat*/
    private Button deleteCat;


    /** used to identify the cat that has been selected by the user*/
    private Cat selectedCat;


    /** used for the button to delete a cat*/
    private Wave waves;

    /** used to identify if the spawning of fish should stop or not*/
    private boolean stopFishSpawn = false;

    //0, 2, 3, 1
    /** used to identify the positions of the cats in the cat holder*/
    private final Vector3[] catHolderPositions = {
            new Vector3(677, 377,0),
            new Vector3(747,297,0),
            new Vector3(747,377,0),
            new Vector3(677, 297,0),
    };


    /** used to identify the points on the map where the fish should change direction */
    public static final Rectangle[] points = {

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


    /** used for the hitbox for not allowing something to be placed in the river */
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

    /**
     * method used to return the delta time based on the current game speed
     *
     * @return returns the delta time based on the current game speed
     */
    private float myDeltaTime(){
        return Gdx.graphics.getDeltaTime() * GAME_SPEED;
    }

    /**
     * method used to display the window to upgrade a cat with its components
     *
     * @param setVisibility : boolean if the visibility should be set to true or false
     */
    private void upgradeStageVisibility(boolean setVisibility){
        deleteCat.setVisible(setVisibility);
        closeUpgrade.setVisible(setVisibility);
        upgradeTwoLabel.setVisible(setVisibility);
        upgradeOneLabel.setVisible(setVisibility);
        upgradeTwo.setVisible(setVisibility);
        upgradeOne.setVisible(setVisibility);
        upgradeOneText.setVisible(setVisibility);
        upgradeTwoText.setVisible(setVisibility);
    }

    /**
     * method used to generate and set the text that is shown in the upgrade window
     *
     * @param cat : the cat that is affected by the click actions
     */
    private void fillUpgradeStage(Cat cat){
        upgradeOneLabel.setText(cat.getOneName() +" LVL "+ cat.getOne());
        upgradeTwoLabel.setText(cat.getTwoName() +" LVL "+cat.getTwo());
        upgradeOneText.setText(String.format("%.0f G",cat.getOneCost()));
        upgradeTwoText.setText(String.format("%.0f G",cat.getTwoCost()));

        helpCatRectangleRange = selectedCat.getRange();
        helpCatRectangleBody = selectedCat.getBody();
    }

    /** method used to generate the upgrade window */
    private void loadUpgradeStage(){
        Label.LabelStyle LABEL_STYLE = new Label.LabelStyle(font2, Color.WHITE);
        viewport = new StretchViewport(800, 400);
        stage = new Stage(viewport);

        Button.ButtonStyle buttonStyle = new Button.ButtonStyle();

        upgradeOne = new Button(buttonStyle);
        upgradeOne.setSize(60, 30);
        upgradeOne.setPosition(577,20);

        upgradeOne.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(gold >= selectedCat.getOneCost() && selectedCat.getOne() < Cat.MAX_LEVEL){
                    gold -= selectedCat.getOneCost();
                    selectedCat.upgradeOne();
                    selectedCat.updateRange();
                    fillUpgradeStage(selectedCat);
                }
            }
        });

        upgradeOneText = new Label("", LABEL_STYLE);
        upgradeOneText.setSize(60, 30);
        upgradeOneText.setPosition(577,20);

        upgradeTwo = new Button(buttonStyle);
        upgradeTwo.setSize(60, 30);
        upgradeTwo.setPosition(662,20);

        upgradeTwo.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(gold >= selectedCat.getTwoCost() && selectedCat.getTwo() < Cat.MAX_LEVEL){
                    gold -= selectedCat.getTwoCost();
                    selectedCat.upgradeTwo();
                    selectedCat.updateRange();
                    fillUpgradeStage(selectedCat);
                }
            }
        });

        upgradeTwoText = new Label("", LABEL_STYLE);
        upgradeTwoText.setSize(60, 30);
        upgradeTwoText.setPosition(662,20);

        deleteCat = new Button(buttonStyle);
        deleteCat.setSize(25, 25);
        deleteCat.setPosition(750,40);

        deleteCat.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                deleteCat(selectedCat);
            }
        });

        closeUpgrade = new Button(buttonStyle);
        closeUpgrade.setSize(22, 20);
        closeUpgrade.setPosition(765,77);

        closeUpgrade.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                upgradeStageVisibility(false);
                upgradeIsOpen = false;
            }
        });

        upgradeOneLabel = new Label("", LABEL_STYLE);
        upgradeOneLabel.setSize(60, 20);
        upgradeOneLabel.setPosition(577,60);

        upgradeTwoLabel = new Label("", LABEL_STYLE);
        upgradeTwoLabel.setSize(60, 20);
        upgradeTwoLabel.setPosition(662,60);

        multiplexer.addProcessor(stage);

        stage.addActor(deleteCat);
        stage.addActor(closeUpgrade);
        stage.addActor(upgradeTwoLabel);
        stage.addActor(upgradeOneLabel);
        stage.addActor(upgradeOneText);
        stage.addActor(upgradeTwoText);
        stage.addActor(upgradeTwo);
        stage.addActor(upgradeOne);
        upgradeStageVisibility(false);
    }

    /** method used to create all textures, animations, sprites and other objects. */
    @Override
    public void create() {
        Gdx.input.setInputProcessor(multiplexer);
        font = FontGenerator.generateFreetypeFont(16);
        font2 =FontGenerator.generateFreetypeFont(11);

        waves = new Wave();

        loadUpgradeStage();
        // load the images for the droplet and the moonCat, 32x32 pixels each
        backgroundImage = new Texture(Gdx.files.internal("backgroundElements/background.png"));
        backgroundImageBridges = new Texture(Gdx.files.internal("backgroundElements/background-2.png"));
        catUpgradeImage = new Texture(Gdx.files.internal("backgroundElements/upgradeBackground.png"));

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
        catUpgradeSprite = new Sprite(catUpgradeImage);

        catHolder = new Rectangle(630,240,34*5, 48*5);
        catUpgrade = new Rectangle(490, 0, 300, 120);

        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();

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

        // create the fish array and spawn the first fish
        allFish = new Array<>();
        allCats = new Array<>();
    }

    /** method used to render the hitbox of every game element */
    private void showHitboxAll(){
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
        shapeRenderer.rect(catUpgrade.x, catUpgrade.y, catUpgrade.width, catUpgrade.height);
        shapeRenderer.end();

        stage.setDebugAll(showHitbox);
    }

    /**
     * method used to make the fish move in any of the eight directions
     *
     * @param fish : fish that is affected by the movement actions
     */
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

    /**
     * method used to deleted the specified cat
     *
     * @param cat : cat that is affected by this action
     */
    private void deleteCat(Cat cat){
            gold += catTypes.get(cat.getID()).getCost()/2 + cat.getOneCost()/2 + cat.getTwoCost()/2;
            allCats.removeValue(cat, false);
            catIsClicked = false;
            upgradeIsOpen = false;
            upgradeStageVisibility(false);
    }

    /** method used to spawn fish based on FishData Objects and Waves */
    private void spawnFish() {
        Fish fish;
        if(waves.getWaveCount() >= waves.getWaveAmount()){
            stopFishSpawn = true;
            return;
        }

        if(countFishData == waves.getWaveData()[waves.getWaveCount()].length){
            speed = (int)(speed * Wave.WAVE_SPEED_MULTIPLIER);
            countFishData = 0;
            stopFishSpawn = true;
            return;
        }

        for (FishType fishType : waves.getWaveData()[waves.getWaveCount()][countFishData].getFish()) {
            switch (fishType) {
                case ClownFish:
                    fish = new ClownFish();
                    break;
                case VomitFish:
                    fish = new VomitFish();
                    break;
                case AnglerFish:
                    fish = new AnglerFish();
                    break;
                default:
                    fish = null;
                    break;
            }
            allFish.add(fish);
        }
        countFish++;
        if(countFish == waves.getWaveData()[waves.getWaveCount()][countFishData].getAmount()){
            countFishData++;
            countFish = 0;
        }

        lastDropTime = TimeUtils.nanoTime();
    }

    /**
     * method used to add random clothes to a specified clothedCat
     *
     * @param cat : the Cat Object being clothed
     * @return returns a ClothedCat object
     */
    private ClothedCat clotheCat(Cat cat){
        ClothedCat helpCat = cat;
        helpCat = new CatDecorator(helpCat);
        helpCat.clothes();

        int randomNumber = rand.nextInt(4);

        switch(randomNumber){
            case 0:
                helpCat = new TopHatDecorator(helpCat);
                helpCat.clothes();
                break;
            case 1:
                helpCat = new TurkHatDecorator(helpCat);
                helpCat.clothes();
                break;
            case 2:
                helpCat = new SwagDecorator(helpCat);
                helpCat.clothes();
                break;
            default:
                break;
        }

        randomNumber = rand.nextInt(3);

        switch(randomNumber){
            case 0:
                helpCat = new RibbonDecorator(helpCat);
                helpCat.clothes();
                break;
            case 1:
                helpCat = new GoldChainDecorator(helpCat);
                helpCat.clothes();
                break;
            case 2:
                helpCat = new SonicShoesDecorator(helpCat);
                helpCat.clothes();
                break;
            default:
                break;
        }
        return helpCat;
    }

    /**
     * method used to spawn Cats based on a specified position and type.
     *
     * @param screenX : X coordinate where the generated chat should be displayed
     * @param screenY : Y coordinate where the generated chat should be displayed
     * @param catID :   id of the cat that is to be displayed
     */
    private void spawnCat(float screenX, float screenY, int catID){
        Cat cat;
        switch (catID) {
            case 0:
                cat= new BaseCat(screenX, screenY);
                break;
            case 1:
                cat = new MoonCat(screenX, screenY);
                break;
            case 2:
                cat = new BrownCat(screenX, screenY);
                break;
            case 3:
                cat = new MooCat(screenX, screenY);
                break;
            default:
                cat = null;
                System.out.println("No cats found");
        }
        allCats.add(cat);
        selectedCat = cat;
        fillUpgradeStage(cat);
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

    /**
     * method used to find a cat based on its position
     *
     * @param touchPos : position (X, Y, (Z)) of the touch
     *
     * @return returns the cat that was searched for.
     *         returns null if a cat cannot be found
     */
    private Cat findCat(Vector3 touchPos){
        Rectangle touchPosRect = new Rectangle(touchPos.x, touchPos.y, 0,0);
        for (Cat cat: allCats){
            if(cat.getBody().overlaps(touchPosRect)){
                return cat;
            }
        }
        return null;
    }

    /**
     * method used to identify if the cat hitbox overlaps with the river, any other cat hitbox or the cat holder
     *
     * @param catPosition : position of the cat that is trying to be placed
     *
     * @return returns true if the cat hitbox overlaps with the river, any other cat hitbox or the cat holder
     */
    private boolean checkIfOverlaps(Rectangle catPosition){
        //if it overlaps river
        for (Rectangle riverPoint : riverHitbox) {
            if (catPosition.overlaps(riverPoint)) {
                return true;
            }
        }
        //if it overlaps any cat
        for (Cat cat : allCats) {
            if (catPosition.overlaps(cat.getBody())) {
                return true;
            }
        }
        //if it overlaps catHolder
        return catPosition.overlaps(catHolder);
    }

    /**
     * LibGDX method used for updating the game every frame. All of the game is played out here.
     */
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
            batch.draw(catAnimations.get(allCats.get(i).getID()).getKeyFrame(timeElapsed.get(i)), allCats.get(i).getBody().x, allCats.get(i).getBody().y, Cat.CAT_WIDTH, Cat.CAT_HEIGHT);
            for(int j=0; j<allCats.get(i).getDecorations().size; j++){
                batch.draw(allCats.get(i).getDecorations().get(j), allCats.get(i).getBody().x, allCats.get(i).getBody().y, Cat.CAT_WIDTH, Cat.CAT_HEIGHT);
            }
        }

        //Cat Holder
        catHolderSprite.draw(batch);

        //Cat Upgrade
        if(upgradeIsOpen){
            catUpgradeSprite.draw(batch);
            batch.draw(catAnimations.get(selectedCat.getID()).getKeyFrame(0), 493, 33, Cat.CAT_WIDTH, Cat.CAT_HEIGHT);
            for(int j=0; j<selectedCat.getDecorations().size; j++){
                batch.draw(selectedCat.getDecorations().get(j), 493, 33, Cat.CAT_WIDTH, Cat.CAT_HEIGHT);
            }
        }

        //Gold
        GlyphLayout glyphLayout = new GlyphLayout();
        String goldValue = gold + "";
        glyphLayout.setText(font, goldValue);
        float m = glyphLayout.width;
        font.draw(batch, goldValue, 755 - m, 460);

        //World health
        font.draw(batch, "HP: " + health, 20, 460);

        //Wave
        font.draw(batch, "Wave: " + (Math.min((waves.getWaveCount() + 1), waves.getWaveAmount())) +" / " + waves.getWaveAmount(), 20, 440);

        //Gold cost for cats
        for(int i=0; i< catHolderPositions.length; i++){
            font.setColor(gold < catTypes.get(i).getCost() ? Color.RED : Color.WHITE);
            font.draw(batch, catTypes.get(i).getCost() + "", catHolderPositions[i].x - 10, catHolderPositions[i].y -28);
        }
        font.setColor(Color.WHITE);

        //Cats inside holder (dummies)
        for(int i= 0; i<catTypes.size; i++){
            batch.draw(catAnimations.get(catTypes.get(i).getID()).getKeyFrame(0), catTypes.get(i).getBody().x, catTypes.get(i).getBody().y, Cat.CAT_WIDTH, Cat.CAT_HEIGHT);
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
            camera.unproject(touchPos);

            Rectangle rect = new Rectangle(touchPos.x, touchPos.y, 0,0);

            Cat cat = findCat(touchPos);
            if(cat!= null){
                upgradeIsOpen = true;
                upgradeStageVisibility(true);

                catIsClicked = true;
                selectedCat = cat;
                fillUpgradeStage(selectedCat);
                //still draw the area when clicking the upgrade menu
            }else if(!rect.overlaps(catUpgrade)){
                catIsClicked = false;
            }
        }

        //draw range when moving or when cat is clicked
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

        if(Gdx.input.isKeyJustPressed(Input.Keys.F3)){
            showHitbox = !showHitbox;
        }

        if(showHitbox){
            showHitboxAll();
        }

        // process user input
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            //find a way to put this into buttonDown
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            Rectangle rectangle = new Rectangle(touchPos.x, touchPos.y, 0, 0);

            int checkWhichCat = checkWhichCat(rectangle);

            if (checkWhichCat != -1 && !isMoving && gold >= catTypes.get(checkWhichCat).getCost()) {
                multiplexer.addProcessor(1,new InputProcessor() {
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

                        if (!helpOverlaps && gold >= catTypes.get(checkWhichCat).getCost()) {
                            gold -= catTypes.get(checkWhichCat).getCost();
                            spawnCat(touchPos.x, touchPos.y, checkWhichCat);
                            ClothedCat clothedCat = clotheCat(selectedCat);
                            selectedCat.setDecorations(clothedCat.clothes());
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
            if(multiplexer.size() >= 2) {
               for(int i=1; i<multiplexer.size(); i++){
                   multiplexer.removeProcessor(i);
               }
            }
        }

        // check if we need to create a new fish
        if (TimeUtils.nanoTime() - lastDropTime > speed && !stopFishSpawn) spawnFish();

        // move the fish, remove any that are beneath the bottom edge of
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

        //start fish spawning again after last fish of wave is killed
        if(stopFishSpawn && allFish.isEmpty()){
            waves.setWaveCount(waves.getWaveCount() + 1);
            stopFishSpawn = false;
        }

        //Stage2D
        stage.act();
        stage.draw();
    }

    /** used to dispose of all native resources */
    @Override
    public void dispose() {
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