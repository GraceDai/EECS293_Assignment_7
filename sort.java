import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;

public class Sort{
	ArrayList<Assignment> assignmentList = new ArrayList<Assignment>();
	ArrayList<Dependency> dependencyList = new ArrayList<Dependency>();
	
	Hashtable<Node, ArrayList<Node>> adjacentNodes = new Hashtable<Node, ArrayList<Node>>();
	ArrayList<Node> nodes = new ArrayList<Node>();
	LinkedList<Node> sortedNodes;
	/*
	 * All the code for setting up the graph
	 */
	public Sort(){}

	
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
	// Used to link begin end nodes in assignment specifically
	public boolean addEdge(Node node1, Node node2, int duration){
		return node1.addEdge(node2, duration);
	}
	
	//Helper method to link nodes (either begin or end) from 2 assignments
	public boolean addAssignmentEdge(Node assignment1, Node assignment2){
		return addEdge(assignment1, assignment2, 0);
	}
	
	
//	Function link assignment
//	input: list of assignment
//	output: void
	public void linkBeginEndNodes(){
//		for each assignment in the list of assignments
		for(Assignment assignment : assignmentList){
			//make new dependency with the begin node, end node and duration
			addEdge(assignment.begin, assignment.end, assignment.duration);
		}
	}

//	Function add dependency links: 
//	input: list of requirements 
//	output: void
	public void addDependencyLinks(){
//	pseudocode:
//		for each dependency in the list of dependencies:
		for(Dependency dependency : dependencyList){
			switch (dependency.getDependencyType()){
//			case 1: Begin-Begin
			case BEGINBEGIN:
//				Make new edge with the depenedency.fromAssignment begin node to the dependency.toAssignment begin node with duration 0.
				addAssignmentEdge(dependency.fromAssignment.begin, dependency.toAssignment.begin);
//			case 2: Begin-End
			case BEGINEND:
//				Make new edge with the dependency.fromAssignment end node to the dependency.toAssignment begin node with duration 0.
				addAssignmentEdge(dependency.fromAssignment.end, dependency.toAssignment.begin);
//			case 3: End-End
			case ENDEND:
//				Make new edge with the dependency.fromAssignment end node to the dependency.fromAssignment end node 
				addAssignmentEdge(dependency.fromAssignment.end, dependency.toAssignment.end);
//			default 4: End-Begin
			default: 
//				Make new edge with the dependency.toAssignment end node to the dependency.fromAssignment begin node
				addAssignmentEdge(dependency.toAssignment.end, dependency.fromAssignment.begin);
			}
		}
	}
	
	
	public void sort(){
		LinkedList<Node> SortedAssignmentNodes = new LinkedList<Node>();
		
		for(Node node: nodes){
			if(!node.permanentChecked()){
				visit(node);
			}
		}
	}
	
	public void visit(Node node) throws Exception{
		if(node.permanentChecked()){
			return;
		}
		else if(node.temporaryChecked()){
			throw new Exception("The assignments are cyclical")
		}
		else{
			node.setTemporaryChecked(true);
			for(Node adjacentNode : adjacentNodes.get(node)){
				visit(adjacentNode);
			}
			node.setPermanentChecked(true);
		}
	}

	
	

//	Function check duration: 
//	for each node in SortedAssignments 
//		check each edge and get maximum 
//		set node's integer to be the maximum
//
//	get first node in sorted assignments and get integer duration	
//	

	
	
}
