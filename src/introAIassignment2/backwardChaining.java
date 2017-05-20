package introAIassignment2;
import java.util.*;
import java.io.*;

//http://snipplr.com/view/56297/ai-backward-chaining-implementation-for-propositional-logic-horn-form-knowledge-bases/

public class backwardChaining {
	
	private static String tell;
	private static String ask;
	private static ArrayList<String> agenda;
	private static ArrayList<String> resultProcess;
	private static knowledgeBase kb;
	 
	public backwardChaining(String a, String t){
		agenda  = new ArrayList<String>();
		resultProcess = new ArrayList<String>();
		tell = t;
		ask = a;
		kb = new knowledgeBase();
		init(tell);
	}
	 
	public static void init(String tell){
		agenda.add(ask);
		kb.addSentences(tell);
	}
	 
	public String execute(){
		String output = "";
	 
		if (backwardChainingEntails()){
			 
			output += "ask = " + ask + "\n";
			output += "YES: ";
			ArrayList<String> lits = kb.literals();
			for (int i=lits.size()-1; i>=0; i--){
				if (i==0)
					output += lits.get(i);
				else	
					output += lits.get(i)+", ";
			}
		}
		else{
			output = "NO";
			output += "\n ask = " + ask;
		}
		return output;		
	}
	
	public void printKb(){
		kb.printKB();
	}
	 
	// method which runs the bc algorithm
	public boolean backwardChainingEntails(){
		// while the list of symbols are not empty
		while(!agenda.isEmpty()){
			// get current symbol
			String q = agenda.remove(agenda.size()-1);
			// add the entailed array
			kb.addLiteral(q);
			resultProcess.add(q);
			
			// if this element is a fact then we dont need to go further
			if (!kb.facts().contains(q)){
				// .. but it isnt so..
				// create array to hold new symbols to be processed 
				ArrayList<String> arguments = new ArrayList<String>();
				for(String sentence : kb.sentences()){
				// for each clause..
					if (conclusionContains(sentence, q)){
					// that contains the symbol as its conclusion
						ArrayList<String> premises = getPremises(sentence);
						for(String premise : premises){
							// add the symbols to a temp array
							arguments.add(premise);
						}
					}						
				}
				// no symbols were generated and since it isnt a fact either 
				// then this sybmol and eventually ASK  cannot be implied by TELL
				if (arguments.size()==0){
					return false;
				}
				else{
					// there are symbols so check for previously processed ones and add to agenda
					for(String argument : arguments){
						if (!kb.literals().contains(argument)){
							agenda.add(argument);
						}	
					}
				}
			}
		}//while end
		return true;
	}
	
	 
	// method that returns the conjuncts contained in a clause
	public static ArrayList<String> getPremises(String clause){
		// get the premise
		String premise = clause.split("=>")[0];
		ArrayList<String> result = new ArrayList<String>();
		String[] conjuncts = premise.split("&");
		// for each conjunct
		for(String conjunct : conjuncts){
				if (!agenda.contains(conjunct))
						result.add(conjunct);
		}
		return result;
	}
	 
	 
	// method which checks if c appears in the conclusion of a given clause	
	// input : clause, c
	// output : true if c is in the conclusion of clause
	public static boolean conclusionContains(String clause, String c){
		String conclusion = clause.split("=>")[1];
		if (conclusion.equals(c))
			return true;
		else
			return false;
	}
}
