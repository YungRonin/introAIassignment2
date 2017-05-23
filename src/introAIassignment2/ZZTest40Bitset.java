package introAIassignment2;

//import static org.junit.Assert.*;
import org.junit.*;

import java.util.Arrays;
//import java.util.BitSet;
//import java.nio.ByteBuffer;

public class ZZTest40Bitset {
	
	@Test
	public void TestBitset() {
		int i = 13;
		int bits = 9;
//		BitSet bitset = new BitSet(bits);
		
//		bitset.valueOf(ByteBuffer.allocate(bits).putInt(i).array());

//		Boolean[] bools = Arrays.str
//		System.out.println(bitset.toString());
//		Boolean.val
//		String[] boolArray = Integer.toBinaryString(i).split("");
		String[] boolArray = String.format("%"+bits+"s", Integer.toBinaryString(i)).replace(' ', '0').split("");
		System.out.println(Arrays.toString(boolArray));
		System.out.println(boolArray.length);
		// binary string goes into the array in reverse order - index 0 is MSB, index length-1 is LSB
		System.out.println(boolArray[boolArray.length-2]);

	}

}
