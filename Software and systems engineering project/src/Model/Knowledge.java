package Model;
/*
 *  Une classe qui représente les connaissances
 */
public class Knowledge extends Component {

	
	private Visibility visibility;
	private Condition visibilityConditions;
	
	public Knowledge(String name, Condition visibilityConditions){
		this.name=name;
		this.visibility=Visibility.visible;
		this.visibilityConditions = visibilityConditions;
	}
	
	public Knowledge(String name, Condition visibilityConditions, Visibility visibility){
		this.name=name;
		this.visibility=visibility;
		this.visibilityConditions = visibilityConditions;
	}
	
	public void addConditionVisibility(Condition c){
		this.visibilityConditions.addCondition(c);
	}
	
	public void setVisibility(Visibility v){
		this.visibility=v;
	}
	
	public Visibility getVisibility(){
		return this.visibility;
	}
	
	public Condition getVisibilityConditions() {
		return this.visibilityConditions;
	}
}
