package telran.util;

import java.util.Comparator;

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

public static <T> Integer binarySearch (T[] array, T searchedNumber, Comparator<T> comp) {
int left = 0;
int right = array.length - 1;
int middle = array.length / 2;
int res = 0;
if (isSorted (array, comp) == true) {
	while(left <= right && comp.compare(array[left], searchedNumber) != 0) {
		if (comp.compare(array[middle], searchedNumber) >= 0) {
			right = middle - 1;
		} else {
			left = middle + 1;
		}
	middle = (left + right) / 2;
	}
	if (left < array.length && comp.compare(array[left], searchedNumber) == 0) {
	res = left;
		} else {
			res = -left - 1;
		}
	if (comp.compare(array[left], searchedNumber) == 0) {
		return res;
	} else {
		return -1;
	}
}
else {
	return -1;
	}	
}

private static <T> boolean isSorted(T[] array, Comparator<T> comp) {
boolean res = true;
int i = 0;
while (i < array.length - 1) {
	if(comp.compare(array[i], array[i + 1]) > 0) {
		res = false;
	}
i++;
}
	return res;
}
}
