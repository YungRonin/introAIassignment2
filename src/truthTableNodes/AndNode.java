package truthTableNodes;

public class AndNode extends OperatorNode{
	
	@Override
	public boolean evaluate(){
	  return (getLeft().evaluate()) && (getRight().evaluate());
	}
}
