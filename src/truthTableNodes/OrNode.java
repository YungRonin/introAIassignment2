package truthTableNodes;

public class OrNode extends OperatorNode{

@Override
public boolean evaluate(){
    return (getLeft().evaluate()) || (getRight().evaluate());
  }
}
