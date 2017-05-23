package truthTableNodes;

public abstract class OperatorNode extends Node {
	  private Node left;
	  private Node right;
	  
	  public OperatorNode()
	  {
	    this.left = null;
	    this.right = null;
	  }
	  
	  public boolean add(Node n)
	  {
	    boolean retVal = false;
	    if (this.left == null)
	    {
	      this.left = n;
	      retVal = true;
	    }
	    else if (!(retVal = this.left.add(n)))
	    {
	      if (this.right == null)
	      {
	        this.right = n;
	        retVal = true;
	      }
	      else
	      {
	        retVal = this.right.add(n);
	      }
	    }
	    return retVal;
	  }
	  
	  public abstract boolean evaluate();
	  
	  public Node getLeft()
	  {
	    return this.left;
	  }
	  
	  public Node getRight()
	  {
	    return this.right;
	  }
}
	
