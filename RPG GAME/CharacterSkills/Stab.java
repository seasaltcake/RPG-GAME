package CharacterSkills;
import Character.*;
import Stage.*;

public class Stab extends ActiveSkill {

    String description = "stabby stab";
    int priority = 1;
    int delay = 1000;
    int cooldown = 0;
    
    public void use(Unit attacker, Unit defender, BattleStage stage) {
        Damage dmg = new Damage(calculateDamage(attacker.calcStat(STAT.STRENGTH), physMulti));
        rollEVABLKCRIT(attacker, defender, dmg);
        defender.takeDamage(dmg);
        stage.battleLog = attacker.name+" pokes "+defender.name+" for "+dmg.asString();
        attacker.delay += delay;
    }
    public Stab() {
        physMulti = 100;
        accuracy = 95;
    }
    public boolean isReady(Unit user) {
        return !onCooldown();
    }
}
