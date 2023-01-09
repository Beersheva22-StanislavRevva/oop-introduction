package telran.util;

public interface List<T> extends Collection<T>{
	void add(int index, T element);
	T remove(int index);
	int indexOf(T pattern);
	int lastIndexOf(T pattern);
	T get(int index);
	void set(int index, T element);
	default  void checkIndex(int index, boolean sizeIncluded) {
		int sizeDelta = sizeIncluded ? 0 : 1;
		if (index < 0 || index > size() - sizeDelta) {
			throw new IndexOutOfBoundsException(index);
		}
		
	}
	
	default boolean contains(T pattern) {
		
		return indexOf(pattern) > -1;
	}
	
	default public boolean remove(T pattern) {
		boolean res = false;
		int index = indexOf(pattern);
		if (index > -1) {
			res = true;
			remove(index);
		}
		return res;
	}
}