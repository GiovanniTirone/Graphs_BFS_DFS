package vertices;

import java.util.*;

public class Vertex implements Comparable<Vertex>{

    private String name;

    private StatusVtx status ;

    private TreeSet<Vertex> adjacentVertices;

    private Vertex father;
    private TreeSet<Vertex> children;

    public Vertex (String name) {
        this.name = name;
        this.adjacentVertices = new TreeSet<>();
        this.children = new TreeSet<>();
        this.status = StatusVtx.NOT_VISITED;
    }

    public String getName() {
        return name;
    }

    public StatusVtx getStatus() {
        return status;
    }

    public void setStatus(StatusVtx status) {
        this.status = status;
    }

    public Set<Vertex> getAdjacentVertices() {
        return this.adjacentVertices;
    }

    public Set<Vertex> getChildren() {
        return children;
    }

    public Vertex getFather() {
        return father;
    }

    public void setFather (Vertex father) {
        if(this.father != null){
            this.father.removeChild(this);
        }
        this.father = father;
    }

    private void removeChild(Vertex vertex) {
        this.children.remove(vertex);
    }

    public void addAdjacentVertex (Vertex vertex){
        this.adjacentVertices.add(vertex);
    }

    public void addAdjacentVertices (Collection<Vertex> vertices){
        this.adjacentVertices.addAll(vertices);
    }

    public void addChild (Vertex vertex){
        this.children.add(vertex);
        vertex.setFather(this);
    }

    /*
    public void printTree ( ){
        recursePrint(this,"");
    }

    private void recursePrint(Vertex vertex, String indent) {
        System.out.println(indent+vertex.getName());
        for (Vertex child: vertex.getChildren())
            recursePrint(child, indent+"   ");
    }
    */


    @Override
    public int compareTo(Vertex v) {
        return this.name.compareTo(v.getName());
    }

    @Override
    public String toString() {
        return this.name;
    }

    public void printTree ( ) {
        System.out.println(generateGenericVerbose( "", false, false, new StringBuilder()));
    }

    private String generateGenericVerbose(String prefix, boolean isRightMost, boolean isLeftMost, StringBuilder sb) {
        int halfSize = (this.getChildren().size() + 1) / 2;
        List<Vertex> children = new ArrayList<>(this.getChildren());
        for (int i = children.size() - 1 ; i >= halfSize; i--) {
            Vertex child = children.get(i);
            child.generateGenericVerbose(
                    prefix + (isRightMost && !isLeftMost ? "    " : "│   "),
                    child.isRightMostChild() ? true : false,
                    child.isLeftMostChild()  ? true : false,
                    sb);
        }
        sb.append(prefix).
                append(isRightMost && isLeftMost ? "└── " : "").
                append(isRightMost && !isLeftMost  ? "┌── " : "").
                append(isLeftMost  && !isRightMost ? "└── " : "").
                append(!isRightMost && !isLeftMost ? "├── " : "").
                append(this.toString()).
                append("\n");
        for (int i = halfSize - 1; i >= 0; i--) {
            Vertex child = children.get(i);
            children.get(i).generateGenericVerbose(
                    prefix + (isLeftMost ? "    " : "│   "),
                    child.isRightMostChild() ? true : false,
                    child.isLeftMostChild()  ? true : false,
                    sb);
        }
        return sb.toString();
    }

    private boolean isRightMostChild () {
        int halfSize = (this.getChildren().size() + 1) / 2;
        int leftCount = 0;
        int rightCount = 0;
        int counter = 0;
        while (children.iterator().hasNext() && counter<children.size()) {
            if(counter<halfSize){
                leftCount = 1 + countChildren(children.iterator().next());
            }
            else{
                rightCount = 1 + countChildren(children.iterator().next());
            }
            counter++;
        }
        return rightCount > leftCount ? true : false;
    }

    private int countChildren (Vertex vertex) {
        int numberOfChidren = 0;
        if(vertex.getChildren().size() == 0){
            return 0;
        }
        for(Vertex child : vertex.getChildren()){
            numberOfChidren += 1;
            for(Vertex secondChild : child.getChildren()){
                numberOfChidren += child.countChildren(secondChild);
            }
        }
        return numberOfChidren;
    }

    private boolean isLeftMostChild () {
        return !isRightMostChild();
    }

}
