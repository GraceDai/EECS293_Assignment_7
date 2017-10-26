
public class Assignment {
	//Field
	public int duration;
	public Node begin;
	public Node end;
	
	//Constructor
	public Assignment(int duration){
		this.duration = duration;
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
	
}
