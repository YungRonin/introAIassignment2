package introAIassignment2;

public abstract class connectedSentence extends sentenceClass {
	// the two sides of the sentence
	// the connective between the two sentences is denoted by the
	sentenceClass _sentenceA, _sentenceB;
	
	public connectedSentence(sentenceClass sentenceA, sentenceClass sentenceB) {
		super();
		_sentenceA = sentenceA;
		_sentenceB = sentenceB;
	}
	
	// will return the evaluation of the values of the two sentences
	public abstract boolean eval();

}
