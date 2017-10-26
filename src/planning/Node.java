import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Node {

	//Field
    private Node vertex;
    private ArrayList<Edge> incomingEdges;
    private ArrayList<Edge> outgoingEdges;
    private Node parent;   
    private int endTime;
    private boolean temporaryCheck;
    private boolean permanentCheck;
    
    

    //Constructor
    public Node() {
        this.incomingEdges = new ArrayList<>();
        this.outgoingEdges = new ArrayList<>();
    }

    //Getters and setters
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
    public boolean addIncomingEdge(Edge edge) {
        return incomingEdges.add(edge);
    }
    
    public boolean addOutgoingEdge(Edge edge) {
        return outgoingEdges.add(edge);
    }


    //List of edges 
    public List<Edge> getIncomingEdges(){
        return incomingEdges;
    }
    
    public List<Edge> getOutgoingEdges(){
    	return outgoingEdges;
    }


    //More getters setters for boolean check marks 
    public boolean isVisiting() {
        return temporaryCheck;
    }
    public boolean isVisited(){
    	return permanentCheck;
    }

    public void setVisiting(boolean temporaryCheck){
        this.temporaryCheck = temporaryCheck;
    }
    public void setVisited(boolean permanentCheck){
    	this.permanentCheck = permanentCheck;
    }

}
