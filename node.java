import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Node {

	//Field
    private Node vertex;
    private ArrayList<Edge> edges;
    private Node parent;   
    private int endTime;
    private boolean temporaryCheck;
    private boolean permanentCheck;

    //Constructor
    public Node(Node vertex) {
        this.vertex = vertex;
        this.edges = new ArrayList<>();
    }

    //Gettesr and setters
    public Node getVertex() {
        return vertex;
    }
    
    public void setEndTime(int endTime){
    	this.endTime = endTime;
    }
    
    public int getEndTime(){
    	return endTime;
    }

    //Add edges to node
    public boolean addEdge(Node node, int weight) {
        if (hasEdge(node)) {
            return false;
        }
        Edge newEdge = new Edge(this, node, weight);
        return edges.add(newEdge);
    }

    //Remove edges from graph
    public boolean removeEdge(Node node) {
        Optional<Edge> optional = findEdge(node);
        if (optional.isPresent()) {
            return edges.remove(optional.get());
        }
        return false;
    }
    
    //Check if node has any edges 
    public boolean hasEdge(Node node) {
        return findEdge(node).isPresent();
    }

    //Find the edges of node
    private Optional<Edge> findEdge(Node node) {
        return edges.stream()
                .filter(edge -> edge.isBetween(this, node))
                .findFirst();
    }

    //List of edges 
    public List<Edge> getEdges() {
        return edges;
    }

    //Get how many edges there are
    public int getEdgeCount() {
        return edges.size();
    }

    //Get the parent of a node
    public Node getParent() {
        return parent;
    }

    //More getters setters for boolean check marks 
    public boolean isTemporaryChecked() {
        return temporaryCheck;
    }
    public boolean isPermanentChecked(){
    	return permanentCheck;
    }

    public void setTemporaryChecked(boolean temporaryCheck){
        this.temporaryCheck = temporaryCheck;
    }
    public void setPermanentChecked(boolean permanentCheck){
    	this.permanentCheck = permanentCheck;
    }

    //Set the node's parent to be 
    public void setParent(Node parent) {
        this.parent = parent;
    }
}
