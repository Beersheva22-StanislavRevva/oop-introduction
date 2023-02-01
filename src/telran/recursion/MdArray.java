package telran.recursion;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class MdArray<T> {
private MdArray<T>[] array;
private T value;
public MdArray(int dimensions[], T value) {
	this(dimensions, 0, value);
}
@SuppressWarnings("unchecked")
public MdArray(int[] dimensions, int firstDim, T value) {
	if (firstDim == dimensions.length) {
		this.value = value;
		array = null;
	} else {
		this.value = null;
		array = new MdArray[dimensions[firstDim]];
		for(int i = 0; i < array.length; i++) {
			array[i] = new MdArray<>(dimensions, firstDim + 1, value);
		}
	}
}
public void forEach(Consumer<T> consumer) {
	if(array == null) {
		consumer.accept(value);
	} else {
		for(MdArray<T> mdArray: array) {
			mdArray.forEach(consumer);
		}
	}
}
public T[] toArray(T[] ar) {
	final ArrayList<T> list = new ArrayList<>();
	forEach(list::add);
	return list.toArray(ar);
	
}
public T getValue(int[] indexes) {
	MdArray<T> res = getScalar(indexes);
	return res.value;
}
public void setValue(int[] indexes, T value) {
	MdArray<T> res = getScalar(indexes);
	res.value = value;
}
private MdArray<T> getScalar(int[] indexes ) {
	MdArray<T> res = this;
	try {
		for(int index: indexes) {
			res = res.array[index];
		}
	} catch (NullPointerException e) {
		throw new NoSuchElementException();
	}
	if(res.array != null) {
		throw new IllegalStateException();
	}
	return res;
}
}