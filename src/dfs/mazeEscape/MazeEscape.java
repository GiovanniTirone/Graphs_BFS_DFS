package dfs.mazeEscape;

public class MazeEscape {

    private Maze maze;

    public MazeEscape (Maze maze) {
        this.maze = maze;
    }

    private void escapeRec (Tile currentTile) {
        currentTile.setVisited(true);
        System.out.println(currentTile);
        if(currentTile.getType() == TileType.END) return;
        for(Tile neighborTile : maze.getNeighborsOfTileWithoutDiags(currentTile)){
            if(neighborTile.getType() == TileType.WALL) continue;
            if(!neighborTile.isVisited()){
                escapeRec(neighborTile);
            }
        }
    }

    public void escape () {
        escapeRec(maze.getStart());
    }







}
