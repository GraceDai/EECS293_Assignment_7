public class Edge {
	
	//Field
    private Node fromNode;
    private Node toNode;
    
    //The duration passed in from assignment 
    private int duration;

    //Constructor
    public Edge(Node fromNode, Node toNode, int duration) {
        this.fromNode = fromNode;
        this.toNode = toNode;
        this.duration = duration;
    }

    //getters 
    public Node getFromNode() {
        return fromNode;
    }

    public Node getToNode() {
        return toNode;
    }
    
    public int getDuration(){
    	return duration;
    }
}