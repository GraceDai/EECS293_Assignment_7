
public class Dependency {
	
	
	//Enum for the type of dependency 
	public enum DependencyType {
		BEGINBEGIN, BEGINEND, ENDBEGIN, ENDEND
	}
	
	//Field
	public Assignment fromAssignment, toAssignment;
	public final DependencyType dependencyType;
	public int duration; //This duration was unnecessary and I did not catch that in my pseudocode
	
	
	//Constructor
	public Dependency(Assignment fromAssignment, Assignment toAssignment, DependencyType dependencyType){
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
