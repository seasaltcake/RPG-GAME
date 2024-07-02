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
        statSheet.put("mHP", 600);
        statSheet.put("STR", 120);
        statSheet.put("MAG", 10);
        statSheet.put("DEF", 30);
        statSheet.put("WIS", 10);
        statSheet.put("SPD", 100);
        statSheet.put("CRI", 15);
        statSheet.put("CDM", 150);
        statSheet.put("EFF", 0);
        statSheet.put("RES", 0);
        statSheet.put("EVA", 10);
        statSheet.put("BLK", 15);
        health = statSheet.get("mHP");
    }
    private void setSkills() {
        skillList = new Skill[] {
            new Stab(),
            new EatFood()
        };
    }    
}
