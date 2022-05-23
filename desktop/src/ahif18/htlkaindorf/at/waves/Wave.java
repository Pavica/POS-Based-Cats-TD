package ahif18.htlkaindorf.at.waves;
import ahif18.htlkaindorf.at.waves.FishData;
import ahif18.htlkaindorf.at.waves.FishType;
import lombok.Data;

@Data
public class Wave{
    public static float WAVE_SPEED_MULTIPLIER = 0.9f;
    private int waveCount = 0;

    private FishData[][] waveData =
    {
        //Fist Wave
        {
            new FishData(10, FishType.ClownFish),
            new FishData(5, FishType.ClownFish, FishType.VomitFish),
            new FishData(3, FishType.AnglerFish)
        },
        //Second Wave
            {
                new FishData(20, FishType.ClownFish),
                new FishData(10, FishType.ClownFish, FishType.VomitFish),
                new FishData(6, FishType.AnglerFish)
            },
        //Third Wave
            {
                new FishData(30, FishType.ClownFish),
                new FishData(15, FishType.ClownFish, FishType.VomitFish),
                new FishData(9, FishType.AnglerFish)
            },
    };
    private int waveAmount = waveData.length;
}
