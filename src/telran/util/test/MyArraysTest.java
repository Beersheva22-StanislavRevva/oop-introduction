package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;

import telran.util.MyArrays;

class MyArraysTest {

	@Test
	@Disabled
	void sortTest() {
		String[] strings = {"abcd", "lmn", "zz"};
		String[] expected = {"zz", "lmn", "abcd"};
		MyArrays.sort(strings, new StringLengthComparator());
		assertArrayEquals(expected, strings);
	}
	
	@Test 
	void evenOddTest() {
	Integer numbers[] = {13, 2, -8, 47, 100, 10, 7};
	Integer expected[] = {-8, 2, 10, 100, 47, 13, 7};
	MyArrays.sort(numbers, new EvenOddComparator());
	EvenOddSort(numbers);
	numbers = EvenOddSort(numbers);
	assertArrayEquals(expected, numbers);
	}
	
	private Integer[] EvenOddSort(Integer[] numbers) {
		Integer numbersEvenOddSort[] = new Integer [numbers.length];
		int j = 0;
		numbersEvenOddSort = EvenSort(numbers,numbersEvenOddSort,j);
		j = numbers.length - 1;
		numbersEvenOddSort = OddSort(numbers,numbersEvenOddSort,j);
		return numbersEvenOddSort;
	}

	private Integer[] OddSort(Integer[] numbers, Integer[] numbersEvenOddSort, int j) {
		for (int i=0; i < numbers.length; i++) {
			if (numbers[i]%2 != 0) {
				numbersEvenOddSort[j] = numbers[i];
				j--;
				}
		}
		return numbersEvenOddSort;
	}

	private Integer[] EvenSort(Integer[] numbers, Integer[] numbersEvenOddSort, int j) {
		for (int i=0; i < numbers.length; i++) {
			if (numbers[i]%2 == 0) {
				numbersEvenOddSort[j] = numbers[i];
				j++;
			}
		}
	return numbersEvenOddSort;
	}
	
	@Test
	void binarySearchTest() {
		Integer array[] = {-5, -1, 1, 2, 3, 4, 5, 6, 7, 100, 1000, 10000};
		Integer searchedNumber = 100;
		assertEquals(9, MyArrays.binarySearch(array,searchedNumber,new EvenOddComparator())); // array is sorted, searchedNumber exist in the array - return index
		
		searchedNumber = 55;
		assertEquals(-1, MyArrays.binarySearch(array,searchedNumber,new EvenOddComparator())); // array is sorted, searchedNumber isn't exist in the array - return -1
		array = new Integer[] {-5, -1, 1, 10, 12, 4, 5, 6, 7, 100, 1000, 10000};
		
		searchedNumber = 100;
		assertEquals(-1, MyArrays.binarySearch(array,searchedNumber,new EvenOddComparator())); // array is not sorted, searchedNumber exist in the array - return -1
	}
	
}
