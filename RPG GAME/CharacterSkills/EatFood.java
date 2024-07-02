package CharacterSkills;
import Character.*;
import Stage.BattleStage;

public class EatFood extends ActiveSkill {

    String description = "The Unit heals for 50-70% of their max health. Used when below half health.";

    String[] foodLines = {
        " takes out a wheel of cheese and eats the entire thing in one sitting",
        " whips out a table and cloth and has a nice meal",
        " chugs a potion made of gravy",
        "'s takeout delivery arrives and is immediately inhaled",
        " eats raw corn on the cob",
        " downs a can of spinach"
    };
    String[] defenderReaction = {
        " stands there and watches.",
        "'s stomach growls.",
        " looked away for two seconds.",
        " sits there and looks all sad."
    };

    public void use(Unit attacker, Unit defender, BattleStage stage) {
        int multi = ((attacker.getStat("mHP"))*(rng.nextInt(11)+40))/100;
        attacker.heal(multi);
        stage.battleLog = attacker.name+getRandomString(foodLines)+" and heals for "+multi
                 +" while "+defender.name+getRandomString(defenderReaction);
        startCooldown();
        attacker.delay(delay);
    }
    public boolean isReady(Unit user, Unit defender) {
        boolean underHalfHealth = user.health < user.getStat("mHP")/2;
        boolean onCD = onCooldown();
        return  underHalfHealth && !onCD;
    }
    String getResult() {
        return result;
    }
    public EatFood() {
        delay = 700;
        cooldown = 5;
        priority = 1;
    }
}