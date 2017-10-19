import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Node {

    private Node vertex;

    private ArrayList<Edge> edges;

    private Node parent;
    
    private int endTime;

    private boolean temporaryCheck;
    private boolean permanentCheck;

    public Node(Node vertex) {
        this.vertex = vertex;
        this.edges = new ArrayList<>();
    }

    public Node vertex() {
        return vertex;
    }
    
    public void setEndTime(int endTime){
    	this.endTime = endTime;
    }
    
    public int getEndTime(){
    	return endTime;
    }

    public boolean addEdge(Node node, int weight) {
        if (hasEdge(node)) {
            return false;
        }
        Edge newEdge = new Edge(this, node, weight);
        return edges.add(newEdge);
    }

    public boolean removeEdge(Node node) {
        Optional<Edge> optional = findEdge(node);
        if (optional.isPresent()) {
            return edges.remove(optional.get());
        }
        return false;
    }

    public boolean hasEdge(Node node) {
        return findEdge(node).isPresent();
    }

    private Optional<Edge> findEdge(Node node) {
        return edges.stream()
                .filter(edge -> edge.isBetween(this, node))
                .findFirst();
    }

    public List<Edge> edges() {
        return edges;
    }

    public int getEdgeCount() {
        return edges.size();
    }

    public Node parent() {
        return parent;
    }

    public boolean temporaryChecked() {
        return temporaryCheck;
    }
    public boolean permanentChecked(){
    	return permanentCheck;
    }

    public void setTemporaryChecked(boolean temporaryCheck){
        this.temporaryCheck = temporaryCheck;
    }
    public void setPermanentChecked(boolean permanentCheck){
    	this.permanentCheck = permanentCheck;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}
