package bfs;

import vertices.StatusVtx;
import vertices.Vertex;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Function;

public class BFS{

    public<Data> void visitGraph (Vertex root, Function<Vertex,Data> func){
        //marcare tutti i vertici come inesplorati
        root.setStatus(StatusVtx.OPEN);
        Queue<Vertex> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            Vertex currentVtx = queue.remove();
            currentVtx.setStatus(StatusVtx.CLOSED);
            func.apply(currentVtx);
            for(Vertex adjVtx : currentVtx.getAdjacentVertices()){
                if(adjVtx.getStatus() == StatusVtx.NOT_VISITED){
                    adjVtx.setStatus(StatusVtx.OPEN);
                    queue.add(adjVtx);
                    currentVtx.addChild(adjVtx);
                }
            }
        }
    }


}
