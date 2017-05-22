package introAIassignment2;

import static org.junit.Assert.*;
import org.junit.*;

import introAIassignment2.InfixToPostfixClass;
import java.util.List;

public class ZZtest30InfixToPostfix {

	@Test
	public void test10InfixToPostFix() {
		List<String> postfixList;
		String output;

		postfixList = InfixToPostfixClass.InfixToPostfix("p1=>d");
		output = "Output: " + String.join(" ",postfixList);
        System.out.println(output);
        assertEquals("Output: p1 d =>", output);
		
		postfixList = InfixToPostfixClass.InfixToPostfix("p1&p3 => c");
		output = "Output: " + String.join(" ",postfixList);
        System.out.println(output);
        assertEquals("Output: p1 p3 & c =>", output);
		
		postfixList = InfixToPostfixClass.InfixToPostfix("(SD&GS)|~DG=>SDG");
		output = "Output: " + String.join(" ",postfixList);
        System.out.println(output);
        assertEquals("Output: SD GS & DG ~ | SDG =>", output);

		postfixList = InfixToPostfixClass.InfixToPostfix("A&B&C"); // example from http://csis.pace.edu/~wolf/CS122/infix-postfix.htm
		output = "Output: " + String.join(" ",postfixList);
        System.out.println(output);
        assertEquals("Output: A B & C &", output);

		postfixList = InfixToPostfixClass.InfixToPostfix("A&(B|C&D)&E"); // example from http://csis.pace.edu/~wolf/CS122/infix-postfix.htm
		output = "Output: " + String.join(" ",postfixList);
        System.out.println(output);
        assertEquals("Output: A B C D & | & E &", output);

        
//		InfixToPostfixClass.InfixToPostfix("(SD&GS)|~DG=>SDG");
//		InfixToPostfixClass.InfixToPostfix("A&B&C"); // example from http://csis.pace.edu/~wolf/CS122/infix-postfix.htm
//		InfixToPostfixClass.InfixToPostfix("A&(B|C&D)&E"); // example from http://csis.pace.edu/~wolf/CS122/infix-postfix.htm
//		InfixToPostfixClass.InfixToPostfix("p1=>d");
		
		// debug print output
	}

}
