package telran.util.test;





import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.*;

class ArrayListTest extends ListTest{
	@BeforeEach
	@Override 
	void setUp() throws Exception {
		collection = new ArrayList<>(2);
		super.setUp();
	}
	@Override
	@Test
	void removeIteratorTest() {
	      ArrayList<String> strings = new ArrayList<>();
	      strings.add("aaa");
	      strings.add("bbb");
	      Iterator<String> it = strings.iterator();
	      it.next(); it.remove(); it.next(); it.remove();
	      assertTrue(strings.isEmpty());
	      super.removeIteratorTest();
	  }
	
	
}