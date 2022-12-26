package telran.util;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

public class MyArrays {
static public <T> void sort(T[] objects, Comparator<T> comparator) {
	int length = objects.length;
	do {
		length--;
	}while(moveMaxAtEnd(objects, length, comparator));
	
}

private static <T> boolean moveMaxAtEnd(T[] objects, int length, Comparator<T> comp) {
	boolean res = false;
	for(int i = 0; i < length; i++) {
		if(comp.compare(objects[i], objects[i + 1]) > 0) {
			swap(objects, i, i + 1);
			res = true;
		}
	}
	return res;
}

private static <T> void swap(T[] objects, int i, int j) {
	T tmp = objects[i];
	objects[i] = objects[j];
	objects[j] = tmp;
	
}

public static <T> int binarySearch(T[] arraySorted, T key, Comparator<T> comp) {
	int left = 0;
	int right = arraySorted.length - 1;
	int middle = right / 2;
	while(left <= right && !arraySorted[middle].equals(key)) {
		if (comp.compare(key,arraySorted[middle]) < 0) {
			right = middle - 1;
		} else {
			left = middle + 1;
		}
		middle = (left + right) / 2;
	}
	return left > right ? -left - 1 : middle;
}

public static<T> T[] filter(T[] array, Predicate<T> predicate) {
	int countPredicate = getCountPredicate(array, predicate);
	
	T[] res = Arrays.copyOf(array, countPredicate);
	int index = 0;
	for(T element: array) {
		if(predicate.test(element)) {
			res[index++] = element;
		}
	}
	
	return res;
}

private static <T> int getCountPredicate(T[] array, Predicate<T> predicate) {
	int res = 0;
	
	for(T element: array) {
		if(predicate.test(element)) {
			res++;
		}
	}
	return res;
}
public static <T> T[] removeIf(T[] array, Predicate<T> predicate) {
		T[] res = filter(array, predicate.negate());
	return res;
}
public static <T> T[] removeRepeated(T[] array) {
	T[] res = Arrays.copyOf(array, array.length);
	int i = 0;
	for (i = 1; i < array.length; i++) {
		res[i] = null;
	}
	int j = 1;
	i = 1;
	for (i = 1; i < array.length; i++) {
		T tmp = array[i];
		Predicate<T> pred = s -> s == tmp;
		if (contains(res, pred) == false) {
			res[j] = array[i];
			j++;
		}
		else {
			res = Arrays.copyOf(res, res.length-1);
		}
	}
	return res;
	}
public static <T> boolean contains(T[] array, Predicate<T> pattern) {
	boolean res = true;
	T[] resArray = filter(array, pattern);
	if (resArray.length == 0) {
	res = false;
	}
	return res;
}



}