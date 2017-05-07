"# introAIassignment2" 

Dan's ideas for a data structure:

Class: KnowledgeBase - a list (or some other collection) of Sentences

abstract class Sentence
  // contains one literal or a literals and/or sentences separated by connectives
  // need to work out a way of exposing all the literals in a sentence and assigning them bool values
  // need to work out how to parse the TELL line and create the Sentence Data Structure
  
  abstract bool eval()
    // returns the boolean value of the sentence provided the literals have assigned values
     

class Literal extends Sentence
  //just stores the the name of the literal and it's bool value
  bool Eval()
    returns the bool value of the literal
    
class Conjunction extends Sentence
  // stores two sentences, A and B
  bool Eval()
    returns the bool value of Sentence A AND Sentence B
    
class Implication
  // stores two sentences, A and B
  bool Eval()
    returns the bool value of NOT(Sentence A AND NOT Sentence B)
  
