
public class Dependency {
	//Int duration-- was in my pseudocode but would've been repeated code here so I commented it out
	
	//Enum for the type of dependency 
	public enum DependencyType {
		BEGINBEGIN{
			public void addDependencyEdge(Dependency dependency){
				Sort.addAssignmentEdge(dependency.fromAssignment.begin, dependency.toAssignment.begin);
			}
		}, BEGINEND{
			public void addDependencyEdge(Dependency dependency){
				Sort.addAssignmentEdge(dependency.fromAssignment.begin, dependency.toAssignment.end);
			}
		}, ENDBEGIN{
			public void addDependencyEdge(Dependency dependency){
				Sort.addAssignmentEdge(dependency.fromAssignment.end, dependency.toAssignment.begin);
			}
		}, ENDEND{
			public void addDependencyEdge(Dependency dependency){
				Sort.addAssignmentEdge(dependency.fromAssignment.end, dependency.toAssignment.end);
			}
		};
		
		public abstract void addDependencyEdge(Dependency dependency);
		
	}
	
	//Field
	public Assignment fromAssignment, toAssignment;
	public final DependencyType dependencyType;
	public int duration; //This duration was unnecessary and I did not catch that in my pseudocode
	
	
	//Constructor
	public Dependency(Assignment fromAssignment, DependencyType dependencyType, Assignment toAssignment){
		this.fromAssignment = fromAssignment;
		this.dependencyType = dependencyType;
		this.toAssignment = toAssignment;
	}
	
	//Getter
	public DependencyType getDependencyType(){
		return dependencyType;
	}

}
