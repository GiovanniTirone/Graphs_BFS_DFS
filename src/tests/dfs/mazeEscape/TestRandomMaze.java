package tests.dfs.mazeEscape;

import dfs.mazeEscape.Maze;

public class TestRandomMaze {

    public static void main(String[] args) {
        Maze maze = new Maze(6);
        maze.printMaze();
        maze.generateRandomWalls(18);
        maze.printMaze();
    }

}
