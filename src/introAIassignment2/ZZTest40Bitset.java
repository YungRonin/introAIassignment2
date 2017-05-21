package introAIassignment2;

import static org.junit.Assert.*;
import org.junit.*;

import java.util.BitSet;
import java.nio.ByteBuffer;

public class ZZTest40Bitset {
	
	@Test
	public void TestBitset() {
		int i = 12;
		int bits = 4;
		BitSet bitset = new BitSet(bits);
		
		bitset.valueOf(ByteBuffer.allocate(bits).putInt(i).array());
		
		System.out.println(bitset.toString());
		
	}

}
