package introAIassignment2;

public class conjunction extends connectedSentence {

	public conjunction(sentenceClass sentenceA, sentenceClass sentenceB) {
		super(sentenceA, sentenceB);
		// TODO Auto-generated constructor stub
	}
	
	// return the boolean result of sentenceA AND sentenceB
	@Override
	public boolean eval() {
		return (_sentenceA.eval() && _sentenceB.eval());
	}
	
	@Override
	public String debug() {
		return "( " + _sentenceA.debug() + " & " + _sentenceB.debug() + " )";
	}
	
	@Override
	public String debugEval(int level) {
		level++;
		return " (A"+ level +": "+_sentenceA.debugEval(level) + " & B"+level+": "+ _sentenceB.debugEval(level) + " ):"+this.eval();
	}
}
