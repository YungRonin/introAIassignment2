"# introAIassignment2" 

2017-05-25 - Adam Richards

Hey guys I think we need to join a team in the submission portal: https://esp.ict.swin.edu.au/
I created one, please join: COS30019_A02_T026

Do we have anything else to expand upon for the features/bug/missing point?

2017-05-25b - Dan Flett

I've updated as much as I can the README.txt file and given attribution for our contributions. Please edit this to suit yourselves. Dylan, can you add some notes about the your influences and inspiration for the FC and BC methods please?

2017-05-25a - Dan Flett

I've just made some minor tweaks to the main code and have added some clearer debug messages to the output of the TruthTableDebug method. You can all this method with "ttd" at the command-line and it prints out all the info you could possibly need to understand what's going on with the Truth Table. :) I'll use this in the report.

I'll be finished the report tonight. I'm working all day tomorrow (Friday) so I won't be able to do much more then. I should be able to get a moment to submit my work though. I guess once the research report and the README.txt file is done we zip up this repository and each submit it? If the README.txt is finished tonight, we're ready to submit the whole thing.

Can we compile the iengine application as a static .exe file? It would be good to have a working exe file in the root directory of zip.

Also, we need to remember to remove this README.md from the zip before we submit it! :) We should remove the test .txt files too.


2017-05-23 - Adam Richards

Hi Dan, thanks for the info,

I have added disjunction, biconditional and inversion classes to Adam's-branch-2 and added some code to truthtable.java
Please let me know if I'm on the right track, or my lack of sleep is getting to me and I'm going completely wrong/missed something completely.
I will have a look at it again tomorrow and do some testing, I will also start adding to the README.txt


2017-05-23 - Dan Flett

OK guys I think we have a working program. The original algorithm below in the README.md wasn't quite right. I had some issues because I was making the implication objects with the left and right the wrong way around and because I wasn't testing against the ASK query. I think it's all good now - test1.txt gives the output "YES: 3" which is what it's supposed to.

Adam, doing the generic TT evaluator is pretty straightforward from here.

You need to make:
disjunction extends connectedSentence

biconditional extends connectedSentence

inversion extends sentenceClass // this takes one sentence

and in truthTable.java look for "// NEW OPERATORS GO HERE" for where to put the code to handle the new operators. Remembering that the inversion (NOT) only pops one symbol off the stack.

You don't need to make classes for parenthesis because the sentence objects are created from a postfix-formatted sentence string, which eliminates the need for parentheses.

Look in connectivesClass to see examples of the symbols to use for disjunction, biconditional and inversion. Then make up some test text files. don't put too many literals in them, this makes the truth table too big to effectively debug.

If you need help, let me know. I've put some debug methods into the sentenceClass derived classes which help you follow the logic

I'll start writing the research report PDF tomorrow and I'll put our names on it.






2017-05-21 - Dan Flett
Hi guys,
I've decided that I'm going to use my sentence classes and tree structure after all to implement the truth table. The reason being is that these classes are able to make the task of associating a literal with it's boolean value easy. This wasn't necessary for BC and FC but it is with with TT. I can do a postfix evaluator that just deals with the literals and operators as strings but each time I'll have to look up the string in a list and then cross-reference it to the value it's supposed to have. The Sentence Classes do this automatically. Also, I can use the postfix evaluation algorithm once to create the sentence tree structure once per program execution. If I don't do that, I have to run the postix evaluation on every row of the Truth Table, and every time I encounter a literal, I have to do a list lookup. I haven't started coding this yet but I've been doing a rough pseudo code:

Turn postfix into sentence tree
Stack is a stack of sentences

	CREATE SENTENCE TREE USING POSTFIX EVALUATION ALGORITHM
	Get symbol in sentence
		if literal,
			if literal already exists in literalList
				get literal object from list and push to stack
			else
				create literal object from string
				add new literal object to literalList
				push new literal object to stack

			
		if 2-input operator,
			Pop 2 sentences,
			create operator class from string
			create new sentence based on operator type,
			push to stack
		if 1-input operator (i.e. NOT),
			Pop 1 sentence,
			create new sentence based on operator type,
			push to stack
	get next symbol

	TRUTH TABLE
	NumOfLiterals = length of literals array
	TTRows = 2 ^ literals
	NumOfTrueWorlds = TTRows // we decrement each time we find a false KB sentence - we move onto the next TT Row (world)
	FOR ROW = 0 to TTRows
		// LiteralVals uses the "toBinaryString" method to create an array of strings that are either 0 or 1.
		// I'll update my "setvalue" method in the literal class to accept "0" or "1" strings as well as boolean true or false
		String[] LiteralVals = String.format("%"+bits+"s", Integer.toBinaryString(ROW)).replace(' ', '0').split("");
		// set all the boolean values of the literals in the KB
		FOR i = 0 to NumOfLiterals-1
			Literal[i].Setvalue(LiteralVals[i])
		// Evaluate each sentence in KB
		IF ASKED QUERY IS TRUE
			FOR EACH SENTENCE IN KB.SENTENCES
				IF !SENTENCE.EVAL
					NumOfTrueWorlds-- // we found a false sentence in this row (world) so decrement the TrueWorlds counter
					BREAK // stop checking the sentences on this row (world) of the TT, move on to the next row
		ELSE
			NumOfTrueWorlds--
	Return NumOfTrueWorlds
		
We can have a chat about this tomorrow and I'll code this up tomorrow night. Once it works with Horn Form sentences (where the only operators are "&" and "=>", it should be fairly simple to add all the other operator classes.

2017-05-20 - Adam Richards

Hey guys I have uploaded a starting base for the final README file. I was planning to work on FC and BC today but just realised Dylan has finished both, and you seem to be working on TT Dan?

I guess I will start with trying to implement a generic logic statement evaluator.


2017-05-20 - Dan Flett

Nice work Dylan! That's a really cool implementation of FC and BC. Much simpler and cleaner than what I was considering.

I believe I can do the Truth Table in a simpler way than the tree structure I was originally going to do. I think I'll be able to make a postfix evaluator that will work with Horn clauses and and generic clauses. So in dan-branch-2 I've dropped all the sentenceClass classes. I think I can just have a list of literals that also stores their boolean values as a Map<String,Boolean>. When the evaluator encounters a literal, it looks it up in the Map to obtain it's boolean value (so it's doing a Key,Value lookup). Setting the values in the truth table is as simple as iterating through the list of literals and setting each boolean true or false as required.

So with the literals having their boolean values set, I'll be able to put a sentence into the postfix evaluator and return a boolean result.

I've just discovered the java Bitset class, which looks like it's perfect for converting an integer or long to an array of booleans. I think this is how to set each "possible world" or row of the truth table. I'm investigating now.

2017-05-20 - Dylan Forster
Hi guys,

I've implemented forward and backward chaining as well as the knowledgeBase class and modified main to run both,
at the moment we still need to:

* Create a truth table (the size = 2 ^ number of literals in KB)
  - evaluate each line of the TT to determine which sentences are true for each line of the TT
* Do a generic logic statement evaluator for the research component
* create final README file for submission

I havent used the sentence, literal, implication, connectedSentence or infixToPostfix classes in this implementation, we can refactor knowledgebase/fwdChian & bkwdChain to use these if desired.
I've merged the changes to master and Dan's branch (just incase you forgot to merge with master) so you dont wast time on the same code.

2017-05-10 - Dan Flett
Hi guys,

I've implemented the sentenceClass as an abstract classes, and this includes the classes:
*	literalClass - contains a literal symbol which contains a name and a boolean value (which can be set)
*	connectedSentence abstract class which extends the sentence class - this contains two sentences - A and B
*	conjunction is a concrete connectedSentence which when eval is called, returns A AND B
*	implication is a concrete connectedSentence which when eval is called, returns A => B

I've made unit tests that test the above classes

I've also implemented an Infix To Postfix parser for the symbols we will use (including for a generic logic sentence parser)
So if you give it the string" "p1&p3 => c" (infix notation)
it will return: "p1 p3 & d =>" (postfix notation) as a string or as a LinkedList of strings (currently outputs to console but I'll change that shortly)

With the sentence in postfix notation it is a lot easier to calculate - you just calculate from left to right. There are some easy algorithms online to evaluate postfix notation.

It is also a lot easier to put the sentence into a tree format - again you evaluate from left to right. I believe by putting the sentences into tree format it will be easier to do Forward and Backward chaining.

TO DO:
* parse the TELL line (the Knowledgebase) into a list of sentences and a list of literals (the literals being pointers to the same literals in the Knowledgebase)
* Put the sentences into tree format
* Evaluate the sentences
* Create a truth table (the size = 2 ^ number of literals in KB)
  - evaluate each line of the TT to determine which sentences are true for each line of the TT
* Do FC and BC algorithms
* Do a generic logic statement evaluator for the research component
* create final README file for submission




2017-05-10 - Dan Flett
Dan's ideas for a data structure:

Class: KnowledgeBase - a list (or some other collection) of Sentences

The Knowledgebase constains a list of sentences - the represents the actual "knowledge" of the knowledgebase
- As sentences are added to the knowledgebase, the literals in those sentences should be added to a separate list of literals
- this list of literals allows them to be set to true or false so the KB can be tested against any possible combination of values
- this list of literals is NOT part of the "knowledge" of the KB - it is only for programming convenience - when testing via truth table or forward/backward chaining, do not test against this list

Knowledgebase contains a list of sentences, and a list of the literals contained in those sentences (

abstract class Sentence
  // contains one literal or a literals and/or sentences separated by connectives
  // need to work out a way of exposing all the literals in a sentence and assigning them bool values - a separate list (index of KB literals, which not part of the KB itself)
  // need to work out how to parse the TELL line and create the Sentence Data Structure
  
  abstract bool eval()
    // returns the boolean value of the sentence provided the literals have assigned values
     

class Literal extends Sentence
  //just stores the the name of the literal (symbol) and it's bool value
  bool Eval()
    returns the bool value of the literal
    
class Conjunction extends Sentence
  // stores an arbitrary number of literals (symbols)
  bool Eval()
    returns the bool value of all literals ANDed together
    
class Implication
  // stores two sentences, A and B
  bool Eval()
    returns the bool value of NOT(Sentence A AND NOT Sentence B)
  
