package Character;
import java.util.*;
import CharacterSkills.*;
import Status.*;
import Stage.*;

public abstract class Unit {



    public String name;
    public String description;
    public int health = 0;
    public int delay = 1000;
    Random rng = new Random();
    
    HashMap<STAT, Integer> statSheet = new HashMap<STAT, Integer>();

    Skill[] skillList;
    ArrayList<StatusEffect> effects = new ArrayList<StatusEffect>();

    public void takeDamage(Damage dmg) {
        health = Math.max(health-dmg.totalDamage(), 0);
        for(StatusEffect eff: dmg.effects) effects.add(eff);
    }
    public void heal(int x) {
        x += health;
        health = Math.min(x, statSheet.get(STAT.HEALTH));
    }
    public void delay(int x) {
        delay += x;
    }
    public void randomDelay() {
        delay += rng.nextInt(101)-50;
    }
    public void advance() {
        delay -= statSheet.get(STAT.SPEED);
    }
    public void advance(int x) {
        delay += x;
    }
    public int getStat(STAT key) {
        return statSheet.get(key);
    }
    public int calcStat(STAT key) {
        int total = statSheet.get(key);
        for(StatusEffect status: effects) {
            if(status.is(TAG.STATMOD)) total += status.getStatMod(key);
        }
        return total;
    }
    public int getDelay() {
        return delay;
    }
    public int applyDef(int damage) {
        return (damage*100)/(calcStat(STAT.DEFENSE)+100);
    }
    public int applyMDEF(int damage) {
        return (damage*100)/(calcStat(STAT.MDEFENSE)+100);
    }
    public int applyEVA(Damage dmg) {
        dmg.evaded();
        return dmg.damage /= 2;
    }
    public int applyCrit(Damage dmg) {
        dmg.critical();
        return dmg.damage = (dmg.damage*calcStat(STAT.CRITDAMAGE))/100;
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
    public void turnStartTrigger() {
        
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