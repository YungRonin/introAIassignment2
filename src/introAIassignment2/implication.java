package introAIassignment2;

public class implication extends connectedSentence {

	public implication(sentenceClass sentenceA, sentenceClass sentenceB) {
		super(sentenceA, sentenceB);
	}

	// return the boolean result of sentenceA implies ( => ) sentenceB
	// material conditional AKA implication (A=>B) is equivalent to ((NOT A) OR B)
	@Override
	public boolean eval() {
		return (!_sentenceA.eval() || _sentenceB.eval());
	}

}
