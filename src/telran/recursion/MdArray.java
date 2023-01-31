package telran.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class MdArray<T> {
	
	private MdArray<T>[] array;
	private T value;
	
	public MdArray(Integer dimensions[], T value) {
		this(dimensions, 0, value);
	}
	
	@SuppressWarnings("unchecked")
	public MdArray(Integer[] dimensions, int firstDim, T value) {
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
	
	public void forEach (Consumer<T> consumer) {
		
		forEach(this, consumer);
		
	}
	
	public void forEach (MdArray<T> thisArray, Consumer<T> consumer) {
		if (thisArray.value != null) {
			consumer.accept(thisArray.value);
		 } else {
				 for(int i = 0; i < thisArray.array.length; i++) {
					 
				 forEach (thisArray.array[i], consumer); 
			 }
			
		}	
	}
	
	public T[] toArray(T[] ar) {
		int size = size();
		if (ar.length < size) {
			ar = Arrays.copyOf(ar, size);
		}
		ArrayList<T> arraylist = new ArrayList<>();	
		arraylist = toArraylist();				
		return arraylist.toArray(ar);
	}

	private ArrayList<T> toArraylist() {
		ArrayList<T> arraylist = new ArrayList<>();
		toArraylist(this, arraylist);
		return arraylist;
	}

	private void toArraylist(MdArray<T> thisArray, ArrayList<T> arraylist) {
		if (thisArray.value != null) {
			arraylist.add(thisArray.value);
		} else {
			 for(int i = 0; i < thisArray.array.length; i++) {
				 
				 toArraylist (thisArray.array[i], arraylist); 
			 } 
		}
	}

	public T getValue (Integer [] dimensions) {
		MdArray<T> res = this;
		for (int i = 0; i < dimensions.length; i++) {
			if (res.array == null) {
				throw new NoSuchElementException();
			}
			res = res.array[dimensions[i]];
		}
		if (res.value == null) {
			throw new IllegalStateException();
		}
		
	return res.value;
	}
	
	public void setValue (Integer [] dimensions, T value) {
		MdArray<T> res = this;
		for (int i = 0; i < dimensions.length; i++) {
			if (res.array == null) {
				throw new NoSuchElementException();
			}
			res = res.array[dimensions[i]];
		}
		if (res.value == null) {
			throw new IllegalStateException();
		}	
		res.value = value;
	}
		
	public int size() {
		int size = 0;
		return size = size(this, size);
	}
	
	private int	size(MdArray<T> thisArray, int size) {
		
		Integer[] sum = {0};
		Consumer<T>consumer = num -> sum[0]+=1;
		forEach(consumer);
		return sum[0];
		
	}

}
