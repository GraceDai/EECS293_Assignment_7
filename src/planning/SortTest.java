import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class SortTest {
	Sort sort = new Sort();
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	/*
	 * scheduleTasks test
	 */
	//Assignment list and dependency lists are not empty
	@Test
	public void test_existent_assignments_and_dependencies() throws Exception{		
		Assignment assignment1 = new Assignment(1);
		Assignment assignment2 = new Assignment(4);
		Assignment assignment3 = new Assignment(3);
		
		List<Assignment> assignmentList = new ArrayList<Assignment>();
		assignmentList.add(assignment1);
		assignmentList.add(assignment2);
		assignmentList.add(assignment3);
		
		Dependency a1toa3 = new Dependency(assignment1, Dependency.DependencyType.ENDBEGIN, assignment2);
		Dependency a2toa3 = new Dependency(assignment2, Dependency.DependencyType.ENDEND, assignment3);
		
		List<Dependency> dependencyList = new ArrayList<Dependency>();
		dependencyList.add(a1toa3);
		dependencyList.add(a2toa3);
		
		int duration = sort.scheduleTasks(assignmentList, dependencyList);
		
		assertEquals(5, duration);
	}
	
	//Assignment list and dependency are empty
	@Test 
	public void test_nonexistent_assignments_and_dependencies() throws Exception{
		List<Assignment> assignmentList = new ArrayList<Assignment>();
		List<Dependency> dependencyList = new ArrayList<Dependency>();

		int duration = sort.scheduleTasks(assignmentList, dependencyList);
		
		assertEquals(0, duration);
	}
	
	//Assignment list is not empty and dependency list is empty
	@Test 
	public void test_existent_assignments_and_nonexistent_dependencies() throws Exception{
		List<Assignment> assignmentList = new ArrayList<Assignment>();
		List<Dependency> dependencyList = new ArrayList<Dependency>();
		Assignment assignment1 = new Assignment(1);
		Assignment assignment2 = new Assignment(4);
		Assignment assignment3 = new Assignment(3);
		assignmentList.add(assignment1);
		assignmentList.add(assignment2);
		assignmentList.add(assignment3);
		
		int duration = sort.scheduleTasks(assignmentList, dependencyList);
		
		assertEquals(4, duration);
	}
	
	//Assignment list is not empty and dependency list 
	@Test
	public void test_nonexistent_assignments_and_existent_dependencies() throws Exception{
		List<Assignment> assignmentList = new ArrayList<Assignment>();
		List<Dependency> dependencyList = new ArrayList<Dependency>();
		
		Assignment assignment1 = new Assignment(1);
		Assignment assignment2 = new Assignment(4);
		Assignment assignment3 = new Assignment(3);
		
		Dependency a1toa3 = new Dependency(assignment1, Dependency.DependencyType.ENDBEGIN, assignment2);
		Dependency a2toa3 = new Dependency(assignment2, Dependency.DependencyType.ENDEND, assignment3);
		
		int duration = sort.scheduleTasks(assignmentList, dependencyList);
		
		assertEquals(0, duration);
	}

	/*
	 * connectBeginEndNodes tests
	 */
	//Assignment list is empty 
	@Test 
	public void test_empty_assignmentList(){	
		List<Assignment> assignmentList = new ArrayList<Assignment>();
	
		sort.connectBeginEndNodes(assignmentList);
	}
	
	//Assignment list is not empty
	/*
	 * visit 
	 */
	
	/*
	 * addEdge 
	 */
	//test with real nodes
	@Test 
	public void test_real_nodes(){	
		Node fromNode = new Node();
		Node toNode = new Node();
		
		sort.addEdge(fromNode, toNode, 3);
		
		assertEquals(fromNode.getOutgoingEdges().get(0), toNode.getIncomingEdges().get(0));
	}
	
	//test with null nodes
	@Test 
	public void test_null_nodes(){
		Node fromNode = null;
		Node toNode = null;
		
		thrown.expect(NullPointerException.class);
		sort.addEdge(fromNode, toNode, 3);
	}
	
	//test with one node existing and one node null
	@Test
	public void test_null_and_not_null_nodes(){
		Node fromNode = new Node();
		Node toNode = null;
		
		thrown.expect(NullPointerException.class);
		sort.addEdge(fromNode, toNode, 3);
	}
	
	
	/*
	 * visit 
	 */
	
	//node is visited
	@Test 
	public void test_visited_node() throws Exception{
		Node node = new Node();
		node.setVisited(true);
		
		sort.visit(node);
		
		assertTrue(node.isVisited());
	}
	
	//node is visiting
	@Test 
	public void test_visiting_node() throws Exception{
		Node node = new Node();
		node.setVisiting(true);
		
		thrown.expect(Exception.class);
		sort.visit(node);
	}
	
	//node is not visited or visiting and edges list is not empty
	@Test
	public void test_has_not_visited_yet_and_has_edges() throws Exception{
		Node firstNode = new Node();
		Node secondNode = new Node();
		
		sort.addEdge(firstNode, secondNode, 0);
		
		sort.visit(firstNode);
		
		assertTrue(firstNode.isVisited());
		assertTrue(secondNode.isVisited());
	}
	
	//node is not visited or visiting and edges list is empty
	@Test
	public void test_has_not_visited_yet_and_no_edges() throws Exception{
		Node firstNode = new Node();
		Node secondNode = new Node();
		
		sort.visit(firstNode);
		
		assertTrue(firstNode.isVisited());
		assertFalse(secondNode.isVisited());
	}
	
	//node is null
	@Test
	public void test_node_is_null() throws Exception{
		Node firstNode = null;
		Node secondNode = new Node();
		
		thrown.expect(NullPointerException.class);
		sort.visit(firstNode);
	}
	
	/*
	 * findMaxDuration
	 */
	//Multiple nodes
	@Test
	public void test_when_there_are_nodes_inList() throws Exception{
		Node testNode = new Node();
		Node anotherNode = new Node();
		Node lastNode = new Node();
		
		sort.addEdge(testNode, anotherNode, 3);
		sort.addEdge(anotherNode, lastNode, 0);
		
		sort.getNodes().add(lastNode);
		sort.getNodes().add(anotherNode);
		sort.getNodes().add(testNode);
		
		sort.sort();
		
		int maxDuration = sort.findMaxDuration();
		
		
		assertEquals(3, maxDuration);
	}
	
	//One node
	@Test public void test_one_node_in_list() throws Exception{
		Node a = new Node();
		Node b = null;
		
		thrown.expect(NullPointerException.class);
		sort.addEdge(a, b, 3);
		
		sort.getNodes().add(a);
		sort.getNodes().add(b);
		
		sort.sort();
	
		int maxDuration = sort.findMaxDuration();
	}
	
	//No node
	@Test
	public void test_no_nodes_in_list(){
		int maxDuration = sort.findMaxDuration();
		
		assertEquals(0, maxDuration);		
	}
	
	/*
	 * setDuration
	 */
	//Test 1: sorted assignment nodes is empty and edges is empty 
	@Test
	public void test_empty_lists(){
		sort.setDuration();

		//WHAT DO I ASSERT HERE????
	}
	
	//Test 2: sorted assignment nodes has one node and edges is empty
	@Test
	public void test_one_node_and_no_edges(){
		Node a = new Node();
		
		sort.sortedAssignmentNodes().add(a);
		
		sort.setDuration();
		
		assertEquals(a.getEndTime(), 0);
	}
	
	//Test 3: sorted assignment nodes has multiple nodes and edges is empty
	@Test
	public void test_multiple_nodes_and_no_edges(){
		Node a = new Node();
		Node b = new Node();
		Node c = new Node();
		
		sort.sortedAssignmentNodes().add(a);
		sort.sortedAssignmentNodes().add(b);
		sort.sortedAssignmentNodes().add(c);
		
		sort.setDuration();
		
		assertEquals(a.getEndTime(), 0);
		assertEquals(b.getEndTime(), 0);
		assertEquals(c.getEndTime(), 0);
	}
	
	//Test 4: sorted assignment nodes is empty and edges has one edge
	/*
	 * HOW TO TEST IF I NEVER ENTER THE FOR LOOP 
	 */
	
	//Test 5: sorted assignment nodes has one node and edges has one edge
	@Test
	public void test_one_node_and_one_edge(){
		Node a = new Node();
		sort.sortedAssignmentNodes().add(a);
		Edge e = new Edge(a, null, 3);
		a.addIncomingEdge(e);
		sort.setDuration();
		
		assertEquals(a.getEndTime(), 3);
	}
	
	//Test 6: sorted assignment nodes has multiple nodes and edges has one edge for each node
	@Test
	public void test_multiple_nodes_but_one_edge(){
		Node a = new Node();
		Node b = new Node();
		Node c = new Node();
		
		sort.sortedAssignmentNodes().add(a);
		sort.sortedAssignmentNodes().add(b);
		sort.sortedAssignmentNodes().add(c);
		
		Edge ae = new Edge(a, b, 3);
		Edge be = new Edge(b, c, 5);
		Edge ca = new Edge(c, a, 4);
		
		a.addIncomingEdge(ca);
		b.addIncomingEdge(ae);
		c.addIncomingEdge(be);
		
		sort.setDuration();
		
		assertEquals(a.getEndTime(), 4);
		assertEquals(b.getEndTime(), 7);
		assertEquals(c.getEndTime(), 12);	
	}
	
	//Test 7: sorted assignment nodes is empty and edges has multiple edges
	@Test 
	public void test_no_assignmentNodes_and_multiple_edges(){
		/*
		 * HOW TO TEST IF THERE'S NO NODES
		 */
	}
	//Test 8: sorted assignment nodes has one node and edges has multiple edges
	@Test
	public void tet_one_node_and_multiple_edge_nodes(){
		Node a = new Node();
		sort.sortedAssignmentNodes().add(a);
		
		Edge ea = new Edge(a, null, 4);
		Edge eb = new Edge(a, null, 8);
		Edge ec = new Edge(a, null, 1);
		
		a.addIncomingEdge(ea);
		a.addIncomingEdge(eb);
		a.addIncomingEdge(ec);
		
		sort.setDuration();
		
		assertEquals(a.getEndTime(), 8);
	}
	//Test 9: sorted assignment nodes has multiple edges and edges has multiple edges.
	
	/*
	 * addAssignmentsToGraph
	 */
	//No assignments
	@Test
	public void test_no_assignments(){
		List<Assignment> assignmentList = new ArrayList<>();
		
		sort.addAssignmentstoGraph(assignmentList);
	
		assertTrue(sort.getNodes().isEmpty());
	}
	
	//One assignment
	@Test 
	public void test_one_assignment(){
		List<Assignment> assignmentList = new ArrayList<>(); 
		
		Assignment a = new Assignment(3);
	
		assignmentList.add(a);
		
		sort.addAssignmentstoGraph(assignmentList);
		
		//There should be two nodes for each assignment so for one assignment
		//there should only be 2 nodes in the list
		assertEquals(2, sort.getNodes().size());
	}
	
	//A couple assignments 
	@Test
	public void test_a_couple_assignments(){
		List<Assignment> assignmentList = new ArrayList<>();
		
		Assignment a = new Assignment(3);
		Assignment b = new Assignment(3);
		Assignment c = new Assignment(3);
		
		assignmentList.add(a);
		assignmentList.add(b);
		assignmentList.add(c);
		
		sort.addAssignmentstoGraph(assignmentList);
		
		assertEquals(6, sort.getNodes().size());
	}
	
	
	
}
