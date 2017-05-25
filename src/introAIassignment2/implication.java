package introAIassignment2;

public class implication extends connectedSentence {

	public implication(sentenceClass sentenceA, sentenceClass sentenceB) {
		super(sentenceA, sentenceB);
	}

	// return the boolean result of sentenceA implies ( => ) sentenceB
	// material conditional AKA implication (A=>B) is equivalent to ((NOT A) OR B)
	@Override
	public boolean eval() {
		return ((!_sentenceA.eval()) || _sentenceB.eval());
	}

	@Override
	public String debug() {
		return "[ " + _sentenceA.debug() + " => " + _sentenceB.debug() + " ]";
	}

	@Override
	public String debugEval(int level) {
		level++;
		return " {A"+ level +": "+_sentenceA.debugEval(level) + " => B"+level+": "+ _sentenceB.debugEval(level) + " }:"+this.eval();
	}
}
