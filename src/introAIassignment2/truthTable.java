package introAIassignment2;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Stack;

public class truthTable extends truthMethod {
	private List<sentenceClass> sentenceList;		// contains sentences represented as sentenceClass object trees
	private Map<String, literalClass> literalList;	// uniquely identifies each literal represented in all sentences in the sentenceList
	private sentenceClass askTree;					// contains the ask query as a sentenceClass object tree (so queries can be complex sentences)
	
	public truthTable(String code, String longName) {
		super(code, longName);
		sentenceList = new LinkedList<sentenceClass>();
		literalList = new LinkedHashMap<String, literalClass>();
	}

	@Override
	public void init(String tell, String ask) {
		this.ask = ask;
		this.tell = tell;
		this.addAllSentences(tell);
		
		// make a sentence object tree for each sentence string in the KB
		// and add to the list of sentence tree objects
		for (String sentence : kb.sentences()) {
			// this also add all the literals to the literalList of literalClass objects
			sentenceList.add(this.makeSentenceTree(sentence));
		}
		askTree = this.makeSentenceTree(ask);
	}

	// for Truth Tables, all facts and sentences are sentences
	private void addAllSentences(String knowledge){
		for (String sentence : knowledge.split(";")){
			kb.addSentence(sentence);
		}
	}
	
	@Override
	public String execute() {
		DecimalFormat decimalFormat = new DecimalFormat("#");
		String output = "";

		double TTTrueModels = this.TruthTableTrueModels();
		if (TTTrueModels > 0) {
			// the method returned that there are true worlds for this KB
			output += "ask = " + ask + "\n";
			output += "YES: ";
			output += decimalFormat.format(TTTrueModels);
		}
		else {
			output += "NO";
			output += "\n ask = " + ask;
		}
		return output;
	}
	
	// returns the number of models in which the KB sentences are true
	private double TruthTableTrueModels() {
		int NumOfLiterals = literalList.size();
		double TTModels = Math.pow(2, NumOfLiterals);

		double TTTrueModels = TTModels; // as false models are found, this is decremented
		List<literalClass> literalArrayList = new ArrayList<literalClass>(literalList.values()); // need it in ArrayList format to iterate it
		
		// iterate through all possible models for this KB
		for (int model = 0; model < TTModels; model++) {

			// LiteralVals uses the "toBinaryString" method to create an array of strings that are either 0 or 1.
			// this is the binary bits that are set (1/true) or not set (0/false) according to this particular model
			String[] LiteralVals = String.format(
					"%"+NumOfLiterals+"s",
					Integer.toBinaryString(model)
					).replace(' ', '0').split("");
			
			// set each literal to TRUE (1) or FALSE (0);
			for (int i = 0; i < NumOfLiterals; i++){
				literalArrayList.get(i).setValue(LiteralVals[i]);

			}
			
			// evaluate each sentence in KB
			// if a sentence is false, decrement the TTTrueModels counter and break out of the sentence evaluation loop
		
			// check that the query is true for this model
			if (!askTree.eval()) {
				TTTrueModels--; // query is false so decrement number of true models
			}
			else {
				for (sentenceClass sentence : sentenceList){
					if (!sentence.eval()) {
						TTTrueModels--; // sentence is false so decrement number of true models
						break;	// we found a false sentence so stop evaluating sentences for this model - the model is false
					}
				}
			}
		}
		return TTTrueModels;
	}
	
	// returns a sentenceClass tree from the given sentence string
	// adds all literals in the sentence the literalList if the literal name is not already present
	// based on: http://www.c4learn.com/data-structure/algorithm-evaluation-of-postfix-expression/
	private sentenceClass makeSentenceTree(String sentenceString){
		Stack<sentenceClass> stack = new Stack<sentenceClass>();
		List<String> postfixList = new LinkedList<String>();
		sentenceClass symbol, sentenceA, sentenceB;
		symbol = null;
		
		// get the sentence into a list of symbols in postFix order
		// a Linked List allows us to remove items easily
		postfixList = InfixToPostfixClass.InfixToPostfix(sentenceString);

		while (!postfixList.isEmpty()) {
			String s = "";
			sentenceA = null;
			sentenceB = null;
			s = postfixList.get(0);
			postfixList.remove(0);
			
			// if s is a literal
			//(not checking for parentheses - there should be no parentheses in a postfix expression)
        	if (!connectivesClass.operators.contains(s)) {
        		// search for literal s in the list of literals
        		symbol = literalList.get(s);

        		if (symbol != null ) {
            		// if the literal already exists in the literalList, push it to the stack
        			stack.push(symbol);
        		} else {
        			// object is not in the literalList so create it,
        			// add it to the literalList and push it to the stack
            		symbol = new literalClass(s);
            		literalList.put(s,(literalClass)symbol); // have to cast symbol to literalClass to put it in the literalList map
            		stack.push(symbol);
        		}
        	} 
    		// process the operators
        	// two-input operators pop two sentences from the stack
        	// and put them into a new sentence which contains
        	// sentence A and sentence B and a method to evaluate them
        	
    		// there should be at least two sentences on the stack when an operator is processed.
        	// if not, there's something wrong with the sentence or the InfixToPostfix converter
        		
        	else if (s.equals("=>")) { // implication (conditional)
        		sentenceB = stack.pop();
        		sentenceA = stack.pop();
        		symbol = new implication(sentenceA, sentenceB);
        		stack.push(symbol);
        	}
        	else if (s.equals("&")) { // conjunction (AND)
        		sentenceB = stack.pop();
        		sentenceA = stack.pop();
        		symbol = new conjunction(sentenceA, sentenceB);
        		stack.push(symbol);
        	}
        	else if (s.equals("~")) { // inversion (NOT)
        		sentenceA = stack.pop();
        		symbol = new inversion(sentenceA);
        		stack.push(symbol);
        	}
        	else if (s.equals("<=>")) { // biconditional
        		sentenceB = stack.pop();
        		sentenceA = stack.pop();
        		symbol = new biconditional(sentenceA, sentenceB);
        		stack.push(symbol);
        	}
        	else if (s.equals("|")) { // disjuntion (or)
        		sentenceB = stack.pop();
        		sentenceA = stack.pop();
        		symbol = new disjunction(sentenceA, sentenceB);
        		stack.push(symbol);
        	}
        	
        	
		} // end of sentence processing
		
		// when the postfixList is empty, the last symbol sentenceClass object created
		// is the top (root) of this particular sentence tree
		// return this symbol
		
		return symbol;
	}	

}
