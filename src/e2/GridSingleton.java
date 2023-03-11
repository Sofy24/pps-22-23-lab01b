package e2;

import java.util.Set;

public final class GridSingleton {
    private static GridSingleton instance;
    public static Set<Cell> grid;

    private GridSingleton(Set<Cell> grid) {
        GridSingleton.grid = grid;
    }


    public static GridSingleton getInstance(Set<Cell> grid) {
        if (instance == null) {
            instance = new GridSingleton(grid);
        }
        return instance;
    }

    public static GridSingleton getInstance() throws NullPointerException{
        if (instance != null) {
            return instance;
        }
        throw new NullPointerException("The instance of the GridSingleton is null");
    }

    /**
     * @return the grid
     * @throws NullPointerException
     */
    public static Set<Cell> getGrid() throws NullPointerException{
        if (GridSingleton.grid == null) {
            throw new NullPointerException("The instance of the GridSingleton is null");
        }
        return GridSingleton.grid;
    }

    public static Cell getCell(int x, int y){
        System.out.print(x+","+y);
        System.out.print("grid singly"+grid);
        return grid.stream().filter(cell -> cell.getX() == x)
                    .filter(cell -> cell.getY() == y)
                    .limit(1).toList().get(0);
    }

}
