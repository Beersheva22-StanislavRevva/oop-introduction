package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.*;

public class SetTest extends CollectionTest {
	Set<Integer> set ;
	@BeforeEach
	void setUp() throws Exception {
		super.setUp();
		set = (Set<Integer>) collection;
	}

	@Override
	@Test
	void testAdd() {
		assertTrue(set.add(Integer.MAX_VALUE));
		assertFalse(set.add(Integer.MAX_VALUE));

	}

	@Override
	@Test
	void testIterator() {
			Iterator <Integer> it = set.iterator();
			assertThrowsExactly(IllegalStateException.class, ()->it.remove());
			Integer num = it.next();
			assertTrue(collection.contains(num));
			it.remove();
			assertFalse(collection.contains(num));
			
			assertThrowsExactly(IllegalStateException.class, ()->it.remove());
			Iterator<Integer> it1 = collection.iterator();
			
			while(it1.hasNext()) {
				num = it1.next();
			}
			assertTrue(collection.contains(num));
			it1.remove();
			assertFalse(collection.contains(num));
			
			
		}

	}