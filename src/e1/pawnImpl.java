package e1;

public class PawnImpl extends AbstractPiece {
    private final int guiSize;

    /**
     * @param guiSize
     */
    public PawnImpl(int guiSize) {
        super(guiSize);
        this.guiSize = guiSize;
    }

    public boolean checkMovement(Pair<Integer, Integer> newPosition) {
        int x = newPosition.getX() - getPosition().getX();
		int y = newPosition.getY() - getPosition().getY();
        if (newPosition.getX() < 0 || newPosition.getY() < 0 || newPosition.getX() >= this.guiSize || newPosition.getY() >= this.guiSize) {
			throw new IndexOutOfBoundsException();
		} 
        return (x == 1 && y == 0);
    }

}
