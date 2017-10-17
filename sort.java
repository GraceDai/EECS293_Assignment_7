import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;

public class Sort<T>{
	
	Hashtable<Node, ArrayList<Node>> adjacentNodes = new Hashtable<Node, ArrayList<Node>>();
	ArrayList<Node> nodes = new ArrayList<Node>();
	LinkedList<Node> sortedNodes;
	/*
	 * All the code for setting up the graph
	 */
	public Sort(){}
	
	public boolean addVertex(Node vertex){
		if(adjacenctNodes.contains(vertex)){
			return false;
		}
		else {
			adjacencyList.put(vertex, new Node<>(vertex));
			return true;
		}
	}

	
	//Helper method to link nodes
	// Used to link begin end nodes in assignment specifically
	public boolean addEdge(Node<T> node1, Node<T> node2, int duration){
		Node<T> begin = getNode(node1);
		Node<T> end = getNode(node2);
		return node1.addEdge(node2, duration);
	}
	
	//Helper method to link nodes (either begin or end) from 2 assignments
	public boolean addAssignmentEdge(Node<T> assignment1, Node<T> assignment2){
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
		for(Dependency dependency : dependenciesList){
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
		
		
	}

	
	

//	Function check duration: 
//	for each node in SortedAssignments 
//		check each edge and get maximum 
//		set node's integer to be the maximum
//
//	get first node in sorted assignments and get integer duration	
//	

	
	
}
