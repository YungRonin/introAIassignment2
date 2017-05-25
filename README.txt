"# introAIassignment2" 

Student Details: 
Your full student names, ids, and your group number (as allocated by ESP).  COS30019_A02_T026 ++
Adam Richards, 7634765
Dylan Forster, 2058979
Dan Flett, 100501422


Features/Bugs/Missing: 
Include a list of the features you have implemented. Clearly state if a
required feature has not been implemented. Failure to do this will result in penalties. Include a list of
any known bugs.

File Reader
Truth Table - with generic logic statement evaluation and debug
Forward Chaining
Backward Chaining



Test cases: The test cases you have developed to test your program. What bugs have you found?
Some of our test cases:

	TELL
	p2=> p3; p3 => p1; c => e; b&e => f; f&g => h; p1=>d; p1&p3 => c;
	ASK
	d

	TELL
	p2=> p3; p3 => p1; p1=>d; a; b; p2;
	ASK
	d

	TELL
	s; t;
	ASK
	s|~s

	TELL
	p2=> p3; p3 => p1; c => e; b&e => f; f&g => h; p1=>d; p1&p3 => c; a; b; p2;
	ASK
	d

	TELL
	(a<=>b); b=>c; a&b|c=>d; a&~(b|c)<=>e; ~f; ~a|(c=>f&g|h)<=>v|r|c; g<=>~i; a<=>~j|(k|l); m&n=>a; a|o; d&p; c=>q; d<=>r; ~s; t; m=>a=>u; ~a<=>(s|t)=>v; g|w;
	ASK
	d&w

	TELL
	p2=> p3; h&d&e=>zed; p3 => p1; c => e; b&e => f; f&g => h; p1=>d; p1&p3 => c; a; b; p2; g;
	ASK
	zed

Acknowledgements/Resources: 
Include in your readme.txt file a list of the resources you have
used to create your work. A simple list of URL's is not enough. Include with each entry a basic
description of how the person or website assisted you in your work.

	The Infix To Posfix converter was initially inspired by this code: http://introcs.cs.princeton.edu/java/43stack/InfixToPostfix.java.html
	This is a very simple implementation in java that clearly shows the algorithm. However, it expects that every infix expression be in parenthesis, whereas we need it to evaluate expressions where parentheses are optional. This site http://csis.pace.edu/~wolf/CS122/infix-postfix.htm provided more insight into a general, parenthesis-optional algorithm. Of course, the symbols had to be adapted to the logic symbols, but the principle is largely the same.
	
	The implementation of the binary expression trees to represent the sentences in the Truth Table evaluator was inspired by algorithms and diagrams on the Wikipedia page on Binary Expression Trees: https://en.wikipedia.org/wiki/Binary_expression_tree. By making the nodes of the tree java objects, we could store the truth value of each literal in the leaf nodes, and have the expression nodes recursively return the result with the "eval()" method.
	A clearer, mode "codelike" algorithm for a postfix evaluator is given here: http://www.c4learn.com/data-structure/algorithm-evaluation-of-postfix-expression/. The sentence-tree construction code was based on this.
	


Notes: 
Anything else you want to tell the marker, such as how to use the GUI version of your
program, and something particular about your implementation.

	The Truth Table method has a debug mode that can be invoked at the command line with the "TTd" option instead of the usual "TT". In this mode, the truth states of every literal and every sentence is displayed for every model, and a summary of the sentences, their structure, their object IDs and the number of Sentences and Literals is listed. After the debug messages is the usual TT output, indicating if the ASK query is entailed and if so, in how many models it is true.

Summary report: 
Summary of the teamwork in this assignment. You need to clearly indicate who
did what and how each team member gave feedback to other members. In this report, the overall
percentage of contribution by each student to the project has to be clearly specified and summed to
100%
	The team members contributed to features as follows:
35%		Dylan Forster
			The Main method (including file parsing and output formatting) - iEngine.java, iEngine.bat
			The Truth Method abstract class - truthMethod.java
			The forward chaining class - forwardChaining.java
			The backward chaining class - backwardChaining.java
			The Knowledge Base class - knowledgeBase.java
			Discussion and input on ideas for the sentenceClass Binary Expression Tree concept and derived classes
			Discussion and input on ideas for research component - a general knowledge base inference engine
			
35%		Danvers Flett
			The list of connectives - connectivesClass.java
			The Infix To Postfix Converter - InfixToPostfixClass.java
			The Truth Table class - truthTable.java, truthTableDebug.java
			Discussion and input on ideas for the sentenceClass Binary Expression Tree concept and derived classes
			The Sentence astract Class and derived classes relevant to Horn clauses
				- sentenceClass.java, literalClass.java, connectedSentence.java, implication.java, conjunction.java
			Discussion and input on ideas for research component - a general knowledge base inference engine
			Preparation of the research report
			
30%		Adam Richards
			Discussion and input on ideas for the sentenceClass Binary Expression Tree concept and derived classes
			Discussion and input on ideas for research component - a general knowledge base inference engine
			Derived classes of Sentence Class for the general knowledge base inference engine
				- biconditional.java, disjunction.java, inversion.java
			Modification of the "makeSentenceTree" method to accommodate the non-Horn-clause connectives for the research component - truthTable.java
			Preparation of the README.txt file
			
			
				
