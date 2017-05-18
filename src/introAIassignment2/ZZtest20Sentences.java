package introAIassignment2;

import static org.junit.Assert.*;

import org.junit.*;

public class ZZtest20Sentences {

	@Test
	public void test10literal() {
		// A
		sentenceClass testLiteral = new literalClass("testA");
		
		((literalClass)testLiteral).setValue(true);	// casting abstract type sentenceClass down to concrete type literalClass so we can call setValue()
		
		assertTrue(testLiteral instanceof sentenceClass); 			// is the tested object a subclass of sentenceClass
		assertEquals(literalClass.class, testLiteral.getClass()); 	// is the tested object an instance of it's own type
		assertEquals("testA", ((literalClass)testLiteral).name()); 	// does the name property function correctly
		assertTrue( testLiteral.eval() );							// does the eval property function correctly
	} 
	
	@Test
	public void test20conjunctionOfTwoLiterals() {
		// A&B
		sentenceClass testLiteralA = new literalClass("testA");
		sentenceClass testLiteralB = new literalClass("testB");
		sentenceClass testConjuction = new conjunction(testLiteralA, testLiteralB);

		assertTrue(testConjuction instanceof sentenceClass); 			// is the tested object a subclass of sentenceClass
		assertEquals(conjunction.class, testConjuction.getClass()); 	// is the tested object an instance of it's own type
		
		// set sentenceA and sentenceB to true and test that the result is true
		((literalClass)testLiteralA).setValue(true);
		((literalClass)testLiteralB).setValue(true);
		assertTrue( testConjuction.eval() );	
		
		// set sentenceB to false and test that the result is false
		((literalClass)testLiteralB).setValue(false);		
		assertTrue( !testConjuction.eval() );	
		
	}

	@Test
	public void test30implicationOfTwoLiterals() {
		// A=>B
		sentenceClass testLiteralA = new literalClass("testA");
		sentenceClass testLiteralB = new literalClass("testB");
		sentenceClass testImplication = new implication(testLiteralA, testLiteralB);

		assertTrue(testImplication instanceof sentenceClass); 			// is the tested object a subclass of sentenceClass
		assertEquals(implication.class, testImplication.getClass()); 	// is the tested object an instance of it's own type
		
		// set sentenceA and sentenceB to true and test that the result is true
		((literalClass)testLiteralA).setValue(true);
		((literalClass)testLiteralB).setValue(true);
		assertTrue( testImplication.eval() );	
		
		// set sentenceB to false and test that the result is false
		((literalClass)testLiteralB).setValue(false);		
		assertTrue( !testImplication.eval() );	
		
	}

	@Test
	public void test40implicationOfConjunctionToLiteral() {
		// A&B=>C
		sentenceClass testLiteralA = new literalClass("testA");
		sentenceClass testLiteralB = new literalClass("testB");
		sentenceClass testLiteralC = new literalClass("testC");
		
		sentenceClass testConjuction = new conjunction(testLiteralA, testLiteralB);
		sentenceClass testImplication = new implication(testConjuction, testLiteralC);

		// set literalA, literalB and literalC to true and test that the result is true
		((literalClass)testLiteralA).setValue(true);
		((literalClass)testLiteralB).setValue(true);
		((literalClass)testLiteralC).setValue(true);
		assertTrue( testImplication.eval() );	
		
		// set literalA to false and test that the result is true
		((literalClass)testLiteralA).setValue(false);		
		assertTrue( testImplication.eval() );
		
		// set literalA and literalB to true and literalC to false and test that the result is false
		((literalClass)testLiteralA).setValue(true);
		((literalClass)testLiteralB).setValue(true);
		((literalClass)testLiteralC).setValue(false);
		assertTrue( !testImplication.eval() );	
		
	}

}
