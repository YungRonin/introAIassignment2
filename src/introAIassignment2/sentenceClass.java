package introAIassignment2;

public abstract class sentenceClass {

	public sentenceClass() {
	}
	
	// if a literalclass, just return the value
	// if a sentence containing a conjunctive, return calculated value
	public abstract boolean eval(); 
}

