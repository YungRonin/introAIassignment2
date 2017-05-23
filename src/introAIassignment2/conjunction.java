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
	public String debug(int level) {
		level++;
		return "[A"+	level +":"+_sentenceA.debug(level) + " & B"+level+":"+ _sentenceB.debug(level) + "]"+this.eval();
	}


}
