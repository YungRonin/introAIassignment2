package introAIassignment2;
import java.util.*;

//http://snipplr.com/view/56296/ai-forward-chaining-implementation-for-propositional-logic-horn-form-knowledge-bases/
 
public class forwardChaining extends truthMethod{

private static ArrayList<String> agenda;
private static ArrayList<Integer> count;
private static knowledgeBase kb;
 
public forwardChaining(String code, String longName){
	super(code, longName);

	agenda  = new ArrayList<String>();
	kb = new knowledgeBase();
	count  = new ArrayList<Integer>();
}
 
@Override
public String execute(){
	String output = "";
	if (fwdChainingEntails()){
			// the method returned true so it entails
		output = "ask = " + ask + "\n";
		output += "YES: ";	
		output += kb.printArrayList(kb.literals());
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

//public String printArrayList(ArrayList<String> args){
//	
//	String result = "";
//	String comma = "";
//	
//	for(String option : args){
//		 result += comma + option;
//		 comma = ", ";
//	}
//	return result;
//}
 
// FC algorithm
public boolean fwdChainingEntails(){
// loop through while there are unprocessed facts
while(!agenda.isEmpty()){

		ArrayList<String> sentences = kb.sentences();
	 	String p = agenda.remove(0);	
	 	// add to literals
		kb.addLiteral(p);

		// for each of the clauses....
	
		for (int i=0;i<sentences.size();i++){
			
			// .... that contain p in its premise
			if (premiseContains(sentences.get(i),p)){
			
			String conditions = sentences.get(i).split("=>")[0];
			if(!conditions.contains("&")){

				kb.addLiteral(conditions);
				agenda.add(conditions);
			}
				
				
				Integer j = count.get(i);
			// reduce count : unknown elements in each premise
			count.set(i,--j);
				// all the elements in the premise are now known
				if (count.get(i)==0){
					// the conclusion has been proven so put into agenda
					String head = sentences.get(i).split("=>")[1];
					// check if ask is true
					if (head.equals(ask)){
						kb.addLiteral(head);
						kb.addFact(head);
						return true;
					}
					agenda.add(head);
				}
			}	
		}
	}
	return false;
}
 
 
 
 
// method which sets up initial values for forward chainin
@Override
public void init(String tell, String ask){

	this.ask = ask;
	this.tell = tell;
	String[] statementes = tell.split(";");

	for (String statement : statementes){
 
		if (!statement.contains("=>")) {
			// add facts to be processed
			agenda.add(statement);
			kb.addLiteral(statement);
		}
		else{
			// add sentences
			kb.addSentence(statement);
			count.add(statement.split("&").length);
			}
	}
}
 
public static boolean premiseContains(String clause, String p){
	String premise = clause.split("=>")[0];
	String[] conjuncts = premise.split("&");
	// check if p is in the premise
	if (conjuncts.length==1){
		return conjuncts[0].contains(p); 
	}
	else{
		for(String conjunct : conjuncts){
			if(conjunct.equals(p)){
				return true;
			}
		}
	}
	return false;
}
}
