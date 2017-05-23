package introAIassignment2;

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
	
	public String name() {
		return _name;
	}

}
