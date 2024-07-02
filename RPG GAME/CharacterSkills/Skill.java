package CharacterSkills;
import Character.*;
import Stage.BattleStage;

import java.util.Random;

public abstract class Skill {

    enum TAG {
        PHYSICAL,
        MAGICAL,
        MELEE,
        RANGED,
        BASIC,
        PASSIVE,
        ACTIVE
    }
    TAG tags[];
    
    String description;
    public int priority = 0;
    String result;

    int physMulti = 0;
    int magMulti = 0;
    int accuracy = 0;
    
    int currentCD = 0;
    int cooldown;
    int delay;

    Random rng = new Random();

    int rngRange(int min, int max) {
        return min+(rng.nextInt(max-min+1));
    }
    int avgRoll() {
        return (rng.nextInt(100)+rng.nextInt(100)+2)/2;
    }
    String getRandomString(String[] array) {
        return array[rng.nextInt(array.length)];
    }
    int calculateDamage(int stat, int multi) {
        return stat*multi/100;
    }
    void tick() {
        if(onCooldown()) {
            --currentCD;
        }
    }
    public boolean onCooldown() {
        return currentCD != 0;
    }
    void startCooldown() {
        currentCD = cooldown;
    }
    public boolean isReady(Unit user, Unit target) {
        return false;
    }
    public void use(Unit attacker, Unit defender, BattleStage stage) {

    }
    void rollEVABLKCRIT(Unit attacker, Unit defender, Damage dmg) {
        int hitChance = accuracy-defender.calcStat("EVA");
        if(rollForEVA(hitChance)) defender.applyEVA(dmg);
        else {
            if(rollForCrit(attacker.calcStat("CRI"))) attacker.applyCrit(dmg);
            if(rollForBLK(defender.calcStat("BLK"))) defender.applyBLK(dmg);
        }
    }
    boolean rollForEVA(int chance) {
        return rng.nextInt(100)+1 <= chance;
    }
    boolean rollForCrit(int chance) {
        return rng.nextInt(100)+1 >= chance;
    }
    boolean rollForBLK(int chance) {
        return rng.nextInt(100)+1 <= chance;
    }
}