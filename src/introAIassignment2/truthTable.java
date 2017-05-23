package introAIassignment2;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Stack;
import java.util.Set;


public class truthTable extends truthMethod {
	private List<sentenceClass> sentenceList; // contains sentences represented as sentenceClass object trees
	private Map<String, literalClass> literalList; // uniquely identifies each literal represented in all sentences in the sentenceList

	public truthTable(String code, String longName) {
		super(code, longName);
		sentenceList = new LinkedList<sentenceClass>();
		literalList = new HashMap<String, literalClass>();
	}

	@Override
	public void init(String tell, String ask) {
		this.ask = ask;
		this.tell = tell;
		kb.addSentences(tell);
		// make a sentence object tree for each sentence string in the KB
		// and add to the list of sentence tree objects
		for (String sentence : kb.sentences()) {
			// this also add all the literals to the literalList of literalClass objects
			sentenceList.add(this.makeSentenceTree(sentence));
		}
	}

	@Override
	public String execute() {
		String output = "";
		output += kb.sentences().toString() + "\n";
		output += literalList.keySet().toString() + "\n";
		output += sentenceList.toString() + "\n";
		// TODO Auto-generated method stub
		return output;
	}
	
	// returns a sentenceClass tree from the given sentence string
	// adds all literals in the sentence the literalList if the literal name is not already present
	public sentenceClass makeSentenceTree(String sentenceString){
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
    		// process operators
        	// two-input operators pop two sentences from the stack
        	// and put them into a new sentence which contains
        	// sentence A and sentence B and a method to evaluate them
        	
    		// there should be at least two sentences on the stack when an operator is processed.
        	// if not, there's something wrong with the sentence or the InfixToPostfix converter
        		
        	else if (s.equals("=>")) { // implication (conditional)
        		sentenceA = stack.pop();
        		sentenceB = stack.pop();
        		symbol = new implication(sentenceA, sentenceB);
        		stack.push(symbol);
        	}
        	else if (s.equals("&")) { // conjunction (AND)
        		sentenceA = stack.pop();
        		sentenceB = stack.pop();
        		symbol = new conjunction(sentenceA, sentenceB);
        		stack.push(symbol);
        	}
// NEW OPERATORS GO HERE
        	
// example of NOT operator (a 1-input connective)

//        	else if (s.equals("~")) { // inversion (NOT)
//        		sentenceA = stack.pop();
//        		symbol = new inversion(sentenceA);
//        		stack.push(symbol);
//        	}
        	
		} // end of sentence processing
		
		// when the postfixList is empty, the last symbol sentenceClass object created
		// is the top (root) of this particular sentence tree
		// return this symbol
		
		return symbol;
	}	

}
