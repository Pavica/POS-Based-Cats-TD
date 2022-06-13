package ahif18.htlkaindorf.at.waves;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
/**
 * This class represents the data of a fish
 *
 * @author Clark | Luka
 * @version 1.0
 * Last modified: 13.06.2022
 */
public class FishData {
    /** array of all types of fish */
    private FishType[] fish;

    /** amount of fish that are being spawned */
    private int amount;

    /**
     * Specific constructor that is used identify a portion of the wave
     * @param amount : used to identify the amount of fish that are being spawned
     * @param fish : used to identify the types of fish that are being spawned
     */
    public FishData(int amount, FishType... fish) {
        this.fish = fish;
        this.amount = amount;
    }
}