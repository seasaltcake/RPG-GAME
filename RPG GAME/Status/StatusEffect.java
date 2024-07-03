package Status;
import java.util.Arrays;
import java.util.HashMap;

import Character.STAT;

public abstract class StatusEffect {


    TAG tags[];
    HashMap<STAT, Integer> statSheet = new HashMap<STAT, Integer>();

    String desciption = "";
    int rank = 0;
    int duration;

    public void tick() {
        --duration;
    }
    public boolean durationZero() {
        return duration == 0;
    }
    public StatusEffect() {

    }
    public boolean is(TAG tag) {
        return Arrays.asList(tags).contains(tag);
    }
    public int getStatMod(STAT stat) {
        return statSheet.get(stat);
    }
}