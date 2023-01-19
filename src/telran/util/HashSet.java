package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashSet<T> extends AbstractCollection<T> implements Set<T> {
	private static final int DEFAULT_TABLE_SIZE = 16;
	private static final float DEFAULT_FACTOR = 0.75f;
	private List<T> [] hashTable;
	private float factor;
	private class HashSetIterator implements Iterator<T> {
		int current = 0;
		int indexOfList = 0;
		T currentElement = null;
		List<T> currentList = null;
		Iterator<T> itList = null;
		boolean flNext = false;
		int oldSIze = size();
		@Override
		public boolean hasNext() {
			return current < oldSIze;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			currentElement = nextElement();
			current++;
			flNext = true;
			return currentElement;
		}

		private T nextElement() {
			if (currentList == null || !itList.hasNext()) {
				currentList = nextList();
				itList = currentList.iterator();
			}
			return itList.next();
		}

		private List<T> nextList() {
			boolean fl = false;
			List<T> res = null;
			while (!fl && indexOfList < hashTable.length) {
				if (hashTable[indexOfList] != null) {
					res = hashTable[indexOfList];
					fl = true;
				}
				indexOfList++;
			}
			return res;
		}

		@Override
		public void remove() {
			if (!flNext) {
				throw new IllegalStateException();
			}
			HashSet.this.remove(currentElement);
			flNext = false;
		}
	}
	@SuppressWarnings("unchecked")
	public HashSet(int tableSize, float factor) {
		if(tableSize < 1) {
			throw new IllegalArgumentException("Wrong initial size of Hash Table");
		}
		if(factor < 0.3 || factor >1) {
			throw new IllegalArgumentException("Wrong factor value");
		}
		hashTable = new List[tableSize];
		this.factor = factor;
	}
	public HashSet() {
		this(DEFAULT_TABLE_SIZE, DEFAULT_FACTOR);
	}
	@Override
	public boolean add(T element) {
		if (size >= hashTable.length * factor) {
			tableRecreation();
		}
		int index = getHashIndex(element);
		List<T> list = hashTable[index];
		boolean res = false;
		if (list == null) {
			list = new LinkedList<>();
			hashTable[index] = list;
		}
		if (!list.contains(element)) {
			res = true;
			list.add(element);
			size++;
		}
		

		return res;
	}

	private void tableRecreation() {
		HashSet<T> hashSet = new HashSet<>(hashTable.length * 2, factor);
		for (List<T> list: hashTable) {
			if (list != null) {
				for(T obj: list) {
					hashSet.add(obj);
				}
			}
		}
		hashTable = hashSet.hashTable;
		
	}
	private int getHashIndex(T element) {
		
		return Math.abs(element.hashCode()) % hashTable.length;
	}
	@Override
	public boolean remove(T pattern) {
		int index = getHashIndex(pattern);
		boolean res = false;
		if (hashTable[index] != null) {
			res = hashTable[index].remove(pattern);
			if (res) {
				size--;
				if(hashTable[index].isEmpty()) {
					hashTable[index] = null;
				}
			}
		}
		
		return res;
	}

	

	

	@Override
	public boolean contains(T pattern) {
		boolean res = false;
		int index = getHashIndex(pattern);
		if (hashTable[index] != null) {
			res = hashTable[index].contains(pattern);
		}
		return res;
	}

	@Override
	public Iterator<T> iterator() {
		
		return new HashSetIterator();
	}
	
	

}