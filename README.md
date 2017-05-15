"# introAIassignment2" 

TEST DAN BRANCH 1 yeah this one

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
  
