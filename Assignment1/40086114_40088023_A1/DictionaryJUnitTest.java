package ADTDictionary;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
public class DictionaryJUnitTest {
	
	
	private static ADTDictionary<Integer, String> dict;
	  
	@Before
	public void setUp() {
		//dict= new LLDictionary<Integer, String>(10);
		dict = new DoubleLLDictionary<Integer, String>(10);
		
		//please comment out the one you dont want to test.
	}

	@Test
	public void testClear() {
		dict.clear();
		assertEquals("clear failed, size is non zero", 0, dict.size());
	}

	@Test
	public void testInsert() {
		dict.clear();
		dict.insert(0, "red");
		dict.insert(1, "blue");
		dict.insert(2,"yellow");
		dict.insert(3, "grey");
		assertEquals("insert failed, cant find the element", "grey",dict.find(3));
	}

	@Test
	public void testRemove() {
		
		dict.clear();
		dict.insert(0, "red");
		dict.insert(1, "blue");
		dict.insert(2,"yellow");
		dict.insert(3, "grey");
		
		//assertEquals("1:blue , 2:yellow , 3:grey ,", dict.toString());
		
		assertEquals("remove failed", "blue", dict.remove(1) ); 
		
	}

	@Test
	public void testRemoveAny() {
		dict.clear();
		dict.insert(0, "red");
		dict.insert(1, "blue");
		dict.insert(2,"yellow");
		dict.insert(3, "grey");
		int size = dict.size();
		dict.removeAny();
		int sizeAfterRemoved = dict.size();
		assertNotEquals("the size is equal", size, sizeAfterRemoved); 
	}

	@Test
	public void testFind() {
		
		dict.clear();
		dict.insert(0, "red");
		dict.insert(1, "blue");
		dict.insert(2,"yellow");
		dict.insert(3, "grey");
	
		assertEquals("Find failed", dict.find(3), "grey"); 
	}

	@Test
	public void testSize() {
		dict.clear();
		dict.insert(0, "red");
	
		
		assertEquals("size failed, size is not as expected", 1, dict.size());
	}

}
