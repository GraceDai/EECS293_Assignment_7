
public class Dependency {
	//Enum for the type of dependency 
	public enum DependencyType {
		BEGINBEGIN, BEGINEND, ENDBEGIN, ENDEND
	}
	
	//Field
	public int duration;
	public Assignment fromAssignment, toAssignment;
	public final DependencyType dependencyType;
	
	
	//Constructor
	public Dependency(int duration, Assignment fromAssignment, Assignment toAssignment, DependencyType dependencyType){
		this.duration = duration;
		this.fromAssignment = fromAssignment;
		this.toAssignment = toAssignment;
		this.dependencyType = dependencyType;
	}
	
	//Getter
	public DependencyType getDependencyType(){
		return dependencyType;
	}
	
	//Method to add dependencies
	
}
