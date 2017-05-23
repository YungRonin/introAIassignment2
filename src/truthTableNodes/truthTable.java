package truthTableNodes;

import java.util.LinkedList;
import java.util.TreeMap;

import introAIassignment2.connectivesClass;

public class truthTable{
  private Node top;
  private TreeMap<String, ValueNode> values;
  private Integer line;
  private double numVals;
  private char truth;
  private char unTruth;
  
  public truthTable(){
    this.line = Integer.valueOf(0);
    this.truth = 'T';
    this.unTruth = 'F';
  }
  
  
  public void start(String tell){
      this.line = 0;
      this.values = new TreeMap<String, ValueNode>();
      parseStatement(tell);
  }
  
  private boolean parseStatement(String tell){
    LinkedList<String> errors = new LinkedList<String>();
    
    int opCount = 0;
    int valCount = 0;
    tell = tell.trim();
    tell = tell.replace(" ", "");
    tell = tell.replace("\t", "");

    char[] arrayOfChar;
    int j = (arrayOfChar = tell.toCharArray()).length;
    for (int i = 0; i < j; i++){
      char symbol = arrayOfChar[i];
      
      //TODO pass each sentence into this method individually...????

      if ((Character.isLetter(symbol)) || (Character.isDigit(symbol))){
          ValueNode val;
          String charString = "" + symbol;
          if (!this.values.containsKey(charString)){
          this.line = this.line * 2 + 1;
          
          Boolean var = Boolean.valueOf(true);
          val = new ValueNode(var);
          
          this.values.put(charString, val);
        }
        else{
            val = (ValueNode)this.values.get(charString);
        }
       
        if (this.top == null){
          this.top = val;
          valCount++;
        }
        else if (!this.top.add(val)){
          if (!errors.contains("Too many values.")) {
            errors.add("Too many values.");
          }
        }
        else{
          valCount++;
        }
      }
      else if (connectivesClass.contains(symbol)){
        OperatorNode op;
        if ((symbol == '*') || (symbol == '&')){
          op = new AndNode();
        }
        else if ((symbol == '+') || (symbol == '|')){
            op = new OrNode();
        }
        else if (symbol == '^'){
            op = new XorNode();
        }
        else if (symbol == '>'){
            op = new ImpNode();
        }
        else if (symbol == '='){   
        	op = new BiimpNode();
        }
        else{
        	op = new NotNode();
        	opCount--;
        }            

        if (this.top == null){
          this.top = op;
          opCount++;
        }
        else if (!this.top.add(op)){
         errors.add("Extra operator: " + symbol);
        }
        else{
          opCount++;
        }
      }
    }
    if ((errors.size() == 0) && (opCount + 1 != valCount)) {
      errors.add("Not enough values.");
    }
    if (errors.size() > 0){
      String msg = "";
      for (String i : errors) {
        msg = msg + i + "\n";
      }
      System.out.println( );
      return false;
    }
    return true;
  }
  
  public String table(String tell){
	  if (parseStatement(tell)){
      String table = "";
      for (String name : this.values.keySet()){
        table = table + name + "     ";
      }
      table = table + "Result\n";
      int numLines = this.line.intValue();
      this.line = 0;
      this.numVals = this.values.keySet().size();
      for (int i = numLines; i >= 0; this.line++){
        table = table + setUpVals();
        table = table + (this.top.evaluate() ? this.truth : this.unTruth) + "\n";i--;
      }
      this.line = 0;
      this.values = new TreeMap<String, ValueNode>();
      return table;
    }
    return "";
  }
  
  private String setUpVals(){
    String res = "";
    String control = Integer.toBinaryString(this.line);
    while (control.length() < this.numVals) {
        control = "0" + control;
    }
    int valNum = 0;
    for (String name : this.values.keySet()){
      ValueNode thisValue = (ValueNode)this.values.get(name);
      
      thisValue.setValue(control.charAt(valNum) == '1');
      
      res = res + (control.charAt(valNum) == '1' ? this.truth : this.unTruth) + "     ";
      
      valNum++;
    }
    return res;
  }

}
