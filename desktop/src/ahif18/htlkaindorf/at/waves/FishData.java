package ahif18.htlkaindorf.at.waves;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FishData {
    private FishType[] fish;
    private int amount;

    public FishData(int amount, FishType... fish) {
        this.fish = fish;
        this.amount = amount;
    }

}