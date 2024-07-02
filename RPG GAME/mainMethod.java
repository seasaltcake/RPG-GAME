import Character.*;
import Stage.BattleStage;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;

public class mainMethod {
    public static void main(String[] args) {
        Unit unit1 = new Peasant("jen");
        Unit unit2 = new Peasant("brian");
        battle(unit1, unit2);
        //massBattle(90000, unit1, unit2);
    }
    public static void battle(String p1, String p2) {
        BattleStage stage = new BattleStage(new Peasant(p1), new Peasant(p2));
        stage.display();

        while(stage.battleInProgress()) {
            stage.progress();
            stage.display();
        }
        stage.checkForWinner();
    }
    public static void battle(Unit p1, Unit p2) {
        BattleStage stage = new BattleStage(p1, p2);
        stage.display();
        while(stage.battleInProgress()) {
            stage.progress();
            stage.display();
        }
        stage.checkForWinner();
    }
    public static void massBattle(int battleCount, Unit type1, Unit type2) {
        int playerOneWins = 0;
        int playerTwoWins = 0;
        for(int i=0; i<battleCount; ++i) {
            Unit p1 = new Peasant();
            Unit p2 = new Peasant();
            try {
                p1 = type1.getClass().getDeclaredConstructor().newInstance();
                p2 = type2.getClass().getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | 
                     NoSuchMethodException | InvocationTargetException |
                     IllegalArgumentException | SecurityException e) {
                e.printStackTrace();
            }
            int result = trial(p1, p2);
            if(result == 1) ++playerOneWins;
            if(result == 2) ++playerTwoWins;
        }
        double one = ((double)playerOneWins/(double)battleCount)*100;
        double two = ((double)playerTwoWins/(double)battleCount)*100;
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println("P1:"+playerOneWins+"("+df.format(one)+")"+"P2:"+playerTwoWins+"("+df.format(two)+")");
    }
    public static int trial(Unit p1, Unit p2) {
        BattleStage stage = new BattleStage(p1, p2);
        doBattle(stage);
        return stage.getWinner();
    }
    public static void doBattle(BattleStage stage) {
        while(stage.battleInProgress()) {
            stage.progress();
        }
    }
}