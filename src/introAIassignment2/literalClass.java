package introAIassignment2;

//import javax.print.DocFlavor.STRING;

public class literalClass extends sentenceClass {
	String _name;
	boolean _value;
	
	public literalClass (String name) {
		super();
		_name = name;
	}
	
	// nothing to calculate, just return the stored value
	@Override
	public boolean eval() {
		return _value;
	}
	
	// value is not set with the constructor, must be set after construction
	public void setValue (boolean value) {
		_value = value;
	}
	
	// uses a String to make it compatible with the toBinaryString method
	public void setValue(String value) {
		if (value.equals("0")) this.setValue(false);
		else this.setValue(true);
	}
	
	public String name() {
		return _name;
	}

}
