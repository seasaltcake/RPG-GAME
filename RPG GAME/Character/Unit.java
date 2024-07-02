package Character;
import java.util.*;
import CharacterSkills.*;
import Status.*;
import Stage.*;

public abstract class Unit {

    enum STAT {
        HEALTH,
        STRENGTH,
        MAGIC,
        DEFENSE,
        MDEFENSE,
        SPEED,
        CRITICAL,
        CRITDAMAGE,
        EFFECTIVENESS,
        RESISTANCE,
        EVASION,
        BLOCK
    }

    public String name;
    public String description;
    public int health = 0;
    public int delay = 1000;
    Random rng = new Random();
    
    HashMap<String, Integer> statSheet = new HashMap<String, Integer>();

    Skill[] skillList;
    ArrayList<StatusEffect> effects = new ArrayList<StatusEffect>();

    public void takeDamage(Damage dmg) {
        health = Math.max(health-dmg.totalDamage(), 0);
        for(StatusEffect eff: dmg.effects) effects.add(eff);
    }
    public void heal(int x) {
        x += health;
        health = Math.min(x, statSheet.get("mHP"));
    }
    public void delay(int x) {
        delay += x;
    }
    public void randomDelay() {
        delay += rng.nextInt(101)-50;
    }
    public void advance() {
        delay -= statSheet.get("SPD");
    }
    public void advance(int x) {
        delay += x;
    }
    public int getStat(String key) {
        return statSheet.get(key);
    }
    public int calcStat(String key) {
        int total = statSheet.get(key);
        for(StatusEffect status: effects) {
            if(status.isBuff()) effects.add(status);
        }
        return total;
    }
    public int getDelay() {
        return delay;
    }
    public int applyDef(int damage) {
        return (damage*100)/(calcStat("DEF")+100);
    }
    public int applyMDEF(int damage) {
        return (damage*100)/(calcStat("WIS")+100);
    }
    public int applyEVA(Damage dmg) {
        dmg.evaded();
        return dmg.damage /= 2;
    }
    public int applyCrit(Damage dmg) {
        dmg.critical();
        return dmg.damage = dmg.damage*calcStat("CDM")/100;
    }
    public int applyBLK(Damage dmg) {
        dmg.blocked();
        return dmg.damage /= 2;
    }
    public void onCriticalHitTrigger() {

    }
    public void onCriticalDamageTrigger() {

    }
    public void onEVATrigger() {

    }
    public void onBLKTrigger() {

    }
    public boolean isDead() {
        return health <= 0;
    }
    public boolean isReady() {
        return delay <= 0;
    }
    public void takeTurn(Unit target, BattleStage stage) {
        Skill choosenSkill = this.skillList[0];
        for(int i=1; i<skillList.length; ++i) {
            boolean skillReady = skillList[i].isReady(this, target);
            boolean higherPrio = skillList[i].priority > choosenSkill.priority;
            if(skillReady && higherPrio) choosenSkill = skillList[i];
        }
        choosenSkill.use(this, target, stage);
    }
    public void onTurnEnd() {
        for(int i=0; i<effects.size(); ++i) {
            effects.get(i).tick();
            if( effects.get(i).durationZero()) effects.remove(i);
        }
    }
    public void onTurnStart() {
        
    }
    public void physicalDamageTrigger() {

    }
    public void magicDamageTrigger() {

    }
    public void onMeleeTrigger() {

    }
    public void onRangedTrigger() {

    }
    public void getStatusEffect(StatusEffect status) {
        effects.add(status);
    }
}