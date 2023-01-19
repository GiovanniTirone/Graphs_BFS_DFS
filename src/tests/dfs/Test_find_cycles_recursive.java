package tests.dfs;

import dfs.DFS_recursive;
import vertices.PrintVertexFunc;
import vertices.Vertex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test_find_cycles_recursive {
    public static void main(String[] args) {
        Vertex a = new Vertex("a");
        Vertex b = new Vertex("b");
        Vertex c = new Vertex("c");
        Vertex d = new Vertex("d");
        Vertex e = new Vertex("e");
        Vertex f = new Vertex("f");

        a.addAdjacentVertex(e);
        a.addAdjacentVertex(c);
        e.addAdjacentVertex(f);
        c.addAdjacentVertex(b);
        c.addAdjacentVertex(d);
        f.addAdjacentVertex(a);

        List<Vertex> graph = new ArrayList<>(Arrays.asList(a,b,c,d,e,f));

        DFS_recursive dfs = new DFS_recursive();

        dfs.visitGraphCycle(f,new PrintVertexFunc());
        //dfs.visitGraph(f,new PrintVertexFunc());

    }


}
