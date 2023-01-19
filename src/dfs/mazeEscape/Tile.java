package dfs.mazeEscape;

public class Tile {

    private int row;

    private int col;

    private TileType type;

    private boolean visited;

    public Tile (TileType type,int row,int col) {
        this.row = row;
        this.col = col;
        this.type = type;
        this.visited = false;
    }

    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }


    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    @Override
    public String toString ( ) {
        return "(" + row + "," + col +") - type: " + type.name() + " - visited: " + visited;
    }
}
