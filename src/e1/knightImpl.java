package e1;


public class knightImpl extends AbstractPiece  {
    private final int guiSize;
    /**
     * @param guiSize
     */
    public knightImpl(int guiSize) {
        super(guiSize);
        this.guiSize = guiSize;
    }

    public boolean checkMovement(Pair<Integer, Integer> newPosition) {
        int x = newPosition.getX() - getPosition().getX();
		int y = newPosition.getY() - getPosition().getY();
        if (newPosition.getX() < 0 || newPosition.getY() < 0 || newPosition.getX() >= this.guiSize || newPosition.getY() >= this.guiSize) {
			throw new IndexOutOfBoundsException();
		} 
        return (x!=0 && y!=0 && Math.abs(x)+Math.abs(y)==3);
    }


}
