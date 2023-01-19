package dfs;

import vertices.StatusVtx;
import vertices.Vertex;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class DFS_recursive {

    public<Data> void visitGraph (Vertex root, Function<Vertex,Data> func){
        root.setStatus(StatusVtx.CLOSED);
        func.apply(root);
        for(Vertex adjVtx : root.getAdjacentVertices()){
            if(adjVtx.getStatus() == StatusVtx.NOT_VISITED){
                root.addChild(adjVtx);
                visitGraph(adjVtx,func);
            }
        }
    }

    private<Data> void visitGraphCyclePrivate (Vertex root, Function<Vertex,Data> func, List<Vertex> possibleCycle){
        root.setStatus(StatusVtx.OPEN);
        func.apply(root);
        for(Vertex adjVtx : root.getAdjacentVertices()){
            if(adjVtx.getStatus() == StatusVtx.NOT_VISITED){
                adjVtx.setStatus(StatusVtx.CLOSED);
                root.addChild(adjVtx);
                possibleCycle.add(adjVtx);
                visitGraphCyclePrivate(adjVtx,func,possibleCycle);
                possibleCycle.remove(adjVtx);
            }
            else if(adjVtx.getStatus() == StatusVtx.OPEN){
                StringBuilder cycle = new StringBuilder("");
                possibleCycle.forEach(v -> cycle.append(v + " --> "));
                System.out.println("There is a cycle : " + cycle + adjVtx);
                return;
            }
        }
        root.setStatus(StatusVtx.CLOSED);
    }

    public <Data> void visitGraphCycle (Vertex root, Function<Vertex,Data> func){
        List<Vertex> possibleCycle = new ArrayList<>();
        possibleCycle.add(root);
        visitGraphCyclePrivate(root,func,possibleCycle);
    }

}
