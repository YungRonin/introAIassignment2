package introAIassignment2;

import java.util.Stack;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

// the static connective variables1 are held in connectivesClass
import introAIassignment2.connectivesClass;

public class InfixToPostfixClass {

	// adapted from http://introcs.cs.princeton.edu/java/43stack/InfixToPostfix.java.html
	// with insight from http://csis.pace.edu/~wolf/CS122/infix-postfix.htm
	
    public static void InfixToPostfix(String sentence) {
    	String _sentence=sentence;
    	connectivesClass c = new connectivesClass();// this includes the list of connectives and operators
   	
// debug print input    	
    	System.out.println(_sentence);
    	for (String connective : c.connectives){
    		_sentence = _sentence.replace(connective, " "+connective+" ");
    	}
    	// trim multiple spaces to one, and trims leading and trailing whitespace
    	_sentence = _sentence.replaceAll(" +", " ").trim();

    	// now _sentence contains all symbols delimited by one space
    	List<String> infixList = new LinkedList<String>(Arrays.asList(_sentence.split(" ")));

    	
// debug print input    	
        System.out.println(String.join(" ", infixList));
    	
    	List<String> postfixList = new ArrayList<String>();
    	Stack<String> stack = new Stack<String>();
    	String s;
        
        while (!infixList.isEmpty()) {
            s = infixList.get(0);
            infixList.remove(0);

            // 1.	Output operands (literals) as they arrive
        	if (!c.connectives.contains(s)) {
        		postfixList.add(s);
        	}
        	
        	// 2.	If the stack is empty or contains a left parenthesis on top, push the incoming operator onto the stack.
        	// 3.	If the incoming symbol is a left parenthesis, push it on the stack.
        	else if (stack.isEmpty() || s.equals("(") ) {
        		stack.push(s);
        	}

        	// 4.	If the incoming symbol is a right parenthesis, pop the stack and print the operators until you see a left parenthesis. Discard the pair of parentheses.
        	// 		incoming symbol is not an operator (it is a literal/operand), so add it to the postfix output
            else if ( s.equals(")") ) {
            	while (!stack.isEmpty() && !stack.peek().equals("(") ) {
            		postfixList.add(stack.pop()); // add the symbols on the stack to the output
            	} 
            	if (!stack.isEmpty() && stack.peek().equals("(") ) {
            		stack.pop(); // discard the left parenthesis once found
            	}
            }
        	
        	// 5.	If the incoming symbol has higher precedence than the top of the stack, push it on the stack.
        	// 6.	If the incoming symbol has equal precedence with the top of the stack, use association.
        	//		If the association is left to right, pop and print the top of the stack and then push the incoming operator.
        	//		If the association is right to left, push the incoming operator.
        	// 7.	If the incoming symbol has lower precedence than the symbol on the top of the stack, pop the stack and print the top operator.
        	//		Then test the incoming operator against the new top of stack.
            else if (c.operators.contains(s)) {
            	while ((!stack.isEmpty()) && c.operators.indexOf(s) <= c.operators.indexOf(stack.peek())) {
            		// the incoming symbol is lower precedence to the top of the stack
            		// so pop the stack and add to the output
            		postfixList.add(stack.pop());
            	}
            	// now add the operator to the stack
            	stack.push(s);
            } 
        }
       // 8. pop and output all remaining operators from the stack
       while (!stack.isEmpty()) {
//    	   System.out.println("8");
    	   postfixList.add(stack.pop());
       }
        
// debug print output
        System.out.println("Output: " + String.join(" ",postfixList));
    }

}
