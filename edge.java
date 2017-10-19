public class Edge {
	
	//Field
    private Node node1;
    private Node node2;
    private int duration;

    //Constructor
    public Edge(Node node1, Node node2, int duration) {
        this.node1 = node1;
        this.node2 = node2;
        this.duration = duration;
    }

    //getters 
    public Node fromNode() {
        return node1;
    }

    public Node toNode() {
        return node2;
    }

    public boolean isBetween(Node node1, Node node2) {
        return (this.node1 == node1 && this.node2 == node2);
    }
    
    public int getDuration(){
    	return duration;
    }
}