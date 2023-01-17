package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.Random;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.*;

public class TreeSetTest {
	
	protected Integer [] numbers = {10, 100, -5, 134, 280, 120, 15, 1000, -10, 2000};
	TreeSet<Integer> treeSet = new TreeSet<>();
	@BeforeEach
	void setUp() throws Exception {
		
		for(Integer num: numbers) {
			treeSet.add(num);
		}
	}
	
	@Test
	void testAdd() {
		
			assertTrue(treeSet.add(Integer.MAX_VALUE));
			assertFalse(treeSet.add(Integer.MAX_VALUE));

		}
	
	@Test
	void testContains() {
		assertTrue(treeSet.contains(numbers[0]));
		assertFalse(treeSet.contains(Integer.MIN_VALUE));
	}
	
	@Test
	void testIterator() {
		Iterator <Integer> it = treeSet.iterator();
		Integer num = 0;
		while(it.hasNext()) {
			num = it.next();
			assertTrue(treeSet.contains(num));
		}
	}
}

