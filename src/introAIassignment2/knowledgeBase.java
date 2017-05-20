package introAIassignment2;

import java.util.ArrayList;

public class knowledgeBase {
	
	private ArrayList<String> facts;
	private ArrayList<String> sentences;
	private ArrayList<String> literals;
	
	public knowledgeBase(){
		sentences  = new ArrayList<String>();
		literals  = new ArrayList<String>();
		facts  = new ArrayList<String>();
	}
	
	public void addLiteral(String args){
		if(!literalsContains(args)){
			literals.add(args);
		}
	}
	
	public void addSentence(String sentence){
		if(!sentencesContains(sentence)){
			sentences.add(sentence);
		}
	}
	
	public void addSentences(String knowledge){
	   String[] sentences = knowledge.split(";");
		for (String sentence : sentences){
			if (!sentence.contains("=>")) {
				// add the facts
				this.addFact(sentence);
				//this.addLiteral(sentence);
			}
			else{
				// add the premises	
				this.addSentence(sentence);
			}
		}
	}
	
	public void addFact(String fact){
		if(!factsContains(fact)){
			facts.add(fact);
		}
	}
	
	public ArrayList<String> literals(){
		return literals;
	}
	
	public ArrayList<String> sentences(){
		return sentences;
	}
	
	public ArrayList<String> facts(){
		return facts;
	}
	
	private boolean literalsContains(String args){
		for(String lit : literals){
			if(lit.equals(args)){
				return true;
			}
		}
		return false;
	}
	
	private boolean sentencesContains(String args){
		for(String sentence : sentences){
			if(sentence.equals(args)){
				return true;
			}
		}
		return false;
	}
	
	private boolean factsContains(String args){
		for(String fact : sentences){
			if(fact.equals(args)){
				return true;
			}
		}
		return false;
	}
	
	public String printArrayList(ArrayList<String> args){
		
		String result = "";
		
		for(String option : args){
			 result += option + " , ";
		}
		
		return result;
	}
	
	public void printKB(){
		//"sentences = " + printArrayList(sentences)
		System.out.println("\n literal = " + printArrayList(literals)
							+ "\n facts = " + printArrayList(facts)
				);		
	}
}