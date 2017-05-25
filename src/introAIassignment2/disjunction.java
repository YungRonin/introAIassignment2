package introAIassignment2;

public class disjunction extends connectedSentence {

	public disjunction(sentenceClass sentenceA, sentenceClass sentenceB) {
		super(sentenceA, sentenceB);
	}
	
	// return the boolean result of sentenceA or sentenceB
	@Override
	public boolean eval() {
		return (_sentenceA.eval() || _sentenceB.eval());
	}
	
	@Override
	public String debug(int level) {
		level++;
		return "<A"+	level +":"+_sentenceA.debug(level) + " | B"+level+":"+ _sentenceB.debug(level) + ">"+this.eval();
	}
}
