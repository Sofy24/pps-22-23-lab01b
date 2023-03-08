package e2;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

public class MineSweeperTest {
    private final int size = 7;
    private final int numberOfMines = 4;
    private Logics logics = new LogicsImpl(this.size, this.numberOfMines);


    @Test
    public void checkIfThereAreEnoughMines(){
        assertTrue(this.logics.getMines().size() == this.numberOfMines);
    }

    @Test
    public void defeatTest(){
        assertTrue(this.logics.isThereMine(this.logics.getMines().get(0)));
    }
}
