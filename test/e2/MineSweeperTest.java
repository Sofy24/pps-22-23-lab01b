package e2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

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
        this.logics.getMines().stream().forEach(mine -> assertTrue(this.logics.isThereMine(mine)));;
    }

    @Test
    public void flagsTest(){
        Cell cell = new CellImpl(new Random().nextInt(size), new Random().nextInt(size));
        assertFalse(this.logics.getFlagList().contains(cell));
        this.logics.changeFlagList(cell);
        assertTrue(this.logics.getFlagList().contains(cell));
        this.logics.changeFlagList(cell);
        assertFalse(this.logics.getFlagList().contains(cell));
    }

    @Test
    public void nearbyMinesTest(){
        Cell cell = new CellImpl(new Random().nextInt(size), new Random().nextInt(size));
        this.logics.setLocalNumberOfMines(cell);
        int numberOfMines = (int) this.logics.getMines().stream().filter(mine -> (mine.getX() == (cell.getX() + 1) || mine.getX() == (cell.getX() - 1)
                                        || mine.getX() == cell.getX()) && ( mine.getY() == cell.getY()
                                        || mine.getY() == (cell.getY() - 1) || mine.getY() == (cell.getY() + 1)))
                                       .count();
        System.out.println("mines:" + this.logics.getMines());
        System.out.println("position" + cell);
        System.out.println(numberOfMines + "io vs f(x)" + this.logics.getLocalNumberOfMines());
        assertEquals(numberOfMines, this.logics.getLocalNumberOfMines().size());
    }


}
