package e1;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.*;

public class LogicTest {
    private static final int GUI_SIZE = 5;
    private Logics logic;
    private Logics logicSecondConstructor;

    @Test
    void initHit(){
        Pair<Integer, Integer> pawnPosition = new Pair<>(1,0);
        Pair<Integer, Integer> knightPosition = new Pair<>(2,2);
        this.logicSecondConstructor = new LogicsImpl(GUI_SIZE, pawnPosition, knightPosition);
    }

    @Test
    void initPawnPosition(){
        Pair<Integer, Integer> pawnPosition = new Pair<>(4,4);
        Pair<Integer, Integer> knightPosition = new Pair<>(2,2);
        this.logicSecondConstructor = new LogicsImpl(GUI_SIZE, pawnPosition, knightPosition);
    }

    @Test
    void initKnightPosition(){
        Pair<Integer, Integer> pawnPosition = new Pair<>(3,2);
        Pair<Integer, Integer> knightPosition = new Pair<>(2,3);
        this.logicSecondConstructor = new LogicsImpl(GUI_SIZE, pawnPosition, knightPosition);
    }

    @BeforeEach
    void beforeEach(){
        this.logic = new LogicsImpl(GUI_SIZE);
    }

    @Test
    void WrongPositionTest() {
        assertThrows(IndexOutOfBoundsException.class, () -> this.logic.hit(GUI_SIZE, GUI_SIZE));
    }

    
    @Test
    void PawnPositionTest(){
        final int PawnXFirstTest = 1;
        final int PawnYFirstTest = 0;
        final int PawnXSecondTest = 4;
        final int PawnYSecondTest = 4;
        initHit();
        assertTrue(this.logicSecondConstructor.hasPawn(PawnXFirstTest, PawnYFirstTest));
        initPawnPosition();
        assertTrue(this.logicSecondConstructor.hasPawn(PawnXSecondTest, PawnYSecondTest));
    }

    @Test
    void KnightPositionTest(){
        final int KnightXFirstTest = 2;
        final int KnightYFirstTest = 2;
        final int KnightXSecondTest = 2;
        final int KnightYSecondTest = 3;
        initHit();
        assertTrue(this.logicSecondConstructor.hasKnight(KnightXFirstTest, KnightYFirstTest));
        initKnightPosition();
        assertTrue(this.logicSecondConstructor.hasKnight(KnightXSecondTest, KnightYSecondTest));
    }



    @Test
    void KightHitPawnTest() {
        final int newKnightX = 1;
        final int newKnightY = 0;
        final int oldKnightX = 2;
        final int oldKnightY = 2;
        initHit();
        assertTrue(this.logicSecondConstructor.hasKnight(oldKnightY, oldKnightX));
        assertTrue(this.logicSecondConstructor.hit(newKnightX, newKnightY));
    }

}
