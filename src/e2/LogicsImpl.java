package e2;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class LogicsImpl implements Logics {
    private final int size;
    private final int numberOfMines;
    private boolean didYouLose;
    private boolean areYouWinner;

    public LogicsImpl(int size, int numberOfMines) {
        this.size = size;
        this.numberOfMines = numberOfMines;
        this.didYouLose = false;
        this.areYouWinner = false;
    }


    @Override
    public List<Pair<Integer, Integer>> getMines() {
        List<Pair<Integer, Integer>> mines = new ArrayList<>();
		final Supplier<Integer> random = ()->new Random().nextInt(size);   
		do {
			mines.add(new Pair<>(random.get(), random.get()));
		} while (mines.stream().distinct().count() < this.numberOfMines);
        return mines;
    }


    @Override
    public boolean didYouLose() {
        return this.didYouLose;
    }


    @Override
    public boolean isThereMine(Pair<Integer, Integer> position) {
        return this.getMines().contains(position);
    }


    @Override
    public boolean areYouAWinner() {
        return this.areYouWinner;
    }

}
