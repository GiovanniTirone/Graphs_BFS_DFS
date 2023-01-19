package tests.dfs.mazeEscape;

import dfs.mazeEscape.Maze;
import dfs.mazeEscape.MazeEscape;

public class TestEscape_01 {

    public static void main(String[] args) {

        Maze maze = new Maze(6);
        maze.setWalls(new int[][]{{0,4},{0,5},{1,0},{1,1},{2,1},{3,1},{3,2},{1,3},{1,4},{1,5},{3,4},{4,4},{4,5},{5,1},{5,5}});
        maze.setStart(0,0);
        maze.setEnd(2,0);
        maze.printMaze();

        //maze.getNeighborsOfTile(0,0).forEach(System.out::println);

        MazeEscape mazeEscape = new MazeEscape(maze);
        mazeEscape.escape();



    }


}
