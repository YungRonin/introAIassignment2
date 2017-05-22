package truthTableNodes;

public class NotNode extends OperatorNode {

	  private Node left;
	  
	  public NotNode(){
	    this.left = null;
	  }
	  
	  public boolean add(Node n){
	    if (this.left == null){
	      this.left = n;
	      return true;
	    }
	   return this.left.add(n);
	  }
	  
	  @Override
	  public boolean evaluate(){
	    return !this.left.evaluate();
	  }
}
