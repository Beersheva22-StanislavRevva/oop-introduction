package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.Collection;
import telran.util.Map;
import telran.util.Map.Entry;
import telran.util.Set;

abstract class MapTest {
Map<Integer, String> map;
	@BeforeEach
	void setUp() throws Exception {
		map.put(1, "One");
		map.put(2, "Two");
		map.put(3, "Three");
	}

	@Test
	void getTest() {
		assertEquals("One", map.get(1));
		assertNull(map.get(1000));
	}
	@Test
	void putTest( ) {
		assertEquals("One", map.put(1, "���"));
		assertEquals("���", map.get(1));
		assertNull(map.put(4, "Four"));
		assertEquals("Four", map.get(4));
	}
	
	@Test
	void putIfAbsentTest( ) {
		assertNull(map.putIfAbsent(4, "Four"));
		assertEquals("Four", map.putIfAbsent(4, "Four"));
		}
	
	@Test
	void getOrDefaultTest( ) {
		assertEquals("Three", map.getOrDefault(3, "Four"));
		assertEquals("Four", map.getOrDefault(4, "Four"));
		}

	@Test
	void containsKeyTest( ) {
		assertTrue(map.containsKey(3));
		assertFalse(map.containsKey(4));
		}
	
	@Test
	void containsValueTest( ) {
		assertTrue(map.containsValue("Three"));
		assertFalse(map.containsValue("Four"));
		}
	
	@Test
	void valuesTest( ) {
		Collection<String> collection = map.values();
		String[] string = {""};
		String[] expected = {"One","Two","Three"};
		assertArrayEquals(expected,collection.toArray(string));
	}
	
	@Test
	void keySetTest( ) {
		Set<Integer> res = map.keySet();
		assertEquals(1, res.get(1));
		assertNotEquals(1, res.get(2));
	}
	
	@Test
	void SetTest( ) {
		Set<Entry<Integer, String>> res = map.entrySet();
		String[] string = new String[3];
		int i = 0;
		for (Entry<Integer, String> key : res) {
			string[i++] = key.getValue();	
		}
		String[] expected = {"One","Two","Three"};
		assertArrayEquals(expected,string);
	}
	
	@Test
	void removeTest( ) {
		assertTrue(map.containsValue("One"));
		assertTrue(map.containsKey(1));
		map.remove(1);
		assertFalse(map.containsValue("One"));
		assertFalse(map.containsKey(1));
	}
}