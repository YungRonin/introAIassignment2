"# introAIassignment2" 

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
  
