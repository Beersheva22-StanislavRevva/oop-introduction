package telran.recursion.tests;

import static org.junit.jupiter.api.Assertions.*;
import static telran.recursion.LinearRecursion.power;

import java.util.NoSuchElementException;
import java.util.function.Consumer;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.recursion.MdArray;

class MdArrayTest {

	@Test
	void forEachTest() {
		MdArray<Integer> array = new MdArray<>(new Integer[]{10,5,7}, 50);
		Integer[] sum = {0};
		Consumer<Integer> consumer = num -> sum[0]+=num;
		array.forEach(consumer);
		assertEquals(17500, sum[0]);
		MdArray<String> strings = new MdArray<>(new Integer[]{3, 15, 7, 2, 10}, "hello"); 
		Integer[] length = {0};
		Consumer<String> consumer2 =(str -> length[0] += str.length());
		strings.forEach(consumer2);
		assertEquals(31500, length[0]);
	}
	
	@Test
	void toArrayTest() {
		MdArray<Integer> array = new MdArray<>(new Integer[]{10,5,7}, 50);
		Integer [] numbers = array.toArray(new Integer[0]);
		Integer [] expected = new Integer [350];
		for (int i = 0; i < expected.length; i++) {
			expected[i] = 50;
		}
		assertArrayEquals(expected,numbers);
		MdArray<String> strings = new MdArray<>(new Integer[]{3, 15, 7, 2, 10}, "hello");
		String[] stringsArray = strings.toArray(new String[0]);
		String [] expected2 = new String [6300];
		for (int i = 0; i < expected2.length; i++) {
			expected2[i] = "hello";
		}
		assertArrayEquals(expected2,stringsArray);
	}
	
	@Test
	void getValueTest() {
		MdArray<Integer> array = new MdArray<>(new Integer[]{10,5,7}, 50);
		assertEquals(50,array.getValue(new Integer[]{3,4,6}));
		assertThrowsExactly(IllegalStateException.class, ()->array.getValue(new Integer[]{3,4}));
		assertThrowsExactly(NoSuchElementException.class, ()->array.getValue(new Integer[]{3,4,6,0}));
		assertThrowsExactly(ArrayIndexOutOfBoundsException.class, ()->array.getValue(new Integer[]{3,4,7}));
	}
	
	@Test
	void setValueTest() {
		MdArray<Integer> array = new MdArray<>(new Integer[]{10,5,7}, 50);
		array.setValue(new Integer[]{3,4,6},100);
		assertEquals(100,array.getValue(new	Integer[]{3,4,6}));
		assertThrowsExactly(IllegalStateException.class, ()->array.setValue(new Integer[]{3,4},100));
		assertThrowsExactly(NoSuchElementException.class, ()->array.setValue(new Integer[]{3,4,6,0},100));
		assertThrowsExactly(ArrayIndexOutOfBoundsException.class, ()->array.setValue(new Integer[]{3,4,7},100));
	}
	
}
