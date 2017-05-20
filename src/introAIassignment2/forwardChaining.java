package introAIassignment2;
import java.util.*;
import java.io.*;

//http://snipplr.com/view/56296/ai-forward-chaining-implementation-for-propositional-logic-horn-form-knowledge-bases/
 
public class forwardChaining{
// create variables
public static String tell;
public static String ask;
public static ArrayList<String> agenda;
 
public static ArrayList<String> facts;
public static ArrayList<String> sentances;
public static ArrayList<Integer> count;
public static HashMap<Integer, String> hMap;
public static ArrayList<String> literal;
 
 
public forwardChaining(String a, String t){
	// initialize variables
	agenda  = new ArrayList<String>();
	sentances  = new ArrayList<String>();
	literal  = new ArrayList<String>();
	facts  = new ArrayList<String>();
	hMap = new HashMap<Integer, String>();
	count  = new ArrayList<Integer>();
	tell = t;
	ask = a;
	init(tell);
}
 
// method which calls the main fcentails() method and returns output back to iengine
public String execute(){
	String output = "";
	if (fwdChainingEntails()){
			// the method returned true so it entails
			output = "YES: ";
			
			output += "\n agenda = " + printArrayList(agenda);
			output += "\n sentances = " + printArrayList(sentances);
			output += "\n literal = " + printArrayList(literal);
			output += "\n facts = " + printArrayList(facts);
			//output += "\n count = " + printArrayList(count);
			output += "\n ask = " + ask;
			
			// for each entailed symbol
			for (int i=0;i<literal.size();i++){
					output += literal.get(i)+", ";
				}
			output += ask;	
	}
	else{
			output = "NO";
			output += "\n agenda = " + printArrayList(agenda);
			output += "\n sentances = " + printArrayList(sentances);
			output += "\n literal = " + printArrayList(literal);
			output += "\n facts = " + printArrayList(facts);
			//output += "\n count = " + printArrayList(count);
			output += "\n ask = " + ask;
			
	}
	return output;		
}

public String printArrayList(ArrayList<String> args){
	
	String result = "";
	
	for(String option : args){
		 result += option + " , ";
	}
	
	return result;
}

public boolean literalContains(String args){
	for(String lit : literal){
		if(lit.equals(args)){
			return true;
		}
	}
	return false;
}

 
// FC algorithm
public boolean fwdChainingEntails(){
// loop through while there are unprocessed facts
while(!agenda.isEmpty()){
		// take the first item and process it
	
		System.out.println("agenda item = " + agenda.get(0));
	 	String p = agenda.remove(0);
		// add to entailed
		
	 	if(!literalContains(p)){
			literal.add(p);
		}
		// for each of the clauses....
	
		for (int i=0;i<sentances.size();i++){
			
		
			//System.out.println("sentence = " + sentances.get(i));
			// .... that contain p in its premise
			if (premiseContains(sentances.get(i),p)){
			
			String conditions = sentances.get(i).split("=>")[0];
				//System.out.println("conditions = " + conditions);
			if(!conditions.contains("&")){
				if(!literalContains(conditions)){
					literal.add(conditions);
				}
				agenda.add(conditions);
			}
				
				
				Integer j = count.get(i);
			// reduce count : unknown elements in each premise
			count.set(i,--j);
				// all the elements in the premise are now known
				if (count.get(i)==0){
					// the conclusion has been proven so put into agenda
					String head = sentances.get(i).split("=>")[1];
					// have we just proven the 'ask'?
					if (head.equals(ask))
						return true;
					agenda.add(head);					
				}
			}	
		}
	}
	// if we arrive here then ask cannot be entailed
	return false;
}
 
 
 
 
// method which sets up initial values for forward chaining
// takes in string representing KB and seperates symbols and clauses, calculates count etc..
public static void init(String tell){
   String[] sentences = tell.split(";");

   System.out.println("tell =" +tell);
	for (int i=0;i<sentences.length;i++){
 
		if (!sentences[i].contains("=>")) 
			// add facts to be processed
			agenda.add(sentences[i]);
		else{
			// add sentences
			sentances.add(sentences[i]);
			hMap.put(i, sentences[i]);
			count.add(sentences[i].split("&").length);
			}
	}
}
 
 
// method which checks if p appears in the premise of a given clause	
// input : clause, p
// output : true if p is in the premise of clause
public static boolean premiseContains(String clause, String p){
	String premise = clause.split("=>")[0];
	String[] conjuncts = premise.split("&");
	// check if p is in the premise
	if (conjuncts.length==1){
		//System.out.println("p = " + p + " conj = " + conjuncts[0]);
		//System.out.println("calc 1 = " + conjuncts[0].equals(p));
		return conjuncts[0].contains(p); 
	}
	else{
		for(String conjunct : conjuncts){
			//System.out.println("p = " + p + " conj = " + conjunct);
			//System.out.println("calc 2 = " + conjunct.equals(p));
			if(conjunct.equals(p)){
				return true;
			}
		}
	}
	return false;
}
}
