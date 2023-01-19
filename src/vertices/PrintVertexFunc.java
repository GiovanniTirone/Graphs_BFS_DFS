package vertices;


import java.util.function.Function;

public class PrintVertexFunc implements Function<Vertex,Void> {
    @Override
    public Void apply(Vertex vertex) {
        System.out.println(vertex.getName());
        return null;
    }
}
