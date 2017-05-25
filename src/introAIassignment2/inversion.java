package introAIassignment2;

public class inversion  extends sentenceClass {

sentenceClass _sentence;
	
	public inversion(sentenceClass sentence) {
		super();
		_sentence = sentence;		
	}
	
	//return the boolean result of not sentence
	@Override
	public boolean eval() {	
		return (!_sentence.eval());
	}

	@Override
	public String debug() {
		return "~" + _sentence.debug();
	}

	@Override
	public String debugEval(int level) {
		level++;
		return " (A"+level +": ~"+_sentence.debugEval(level) + " ):"+this.eval();
	}
}
