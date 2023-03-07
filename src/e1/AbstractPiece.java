package e1;

import java.util.Random;

public abstract class AbstractPiece {
    private final int guiSize;
	private Pair<Integer,Integer> piecePosition;
    private final Random random = new Random();


    public AbstractPiece(int guiSize) {
        this.guiSize = guiSize;
        piecePosition = this.randomEmptyPosition();
    }

    private final Pair<Integer,Integer> randomEmptyPosition(){
    	Pair<Integer,Integer> pos = new Pair<>(this.random.nextInt(this.guiSize),this.random.nextInt(this.guiSize));
    	// the recursive call below prevents clash with an existing pawn
        return piecePosition!=null && piecePosition.equals(pos) ? randomEmptyPosition() : pos;
    }

    public boolean isPieceInThisPosition(Pair<Integer, Integer> position) {
        return piecePosition.equals(position);
    }

    public void move(Pair<Integer, Integer> newPosition) {
        if (checkMovement(newPosition)){
            piecePosition = newPosition;
        } else {
            throw new UnsupportedOperationException("This movement is not allowed");
        }
    }
        
    abstract protected boolean checkMovement(Pair<Integer, Integer> newPosition);

    public Pair<Integer, Integer> getPosition() {
        return piecePosition;
    }

}
