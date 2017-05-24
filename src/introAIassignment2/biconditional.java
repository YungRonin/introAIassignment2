package introAIassignment2;

public class biconditional extends connectedSentence {

	public biconditional(sentenceClass sentenceA, sentenceClass sentenceB) {
		super(sentenceA, sentenceB);
	}
	
	// return the boolean result of: sentenceA implies ( => ) sentenceB and sentenceB implies ( => ) sentenceA
	// biconditional (A<=>B) is equivalent to ((NOT A) OR B) AND ((NOT B) OR A)
	@Override
	public boolean eval() {
		return (((!_sentenceA.eval()) || _sentenceB.eval()) && (!_sentenceB.eval()) || _sentenceA.eval());
	}
	
	@Override
	public String debug(int level) {
		level++;
		return "[A"+level +":"+_sentenceA.debug(level) + " & B"+level+":"+ _sentenceB.debug(level) + "]"+this.eval();
	}
}

