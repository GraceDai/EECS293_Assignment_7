import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;

public class Sort{	
	Hashtable<Node, ArrayList<Node>> adjacentNodes = new Hashtable<Node, ArrayList<Node>>();
	ArrayList<Node> nodes = new ArrayList<Node>();
	LinkedList<Node> sortedAssignmentNodes;
	/*
	 * All the code for setting up the graph
	 */
	public Sort(){}

	//Place vertexes onto graph 
	public void addVertex(Node vertex){
		if(adjacentNodes.contains(vertex)){
			return; 
		}
		else{
			adjacentNodes.put(vertex, new ArrayList<Node>());
			nodes.add(vertex);
		}
	}

	//Helper method to link nodes
	//By itself used to link the begin and end nodes of one assignment
	public boolean addEdge(Node node1, Node node2, int duration){
		return node1.addEdge(node2, duration);
	}
	
	//Helper method to link nodes (either begin or end) from two assignments
	public boolean addAssignmentEdge(Node assignment1, Node assignment2){
		return addEdge(assignment1, assignment2, 0);
	}
	
	
	//Procedure: link assignment
	//input: list of assignment
	//output: void
	//method links the begin and end nodes of an assignment with it's duration
	public void connectBeginEndNodes(ArrayList<Assignment> assignmentList){
		//for each assignment in the list of assignments
		for(Assignment assignment : assignmentList){
			//make new dependency with the begin node, end node and duration
			addEdge(assignment.begin, assignment.end, assignment.duration);
		}
	}

	//Function add dependency links: 
	//input: list of requirements 
	//output: void
	//method connects the beginning and end nodes based off of dependency requirement
	public void addDependencyConnections(ArrayList<Dependency> dependencyList){
		//for each dependency in the list of dependencies:
		for(Dependency dependency : dependencyList){
			switch (dependency.getDependencyType()){
			//case 1: Begin-Begin
			case BEGINBEGIN:
				//Make new edge with the depenedency.fromAssignment begin node to the dependency.toAssignment begin node with duration 0.
				addAssignmentEdge(dependency.fromAssignment.begin, dependency.toAssignment.begin);
			//case 2: Begin-End
			case BEGINEND:
				//Make new edge with the dependency.fromAssignment end node to the dependency.toAssignment begin node with duration 0.
				addAssignmentEdge(dependency.fromAssignment.end, dependency.toAssignment.begin);
			//case 3: End-End
			case ENDEND:
				//Make new edge with the dependency.fromAssignment end node to the dependency.fromAssignment end node 
				addAssignmentEdge(dependency.fromAssignment.end, dependency.toAssignment.end);
			//default 4: End-Begin
			default: 
				//Make new edge with the dependency.toAssignment end node to the dependency.fromAssignment begin node
				addAssignmentEdge(dependency.toAssignment.end, dependency.fromAssignment.begin);
			}
		}
	}
	
	//Topological sort methods
	//input: nodes list (not assignment list)
	//output: sorted list of assignments 
	public void sort() throws Exception{
		//for each node in my list of nodes
		for(Node node: nodes){
			//check boolean checked on node n
			if(!node.isPermanentChecked()){
				//if not checked, use visit
				visit(node);
			}
		}
	}
	
	//visit method for the topological sort
	public void visit(Node node) throws Exception{
		//if n is permanentChecked then return
		if(node.isPermanentChecked()){
			return;
		}
		//if n  is temporaryChecked then stop 
		else if(node.isTemporaryChecked()){
			//print that is cyclical (used an exception to print)  
			throw new Exception("The assignments are cyclical");
		}
		//if not marked then 
		else{
			//temporaryCheck n
			node.setTemporaryChecked(true);
			//for each node n is linked to 
			for(Node adjacentNode : adjacentNodes.get(node)){
				//visit(n)
				visit(adjacentNode);
			}
			//permanentCheck n 
			node.setPermanentChecked(true);
			//add n to the front of SortedAssignments 
			sortedAssignmentNodes.addFirst(node);
		}
		
	}

	
	

	//Function check duration: 		
	public int findMaxDuration(){
		setDuration();
		//Get first node in sorted assignments and get integer duration
		int maxOverallDuration = sortedAssignmentNodes.get(0).getEndTime(); 
		//Find max duration in the list of sorted assignments
		for(Node node : sortedAssignmentNodes){
			int duration = node.getEndTime();
			if(duration > maxOverallDuration){
				maxOverallDuration = duration;
			}
		}
		
		
		return maxOverallDuration;
	}


	
	public void setDuration(){
		//for each node in SortedAssignments 
		for(Node node : sortedAssignmentNodes){
			int maxEdgeDuration = 0;
			//check each edge and get maximum
			for(Edge edge : node.getEdges()){
				if(edge.getDuration() > maxEdgeDuration){
					//set node's integer to be the maximum
					maxEdgeDuration = edge.getDuration();
					node.setEndTime(maxEdgeDuration);
				}
			}
		}

	}
	
	
}
