package truthTableNodes;

public class ImpNode extends OperatorNode {

	@Override
	public boolean evaluate() {
	    return (!getLeft().evaluate()) || (getRight().evaluate());
	  }

}
