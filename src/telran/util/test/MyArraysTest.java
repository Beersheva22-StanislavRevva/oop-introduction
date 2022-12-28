package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.util.ArrayList;
import telran.util.MyArrays;

class MyArraysTest {
	Integer numbers[] = {13, 2, -8, 47, 100, 10, -7, 7, 13, 47, 7, -7};
	static final int N_NUMBERS = 10000;
	static final int N_RUNS = 1000;
	String strings[] = {
			"ab", "abm", "abmb", "abmbc"	
		};
	Comparator<Integer> evenOddComparator = this::evenOddCompare;
	@Test
	@Disabled
	void sortTest() {
		String[] strings = {"abcd", "lmn", "zz"};
		String[] expected = {"zz", "lmn", "abcd"};
		
		
		MyArrays.sort(strings, new StringLengthComparator());
		assertArrayEquals(expected, strings);
		
		
	}
	@Test
	@Disabled
	void evenOddTest() {
		
		Integer expected[] = {-8, 2, 10, 100, 47, 47, 13, 13, 7, 7, -7, -7};
		MyArrays.sort(numbers, evenOddComparator);
		assertArrayEquals(expected, numbers);
	}
	@Test
	@Disabled
	void binarySearchTest() {
		String strings[] = {
			"ab", "abm", "abmb", "abmbc"	
		};
		Comparator<String> comp = new StringsComparator();
		assertEquals(0, MyArrays.binarySearch(strings, "ab", comp));
		assertEquals(2, MyArrays.binarySearch(strings, "abmb", comp));
		assertEquals(3, MyArrays.binarySearch(strings, "abmbc", comp));
		assertEquals(-1, MyArrays.binarySearch(strings, "a", comp));
		assertEquals(-3, MyArrays.binarySearch(strings, "abma", comp));
		assertEquals(-5, MyArrays.binarySearch(strings, "lmn", comp));
	}
	@Test
	@Disabled
	void filterTest() {
		int dividor = 2;
		String subStr = "m";
		Predicate<Integer> predEven = t -> t % dividor == 0;
		Predicate<String> predSubstr = s -> s.contains(subStr);
		String expectedStr[] = {
				 "abm", "abmb", "abmbc"	
			};
		Integer expectedNumbers[] ={2, -8, 100, 10};
		assertArrayEquals(expectedStr, MyArrays.filter(strings, predSubstr));
		assertArrayEquals(expectedNumbers, MyArrays.filter(numbers, predEven));
		
	}
	int evenOddCompare(Integer o1, Integer o2) {
		int remainder =  Math.abs(o1) % 2;
		int res = remainder - Math.abs(o2) %2;
		if (res == 0) {
			res = remainder != 0 ? Integer.compare(o2, o1) : Integer.compare(o1, o2);
		}
		return res;
	}
	@Test
	@Disabled
	void removeIfTest() {
		Integer expected[] = {2, -8, 100, 10};
		assertArrayEquals(expected, MyArrays.removeIf(numbers, n -> n % 2 != 0));
	}
	@Test
	@Disabled
	void removeRepeated() {
		Integer expected[] = {13, 2, -8, 47, 100, 10, -7, 7};
		assertArrayEquals(expected, MyArrays.removeRepeated(numbers));
		String strings [] = {"aaa", "cccc", "aaa", "aaa"};
		assertArrayEquals(new String[] {"aaa", "cccc"},MyArrays.removeRepeated(strings) );
		Integer[] numbersRepeatedValues = { 13, 13, 2, -8, -8, 47, 100, 100, 100, 10, 7, 7 , 13};
		Integer expected2[] = { 13,  2, -8,  47, 100, 10, 7};
		assertArrayEquals(expected2, MyArrays.removeRepeated(numbersRepeatedValues));
	}
	@Test
	@Disabled
	void joinFunctionalTest() {
		String expected = "13,2,-8,47,100,10,-7,7,13,47,7,-7";
		assertEquals(expected, MyArrays.join(numbers, ","));
	}
	@Test
	@Disabled
	void joinPerformanceTest() {
		Integer largeArray[] = getLargeNumbersArray();
		for (int i = 0; i < N_RUNS; i++) {
			MyArrays.join(largeArray, ",");
		}
	}
	Integer[] getLargeNumbersArray() {
		Integer[] res = new Integer[N_NUMBERS];
		Arrays.fill(res, 1000);
		return res;
	}
	@Test
	void removeTest() {
		ArrayList<String> list = new ArrayList<String>(3);
		list.add("a");
		list.add("b");
		list.add("c");
		list.remove("a");
		String [] expected = {"b","c"};
		assertArrayEquals(expected,list.array);
		}
	
	@Test
	void removeIfMatchTest() {
		ArrayList<String> list = new ArrayList<String>(7);
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("a");
		list.add("d");
		list.add("a");
		list.add("a");
		String [] expected = {"b","c","d"};
		Predicate<String> predStr = s -> "a".equals(s);
		list.removeIf(predStr);
	}
	
	@Test
	void isEmptyTest() {
		ArrayList<String> list = new ArrayList<String>();
		assertTrue(list.isEmpty());
		list.add("a");
		list.add("b");
		list.add("c");
		assertFalse(list.isEmpty());
		}
	
	@Test
	void containsTest() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		assertTrue(list.contains("a"));
		assertFalse(list.contains("d"));
		}
	
	@Test
	void toArrayTest() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		String [] ar = new String [1];
		String [] expected = {"a","b","c"};
		assertArrayEquals(expected,list.toArray(ar));
	}
	
	@Test
	void addAtIndexTest() {
		ArrayList<String> list = new ArrayList<String>(3);
		list.add("a");
		list.add("b");
		list.add("c");
		list.add(2, "d");
		String [] expected = {"a","b","d","c",null,null};
		assertArrayEquals(expected,list.array);
	}
	
	@Test
	void removeAtIndexTest() {
		ArrayList<String> list = new ArrayList<String>(3);
		list.add("a");
		list.add("b");
		list.add("c");
		assertEquals("b",list.remove(1));
		String [] expected = {"a","c"};
		assertArrayEquals(expected,list.array);
	}
	
	@Test
	void indexOfTest() {
		ArrayList<String> list = new ArrayList<String>(3);
		list.add("a");
		list.add("b");
		list.add("c");
		assertEquals(2,list.indexOf("c"));
		assertEquals(-1,list.indexOf("d"));
	}
	
	@Test
	void lastIndexOf() {
		ArrayList<String> list = new ArrayList<String>(3);
		list.add("a");
		list.add("a");
		list.add("a");
		assertEquals(2,list.lastIndexOf("a"));
		assertEquals(-1,list.indexOf("d"));
	}
	
	@Test
	void getTest() {
		ArrayList<String> list = new ArrayList<String>(3);
		list.add("a");
		list.add("b");
		list.add("c");
		assertEquals("b",list.get(1));
	}
	
	@Test
	void setTest() {
		ArrayList<String> list = new ArrayList<String>(3);
		list.add("a");
		list.add("b");
		list.add("c");
		list.set(2, "a");
		assertEquals("a",list.get(2));
	}
	
	

}