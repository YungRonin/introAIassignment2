package truthTableNodes;

public class XorNode extends OperatorNode {

@Override
  public boolean evaluate(){
    return getLeft().evaluate() ^ getRight().evaluate();
  }
}
