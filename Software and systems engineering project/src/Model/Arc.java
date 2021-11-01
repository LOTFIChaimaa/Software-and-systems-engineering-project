package Model;
import java.util.List;

public class Arc extends Component {
	
	private String name;
	private Visibility visibility;
	private Condition visibilityCondition;
	private Place target;
	private Place origin;
	private Exchange exchange;
	
	public Arc (String name, Place target, Place origin, Exchange exchange){
		this.name=name;
		this.visibility=Visibility.invisible;
		this.target = target;
		this.origin = origin;
		this.exchange = exchange;
	}
	
	public Arc (String vname, Visibility visibility, Condition visibilityCondition, Place origin, Place target, Exchange exchange){
		this.name=vname;
		this.visibility=visibility;
		this.visibilityCondition = visibilityCondition;
		this.target = target;
		this.origin = origin;
		this.exchange = exchange;
	}
	
	public Condition getVisibilityCondition() {
		return this.visibilityCondition;
	}
	
	public void setVisibilityCondition(Condition visibilityCond) {
		this.visibilityCondition = visibilityCond;
	}
	
	public void setVisibility(Visibility v){
		this.visibility = v;
	}
	
	public Visibility getVisibility(){
		return this.visibility;
	}

	public Place getTarget() {
		return this.target;
	}

	public Place getOrigin() {
		return this.origin;
	}
	
	public Exchange getExchange() {
		return this.exchange;
	}
	
	public String getName() {
		return this.name;
	}
}
