package Character;
import CharacterSkills.*;

public class Peasant extends Unit {

    String description = "A peasant with a stick.";
    public Peasant(String n) {
        name = n;
        setStats();
        setSkills();
    }
    public Peasant() {
        name = "Unknown";
        setStats();
        setSkills();
    }
    private void setStats() {
        statSheet.put(STAT.HEALTH, 600);
        statSheet.put(STAT.STRENGTH, 120);
        statSheet.put(STAT.MAGIC, 10);
        statSheet.put(STAT.DEFENSE, 30);
        statSheet.put(STAT.MDEFENSE, 10);
        statSheet.put(STAT.SPEED, 100);
        statSheet.put(STAT.CRITICAL, 15);
        statSheet.put(STAT.CRITDAMAGE, 150);
        statSheet.put(STAT.EFFECTIVENESS, 0);
        statSheet.put(STAT.RESISTANCE, 0);
        statSheet.put(STAT.EVASION, 10);
        statSheet.put(STAT.BLOCK, 15);
        health = statSheet.get(STAT.HEALTH);
    }
    private void setSkills() {
        skillList = new Skill[] {
            new Stab(),
            new EatFood()
        };
    }    
}
