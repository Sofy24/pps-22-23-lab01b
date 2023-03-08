package e1;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class PieceTest {
    private static final Integer GUI_SIZE = 5;
    private Logics logics = new LogicsImpl(GUI_SIZE);
    private List<Pair<Integer, Integer>> knightPositions = List.of(new Pair<Integer,Integer>(1,2),
    new Pair<Integer,Integer>(2,1), new Pair<Integer,Integer>(-1,-2), new Pair<Integer,Integer>(-2,-1),
    new Pair<Integer,Integer>(1,-2), new Pair<Integer,Integer>(-1,2), new Pair<Integer,Integer>(-2,1),
    new Pair<Integer,Integer>(2,-1));

    @Test
    public void wrongKnightPositionTest(){
        Pair<Integer,Integer> invalidKnightPosition = new Pair<Integer,Integer>(this.logics.getKnight().getPosition().getX() + 1, this.logics.getKnight().getPosition().getY() + 1);
        assertThrows(IndexOutOfBoundsException.class, () -> this.logics.getKnight().checkMovement(new Pair<Integer,Integer>(GUI_SIZE, GUI_SIZE)));
        try {
            this.logics.getKnight().move(invalidKnightPosition);
        } catch (UnsupportedOperationException | IndexOutOfBoundsException e) {
            e.getMessage();
        }
        
    }

    @Test
    public void wrongPawnPositionTest(){
        Pair<Integer,Integer> invalidPawnPosition = new Pair<Integer,Integer>(this.logics.getPawn().getPosition().getX() + 1, this.logics.getPawn().getPosition().getY() + 1);
        assertThrows(IndexOutOfBoundsException.class, () -> this.logics.getPawn().checkMovement(new Pair<Integer,Integer>(GUI_SIZE, GUI_SIZE)));
        try {
            this.logics.getPawn().move(invalidPawnPosition);
        } catch (UnsupportedOperationException | IndexOutOfBoundsException e) {
            e.getMessage();
        }
    }


    private Pair<Integer,Integer> getNewPawnPosition(){
        int x = this.logics.getPawn().getPosition().getX();
        int y = this.logics.getPawn().getPosition().getY();
        return x + 1 >= GUI_SIZE ? new Pair<Integer,Integer>(x - 1, y) : new Pair<Integer,Integer>(x + 1, y);
    }

    @Test
    public void CorrectPawnPositionTest(){
        Pair<Integer, Integer> correctPawnPosition = getNewPawnPosition();
        this.logics.getPawn().move(correctPawnPosition);
        assertTrue(this.logics.getPawn().isPieceInThisPosition(correctPawnPosition));
    }


    private Pair<Integer,Integer> getNewKnightPosition(){
        int x = this.logics.getKnight().getPosition().getX();
        int y = this.logics.getKnight().getPosition().getY();
        return this.knightPositions.stream()
                                   .map(newPosition -> new Pair<>(newPosition.getX() + x, newPosition.getY() + y))
                                   .filter(newPosition -> newPosition.getX() >= 0 && newPosition.getY() >= 0)
                                   .filter(newPosition -> newPosition.getX() < GUI_SIZE && newPosition.getY() < GUI_SIZE)
                                   .limit(1)
                                   .collect(Collectors.toList()).get(0);
        
    }

    @Test
    public void CorrectKnightPositionTest(){
        Pair<Integer, Integer> correctKnightPosition = getNewKnightPosition();
        this.logics.getKnight().move(correctKnightPosition);
        assertTrue(this.logics.getKnight().isPieceInThisPosition(correctKnightPosition));
    }


}
