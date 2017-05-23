package truthTableNodes;

public class BiimpNode extends OperatorNode {

	@Override
	public boolean evaluate() {
	    return !(getLeft().evaluate() ^ getRight().evaluate());
	  }
}
