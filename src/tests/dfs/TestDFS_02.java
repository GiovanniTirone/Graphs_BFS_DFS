package tests.dfs;

import dfs.DFS;
import vertices.PrintVertexFunc;
import vertices.Vertex;

import java.util.Arrays;

public class TestDFS_02 {
    public static void main(String[] args) {


        Vertex a = new Vertex("a");
        Vertex b = new Vertex("b");
        Vertex c = new Vertex("c");
        Vertex d = new Vertex("d");
        Vertex e = new Vertex("e");

        a.addAdjacentVertices(Arrays.asList(b,c));
        c.addAdjacentVertex(d);
        d.addAdjacentVertex(e);

        DFS dfs = new DFS();
        dfs.visitGraph(a,new PrintVertexFunc());
    }
}
