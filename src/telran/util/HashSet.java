package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import telran.util.LinkedList.Node;

//import telran.util.LinkedList.Node;

public class HashSet<T> extends AbstractCollection<T> implements Set<T> {
	private static final int DEFAULT_TABLE_SIZE = 16;
	private static final float DEFAULT_FACTOR = 0.75f;
	private List<T> [] hashTable;
	private float factor;
	private class HashSetIterator implements Iterator<T> {
		 boolean flNext = false;
		 int index = 0;
		 int indexOfSize = 0;
		 LinkedList<T> list = (LinkedList<T>) hashTable[0];
		 Node<T> current;
		 
		@Override
		public boolean hasNext() {

			return indexOfSize < size;
		}

		@Override
		public T next() {
			T res;
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			while (list == null) {
				index++;
				list = (LinkedList<T>) hashTable[index];
				current = list.head;
			}
				if (current.next != null) {
					res = current.obj;
					current = current.next;
					indexOfSize++;
					flNext = true;
				} else {
					res = current.obj;
					indexOfSize++;	
					index++;
					list = (LinkedList<T>) hashTable[index];
					current = list.head;
				}
				
				return res;
			}
		
		@Override
		public void remove() {
			
			if(!flNext) {
				throw new IllegalStateException();
			}
			
			if (current.prev != null) {
			Node<T> removedNode = current;
			current = current.prev;
			list.removeNode(removedNode);
			flNext = false;
			indexOfSize--;
			}
			else {
				list.removeNode(current);
				
				while (list.isEmpty() && index != 0) {
					index--;
					list = (LinkedList<T>) hashTable[index];
					current = list.tail;
					
				}
			}
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
	//FIXME The following method is only for initial test
	//after HashTableIterator implementation is done the method should be removed
	@Override
	public T[] toArray(T[] ar) {
		if (ar.length < size) {
			ar = Arrays.copyOf(ar, size);
		}
		int index = 0;
		for (List<T> list: hashTable) {
			if (list != null) {
				for(T obj: list) {
					ar[index++] = obj;
				}
			}
		}
		Arrays.fill(ar, size, ar.length, null);
		return ar;
	}

}