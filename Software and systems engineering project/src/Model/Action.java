package Model;

public class Action extends Component {
	
	private Exchange exchange;
	private String name;
	
	public Action(String name, Exchange exchange){
		this.name=name;
		this.exchange = exchange;
	}
	
	public Exchange getExchange() {
		return this.exchange;
	}
}
