package dfs;

import vertices.StatusVtx;
import vertices.Vertex;

import java.util.Stack;
import java.util.function.Function;

public class DFS {

    public<Data> void visitGraph (Vertex root, Function<Vertex,Data> func) {
        //maracare tutti i vertici come inesplorati
        root.setStatus(StatusVtx.OPEN);
        Stack<Vertex> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            Vertex currentVtx = stack.pop();
            if(currentVtx.getStatus() == StatusVtx.CLOSED) continue;
            currentVtx.setStatus(StatusVtx.CLOSED);
            func.apply(currentVtx);
            for(Vertex adjVtx : currentVtx.getAdjacentVertices()){
                if(adjVtx.getStatus() == StatusVtx.NOT_VISITED){
                    adjVtx.setStatus(StatusVtx.OPEN);
                    stack.push(adjVtx);
                    currentVtx.addChild(adjVtx);
                }
                else if(adjVtx.getStatus() == StatusVtx.OPEN){
                    stack.push(adjVtx);
                    currentVtx.addChild(adjVtx);
                }
            }
        }
    }

}
