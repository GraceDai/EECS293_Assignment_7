import java.util.List;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;
import java.util.ArrayList;

public class Sort{	
	//Need input of arrayList of assignments for method connectBeginEndNodes
	////ArrayList<Assignment> assignmentList
	//Need input of arrayList of dependenies for method addDependencyConnections
	

	List<Node> nodes = new ArrayList<Node>();
	
	List<Node> sortedAssignmentNodes;
	/*
	 * All the code for setting up the graph
	 */
	public Sort(){
		sortedAssignmentNodes = new ArrayList<>();
	}
	
	public int scheduleTasks(List<Assignment> assignmentList, List<Dependency> dependencyList) throws Exception{
		addAssignmentstoGraph(assignmentList);
		connectBeginEndNodes(assignmentList);
		addDependencyConnections(dependencyList);
		sort();
		int duration = findMaxDuration();
		
		return duration;
	}

	
	/*
	 * ACTUAL ALGORITHM
	 */
	
	//Procedure: link assignment
	//input: list of assignment
	//output: void
	//method links the begin and end nodes of an assignment with it's duration
	public void connectBeginEndNodes(List<Assignment> assignmentList){
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
	public void addDependencyConnections(List<Dependency> dependencyList){
		//for each dependency in the list of dependencies:
		for(Dependency dependency : dependencyList){
			dependency.getDependencyType().addDependencyEdge(dependency);
		}
	}
	
	
	
	//Topological sort methods
	//input: nodes list (not assignment list)
	//output: sorted list of assignments 
	public void sort() throws Exception{
		//for each node in my list of nodes
		for(Node node: nodes){
			//check boolean checked on node n
			if(!node.isVisited()){
				//if not checked, use visit
				visit(node);
			}
		}
	}
	
	//visit method for the topological sort
	public void visit(Node node) throws Exception{
		//if n is permanentChecked then return
		if(node.isVisited()){
			return;
		}
		//if n  is temporaryChecked then stop 
		else if(node.isVisiting()){
			//print that is cyclical (used an exception to print)  
			throw new Exception("The assignments are cyclical");
		}
		//if not marked then 
		else{
			//temporaryCheck n
			node.setVisiting(true);
			//for each node n is linked to 
			for(Edge adjacentOutgoingNode : node.getOutgoingEdges()){
				//visit(n)
				visit(adjacentOutgoingNode.getToNode());
			}
			//permanentCheck n 
			node.setVisited(true);
			//add n to the front of SortedAssignments 
			sortedAssignmentNodes.add(0, node);
		}
		
	}

	
	

	//Function check duration: 		
	public int findMaxDuration(){
		setDuration();
		//Get first node in sorted assignments and get integer duration
		int maxOverallDuration = 0; 
		//Find max duration in the list of sorted assignments
		for(Node node : sortedAssignmentNodes){
			int duration = node.getEndTime();
			maxOverallDuration = Math.max(duration, maxOverallDuration);
		}
		return maxOverallDuration;
	}


	
	public void setDuration(){
		//for each node in SortedAssignments 
		for(Node node : sortedAssignmentNodes){
			int maxEdgeDuration = 0;
			//check each edge and get maximum
			for(Edge edge : node.getIncomingEdges()){
				maxEdgeDuration = Math.max(edge.getFromNode().getEndTime() + edge.getDuration(), maxEdgeDuration);
			}
			
			node.setEndTime(maxEdgeDuration);
		}

	}

	/*
	 * Helper methods for my algorithm
	 */	
	//Helper method to link nodes
	//By itself used to link the begin and end nodes of one assignment
	public static void addEdge(Node node1, Node node2, int duration){
		Edge edge = new Edge(node1, node2, duration);
		
		node1.addOutgoingEdge(edge);	
		node2.addIncomingEdge(edge);
	}

	
	//Helper method to link nodes (either begin or end) from two assignments
	public static void addAssignmentEdge(Node fromAssignment, Node toAssignment){
		addEdge(fromAssignment, toAssignment, 0);
	}
	
	//Method to add nodes to the graph for all the nodes in the assignment list
	public void addAssignmentstoGraph(List<Assignment> assignmentList){
		for(Assignment assignment : assignmentList){
			nodes.add(assignment.begin);
			nodes.add(assignment.end);
		}
	}
	
}
