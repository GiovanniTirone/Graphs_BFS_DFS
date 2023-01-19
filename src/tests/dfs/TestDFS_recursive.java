package tests.dfs;

import dfs.DFS_recursive;
import vertices.PrintVertexFunc;
import vertices.Vertex;

import java.util.Arrays;

public class TestDFS_recursive {

    public static void main(String[] args) {


        Vertex a = new Vertex("a");
        Vertex b = new Vertex("b");
        Vertex c = new Vertex("c");
        Vertex d = new Vertex("d");
        Vertex e = new Vertex("e");

        a.addAdjacentVertices(Arrays.asList(b,c));
        c.addAdjacentVertex(d);
        d.addAdjacentVertex(e);

        DFS_recursive dfs = new DFS_recursive();
        dfs.visitGraph(a,new PrintVertexFunc());
    }
}
