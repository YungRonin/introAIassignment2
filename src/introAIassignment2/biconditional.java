package introAIassignment2;

public class biconditional extends connectedSentence {

	public biconditional(sentenceClass sentenceA, sentenceClass sentenceB) {
		super(sentenceA, sentenceB);
	}
	
	// return the boolean result of: sentenceA implies ( => ) sentenceB and sentenceB implies ( => ) sentenceA
	// biconditional (A<=>B) is equivalent to ((NOT A) OR B) AND ((NOT B) OR A)
	@Override
	public boolean eval() {
		boolean P = _sentenceA.eval();
		boolean Q = _sentenceB.eval();
		
		return (( !P || Q ) && ( !Q || P ));
	}
	
	@Override
	public String debug() {
		return "[ " + _sentenceA.debug() + " <=> " + _sentenceB.debug() + " ]";
	}
	
	@Override
	public String debugEval(int level) {
		level++;
		return " [A"+ level +": "+_sentenceA.debugEval(level) + " <=> B"+level+": "+ _sentenceB.debugEval(level) + " ]:"+this.eval();
	}
}

