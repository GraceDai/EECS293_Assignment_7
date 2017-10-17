
public class Assignment {
	//Field
	public int duration;
	public Node begin;
	public Node end;
	
	//Constructor
	public Assignment(int duration, Node begin, Node end){
		this.duration = duration;
		this.begin = begin;
		this.end = end;
	}
	
	//Getters
	public int getDuration(){
		return duration;
	}
	
	public Node getBeginNode(){
		return begin;
	}
	
	public Node getEndNode(){
		return end;
	}
	
	//Method to add assignments 
	
}
