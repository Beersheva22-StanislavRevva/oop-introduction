package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.Ignore;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.util.MyArrays;

class MyArraysTest {
	Integer numbers[] = {13, 2, -8, 47, 100, 10, -7, 7};
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
		
		Integer expected[] = {-8, 2, 10, 100, 47, 13, 7, -7};
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
	@Test
	void removeIfTest() {
		Predicate<Integer> pred = t -> t == 100;
		Integer expectedNumbers[] ={13, 2, -8, 47, 10, -7, 7};
		assertArrayEquals(expectedNumbers, MyArrays.removeIf(numbers, pred));
		Predicate<String> predStr = s -> s == "abm";
		String expectedStr[] = {"ab", "abmb", "abmbc"};
		assertArrayEquals(expectedStr, MyArrays.removeIf(strings, predStr));
	}
	
	@Test
	void containsTest() {
		Predicate<Integer> pred = t -> t == 47;
		assertTrue(MyArrays.contains(numbers, pred));
		pred = t -> t == -10;
		assertFalse(MyArrays.contains(numbers, pred));
		Predicate<String> predStr = s -> s == "abmb";
		assertTrue(MyArrays.contains(strings, predStr));
		predStr = s -> s == "lky";
		assertFalse(MyArrays.contains(strings, predStr));
	}
	
	@Test
	void removeRepeatedTest() {
		String strings[] = {"ab", "lmn", "opt", "ab", "re", "ab","lmn"};
		String expectedStr[] = {"ab", "lmn", "opt", "re"};
		assertArrayEquals(expectedStr, MyArrays.removeRepeated(strings));
		Integer numbers[] = {13, 2, -8, 47, 100, -8, -7, -8};
		Integer expectedNumbers[] = {13, 2, -8, 47, 100, -7};
		assertArrayEquals(expectedNumbers, MyArrays.removeRepeated(numbers));
		
	}
	
	int evenOddCompare(Integer o1, Integer o2) {
		int remainder =  Math.abs(o1) % 2;
		int res = remainder - Math.abs(o2) %2;
		if (res == 0) {
			res = remainder != 0 ? Integer.compare(o2, o1) : Integer.compare(o1, o2);
		}
		return res;
	}

}	