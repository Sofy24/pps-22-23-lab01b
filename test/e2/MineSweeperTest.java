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
        Pair<Integer, Integer> position = new Pair<>(new Random().nextInt(size), new Random().nextInt(size));
        assertFalse(this.logics.getFlagList().contains(position));
        this.logics.changeFlagList(position);
        assertTrue(this.logics.getFlagList().contains(position));
        this.logics.changeFlagList(position);
        assertFalse(this.logics.getFlagList().contains(position));
    }

    @Test
    public void nearbyMinesTest(){
        Pair<Integer, Integer> position = new Pair<>(new Random().nextInt(size), new Random().nextInt(size));
        this.logics.setLocalNumberOfMines(position);
        int numberOfMines = (int) this.logics.getMines().stream().filter(mine -> (mine.getX() == (position.getX() + 1) || mine.getX() == (position.getX() - 1)
                                        || mine.getX() == position.getX()) && ( mine.getY() == position.getY()
                                        || mine.getY() == (position.getY() - 1) || mine.getY() == (position.getY() + 1)))
                                       .count();
        System.out.println("mines:" + this.logics.getMines());
        System.out.println("position" + position);
        System.out.println(numberOfMines + "io vs f(x)" + this.logics.getLocalNumberOfMines());
        assertEquals(numberOfMines, this.logics.getLocalNumberOfMines().size());
    }


}
