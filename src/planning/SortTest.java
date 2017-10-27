import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SortTest {
	/*
	 * scheduleTasks test
	 */
	//Assignment list and dependency lists are not empty
	@Test
	public void test_existent_assignments_and_dependencies() throws Exception{
		Sort sort = new Sort();
		
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
		Sort sort = new Sort();	
		List<Assignment> assignmentList = new ArrayList<Assignment>();
		List<Dependency> dependencyList = new ArrayList<Dependency>();

		int duration = sort.scheduleTasks(assignmentList, dependencyList);
		
		assertEquals(0, duration);
	}
	
	//Assignment list is not empty and dependency list is empty
	@Test 
	public void test_existent_assignments_and_nonexistent_dependencies() throws Exception{
		Sort sort = new Sort();	
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
		Sort sort = new Sort();	
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
	 * connectBeginEndNodes 
	 */
	//Assignment list is empty 
	@Test 
	public void test_empty_assignmentList(){
		
	}
}
