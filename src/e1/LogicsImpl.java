package e1;

public class LogicsImpl implements Logics {
	private final int size;
	private AbstractPiece pawnImpl;
	private AbstractPiece knightImpl;
	 
    public LogicsImpl(int size){
    	this.size = size;
		this.pawnImpl = new PawnImpl(size);
		this.knightImpl = new KnightImpl(size);

    }

	public LogicsImpl(int size, Pair<Integer, Integer> pawnPosition, Pair<Integer, Integer> knightPosition){
    	this.size = size;
		this.pawnImpl = new PawnImpl(size);
		this.knightImpl = new KnightImpl(size);
		this.knightImpl.setPosition(knightPosition);
		this.pawnImpl.setPosition(pawnPosition);
    }
    
	@Override
	public boolean hit(int row, int col) {
		if (row<0 || col<0 || row >= this.size || col >= this.size) {
			throw new IndexOutOfBoundsException();
		}
		// Below a compact way to express allowed moves for the knight
		int x = row-this.knightImpl.getPosition().getX();
		int y = col-this.knightImpl.getPosition().getY();
		if (x!=0 && y!=0 && Math.abs(x)+Math.abs(y)==3) {
			this.knightImpl.move(new Pair<>(row,col));
			return this.pawnImpl.getPosition().equals(this.knightImpl.getPosition());
		}
		return false;
	}

	@Override
	public boolean hasKnight(int row, int col) {
		return this.knightImpl.getPosition().equals(new Pair<>(row,col));
	}

	@Override
	public boolean hasPawn(int row, int col) {
		return this.pawnImpl.getPosition().equals(new Pair<>(row,col));
	}

	@Override
	public AbstractPiece getKnight() {
		return this.knightImpl;
	}

	@Override
	public AbstractPiece getPawn() {
		return this.pawnImpl;
	}
}
