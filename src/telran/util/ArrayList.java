package telran.util;

import java.util.Arrays;
import java.util.function.Predicate;
import static org.junit.jupiter.api.Assertions.*;

public class ArrayList<T> implements List<T> {
	static final int DEFAULT_CAPACITY = 16;
public T [] array;
private int size;
public ArrayList(int capacity) {
	array = (T[])new Object[capacity];
}
public ArrayList() {
	this(DEFAULT_CAPACITY);
}
	@Override
	public boolean add(T element) {
		if(size == array.length) {
			reallocate();
		}
		array[size++] = element;
		return true;
	}
	private void reallocate() {
		array = Arrays.copyOf(array, array.length * 2);
	}

	@Override
	public boolean remove(T pattern) {
		int i = 0;
		while (i < (array.length - 1) && array[i] != pattern) {
			i++;
			}
		if (array[i].equals(pattern)) {
		System.arraycopy(array, i + 1, array, i, size() - (i + 1));
		array = Arrays.copyOf(array, array.length-1);
		}	else  throw new ArithmeticException("pattern is not present");	
		return true;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		for (int i = 0; i < array.length; i++){
			if(predicate.test(array[i]) == true) {
				System.arraycopy(array, (i + 1), array, i, array.length - (i + 1));
				array = Arrays.copyOf(array, array.length-1);
			}
	}
		return true;
	}
	
	@Override
	public boolean isEmpty() {
		if(size() == 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public int size() {
		
		return size;
	}

	@Override
	public boolean contains(T pattern) {
		int index = 0;
		while(index < array.length && !isEqual(array[index], pattern)) {
			index++;
		}
		return index < array.length;
	}
	static private boolean isEqual(Object element, Object pattern) {
		return element == null ? element == pattern : element.equals(pattern);
	}	

	@Override
	public T[] toArray(T[] ar) {
		if (ar.length >= size()) {
			System.arraycopy(array, 0, ar, 0, size());	
		}else { ar = Arrays.copyOf(ar, size());
				System.arraycopy(array, 0, ar, 0, size());
			}
		return ar;
	}

	@Override
	public void add(int index, T element) {
		if (index >array.length) {
			reallocate();
		}
		if(size == array.length) {
			reallocate();
		}
		System.arraycopy(array, index, array, index + 1, size() - index);
		array[index] = element;
	}

	@Override
	public T remove(int index) {
		T res = array[index];
		System.arraycopy(array, index + 1, array, index, size() - (index + 1));
		array = Arrays.copyOf(array, array.length-1);
		return res;
	}

	@Override
	public int indexOf(T pattern) {
		int index = 0;
		if (contains(pattern)) {
			while(index < size && !isEqual(array[index], pattern)) {
				index++;
			}
		} else {index = -1;
			}
		return index;
	}

	@Override
	public int lastIndexOf(T pattern) {
		int index = -1;
			for (int i = 0; i < size(); i++) {
				if (isEqual(array[i], pattern)) {
					index = i;
				}
			}
		return index;
	}

	@Override
	public T get(int index) {
		return array[index];
	}

	@Override
	public void set(int index, T element) {
		array[index] = element;
	}

}