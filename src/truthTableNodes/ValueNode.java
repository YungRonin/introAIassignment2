package truthTableNodes;

public class ValueNode extends Node{
	private Boolean value;

	public ValueNode(Boolean args){
	  this.value = args;
	}
	
	public boolean add(Node n){
	  return false;
	}
	
	@Override
	public boolean evaluate(){
	  return this.value.booleanValue();
	}
	
	public void setValue(boolean value){
	  this.value = Boolean.valueOf(value);
	}
}