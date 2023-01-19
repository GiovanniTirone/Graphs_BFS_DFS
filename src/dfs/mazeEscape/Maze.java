package dfs.mazeEscape;

import utility.Utility;

import java.util.*;
import java.util.stream.Collectors;

public class Maze {

    int size;
    private Tile start;

    private Tile end;

    private Tile [][] maze;

    public Maze (int numberOfRowCol){
        this.size = numberOfRowCol;
        this.maze = new Tile[numberOfRowCol][numberOfRowCol];
        for(int i=0; i<numberOfRowCol; i++){
            for(int j=0; j<numberOfRowCol; j++){
                this.maze[i][j] = new Tile(TileType.FREE,i,j);
            }
        }
    }

    public Tile getStart() {
        return start;
    }

    public Tile getEnd() {
        return end;
    }

    public Set<Tile> getNeighborsOfTile(int i, int j){
        Set <Tile> adjacetTiles = new HashSet<>();
        for(int k=-1; k<2; k++){
            for(int s=-1; s<2; s++){
                if(k==0 && s==0) continue;
                if(checkCoordinatesInRange(i+k,j+s))
                    adjacetTiles.add(this.maze[i+k][j+s]);
            }
        }
        return adjacetTiles;
    }


    protected Set<Tile> getNeighborsOfTile (Tile tile){
        int i = tile.getRow();
        int j = tile.getCol();
        return getNeighborsOfTile(i,j);
    }

    public Set<Tile> getNeighborsOfTileWithoutDiags (int i, int j){
        Set <Tile> adjacetTiles = new HashSet<>();
        for(int k=-1; k<2; k+=2){
            for(int s=0; s<2; s++){
                if(checkCoordinatesInRange(i+(1-s)*k,j+s*k))
                    adjacetTiles.add(this.maze[i+(1-s)*k][j+s*k]);
            }
        }
        return adjacetTiles;
    }

    protected Set<Tile> getNeighborsOfTileWithoutDiags (Tile tile){
        int i = tile.getRow();
        int j = tile.getCol();
        return getNeighborsOfTileWithoutDiags(i,j);
    }

    public void setWalls(int[][] tilesCoordinates){
        for(int i=0; i<this.size; i++){
            for(int j=0; j<this.size; j++) {
                int [] target = new int[]{i,j};
                if(Arrays.asList(tilesCoordinates).stream()
                        .anyMatch(coord -> coord[0]==target[0]&&coord[1]==target[1])){
                    setWall(i,j);
                }
            }
        }
    }

    public void setWall (int i, int j) {
        this.maze[i][j].setType(TileType.WALL);
    }

    public void setStart (int i, int j){
        this.maze[i][j].setType(TileType.START);
        this.start = this.maze[i][j];
    }

    public void setEnd (int i,int j){
        this.maze[i][j].setType(TileType.END);
        this.end = this.maze[i][j];
    }

    public void setTileType (int i, int j, TileType type) {
        this.maze[i][j].setType(type);
    }

    public void setTileIsVisited (int i, int j, boolean visited) {
        this.maze[i][j].setVisited(visited);
    }

    public boolean tileIsVisited (int i,int j){
        return this.maze[i][j].isVisited();
    }

    private boolean checkCoordinatesInRange (int i,int j){
        return i>=0 && j>=0 && i<this.size && j<this.size;
    }


    public void generateRandomWalls (int minNumberOfFreeTiles) {
        List<Tile> possibleTiles = new ArrayList<>(Arrays.stream(this.maze).flatMap(Arrays::stream).toList());
        Random random = new Random();
        while(this.size*this.size - possibleTiles.size() < minNumberOfFreeTiles) {
            Tile wall = possibleTiles.get(random.nextInt(possibleTiles.size()));
            possibleTiles.removeAll(addWallWith90angles(wall));
        }
    }

    private Set<Tile> addWallWith90angles (Tile wall){
        Random random = new Random();
        wall.setType(TileType.WALL);
        Set walls = new HashSet<>();
        walls.add(wall);
        int i = wall.getRow();
        int j = wall.getCol();
        for(int k=-1;k<2;k+=2){
            if(checkCoordinatesInRange(i+k,j+k) && this.maze[i+k][j+k].getType()==TileType.WALL){
                boolean just90angle = false;
                for(int s=0; s<2; s++ ){
                    if(this.maze[i+(1-s)*k][j+s*k].getType()==TileType.WALL){
                        just90angle = true;
                        break;
                    }
                }
                if(!just90angle){
                    int s = random.nextInt(2);
                    walls.addAll(addWallWith90angles(this.maze[i+(1-s)*k][j+s*k]));
                }
            }
        }
        return walls;
    }


    public void printMaze () {
        int n = this.maze.length;
        String [][] typeTile = new String[n][n];
        for(int i=0; i<n; i++) {
            for (int j = 0; j<n; j++) {
                typeTile[i][j] = this.maze[i][j].getType().name().substring(0,1);
            }
        }
        System.out.println(Utility.formatStringMatrix(typeTile));
    }

}



  /*
    public Tile[][] generateRandomMaze (int numberOfRows_cols) {
        Random random = new Random();
        this.maze = new Tile[numberOfRows_cols][numberOfRows_cols];
        int startRow = random.nextInt(numberOfRows_cols);
        int startCol = random.nextInt(numberOfRows_cols);
        int endRow;
        int endCol;
        do{
            endRow = random.nextInt(numberOfRows_cols);
            endCol = random.nextInt(numberOfRows_cols);
        }
        while(startRow == endRow && startCol == endCol);

        for(int i=0; i<numberOfRows_cols; i++){
            for (int j=0; j<numberOfRows_cols; j++){
                if((i==startRow && j==startCol)) {
                    Tile newStart = new Tile(TileType.START,i,j);
                    this.maze[i][j] = newStart;
                    this.start = newStart;
                    continue;
                }
                if((i==endRow && j==endCol)) {
                    Tile newEnd = new Tile(TileType.END,i,j);
                    this.maze[i][j] = newEnd;
                    this.end = newEnd;
                    continue;
                }
                int k = random.nextInt(2);
                this.maze[i][j] = new Tile(TileType.values()[k],i,j);
            }
        }
        return this.maze;
    }
    */

