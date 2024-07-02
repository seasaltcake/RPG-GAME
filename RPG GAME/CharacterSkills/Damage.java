package CharacterSkills;
import Character.*;
import Status.*;
import java.util.ArrayList;
import java.util.Random;

public class Damage {

    public int damage = 0;

    boolean graze = false;
    boolean crit = false;
    boolean block = false; 

    public ArrayList<StatusEffect> effects = new ArrayList<StatusEffect>();

    Random rng = new Random();

    int rngRange(int min, int max) {
        return min+(rng.nextInt(max-min+1));
    }
    int avgRoll() {
        return (rng.nextInt(100)+rng.nextInt(100)+2)/2;
    }
    int randomizeDamage(int damage) {
        int ran = (-5+rng.nextInt(11))+100;
        return damage*ran/100;
    }
    int applyCrit(Unit attacker, int damage) {
        if(rng.nextInt(100)+1 <= attacker.calcStat("CRI")) {
            return damage*attacker.calcStat("CDM")/100;
        }
        return damage;
    }
    public Damage() {
    }
    public Damage(int dmg) {
        damage = randomizeDamage(dmg);
    }
    String asString() {
        String s = Integer.toString(totalDamage());
        if(graze) s += "..";
        else {
            if(crit) s += "!";
            if(block) s += "?";
        }
        return s;
    }
    public int totalDamage() {
        return damage;
    }
    public void evaded() {
        graze = true;
    }
    public void critical() {
        crit = true;
    }
    public void blocked() {
        block = true;
    }
}
