package introAIassignment2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class connectivesClass {
	// surround all connectives with spaces
	public static String[] cons = {
			"<=>", // biconditional
			"=>", // implication (conditional)
			"|", // disjunction (OR)
			"&", // conjunction (AND)
			"~", // inversion (NOT)
			")", // close parenthesis
			"(" // open parenthesis
	};
	
	public static boolean contains(char args){
		String arg = "" + args;
		for(String con : cons){
			if(con.contains(arg))
				return true;
		}
		return false;
	}

	// connectives is the list of all non-literals, including parentheses
   	public static List<String> connectives = new LinkedList<String>(Arrays.asList(cons));
   	
   	// operators are the non-literal and non-parenthesis symbols
	public static List<String> operators = new LinkedList<String>(connectives.subList(0, connectives.size()-2));
}
