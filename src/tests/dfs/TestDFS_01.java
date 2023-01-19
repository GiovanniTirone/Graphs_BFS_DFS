package tests.dfs;

import dfs.DFS;
import vertices.PrintVertexFunc;
import vertices.Vertex;

import java.util.Arrays;

public class TestDFS_01 {

    public static void main(String[] args) {

        Vertex a = new Vertex("a");
        Vertex b = new Vertex("b");
        Vertex c = new Vertex("c");
        Vertex d = new Vertex("d");
        Vertex e = new Vertex("e");
        Vertex f = new Vertex("f");
        Vertex g = new Vertex("g");



        a.addAdjacentVertices(Arrays.asList(b,c,d));
        b.addAdjacentVertices(Arrays.asList(a,c));
        d.addAdjacentVertices(Arrays.asList(a,c));
        c.addAdjacentVertices(Arrays.asList(a,b,d,e));
        e.addAdjacentVertices(Arrays.asList(c,f,g));
        f.addAdjacentVertices(Arrays.asList(e,g));
        g.addAdjacentVertices(Arrays.asList(e,f));

        DFS dfs = new DFS();

        dfs.visitGraph(b,new PrintVertexFunc());
        b.printTree();

    }

}
