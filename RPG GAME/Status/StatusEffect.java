package Status;
import java.util.Arrays;

public abstract class StatusEffect {

    enum TAG {
        BUFF,
        DEBUFF, 
        STATMOD,
        BARRIER,
        UNIQUE,
        DAMAGEOVERTIME,
        HEALOVERTIME 
    }
    TAG tags[];

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
    public boolean isBuff() {
        return Arrays.asList(tags).contains(TAG.BUFF);
    }
    public boolean isDebuff() {
        return Arrays.asList(tags).contains(TAG.DEBUFF);
    }
    public boolean isStatMod() {
        return Arrays.asList(tags).contains(TAG.STATMOD);
    }
    public boolean isDOT() {
        return Arrays.asList(tags).contains(TAG.DAMAGEOVERTIME);
    }
    public boolean isHOT() {
        return Arrays.asList(tags).contains(TAG.HEALOVERTIME);
    }    
    public boolean isBarrier() {
        return Arrays.asList(tags).contains(TAG.BARRIER);
    }
    public boolean isUnique() {
        for(TAG curr: tags) {
            if(curr == TAG.UNIQUE) return true;
        }
        return false;
    }  
}