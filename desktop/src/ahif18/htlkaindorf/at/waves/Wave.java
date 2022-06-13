package ahif18.htlkaindorf.at.waves;
import lombok.Data;

@Data
public class Wave{
    public static float WAVE_SPEED_MULTIPLIER = 0.9f;
    private int waveCount = 0;

    private FishData[][] waveData =
    {
        //1. Wave
        {
            new FishData(10, FishType.ClownFish),
            new FishData(5, FishType.ClownFish, FishType.VomitFish),
            new FishData(3, FishType.AnglerFish)
        },
        //2. Wave
        {
            new FishData(20, FishType.ClownFish),
            new FishData(10, FishType.ClownFish, FishType.VomitFish),
            new FishData(6, FishType.AnglerFish)
        },
        //3. Wave
        {
            new FishData(30, FishType.ClownFish),
            new FishData(15, FishType.ClownFish, FishType.VomitFish),
            new FishData(9, FishType.AnglerFish)
        },
        //4. Wave
        {
            new FishData(20, FishType.ClownFish, FishType.VomitFish,FishType.AnglerFish),
        },
        //5. Wave
        {
            new FishData(20, FishType.VomitFish),
            new FishData(12, FishType.AnglerFish)
        },
        //6. Wave
        {
            new FishData(25, FishType.ClownFish, FishType.VomitFish),
            new FishData(15, FishType.VomitFish, FishType.AnglerFish),
        },
        //7. Wave
        {
            new FishData(20, FishType.ClownFish),
            new FishData(10, FishType.VomitFish),
            new FishData(5, FishType.AnglerFish),
            new FishData(20, FishType.ClownFish),
            new FishData(10, FishType.VomitFish),
            new FishData(5, FishType.AnglerFish),
            new FishData(20, FishType.ClownFish),
            new FishData(10, FishType.VomitFish),
            new FishData(5, FishType.AnglerFish)
        },
        //8. Wave
        {
            new FishData(60, FishType.ClownFish,FishType.VomitFish, FishType.AnglerFish),
        },
        //9. Wave
        {
            new FishData(60, FishType.ClownFish, FishType.VomitFish),
            new FishData(60, FishType.ClownFish,FishType.ClownFish, FishType.ClownFish),
            new FishData(60, FishType.ClownFish, FishType.AnglerFish),
        },
        //10. Wave
        {
            new FishData(100, FishType.ClownFish,FishType.VomitFish, FishType.AnglerFish, FishType.ClownFish,FishType.VomitFish, FishType.AnglerFish),
        },
    };
    private int waveAmount = waveData.length;
}
