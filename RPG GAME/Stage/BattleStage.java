package Stage;
import Character.*;

public class BattleStage {

    Unit playerOne;
    Unit playerTwo;
    public String battleLog = "Ready to battle!";
    String turnArrow = " ";

    public BattleStage(Unit p1, Unit p2) {
        playerOne = p1; playerTwo = p2;
        playerOne.randomDelay(); playerTwo.randomDelay();
    }
    public void addP1(Unit unit) {
        playerOne = unit;
    }
    public boolean battleInProgress() {
        return !playerOne.isDead() && !playerTwo.isDead();
    }
    public void advanceTime() {
        while(!checkUnitReady()) {
            playerOne.advance();
            playerTwo.advance();
        }
    }
    boolean checkUnitReady() {
        return playerOne.isReady() || playerTwo.isReady();
    }
    Unit getReadyUnit() {
        //return (playerOne.delay < playerTwo.delay) ? playerOne : playerTwo;
        if(playerOne.delay <= playerTwo.delay) return playerOne; else return playerTwo;
    }
    Unit getTargetUnit() {
        //return playerOne.delay > playerTwo.delay ? playerOne : playerTwo;
        if(playerOne.delay > playerTwo.delay) return playerOne; else return playerTwo;
    }
    public void progress() {
        advanceTime();
        turnArrow = getReadyUnit() == playerOne ? ">":"<";
        getReadyUnit().takeTurn(getTargetUnit(), this);
    }
    public void display() {
    System.out.println("["+String.format("%4d", playerOne.health)+"]"+turnArrow+"["+String.format("%4d", playerTwo.health)+"]"+battleLog);
    }
    public void checkForWinner() {
        if(playerOne.isDead() || playerTwo.isDead()) {
            String winner = !playerOne.isDead() ? playerOne.name:playerTwo.name;
            String loser = playerOne.isDead() ? playerOne.name:playerTwo.name;
            System.out.println(winner+" has defeated "+loser+"!");
        }
    }
    public int getWinner() {
        if(battleInProgress()) return 0;
        return (playerTwo.isDead() ? 1:2);
    }
}